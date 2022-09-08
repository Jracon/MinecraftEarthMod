package com.jracon.mcearth.entities.rabbits;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MuddyFootRabbitRenderer extends MobRenderer<GenericRabbitEntity, RabbitModel<GenericRabbitEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/rabbits/muddy_foot_rabbit.png");

    public MuddyFootRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new RabbitModel<>(context.bakeLayer(new ModelLayerLocation(new ResourceLocation("mcearth", "muddy_foot_rabbit"), "main"))), 0.3f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GenericRabbitEntity entity) {
        return TEXTURE;
    }

}
