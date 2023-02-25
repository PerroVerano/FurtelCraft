package com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.page;

import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget.FTextWidget;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget.PageWidget;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;

import java.awt.*;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook.page
 * @NAME: PerfacePage3
 * @USER: Perano
 * @DATE: 2023/1/30
 * @TIME: 16:19
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 30
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 16
 * @MINUTE: 19
 * @PROJECT_NAME: furtelcraft
 */
public class PerfacePage3 extends PageWidget {
    private final int iPage;
    private final int jPage;
    private FTextWidget titleEle;
    private FTextWidget plotEle;
    private FTextWidget mainPlotEle;
    private FTextWidget sidingPlotEle;
    private FTextWidget characterPlotEle;
    private FTextWidget worldPlotEle;
    private FTextWidget guideModeEle;
    private FTextWidget mode1Ele;
    private FTextWidget mode2Ele;
    private FTextWidget mode3Ele;
    private FTextWidget mode4Ele;
    private FTextWidget mode5Ele;

    public PerfacePage3(int x, int y) {
        super(x, y);
        this.iPage = x;
        this.jPage = y;
        this.initWidget();
        this.addWidget();
    }

    private void addWidget() {
        this.addChild(this.titleEle);
        this.addChild(this.plotEle);
        this.addChild(this.mainPlotEle);
        this.addChild(this.sidingPlotEle);
        this.addChild(this.characterPlotEle);
        this.addChild(this.worldPlotEle);
        this.addChild(this.guideModeEle);
        this.addChild(this.mode1Ele);
        this.addChild(this.mode2Ele);
        this.addChild(this.mode3Ele);
        this.addChild(this.mode4Ele);
        this.addChild(this.mode5Ele);
    }

    private void initWidget() {
        this.titleEle = new FTextWidget(iPage + 96, jPage + 6, 20, 9,
                new TranslatableText("text.furtelcraft.content").setStyle(Style.EMPTY.withBold(true)), Color.DARK_GRAY, true, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.plotEle = new FTextWidget(iPage + 10, jPage + 13 + ((this.pageHeight / 17)), 114, 9,
                new TranslatableText("text.furtelcraft.content.storymode").setStyle(Style.EMPTY.withBold(true)), Color.DARK_GRAY, true, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.mainPlotEle = new FTextWidget(iPage + 10, jPage + 13 + ((this.pageHeight / 17) * 2), 40, 9,
                new TranslatableText("text.furtelcraft.content.storymode.mainplot"), Color.GRAY, false, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.sidingPlotEle = new FTextWidget(iPage + 10, jPage + 13 + ((this.pageHeight / 17) * 3), 40, 9,
                new TranslatableText("text.furtelcraft.content.storymode.sidingplot"), Color.GRAY, false, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.characterPlotEle = new FTextWidget(iPage + 10, jPage + 13 + ((this.pageHeight / 17) * 4), 40, 9,
                new TranslatableText("text.furtelcraft.content.storymode.characterplot"), Color.GRAY, false, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.worldPlotEle = new FTextWidget(iPage + 10, jPage + 13 + ((this.pageHeight / 17) * 5), 40, 9,
                new TranslatableText("text.furtelcraft.content.storymode.worldplot"), Color.GRAY, false, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.guideModeEle = new FTextWidget(iPage + 10, jPage + 13 + ((this.pageHeight / 17) * 6), 114, 9,
                new TranslatableText("text.furtelcraft.content.guidemode").setStyle(Style.EMPTY.withBold(true)), Color.DARK_GRAY, true, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.mode1Ele = new FTextWidget(iPage + 10, jPage + 13 + ((this.pageHeight / 17) * 7), 114, 9,
                new TranslatableText("text.furtelcraft.content.guidemode.makedna"), Color.GRAY, false, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.mode2Ele = new FTextWidget(iPage + 10, jPage + 13 + ((this.pageHeight / 17) * 8), 114, 9,
                new TranslatableText("text.furtelcraft.content.guidemode.makefurriesmob"), Color.GRAY, false, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.mode3Ele = new FTextWidget(iPage + 10, jPage + 13 + ((this.pageHeight / 17) * 9), 114, 9,
                new TranslatableText("text.furtelcraft.content.guidemode.cultivatefeeling"), Color.GRAY, false, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.mode4Ele = new FTextWidget(iPage + 10, jPage + 13 + ((this.pageHeight / 17) * 10), 114, 9,
                new TranslatableText("text.furtelcraft.content.guidemode.transfur"), Color.GRAY, false, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
        this.mode5Ele = new FTextWidget(iPage + 10, jPage + 13 + ((this.pageHeight / 17) * 11), 114, 9,
                new TranslatableText("text.furtelcraft.content.guidemode.multiblockstructure"), Color.GRAY, false, false, widget -> {
        },
                (widget, matrices, mouseX, mouseY) -> {
                });
    }
}
