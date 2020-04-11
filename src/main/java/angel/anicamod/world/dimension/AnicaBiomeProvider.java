package angel.anicamod.world.dimension;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import angel.anicamod.AnicaModBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class AnicaBiomeProvider extends BiomeProvider {

	private Random rand;

	public AnicaBiomeProvider() {
		super(biomeList);
		rand = new Random();
	}

	private static final Set<Biome> biomeList = ImmutableSet.of(AnicaModBiomes.anica_biome);

	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return AnicaModBiomes.anica_biome;
	}

}