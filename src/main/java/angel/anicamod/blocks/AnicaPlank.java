package angel.anicamod.blocks;

import angel.anicamod.AnicaMod;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class AnicaPlank extends Block {

	public AnicaPlank(Properties properties) {
		super(properties);
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_PLANK));
	}

}
