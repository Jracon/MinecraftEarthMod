package com.jracon.mcearth.datagen;

import com.jracon.mcearth.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MCERecipes extends RecipeProvider {
    public MCERecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Registration.MUD_BUCKET.get())
                .requires(Blocks.DIRT, 8)
                .requires(Items.WATER_BUCKET, 1)
                .unlockedBy("has_item", has(Items.WATER_BUCKET))
                .group("mcearth")
                .save(consumer);
        ShapedRecipeBuilder.shaped(Registration.RAINBOW_CARPET.get())
                .pattern("ww")
                .define('w', Registration.RAINBOW_WOOL.get())
                .unlockedBy("has_item", has(Registration.RAINBOW_WOOL.get()))
                .group("mcearth")
                .save(consumer);
        ShapedRecipeBuilder.shaped(Registration.RAINBOW_WOOL.get())
                .pattern("www")
                .pattern("ppp")
                .define('w', Registration.RAINBOW_WOOL.get())
                .define('p', Blocks.ACACIA_PLANKS)
                .unlockedBy("has_item", has(Registration.RAINBOW_BED.get()))
                .group("mcearth")
                .save(consumer);
    }
}