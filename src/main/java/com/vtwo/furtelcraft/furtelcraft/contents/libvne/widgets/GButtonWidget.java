package com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.widgets
 * @NAME: GButtonWidget
 * @USER: Perano
 * @DATE: 2023/1/24
 * @TIME: 22:26
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 24
 * @DAY_NAME_SHORT: 周二
 * @DAY_NAME_FULL: 星期二
 * @HOUR: 22
 * @MINUTE: 26
 * @PROJECT_NAME: furtelcraft
 */
public class GButtonWidget extends BasedWidget {
    protected int textureWidth = 256;
    protected int textureHeight = 256;
    protected int ButtonWidth;
    protected int ButtonHeight;
    protected int iButton;
    protected int jButton;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/widgets.png");

    public GButtonWidget(int x, int y, int width, int height, @Nullable Text message, @Nullable Color textColor, @Nullable BasedWidget.PressAction onPress, @Nullable BasedWidget.TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, message, textColor, onPress, tooltipSupplier);
        this.iButton = x;
        this.jButton = y;
        this.ButtonWidth = width;
        this.ButtonHeight = 16;
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.setRenderSystem(TEXTURE);
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;
        int pos = this.getPos(this.isHovered(), mouseX, mouseY);
        drawTexture(matrices, this.iButton, this.jButton, 0, pos, this.ButtonWidth / 2, this.ButtonHeight);
        drawTexture(matrices, this.iButton + this.ButtonWidth / 2, this.jButton, this.textureWidth - this.ButtonWidth / 2, pos, this.ButtonWidth / 2, this.ButtonHeight);
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        drawCenteredText(matrices, textRenderer, this.getMessage(), this.iButton + this.ButtonWidth / 2, this.jButton + (this.ButtonHeight - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
        if (this.isHovered()) {
            this.renderTooltip(matrices, mouseX, mouseY);
        }
    }

    private int getPos(boolean isHovered, double mouseX, double mouseY) {
        int state = 1;
        if (!this.active) {
            state = 0;
        } else if (isHovered) {
            state = 2;
        } else if (this.isMouseOver(mouseX, mouseY)) {
            state = 3;
        }
        return state * ButtonHeight;
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
