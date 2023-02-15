package com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.contents.libvne.VNScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;
import java.awt.*;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.widgets
 * @NAME: NameBarWidget
 * @USER: Perano
 * @DATE: 2023/1/10
 * @TIME: 15:41
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 10
 * @DAY_NAME_SHORT: 周二
 * @DAY_NAME_FULL: 星期二
 * @HOUR: 15
 * @MINUTE: 41
 * @PROJECT_NAME: furtelcraft
 */
public class NameBarWidget extends BasedWidget {
    protected int textureWidth;
    protected int textureHeight;
    protected int NameBarWidth;
    protected int NameBarHeight;
    protected int iNameBar;
    protected int jNameBar;
    protected Color textColor;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private Text message;

    public NameBarWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Text message, @Nullable Color textColor, @Nullable PressAction onPress, @Nullable TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, textureWidth, textureHeight, message, textColor, onPress, tooltipSupplier);
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.NameBarWidth = width;
        this.NameBarHeight = height;
        this.iNameBar = x;
        this.jNameBar = y;
        this.message = message;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.textColor = textColor;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, VNScreen.TEXTURE);
        drawTexture(matrices, iNameBar, jNameBar, 27, 75, NameBarWidth, NameBarHeight, textureWidth, textureHeight);
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        drawCenteredText(matrices, textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 6) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
    }
}
