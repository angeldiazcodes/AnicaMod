package angel.anicamod;

import angel.anicamod.world.structures.AnicaCabinStrucutre;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(AnicaMod.MODID)
public class AnicaModStrucutresList {	
		
	    // Structures - https://github.com/stal111/Valhelsia-Structures
		public static final Structure<NoFeatureConfig> ANICA_CABIN = create(AnicaMod.ANICA_CABIN, new AnicaCabinStrucutre(NoFeatureConfig::deserialize));
	    
		private static boolean debug = false;
		
	    public static void registerStructures(IForgeRegistry<Feature<?>> registry) {
	        if (AnicaModStrucutresList.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaModStrucutresList: registerStructures" );
	        registry.register(ANICA_CABIN);
	    }

	    private static <T extends Feature<?>> T create(String name, T feature) {
	        feature.setRegistryName(AnicaMod.MODID, name);
	        return feature;
	    }
	}
