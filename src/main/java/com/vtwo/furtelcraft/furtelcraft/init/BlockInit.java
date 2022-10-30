package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.blockentities.MagneticParticleProcessorEntity;
import com.vtwo.furtelcraft.furtelcraft.blocks.Centrifuge;
import com.vtwo.furtelcraft.furtelcraft.blocks.MagneticParticleProcessor;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class BlockInit {
    //nonOpaque:不完整方块渲染
    public static final Identifier M_P_P = new Identifier(MOD_ID,"magnetic_particle_processor");
    public static final Identifier CENTRIFUGE_ID = new Identifier(MOD_ID,"centrifuge");
    //===============//
    public static final MagneticParticleProcessor MAGNETIC_PARTICLE_PROCESSOR = new MagneticParticleProcessor(AbstractBlock.Settings.of(Material.METAL).nonOpaque().requiresTool().strength(5.0F,8.0F));
    public static final BlockItem MAGNETIC_PARTICLE_PROCESSOR_ITEM = new BlockItem(MAGNETIC_PARTICLE_PROCESSOR,new FabricItemSettings());
    public static final BlockEntityType<MagneticParticleProcessorEntity> MAGNETIC_PARTICLE_PROCESSOR_ENTITY = FabricBlockEntityTypeBuilder.create(MagneticParticleProcessorEntity::new,MAGNETIC_PARTICLE_PROCESSOR).build(null);
    //================//
    public static final Centrifuge CENTRIFUGE = new Centrifuge(AbstractBlock.Settings.of(Material.METAL).nonOpaque().requiresTool().strength(5.0F,8.0F));
    public static final BlockItem CENTRIFUGE_ITEM = new BlockItem(CENTRIFUGE,new FabricItemSettings());


    public static final ItemGroup FC_BLOCK_GROUP = FabricItemGroupBuilder.create(
            new Identifier("furtelcraft","fc_block_group"))
            .icon(() -> new ItemStack(BlockInit.CENTRIFUGE_ITEM))
            .appendItems(itemStacks -> {
                itemStacks.add(new ItemStack(BlockInit.MAGNETIC_PARTICLE_PROCESSOR_ITEM));
                itemStacks.add(new ItemStack(BlockInit.CENTRIFUGE_ITEM));
            })
            .build();

    public static void init(){
        Registry.register(Registry.BLOCK,M_P_P,MAGNETIC_PARTICLE_PROCESSOR);
        Registry.register(Registry.ITEM,M_P_P,MAGNETIC_PARTICLE_PROCESSOR_ITEM);
        Registry.register(Registry.BLOCK_ENTITY_TYPE,M_P_P,MAGNETIC_PARTICLE_PROCESSOR_ENTITY);

        Registry.register(Registry.BLOCK,CENTRIFUGE_ID,CENTRIFUGE);
        Registry.register(Registry.ITEM,CENTRIFUGE_ID,CENTRIFUGE_ITEM);
    }
}
