package angel.anicamod.world;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBiomes;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class AnicaBiomeWorldType extends WorldType {

	public AnicaBiomeWorldType() {
		super( AnicaMod.ANICA_WORLD_TYPE );
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator(World world) {

		OverworldGenSettings genSettings = new OverworldGenSettings();
		SingleBiomeProviderSettings singleSetting = new SingleBiomeProviderSettings( world.getWorldInfo() );
		singleSetting.setBiome( AnicaModBiomes.anica_biome );
		
		return new OverworldChunkGenerator( world, new SingleBiomeProvider(singleSetting), genSettings );
	}
}
