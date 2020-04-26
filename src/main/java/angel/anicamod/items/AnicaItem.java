package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class AnicaItem extends Item {
	private static boolean debug = false;
	
	public AnicaItem() {
		super(new Properties().group(AnicaMod.anicaModTab) );
		AnicaMod.log(debug, "AnicaItem: constructor ");
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_ITEM));
	}

}
