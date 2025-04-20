package cn.luz.furtalcraft.block;

import cn.luz.furtalcraft.Constants;
import cn.luz.mml.neoforge.reg.NeoForgeRegistrable;
import net.minecraft.world.level.block.Block;

@NeoForgeRegistrable(type = NeoForgeRegistrable.NeoForgeRegistrableType.BLOCK, modId = Constants.MOD_ID, setId = "example-block")
public class AnnotationedExempleBlock extends Block {
    public AnnotationedExempleBlock(Properties p_49795_) {
        super(p_49795_);
    }
}
