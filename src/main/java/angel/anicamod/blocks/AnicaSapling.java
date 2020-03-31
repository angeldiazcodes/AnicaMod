package angel.anicamod.blocks;

import java.util.Random;
import java.util.function.Supplier;

import angel.anicamod.AnicaMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

public class AnicaSapling extends BushBlock implements IGrowable {

	public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0d, 0.0d, 2.0d, 14.0d, 12.0d, 14.0d);
	private final Supplier<Tree> tree;
	
	public AnicaSapling(Supplier<Tree> treeIn, Properties propertiesIn) {
		super(propertiesIn);
		this.tree = treeIn;
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_SAPLING));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	// Tick function
	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		if ( !worldIn.isAreaLoaded(pos, 1)) {
			return;
		}
		if ( worldIn.getLight(pos.up()) >= 9 && rand.nextInt(7) == 0 ) {
			this.grow(worldIn, pos, state, rand);
		}
	}
	
	public void grow(ServerWorld serverWorld, BlockPos pos, BlockState state, Random rand) 
	{
		if ( state.get(STAGE) == 0 ) {
			serverWorld.setBlockState(pos, state.cycle(STAGE), 4);
		} else {
			if ( !ForgeEventFactory.saplingGrowTree(serverWorld, rand, pos)) return;
			this.tree.get().func_225545_a_(serverWorld, serverWorld.getChunkProvider().getChunkGenerator(), pos, state, rand);
		}
	}
	@Override
	public boolean canGrow(IBlockReader arg0, BlockPos arg1, BlockState arg2, boolean arg3) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return (double)worldIn.rand.nextFloat() < 0.45D;
	}

	// grow
	@Override
	public void grow(ServerWorld serverWorld, Random rand, BlockPos pos, BlockState state) {
		this.grow(serverWorld, pos, state, rand);
		
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(STAGE);
	}
}
