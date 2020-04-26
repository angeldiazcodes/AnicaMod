package angel.anicamod.world;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModStrucutresList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class WorldGen {

		private static boolean debug = false;
		
	    public static void setupWorldGen() {
	    	
	        // Add Structures
	    	AnicaMod.log(debug, "setupWorldGen: starting ...");
	        for (Biome biome : ForgeRegistries.BIOMES) {
	            
	        	// Blacklisted 
	            if (biome == Biomes.RIVER) 
	            {
	                continue;
	            }
	            
	            AnicaMod.log(debug,"setupWorldGen: " + biome.getDisplayName().getString());
   
                addSurfaceStructure(biome, AnicaModStrucutresList.ANICA_CABIN);
                
	            // Use categories to allow compatibility with biome mods such as Biomes O' Plenty.
	            if (biome.getCategory() == Biome.Category.PLAINS || biome.getCategory() == Biome.Category.FOREST) {
	                if (biome.getTempCategory() == Biome.TempCategory.MEDIUM && biome.getPrecipitation() == Biome.RainType.RAIN) {
	                    // addSurfaceStructure(biome, AnicaModStrucutresList.ANICA_CABIN);
	                }
	            }
	            if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
	                // addUndergroundStructure(biome, AnicaModStrucutresList.SMALL_DUNGEON);
	            }
	        }
	    }

	    /**
	     * Add a structure to the given biome.
	     * @param biome The biome to add a structure to.
	     * @param structure The structure to add.
	     */
	    private static void addSurfaceStructure(Biome biome, Structure<NoFeatureConfig> structure) {
	    	//structure.withConfiguration func_225566_b_(p_225566_1_)
	    	//structure.field_227245_q_
	    	
	    	AnicaMod.log(debug, "addSurfaceStructure: ");	
	        biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, 
	        		structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure( 
	        		new AtSurfaceWithExtraConfig( 1, 1f, 1 )))); // 1000 is a lot
	    }

	    private static void addUndergroundStructure(Biome biome, Structure<NoFeatureConfig> structure) {
	    	AnicaMod.log(debug, "addUndergroundStructure: ");
	        //biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	        //biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
	    }
}
