package com.ewyboy.ewysworkshop.network.data;

import com.ewyboy.ewysworkshop.network.DataReader;
import com.ewyboy.ewysworkshop.network.DataWriter;
import com.ewyboy.ewysworkshop.network.IBitCount;
import com.ewyboy.ewysworkshop.network.MaxCount;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;

public class DataFuel extends DataBase {
    private static IBitCount FUEL_BIT_COUNT = new MaxCount(TileEntityTable.MAX_POWER);

    @Override
    public void save(TileEntityTable table, DataWriter dw, int id) {
        dw.writeData(table.getPower(), FUEL_BIT_COUNT);
    }

    @Override
    public void load(TileEntityTable table, DataReader dr, int id) {
        table.setPower(dr.readData(FUEL_BIT_COUNT));
    }
}
