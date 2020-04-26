package angel.anicamod.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import angel.anicamod.blocks.AnicaOre;
import angel.anicamod.containers.AnicaFurnaceContainer;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraft.block.Block;
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

/*
 * AnicaFurnaceTileEntity
 * 
 * Description: Here we are adding functionality to our block. This is done with a tile entity.
 * Because we know we're going to need a tile entity that does some work we implement 
 * ITickableTileEntity so that our tile entity gets notified every tick.
 * 
 */

public class AnicaFurnaceTileEntity extends TileEntity  implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<AnicaItemStackHandler> handler = LazyOptional.of(this::createHandler);
    
    private AnicaItemStackHandler anicaItemStackHandler =  null;
    private AnicaFurnaceContainer anicaFurnaceContainer = null;
    
    private int tickCount = 0;
    
    private static boolean debug = false;
    
	public AnicaFurnaceTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		AnicaMod.log(debug, "AnicaFurnaceTileEntity: Contructor 1 " + tileEntityTypeIn.toString() );
	}

	public AnicaFurnaceTileEntity() {
		this(AnicaModBlocks.anica_furnace_tile_entity);
		AnicaMod.log(debug, "AnicaFurnaceTileEntity: Contructor 2 " + AnicaModBlocks.anica_furnace_tile_entity.getRegistryName() );
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
        super.read(tag);
        
        AnicaMod.log(debug, "AnicaFurnaceTileEntity: read TAG " + invTag.toString() );
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

        AnicaMod.log(debug, "AnicaFurnaceTileEntity: write TAG " + tag.toString() );
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
		AnicaMod.log(debug, "AnicaItemStackHandler: " + anicaItemStackHandler.getSlots() );
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
    	AnicaMod.log(debug, "AnicaFurnaceTileEntity: createMenu " );
    	anicaFurnaceContainer = new AnicaFurnaceContainer(i, world, pos, playerInventory, playerEntity);
    	
    	return anicaFurnaceContainer;
    }

	@Override
	public ITextComponent getDisplayName() {
		AnicaMod.log(debug, "AnicaFurnaceTileEntity: getDisplayName " + getType().getRegistryName().getPath());
		return new StringTextComponent(getType().getRegistryName().getPath());
	}

	@Override
	public void tick() {
		if ( tickCount == 5 )
		{
			
		if ((anicaItemStackHandler != null) & ( anicaFurnaceContainer != null))
		{
			ItemStack inputStack = anicaItemStackHandler.getStackInSlot(0);
				
			if ( inputStack != null ) 
			{
				// there must be items in the inputStack and they must be diamonds
				if ( ( inputStack.getCount() > 0 ) & (inputStack.getItem() == Items.DIAMOND) )
				{
					ItemStack outputStack = anicaFurnaceContainer.getSlot(1).getStack();
					if (outputStack != null)
					{
						int outputStackCount = outputStack.getCount();
						if ( outputStackCount <64 )
						{
							if ( (outputStack.getItem() == Items.COAL) || (outputStack.getItem() == Items.AIR) )
							{
								AnicaMod.log(debug, "AnicaFurnaceTileEntity: smelting diamonds");
								inputStack.shrink(1);
								anicaFurnaceContainer.putStackInSlot(0, inputStack );
							
								//outputStack = new ItemStack( AnicaModBlocks.anica_ore , outputStackCount + 1 );
								outputStack = new ItemStack( Items.COAL , outputStackCount + 1 );
								anicaFurnaceContainer.putStackInSlot(1, outputStack );
							}
						}
						
					}
					
				}
			}		
		}
		tickCount = 0;
		}
		tickCount++;

	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		super.onDataPacket(net, pkt);
		AnicaMod.log(debug, "AnicaFurnaceTileEntity: onDataPacket " + pkt.toString());
	}
}
