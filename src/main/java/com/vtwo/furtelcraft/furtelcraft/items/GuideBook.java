package com.vtwo.furtelcraft.furtelcraft.items;

import com.vtwo.furtelcraft.furtelcraft.init.FCNetPacks;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GuideBook extends Item {
    public GuideBook(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeBoolean(true);
            ClientPlayNetworking.send(FCNetPacks.CLIENT_OPEN_GUIDE_BOOK_SCREEN_ID, buf);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
