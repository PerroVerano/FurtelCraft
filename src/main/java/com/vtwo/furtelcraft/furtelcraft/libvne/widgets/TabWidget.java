package com.vtwo.furtelcraft.furtelcraft.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.libvne.EditScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.widgets
 * @NAME: TabWidget
 * @USER: Perano
 * @DATE: 2023/1/19
 * @TIME: 18:11
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 19
 * @DAY_NAME_SHORT: 周四
 * @DAY_NAME_FULL: 星期四
 * @HOUR: 18
 * @MINUTE: 11
 * @PROJECT_NAME: furtelcraft
 */
public class TabWidget extends BasedNonButtonWidget {
    private int iTab;
    private int jTab;
    private @Deprecated int TabWidth;
    private @Deprecated int TabHeight;
    private Text message;
    private Color textColor;
    private PressAction onPress;
    private TooltipSupplier tooltipSupplier;
    private boolean isEnabled;

    public TabWidget(int x, int y, @Deprecated int width, @Deprecated int height, @Nullable Text message, @Nullable Color textColor, @Nullable BasedNonButtonWidget.PressAction onPress, @Nullable BasedNonButtonWidget.TooltipSupplier tooltipSupplier, boolean isEnabled) {
        super(x, y, width, height, message, textColor, onPress, tooltipSupplier);
        this.iTab = x;
        this.jTab = y;
        this.TabWidth = width;
        this.TabHeight = height;
        this.message = message;
        this.textColor = textColor;
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.isEnabled = isEnabled;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EditScreen.TEXTURE);
        if (isEnabled) {
            drawTexture(matrices, iTab, jTab, 0, 185, 32, 19);
        } else {
            drawTexture(matrices, iTab, jTab, 0, 168, 32, 17);
        }
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        drawCenteredText(matrices, textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
        if (this.isHovered()) {
            this.renderTooltip(matrices, mouseX, mouseY);
        }
    }
}
