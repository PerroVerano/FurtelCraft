package com.vtwo.furtelcraft.furtelcraft.items;

import com.vtwo.furtelcraft.furtelcraft.init.FCItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class WaterTube extends Item {
    public WaterTube(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack){
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatSound(){
        return SoundEvents.ENTITY_WANDERING_TRADER_DRINK_POTION;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user){
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity) user : null;
        ItemStack last = new ItemStack(FCItems.EMPTY_TUBE);
        if (playerEntity instanceof ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
        }

        if (!world.isClient) {
            List<StatusEffectInstance> list = PotionUtil.getPotionEffects(stack);

            for (StatusEffectInstance statusEffectInstance : list) {
                if (statusEffectInstance.getEffectType().isInstant()) {
                    statusEffectInstance.getEffectType().applyInstantEffect(playerEntity, playerEntity, user, statusEffectInstance.getAmplifier(), 1.0);
                } else {
                    user.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
                }
            }
        }

        if (playerEntity != null) {
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!playerEntity.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }

        if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(FCItems.EMPTY_TUBE);
            }

            if (playerEntity != null) {
                if (!playerEntity.getInventory().insertStack(last)){
                    playerEntity.dropItem(FCItems.EMPTY_TUBE);
                }
                else {
                    playerEntity.getInventory().insertStack(last);
                }

            }
        }

        world.emitGameEvent(user, GameEvent.DRINKING_FINISH, user.getCameraBlockPos());
        return stack;
    }
}
