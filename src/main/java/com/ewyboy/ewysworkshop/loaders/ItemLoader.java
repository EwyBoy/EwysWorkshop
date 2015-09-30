package com.ewyboy.ewysworkshop.loaders;

import com.ewyboy.ewysworkshop.item.ItemUpgrade;
import com.ewyboy.ewysworkshop.util.Logger;
import com.ewyboy.ewysworkshop.util.StringMap;
import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.registry.GameRegistry;

import java.util.concurrent.TimeUnit;

@GameRegistry.ObjectHolder(StringMap.ID)
public final class ItemLoader {
    public static ItemUpgrade upgrade;
    public static void log(ItemUpgrade item) {Logger.info("  " + item.getUnlocalizedName() + " successfully loaded");}

    public static void loadItems() {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading items started");
                upgrade = new ItemUpgrade();
                    upgrade.setUnlocalizedName(StringMap.WorkshopTableUpgrade);
                        GameRegistry.registerItem(upgrade, StringMap.WorkshopTableUpgrade);
                    log(upgrade);
        Logger.info("Loading items finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
    private ItemLoader() {}
}

