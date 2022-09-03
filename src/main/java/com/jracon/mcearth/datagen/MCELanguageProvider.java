package com.jracon.mcearth.datagen;

import com.jracon.mcearth.MCEarth;
import com.jracon.mcearth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static com.jracon.mcearth.setup.ModSetup.TAB_NAME;

public class MCELanguageProvider extends LanguageProvider {

    public MCELanguageProvider(DataGenerator gen, String locale) {
        super(gen, MCEarth.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + TAB_NAME, "MCEarth");

        add(Registration.BUTTERCUP.get(), "Buttercup");
        add(Registration.BUTTERCUP_POT.get(), "Potted Buttercup");
        add(Registration.PINK_DAISY.get(), "Pink Daisy");
        add(Registration.PINK_DAISY_POT.get(), "Potted Pink Daisy");

        add(Registration.MUD_BUCKET.get(), "Bucket of Mud");
        add(Registration.MOOBLOOM_SPAWN_EGG.get(), "Moobloom Spawn Egg");
        add(Registration.MOOLIP_SPAWN_EGG.get(), "Moolip Spawn Egg");
        add(Registration.CLUCKSHROOM_SPAWN_EGG.get(), "Cluckshroom Spawn Egg");
        add(Registration.MUDDY_PIG_SPAWN_EGG.get(), "Muddy Pig Spawn Egg");
        add(Registration.JUMBO_RABBIT_SPAWN_EGG.get(), "Jumbo Rabbit Spawn Egg");

        add(Registration.MOOBLOOM.get(), "Moobloom");
        add(Registration.MOOLIP.get(), "Moolip");
        add(Registration.CLUCKSHROOM.get(), "Cluckshroom");
        add(Registration.MUDDY_PIG.get(), "Muddy Pig");
        add(Registration.JUMBO_RABBIT.get(), "Jumbo Rabbit");
    }
}