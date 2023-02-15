package com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.widgets
 * @NAME: BasedScollBarWidget
 * @USER: Perano
 * @DATE: 2023/1/11
 * @TIME: 17:36
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 11
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 17
 * @MINUTE: 36
 * @PROJECT_NAME: furtelcraft
 */
public class BasedScollBlockWidget extends BasedWidget {
    protected int ScollBlockWidth;
    protected int ScollBlockHeight;
    protected int iScollBlock;
    protected int jScollBlock;
    protected Color textColor;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private Text message;
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/vne_scoll_block.png");

    public BasedScollBlockWidget(int x, int y, int width, int height, @Nullable Text message, @Nullable Color textColor, @Nullable BasedWidget.PressAction onPress, @Nullable BasedWidget.TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, message, textColor, onPress, tooltipSupplier);
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.ScollBlockWidth = width;
        this.ScollBlockHeight = height;
        this.iScollBlock = x;
        this.jScollBlock = y;
        this.message = message;
        this.textColor = textColor;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, iScollBlock, jScollBlock, 0, 0, 5, 12, 5, 12);
    }
}
