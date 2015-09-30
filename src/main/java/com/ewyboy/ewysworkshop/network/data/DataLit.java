package com.ewyboy.ewysworkshop.network.data;

public class DataLit extends com.ewyboy.ewysworkshop.network.data.DataBase {
    @Override
    public void save(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.network.DataWriter dw, int id) {
        dw.writeBoolean(table.isLit());
    }

    @Override
    public void load(com.ewyboy.ewysworkshop.tileentity.TileEntityTable table, com.ewyboy.ewysworkshop.network.DataReader dr, int id) {
        table.setLit(dr.readBoolean());
    }
}
