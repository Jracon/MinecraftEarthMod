package com.jracon.mcearth.setup;

import com.jracon.mcearth.MCEarth;
import com.jracon.mcearth.entities.chickens.CluckshroomEntity;
import com.jracon.mcearth.entities.cows.flowercows.MoobloomEntity;
import com.jracon.mcearth.entities.cows.flowercows.MoolipEntity;
import com.jracon.mcearth.entities.pigs.MuddyPigEntity;
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

@Mod.EventBusSubscriber(modid = MCEarth.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static final String TAB_NAME = "mcearth";
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon() {
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
        });
    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(Registration.MOOBLOOM.get(), MoobloomEntity.prepareAttributes().build());
        event.put(Registration.MOOLIP.get(), MoolipEntity.prepareAttributes().build());
        event.put(Registration.CLUCKSHROOM.get(), CluckshroomEntity.prepareAttributes().build());
        event.put(Registration.MUDDY_PIG.get(), MuddyPigEntity.prepareAttributes().build());
        event.put(Registration.JUMBO_RABBIT.get(), JumboRabbitEntity.createAttributes().build());
    }
}
