package com.ewyboy.ewysworkshop.block;

import com.ewyboy.ewysworkshop.loaders.ConfigLoader;
import com.ewyboy.ewysworkshop.loaders.CreativeTabLoader;
import com.ewyboy.ewysworkshop.tileentity.BaseTileEntity;
import com.ewyboy.ewysworkshop.util.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BaseBlock extends BlockContainer {

    public BaseBlock() {
        this(Material.rock);
    }

    public BaseBlock(Material material) {
        super(material);
        setCreativeTab(CreativeTabLoader.EwysWorkshopTab);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        this.dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }

    public static int blockFacing;

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        super.onBlockPlacedBy(world, x, y, z, entityLivingBase, itemStack);
        if (world.getTileEntity(x,y,z) instanceof BaseTileEntity || world.getBlock(x,y,z) instanceof Block) {
            ForgeDirection direction = null;
            int facing = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            switch (facing) {
                case 0: direction = ForgeDirection.NORTH; break;
                case 1: direction = ForgeDirection.EAST; break;
                case 2: direction = ForgeDirection.SOUTH; break;
                case 3: direction = ForgeDirection.WEST; break;
                default: direction = null;
            }
            ((BaseTileEntity) world.getTileEntity(x, y, z)).setOrientation(direction);
            blockFacing = direction.ordinal();
            if (ConfigLoader.debugMode) {Logger.info(direction.name());}
        }
    }

    protected void dropInventory(World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x,y,z);
        if(tileEntity instanceof IInventory) {
            IInventory inventory = (IInventory) tileEntity;
            for(int i = 0; i < inventory.getSizeInventory();i++) {
                ItemStack itemStack = inventory.getStackInSlot(i);
                if (itemStack != null && itemStack.stackSize > 0) {
                    Random random = new Random();
                    float dX = random.nextFloat() * 0.8F + 0.1F;
                    float dY = random.nextFloat() * 0.8F + 0.1F;
                    float dZ = random.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityItem = new EntityItem(world, (double)((float)x + dX), (double)((float)y + dY), (double)((float)z + dZ), itemStack.copy());
                    if (itemStack.hasTagCompound()) {
                        entityItem.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
                    }
                    float factor = 0.05F;
                    entityItem.motionX = random.nextGaussian() * (double)factor;
                    entityItem.motionX = random.nextGaussian() * (double)factor + 0.20000000298023224D;
                    entityItem.motionX = random.nextGaussian() * (double)factor;
                    world.spawnEntityInWorld(entityItem);
                    itemStack.stackSize = 0;
                }
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return null;
    }
}
