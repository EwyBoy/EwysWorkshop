package com.ewyboy.ewysworkshop.loaders;

import com.ewyboy.ewysworkshop.item.Upgrade;
import com.ewyboy.ewysworkshop.util.Logger;
import com.ewyboy.ewysworkshop.util.StringMap;
import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.concurrent.TimeUnit;

@GameRegistry.ObjectHolder(StringMap.ID)
public class RecipeLoader {
    private static final String STONE = "stone";
    private static final String PLANKS = "plankWood";
    private static final String COBBLE = "cobblestone";
    private static final String LAPIS = "gemLapis";
    private static final String IRON = "ingotIron";
    private static final String REDSTONE = "dustRedstone";
    private static final String GLASS = "blockGlass";
    private static final String GLOW_STONE = "dustGlowstone";
    private static final String GOLD = "ingotGold";
    private static final String REDSTONE_BLOCK = "blockRedstone";
    private static final String DIAMOND = "gemDiamond";
    private static final String WOOD = "logWood";

    public static void loadRecipes() {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading recipes started");
                GameRegistry.addRecipe(new ShapedOreRecipe(BlockLoader.workshopTable, "PPP", "CUC", "CCC", 'P', PLANKS, 'C', COBBLE, 'U', Upgrade.BLANK.getItemStack()));
                GameRegistry.addRecipe(new ShapedOreRecipe(BlockLoader.barrel, "LIL", "LCL", "LLL", 'I', IRON, 'L', WOOD, 'C', Blocks.chest));
                addRecipe(Upgrade.BLANK, "SP", "PS", 'S', STONE, 'P', PLANKS);
                addRecipe(Upgrade.STORAGE, "C", "U", 'C', Blocks.chest, 'U', Upgrade.BLANK.getItemStack());
                addRecipe(Upgrade.AUTO_CRAFTER, "PPP", "CTC", "CUC", 'P', PLANKS, 'C', COBBLE, 'T', Blocks.piston, 'U', Upgrade.BLANK.getItemStack());
                addRecipe(Upgrade.CHARGED, "IRI", "IUI", "IRI", 'I', IRON, 'R', REDSTONE, 'U', Upgrade.BLANK.getItemStack());
                addRecipe(Upgrade.SPEED, "IRI", "LUL", "IRI", 'I', IRON, 'R', REDSTONE, 'L', LAPIS, 'U', Upgrade.BLANK.getItemStack());
                addRecipe(Upgrade.QUEUE, "PPP", "IUI", "PPP", 'I', IRON, 'P', PLANKS, 'U', Upgrade.BLANK.getItemStack());
                addRecipe(Upgrade.LAVA, "NFN", "NUN", "NNN", 'N', Blocks.netherrack, 'F', Blocks.furnace, 'U', Upgrade.BLANK.getItemStack());
                addRecipe(Upgrade.SOLAR, "CCC", "IGI", "DUD", 'I', IRON, 'G', GLOW_STONE, 'C', GLASS, 'U', DIAMOND,'D', Upgrade.BLANK.getItemStack());
                addRecipe(Upgrade.EFFICIENCY, "III", "FPF", "RUR", 'I', IRON, 'R', REDSTONE, 'F', Blocks.furnace, 'P', Blocks.piston, 'U', Upgrade.BLANK.getItemStack());
                addRecipe(Upgrade.AUTO_TRANSFER, "GGG", "HUH", "GGG", 'G', GOLD, 'H', Blocks.hopper, 'U', Upgrade.BLANK.getItemStack());
                addRecipe(Upgrade.FILTER, "III", "GBG", "IUI", 'G', Blocks.light_weighted_pressure_plate, 'I', IRON, 'B', Blocks.iron_bars, 'U', Upgrade.BLANK.getItemStack());
                addRecipe(Upgrade.TRANSFER, "III", "GRG", "GUG", 'G', GOLD, 'I', IRON, 'R', REDSTONE_BLOCK, 'U', Upgrade.BLANK.getItemStack());
            Logger.info("Loading recipes finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }

    private static void addRecipe(Upgrade upgrade, Object ... recipe) {
        if (upgrade.isEnabled()) {
            GameRegistry.addRecipe(new ShapedOreRecipe(upgrade.getItemStack(), recipe));
            Logger.info("   " + upgrade + " recipe loaded");
        }
    }
}
