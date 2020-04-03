package angel.anicamod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AnicaModSoundList {	
		
	public static final DeferredRegister<SoundEvent> SOUND = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, AnicaMod.MODID);

	public static final RegistryObject<SoundEvent> ANICA_ENTITY_AMBIENT = SOUND.register("entity.anica_mob_entity",
			() -> new SoundEvent(new ResourceLocation(AnicaMod.MODID, "entity.anica_mob_entity")));
	}
