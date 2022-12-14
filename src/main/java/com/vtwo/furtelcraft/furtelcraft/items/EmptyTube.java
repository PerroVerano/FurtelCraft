package com.vtwo.furtelcraft.furtelcraft.items;

import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class EmptyTube extends Item {
    public EmptyTube(Settings settings) {
        super(settings);
    }

    public static final Item block;

    static {
        block = null;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        BlockHitResult hitResult = raycast(world,user, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (hand == Hand.MAIN_HAND && hitResult.getType() == HitResult.Type.BLOCK) {
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = hitResult.getBlockPos();
                if (world.getFluidState(blockPos).isIn(FluidTags.WATER)){
                    if (!user.getAbilities().creativeMode) {
                        user.getMainHandStack().setCount(user.getMainHandStack().getCount() - 1);
                    }
                    user.swingHand(hand,true);
                    user.giveItemStack(ItemInit.WATER_TUBE.getDefaultStack());
                    user.playSound(SoundEvents.ITEM_BOTTLE_FILL,1.0F,1.0F);
                }
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
