package com.vtwo.furtelcraft.furtelcraft.contents.fluffybook;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.fac.PageFactory;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.reg.OneWordReg;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.reg.PageReg;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget.FEmptyWidget;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget.FPageTurnBtnWidget;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget.FTextWidget;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget.PageWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook
 * @NAME: GuideBookScreen
 * @USER: Perano
 * @DATE: 2023/1/29
 * @TIME: 13:23
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 29
 * @DAY_NAME_SHORT: 周日
 * @DAY_NAME_FULL: 星期日
 * @HOUR: 13
 * @MINUTE: 23
 * @PROJECT_NAME: furtelcraft
 */
public class GuideBookScreen extends Screen {
    private FEmptyWidget safeProtect;
    private PageWidget leftPage;
    private PageWidget rightPage;
    private FTextWidget leftpageinaEle;
    private FTextWidget rightpageinaEle;
    private FPageTurnBtnWidget leftTurnEle;
    private FPageTurnBtnWidget rightTurnEle;
    private boolean isEnabledLeftTurn = false;
    private boolean isEnabledRightTurn = true;
    private int leftPageina = 1;
    private int rightPageina = 2;
    private final int backgroundWidth = 256;
    private final int backgroundHeight = 174;
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/book.png");

    public GuideBookScreen(Text title) {
        super(title);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        int iBase = (width - backgroundWidth) / 2;
        int jBase = (height - backgroundHeight) / 2;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, iBase, jBase, 0, 0, backgroundWidth, backgroundHeight);
        this.updateWidgets();
        super.render(matrices, mouseX, mouseY, delta);
    }

    private void updateWidgets() {
        this.isEnabledLeftTurn = leftPageina > 1;
        this.isEnabledRightTurn = rightPageina < PageFactory.getSize() - 1;

        this.leftTurnEle.visible = this.isEnabledLeftTurn;
        this.rightTurnEle.visible = this.isEnabledRightTurn;
    }

    @Override
    protected void init() {
        super.init();
        this.initBook();
        this.initWidgets();
        this.addWidgets();
    }

    private void initBook() {
        new OneWordReg();
        new PageReg((width - backgroundWidth) / 2, (height - backgroundHeight) / 2);
    }

    private void addWidgets() {
        this.addDrawableChild(this.safeProtect);
        this.addDrawableChild(this.leftPage);
        this.addDrawableChild(this.rightPage);
        this.addDrawableChild(this.leftpageinaEle);
        this.addDrawableChild(this.rightpageinaEle);
        this.addDrawableChild(this.leftTurnEle);
        this.addDrawableChild(this.rightTurnEle);
    }

    private void initWidgets() {
        int iBase = (width - backgroundWidth) / 2;
        int jBase = (height - backgroundHeight) / 2;
        this.leftPage = PageFactory.get(leftPageina);
        this.rightPage = PageFactory.get(rightPageina);
        this.leftpageinaEle = new FTextWidget(iBase + 10, jBase + 163, 114, 10,
                new LiteralText(String.valueOf(leftPageina)), Color.BLACK, true, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.rightpageinaEle = new FTextWidget(iBase + 133, jBase + 163, 114, 10,
                new LiteralText(String.valueOf(rightPageina)), Color.BLACK, true, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.leftTurnEle = new FPageTurnBtnWidget(iBase, jBase + 175,
                widget -> {
                    if (leftPageina > 0 && rightPageina < PageFactory.getSize()) {
                        leftPageina -= 2;
                        rightPageina -= 2;
                        this.leftPage = PageFactory.get(leftPageina);
                        this.leftPage = PageFactory.get(rightPageina);
                        this.leftpageinaEle.setMessage(new LiteralText(String.valueOf(leftPageina)));
                        this.rightpageinaEle.setMessage(new LiteralText(String.valueOf(rightPageina)));
                        this.clearChildren();
                        this.addDrawableChild(this.safeProtect);
                        this.init();
                    }
                },
                (widget, matrices, mouseX, mouseY) -> {
                },
                true);
        this.rightTurnEle = new FPageTurnBtnWidget(iBase + 245, jBase + 175,
                widget -> {
                    if (leftPageina > 0 && rightPageina < PageFactory.getSize()) {
                        leftPageina += 2;
                        rightPageina += 2;
                        this.leftPage = PageFactory.get(leftPageina);
                        this.leftPage = PageFactory.get(rightPageina);
                        this.leftpageinaEle.setMessage(new LiteralText(String.valueOf(leftPageina)));
                        this.rightpageinaEle.setMessage(new LiteralText(String.valueOf(rightPageina)));
                        this.clearChildren();
                        this.addDrawableChild(this.safeProtect);
                        this.init();
                    }
                },
                (widget, matrices, mouseX, mouseY) -> {
                },
                false);
        this.safeProtect = new FEmptyWidget(iBase, jBase);
    }
}
