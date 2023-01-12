package com.vtwo.furtelcraft.furtelcraft.items;

import com.vtwo.furtelcraft.furtelcraft.screens.vne.MainScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.items
 * @NAME: TestServerBook
 * @USER: Perano
 * @DATE: 2023/1/9
 * @TIME: 22:21
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 09
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 22
 * @MINUTE: 21
 * @PROJECT_NAME: furtelcraft
 */
public class TestServerBook extends Item {
    public TestServerBook(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            MinecraftClient client = MinecraftClient.getInstance();
            client.setScreen(new MainScreen(Text.literal("")));
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
