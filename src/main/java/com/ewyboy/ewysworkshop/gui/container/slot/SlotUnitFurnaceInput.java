package com.ewyboy.ewysworkshop.gui.container.slot;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;


public class SlotUnitFurnaceInput extends com.ewyboy.ewysworkshop.gui.container.slot.SlotUnit {
    public SlotUnitFurnaceInput(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.page.Page page, int id, int x, int y, com.ewyboy.ewysworkshop.page.unit.Unit unit) {
        super(table, page, id, x, y, unit);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return super.isItemValid(itemstack) && FurnaceRecipes.smelting().getSmeltingResult(itemstack) != null;
    }

    @Override
    public boolean canShiftClickInto(ItemStack item) {
        return true;
    }
}
