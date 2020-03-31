package angel.anicamod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class AnicaItemGroup extends ItemGroup {

	public AnicaItemGroup() {
		super(AnicaMod.ANICA_TAB);
	}

	@Override
	public ItemStack createIcon() {
	
		return new ItemStack(Item.BLOCK_TO_ITEM.get(AnicaModBlocks.anica_block));
	}

	
}
