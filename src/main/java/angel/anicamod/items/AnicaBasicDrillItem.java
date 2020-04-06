package angel.anicamod.items;

import java.util.List;

import angel.anicamod.AnicaMod;
import angel.anicamod.lists.ToolMaterialList;
import angel.anicamod.tileentity.AnicaEnergyStorage;
import angel.anicamod.util.helpers.KeyboardHelper;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.util.ActionResultType;

public class AnicaBasicDrillItem extends Item {
	
    public AnicaEnergyStorage energy = null;
	
    private static boolean debug = true;
    
	public AnicaBasicDrillItem() {
		super(new Properties().group(AnicaMod.anicaModTab).maxStackSize(1) ); // 
		//super(ToolMaterialList.anica, -1, 6.0f, new Properties().group(AnicaMod.anicaModTab).maxStackSize(1) );
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_BASIC_DRILL));
		energy = createEnergy();
	}
	
	/*
	 * Called before a block is broken. Return true to prevent default block harvesting. Note: In SMP, this is called on both client and server sides!
	 */
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onBlockStartBreak ");
		return super.onBlockStartBreak(itemstack, pos, player);
	}
	
	/*
	 * Called each tick while using an item.
	 */
	
	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onUsingTick ");
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		// "Enchantment glint" glow effect
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
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
	
	/*
	 * Called each tick as long the item is on a player inventory. 
	 */
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {		
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
		
	}
	
	/*
	 * Called when this item is used when targetting a Block (right click)
	 */
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onItemUse ");
		
		BlockPos pos = context.getPos();
		World world = context.getWorld();   
		world.destroyBlock(pos, true); // drop the block
		world.setBlockState(pos, Blocks.AIR.getDefaultState());
		
		return ActionResultType.SUCCESS;
	}
	
	/*
	 * Called when the player Left Clicks (attacks) an entity. Processed before damage is done, if 
	 * return value is true further processing is canceled and the entity is not attacked.
	 */
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onLeftClickEntity " + entity.toString());
		// not getting this called - do not know why
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	/*
	 * Called when a entity tries to play the 'swing' animation.
	 */
	
	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		// if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing ");
		
		World world = entity.getEntityWorld();
		BlockPos pos = entity.getPosition();
		/*
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing pos " + world.getBlockState(pos).toString());
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing pos up " + world.getBlockState(pos.up()).toString());
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing pos down " + world.getBlockState(pos.down()).toString());
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing pos north " + world.getBlockState(pos.north()).toString());
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing pos south " + world.getBlockState(pos.south()).toString());
		if (AnicaBasicDrillItem.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicDrillItem: onEntitySwing entity  " + entity.getBlockState().toString());
		*/
		
		
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
