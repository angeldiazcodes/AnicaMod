package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.util.ResourceLocation;

public class AnicaAcidBucketItem extends BucketItem {

	public AnicaAcidBucketItem(Fluid containedFluidIn, Properties builder) {
		super(containedFluidIn, builder.maxStackSize(1));
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_ACID_BUCKET));
	}

}
