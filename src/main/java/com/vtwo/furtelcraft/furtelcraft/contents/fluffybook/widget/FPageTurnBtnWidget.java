package com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget;

import com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets.BasedWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook.widget
 * @NAME: FPageTurnBtnWidget
 * @USER: Perano
 * @DATE: 2023/1/30
 * @TIME: 17:03
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 30
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 17
 * @MINUTE: 03
 * @PROJECT_NAME: furtelcraft
 */
public class FPageTurnBtnWidget extends BasedWidget {
    private final int iBtn;
    private final int jBtn;
    private final int Btn;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private final boolean isLeft;
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/book_btn.png");

    public FPageTurnBtnWidget(int x, int y, @Nullable BasedWidget.PressAction onPress, @Nullable BasedWidget.TooltipSupplier tooltipSupplier, boolean isLeft) {
        super(x, y, 11, 11, onPress, tooltipSupplier);
        this.iBtn = x;
        this.jBtn = y;
        this.Btn = 11;
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
        this.isLeft = isLeft;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.setRenderSystem(TEXTURE);
        int pos = getPos(this.isHovered());
        if (this.isLeft) {
            drawTexture(matrices, this.iBtn, this.jBtn, 0, pos * 11, this.Btn, this.Btn);
        } else {
            drawTexture(matrices, this.iBtn, this.jBtn, 0, 22 + (pos * 11), this.Btn, this.Btn);
        }
    }

    private int getPos(boolean isHovered) {
        int state = 0;
        if (isHovered) {
            state = 1;
        }
        return state;
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.master(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F));
    }
}
