package cn.luz.mml.neoforge.reg;

/**
 * 注册信息
 *
 * @param classQualifiedName 类的全限定名
 * @param registrable        注册注解
 * @author Luzove El
 * @timestamp 2025年4月12日 15点57分
 */
public record RegistryInfo(String classQualifiedName, NeoForgeRegistrable registrable) {
}
