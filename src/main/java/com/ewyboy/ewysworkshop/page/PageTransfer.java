package com.ewyboy.ewysworkshop.page;

import com.ewyboy.ewysworkshop.loaders.BlockLoader;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class PageTransfer extends com.ewyboy.ewysworkshop.page.Page {
    private List<com.ewyboy.ewysworkshop.page.setting.Setting> settings;
    private com.ewyboy.ewysworkshop.page.setting.Setting selectedSetting;
    private com.ewyboy.ewysworkshop.page.setting.Side selectedSide;
    private List<com.ewyboy.ewysworkshop.gui.component.CheckBox> checkBoxes;
    private List<com.ewyboy.ewysworkshop.gui.component.ArrowScroll> arrows;
    private boolean selectMode;
    private com.ewyboy.ewysworkshop.page.setting.Transfer selectedTransfer;

    public PageTransfer(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, String name) {
        super(table, name);

        settings = new ArrayList<com.ewyboy.ewysworkshop.page.setting.Setting>();
        for (int i = 0; i < 4; i++) {
            int x = SETTING_X + (i % 2) * SETTING_OFFSET;
            int y = SETTING_Y + (i / 2) * SETTING_OFFSET;
            settings.add(new com.ewyboy.ewysworkshop.page.setting.SettingNormal(table, i, x, y));
        }
        settings.add(new com.ewyboy.ewysworkshop.page.setting.SettingCoal(table, 4, SETTING_X + 2 * SETTING_OFFSET, SETTING_Y + SETTING_OFFSET / 2));


        for (com.ewyboy.ewysworkshop.page.setting.Setting setting : settings) {
            for (com.ewyboy.ewysworkshop.page.setting.Direction direction : com.ewyboy.ewysworkshop.page.setting.Direction.values()) {
                setting.getSides().add(new com.ewyboy.ewysworkshop.page.setting.Side(setting, direction, SIDE_X + direction.getInterfaceX() * SIDE_OFFSET, SIDE_Y + direction.getInterfaceY() * SIDE_OFFSET));
            }
        }

        checkBoxes = new ArrayList<com.ewyboy.ewysworkshop.gui.component.CheckBox>();
        checkBoxes.add(new com.ewyboy.ewysworkshop.gui.component.CheckBox("Select mode", 165, 20) {
            @Override
            public void setValue(boolean value) {
                selectMode = value;
            }

            @Override
            public boolean getValue() {
                return selectMode;
            }

            @Override
            public void onUpdate() {
                if (!getValue()) {
                    selectedSide = null;
                    selectedTransfer = null;
                }
            }

            @Override
            public boolean isVisible() {
                return selectedSetting != null && shouldSelectModeBeVisible();
            }
        });

        checkBoxes.add(new com.ewyboy.ewysworkshop.gui.component.CheckBox("Enabled", 170, 58) {
            @Override
            public void setValue(boolean value) {
                selectedTransfer.setEnabled(value);
                PageTransfer.this.table.updateServer(com.ewyboy.ewysworkshop.network.data.DataType.SIDE_ENABLED, com.ewyboy.ewysworkshop.network.data.DataSide.getId(selectedSetting, selectedSide, selectedTransfer));
                PageTransfer.this.table.onSideChange();
            }

            @Override
            public boolean getValue() {
                return selectedTransfer.isEnabled();
            }

            @Override
            public boolean isVisible() {
                return selectedTransfer != null;
            }
        });

        checkBoxes.add(new com.ewyboy.ewysworkshop.gui.component.CheckBox("Auto transfer", 170, 68) {
            @Override
            public void setValue(boolean value) {
                selectedTransfer.setAuto(value);
                PageTransfer.this.table.updateServer(com.ewyboy.ewysworkshop.network.data.DataType.SIDE_AUTO, getSyncId());
            }

            @Override
            public boolean getValue() {
                return selectedTransfer.isAuto();
            }

            @Override
            public boolean isVisible() {
                return selectedTransfer != null && PageTransfer.this.table.getUpgradePage().hasGlobalUpgrade(com.ewyboy.ewysworkshop.item.Upgrade.AUTO_TRANSFER);
            }
        });

        arrows = new ArrayList<com.ewyboy.ewysworkshop.gui.component.ArrowScroll>();
        arrows.add(new com.ewyboy.ewysworkshop.gui.component.ArrowScroll(165, 40, 50) {
            @Override
            public String getText() {
                return selectedTransfer.isInput() ? "Input" : "Output";
            }

            @Override
            public void setId(int id) {
                selectedTransfer = id == 0 ? selectedSide.getInput() : selectedSide.getOutput();
            }

            @Override
            public int getId() {
                return selectedTransfer.isInput() ? 0 : 1;
            }

            @Override
            public boolean isVisible() {
                return selectedTransfer != null;
            }

            @Override
            protected int getLength() {
                return 2;
            }
        });

        arrows.add(new com.ewyboy.ewysworkshop.gui.component.ArrowScroll(10, 112, 80) {
            @Override
            public String getText() {
                return selectedTransfer.hasWhiteList() ? "Use white list" : "Use black list";
            }

            @Override
            public void setId(int id) {
                selectedTransfer.setUseWhiteList(id == 0);
            }

            @Override
            public int getId() {
                return selectedTransfer.hasWhiteList() ? 0 : 1;
            }

            @Override
            public boolean isVisible() {
                return selectedTransfer != null && PageTransfer.this.table.getUpgradePage().hasGlobalUpgrade(com.ewyboy.ewysworkshop.item.Upgrade.FILTER);
            }

            @Override
            public void onUpdate() {
                PageTransfer.this.table.updateServer(com.ewyboy.ewysworkshop.network.data.DataType.SIDE_WHITE_LIST, getSyncId());
            }

            @Override
            protected int getLength() {
                return 2;
            }

        });
    }

    public int getSyncId() {
        return com.ewyboy.ewysworkshop.network.data.DataSide.getId(selectedSetting, selectedSide, selectedTransfer);
    }

    public int getSyncId(com.ewyboy.ewysworkshop.page.setting.ItemSetting itemSetting) {
        return com.ewyboy.ewysworkshop.network.data.DataSide.FilterBase.getId(selectedSetting, selectedSide, selectedTransfer, itemSetting);
    }

    private boolean shouldSelectModeBeVisible() {
        return table.getUpgradePage().hasGlobalUpgrade(com.ewyboy.ewysworkshop.item.Upgrade.AUTO_TRANSFER) || table.getUpgradePage().hasGlobalUpgrade(com.ewyboy.ewysworkshop.item.Upgrade.FILTER);
    }

    private static final int SIDE_X = 75;
    private static final int SIDE_Y = 15;
    private static final int SIDE_OFFSET = 20;
    private static final int SIDE_SIZE = 18;
    private static final int SIDE_SRC_X = 0;
    private static final int SIDE_SRC_Y = 166;
    private static final int SIDE_ITEM_OFFSET = 1;

    private static final int SETTING_X = 5;
    private static final int SETTING_Y = 25;
    private static final int SETTING_OFFSET = 20;
    private static final int SETTING_SIZE = 18;
    private static final int SETTING_SRC_X = 0;
    private static final int SETTING_SRC_Y = 112;
    private static final int SETTING_ITEM_OFFSET = 1;

    private static final int ITEM_X = 10;
    private static final int ITEM_Y = 125;
    private static final int ITEM_OFFSET = 20;
    private static final int ITEM_SIZE = 18;


    @Override
    public int createSlots(int id) {
        return id;
    }

    @Override
    public void draw(com.ewyboy.ewysworkshop.gui.GuiBase gui, int mX, int mY) {
        super.draw(gui, mX, mY);

        for (com.ewyboy.ewysworkshop.page.setting.Setting setting : settings) {
            gui.prepare();
            boolean isValid = setting.isValid();
            boolean isSelected = setting.equals(selectedSetting);

            if (isSelected) {
                if (!shouldSelectModeBeVisible()) {
                    selectMode = false;
                    selectedTransfer = null;
                    selectedSide = null;
                }

                if (!isValid) {
                    selectedTransfer = null;
                    selectedSide = null;
                    selectedSetting = null;
                }
            }

            boolean hover = gui.inBounds(setting.getX(), setting.getY(), SETTING_SIZE, SETTING_SIZE, mX, mY);
            int textureIndexX = isValid && hover ? 1 : 0;
            int textureIndexY = isValid ? isSelected ? 1 : 0 : 2;

            ItemStack item = setting.getItem();
            gui.drawRect(setting.getX(), setting.getY(), SETTING_SRC_X + textureIndexX * SETTING_SIZE, SETTING_SRC_Y + textureIndexY * SETTING_SIZE, SETTING_SIZE, SETTING_SIZE);
            gui.drawItem(item, setting.getX() + SETTING_ITEM_OFFSET, setting.getY() + SETTING_ITEM_OFFSET);

            if (hover && isValid) {
                String name = setting.getName();
                if (name == null) {
                    gui.getItemName(item);
                }
                gui.drawMouseOver(name);
            }
        }

        if (selectedSetting != null) {
            for (com.ewyboy.ewysworkshop.page.setting.Side side : selectedSetting.getSides()) {
                gui.prepare();
                boolean hover = gui.inBounds(side.getX(), side.getY(), SIDE_SIZE, SIDE_SIZE, mX, mY);
                int textureIndexX = side.equals(selectedSide) ? 2 : hover ? 1 : 0;
                boolean output = side.isOutputEnabled();
                boolean input = side.isInputEnabled();
                int textureIndexY = output && input ? 3 : output ? 2 : input ? 1 : 0;


                gui.drawRect(side.getX(), side.getY(), SIDE_SRC_X + textureIndexX * SIDE_SIZE, SIDE_SRC_Y + textureIndexY * SIDE_SIZE, SIDE_SIZE, SIDE_SIZE);
                gui.drawBlockIcon(BlockLoader.workshopTable.getIcon(side.getDirection().ordinal(), 0), side.getX() + SIDE_ITEM_OFFSET, side.getY() + SIDE_ITEM_OFFSET);

                if (hover) {
                    gui.drawMouseOver(side.getDescription(side == selectedSide));
                }
            }

            if (selectedTransfer != null && table.getUpgradePage().hasGlobalUpgrade(com.ewyboy.ewysworkshop.item.Upgrade.FILTER)) {
                gui.drawString("Filter", 8, 100, 0x404040);

                for (int i = 0; i < com.ewyboy.ewysworkshop.page.setting.ItemSetting.ITEM_COUNT; i++) {
                    gui.prepare();
                    com.ewyboy.ewysworkshop.page.setting.ItemSetting setting = selectedTransfer.getItem(i);
                    ItemStack item = setting != null ? setting.getItem() : null;
                    int x = ITEM_X + i * ITEM_OFFSET;

                    gui.drawItemWithBackground(item, x, ITEM_Y, mX, mY);
                }
            }
        }

        for (com.ewyboy.ewysworkshop.gui.component.CheckBox checkBox : checkBoxes) {
            checkBox.draw(gui, mX, mY);
        }
        for (com.ewyboy.ewysworkshop.gui.component.ArrowScroll arrow : arrows) {
            arrow.draw(gui, mX, mY);
        }
    }

    @Override
    public void onClick(com.ewyboy.ewysworkshop.gui.GuiBase gui, int mX, int mY, int button) {
        for (com.ewyboy.ewysworkshop.page.setting.Setting setting : settings) {
            if (gui.inBounds(setting.getX(), setting.getY(), SETTING_SIZE, SETTING_SIZE, mX, mY)) {
                if (setting.isValid()) {
                    if (setting.equals(selectedSetting)) {
                        selectedSetting = null;
                        selectedSide = null;
                        selectedTransfer = null;
                    }else{
                        if (selectedSide != null) {
                            com.ewyboy.ewysworkshop.page.setting.Side side = setting.getSides().get(selectedSide.getDirection().ordinal());
                            selectedTransfer = selectedTransfer.isInput() ? side.getInput() : side.getOutput();
                            selectedSide = side;
                        }
                        selectedSetting = setting;
                    }
                }

                break;
            }
        }

        if (selectedSetting != null) {
            for (com.ewyboy.ewysworkshop.page.setting.Side side : selectedSetting.getSides()) {
                if (gui.inBounds(side.getX(), side.getY(), SIDE_SIZE, SIDE_SIZE, mX, mY)) {
                    if (selectMode) {
                        if (side.equals(selectedSide)) {
                            selectedSide = null;
                            selectedTransfer = null;
                        }else{
                            if (selectedTransfer == null) {
                                selectedTransfer = side.getInput();
                            }else{
                                selectedTransfer = selectedTransfer.isInput() ? side.getInput() : side.getOutput();
                            }
                            selectedSide = side;
                        }

                    }else{
                        boolean input = side.isInputEnabled();
                        boolean output = side.isOutputEnabled();

                        int id = (output ? 2 : 0) + (input ? 1 : 0);
                        id += button == 0 ? 1 : -1;
                        if (id < 0) {
                            id += 4;
                        }else{
                            id %= 4;
                        }

                        boolean newInput = (id & 1) != 0;
                        boolean newOutput = (id & 2) != 0;
                        if (newInput != input) {
                            side.setInputEnabled(newInput);
                            table.updateServer(com.ewyboy.ewysworkshop.network.data.DataType.SIDE_ENABLED, com.ewyboy.ewysworkshop.network.data.DataSide.getId(selectedSetting, side, side.getInput()));
                        }
                        if (newOutput != output) {
                            side.setOutputEnabled(newOutput);
                            table.updateServer(com.ewyboy.ewysworkshop.network.data.DataType.SIDE_ENABLED, com.ewyboy.ewysworkshop.network.data.DataSide.getId(selectedSetting, side, side.getOutput()));
                        }

                        table.onSideChange();
                    }
                    break;
                }
            }

            if(selectedTransfer != null && table.getUpgradePage().hasGlobalUpgrade(com.ewyboy.ewysworkshop.item.Upgrade.FILTER)) {
                for (int i = 0; i < com.ewyboy.ewysworkshop.page.setting.ItemSetting.ITEM_COUNT; i++) {
                    if (gui.inBounds(ITEM_X + i * ITEM_OFFSET, ITEM_Y, ITEM_SIZE, ITEM_SIZE, mX, mY)) {
                        EntityPlayer player = getPlayer();
                        ItemStack itemStack = player.inventory.getItemStack();
                        if (itemStack == null) {
                            table.setMenu(new com.ewyboy.ewysworkshop.gui.menu.GuiMenuItem(table, selectedTransfer.getItem(i)));
                        }else{
                            itemStack = itemStack.copy();
                            itemStack.stackSize = 1;
                            selectedTransfer.getItem(i).setItem(itemStack);
                            table.updateServer(com.ewyboy.ewysworkshop.network.data.DataType.SIDE_FILTER, getSyncId(selectedTransfer.getItem(i)));
                        }

                        break;
                    }
                }
            }
        }

        for (com.ewyboy.ewysworkshop.gui.component.CheckBox checkBox : checkBoxes) {
            checkBox.onClick(gui, mX, mY);
        }
        for (com.ewyboy.ewysworkshop.gui.component.ArrowScroll arrow : arrows) {
            arrow.onClick(gui, mX, mY);
        }


    }

    @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    private EntityPlayer getPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public void onRelease(com.ewyboy.ewysworkshop.gui.GuiTable gui, int mX, int mY, int button) {
        for (com.ewyboy.ewysworkshop.gui.component.ArrowScroll arrow : arrows) {
            arrow.onRelease();
        }
    }

    public List<com.ewyboy.ewysworkshop.page.setting.Setting> getSettings() {
        return settings;
    }
}
