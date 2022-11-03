package com.vtwo.furtelcraft.furtelcraft.blocks;

import com.vtwo.furtelcraft.furtelcraft.blockentities.CentrifugeEntity;
import com.vtwo.furtelcraft.furtelcraft.blockentities.MagneticParticleProcessorEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Centrifuge extends BlockWithEntity implements BlockEntityProvider{
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


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CentrifugeEntity(pos,state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory namedScreenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (namedScreenHandlerFactory != null) {
                player.openHandledScreen(namedScreenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    // 这个方法能让方块破坏时物品全部掉落

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MagneticParticleProcessorEntity) {
                ItemScatterer.spawn(world,pos, (Inventory) blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state,world,pos,newState,moved);
        }
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }
}
