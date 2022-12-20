package com.vtwo.furtelcraft.furtelcraft.blocks;

import com.vtwo.furtelcraft.furtelcraft.blockentities.RackEntity;
import com.vtwo.furtelcraft.furtelcraft.blockentities.TubeHolderEntity;
import com.vtwo.furtelcraft.furtelcraft.init.BlockInit;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Rack extends BlockWithEntity implements BlockEntityProvider {
    public static final VoxelShape NORTH_SHAPE;
    public static final VoxelShape EAST_SHAPE;
    public static final VoxelShape SOUTH_SHAPE;
    public static final VoxelShape WEST_SHAPE;
    static {
        NORTH_SHAPE = Block.createCuboidShape(-15.0,0.0,4.0,15.0,16.0,16.0);
        EAST_SHAPE = Block.createCuboidShape(0.0,0.0,-15.0,12.0,16.0,15.0);
        SOUTH_SHAPE = Block.createCuboidShape(1.0,0.0,0.0,31.0,16.0,12.0);
        WEST_SHAPE = Block.createCuboidShape(4.0,0.0,1.0,16.0,16.0,31.0);
    }
    public Rack(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RackEntity(pos,state);
    }

    //方块面向玩家 —— 开始
    //无上下面方块面向玩家，疑似与FIT有关
    public static final DirectionProperty FACING;
    public static final BooleanProperty LIT;

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING,LIT);
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        LIT = RedstoneTorchBlock.LIT;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING,ctx.getPlayerFacing().getOpposite());
    }

    //配合方块状态json文件代码
    //方块面向玩家 —— 结束


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof RackEntity) {
                ItemScatterer.spawn(world,pos, (Inventory) blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state,world,pos,newState,moved);
        }
    }


    private VoxelShape getShape (BlockState state) {
        Direction direction = state.get(FACING);
        if (direction == Direction.NORTH) {
            return NORTH_SHAPE;
        } else if (direction == Direction.SOUTH) {
            return SOUTH_SHAPE;
        } else if (direction == Direction.EAST) {
            return EAST_SHAPE;
        } else {
            return WEST_SHAPE;
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof RackEntity rackEntity) {
            if (!world.isClient) {
                NamedScreenHandlerFactory namedScreenHandlerFactory = state.createScreenHandlerFactory(world, pos);
                if (namedScreenHandlerFactory != null) {
                    player.openHandledScreen(namedScreenHandlerFactory);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockInit.RACK_ENTITY,RackEntity::tick);
    }
}
