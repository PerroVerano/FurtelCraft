package com.vtwo.furtelcraft.furtelcraft.libvne;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.init.NetPackInit;
import com.vtwo.furtelcraft.furtelcraft.libvne.widgets.BasedNonButtonWidget;
import com.vtwo.furtelcraft.furtelcraft.libvne.widgets.EditScreenTextWidget;
import com.vtwo.furtelcraft.furtelcraft.libvne.widgets.MultiLineStripWidget;
import com.vtwo.furtelcraft.furtelcraft.libvne.widgets.TabWidget;
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
import net.minecraft.text.Text;
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
    private final Text EMPTY = Text.empty();
    private final int backgroundWidth = 256;
    private final int backgroundHeight = 168;
    protected LivingEntity entity;
    private TextFieldWidget nameFieldWidget;
    private TextFieldWidget wordFieldWidget;
    private ButtonWidget DoneBtnWidget;
    private ButtonWidget NextBtnWidget;
    private ButtonWidget DelBtnWidget;
    private EditScreenTextWidget TextWidget;
    private TabWidget EnabledSingleLineTab;
    private TabWidget DisabledSingleLineTab;
    private TabWidget EnabledMultiLineTab;
    private TabWidget DisabledMultiLineTab;
    private TabWidget EnabledHistTab;
    private TabWidget DisabledHistTab;
    private MultiLineStripWidget OS1StripWidget;
    private MultiLineStripWidget OS2StripWidget;
    private TextFieldWidget OS1FieldWidget;
    private TextFieldWidget OS2FieldWidget;
    private TextFieldWidget OS1WordField;
    private TextFieldWidget OS2WordField;
    private EditScreenTextWidget OS1TextWidget;
    private EditScreenTextWidget OS2TextWidget;
    private boolean isEnabledSingleLineTab = true;
    private boolean isEnabledMultiLineTab = false;
    private boolean isEnabledHistTab = false;
    private int count = 0;
    private int OS1count = 0;
    private int OS2count = 0;
    private String s = "";
    private String s1 = "";
    private String s2 = "";
    private final List<String> WordList = Lists.newArrayList();
    private final List<String> WordHist = Lists.newArrayList();
    private final List<String> OS1Word = Lists.newArrayList();
    private final List<String> OS2Word = Lists.newArrayList();
    private final List<String> OS1WordHist = Lists.newArrayList();
    private final List<String> OS2WordHist = Lists.newArrayList();
    public static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/vne_edit.png");

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
        this.updateWidget();

        super.render(matrices, mouseX, mouseY, delta);
    }

    private void updateInfo() {
        this.entity.setCustomName(Text.literal(this.nameFieldWidget.getText()));
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
        this.nameFieldWidget.setText(this.entity.getName().getString());
        this.wordFieldWidget = new TextFieldWidget(this.textRenderer, iBase + 85, jBase + 60, 162, 14, this.EMPTY);
        this.DoneBtnWidget = new ButtonWidget(iBase + 212, jBase + 143, 36, 20, Text.translatable("widget.furtelcraft.vn_edit_screen_donebtn"), button -> {
            this.saveInfo();
            assert this.client != null;
            this.client.setScreen(null);
        });
        this.NextBtnWidget = new ButtonWidget(iBase + 172, jBase + 143, 36, 20, Text.translatable("widget.furtelcraft.vn_edit_screen_nextbtn"), button -> {
            if (this.isEnabledSingleLineTab) {
                this.count++;
                WordList.add(this.wordFieldWidget.getText());
                this.WordHist.add(this.count + ">" + this.wordFieldWidget.getText() + "\n");
                this.s = this.WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                this.TextWidget.setMessage(Text.literal(this.s));
                this.wordFieldWidget.setText("");
            } else if (this.isEnabledMultiLineTab && this.OS1StripWidget.getState() == 0) {
                this.OS1count++;
                OS1Word.add(this.OS1WordField.getText());
                this.OS1WordHist.add(this.OS1count + ">" + this.OS1WordField.getText() + "\n");
                this.s1 = this.OS1WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                this.OS1TextWidget.setMessage(Text.literal(this.s1));
                this.OS1WordField.setText("");
            } else if (this.isEnabledMultiLineTab && this.OS2StripWidget.getState() == 0) {
                this.OS2count++;
                OS2Word.add(this.OS2WordField.getText());
                this.OS2WordHist.add(this.OS2count + ">" + this.OS2WordField.getText() + "\n");
                this.s2 = this.OS2WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                this.OS2TextWidget.setMessage(Text.literal(this.s2));
                this.OS2WordField.setText("");
            }
        });
        this.DelBtnWidget = new ButtonWidget(iBase + 132, jBase + 143, 36, 20, Text.translatable("widget.furtelcraft.vn_edit_screen_delbtn"), button -> {
            if (this.isEnabledSingleLineTab) {
                if (!WordList.isEmpty()) {
                    this.count--;
                    WordList.remove(this.count);
                    this.WordHist.remove(this.count);
                    this.s = this.WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                    this.TextWidget.setMessage(Text.literal(this.s));
                }
            } else if (this.isEnabledMultiLineTab && this.OS1StripWidget.getState() == 0) {
                if (!OS1Word.isEmpty()) {
                    this.OS1count--;
                    OS1Word.remove(this.OS1count);
                    this.OS1WordHist.remove(this.OS1count);
                    this.s1 = this.OS1WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                    this.OS1TextWidget.setMessage(Text.literal(this.s1));
                }
            } else if (this.isEnabledMultiLineTab && this.OS2StripWidget.getState() == 0) {
                if (!OS2Word.isEmpty()) {
                    this.OS2count--;
                    OS2Word.remove(this.OS2count);
                    this.OS2WordHist.remove(this.OS2count);
                    this.s2 = this.OS2WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                    this.OS2TextWidget.setMessage(Text.literal(this.s2));
                }
            }
        });
        this.TextWidget = new EditScreenTextWidget(iBase + 84, jBase + 77, 164, 64, EMPTY, Color.WHITE);
        this.EnabledSingleLineTab = new TabWidget(
                iBase + 3,
                jBase + 165,
                32,
                19,
                Text.translatable("widget.furtelcraft.vn_edit_screen.singleline"),
                Color.WHITE,
                widget -> {
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        Text.translatable("widget.furtelcraft.vn_edit_screen.singleline.tooltip"),
                        mouseX,
                        mouseY
                ),
                true
        );
        this.DisabledSingleLineTab = new TabWidget(
                iBase + 3,
                jBase + 168,
                32,
                17,
                Text.translatable("widget.furtelcraft.vn_edit_screen.singleline"),
                Color.WHITE,
                widget -> {
                    this.isEnabledSingleLineTab = true;
                    this.isEnabledMultiLineTab = false;
                    this.isEnabledHistTab = false;
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        Text.translatable("widget.furtelcraft.vn_edit_screen.singleline.tooltip"),
                        mouseX,
                        mouseY
                ),
                false
        );

        this.EnabledMultiLineTab = new TabWidget(
                iBase + 37,
                jBase + 165,
                32,
                19,
                Text.translatable("widget.furtelcraft.vn_edit_screen.multiline"),
                Color.WHITE,
                widget -> {
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        Text.translatable("widget.furtelcraft.vn_edit_screen.multiline.tooltip"),
                        mouseX,
                        mouseY
                ),
                true
        );

        this.DisabledMultiLineTab = new TabWidget(
                iBase + 37,
                jBase + 168,
                32,
                17,
                Text.translatable("widget.furtelcraft.vn_edit_screen.multiline"),
                Color.WHITE,
                widget -> {
                    this.isEnabledSingleLineTab = false;
                    this.isEnabledMultiLineTab = true;
                    this.isEnabledHistTab = false;
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        Text.translatable("widget.furtelcraft.vn_edit_screen.multiline.tooltip"),
                        mouseX,
                        mouseY
                ),
                false
        );

        this.EnabledHistTab = new TabWidget(
                iBase + 71,
                jBase + 165,
                32,
                19,
                Text.translatable("widget.furtelcraft.vn_edit_screen.hist"),
                Color.WHITE,
                widget -> {
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        Text.translatable("widget.furtelcraft.vn_edit_screen.hist.tooltip"),
                        mouseX,
                        mouseY
                ),
                true
        );
        this.DisabledHistTab = new TabWidget(
                iBase + 71,
                jBase + 168,
                32,
                17,
                Text.translatable("widget.furtelcraft.vn_edit_screen.hist"),
                Color.WHITE,
                widget -> {
                    this.isEnabledSingleLineTab = false;
                    this.isEnabledMultiLineTab = false;
                    this.isEnabledHistTab = true;
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        Text.translatable("widget.furtelcraft.vn_edit_screen.hist.tooltip"),
                        mouseX,
                        mouseY
                ),
                false
        );
        this.OS1StripWidget = new MultiLineStripWidget(
                iBase + 96,
                jBase + 22,
                70,
                14,
                Text.translatable("widget.furtelcraft.vn_edit_screen.multiline.os1strip"),
                Color.WHITE,
                widget -> {
                    if (this.OS2StripWidget.getState() == 0 && this.OS1StripWidget.getState() == 1) {
                        this.OS1StripWidget.setState(0);
                        this.OS2StripWidget.setState(2);
                    }
                },
                (widget, matrices, mouseX, mouseY) -> {
                },
                0
        );
        this.OS2StripWidget = new MultiLineStripWidget(
                iBase + 166,
                jBase + 22,
                70,
                14,
                Text.translatable("widget.furtelcraft.vn_edit_screen.multiline.os2strip"),
                Color.WHITE,
                widget -> {
                    if (this.OS2StripWidget.getState() == 2 && this.OS1StripWidget.getState() == 0) {
                        this.OS2StripWidget.setState(0);
                        this.OS1StripWidget.setState(1);
                    }
                },
                (widget, matrices, mouseX, mouseY) -> {
                },
                2
        );
        this.OS1FieldWidget = new TextFieldWidget(this.textRenderer, iBase + 85, jBase + 40, 162, 14, Text.translatable("widget.furtelcraft.vn_edit_screen.multiline.osfield"));
        this.OS2FieldWidget = new TextFieldWidget(this.textRenderer, iBase + 85, jBase + 40, 162, 14, Text.translatable("widget.furtelcraft.vn_edit_screen.multiline.osfield"));
        this.OS1WordField = new TextFieldWidget(this.textRenderer, iBase + 85, jBase + 60, 162, 14, this.EMPTY);
        this.OS2WordField = new TextFieldWidget(this.textRenderer, iBase + 85, jBase + 60, 162, 14, this.EMPTY);
        this.OS1TextWidget = new EditScreenTextWidget(iBase + 84, jBase + 77, 164, 64, EMPTY, Color.WHITE);
        this.OS2TextWidget = new EditScreenTextWidget(iBase + 84, jBase + 77, 164, 64, EMPTY, Color.WHITE);
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
        this.addDrawableChild(this.DisabledHistTab);
        this.addDrawableChild(this.EnabledHistTab);
        this.addDrawableChild(this.DisabledMultiLineTab);
        this.addDrawableChild(this.EnabledMultiLineTab);
        this.addDrawableChild(this.DisabledSingleLineTab);
        this.addDrawableChild(this.EnabledSingleLineTab);

        this.addDrawableChild(this.OS1StripWidget);
        this.addDrawableChild(this.OS2StripWidget);
        this.addDrawableChild(this.OS1FieldWidget);
        this.addDrawableChild(this.OS2FieldWidget);
        this.addDrawableChild(this.OS1WordField);
        this.addDrawableChild(this.OS2WordField);
        this.addDrawableChild(this.OS1TextWidget);
        this.addDrawableChild(this.OS2TextWidget);
    }

    private void updateWidget() {
        this.EnabledSingleLineTab.visible = this.isEnabledSingleLineTab;
        this.DisabledSingleLineTab.visible = !this.isEnabledSingleLineTab;
        this.EnabledMultiLineTab.visible = this.isEnabledMultiLineTab;
        this.DisabledMultiLineTab.visible = !this.isEnabledMultiLineTab;
        this.EnabledHistTab.visible = this.isEnabledHistTab;
        this.DisabledHistTab.visible = !this.isEnabledHistTab;
        this.updateSingle();
        this.updateMulti();
    }

    private void updateSingle() {
        this.TextWidget.visible = this.isEnabledSingleLineTab;
        this.wordFieldWidget.visible = this.isEnabledSingleLineTab;
    }

    private void updateMulti() {
        this.OS1StripWidget.visible = this.isEnabledMultiLineTab;
        this.OS2StripWidget.visible = this.isEnabledMultiLineTab;
        this.OS1FieldWidget.visible = this.isEnabledMultiLineTab && this.OS1StripWidget.getState() == 0;
        this.OS2FieldWidget.visible = this.isEnabledMultiLineTab && this.OS2StripWidget.getState() == 0;
        this.OS1WordField.visible = this.isEnabledMultiLineTab && this.OS1StripWidget.getState() == 0;
        this.OS2WordField.visible = this.isEnabledMultiLineTab && this.OS2StripWidget.getState() == 0;
        this.OS1TextWidget.visible = this.isEnabledMultiLineTab && this.OS1StripWidget.getState() == 0;
        this.OS2TextWidget.visible = this.isEnabledMultiLineTab && this.OS2StripWidget.getState() == 0;
    }
}
