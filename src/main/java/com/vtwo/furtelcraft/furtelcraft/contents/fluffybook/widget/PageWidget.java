package com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.widget;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.ParentElement;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;

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
public class PageWidget implements Drawable, ParentElement, Selectable {
    protected int iPage;
    protected int jPage;
    protected int pageWidth = 114;
    protected int pageHeight = 170;
    private final List<Drawable> drawables = Lists.newArrayList();
    private final List<Element> elements = Lists.newArrayList();
    private final List<Selectable> selectables = Lists.newArrayList();

    public PageWidget(int x, int y) {
        this.iPage = x;
        this.jPage = y;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        for (Drawable drawable : this.drawables) {
            drawable.render(matrices, mouseX, mouseY, delta);
        }
    }

    public <T extends Element & Drawable & Selectable> void addChild(T child) {
        this.drawables.add(child);
        this.elements.add(child);
        this.selectables.add(child);
    }

    public void clearChild() {
        this.drawables.clear();
        this.elements.clear();
        this.selectables.clear();
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {

    }

    @Override
    public List<? extends Element> children() {
        return this.elements;
    }

    @Override
    public boolean isDragging() {
        return false;
    }

    @Override
    public void setDragging(boolean dragging) {

    }

    @Nullable
    @Override
    public Element getFocused() {
        return null;
    }

    @Override
    public void setFocused(@Nullable Element focused) {

    }

    @Override
    public SelectionType getType() {
        return SelectionType.NONE;
    }
}
