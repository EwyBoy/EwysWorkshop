package com.ewyboy.ewysworkshop.gui.container.slot;

import com.ewyboy.ewysworkshop.page.Page;
import com.ewyboy.ewysworkshop.page.unit.Unit;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import net.minecraft.item.ItemStack;

public class SlotUnit extends SlotTable {
    protected Unit unit;
    public SlotUnit(TileEntityTable table, Page page, int id, int x, int y, Unit unit) {
        super(table, page, id, x, y);

        this.unit = unit;
    }

    @Override
    public boolean isVisible() {
        return super.isVisible() && isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return unit.isEnabled();
    }


    @Override
    public boolean canSupplyItems() {
        return false;
    }

    @Override
    public boolean canShiftClickInto(ItemStack item) {
        return false;
    }
}
