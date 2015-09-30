package com.ewyboy.ewysworkshop.page.setting;

import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class Side {
    private int x;
    private int y;
    private com.ewyboy.ewysworkshop.page.setting.Direction direction;
    private com.ewyboy.ewysworkshop.page.setting.Setting setting;
    private com.ewyboy.ewysworkshop.page.setting.Transfer input;
    private com.ewyboy.ewysworkshop.page.setting.Transfer output;

    public Side(com.ewyboy.ewysworkshop.page.setting.Setting setting, com.ewyboy.ewysworkshop.page.setting.Direction direction, int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.setting = setting;

        input = new com.ewyboy.ewysworkshop.page.setting.Transfer(true);
        output = new com.ewyboy.ewysworkshop.page.setting.Transfer(false);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOutputEnabled() {
        return output.isEnabled();
    }

    public boolean isInputEnabled() {
        return input.isEnabled();
    }

    public void setOutputEnabled(boolean value) {
        output.setEnabled(value);
    }

    public void setInputEnabled(boolean value) {
        input.setEnabled(value);
    }

    public com.ewyboy.ewysworkshop.page.setting.Direction getDirection() {
        return direction;
    }

    public com.ewyboy.ewysworkshop.page.setting.Setting getSetting() {
        return setting;
    }

    public com.ewyboy.ewysworkshop.page.setting.Transfer getOutput() {
        return output;
    }

    public com.ewyboy.ewysworkshop.page.setting.Transfer getInput() {
        return input;
    }

    public List<String> getDescription(boolean selected) {
        List<String> str = new ArrayList<String>();
        str.add(direction.getName());

        String description = direction.getDescription();
        if (description != null) {
            str.add(EnumChatFormatting.GRAY + description);
        }
        if (selected) {
            str.add(EnumChatFormatting.YELLOW + "Selected");
        }

        str.add("");
        addTransferInfo(str, input, EnumChatFormatting.BLUE);
        addTransferInfo(str, output, EnumChatFormatting.RED);

        return str;
    }

    private void addTransferInfo(List<String> lst, com.ewyboy.ewysworkshop.page.setting.Transfer transfer, EnumChatFormatting color) {
        String name = transfer.isInput() ? "Input" : "Output";
        if (transfer.isEnabled()) {
            lst.add(color + name + ": Enabled");
            if (transfer.isAuto() && setting.table.getUpgradePage().hasGlobalUpgrade(com.ewyboy.ewysworkshop.item.Upgrade.AUTO_TRANSFER)) {
                lst.add(EnumChatFormatting.GRAY + name + " Transfer: " + EnumChatFormatting.GREEN + "Auto");
            }
            if (transfer.hasFilter(setting.table)) {
                if (transfer.hasWhiteList()) {
                    lst.add(EnumChatFormatting.GRAY + name + " Filter: " + EnumChatFormatting.WHITE + "White list");
                }else{
                    lst.add(EnumChatFormatting.GRAY + name + " Filter: " + EnumChatFormatting.DARK_GRAY + "Black list");
                }
            }
        }else{
            lst.add(EnumChatFormatting.GRAY + name + ": Disabled");
        }
    }
}
