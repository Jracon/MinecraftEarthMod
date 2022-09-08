package com.jracon.mcearth.entities.pigs;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class MuddyPigRenderer extends MobRenderer<MuddyPigEntity, MuddyPigModel<MuddyPigEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/pigs/muddy_pig.png");
    private static final ResourceLocation DRIED_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/pigs/muddy_pig_dried.png");

    public MuddyPigRenderer(EntityRendererProvider.Context context) {
        super(context, new MuddyPigModel<>(context.bakeLayer(MuddyPigModel.LAYER_LOCATION)), 0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(MuddyPigEntity entity) {
        if (entity.isMuddy) {
            return TEXTURE;
        } else {
            return DRIED_TEXTURE;
        }
    }
}