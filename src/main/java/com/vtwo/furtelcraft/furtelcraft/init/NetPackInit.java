package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.libvne.psdata.EntityWordServerState;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Objects;
import java.util.UUID;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.init
 * @NAME: NetPackInit
 * @USER: Perano
 * @DATE: 2023/1/9
 * @TIME: 19:46
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 09
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 19
 * @MINUTE: 46
 * @PROJECT_NAME: furtelcraft
 */
public class NetPackInit {
    public static final Identifier EDIT_SCREEN_ENTITY_NAME_ID = new Identifier(MOD_ID, "edit_screen_entity_name_id");
    public static final Identifier CLIENT_OPEN_EDIT_SCREEN_ID = new Identifier(MOD_ID, "client_open_edit_screen_id");
    public static final Identifier CLIENT_OPEN_GUIDE_BOOK_SCREEN_ID = new Identifier(MOD_ID, "client_open_guide_book_id");
    public static final Identifier CLIENT_OPEN_VN_SCREEN_ID = new Identifier(MOD_ID, "client_open_vn_screen_id");
    public static final Identifier EDIT_SCREEN_SAVE_ENTITY_WORD_ID = new Identifier(MOD_ID, "edit_screen_save_entity_word_id");
    public static final Identifier DATA_VN_ENTITY_WORD_SCRIPT_ID = new Identifier(MOD_ID, "data_vn_entity_word_script_id");

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(EDIT_SCREEN_ENTITY_NAME_ID, (server, player, handler, buf, responseSender) -> {
            String name = buf.readString();
            int id = buf.readInt();
            server.execute(() -> {
                Objects.requireNonNull(player.world.getEntityById(id)).setCustomName(Text.literal(name));
                Objects.requireNonNull(player.world.getEntityById(id)).addScoreboardTag("furtelcraft:isEditedVN");
                if (!(player.world.getEntityById(id) instanceof PlayerEntity)) {
                    if (!player.world.isClient && Objects.requireNonNull(player.world.getEntityById(id)).isAlive()) {
                        if (player.world.getEntityById(id) instanceof MobEntity) {
                            ((MobEntity) Objects.requireNonNull(player.world.getEntityById(id))).setPersistent();
                        }
                    }
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(CLIENT_OPEN_EDIT_SCREEN_ID, (server, player, handler, buf, responseSender) -> {
            buf.retain();
            server.execute(() -> {
                ServerPlayNetworking.send(player, CLIENT_OPEN_EDIT_SCREEN_ID, buf);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(CLIENT_OPEN_GUIDE_BOOK_SCREEN_ID, (server, player, handler, buf, responseSender) -> {
            buf.retain();
            server.execute(() -> {
                ServerPlayNetworking.send(player, CLIENT_OPEN_GUIDE_BOOK_SCREEN_ID, buf);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(CLIENT_OPEN_VN_SCREEN_ID, (server, player, handler, buf, responseSender) -> {
            buf.retain();
            server.execute(() -> {
                ServerPlayNetworking.send(player, CLIENT_OPEN_VN_SCREEN_ID, buf);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(EDIT_SCREEN_SAVE_ENTITY_WORD_ID, (server, player, handler, buf, responseSender) -> {
            UUID uuid = buf.readUuid();
            NbtList list = (NbtList) Objects.requireNonNull(buf.readNbt()).get("Word");
            server.execute(() -> {
                EntityWordServerState.setEntityState((LivingEntity) Objects.requireNonNull(server.getOverworld().getEntity(uuid)), list);
            });
        });
    }
}
