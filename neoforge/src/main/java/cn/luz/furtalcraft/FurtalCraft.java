package cn.luz.furtalcraft;


import cn.luz.furtalcraft.reg.AutoGenBlockRegistries;
import cn.luz.furtalcraft.reg.AutoGenItemRegistries;
import cn.luz.mml.neoforge.reg.EnableNeoForgeGeneratedReg;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
@EnableNeoForgeGeneratedReg(modId = Constants.MOD_ID)
public class FurtalCraft {

    public FurtalCraft(IEventBus eventBus) {
        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        Constants.LOG.info("Hello NeoForge world!");
        CommonClass.init();
        AutoGenBlockRegistries.BLOCKS.register(eventBus);
        AutoGenItemRegistries.ITEMS.register(eventBus);
    }
}
