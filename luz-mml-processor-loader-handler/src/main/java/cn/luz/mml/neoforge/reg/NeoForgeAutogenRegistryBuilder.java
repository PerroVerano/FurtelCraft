package cn.luz.mml.neoforge.reg;

import cn.luz.mml.ClassUtil;
import cn.luz.mml.RegistryUsageClassEnums;
import com.palantir.javapoet.*;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 这里面放着各类生成的代码
 *
 * @author Luz
 * @since 2025-04-22 周二 上午10:06
 */
public class NeoForgeAutogenRegistryBuilder {

    // ============================ [Maintenance Fields Below] ============================ //
    /**
     * 方块自动注册类的类名
     */
    private static final String BLOCK_AUTOGEN_CLASS_NAME = "AutoGenBlockRegistries";
    /**
     * 物品自动注册类的类名
     */
    private static final String ITEM_AUTOGEN_CLASS_NAME = "AutoGenItemRegistries";
    /**
     * 方块字段名
     */
    private static final String BLOCKS = "BLOCKS";
    /**
     * 物品字段名
     */
    private static final String ITEMS = "ITEMS";
    /**
     * 注册简易方块的赋值表达式模板
     */
    private static final String SIMPLE_BLOCK_REG_TEMPLATE = "$L.register($S, resourceLocation -> new $L($T.of()))";
    /**
     * 注册简易方块物品的赋值表达式模板
     */
    private static final String SIMPLE_BLOCK_ITEM_REG_TEMPLATE = "$L.registerSimpleBlockItem($T.$L)";

    // ============================ [Maintenance Fields Above] ============================ //

    /**
     * 方块列表
     */
    private final Map<String, FieldSpec> blocks;
    /**
     * 物品列表
     */
    private final Map<String, FieldSpec> items;
    /**
     * 自动注册包名
     */
    private final String autoGenPakName;
    /**
     * 模组ID / 模组命名空间
     */
    private final String modId;

    /**
     * 构造器
     *
     * @param modId          模组ID
     * @param autoGenPakName 自动注册类生成所在的包名
     */
    private NeoForgeAutogenRegistryBuilder(String modId, String autoGenPakName) {
        this.modId = modId;
        this.blocks = new LinkedHashMap<>() {{
            put(BLOCKS, FieldSpec.builder(
                    ClassName.get(
                            RegistryUsageClassEnums.NF_DEFERRED_REGISTER$BLOCKS.getPakName(),
                            RegistryUsageClassEnums.NF_DEFERRED_REGISTER$BLOCKS.getSimpleName(),
                            RegistryUsageClassEnums.NF_DEFERRED_REGISTER$BLOCKS.getInternSimpleNames()
                    ),
                    BLOCKS,
                    Modifier.PUBLIC,
                    Modifier.STATIC,
                    Modifier.FINAL
            ).initializer(
                    "$T.createBlocks($S)",
                    ClassName.get(
                            RegistryUsageClassEnums.NF_DEFERRED_REGISTER.getPakName(),
                            RegistryUsageClassEnums.NF_DEFERRED_REGISTER.getSimpleName()
                    ),
                    modId
            ).build());
        }};
        this.items = new LinkedHashMap<>() {{
            put(ITEMS, FieldSpec.builder(
                    ClassName.get(
                            RegistryUsageClassEnums.NF_DEFERRED_REGISTER$ITEMS.getPakName(),
                            RegistryUsageClassEnums.NF_DEFERRED_REGISTER$ITEMS.getSimpleName(),
                            RegistryUsageClassEnums.NF_DEFERRED_REGISTER$ITEMS.getInternSimpleNames()
                    ),
                    ITEMS,
                    Modifier.PUBLIC,
                    Modifier.STATIC,
                    Modifier.FINAL
            ).initializer(
                    "$T.createItems($S)",
                    ClassName.get(
                            RegistryUsageClassEnums.NF_DEFERRED_REGISTER.getPakName(),
                            RegistryUsageClassEnums.NF_DEFERRED_REGISTER.getSimpleName()
                    ),
                    modId
            ).build());
        }};
        this.autoGenPakName = autoGenPakName;
    }

    /**
     * 构造器
     *
     * @param modId          模组ID
     * @param autoGenPakName 自动注册类生成所在的包名
     */
    public static NeoForgeAutogenRegistryBuilder create(String modId, String autoGenPakName) {
        return new NeoForgeAutogenRegistryBuilder(modId, autoGenPakName);
    }

    /**
     * 添加没有物品的方块
     *
     * @param info 注册信息
     * @return builder
     */
    public NeoForgeAutogenRegistryBuilder addBlockWithoutItem(RegistryInfo info) {
        // 方块的各个名称
        String blockSimpleName = ClassUtil.getSimpleName(info.classQualifiedName());
        String blockPakName = ClassUtil.getPackageName(info.classQualifiedName());
        String blockFieldName = ClassUtil.toSnakeCase(ClassUtil.getSimpleName(info.classQualifiedName())).toUpperCase();
        // 方块泛型类字段
        ParameterizedTypeName blockType = ParameterizedTypeName.get(
                ClassName.get(
                        RegistryUsageClassEnums.NF_DEFERRED_BLOCK.getPakName(),
                        RegistryUsageClassEnums.NF_DEFERRED_BLOCK.getSimpleName()
                ),
                ClassName.get(
                        blockPakName,
                        blockSimpleName
                )
        );
        // 定义方块引用字段
        FieldSpec blockRef = FieldSpec.builder(
                        blockType,
                        blockFieldName,
                        Modifier.PUBLIC,
                        Modifier.STATIC,
                        Modifier.FINAL
                ).initializer(
                        SIMPLE_BLOCK_REG_TEMPLATE,
                        BLOCKS,
                        info.registrable().setId(),
                        blockSimpleName,
                        ClassName.get(
                                RegistryUsageClassEnums.NF_MINECRAFT_BLOCK_BEHAVIOUR$PROPERTIES.getPakName(),
                                RegistryUsageClassEnums.NF_MINECRAFT_BLOCK_BEHAVIOUR$PROPERTIES.getSimpleName(),
                                RegistryUsageClassEnums.NF_MINECRAFT_BLOCK_BEHAVIOUR$PROPERTIES.getInternSimpleNames()
                        )
                )
                .build();
        // 添加到列表中
        this.blocks.put(blockFieldName, blockRef);
        return this;
    }

    /**
     * 添加方块
     *
     * @param info 注册信息
     * @return builder
     */
    public NeoForgeAutogenRegistryBuilder addBlock(RegistryInfo info) {
        // 先添加方块
        this.addBlockWithoutItem(info);
        // 方块的字段名称
        String blockFieldName = ClassUtil.toSnakeCase(ClassUtil.getSimpleName(info.classQualifiedName())).toUpperCase();
        // 创建方块物品
        ParameterizedTypeName blockItemType = ParameterizedTypeName.get(
                ClassName.get(
                        RegistryUsageClassEnums.NF_DEFERRED_ITEM.getPakName(),
                        RegistryUsageClassEnums.NF_DEFERRED_ITEM.getSimpleName()
                ),
                ClassName.get(
                        RegistryUsageClassEnums.NF_MINECRAFT_BLOCK_ITEM.getPakName(),
                        RegistryUsageClassEnums.NF_MINECRAFT_BLOCK_ITEM.getSimpleName()
                )
        );
        // 定义方块物品引用字段
        String blockItemFieldName = blockFieldName + "_ITEM";
        FieldSpec blockItemRef = FieldSpec.builder(
                        blockItemType,
                        blockItemFieldName,
                        Modifier.PUBLIC,
                        Modifier.STATIC,
                        Modifier.FINAL
                ).initializer(
                        SIMPLE_BLOCK_ITEM_REG_TEMPLATE,
                        ITEMS,
                        ClassName.get(
                                this.autoGenPakName,
                                BLOCK_AUTOGEN_CLASS_NAME
                        ),
                        blockFieldName
                )
                .build();
        // 添加到列表中
        this.items.put(blockItemFieldName, blockItemRef);
        return this;
    }

    /**
     * 写入文件
     *
     * @param filer filer in annotation process env
     * @throws IOException 写入异常
     */
    public void write(Filer filer) throws IOException {
        // 创建方块自动注册类
        TypeSpec blockAutoGenClass = TypeSpec.classBuilder(BLOCK_AUTOGEN_CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addFields(this.blocks.values())
                .build();
        // 创建方块自动注册类文件
        JavaFile blockAutoGenFile = JavaFile.builder(this.autoGenPakName, blockAutoGenClass).build();
        // 写入
        blockAutoGenFile.writeTo(filer);
        // 创建物品自动注册类
        TypeSpec itemAutoGenClass = TypeSpec.classBuilder(ITEM_AUTOGEN_CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addFields(this.items.values())
                .build();
        // 创建物品自动注册类文件
        JavaFile itemAutoGenFile = JavaFile.builder(this.autoGenPakName, itemAutoGenClass).build();
        // 写入
        itemAutoGenFile.writeTo(filer);
    }
}
