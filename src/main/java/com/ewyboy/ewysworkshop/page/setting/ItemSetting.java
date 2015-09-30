package com.ewyboy.ewysworkshop.page.setting;

import net.minecraft.item.ItemStack;

public class ItemSetting {
    public static final int ITEM_COUNT = 10;

    private int id;
    private ItemStack item;
    private com.ewyboy.ewysworkshop.page.setting.TransferMode mode = com.ewyboy.ewysworkshop.page.setting.TransferMode.PRECISE;


    public ItemSetting(int id) {
        this.id = id;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public com.ewyboy.ewysworkshop.page.setting.TransferMode getMode() {
        return mode;
    }

    public void setMode(com.ewyboy.ewysworkshop.page.setting.TransferMode mode) {
        this.mode = mode;
    }
}
