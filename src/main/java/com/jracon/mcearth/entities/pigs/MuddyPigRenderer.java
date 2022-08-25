package com.jracon.mcearth.entities.pigs;

import com.jracon.mcearth.MCEarth;
import com.jracon.mcearth.entities.cows.flowercows.MoolipEntity;
import com.jracon.mcearth.entities.cows.flowercows.MoolipModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class MuddyPigRenderer extends MobRenderer<MuddyPigEntity, MuddyPigModel<MuddyPigEntity>>{

    private static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/muddy_pig.png");

    public MuddyPigRenderer(EntityRendererProvider.Context context) {
        super(context, new MuddyPigModel<>(context.bakeLayer(MuddyPigModel.LAYER_LOCATION)), 0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(MuddyPigEntity entity) {
        return TEXTURE;
    }
}