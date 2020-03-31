package angel.anicamod.world;

import angel.anicamod.AnicaModBlocks;
import angel.anicamod.AnicaModItems;
import angel.anicamod.world.feature.AnicaTree;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class AnicaBiome extends Biome{

	public AnicaBiome() {
		super( getBuilder() );
		
		DefaultBiomeFeatures.addBamboo(this);
		DefaultBiomeFeatures.addBirchTrees(this);
		DefaultBiomeFeatures.addDoubleFlowers(this);
		DefaultBiomeFeatures.addCarvers(this); //caves
		
		// this.func_226711_a_( Structure.WOODLAND_MANSION); 
		this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry( EntityType.CHICKEN, 1000 , 5, 10));
		
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				Feature.NORMAL_TREE.withConfiguration(AnicaTree.ANICA_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure( new AtSurfaceWithExtraConfig(10, 1, 1) ))); // count, extra chance, extra count
	}

	private static Builder getBuilder()
	{
		Builder biomeBuilder = new Biome.Builder();
		
		biomeBuilder.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig( AnicaModBlocks.anica_ore.getDefaultState(), Blocks.DIAMOND_ORE.getDefaultState(), AnicaModBlocks.anica_ore.getDefaultState()  )); // top block, second block, top block under water
		biomeBuilder.precipitation(RainType.NONE);
		biomeBuilder.category(Category.PLAINS);
		biomeBuilder.downfall(0f); // how much does it rain
		biomeBuilder.depth(.125f); // how deep should the biome go - PLAINS is .125 normally
		biomeBuilder.temperature(0.5f); // spawn ice or water
		biomeBuilder.scale(0.5f); // how wide is the biome
		biomeBuilder.waterColor(0xFF69B4); 
		biomeBuilder.waterFogColor(0xFF69B4);
		biomeBuilder.parent(null); // does this biome spawn inside of another
		return biomeBuilder;
	}
}
