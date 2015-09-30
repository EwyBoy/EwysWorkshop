package com.ewyboy.ewysworkshop.gui.container.slot;


import com.ewyboy.ewysworkshop.page.Page;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class SlotFuel extends SlotTable {
    public SlotFuel(TileEntityTable table, Page page, int id, int x, int y) {
        super(table, page, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return super.isItemValid(itemstack) && TileEntityFurnace.isItemFuel(itemstack) && !FluidContainerRegistry.isFilledContainer(itemstack);
    }

    @Override
    public boolean canShiftClickInto(ItemStack item) {
        return !item.getItem().equals(Item.getItemFromBlock(Blocks.crafting_table));
    }
}
