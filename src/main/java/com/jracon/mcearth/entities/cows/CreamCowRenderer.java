package com.jracon.mcearth.entities.cows;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class CreamCowRenderer extends MobRenderer<GenericCowEntity, CowModel<GenericCowEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/cows/cream_cow.png");

    public CreamCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(new ModelLayerLocation(new ResourceLocation("mcearth", "cream_cow"), "main"))), 0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(GenericCowEntity entity) {
        return TEXTURE;
    }

}
