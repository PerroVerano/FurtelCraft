package com.vtwo.furtelcraft.furtelcraft.stories.main;

import com.vtwo.furtelcraft.furtelcraft.utils.PageUtils;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.WText;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;
import static com.vtwo.furtelcraft.furtelcraft.utils.PageUtils.PAGE_SIZE_X;
import static com.vtwo.furtelcraft.furtelcraft.utils.PageUtils.PAGE_SIZE_Y;

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
    private static final Identifier PAGE3 = new Identifier(MOD_ID, "textures/screen/book_ele/chapter1_page3.png");
    private static final Identifier PAGE4 = new Identifier(MOD_ID, "textures/screen/book_ele/chapter1_page4.png");

    public static WGridPanel Page1() {
        WGridPanel root = PageUtils.getDefaultGridPanel();
        root.add(new WText(Text.translatable("text.furtelcraft.mainplot.chapter1").setStyle(Style.EMPTY.withBold(true))), 10, -1, 5, 1);
        root.add(new WText(Text.translatable("text.furtelcraft.story.begin")
                .setStyle(Style.EMPTY.withBold(true)
                        .withColor(Formatting.GRAY)))
                .setHorizontalAlignment(HorizontalAlignment.CENTER), 0, 0, 14, 1);
        root.add(new WText(Text.translatable("text.furtelcraft.mainplot.chapter1.page1")), 0, 1, 14, 10);
        return root;
    }

    public static WGridPanel Page2() {
        WGridPanel root = PageUtils.getDefaultGridPanel();
        root.add(new WText(Text.translatable("text.furtelcraft.mainplot.chapter1.page2").setStyle(Style.EMPTY.withBold(true)))
                .setHorizontalAlignment(HorizontalAlignment.CENTER), 0, 7, 14, 4);
        return root;
    }

    public static WPlainPanel Page3() {
        WPlainPanel root = PageUtils.getDefaultPanel();
        root.add(new WSprite(PAGE3), 0, 0, PAGE_SIZE_X, PAGE_SIZE_Y);
        return root;
    }

    public static WGridPanel Page4() {
        WGridPanel root = PageUtils.getDefaultGridPanel();
        root.add(new WText(Text.translatable("text.furtelcraft.mainplot.chapter1.page4.1")), 0, 0, 14, 10);
        root.add(new WSprite(PAGE4), 0, 10, 13, 3);
        root.add(new WText(Text.translatable("text.furtelcraft.mainplot.chapter1.page4.2")), 0, 14, 14, 1);
        return root;
    }

    public static WGridPanel Page5() {
        WGridPanel root = PageUtils.getDefaultGridPanel();
        return root;
    }
}
