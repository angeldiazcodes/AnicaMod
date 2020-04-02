package angel.anicamod;

import angel.anicamod.effect.AnicaModEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(AnicaMod.MODID)
public class AnicaModEffectList {

	public static final AnicaModEffect ANICA_EFFECT_ODD = (AnicaModEffect) new AnicaModEffect(EffectType.NEUTRAL, 0xD4F0AD).setRegistryName(AnicaMod.ANICA_POTION_ODD);
	
	public static void registerEffect(final IForgeRegistry<Effect> registry) {
		final Effect[] effects = {
				ANICA_EFFECT_ODD
		};	
			registry.registerAll(effects);
		}
}