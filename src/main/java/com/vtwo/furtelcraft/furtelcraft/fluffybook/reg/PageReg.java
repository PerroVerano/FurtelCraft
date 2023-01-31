package com.vtwo.furtelcraft.furtelcraft.fluffybook.reg;

import com.vtwo.furtelcraft.furtelcraft.fluffybook.fac.PageFactory;
import com.vtwo.furtelcraft.furtelcraft.fluffybook.page.EmptyPage;
import com.vtwo.furtelcraft.furtelcraft.fluffybook.page.PerfacePage1;
import com.vtwo.furtelcraft.furtelcraft.fluffybook.page.PerfacePage2;
import com.vtwo.furtelcraft.furtelcraft.fluffybook.page.PerfacePage3;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook.reg
 * @NAME: PageReg
 * @USER: Perano
 * @DATE: 2023/1/30
 * @TIME: 0:17
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 30
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 00
 * @MINUTE: 17
 * @PROJECT_NAME: furtelcraft
 */
public class PageReg {
    public PageReg(int x, int y) {
        int rightX = x + 133;
        int rightY = y + 2;
        PageFactory.registry(0, new EmptyPage(x, y));
        PageFactory.registry(1, new PerfacePage1(x, y));
        PageFactory.registry(2, new PerfacePage2(rightX, rightY));
        PageFactory.registry(3, new PerfacePage3(x, y));
        PageFactory.registry(4, new EmptyPage(rightX, rightY));
    }
}
