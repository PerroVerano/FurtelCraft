package com.vtwo.furtelcraft.furtelcraft.init.client;

import com.vtwo.furtelcraft.furtelcraft.init.FCBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class FCBlockTrans {
    public static void init() {
        BlockRenderLayerMap.INSTANCE.putBlock(FCBlocks.MAGNETIC_PARTICLE_PROCESSOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(FCBlocks.CENTRIFUGE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(FCBlocks.TUBE_HOLDER, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(FCBlocks.DNA_MIXER, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(FCBlocks.RACK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(FCBlocks.INCUBATOR, RenderLayer.getTranslucent());
    }
}
