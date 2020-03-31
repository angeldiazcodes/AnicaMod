package angel.anicamod.blocks;

import angel.anicamod.AnicaMod;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.ResourceLocation;

public class AnicaLeaves extends LeavesBlock {

	public AnicaLeaves(Properties properties) {
		super(properties);
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_LEAVES));
	}

}
