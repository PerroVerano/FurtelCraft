package com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.widgets
 * @NAME: EditScreenTextWidget
 * @USER: Perano
 * @DATE: 2023/1/12
 * @TIME: 22:32
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 12
 * @DAY_NAME_SHORT: 周四
 * @DAY_NAME_FULL: 星期四
 * @HOUR: 22
 * @MINUTE: 32
 * @PROJECT_NAME: furtelcraft
 */
public class EditScreenTextWidget extends BasedWidget {
    protected int textureHeight;
    protected int TextPanelWidth;
    protected int TextPanelHeight;
    protected int iTextPanel;
    protected int jTextPanel;
    private double progress = 0.0;
    protected Color textColor;
    private final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
    private Text message;
    private ScollBarWidget scollBarEle;
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/vne_edit.png");

    public EditScreenTextWidget(int x, int y, int width, int height, @Nullable Text message, @Nullable Color textColor) {
        super(x, y, width, height, message, textColor);
        this.iTextPanel = x;
        this.jTextPanel = y;
        this.TextPanelWidth = width;
        this.TextPanelHeight = height;
        this.message = message;
        this.textColor = textColor;
        this.scollBarEle = new ScollBarWidget(iTextPanel + 160, jTextPanel, TextPanelHeight);
        this.addChild(scollBarEle);
    }

    @Override
    public void setMessage(Text message) {
        this.message = message;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        List<OrderedText> cache = textRenderer.wrapLines(this.getMessage(), 158);
        this.scollBarEle.visible = cache.size() > 6;
        int m = (int) (this.scollBarEle.getProgress() * cache.size());
        int a = MathHelper.ceil(cache.size() / 6.0F);
        int p = (int) (m / 6.0F);
        if (!cache.isEmpty()) {
            for (int i = 0; i < Math.min(6, cache.size()); i++) {
                int n;
                if (p > 0) {
                    if (p < a) {
                        int k = -1;
                        for (n = p * 6; n < (p * 6) + 6; n++) {
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
            this.scollBarEle.updateScollHeight(6.0 / cache.size());
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

    public TextRenderer getTextRenderer() {
        return this.textRenderer;
    }

    public void resetPos() {
        this.scollBarEle.resetProgress();
    }
}
