package com.vtwo.furtelcraft.furtelcraft.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.screens.handler.MagneticParticleProcessorScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class MagneticParticleProcessorScreen extends HandledScreen<MagneticParticleProcessorScreenHandler> {
    //GUI纹理路径
    private static final Identifier TEXTURE = new Identifier(MOD_ID,"textures/screen/mpp.png");

    public MagneticParticleProcessorScreen(MagneticParticleProcessorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.passEvents = false;
        this.backgroundHeight = 167;
        this.backgroundWidth = 176;
    }

    /* x:目标x坐标 - 1
     * y:目标y坐标 - 1
     * u:替代x坐标 - 1
     * v:替代y坐标 - 1
     * w:替代的宽度
     * h:替代的高度
     */
    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int i = this.x;
        int j = this.y;
        this.drawTexture(matrices,i,j,0,0,this.backgroundWidth,this.backgroundHeight);
        int tick = this.handler.getTick() / 20;
        int energy = this.handler.getEnergy();
        this.drawTexture(matrices,i + 75,j + 38,176,1,tick * 3,9);
        int k = 12 - energy;
        this.drawTexture(matrices,i + 151,j + 10,176,58 - (k * 4),16, 48 - (energy * 4));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices,mouseX,mouseY,delta);
        drawMouseoverTooltip(matrices,mouseX,mouseY);
    }


    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 4;
    }
}
