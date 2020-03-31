package angel.anicamod.items;

import angel.anicamod.AnicaMod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.util.ResourceLocation;

public class AnicaBoots extends ArmorItem {

	public AnicaBoots(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_BOOTS));
	}

}
