package angel.anicamod.blocks;

import angel.anicamod.AnicaMod;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class AnicaBlock extends Block {

	public AnicaBlock(Properties properties) {
		super(properties);
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBlock: constructor ");
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_BLOCK));
	}
}
