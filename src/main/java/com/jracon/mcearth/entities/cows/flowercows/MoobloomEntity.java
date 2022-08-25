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
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;

public class MoobloomEntity extends Cow implements IForgeShearable {
    private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(MoobloomEntity.class, EntityDataSerializers.STRING);

    public MoobloomEntity(EntityType<? extends MoobloomEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static boolean checkFlowerSpawnRules(EntityType<MoobloomEntity> pMoobloomEntity, LevelAccessor pLevle, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandomSource) {
        return pLevle.getBlockState(pPos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && isBrightEnoughToSpawn(pLevle, pPos);
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.2F)
                .add(Attributes.FOLLOW_RANGE, 32);
    }

    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return pLevel.getBlockState(pPos.below()).is(Blocks.GRASS) ? 10.0F : pLevel.getPathfindingCostFromLightLevels(pPos);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE, FlowerType.BUTTERCUP.type);
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (false && itemstack.getItem() == Items.SHEARS && this.readyForShearing()) { //Forge: Moved to onSheared
            this.shear(SoundSource.PLAYERS);
            this.gameEvent(GameEvent.SHEAR, pPlayer);
            if (!this.level.isClientSide) {
                itemstack.hurtAndBreak(1, pPlayer, (p_28927_) -> {
                    p_28927_.broadcastBreakEvent(pHand);
                });
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    @Override
    public java.util.List<ItemStack> onSheared(@org.jetbrains.annotations.Nullable Player player, @org.jetbrains.annotations.NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
        this.gameEvent(GameEvent.SHEAR, player);
        return shearInternal(player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS);
    }

    public void shear(SoundSource pCategory) {
        shearInternal(pCategory).forEach(s -> this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(1.0D), this.getZ(), s)));
    }

    private java.util.List<ItemStack> shearInternal(SoundSource pCategory) {
        this.level.playSound((Player) null, this, SoundEvents.MOOSHROOM_SHEAR, pCategory, 1.0F, 1.0F);
        if (!this.level.isClientSide()) {
            java.util.List<ItemStack> items = new java.util.ArrayList<>();
            for (int i = 0; i < 1; ++i) {
                items.add(new ItemStack(this.getFlowerType().blockState.getBlock()));
            }
            return items;
        }
        return java.util.Collections.emptyList();
    }

    public boolean readyForShearing() {
        return this.isAlive();
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("Type", this.getFlowerType().type);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFlowerType(MoobloomEntity.FlowerType.byType(pCompound.getString("Type")));
    }

    public MoobloomEntity.FlowerType getFlowerType() {
        return MoobloomEntity.FlowerType.byType(this.entityData.get(DATA_TYPE));
    }

    private void setFlowerType(MoobloomEntity.FlowerType pType) {
        this.entityData.set(DATA_TYPE, pType.type);
    }

    @Nullable
    @Override
    public MoobloomEntity getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    @Override
    public boolean isShearable(@org.jetbrains.annotations.NotNull ItemStack item, Level world, BlockPos pos) {
        return readyForShearing();
    }
    public enum FlowerType {
        BUTTERCUP("buttercup", Registration.BUTTERCUP.get().defaultBlockState());

        final String type;
        final BlockState blockState;

        private FlowerType(String pType, BlockState pBlockState) {
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

        public BlockState getBlockState() {
            return this.blockState;
        }
    }
}