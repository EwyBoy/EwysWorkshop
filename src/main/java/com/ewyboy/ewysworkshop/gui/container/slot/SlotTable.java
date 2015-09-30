package com.ewyboy.ewysworkshop.gui.container.slot;

public class SlotTable extends com.ewyboy.ewysworkshop.gui.container.slot.SlotBase {
    private com.ewyboy.ewysworkshop.page.Page page;

    public SlotTable(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.page.Page page, int id, int x, int y) {
        super(table, table, id, x, y);

        this.page = page;
    }

    @Override
    public boolean isVisible() {
        return super.isVisible() && (page == null || page.equals(table.getSelectedPage()));
    }

    @Override
    public int getTextureIndex(com.ewyboy.ewysworkshop.gui.GuiBase gui) {
        return shouldSlotHighlightSelf() && shouldHighlight(this, gui.getSelectedSlot()) && gui.getSelectedSlot() instanceof com.ewyboy.ewysworkshop.gui.container.slot.SlotPlayer ? 3 : super.getTextureIndex(gui);
    }
}
