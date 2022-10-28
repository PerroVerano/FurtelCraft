package com.vtwo.furtelcraft.furtelcraft.blocktrans;

import com.vtwo.furtelcraft.furtelcraft.init.BlockInit;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class BlockTransInit {
    public static void init(){
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.MAGNETIC_PARTICLE_PROCESSOR, RenderLayer.getTranslucent());
    }
}
