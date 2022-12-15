package com.vtwo.furtelcraft.furtelcraft.blockentities;

import com.vtwo.furtelcraft.furtelcraft.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class TubeHolderEntity extends BlockEntity {

    public TubeHolderEntity(BlockPos pos, BlockState state) {
        super(BlockInit.TUBE_HOLDER_ENTITY, pos, state);
    }
}
