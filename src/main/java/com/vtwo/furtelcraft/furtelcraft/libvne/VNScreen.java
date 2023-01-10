package com.vtwo.furtelcraft.furtelcraft.libvne;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import com.vtwo.furtelcraft.furtelcraft.libvne.widgets.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
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
    protected int ItemFrame = 21;
    protected int TextPanelWidth = 232;
    protected int TextPanelHeight = 50;
    protected boolean isSkipEnabled = false;
    protected boolean isAutoEnabled = true;
    protected boolean isHistEnabled = false;
    protected boolean isExitEnabled = true;
    protected boolean isScollEnabled = false;
    protected boolean isOS1Enabled = false;
    protected boolean isOS2Enabled = false;
    protected boolean isTextEnabled = false;
    protected boolean isNameEnabled = false;
    protected boolean isItemEnabled = false;
    protected @Nullable Text TheName = LiteralText.EMPTY;
    protected @Nullable Text TheText = LiteralText.EMPTY;
    protected @Nullable ItemStack TheItem = new ItemStack(ItemInit.GUIDE_BOOK);
    protected @Nullable Color TheColor = Color.ORANGE;
    protected static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/vne.png");

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

        addWidgets();

        super.render(matrices, mouseX, mouseY, delta);
    }

    protected void addWidgets() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        if (isItemEnabled) {
            int iItem = iBase + 224;
            int jItem = jBase + 40;
            this.addDrawableChild(new ItemWidget(
                    iItem,
                    jItem,
                    ItemFrame,
                    ItemFrame,
                    textureWidth,
                    textureHeight,
                    LiteralText.EMPTY,
                    null,
                    TheItem,
                    widget -> {

                    },
                    (widget, matrices1, mouseX1, mouseY1) -> {
                        assert TheItem != null;
                        this.renderTooltip(
                                matrices1,
                                new TranslatableText(TheItem.getTranslationKey()),
                                mouseX1,
                                mouseY1
                        );
                    }
            ));
        }


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
                    LiteralText.EMPTY,
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
                    LiteralText.EMPTY,
                    null,
                    widget -> {

                    },
                    (widget, matrices1, mouseX1, mouseY1) -> {

                    },
                    true
            ));
        }

        if (isNameEnabled) {
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
                    TheColor,
                    widget -> {

                    },
                    (widget, matrices1, mouseX1, mouseY1) -> {

                    }
            ));
        }


        if (isTextEnabled) {
            int iTextPanel = iBase + 7;
            int jTextPanel = jBase + 7;
            this.addDrawableChild(new TextWidget(
                    iTextPanel,
                    jTextPanel,
                    TextPanelWidth,
                    TextPanelHeight,
                    textureWidth,
                    textureHeight,
                    TheText,
                    Color.WHITE,
                    widget -> {
                    },
                    (widget, matrices1, mouseX1, mouseY1) -> {
                    }
            ));
        }


        if (isOS1Enabled) {
            int iOptionSelect1 = iBase + 2;
            int jOptionSelect1 = jBase + 64;
            this.addDrawableChild(new OptionSelectWidget(
                    iOptionSelect1,
                    jOptionSelect1,
                    selectionButtonWidth,
                    ButtonHeight,
                    textureWidth,
                    textureHeight,
                    new LiteralText("你就放屁吧你"),
                    Color.PINK,
                    widget -> {

                    },
                    (widget, matrices1, mouseX1, mouseY1) -> {
                    }
            ));
        }


        if (isOS2Enabled) {
            int iOptionSelect2 = iBase + 13;
            int jOptionSelect2 = jBase + 64;
            this.addDrawableChild(new OptionSelectWidget(
                    iOptionSelect2,
                    jOptionSelect2,
                    selectionButtonWidth,
                    ButtonHeight,
                    textureWidth,
                    textureHeight,
                    new LiteralText("你就放屁吧你"),
                    Color.PINK,
                    widget -> {

                    },
                    (widget, matrices1, mouseX1, mouseY1) -> {
                    }
            ));
        }


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
                    LiteralText.EMPTY,
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> this.renderTooltip(
                            matrices1,
                            new TranslatableText("text.furtelcraft.vne.skip.tooltip"),
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
                    LiteralText.EMPTY,
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> this.renderTooltip(
                            matrices1,
                            new TranslatableText("text.furtelcraft.vne.skip.tooltip"),
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
                    LiteralText.EMPTY,
                    button -> {
                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                new TranslatableText("text.furtelcraft.vne.auto.tooltip"),
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
                    LiteralText.EMPTY,
                    button -> {
                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                new TranslatableText("text.furtelcraft.vne.auto.tooltip"),
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
                    LiteralText.EMPTY,
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                new TranslatableText("text.furtelcraft.vne.hist.tooltip"),
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
                    LiteralText.EMPTY,
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                new TranslatableText("text.furtelcraft.vne.hist.tooltip"),
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
                    LiteralText.EMPTY,
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                new TranslatableText("text.furtelcraft.vne.exit.tooltip"),
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
                    LiteralText.EMPTY,
                    button -> {

                    },
                    (button, matrices1, mouseX1, mouseY1) -> {
                        this.renderTooltip(
                                matrices1,
                                new TranslatableText("text.furtelcraft.vne.exit.tooltip"),
                                mouseX1,
                                mouseY1
                        );
                    },
                    false
            ));
        }
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

    @Nullable
    public Color getTheColor() {
        return TheColor;
    }

    public void setTheColor(@Nullable Color theColor) {
        TheColor = theColor;
    }

    public boolean isOS1Enabled() {
        return isOS1Enabled;
    }

    public void setOS1Enabled(boolean OS1Enabled) {
        isOS1Enabled = OS1Enabled;
    }

    public boolean isOS2Enabled() {
        return isOS2Enabled;
    }

    public void setOS2Enabled(boolean OS2Enabled) {
        isOS2Enabled = OS2Enabled;
    }

    public boolean isTextEnabled() {
        return isTextEnabled;
    }

    public void setTextEnabled(boolean textEnabled) {
        isTextEnabled = textEnabled;
    }

    public boolean isNameEnabled() {
        return isNameEnabled;
    }

    public void setNameEnabled(boolean nameEnabled) {
        isNameEnabled = nameEnabled;
    }

    @Nullable
    public Text getTheText() {
        return TheText;
    }

    public void setTheText(@Nullable Text theText) {
        TheText = theText;
    }

    public boolean isItemEnabled() {
        return isItemEnabled;
    }

    public void setItemEnabled(boolean itemEnabled) {
        isItemEnabled = itemEnabled;
    }

    @Nullable
    public ItemStack getTheItem() {
        return TheItem;
    }

    public void setTheItem(@Nullable ItemStack theItem) {
        TheItem = theItem;
    }
}
