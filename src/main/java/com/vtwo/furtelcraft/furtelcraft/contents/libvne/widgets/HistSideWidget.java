package com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
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
public class HistSideWidget extends BasedWidget {
    protected int textureWidth;
    protected int textureHeight;
    protected int HistWidth;
    protected int HistHeight;
    protected int iHistPanel;
    protected int jHistPanel;
    protected Color textColor;
    private double progress = 0.0;
    private final ScollBarWidget scollBarEle;
    protected static final Identifier HIST_TEXTURE = new Identifier(MOD_ID, "textures/screen/vne_hist.png");

    private List<OrderedText> TheList = Lists.newArrayList();

    public HistSideWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Text message, @Nullable Color textColor) {
        super(x, y, width, height, textureWidth, textureHeight, message, textColor, widget -> System.out.println("dasda"), (widget, matrices, mouseX, mouseY) -> {
        });
        this.HistWidth = width;
        this.HistHeight = height;
        this.iHistPanel = x;
        this.jHistPanel = y;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.textColor = textColor;
        this.TheList.add(this.getMessage().asOrderedText());
        this.scollBarEle = new ScollBarWidget(iHistPanel + 124, jHistPanel + 4, HistHeight - 8, HistHeight - 8, widget -> System.out.println("test"), (widget, matrices, mouseX, mouseY) -> this.renderTooltip(matrices, new LiteralText("dsa"), mouseX, mouseY));
        this.addChild(this.scollBarEle);
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
        //            addScollBar(matrices);
        this.scollBarEle.visible = TheList.size() > 10;


        int m = (int) (this.scollBarEle.getProgress() * TheList.size());//dang qian hang shu
        int a = MathHelper.ceil(TheList.size() / 12.0F);//zong ye shu
        int p = (int) (m / 12.0F);//dang qian ye shu
        if (!TheList.isEmpty()) {
            for (int i = 0; i < Math.min(12, TheList.size()); i++) {
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
            this.scollBarEle.updateScollHeight(10.0 / this.TheList.size());
        }


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
