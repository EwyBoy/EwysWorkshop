package com.ewyboy.ewysworkshop.page.setting;


import com.ewyboy.ewysworkshop.gui.container.slot.SlotBase;
import com.ewyboy.ewysworkshop.page.unit.Unit;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import net.minecraft.item.ItemStack;

import java.util.List;

public class SettingNormal extends Setting {
    public SettingNormal(TileEntityTable table, int id, int x, int y) {
        super(table, id, x, y);
    }

    @Override
    public ItemStack getItem() {
        return table.getUpgradePage().getUpgradeMainItem(id);
    }

    @Override
    public List<SlotBase> getSlots() {
        Unit unit = table.getMainPage().getCraftingList().get(id);
        if (!unit.isEnabled()) {
            unit = table.getMainPage().getSmeltingList().get(id);
            if (!unit.isEnabled()) {
                return null;
            }
        }
        return unit.getSlots();
    }

    @Override
    public String getName() {
        return null;
    }
}
