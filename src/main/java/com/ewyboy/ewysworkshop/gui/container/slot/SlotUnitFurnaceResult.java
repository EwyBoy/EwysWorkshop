package com.ewyboy.ewysworkshop.gui.container.slot;


import com.ewyboy.ewysworkshop.page.Page;
import com.ewyboy.ewysworkshop.page.unit.Unit;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;


public class SlotUnitFurnaceResult extends SlotUnit {
    public SlotUnitFurnaceResult(TileEntityTable table, Page page, int id, int x, int y, Unit unit) {
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
    public boolean canSupplyItems() {
        return true;
    }

    @Override
    public boolean canAcceptItems() {
        return false;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack item) {
        super.onPickupFromSlot(player, item);
        FMLCommonHandler.instance().firePlayerSmeltedEvent(player, item);
        item.onCrafting(player.getEntityWorld(), player, item.stackSize);
        if (item.getItem() == Items.iron_ingot){
            player.addStat(AchievementList.acquireIron, 1);
        }else if (item.getItem() == Items.cooked_fished) {
            player.addStat(AchievementList.cookFish, 1);
        }
    }
}
