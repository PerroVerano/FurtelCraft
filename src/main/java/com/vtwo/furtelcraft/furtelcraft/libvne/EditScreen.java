package com.vtwo.furtelcraft.furtelcraft.libvne;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.init.NetPackInit;
import com.vtwo.furtelcraft.furtelcraft.libvne.widgets.BasedNonButtonWidget;
import com.vtwo.furtelcraft.furtelcraft.libvne.widgets.EditScreenTextWidget;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne
 * @NAME: EditScreen
 * @USER: Perano
 * @DATE: 2023/1/12
 * @TIME: 18:05
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 12
 * @DAY_NAME_SHORT: 周四
 * @DAY_NAME_FULL: 星期四
 * @HOUR: 18
 * @MINUTE: 05
 * @PROJECT_NAME: furtelcraft
 */
public class EditScreen extends Screen {
    private final Text EMPTY = LiteralText.EMPTY;
    private final int backgroundWidth = 256;
    private final int backgroundHeight = 168;
    protected LivingEntity entity;
    private TextFieldWidget nameFieldWidget;
    private TextFieldWidget wordFieldWidget;
    private ButtonWidget DoneBtnWidget;
    private ButtonWidget NextBtnWidget;
    private ButtonWidget DelBtnWidget;
    private EditScreenTextWidget TextWidget;
    private int count = 0;
    private String s = "";
    private final List<String> WordList = Lists.newArrayList();
    private final List<String> WordHist = Lists.newArrayList();
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/vne_edit.png");

    public EditScreen(Text title) {
        super(title);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        int iBase = (width - backgroundWidth) / 2;
        int jBase = (height - backgroundHeight) / 2;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, iBase, jBase, 0, 0, backgroundWidth, backgroundHeight);
        BasedNonButtonWidget.drawEntity(iBase + 44, jBase + 102, 28, (float) (iBase + 44) - mouseX, (float) (jBase + 122 - 50) - mouseY, this.entity);
        this.textRenderer.drawWithShadow(matrices, this.getTitle(), iBase + 8, jBase + 8, 16777215);
        //this.textRenderer.drawWithShadow(matrices, new TranslatableText("widget.furtelcraft.vn_edit_screen.textfieldwidget"), iBase + 142, jBase + 27, 16777215);

        this.updateInfo();

        super.render(matrices, mouseX, mouseY, delta);
    }

    private void updateInfo() {
        this.entity.setCustomName(new LiteralText(this.nameFieldWidget.getText()));
    }

    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }

    @Override
    protected void init() {
        super.init();
        this.initWidgets();
        this.addWidgets();
    }

    private void initWidgets() {
        int iBase = (width - backgroundWidth) / 2;
        int jBase = (height - backgroundHeight) / 2;
        this.nameFieldWidget = new TextFieldWidget(this.textRenderer, iBase + 11, jBase + 126, 65, 14, this.EMPTY);
        this.nameFieldWidget.setText(this.entity.getName().asString());
        this.wordFieldWidget = new TextFieldWidget(this.textRenderer, iBase + 85, jBase + 60, 162, 14, this.EMPTY);
        this.DoneBtnWidget = new ButtonWidget(iBase + 212, jBase + 143, 36, 20, new TranslatableText("widget.furtelcraft.vn_edit_screen_donebtn"), button -> {
            this.saveInfo();
            assert this.client != null;
            this.client.setScreen(null);
        });
        this.NextBtnWidget = new ButtonWidget(iBase + 172, jBase + 143, 36, 20, new TranslatableText("widget.furtelcraft.vn_edit_screen_nextbtn"), button -> {
            this.count++;
            WordList.add(this.wordFieldWidget.getText());
            this.WordHist.add(this.count + ">" + this.wordFieldWidget.getText() + "\n");
            this.s = this.WordHist.stream().map(Objects::toString).collect(Collectors.joining());
            this.TextWidget.setMessage(new LiteralText(this.s));
            this.wordFieldWidget.setText("");
        });
        this.DelBtnWidget = new ButtonWidget(iBase + 132, jBase + 143, 36, 20, new TranslatableText("widget.furtelcraft.vn_edit_screen_delbtn"), button -> {
            if (!WordList.isEmpty()) {
                this.count--;
                WordList.remove(this.count);
                this.WordHist.remove(this.count);
                this.s = this.WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                this.TextWidget.setMessage(new LiteralText(this.s));
            }

        });
        this.TextWidget = new EditScreenTextWidget(iBase + 84, jBase + 77, 164, 64, EMPTY, Color.WHITE);
    }

    private void saveInfo() {
        /*Stream<NbtString> stream = this.WordList.stream().map(NbtString::of);
        Objects.requireNonNull(this.nbtList);
        stream.forEach(this.nbtList::add);
        if (!this.WordList.isEmpty()) {
            this.entity.writeCustomDataToNbt(this.nbtList.getCompound(0));
        }
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putString("Name", this.nameFieldWidget.getText());
        this.entity.writeCustomDataToNbt(nbtCompound);*/

        PacketByteBuf byteBuf = PacketByteBufs.create();
        byteBuf.writeString(this.nameFieldWidget.getText());
        byteBuf.writeInt(this.entity.getId());
        ClientPlayNetworking.send(NetPackInit.EDIT_SCREEN_ENTITY_NAME_ID, byteBuf);

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeUuid(this.entity.getUuid());
        NbtList nbtList = new NbtList();
        NbtString string = NbtString.of(this.nameFieldWidget.getText());
        nbtList.add(string);
        Stream<NbtString> stream = this.WordList.stream().map(NbtString::of);
        Objects.requireNonNull(nbtList);
        stream.forEach(nbtList::add);
        NbtCompound temp = new NbtCompound();
        temp.put("Word", nbtList);
        buf.writeNbt(temp);
        ClientPlayNetworking.send(NetPackInit.EDIT_SCREEN_SAVE_ENTITY_WORD_ID, buf);
    }

    private void addWidgets() {
        this.addDrawableChild(this.nameFieldWidget);
        this.addDrawableChild(this.wordFieldWidget);
        this.addDrawableChild(this.DoneBtnWidget);
        this.addDrawableChild(this.NextBtnWidget);
        this.addDrawableChild(this.TextWidget);
        this.addDrawableChild(this.DelBtnWidget);
    }
}
