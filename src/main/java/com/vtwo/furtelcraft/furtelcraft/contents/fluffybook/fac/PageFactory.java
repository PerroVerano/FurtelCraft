package com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.fac;

import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.imp.IPageFactory;
import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget.PageWidget;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook
 * @NAME: PageFactory
 * @USER: Perano
 * @DATE: 2023/1/30
 * @TIME: 0:13
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 30
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 00
 * @MINUTE: 13
 * @PROJECT_NAME: furtelcraft
 */
public class PageFactory implements IPageFactory {
    public PageFactory() {

    }

    public static void registry(int index, PageWidget widget) {
        pages.put(index, widget);
    }

    public static PageWidget get(int index) {
        if (index < 0 || index > pages.size()) {
            return pages.get(0);
        } else {
            return pages.get(index);
        }
    }

    public static int getSize() {
        return pages.size();
    }
}
