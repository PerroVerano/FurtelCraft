package com.vtwo.furtelcraft.furtelcraft.libvne.widgets;

import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.libvne.widgets
 * @NAME: NullWidget
 * @USER: Perano
 * @DATE: 2023/1/11
 * @TIME: 14:21
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 11
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 14
 * @MINUTE: 21
 * @PROJECT_NAME: furtelcraft
 */
public class NullWidget extends BasedWidget {
    protected int textureWidth;
    protected int textureHeight;
    protected int NullWidth;
    protected int NullHeight;
    protected int iNullPanel;
    protected int jNullPanel;
    protected Color textColor;
    protected final PressAction onPress;
    protected final TooltipSupplier tooltipSupplier;
    private Text message;

    public NullWidget(int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Text message, @Nullable Color textColor, @Nullable BasedWidget.PressAction onPress, @Nullable BasedWidget.TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, textureWidth, textureHeight, message, textColor, onPress, tooltipSupplier);
        this.NullWidth = width;
        this.NullHeight = height;
        this.iNullPanel = x;
        this.jNullPanel = y;
        this.message = message;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.textColor = textColor;
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
    }
}
