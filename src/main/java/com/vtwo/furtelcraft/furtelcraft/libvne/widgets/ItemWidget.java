package com.vtwo.furtelcraft.furtelcraft.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.libvne.VNScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Objects;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.widgets
 * @NAME: ItemWidget
 * @USER: Perano
 * @DATE: 2023/1/10
 * @TIME: 20:51
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 10
 * @DAY_NAME_SHORT: 周二
 * @DAY_NAME_FULL: 星期二
 * @HOUR: 20
 * @MINUTE: 51
 * @PROJECT_NAME: furtelcraft
 */
public class ItemWidget extends BasedWidget {
    protected int textureWidth;
    protected int textureHeight;
    protected int ItemWidth;
    protected int ItemHeight;
    protected int iItem;
    protected int jItem;
    protected Color textColor;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    protected ItemStack itemStack;
    private Text message;

    public ItemWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Text message, @Nullable Color textColor, ItemStack itemStack, @Nullable BasedWidget.PressAction onPress, @Nullable BasedWidget.TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, textureWidth, textureHeight, message, textColor, onPress, tooltipSupplier);
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.ItemWidth = width;
        this.ItemHeight = height;
        this.iItem = x;
        this.jItem = y;
        this.message = message;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.textColor = textColor;
        this.itemStack = itemStack;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, VNScreen.TEXTURE);
        drawTexture(matrices, iItem, jItem, 96, 64, ItemWidth, ItemHeight, textureWidth, textureHeight);
        ItemRenderer item = minecraftClient.getItemRenderer();
        item.renderInGui(this.itemStack, iItem + 2, jItem + 2);
        if (this.isHovered()) {
            this.renderTooltip(matrices, mouseX, mouseY);
        }
    }

    public void setItemStack(ItemStack itemStack) {
        Objects.requireNonNull(itemStack, "stacks == null!");
        if (itemStack.isEmpty()) {
            throw new IllegalArgumentException("The stack list is empty!");
        } else {
            this.itemStack = itemStack;
        }
    }
}
