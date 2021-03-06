package angel.anicamod.proxy;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import angel.anicamod.AnicaModPotionList;
import angel.anicamod.events.EventHandler;
import angel.anicamod.gui.AnicaBasicSolarScreen;
import angel.anicamod.gui.AnicaBatteryScreen;
import angel.anicamod.gui.AnicaFurnaceScreen;
import angel.anicamod.gui.AnicaGeneratorScreen;
import angel.anicamod.network.Networking;
import angel.anicamod.world.OreGeneration;
import angel.anicamod.world.WorldGen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientProxy implements IProxy {
	private static boolean debug = false;

	@Override
	public void init()
	{
		AnicaMod.log(debug,"ClientProxy:Init Starting " + ScreenManager.isMissingScreen());
		
		// add potion recipies
		AnicaModPotionList.addRecipes();
		
		OreGeneration.setupOrgeGeneration();
	
		// Couple our container(s) to the screen
        ScreenManager.registerFactory(AnicaModBlocks.anica_furnace_container, AnicaFurnaceScreen::new);
        ScreenManager.registerFactory(AnicaModBlocks.anica_generator_container, AnicaGeneratorScreen::new);
        ScreenManager.registerFactory(AnicaModBlocks.anica_battery_container, AnicaBatteryScreen::new);
        ScreenManager.registerFactory(AnicaModBlocks.anica_basic_solar_container, AnicaBasicSolarScreen::new);
        
        // register event handler class (a different style)
        MinecraftForge.EVENT_BUS.register(EventHandler.INSTANCE);
        
        // set up client/server networking packets
        Networking.registerMessages();
        		
        RenderTypeLookup.setRenderLayer(AnicaModBlocks.anica_crop_pepper_block, RenderType.getCutout()); // update mappings - getCutout
        RenderTypeLookup.setRenderLayer(AnicaModBlocks.anica_sapling, RenderType.getCutout()); // update mappings - getCutout
        RenderTypeLookup.setRenderLayer(AnicaModBlocks.anica_basic_solar, RenderType.getCutout()); // update mappings - getCutout
        RenderTypeLookup.setRenderLayer(AnicaModBlocks.anica_basic_cable, RenderType.getCutout()); // update mappings - getCutout
        
        // Add structures 
        WorldGen.setupWorldGen();
	}

	@Override
	public PlayerEntity getClientPlayer()
	{
		return Minecraft.getInstance().player;
	}

	@Override
	public World getClientWorld()
	{
		return Minecraft.getInstance().world;
	}
}