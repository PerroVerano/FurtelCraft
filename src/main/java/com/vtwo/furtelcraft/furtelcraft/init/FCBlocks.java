package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.blocks.*;
import com.vtwo.furtelcraft.furtelcraft.blocks.entity.*;
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

public class FCBlocks {
    //nonOpaque:不完整方块渲染
    public static final Identifier M_P_P = new Identifier(MOD_ID, "magnetic_particle_processor");
    public static final Identifier CENTRIFUGE_ID = new Identifier(MOD_ID, "centrifuge");
    public static final Identifier TUBE_HOLDER_ID = new Identifier(MOD_ID, "tube_holder");
    public static final Identifier DNA_MIXER_ID = new Identifier(MOD_ID, "dna_mixer");
    public static final Identifier RACK_ID = new Identifier(MOD_ID, "rack");
    public static final Identifier INCUBATOR_ID = new Identifier(MOD_ID, "incubator");
    //===============//
    public static final MagneticParticleProcessor MAGNETIC_PARTICLE_PROCESSOR = new MagneticParticleProcessor(AbstractBlock.Settings.of(Material.METAL).nonOpaque().requiresTool().strength(5.0F, 8.0F));
    public static final BlockItem MAGNETIC_PARTICLE_PROCESSOR_ITEM = new BlockItem(MAGNETIC_PARTICLE_PROCESSOR, new FabricItemSettings());
    public static final BlockEntityType<MagneticParticleProcessorEntity> MAGNETIC_PARTICLE_PROCESSOR_ENTITY = FabricBlockEntityTypeBuilder.create(MagneticParticleProcessorEntity::new, MAGNETIC_PARTICLE_PROCESSOR).build(null);
    //================//
    public static final Centrifuge CENTRIFUGE = new Centrifuge(AbstractBlock.Settings.of(Material.METAL).nonOpaque().requiresTool().strength(5.0F, 8.0F));
    public static final BlockItem CENTRIFUGE_ITEM = new BlockItem(CENTRIFUGE, new FabricItemSettings());
    public static final BlockEntityType<CentrifugeEntity> CENTRIFUGE_ENTITY = FabricBlockEntityTypeBuilder.create(CentrifugeEntity::new, CENTRIFUGE).build(null);
    //================//
    public static final TubeHolder TUBE_HOLDER = new TubeHolder(AbstractBlock.Settings.of(Material.METAL).nonOpaque().requiresTool().strength(4.0F, 6.0F));
    public static final BlockItem TUBE_HOLDER_ITEM = new BlockItem(TUBE_HOLDER, new FabricItemSettings());
    public static final BlockEntityType<TubeHolderEntity> TUBE_HOLDER_ENTITY = FabricBlockEntityTypeBuilder.create(TubeHolderEntity::new, TUBE_HOLDER).build(null);
    //================//
    public static final DNAMixer DNA_MIXER = new DNAMixer(AbstractBlock.Settings.of(Material.METAL).nonOpaque().requiresTool().strength(5.0F, 8.0F));
    public static final BlockItem DNA_MIXER_ITEM = new BlockItem(DNA_MIXER, new FabricItemSettings());
    public static final BlockEntityType<DNAMixerEntity> DNA_MIXER_ENTITY = FabricBlockEntityTypeBuilder.create(DNAMixerEntity::new, DNA_MIXER).build(null);
    //================//
    public static final Rack RACK = new Rack(AbstractBlock.Settings.of(Material.METAL).nonOpaque().requiresTool().strength(5.0F, 8.0F));
    public static final BlockItem RACK_ITEM = new BlockItem(RACK, new FabricItemSettings());
    public static final BlockEntityType<RackEntity> RACK_ENTITY = FabricBlockEntityTypeBuilder.create(RackEntity::new, RACK).build(null);
    //================//
    public static final Incubator INCUBATOR = new Incubator(AbstractBlock.Settings.of(Material.METAL).nonOpaque().requiresTool().strength(5.0F, 8.0F));
    public static final BlockItem INCUBATOR_ITEM = new BlockItem(INCUBATOR, new FabricItemSettings());
    public static final BlockEntityType<IncubatorEntity> INCUBATOR_ENTITY = FabricBlockEntityTypeBuilder.create(IncubatorEntity::new, INCUBATOR).build(null);


    public static final ItemGroup FC_BLOCK_GROUP = FabricItemGroupBuilder.create(
                    new Identifier("furtelcraft", "fc_block_group"))
            .icon(() -> new ItemStack(CENTRIFUGE_ITEM))
            .appendItems(itemStacks -> {
                itemStacks.add(new ItemStack(MAGNETIC_PARTICLE_PROCESSOR_ITEM));
                itemStacks.add(new ItemStack(CENTRIFUGE_ITEM));
                itemStacks.add(new ItemStack(TUBE_HOLDER_ITEM));
                itemStacks.add(new ItemStack(DNA_MIXER_ITEM));
                itemStacks.add(new ItemStack(RACK_ITEM));
                itemStacks.add(new ItemStack(INCUBATOR_ITEM));
            })
            .build();

    public static void init() {
        Registry.register(Registry.BLOCK, M_P_P, MAGNETIC_PARTICLE_PROCESSOR);
        Registry.register(Registry.ITEM, M_P_P, MAGNETIC_PARTICLE_PROCESSOR_ITEM);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, M_P_P, MAGNETIC_PARTICLE_PROCESSOR_ENTITY);

        Registry.register(Registry.BLOCK, CENTRIFUGE_ID, CENTRIFUGE);
        Registry.register(Registry.ITEM, CENTRIFUGE_ID, CENTRIFUGE_ITEM);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, CENTRIFUGE_ID, CENTRIFUGE_ENTITY);

        Registry.register(Registry.BLOCK, TUBE_HOLDER_ID, TUBE_HOLDER);
        Registry.register(Registry.ITEM, TUBE_HOLDER_ID, TUBE_HOLDER_ITEM);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, TUBE_HOLDER_ID, TUBE_HOLDER_ENTITY);

        Registry.register(Registry.BLOCK, DNA_MIXER_ID, DNA_MIXER);
        Registry.register(Registry.ITEM, DNA_MIXER_ID, DNA_MIXER_ITEM);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, DNA_MIXER_ID, DNA_MIXER_ENTITY);

        Registry.register(Registry.BLOCK, RACK_ID, RACK);
        Registry.register(Registry.ITEM, RACK_ID, RACK_ITEM);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, RACK_ID, RACK_ENTITY);

        Registry.register(Registry.BLOCK, INCUBATOR_ID, INCUBATOR);
        Registry.register(Registry.ITEM, INCUBATOR_ID, INCUBATOR_ITEM);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, INCUBATOR_ID, INCUBATOR_ENTITY);
    }
}
