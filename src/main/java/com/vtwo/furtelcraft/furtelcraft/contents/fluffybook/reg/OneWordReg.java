package com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.reg;

import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.fac.OneWordFactory;
import net.minecraft.text.TranslatableText;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook.reg
 * @NAME: OneWordReg
 * @USER: Perano
 * @DATE: 2023/1/30
 * @TIME: 13:05
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 30
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 13
 * @MINUTE: 05
 * @PROJECT_NAME: furtelcraft
 */
public class OneWordReg {
    public OneWordReg() {
        OneWordFactory.registry(0, new TranslatableText("text.furtelcraft.oneword1"));
        OneWordFactory.registry(1, new TranslatableText("text.furtelcraft.oneword2"));
        OneWordFactory.registry(2, new TranslatableText("text.furtelcraft.oneword3"));
        OneWordFactory.registry(3, new TranslatableText("text.furtelcraft.oneword4"));
        OneWordFactory.registry(4, new TranslatableText("text.furtelcraft.oneword5"));
        OneWordFactory.registry(5, new TranslatableText("text.furtelcraft.oneword6"));
        OneWordFactory.registry(6, new TranslatableText("text.furtelcraft.oneword7"));
    }
}
