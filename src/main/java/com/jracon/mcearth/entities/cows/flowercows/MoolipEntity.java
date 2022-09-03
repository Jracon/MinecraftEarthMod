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
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;
import java.util.Random;

public class MoolipEntity extends Cow implements IForgeShearable {
    private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(MoolipEntity.class, EntityDataSerializers.STRING);
    public boolean isSheared;
    private EatBlockGoal eatBlockGoal;

    public MoolipEntity(EntityType<? extends MoolipEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static boolean checkFlowerSpawnRules(EntityType<MoolipEntity> pMoolipEntity, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandomSource) {
        return pLevel.getBlockState(pPos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && isBrightEnoughToSpawn(pLevel, pPos);
    }


    protected void registerGoals() {
        this.eatBlockGoal = new EatBlockGoal(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, this.eatBlockGoal);
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.2F)
                .add(Attributes.FOLLOW_RANGE, 32);
    }

    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return pLevel.getBlockState(pPos.below()).is(Blocks.GRASS) ? 10.0F : pLevel.getPathfindingCostFromLightLevels(pPos);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE, FlowerType.PINK_DAISY.type);
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

    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.tickLeash();
            if (this.tickCount % 5 == 0) {
                this.updateControlFlags();
            }
        }
        int a = new Random().nextInt(10);
        int b = new Random().nextInt(10);
        if (level.getBlockState(this.blockPosition().below(0)).getProperties().isEmpty() && (level.getBlockState(this.blockPosition().below(1)).is(Blocks.DIRT) || level.getBlockState(this.blockPosition().below(1)).is(Blocks.GRASS_BLOCK) || level.getBlockState(this.blockPosition().below(1)).is(Blocks.PODZOL) || level.getBlockState(this.blockPosition().below(1)).is(Blocks.COARSE_DIRT) || level.getBlockState(this.blockPosition().below(1)).is(Blocks.ROOTED_DIRT))) {
            if (a >= 4) {
                level.setBlock(this.blockPosition().below(0), Blocks.ALLIUM.defaultBlockState(), 1);
            } else if (b >= 4) {
                level.setBlock(this.blockPosition().below(0), Blocks.LILAC.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER), 1);
                level.setBlock(this.blockPosition().above(1), Blocks.LILAC.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), 1);
            } else {
                level.setBlock(this.blockPosition().below(0), Blocks.PEONY.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER), 1);
                level.setBlock(this.blockPosition().above(1), Blocks.PEONY.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), 1);
            }
        }
    }

    @Override
    public java.util.List<ItemStack> onSheared(@org.jetbrains.annotations.Nullable Player player, @org.jetbrains.annotations.NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
        this.gameEvent(GameEvent.SHEAR, player);
        this.isSheared = true;
        return shearInternal(player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS);
    }

    public void shear(SoundSource pCategory) {
        this.isSheared = true;
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
        return this.isAlive() && !this.isSheared;
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("Type", this.getFlowerType().type);
    }

    public void ate() {
        super.ate();
        this.isSheared = false;
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFlowerType(MoolipEntity.FlowerType.byType(pCompound.getString("Type")));
    }

    public MoolipEntity.FlowerType getFlowerType() {
        return MoolipEntity.FlowerType.byType(this.entityData.get(DATA_TYPE));
    }

    private void setFlowerType(MoolipEntity.FlowerType pType) {
        this.entityData.set(DATA_TYPE, pType.type);
    }

    @Nullable
    @Override
    public MoolipEntity getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    @Override
    public boolean isShearable(@org.jetbrains.annotations.NotNull ItemStack item, Level world, BlockPos pos) {
        return readyForShearing();
    }

    public enum FlowerType {
        PINK_DAISY("pink_daisy", Registration.PINK_DAISY.get().defaultBlockState());

        final String type;
        final BlockState blockState;

        private FlowerType(String pType, BlockState pBlockState) {
            this.type = pType;
            this.blockState = pBlockState;
        }

        static MoolipEntity.FlowerType byType(String pName) {
            for (MoolipEntity.FlowerType MoolipEntity$FlowerType : values()) {
                if (MoolipEntity$FlowerType.type.equals(pName)) {
                    return MoolipEntity$FlowerType;
                }
            }

            return PINK_DAISY;
        }

        public BlockState getBlockState() {
            return this.blockState;
        }
    }
}