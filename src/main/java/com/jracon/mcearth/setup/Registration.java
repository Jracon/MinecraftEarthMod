package com.jracon.mcearth.setup;

import com.jracon.mcearth.MCEarth;
import com.jracon.mcearth.entities.*;
import com.jracon.mcearth.fluids.BaseFluidType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.jracon.mcearth.MCEarth.MOD_ID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MCEarth.MOD_ID);

    public static void init() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ENTITIES.register(bus);
        FLUIDS.register(bus);
        FLUID_TYPES.register(bus);
    }

    public static final RegistryObject<FlowerBlock> BUTTERCUP = registerBlock("buttercup", () -> new FlowerBlock(MobEffects.DIG_SPEED,  300, BlockBehaviour.Properties.copy(Blocks.DANDELION)), ModSetup.ITEM_GROUP);
    public static final RegistryObject<FlowerPotBlock> BUTTERCUP_POT = BLOCKS.register("buttercup_pot", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, Registration.BUTTERCUP, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final ResourceLocation MUD_STILL_RL = new ResourceLocation(MCEarth.MOD_ID, "block/mud_still");
    public static final ResourceLocation MUD_FLOWING_RL = new ResourceLocation(MCEarth.MOD_ID, "block/mud_flow");
    public static final ResourceLocation MUD_OVERLAY_RL = new ResourceLocation(MOD_ID, "misc/mud_fluid_overlay");

    public static final RegistryObject<FluidType> MUD_FLUID_TYPE = register("mud_fluid",
            FluidType.Properties.create().lightLevel(0));

    public static final RegistryObject<FlowingFluid> SOURCE_MUD_FLUID = FLUIDS.register("mud_fluid_block",
            () -> new ForgeFlowingFluid.Source(Registration.MUD_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_MUD_FLUID = FLUIDS.register("flowing_mud_fluid",
            () -> new ForgeFlowingFluid.Flowing(Registration.MUD_FLUID_PROPERTIES));

    public static final RegistryObject<LiquidBlock> MUD_FLUID_BLOCK = BLOCKS.register("mud_fluid_block",
            () -> new LiquidBlock(Registration.SOURCE_MUD_FLUID, BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<Item> MUD_BUCKET = ITEMS.register("mud_bucket",
            () -> new BucketItem(Registration.SOURCE_MUD_FLUID, new Item.Properties().tab(ModSetup.ITEM_GROUP).rarity(Rarity.RARE).stacksTo(1)));

    public static final ForgeFlowingFluid.Properties MUD_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            Registration.MUD_FLUID_TYPE, SOURCE_MUD_FLUID, FLOWING_MUD_FLUID)
            .slopeFindDistance(0).levelDecreasePerBlock(4).block(Registration.MUD_FLUID_BLOCK)
            .bucket(Registration.MUD_BUCKET).explosionResistance(50);


    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(MUD_STILL_RL, MUD_FLOWING_RL, MUD_OVERLAY_RL, properties));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return Registration.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static final RegistryObject<EntityType<MoobloomEntity>> MOOBLOOM = ENTITIES.register("moobloom", () -> EntityType.Builder.of(MoobloomEntity::new, MobCategory.CREATURE)
            .sized(1.4f, 0.9f)
            .setShouldReceiveVelocityUpdates(false)
            .build("moobloom"));

    public static final RegistryObject<Item> MOOBLOOM_SPAWN_EGG = ITEMS.register("moobloom_spawn_egg", () -> new ForgeSpawnEggItem(MOOBLOOM, 0xffd600, 0xfaf7dc, new Item.Properties().tab(ModSetup.ITEM_GROUP)));
}