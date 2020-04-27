package angel.anicamod.blocks;

import angel.anicamod.AnicaMod;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class EnderPearlOre extends Block {
	private static boolean debug = false;
	
	public EnderPearlOre(Properties properties) {
		super(properties);
		AnicaMod.log(debug, "EnderPearlOre: constructor ");
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ENDER_PEARL_ORE));
	}

}
