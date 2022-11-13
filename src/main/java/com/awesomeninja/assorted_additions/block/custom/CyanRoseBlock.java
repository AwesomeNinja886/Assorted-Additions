package com.awesomeninja.assorted_additions.block.custom;

import com.awesomeninja.assorted_additions.block.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CyanRoseBlock extends FlowerBlock {
    public CyanRoseBlock(Properties pProperties) {
        super(MobEffects.ABSORPTION, 10, pProperties);
    }

    protected boolean mayPlaceOn(BlockState pBlockState, BlockGetter pBlockGetter, BlockPos pPos) {
        return super.mayPlaceOn(pBlockState, pBlockGetter, pPos) || pBlockState.is(ModBlocks.GLOWING_OBSIDIAN.get());
    }
    
    public void animateTick(BlockState pBlockState, Level pLevel, BlockPos pPos, RandomSource pRandomSource) {
        VoxelShape voxelshape = this.getShape(pBlockState, pLevel, pPos, CollisionContext.empty());
        Vec3 vec3 = voxelshape.bounds().getCenter();
        double d0 = (double)pPos.getX() + vec3.x;
        double d1 = (double)pPos.getZ() + vec3.z;
        
        for(int i = 0; i < 3; ++i) {
            if (pRandomSource.nextBoolean()) {
                pLevel.addParticle(ParticleTypes.ENCHANT, d0 + pRandomSource.nextDouble() / 5.0D, (double)pPos.getY() + (0.5D - pRandomSource.nextDouble()), d1 + pRandomSource.nextDouble() / 5.0D, 0.0D, 0.0D, 0.0D);
            }
        }
  
    }
    public void entityInside(BlockState p_58238_, Level p_58239_, BlockPos pPos, Entity pEntity) {
        if (!p_58239_.isClientSide()) {
            if (pEntity instanceof ItemEntity pItemEntity) {
                if (pItemEntity.getItem().getItem() == Blocks.STONE.asItem()
                    || pItemEntity.getItem().getItem() == Blocks.COBBLESTONE.asItem()
                    || pItemEntity.getItem().getItem() == ModBlocks.CLASSIC_COBBLESTONE.get().asItem()
                    || pItemEntity.getItem().getItem() == ModBlocks.POLISHED_OBSIDIAN.get().asItem()) {
                    // Code based on Create's bulk processing, credits to the Create team
                    // The specific method is decrementProcessingTime, in the class InWorldProcessing
                    CompoundTag nbt = pItemEntity.getPersistentData();
                    if (!nbt.contains("AssortedAdditionsData")) {
                        nbt.put("AssortedAdditionsData", new CompoundTag());
                    }
                    CompoundTag assortedAdditionsData = nbt.getCompound("AssortedAdditionsData");
                    if (!assortedAdditionsData.contains("CyanRoseConvertingTime")) {
                        int timeModifierForStackSize = ((pItemEntity.getItem().getCount() - 1) / 16) + 1;
                        int convertingTime =(int) (20 * timeModifierForStackSize) + 1;
                        assortedAdditionsData.putInt("CyanRoseConvertingTime", convertingTime);
                    }
                    int value = assortedAdditionsData.getInt("CyanRoseConvertingTime");
                    if (value == 0) {
                        if (pItemEntity.getItem().getItem() == Blocks.STONE.asItem()) {
                            pItemEntity.setItem(new ItemStack(ModBlocks.CLASSIC_STONE.get().asItem(), pItemEntity.getItem().getCount()));
                        }
                        else if (pItemEntity.getItem().getItem() == Blocks.COBBLESTONE.asItem()) {
                            pItemEntity.setItem(new ItemStack(ModBlocks.CLASSIC_COBBLESTONE.get().asItem(), pItemEntity.getItem().getCount()));
                        }
                        else if (pItemEntity.getItem().getItem() == ModBlocks.CLASSIC_COBBLESTONE.get().asItem()) {
                            pItemEntity.setItem(new ItemStack(ModBlocks.RETRO_COBBLESTONE.get().asItem(), pItemEntity.getItem().getCount()));
                        }
                        else if (pItemEntity.getItem().getItem() == ModBlocks.POLISHED_OBSIDIAN.get().asItem()) {
                            pItemEntity.setItem(new ItemStack(ModBlocks.GLOWING_OBSIDIAN.get().asItem(), pItemEntity.getItem().getCount()));
                        }
                    }
                    assortedAdditionsData.putInt("CyanRoseConvertingTime", value - 1);
                }
            }
        }
    }
}
