package com.jracon.mcearth.world.biomemods;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import static com.jracon.mcearth.setup.Registration.VEGETAL_MODIFIER;

public record MCEVegetalBiomeModifier(HolderSet<Biome> biomes, Holder<PlacedFeature> feature) implements BiomeModifier {
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder){
        if (phase == Phase.ADD && biomes.contains(biome)) {
            builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, feature);
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() { return VEGETAL_MODIFIER.get(); }
}
