package com.vtwo.furtelcraft.furtelcraft.libvne;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.libvne.widgets.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import javax.annotation.Nullable;
import java.awt.*;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.vne
 * @NAME: TestVNGui
 * @USER: Perano
 * @DATE: 2023/1/10
 * @TIME: 11:06
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 10
 * @DAY_NAME_SHORT: 周二
 * @DAY_NAME_FULL: 星期二
 * @HOUR: 11
 * @MINUTE: 06
 * @PROJECT_NAME: furtelcraft
 */
public class VNScreen extends Screen {
    protected int textureWidth = 256;
    protected int textureHeight = 86;
    protected int mainPanleWidth = 252;
    protected int mainPanelHeight = 64;
    protected int iButtonWidth = 25;
    protected int ButtonWidth = 27;
    protected int ButtonHeight = 11;
    protected int nameBarWidth = 44;
    protected int selectionButtonWidth = 96;
    protected int scollBarWidth = 4;
    protected int scollBarHeight = 54;
    protected int scollWidth = 4;
    protected int scollHeight = 10;
    protected int ItemFrame = 22;
    protected boolean isSkipEnabled = false;
    protected boolean isAutoEnabled = true;
    protected boolean isHistEnabled = false;
    protected boolean isExitEnabled = true;
    protected boolean isScollEnabled = false;
    protected @Nullable Text TheName = Text.empty();
    public static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/vne.png");

    public VNScreen(Text title) {
        super(title);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, iBase, jBase, 0, 0, mainPanleWidth, mainPanelHeight, textureWidth, textureHeight);


        if (isScollEnabled) {
            int iScollBar = iBase + 243;
            int jScollBar = jBase + 5;
            int iScollBlock = iBase + 243;
            int jScollBlock = jBase + 5;
            this.addDrawableChild(new ScollBarWidget(
                    iScollBar,
                    jScollBar,
                    scollBarWidth,
                    scollBarHeight,
                    textureWidth,
                    textureHeight,
                    Text.empty(),
                    null,
                    widget -> {

                    },
                    (widget, matrices1, mouseX1, mouseY1) -> {

                    },
                    false
            ));
            this.addDrawableChild(new ScollBarWidget(
                    iScollBlock,
                    jScollBlock,
                    scollWidth,
                    scollHeight,
                    textureWidth,
                    textureHeight,
                    Text.empty(),
                    null,
                    widget -> {

                    },
                    (widget, matrices1, mouseX1, mouseY1) -> {

                    },
                    true
            ));
        }

        int iNameBar = iBase + 2;
        int jNameBar = jBase - 11;
        this.addDrawableChild(new NameBarWidget(
                iNameBar,
                jNameBar,
                nameBarWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                TheName,
                Color.ORANGE,
                widget -> {

                },
                (widget, matrices1, mouseX1, mouseY1) -> {

                }
        ));

        int iIButton = iBase + 227;
        int jButton = jBase + 64;
        if (isSkipEnabled) {
            this.addDrawableChild(new SkipButtonWidget(
                    iIButton,
                    jButton,
                    iButtonWidth,
                    ButtonHeight,
                    textureWidth,
                    textureHeight,
                    Text.empty(),
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> this.renderTooltip(
                            matrices1,
                            Text.translatable("text.furtelcraft.vne.skip.tooltip"),
                            mouseX1,
                            mouseY1),
                    true));
        } else {
            this.addDrawableChild(new SkipButtonWidget(
                    iIButton,
                    jButton,
                    iButtonWidth,
                    ButtonHeight,
                    textureWidth,
                    textureHeight,
                    Text.empty(),
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> this.renderTooltip(
                            matrices1,
                            Text.translatable("text.furtelcraft.vne.skip.tooltip"),
                            mouseX1,
                            mouseY1),
                    false));
        }


        int iAutoButton = iBase + 200;
        if (isAutoEnabled) {
            this.addDrawableChild(new AutoButtonWidget(
                    iAutoButton,
                    jButton,
                    ButtonWidth,
                    ButtonHeight,
                    textureWidth,
                    textureHeight,
                    Text.empty(),
                    button -> {
                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                Text.translatable("text.furtelcraft.vne.auto.tooltip"),
                                mouseX1,
                                mouseY1);
                    },
                    true
            ));
        } else {
            this.addDrawableChild(new AutoButtonWidget(
                    iAutoButton,
                    jButton,
                    ButtonWidth,
                    ButtonHeight,
                    textureWidth,
                    textureHeight,
                    Text.empty(),
                    button -> {
                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                Text.translatable("text.furtelcraft.vne.auto.tooltip"),
                                mouseX1,
                                mouseY1);
                    },
                    false
            ));
        }


        int iHistButton = iBase + 175;
        if (isHistEnabled) {
            this.addDrawableChild(new HistButtonWidget(
                    iHistButton,
                    jButton,
                    iButtonWidth,
                    ButtonHeight,
                    textureWidth,
                    textureHeight,
                    Text.empty(),
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                Text.translatable("text.furtelcraft.vne.hist.tooltip"),
                                mouseX1,
                                mouseY1);
                    },
                    true
            ));
        } else {
            this.addDrawableChild(new HistButtonWidget(
                    iHistButton,
                    jButton,
                    iButtonWidth,
                    ButtonHeight,
                    textureWidth,
                    textureHeight,
                    Text.empty(),
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                Text.translatable("text.furtelcraft.vne.hist.tooltip"),
                                mouseX1,
                                mouseY1);
                    },
                    false
            ));
        }


        int iExitButton = iBase + 150;
        if (isExitEnabled) {
            this.addDrawableChild(new ExitButtonWidget(
                    iExitButton,
                    jButton,
                    iButtonWidth,
                    ButtonHeight,
                    textureWidth,
                    textureHeight,
                    Text.empty(),
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                Text.translatable("text.furtelcraft.vne.exit.tooltip"),
                                mouseX1,
                                mouseY1
                        );
                    },
                    true
            ));
        } else {
            this.addDrawableChild(new ExitButtonWidget(
                    iExitButton,
                    jButton,
                    iButtonWidth,
                    ButtonHeight,
                    textureWidth,
                    textureHeight,
                    Text.empty(),
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                Text.translatable("text.furtelcraft.vne.exit.tooltip"),
                                mouseX1,
                                mouseY1
                        );
                    },
                    false
            ));
        }

        super.render(matrices, mouseX, mouseY, delta);
    }

    public boolean isSkipEnabled() {
        return isSkipEnabled;
    }

    public void setSkipEnabled(boolean skipEnabled) {
        isSkipEnabled = skipEnabled;
    }

    public boolean isAutoEnabled() {
        return isAutoEnabled;
    }

    public void setAutoEnabled(boolean autoEnabled) {
        isAutoEnabled = autoEnabled;
    }

    public boolean isHistEnabled() {
        return isHistEnabled;
    }

    public void setHistEnabled(boolean histEnabled) {
        isHistEnabled = histEnabled;
    }

    public boolean isExitEnabled() {
        return isExitEnabled;
    }

    public void setExitEnabled(boolean exitEnabled) {
        isExitEnabled = exitEnabled;
    }

    public boolean isScollEnabled() {
        return isScollEnabled;
    }

    public void setScollEnabled(boolean scollEnabled) {
        isScollEnabled = scollEnabled;
    }

    public @Nullable Text getTheName() {
        return TheName;
    }

    public void setTheName(@Nullable Text theName) {
        TheName = theName;
    }
}
