package com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

import static net.minecraft.client.gui.widget.ClickableWidget.WIDGETS_TEXTURE;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.vne.widgets
 * @NAME: VNScollBarWidget
 * @USER: Perano
 * @DATE: 2023/1/10
 * @TIME: 14:51
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 10
 * @DAY_NAME_SHORT: 周二
 * @DAY_NAME_FULL: 星期二
 * @HOUR: 14
 * @MINUTE: 51
 * @PROJECT_NAME: furtelcraft
 */
@Environment(EnvType.CLIENT)
public abstract class BasedWidget extends AbstractParentElement implements Drawable, Selectable {
    protected boolean hovered;
    protected int width;
    protected int height;
    protected int textureWidth;
    protected int textureHeight;
    public int x;
    public int y;
    protected Color textColor;
    public boolean active = true;
    public boolean visible = true;
    protected float alpha = 1.0F;
    protected PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private Text message;
    private final List<Drawable> drawables = Lists.newArrayList();
    private final List<Element> elements = Lists.newArrayList();

    public BasedWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, Text message, Color textColor, PressAction onPress, TooltipSupplier tooltipSupplier) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.message = message;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.textColor = textColor;
    }

    public BasedWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, Text message, Color textColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.message = message;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.onPress = null;
        this.tooltipSupplier = null;
        this.textColor = textColor;
    }

    public BasedWidget(int x, int y, int width, int height, Text message, Color textColor, PressAction onPress, TooltipSupplier tooltipSupplier) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.message = message;
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.textColor = textColor;
    }

    public BasedWidget(int x, int y, int width, int height, Text message, Color textColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.message = message;
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.onPress = null;
        this.tooltipSupplier = null;
        this.textColor = textColor;
    }

    public BasedWidget(int x, int y, int width, int height, int textureWidth, int textureHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.message = LiteralText.EMPTY;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.onPress = null;
        this.tooltipSupplier = null;
        this.textColor = Color.WHITE;
    }

    public BasedWidget(int x, int y, int width, int height, PressAction onPress, TooltipSupplier tooltipSupplier) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.message = LiteralText.EMPTY;
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.textColor = Color.WHITE;
    }

    public BasedWidget(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.message = LiteralText.EMPTY;
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.onPress = null;
        this.tooltipSupplier = null;
        this.textColor = Color.WHITE;
    }

    public int getHeight() {
        return this.height;
    }

    protected int getYImage(boolean hovered) {
        int i = 1;
        if (!this.active) {
            i = 0;
        } else if (hovered) {
            i = 2;
        }

        return i;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (this.visible) {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            this.renderWidget(matrices, mouseX, mouseY, delta);
            for (Drawable drawable : this.drawables) {
                drawable.render(matrices, mouseX, mouseY, delta);
            }
            this.tick();
        }
    }

    public void tick() {

    }

    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, WIDGETS_TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.drawTexture(matrices, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
        this.drawTexture(matrices, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
        if (this.isHovered()) {
            this.renderTooltip(matrices, mouseX, mouseY);
        }
        if (this.textColor != null) {
            int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
            drawCenteredText(matrices, textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
        }
    }

    protected int RGB2DEC(int r, int g, int b) {
        int n = 0;
        n += (r << 16);
        n += (g << 8);
        n += b;
        return n;
    }

    public <T extends Element & Drawable> void addChild(T child) {
        this.drawables.add(child);
        this.elements.add(child);
    }

    public void clearChild() {
        this.drawables.clear();
        this.elements.clear();
    }

    public static void drawEntity(int x, int y, int size, float mouseX, float mouseY, @NotNull LivingEntity entity) {
        float f = (float) Math.atan(mouseX / 40.0F);
        float g = (float) Math.atan(mouseY / 40.0F);
        MatrixStack matrixStack = RenderSystem.getModelViewStack();
        matrixStack.push();
        matrixStack.translate(x, y, 1050.0);
        matrixStack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        MatrixStack matrixStack2 = new MatrixStack();
        matrixStack2.translate(0.0, 0.0, 1000.0);
        matrixStack2.scale((float) size, (float) size, (float) size);
        Quaternion quaternion = Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F);
        Quaternion quaternion2 = Vec3f.POSITIVE_X.getDegreesQuaternion(g * 20.0F);
        quaternion.hamiltonProduct(quaternion2);
        matrixStack2.multiply(quaternion);
        float h = entity.bodyYaw;
        float i = entity.getYaw();
        float j = entity.getPitch();
        float k = entity.prevHeadYaw;
        float l = entity.headYaw;
        entity.bodyYaw = 180.0F + f * 20.0F;
        entity.setYaw(180.0F + f * 40.0F);
        entity.setPitch(-g * 20.0F);
        entity.headYaw = entity.getYaw();
        entity.prevHeadYaw = entity.getYaw();
        DiffuseLighting.method_34742();
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        quaternion2.conjugate();
        entityRenderDispatcher.setRotation(quaternion2);
        entityRenderDispatcher.setRenderShadows(false);
        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        RenderSystem.runAsFancy(() -> entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, matrixStack2, immediate, 15728880));
        immediate.draw();
        entityRenderDispatcher.setRenderShadows(true);
        entity.bodyYaw = h;
        entity.setYaw(i);
        entity.setPitch(j);
        entity.prevHeadYaw = k;
        entity.headYaw = l;
        matrixStack.pop();
        RenderSystem.applyModelViewMatrix();
        DiffuseLighting.enableGuiDepthLighting();
    }

    public void renderTooltip(MatrixStack matrices, Text text, int x, int y) {
        Screen screen = MinecraftClient.getInstance().currentScreen;
        assert screen != null;
        screen.renderTooltip(matrices, text, x, y);
    }

    public void onClick() {
        this.onPress();
    }

    public List<? extends Element> children() {
        return this.elements;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible) {
            if (this.isValidClickButton(button)) {
                boolean clicked = this.isMouseOver(mouseX, mouseY);
                if (clicked) {
                    Iterator<? extends Element> iterator = this.children().iterator();
                    Element element;
                    do {
                        if (!iterator.hasNext()) {
                            this.playDownSound(MinecraftClient.getInstance().getSoundManager());
                            this.onClick();
                            return true;
                        }
                        element = iterator.next();
                        this.setInitialFocus(element);
                    } while (!element.mouseClicked(mouseX, mouseY, button));
                    this.setDragging(true);
                }
            }
        }
        return false;
    }

    protected boolean isValidClickButton(int button) {
        return button == 0;
    }

    public boolean isHovered() {
        return this.hovered;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= (double) this.x && mouseY >= (double) this.y && mouseX < (double) (this.x + this.width) && mouseY < (double) (this.y + this.height);
    }

    public boolean hasShiftDown() {
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 340) || InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 344);
    }

    public void renderTooltip(MatrixStack matrices, int mouseX, int mouseY) {
        if (this.tooltipSupplier != null) {
            this.tooltipSupplier.onTooltip(this, matrices, mouseX, mouseY);
        }
    }

    public void playDownSound(SoundManager soundManager) {

    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setMessage(Text message) {
        this.message = message;
    }

    public Text getMessage() {
        return this.message;
    }

    public void onPress() {
        if (this.onPress != null) {
            this.onPress.onPress(this);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.active && this.visible) {
            if (keyCode != 257 && keyCode != 32 && keyCode != 335) {
                return false;
            } else {
                this.playDownSound(MinecraftClient.getInstance().getSoundManager());
                this.onPress();
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public SelectionType getType() {
        return SelectionType.NONE;
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {

    }

    @Environment(EnvType.CLIENT)
    public interface TooltipSupplier {
        void onTooltip(BasedWidget widget, MatrixStack matrices, int mouseX, int mouseY);
    }

    @Environment(EnvType.CLIENT)
    public interface PressAction {
        void onPress(BasedWidget widget);
    }
}
