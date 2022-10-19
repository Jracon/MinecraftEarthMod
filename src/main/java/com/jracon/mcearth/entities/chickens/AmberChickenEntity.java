package com.jracon.mcearth.entities.chickens;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AmberChickenEntity extends Chicken {
    public AmberChickenEntity(EntityType<? extends Chicken> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public AmberChickenEntity getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
        return null;
    }
}
