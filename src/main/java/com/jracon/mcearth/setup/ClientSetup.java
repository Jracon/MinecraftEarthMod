package com.jracon.mcearth.setup;

import com.jracon.mcearth.entities.chickens.CluckshroomModel;
import com.jracon.mcearth.entities.chickens.CluckshroomRenderer;
import com.jracon.mcearth.entities.cows.flowercows.MoobloomModel;
import com.jracon.mcearth.entities.cows.flowercows.MoobloomRenderer;
import com.jracon.mcearth.entities.cows.flowercows.MoolipModel;
import com.jracon.mcearth.entities.cows.flowercows.MoolipRenderer;
import com.jracon.mcearth.entities.pigs.MuddyPigModel;
import com.jracon.mcearth.entities.pigs.MuddyPigRenderer;
import com.jracon.mcearth.entities.rabbits.JumboRabbitModel;
import com.jracon.mcearth.entities.rabbits.JumboRabbitRenderer;
import com.jracon.mcearth.fluids.MCEInteractionInformations;
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
        ItemBlockRenderTypes.setRenderLayer(Registration.SOURCE_MUD_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.FLOWING_MUD_FLUID.get(), RenderType.translucent());
        MCEInteractionInformations.init();
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MoobloomModel.LAYER_LOCATION, MoobloomModel::createBodyLayer);
        event.registerLayerDefinition(MoolipModel.LAYER_LOCATION, MoolipModel::createBodyLayer);
        event.registerLayerDefinition(CluckshroomModel.LAYER_LOCATION, CluckshroomModel::createBodyLayer);
        event.registerLayerDefinition(MuddyPigModel.LAYER_LOCATION, MuddyPigModel::createBodyLayer);
        event.registerLayerDefinition(JumboRabbitModel.LAYER_LOCATION, JumboRabbitModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Registration.MOOBLOOM.get(), MoobloomRenderer::new);
        event.registerEntityRenderer(Registration.MOOLIP.get(), MoolipRenderer::new);
        event.registerEntityRenderer(Registration.CLUCKSHROOM.get(), CluckshroomRenderer::new);
        event.registerEntityRenderer(Registration.MUDDY_PIG.get(), MuddyPigRenderer::new);
        event.registerEntityRenderer(Registration.JUMBO_RABBIT.get(), JumboRabbitRenderer::new);
    }
}
