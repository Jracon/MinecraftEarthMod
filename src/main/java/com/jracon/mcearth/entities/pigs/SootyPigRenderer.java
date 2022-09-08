package com.jracon.mcearth.entities.pigs;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SootyPigRenderer extends MobRenderer<GenericPigEntity, UniquePigModel<GenericPigEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/pigs/sooty_pig.png");

    public SootyPigRenderer(EntityRendererProvider.Context context) {
        super(context, new UniquePigModel<>(context.bakeLayer(new ModelLayerLocation(new ResourceLocation("mcearth", "sooty_pig"), "main"))), 0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(GenericPigEntity entity) {
        return TEXTURE;
    }

}
