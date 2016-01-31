package com.ewyboy.ewysworkshop.dependencies.waila;

import com.ewyboy.ewysworkshop.block.BlockWorkshopTable;
import com.ewyboy.ewysworkshop.util.Logger;
import com.google.common.base.Stopwatch;
import mcp.mobius.waila.api.IWailaRegistrar;

import java.util.concurrent.TimeUnit;

public class Waila {
    public static void onWailaCall(IWailaRegistrar registrar) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Found " + mcp.mobius.waila.Waila.instance);
                Logger.info("Loading Waila features");
                    registrar.registerStackProvider(new WailaWorkshop(), BlockWorkshopTable.class);
                    registrar.registerBodyProvider(new WailaWorkshop(), BlockWorkshopTable.class);
                    registrar.registerNBTProvider(new WailaWorkshop(), BlockWorkshopTable.class);
        Logger.info("Waila features finished loading after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
}
