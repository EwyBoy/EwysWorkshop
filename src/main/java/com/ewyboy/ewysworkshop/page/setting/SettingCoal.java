package com.ewyboy.ewysworkshop.page.setting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class SettingCoal extends com.ewyboy.ewysworkshop.page.setting.Setting {
    private ItemStack itemStack;

    public SettingCoal(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, int id, int x, int y) {
        super(table, id, x, y);
        itemStack = new ItemStack(Items.coal);
    }

    @Override
    public ItemStack getItem() {
        return itemStack;
    }

    @Override
    public List<com.ewyboy.ewysworkshop.gui.container.slot.SlotBase> getSlots() {
        List<com.ewyboy.ewysworkshop.gui.container.slot.SlotBase> slots = new ArrayList<com.ewyboy.ewysworkshop.gui.container.slot.SlotBase>();
        slots.add(table.getSlots().get(0));
        return slots;
    }

    @Override
    public String getName() {
        return "Fuel";
    }
}
