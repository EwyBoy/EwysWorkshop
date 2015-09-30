package com.ewyboy.ewysworkshop.loaders;

import com.ewyboy.ewysworkshop.item.Upgrade;
import com.ewyboy.ewysworkshop.util.Logger;
import com.ewyboy.ewysworkshop.util.StringMap;
import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.concurrent.TimeUnit;

@GameRegistry.ObjectHolder(StringMap.ID)
public final class ConfigLoader {

    private static final String UPGRADES = "Upgrades";
    private static final String MAX_COUNT_SUFFIX = ".max_count";

    public static int MAX_POWER,MAX_LAVA,MAX_LAVA_DRAIN,LAVA_EFFICIENCY,SOLAR_GENERATION,FUEL_DELAY;

    public static void init(File file) {
        String spacing = "  ";
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading configs started");
                Configuration config = new Configuration(file);
                Logger.info(spacing + "Reading " + config);
                    config.load();
                        MAX_POWER = config.getInt("Max Energy", StringMap.ConfigCategoryTweaks, 30000, 0, Integer.MAX_VALUE, "Sets the max number of energy storage in the workshop table");
                            Logger.info(spacing + "Max Power = " + MAX_POWER);
                        MAX_LAVA = config.getInt("Max Lava", StringMap.ConfigCategoryTweaks, 1000, 0, Integer.MAX_VALUE, "Sets the max number of lava energy storage in the workshop table");
                            Logger.info(spacing + "Max Lava = " + MAX_LAVA);
                        MAX_LAVA_DRAIN = config.getInt("Max Lava Drain", StringMap.ConfigCategoryTweaks, 30, 0, Integer.MAX_VALUE, "Sets the max number of lava drained from the workshop table");
                            Logger.info(spacing + "Max Lava Drain = " + MAX_LAVA_DRAIN);
                        LAVA_EFFICIENCY = config.getInt("Max Efficiency", StringMap.ConfigCategoryTweaks, 12, 0, Integer.MAX_VALUE, "Sets the lava efficacy in the workshop table");
                            Logger.info(spacing + "Max Efficiency = " + LAVA_EFFICIENCY);
                        SOLAR_GENERATION = config.getInt("Solar Generation", StringMap.ConfigCategoryTweaks, 4, 0, Integer.MAX_VALUE, "Sets the amount of energy generated per tick with solar panel upgrade in the workshop table");
                            Logger.info(spacing + "Solar Generation = " + SOLAR_GENERATION);
                        FUEL_DELAY = config.getInt("Fuel Delay", StringMap.ConfigCategoryTweaks, 15, 0, Integer.MAX_VALUE, "Sets the amount of ticks between each time the worktable consumes a fuel resource");
                            Logger.info(spacing + "Fuel Delay = " + FUEL_DELAY);
                        for (Upgrade upgrade : Upgrade.values()) {
                            Upgrade.MaxCount max = upgrade.getMaxCountObject();
                            if (max.getConfigurableMax() > 0) {
                                upgrade.getMaxCountObject().setMax(config.getInt(upgrade.getUnlocalizedName() + MAX_COUNT_SUFFIX, UPGRADES, max.getMax(), 0, max.getConfigurableMax(), "Max count of the " + upgrade.getName() + " upgrade"));
                            }
                        }
                    config.save();
                Logger.info(spacing + "Saving " + config);
        Logger.info("Loading configs finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
    private ConfigLoader() {}
}
