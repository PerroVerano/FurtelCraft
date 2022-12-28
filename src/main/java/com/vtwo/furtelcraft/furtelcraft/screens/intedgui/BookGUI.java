package com.vtwo.furtelcraft.furtelcraft.screens.intedgui;

import com.vtwo.furtelcraft.furtelcraft.screens.intedwidget.WBookPageBtnWidget;
import com.vtwo.furtelcraft.furtelcraft.stories.main.ChapterOne;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import juuxel.libninepatch.NinePatch;
import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class BookGUI extends LightweightGuiDescription {
    private static final int LEFT_PAGE_PANEL_X = 26;
    private static final int PAGE_PANEL_Y = 11;
    private static final int RIGHT_PAGE_PANEL_X = 129;
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/book.png");

    public BookGUI() {
        WPlainPanel root = new WPlainPanel();
        root.setInsets(Insets.ROOT_PANEL);
        root.setSize(256, 174);

        root.add(addPageBtnPanel(), 23, 153);

        root.add(getPagePanel(1),LEFT_PAGE_PANEL_X,PAGE_PANEL_Y);
        root.add(getPagePanel(2),RIGHT_PAGE_PANEL_X,PAGE_PANEL_Y);

        setRootPanel(root);
    }

    @Override
    public void addPainters() {
        this.getRootPanel().setBackgroundPainter(BackgroundPainter.createNinePatch(new Texture(TEXTURE), identifierBuilder -> {
            identifierBuilder.cornerSize(8).cornerUv(0.0F).mode(NinePatch.Mode.STRETCHING);
        }));
    }

    private static WPlainPanel addPageBtnPanel() {
        WPlainPanel pageRoot = new WPlainPanel();
        pageRoot.setSize(204, 11);
        WBookPageBtnWidget pribtn = new WBookPageBtnWidget(WBookPageBtnWidget.PRI_BTN);
        WBookPageBtnWidget nextbtn = new WBookPageBtnWidget(WBookPageBtnWidget.NEXT_BTN);
        pageRoot.add(pribtn, 0, 0);
        pageRoot.add(nextbtn, 183, 0);
        return pageRoot;
    }

    private static WPlainPanel getPagePanel(int page) {
        return new ChapterOne().getPage(page);
    }
}
