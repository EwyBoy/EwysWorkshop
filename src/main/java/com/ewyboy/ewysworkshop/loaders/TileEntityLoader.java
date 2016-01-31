package com.ewyboy.ewysworkshop.loaders;

import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import com.ewyboy.ewysworkshop.util.Logger;
import com.ewyboy.ewysworkshop.util.StringMap;
import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.registry.GameRegistry;

import java.util.concurrent.TimeUnit;

@GameRegistry.ObjectHolder(StringMap.ID)
public class TileEntityLoader {

    public static void log(Class tileEntity) {Logger.info("  " + tileEntity + " successfully loaded");}

    public static void loadTileEntities() {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading tile entities started");
                GameRegistry.registerTileEntity(TileEntityTable.class, StringMap.WorkshopTable);
                    log(TileEntityTable.class);
        Logger.info("Loading tile entities finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
}
