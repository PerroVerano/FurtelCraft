package cn.luz.mml.neoforge.reg;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用NeoForge的自动注解式注册功能，要放在模组主类上
 *
 * @author Luzove El
 * @since 2025-04-12 周六 下午5:17
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface EnableNeoForgeGeneratedReg {
    /**
     * 模组ID / 模组命名空间
     */
    String modId();
}
