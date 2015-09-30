package com.ewyboy.ewysworkshop.gui.container.slot;

import net.minecraft.inventory.IInventory;

public class SlotPlayer extends com.ewyboy.ewysworkshop.gui.container.slot.SlotBase {
    public SlotPlayer(IInventory inventory, com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, int id, int x, int y) {
        super(inventory, table, id, x, y);
    }

    @Override
    public int getTextureIndex(com.ewyboy.ewysworkshop.gui.GuiBase gui) {
        return shouldHighlight(gui.getSelectedSlot(), this) && gui.getSelectedSlot().shouldSlotHighlightItems() ? 3 : super.getTextureIndex(gui);
    }

    @Override
    public boolean shouldSlotHighlightItems() {
        return false;
    }
}
