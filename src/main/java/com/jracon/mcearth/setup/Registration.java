package com.jracon.mcearth.setup;

import com.jracon.mcearth.MCEarth;
import com.jracon.mcearth.entities.chickens.CluckshroomEntity;
import com.jracon.mcearth.entities.cows.flowercows.MoobloomEntity;
import com.jracon.mcearth.entities.cows.flowercows.MoolipEntity;
import com.jracon.mcearth.entities.pigs.MuddyPigEntity;
import com.jracon.mcearth.fluids.BaseFluidType;
import com.jracon.mcearth.fluids.MudFluid;
import com.jracon.mcearth.world.biomemods.MCEVegetalBiomeModifier;
import com.jracon.mcearth.world.feature.MCEConfiguredFeatures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

import static com.jracon.mcearth.MCEarth.MOD_ID;

public class Registration {

    // Deferred Registers
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MCEarth.MOD_ID);
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MCEarth.MOD_ID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MCEarth.MOD_ID);
    //

    // Block Registry Objects
    public static final RegistryObject<LiquidBlock> MUD_FLUID_BLOCK = BLOCKS.register("mud_fluid_block",
            () -> new LiquidBlock(Registration.SOURCE_MUD_FLUID, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100F).noLootTable()));
    public static final RegistryObject<FlowerBlock> BUTTERCUP = registerBlock("buttercup", () ->
            new FlowerBlock(MobEffects.LUCK, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)), ModSetup.ITEM_GROUP);
    public static final RegistryObject<FlowerPotBlock> BUTTERCUP_POT = BLOCKS.register("buttercup_pot", () ->
            new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, Registration.BUTTERCUP, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<FlowerBlock> PINK_DAISY = registerBlock("pink_daisy", () ->
            new FlowerBlock(MobEffects.LUCK, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)), ModSetup.ITEM_GROUP);
    public static final RegistryObject<FlowerPotBlock> PINK_DAISY_POT = BLOCKS.register("pink_daisy_pot", () ->
            new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, Registration.PINK_DAISY, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    //

    // Resource Locations
    public static final ResourceLocation MUD_STILL_RL = new ResourceLocation(MCEarth.MOD_ID, "block/mud_still");
    public static final ResourceLocation MUD_FLOWING_RL = new ResourceLocation(MCEarth.MOD_ID, "block/mud_flow");
    public static final ResourceLocation MUD_OVERLAY_RL = new ResourceLocation(MOD_ID, "misc/mud_fluid_overlay");
    //

    // Fluid Registry Objects
    public static final RegistryObject<FluidType> MUD_FLUID_TYPE = FLUID_TYPES.register("mud",
            () -> new BaseFluidType(MUD_STILL_RL, MUD_FLOWING_RL, MUD_OVERLAY_RL, FluidType.Properties.create()
                    .canExtinguish(true).motionScale(0.0075F).viscosity(2000).density(2000)
                    .fallDistanceModifier(0F).supportsBoating(true)));
    public static final RegistryObject<FlowingFluid> FLOWING_MUD_FLUID = FLUIDS.register("flowing_mud_fluid",
            MudFluid.Flowing::new);
    public static final RegistryObject<FlowingFluid> SOURCE_MUD_FLUID = FLUIDS.register("source_mud_fluid",
            MudFluid.Source::new);
    //

    // Entity Registry Objects
    public static final RegistryObject<EntityType<MoobloomEntity>> MOOBLOOM = ENTITIES.register("moobloom", () -> EntityType.Builder.of(MoobloomEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 1.4f)
            .setShouldReceiveVelocityUpdates(false)
            .build("moobloom"));
    public static final RegistryObject<EntityType<MoolipEntity>> MOOLIP = ENTITIES.register("moolip", () -> EntityType.Builder.of(MoolipEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 1.4f)
            .setShouldReceiveVelocityUpdates(false)
            .build("moolip"));
    public static final RegistryObject<EntityType<CluckshroomEntity>> CLUCKSHROOM = ENTITIES.register("cluckshroom", () -> EntityType.Builder.of(CluckshroomEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.7f)
            .setShouldReceiveVelocityUpdates(false)
            .build("cluckshroom"));
    public static final RegistryObject<EntityType<MuddyPigEntity>> MUDDY_PIG = ENTITIES.register("muddy_pig", () -> EntityType.Builder.of(MuddyPigEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 0.9f)
            .setShouldReceiveVelocityUpdates(false)
            .build("muddy_pig"));
    //

    // Item Registry Objects
    public static final RegistryObject<Item> MUD_BUCKET = ITEMS.register("mud_bucket",
            () -> new BucketItem(Registration.SOURCE_MUD_FLUID, new Item.Properties().tab(ModSetup.ITEM_GROUP).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> MOOBLOOM_SPAWN_EGG = ITEMS.register("moobloom_spawn_egg", () -> new ForgeSpawnEggItem(MOOBLOOM, 0xffd600, 0xfaf7dc, new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> MOOLIP_SPAWN_EGG = ITEMS.register("moolip_spawn_egg", () -> new ForgeSpawnEggItem(MOOLIP, 0xdb307b, 0x7a3653, new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> CLUCKSHROOM_SPAWN_EGG = ITEMS.register("cluckshroom_spawn_egg", () -> new ForgeSpawnEggItem(CLUCKSHROOM, 0xd41c20, 0xf59545, new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> MUDDY_PIG_SPAWN_EGG = ITEMS.register("muddy_pig_spawn_egg", () -> new ForgeSpawnEggItem(MUDDY_PIG, 0xd41c20, 0xf59545, new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    //

    // Tags
    public static final TagKey<Fluid> MUD = TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation(MOD_ID, "mud"));
    //

    // Biome Modifier Registry Objects
    public static RegistryObject<Codec<MCEVegetalBiomeModifier>> VEGETAL_MODIFIER = BIOME_MODIFIERS.register(
            "vegetal", () ->
                    RecordCodecBuilder.create(builder -> builder.group(
                            Biome.LIST_CODEC.fieldOf("biomes").forGetter(MCEVegetalBiomeModifier::biomes),
                            PlacedFeature.CODEC.fieldOf("feature").forGetter(MCEVegetalBiomeModifier::feature)
                    ).apply(builder, MCEVegetalBiomeModifier::new)));
    //

    // Placed Feature Registry Objects
    public static final RegistryObject<PlacedFeature> BUTTERCUP_PLACED = PLACED_FEATURES.register("buttercup_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                    MCEConfiguredFeatures.BUTTERCUP, List.of(RarityFilter.onAverageOnceEvery(16),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PINK_DAISY_PLACED = PLACED_FEATURES.register("pink_daisy_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                    MCEConfiguredFeatures.PINK_DAISY, List.of(RarityFilter.onAverageOnceEvery(16),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    //

    public static void init() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ENTITIES.register(bus);
        FLUIDS.register(bus);
        FLUID_TYPES.register(bus);
        BIOME_MODIFIERS.register(bus);
        PLACED_FEATURES.register(bus);
    }

    // Convenience Methods
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return Registration.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    //
}