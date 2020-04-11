package angel.anicamod.events;

import angel.anicamod.AnicaDimensionList;
import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import angel.anicamod.commands.AnicaModCommands;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = AnicaMod.MODID, bus = Bus.FORGE) // comment this if it get laggy
public class ForgeEventHandler {
	
	private static boolean debug = true;
	 
	@SubscribeEvent
	public static void onLivingJumpEvent(LivingJumpEvent event) {
		/*
		if (ForgeEventHandler.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaJumpEvent: anicaJumpEvent ");
		
		LivingEntity livingEntity = event.getEntityLiving();
		World world = livingEntity.getEntityWorld();
		world.setBlockState(livingEntity.getPosition().add(0, 5, 0), AnicaModBlocks.anica_block.getDefaultState());
		livingEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 255));
		livingEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 5000, 255));
		livingEntity.setGlowing(true);
		*/
	}
	
    @SubscribeEvent
    public static void onDimensionRegistry(RegisterDimensionsEvent event) {
    	if (ForgeEventHandler.debug) AnicaMod.logger.info(AnicaMod.logStub + "onDimensionRegistry: Dimensions Registered! ");
    	AnicaDimensionList.DIMENSION_TYPE = DimensionManager.registerOrGetDimension(AnicaMod.ANICA_DIM_TYPE, AnicaDimensionList.ANICA_DIM.get(), null, true);
    }
	 
    @SubscribeEvent
    public static void serverLoad(FMLServerStartingEvent event) {
        AnicaModCommands.register(event.getCommandDispatcher());
    }
}
