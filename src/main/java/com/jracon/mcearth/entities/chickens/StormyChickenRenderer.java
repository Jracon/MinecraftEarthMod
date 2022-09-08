package com.jracon.mcearth.entities.chickens;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class StormyChickenRenderer extends MobRenderer<GenericChickenEntity, ChickenModel<GenericChickenEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/chickens/stormy_chicken.png");

    public StormyChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel<>(context.bakeLayer(new ModelLayerLocation(new ResourceLocation("mcearth", "stormy_chicken"), "main"))), 0.3f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GenericChickenEntity entity) {
        return TEXTURE;
    }

    protected float getBob(GenericChickenEntity pLivingBase, float pPartialTicks) {
        float f = Mth.lerp(pPartialTicks, pLivingBase.oFlap, pLivingBase.flap);
        float f1 = Mth.lerp(pPartialTicks, pLivingBase.oFlapSpeed, pLivingBase.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
}
