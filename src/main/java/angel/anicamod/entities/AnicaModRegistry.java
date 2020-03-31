package angel.anicamod.entities;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class AnicaModRegistry {
	
	public static void registryEntityRenders()
	{
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registryEntityRenders: Starting ...");
		RenderingRegistry.registerEntityRenderingHandler( AnicaModEntities.anica_mob_entity , new AnicaMobRenderer.RenderFactory() );
	}

}
