package com.jracon.mcearth.world.feature;

import com.jracon.mcearth.MCEarth;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class MCEPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MCEarth.MOD_ID);

    public static final RegistryObject<PlacedFeature> BUTTERCUP_PLACED = PLACED_FEATURES.register("buttercup_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                    MCEConfiguredFeatures.BUTTERCUP, List.of(RarityFilter.onAverageOnceEvery(16),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PINK_DAISY_PLACED = PLACED_FEATURES.register("pink_daisy_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                    MCEConfiguredFeatures.PINK_DAISY, List.of(RarityFilter.onAverageOnceEvery(16),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static void register(IEventBus eventBus){
        PLACED_FEATURES.register(eventBus);
    }
}
