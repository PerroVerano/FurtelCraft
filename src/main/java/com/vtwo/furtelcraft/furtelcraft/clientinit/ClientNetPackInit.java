package com.vtwo.furtelcraft.furtelcraft.clientinit;

import com.vtwo.furtelcraft.furtelcraft.init.NetPackInit;
import com.vtwo.furtelcraft.furtelcraft.libvne.EditScreen;
import com.vtwo.furtelcraft.furtelcraft.libvne.VNScreen;
import com.vtwo.furtelcraft.furtelcraft.screens.intedgui.BookGUI;
import com.vtwo.furtelcraft.furtelcraft.screens.intedscreen.BookScreen;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;

import java.util.Objects;
import java.util.UUID;

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
public class ClientNetPackInit {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(NetPackInit.CLIENT_OPEN_EDIT_SCREEN_ID, (client, handler, buf, responseSender) -> {
            boolean isOpen = buf.readBoolean();
            int id = buf.readInt();
            client.executeSync(() -> {
                if (isOpen) {
                    assert client.world != null;
                    EditScreen screen = new EditScreen(
                            Text.translatable("title.furtelcraft.vn_edit_screen",
                                    Objects.requireNonNull(client.world.getEntityById(id)).getName()));
                    screen.setEntity((LivingEntity) client.world.getEntityById(id));
                    client.setScreen(screen);
                }
            });
            //buf.release();
        });

        ClientPlayNetworking.registerGlobalReceiver(NetPackInit.CLIENT_OPEN_GUIDE_BOOK_SCREEN_ID, (client, handler, buf, responseSender) -> {
            boolean isOpen = buf.readBoolean();
            client.execute(() -> {
                if (isOpen) {
                    client.setScreen(new BookScreen(new BookGUI()));
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(NetPackInit.CLIENT_OPEN_VN_SCREEN_ID, (client, handler, buf, responseSender) -> {
            boolean isOpen = buf.readBoolean();
            UUID uuid = buf.readUuid();
            client.execute(() -> {
                if (isOpen) {
                    VNScreen screen = new VNScreen(Text.empty());
                    client.setScreen(screen);
                }
            });
        });
    }
}
