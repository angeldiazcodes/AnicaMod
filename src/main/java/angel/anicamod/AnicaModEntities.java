package angel.anicamod;

import angel.anicamod.entities.AnicaMobEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class AnicaModEntities {
	private static boolean debug = false;

	// Must setup entities here due to sequencing of Items, Blocks, Entities etc. 
	@SuppressWarnings("unchecked")
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_MOB_ENTITY)
	public static EntityType<AnicaMobEntity> anica_mob_entity = (EntityType<AnicaMobEntity>) EntityType.Builder.create(AnicaMobEntity::new, EntityClassification.CREATURE).build(AnicaMod.ANICA_MOB_ENTITY).setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_MOB_ENTITY));

	public static void registerEntities(final IForgeRegistry<EntityType<?>> registry)
	{
		registry.register(anica_mob_entity);
        AnicaModEntities.registerEntityWorldSpawn();
	}
	
	public static Item registerEntitySpawnEgg(EntityType<?> type, int primaryColor, int secondaryColor, String name)
	{
		AnicaMod.log(debug, "registerEntitySpawnEgg: Starting");
		
		SpawnEggItem item = new SpawnEggItem(type, primaryColor, secondaryColor, new Item.Properties().group(AnicaMod.anicaModTab) );
		item.setRegistryName( new ResourceLocation(AnicaMod.MODID, name));
		
		return item;
	}
	
	public static void registerEntityWorldSpawn()
	{
		registerEntityWorldSpawn(anica_mob_entity, Biomes.BEACH, Biomes.DESERT, Biomes.JUNGLE);
	}
	
	public static void registerEntityWorldSpawn(EntityType<?> entity, Biome... biomes)
	{
		for (Biome biome : ForgeRegistries.BIOMES ) //  biomes )
		{
			if ( biome != null)
			{
				AnicaMod.log(debug, "registerEntitySpawnEgg: " + biome.toString() );
				biome.getSpawns(entity.getClassification()).add(new SpawnListEntry(entity, AnicaMod.ANICA_MOB_FREQUENCY, AnicaMod.ANICA_MOB_MIN_SPAWN, AnicaMod.ANICA_MOB_MAX_SPAWN) ); // spawn rate, min spawn together, max spawn together
			}
		}
	}
}
