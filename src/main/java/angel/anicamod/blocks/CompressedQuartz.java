package angel.anicamod.blocks;

import angel.anicamod.AnicaMod;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class CompressedQuartz extends Block {
	private static boolean debug = false;

	public CompressedQuartz(Properties properties) {
		super(properties);
		AnicaMod.log(debug, "CompressedQuartz: constructor ");
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.COMPRESSED_QUARTZ));
	}
}
