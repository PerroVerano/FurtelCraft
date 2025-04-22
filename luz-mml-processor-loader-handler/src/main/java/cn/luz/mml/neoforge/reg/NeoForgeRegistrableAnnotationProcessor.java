package cn.luz.mml.neoforge.reg;

import cn.luz.mml.ClassUtil;
import com.google.auto.service.AutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        // 先遍历一遍看看有没有启用注解式注册
        boolean isAnnotationRegEnabled = false;
        String modId = null;
        for (TypeElement annotation : annotations) {
            if (annotation.getQualifiedName().contentEquals(EnableNeoForgeGeneratedReg.class.getName())) {
                // 注入方块类的信息
                Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(EnableNeoForgeGeneratedReg.class);
                for (Element element : elements) {
                    EnableNeoForgeGeneratedReg reg = element.getAnnotation(EnableNeoForgeGeneratedReg.class);
                    if (reg != null) {
                        // 一次循环就结束
                        isAnnotationRegEnabled = true;
                        modId = reg.modId();
                        break;
                    }
                }
            }
        }
        // 没有启用，退出方法
        if (!isAnnotationRegEnabled) {
            return false;
        }
        if (ClassUtil.strIsEmpty(modId)) {
            LOGGER.warn("Mod ID was null or empty, and this is not to be, processor will skip generate autogen class.");
            return false;
        }
        // 对该注解处理器支持的注解进行遍历（其实只有辣个注解）
        for (TypeElement annotation : annotations) {
            // 开启注解处理逻辑
            if (annotation.getQualifiedName().contentEquals(NeoForgeRegistrable.class.getName())) {
                // 获取所有被该注解标注过的类
                Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
                // log一下，轻松98
                LOGGER.info("Starting to process annotated registration classes for NeoForge from: [{}]", LocalDateTime.now());
                // 遍历，获取需要注册的所有信息
                for (Element element : elements) {
                    // 获取被标注（意味深）的类上的注解
                    NeoForgeRegistrable registrable = element.getAnnotation(NeoForgeRegistrable.class);
                    // 进行注解参数判断
                    if (registrable.type() == null || ClassUtil.strIsEmpty(registrable.setId())) {
                        // 打印错误日志并跳过该类
                        LOGGER.warn(
                                "Annotated obj {} is annotated wrong args with [{}], has skipped registration processing.",
                                element.getClass(),
                                List.of("type=null", "setId=" + registrable.setId())
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
                    registryInfos.add(new RegistryInfo(
                            ((TypeElement) element).getQualifiedName().toString().replaceAll("\\$", "."),
                            registrable
                    ));
                }
            }
        }
        // 根据注册类型进行分组
        Map<NeoForgeRegistrable.NeoForgeRegistrableType, List<RegistryInfo>> regInfoTypeMap = registryInfos.parallelStream()
                .collect(Collectors.groupingBy(registryInfo -> registryInfo.registrable().type()));
        // 根据各种类型生成注册类
        NeoForgeAutogenRegistryBuilder registryBuilder = NeoForgeAutogenRegistryBuilder.create(
                modId,
                ClassUtil.getRootPackageName(registryInfos.getFirst().classQualifiedName()) + ".reg"
        );
        for (NeoForgeRegistrable.NeoForgeRegistrableType neoForgeRegistrableType : regInfoTypeMap.keySet()) {
            switch (neoForgeRegistrableType) {
                case NeoForgeRegistrable.NeoForgeRegistrableType.BLOCK ->
                        registryInfos.forEach(registryBuilder::addBlock);
//                case NeoForgeRegistrable.NeoForgeRegistrableType.ITEM -> registryInfos.forEach(registryBuilder::addItem);
                default -> {
                    return false;
                }
            }
        }
        // 写入
        try {
            registryBuilder.write(processingEnv.getFiler());
        } catch (IOException e) {
            LOGGER.warn("Writing auto generated class file error, the annotation processor will skip this, here is the stacktrace: \n", e);
            return false;
        }
        return false;
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
}
