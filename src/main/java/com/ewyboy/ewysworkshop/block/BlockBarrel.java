package com.ewyboy.ewysworkshop.block;

import com.ewyboy.ewysworkshop.loaders.CreativeTabLoader;
import com.ewyboy.ewysworkshop.tileentity.TileEntityBarrel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBarrel extends BlockContainer {

    public BlockBarrel() {
        super(Material.wood);
        setCreativeTab(CreativeTabLoader.EwysWorkshopTab);
        setHardness(3.5f);
        setStepSound(soundTypeWood);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
        setBlockBounds(0.085f,0.0f,0.0375f, 0.915f, 0.9375f, 0.9625f);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return Blocks.log.getBlockTextureFromSide(1);
    }

    @Override
    public int getRenderType() {
        return (-1);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public boolean canRenderInPass(int pass) {
        return true;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityBarrel();
    }
}
