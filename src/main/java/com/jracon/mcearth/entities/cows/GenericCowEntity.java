package com.jracon.mcearth.entities.cows;

import com.jracon.mcearth.setup.Registration;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class GenericCowEntity extends Cow {
    private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(GenericCowEntity.class, EntityDataSerializers.INT);
    public static final int TYPE_ALBINO = 1;
    public static final int TYPE_ASHEN = 2;
    public static final int TYPE_COOKIE = 3;
    public static final int TYPE_CREAM = 4;
    public static final int TYPE_DAIRY = 5;
    public static final int TYPE_PINTO = 6;
    public static final int TYPE_SUNSET = 7;

    public GenericCowEntity(EntityType<? extends Cow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE_ID, 0);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("GenericCowEntityType", this.getGenericCowEntityType());
    }

    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setGenericCowEntityType(pCompound.getInt("GenericCowEntityType"));
    }

    @Override
    public GenericCowEntity getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        GenericCowEntity cow = (GenericCowEntity) pOtherParent;
        GenericCowEntity cow1 = Registration.GENERIC_COW.get().create(pLevel);
        cow1.setGenericCowEntityType(this.getGenericCowEntityType());
        return cow1;
    }

    public int getGenericCowEntityType() {
        return this.entityData.get(DATA_TYPE_ID);
    }

    public EntityModel<GenericCowEntity> getModelLayerLocation(EntityRendererProvider.Context context) {
        return switch (this.getGenericCowEntityType()) {
            case 1, 2, 3, 6, 7 -> new UniqueCowModel<>(context.bakeLayer(new ModelLayerLocation(new ResourceLocation("mcearth", "generic_cow_unique"), "main")));
            case 4, 5 -> new CowModel<>(context.bakeLayer(new ModelLayerLocation(new ResourceLocation("mcearth", "generic_cow"), "main")));
            default -> throw new IllegalStateException("Unexpected value: " + this.getGenericCowEntityType());
        };
    }

    public void setGenericCowEntityType(int GenericCowEntityTypeId) {
        this.entityData.set(DATA_TYPE_ID, GenericCowEntityTypeId);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        int i = this.getRandomGenericCowEntityType(pLevel);
        if (pSpawnData instanceof GenericCowEntity.GenericCowEntityGroupData) {
            i = ((GenericCowEntity.GenericCowEntityGroupData)pSpawnData).GenericCowEntityType;
        } else {
            pSpawnData = new GenericCowEntity.GenericCowEntityGroupData(i);
        }

        this.setGenericCowEntityType(i);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    private int getRandomGenericCowEntityType(LevelAccessor pLevel) {
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

    public static class GenericCowEntityGroupData extends AgeableMob.AgeableMobGroupData {
        public final int GenericCowEntityType;

        public GenericCowEntityGroupData(int pGenericCowEntityType) {
            super(0.5F);
            this.GenericCowEntityType = pGenericCowEntityType;
        }
    }
}
