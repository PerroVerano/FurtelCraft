package com.vtwo.furtelcraft.furtelcraft.libvne.file;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.util.WorldSavePath;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.file
 * @NAME: EntityWordFiles
 * @USER: Perano
 * @DATE: 2023/1/16
 * @TIME: 20:56
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 16
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 20
 * @MINUTE: 56
 * @PROJECT_NAME: furtelcraft
 */
public class EntityWordFiles {
    private UUID uuid;
    private NbtCompound nbt;
    private File file;
    private long seed;

    public EntityWordFiles(LivingEntity entity) throws IOException {
        this.seed = Objects.requireNonNull(entity.getServer()).getOverworld().getSeed();
        this.file = new File(Objects.requireNonNull(entity.getServer()).getSavePath(WorldSavePath.ROOT) + "\\furtelcraft_plot_" + seed);
    }

    public void setWords(LivingEntity entity, NbtCompound nbtCompound) throws IOException {
        this.uuid = entity.getUuid();
        this.nbt = nbtCompound;
        this.seed = Objects.requireNonNull(entity.getServer()).getOverworld().getSeed();
        this.file = new File(Objects.requireNonNull(entity.getServer()).getSavePath(WorldSavePath.ROOT) + "\\furtelcraft_plot_" + seed);
        this.mark();
    }

    private void mark() throws IOException {
        NbtCompound compound;
        try {
            compound = NbtIo.readCompressed(file);
        } catch (IOException e) {
            compound = new NbtCompound();
        }
        NbtCompound nbtCompound = compound.copy();
        nbtCompound.put(String.valueOf(uuid), nbt);
        NbtIo.writeCompressed(nbtCompound, file);
    }

    public NbtCompound getWords(LivingEntity entity) throws IOException {
        return (NbtCompound) NbtIo.readCompressed(file).get(String.valueOf(entity.getUuid()));
    }

    public void removeWords(LivingEntity entity) throws IOException {
        NbtIo.readCompressed(file).remove(String.valueOf(entity.getUuid()));
    }
}
