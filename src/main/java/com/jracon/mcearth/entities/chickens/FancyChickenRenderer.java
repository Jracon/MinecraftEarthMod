package com.jracon.mcearth.entities.chickens;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class FancyChickenRenderer extends MobRenderer<FancyChickenEntity, FancyChickenModel<FancyChickenEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/chickens/fancy_chicken.png");

    public FancyChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new FancyChickenModel<>(context.bakeLayer(FancyChickenModel.LAYER_LOCATION)), 0.3f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@NotNull FancyChickenEntity entity) {
        return TEXTURE;
    }

    protected float getBob(FancyChickenEntity pLivingBase, float pPartialTicks) {
        float f = Mth.lerp(pPartialTicks, pLivingBase.oFlap, pLivingBase.flap);
        float f1 = Mth.lerp(pPartialTicks, pLivingBase.oFlapSpeed, pLivingBase.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
}