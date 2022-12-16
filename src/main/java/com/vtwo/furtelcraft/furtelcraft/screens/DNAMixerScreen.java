package com.vtwo.furtelcraft.furtelcraft.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.screens.handler.DNAMixerScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class DNAMixerScreen extends HandledScreen<DNAMixerScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(MOD_ID,"textures/screen/dna_mixer.png");
    public DNAMixerScreen(DNAMixerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.passEvents = false;
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int i = (width - backgroundWidth) / 2;
        int j = (height - backgroundHeight) / 2;
        this.drawTexture(matrices,i,j,0,0,this.backgroundWidth,this.backgroundHeight);
        int tick = this.handler.getTick() / 20;
        this.drawTexture(matrices,i + 51,j + 39,176,3,2,tick / 3);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices,mouseX,mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
