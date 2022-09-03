package com.jracon.mcearth.datagen;

import com.jracon.mcearth.MCEarth;
import com.jracon.mcearth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MCEItemModels extends ItemModelProvider {

    public MCEItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MCEarth.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.MOOBLOOM_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(Registration.MOOLIP_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(Registration.CLUCKSHROOM_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(Registration.MUDDY_PIG_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(Registration.JUMBO_RABBIT_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        singleTexture(Registration.BUTTERCUP.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/buttercup"));
        singleTexture(Registration.PINK_DAISY.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/pink_daisy"));
        singleTexture(Registration.MUD_BUCKET.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/mud_bucket"));
    }
}
