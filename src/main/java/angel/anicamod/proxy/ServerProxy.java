package angel.anicamod.proxy;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModPotionList;
import angel.anicamod.world.OreGeneration;
import angel.anicamod.world.WorldGen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {

	@Override
	public void init()
	{
		// Ore generation
        OreGeneration.setupOrgeGeneration();
        
        // Add structures 
        WorldGen.setupWorldGen();
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