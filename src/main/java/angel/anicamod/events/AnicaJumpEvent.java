package angel.anicamod.events;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = AnicaMod.MODID, bus = Bus.FORGE) // comment this if it get laggy
public class AnicaJumpEvent {
	
	private static boolean debug = true;
	 
	@SubscribeEvent
	public static void onLivingJumpEvent(LivingJumpEvent event) {
		/*
		if (AnicaJumpEvent.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaJumpEvent: anicaJumpEvent ");
		
		LivingEntity livingEntity = event.getEntityLiving();
		World world = livingEntity.getEntityWorld();
		world.setBlockState(livingEntity.getPosition().add(0, 5, 0), AnicaModBlocks.anica_block.getDefaultState());
		livingEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 255));
		livingEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 5000, 255));
		livingEntity.setGlowing(true);
		*/
	}
	 
}
