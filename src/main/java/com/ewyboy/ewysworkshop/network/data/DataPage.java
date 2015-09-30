package com.ewyboy.ewysworkshop.network.data;

import com.ewyboy.ewysworkshop.network.BasicCount;
import com.ewyboy.ewysworkshop.network.DataReader;
import com.ewyboy.ewysworkshop.network.DataWriter;
import com.ewyboy.ewysworkshop.network.IBitCount;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;

public class DataPage extends DataBase {
    private static final IBitCount BITS = new BasicCount(2);

    @Override
    public void save(TileEntityTable table, DataWriter dw, int id) {
        dw.writeData(table.getSelectedPage().getId(), BITS);
    }

    @Override
    public void load(TileEntityTable table, DataReader dr, int id) {
        table.setSelectedPage(table.getPages().get(dr.readData(BITS)));
    }
}
