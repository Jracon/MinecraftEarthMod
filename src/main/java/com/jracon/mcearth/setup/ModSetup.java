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
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = MCEarth.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static final String TAB_NAME = "mcearth";
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.DIAMOND);
        }
    };

    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
    }

    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(Registration.BUTTERCUP.getId(), Registration.BUTTERCUP_POT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(Registration.PINK_DAISY.getId(), Registration.PINK_DAISY_POT);
            Registration.registerAdditionalEntityInformation();
        });
    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {

        // Chickens
        event.put(Registration.AMBER_CHICKEN.get(), GenericChickenEntity.createAttributes().build());
        event.put(Registration.BRONZED_CHICKEN.get(), GenericChickenEntity.createAttributes().build());
        event.put(Registration.GOLD_CRESTED_CHICKEN.get(), GenericChickenEntity.createAttributes().build());
        event.put(Registration.MIDNIGHT_CHICKEN.get(), GenericChickenEntity.createAttributes().build());
        event.put(Registration.SKEWBALD_CHICKEN.get(), GenericChickenEntity.createAttributes().build());
        event.put(Registration.STORMY_CHICKEN.get(), GenericChickenEntity.createAttributes().build());

        event.put(Registration.CLUCKSHROOM.get(), CluckshroomEntity.prepareAttributes().build());
        event.put(Registration.FANCY_CHICKEN.get(), FancyChickenEntity.createAttributes().build());
        //

        // Cows
        event.put(Registration.ALBINO_COW.get(), GenericCowEntity.createAttributes().build());
        event.put(Registration.ASHEN_COW.get(), GenericCowEntity.createAttributes().build());
        event.put(Registration.COOKIE_COW.get(), GenericCowEntity.createAttributes().build());
        event.put(Registration.PINTO_COW.get(), GenericCowEntity.createAttributes().build());
        event.put(Registration.SUNSET_COW.get(), GenericCowEntity.createAttributes().build());

        event.put(Registration.CREAM_COW.get(), GenericCowEntity.createAttributes().build());
        event.put(Registration.DAIRY_COW.get(), GenericCowEntity.createAttributes().build());

        event.put(Registration.MOOBLOOM.get(), MoobloomEntity.createAttributes().build());
        event.put(Registration.MOOLIP.get(), MoolipEntity.createAttributes().build());
        //

        // Pigs
        event.put(Registration.MOTTLED_PIG.get(), GenericPigEntity.createAttributes().build());
        event.put(Registration.PALE_PIG.get(), GenericPigEntity.createAttributes().build());

        event.put(Registration.MUDDY_PIG.get(), MuddyPigEntity.prepareAttributes().build());

        event.put(Registration.PIEBALD_PIG.get(), GenericPigEntity.createAttributes().build());
        event.put(Registration.PINK_FOOTED_PIG.get(), GenericPigEntity.createAttributes().build());
        event.put(Registration.SOOTY_PIG.get(), GenericPigEntity.createAttributes().build());
        event.put(Registration.SPOTTED_PIG.get(), GenericPigEntity.createAttributes().build());
        //

        // Rabbits
        event.put(Registration.BOLD_STRIPED_RABBIT.get(), GenericRabbitEntity.createAttributes().build());
        event.put(Registration.FRECKLED_RABBIT.get(), GenericRabbitEntity.createAttributes().build());
        event.put(Registration.HARELEQUIN_RABBIT.get(), GenericRabbitEntity.createAttributes().build());
        event.put(Registration.MUDDY_FOOT_RABBIT.get(), GenericRabbitEntity.createAttributes().build());
        event.put(Registration.VESTED_RABBIT.get(), GenericRabbitEntity.createAttributes().build());

        event.put(Registration.JUMBO_RABBIT.get(), JumboRabbitEntity.createAttributes().build());
        //
    }
}
