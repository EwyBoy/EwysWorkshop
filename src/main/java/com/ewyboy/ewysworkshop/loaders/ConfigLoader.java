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

    private static final String MAX_COUNT_SUFFIX = " maximum amount";
    public static boolean doRenderSpinningEntity, debugMode, RFSupport;
    public static int MAX_POWER, MAX_LAVA, MAX_LAVA_DRAIN, LAVA_EFFICIENCY, SOLAR_GENERATION, FUEL_DELAY, RF_BUFFER, RF_CONVERSION_RATE, RF_CONVERSION_RATIO;

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
                        RF_BUFFER = config.getInt("RF Buffer Capacity", StringMap.ConfigCategoryTweaks, 5000, 100, Integer.MAX_VALUE, "Sets the internal RF buffer capacity if CoFH Core is added");
                            Logger.info(spacing + "RF Buffer Capacity = " + RF_BUFFER);
                        RF_CONVERSION_RATE = config.getInt("RF Conversion Rate", StringMap.ConfigCategoryTweaks, 50, 1, Integer.MAX_VALUE, "Sets the amount of RF converted to power on each fuel reload session (RF -> Power for each (Fuel Delay) tick)");
                            Logger.info(spacing + "RF Conversion Rate = " + RF_CONVERSION_RATE);
                        RF_CONVERSION_RATIO = config.getInt("RF Conversion Ratio", StringMap.ConfigCategoryTweaks, 1, 1, Integer.MAX_VALUE, "Sets the ratio RF gets converted to power (RF Conversion Ratio for each Power (Default 10:1 ratio)) THIS CAN NOT BE LOWER OR EQUAL TO THE CONVERSION RATE!");
                            Logger.info(spacing + "RF Conversion Ratio = " + RF_CONVERSION_RATIO + ":1 ratio");
                        debugMode = config.getBoolean("Debug Mode", StringMap.ConfigCategoryTogglable, false, "Set true to turn on developer debug mode for debugging info in console");
                            Logger.info(spacing + "Debug Mode = " + debugMode);
                        RFSupport = config.getBoolean("RF Support", StringMap.ConfigCategoryTogglable, true, "Set to false to disable RF support if CoFH Core is added");
                            Logger.info(spacing + "RF Support = " + RFSupport);
                        for (Upgrade upgrade : Upgrade.values()) {
                            Upgrade.MaxCount max = upgrade.getMaxCountObject();
                            if (max.getConfigurableMax() > 0) {
                                upgrade.getMaxCountObject().setMax(config.getInt(upgrade.getName() + MAX_COUNT_SUFFIX, StringMap.ConfigCategoryUpgrades, max.getMax(), 0, max.getConfigurableMax(), "Max amount of the " + upgrade.getName() + " upgrade"));
                                Logger.info(spacing + upgrade.getName() + " = " + max.getMax());
                            }
                        }
                    config.save();
                Logger.info(spacing + "Saving " + config);
        Logger.info("Loading configs finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
    private ConfigLoader() {}
}
