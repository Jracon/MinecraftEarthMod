package com.jracon.mcearth.entities.cows;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class AlbinoCowRenderer extends MobRenderer<GenericCowEntity, UniqueCowModel<GenericCowEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/cows/albino_cow.png");

    public AlbinoCowRenderer(EntityRendererProvider.Context context) {
        super(context, new UniqueCowModel<>(context.bakeLayer(new ModelLayerLocation(new ResourceLocation("mcearth", "albino_cow"), "main"))), 0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(GenericCowEntity entity) {
        return TEXTURE;
    }

}
