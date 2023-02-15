package com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.fac;

import com.vtwo.furtelcraft.furtelcraft.contents.fluffybook.imp.IOneWordFactory;
import net.minecraft.text.TranslatableText;

import java.util.Random;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.fluffybook.fac
 * @NAME: OneWordFactory
 * @USER: Perano
 * @DATE: 2023/1/30
 * @TIME: 13:04
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 30
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 13
 * @MINUTE: 04
 * @PROJECT_NAME: furtelcraft
 */
public class OneWordFactory implements IOneWordFactory {
    public OneWordFactory() {

    }

    public static void registry(int index, TranslatableText key) {
        oneWords.put(index, key);
    }

    public static TranslatableText get(int index) {
        return oneWords.get(index);
    }

    public static TranslatableText getRandom() {
        return oneWords.get(new Random().nextInt(oneWords.size()));
    }
}
