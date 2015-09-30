package com.ewyboy.ewysworkshop.gui.container.slot;


import com.ewyboy.ewysworkshop.item.Upgrade;
import com.ewyboy.ewysworkshop.page.Page;
import com.ewyboy.ewysworkshop.page.unit.Unit;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import net.minecraft.item.ItemStack;

public class SlotUnitFurnaceQueue extends SlotUnitFurnaceInput {
    private int queueId;
    public SlotUnitFurnaceQueue(TileEntityTable table, Page page, int id, int x, int y, Unit unit, int queueId) {
        super(table, page, id, x, y, unit);
        this.queueId = queueId;
    }

    @Override
    public boolean isVisible() {
        return isUsed() && super.isVisible();
    }

    @Override
    public boolean isEnabled() {
        return isUsed() && super.isEnabled();
    }

    private boolean isUsed() {
        return queueId < table.getUpgradePage().getUpgradeCount(unit.getId(), Upgrade.QUEUE);
    }

    @Override
    public boolean canShiftClickInto(ItemStack item) {
        return true;
    }
}
