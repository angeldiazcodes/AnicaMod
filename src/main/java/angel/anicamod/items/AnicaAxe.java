package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class AnicaAxe extends AxeItem {

	public AnicaAxe(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_AXE));
	}

}