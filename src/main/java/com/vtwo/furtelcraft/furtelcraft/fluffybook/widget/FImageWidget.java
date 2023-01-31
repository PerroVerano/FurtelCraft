package com.vtwo.furtelcraft.furtelcraft.fluffybook.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.libvne.widgets.BasedWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook.widget
 * @NAME: ImageWidget
 * @USER: Perano
 * @DATE: 2023/1/29
 * @TIME: 13:43
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 29
 * @DAY_NAME_SHORT: 周日
 * @DAY_NAME_FULL: 星期日
 * @HOUR: 13
 * @MINUTE: 43
 * @PROJECT_NAME: furtelcraft
 */
public class FImageWidget extends BasedWidget {
    private final int iImage;
    private final int jImage;
    private final int imageWidth;
    private final int imageHeight;
    private final Identifier image;
    private boolean hasTitle = false;
    private Text title;
    private Color titleColor;

    public FImageWidget(int x, int y, int width, int height, Identifier image) {
        super(x, y, width, height);
        this.iImage = x;
        this.jImage = y;
        this.imageWidth = width;
        this.imageHeight = height;
        this.image = image;
    }

    public FImageWidget(int x, int y, int width, int height, Identifier image, boolean hasTitle, Text title, Color titleColor) {
        super(x, y, width, height);
        this.iImage = x;
        this.jImage = y;
        this.imageWidth = width;
        this.imageHeight = height;
        this.image = image;
        this.hasTitle = hasTitle;
        this.title = title;
        this.titleColor = titleColor;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.image);
        drawTexture(matrices, iImage, jImage, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
        if (this.hasTitle) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            TextRenderer textRenderer = minecraftClient.textRenderer;
            int j = RGB2DEC(this.titleColor.getRed(), this.titleColor.getGreen(), this.titleColor.getBlue());
            drawCenteredText(matrices, textRenderer, this.title, this.iImage + this.imageWidth / 2, this.jImage + (this.imageHeight * 2 + 2) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
        }
        RenderSystem.disableBlend();
    }
}
