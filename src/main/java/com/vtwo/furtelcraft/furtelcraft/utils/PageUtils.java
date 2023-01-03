package com.vtwo.furtelcraft.furtelcraft.utils;

import com.vtwo.furtelcraft.furtelcraft.stories.ChapterPreface;
import com.vtwo.furtelcraft.furtelcraft.stories.main.ChapterOne;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WText;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.minecraft.text.LiteralText;

import java.util.List;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.utils
 * @NAME: PageManager
 * @USER: Perano
 * @DATE: 2022/12/28
 * @TIME: 20:44
 * @YEAR: 2022
 * @MONTH: 12
 * @MONTH_NAME_SHORT: 12月
 * @MONTH_NAME_FULL: 十二月
 * @DAY: 28
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 20
 * @MINUTE: 44
 * @PROJECT_NAME: furtelcraft
 */
public class PageUtils {
    //一页260字
    public static final int PAGE_SIZE_X = 136;
    public static final int PAGE_SIZE_Y = 144;
    private static final List<WWidget> PageList;

    static {
        PageList = List.of(
                ChapterPreface.Page1(),
                ChapterPreface.Page2(),
                ChapterPreface.Page3(),
                ChapterOne.Page1(),
                ChapterOne.Page2(),
                ChapterOne.Page3(),
                ChapterOne.Page4()
        );
    }

    public static WPlainPanel getDefaultPanel() {
        WPlainPanel panel = new WPlainPanel();
        panel.setSize(PAGE_SIZE_X, PAGE_SIZE_Y);
        return panel;
    }

    public static WGridPanel getDefaultGridPanel() {
        WGridPanel panel = new WGridPanel(10);
        panel.setSize(PAGE_SIZE_X, PAGE_SIZE_Y);
        return panel;
    }

    public static boolean isOddNumber(int num) {
        return (num & 1) != 0;
    }

    public static WPlainPanel getPage(int page) {
        WPlainPanel panel = PageUtils.getDefaultPanel();
        panel.add(getText(page), 0, 0, PAGE_SIZE_X, PAGE_SIZE_Y);
        return panel;
    }

    private static WWidget getText(int page) {
        if (page > getPageSize() - 1 || page < 0) {
            return DefaultPage();
        } else {
            return PageList.get(page);
        }
    }

    public static int getPageSize() {
        return PageList.size();
    }

    public static WText DefaultPage() {
        return new WText(new LiteralText(" "));
    }
}
