package com.vtwo.furtelcraft.furtelcraft.blocks.entity;

import com.vtwo.furtelcraft.furtelcraft.init.FCBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.blocks.entity
 * @NAME: IncubatorEntity
 * @USER: Perano
 * @DATE: 2023/3/8
 * @TIME: 21:14
 * @YEAR: 2023
 * @MONTH: 03
 * @MONTH_NAME_SHORT: 3月
 * @MONTH_NAME_FULL: 三月
 * @DAY: 08
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 21
 * @MINUTE: 14
 * @PROJECT_NAME: furtelcraft
 */
public class IncubatorEntity extends BlockEntity {
    public IncubatorEntity(BlockPos pos, BlockState state) {
        super(FCBlocks.INCUBATOR_ENTITY, pos, state);
    }

    public void use(BlockState state, World world, BlockPos pos, PlayerEntity player) {
    }
}
