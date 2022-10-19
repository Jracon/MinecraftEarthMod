package com.jracon.mcearth.entities.chickens;

import com.jracon.mcearth.MCEarth;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class GenericChickenRenderer extends MobRenderer<?, ChickenModel<?>> {

    private static final ResourceLocation AMBER_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/chickens/amber_chicken.png");
    private static final ResourceLocation BRONZED_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/chickens/bronzed_chicken.png");
    private static final ResourceLocation GOLD_CRESTED_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/chickens/gold_crested_chicken.png");
    private static final ResourceLocation MIDNIGHT_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/chickens/midnight_chicken.png");
    private static final ResourceLocation SKEWBALD_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/chickens/skewbald_chicken.png");
    private static final ResourceLocation STORMY_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/chickens/stormy_chicken.png");


    public GenericChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.3f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ? entity) {
        return switch (entity.getGenericChickenEntityType()) {
            case 1 -> AMBER_TEXTURE;
            case 2 -> BRONZED_TEXTURE;
            case 3 -> GOLD_CRESTED_TEXTURE;
            case 4 -> MIDNIGHT_TEXTURE;
            case 5 -> SKEWBALD_TEXTURE;
            case 6 -> STORMY_TEXTURE;
            default -> throw new IllegalStateException("Unexpected value: " + entity.getGenericChickenEntityType());
        };
    }

    protected float getBob(GenericChickenEntity pLivingBase, float pPartialTicks) {
        float f = Mth.lerp(pPartialTicks, pLivingBase.oFlap, pLivingBase.flap);
        float f1 = Mth.lerp(pPartialTicks, pLivingBase.oFlapSpeed, pLivingBase.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
}
