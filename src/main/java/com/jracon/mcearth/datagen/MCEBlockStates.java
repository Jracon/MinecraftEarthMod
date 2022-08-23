package com.jracon.mcearth.datagen;

import com.jracon.mcearth.MCEarth;
import com.jracon.mcearth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MCEBlockStates extends BlockStateProvider {

    public MCEBlockStates(DataGenerator gen, ExistingFileHelper helper) { super(gen, MCEarth.MOD_ID, helper); }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.BUTTERCUP.get(), models().cross("buttercup", modLoc("block/buttercup")));
    }
}