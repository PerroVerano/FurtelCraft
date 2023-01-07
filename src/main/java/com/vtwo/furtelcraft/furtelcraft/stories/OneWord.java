package com.vtwo.furtelcraft.furtelcraft.stories;

import net.minecraft.text.Text;

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

    public static List<Text> getOneWordList() {
        Text word1 = Text.translatable("text.furtelcraft.oneword1");
        Text word2 = Text.translatable("text.furtelcraft.oneword2");
        Text word3 = Text.translatable("text.furtelcraft.oneword3");
        Text word4 = Text.translatable("text.furtelcraft.oneword4");
        Text word5 = Text.translatable("text.furtelcraft.oneword5");
        Text word6 = Text.translatable("text.furtelcraft.oneword6");
        Text word7 = Text.translatable("text.furtelcraft.oneword7");

        Text[] word = new Text[]{
                word1,
                word2,
                word3,
                word4,
                word5,
                word6,
                word7
        };

        return new ArrayList<>(Arrays.asList(word).subList(0, word.length));
    }

    public static Text getAssignOneWord(int index) {
        return getOneWordList().get(index);
    }

    public static Text getRandomOneWord() {
        return getOneWordList().get(new Random().nextInt(getOneWordList().size()));
    }
}
