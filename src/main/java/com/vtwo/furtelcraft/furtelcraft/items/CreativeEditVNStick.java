package com.vtwo.furtelcraft.furtelcraft.items;

import com.vtwo.furtelcraft.furtelcraft.init.FCNetPacks;
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
 * @NAME: CreativeEditVNStick
 * @USER: Perano
 * @DATE: 2023/1/12
 * @TIME: 17:38
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 12
 * @DAY_NAME_SHORT: 周四
 * @DAY_NAME_FULL: 星期四
 * @HOUR: 17
 * @MINUTE: 38
 * @PROJECT_NAME: furtelcraft
 */
public class CreativeEditVNStick extends Item {
    public CreativeEditVNStick(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user.isCreative()) {
            if (user.world.isClient) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeBoolean(true);
                buf.writeInt(entity.getId());
                buf.writeUuid(entity.getUuid());
                ClientPlayNetworking.send(FCNetPacks.CLIENT_OPEN_EDIT_SCREEN_ID, buf);
            }
        } else {
            return ActionResult.PASS;
        }
        return ActionResult.success(true);
    }
}
