package com.ewyboy.ewysworkshop.waila;

import com.ewyboy.ewysworkshop.block.BlockWorkshopTable;
import mcp.mobius.waila.api.IWailaRegistrar;

public class Waila {
    public static void onWailaCall(IWailaRegistrar registrar) {
        registrar.registerStackProvider(new WailaWorkshop(), BlockWorkshopTable.class);
        registrar.registerBodyProvider(new WailaWorkshop(), BlockWorkshopTable.class);
        registrar.registerNBTProvider(new WailaWorkshop(), BlockWorkshopTable.class);
    }
}
