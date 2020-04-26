package angel.anicamod.blocks;

import javax.annotation.Nullable;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class AnicaFurnace extends Block  {
	
	private static boolean debug = false;
	
	public AnicaFurnace(final Properties properties) {			
		super( properties );	
		AnicaMod.log(debug,"AnicaFurnace: constructor ");
		setRegistryName(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_FURNACE));
	}

	/*
	 * ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result)
	 * 
	 * Description: This method will be called when the user activates (right clicks) our block
	 * 
	 */
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
			Hand hand, BlockRayTraceResult result) {
		@SuppressWarnings("deprecation")
		ActionResultType rt = super.onBlockActivated(state, world, pos, player, hand, result);
    	
		AnicaMod.log(debug,"AnicaFurnace: onBlockActivated " + rt.toString());
    	
        if (!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof INamedContainerProvider) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
            return rt;
        }
        return rt;
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
		AnicaMod.log(debug,"AnicaFurnace: Creating tile entity " + AnicaModBlocks.anica_furnace_tile_entity.toString() );
		return AnicaModBlocks.anica_furnace_tile_entity.create();
	}
	
	@SuppressWarnings({ "deprecation" })
	@Override
	public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		AnicaMod.log(debug,"AnicaFurnace: onBlockClicked ");
		super.onBlockClicked(state, worldIn, pos, player);
	}

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (entity != null) {
        	AnicaMod.log(debug, "AnicaBlock: onBlockPlacedBy ");
            world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
        }
    }

    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
    	AnicaMod.log(debug,"AnicaBlock: getFacingFromEntity ");
    	return Direction.getFacingFromVector((float) (entity.lastTickPosX - clickedBlock.getX()), (float) (entity.lastTickPosY - clickedBlock.getY()), (float) (entity.lastTickPosZ - clickedBlock.getZ()));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    	AnicaMod.log(debug, "AnicaBlock: fillStateContainer ");
        builder.add(BlockStateProperties.FACING);
    }
}
