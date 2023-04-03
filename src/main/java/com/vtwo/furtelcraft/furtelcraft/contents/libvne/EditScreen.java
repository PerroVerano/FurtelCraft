package com.vtwo.furtelcraft.furtelcraft.contents.libvne;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.vtwo.furtelcraft.furtelcraft.contents.libvne.widgets.*;
import com.vtwo.furtelcraft.furtelcraft.init.FCNetPacks;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.Screen;
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
    private GButtonWidget DoneBtnWidget;
    private GButtonWidget NextBtnWidget;
    private GButtonWidget DelBtnWidget;
    private GButtonWidget editBtnWidget;
    private EditScreenTextWidget TextWidget;
    private TabWidget EnabledSingleLineTab;
    private TabWidget DisabledSingleLineTab;
    private TabWidget EnabledMultiLineTab;
    private TabWidget DisabledMultiLineTab;
    private TabWidget EnabledHistTab;
    private TabWidget DisabledHistTab;
    private StripWidget OS1StripWidget;
    private StripWidget OS2StripWidget;
    private TextFieldWidget OS1FieldWidget;
    private TextFieldWidget OS2FieldWidget;
    private GButtonWidget copyWordBtnWidget;
    private TextFieldWidget OS1WordField;
    private TextFieldWidget OS2WordField;
    private EditScreenTextWidget OS1TextWidget;
    private EditScreenTextWidget OS2TextWidget;
    private StripWidget singleHistWidget;
    private StripWidget os1HistWidget;
    private StripWidget os2HistWidget;
    private boolean isEnabledSingleLineTab = true;
    private boolean isEnabledMultiLineTab = false;
    private boolean isEnabledHistTab = false;
    private int count = 0;
    private int OS1count = 0;
    private int OS2count = 0;
    private String s = "";
    private String s1 = "";
    private String s2 = "";
    private String sh = "";
    private final List<String> WordList = Lists.newArrayList();
    private final List<String> WordHist = Lists.newArrayList();
    private List<String> OS1Word = Lists.newArrayList();
    private List<String> OS2Word = Lists.newArrayList();
    private List<String> OS1WordHist = Lists.newArrayList();
    private List<String> OS2WordHist = Lists.newArrayList();
    private EditScreenTextWidget histTextWidget;
    private NbtCompound nbtCompound;
    public static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/screen/vne_edit.png");

    public EditScreen(Text title) {
        super(title);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        int iBase = (width - backgroundWidth) / 2;
        int jBase = (height - backgroundHeight) / 2;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, iBase, jBase, 0, 0, backgroundWidth, backgroundHeight);
        BasedWidget.drawEntity(iBase + 44, jBase + 102, 28, (float) (iBase + 44) - mouseX, (float) (jBase + 122 - 50) - mouseY, this.entity);
        this.textRenderer.drawWithShadow(matrices, this.getTitle(), iBase + 8, jBase + 8, 16777215);
        //this.textRenderer.drawWithShadow(matrices, new TranslatableText("widget.furtelcraft.vn_edit_screen.textfieldwidget"), iBase + 142, jBase + 27, 16777215);

        this.updateInfo(matrices);
        this.updateWidget();

        super.render(matrices, mouseX, mouseY, delta);
    }

    private void updateInfo(MatrixStack matrices) {
        int iBase = (width - backgroundWidth) / 2;
        int jBase = (height - backgroundHeight) / 2;
        this.entity.setCustomName(new LiteralText(this.nameFieldWidget.getText()));
        if (this.os1HistWidget.isSelected() && this.isEnabledHistTab) {
            if (this.nbtCompound == null || !this.nbtCompound.contains("OS1")) {
                this.textRenderer.drawWithShadow(matrices, new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.os1.empty"), iBase + 85, jBase + 40, 16777215);
            } else {
                this.textRenderer.drawWithShadow(matrices, new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.os1").append(this.nbtCompound.getString("OS1TEXT")), iBase + 85, jBase + 40, 16777215);
            }
        } else if (this.os2HistWidget.isSelected() && this.isEnabledHistTab) {
            if (this.nbtCompound == null || !this.nbtCompound.contains("OS2")) {
                this.textRenderer.drawWithShadow(matrices, new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.os2.empty"), iBase + 85, jBase + 40, 16777215);
            } else {
                this.textRenderer.drawWithShadow(matrices, new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.os2").append(this.nbtCompound.getString("OS2TEXT")), iBase + 85, jBase + 40, 16777215);
            }
        }
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
        //1.19:this.nameFieldWidget.setText(this.entity.getName().getString());
        this.nameFieldWidget.setText(this.entity.getName().asString());
        this.wordFieldWidget = new TextFieldWidget(this.textRenderer, iBase + 85, jBase + 60, 162, 14, this.EMPTY);
        this.DoneBtnWidget = new GButtonWidget(iBase + 212, jBase + 143, 36, 16, new TranslatableText("widget.furtelcraft.vn_edit_screen_donebtn"), Color.WHITE, button -> this.saveInfo(), (widget, matrices, mouseX, mouseY) -> {
        });
        this.NextBtnWidget = new GButtonWidget(iBase + 172, jBase + 143, 36, 16, new TranslatableText("widget.furtelcraft.vn_edit_screen_nextbtn"), Color.WHITE, button -> {
            if (this.isEnabledSingleLineTab) {
                this.count++;
                WordList.add(this.wordFieldWidget.getText());
                this.WordHist.add(this.count + ">" + this.wordFieldWidget.getText() + "\n");
                this.s = this.WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                this.TextWidget.setMessage(new LiteralText(this.s));
                this.wordFieldWidget.setText("");
            } else if (this.isEnabledMultiLineTab && this.OS1StripWidget.isSelected()) {
                this.OS1count++;
                OS1Word.add(this.OS1WordField.getText());
                this.OS1WordHist.add(this.OS1count + ">" + this.OS1WordField.getText() + "\n");
                this.s1 = this.OS1WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                this.OS1TextWidget.setMessage(new LiteralText(this.s1));
                this.OS1WordField.setText("");
            } else if (this.isEnabledMultiLineTab && this.OS2StripWidget.isSelected()) {
                this.OS2count++;
                OS2Word.add(this.OS2WordField.getText());
                this.OS2WordHist.add(this.OS2count + ">" + this.OS2WordField.getText() + "\n");
                this.s2 = this.OS2WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                this.OS2TextWidget.setMessage(new LiteralText(this.s2));
                this.OS2WordField.setText("");
            }
        }, (widget, matrices, mouseX, mouseY) -> {
        });
        this.DelBtnWidget = new GButtonWidget(iBase + 132, jBase + 143, 36, 16, new TranslatableText("widget.furtelcraft.vn_edit_screen_delbtn"), Color.WHITE, button -> {
            if (this.isEnabledSingleLineTab) {
                if (!WordList.isEmpty()) {
                    this.count--;
                    WordList.remove(this.count);
                    this.WordHist.remove(this.count);
                    this.s = this.WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                    this.TextWidget.setMessage(new LiteralText(this.s));
                }
            } else if (this.isEnabledMultiLineTab && this.OS1StripWidget.isSelected()) {
                if (!OS1Word.isEmpty()) {
                    this.OS1count--;
                    OS1Word.remove(this.OS1count);
                    this.OS1WordHist.remove(this.OS1count);
                    this.s1 = this.OS1WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                    this.OS1TextWidget.setMessage(new LiteralText(this.s1));
                }
            } else if (this.isEnabledMultiLineTab && this.OS2StripWidget.isSelected()) {
                if (!OS2Word.isEmpty()) {
                    this.OS2count--;
                    OS2Word.remove(this.OS2count);
                    this.OS2WordHist.remove(this.OS2count);
                    this.s2 = this.OS2WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                    this.OS2TextWidget.setMessage(new LiteralText(this.s2));
                }
            } else if (this.isEnabledHistTab && this.os1HistWidget.isSelected()) {
                PacketByteBuf byteBuf = PacketByteBufs.create();
                byteBuf.writeInt(1);
                byteBuf.writeUuid(this.entity.getUuid());
                ClientPlayNetworking.send(FCNetPacks.DEL_ENTITY_WORD_OS_ID, byteBuf);
                if (this.nbtCompound != null) {
                    if (this.nbtCompound.contains("OS1")) {
                        this.histTextWidget.setMessage(EMPTY);
                        this.nbtCompound.remove("OS1");
                        this.nbtCompound.remove("OS1TEXT");
                    }
                }
            } else if (this.isEnabledHistTab && this.os2HistWidget.isSelected()) {
                PacketByteBuf byteBuf = PacketByteBufs.create();
                byteBuf.writeInt(2);
                byteBuf.writeUuid(this.entity.getUuid());
                ClientPlayNetworking.send(FCNetPacks.DEL_ENTITY_WORD_OS_ID, byteBuf);
                if (this.nbtCompound != null) {
                    if (this.nbtCompound.contains("OS2")) {
                        this.histTextWidget.setMessage(EMPTY);
                        this.nbtCompound.remove("OS2");
                        this.nbtCompound.remove("OS2TEXT");
                    }
                }
            }
        }, (widget, matrices, mouseX, mouseY) -> {
        });
        this.TextWidget = new EditScreenTextWidget(iBase + 84, jBase + 77, 164, 64, EMPTY, Color.WHITE);
        this.EnabledSingleLineTab = new TabWidget(
                iBase + 3,
                jBase + 165,
                32,
                19,
                new TranslatableText("widget.furtelcraft.vn_edit_screen.singleline"),
                Color.WHITE,
                widget -> {
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        new TranslatableText("widget.furtelcraft.vn_edit_screen.singleline.tooltip"),
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
                new TranslatableText("widget.furtelcraft.vn_edit_screen.singleline"),
                Color.WHITE,
                widget -> {
                    this.isEnabledSingleLineTab = true;
                    this.isEnabledMultiLineTab = false;
                    this.isEnabledHistTab = false;
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        new TranslatableText("widget.furtelcraft.vn_edit_screen.singleline.tooltip"),
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
                new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline"),
                Color.WHITE,
                widget -> {
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline.tooltip"),
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
                new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline"),
                Color.WHITE,
                widget -> {
                    this.isEnabledSingleLineTab = false;
                    this.isEnabledMultiLineTab = true;
                    this.isEnabledHistTab = false;
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline.tooltip"),
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
                new TranslatableText("widget.furtelcraft.vn_edit_screen.hist"),
                Color.WHITE,
                widget -> {
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.tooltip"),
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
                new TranslatableText("widget.furtelcraft.vn_edit_screen.hist"),
                Color.WHITE,
                widget -> {
                    this.isEnabledSingleLineTab = false;
                    this.isEnabledMultiLineTab = false;
                    this.isEnabledHistTab = true;
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(
                        matrices,
                        new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.tooltip"),
                        mouseX,
                        mouseY
                ),
                false
        );
        this.OS1StripWidget = new StripWidget(
                iBase + 96,
                jBase + 22,
                70,
                14,
                new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline.os1strip"),
                Color.WHITE,
                widget -> {
                    if (this.OS2StripWidget.isSelected() && this.OS1StripWidget.getState() == 1) {
                        this.OS1StripWidget.setState(0);
                        this.OS2StripWidget.setState(2);
                    }
                },
                (widget, matrices, mouseX, mouseY) -> {
                },
                0
        );
        this.OS2StripWidget = new StripWidget(
                iBase + 166,
                jBase + 22,
                70,
                14,
                new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline.os2strip"),
                Color.WHITE,
                widget -> {
                    if (this.OS2StripWidget.getState() == 2 && this.OS1StripWidget.isSelected()) {
                        this.OS2StripWidget.setState(0);
                        this.OS1StripWidget.setState(1);
                    }
                },
                (widget, matrices, mouseX, mouseY) -> {
                },
                2
        );
        this.OS1FieldWidget = new TextFieldWidget(this.textRenderer, iBase + 85, jBase + 40, 130, 14, new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline.osfield"));
        this.OS2FieldWidget = new TextFieldWidget(this.textRenderer, iBase + 85, jBase + 40, 130, 14, new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline.osfield"));
        this.copyWordBtnWidget = new GButtonWidget(iBase + 219, jBase + 39, 28, 16, new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline.copybtn"), Color.WHITE,
                widget -> {
                    if (this.OS1FieldWidget.visible) {
                        this.OS2Word = this.OS1Word.stream().map(String::toString).collect(Collectors.toList());
                        this.OS2WordHist = this.OS1WordHist.stream().map(String::toString).collect(Collectors.toList());
                        this.OS2count = this.OS1count;
                        this.s2 = this.s1;
                        this.OS2TextWidget.setMessage(new LiteralText(this.s2));
                    } else if (this.OS2FieldWidget.visible) {
                        this.OS1Word = this.OS2Word.stream().map(String::toString).collect(Collectors.toList());
                        this.OS1WordHist = this.OS2WordHist.stream().map(String::toString).collect(Collectors.toList());
                        this.OS1count = this.OS2count;
                        this.s1 = this.s2;
                        this.OS1TextWidget.setMessage(new LiteralText(this.s1));
                    }
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(matrices, new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline.copybtn.tooltip"), mouseX, mouseY));
        this.OS1WordField = new TextFieldWidget(this.textRenderer, iBase + 85, jBase + 60, 162, 14, this.EMPTY);
        this.OS2WordField = new TextFieldWidget(this.textRenderer, iBase + 85, jBase + 60, 162, 14, this.EMPTY);
        this.OS1TextWidget = new EditScreenTextWidget(iBase + 84, jBase + 77, 164, 64, EMPTY, Color.WHITE);
        this.OS2TextWidget = new EditScreenTextWidget(iBase + 84, jBase + 77, 164, 64, EMPTY, Color.WHITE);

        this.singleHistWidget = new StripWidget(iBase + 85, jBase + 22, 54, 14, new TranslatableText("widget.furtelcraft.vn_edit_screen.singleline"), Color.WHITE,
                widget -> {
                    if (this.singleHistWidget.getState() == 1) {
                        if (this.os1HistWidget.isSelected() || this.os2HistWidget.isSelected()) {
                            this.singleHistWidget.setState(0);
                            this.os1HistWidget.setState(3);
                            this.os2HistWidget.setState(2);
                        }
                    }
                    if (this.nbtCompound == null) {
                        this.histTextWidget.setMessage(new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.empty"));
                    } else {
                        this.sh = "";
                        NbtList nbtList = (NbtList) this.nbtCompound.get("SINGLE");
                        StringBuilder builder = new StringBuilder(this.sh);
                        for (int i = 0; i < Objects.requireNonNull(nbtList).size(); i++) {
                            builder.append(i + 1).append(">").append(nbtList.getString(i)).append("\n");
                        }
                        this.sh = builder.toString();
                        this.histTextWidget.resetPos();
                        this.histTextWidget.setMessage(new LiteralText(this.sh));
                    }
                },
                (widget, matrices, mouseX, mouseY) -> {
                },
                0);
        this.os1HistWidget = new StripWidget(iBase + 139, jBase + 22, 54, 14, new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline.os1strip"), Color.WHITE,
                widget -> {
                    if (this.os1HistWidget.getState() == 3) {
                        if (this.singleHistWidget.isSelected() || this.os2HistWidget.isSelected()) {
                            this.singleHistWidget.setState(1);
                            this.os1HistWidget.setState(0);
                            this.os2HistWidget.setState(2);
                        }
                    }
                    if (this.nbtCompound == null || !this.nbtCompound.contains("OS1")) {
                        this.histTextWidget.setMessage(new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.empty"));
                    } else if (this.nbtCompound.contains("OS1")) {
                        this.sh = "";
                        NbtList nbtList = (NbtList) this.nbtCompound.get("OS1");
                        StringBuilder builder = new StringBuilder(this.sh);
                        for (int i = 0; i < Objects.requireNonNull(nbtList).size(); i++) {
                            builder.append(i + 1).append(">").append(nbtList.getString(i)).append("\n");
                        }
                        this.sh = builder.toString();
                        this.histTextWidget.resetPos();
                        this.histTextWidget.setMessage(new LiteralText(this.sh));
                    } else {
                        this.histTextWidget.resetPos();
                        this.histTextWidget.setMessage(EMPTY);
                    }
                },
                (widget, matrices, mouseX, mouseY) -> {
                },
                3);
        this.os2HistWidget = new StripWidget(iBase + 193, jBase + 22, 54, 14, new TranslatableText("widget.furtelcraft.vn_edit_screen.multiline.os2strip"), Color.WHITE,
                widget -> {
                    if (this.os2HistWidget.getState() == 2) {
                        if (this.singleHistWidget.isSelected() || this.os1HistWidget.isSelected()) {
                            this.singleHistWidget.setState(1);
                            this.os1HistWidget.setState(3);
                            this.os2HistWidget.setState(0);
                        }
                    }
                    if (this.nbtCompound == null || !this.nbtCompound.contains("OS2")) {
                        this.histTextWidget.setMessage(new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.empty"));
                    } else if (this.nbtCompound.contains("OS2")) {
                        this.sh = "";
                        NbtList nbtList = (NbtList) this.nbtCompound.get("OS2");
                        StringBuilder builder = new StringBuilder(this.sh);
                        for (int i = 0; i < Objects.requireNonNull(nbtList).size(); i++) {
                            builder.append(i + 1).append(">").append(nbtList.getString(i)).append("\n");
                        }
                        this.sh = builder.toString();
                        this.histTextWidget.resetPos();
                        this.histTextWidget.setMessage(new LiteralText(this.sh));
                    } else {
                        this.histTextWidget.resetPos();
                        this.histTextWidget.setMessage(EMPTY);
                    }
                },
                (widget, matrices, mouseX, mouseY) -> {

                },
                2);
        this.histTextWidget = new EditScreenTextWidget(iBase + 84, jBase + 77, 164, 64, EMPTY, Color.WHITE);
        if (this.nbtCompound == null || !this.nbtCompound.contains("SINGLE")) {
            this.histTextWidget.setMessage(new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.empty"));
        } else {
            this.sh = "";
            NbtList nbtList = (NbtList) this.nbtCompound.get("SINGLE");
            StringBuilder builder = new StringBuilder(this.sh);
            for (int i = 0; i < Objects.requireNonNull(nbtList).size(); i++) {
                builder.append(i + 1).append(">").append(nbtList.getString(i)).append("\n");
            }
            this.sh = builder.toString();
            this.histTextWidget.setMessage(new LiteralText(this.sh));
        }
        this.editBtnWidget = new GButtonWidget(iBase + 172, jBase + 143, 36, 16, new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.edit"), Color.WHITE,
                widget -> {
                    NbtList single = (NbtList) this.nbtCompound.get("SINGLE");
                    this.WordList.clear();
                    this.WordHist.clear();
                    this.count = 0;
                    for (int i = 0; i < Objects.requireNonNull(single).size(); i++) {
                        this.count++;
                        this.WordHist.add((i + 1) + ">" + single.getString(i) + "\n");
                        this.WordList.add(single.getString(i));
                    }
                    this.s = this.WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                    this.TextWidget.setMessage(new LiteralText(this.s));
                    NbtList os1 = (NbtList) this.nbtCompound.get("OS1");
                    this.OS1Word.clear();
                    this.OS1WordHist.clear();
                    this.OS1count = 0;
                    for (int i = 0; i < Objects.requireNonNull(os1).size(); i++) {
                        this.OS1count++;
                        this.OS1WordHist.add((i + 1) + ">" + os1.getString(i) + "\n");
                        this.OS1Word.add(os1.getString(i));
                    }
                    this.s1 = this.OS1WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                    this.OS1TextWidget.setMessage(new LiteralText(this.s1));
                    this.OS1FieldWidget.setText(this.nbtCompound.getString("OS1TEXT"));
                    NbtList os2 = (NbtList) this.nbtCompound.get("OS2");
                    this.OS2Word.clear();
                    this.OS2WordHist.clear();
                    this.OS2count = 0;
                    for (int i = 0; i < Objects.requireNonNull(os2).size(); i++) {
                        this.OS2count++;
                        this.OS2WordHist.add((i + 1) + ">" + os2.getString(i) + "\n");
                        this.OS2Word.add(os2.getString(i));
                    }
                    this.s2 = this.OS2WordHist.stream().map(Objects::toString).collect(Collectors.joining());
                    this.OS2TextWidget.setMessage(new LiteralText(this.s2));
                    this.OS2FieldWidget.setText(this.nbtCompound.getString("OS2TEXT"));
                },
                (widget, matrices, mouseX, mouseY) -> this.renderTooltip(matrices, new TranslatableText("widget.furtelcraft.vn_edit_screen.hist.edit.tooltip"), mouseX, mouseY));
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
        ClientPlayNetworking.send(FCNetPacks.EDIT_SCREEN_ENTITY_NAME_ID, byteBuf);

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeUuid(this.entity.getUuid());
        /*NbtList nbtList = new NbtList();
        NbtString string = NbtString.of(this.nameFieldWidget.getText());
        nbtList.add(string);
        Stream<NbtString> stream = this.WordList.stream().map(NbtString::of);
        Objects.requireNonNull(nbtList);
        stream.forEach(nbtList::add);
        */
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putString("NAME", this.nameFieldWidget.getText());
        NbtList singlelist = new NbtList();
        Stream<NbtString> singleStream = this.WordList.stream().map(NbtString::of);
        Objects.requireNonNull(singlelist);
        singleStream.forEach(singlelist::add);
        nbtCompound.put("SINGLE", singlelist);
        if (!this.OS1Word.isEmpty()) {
            NbtList os1List = new NbtList();
            Stream<NbtString> os1Stream = this.OS1Word.stream().map(NbtString::of);
            Objects.requireNonNull(os1List);
            os1Stream.forEach(os1List::add);
            nbtCompound.put("OS1", os1List);
            nbtCompound.putString("OS1TEXT", this.OS1FieldWidget.getText());
        }
        if (!this.OS2Word.isEmpty()) {
            NbtList os2List = new NbtList();
            Stream<NbtString> os2Stream = this.OS2Word.stream().map(NbtString::of);
            Objects.requireNonNull(os2List);
            os2Stream.forEach(os2List::add);
            nbtCompound.put("OS2", os2List);
            nbtCompound.putString("OS2TEXT", this.OS2FieldWidget.getText());
        }
        NbtCompound temp = new NbtCompound();
        temp.put("Word", nbtCompound);
        buf.writeNbt(temp);
        ClientPlayNetworking.send(FCNetPacks.EDIT_SCREEN_SAVE_ENTITY_WORD_ID, buf);
    }

    private void addWidgets() {
        this.addDrawableChild(this.nameFieldWidget);
        this.addDrawableChild(this.wordFieldWidget);
        this.addDrawableChild(this.DoneBtnWidget);
        this.addDrawableChild(this.NextBtnWidget);
        this.addDrawableChild(this.TextWidget);
        this.addDrawableChild(this.DelBtnWidget);
        this.addDrawableChild(this.editBtnWidget);
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
        this.addDrawableChild(this.copyWordBtnWidget);
        this.addDrawableChild(this.OS1WordField);
        this.addDrawableChild(this.OS2WordField);
        this.addDrawableChild(this.OS1TextWidget);
        this.addDrawableChild(this.OS2TextWidget);

        this.addDrawableChild(this.singleHistWidget);
        this.addDrawableChild(this.os1HistWidget);
        this.addDrawableChild(this.os2HistWidget);
        this.addDrawableChild(this.histTextWidget);
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
        this.updateHist();
    }

    private void updateSingle() {
        this.TextWidget.visible = this.isEnabledSingleLineTab;
        this.wordFieldWidget.visible = this.isEnabledSingleLineTab;
    }

    private void updateMulti() {
        this.OS1StripWidget.visible = this.isEnabledMultiLineTab;
        this.OS2StripWidget.visible = this.isEnabledMultiLineTab;
        this.copyWordBtnWidget.visible = this.isEnabledMultiLineTab;
        this.OS1FieldWidget.visible = this.isEnabledMultiLineTab && this.OS1StripWidget.isSelected();
        this.OS2FieldWidget.visible = this.isEnabledMultiLineTab && this.OS2StripWidget.isSelected();
        this.OS1WordField.visible = this.isEnabledMultiLineTab && this.OS1StripWidget.isSelected();
        this.OS2WordField.visible = this.isEnabledMultiLineTab && this.OS2StripWidget.isSelected();
        this.OS1TextWidget.visible = this.isEnabledMultiLineTab && this.OS1StripWidget.isSelected();
        this.OS2TextWidget.visible = this.isEnabledMultiLineTab && this.OS2StripWidget.isSelected();
    }

    private void updateHist() {
        this.NextBtnWidget.visible = this.isEnabledSingleLineTab || this.isEnabledMultiLineTab || !this.isEnabledHistTab;
        this.DelBtnWidget.visible = !(this.singleHistWidget.isSelected()) || this.isEnabledSingleLineTab || this.isEnabledMultiLineTab;
        this.singleHistWidget.visible = this.isEnabledHistTab;
        this.os1HistWidget.visible = this.isEnabledHistTab;
        this.os2HistWidget.visible = this.isEnabledHistTab;
        this.histTextWidget.visible = this.isEnabledHistTab;
        this.editBtnWidget.visible = this.isEnabledHistTab;
    }

    public void setEntityNbt(NbtCompound nbt) {
        this.nbtCompound = nbt;
    }
}
