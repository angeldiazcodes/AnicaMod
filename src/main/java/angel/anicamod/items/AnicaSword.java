package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;

public class AnicaSword extends SwordItem {

	public AnicaSword(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
		super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_SWORD));
	}

}
