package com.jracon.mcearth.entities.pigs;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SpottedPigRenderer extends MobRenderer<GenericPigEntity, UniquePigModel<GenericPigEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/pigs/spotted_pig.png");

    public SpottedPigRenderer(EntityRendererProvider.Context context) {
        super(context, new UniquePigModel<>(context.bakeLayer(new ModelLayerLocation(new ResourceLocation("mcearth", "spotted_pig"), "main"))), 0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(GenericPigEntity entity) {
        return TEXTURE;
    }

}
