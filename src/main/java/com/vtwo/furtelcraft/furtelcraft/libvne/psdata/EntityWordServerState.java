package com.vtwo.furtelcraft.furtelcraft.libvne.psdata;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.psdata
 * @NAME: EntityWordServerState
 * @USER: Perano
 * @DATE: 2023/1/15
 * @TIME: 17:11
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 15
 * @DAY_NAME_SHORT: 周日
 * @DAY_NAME_FULL: 星期日
 * @HOUR: 17
 * @MINUTE: 11
 * @PROJECT_NAME: furtelcraft
 */
public class EntityWordServerState extends PersistentState {
    public static HashMap<UUID, NbtList> word = new HashMap<>();
    private static NbtList wordList = new NbtList();

    public EntityWordServerState() {
        super();
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound entityNbt = new NbtCompound();
        word.forEach((uuid, nbtList) -> {
            NbtCompound nbtCompound = new NbtCompound();
            nbtCompound.put("Words", wordList);
            entityNbt.put(String.valueOf(uuid), nbtList);
        });
        nbt.put("entityWord", entityNbt);
        return nbt;
    }

    public static EntityWordServerState fromNbt(NbtCompound tag) {
        EntityWordServerState serverState = new EntityWordServerState();
        NbtCompound entityTag = tag.getCompound("entityWord");
        entityTag.getKeys().forEach(s -> {
            UUID uuid = UUID.fromString(s);
            NbtList nbtList = entityTag.getList("Words", NbtElement.LIST_TYPE);
            word.put(uuid, nbtList);
        });
        return serverState;
    }

    private static EntityWordServerState getEntityWordOverworldState(MinecraftServer server) {
        PersistentStateManager persistentStateManager = Objects.requireNonNull(server.getWorld(World.OVERWORLD)).getPersistentStateManager();
        EntityWordServerState serverState = persistentStateManager.getOrCreate(
                EntityWordServerState::fromNbt,//read fun
                EntityWordServerState::new,//supplier
                MOD_ID + "_entity_script_word"
        );
        serverState.markDirty();
        return serverState;
    }

    public static NbtList getEntityState(LivingEntity entity) {
        getEntityWordOverworldState(Objects.requireNonNull(entity.getServer()));
        return word.computeIfAbsent(entity.getUuid(), uuid -> wordList);
    }

    public static EntityWordServerState setEntityState(LivingEntity entity, NbtList nbtList) {
        EntityWordServerState serverState = getEntityWordOverworldState(Objects.requireNonNull(entity.getServer()));
        wordList = nbtList;
        word.put(entity.getUuid(), nbtList);
        return serverState;
    }
}
