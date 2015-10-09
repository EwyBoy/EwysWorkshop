package com.ewyboy.ewysworkshop.util;

public class StringMap {

    //Channels
    public static final String Channel = "EWorkshop";

    //Mod info
    public static final String ID = "ewysworkshop";
    public static final String Name = "Ewy's Workshop";
    public static final String MinecraftVersion = "1.7.10";
    public static final String VersionMajor = "1";
    public static final String VersionMinor = "0";
    public static final String VersionPatch = "2";
    public static final String VersionBuildName = Name + "-" + MinecraftVersion + "-" + VersionMajor + "." + VersionMinor + "." + VersionPatch;

    //Proxies
    public static final String clientProxyPath = "com.ewyboy.ewysworkshop.network.proxies.ClientProxy";
    public static final String commonProxyPath = "com.ewyboy.ewysworkshop.network.proxies.CommonProxy";

    //Blocks
    public static final String WorkshopTable = "WorkshopTable";
    public static final String Barrel = "Barrel";

    //Items
    public static final String ItemPath = "ewysworkshop:item";
    public static final String WorkshopTableUpgrade = "WorkshopTableUpgrade";

    //RenderIDs
    public static int BlockSwapperRenderID;

    //ModelTexturePath
    public static final String BarrelTexturePath = "production:textures/models/BarrelTextureMap.png";

    //Configs
    public static final String ConfigCategoryTweaks = "Tweaks";

    //Pages
    public static final String PageMain = "main";
    public static final String PageTransfer = "transfer";
    public static final String PageUpgrade = "upgrade";

    //Waila
    public static final String WailaPath = "com.ewyboy.ewysworkshop.waila.Waila.onWailaCall";
}
