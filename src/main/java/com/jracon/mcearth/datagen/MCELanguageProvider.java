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
        //add(Registration.RAINBOW_CARPET.get(), "Rainbow Carpet");
        //add(Registration.RAINBOW_WOOL.get(), "Rainbow Wool");

        add(Registration.MUD_BUCKET.get(), "Bucket of Mud");
        add(Registration.FANCY_FEATHER.get(), "Fancy Feather");
        add(Registration.HORN.get(), "Horn");

        add(Registration.MOOBLOOM_SPAWN_EGG.get(), "Moobloom Spawn Egg");
        add(Registration.MOOLIP_SPAWN_EGG.get(), "Moolip Spawn Egg");
        add(Registration.CLUCKSHROOM_SPAWN_EGG.get(), "Cluckshroom Spawn Egg");
        add(Registration.MUDDY_PIG_SPAWN_EGG.get(), "Muddy Pig Spawn Egg");
        add(Registration.JUMBO_RABBIT_SPAWN_EGG.get(), "Jumbo Rabbit Spawn Egg");

        // Chickens
        add(Registration.GENERIC_CHICKEN.get(), "Chicken");

        add(Registration.CLUCKSHROOM.get(), "Cluckshroom");
        add(Registration.FANCY_CHICKEN.get(), "Fancy Chicken");
        //

        // Cows
        add(Registration.GENERIC_COW.get(), "Albino Cow");

        add(Registration.MOOBLOOM.get(), "Moobloom");
        add(Registration.MOOLIP.get(), "Moolip");
        //

        // Pigs
        add(Registration.MOTTLED_PIG.get(), "Mottled Pig");
        add(Registration.PALE_PIG.get(), "Pale Pig");

        add(Registration.MUDDY_PIG.get(), "Muddy Pig");

        add(Registration.PIEBALD_PIG.get(), "Piebald Pig");
        add(Registration.PINK_FOOTED_PIG.get(), "Pink Footed Pig");
        add(Registration.SOOTY_PIG.get(), "Sooty Pig");
        add(Registration.SPOTTED_PIG.get(), "Spotted Pig");
        //

        // Rabbits
        add(Registration.BOLD_STRIPED_RABBIT.get(), "Bold Striped Rabbit");
        add(Registration.FRECKLED_RABBIT.get(), "Freckled Rabbit");
        add(Registration.HARELEQUIN_RABBIT.get(), "Harelequin Rabbit");
        add(Registration.MUDDY_FOOT_RABBIT.get(), "Muddy Foot Rabbit");
        add(Registration.VESTED_RABBIT.get(), "Vested Rabbit");

        add(Registration.JUMBO_RABBIT.get(), "Jumbo Rabbit");
        //
    }
}