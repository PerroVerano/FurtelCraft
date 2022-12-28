package com.vtwo.furtelcraft.furtelcraft.stories.main;

import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WText;
import net.minecraft.text.LiteralText;

import static com.vtwo.furtelcraft.furtelcraft.utils.PageManager.PAGE_SIZE_X;
import static com.vtwo.furtelcraft.furtelcraft.utils.PageManager.PAGE_SIZE_Y;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.stories.main
 * @NAME: ChapterOne
 * @USER: Perano
 * @DATE: 2022/12/28
 * @TIME: 20:27
 * @YEAR: 2022
 * @MONTH: 12
 * @MONTH_NAME_SHORT: 12月
 * @MONTH_NAME_FULL: 十二月
 * @DAY: 28
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 20
 * @MINUTE: 27
 * @PROJECT_NAME: furtelcraft
 */
public class ChapterOne {
    private int page;
    public WPlainPanel getPage(int page) {
        this.page = page;
        return PagePanel();
    }

    private WPlainPanel getDefaultPanel() {
        WPlainPanel panel = new WPlainPanel();
        panel.setSize(PAGE_SIZE_X,PAGE_SIZE_Y);
        return panel;
    }

    private WText getText() {
        return switch (this.page) {
            case 1 -> Page1();
            case 2 -> Page2();
            default -> throw new IllegalStateException("Unexpected value: " + this.page);
        };
    }

    private WPlainPanel PagePanel() {
        WPlainPanel panel = getDefaultPanel();
        panel.add(getText(),0,0);
        return panel;
    }

    private WText Page1() {
        String story =
                "dbajsdbsabdjksabjkdbasjkdbsajbdjkjasbdjkjsadb" +
                        "dbsabdkasbdjskabdjkasbjdkbasjkdbaksjdbakjsdb" +
                        "asbjkdbsakdbsakdbahkjbdjkasbdjkasbdkjasjbjdkasbd" +
                        "asbjdbaskdbsahjjdbsakbdaskjbdjksabdjabdkajsdbkas" +
                        "abshjdbsahjdbashbdsadbahsksdsakhhkdas" +
                        "bashjdbsahjdbaskdbaskhhdbsahkhbdaskbdaskdbask";
        LiteralText text = new LiteralText(story);
        return new WText(text);
    }

    private WText Page2() {
        String story =
                "dbajsdbsabdjksabjkdbasjkdbsajbdjkjasbdjkjsadb" +
                        "dbsabdkasbdjskabdjkasbjdkbasjkdbaksjdbakjsdb" +
                        "asbjkdbsakdbsakdbahkjbdjkasbdjkasbdkjasjbjdkasbd" +
                        "asbjdbaskdbsahjjdbsakbdaskjbdjksabdjabdkajsdbkas" +
                        "abshjdbsahjdbashbdsadbahsksdsakhhkdas" +
                        "bashjdbsahjdbaskdbaskhhdbsahkhbdaskbdaskdbask";
        LiteralText text = new LiteralText(story);
        return new WText(text);
    }

}
