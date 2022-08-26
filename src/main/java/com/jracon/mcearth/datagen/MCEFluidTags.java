package com.jracon.mcearth.datagen;

import com.jracon.mcearth.MCEarth;
import com.jracon.mcearth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MCEFluidTags extends FluidTagsProvider {

    public MCEFluidTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, MCEarth.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(Registration.MUD)
                .add(Registration.MUD_FLUID_BLOCK.get().getFluid());
    }

    @Override
    public String getName() {
        return "Minecraft Earth Tags";
    }
}
