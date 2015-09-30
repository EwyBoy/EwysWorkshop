package com.ewyboy.ewysworkshop.gui.container.slot;

import com.ewyboy.ewysworkshop.page.Page;
import com.ewyboy.ewysworkshop.page.unit.UnitCrafting;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import net.minecraft.item.ItemStack;

public class SlotUnitCraftingGrid extends com.ewyboy.ewysworkshop.gui.container.slot.SlotUnit {
    public SlotUnitCraftingGrid(TileEntityTable table, Page page, int id, int x, int y, UnitCrafting unit) {
        super(table, page, id, x, y, unit);
    }

    @Override
    public boolean canAcceptItem(ItemStack item) {
        if (getHasStack() && item != null) {
            com.ewyboy.ewysworkshop.page.unit.UnitCrafting crafting = (UnitCrafting)unit;

            int own = getCount(item, getStack());

            if (own != -1) {
            int start = crafting.getGridId();
                for (int i = start; i < start + 9; i++) {
                    if (i != getSlotIndex())   {
                        int other = getCount(item, table.getStackInSlot(i));
                        if (other != -1 && other < own) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }

        return false;
    }

    private int getCount(ItemStack item, ItemStack slotItem) {
        if (slotItem != null && slotItem.isItemEqual(item) && ItemStack.areItemStackTagsEqual(item, slotItem)) {
            return slotItem.stackSize;
        }else{
            return -1;
        }
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        ((UnitCrafting)unit).onGridChanged();
    }

    @Override
    public boolean shouldSlotHighlightItems() {
        return false;
    }

    @Override
    public boolean shouldSlotHighlightSelf() {
        return false;
    }
}
