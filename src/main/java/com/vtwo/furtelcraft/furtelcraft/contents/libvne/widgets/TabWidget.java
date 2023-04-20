package com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets;

import com.vtwo.furtelcraft.furtelcraft.contents.libvne.EditScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.widgets
 * @NAME: TabWidget
 * @USER: Perano
 * @DATE: 2023/1/19
 * @TIME: 18:11
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 19
 * @DAY_NAME_SHORT: 周四
 * @DAY_NAME_FULL: 星期四
 * @HOUR: 18
 * @MINUTE: 11
 * @PROJECT_NAME: furtelcraft
 */
public class TabWidget extends BasedWidget {
    private int iTab;
    private int jTab;
    private @Deprecated int TabWidth;
    private @Deprecated int TabHeight;
    private Text message;
    private Color textColor;
    private PressAction onPress;
    private TooltipSupplier tooltipSupplier;
    private boolean isEnabled;

    public TabWidget(int x, int y, @Deprecated int width, @Deprecated int height, @Nullable Text message, @Nullable Color textColor, @Nullable BasedWidget.PressAction onPress, @Nullable BasedWidget.TooltipSupplier tooltipSupplier, boolean isEnabled) {
        super(x, y, width, height, message, textColor, onPress, tooltipSupplier);
        this.iTab = x;
        this.jTab = y;
        this.TabWidth = width;
        this.TabHeight = height;
        this.message = message;
        this.textColor = textColor;
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.isEnabled = isEnabled;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;
        this.setRenderSystem(EditScreen.TEXTURE);
        if (isEnabled) {
            drawTexture(matrices, iTab, jTab, 0, 185, 32, 19);
        } else {
            drawTexture(matrices, iTab, jTab, 0, 168, 32, 17);
        }
        int j = RGB2DEC(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
        if (this.isHovered()) {
            this.renderTooltip(matrices, mouseX, mouseY);
            j = RGB2DEC(Color.ORANGE.getRed(), Color.ORANGE.getGreen(), Color.ORANGE.getBlue());
        }
        drawCenteredText(matrices, textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
