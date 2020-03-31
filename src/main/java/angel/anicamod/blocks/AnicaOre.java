package angel.anicamod.blocks;

import angel.anicamod.AnicaMod;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class AnicaOre extends Block {

	public AnicaOre(Properties properties) {
		super(properties);
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaOre: constructor ");
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_ORE));
	}

}
