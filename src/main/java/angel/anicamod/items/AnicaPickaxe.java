package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ResourceLocation;

public class AnicaPickaxe extends PickaxeItem {

	public AnicaPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_PICKAXE));
	}

}
