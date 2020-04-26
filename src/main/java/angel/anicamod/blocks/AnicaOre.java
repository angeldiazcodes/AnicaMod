package angel.anicamod.blocks;

import angel.anicamod.AnicaMod;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class AnicaOre extends Block {
	private static boolean debug = false;
	
	public AnicaOre(Properties properties) {
		super(properties);
		AnicaMod.log(debug, "AnicaOre: constructor ");
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_ORE));
	}

}
