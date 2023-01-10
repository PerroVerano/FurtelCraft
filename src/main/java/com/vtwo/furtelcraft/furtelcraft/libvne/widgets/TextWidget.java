package com.vtwo.furtelcraft.furtelcraft.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.libvne.VNScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.widgets
 * @NAME: TextWidget
 * @USER: Perano
 * @DATE: 2023/1/10
 * @TIME: 19:19
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 10
 * @DAY_NAME_SHORT: 周二
 * @DAY_NAME_FULL: 星期二
 * @HOUR: 19
 * @MINUTE: 19
 * @PROJECT_NAME: furtelcraft
 */
public class TextWidget extends BasedNonButtonWidget {

    protected int textureWidth;
    protected int textureHeight;
    protected int TextPanelWidth;
    protected int TextPanelHeight;
    protected int iTextPanel;
    protected int jTextPanel;
    protected Color textColor;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private Text message;

    public TextWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Text message, @Nullable Color textColor, @Nullable BasedNonButtonWidget.PressAction onPress, @Nullable BasedNonButtonWidget.TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, textureWidth, textureHeight, message, textColor, onPress, tooltipSupplier);
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.TextPanelWidth = width;
        this.TextPanelHeight = height;
        this.iTextPanel = x;
        this.jTextPanel = y;
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
        drawTexture(matrices, iTextPanel, jTextPanel, 7, 7, TextPanelWidth, TextPanelHeight, textureWidth, textureHeight);
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        List<OrderedText> cache = textRenderer.wrapLines(this.getMessage(), 236);
        for (int i = 0; i < Math.min(128 / 9, cache.size()); i++) {
            textRenderer.drawWithShadow(matrices, cache.get(i), iTextPanel, jTextPanel + (i * 10), j);
        }
    }

    @Override
    public Text getMessage() {
        return message;
    }
}
