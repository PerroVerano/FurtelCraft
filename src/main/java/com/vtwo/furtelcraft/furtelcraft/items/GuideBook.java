package com.vtwo.furtelcraft.furtelcraft.items;

import com.vtwo.furtelcraft.furtelcraft.screens.intedgui.BookGUI;
import com.vtwo.furtelcraft.furtelcraft.screens.intedscreen.BookScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
            MinecraftClient client = MinecraftClient.getInstance();
            client.setScreen(new BookScreen(new BookGUI()));
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
