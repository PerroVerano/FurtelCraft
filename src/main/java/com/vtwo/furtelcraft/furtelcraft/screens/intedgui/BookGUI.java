package com.vtwo.furtelcraft.furtelcraft.screens.intedgui;

import com.vtwo.furtelcraft.furtelcraft.screens.intedwidget.WBookPageBtnWidget;
import com.vtwo.furtelcraft.furtelcraft.utils.PageUtils;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WText;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import juuxel.libninepatch.NinePatch;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class BookGUI extends LightweightGuiDescription {
    private static final boolean isInitBook = true;
    private static boolean isPriBtnRemoved = false;
    private static boolean isNextBtnRemoved = false;
    public static int leftpagination;
    public static int rightpagination;
    private WPlainPanel leftpanel;
    private WPlainPanel rightpanel;
    private static WText leftPaginationText;
    private static WText rightPaginationText;
    private static final int LEFT_PAGE_PANEL_X = 12;
    private static final int PAGE_PANEL_Y = 20;
    private static final int RIGHT_PAGE_PANEL_X = 160;
    private static final int LEFT_PAGINATION_TEXT_X = 68;
    private static final int PAGINATION_TEXT_Y = 180;
    private static final int RIGHT_PAGINATION_TEXT_X = 220;
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/book.png");

    public BookGUI() {
        WPlainPanel root = new WPlainPanel();
        root.setInsets(Insets.ROOT_PANEL);
        root.setSize(320, 174);
        if (isInitBook) {
            initPage();
        } else {
            leftpanel = getLeftPagePanel();
            rightpanel = getRightPagePanel();
        }
        root.add(leftpanel, LEFT_PAGE_PANEL_X, PAGE_PANEL_Y);
        root.add(rightpanel, RIGHT_PAGE_PANEL_X, PAGE_PANEL_Y);
        root.add(leftPaginationText, LEFT_PAGINATION_TEXT_X, PAGINATION_TEXT_Y);
        root.add(rightPaginationText, RIGHT_PAGINATION_TEXT_X, PAGINATION_TEXT_Y);


        WPlainPanel pageRoot = new WPlainPanel();
        pageRoot.setSize(282, 15);
        WBookPageBtnWidget pribtn = new WBookPageBtnWidget(WBookPageBtnWidget.PRI_BTN);
        WBookPageBtnWidget nextbtn = new WBookPageBtnWidget(WBookPageBtnWidget.NEXT_BTN);
        pageRoot.add(pribtn, 0, 3);
        pageRoot.add(nextbtn, 271, 3);
        root.add(pageRoot, 12, 180);

        pribtn.setOnClick(() -> {
            root.remove(leftpanel);
            root.remove(rightpanel);
            root.remove(leftPaginationText);
            root.remove(rightPaginationText);

            if (leftpagination > 1) {
                leftpagination -= 2;
            }
            rightpagination = leftpagination + 1;

            leftpanel = getLeftPagePanel();
            rightpanel = getRightPagePanel();
            leftPaginationText = new WText(Text.literal("-" + leftpagination + "-")).setHorizontalAlignment(HorizontalAlignment.CENTER);
            rightPaginationText = new WText(Text.literal("-" + rightpagination + "-")).setHorizontalAlignment(HorizontalAlignment.CENTER);

            root.add(leftpanel, LEFT_PAGE_PANEL_X, PAGE_PANEL_Y);
            root.add(rightpanel, RIGHT_PAGE_PANEL_X, PAGE_PANEL_Y);
            root.add(leftPaginationText, LEFT_PAGINATION_TEXT_X, PAGINATION_TEXT_Y);
            root.add(rightPaginationText, RIGHT_PAGINATION_TEXT_X, PAGINATION_TEXT_Y);

            if (leftpagination == 1) {
                pageRoot.remove(pribtn);
                isPriBtnRemoved = true;
            }
            if (isNextBtnRemoved) {
                pageRoot.add(nextbtn, 271, 3);
            }

            root.validate(this);
        });
        nextbtn.setOnClick(() -> {
            root.remove(leftpanel);
            root.remove(rightpanel);
            root.remove(leftPaginationText);
            root.remove(rightPaginationText);

            if (rightpagination < PageUtils.getPageSize()) {
                rightpagination += 2;
            }
            leftpagination = rightpagination - 1;

            leftpanel = getLeftPagePanel();
            rightpanel = getRightPagePanel();
            leftPaginationText = new WText(Text.literal("-" + leftpagination + "-")).setHorizontalAlignment(HorizontalAlignment.CENTER);
            rightPaginationText = new WText(Text.literal("-" + rightpagination + "-")).setHorizontalAlignment(HorizontalAlignment.CENTER);

            root.add(leftpanel, LEFT_PAGE_PANEL_X, PAGE_PANEL_Y);
            root.add(rightpanel, RIGHT_PAGE_PANEL_X, PAGE_PANEL_Y);
            root.add(leftPaginationText, LEFT_PAGINATION_TEXT_X, PAGINATION_TEXT_Y);
            root.add(rightPaginationText, RIGHT_PAGINATION_TEXT_X, PAGINATION_TEXT_Y);

            if (rightpagination == PageUtils.getPageSize()) {
                pageRoot.remove(nextbtn);
                isNextBtnRemoved = true;
            }
            if (isPriBtnRemoved) {
                pageRoot.add(pribtn, 0, 3);
            }

            root.validate(this);
        });

        setRootPanel(root);
    }

    @Override
    public void addPainters() {
        this.getRootPanel().setBackgroundPainter(BackgroundPainter.createNinePatch(new Texture(TEXTURE), identifierBuilder -> identifierBuilder.cornerSize(8).cornerUv(0.0F).mode(NinePatch.Mode.STRETCHING)));
    }

    private WPlainPanel getLeftPagePanel() {
        return PageUtils.getPage(leftpagination);
    }

    private WPlainPanel getRightPagePanel() {
        return PageUtils.getPage(rightpagination);
    }

    private void initPage() {
        if (isInitBook) {
            leftpagination = 1;
            rightpagination = leftpagination + 1;
        }
        leftpanel = getLeftPagePanel();
        rightpanel = getRightPagePanel();
        leftPaginationText = new WText(Text.literal("-" + leftpagination + "-")).setHorizontalAlignment(HorizontalAlignment.CENTER);
        rightPaginationText = new WText(Text.literal("-" + rightpagination + "-")).setHorizontalAlignment(HorizontalAlignment.CENTER);
    }
}
