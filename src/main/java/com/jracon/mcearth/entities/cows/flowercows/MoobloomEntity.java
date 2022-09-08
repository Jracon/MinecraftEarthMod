package com.jracon.mcearth.entities.cows.flowercows;

import com.jracon.mcearth.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.IForgeShearable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;

public class MoobloomEntity extends Cow implements IForgeShearable {
    private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(MoobloomEntity.class, EntityDataSerializers.STRING);
    public boolean isSheared;

    public MoobloomEntity(EntityType<? extends MoobloomEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void registerGoals() {
        EatBlockGoal eatBlockGoal = new EatBlockGoal(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, eatBlockGoal);
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public void ate() {
        super.ate();
        this.setSheared(false);
    }

    public void setSheared(boolean pSheared) {
        this.isSheared = pSheared;
    }

    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.tickLeash();
            if (this.tickCount % 5 == 0) {
                this.updateControlFlags();
            }
        }
        int a = new Random().nextInt(10);
        if (level.getBlockState(this.blockPosition().below(0)).getProperties().isEmpty() && level.getBlockState(this.blockPosition().above(1)).getProperties().isEmpty() && (level.getBlockState(this.blockPosition().below(1)).is(Blocks.DIRT) || level.getBlockState(this.blockPosition().below(1)).is(Blocks.GRASS_BLOCK) || level.getBlockState(this.blockPosition().below(1)).is(Blocks.PODZOL) || level.getBlockState(this.blockPosition().below(1)).is(Blocks.COARSE_DIRT) || level.getBlockState(this.blockPosition().below(1)).is(Blocks.ROOTED_DIRT))) {
            if (a >= 8) {
                level.setBlock(this.blockPosition().below(0), Blocks.DANDELION.defaultBlockState(), 1);
            } else {
                level.setBlock(this.blockPosition().below(0), Blocks.SUNFLOWER.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER), 1);
                level.setBlock(this.blockPosition().above(1), Blocks.SUNFLOWER.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), 1);
            }
        }
    }

    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return pLevel.getBlockState(pPos.below()).is(Blocks.GRASS) ? 10.0F : pLevel.getPathfindingCostFromLightLevels(pPos);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE, FlowerType.BUTTERCUP.type);
    }

    public @NotNull InteractionResult mobInteract(@NotNull Player pPlayer, @NotNull InteractionHand pHand) {
        return super.mobInteract(pPlayer, pHand);
    }

    public java.util.@NotNull List<ItemStack> onSheared(@Nullable Player player, @org.jetbrains.annotations.NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
        world.playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
        this.gameEvent(GameEvent.SHEAR, player);
        if (!world.isClientSide) {
            this.setSheared(true);
            int i = 1;
            java.util.List<ItemStack> items = new java.util.ArrayList<>();
            for (int j = 0; j < i; ++j) {
                items.add(new ItemStack(this.getFlowerType().blockState.getBlock()));
            }
            return items;
        }
        return java.util.Collections.emptyList();
    }

    public boolean readyForShearing() {
        return this.isAlive() && !this.isSheared;
    }

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("Sheared", this.isSheared);
        pCompound.putString("Type", this.getFlowerType().type);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setSheared(pCompound.getBoolean("Sheared"));
        this.setFlowerType(MoobloomEntity.FlowerType.byType(pCompound.getString("Type")));
    }

    public MoobloomEntity.FlowerType getFlowerType() {
        return MoobloomEntity.FlowerType.byType(this.entityData.get(DATA_TYPE));
    }

    private void setFlowerType(MoobloomEntity.FlowerType pType) {
        this.entityData.set(DATA_TYPE, pType.type);
    }

    public MoobloomEntity getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
        return null;
    }

    public boolean isShearable(@org.jetbrains.annotations.NotNull ItemStack item, Level world, BlockPos pos) {
        return readyForShearing();
    }

    public enum FlowerType {
        BUTTERCUP("buttercup", Registration.BUTTERCUP.get().defaultBlockState());
        final String type;
        final BlockState blockState;

        FlowerType(String pType, BlockState pBlockState) {
            this.type = pType;
            this.blockState = pBlockState;
        }

        static MoobloomEntity.FlowerType byType(String pName) {
            for (MoobloomEntity.FlowerType MoobloomEntity$FlowerType : values()) {
                if (MoobloomEntity$FlowerType.type.equals(pName)) {
                    return MoobloomEntity$FlowerType;
                }
            }
            return BUTTERCUP;
        }
    }
}