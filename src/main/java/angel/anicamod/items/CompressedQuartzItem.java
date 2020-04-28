package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;

public class CompressedQuartzItem extends BlockItem {

	public CompressedQuartzItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.COMPRESSED_QUARTZ));
	}
}
