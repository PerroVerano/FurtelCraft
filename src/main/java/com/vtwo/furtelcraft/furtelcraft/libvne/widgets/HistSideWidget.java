package com.vtwo.furtelcraft.furtelcraft.libvne.widgets;

import com.google.common.collect.Lists;
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
 * @NAME: HistSideWidget
 * @USER: Perano
 * @DATE: 2023/1/11
 * @TIME: 11:55
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 11
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 11
 * @MINUTE: 55
 * @PROJECT_NAME: furtelcraft
 */
public class HistSideWidget extends BasedNonButtonWidget {
    protected int textureWidth;
    protected int textureHeight;
    protected int HistWidth;
    protected int HistHeight;
    protected int iHistPanel;
    protected int jHistPanel;
    protected Color textColor;
    protected final PressAction onPress;
    protected TooltipSupplier tooltipSupplier;
    private int posBlock = 0;
    protected static final Identifier HIST_TEXTURE = new Identifier(MOD_ID, "textures/screen/vne_hist.png");

    private List<OrderedText> TheList = Lists.newArrayList();

    public HistSideWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Text message, @Nullable Color textColor, @Nullable BasedNonButtonWidget.PressAction onPress, @Nullable BasedNonButtonWidget.TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, textureWidth, textureHeight, message, textColor, onPress, tooltipSupplier);
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.HistWidth = width;
        this.HistHeight = height;
        this.iHistPanel = x;
        this.jHistPanel = y;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.textColor = textColor;
        this.TheList.add(this.getMessage().asOrderedText());
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, HIST_TEXTURE);
        drawTexture(matrices, iHistPanel, jHistPanel, 0, 0, HistWidth, HistHeight, textureWidth, textureHeight);
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        TheList = textRenderer.wrapLines(this.getMessage(), 118);
        if (TheList.size() > 10) {
            addScollBar(matrices);
        }
        int m = (int) ((posBlock / 116.0F) * TheList.size());//dang qian hang shu
        int a = MathHelper.ceil(TheList.size() / 12.0F);//zong ye shu
        int p = (int) (m / 12.0F);//dang qian ye shu
        if (!TheList.isEmpty()) {
            for (int i = 0; i < 12; i++) {
                int n;
                if (p > 0) {
                    if (p < a) {
                        int k = -1;
                        for (n = p * 12; n < (p * 12) + 12; n++) {
                            if (n < TheList.size()) {
                                k++;
                                textRenderer.drawWithShadow(matrices, TheList.get(n), iHistPanel + 7, 7 + jHistPanel + (k * 10), j);
                            } else {
                                break;
                            }

                        }
                    }
                    continue;
                } else {
                    n = i;
                }
                textRenderer.drawWithShadow(matrices, TheList.get(n), iHistPanel + 7, 7 + jHistPanel + (i * 10), j);
            }
        }
    }

    private void addScollBar(MatrixStack matrices) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, HIST_TEXTURE);
        drawTexture(matrices, iHistPanel + 125, jHistPanel + 4, 134, 0, 5, 128, textureWidth, textureHeight);
        drawTexture(matrices, iHistPanel + 125, jHistPanel + 4 + posBlock, 134, 128, 5, 12, textureWidth, textureHeight);

    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (posBlock >= 0 && posBlock <= 116) {
            if (hasShiftDown()) {
                amount *= 10;
            }
            posBlock -= amount;
        }
        if (posBlock <= -1) {
            posBlock = 0;
        } else if (posBlock >= 117) {
            posBlock = 116;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        int regionMinX = 126;
        int regionMaxX = 130;
        int regionMinY = 5 + posBlock;
        int regionMaxY = 16 + posBlock;
        if ((mouseX - iHistPanel) >= regionMinX && (mouseX - iHistPanel) <= regionMaxX) {
            if ((mouseY - jHistPanel) >= regionMinY && (mouseY - jHistPanel) <= regionMaxY) {
                posBlock = (int) (mouseY - jHistPanel - 9);
            }
        } else if (posBlock >= 0 && posBlock <= 116) {
            posBlock -= deltaY;
        }
        if (posBlock <= -1) {
            posBlock = 0;
        } else if (posBlock >= 117) {
            posBlock = 116;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    private boolean hasShiftDown() {
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 340) || InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 344);
    }
}
