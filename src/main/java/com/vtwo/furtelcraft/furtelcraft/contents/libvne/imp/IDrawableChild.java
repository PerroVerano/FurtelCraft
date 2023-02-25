package com.vtwo.furtelcraft.furtelcraft.contents.libvne.imp;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;

import java.util.List;

public interface IDrawableChild {
    List<Drawable> drawables = Lists.newArrayList();
    List<Element> elements = Lists.newArrayList();
    List<Selectable> selectables = Lists.newArrayList();

    default <T extends Element & Drawable & Selectable> void addChild(T child) {
        drawables.add(child);
        elements.add(child);
        selectables.add(child);
    }

    default void clearChild() {
        drawables.clear();
        elements.clear();
        selectables.clear();
    }

    default <T extends Element & Drawable & Selectable> void removeChild(T child) {
        if (child != null) {
            drawables.remove(child);
        }

        if (child != null) {
            selectables.remove(child);
        }

        elements.remove(child);
    }
}
