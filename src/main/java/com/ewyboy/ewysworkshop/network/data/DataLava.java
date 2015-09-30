package com.ewyboy.ewysworkshop.network.data;

import com.ewyboy.ewysworkshop.network.DataReader;
import com.ewyboy.ewysworkshop.network.DataWriter;
import com.ewyboy.ewysworkshop.network.IBitCount;
import com.ewyboy.ewysworkshop.network.MaxCount;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;

public class DataLava extends DataBase {
    private static IBitCount LAVA_BIT_COUNT = new MaxCount(TileEntityTable.MAX_LAVA);

    @Override
    public void save(TileEntityTable table, DataWriter dw, int id) {
        dw.writeData(table.getLava(), LAVA_BIT_COUNT);
    }

    @Override
    public void load(TileEntityTable table, DataReader dr, int id) {
        table.setLava(dr.readData(LAVA_BIT_COUNT));
    }
}
