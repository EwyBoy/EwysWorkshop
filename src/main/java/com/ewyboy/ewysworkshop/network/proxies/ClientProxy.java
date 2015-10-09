package com.ewyboy.ewysworkshop.network.proxies;

import com.ewyboy.ewysworkshop.loaders.BlockLoader;
import com.ewyboy.ewysworkshop.rendering.renders.BarrelRenderer;
import com.ewyboy.ewysworkshop.tileentity.TileEntityBarrel;
import com.ewyboy.ewysworkshop.util.StringMap;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    public static void init() {
        loadModels();
    }

    public static void loadModels() {
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockLoader.barrel), new BarrelRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrel.class, new BarrelRenderer());
        StringMap.BlockSwapperRenderID = RenderingRegistry.getNextAvailableRenderId();
    }
}
