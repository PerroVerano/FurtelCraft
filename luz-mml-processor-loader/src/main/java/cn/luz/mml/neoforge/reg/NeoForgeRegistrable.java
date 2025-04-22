package cn.luz.mml.neoforge.reg;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 为NeoForge使用注解式注册
 *
 * @author Luzove El
 * @timestamp 2025年4月12日 15点21分
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface NeoForgeRegistrable {
    /**
     * 注册类型
     */
    NeoForgeRegistrableType type();

    /**
     * 被注册物的资源键
     */
    String setId();

    /**
     * 本注解支持的NeoForge的注册类型
     *
     * @author Luzove El
     * @timestamp 2025年4月12日 15点27分
     */
    enum NeoForgeRegistrableType {
        BLOCK,
        BLOCK_WITHOUT_ITEM,
        ITEM,
        SCREEN
    }
}
