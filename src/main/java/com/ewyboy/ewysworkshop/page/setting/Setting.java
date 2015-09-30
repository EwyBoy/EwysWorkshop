package com.ewyboy.ewysworkshop.page.setting;

import com.ewyboy.ewysworkshop.gui.container.slot.SlotBase;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Setting {
    private int x;
    private int y;
    protected int id;
    protected TileEntityTable table;
    private List<Side> sides;

    public Setting(TileEntityTable table, int id, int x, int y) {
        this.table = table;
        this.id = id;
        this.x = x;
        this.y = y;
        sides = new ArrayList<Side>();
    }

    public boolean isValid() {
        return getItem() != null;
    }

    public abstract ItemStack getItem();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Side> getSides() {
        return sides;
    }

    public int getId() {
        return id;
    }

    public abstract List<SlotBase> getSlots();

    public abstract String getName();
}