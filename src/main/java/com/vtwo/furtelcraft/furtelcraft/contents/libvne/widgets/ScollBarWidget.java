package com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets
 * @NAME: ScollBarWidget
 * @USER: Perano
 * @DATE: 2023/4/10
 * @TIME: 20:42
 * @YEAR: 2023
 * @MONTH: 04
 * @MONTH_NAME_SHORT: 4月
 * @MONTH_NAME_FULL: 四月
 * @DAY: 10
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 20
 * @MINUTE: 42
 * @PROJECT_NAME: furtelcraft
 */
public class ScollBarWidget extends BasedWidget {
    protected int iScoll;
    protected int jScoll;
    protected int scollWidth;
    protected int scollHeight;
    protected int blockHeight;
    protected double blockPos;
    private static final Identifier TEXTURE;

    static {
        TEXTURE = new Identifier(MOD_ID, "textures/screen/scolls.png");
    }

    public ScollBarWidget(int x, int y, int height) {
        super(x, y, 6, height);
        this.iScoll = x;
        this.jScoll = y;
        this.scollWidth = 6;
        this.scollHeight = height;
        this.blockHeight = height;
        this.blockPos = 0.0;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, iScoll, jScoll, 0, 0, scollWidth, scollHeight / 2);
        drawTexture(matrices, iScoll, jScoll + (scollHeight / 2), 0, this.textureHeight - (scollHeight / 2), scollWidth, scollHeight / 2);
        int imageY = this.getImageY(this.isHovered());
        drawTexture(matrices, iScoll, (int) (jScoll + blockPos), imageY, 0, scollWidth, blockHeight / 2);
        drawTexture(matrices, iScoll, (int) (blockPos + jScoll + (blockHeight / 2)), imageY, this.textureHeight - (blockHeight / 2), scollWidth, blockHeight / 2);
        this.scollSync();
    }

    private int getImageY(boolean hovered) {
        int state = 1;
        if (hovered) {
            state = 2;
        }
        return state * scollWidth;
    }

    public double getProgress() {
        return blockPos / (scollHeight - blockHeight);
    }

    public void updateScollHeight(double rate) {
        if (rate > 1.0) {
            rate = 1.0;
        } else if (rate < 0.05) {
            rate = 0.05;
        }
        this.blockHeight = (int) (rate * scollHeight);
    }

    public void setProgress(double progress) {
        if (blockPos >= 0 && blockPos <= scollHeight - blockHeight) {
            this.blockPos = progress * (scollHeight - blockHeight);
        }
        if (blockPos <= 0) {
            this.blockPos = 0;
        } else if (blockPos >= scollHeight - blockHeight) {
            this.blockPos = scollHeight - blockHeight;
        }
    }

    public void resetProgress() {
        blockPos = 0.0;
    }

    private void scollSync() {
        this.y = (int) (jScoll + blockPos);
        this.height = blockHeight;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (blockPos >= 0 && blockPos <= scollHeight - blockHeight) {
            blockPos += deltaY;
        }
        if (blockPos <= 0) {
            blockPos = 0;
        } else if (blockPos >= scollHeight - blockHeight) {
            blockPos = scollHeight - blockHeight;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}
