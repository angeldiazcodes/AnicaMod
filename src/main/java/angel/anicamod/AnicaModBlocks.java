package angel.anicamod;

import angel.anicamod.blocks.AnicaAcidBlock;
import angel.anicamod.blocks.AnicaBasicCable;
import angel.anicamod.blocks.AnicaBasicSolar;
import angel.anicamod.blocks.AnicaBattery;
import angel.anicamod.blocks.AnicaBlock;
import angel.anicamod.blocks.AnicaFurnace;
import angel.anicamod.blocks.AnicaGenerator;
import angel.anicamod.blocks.AnicaLeaves;
import angel.anicamod.blocks.AnicaLog;
import angel.anicamod.blocks.AnicaOre;
import angel.anicamod.blocks.AnicaPlank;
import angel.anicamod.blocks.AnicaSapling;
import angel.anicamod.blocks.AnicaCropPepperBlock;
import angel.anicamod.containers.AnicaBasicCableContainer;
import angel.anicamod.containers.AnicaBasicSolarContainer;
import angel.anicamod.containers.AnicaBatteryContainer;
import angel.anicamod.containers.AnicaFurnaceContainer;
import angel.anicamod.containers.AnicaGeneratorContainer;
import angel.anicamod.tileentity.AnicaBasicCableTileEntity;
import angel.anicamod.tileentity.AnicaBasicSolarTileEntity;
import angel.anicamod.tileentity.AnicaBatteryTileEntity;
import angel.anicamod.tileentity.AnicaFurnaceTileEntity;
import angel.anicamod.tileentity.AnicaGeneratorTileEntity;
import angel.anicamod.util.helpers.ModUtil;
import angel.anicamod.world.feature.AnicaTree;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class AnicaModBlocks {

	// Blocks
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BLOCK)
	public static AnicaBlock anica_block = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_ORE)
	public static AnicaOre anica_ore = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_FURNACE)
	public static AnicaFurnace anica_furnace = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_GENERATOR)
	public static AnicaGenerator anica_generator = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BATTERY)
	public static AnicaBattery anica_battery = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BASIC_SOLAR)
	public static AnicaBasicSolar anica_basic_solar = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BASIC_CABLE)
	public static AnicaBasicCable anica_basic_cable = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_PLANK)
	public static AnicaPlank anica_plank = ModUtil._null();

	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_LOG)
	public static AnicaLog anica_log = ModUtil._null();

	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_LEAVES)
	public static AnicaLeaves anica_leaves = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_SAPLING)
	public static AnicaSapling anica_sapling = ModUtil._null();
	
	// Containers
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_FURNACE_CONTAINER)
	public static ContainerType<AnicaFurnaceContainer> anica_furnace_container = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_GENERATOR_CONTAINER)
	public static ContainerType<AnicaGeneratorContainer> anica_generator_container = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BATTERY_CONTAINER)
	public static ContainerType<AnicaBatteryContainer> anica_battery_container = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BASIC_SOLAR_CONTAINER)
	public static ContainerType<AnicaBasicSolarContainer> anica_basic_solar_container = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BASIC_CABLE_CONTAINER)
	public static ContainerType<AnicaBasicCableContainer> anica_basic_cable_container = ModUtil._null();
	
	// Tile entities
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_FURNACE_TILE_ENTITY)
	public static TileEntityType<?> anica_furnace_tile_entity = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_GENERATOR_TILE_ENTITY)
	public static TileEntityType<?> anica_generator_tile_entity = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BATTERY_TILE_ENTITY)
	public static TileEntityType<?> anica_battery_tile_entity = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BASIC_SOLAR_TILE_ENTITY)
	public static TileEntityType<?> anica_basic_solar_tile_entity = ModUtil._null();
	
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_BASIC_CABLE_TILE_ENTITY)
	public static TileEntityType<?> anica_basic_cable_tile_entity = ModUtil._null();
	
	// Crops
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_CROP_PEPPER_BLOCK)
	public static AnicaCropPepperBlock anica_crop_pepper_block = ModUtil._null();
	
	// Fluids
	@ObjectHolder(AnicaMod.MODID + ":" + AnicaMod.ANICA_ACID_STILL) // ANICA_ACID
	public static AnicaAcidBlock anica_acid_block = ModUtil._null();
	
	public static void registerTiles(IForgeRegistry<TileEntityType<?>> registry)
	{
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerTiles: Tiles registered " + registry.toString());
		
		//
		// anica_furnace
		//
		
		anica_furnace_tile_entity  = TileEntityType.Builder.create(AnicaFurnaceTileEntity::new, AnicaModBlocks.anica_furnace).build(null);
		//  associate the tile entity type with the blocks that need it 
		anica_furnace_tile_entity.setRegistryName( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_FURNACE) ); // ANICA_FURNACE_TILE_ENTITY ANICA_FURNACE
		registry.register(anica_furnace_tile_entity); 
		
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerTiles: Tiles registered anica_furnace_tile_entity " + anica_furnace_tile_entity.getRegistryName().toString() );
		
		//
		// anica_generator
		//
		
		anica_generator_tile_entity  = TileEntityType.Builder.create(AnicaGeneratorTileEntity::new, AnicaModBlocks.anica_generator).build(null);
		//  associate the tile entity type with the blocks that need it 
		anica_generator_tile_entity.setRegistryName( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_GENERATOR) ); 
		registry.register(anica_generator_tile_entity); 
		
		//
		// anica_battery
		//
		
		anica_battery_tile_entity  = TileEntityType.Builder.create(AnicaBatteryTileEntity::new, AnicaModBlocks.anica_battery).build(null);
		//  associate the tile entity type with the blocks that need it 
		anica_battery_tile_entity.setRegistryName( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_BATTERY) ); 
		registry.register(anica_battery_tile_entity); 

		//
		// anica_basic_solar
		//
		
		anica_basic_solar_tile_entity  = TileEntityType.Builder.create(AnicaBasicSolarTileEntity::new, AnicaModBlocks.anica_basic_solar).build(null);
		//  associate the tile entity type with the blocks that need it 
		anica_basic_solar_tile_entity.setRegistryName( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_BASIC_SOLAR) ); 
		registry.register(anica_basic_solar_tile_entity); 
		
		//
		// anica_basic_cable
		//
		
		anica_basic_cable_tile_entity  = TileEntityType.Builder.create(AnicaBasicCableTileEntity::new, AnicaModBlocks.anica_basic_cable).build(null);
		//  associate the tile entity type with the blocks that need it 
		anica_basic_cable_tile_entity.setRegistryName( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_BASIC_CABLE) ); 
		registry.register(anica_basic_cable_tile_entity); 
		
		
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerTiles: Tiles registered anica_generator_tile_entity " + anica_generator_tile_entity.getRegistryName().toString() );
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerTiles: Tiles registered anica_furnace_tile_entity " + anica_furnace_tile_entity.toString());
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerTiles: Tiles registered anica_battery_tile_entity " + anica_battery_tile_entity.toString());	
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerTiles: Tiles registered anica_basic_solar_tile_entity " + anica_basic_solar_tile_entity.toString());
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerTiles: Tiles registered anica_basic_cable_tile_entity " + anica_basic_cable_tile_entity.toString());
	}
	
	public static void registerContainers(IForgeRegistry<ContainerType<?>> registry)
	{
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers: Containers registered");
		
		//
		// anica_furnace_container
		//
		
		anica_furnace_container = IForgeContainerType.create( (windowId, inv, data) -> 
		{ 
			BlockPos pos = data.readBlockPos();
			
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + windowId );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + pos.toString() );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + AnicaMod.proxy.getClientWorld() );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + AnicaMod.proxy.getClientPlayer());
			
			return new AnicaFurnaceContainer(windowId, AnicaMod.proxy.getClientWorld(), pos, inv, AnicaMod.proxy.getClientPlayer());
		} );
		// associate the container type with the blocks that need it 
		anica_furnace_container.setRegistryName( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_FURNACE )); // ANICA_FURNACE_CONTAINER ANICA_FURNACE
		
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers: anica_furnace_container" + anica_furnace_container.getRegistryName().toString() );
		
		registry.register(anica_furnace_container);
		
		// 
		// anica_generator_container
		//
		
		anica_generator_container = IForgeContainerType.create( (windowId, inv, data) -> 
		{ 
			BlockPos pos = data.readBlockPos();
			
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + windowId );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + pos.toString() );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + AnicaMod.proxy.getClientWorld() );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + AnicaMod.proxy.getClientPlayer());
			
			return new AnicaGeneratorContainer(windowId, AnicaMod.proxy.getClientWorld(), pos, inv, AnicaMod.proxy.getClientPlayer());
		} );
		// associate the container type with the blocks that need it 
		anica_generator_container.setRegistryName( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_GENERATOR )); // ANICA_FURNACE_CONTAINER ANICA_FURNACE
		
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers: anica_generator_container " + anica_generator_container.getRegistryName().toString() );
		
		registry.register(anica_generator_container);
		
		// 
		// anica_battery_container
		//
		
		anica_battery_container = IForgeContainerType.create( (windowId, inv, data) -> 
		{ 
			BlockPos pos = data.readBlockPos();
			
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + windowId );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + pos.toString() );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + AnicaMod.proxy.getClientWorld() );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + AnicaMod.proxy.getClientPlayer());
			
			return new AnicaBatteryContainer(windowId, AnicaMod.proxy.getClientWorld(), pos, inv, AnicaMod.proxy.getClientPlayer());
		} );
		// associate the container type with the blocks that need it 
		anica_battery_container.setRegistryName( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_BATTERY )); 
		
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers: anica_battery_container " + anica_battery_container.getRegistryName().toString() );
		
		registry.register(anica_battery_container);
		
		// 
		// anica_basic_solar_container
		//
		
		anica_basic_solar_container = IForgeContainerType.create( (windowId, inv, data) -> 
		{ 
			BlockPos pos = data.readBlockPos();
			
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + windowId );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + pos.toString() );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + AnicaMod.proxy.getClientWorld() );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + AnicaMod.proxy.getClientPlayer());
			
			return new AnicaBasicSolarContainer(windowId, AnicaMod.proxy.getClientWorld(), pos, inv, AnicaMod.proxy.getClientPlayer());
		} );
		// associate the container type with the blocks that need it 
		anica_basic_solar_container.setRegistryName( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_BASIC_SOLAR )); 
		
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers: anica_basic_solar_container " + anica_basic_solar_container.getRegistryName().toString() );
		
		registry.register(anica_basic_solar_container);
		
		// 
		// anica_basic_cable_container
		//
		
		anica_basic_cable_container = IForgeContainerType.create( (windowId, inv, data) -> 
		{ 
			BlockPos pos = data.readBlockPos();
			
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + windowId );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + pos.toString() );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + AnicaMod.proxy.getClientWorld() );
			if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers:" + AnicaMod.proxy.getClientPlayer());
			
			return new AnicaBasicCableContainer(windowId, AnicaMod.proxy.getClientWorld(), pos, inv, AnicaMod.proxy.getClientPlayer());
		} );
		// associate the container type with the blocks that need it 
		anica_basic_cable_container.setRegistryName( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_BASIC_CABLE )); 
		
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers: anica_basic_cable_container " + anica_basic_cable_container.getRegistryName().toString() );
		
		registry.register(anica_basic_cable_container);
		
		
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "registerContainers: Containers registered Done!");
	}
	
	public static void register(IForgeRegistry<Block> registry)
	{
		if (AnicaMod.debug) AnicaMod.logger.info(AnicaMod.logStub + "register: Block registered");
    	
		anica_block = new AnicaBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL));
		registry.register(anica_block);
		
		anica_ore = new AnicaOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL));
		registry.register(anica_ore);
		
		anica_furnace = new AnicaFurnace(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL));
		registry.register(anica_furnace);	
		
		anica_generator = new AnicaGenerator(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL));
		registry.register(anica_generator);	
	
		anica_battery = new AnicaBattery(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL));
		registry.register(anica_battery);	
		
		anica_basic_solar = new AnicaBasicSolar(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL));
		registry.register(anica_basic_solar);	
		
		anica_basic_cable = new AnicaBasicCable(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL));
		registry.register(anica_basic_cable);	
		
		anica_crop_pepper_block = new AnicaCropPepperBlock();
		registry.register(anica_crop_pepper_block);
		
		anica_plank = new AnicaPlank(  Block.Properties.from(Blocks.OAK_PLANKS)); 
		registry.register( anica_plank );
			
		anica_log = new AnicaLog( MaterialColor.WOOD, Block.Properties.from(Blocks.ACACIA_LOG));
		registry.register( anica_log );
		
		anica_leaves = new AnicaLeaves( Block.Properties.from(Blocks.DARK_OAK_LEAVES));
		registry.register( anica_leaves );	
		
		anica_sapling = new AnicaSapling(  () -> new AnicaTree() , Block.Properties.from( Blocks.OAK_SAPLING)) ;
		registry.register( anica_sapling );
		
		anica_acid_block = new AnicaAcidBlock( AnicaModFluidsList.ANICA_ACID_STILL, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100f).noDrops());
		registry.register( anica_acid_block );
		
	}
}
