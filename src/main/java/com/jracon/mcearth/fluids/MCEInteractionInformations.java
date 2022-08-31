package com.jracon.mcearth.fluids;

import com.jracon.mcearth.setup.Registration;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidInteractionRegistry;

public class MCEInteractionInformations {
    public static void init() {
        FluidInteractionRegistry.addInteraction(ForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                Registration.MUD_FLUID_TYPE.get(),
                fluidState -> Blocks.DIRT.defaultBlockState()
        ));
        FluidInteractionRegistry.addInteraction(ForgeMod.WATER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                (level, currentPos, relativePos, currentState) -> level.getFluidState(relativePos).getFluidType() == Registration.MUD_FLUID_TYPE.get(), (level, currentPos, relativePos, currentState) ->
        {
            level.setBlockAndUpdate(currentPos, ForgeEventFactory.fireFluidPlaceBlockEvent(level, currentPos, currentPos, Blocks.WATER.defaultBlockState()));
            level.levelEvent(2001, currentPos, Block.getId(Blocks.WATER.defaultBlockState()));
        }));
    }
}
