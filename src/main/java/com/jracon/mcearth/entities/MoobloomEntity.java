package com.jracon.mcearth.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class MoobloomEntity extends Cow {

    public MoobloomEntity(EntityType<? extends Cow> type, Level worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public Cow getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, (double)0.2F)
                .add(Attributes.FOLLOW_RANGE, 32);
    }
}
