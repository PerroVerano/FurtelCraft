package com.vtwo.furtelcraft.furtelcraft.screens.intedgui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import juuxel.libninepatch.NinePatch;
import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class BookBaseGUI extends LightweightGuiDescription {

    private static final Identifier TEXTURE = new Identifier(MOD_ID,"textures/screen/book.png");

    public BookBaseGUI() {
        WPlainPanel root = new WPlainPanel();
        root.setInsets(Insets.ROOT_PANEL);
        root.setSize(256,174);

        setRootPanel(root);
    }

    @Override
    public void addPainters() {
        this.getRootPanel().setBackgroundPainter(BackgroundPainter.createNinePatch(new Texture(TEXTURE),identifierBuilder -> {
            identifierBuilder.cornerSize(8).cornerUv(0.0F).mode(NinePatch.Mode.STRETCHING);
        }));
    }
}
