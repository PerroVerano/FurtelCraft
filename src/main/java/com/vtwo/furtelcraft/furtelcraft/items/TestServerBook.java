package com.vtwo.furtelcraft.furtelcraft.items;

import com.vtwo.furtelcraft.furtelcraft.init.NetPackInit;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

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

    /*@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeBoolean(true);
            ClientPlayNetworking.send(NetPackInit.CLIENT_OPEN_VN_SCREEN_ID, buf);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }*/

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user.world.isClient) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeBoolean(true);
            buf.writeUuid(entity.getUuid());
            ClientPlayNetworking.send(NetPackInit.CLIENT_OPEN_VN_SCREEN_ID, buf);
        }
        return ActionResult.success(true);
    }
}
