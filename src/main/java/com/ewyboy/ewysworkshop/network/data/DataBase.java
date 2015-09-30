package com.ewyboy.ewysworkshop.network.data;

public abstract class DataBase {

    public abstract void save(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.network.DataWriter dw, int id);
    public abstract void load(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.network.DataReader dr, int id);
    public boolean shouldBounce(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table) {
        return true;
    }
    public boolean shouldBounceToAll(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table) {
        return false;
    }
}
