package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.ResourceLocation;

public class AnicaShovel extends ShovelItem {

	public AnicaShovel(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_SHOVEL));
	}

}
