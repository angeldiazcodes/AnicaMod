package angel.anicamod.events;

import angel.anicamod.AnicaMod;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedInEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {

	public static final EventHandler INSTANCE = new EventHandler();
	public static boolean debug = false;
	
	@SubscribeEvent
	public void handlePlayerLoggedInEvent(LoggedInEvent event)
	{
		if (EventHandler.debug) AnicaMod.logger.info(AnicaMod.logStub + "EventHandler: handlePlayerLoggedInEvent");
	}
	
	@SubscribeEvent
	public void handleBreakEvent( BlockEvent event)
	{
		if (EventHandler.debug) AnicaMod.logger.info(AnicaMod.logStub + "EventHandler: handleBreakEvent");
	}
	
	@SubscribeEvent
	public void handleLivingDropsEvent(LivingDropsEvent event)
	{
		if (EventHandler.debug) AnicaMod.logger.info(AnicaMod.logStub + "EventHandler: handleLivingDropsEvent");
	    if (event.getEntity().getType().equals( event.getEntity().getType().SHEEP ) )
	    {
	    	if (EventHandler.debug) AnicaMod.logger.info(AnicaMod.logStub + "EventHandler: handleLivingDropsEvent Sheep!!!");
	    	
	        event.getDrops().clear();
	        ItemStack itemStackToDrop = new ItemStack( Items.DIAMOND, 5);
	        event.getDrops().add( new ItemEntity(  event.getEntity().getEntityWorld(), event.getEntity().getPosX(),
	        		event.getEntity().getPosY(), event.getEntity().getPosZ(),itemStackToDrop
	        		));
	    }
	}
}