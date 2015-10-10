package com.ewyboy.ewysworkshop.rendering.renders;

import com.ewyboy.ewysworkshop.loaders.BlockLoader;
import com.ewyboy.ewysworkshop.loaders.ConfigLoader;
import com.ewyboy.ewysworkshop.rendering.models.BarrelModel;
import com.ewyboy.ewysworkshop.util.StringMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

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
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
        ItemStack entityDisplay = new ItemStack(BlockLoader.workshopTable, 1, 0);
        EntityItem entityDisplayer = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, entityDisplay);

        GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(StringMap.BarrelTexturePath));
            GL11.glPushMatrix();
                GL11.glRotatef(180f, 0.0f, 0.0f, 1.0f);
                this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            entityDisplayer.hoverStart = 1.0f;
                RenderItem.renderInFrame = true;
                    GL11.glTranslatef(0.0f, -1.125f, -0.48f);
                        if (ConfigLoader.doRenderSpinningEntity) {
                            if (!rotations.containsKey(entity)) rotations.put(entity, 0.0f);
                                float rotation = rotations.get(entity);
                                rotation+=ConfigLoader.SPIN_SPEED * 4;
                                GL11.glRotatef(rotation, 0.0f, rotation, 0.0f);
                            rotations.remove(entity);
                            rotations.put(entity, rotation);
                        }
                    GL11.glScalef(0.5f, 0.5f, 0.5f);
                    RenderManager.instance.renderEntityWithPosYaw(entityDisplayer, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
                RenderItem.renderInFrame = false;
            GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
