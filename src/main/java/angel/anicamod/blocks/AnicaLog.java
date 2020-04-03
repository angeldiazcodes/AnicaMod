package angel.anicamod.blocks;

import angel.anicamod.AnicaMod;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;

public class AnicaLog extends LogBlock {

	public AnicaLog(MaterialColor colorIn, Properties properties) {
		super(colorIn, properties);
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_LOG));
	}
}
