package com.cleanroommc.nbtviewer;

import com.cleanroommc.nbtviewer.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = NBTViewer.MODID, name = NBTViewer.NAME, version = NBTViewer.VERSION)
public class NBTViewer {
    public static final String MODID = "nbtviewer";
    public static final String NAME = "NBTViewer";
    public static final String VERSION = "@VERSION@";

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @SidedProxy(clientSide = "com.cleanroommc.nbtviewer.proxy.ClientProxy", serverSide = "com.cleanroommc.nbtviewer.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
