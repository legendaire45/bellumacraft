// Date: 28/02/2013 20:47:36
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package mod.legendaire45.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMoule extends ModelBase
{
  //fields
    ModelRenderer base;
    ModelRenderer bord1;
    ModelRenderer bord2;
    ModelRenderer bord3;
    ModelRenderer bord4;
    ModelRenderer lait;
  
  public ModelMoule()
  {
    textureWidth = 128;
    textureHeight = 32;
    
      base = new ModelRenderer(this, 0, 0);
      base.addBox(0F, 0F, 0F, 16, 4, 16);
      base.setRotationPoint(-8F, 40F, -8F);
      base.setTextureSize(128, 32);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      bord1 = new ModelRenderer(this, 71, 0);
      bord1.addBox(0F, 0F, 0F, 3, 7, 16);
      bord1.setRotationPoint(-8F, 44F, -8F);
      bord1.setTextureSize(128, 32);
      bord1.mirror = true;
      setRotation(bord1, 0F, 0F, 0F);
      bord2 = new ModelRenderer(this, 71, 0);
      bord2.addBox(0F, 0F, 0F, 3, 7, 16);
      bord2.setRotationPoint(5F, 44F, -8F);
      bord2.setTextureSize(128, 32);
      bord2.mirror = true;
      setRotation(bord2, 0F, 0F, 0F);
      bord3 = new ModelRenderer(this, 11, 21);
      bord3.addBox(0F, 0F, 0F, 10, 7, 3);
      bord3.setRotationPoint(-5F, 44F, -8F);
      bord3.setTextureSize(128, 32);
      bord3.mirror = true;
      setRotation(bord3, 0F, 0F, 0F);
      bord4 = new ModelRenderer(this, 11, 21);
      bord4.addBox(0F, 0F, 0F, 10, 7, 3);
      bord4.setRotationPoint(-5F, 44F, 5F);
      bord4.setTextureSize(128, 32);
      bord4.mirror = true;
      setRotation(bord4, 0F, 0F, 0F);
      lait = new ModelRenderer(this, 39, 21);
      lait.addBox(0F, 0F, 0F, 10, 0, 10);
      lait.setRotationPoint(-5F, 50F, -5F);
      lait.setTextureSize(128, 32);
      lait.mirror = true;
      setRotation(lait, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    base.render(f5);
    bord1.render(f5);
    bord2.render(f5);
    bord3.render(f5);
    bord4.render(f5);
    lait.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
  }

  public void renderAll()
  {
	float f5 = 0.0625F;
    base.render(f5);
    bord1.render(f5);
    bord2.render(f5);
    bord3.render(f5);
    bord4.render(f5);
    lait.render(f5);
  }
}
