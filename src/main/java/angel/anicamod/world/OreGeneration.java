package angel.anicamod.world;

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
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			CountRangeConfig anica_ore_placement = new CountRangeConfig(1000, 20, 20, 100); // spawn between 20 and 100 ; 1000 is frequency - its a lot right now
			ConfiguredPlacement anica_ore_custom_config = Placement.COUNT_RANGE.configure( anica_ore_placement );
			OreFeatureConfig anica_ore_feature_config = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AnicaModBlocks.anica_ore.getDefaultState() , 10 ); // 10 is max ore-vien size
					
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(anica_ore_feature_config).withPlacement(anica_ore_custom_config));
		}
	
		
	}
}
