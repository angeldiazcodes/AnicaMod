package angel.anicamod.containers;

import angel.anicamod.AnicaMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class AnicaContainer extends Container {

	public TileEntity tileEntity;
	public PlayerEntity playerEntity;
	public IItemHandler playerInventory;

	public static final int AC_ITEM_LOC_X = 64;
	public static final int AC_ITEM_LOC_Y = 24;
	public static final int AC_INVENTORY_LEFTCOL = 10;
	public static final int AC_INVENTORY_TOPROW = 70;
	public static final int AC_INVENTORY_ROWS = 9;
	public static final int AC_INVENTORY_COLS = 3;
	public static final int AC_INVENTORY_INC = 18; // 18 pixels increment to next slot
    
	private static boolean debug = true;
	
    public AnicaContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, ContainerType<?> anica_container) {
        super(anica_container, windowId);
        
        AnicaMod.log(debug,"AnicaContainer: Constructor");
        
        tileEntity = world.getTileEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
    }
  
    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
    	// if (AnicaFurnaceContainer.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaFurnaceContainer: canInteractWith");
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, tileEntity.getBlockState().getBlock());
    }

    /*
     * int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx)
     * 
     * Description: Fill the container/GUI internal inventory for a row.
     * 
     */
    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
    	AnicaMod.log(debug, "AnicaContainer: addSlotRange");
        for (int i = 0 ; i < amount ; i++) {
        	SlotItemHandler slotItemHandler = new SlotItemHandler(handler, index, x, y);
            addSlot(slotItemHandler);
            x += dx;
            index++;
        }
        return index;
    }

    /*
     * addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy)
     * 
     * Description: Fill the container/GUI internal inventory rows x cols grid.
     * 
     */
    
    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
    	AnicaMod.log(debug, "AnicaContainer: addSlotBox");
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    /*
     * void layoutPlayerInventorySlots(int leftCol, int topRow)
     * 
     * Description: Go through the playerInvetory and layout the items into the right positions
     * in the container/GUI - both internal storage and hot bar.
     * 
     */
    
    public void layoutPlayerInventorySlots(int leftCol, int topRow) {
    	AnicaMod.log(debug, "AnicaContainer: layoutPlayerInventorySlots");
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, AC_INVENTORY_ROWS, AC_INVENTORY_INC, AC_INVENTORY_COLS, AC_INVENTORY_INC);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
    
}
