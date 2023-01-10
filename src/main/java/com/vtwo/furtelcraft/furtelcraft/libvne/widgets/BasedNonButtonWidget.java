package com.vtwo.furtelcraft.furtelcraft.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;
import java.awt.*;
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
public class BasedNonButtonWidget extends DrawableHelper implements Drawable, Element, Selectable {
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
    protected float alpha = 1.0F;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private Text message;

    public BasedNonButtonWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Text message, @Nullable Color textColor, @Nullable PressAction onPress, @Nullable TooltipSupplier tooltipSupplier) {
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
        }
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
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        drawCenteredText(matrices, textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
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

    public void onClick(double mouseX, double mouseY) {
        this.onPress();
    }

    public void onRelease(double mouseX, double mouseY) {
    }

    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
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

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.isValidClickButton(button)) {
            this.onDrag(mouseX, mouseY, deltaX, deltaY);
            return true;
        } else {
            return false;
        }
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
        this.tooltipSupplier.onTooltip(this, matrices, mouseX, mouseY);
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
        assert this.tooltipSupplier != null;
        this.tooltipSupplier.supply((text) -> builder.put(NarrationPart.HINT, text));
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
        assert this.onPress != null;
        this.onPress.onPress(this);
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

    @Environment(EnvType.CLIENT)
    public interface TooltipSupplier {
        void onTooltip(BasedNonButtonWidget widget, MatrixStack matrices, int mouseX, int mouseY);

        default void supply(Consumer<Text> consumer) {
        }
    }

    @Environment(EnvType.CLIENT)
    public interface PressAction {
        void onPress(BasedNonButtonWidget widget);
    }
}
