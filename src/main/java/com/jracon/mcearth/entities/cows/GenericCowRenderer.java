package com.jracon.mcearth.entities.cows;

import com.jracon.mcearth.MCEarth;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nonnull;

public class GenericCowRenderer extends MobRenderer<GenericCowEntity, EntityModel<GenericCowEntity>> {

    public static final ResourceLocation ALBINO_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/cows/albino_cow.png");
    public static final ResourceLocation ASHEN_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/cows/ashen_cow.png");
    public static final ResourceLocation COOKIE_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/cows/cookie_cow.png");
    public static final ResourceLocation CREAM_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/cows/cream_cow.png");
    public static final ResourceLocation DAIRY_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/cows/dairy_cow.png");
    public static final ResourceLocation PINTO_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/cows/pinto_cow.png");
    public static final ResourceLocation SUNSET_TEXTURE = new ResourceLocation(MCEarth.MOD_ID, "textures/entity/cows/sunset_cow.png");

    public GenericCowRenderer(EntityRendererProvider.Context context, GenericCowEntity entity) {
        super(context, entity.getModelLayerLocation(context),0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(GenericCowEntity entity) {
        return switch (entity.getGenericCowEntityType()) {
            case 1 -> ALBINO_TEXTURE;
            case 2 -> ASHEN_TEXTURE;
            case 3 -> COOKIE_TEXTURE;
            case 4 -> CREAM_TEXTURE;
            case 5 -> DAIRY_TEXTURE;
            case 6 -> PINTO_TEXTURE;
            case 7 -> SUNSET_TEXTURE;
            default -> throw new IllegalStateException("Unexpected value: " + entity.getGenericCowEntityType());
        };
    }

    @Override
    public void render(GenericCowEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
        Entity entity = pEntity.getLeashHolder();
        if (entity != null) {
            //this.renderLeash(pEntity, pPartialTicks, pMatrixStack, pBuffer, entity);
        }
    }
}