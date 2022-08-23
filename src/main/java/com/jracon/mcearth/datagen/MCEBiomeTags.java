package com.jracon.mcearth.datagen;

import com.jracon.mcearth.MCEarth;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MCEBiomeTags extends TagsProvider<Biome> {

    public MCEBiomeTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.BIOME, MCEarth.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
    }

    @Override
    public String getName() {
        return "Minecraft Earth Tags";
    }
}
