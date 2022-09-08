package com.jracon.mcearth.entities.rabbits;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;

public class GenericRabbitEntity extends Rabbit {

    public GenericRabbitEntity(EntityType<? extends Rabbit> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public GenericRabbitEntity getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
        return null;
    }

    @Override
    public boolean canBreed() {
        return false;
    }

    @Override
    public boolean canFallInLove() {
        return false;
    }

    protected static boolean isGenericRabbitBrightEnoughToSpawn(BlockAndTintGetter pLevel, BlockPos pPos) {
        return pLevel.getRawBrightness(pPos, 0) > 8;
    }

    public static boolean checkGenericRabbitSpawnRules(EntityType<GenericRabbitEntity> pRabbit, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getBlockState(pPos.below()).is(BlockTags.RABBITS_SPAWNABLE_ON) && isGenericRabbitBrightEnoughToSpawn(pLevel, pPos);
    }
}
