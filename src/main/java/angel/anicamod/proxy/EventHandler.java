package angel.anicamod.proxy;

import angel.anicamod.AnicaMod;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class EventHandler {

	public static final EventHandler INSTANCE = new EventHandler();

	@SubscribeEvent
	public void handlePlayerLoggedInEvent(LoggedInEvent event)
	{
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "EventHandler: handlePlayerLoggedInEvent");
	}
	
}