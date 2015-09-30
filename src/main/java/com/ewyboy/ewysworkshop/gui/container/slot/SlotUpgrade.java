package com.ewyboy.ewysworkshop.gui.container.slot;

import com.ewyboy.ewysworkshop.gui.GuiBase;
import com.ewyboy.ewysworkshop.item.Upgrade;
import com.ewyboy.ewysworkshop.loaders.ItemLoader;
import com.ewyboy.ewysworkshop.page.Page;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class SlotUpgrade extends SlotTable {
    private SlotUpgrade main;
    private boolean isMain;
    private int upgradeSection;

    public SlotUpgrade(TileEntityTable table, Page page, int id, int x, int y, SlotUpgrade main, int upgradeSection) {
        super(table, page, id, x, y);
        this.main = main;
        isMain = main == null && upgradeSection < 4;
        this.upgradeSection = upgradeSection;
    }


    @Override
    public int getSlotStackLimit(ItemStack item) {
        if (isMain) {
            return 1;
        }else{
            Upgrade upgrade = ItemLoader.upgrade.getUpgrade(item);
            if (upgrade != null) {
                int count = table.getUpgradePage().getUpgradeCount(upgradeSection, upgrade);
                return Math.min(64, upgrade.getMaxCount() - count + (getStack() != null ? getStack().stackSize : 0));
            }else{
                return super.getSlotStackLimit(item);
            }
        }

    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return super.isItemValid(itemstack) && (itemstack == null || (isMain ? isMainItem(itemstack) : isUpgradeItem(itemstack)));
    }

    private boolean isUpgradeItem(ItemStack itemstack) {
        Upgrade upgrade = ItemLoader.upgrade.getUpgrade(itemstack);
        return upgrade != null && upgrade.isValid(main != null ? main.getStack() : null);
    }

    private boolean isMainItem(ItemStack itemstack) {
        return itemstack.getItem().equals(Item.getItemFromBlock(Blocks.crafting_table)) || itemstack.getItem().equals(Item.getItemFromBlock(Blocks.furnace));
    }

    @Override
    public boolean isEnabled() {
        return main == null || main.getHasStack();
    }

    @Override
    public int getTextureIndex(GuiBase gui) {
        if (isMain) {
            if (getHasStack()) {
                return 2;
            }
        }else{
            Upgrade upgrade = ItemLoader.upgrade.getUpgrade(getStack());
            if (upgrade != null) {
                if (table.getUpgradePage().getUpgradeCountRaw(upgradeSection, upgrade) > upgrade.getMaxCount() || !isUpgradeItem(getStack())) {
                    return 4;
                }
            }
        }

        return super.getTextureIndex(gui);
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        table.onUpgradeChangeDistribute();
    }

    @Override
    public boolean canDragIntoSlot() {
        return isMain;
    }

    @Override
    public boolean canPickUpOnDoubleClick() {
        return false;
    }
}
