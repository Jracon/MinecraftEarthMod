package com.jracon.mcearth.datagen;

import com.jracon.mcearth.MCEarth;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = MCEarth.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeServer(), new MCERecipes(generator));
        generator.addProvider(event.includeServer(), new MCELootTables(generator));
        MCEBlockTags blockTags = new MCEBlockTags(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new MCEItemTags(generator, blockTags, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new MCEBiomeTags(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new MCEStructureSetTags(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new MCEBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new MCEItemModels(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new MCELanguageProvider(generator, "en_us"));
    }
}