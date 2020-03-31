package angel.anicamod.blocks;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class AnicaCropPepperBlock extends CropsBlock {

	protected AnicaCropPepperBlock(Properties builder) {
		super(builder);
	}

	public static final VoxelShape[] SHAPES = new VoxelShape[] { 
			Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
			Block.makeCuboidShape(0, 0, 0, 16, 3, 16),
			Block.makeCuboidShape(0, 0, 0, 16, 4, 16),
			Block.makeCuboidShape(0, 0, 0, 16, 5, 16),
			Block.makeCuboidShape(0, 0, 0, 16, 6, 16),
			Block.makeCuboidShape(0, 0, 0, 16, 7, 16),
			Block.makeCuboidShape(0, 0, 0, 16, 8, 16),
			Block.makeCuboidShape(0, 0, 0, 16, 9, 16)
	};
	
	public AnicaCropPepperBlock() {
		super ( Block.Properties.create(Material.PLANTS).hardnessAndResistance(0f).doesNotBlockMovement().doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT) ); 
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_CROP_PEPPER_BLOCK));
	}

	@Override
	protected IItemProvider getSeedsItem() {
		return AnicaModItems.anica_pepper_item;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.get(this.getAgeProperty())];
	}
}
