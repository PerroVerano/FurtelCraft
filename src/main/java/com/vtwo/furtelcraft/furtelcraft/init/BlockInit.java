package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.blocks.Centrifuge;
import com.vtwo.furtelcraft.furtelcraft.blocks.MagneticParticleProcessor;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {
    //nonOpaque:不完整方块渲染
    public static final MagneticParticleProcessor MAGNETIC_PARTICLE_PROCESSOR = new MagneticParticleProcessor(AbstractBlock.Settings.of(Material.METAL).nonOpaque().requiresTool().strength(5.0F,8.0F));
    public static final BlockItem MAGNETIC_PARTICLE_PROCESSOR_ITEM = new BlockItem(MAGNETIC_PARTICLE_PROCESSOR,new FabricItemSettings());
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
        Registry.register(Registry.BLOCK,new Identifier("furtelcraft","magnetic_particle_processor"),MAGNETIC_PARTICLE_PROCESSOR);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","magnetic_particle_processor"),MAGNETIC_PARTICLE_PROCESSOR_ITEM);
        Registry.register(Registry.BLOCK,new Identifier("furtelcraft","centrifuge"),CENTRIFUGE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","centrifuge"),CENTRIFUGE_ITEM);
    }
}
