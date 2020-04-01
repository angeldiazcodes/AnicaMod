package angel.anicamod.blocks;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModFluidsList;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AnicaAcidBlock extends FlowingFluidBlock {

	private static boolean debug = false;
	
	@SuppressWarnings("deprecation")
	public AnicaAcidBlock(FlowingFluid fluidIn, Properties builder) {
		super(fluidIn, builder);
		if (AnicaAcidBlock.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaAcidBlock: constructor" );
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_ACID_STILL)); // ANICA_ACID
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (AnicaAcidBlock.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaAcidBlock: onEntityCollision" );
		
		// if (this.getFluid().isIn(AnicaModFluidsList.Tags.ANICA_ACID)) { - this is not working ... 
			
		if (this.getFluid().isEquivalentTo(AnicaModFluidsList.ANICA_FLOWING_ACID) || this.getFluid().isEquivalentTo(AnicaModFluidsList.ANICA_ACID_STILL) ) { 	
			if (AnicaAcidBlock.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaAcidBlock: You are in Anica Acid" );
			if ( entityIn instanceof LivingEntity ) {
				((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.POISON, 160, 2));
				if (AnicaAcidBlock.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaAcidBlock: Poison for you" );
			}
			else {
				entityIn.remove();
			}
		}
	}
}
