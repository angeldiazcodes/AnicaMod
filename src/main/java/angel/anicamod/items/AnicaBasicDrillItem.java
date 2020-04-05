package angel.anicamod.items;

import java.util.List;
import java.util.UUID;

import angel.anicamod.AnicaMod;
import angel.anicamod.blocks.AnicaBasicCable;
import angel.anicamod.gui.RainItemGUI;
import angel.anicamod.tileentity.AnicaBatteryTileEntity;
import angel.anicamod.tileentity.AnicaEnergyStorage;
import angel.anicamod.util.helpers.KeyboardHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Item.Properties;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraft.util.ActionResultType;

public class AnicaBasicDrillItem extends Item {
	
    public AnicaEnergyStorage energy = null;
	
    private static boolean debug = true;
    
	public AnicaBasicDrillItem() {
		super(new Properties().group(AnicaMod.anicaModTab).maxStackSize(1) );
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_BASIC_DRILL));
		energy = createEnergy();
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		// "Enchantment glint" glow effect
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		// Tool Tip
		
		if (KeyboardHelper.isHoldingShift())
		{
			tooltip.add( new StringTextComponent( "\u00A7d" + "Anica's very special custom rain item! Feel the hatred of 10,000 years!" + "\u00A77"));
		} else
		{
			if ( stack.getTag() == null ) { stack.setTag( new CompoundNBT() ); stack.getTag().putInt("egy", 0 ); }
			
			tooltip.add( new StringTextComponent("Hold SHIFT for more information. Energy :" + stack.getTag().getInt("egy") ));			
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {		
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
		
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onItemUse ");
		// right click on a block and "use" the item
		return super.onItemUse(context);
	}
	
	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing ");
		
		World world = entity.getEntityWorld();
		BlockPos pos = entity.getPosition();
		
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing pos " + world.getBlockState(pos).toString());
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing pos " + world.getBlockState(pos.up()).toString());
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing pos " + world.getBlockState(pos.down()).toString());
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing pos " + world.getBlockState(pos.north()).toString());
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing pos " + world.getBlockState(pos.south()).toString());
		
		//entity.getPosition().
		//world.destroyBlock(pos, true);
		
        // Thinking about a drill:
        // BlockState junglePlanks = Blocks.BIRCH_PLANKS.getDefaultState();
        // world.setBlockState(this.getPos().up(), junglePlanks);
        // world.destroyBlock(this.getPos().up(), true ); // drop the block
		
		return super.onEntitySwing(stack, entity);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

		if ( !worldIn.isRemote ) 
		{
			// playerIn.sendMessage(new StringTextComponent( "Anica's Basic Drill"));
		}
		else
		{
			// TBD
		}
	
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
    private AnicaEnergyStorage createEnergy() {
        return new AnicaEnergyStorage(100000, 0, true);
    }
}
