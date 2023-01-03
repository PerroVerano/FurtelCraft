package com.vtwo.furtelcraft.furtelcraft.stories;

import com.vtwo.furtelcraft.furtelcraft.screens.intedwidget.WCustomText;
import com.vtwo.furtelcraft.furtelcraft.utils.PageUtils;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WText;
import io.github.cottonmc.cotton.gui.widget.data.Color;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.stories
 * @NAME: ProgressManager
 * @USER: Perano
 * @DATE: 2023/1/2
 * @TIME: 14:40
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 02
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 14
 * @MINUTE: 40
 * @PROJECT_NAME: furtelcraft
 */
public class ProgressManager {
    public static int stage;

    private static final WCustomText STORY_MODE;
    private static final WCustomText MAIN_PLOT;
    private static final WCustomText SIDING_PLOT;
    private static final WCustomText CHARACTER_PLOT;
    private static final WCustomText WORLD_PLOT;

    private static final WCustomText GUIDE_MODE;
    private static final WCustomText MAKE_DNA;
    private static final WCustomText MAKE_FURRIES_MOB;
    private static final WCustomText CULTIVATE_FEELING;
    private static final WCustomText TRANSFUR;
    private static final WCustomText MULTIBLOCK_STRUCTURE;

    static {
        STORY_MODE = new WCustomText(Text.translatable("text.furtelcraft.content.storymode").setStyle(Style.EMPTY.withBold(true))).setHorizontalAlignment(HorizontalAlignment.CENTER);
        MAIN_PLOT = new WCustomText(Text.translatable("text.furtelcraft.content.storymode.mainplot"));
        SIDING_PLOT = new WCustomText(Text.translatable("text.furtelcraft.content.storymode.sidingplot"));
        CHARACTER_PLOT = new WCustomText(Text.translatable("text.furtelcraft.content.storymode.characterplot"));
        WORLD_PLOT = new WCustomText(Text.translatable("text.furtelcraft.content.storymode.worldplot"));

        GUIDE_MODE = new WCustomText(Text.translatable("text.furtelcraft.content.guidemode").setStyle(Style.EMPTY.withBold(true))).setHorizontalAlignment(HorizontalAlignment.CENTER);
        MAKE_DNA = new WCustomText(Text.translatable("text.furtelcraft.content.guidemode.makedna"));
        MAKE_FURRIES_MOB = new WCustomText(Text.translatable("text.furtelcraft.content.guidemode.makefurriesmob"));
        CULTIVATE_FEELING = new WCustomText(Text.translatable("text.furtelcraft.content.guidemode.cultivatefeeling"));
        TRANSFUR = new WCustomText(Text.translatable("text.furtelcraft.content.guidemode.transfur"));
        MULTIBLOCK_STRUCTURE = new WCustomText(Text.translatable("text.furtelcraft.content.guidemode.multiblockstructure"));

    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        ProgressManager.stage = stage;
    }

    public static WGridPanel getStagedContentPanel() {
        WGridPanel root = PageUtils.getDefaultGridPanel();

        WText content = new WText(Text.translatable("text.furtelcraft.content").setStyle(Style.EMPTY.withBold(true)));
        content.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        root.add(content, 11, -1, 2, 1);

        switch (stage) {
            case 0 -> Stage0();
            default -> Stage0();
        }

        root.add(STORY_MODE, 0, 1, 14, 1);
        root.add(MAIN_PLOT, 0, 2, 14, 1);
        root.add(SIDING_PLOT, 0, 3, 14, 1);
        root.add(CHARACTER_PLOT, 0, 4, 14, 1);
        root.add(WORLD_PLOT, 0, 5, 14, 1);
        root.add(GUIDE_MODE, 0, 6, 14, 1);
        root.add(MAKE_DNA, 0, 7, 14, 1);
        root.add(MAKE_FURRIES_MOB, 0, 8, 14, 1);
        root.add(CULTIVATE_FEELING, 0, 9, 14, 1);
        root.add(TRANSFUR, 0, 10, 14, 1);
        root.add(MULTIBLOCK_STRUCTURE, 0, 11, 14, 1);

        return root;
    }

    public static WGridPanel getFullStageContentPanel() {
        WGridPanel root = PageUtils.getDefaultGridPanel();

        WText content = new WText(Text.translatable("text.furtelcraft.content").setStyle(Style.EMPTY.withBold(true)));
        content.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        root.add(content, 11, -1, 2, 1);

        root.add(STORY_MODE, 0, 1, 14, 1);
        root.add(MAIN_PLOT, 0, 2, 14, 1);
        root.add(SIDING_PLOT, 0, 3, 14, 1);
        root.add(CHARACTER_PLOT, 0, 4, 14, 1);
        root.add(WORLD_PLOT, 0, 5, 14, 1);
        root.add(GUIDE_MODE, 0, 6, 14, 1);
        root.add(MAKE_DNA, 0, 7, 14, 1);
        root.add(MAKE_FURRIES_MOB, 0, 8, 14, 1);
        root.add(CULTIVATE_FEELING, 0, 9, 14, 1);
        root.add(TRANSFUR, 0, 10, 14, 1);
        root.add(MULTIBLOCK_STRUCTURE, 0, 11, 14, 1);

        return root;
    }

    private static void Stage0() {
        MAIN_PLOT.setColor(Color.GRAY_DYE.toRgb());
        SIDING_PLOT.setColor(Color.GRAY_DYE.toRgb());
        CHARACTER_PLOT.setColor(Color.GRAY_DYE.toRgb());
        WORLD_PLOT.setColor(Color.GRAY_DYE.toRgb());
    }
}
