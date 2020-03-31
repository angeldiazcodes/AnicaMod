package angel.anicamod.containers;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraft.inventory.container.Slot;

/*
 * AnicaFurnaceContainer
 * 
 * Description: This is a class that exists on the client and the server and it makes sure that 
 * changes the user does on the client are communicated properly to the server and the other 
 * way around. In the container we store the inventory of the player (as we need to manipulate 
 * player inventory slots too) and the inventory of our tile entity.
 * 
 */

public class AnicaFurnaceContainer extends AnicaContainer {
	
    private static final boolean debug = false;
    
    public AnicaFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(windowId, world, pos, playerInventory, player, AnicaModBlocks.anica_furnace_container);
        
        if (AnicaFurnaceContainer.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaFurnaceContainer: Constructor");
        
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
        	// net.minecraftforge.items.SlotItemHandler: SlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition
            // net.minecraft.inventory.Container: addSlot(Slot slotIn)
        	addSlot(new SlotItemHandler(h, 0, AC_ITEM_LOC_X, AC_ITEM_LOC_Y)); // sets the position to place the item in the GUI
        	addSlot(new SlotItemHandler(h, 1, AC_ITEM_LOC_X +18 +18 , AC_ITEM_LOC_Y)); 
        });
        
        // layoutPlayerInventorySlots(int leftCol, int topRow) - this happens when opening furnace GUI
        layoutPlayerInventorySlots(AC_INVENTORY_LEFTCOL, AC_INVENTORY_TOPROW);
    }

    /*
     * transferStackInSlot(PlayerEntity playerIn, int index)
     * 
     * Description: This function is responsible for transfer of items when the player shift-clicks them.
     * It will be restricted to only support diamonds. 
     */
    
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        
        if (slot != null && slot.getHasStack()) 
        {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            
            if (index == 0) 
            {
                if (!this.mergeItemStack(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemstack);
            } else 
            {
                if ( stack.getItem() == Items.DIAMOND) 
                {
                    if (!this.mergeItemStack(stack, 0, 1, false)) 
                    {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 28) 
                	{
                    	if (!this.mergeItemStack(stack, 28, 37, false)) 
                    	{
                    		return ItemStack.EMPTY;
                    	}
                } else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) 
                	{
                    	return ItemStack.EMPTY;
                	}
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }

}
