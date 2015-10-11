package com.ewyboy.ewysworkshop.tileentity;

import com.ewyboy.ewysworkshop.util.StringMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class BaseTileEntity extends TileEntity {

    public BaseTileEntity() {
        orientation = getOrientation();
    }

    protected ForgeDirection orientation;

    public ForgeDirection getOrientation()
    {
        return orientation;
    }

    public void setOrientation(ForgeDirection orientation)
    {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation)
    {
        this.orientation = ForgeDirection.getOrientation(orientation);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        if (nbtTagCompound.hasKey(StringMap.NBTDirection)) {
            this.orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte(StringMap.NBTDirection));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setByte(StringMap.NBTDirection, (byte) orientation.ordinal());
    }
}
