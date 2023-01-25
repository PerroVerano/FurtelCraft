package com.vtwo.furtelcraft.furtelcraft.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.InputUtil;
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
public class EditScreenTextWidget extends BasedNonButtonWidget {
    protected int textureHeight;
    protected int TextPanelWidth;
    protected int TextPanelHeight;
    protected int iTextPanel;
    protected int jTextPanel;
    private int posBlock = 0;
    protected Color textColor;
    private final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
    private Text message;
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/vne_edit.png");

    public EditScreenTextWidget(int x, int y, int width, int height, @Nullable Text message, @Nullable Color textColor) {
        super(x, y, width, height, message, textColor);
        this.iTextPanel = x;
        this.jTextPanel = y;
        this.TextPanelWidth = width;
        this.TextPanelHeight = height;
        this.message = message;
        this.textColor = textColor;
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
        //drawTexture(matrices, iTextPanel, jTextPanel, 94, 82, TextPanelWidth, TextPanelHeight, textureWidth, textureHeight);
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        List<OrderedText> cache = textRenderer.wrapLines(this.getMessage(), 158);
        if (cache.size() > 6) {
            addScollBar(matrices);
        }
        int m = (int) ((posBlock / 52.0F) * cache.size());
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
        }
    }

    @Override
    public Text getMessage() {
        return message;
    }


    private void addScollBar(MatrixStack matrices) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, iTextPanel + 160, jTextPanel, 252, 168, 4, 64);
        drawTexture(matrices, iTextPanel + 160, jTextPanel + posBlock, 252, 232, 4, 12);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (posBlock >= 0 && posBlock <= 52) {
            if (hasShiftDown()) {
                amount *= 7;
            }
            posBlock -= amount;
        }
        if (posBlock <= -1) {
            posBlock = 0;
        } else if (posBlock >= 53) {
            posBlock = 52;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        int regionMinX = 160;
        int regionMaxX = 164;
        int regionMinY = posBlock;
        int regionMaxY = 12 + posBlock;
        if ((mouseX - iTextPanel) >= regionMinX && (mouseX - iTextPanel) <= regionMaxX) {
            if ((mouseY - jTextPanel) >= regionMinY && (mouseY - jTextPanel) <= regionMaxY) {
                posBlock = (int) (mouseY - jTextPanel - 7);
            }
        } else if (posBlock >= 0 && posBlock <= 52) {
            posBlock -= deltaY;
        }
        if (posBlock <= -1) {
            posBlock = 0;
        } else if (posBlock >= 53) {
            posBlock = 52;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    private boolean hasShiftDown() {
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 340) || InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 344);
    }

    public TextRenderer getTextRenderer() {
        return this.textRenderer;
    }

    public void resetPos() {
        this.posBlock = 0;
    }
}
