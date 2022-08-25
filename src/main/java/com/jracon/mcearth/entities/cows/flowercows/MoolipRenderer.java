package com.jracon.mcearth.entities.cows.flowercows;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.renderer.entity.*;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class MoolipRenderer extends MobRenderer<MoolipEntity, MoolipModel<MoolipEntity>>{

    private static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/moolip.png");

    public MoolipRenderer(EntityRendererProvider.Context context) {
        super(context, new MoolipModel(context.bakeLayer(MoolipModel.LAYER_LOCATION)), 0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(MoolipEntity entity) {
        return TEXTURE;
    }
}