package com.ewyboy.ewysworkshop.gui;


import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof com.ewyboy.ewysworkshop.tileentity.TileEntityTable) {
            return new com.ewyboy.ewysworkshop.gui.container.ContainerTable((TileEntityTable)te, player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof com.ewyboy.ewysworkshop.tileentity.TileEntityTable) {
            return new com.ewyboy.ewysworkshop.gui.GuiTable((com.ewyboy.ewysworkshop.tileentity.TileEntityTable)te, player);
        }
        return null;
    }
}
