package com.vtwo.furtelcraft.furtelcraft.screens.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.init.FCBlocks;
import com.vtwo.furtelcraft.furtelcraft.init.FCNetPacks;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.screens.hud
 * @NAME: TubeHolderHud
 * @USER: Perano
 * @DATE: 2023/2/1
 * @TIME: 0:43
 * @YEAR: 2023
 * @MONTH: 02
 * @MONTH_NAME_SHORT: 2月
 * @MONTH_NAME_FULL: 二月
 * @DAY: 01
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 00
 * @MINUTE: 43
 * @PROJECT_NAME: furtelcraft
 */
public class TubeHolderHud implements HudRenderCallback {
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/hud/tooltiphud.png");
    private static TubeHolderHud instance;
    private HashMap<Integer, ItemStack> items = new HashMap<>();

    public TubeHolderHud() {
        instance = this;
    }

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        HitResult hit = client.crosshairTarget;
        assert hit != null;
        if (hit.getType().equals(HitResult.Type.BLOCK)) {
            assert client.player != null;
            if (!client.player.isSpectator()) {
                BlockHitResult blockHit = (BlockHitResult) hit;
                BlockPos pos = blockHit.getBlockPos();
                assert client.world != null;
                BlockState state = client.world.getBlockState(pos);
                Block block = state.getBlock();
                if (block.equals(FCBlocks.TUBE_HOLDER)) {
                    PacketByteBuf byteBuf = PacketByteBufs.create();
                    byteBuf.writeBlockPos(pos);
                    ClientPlayNetworking.send(FCNetPacks.CLIENT_TUBE_HOLDER_HUD_RENDER_ID, byteBuf);
                    this.render(matrixStack, client);
                }
            }
        }
    }

    public void render(MatrixStack matrixStack, MinecraftClient client) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int backgroundWidth = 112;
        int centerX = (client.getWindow().getScaledWidth() - backgroundWidth) / 2;
        int backgroundHeight = 48;
        int centerY = (client.getWindow().getScaledHeight() - backgroundHeight) / 2;
        DrawableHelper.drawTexture(matrixStack, centerX + (backgroundWidth / 2) + 6, centerY, 0, 0, backgroundWidth, backgroundHeight, 256, 256);
        if (!this.items.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                String name;
                if (items.get(i).getName().getString().equals(ItemStack.EMPTY.getName().getString())) {
                    name = "";
                } else {
                    name = items.get(i).getName().getString();
                }
                DrawableHelper.drawWithShadow(matrixStack, MinecraftClient.getInstance().textRenderer, new TranslatableText("block.furtelcraft.tube_holder.hud.title").setStyle(Style.EMPTY.withColor(Formatting.GOLD)).asOrderedText(), centerX + (backgroundWidth / 2) + 10, 4 + centerY, 16777215);
                DrawableHelper.drawStringWithShadow(matrixStack, MinecraftClient.getInstance().textRenderer, name, centerX + (backgroundWidth / 2) + 10, 18 + centerY + i * 9, 16777215);
            }
        }
    }

    public void setItems(HashMap<Integer, ItemStack> map) {
        this.items = map;
    }

    public static TubeHolderHud getInstance() {
        return instance;
    }
}
