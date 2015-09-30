package com.ewyboy.ewysworkshop.gui.container.slot;

import com.ewyboy.ewysworkshop.page.unit.UnitCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;


public class SlotUnitCraftingResult extends com.ewyboy.ewysworkshop.gui.container.slot.SlotUnit {
    public SlotUnitCraftingResult(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.page.Page page, int id, int x, int y, com.ewyboy.ewysworkshop.page.unit.UnitCrafting unit) {
        super(table, page, id, x, y, unit);
    }

    @Override
    public boolean isBig() {
         return true;
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return false;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack item) {
        super.onPickupFromSlot(player, item);
        ((UnitCrafting)unit).onCrafting(player, item);
    }

    @Override
    public boolean canAcceptItems() {
        return false;
    }


    @Override
    public int getY() {
        int offset = 0;
        if (table.getUpgradePage().hasUpgrade(unit.getId(), com.ewyboy.ewysworkshop.item.Upgrade.AUTO_CRAFTER)) {
            offset = com.ewyboy.ewysworkshop.page.unit.UnitCrafting.RESULT_AUTO_OFFSET;
        }
        return super.getY() + offset;
    }

    @Override
    public boolean canPickUpOnDoubleClick() {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int count) {
        ItemStack itemstack = getStack();
        if (itemstack != null) {
            putStack(null);
        }
        return itemstack;
    }

    @Override
    public boolean shouldDropOnClosing() {
        return false;
    }
}
