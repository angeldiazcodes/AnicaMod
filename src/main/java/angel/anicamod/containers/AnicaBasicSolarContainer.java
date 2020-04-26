package angel.anicamod.containers;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import angel.anicamod.tileentity.AnicaEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/*
 * AnicaBasicSolarContainer
 * 
 * Description: This is a class that exists on the client and the server and it makes sure that 
 * changes the user does on the client are communicated properly to the server and the other 
 * way around. In the container we store the inventory of the player (as we need to manipulate 
 * player inventory slots too) and the inventory of our tile entity.
 * 
 */

public class AnicaBasicSolarContainer extends AnicaContainer {
    
    private static final boolean debug = false;
    
    public AnicaBasicSolarContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(windowId, world, pos, playerInventory, player, AnicaModBlocks.anica_basic_solar_container);
        
        AnicaMod.log(debug,"AnicaBasicSolarContainer: Constructor");
       
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
        	// net.minecraftforge.items.SlotItemHandler: SlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition
            // net.minecraft.inventory.Container: addSlot(Slot slotIn)
        	addSlot(new SlotItemHandler(h, 0, AC_ITEM_LOC_X, AC_ITEM_LOC_Y)); // sets the position to place the item in the GUI
        });
        
        // layoutPlayerInventorySlots(int leftCol, int topRow) - this happens when opening generator GUI
        layoutPlayerInventorySlots(AC_INVENTORY_LEFTCOL, AC_INVENTORY_TOPROW);
        
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return getEnergy();
            }

            @Override
            public void set(int value) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((AnicaEnergyStorage)h).setEnergy(value));
            }
        });
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

        return itemstack;
    }

    public int getEnergy() {
    	return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }
}
