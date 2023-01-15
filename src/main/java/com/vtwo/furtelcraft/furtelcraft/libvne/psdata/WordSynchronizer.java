package com.vtwo.furtelcraft.furtelcraft.libvne.psdata;

import net.minecraft.world.PersistentState;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.psdata
 * @NAME: WordSynchronizer
 * @USER: Perano
 * @DATE: 2023/1/15
 * @TIME: 20:54
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 15
 * @DAY_NAME_SHORT: 周日
 * @DAY_NAME_FULL: 星期日
 * @HOUR: 20
 * @MINUTE: 54
 * @PROJECT_NAME: furtelcraft
 */
public class WordSynchronizer implements Runnable {
    private final PersistentState compound;

    public WordSynchronizer(PersistentState compound) {
        this.compound = compound;
    }

    @Override
    public void run() {
        this.compound.markDirty();
    }
}
