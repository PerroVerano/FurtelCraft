package com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets;

import com.vtwo.furtelcraft.furtelcraft.contents.libvne.VNScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
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
public class TextWidget extends BasedWidget {

    protected int textureWidth;
    protected int textureHeight;
    protected int TextPanelWidth;
    protected int TextPanelHeight;
    protected int iTextPanel;
    protected int jTextPanel;
    protected Color textColor;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private double progress = 0.0;
    private ScollBarWidget scollBarEle;
    private Text message;

    public TextWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Text message, @Nullable Color textColor, @Nullable BasedWidget.PressAction onPress, @Nullable BasedWidget.TooltipSupplier tooltipSupplier) {
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
        this.scollBarEle = new ScollBarWidget(iTextPanel + 234, jTextPanel - 2, TextPanelHeight);
        this.addChild(scollBarEle);
    }

    @Override
    public void setMessage(Text message) {
        this.message = message;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;
        this.setRenderSystem(VNScreen.TEXTURE);
        drawTexture(matrices, iTextPanel, jTextPanel, 7, 7, TextPanelWidth - 9, TextPanelHeight, textureWidth, textureHeight);
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        List<OrderedText> cache = textRenderer.wrapLines(this.getMessage(), 236);
        this.scollBarEle.visible = cache.size() > 5;
        int m = (int) (this.scollBarEle.getProgress() * cache.size());
        int a = MathHelper.ceil(cache.size() / 5.0F);
        int p = (int) (m / 5.0F);
        if (!cache.isEmpty()) {
            for (int i = 0; i < Math.min(5, cache.size()); i++) {
                int n;
                if (p > 0) {
                    if (p < a) {
                        int k = -1;
                        for (n = p * 5; n < (p * 5) + 5; n++) {
                            if (n < cache.size()) {
                                k++;
                                textRenderer.drawWithShadow(matrices, cache.get(n), iTextPanel + 1, 1 + jTextPanel + (k * 10), j);
                            } else {
                                break;
                            }

                        }
                    }
                    continue;
                } else {
                    n = i;
                }
                textRenderer.drawWithShadow(matrices, cache.get(n), iTextPanel + 1, 1 + jTextPanel + (i * 10), j);
            }
            this.scollBarEle.updateScollHeight(5.0 / cache.size());
        }
    }

    @Override
    public Text getMessage() {
        return message;
    }


    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (progress >= 0.0 && progress <= 50.0) {
            this.scollBarEle.setProgress((progress -= amount) / 50.0);
        }
        if (progress < 0.0) {
            progress = 0.0;
        } else if (progress > 50.0) {
            progress = 50.0;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (progress >= 0.0 && progress <= 50.0) {
            this.scollBarEle.setProgress((progress -= deltaY) / 50.0);
        }
        if (progress < 0.0) {
            progress = 0.0;
        } else if (progress > 50.0) {
            progress = 50.0;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}
