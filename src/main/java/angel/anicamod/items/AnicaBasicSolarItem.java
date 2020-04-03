package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;

public class AnicaBasicSolarItem extends BlockItem {

	public AnicaBasicSolarItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_BASIC_SOLAR));
	}

}
