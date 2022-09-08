package com.jracon.mcearth.entities.rabbits;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class HarelequinRabbitRenderer extends MobRenderer<GenericRabbitEntity, RabbitModel<GenericRabbitEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/rabbits/harelequin_rabbit.png");

    public HarelequinRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new RabbitModel<>(context.bakeLayer(new ModelLayerLocation(new ResourceLocation("mcearth", "harelequin_rabbit"), "main"))), 0.3f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GenericRabbitEntity entity) {
        return TEXTURE;
    }

}
