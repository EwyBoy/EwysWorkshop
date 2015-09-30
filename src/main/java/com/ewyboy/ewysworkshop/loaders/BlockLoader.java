package com.ewyboy.ewysworkshop.loaders;

import com.ewyboy.ewysworkshop.block.BlockWorkshopTable;
import com.ewyboy.ewysworkshop.util.Logger;
import com.ewyboy.ewysworkshop.util.StringMap;
import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

import java.util.concurrent.TimeUnit;

@GameRegistry.ObjectHolder(StringMap.ID)
public final class BlockLoader {

    public static Block workshopTable;
    public static void log(Block block) {Logger.info("  " + block.getUnlocalizedName() + " successfully loaded");}

    public static void loadBlocks() {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading blocks started");
                workshopTable = new BlockWorkshopTable().setBlockName(StringMap.WorkshopTable);
                    GameRegistry.registerBlock(workshopTable, StringMap.WorkshopTable);
                        log(workshopTable);
        Logger.info("Loading blocks finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
    private BlockLoader(){}
}
