package com.jracon.mcearth.datagen;

import com.jracon.mcearth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class MCERecipes extends RecipeProvider {
    public MCERecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Registration.MUD_BUCKET.get())
                .requires(Blocks.DIRT, 8)
                .requires(Items.WATER_BUCKET, 1);
    }
}
