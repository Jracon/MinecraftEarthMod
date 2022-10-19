package com.jracon.mcearth.setup;

import com.jracon.mcearth.entities.chickens.*;
import com.jracon.mcearth.entities.cows.*;
import com.jracon.mcearth.entities.cows.flowercows.MoobloomModel;
import com.jracon.mcearth.entities.cows.flowercows.MoobloomRenderer;
import com.jracon.mcearth.entities.cows.flowercows.MoolipModel;
import com.jracon.mcearth.entities.cows.flowercows.MoolipRenderer;
import com.jracon.mcearth.entities.pigs.*;
import com.jracon.mcearth.entities.rabbits.*;
import com.jracon.mcearth.fluids.MCEInteractionInformations;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Supplier;

import static com.jracon.mcearth.MCEarth.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Registration.SOURCE_MUD_FLUID.get(), RenderType.solid());
        ItemBlockRenderTypes.setRenderLayer(Registration.FLOWING_MUD_FLUID.get(), RenderType.solid());
        MCEInteractionInformations.init();
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {

        // Chickens
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "generic_chicken"), "main"), ChickenModel::createBodyLayer);

        event.registerLayerDefinition(CluckshroomModel.LAYER_LOCATION, CluckshroomModel::createBodyLayer);
        event.registerLayerDefinition(FancyChickenModel.LAYER_LOCATION, FancyChickenModel::createBodyLayer);
        //

        // Cows
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "generic_cow"), "main"), CowModel::createBodyLayer);
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "generic_cow_unique"), "main"), UniqueCowModel::createBodyLayer);

        event.registerLayerDefinition(MoobloomModel.LAYER_LOCATION, MoobloomModel::createBodyLayer);
        event.registerLayerDefinition(MoolipModel.LAYER_LOCATION, MoolipModel::createBodyLayer);
        //

        // Pigs
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "mottled_pig"), "main"), GenericPigModel::createBodyLayer);
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "pale_pig"), "main"), GenericPigModel::createBodyLayer);

        event.registerLayerDefinition(MuddyPigModel.LAYER_LOCATION, MuddyPigModel::createBodyLayer);

        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "piebald_pig"), "main"), UniquePigModel::createBodyLayer);
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "pink_footed_pig"), "main"), UniquePigModel::createBodyLayer);
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "sooty_pig"), "main"), UniquePigModel::createBodyLayer);
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "spotted_pig"), "main"), UniquePigModel::createBodyLayer);
        //

        // Rabbits
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "bold_striped_rabbit"), "main"), RabbitModel::createBodyLayer);
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "freckled_rabbit"), "main"), RabbitModel::createBodyLayer);
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "harelequin_rabbit"), "main"), RabbitModel::createBodyLayer);
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "muddy_foot_rabbit"), "main"), RabbitModel::createBodyLayer);
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation("mcearth", "vested_rabbit"), "main"), RabbitModel::createBodyLayer);

        event.registerLayerDefinition(JumboRabbitModel.LAYER_LOCATION, JumboRabbitModel::createBodyLayer);
        //
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {

        // Chickens
        event.registerEntityRenderer(Registration.GENERIC_CHICKEN.get(), GenericChickenRenderer::new);

        event.registerEntityRenderer(Registration.AMBER_CHICKEN.get(), AmberChickenRenderer::new);

        event.registerEntityRenderer(Registration.CLUCKSHROOM.get(), CluckshroomRenderer::new);
        event.registerEntityRenderer(Registration.FANCY_CHICKEN.get(), FancyChickenRenderer::new);
        //

        // Cows
        //event.registerEntityRenderer(Registration.GENERIC_COW.get(), GenericCowRenderer::new);

        event.registerEntityRenderer(Registration.MOOBLOOM.get(), MoobloomRenderer::new);
        event.registerEntityRenderer(Registration.MOOLIP.get(), MoolipRenderer::new);
        //

        // Pigs
        event.registerEntityRenderer(Registration.MOTTLED_PIG.get(), MottledPigRenderer::new);
        event.registerEntityRenderer(Registration.PALE_PIG.get(), PalePigRenderer::new);

        event.registerEntityRenderer(Registration.MUDDY_PIG.get(), MuddyPigRenderer::new);

        event.registerEntityRenderer(Registration.PIEBALD_PIG.get(), PiebaldPigRenderer::new);
        event.registerEntityRenderer(Registration.PINK_FOOTED_PIG.get(), PinkFootedPigRenderer::new);
        event.registerEntityRenderer(Registration.SOOTY_PIG.get(), SootyPigRenderer::new);
        event.registerEntityRenderer(Registration.SPOTTED_PIG.get(), SpottedPigRenderer::new);
        //

        // Rabbits
        event.registerEntityRenderer(Registration.BOLD_STRIPED_RABBIT.get(), BoldStripedRabbitRenderer::new);
        event.registerEntityRenderer(Registration.FRECKLED_RABBIT.get(), FreckledRabbitRenderer::new);
        event.registerEntityRenderer(Registration.HARELEQUIN_RABBIT.get(), HarelequinRabbitRenderer::new);
        event.registerEntityRenderer(Registration.MUDDY_FOOT_RABBIT.get(), MuddyFootRabbitRenderer::new);
        event.registerEntityRenderer(Registration.VESTED_RABBIT.get(), VestedRabbitRenderer::new);

        event.registerEntityRenderer(Registration.JUMBO_RABBIT.get(), JumboRabbitRenderer::new);
        //
    }
}
