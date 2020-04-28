package angel.anicamod.world;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {
	@SuppressWarnings("rawtypes")
	public static void setupOrgeGeneration() 
	{
		CountRangeConfig placementConfig = null;
		ConfiguredPlacement placement = null;
		OreFeatureConfig featureConfig = null;
		
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			// Anica Ore
			placementConfig = new CountRangeConfig(AnicaMod.ANICA_ORE_FREQUENCY, 20, 20, 100); // spawn between 20 and 100 ; first argument is frequency - 1000 is frequency - its a lot right now
			placement = Placement.COUNT_RANGE.configure( placementConfig );
			featureConfig = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AnicaModBlocks.anica_ore.getDefaultState() , 10 ); // 10 is max ore-vien size
					
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(featureConfig).withPlacement(placement));
            
            // Ender Pearl Ore
			placementConfig = new CountRangeConfig(AnicaMod.ENDER_PEARL_ORE_FREQUENCY, 20, 10, 25); // spawn between 20 and 100 ; first argument is frequency - 1000 is frequency - its a lot right now
			placement = Placement.COUNT_RANGE.configure( placementConfig );
			featureConfig = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AnicaModBlocks.ender_pearl_ore.getDefaultState() , 3 ); // 3 is max ore-vien size
					
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(featureConfig).withPlacement(placement));            
		}
	
		
	}
}
