package com.ewyboy.ewysworkshop.nei;

import codechicken.nei.recipe.GuiCraftingRecipe;
import com.ewyboy.ewysworkshop.page.unit.Unit;
import com.ewyboy.ewysworkshop.page.unit.UnitCrafting;
import com.ewyboy.ewysworkshop.page.unit.UnitSmelting;

public class NEICallback implements INEICallback {

    @Override
    public void onArrowClick(Unit unit) {
        if (unit instanceof UnitCrafting) {
            GuiCraftingRecipe.openRecipeGui("crafting");
        }else if(unit instanceof UnitSmelting) {
            GuiCraftingRecipe.openRecipeGui("smelting");
        }
    }
}