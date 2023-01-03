package com.vtwo.furtelcraft.furtelcraft.screens.intedwidget;

import io.github.cottonmc.cotton.gui.client.LibGui;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.InputResult;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.screens.intedwidget
 * @NAME: WCustomText
 * @USER: Perano
 * @DATE: 2023/1/2
 * @TIME: 17:53
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 02
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 17
 * @MINUTE: 53
 * @PROJECT_NAME: furtelcraft
 */
public class WCustomText extends WWidget {
    protected Text text;
    protected int color;
    protected int darkmodeColor;
    protected HorizontalAlignment horizontalAlignment;
    protected VerticalAlignment verticalAlignment;
    @Environment(EnvType.CLIENT)
    private List<OrderedText> wrappedLines;
    private boolean wrappingScheduled;
    private @Nullable Runnable onClick;

    public WCustomText(Text text) {
        this(text, 4210752);
    }

    public WCustomText(Text text, int color) {
        this.horizontalAlignment = HorizontalAlignment.LEFT;
        this.verticalAlignment = VerticalAlignment.TOP;
        this.wrappingScheduled = false;
        this.text = Objects.requireNonNull(text, "text must not be null");
        this.color = color;
        this.darkmodeColor = color == 4210752 ? 12369084 : color;
    }

    public void setSize(int x, int y) {
        super.setSize(x, y);
        this.wrappingScheduled = true;
    }

    public boolean canResize() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    private void wrapLines() {
        TextRenderer font = MinecraftClient.getInstance().textRenderer;
        this.wrappedLines = font.wrapLines(this.text, this.width);
    }

    @Environment(EnvType.CLIENT)
    public @Nullable Style getTextStyleAt(int x, int y) {
        TextRenderer font = MinecraftClient.getInstance().textRenderer;
        Objects.requireNonNull(font);
        int lineIndex = y / 9;
        if (lineIndex >= 0 && lineIndex < this.wrappedLines.size()) {
            OrderedText line = (OrderedText) this.wrappedLines.get(lineIndex);
            return font.getTextHandler().getStyleAt(line, x);
        } else {
            return null;
        }
    }

    @Environment(EnvType.CLIENT)
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        if (this.wrappedLines == null || this.wrappingScheduled) {
            this.wrapLines();
            this.wrappingScheduled = false;
        }

        TextRenderer font = MinecraftClient.getInstance().textRenderer;
        int var10000;
        switch (this.verticalAlignment) {
            case CENTER -> {
                var10000 = this.height / 2;
                Objects.requireNonNull(font);
                var10000 -= 9 * this.wrappedLines.size() / 2;
            }
            case BOTTOM -> {
                var10000 = this.height;
                Objects.requireNonNull(font);
                var10000 -= 9 * this.wrappedLines.size();
            }
            case TOP -> var10000 = 0;
            default -> throw new IncompatibleClassChangeError();
        }

        int yOffset = var10000;

        for (int i = 0; i < this.wrappedLines.size(); ++i) {
            OrderedText line = this.wrappedLines.get(i);
            int c = LibGui.isDarkMode() ? this.darkmodeColor : this.color;
            HorizontalAlignment var10002 = this.horizontalAlignment;
            int var10004 = y + yOffset;
            Objects.requireNonNull(font);
            ScreenDrawing.drawString(matrices, line, var10002, x, var10004 + i * 9, this.width, c);
        }

        Style hoveredTextStyle = this.getTextStyleAt(mouseX, mouseY);
        ScreenDrawing.drawTextHover(matrices, hoveredTextStyle, x + mouseX, y + mouseY);
    }

    @Environment(EnvType.CLIENT)
    public InputResult onClick(int x, int y, int button) {
        if (this.isWithinBounds(x, y)) {
            if (this.onClick != null) {
                this.onClick.run();
            }

            return InputResult.PROCESSED;
        } else {
            return InputResult.IGNORED;
        }
        /*if (button != 0) {
            return InputResult.IGNORED;
        } else {
            Style hoveredTextStyle = this.getTextStyleAt(x, y);
            if (hoveredTextStyle != null) {
                assert MinecraftClient.getInstance().currentScreen != null;
                boolean processed = MinecraftClient.getInstance().currentScreen.handleTextClick(hoveredTextStyle);
                if (this.onClick != null) {
                    this.onClick.run();
                }
                return InputResult.of(processed);
            } else {
                return InputResult.IGNORED;
            }
        }*/
    }

    public void setOnClick(@Nullable Runnable onClick) {
        this.onClick = onClick;
    }

    public Text getText() {
        return this.text;
    }

    public WCustomText setText(Text text) {
        Objects.requireNonNull(text, "text is null");
        this.text = text;
        this.wrappingScheduled = true;
        return this;
    }

    public int getColor() {
        return this.color;
    }

    public WCustomText setColor(int color) {
        this.color = color;
        return this;
    }

    public int getDarkmodeColor() {
        return this.darkmodeColor;
    }

    public void setDarkmodeColor(int darkmodeColor) {
        this.darkmodeColor = darkmodeColor;
    }

    public WCustomText setColor(int color, int darkmodeColor) {
        this.setColor(color);
        this.setDarkmodeColor(darkmodeColor);
        return this;
    }

    public WCustomText disableDarkmode() {
        this.darkmodeColor = this.color;
        return this;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return this.horizontalAlignment;
    }

    public WCustomText setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    public VerticalAlignment getVerticalAlignment() {
        return this.verticalAlignment;
    }

    public WCustomText setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        return this;
    }

    @Environment(EnvType.CLIENT)
    public void addNarrations(NarrationMessageBuilder builder) {
        builder.put(NarrationPart.TITLE, this.text);
    }
}
