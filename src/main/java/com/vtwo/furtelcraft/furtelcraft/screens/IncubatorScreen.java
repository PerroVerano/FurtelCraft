package com.vtwo.furtelcraft.furtelcraft.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.text.Text;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.screens
 * @NAME: IncubatorScreen
 * @USER: Perano
 * @DATE: 2023/3/8
 * @TIME: 21:50
 * @YEAR: 2023
 * @MONTH: 03
 * @MONTH_NAME_SHORT: 3月
 * @MONTH_NAME_FULL: 三月
 * @DAY: 08
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 21
 * @MINUTE: 50
 * @PROJECT_NAME: furtelcraft
 */
public class IncubatorScreen extends Screen {

    private static final LivingEntity entity;

    static {
        assert MinecraftClient.getInstance().world != null;
        entity = new ZombieEntity(EntityType.ZOMBIE, MinecraftClient.getInstance().world);
    }

    public IncubatorScreen(Text title) {
        super(title);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        int iBase = width / 2;
        int jBase = height / 2;
        super.render(matrices, mouseX, mouseY, delta);
    }
}
