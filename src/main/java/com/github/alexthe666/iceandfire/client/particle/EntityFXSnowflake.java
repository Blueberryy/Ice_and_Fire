package com.github.alexthe666.iceandfire.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFXSnowflake extends EntityFX
{
    float particleScaleOverTime;
    private static final String __OBFID = "CL_00000909";

    public EntityFXSnowflake(World worldIn, double x, double y, double z, double motX, double motY, double motZ)
    {
        this(worldIn, x, y, z, motX, motY, motZ, 2.0F);
    }

    protected EntityFXSnowflake(World worldIn, double x, double y, double z, double motX, double motY, double motZ, float i)
    {
        super(worldIn, x, y, z, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.009999999776482582D;
        this.motionY *= 0.009999999776482582D;
        this.motionZ *= 0.009999999776482582D;
        this.motionY += 0.1D;
        this.particleScale *= 0.75F;
        this.particleScale *= i;
        this.particleScaleOverTime = this.particleScale;
        this.particleMaxAge = 16;
        this.noClip = false;
        this.setParticleTextureIndex(80);
    }

    public void func_180434_a(WorldRenderer worldRender, Entity entity, float x, float y, float z, float f, float f1, float f2)
    {
        float f6 = ((float)this.particleAge + x) / (float)this.particleMaxAge * 32.0F;
        f6 = MathHelper.clamp_float(f6, 0.0F, 1.0F);
        this.particleScale = this.particleScaleOverTime * f6;
       // super.func_180434_a(worldRender, entity, x, y, z, f, f1, f2);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);

        if (this.posY == this.prevPosY)
        {
            this.motionX *= 1.1D;
            this.motionZ *= 1.1D;
        }

        this.motionX *= 0.8600000143051147D;
        this.motionY *= 0.8600000143051147D;
        this.motionZ *= 0.8600000143051147D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }

}