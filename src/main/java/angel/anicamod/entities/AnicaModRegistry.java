package angel.anicamod.entities;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class AnicaModRegistry {
	private static boolean debug = false;
	
	public static void registryEntityRenders()
	{
		AnicaMod.log(debug, "registryEntityRenders: Starting ...");
		RenderingRegistry.registerEntityRenderingHandler( AnicaModEntities.anica_mob_entity , new AnicaMobRenderer.RenderFactory() );
	}

}
