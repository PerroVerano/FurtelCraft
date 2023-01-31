package com.vtwo.furtelcraft.furtelcraft.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.libvne.VNScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.widgets
 * @NAME: OptionSelectWidget
 * @USER: Perano
 * @DATE: 2023/1/10
 * @TIME: 19:30
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 10
 * @DAY_NAME_SHORT: 周二
 * @DAY_NAME_FULL: 星期二
 * @HOUR: 19
 * @MINUTE: 30
 * @PROJECT_NAME: furtelcraft
 */
public class OptionSelectWidget extends BasedWidget {
    protected int textureWidth;
    protected int textureHeight;
    protected int osWidth;
    protected int osHeight;
    protected int iOsPanel;
    protected int jOsPanel;
    protected Color textColor;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private Text message;

    public OptionSelectWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Text message, @Nullable Color textColor, @Nullable BasedWidget.PressAction onPress, @Nullable BasedWidget.TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, textureWidth, textureHeight, message, textColor, onPress, tooltipSupplier);
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.osWidth = width;
        this.osHeight = height;
        this.iOsPanel = x;
        this.jOsPanel = y;
        this.message = message;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.textColor = textColor;
    }

    @Override
    public void setMessage(Text message) {
        this.message = message;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, VNScreen.TEXTURE);
        drawTexture(matrices, iOsPanel, jOsPanel, 0, 64, osWidth, osHeight, textureWidth, textureHeight);
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        drawCenteredText(matrices, textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
