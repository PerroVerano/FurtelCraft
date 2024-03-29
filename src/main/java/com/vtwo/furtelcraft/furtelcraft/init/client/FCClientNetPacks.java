package com.vtwo.furtelcraft.furtelcraft.init.client;

import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.GuideBookScreen;
import com.vtwo.furtelcraft.furtelcraft.contents.libvne.EditScreen;
import com.vtwo.furtelcraft.furtelcraft.contents.libvne.VNScreen;
import com.vtwo.furtelcraft.furtelcraft.init.FCNetPacks;
import com.vtwo.furtelcraft.furtelcraft.screens.IncubatorScreen;
import com.vtwo.furtelcraft.furtelcraft.screens.hud.TubeHolderHud;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import java.util.HashMap;
import java.util.Objects;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.clientinit
 * @NAME: ClientNetPackInit
 * @USER: Perano
 * @DATE: 2023/1/13
 * @TIME: 13:43
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 13
 * @DAY_NAME_SHORT: 周五
 * @DAY_NAME_FULL: 星期五
 * @HOUR: 13
 * @MINUTE: 43
 * @PROJECT_NAME: furtelcraft
 */
public class FCClientNetPacks {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(FCNetPacks.CLIENT_OPEN_EDIT_SCREEN_ID, (client, handler, buf, responseSender) -> {
            boolean isOpen = buf.readBoolean();
            int id = buf.readInt();
            NbtCompound compound = buf.readNbt();
            client.execute(() -> {
                if (isOpen) {
                    assert client.world != null;
                    EditScreen screen = new EditScreen(
                            new TranslatableText("title.furtelcraft.vn_edit_screen",
                                    Objects.requireNonNull(client.world.getEntityById(id)).getName()));
                    screen.setEntity((LivingEntity) client.world.getEntityById(id));
                    screen.setEntityNbt(compound);
                    client.setScreen(screen);
                }
            });
            //buf.release();
        });

        ClientPlayNetworking.registerGlobalReceiver(FCNetPacks.CLIENT_OPEN_GUIDE_BOOK_SCREEN_ID, (client, handler, buf, responseSender) -> {
            boolean isOpen = buf.readBoolean();
            client.execute(() -> {
                if (isOpen) {
                    client.setScreen(new GuideBookScreen(LiteralText.EMPTY));
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(FCNetPacks.CLIENT_OPEN_VN_SCREEN_ID, (client, handler, buf, responseSender) -> {
            NbtCompound word = buf.readNbt();
            client.execute(() -> {
                VNScreen screen = new VNScreen(LiteralText.EMPTY);
                assert word != null;
                screen.setTheName(new LiteralText(word.getString("NAME")));
                screen.setTheTextList(word);
                client.setScreen(screen);
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(FCNetPacks.CLIENT_TUBE_HOLDER_HUD_RENDER_ID, (client, handler, buf, responseSender) -> {
            HashMap<Integer, ItemStack> items = new HashMap<>();
            for (int i = 0; i < 3; i++) {
                items.put(i, buf.readItemStack());
            }
            client.execute(() -> {
                TubeHolderHud hud = TubeHolderHud.getInstance();
                hud.setItems(items);
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(FCNetPacks.OPEN_INCUBATOR_SCREEN_ID, (client, handler, buf, responseSender) -> {
            client.execute(() -> {
                IncubatorScreen screen = new IncubatorScreen(LiteralText.EMPTY);
                client.setScreen(screen);
            });
        });
    }
}
