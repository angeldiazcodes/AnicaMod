package angel.anicamod.blocks;

import angel.anicamod.AnicaMod;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class AnicaBlock extends Block {
	private static boolean debug = false;

	public AnicaBlock(Properties properties) {
		super(properties);
		AnicaMod.log(debug, "AnicaBlock: constructor ");
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_BLOCK));
	}
}
