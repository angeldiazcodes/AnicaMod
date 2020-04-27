package angel.anicamod;

import angel.anicamod.items.AnicaAcidBucketItem;
import angel.anicamod.items.AnicaAxe;
import angel.anicamod.items.AnicaBasicCableItem;
import angel.anicamod.items.AnicaBasicDrillItem;
import angel.anicamod.items.AnicaBasicSolarItem;
import angel.anicamod.items.AnicaBatteryItem;
import angel.anicamod.items.AnicaBlockItem;
import angel.anicamod.items.AnicaBoots;
import angel.anicamod.items.AnicaChestplate;
import angel.anicamod.items.AnicaFurnaceItem;
import angel.anicamod.items.AnicaGeneratorItem;
import angel.anicamod.items.AnicaHelmet;
import angel.anicamod.items.AnicaHoe;
import angel.anicamod.items.AnicaItem;
import angel.anicamod.items.AnicaLeavesItem;
import angel.anicamod.items.AnicaLeggings;
import angel.anicamod.items.AnicaLogItem;
import angel.anicamod.items.AnicaOreItem;
import angel.anicamod.items.AnicaPepperItem;
import angel.anicamod.items.AnicaPickaxe;
import angel.anicamod.items.AnicaPlankItem;
import angel.anicamod.items.AnicaSaplingItem;
import angel.anicamod.items.AnicaShovel;
import angel.anicamod.items.AnicaSword;
import angel.anicamod.items.EnderPearlOreItem;
import angel.anicamod.items.RainItem;
import angel.anicamod.lists.ArmorMaterialList;
import angel.anicamod.lists.ToolMaterialList;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class AnicaModItems {
	private static boolean debug = true;
	
	// Items
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_ITEM)
	public static AnicaItem anica_item;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_AXE)
	public static Item anica_axe;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_HOE)
	public static Item anica_hoe;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_PICKAXE)
	public static Item anica_pickaxe;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_SHOVEL)
	public static Item anica_shovel;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_SWORD)
	public static Item anica_sword;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_HELMET)
	public static Item anica_helmet;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_CHESTPLATE)
	public static Item anica_chestplate;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_LEGGINGS)
	public static Item anica_leggings;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BOOTS)
	public static Item anica_boots;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.RAIN_ITEM)
	public static Item rain_item;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_ACID_BUCKET)	
	public static  Item anica_bucket_item;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BASIC_DRILL)	
	public static  Item anica_basic_drill;
	
	// Blocks
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BLOCK)
	public static Item anica_block;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_ORE)
	public static Item anica_ore;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ENDER_PEARL_ORE)
	public static Item ender_pearl_ore;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_FURNACE)
	public static Item anica_furnace;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_GENERATOR)
	public static Item anica_generator;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BATTERY)
	public static Item anica_battery;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BASIC_SOLAR)
	public static Item anica_basic_solar;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BASIC_CABLE)
	public static Item anica_basic_cable;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_PLANK)
	public static Item anica_plank;

	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_LOG)
	public static Item anica_log;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_LEAVES)
	public static Item anica_leaves;
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_SAPLING)
	public static Item anica_sapling;
	
	// spawn eggs
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_MOB_ENTITY_EGG)
	public static Item anica_mob_entity_egg;
	
	// Food
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_PEPPER_ITEM)
	public static Item anica_pepper_item;
	
	public static void register(IForgeRegistry<Item> registry)
	{
		
		AnicaMod.log(debug, "AnicaModItems:register: event recieved " + registry.toString());
		
		// Items
		anica_item = new AnicaItem(); 
		registry.register(anica_item);
		
		rain_item = new RainItem(); 
		registry.register(rain_item);
		
		anica_axe = new AnicaAxe(ToolMaterialList.anica, -1.0f, 6.0f, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_axe);
		
		anica_hoe = new AnicaHoe(ToolMaterialList.anica, 6.0f, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_hoe);
		
		anica_pickaxe = new AnicaPickaxe(ToolMaterialList.anica, -1, 6.0f, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_pickaxe);
		
		anica_shovel = new AnicaShovel(ToolMaterialList.anica, -1.0f, 6.0f, new Item.Properties().group(AnicaMod.anicaModTab));
	    registry.register(anica_shovel);
	    
	    anica_sword = new AnicaSword(ToolMaterialList.anica, 0, 6.0f, new Item.Properties().group(AnicaMod.anicaModTab));
	    registry.register(anica_sword);    
	    
	    anica_helmet = new AnicaHelmet(ArmorMaterialList.anica, EquipmentSlotType.HEAD, new Item.Properties().group(AnicaMod.anicaModTab));
	    registry.register(anica_helmet);
	    
	    anica_chestplate = new AnicaChestplate(ArmorMaterialList.anica, EquipmentSlotType.CHEST, new Item.Properties().group(AnicaMod.anicaModTab));
	    registry.register(anica_chestplate);
	    
	    anica_leggings = new AnicaLeggings(ArmorMaterialList.anica, EquipmentSlotType.LEGS, new Item.Properties().group(AnicaMod.anicaModTab));
	    registry.register(anica_leggings);
	    
	    anica_boots = new AnicaBoots(ArmorMaterialList.anica, EquipmentSlotType.FEET, new Item.Properties().group(AnicaMod.anicaModTab));
	    registry.register(anica_boots);
	    
	    anica_bucket_item = new AnicaAcidBucketItem( AnicaModFluidsList.ANICA_ACID_STILL, new Item.Properties().group(AnicaMod.anicaModTab));
	    registry.register(anica_bucket_item);
	    
	    anica_basic_drill = new AnicaBasicDrillItem();
	    registry.register(anica_basic_drill);
	    
	    // Blocks
	    anica_block = new AnicaBlockItem( AnicaModBlocks.anica_block, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_block);
		
		anica_ore = new AnicaOreItem(AnicaModBlocks.anica_ore, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_ore);	
		
		ender_pearl_ore = new EnderPearlOreItem(AnicaModBlocks.ender_pearl_ore, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(ender_pearl_ore);	
		
		anica_furnace = new AnicaFurnaceItem(AnicaModBlocks.anica_furnace, new Item.Properties().group(AnicaMod.anicaModTab).maxStackSize(1));
		registry.register(anica_furnace);	
		
		anica_generator = new AnicaGeneratorItem(AnicaModBlocks.anica_generator, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_generator);	
	
		anica_battery = new AnicaBatteryItem(AnicaModBlocks.anica_battery, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_battery);	
		
		anica_basic_solar = new AnicaBasicSolarItem(AnicaModBlocks.anica_basic_solar, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_basic_solar);	
	
		anica_basic_cable = new AnicaBasicCableItem(AnicaModBlocks.anica_basic_cable, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_basic_cable);	
		
		anica_plank = new AnicaPlankItem( AnicaModBlocks.anica_plank, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_plank);
		
		anica_log = new AnicaLogItem( AnicaModBlocks.anica_log, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_log);
		
		anica_leaves = new AnicaLeavesItem( AnicaModBlocks.anica_leaves, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_leaves);
	
		anica_sapling = new AnicaSaplingItem( AnicaModBlocks.anica_sapling, new Item.Properties().group(AnicaMod.anicaModTab));
		registry.register(anica_sapling);
		
		// Entities
		anica_mob_entity_egg = AnicaModEntities.registerEntitySpawnEgg(AnicaModEntities.anica_mob_entity, 0x280bba, 0x280bba, AnicaMod.ANICA_MOB_ENTITY_EGG);
		registry.register(anica_mob_entity_egg);
	
		// Food
		AnicaModFoodList.createFood();
		anica_pepper_item = new AnicaPepperItem( AnicaModBlocks.anica_crop_pepper_block, new Item.Properties().group(AnicaMod.anicaModTab).food( AnicaModFoodList.anica_food_pepper));
		registry.register(anica_pepper_item);
				
		AnicaMod.log(debug, "AnicaModItems:register: event recieved Done!");
        
	}
	
}
