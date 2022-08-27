package com.jracon.mcearth.world.biomemods;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.jracon.mcearth.MCEarth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MCEBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MCEarth.MOD_ID);

public static RegistryObject<Codec<MCEVegetalBiomeModifier>> VEGETAL_MODIFIER = BIOME_MODIFIERS.register(
        "vegetal", () ->
        RecordCodecBuilder.create(builder -> builder.group(
        Biome.LIST_CODEC.fieldOf("biomes").forGetter(MCEVegetalBiomeModifier::biomes),
        PlacedFeature.CODEC.fieldOf("feature").forGetter(MCEVegetalBiomeModifier::feature)
        ).apply(builder, MCEVegetalBiomeModifier::new)));

    public static void register(IEventBus eventBus) {
        BIOME_MODIFIERS.register(eventBus);
    }
}
