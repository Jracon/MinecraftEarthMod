package com.jracon.mcearth.entities.chickens;

import com.jracon.mcearth.setup.Registration;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class GenericChickenEntity extends Chicken {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(GenericChickenEntity.class, EntityDataSerializers.INT);
    public static final int TYPE_AMBER = 1;
    public static final int TYPE_BRONZED = 2;
    public static final int TYPE_GOLD_CRESTED = 3;
    public static final int TYPE_MIDNIGHT = 4;
    public static final int TYPE_SKEWBALD = 5;
    public static final int TYPE_STORMY = 6;

    public GenericChickenEntity(EntityType<? extends Chicken> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE_ID, 0);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("GenericChickenEntityType", this.getGenericChickenEntityType());
    }

    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setGenericChickenEntityType(pCompound.getInt("GenericChickenEntityType"));
    }

    @Override
    public GenericChickenEntity getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        GenericChickenEntity chicken = (GenericChickenEntity) pOtherParent;
        GenericChickenEntity chicken1 = Registration.GENERIC_CHICKEN.get().create(pLevel);
        chicken1.setGenericChickenEntityType(this.getGenericChickenEntityType());
        return chicken1;
    }

    public int getGenericChickenEntityType() {
        return this.entityData.get(DATA_TYPE_ID);
    }

    public void setGenericChickenEntityType(int GenericChickenEntityTypeId) {
        this.entityData.set(DATA_TYPE_ID, GenericChickenEntityTypeId);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        int i = this.getRandomGenericChickenEntityType(pLevel);
        if (pSpawnData instanceof GenericChickenEntity.GenericChickenEntityGroupData) {
            i = ((GenericChickenEntity.GenericChickenEntityGroupData)pSpawnData).GenericChickenEntityType;
        } else {
            pSpawnData = new GenericChickenEntity.GenericChickenEntityGroupData(i);
        }

        this.setGenericChickenEntityType(i);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    private int getRandomGenericChickenEntityType(LevelAccessor pLevel) {
        int i = pLevel.getRandom().nextInt(240);
        if (i < 200) {
            if (i < 160) {
                if (i < 88) {
                    if (i < 42) {
                        if (i < 21) {
                            return 1;
                        } else {
                            return 2;
                        }
                    } else {
                        return 4;
                    }
                } else {
                    return 6;
                }
            } else {
                return 5;
            }
        } else {
            return 3;
        }
    }

    public static class GenericChickenEntityGroupData extends AgeableMob.AgeableMobGroupData {
        public final int GenericChickenEntityType;

        public GenericChickenEntityGroupData(int pGenericChickenEntityType) {
            super(0.5F);
            this.GenericChickenEntityType = pGenericChickenEntityType;
        }
    }

}
