package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.blocks.MagneticParticleProcessor;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {
    //nonOpaque:不完整方块渲染
    public static final Block MAGNETIC_PARTICLE_PROCESSOR = new MagneticParticleProcessor(AbstractBlock.Settings.of(Material.METAL).hardness(2.0F).nonOpaque().requiresTool());
    public static final Item MAGNETIC_PARTICLE_PROCESSOR_ITEM = new BlockItem(MAGNETIC_PARTICLE_PROCESSOR,new FabricItemSettings());


    public static final ItemGroup FC_BLOCK_GROUP = FabricItemGroupBuilder.create(
            new Identifier("furtelcraft","fc_block_group"))
            .icon(() -> new ItemStack(ItemInit.IRON_TRAP))
            .appendItems(itemStacks -> {
                itemStacks.add(new ItemStack(BlockInit.MAGNETIC_PARTICLE_PROCESSOR));
            })
            .build();

    public static void init(){
        Registry.register(Registry.BLOCK,new Identifier("furtelcraft","magnetic_particle_processor"),MAGNETIC_PARTICLE_PROCESSOR);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","magnetic_particle_processor"),MAGNETIC_PARTICLE_PROCESSOR_ITEM);
    }
}
