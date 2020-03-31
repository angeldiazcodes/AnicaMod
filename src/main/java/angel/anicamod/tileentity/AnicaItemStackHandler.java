package angel.anicamod.tileentity;

import javax.annotation.Nonnull;

import angel.anicamod.AnicaMod;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class AnicaItemStackHandler extends ItemStackHandler  {

	  private TileEntity anicaTileEntity = null;
	  private static boolean debug = false;
	  
	  public AnicaItemStackHandler(TileEntity tileEntity, int size)
	  {
		  super(size);
		  anicaTileEntity = tileEntity;
	  }
	  
	  @Override
      public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
          return true;
      }

      @Nonnull
      @Override
      public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
          return super.insertItem(slot, stack, simulate);
      }
      
      @Override
      protected void onContentsChanged(int slot) 
      {
    	  if (AnicaItemStackHandler.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaItemStackHandler: onContentsChanged " + slot); 
    	  anicaTileEntity.markDirty();
      }
}
