package angel.anicamod.tileentity;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import angel.anicamod.AnicaModItems;
import angel.anicamod.blocks.AnicaOre;
import angel.anicamod.containers.AnicaBatteryContainer;
import angel.anicamod.containers.AnicaGeneratorContainer;
import angel.anicamod.items.RainItem;
import angel.anicamod.util.helpers.AnicaConfig;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;

/*
 * AnicaFurnaceTileEntity
 * 
 * Description: Here we are adding functionality to our block. This is done with a tile entity.
 * Because we know we're going to need a tile entity that does some work we implement 
 * ITickableTileEntity so that our tile entity gets notified every tick.
 * 
 */

public class AnicaBatteryTileEntity extends TileEntity  implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<AnicaItemStackHandler> handler = LazyOptional.of(this::createHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
    
    private AnicaItemStackHandler anicaItemStackHandler =  null;
    
    private AnicaBatteryContainer anicaBatteryContainer = null;
    
    private int tickCount = 0;
    
    private static boolean debug = true;
    
	public AnicaBatteryTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		if (AnicaBatteryTileEntity.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryTileEntity: Contructor 1 " + tileEntityTypeIn.toString() );
	}

	public AnicaBatteryTileEntity() {
		this(AnicaModBlocks.anica_battery_tile_entity);
		if (AnicaBatteryTileEntity.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryTileEntity: Contructor 2 " + AnicaModBlocks.anica_battery_tile_entity.getRegistryName() );
	}
	
	/*
	 * read(CompoundNBT tag
	 * 
	 * Description: To make sure our inventory gets stored when Minecraft saves and restored when 
	 * it loads we also have to implement read() and write() in our tile entity
	 * 
	 */
	
	@Override
	public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(invTag));
        
        CompoundNBT energyTag = tag.getCompound("energy");
        energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(energyTag));
        
        tickCount = tag.getInt("counter");
        
        super.read(tag);
        
		if (AnicaBatteryTileEntity.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryTileEntity: read TAG " + invTag.toString() );
	}

	/*
	 * CompoundNBT write(CompoundNBT tag)
	 * 
	 * Description: To make sure our inventory gets stored when Minecraft saves and restored when 
	 * it loads we also have to implement read() and write() in our tile entity
	 * 
	 */
	
	@Override
	public CompoundNBT write(CompoundNBT tag) {
       handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            tag.put("inv", compound);
        });
        
        energy.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            tag.put("energy", compound);
        });

        tag.putInt("counter", tickCount);
        
		if (AnicaBatteryTileEntity.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryTileEntity: write TAG " + tag.toString() );
        return super.write(tag);
	}
    
	/*
	 * createHandler()
	 * 
	 * Description: Add an item handler so that our block has an inventory. Restrict items 
	 * supported. Our handler can only contain diamonds
	 * 
	 */
	
	private AnicaItemStackHandler createHandler()
	{
		anicaItemStackHandler = new AnicaItemStackHandler( this, 3 );
		if (AnicaBatteryTileEntity.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryTileEntity: " + anicaItemStackHandler.getSlots() );
		return anicaItemStackHandler;
	}
    
    /*
     * LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
     * 
     * Description: To associate the inventory with our tile entity we use a Forge system called 
     * capabilities. Using capabilities you can attach various things (energy, inventories, ...) 
     * to tile entities, entities and so on. To give our tile entity the item handler capability 
     * you have to override getCapability and return the handler.
     * 
     */
    
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }
    
    /*
     * Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity)
     * 
     * Description: This is responsible for creating the server-side version of the container.
     * 
     */
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
    	if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryTileEntity: createMenu " );
    	anicaBatteryContainer = new AnicaBatteryContainer(i, world, pos, playerInventory, playerEntity);
    	
    	return anicaBatteryContainer;
    }

	@Override
	public ITextComponent getDisplayName() {
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryTileEntity: getDisplayName " + getType().getRegistryName().getPath());
		return new StringTextComponent(getType().getRegistryName().getPath());
	}

	@Override
	public void tick() {
        if (world.isRemote) {
        	// if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryTileEntity: world is remote - no tick for you" );
            return;
        }
        handler.ifPresent(h -> {
        	ItemStack inputStack = h.getStackInSlot(0);
        	
        	if (inputStack != null )
        	{
        		if ( inputStack.getItem().toString().compareTo( AnicaMod.RAIN_ITEM ) == 0 )
        			{
        			
        	        energy.ifPresent(energy -> {
        	            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
        	            if (capacity.get() > 0) {
        	            	
            				int stackCount = inputStack.getCount();
            				String stackDisplayName = inputStack.getDisplayName().toString();
            			
            				if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "RainItem:inventoryTick: stackCount " + stackCount);
            				if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "RainItem:inventoryTick: stackDisplayName " + stackDisplayName);
            					
            				if ( inputStack.getTag() == null ) { inputStack.setTag( new CompoundNBT() ); inputStack.getTag().putInt("egy", 0 ); }
            			
            				if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "RainItem:inventoryTick: before getInt " + inputStack.getTag().getInt("egy"));
            		
            				inputStack.getTag().putInt("egy", inputStack.getTag().getInt("egy")+ 10 );
            			
            				if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "RainItem:inventoryTick: after getInt " + inputStack.getTag().getInt("egy"));
           
            				h.setStackInSlot(0, inputStack );
            				anicaBatteryContainer.putStackInSlot(0, inputStack );
            				
        	            	((AnicaEnergyStorage) energy).consumeEnergy(10);
                            markDirty();
        	            }
        	        });
        				       
        			}
        				
        	}
        });
        

	}
    
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		super.onDataPacket(net, pkt);
		if (AnicaBatteryTileEntity.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryTileEntity: onDataPacket " + pkt.toString());
	}
	
    private IEnergyStorage createEnergy() {
        return new AnicaEnergyStorage(100000, 0, true);
    }
}
