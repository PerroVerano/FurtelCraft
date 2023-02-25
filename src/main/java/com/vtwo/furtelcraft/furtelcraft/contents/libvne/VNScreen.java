package com.vtwo.furtelcraft.furtelcraft.contents.libvne;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets.*;
import com.vtwo.furtelcraft.furtelcraft.init.FCItems;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

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
    protected int nameBarWidth = 69;
    protected int selectionButtonWidth = 96;
    protected int ItemFrame = 21;
    protected int HistWidth = 134;
    protected int HistHeight = 136;
    protected int TextPanelWidth = 254;
    protected int TextPanelHeight = 54;
    protected boolean isSkipEnabled = false;
    protected boolean isAutoEnabled = true;
    protected boolean isHistEnabled = true;
    protected boolean isExitEnabled = true;
    private boolean isScollEnabled = false;
    protected boolean isOS1Enabled = false;
    protected boolean isOS2Enabled = false;
    protected boolean isTextEnabled = true;
    protected boolean isNameEnabled = false;
    protected boolean isItemEnabled = false;
    protected boolean isHistSideEnabled = false;
    private static final Text EMPTY = LiteralText.EMPTY;
    protected @Nullable Text TheName = EMPTY;
    protected @Nullable Text TheText = EMPTY;
    private String HistText = "";
    private NbtList WordList;
    private int pageCount = 0;
    private NbtList OS1WordList;
    private NbtList OS2WordList;
    private boolean hasSelected = false;
    protected @Nullable Text TheOptionSelect1 = EMPTY;
    protected @Nullable Text TheOptionSelect2 = EMPTY;
    protected BasedWidget.PressAction onPress = widget -> {
    };
    protected BasedWidget.TooltipSupplier tooltipSupplier = (widget, matrices, mouseX, mouseY) -> {
    };
    protected BasedWidget.TooltipSupplier OS1Tooltip = tooltipSupplier;
    protected BasedWidget.TooltipSupplier OS2Tooltip = tooltipSupplier;
    protected @Nullable ItemStack TheItem = new ItemStack(FCItems.GUIDE_BOOK);
    protected @Nullable Color TheColor = Color.ORANGE;
    public static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/vne.png");
    private List<? extends BasedWidget> WidgetList;

    private HistSideWidget HistSideEle;
    private ItemWidget ItemEle;
    private NameBarWidget NameBarEle;
    private TextWidget TextEle;
    private OptionSelectWidget OS1Ele;
    private OptionSelectWidget OS2Ele;
    private SkipButtonWidget EnabledSkipBtnEle;
    private SkipButtonWidget DisabledSkipBtnEle;
    private AutoButtonWidget EnabledAutoBtnEle;
    private AutoButtonWidget DisabledAutoBtnEle;
    private HistButtonWidget EnabledHistBtnEle;
    private HistButtonWidget DisabledHistBtnEle;
    private ExitButtonWidget EnabledExitBtnEle;
    private ExitButtonWidget DisabledExitBtnEle;
    protected int ScreenWidth;
    protected int ScreenHeight;

    public VNScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        this.passEvents = false;
        this.initWidgetList();
        this.addWidgets();
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.ScreenWidth = width;
        this.ScreenHeight = height;
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, iBase, jBase, 0, 0, mainPanleWidth, mainPanelHeight, textureWidth, textureHeight);


        this.updateWidgets();

        super.render(matrices, mouseX, mouseY, delta);
    }

    protected void addWidgets() {
        this.HistSideEle = (HistSideWidget) this.addDrawableChild(WidgetList.get(1));
        this.ItemEle = (ItemWidget) this.addDrawableChild(WidgetList.get(2));
        this.NameBarEle = (NameBarWidget) this.addDrawableChild(WidgetList.get(3));
        this.TextEle = (TextWidget) this.addDrawableChild(WidgetList.get(4));
        this.OS1Ele = (OptionSelectWidget) this.addDrawableChild(WidgetList.get(5));
        this.OS2Ele = (OptionSelectWidget) this.addDrawableChild(WidgetList.get(6));
        this.EnabledSkipBtnEle = (SkipButtonWidget) this.addDrawableChild(WidgetList.get(7));
        this.DisabledSkipBtnEle = (SkipButtonWidget) this.addDrawableChild(WidgetList.get(8));
        this.EnabledAutoBtnEle = (AutoButtonWidget) this.addDrawableChild(WidgetList.get(9));
        this.DisabledAutoBtnEle = (AutoButtonWidget) this.addDrawableChild(WidgetList.get(10));
        this.EnabledHistBtnEle = (HistButtonWidget) this.addDrawableChild(WidgetList.get(11));
        this.DisabledHistBtnEle = (HistButtonWidget) this.addDrawableChild(WidgetList.get(12));
        this.EnabledExitBtnEle = (ExitButtonWidget) this.addDrawableChild(WidgetList.get(13));
        this.DisabledExitBtnEle = (ExitButtonWidget) this.addDrawableChild(WidgetList.get(14));
    }

    private void initWidgetList() {
        WidgetList = List.of(
                getNullWidget(),//0
                getHistSideWidget(),//1
                getItemWidget(),//2
                getNameBarWidget(),//3
                getTextWidget(),//4
                getOS1Widget(),//5
                getOS2Widget(),//6
                getEnabledSkipButtonWidget(),//7
                getDisabledSkipButtonWidget(),//8
                getEnabledAutoButtonWidget(),//9
                getDisabledAutoButtonWidget(),//10
                getEnabledHistButtonWidget(),//11
                getDisabledHistButtonWidget(),//12
                getEnabledExitButtonWidget(),//13
                getDisabledExitButtonWidget()//14
        );
    }

    private void updateWidgets() {
        this.HistSideEle.visible = this.isHistSideEnabled;
        this.ItemEle.visible = this.isItemEnabled;
        this.NameBarEle.visible = this.isNameEnabled;
        this.TextEle.visible = this.isTextEnabled;
        this.OS1Ele.visible = this.isOS1Enabled;
        this.OS2Ele.visible = this.isOS2Enabled;
        this.EnabledSkipBtnEle.visible = this.isSkipEnabled;
        this.DisabledSkipBtnEle.visible = !this.isSkipEnabled;
        this.EnabledAutoBtnEle.visible = this.isAutoEnabled;
        this.DisabledAutoBtnEle.visible = !this.isAutoEnabled;
        this.EnabledHistBtnEle.visible = this.isHistEnabled;
        this.DisabledHistBtnEle.visible = !this.isHistEnabled;
        this.EnabledExitBtnEle.visible = this.isExitEnabled;
        this.DisabledExitBtnEle.visible = !this.isExitEnabled;
        this.TextEle.setMessage(TheText);
        this.HistSideEle.setMessage(new LiteralText(HistText));
    }

    protected NullWidget getNullWidget() {
        return new NullWidget(
                0,
                0,
                0,
                0,
                0,
                0,
                EMPTY,
                null,
                onPress,
                tooltipSupplier
        );
    }

    protected ItemWidget getItemWidget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int iItem = iBase + 224;
        int jItem = jBase + 40;
        return new ItemWidget(
                iItem,
                jItem,
                ItemFrame,
                ItemFrame,
                textureWidth,
                textureHeight,
                EMPTY,
                null,
                TheItem,
                onPress,
                (widget, matrices1, mouseX1, mouseY1) -> {
                    assert TheItem != null;
                    this.renderTooltip(
                            matrices1,
                            new TranslatableText(TheItem.getTranslationKey()),
                            mouseX1,
                            mouseY1
                    );
                }
        );
    }

    protected HistSideWidget getHistSideWidget() {
        int iBase = (width - HistWidth);
        int jBase = (height - HistHeight) / 6;
        return new HistSideWidget(
                iBase,
                jBase,
                HistWidth,
                HistHeight,
                140,
                236,
                new LiteralText(HistText),
                Color.WHITE,
                onPress,
                tooltipSupplier
        );
    }

    protected NameBarWidget getNameBarWidget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int iNameBar = iBase + 2;
        int jNameBar = jBase - 11;
        return new NameBarWidget(
                iNameBar,
                jNameBar,
                nameBarWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                TheName,
                TheColor,
                onPress,
                tooltipSupplier
        );
    }

    protected TextWidget getTextWidget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int iTextPanel = iBase + 7;
        int jTextPanel = jBase + 7;
        return new TextWidget(
                iTextPanel,
                jTextPanel,
                TextPanelWidth,
                TextPanelHeight,
                textureWidth,
                textureHeight,
                TheText,
                Color.WHITE,
                widget -> {
                    if (pageCount < WordList.size() - 1) {
                        pageCount++;
                        TheText = new LiteralText(WordList.getString(pageCount));
                        assert TheName != null;
                        HistText += "<" + TheName.getString() + ">" + WordList.getString(pageCount - 1) + "\n";
                        if (pageCount == WordList.size() - 1 && !hasSelected) {
                            if (this.OS1WordList != null) {
                                if (!OS1WordList.isEmpty()) {
                                    setOS1Enabled(true);
                                }
                            }
                            if (this.OS2WordList != null) {
                                if (!OS2WordList.isEmpty()) {
                                    setOS2Enabled(true);
                                }
                            }
                        }
                    }
                },
                tooltipSupplier
        );
    }

    protected OptionSelectWidget getOS1Widget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int iOptionSelect1 = iBase + 2;
        int jOptionSelect1 = jBase + 64;
        return new OptionSelectWidget(
                iOptionSelect1,
                jOptionSelect1,
                selectionButtonWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                TheOptionSelect1,
                Color.PINK,
                widget -> {
                    pageCount = 0;
                    WordList = OS1WordList;
                    TheText = new LiteralText(WordList.getString(pageCount));
                    hasSelected = true;
                    setOS1Enabled(false);
                    setOS2Enabled(false);
                },
                OS1Tooltip
        );
    }

    protected OptionSelectWidget getOS2Widget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int iOptionSelect2 = iBase + 2;
        int jOptionSelect2 = jBase + 75;
        return new OptionSelectWidget(
                iOptionSelect2,
                jOptionSelect2,
                selectionButtonWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                TheOptionSelect2,
                Color.PINK,
                widget -> {
                    pageCount = 0;
                    WordList = OS2WordList;
                    TheText = new LiteralText(WordList.getString(pageCount));
                    hasSelected = true;
                    setOS1Enabled(false);
                    setOS2Enabled(false);
                },
                OS2Tooltip
        );
    }

    protected SkipButtonWidget getEnabledSkipButtonWidget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int iIButton = iBase + 227;
        int jButton = jBase + 64;
        return new SkipButtonWidget(
                iIButton,
                jButton,
                iButtonWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                EMPTY,
                null,
                button -> {

                },
                (button, matrices1, mouseX1, mouseY1) -> this.renderTooltip(
                        matrices1,
                        new TranslatableText("text.furtelcraft.vne.skip.tooltip"),
                        mouseX1,
                        mouseY1),
                true);
    }

    protected SkipButtonWidget getDisabledSkipButtonWidget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int iIButton = iBase + 227;
        int jButton = jBase + 64;
        return new SkipButtonWidget(
                iIButton,
                jButton,
                iButtonWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                EMPTY,
                null,
                button -> {

                },
                (button, matrices1, mouseX1, mouseY1) -> this.renderTooltip(
                        matrices1,
                        new TranslatableText("text.furtelcraft.vne.skip.tooltip"),
                        mouseX1,
                        mouseY1),
                false);
    }

    protected AutoButtonWidget getEnabledAutoButtonWidget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int jButton = jBase + 64;
        int iAutoButton = iBase + 200;
        return new AutoButtonWidget(
                iAutoButton,
                jButton,
                ButtonWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                EMPTY,
                null,
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
        );
    }

    protected AutoButtonWidget getDisabledAutoButtonWidget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int jButton = jBase + 64;
        int iAutoButton = iBase + 200;
        return new AutoButtonWidget(
                iAutoButton,
                jButton,
                ButtonWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                EMPTY,
                null,
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
        );
    }

    protected HistButtonWidget getEnabledHistButtonWidget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int jButton = jBase + 64;
        int iHistButton = iBase + 175;
        return new HistButtonWidget(
                iHistButton,
                jButton,
                iButtonWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                EMPTY,
                null,
                button -> {
                    this.isHistSideEnabled = !this.isHistSideEnabled;

                },
                (button, matrices1, mouseX1, mouseY1) -> {
                    this.renderTooltip(
                            matrices1,
                            new TranslatableText("text.furtelcraft.vne.hist.tooltip"),
                            mouseX1,
                            mouseY1);
                },
                true
        );
    }

    protected HistButtonWidget getDisabledHistButtonWidget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int jButton = jBase + 64;
        int iHistButton = iBase + 175;
        return new HistButtonWidget(
                iHistButton,
                jButton,
                iButtonWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                EMPTY,
                null,
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
        );
    }

    protected ExitButtonWidget getEnabledExitButtonWidget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int jButton = jBase + 64;
        int iExitButton = iBase + 150;
        return new ExitButtonWidget(
                iExitButton,
                jButton,
                iButtonWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                EMPTY,
                null,
                button -> {
                    assert this.client != null;
                    this.client.setScreen(null);
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
        );
    }

    protected ExitButtonWidget getDisabledExitButtonWidget() {
        int iBase = (width - textureWidth) / 2;
        int jBase = (height - textureHeight - 1);
        int jButton = jBase + 64;
        int iExitButton = iBase + 150;
        return new ExitButtonWidget(
                iExitButton,
                jButton,
                iButtonWidth,
                ButtonHeight,
                textureWidth,
                textureHeight,
                EMPTY,
                null,
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
        );
    }

    @Override
    public void tick() {
        super.tick();
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

    @Deprecated
    public boolean isScollEnabled() {
        return isScollEnabled;
    }

    @Deprecated
    public void setScollEnabled(boolean scollEnabled) {
        isScollEnabled = scollEnabled;
    }

    public @Nullable Text getTheName() {
        return TheName;
    }

    public void setTheName(@Nullable Text theName) {
        this.isNameEnabled = true;
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
    public NbtList getTheTextList() {
        return WordList;
    }

    public void setTheTextList(NbtCompound nbt) {
        this.isTextEnabled = true;
        WordList = (NbtList) nbt.get("SINGLE");
        assert WordList != null;
        TheText = new LiteralText(WordList.getString(pageCount));
        if (nbt.contains("OS1")) {
            OS1WordList = (NbtList) nbt.get("OS1");
            TheOptionSelect1 = new LiteralText(nbt.getString("OS1TEXT"));
            if (WordList.size() == 1) {
                setOS1Enabled(true);
            }
        }
        if (nbt.contains("OS2")) {
            OS2WordList = (NbtList) nbt.get("OS2");
            TheOptionSelect2 = new LiteralText(nbt.getString("OS2TEXT"));
            if (WordList.size() == 1) {
                setOS2Enabled(true);
            }
        }
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

    @Nullable
    public Text getTheOptionSelect1() {
        return TheOptionSelect1;
    }

    public void setTheOptionSelect1(@Nullable Text theOptionSelect1) {
        TheOptionSelect1 = theOptionSelect1;
    }

    @Nullable
    public Text getTheOptionSelect2() {
        return TheOptionSelect2;
    }

    public void setTheOptionSelect2(@Nullable Text theOptionSelect2) {
        TheOptionSelect2 = theOptionSelect2;
    }

    public BasedWidget.PressAction getOnPress() {
        return onPress;
    }

    public void setOnPress(BasedWidget.PressAction onPress) {
        this.onPress = onPress;
    }

    public BasedWidget.TooltipSupplier getTooltipSupplier() {
        return tooltipSupplier;
    }

    public void setTooltipSupplier(BasedWidget.TooltipSupplier tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
    }

    public BasedWidget.TooltipSupplier getOS1Tooltip() {
        return OS1Tooltip;
    }

    public void setOS1Tooltip(BasedWidget.TooltipSupplier OS1Tooltip) {
        this.OS1Tooltip = OS1Tooltip;
    }

    public BasedWidget.TooltipSupplier getOS2Tooltip() {
        return OS2Tooltip;
    }

    public void setOS2Tooltip(BasedWidget.TooltipSupplier OS2Tooltip) {
        this.OS2Tooltip = OS2Tooltip;
    }

    public boolean isHistSideEnabled() {
        return isHistSideEnabled;
    }

    public void setHistSideEnabled(boolean histSideEnabled) {
        isHistSideEnabled = histSideEnabled;
    }

    public int getScreenWidth() {
        return this.ScreenWidth;
    }

    public int getScreenHeight() {
        return this.ScreenHeight;
    }
}
