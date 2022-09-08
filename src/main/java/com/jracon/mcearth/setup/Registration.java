package com.jracon.mcearth.setup;

import com.jracon.mcearth.MCEarth;
import com.jracon.mcearth.entities.chickens.CluckshroomEntity;
import com.jracon.mcearth.entities.chickens.FancyChickenEntity;
import com.jracon.mcearth.entities.chickens.GenericChickenEntity;
import com.jracon.mcearth.entities.cows.GenericCowEntity;
import com.jracon.mcearth.entities.cows.flowercows.MoobloomEntity;
import com.jracon.mcearth.entities.cows.flowercows.MoolipEntity;
import com.jracon.mcearth.entities.pigs.GenericPigEntity;
import com.jracon.mcearth.entities.pigs.MuddyPigEntity;
import com.jracon.mcearth.entities.rabbits.GenericRabbitEntity;
import com.jracon.mcearth.entities.rabbits.JumboRabbitEntity;
import com.jracon.mcearth.fluids.BaseFluidType;
import com.jracon.mcearth.fluids.MudFluid;
import com.jracon.mcearth.world.biomemods.MCEVegetalBiomeModifier;
import com.jracon.mcearth.world.feature.MCEConfiguredFeatures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
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
import org.jetbrains.annotations.NotNull;

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
            new FlowerBlock(MobEffects.LUCK, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistryObject<FlowerPotBlock> BUTTERCUP_POT = BLOCKS.register("buttercup_pot", () ->
            new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, Registration.BUTTERCUP, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<FlowerBlock> PINK_DAISY = registerBlock("pink_daisy", () ->
            new FlowerBlock(MobEffects.LUCK, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistryObject<FlowerPotBlock> PINK_DAISY_POT = BLOCKS.register("pink_daisy_pot", () ->
            new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, Registration.PINK_DAISY, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final RegistryObject<CarpetBlock> RAINBOW_CARPET = registerBlock("rainbow_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.of(Material.WOOL)));
    public static final RegistryObject<Block> RAINBOW_WOOL = registerBlock("rainbow_wool", () -> new Block(BlockBehaviour.Properties.of(Material.WOOL)));
    public static final RegistryObject<CustomBedBlock> RAINBOW_BED = registerBlock("rainbow_bed", () -> new CustomBedBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_BED)));
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

    // Generic Chicken Registry Objects
    public static final RegistryObject<EntityType<GenericChickenEntity>> AMBER_CHICKEN = ENTITIES.register("amber_chicken", () -> EntityType.Builder.of(GenericChickenEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.7f)
            .setShouldReceiveVelocityUpdates(false)
            .build("amber_chicken"));
    public static final RegistryObject<EntityType<GenericChickenEntity>> BRONZED_CHICKEN = ENTITIES.register("bronzed_chicken", () -> EntityType.Builder.of(GenericChickenEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.7f)
            .setShouldReceiveVelocityUpdates(false)
            .build("bronzed_chicken"));
    public static final RegistryObject<EntityType<GenericChickenEntity>> GOLD_CRESTED_CHICKEN = ENTITIES.register("gold_crested_chicken", () -> EntityType.Builder.of(GenericChickenEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.7f)
            .setShouldReceiveVelocityUpdates(false)
            .build("gold_crested_chicken"));
    public static final RegistryObject<EntityType<GenericChickenEntity>> MIDNIGHT_CHICKEN = ENTITIES.register("midnight_chicken", () -> EntityType.Builder.of(GenericChickenEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.7f)
            .setShouldReceiveVelocityUpdates(false)
            .build("midnight_chicken"));
    public static final RegistryObject<EntityType<GenericChickenEntity>> SKEWBALD_CHICKEN = ENTITIES.register("skewbald_chicken", () -> EntityType.Builder.of(GenericChickenEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.7f)
            .setShouldReceiveVelocityUpdates(false)
            .build("skewbald_chicken"));
    public static final RegistryObject<EntityType<GenericChickenEntity>> STORMY_CHICKEN = ENTITIES.register("stormy_chicken", () -> EntityType.Builder.of(GenericChickenEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.7f)
            .setShouldReceiveVelocityUpdates(false)
            .build("stormy_chicken"));
    //

    // Other Chicken Registry Objects
    public static final RegistryObject<EntityType<CluckshroomEntity>> CLUCKSHROOM = ENTITIES.register("cluckshroom", () -> EntityType.Builder.of(CluckshroomEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.7f)
            .setShouldReceiveVelocityUpdates(false)
            .build("cluckshroom"));
    public static final RegistryObject<EntityType<FancyChickenEntity>> FANCY_CHICKEN = ENTITIES.register("fancy_chicken", () -> EntityType.Builder.of(FancyChickenEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.7f)
            .setShouldReceiveVelocityUpdates(false)
            .build("fancy_chicken"));
    //

    // Unique Cow Registry Objects
    public static final RegistryObject<EntityType<GenericCowEntity>> ALBINO_COW = ENTITIES.register("albino_cow", () -> EntityType.Builder.of(GenericCowEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 1.4f)
            .setShouldReceiveVelocityUpdates(false)
            .build("albino_cow"));
    public static final RegistryObject<EntityType<GenericCowEntity>> ASHEN_COW = ENTITIES.register("ashen_cow", () -> EntityType.Builder.of(GenericCowEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 1.4f)
            .setShouldReceiveVelocityUpdates(false)
            .build("ashen_cow"));
    public static final RegistryObject<EntityType<GenericCowEntity>> COOKIE_COW = ENTITIES.register("cookie_cow", () -> EntityType.Builder.of(GenericCowEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 1.4f)
            .setShouldReceiveVelocityUpdates(false)
            .build("cookie_cow"));
    public static final RegistryObject<EntityType<GenericCowEntity>> PINTO_COW = ENTITIES.register("pinto_cow", () -> EntityType.Builder.of(GenericCowEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 1.4f)
            .setShouldReceiveVelocityUpdates(false)
            .build("pinto_cow"));
    public static final RegistryObject<EntityType<GenericCowEntity>> SUNSET_COW = ENTITIES.register("sunset_cow", () -> EntityType.Builder.of(GenericCowEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 1.4f)
            .setShouldReceiveVelocityUpdates(false)
            .build("sunset_cow"));
    //

    // Generic Cow Registry Objects
    public static final RegistryObject<EntityType<GenericCowEntity>> CREAM_COW = ENTITIES.register("cream_cow", () -> EntityType.Builder.of(GenericCowEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 1.4f)
            .setShouldReceiveVelocityUpdates(false)
            .build("cream_cow"));
    public static final RegistryObject<EntityType<GenericCowEntity>> DAIRY_COW = ENTITIES.register("dairy_cow", () -> EntityType.Builder.of(GenericCowEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 1.4f)
            .setShouldReceiveVelocityUpdates(false)
            .build("dairy_cow"));
    //

    // Flower Cow Registry Objects
    public static final RegistryObject<EntityType<MoobloomEntity>> MOOBLOOM = ENTITIES.register("moobloom", () -> EntityType.Builder.of(MoobloomEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 1.4f)
            .setShouldReceiveVelocityUpdates(false)
            .build("moobloom"));
    public static final RegistryObject<EntityType<MoolipEntity>> MOOLIP = ENTITIES.register("moolip", () -> EntityType.Builder.of(MoolipEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 1.4f)
            .setShouldReceiveVelocityUpdates(false)
            .build("moolip"));
    //

    // Generic Pig Registry Objects
    public static final RegistryObject<EntityType<GenericPigEntity>> MOTTLED_PIG = ENTITIES.register("mottled_pig", () -> EntityType.Builder.of(GenericPigEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 0.9f)
            .setShouldReceiveVelocityUpdates(false)
            .build("mottled_pig"));
    public static final RegistryObject<EntityType<GenericPigEntity>> PALE_PIG = ENTITIES.register("pale_pig", () -> EntityType.Builder.of(GenericPigEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 0.9f)
            .setShouldReceiveVelocityUpdates(false)
            .build("pale_pig"));
    //

    // Other Pig Registry Objects
    public static final RegistryObject<EntityType<MuddyPigEntity>> MUDDY_PIG = ENTITIES.register("muddy_pig", () -> EntityType.Builder.of(MuddyPigEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 0.9f)
            .setShouldReceiveVelocityUpdates(false)
            .build("muddy_pig"));
    //

    // Unique Pig Registry Objects
    public static final RegistryObject<EntityType<GenericPigEntity>> PIEBALD_PIG = ENTITIES.register("piebald_pig", () -> EntityType.Builder.of(GenericPigEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 0.9f)
            .setShouldReceiveVelocityUpdates(false)
            .build("piebald_pig"));
    public static final RegistryObject<EntityType<GenericPigEntity>> PINK_FOOTED_PIG = ENTITIES.register("pink_footed_pig", () -> EntityType.Builder.of(GenericPigEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 0.9f)
            .setShouldReceiveVelocityUpdates(false)
            .build("pink_footed_pig"));
    public static final RegistryObject<EntityType<GenericPigEntity>> SOOTY_PIG = ENTITIES.register("sooty_pig", () -> EntityType.Builder.of(GenericPigEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 0.9f)
            .setShouldReceiveVelocityUpdates(false)
            .build("sooty_pig"));
    public static final RegistryObject<EntityType<GenericPigEntity>> SPOTTED_PIG = ENTITIES.register("spotted_pig", () -> EntityType.Builder.of(GenericPigEntity::new, MobCategory.CREATURE)
            .sized(0.9f, 0.9f)
            .setShouldReceiveVelocityUpdates(false)
            .build("spotted_pig"));
    //

    // Generic Rabbit Registry Objects
    public static final RegistryObject<EntityType<GenericRabbitEntity>> BOLD_STRIPED_RABBIT = ENTITIES.register("bold_striped_rabbit", () -> EntityType.Builder.of(GenericRabbitEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.5f)
            .setShouldReceiveVelocityUpdates(false)
            .build("bold_striped_rabbit"));
    public static final RegistryObject<EntityType<GenericRabbitEntity>> FRECKLED_RABBIT = ENTITIES.register("freckled_rabbit", () -> EntityType.Builder.of(GenericRabbitEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.5f)
            .setShouldReceiveVelocityUpdates(false)
            .build("freckled_rabbit"));
    public static final RegistryObject<EntityType<GenericRabbitEntity>> HARELEQUIN_RABBIT = ENTITIES.register("harelequin_rabbit", () -> EntityType.Builder.of(GenericRabbitEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.5f)
            .setShouldReceiveVelocityUpdates(false)
            .build("harelequin_rabbit"));
    public static final RegistryObject<EntityType<GenericRabbitEntity>> MUDDY_FOOT_RABBIT = ENTITIES.register("muddy_foot_rabbit", () -> EntityType.Builder.of(GenericRabbitEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.5f)
            .setShouldReceiveVelocityUpdates(false)
            .build("muddy_foot_rabbit"));
    public static final RegistryObject<EntityType<GenericRabbitEntity>> VESTED_RABBIT = ENTITIES.register("vested_rabbit", () -> EntityType.Builder.of(GenericRabbitEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.5f)
            .setShouldReceiveVelocityUpdates(false)
            .build("vested_rabbit"));
    //

    // Other Rabbit Registry Objects
    public static final RegistryObject<EntityType<JumboRabbitEntity>> JUMBO_RABBIT = ENTITIES.register("jumbo_rabbit", () -> EntityType.Builder.of(JumboRabbitEntity::new, MobCategory.CREATURE)
            .sized(0.7f, 1.2f)
            .setShouldReceiveVelocityUpdates(false)
            .build("jumbo_rabbit"));
    //

    // Item Registry Objects
    public static final RegistryObject<Item> MUD_BUCKET = ITEMS.register("mud_bucket",
            () -> new BucketItem(Registration.SOURCE_MUD_FLUID, new Item.Properties().tab(ModSetup.ITEM_GROUP).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> MOOBLOOM_SPAWN_EGG = ITEMS.register("moobloom_spawn_egg", () -> new ForgeSpawnEggItem(MOOBLOOM, 0xffd600, 0xfaf7dc, new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> MOOLIP_SPAWN_EGG = ITEMS.register("moolip_spawn_egg", () -> new ForgeSpawnEggItem(MOOLIP, 0xdb307b, 0x7a3653, new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> CLUCKSHROOM_SPAWN_EGG = ITEMS.register("cluckshroom_spawn_egg", () -> new ForgeSpawnEggItem(CLUCKSHROOM, 0xd41c20, 0xf59545, new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> MUDDY_PIG_SPAWN_EGG = ITEMS.register("muddy_pig_spawn_egg", () -> new ForgeSpawnEggItem(MUDDY_PIG, 0xd41c20, 0xf59545, new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> JUMBO_RABBIT_SPAWN_EGG = ITEMS.register("jumbo_rabbit_spawn_egg", () -> new ForgeSpawnEggItem(JUMBO_RABBIT, 0xd41c20, 0xf59545, new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> FANCY_FEATHER = ITEMS.register("fancy_feather", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> HORN = ITEMS.register("horn", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP).rarity(Rarity.RARE)));
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

    public static void registerAdditionalEntityInformation() {
        registerEntitySpawnRestrictions();
    }

    private static void registerEntitySpawnRestrictions() {

        // Chickens
        SpawnPlacements.register(AMBER_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(BRONZED_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(GOLD_CRESTED_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MIDNIGHT_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(SKEWBALD_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(STORMY_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);

        SpawnPlacements.register(CLUCKSHROOM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);

        SpawnPlacements.register(FANCY_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        //

        // Cows
        SpawnPlacements.register(ALBINO_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(ASHEN_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(COOKIE_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CREAM_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(DAIRY_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(PINTO_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(SUNSET_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);

        SpawnPlacements.register(MOOBLOOM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MOOLIP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);


        //

        // Pigs
        SpawnPlacements.register(MOTTLED_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(PALE_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);

        SpawnPlacements.register(MUDDY_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);

        SpawnPlacements.register(PIEBALD_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(PINK_FOOTED_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(SOOTY_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(SPOTTED_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        //

        // Rabbits
        SpawnPlacements.register(Registration.BOLD_STRIPED_RABBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GenericRabbitEntity::checkGenericRabbitSpawnRules);
        SpawnPlacements.register(Registration.FRECKLED_RABBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GenericRabbitEntity::checkGenericRabbitSpawnRules);
        SpawnPlacements.register(Registration.HARELEQUIN_RABBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GenericRabbitEntity::checkGenericRabbitSpawnRules);
        SpawnPlacements.register(Registration.MUDDY_FOOT_RABBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GenericRabbitEntity::checkGenericRabbitSpawnRules);
        SpawnPlacements.register(Registration.VESTED_RABBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GenericRabbitEntity::checkGenericRabbitSpawnRules);

        SpawnPlacements.register(Registration.JUMBO_RABBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JumboRabbitEntity::checkJumboRabbitSpawnRules);
        //
    }

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
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        Registration.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    }

    static class CustomBedBlock extends BedBlock {
        public CustomBedBlock(Properties pProperties) {
            super(DyeColor.BLACK, pProperties);
        }
        @Override
        public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
            return RenderShape.MODEL;
        }
        @Override
        public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
            return null;
        }
    }
    //
}