package com.jracon.mcearth.entities.rabbits;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class JumboRabbitEntity extends Rabbit {

    public JumboRabbitEntity(EntityType<? extends Rabbit> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected static boolean isJumboRabbitBrightEnoughToSpawn(BlockAndTintGetter pLevel, BlockPos pPos) {
        return pLevel.getRawBrightness(pPos, 0) > 8;
    }

    public static boolean checkJumboRabbitSpawnRules(EntityType<JumboRabbitEntity> pRabbit, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getBlockState(pPos.below()).is(BlockTags.RABBITS_SPAWNABLE_ON) && isJumboRabbitBrightEnoughToSpawn(pLevel, pPos);
    }
}
