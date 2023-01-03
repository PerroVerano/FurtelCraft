package com.vtwo.furtelcraft.furtelcraft.stories;

import net.minecraft.text.TranslatableText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.stories
 * @NAME: OneWord
 * @USER: Perano
 * @DATE: 2023/1/2
 * @TIME: 13:42
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 02
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 13
 * @MINUTE: 42
 * @PROJECT_NAME: furtelcraft
 */
public class OneWord {

    public static List<TranslatableText> getOneWordList() {
        TranslatableText word1 = new TranslatableText("text.furtelcraft.oneword1");
        TranslatableText word2 = new TranslatableText("text.furtelcraft.oneword2");
        TranslatableText word3 = new TranslatableText("text.furtelcraft.oneword3");
        TranslatableText word4 = new TranslatableText("text.furtelcraft.oneword4");
        TranslatableText word5 = new TranslatableText("text.furtelcraft.oneword5");

        TranslatableText[] word = new TranslatableText[]{
                word1,
                word2,
                word3,
                word4,
                word5
        };

        return new ArrayList<>(Arrays.asList(word).subList(0, word.length));
    }

    public static TranslatableText getAssignOneWord(int index) {
        return getOneWordList().get(index);
    }

    public static TranslatableText getRandomOneWord() {
        return getOneWordList().get(new Random().nextInt(getOneWordList().size()));
    }
}
