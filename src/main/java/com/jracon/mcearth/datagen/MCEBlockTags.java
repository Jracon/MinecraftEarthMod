package com.jracon.mcearth.datagen;

import com.jracon.mcearth.MCEarth;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MCEBlockTags extends BlockTagsProvider {

    public MCEBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, MCEarth.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
    }

    @Override
    public String getName() {
        return "Minecraft Earth Tags";
    }
}