package cn.luz.mml.neoforge.reg;

import com.google.auto.service.AutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * NeoForge 注解式注册的处理器
 *
 * @author Luzove El
 * @timestamp 2025年4月12日 15点22分
 * @see EnableNeoForgeGeneratedReg
 * @see NeoForgeRegistrable
 */
@AutoService(Processor.class)
public class NeoForgeRegistrableAnnotationProcessor extends AbstractProcessor {

    /**
     * 日志打印对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NeoForgeRegistrableAnnotationProcessor.class);

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 定义存放着所有需要注册进NeoForge的注册类的信息
        List<RegistryInfo> registryInfos = new ArrayList<>();
        // 方块注册类信息
        RegistryBlockClassInfo blockClassInfo = null;
        // 对该注解处理器支持的注解进行遍历（其实只有辣个注解）
        for (TypeElement annotation : annotations) {
            // 开启注解处理逻辑
            if (annotation.getQualifiedName().contentEquals(EnableNeoForgeGeneratedReg.class.getName())) {
                // 注入方块类的信息
                Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(EnableNeoForgeGeneratedReg.class);
                for (Element element : elements) {
                    EnableNeoForgeGeneratedReg reg = element.getAnnotation(EnableNeoForgeGeneratedReg.class);
                    blockClassInfo = new RegistryBlockClassInfo(
                            this.getClassQualifierNameFromAnnotation(
                                    reg,
                                    enableNeoForgeGeneratedReg -> enableNeoForgeGeneratedReg.deferredBlock().getName()
                                            .replaceAll("\\$", ".")
                            ),
                            this.getClassQualifierNameFromAnnotation(
                                    reg,
                                    enableNeoForgeGeneratedReg -> enableNeoForgeGeneratedReg.minecraftBlock().getName()
                                            .replaceAll("\\$", ".")
                            ),
                            this.getClassQualifierNameFromAnnotation(
                                    reg,
                                    enableNeoForgeGeneratedReg -> enableNeoForgeGeneratedReg.deferredRegister().getName()
                                            .replaceAll("\\$", ".")
                            ),
                            this.getClassQualifierNameFromAnnotation(
                                    reg,
                                    enableNeoForgeGeneratedReg -> enableNeoForgeGeneratedReg.deferredRegisterBlock().getName()
                                            .replaceAll("\\$", ".")
                            ),
                            this.getClassQualifierNameFromAnnotation(
                                    reg,
                                    enableNeoForgeGeneratedReg -> enableNeoForgeGeneratedReg.properties().getName()
                                            .replaceAll("\\$", ".")
                            ),
                            this.getClassQualifierNameFromAnnotation(
                                    reg,
                                    enableNeoForgeGeneratedReg -> enableNeoForgeGeneratedReg.resourceKey().getName()
                                            .replaceAll("\\$", ".")
                            ),
                            this.getClassQualifierNameFromAnnotation(
                                    reg,
                                    enableNeoForgeGeneratedReg -> enableNeoForgeGeneratedReg.registries().getName()
                                            .replaceAll("\\$", ".")
                            )
                    );
                    // 一次循环就结束
                    break;
                }
            } else if (annotation.getQualifiedName().contentEquals(NeoForgeRegistrable.class.getName())) {
                // 获取所有被该注解标注过的类
                Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
                // log一下，轻松98
                LOGGER.info("Starting to process annotated registration classes for NeoForge from: [{}]", LocalDateTime.now());
                // 遍历，获取需要注册的所有信息
                for (Element element : elements) {
                    // 获取被标注（意味深）的类上的注解
                    NeoForgeRegistrable registrable = element.getAnnotation(NeoForgeRegistrable.class);
                    // 进行注解参数判断
                    if (registrable.type() == null || this.strIsEmpty(registrable.setId()) || this.strIsEmpty(registrable.modId())) {
                        // 打印错误日志并跳过该类
                        LOGGER.warn(
                                "Annotated obj {} is annotated wrong args with [{}], has skipped registration processing.",
                                element.getClass(),
                                List.of("type=null", "setId=" + registrable.setId(), "modId=" + registrable.modId())
                        );
                        continue;
                    }
                    // 进行标注类型判断
                    if (!element.getKind().isClass()) {
                        // 打印错误日志并跳过该类
                        LOGGER.warn("Annotated obj {} is not supposed to be a class.", element.getClass());
                        continue;
                    }
                    // 伪  人  验  证  通  过， 进  去  吧
                    registryInfos.add(new RegistryInfo(((TypeElement) element).getQualifiedName().toString().replaceAll("\\$", "."), registrable));
                }
            }
        }
        // 为null就是没有开启自动注册
        if (blockClassInfo == null) {
            return false;
        }
        // 根据注册类型进行分组
        Map<NeoForgeRegistrable.NeoForgeRegistrableType, List<RegistryInfo>> regInfoTypeMap = registryInfos.parallelStream()
                .collect(Collectors.groupingBy(registryInfo -> registryInfo.registrable().type()));
        // 根据各种类型生成注册类
        for (NeoForgeRegistrable.NeoForgeRegistrableType neoForgeRegistrableType : regInfoTypeMap.keySet()) {
            switch (neoForgeRegistrableType) {
                case NeoForgeRegistrable.NeoForgeRegistrableType.BLOCK ->
                        this.generateBlockRegistryClass(registryInfos, blockClassInfo);
                case NeoForgeRegistrable.NeoForgeRegistrableType.ITEM -> this.generateItemRegistryClass();
                default -> {
                    return false;
                }
            }
        }
        return false;
    }

    private void generateBlockRegistryClass(List<RegistryInfo> registryInfos, RegistryBlockClassInfo registryBlockClassInfo) {
        // 没东西？滚出克，没钱玩nmb
        if (registryInfos == null || registryInfos.isEmpty()) {
            return;
        }
        try {
            // 相信所有的注册方块都是放在一个主包下的，对吧……又寸口巴？那我直接拿第一个了
            String pakName = this.getPackageName(registryInfos.getFirst().classQualifiedName()) + ".genedreg";
            // 方块注册类名
            String registryClassName = "AutoGeneratedBlockRegistries";
            // 创建java文件对象
            JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(pakName + "." + registryClassName);
            // 写入类的sb（确信
            StringBuilder sb = new StringBuilder("package ").append(pakName).append(";")
                    .append(System.lineSeparator())
                    .append(System.lineSeparator())
                    .append("public class ").append(registryClassName).append(" {")
                    .append(System.lineSeparator())
                    .append("public static final ").append(registryBlockClassInfo.deferredRegisterBlockQualifier())
                    .append(" BLOCKS = ").append(registryBlockClassInfo.deferredRegisterQualifier()).append(".createBlocks(\"")
                    .append(registryInfos.getFirst().registrable().modId()).append("\");")
                    .append(System.lineSeparator());
            // 写入java文件对象
            try (PrintWriter out = new PrintWriter(fileObject.openWriter())) {
                // 循环类信息，写入注册
                for (RegistryInfo registryInfo : registryInfos) {
                    sb.append("public static final ").append(registryBlockClassInfo.deferredBlockQualifier())
                            .append("<").append(registryInfo.classQualifiedName()).append(">").append(" ")
                            .append(this.toSnakeCase(this.getSimpleName(registryInfo.classQualifiedName())).toUpperCase())
                            .append(" = ").append("BLOCKS.register(")
                            .append("\"").append(registryInfo.registrable().setId()).append("\", ")
                            .append("resourceLocation -> new ").append(registryInfo.classQualifiedName())
                            .append("(").append(registryBlockClassInfo.properties()).append(".of()")
//                            .append(".setId(").append(registryBlockClassInfo.resourceKey()).append(".create(")
//                            .append(registryBlockClassInfo.registries()).append(".BLOCK, resourceLocation))")
                            .append("));");
                }
                out.println(sb);
                out.println("}");
            }
        } catch (IOException e) {
            LOGGER.warn("Failed to generate block registry class because it cant be interactioned with the file system. Here is the stacktrace: \n", e);
        }
    }

    private void generateItemRegistryClass() {

    }

    private void generateScreenRegistryClass() {

    }

    /**
     * 通过全限定名获取类名
     *
     * @param qualifiedName 全限定名
     * @return 类名
     */
    private String getSimpleName(String qualifiedName) {
        return qualifiedName.substring(qualifiedName.lastIndexOf('.') + 1);
    }

    /**
     * 获取类所在的包名
     *
     * @param classQualifiedName 类的全限定名
     * @return 上一级包名
     */
    private String getPackageName(String classQualifiedName) {
        String packageName = classQualifiedName;
        int lastDot = classQualifiedName.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = classQualifiedName.substring(0, lastDot);
        }
        return packageName;
    }

    /**
     * 从注解中获取还未初始化的类名
     *
     * @param annotation 注解
     * @param sup        注解getter
     * @return 类全限定名
     */
    private <T extends Annotation> String getClassQualifierNameFromAnnotation(T annotation, Function<T, String> sup) {
        try {
            return sup.apply(annotation);
        } catch (MirroredTypeException e) {
            TypeMirror typeMirror = e.getTypeMirror();
            return typeMirror.toString();
        }
    }


    /**
     * 大、小驼峰命名法转下划线命名法
     *
     * @param str 大、小驼峰命名法字符串
     * @return 下划线命名法字符串
     * @author Luz
     * @since 2024/7/24 下午5:00
     **/
    private String toSnakeCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        // 使用正则表达式按照大写字符分割后再拼接
        return String.join("_", str.split("(?=[A-Z])")).toLowerCase();
    }

    /**
     * 字符串判空
     *
     * @param str 字符串
     * @return 是否为空字符串
     */
    private boolean strIsEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 定义当前的注解处理器能够处理那些注解类型
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        // 啊~注册注解放进去
        return Set.of(EnableNeoForgeGeneratedReg.class.getName(), NeoForgeRegistrable.class.getName());
    }

    /**
     * 该注解处理器能够支持的最新的版本号
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        // 默认支持到最新版本
        return SourceVersion.latestSupported();
    }

    /**
     * 注册信息
     *
     * @param classQualifiedName 类的全限定名
     * @param registrable        注册注解
     * @author Luzove El
     * @timestamp 2025年4月12日 15点57分
     */
    private record RegistryInfo(String classQualifiedName, NeoForgeRegistrable registrable) {
    }

    /**
     * NeoForge注册方块类相关的信息
     *
     * @param deferredBlockQualifier         NeoForge的注册后方块引用对象
     * @param minecraftBlockQualifier        MC原版的方块基类
     * @param deferredRegisterQualifier      NeoForge的引用注册类
     * @param deferredRegisterBlockQualifier NeoForge的引用注册类中的内部方块类
     * @param properties                     BlockBehavior.Properties
     */
    private record RegistryBlockClassInfo(String deferredBlockQualifier, String minecraftBlockQualifier,
                                          String deferredRegisterQualifier, String deferredRegisterBlockQualifier,
                                          String properties, String resourceKey, String registries) {
    }
}
