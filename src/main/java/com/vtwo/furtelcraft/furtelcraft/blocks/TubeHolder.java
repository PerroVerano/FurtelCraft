package com.vtwo.furtelcraft.furtelcraft.blocks;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class TubeHolder extends Block {
    public static final VoxelShape NORTH_SHAPE;
    public static final VoxelShape EAST_SHAPE;
    static {
        NORTH_SHAPE = Block.createCuboidShape(1.0,0.0,5.0,15.0,9.0,11.0);
        EAST_SHAPE = Block.createCuboidShape(5.0,0.0,1.0,11.0,9.0,15.0);
    }
    public TubeHolder(Settings settings) {
        super(settings);
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

    private VoxelShape getShape (BlockState state) {
        Direction direction = state.get(FACING);
        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            return NORTH_SHAPE;
        }
        else {
            return EAST_SHAPE;
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }
}
