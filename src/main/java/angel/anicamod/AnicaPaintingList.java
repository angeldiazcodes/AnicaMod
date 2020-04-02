package angel.anicamod;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AnicaPaintingList {

	public static final DeferredRegister<PaintingType> PAINTING_TYPES = new DeferredRegister<PaintingType>(ForgeRegistries.PAINTING_TYPES, AnicaMod.MODID);
	
	public static final RegistryObject<PaintingType> ANICA_MOD_PAINTING = PAINTING_TYPES.register(AnicaMod.ANICA_MOD_PAINTING, () -> new PaintingType(64,48));
}
