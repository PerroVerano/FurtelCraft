//package com.vtwo.furtelcraft.furtelcraft.screens.intedwidget;
//
//import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
//import io.github.cottonmc.cotton.gui.widget.WWidget;
//import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
//import io.github.cottonmc.cotton.gui.widget.data.InputResult;
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.sound.PositionedSoundInstance;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.sound.SoundEvents;
//import net.minecraft.util.Identifier;
//import org.jetbrains.annotations.Nullable;
//
//import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;
//
///**
// * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.screens.intedwidget
// * @NAME: BookPagePriBtnWidget
// * @USER: Perano
// * @DATE: 2022/12/28
// * @TIME: 18:28
// * @YEAR: 2022
// * @MONTH: 12
// * @MONTH_NAME_SHORT: 12月
// * @MONTH_NAME_FULL: 十二月
// * @DAY: 28
// * @DAY_NAME_SHORT: 周三
// * @DAY_NAME_FULL: 星期三
// * @HOUR: 18
// * @MINUTE: 28
// * @PROJECT_NAME: furtelcraft
// */
//public class WBookPageBtnWidget extends WWidget {
//    public static final int PRI_BTN = 1024;
//    public static final int NEXT_BTN = 1025;
//    public static final int NULL_BTN = 1026;
//    private final int model;
//    private boolean enabled = true;
//    private @Nullable Runnable onClick;
//
//    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/book_btn.png");
//    protected HorizontalAlignment alignment;
//
//    public WBookPageBtnWidget(int model) {
//        this.alignment = HorizontalAlignment.CENTER;
//        this.model = model;
//    }
//
//    @Override
//    public boolean canResize() {
//        return true;
//    }
//
//    @Override
//    public boolean canFocus() {
//        return true;
//    }
//
//    @Environment(EnvType.CLIENT)
//    @Override
//    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
//        boolean hovered = mouseX >= 0 && mouseY >= 0 && mouseX < this.getWidth() && mouseY < this.getHeight();
//        int state = 0;
//        if (!this.enabled) {
//            state = 8;
//        } else if (hovered || this.isFocused()) {
//            state = 1;
//        }
//        int btnmodel = 0;
//        if (this.model == NEXT_BTN) {
//            btnmodel = 22;
//        }
//
//        float px = 0.00390625F;
//        float buttonLeft = 0.0F * px;
//        float buttonTop;
//        if (this.model == NEXT_BTN || this.model == NULL_BTN) {
//            buttonTop = (float) (btnmodel + state * 11) * px;
//        } else {
//            buttonTop = (float) (state * 11) * px;
//        }
//        float buttonWidth = 11.0F * px;
//        float buttonHeight = 11.0F * px;
//
//        ScreenDrawing.texturedRect(matrices, x, y, 11, 11, TEXTURE, buttonLeft, buttonTop, buttonLeft + buttonWidth, buttonTop + buttonHeight, -1);
//    }
//
//    @Override
//    public void setSize(int x, int y) {
//        super.setSize(11, 11);
//    }
//
//    @Environment(EnvType.CLIENT)
//    @Override
//    public InputResult onClick(int x, int y, int button) {
//        if (this.enabled && this.isWithinBounds(x, y)) {
//            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F));
//            if (this.onClick != null) {
//                this.onClick.run();
//            }
//
//            return InputResult.PROCESSED;
//        } else {
//            return InputResult.IGNORED;
//        }
//    }
//
//    @Override
//    public void onKeyPressed(int ch, int key, int modifiers) {
//        if (isActivationKey(ch)) {
//            this.onClick(0, 0, 0);
//        }
//    }
//
//    public @Nullable Runnable getOnClick() {
//        return this.onClick;
//    }
//
//    public void setOnClick(@Nullable Runnable onClick) {
//        this.onClick = onClick;
//    }
//
//    public boolean isEnabled() {
//        return this.enabled;
//    }
//
//    public WBookPageBtnWidget setEnabled(boolean enabled) {
//        this.enabled = enabled;
//        return this;
//    }
//
//    public HorizontalAlignment getAlignment() {
//        return this.alignment;
//    }
//
//    public WBookPageBtnWidget setAlignment(HorizontalAlignment alignment) {
//        this.alignment = alignment;
//        return this;
//    }
//}
