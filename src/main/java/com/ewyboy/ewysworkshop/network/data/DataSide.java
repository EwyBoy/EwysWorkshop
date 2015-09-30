package com.ewyboy.ewysworkshop.network.data;


import com.ewyboy.ewysworkshop.network.DataReader;
import com.ewyboy.ewysworkshop.network.DataWriter;
import com.ewyboy.ewysworkshop.page.setting.*;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public abstract class DataSide extends DataBase {

    private static final int SETTINGS = 5;
    private static final int SIDES = 6;
    private static final int MODES = 2;
    public static final int LENGTH = SETTINGS * SIDES * MODES;


    protected com.ewyboy.ewysworkshop.page.setting.Transfer getTransfer(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, int id) {
        int settingId = id % SETTINGS;
        id /= SETTINGS;
        int sideId = id % SIDES;
        id /= SIDES;
        int modeId = id;
        com.ewyboy.ewysworkshop.page.setting.Side side = table.getTransferPage().getSettings().get(settingId).getSides().get(sideId);
        if (modeId == 0) {
            return side.getInput();
        }else{
            return side.getOutput();
        }
    }

    public static int getId(com.ewyboy.ewysworkshop.page.setting.Setting setting, com.ewyboy.ewysworkshop.page.setting.Side side, com.ewyboy.ewysworkshop.page.setting.Transfer transfer) {
        return setting.getId() + SETTINGS * side.getDirection().ordinal() + (transfer.isInput() ? 0 : SETTINGS * SIDES);
    }

    public static class Enabled extends DataSide {
        @Override
        public void save(TileEntityTable table, com.ewyboy.ewysworkshop.network.DataWriter dw, int id) {
            dw.writeBoolean(getTransfer(table, id).isEnabled());
        }

        @Override
        public void load(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.network.DataReader dr, int id) {
            getTransfer(table, id).setEnabled(dr.readBoolean());
        }
    }

    public static class Auto extends DataSide {
        @Override
        public void save(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.network.DataWriter dw, int id) {
            dw.writeBoolean(getTransfer(table, id).isAuto());
        }

        @Override
        public void load(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.network.DataReader dr, int id) {
            getTransfer(table, id).setAuto(dr.readBoolean());
        }
    }

    public static class WhiteList extends DataSide {
        @Override
        public void save(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.network.DataWriter dw, int id) {
            dw.writeBoolean(getTransfer(table, id).hasWhiteList());
        }

        @Override
        public void load(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.network.DataReader dr, int id) {
            getTransfer(table, id).setUseWhiteList(dr.readBoolean());
        }
    }

    public static abstract class FilterBase extends DataSide {
        public static final int LENGTH = DataSide.LENGTH * ItemSetting.ITEM_COUNT;

        protected ItemSetting getSetting(TileEntityTable table, int id) {
            return getTransfer(table, id / ItemSetting.ITEM_COUNT).getItem(id % ItemSetting.ITEM_COUNT);
        }

        public static int getId(Setting setting, Side side, Transfer transfer, ItemSetting itemSetting) {
            return getId(setting, side, transfer) * ItemSetting.ITEM_COUNT + itemSetting.getId();
        }
    }

    public static class Filter extends FilterBase {
        @Override
        public void save(TileEntityTable table, DataWriter dw, int id) {
            ItemSetting setting = getSetting(table, id);
            ItemStack itemStack = setting.getItem();

            dw.writeBoolean(itemStack != null);
            if (itemStack != null) {
                dw.writeShort(Item.getIdFromItem(itemStack.getItem()));
                dw.writeShort(itemStack.getItemDamage());
                dw.writeNBT(itemStack.getTagCompound());
            }
        }

        @Override
        public void load(TileEntityTable table, DataReader dr, int id) {
            ItemSetting setting = getSetting(table, id);

            if (dr.readBoolean()) {
                int itemId = dr.readShort();
                int itemDmg = dr.readShort();

                ItemStack item = new ItemStack(Item.getItemById(itemId), 1, itemDmg);
                item.setTagCompound(dr.readNBT());

                setting.setItem(item);
            }else{
                setting.setItem(null);
            }
        }
    }

    public static class FilterMode extends FilterBase {
        @Override
        public void save(TileEntityTable table, DataWriter dw, int id) {
            dw.writeEnum(getSetting(table, id).getMode());
        }

        @Override
        public void load(TileEntityTable table, DataReader dr, int id) {
            getSetting(table, id).setMode(dr.readEnum(TransferMode.class));
        }
    }
}
