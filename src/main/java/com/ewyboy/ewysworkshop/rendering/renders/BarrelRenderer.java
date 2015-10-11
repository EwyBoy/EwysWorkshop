package com.ewyboy.ewysworkshop.rendering.renders;

import com.ewyboy.ewysworkshop.loaders.BlockLoader;
import com.ewyboy.ewysworkshop.loaders.ConfigLoader;
import com.ewyboy.ewysworkshop.rendering.models.BarrelModel;
import com.ewyboy.ewysworkshop.tileentity.TileEntityBarrel;
import com.ewyboy.ewysworkshop.util.StringMap;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.HashMap;
import java.util.Map;

public class BarrelRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    private final BarrelModel model;

    public BarrelRenderer() {
        this.model = new BarrelModel();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
            switch (type) {
                case EQUIPPED:
                    GL11.glRotatef(180, 1.0f, 0.0f, 1.0f);
                    GL11.glTranslatef(1.0f,-0.925f,1.0f);
                break;
                case INVENTORY:
                    GL11.glRotatef(180, 1f, 0f, 1f);
                    GL11.glTranslatef(0.5f,-1.425f,0.5f);
                break;
                case ENTITY:
                    GL11.glRotatef(180, 1.0f, 0.0f, 1.0f);
                    GL11.glTranslatef(0.0f, -2.0f, 0.0f);
                    GL11.glScalef(1.5f, 1.5f, 1.5f);
                break;
                case EQUIPPED_FIRST_PERSON:
                    GL11.glRotatef(180, 1f, 0f, 1f);
                    GL11.glTranslatef(0.5f, -1.5f, 0.5f);
                break;
                default:
                    GL11.glRotatef(180, 1f, 0f, 1f);
                    GL11.glTranslatef(0.5f, -1.5f, 0.5f);
                break;
            }
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(StringMap.BarrelTexturePath));
            this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    Map<TileEntity, Float> rotations = new HashMap<TileEntity, Float>();

    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float tick) {
        ItemStack entityDisplay = new ItemStack(BlockLoader.workshopTable, 1, 0);
        //ItemStack entityDisplay = new ItemStack(Items.potato, 1, 0);
        EntityItem entityDisplayer = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, entityDisplay);

        if (entity instanceof TileEntityBarrel) {
            TileEntityBarrel tileEntityBarrel = (TileEntityBarrel) entity;
            ForgeDirection direction = null;

            if (tileEntityBarrel.getWorldObj() != null) {direction = tileEntityBarrel.getOrientation();}
            short angle = 0;
            if (direction != null) {
                switch (direction) {
                    case NORTH: angle = 180; break;
                    case SOUTH: angle = 0;   break;
                    case WEST:  angle = 90;  break;
                    case EAST:  angle = -90; break;
                }
            }
            GL11.glPushMatrix();
                GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
                GL11.glScalef(1.0F, -1.0F, -1.0F);
                GL11.glTranslatef(0.5f, -0.5f, 0.5f);

                GL11.glPushMatrix();
                    GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(StringMap.BarrelTexturePath));
                    this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();

                GL11.glPushMatrix();
                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                    entityDisplayer.hoverStart = 1.0f;
                    RenderItem.renderInFrame = true;
                        GL11.glScalef(0.5f, 0.5f, 0.5f);
                        if (direction != null) {
                            switch (direction) {
                                case NORTH: GL11.glTranslatef(-0.015f, 2.275f, 0.960f);  break;
                                case SOUTH: GL11.glTranslatef(0.015f, 2.275f, -0.960f);  break;
                                case WEST:  GL11.glTranslatef(-0.960f, 2.275f, -0.015f); break;
                                case EAST:  GL11.glTranslatef(0.960f, 2.275f, 0.015f);   break;
                            }
                        } GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
                            GL11.glRotatef(180f,0.0f,0.0f,1.0f);
                            GL11.glRotatef(302.5f,0.0f,1.0f,0.0f);
                        if (ConfigLoader.doRenderSpinningEntity) {
                            if (!rotations.containsKey(entity)) rotations.put(entity, 0.0f);
                                float rotation = rotations.get(entity);
                                //rotation+=ConfigLoader.SPIN_SPEED * 4;
                                GL11.glRotatef(rotation, 0.0f, rotation, 0.0f);
                            rotations.remove(entity);
                            rotations.put(entity, rotation);
                        } RenderManager.instance.renderEntityWithPosYaw(entityDisplayer, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
                    RenderItem.renderInFrame = false;
                GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
    }
}