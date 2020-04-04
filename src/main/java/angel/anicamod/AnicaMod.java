package angel.anicamod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import angel.anicamod.entities.AnicaModRegistry;
import angel.anicamod.proxy.ClientProxy;
import angel.anicamod.proxy.IProxy;
import angel.anicamod.proxy.ServerProxy;
import angel.anicamod.util.helpers.AnicaConfig;
import angel.anicamod.world.AnicaBiomeWorldType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod(AnicaMod.MODID)
@EventBusSubscriber(modid = AnicaMod.MODID, bus = Bus.MOD)
public class AnicaMod {
	
	// Anica Mod
	public static AnicaMod instance;
	public static final String MODID 						= "anicamod";
	public static final String PROTOCOL 					= "1.14.4-1";
	
	// Tab
	public static final String ANICA_TAB					= "anica";
	
	// Items
	public static final String ANICA_ITEM 					= "anica_item";
	public static final String ANICA_AXE 					= "anica_axe";
	public static final String ANICA_HOE 					= "anica_hoe";
	public static final String ANICA_PICKAXE				= "anica_pickaxe";
	public static final String ANICA_SHOVEL					= "anica_shovel";
	public static final String ANICA_SWORD 					= "anica_sword";
	public static final String ANICA_HELMET 				= "anica_helmet";
	public static final String ANICA_CHESTPLATE 			= "anica_chestplate";
	public static final String ANICA_LEGGINGS 				= "anica_leggings";
	public static final String ANICA_BOOTS	 				= "anica_boots";
	public static final String RAIN_ITEM	 				= "rain_item";
	public static final String ANICA_PEPPER_ITEM			= "anica_pepper_item";
	public static final String ANICA_ACID_BUCKET		 	= "anica_acid_bucket";
	
	// Blocks
	public static final String ANICA_BLOCK	 				= "anica_block";
	public static final String ANICA_ORE	 				= "anica_ore";
	
	public static final String ANICA_FURNACE				= "anica_furnace";
	public static final String ANICA_FURNACE_CONTAINER		= "anica_furnace_container";
	public static final String ANICA_FURNACE_TILE_ENTITY	= "anica_furnace_tile_entity";
	
	public static final String ANICA_GENERATOR				= "anica_generator";
	public static final String ANICA_GENERATOR_CONTAINER	= "anica_generator_container";
	public static final String ANICA_GENERATOR_TILE_ENTITY	= "anica_generator_tile_entity";
	
	public static final String ANICA_BATTERY				= "anica_battery";
	public static final String ANICA_BATTERY_CONTAINER		= "anica_battery_container";
	public static final String ANICA_BATTERY_TILE_ENTITY	= "anica_battery_tile_entity";
	
	public static final String ANICA_BASIC_SOLAR			= "anica_basic_solar";
	public static final String ANICA_BASIC_SOLAR_CONTAINER	= "anica_basic_solar_container";
	public static final String ANICA_BASIC_SOLAR_TILE_ENTITY= "anica_basic_solar_tile_entity";

	public static final String ANICA_BASIC_CABLE			= "anica_basic_cable";
	public static final String ANICA_BASIC_CABLE_CONTAINER	= "anica_basic_cable_container";
	public static final String ANICA_BASIC_CABLE_TILE_ENTITY= "anica_basic_cable_tile_entity";
	
	public static final String ANICA_CROP_PEPPER_BLOCK	 	= "anica_crop_pepper_block";
	
	public static final String ANICA_PLANK				 	= "anica_plank";
	public static final String ANICA_LOG					= "anica_log";
	public static final String ANICA_LEAVES					= "anica_leaves";
	public static final String ANICA_SAPLING				= "anica_sapling";

	// Textures
	public static final String ANICA_FURNACE_GUI 			= "textures/gui/anica_furnace_gui.png";
	public static final String ANICA_GENERATOR_GUI 			= "textures/gui/anica_generator_gui.png";
	public static final String ANICA_BATTERY_GUI 			= "textures/gui/anica_battery_gui.png";
	public static final String ANICA_BASIC_SOLAR_GUI 		= "textures/gui/anica_basic_solar_gui.png";
	public static final String ANICA_RAIN_ITEM_GUI			= "textures/gui/anica_rain_item_gui.png";
	public static final String ANICA_MOB_ENTITY_TEXTURE	    = "textures/entity/anica_mob_entity.png";
	public static final String ANICA_ACID_STILL_TEXTURE  	= "block/anica_acid_still";
	public static final String ANICA_ACID_FLOW_TEXTURE  	= "block/anica_acid_flow";
	
	// Mobs
	public static final String ANICA_MOB_ENTITY				= "anica_mob_entity";
	public static final String ANICA_MOB_ENTITY_EGG			= "anica_mob_entity_egg";
	
	// Biomes
	public static final String ANICA_BIOME					= "anica_biome";
	
	// Worlds
	public static final String ANICA_WORLD_TYPE				= "anica_world_type";
	public static WorldType anica_world_type				= new AnicaBiomeWorldType();
	
	// Food
	public static final String ANICA_FOOD_PEPPER			= "anica_food_pepper";
	
	// Structures
	public static final String ANICA_CABIN		 			= "anica_cabin";	
	
	// Fluids
	public static final String ANICA_ACID_STILL		  		= "anica_acid_still";
	public static final String ANICA_ACID_FLOW			  	= "anica_acid_flow";
	
	// Potions
	public static final String ANICA_POTION_ODD				= "anica_potion_odd";
	public static final String ANICA_POTION_LONG_ODD		= "anica_potion_long_odd";
	public static final String ANICA_POTION_SHORT_ODD		= "anica_potion_short_odd";
	
	// Paintings
	public static final String ANICA_MOD_PAINTING			= "anica_painting";
	
	public static final Logger logger = LogManager.getLogger(MODID);
	public static final String logStub = "[*****************]:";
	public static boolean debug = true;

	public static final ItemGroup anicaModTab = new AnicaItemGroup();  
	
	public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
	
	public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, "network"), () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals);

	public AnicaMod() {
        instance = this; 

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        AnicaModSoundList.SOUND.register(modEventBus);
        AnicaPaintingList.PAINTING_TYPES.register(modEventBus);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, AnicaConfig.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AnicaConfig.COMMON_CONFIG);

        // Register the setup method(s) for mod loading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
        
        AnicaConfig.loadConfig(AnicaConfig.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("anicamod-client.toml"));
        AnicaConfig.loadConfig(AnicaConfig.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("anicamod-common.toml"));
        
        MinecraftForge.EVENT_BUS.register(this);
        
	}
	
	private void setup( final FMLCommonSetupEvent event) {
        if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMod:setup: Setup message registered");
        proxy.init();

	}

	private void clientRegistries( final FMLClientSetupEvent event) {
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMod:clientRegistries: Client message registered");
		AnicaModRegistry.registryEntityRenders();
	}

	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {

		@SubscribeEvent
		public static void onBlockRegistry(final RegistryEvent.Register<Block> event)
		{
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "onBlockRegistry: delegating to AnicaModBlocks");
			AnicaModBlocks.register(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void onItemsRegistry(final RegistryEvent.Register<Item> event)
		{
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "onItemsRegistry: delegating to AnicaModItems");
			AnicaModItems.register(event.getRegistry());	
		}
		
		@SubscribeEvent
		public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event)
		{
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "onTileEntityRegistry: delegating to AnicaModBlocks");
			AnicaModBlocks.registerTiles(event.getRegistry());
		}
	
		@SubscribeEvent
		public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event)
		{
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "onContainerRegistry: delegating to AnicaModBlocks");
			AnicaModBlocks.registerContainers(event.getRegistry());
		}
		
        @SubscribeEvent
        public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> event) {
	    	if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMod:onEntityRegistry: delegating to AnicaModEntities"); 
	    	AnicaModEntities.registerEntities( event.getRegistry() );
        }
		
        @SubscribeEvent
        public static void onBiomeRegistry(final RegistryEvent.Register<Biome> event) {
	    	if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMod:onEntityRegistry: delegating to AnicaModBiomes"); 
	    	AnicaModBiomes.registerBiomes( event.getRegistry() );
        }
        
	    @SubscribeEvent
	    public static void registerStructures(final RegistryEvent.Register<Feature<?>> event) {
	    	if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMod:registerStructures" );
	    	AnicaModStrucutresList.registerStructures( event.getRegistry() );
	    }
        
	    @SubscribeEvent
	    public static void registerFluids(final RegistryEvent.Register<Fluid> event) {
	    	if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMod:registerFluids" );
	    	AnicaModFluidsList.registerFluids( event.getRegistry() );
	    }
        
		@SubscribeEvent
		public static void registerEffect(final RegistryEvent.Register<Effect> event) {
	    	if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMod:registerEffect" );
	    	AnicaModEffectList.registerEffect( event.getRegistry() );			
		}
	    
		@SubscribeEvent
		public static void registerPotion(final RegistryEvent.Register<Potion> event) {
	    	if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMod:registerPotion" );
	    	AnicaModPotionList.registerPotion( event.getRegistry() );
		}
	    // nothing to do here - already done
	    @SubscribeEvent
	    public static void registerItems(final RegistryEvent.Register<Item> event) {
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMod:registerItems: nothing to do here, handled by AnicaModItems");
	    }
	        
	    @SubscribeEvent
	    public static void registerBlocks(final RegistryEvent.Register<Block> event) 
	    {
	        if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMod:registerBlocks: nothing to do here, handled by AnicaModBlocks");
	    }
	        
	    @SubscribeEvent
	    public static void registerTE(RegistryEvent.Register<TileEntityType<?>> evt) 
	    {
	    	 if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMod:registerTE: nothing to do here, handled by AnicaModBlocks");
	    }    
	}
}
