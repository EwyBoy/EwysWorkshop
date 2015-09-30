package com.ewyboy.ewysworkshop.gui.container.slot;

public class SlotUnitCraftingStorage extends com.ewyboy.ewysworkshop.gui.container.slot.SlotUnit {
    public SlotUnitCraftingStorage(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.page.Page page, int id, int x, int y, com.ewyboy.ewysworkshop.page.unit.Unit unit) {
        super(table, page, id, x, y, unit);
    }

    @Override
    public boolean isVisible() {
        return isAvailable() && super.isVisible();
    }

    @Override
    public boolean isEnabled() {
        return isAvailable() && super.isEnabled();
    }

    private boolean isAvailable() {
        return table.getUpgradePage().hasUpgrade(unit.getId(), com.ewyboy.ewysworkshop.item.Upgrade.STORAGE);
    }

    @Override
    public boolean canAcceptItems() {
        return true;
    }

    @Override
    public boolean shouldSlotHighlightItems() {
        return false;
    }

    @Override
    public boolean shouldSlotHighlightSelf() {
        return false;
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        ((com.ewyboy.ewysworkshop.page.unit.UnitCrafting)unit).onGridChanged();
    }
}
