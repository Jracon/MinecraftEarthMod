package com.jracon.mcearth.entities.pigs;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class MottledPigRenderer extends MobRenderer<GenericPigEntity, GenericPigModel<GenericPigEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/pigs/mottled_pig.png");

    public MottledPigRenderer(EntityRendererProvider.Context context) {
        super(context, new GenericPigModel<>(context.bakeLayer(new ModelLayerLocation(new ResourceLocation("mcearth", "mottled_pig"), "main"))), 0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(GenericPigEntity entity) {
        return TEXTURE;
    }

}
