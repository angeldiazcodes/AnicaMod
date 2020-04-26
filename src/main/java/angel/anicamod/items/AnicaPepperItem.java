package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class AnicaPepperItem extends BlockItem {
	private static boolean debug = false;
	
	public AnicaPepperItem(Block blockIn, Properties builder) {
		super( blockIn, builder);
		AnicaMod.log(debug,"AnicaPepperItem: constructor ");
		// setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_PEPPER_ITEM));
		setRegistryName( AnicaModBlocks.anica_crop_pepper_block.getRegistryName() ); // so that it drops crop pepper block
	}
}
