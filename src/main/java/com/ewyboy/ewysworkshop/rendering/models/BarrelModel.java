package com.ewyboy.ewysworkshop.rendering.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BarrelModel extends ModelBase {

    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape2_1;
    public ModelRenderer shape2_2;
    public ModelRenderer shape2_3;
    public ModelRenderer shape7;
    public ModelRenderer shape7_1;
    public ModelRenderer shape7_2;
    public ModelRenderer shape7_3;
    public ModelRenderer shape11;
    public ModelRenderer shape11_1;
    public ModelRenderer shape11_2;
    public ModelRenderer shape11_3;
    public ModelRenderer shape15;

    public BarrelModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape11_2 = new ModelRenderer(this, 0, 62);
        this.shape11_2.setRotationPoint(0.0F, 5.0F, 7.0F);
        this.shape11_2.addBox(-5.5F, 4.0F, -0.5F, 11, 1, 1, 0.0F);
        this.shape2_1 = new ModelRenderer(this, 0, 47);
        this.shape2_1.setRotationPoint(6.0F, 17.0F, -7.0F);
        this.shape2_1.addBox(-0.5F, -8.0F, -0.5F, 1, 16, 1, 0.0F);
        this.shape11_1 = new ModelRenderer(this, 0, 62);
        this.shape11_1.setRotationPoint(0.0F, 18.0F, -7.0F);
        this.shape11_1.addBox(-5.5F, 4.0F, -0.5F, 11, 1, 1, 0.0F);
        this.shape7_3 = new ModelRenderer(this, 0, 50);
        this.shape7_3.setRotationPoint(-6.0F, 9.0F, 0.0F);
        this.shape7_3.addBox(-0.5F, 0.0F, -6.5F, 1, 1, 13, 0.0F);
        this.shape11_3 = new ModelRenderer(this, 0, 62);
        this.shape11_3.setRotationPoint(0.0F, 5.0F, -7.0F);
        this.shape11_3.addBox(-5.5F, 4.0F, -0.5F, 11, 1, 1, 0.0F);
        this.shape2_3 = new ModelRenderer(this, 0, 47);
        this.shape2_3.setRotationPoint(-6.0F, 17.0F, 7.0F);
        this.shape2_3.addBox(-0.5F, -8.0F, -0.5F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape2_3, 0.0F, -0.010995574287564275F, 0.0F);
        this.shape2_2 = new ModelRenderer(this, 0, 47);
        this.shape2_2.setRotationPoint(-6.0F, 17.0F, -7.0F);
        this.shape2_2.addBox(-0.5F, -8.0F, -0.5F, 1, 17, 1, 0.0F);
        this.shape7 = new ModelRenderer(this, 0, 50);
        this.shape7.setRotationPoint(-6.0F, 22.0F, 0.0F);
        this.shape7.addBox(-0.5F, 0.0F, -6.5F, 1, 1, 13, 0.0F);
        this.shape11 = new ModelRenderer(this, 0, 62);
        this.shape11.setRotationPoint(0.0F, 18.0F, 7.0F);
        this.shape11.addBox(-5.5F, 4.0F, -0.5F, 11, 1, 1, 0.0F);
        this.shape7_2 = new ModelRenderer(this, 0, 50);
        this.shape7_2.setRotationPoint(6.0F, 9.0F, 0.0F);
        this.shape7_2.addBox(-0.5F, 0.0F, -6.5F, 1, 1, 13, 0.0F);
        this.shape15 = new ModelRenderer(this, 4, 55);
        this.shape15.setRotationPoint(0.0F, 16.0F, -7.0F);
        this.shape15.addBox(-3.0F, -3.0F, -0.5F, 6, 6, 1, 0.0F);
        this.shape7_1 = new ModelRenderer(this, 0, 50);
        this.shape7_1.setRotationPoint(6.0F, 22.0F, 0.0F);
        this.shape7_1.addBox(-0.5F, 0.0F, -6.5F, 1, 1, 13, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shape1.addBox(-6.0F, -6.0F, -7.0F, 12, 12, 14, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 47);
        this.shape2.setRotationPoint(6.0F, 17.0F, 7.0F);
        this.shape2.addBox(-0.5F, -8.0F, -0.5F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape2, 0.0F, -0.010995574287564275F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape11_2.render(f5);
        this.shape2_1.render(f5);
        this.shape11_1.render(f5);
        this.shape7_3.render(f5);
        this.shape11_3.render(f5);
        this.shape2_3.render(f5);
        this.shape2_2.render(f5);
        this.shape7.render(f5);
        this.shape11.render(f5);
        this.shape7_2.render(f5);
        this.shape15.render(f5);
        this.shape7_1.render(f5);
        this.shape1.render(f5);
        this.shape2.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
