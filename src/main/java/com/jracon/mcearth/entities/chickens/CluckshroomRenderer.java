package com.jracon.mcearth.entities.chickens;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Chicken;

import javax.annotation.Nonnull;

public class CluckshroomRenderer extends MobRenderer<CluckshroomEntity, CluckshroomModel<CluckshroomEntity>>{

    private static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/cluckshroom.png");

    public CluckshroomRenderer(EntityRendererProvider.Context context) {
        super(context, new CluckshroomModel(context.bakeLayer(CluckshroomModel.LAYER_LOCATION)), 0.3f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(CluckshroomEntity entity) {
        return TEXTURE;
    }

    protected float getBob(Chicken pLivingBase, float pPartialTicks) {
        float f = Mth.lerp(pPartialTicks, pLivingBase.oFlap, pLivingBase.flap);
        float f1 = Mth.lerp(pPartialTicks, pLivingBase.oFlapSpeed, pLivingBase.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
}