package mod.caedis.aainfo.proxy;

import mod.caedis.aainfo.events.ClientEvents;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        new ClientEvents();
    }
}
