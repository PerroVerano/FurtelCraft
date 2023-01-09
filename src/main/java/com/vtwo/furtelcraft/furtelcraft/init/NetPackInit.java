package com.vtwo.furtelcraft.furtelcraft.init;

import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.init
 * @NAME: NetPackInit
 * @USER: Perano
 * @DATE: 2023/1/9
 * @TIME: 19:46
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 09
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 19
 * @MINUTE: 46
 * @PROJECT_NAME: furtelcraft
 */
public class NetPackInit {
    public static Identifier PACKET_GUIDE_BOOK_ID;

    public static void init() {
        PACKET_GUIDE_BOOK_ID = new Identifier(MOD_ID, "packet_guide_book_id");
    }
}
