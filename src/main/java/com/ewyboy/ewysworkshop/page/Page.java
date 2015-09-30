package com.ewyboy.ewysworkshop.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class Page {
    private String name;
    protected com.ewyboy.ewysworkshop.tileentity.TileEntityTable table;
    private int id;


    public Page(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, String name) {
        this.id = table.getPages().size();
        this.table = table;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int createSlots(int id);

    protected void addSlot(com.ewyboy.ewysworkshop.gui.container.slot.SlotBase slot) {
        table.addSlot(slot);
    }

    @SideOnly(Side.CLIENT)
    public void draw(com.ewyboy.ewysworkshop.gui.GuiBase gui, int mX, int mY) {
        gui.drawString(name, 8, 6, 0x404040);
    }
    @SideOnly(Side.CLIENT)
    public void onClick(com.ewyboy.ewysworkshop.gui.GuiBase gui, int mX, int mY, int button) {}

    public int getId() {
        return id;
    }

    public void onUpdate() {}

    @SideOnly(Side.CLIENT)
    public void onRelease(com.ewyboy.ewysworkshop.gui.GuiTable gui, int mX, int mY, int button) {}
}
