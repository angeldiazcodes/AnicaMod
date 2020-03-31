package angel.anicamod.proxy;

import angel.anicamod.AnicaMod;
import angel.anicamod.world.OreGeneration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {

	@Override
	public void init()
	{
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "ServerProxy:Init");
        OreGeneration.setupOrgeGeneration();
	}

	@Override
	public PlayerEntity getClientPlayer()
	{
		throw new IllegalStateException("This should only be called from client side");
	}

	@Override
	public World getClientWorld()
	{
		throw new IllegalStateException("This should only be called from client side");
	}

}