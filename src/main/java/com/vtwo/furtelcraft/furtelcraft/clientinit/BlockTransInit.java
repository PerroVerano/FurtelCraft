package com.vtwo.furtelcraft.furtelcraft.clientinit;

import com.vtwo.furtelcraft.furtelcraft.init.BlockInit;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class BlockTransInit {
    public static void init(){
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.MAGNETIC_PARTICLE_PROCESSOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.CENTRIFUGE,RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.TUBE_HOLDER,RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.DNA_MIXER,RenderLayer.getTranslucent());
    }
}
