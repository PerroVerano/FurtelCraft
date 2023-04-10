package com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget
 * @NAME: FGridPageWidget
 * @USER: Perano
 * @DATE: 2023/4/3
 * @TIME: 11:09
 * @YEAR: 2023
 * @MONTH: 04
 * @MONTH_NAME_SHORT: 4月
 * @MONTH_NAME_FULL: 四月
 * @DAY: 03
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 11
 * @MINUTE: 09
 * @PROJECT_NAME: furtelcraft
 */
public class FGridPageWidget extends PageWidget {
    protected int gridWidth = this.pageWidth / 11;
    protected int gridHeight = this.pageHeight / 17;

    public FGridPageWidget(int x, int y) {
        super(x, y);
    }
}
