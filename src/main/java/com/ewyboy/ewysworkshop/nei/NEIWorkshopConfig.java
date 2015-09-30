package com.ewyboy.ewysworkshop.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.ewyboy.ewysworkshop.gui.GuiTable;
import com.ewyboy.ewysworkshop.main.EwysWorkshop;
import com.ewyboy.ewysworkshop.util.StringMap;

public class NEIWorkshopConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        OverlayWrapper overlay = new OverlayWrapper();
        API.registerGuiOverlay(GuiTable.class, "crafting", overlay);
        API.registerGuiOverlayHandler(GuiTable.class, overlay, "crafting");
        EwysWorkshop.nei = new NEICallback();
    }

    @Override
    public String getName() {
        return StringMap.Name;
    }

    @Override
    public String getVersion() {
        return StringMap.VersionBuildName;
    }
}
