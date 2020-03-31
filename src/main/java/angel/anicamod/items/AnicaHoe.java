package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.util.ResourceLocation;

public class AnicaHoe extends HoeItem {

	public AnicaHoe(IItemTier tier, float attackSpeedIn, Properties builder) {
		super(tier, attackSpeedIn, builder);
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_HOE));
	}

}
