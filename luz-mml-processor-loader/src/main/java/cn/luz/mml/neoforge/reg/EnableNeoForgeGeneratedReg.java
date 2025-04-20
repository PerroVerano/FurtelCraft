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
     * NeoForge的注册后方块引用对象
     */
    Class<?> deferredBlock();

    /**
     * MC原版的方块基类
     */
    Class<?> minecraftBlock();

    /**
     * NeoForge的引用注册类
     */
    Class<?> deferredRegister();

    /**
     * NeoForge的引用注册类中的内部方块类
     */
    Class<?> deferredRegisterBlock();

    /**
     * BlockBehavior.Properties
     */
    Class<?> properties();

    /**
     * ResourceKey
     */
    Class<?> resourceKey();

    /**
     * Registries
     */
    Class<?> registries();
}
