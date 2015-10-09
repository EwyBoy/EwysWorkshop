package com.ewyboy.ewysworkshop.gui.container.slot;

import com.ewyboy.ewysworkshop.gui.GuiBase;
import com.ewyboy.ewysworkshop.page.Page;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;

public class SlotTable extends com.ewyboy.ewysworkshop.gui.container.slot.SlotBase {
    private com.ewyboy.ewysworkshop.page.Page page;

    public SlotTable(TileEntityTable table, Page page, int id, int x, int y) {
        super(table, table, id, x, y);

        this.page = page;
    }

    @Override
    public boolean isVisible() {
        return super.isVisible() && (page == null || page.equals(table.getSelectedPage()));
    }

    @Override
    public int getTextureIndex(GuiBase gui) {
        return shouldSlotHighlightSelf() && shouldHighlight(this, gui.getSelectedSlot()) && gui.getSelectedSlot() instanceof SlotPlayer ? 3 : super.getTextureIndex(gui);
    }
}
