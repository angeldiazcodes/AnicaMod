package angel.anicamod;

import angel.anicamod.util.helpers.ModUtil;
import net.minecraft.item.Food;
import net.minecraft.item.Food.Builder;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class AnicaModFoodList {
	
	// Foods
	public static Food anica_food_pepper = ModUtil._null(); 

	@SuppressWarnings("deprecation")
	public static void createFood()
	{
		Builder anicaFoodPepper = new Food.Builder();
		anicaFoodPepper.hunger(10);
		anicaFoodPepper.saturation(0.2f);
		anicaFoodPepper.effect( new EffectInstance(Effects.GLOWING,60,1), 1);
		anicaFoodPepper.setAlwaysEdible();
		anica_food_pepper = anicaFoodPepper.build();
	}
	
}
