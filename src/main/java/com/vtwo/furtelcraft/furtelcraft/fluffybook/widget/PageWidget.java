package com.vtwo.furtelcraft.furtelcraft.fluffybook.widget;

import com.google.common.collect.Lists;
import com.vtwo.furtelcraft.furtelcraft.libvne.widgets.BasedWidget;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.util.math.MatrixStack;

import java.util.List;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook.widget
 * @NAME: PageWidget
 * @USER: Perano
 * @DATE: 2023/1/29
 * @TIME: 13:31
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 29
 * @DAY_NAME_SHORT: 周日
 * @DAY_NAME_FULL: 星期日
 * @HOUR: 13
 * @MINUTE: 31
 * @PROJECT_NAME: furtelcraft
 */
public class PageWidget extends BasedWidget {
    protected int iPage;
    protected int jPage;
    protected int pageWidth = 114;
    protected int pageHeight = 170;
    private final List<Drawable> child = Lists.newArrayList();

    public PageWidget(int x, int y) {
        super(x, y, 114, 170);
        this.iPage = x;
        this.jPage = y;
    }

    @Override
    public void renderWidget(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        for (Drawable drawable : this.child) {
            drawable.render(matrices, mouseX, mouseY, delta);
        }
    }

    public <T extends Element & Drawable & Selectable> void addChild(T child) {
        this.child.add(child);
    }

    public void setOnPress(PressAction onPress) {
        this.onPress = onPress;
    }
}
