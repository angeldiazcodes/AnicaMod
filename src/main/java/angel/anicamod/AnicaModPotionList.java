package angel.anicamod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(AnicaMod.MODID)
public class AnicaModPotionList {
	private static boolean debug = true;

	public static final Potion ANICA_POTION_ODD = new Potion(new EffectInstance(AnicaModEffectList.ANICA_EFFECT_ODD, 3600)).setRegistryName(AnicaMod.ANICA_POTION_ODD); // add effect to the potion along with durration 
	public static final Potion ANICA_POTION_LONG_ODD = new Potion(new EffectInstance(AnicaModEffectList.ANICA_EFFECT_ODD, 9600)).setRegistryName(AnicaMod.ANICA_POTION_LONG_ODD); // add effect to the potion along with durration 
	public static final Potion ANICA_POTION_SHORT_ODD = new Potion(new EffectInstance(AnicaModEffectList.ANICA_EFFECT_ODD, 1200)).setRegistryName(AnicaMod.ANICA_POTION_SHORT_ODD); // add effect to the potion along with durration 
	
	private static Method brewing;
	
	public static void registerPotion(final IForgeRegistry<Potion> registry) {
		if (AnicaModPotionList.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaModPotionList: registerPotion" );
		
		final Potion[] potions = {
				ANICA_POTION_ODD,
				ANICA_POTION_LONG_ODD,
				ANICA_POTION_SHORT_ODD
		};	
		registry.registerAll(potions);
	}

	// using Java reflection to add brewing 
	@SuppressWarnings("unused")
	private static void addMix(Potion base, Item ingredient, Potion result) {
		if (AnicaModPotionList.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaModPotionList: addMix" );
		
		if(brewing == null) {
			brewing = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "addMix", Potion.class, Item.class, Potion.class); // find the addMix class using Java reflection
			brewing.setAccessible(true);
		}
		
		try {
			if (AnicaModPotionList.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaModPotionList: adding the mix " + result.toString() );
			brewing.invoke(null, base, ingredient, result);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	// register mixes for the various potions
	public static void addRecipes() {
		if (AnicaModPotionList.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaModPotionList: addRecipes" );
		
		addMix(Potions.AWKWARD, Items.CHARCOAL, ANICA_POTION_ODD); // mix for ANICA_POTION_ODD
		addMix(ANICA_POTION_ODD, Items.REDSTONE, ANICA_POTION_LONG_ODD); // mix for ANICA_POTION_LONG_ODD
		addMix(ANICA_POTION_ODD, Items.FEATHER, ANICA_POTION_SHORT_ODD); // mix ANICA_POTION_SHORT_ODD
	}
}