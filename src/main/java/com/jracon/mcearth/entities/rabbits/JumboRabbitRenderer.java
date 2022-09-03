package com.jracon.mcearth.entities.rabbits;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class JumboRabbitRenderer extends MobRenderer<JumboRabbitEntity, JumboRabbitModel<JumboRabbitEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/jumbo_rabbit.png");

    public JumboRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new JumboRabbitModel<>(context.bakeLayer(JumboRabbitModel.LAYER_LOCATION)), 0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(JumboRabbitEntity entity) {
        return TEXTURE;
    }
}
