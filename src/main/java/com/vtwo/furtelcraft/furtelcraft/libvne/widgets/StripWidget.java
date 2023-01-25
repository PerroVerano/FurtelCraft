package com.vtwo.furtelcraft.furtelcraft.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.GameRenderer;
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
 * @NAME: MultiLineStripWidget
 * @USER: Perano
 * @DATE: 2023/1/19
 * @TIME: 20:55
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 19
 * @DAY_NAME_SHORT: 周四
 * @DAY_NAME_FULL: 星期四
 * @HOUR: 20
 * @MINUTE: 55
 * @PROJECT_NAME: furtelcraft
 */
public class StripWidget extends BasedNonButtonWidget {
    private int iStrip;
    private int jStrip;
    private int StripWidth;
    private int StripHeight;
    private Text message;
    private Color textColor;
    private PressAction onPress;
    private TooltipSupplier tooltipSupplier;
    private int state;
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/widgets.png");

    public StripWidget(int x, int y, int width, @Deprecated int height, @Nullable Text message, @Nullable Color textColor, @Nullable BasedNonButtonWidget.PressAction onPress, @Nullable BasedNonButtonWidget.TooltipSupplier tooltipSupplier, int state) {
        super(x, y, width, height, message, textColor, onPress, tooltipSupplier);
        this.iStrip = x;
        this.jStrip = y;
        this.StripWidth = width;
        this.StripHeight = 14;
        this.message = message;
        this.textColor = textColor;
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.state = state;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, this.iStrip, this.jStrip, 0, 64 + this.state * 14, this.StripWidth / 2, this.StripHeight);
        drawTexture(matrices, this.iStrip + this.StripWidth / 2, this.jStrip, 256 - this.StripWidth / 2, 64 + this.state * 14, this.StripWidth / 2, this.StripHeight);
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        if (this.isHovered()) {
            this.renderTooltip(matrices, mouseX, mouseY);
            j = RGB2DEC(Color.ORANGE.getRed(), Color.ORANGE.getGreen(), Color.ORANGE.getBlue());
        }
        drawCenteredText(matrices, textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
