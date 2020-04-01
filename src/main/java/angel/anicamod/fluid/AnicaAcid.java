package angel.anicamod.fluid;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import angel.anicamod.AnicaModFluidsList;
import angel.anicamod.AnicaModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;

public abstract class AnicaAcid extends FlowingFluid {

	@Override
	public Fluid getFlowingFluid() {
		return AnicaModFluidsList.ANICA_FLOWING_ACID;
	}

	@Override
	public Fluid getStillFluid() {
		return AnicaModFluidsList.ANICA_ACID_STILL;
	}

	@Override
	protected boolean canSourcesMultiply() {
		return true;
	}

	@Override
	protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub
	}

	@Override
	protected int getSlopeFindDistance(IWorldReader worldIn) {
		return 4;
	}

	@Override
	protected int getLevelDecreasePerBlock(IWorldReader worldIn) {
		return 3;
	}

	@Override
	public Item getFilledBucket() {
		return AnicaModItems.anica_bucket_item;
	}

	@Override
	protected boolean canDisplace(IFluidState state, IBlockReader world, BlockPos pos, Fluid fluid, Direction direction) {
		return direction == Direction.DOWN && !fluid.isIn(AnicaModFluidsList.Tags.ANICA_ACID);
	}

	@Override
	public int getTickRate(IWorldReader p_205569_1_) {
		return 60;
	}

	@Override
	protected float getExplosionResistance() {
		return 100.0F;
	}

	@Override
	protected BlockState getBlockState(IFluidState state) {
		return AnicaModBlocks.anica_acid_block.getDefaultState().with(FlowingFluidBlock.LEVEL, Integer.valueOf(getLevelFromState(state)));
	}

	@Override
	public boolean isSource(IFluidState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLevel(IFluidState state) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isEquivalentTo(Fluid fluidIn) {
		return fluidIn == AnicaModFluidsList.ANICA_ACID_STILL || fluidIn == AnicaModFluidsList.ANICA_FLOWING_ACID;
	}
	
	@Override
	protected FluidAttributes createAttributes() {
		return FluidAttributes.builder( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_ACID_STILL_TEXTURE), 
				new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_ACID_FLOW_TEXTURE)).translationKey("block.anicamod.acid").build(this); //not sure about block.anicamod.acid
	}
	
	public static class Flowing extends AnicaAcid {
		
		@Override
		protected void fillStateContainer(Builder<Fluid, IFluidState> builder) {
			super.fillStateContainer(builder);
			builder.add(LEVEL_1_8);
		}
		
		@Override
		public boolean isSource(IFluidState state) {
			return false;
		}
		
		@Override
		public int getLevel(IFluidState state) {
			return state.get(AnicaAcid.LEVEL_1_8);
		}
		
	}
	
	public static class Source extends AnicaAcid {
		
		@Override
		public boolean isSource(IFluidState state) {
			return true;
		}
		
		@Override
		public int getLevel(IFluidState p_207192_1_) {
			return 8;
		}
		
	}
	
}
