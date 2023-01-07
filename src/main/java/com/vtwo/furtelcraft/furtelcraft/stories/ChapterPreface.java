package com.vtwo.furtelcraft.furtelcraft.stories;

import com.vtwo.furtelcraft.furtelcraft.utils.PageUtils;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.WText;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;
import static com.vtwo.furtelcraft.furtelcraft.utils.PageUtils.PAGE_SIZE_X;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.stories
 * @NAME: ChapterPreface
 * @USER: Perano
 * @DATE: 2022/12/29
 * @TIME: 13:31
 * @YEAR: 2022
 * @MONTH: 12
 * @MONTH_NAME_SHORT: 12月
 * @MONTH_NAME_FULL: 十二月
 * @DAY: 29
 * @DAY_NAME_SHORT: 周四
 * @DAY_NAME_FULL: 星期四
 * @HOUR: 13
 * @MINUTE: 31
 * @PROJECT_NAME: furtelcraft
 */
public class ChapterPreface {
    private static final Identifier FOOTPRINT = new Identifier(MOD_ID, "textures/screen/book_ele/preface_footprint.png");

    public static WPlainPanel Page1() {
        WPlainPanel root = PageUtils.getDefaultPanel();

        WSprite footprint = new WSprite(FOOTPRINT);
        root.add(footprint, 22, 30, 90, 90);

        WText oneword = new WText(OneWord.getRandomOneWord().formatted(Formatting.YELLOW));
        oneword.setHorizontalAlignment(HorizontalAlignment.CENTER);
        root.add(oneword, 0, 138, PAGE_SIZE_X, 16);
        return root;
    }

    public static WPlainPanel Page2() {
        WPlainPanel root = PageUtils.getDefaultPanel();

        WText author = new WText(new TranslatableText("text.furtelcraft.author").setStyle(Style.EMPTY.withBold(true)));
        WText version = new WText(new TranslatableText("text.furtelcraft.version").formatted(Formatting.GRAY));
        WText tip = new WText(new TranslatableText("text.furtelcraft.tip").setStyle(Style.EMPTY.withBold(true).withColor(Formatting.BLUE)));
        author.setHorizontalAlignment(HorizontalAlignment.CENTER);
        version.setHorizontalAlignment(HorizontalAlignment.CENTER);
        tip.setHorizontalAlignment(HorizontalAlignment.CENTER);
        root.add(author, 0, 52, PAGE_SIZE_X, 16);
        root.add(version, 0, 66, PAGE_SIZE_X, 16);
        root.add(tip, 0, 78, PAGE_SIZE_X, 16);

        return root;
    }

    public static WWidget Page3() {
        return ProgressManager.getStagedContentPanel();
    }
}
