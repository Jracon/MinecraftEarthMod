package com.jracon.mcearth.datagen;

import com.jracon.mcearth.setup.Registration;
import net.minecraft.data.DataGenerator;
public class MCELootTables extends BaseLootTableProvider {

    public MCELootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.BUTTERCUP.get(), createSimpleTable("buttercup", Registration.BUTTERCUP.get()));
    }
}