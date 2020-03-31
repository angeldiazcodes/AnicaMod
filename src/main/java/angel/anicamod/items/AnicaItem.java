package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class AnicaItem extends Item {

	public AnicaItem() {
		super(new Properties().group(AnicaMod.anicaModTab) );
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaItem: constructor ");
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_ITEM));
	}

}
