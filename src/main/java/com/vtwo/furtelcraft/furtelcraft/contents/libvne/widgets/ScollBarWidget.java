package com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.contents.libvne.VNScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.widgets
 * @NAME: ScollBarWidget
 * @USER: Perano
 * @DATE: 2023/1/10
 * @TIME: 17:49
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 10
 * @DAY_NAME_SHORT: 周二
 * @DAY_NAME_FULL: 星期二
 * @HOUR: 17
 * @MINUTE: 49
 * @PROJECT_NAME: furtelcraft
 */
public class ScollBarWidget extends BasedWidget {
    protected int textureWidth;
    protected int textureHeight;
    protected int ScollBarWidth;
    protected int ScollBarHeight;
    protected int iScollBar;
    protected int jScollBar;
    protected int ScollBlockWidth;
    protected int ScollBlockHeight;
    protected int iScollBlock;
    protected int jScollBlock;
    protected Color textColor;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private float rate;
    protected boolean isScollBlock;
    private Text message;

    public ScollBarWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Text message, @Nullable Color textColor, @Nullable PressAction onPress, @Nullable TooltipSupplier tooltipSupplier, boolean isScollBlock) {
        super(x, y, width, height, textureWidth, textureHeight, message, textColor, onPress, tooltipSupplier);
        this.isScollBlock = isScollBlock;
        if (!isScollBlock) {
            this.onPress = onPress;
            this.tooltipSupplier = tooltipSupplier;
            this.ScollBarWidth = width;
            this.ScollBarHeight = height;
            this.iScollBar = x;
            this.jScollBar = y;
            this.message = message;
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
            this.textColor = textColor;
        } else {
            this.onPress = onPress;
            this.tooltipSupplier = tooltipSupplier;
            this.ScollBlockWidth = width;
            this.ScollBlockHeight = height;
            this.iScollBlock = x;
            this.jScollBlock = y;
            this.message = message;
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
            this.textColor = textColor;
        }
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, VNScreen.TEXTURE);
        drawTexture(matrices, iScollBar, jScollBar, 252, 0, ScollBarWidth, ScollBarHeight, textureWidth, textureHeight);
        drawTexture(matrices, iScollBlock, jScollBlock, 252, 54, ScollBlockWidth, ScollBlockHeight, textureWidth, textureHeight);
    }
}
