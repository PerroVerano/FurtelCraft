package com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.page;

import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.fac.OneWordFactory;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget.FImageWidget;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget.FTextWidget;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget.PageWidget;
import net.minecraft.util.Identifier;

import java.awt.*;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook.page
 * @NAME: PerfacePage1
 * @USER: Perano
 * @DATE: 2023/1/29
 * @TIME: 14:02
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 29
 * @DAY_NAME_SHORT: 周日
 * @DAY_NAME_FULL: 星期日
 * @HOUR: 14
 * @MINUTE: 02
 * @PROJECT_NAME: furtelcraft
 */
public class PerfacePage1 extends PageWidget {
    private final int iPage;
    private final int jPage;
    private FImageWidget footprintEle;
    private FTextWidget onewordEle;

    public PerfacePage1(int x, int y) {
        super(x, y);
        this.iPage = x;
        this.jPage = y;
        this.initWidget();
        this.addWidget();
    }

    private void initWidget() {
        this.footprintEle = new FImageWidget(
                iPage + ((this.pageWidth - 24) / 4),
                jPage + (this.pageHeight / 4),
                90,
                90,
                new Identifier(MOD_ID, "textures/screen/book_ele/preface_footprint.png"));
        this.onewordEle = new FTextWidget(
                iPage + 10,
                jPage + (this.pageHeight / 17) * 14,
                112,
                26,
                OneWordFactory.getRandom(),
                Color.YELLOW,
                true,
                true,
                (widget, matrices, mouseX, mouseY) -> {
                }
        );
    }

    private void addWidget() {
        this.addChild(this.footprintEle);
        this.addChild(this.onewordEle);
    }

    @Override
    public void tick() {

    }
}
