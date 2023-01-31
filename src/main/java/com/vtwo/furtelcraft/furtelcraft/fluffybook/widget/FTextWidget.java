package com.vtwo.furtelcraft.furtelcraft.fluffybook.widget;

import com.vtwo.furtelcraft.furtelcraft.libvne.widgets.BasedWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.awt.*;
import java.util.List;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook.widget
 * @NAME: FTextWidget
 * @USER: Perano
 * @DATE: 2023/1/30
 * @TIME: 12:51
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 30
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 12
 * @MINUTE: 51
 * @PROJECT_NAME: furtelcraft
 */
public class FTextWidget extends BasedWidget {
    private final int iText;
    private final int jText;
    private final int textWidth;
    private Text message;
    private final Color textColor;
    private final boolean isCentered;
    private final boolean hasShadow;
    protected final TooltipSupplier tooltipSupplier;

    public FTextWidget(int x, int y, int width, int height, Text message, Color textColor, boolean isCentered, boolean hasShadow, BasedWidget.TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, message, textColor, null, tooltipSupplier);
        this.iText = x;
        this.jText = y;
        this.textWidth = width;
        this.message = message;
        this.textColor = textColor;
        this.isCentered = isCentered;
        this.hasShadow = hasShadow;
        this.tooltipSupplier = tooltipSupplier;
        this.setZOffset(10);
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        List<OrderedText> cache = textRenderer.wrapLines(this.message, this.textWidth + 1);
        int k = -10;
        for (OrderedText orderedText : cache) {
            k += 10;
            if (this.hasShadow && this.isCentered) {
                drawCenteredTextWithShadow(matrices, textRenderer, orderedText, this.iText + this.textWidth / 2, this.jText + k, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
            }
            if (this.hasShadow && !this.isCentered) {
                textRenderer.drawWithShadow(matrices, orderedText, this.iText, this.jText + k, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
            }
            if (!this.hasShadow && this.isCentered) {
                textRenderer.draw(matrices, orderedText, (float) ((this.iText + this.textWidth / 2) - textRenderer.getWidth(orderedText) / 2), (float) (this.jText + k), j | MathHelper.ceil(this.alpha * 255.0F) << 24);
            }
            if (!this.hasShadow && !this.isCentered) {
                textRenderer.draw(matrices, orderedText, this.iText, this.jText + k, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
            }
        }
        if (this.isHovered()) {
            this.renderTooltip(matrices, mouseX, mouseY);
        }
    }

    @Override
    public void setMessage(Text message) {
        this.message = message;
    }
}
