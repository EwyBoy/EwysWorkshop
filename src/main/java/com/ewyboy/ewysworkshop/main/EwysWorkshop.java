package com.ewyboy.ewysworkshop.main;

import com.ewyboy.ewysworkshop.gui.GuiHandler;
import com.ewyboy.ewysworkshop.loaders.*;
import com.ewyboy.ewysworkshop.nei.INEICallback;
import com.ewyboy.ewysworkshop.network.PacketHandler;
import com.ewyboy.ewysworkshop.util.Logger;
import com.ewyboy.ewysworkshop.util.StringMap;
import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;

import java.util.concurrent.TimeUnit;

@Mod(modid = StringMap.ID, name = StringMap.Name, version = StringMap.VersionBuildName, acceptedMinecraftVersions = "["+StringMap.MinecraftVersion+"]")
public class EwysWorkshop {

    public static INEICallback nei;
    public static FMLEventChannel packetHandler;

    @Mod.Instance(StringMap.ID)
    public static EwysWorkshop instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Pre-Initialization started");
                packetHandler = NetworkRegistry.INSTANCE.newEventDrivenChannel(StringMap.Channel);
                new CreativeTabLoader();
                BlockLoader.loadBlocks();
                ItemLoader.loadItems();
                TileEntityLoader.loadTileEntities();
                ConfigLoader.init(event.getSuggestedConfigurationFile());
                FMLInterModComms.sendMessage("Waila", "register", "com.ewyboy.ewysworkshop.waila.Waila.onWailaCall");
            Logger.info("Pre-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms )");
        Logger.info("Pre-Initialization process successfully done");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Initialization started");
                NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
                packetHandler.register(new PacketHandler());
            Logger.info("Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms )");
        Logger.info("Initialization process successfully done");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Post-Initialization started");
                RecipeLoader.loadRecipes();
            Logger.info("Post-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms )");
        Logger.info("Post-Initialization process successfully done");
    }
}
