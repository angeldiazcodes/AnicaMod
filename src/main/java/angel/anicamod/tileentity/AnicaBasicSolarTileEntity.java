package angel.anicamod.tileentity;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import angel.anicamod.containers.AnicaBasicSolarContainer;
import angel.anicamod.util.helpers.AnicaConfig;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.model.BlockPart;
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

public class AnicaBasicSolarTileEntity extends TileEntity  implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<AnicaItemStackHandler> handler = LazyOptional.of(this::createHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
    
    private AnicaItemStackHandler anicaItemStackHandler =  null;
    
    private AnicaBasicSolarContainer anicaBasicSolarContainer = null;
    
    private int tickCount = 0;
    
    private static boolean debug = false;
    
	public AnicaBasicSolarTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		AnicaMod.log(debug, "AnicaBasicSolarTileEntity: Contructor 1 " + tileEntityTypeIn.toString() );
	}

	public AnicaBasicSolarTileEntity() {
		this(AnicaModBlocks.anica_basic_solar_tile_entity);
		AnicaMod.log(debug, "AnicaBasicSolarTileEntity: Contructor 2 " + AnicaModBlocks.anica_basic_solar_tile_entity.getRegistryName() );
	}
	
	/*
	 * read(CompoundNBT tag
	 * 
	 * Description: To make sure our inventory gets stored when Minecraft saves and restored when 
	 * it loads we also have to implement read() and write() in our tile entity
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(invTag));
        
        CompoundNBT energyTag = tag.getCompound("energy");
        energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(energyTag));
        
        tickCount = tag.getInt("counter");
        
        super.read(tag);
        
        AnicaMod.log(debug,"AnicaBasicSolarTileEntity: read TAG " + invTag.toString() );
	}

	/*
	 * CompoundNBT write(CompoundNBT tag)
	 * 
	 * Description: To make sure our inventory gets stored when Minecraft saves and restored when 
	 * it loads we also have to implement read() and write() in our tile entity
	 * 
	 */
	
	@SuppressWarnings("unchecked")
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
        
        AnicaMod.log(debug, "AnicaBasicSolarTileEntity: write TAG " + tag.toString() );
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
		AnicaMod.log(debug,"AnicaBasicSolarTileEntity: " + anicaItemStackHandler.getSlots() );
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
    	AnicaMod.log(debug, "AnicaBasicSolarTileEntity: createMenu " );
    	anicaBasicSolarContainer = new AnicaBasicSolarContainer(i, world, pos, playerInventory, playerEntity);
    	
    	return anicaBasicSolarContainer;
    }

	@Override
	public ITextComponent getDisplayName() {
		AnicaMod.log(debug, "AnicaBasicSolarTileEntity: getDisplayName " + getType().getRegistryName().getPath());
		return new StringTextComponent(getType().getRegistryName().getPath());
	}

	@Override
	public void tick() {
        if (world.isRemote) {
        	AnicaMod.log(debug, "AnicaBasicSolarTileEntity: world is remote - no tick for you" );
            return;
        }
        
        if (tickCount > 0) {
        	tickCount--;
            if (tickCount <= 0) {
            	energy.ifPresent(e -> ((AnicaEnergyStorage) e).addEnergy(AnicaConfig.ANICA_GENERATOR_GENERATE.get()));
            }
            markDirty();
        } 
       
        if( this.world.canBlockSeeSky(this.getPos().up()) && this.world.isDaytime() ) // check daytime/obstruction and add power
		{
            if (tickCount <= 0) {
                tickCount = 20;
                markDirty();
            }		
		}
        
        sendOutPower();
        
        BlockState blockState = world.getBlockState(pos);
        if (blockState.get(BlockStateProperties.POWERED) != tickCount > 0) {
            world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, tickCount > 0), 3);
        }
	}
	
    private void sendOutPower() {
        energy.ifPresent(energy -> {
            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
            if (capacity.get() > 0) {
                for (Direction direction : Direction.values()) {
                    TileEntity te = world.getTileEntity(pos.offset(direction));
                    if (te != null) {
                        boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                        			AnicaMod.log(debug, "AnicaGeneratorTileEntity: handler.canReceive() " + handler.canReceive());
                                    if (handler.canReceive()) {
                                        int received = handler.receiveEnergy(Math.min(capacity.get(), 100), false);
                                        capacity.addAndGet(-received);
                                        ((AnicaEnergyStorage) energy).consumeEnergy(received);
                         
                                        return capacity.get() > 0;
                                    } else {
                                    	
                                        return true;
                                    }
                                }
                        ).orElse(true);
                        if (!doContinue) {
                        	
                            return;
                        }
                    }
                }
            }
        });
        
    }
    
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		super.onDataPacket(net, pkt);
		AnicaMod.log(debug, "AnicaBasicSolarTileEntity: onDataPacket " + pkt.toString());
	}
	
    private IEnergyStorage createEnergy() {
        return new AnicaEnergyStorage(100000, 0, false);
    }
}
