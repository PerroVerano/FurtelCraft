package com.vtwo.furtelcraft.furtelcraft.fluffybook.page;

import com.vtwo.furtelcraft.furtelcraft.fluffybook.widget.FTextWidget;
import com.vtwo.furtelcraft.furtelcraft.fluffybook.widget.PageWidget;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;

import java.awt.*;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook.page
 * @NAME: PerfacePage2
 * @USER: Perano
 * @DATE: 2023/1/30
 * @TIME: 14:19
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 30
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 14
 * @MINUTE: 19
 * @PROJECT_NAME: furtelcraft
 */
public class PerfacePage2 extends PageWidget {
    private final int iPage;
    private final int jPage;
    private FTextWidget arthurEle;
    private FTextWidget versionEle;
    private FTextWidget tipEle;

    public PerfacePage2(int x, int y) {
        super(x, y);
        this.iPage = x;
        this.jPage = y;
        this.initWidget();
        this.addWidget();
    }

    private void addWidget() {
        this.addChild(this.arthurEle);
        this.addChild(this.versionEle);
        this.addChild(this.tipEle);
    }

    private void initWidget() {
        this.arthurEle = new FTextWidget(iPage + 1, jPage + 10 + ((this.pageHeight / 10) * 3), 114, 9,
                new TranslatableText("text.furtelcraft.author").setStyle(Style.EMPTY.withBold(true)), Color.GRAY, true, false,
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.versionEle = new FTextWidget(iPage + 1, jPage + 10 + ((this.pageHeight / 10) * 4) - 3, 114, 9,
                new TranslatableText("text.furtelcraft.version"), Color.LIGHT_GRAY, true, false,
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.tipEle = new FTextWidget(iPage - 1, jPage + 10 + ((this.pageHeight / 10) * 5) - 5, 116, 9,
                new TranslatableText("text.furtelcraft.tip"), Color.BLACK, true, false,
                (widget, matrices, mouseX, mouseY) -> {
                });
    }
}
