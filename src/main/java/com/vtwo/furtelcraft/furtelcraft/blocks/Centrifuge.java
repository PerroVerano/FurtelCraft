package com.vtwo.furtelcraft.furtelcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import org.jetbrains.annotations.Nullable;

public class Centrifuge extends Block {
    public Centrifuge(Settings settings) {
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
}
