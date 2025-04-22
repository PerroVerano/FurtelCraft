package cn.luz.mml;

/**
 * 嗯，RegistryUsageClassEnums！这是什么？
 *
 * @author Luz
 * @since 2025-04-22 周二 上午9:16
 */
public enum RegistryUsageClassEnums {

    // ============================ [For NeoForge, aka. NF prefix, Side] ============================ //

    /**
     * NeoForge的注册后方块引用对象
     */
    NF_DEFERRED_BLOCK("net.neoforged.neoforge.registries.DeferredBlock"),
    /**
     * NeoForge的注册后物品引用对象
     */
    NF_DEFERRED_ITEM("net.neoforged.neoforge.registries.DeferredItem"),
    /**
     * MC原版的方块基类（NeoForge Mapping）
     */
    NF_MINECRAFT_BLOCK("net.minecraft.world.level.block.Block"),
    /**
     * MC原版的方块物品基类（NeoForge Mapping）
     */
    NF_MINECRAFT_BLOCK_ITEM("net.minecraft.world.item.BlockItem"),
    /**
     * NeoForge的引用注册类
     */
    NF_DEFERRED_REGISTER("net.neoforged.neoforge.registries.DeferredRegister"),
    /**
     * NeoForge的引用注册类中的内部方块类
     */
    NF_DEFERRED_REGISTER$BLOCKS("net.neoforged.neoforge.registries.DeferredRegister$Blocks"),
    /**
     * NeoForge的引用注册类中的内部物品类
     */
    NF_DEFERRED_REGISTER$ITEMS("net.neoforged.neoforge.registries.DeferredRegister$Items"),
    /**
     * MC原版方块行为类中的属性内部类（NeoForge Mapping）
     */
    NF_MINECRAFT_BLOCK_BEHAVIOUR$PROPERTIES("net.minecraft.world.level.block.state.BlockBehaviour$Properties"),
    /**
     * MC原版资源键（NeoForge Mapping）
     */
    NF_MINECRAFT_RESOURCE_KEY("net.minecraft.resources.ResourceKey"),
    /**
     * MC原版注册类（NeoForge Mapping）
     */
    NF_MINECRAFT_REGISTRIES("net.minecraft.core.registries.Registries"),

    // ============================ [For Fabric, aka. FB prefix, Side] ============================ //


    // ============================ [For Forge, aka. FG prefix, Side] ============================ //


    ;

    // ============================ [Fields Below] ============================ //

    /**
     * 类的全限定名（点制）
     */
    private final String qualifierName;
    /**
     * 所在包名
     */
    private final String pakName;
    /**
     * 类名
     */
    private final String simpleName;
    /**
     * 内部类列表
     */
    private final String[] internSimpleNames;

    // ============================ [Constructor Below] ============================ //

    /**
     * 构造器
     *
     * @param qualifierName 类的全限定名（点$制）
     */
    RegistryUsageClassEnums(String qualifierName) {
        this.qualifierName = qualifierName.replaceAll("\\$", ".");
        this.pakName = ClassUtil.getPackageName(qualifierName);
        String[] simpleNameSplit = ClassUtil.getSimpleName(qualifierName).split("\\.");
        this.simpleName = simpleNameSplit[0];
        String[] internNames = new String[simpleNameSplit.length - 1];
        System.arraycopy(simpleNameSplit, 1, internNames, 0, simpleNameSplit.length - 1);
        this.internSimpleNames = internNames;
    }


    // ============================ [Getters Below] ============================ //


    public String getQualifierName() {
        return this.qualifierName;
    }

    public String getPakName() {
        return this.pakName;
    }

    public String getSimpleName() {
        return this.simpleName;
    }

    public String[] getInternSimpleNames() {
        return this.internSimpleNames;
    }
}
