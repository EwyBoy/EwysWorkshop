package com.ewyboy.ewysworkshop.loaders;

import com.ewyboy.ewysworkshop.util.StringMap;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@GameRegistry.ObjectHolder(StringMap.ID)
public class CreativeTabLoader {
    public static CreativeTabs EwysWorkshopTab = new CreativeTabs(StringMap.Name) {
        public ItemStack getIconItemStack(){return new ItemStack(BlockLoader.workshopTable);}

        @Override
        public Item getTabIconItem() {
            return null;
        }
    };
}
