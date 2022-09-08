package com.jracon.mcearth.datagen;

import com.jracon.mcearth.MCEarth;
import com.jracon.mcearth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MCEBlockStates extends BlockStateProvider {

    public MCEBlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, MCEarth.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.BUTTERCUP.get(), models().cross("buttercup", modLoc("block/buttercup")).renderType("cutout"));
        simpleBlock(Registration.PINK_DAISY.get(), models().cross("pink_daisy", modLoc("block/pink_daisy")).renderType("cutout"));
        simpleBlock(Registration.RAINBOW_WOOL.get(), models().cube("rainbow_wool", modLoc("block/rainbow_wool_down"), modLoc("block/rainbow_wool_up"), modLoc("block/rainbow_wool_north"), modLoc("block/rainbow_wool_south"), modLoc("block/rainbow_wool_east"), modLoc("block/rainbow_wool_west")));
    }
}