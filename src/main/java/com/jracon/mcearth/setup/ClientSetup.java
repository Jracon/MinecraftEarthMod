package com.jracon.mcearth.setup;

import com.jracon.mcearth.MCEarth;
import com.jracon.mcearth.entities.MoobloomModel;
import com.jracon.mcearth.entities.MoobloomRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.jracon.mcearth.MCEarth.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Registration.BUTTERCUP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Registration.BUTTERCUP_POT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Registration.SOURCE_MUD_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.FLOWING_MUD_FLUID.get(), RenderType.translucent());
    }
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MoobloomModel.LAYER_LOCATION, MoobloomModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Registration.MOOBLOOM.get(), MoobloomRenderer::new);
    }
}
