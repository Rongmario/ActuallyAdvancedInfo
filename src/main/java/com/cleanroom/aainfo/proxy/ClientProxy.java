package com.cleanroom.aainfo.proxy;

import com.cleanroom.aainfo.events.ClientEvents;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        new ClientEvents();
    }
}
