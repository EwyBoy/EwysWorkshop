package com.ewyboy.ewysworkshop.gui.container.slot;

import com.ewyboy.ewysworkshop.gui.GuiBase;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import net.minecraft.inventory.IInventory;

public class SlotPlayer extends SlotBase {
    public SlotPlayer(IInventory inventory, TileEntityTable table, int id, int x, int y) {
        super(inventory, table, id, x, y);
    }

    @Override
    public int getTextureIndex(GuiBase gui) {
        return shouldHighlight(gui.getSelectedSlot(), this) && gui.getSelectedSlot().shouldSlotHighlightItems() ? 3 : super.getTextureIndex(gui);
    }

    @Override
    public boolean shouldSlotHighlightItems() {
        return false;
    }
}
