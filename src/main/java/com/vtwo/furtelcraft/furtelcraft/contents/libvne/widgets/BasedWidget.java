package com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

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
public class BasedWidget extends DrawableHelper implements Drawable, ParentElement, Selectable {
    protected boolean hovered;
    private boolean focused;
    protected int width;
    protected int height;
    protected int textureWidth;
    protected int textureHeight;
    public int x;
    public int y;
    protected Color textColor;
    public boolean active = true;
    public boolean visible = true;
    @Nullable
    private Element efocused;
    protected float alpha = 1.0F;
    protected PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private Text message;
    private final List<? extends Element> children = Lists.newArrayList();
    private boolean dragging;

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
            this.tick();
        }
    }

    public void tick() {
    }

    protected MutableText getNarrationMessage() {
        return getNarrationMessage(this.getMessage());
    }

    public static MutableText getNarrationMessage(Text message) {
        return new TranslatableText("narrate.furtelcraft.widget", message);
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
        this.renderBackground(matrices, minecraftClient, mouseX, mouseY);
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

    protected void renderBackground(MatrixStack matrices, MinecraftClient client, int mouseX, int mouseY) {
    }

    public static void drawEntity(int x, int y, int size, float mouseX, float mouseY, LivingEntity entity) {
        float f = (float) Math.atan((double) (mouseX / 40.0F));
        float g = (float) Math.atan((double) (mouseY / 40.0F));
        MatrixStack matrixStack = RenderSystem.getModelViewStack();
        matrixStack.push();
        matrixStack.translate((double) x, (double) y, 1050.0);
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

    public void onClick(double mouseX, double mouseY) {
        this.onPress();
    }

    public void onRelease(double mouseX, double mouseY) {
    }

    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
    }

    @Override
    public List<? extends Element> children() {
        return this.children;
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible) {
            if (this.isValidClickButton(button)) {
                boolean bl = this.clicked(mouseX, mouseY);
                if (bl) {
                    this.playDownSound(MinecraftClient.getInstance().getSoundManager());
                    this.onClick(mouseX, mouseY);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.isValidClickButton(button)) {
            this.onRelease(mouseX, mouseY);
            return true;
        } else {
            return false;
        }
    }

    protected boolean isValidClickButton(int button) {
        return button == 0;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return ParentElement.super.mouseScrolled(mouseX, mouseY, amount);
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.isValidClickButton(button)) {
            this.onDrag(mouseX, mouseY, deltaX, deltaY);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isDragging() {
        return this.dragging;
    }

    @Override
    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    protected boolean clicked(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= (double) this.x && mouseY >= (double) this.y && mouseX < (double) (this.x + this.width) && mouseY < (double) (this.y + this.height);
    }

    public boolean isHovered() {
        return this.hovered || this.focused;
    }

    public boolean changeFocus(boolean lookForwards) {
        if (this.active && this.visible) {
            this.focused = !this.focused;
            this.onFocusedChanged(this.focused);
            return this.focused;
        } else {
            return false;
        }
    }

    protected void onFocusedChanged(boolean newFocused) {
    }

    public boolean isMouseOver(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= (double) this.x && mouseY >= (double) this.y && mouseX < (double) (this.x + this.width) && mouseY < (double) (this.y + this.height);
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

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setMessage(Text message) {
        this.message = message;
    }

    public Text getMessage() {
        return this.message;
    }

    public boolean isFocused() {
        return this.focused;
    }

    public boolean isNarratable() {
        return this.visible && this.active;
    }

    protected void setFocused(boolean focused) {
        this.focused = focused;
    }

    public Selectable.SelectionType getType() {
        if (this.focused) {
            return SelectionType.FOCUSED;
        } else {
            return this.hovered ? SelectionType.HOVERED : SelectionType.NONE;
        }
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {
        this.appendDefaultNarrations(builder);
        if (this.tooltipSupplier != null) {
            this.tooltipSupplier.supply((text) -> builder.put(NarrationPart.HINT, text));
        }
    }

    private void appendDefaultNarrations(NarrationMessageBuilder builder) {
        builder.put(NarrationPart.TITLE, this.getNarrationMessage());
        if (this.active) {
            if (this.isFocused()) {
                builder.put(NarrationPart.USAGE, new TranslatableText("narration.button.usage.focused"));
            } else {
                builder.put(NarrationPart.USAGE, new TranslatableText("narration.button.usage.hovered"));
            }
        }
    }

    public void onPress() {
        if (this.onPress != null) {
            this.onPress.onPress(this);
        }
    }


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

    @Nullable
    @Override
    public Element getFocused() {
        return this.efocused;
    }

    @Override
    public void setFocused(@Nullable Element focused) {
        this.efocused = focused;
    }

    @Environment(EnvType.CLIENT)
    public interface TooltipSupplier {
        void onTooltip(BasedWidget widget, MatrixStack matrices, int mouseX, int mouseY);

        default void supply(Consumer<Text> consumer) {
        }
    }

    @Environment(EnvType.CLIENT)
    public interface PressAction {
        void onPress(BasedWidget widget);
    }
}
