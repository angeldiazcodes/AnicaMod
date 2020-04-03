package angel.anicamod.world.feature;

import java.util.Random;

import angel.anicamod.AnicaModBlocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;

public class AnicaTree extends Tree {

	public static final TreeFeatureConfig ANICA_TREE_CONFIG = (new TreeFeatureConfig.Builder(
			new SimpleBlockStateProvider(AnicaModBlocks.anica_log.getDefaultState()),
			new SimpleBlockStateProvider(AnicaModBlocks.anica_leaves.getDefaultState()),
			new BlobFoliagePlacer(3,0))).baseHeight(14).heightRandA(5).foliageHeight(9).ignoreVines().setSapling((IPlantable)AnicaModBlocks.anica_sapling).build();
	
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
		return Feature.NORMAL_TREE.withConfiguration(ANICA_TREE_CONFIG);
	}

}
