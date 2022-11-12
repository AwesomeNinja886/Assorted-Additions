package com.awesomeninja.assorted_additions.block.custom;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class QuicksandBlock extends Block implements BucketPickup {
    public static ParticleOptions particleType = ParticleTypes.SPLASH;
    public final Item bucketItem;
    private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, (double)0.9F, 1.0D);
    public QuicksandBlock(Item pBucketItem, Properties pProperties) {
        super(pProperties);
        this.bucketItem = pBucketItem;
    }

    public boolean skipRendering(BlockState p_154268_, BlockState p_154269_, Direction p_154270_) {
        return p_154269_.is(this) ? true : super.skipRendering(p_154268_, p_154269_, p_154270_);
    }
  
     public VoxelShape getOcclusionShape(BlockState pBlockState, BlockGetter pBlockGetter, BlockPos pPos) {
        return Shapes.empty();
    }

    public void entityInside(BlockState p_154263_, Level p_154264_, BlockPos p_154265_, Entity p_154266_) {
        if (!(p_154266_ instanceof LivingEntity) || p_154266_.getFeetBlockState().is(this)) {
           p_154266_.makeStuckInBlock(p_154263_, new Vec3((double)0.9F, 1.5D, (double)0.9F));
           if (p_154264_.isClientSide) {
              RandomSource randomsource = p_154264_.getRandom();
              boolean flag = p_154266_.xOld != p_154266_.getX() || p_154266_.zOld != p_154266_.getZ();
              if (flag && randomsource.nextBoolean()) {
                 p_154264_.addParticle(particleType, p_154266_.getX(), (double)(p_154265_.getY() + 1), p_154266_.getZ(), (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F), (double)0.05F, (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F));
              }
           }
        }
  
     }
  
     public void fallOn(Level p_196695_, BlockState p_196696_, BlockPos p_196697_, Entity p_196698_, float p_196699_) {
        if (!((double)p_196699_ < 4.0D) && p_196698_ instanceof LivingEntity livingentity) {
           LivingEntity.Fallsounds $$7 = livingentity.getFallSounds();
           SoundEvent soundevent = (double)p_196699_ < 7.0D ? $$7.small() : $$7.big();
           p_196698_.playSound(soundevent, 1.0F, 1.0F);
        }
     }
  
     public VoxelShape getCollisionShape(BlockState p_154285_, BlockGetter p_154286_, BlockPos p_154287_, CollisionContext p_154288_) {
        if (p_154288_ instanceof EntityCollisionContext entitycollisioncontext) {
           Entity entity = entitycollisioncontext.getEntity();
           if (entity != null) {
              if (entity.fallDistance > 2.5F) {
                 return FALLING_COLLISION_SHAPE;
              }
  
              boolean flag = entity instanceof FallingBlockEntity;
              if (flag || canEntityWalkOnQuicksand(entity) && p_154288_.isAbove(Shapes.block(), p_154287_, false) && !p_154288_.isDescending()) {
                 return super.getCollisionShape(p_154285_, p_154286_, p_154287_, p_154288_);
              }
           }
        }
  
        return Shapes.empty();
     }
  
     public VoxelShape getVisualShape(BlockState p_154276_, BlockGetter p_154277_, BlockPos p_154278_, CollisionContext p_154279_) {
        return Shapes.empty();
     }
  
     public static boolean canEntityWalkOnQuicksand(Entity p_154256_) {
        /*
        if (p_154256_.getType().is(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS)) {
           return true;
        } else {
           return p_154256_ instanceof LivingEntity ? ((LivingEntity)p_154256_).getItemBySlot(EquipmentSlot.FEET).canWalkOnPowderedSnow((LivingEntity)p_154256_) : false;
        }
         */
        return false;
     }
  
     public ItemStack pickupBlock(LevelAccessor p_154281_, BlockPos p_154282_, BlockState p_154283_) {
        p_154281_.setBlock(p_154282_, Blocks.AIR.defaultBlockState(), 11);
        if (!p_154281_.isClientSide()) {
           p_154281_.levelEvent(2001, p_154282_, Block.getId(p_154283_));
        }
  
        return new ItemStack(Items.POWDER_SNOW_BUCKET);
     }
  
     public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.SAND_BREAK);
     }
  
     public boolean isPathfindable(BlockState p_154258_, BlockGetter p_154259_, BlockPos p_154260_, PathComputationType p_154261_) {
        return true;
     }
    
}
