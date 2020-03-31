package angel.anicamod;

import net.minecraftforge.common.BiomeDictionary.Type;

import angel.anicamod.util.helpers.ModUtil;
import angel.anicamod.world.AnicaBiome;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class AnicaModBiomes {

		// Biomes
		@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BIOME)
		public static Biome anica_biome = ModUtil._null();
		
		public static void registerBiomes(IForgeRegistry<Biome> registry)
		{
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerBiomes: Biomes registered " + registry.toString());
			anica_biome = new AnicaBiome();
			anica_biome.setRegistryName( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_BIOME) );
			registry.register( anica_biome );
			registerBiome( anica_biome, Type.PLAINS, Type.LUSH, Type.END);
		}

		public static void registerBiome(Biome biome, Type... type )
		{
			BiomeDictionary.addTypes(biome, type);
			BiomeManager.addSpawnBiome(biome);
		}
}
