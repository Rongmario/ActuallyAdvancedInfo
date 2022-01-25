package com.cleanroommc.nbtviewer.proxy;

import com.cleanroommc.nbtviewer.events.ClientEvents;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        new ClientEvents();
    }
}
