package angel.anicamod.blocks;

import javax.annotation.Nullable;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class AnicaBasicCable extends Block  {
	
	private static boolean debug = false;
	//private static final VoxelShape BOX = VoxelShapes.create(6, 6, 6, 10, 10, 10); //  do not know the right shape to use - https://github.com/raoulvdberge/refinedstorage/blob/mc1.15/src/main/java/com/raoulvdberge/refinedstorage/block/CableBlock.java
	
    private static final BooleanProperty NORTH = BooleanProperty.create("north");
    private static final BooleanProperty EAST = BooleanProperty.create("east");
    private static final BooleanProperty SOUTH = BooleanProperty.create("south");
    private static final BooleanProperty WEST = BooleanProperty.create("west");
    private static final BooleanProperty UP = BooleanProperty.create("up");
    private static final BooleanProperty DOWN = BooleanProperty.create("down");

    protected static final VoxelShape HOLDER_NORTH = makeCuboidShape(7, 7, 2, 9, 9, 6);
    protected static final VoxelShape HOLDER_EAST = makeCuboidShape(10, 7, 7, 14, 9, 9);
    protected static final VoxelShape HOLDER_SOUTH = makeCuboidShape(7, 7, 10, 9, 9, 14);
    protected static final VoxelShape HOLDER_WEST = makeCuboidShape(2, 7, 7, 6, 9, 9);
    protected static final VoxelShape HOLDER_UP = makeCuboidShape(7, 10, 7, 9, 14, 9);
    protected static final VoxelShape HOLDER_DOWN = makeCuboidShape(7, 2, 7, 9, 6, 9);

    private static final VoxelShape SHAPE_CORE = makeCuboidShape(6, 6, 6, 10, 10, 10);
    private static final VoxelShape SHAPE_NORTH = makeCuboidShape(6, 6, 0, 10, 10, 6);
    private static final VoxelShape SHAPE_EAST = makeCuboidShape(10, 6, 6, 16, 10, 10);
    private static final VoxelShape SHAPE_SOUTH = makeCuboidShape(6, 6, 10, 10, 10, 16);
    private static final VoxelShape SHAPE_WEST = makeCuboidShape(0, 6, 6, 6, 10, 10);
    private static final VoxelShape SHAPE_UP = makeCuboidShape(6, 10, 6, 10, 16, 10);
    private static final VoxelShape SHAPE_DOWN = makeCuboidShape(6, 0, 6, 10, 6, 10);
    
	public AnicaBasicCable(final Properties properties) {			
		super( properties );	
		if (AnicaBasicCable.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicCable: constructor ");
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_BASIC_CABLE));
		
		this.setDefaultState(getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(UP, false).with(DOWN, false));
	}

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState state, Direction dir, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
        return getState(state, world, pos);
    }
    
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
        VoxelShape shape = SHAPE_CORE;
 
        if (state.get(NORTH)) {
            shape = VoxelShapes.or(shape, SHAPE_NORTH);
        }

        if (state.get(EAST)) {
            shape = VoxelShapes.or(shape, SHAPE_EAST);
        }

        if (state.get(SOUTH)) {
            shape = VoxelShapes.or(shape, SHAPE_SOUTH);
        }

        if (state.get(WEST)) {
            shape = VoxelShapes.or(shape, SHAPE_WEST);
        }

        if (state.get(UP)) {
            shape = VoxelShapes.or(shape, SHAPE_UP);
        }

        if (state.get(DOWN)) {
            shape = VoxelShapes.or(shape, SHAPE_DOWN);
        }

        return shape;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
        VoxelShape shape = SHAPE_CORE;

        if (state.get(NORTH)) {
            shape = VoxelShapes.or(shape, SHAPE_NORTH);
        }

        if (state.get(EAST)) {
            shape = VoxelShapes.or(shape, SHAPE_EAST);
        }

        if (state.get(SOUTH)) {
            shape = VoxelShapes.or(shape, SHAPE_SOUTH);
        }

        if (state.get(WEST)) {
            shape = VoxelShapes.or(shape, SHAPE_WEST);
        }

        if (state.get(UP)) {
            shape = VoxelShapes.or(shape, SHAPE_UP);
        }

        if (state.get(DOWN)) {
            shape = VoxelShapes.or(shape, SHAPE_DOWN);
        }

        return shape;
	}

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return getState(getDefaultState(), ctx.getWorld(), ctx.getPos());
    }

	/* 
	 * boolean hasTileEntity(final BlockState state)
	 * 
	 * Description: Make sure the tile entity is  associated with this block
	 * 
	 */
	
	@Override
	public boolean hasTileEntity(final BlockState state) {
		return true;
	}
	
	/* 
	 * TileEntity createTileEntity(final BlockState state, final IBlockReader world)
	 * 
	 * Description: Make sure the tile entity is  associated with this block
	 * 
	 */
	
	@Nullable
	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
		if (AnicaBasicCable.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicCable: Creating tile entity " + AnicaModBlocks.anica_basic_cable_tile_entity.toString() );
		return AnicaModBlocks.anica_basic_cable_tile_entity.create();
	}
	

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (entity != null) {
        	if (AnicaBasicCable.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicCable: onBlockPlacedBy ");
            world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
        }
    }

    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
    	if (AnicaBasicCable.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicCable: getFacingFromEntity ");
    	return Direction.getFacingFromVector((float) (entity.lastTickPosX - clickedBlock.getX()), (float) (entity.lastTickPosY - clickedBlock.getY()), (float) (entity.lastTickPosZ - clickedBlock.getZ()));
    }

    /*
     * getLightValue(BlockState state) and fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
     * 
     * Description: would be nice if our power generation actually had a different front panel when it is generating power. 
     * To do that we need to add a new property to our block
     * 
     */
    
    @SuppressWarnings("deprecation")
	@Override
    public int getLightValue(BlockState state) {
        return state.get(BlockStateProperties.POWERED) ? super.getLightValue(state) : 0;
    }
    
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    	super.fillStateContainer(builder);
        builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }
    
    
    private boolean hasNode(IWorld world, BlockPos pos, BlockState state, Direction direction) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile == null) {
            return false;
        } 
        if (AnicaBasicCable.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBasicCable: hasNode is air " + tile.getBlockState().isAir() );
        
        return !tile.getBlockState().isAir();
    }
    
    private BlockState getState(BlockState currentState, IWorld world, BlockPos pos) {
        boolean north = hasNode(world, pos.offset(Direction.NORTH), currentState, Direction.SOUTH);
        boolean east = hasNode(world, pos.offset(Direction.EAST), currentState, Direction.WEST);
        boolean south = hasNode(world, pos.offset(Direction.SOUTH), currentState, Direction.NORTH);
        boolean west = hasNode(world, pos.offset(Direction.WEST), currentState, Direction.EAST);
        boolean up = hasNode(world, pos.offset(Direction.UP), currentState, Direction.DOWN);
        boolean down = hasNode(world, pos.offset(Direction.DOWN), currentState, Direction.UP);

        return currentState
            .with(NORTH, north)
            .with(EAST, east)
            .with(SOUTH, south)
            .with(WEST, west)
            .with(UP, up)
            .with(DOWN, down);
    }
}
