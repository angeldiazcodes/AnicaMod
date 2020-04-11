package angel.anicamod;

import angel.anicamod.util.helpers.ModUtil;
import angel.anicamod.world.dimension.AnicaModDimension;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class AnicaDimensionList {
	
	public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, AnicaMod.MODID);
	public static final RegistryObject<ModDimension> ANICA_DIM = MOD_DIMENSIONS.register(AnicaMod.ANICA_DIM, () -> new AnicaModDimension());
}
