package com.jracon.mcearth.entities.cows.flowercows;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class MoobloomRenderer extends MobRenderer<MoobloomEntity, MoobloomModel<MoobloomEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/moobloom.png");

    public MoobloomRenderer(EntityRendererProvider.Context context) {
        super(context, new MoobloomModel(context.bakeLayer(MoobloomModel.LAYER_LOCATION)), 0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(MoobloomEntity entity) {
        return TEXTURE;
    }
}