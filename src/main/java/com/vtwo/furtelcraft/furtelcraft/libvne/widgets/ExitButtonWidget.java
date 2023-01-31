package com.vtwo.furtelcraft.furtelcraft.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.libvne.VNScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.vne.widgets
 * @NAME: SkipButtonWidget
 * @USER: Perano
 * @DATE: 2023/1/10
 * @TIME: 13:37
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 10
 * @DAY_NAME_SHORT: 周二
 * @DAY_NAME_FULL: 星期二
 * @HOUR: 13
 * @MINUTE: 37
 * @PROJECT_NAME: furtelcraft
 */
public class ExitButtonWidget extends BasedWidget {
    protected int textureWidth;
    protected int textureHeight;
    protected int iButtonWidth;
    protected int ButtonHeight;
    protected int iIButton;
    protected int jButton;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    protected boolean isEnabled;

    public ExitButtonWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Text message, @Nullable Color textColor, @Nullable BasedWidget.PressAction onPress, @Nullable BasedWidget.TooltipSupplier tooltipSupplier, boolean isEnabled) {
        super(x, y, width, height, textureWidth, textureHeight, message, textColor, onPress, tooltipSupplier);
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.iButtonWidth = width;
        this.ButtonHeight = height;
        this.iIButton = x;
        this.jButton = y;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.isEnabled = isEnabled;
    }


    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, VNScreen.TEXTURE);

        if (this.isEnabled) {
            drawTexture(matrices, iIButton, jButton, 154, 64, iButtonWidth, ButtonHeight, textureWidth, textureHeight);
        } else {
            drawTexture(matrices, iIButton, jButton, 154, 75, iButtonWidth, ButtonHeight, textureWidth, textureHeight);
        }


        if (this.isHovered()) {
            this.renderTooltip(matrices, mouseX, mouseY);
        }
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
