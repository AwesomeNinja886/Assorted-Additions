package com.awesomeninja.assorted_additions.block.custom;

import java.util.Optional;

import com.awesomeninja.assorted_additions.block.ModBlocks;
import com.awesomeninja.assorted_additions.recipe.CyanRoseConversionRecipe;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PottedCyanRoseBlock extends FlowerPotBlock {
    public PottedCyanRoseBlock(Properties properties) {
        super(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.CYAN_ROSE, properties);
    }
    /**
     * Copied from {@link com.awesomeninja.assorted_additions.block.custom.CyanRoseBlock}
     */
    public void animateTick(BlockState pBlockState, Level pLevel, BlockPos pPos, RandomSource pRandomSource) {
        VoxelShape voxelshape = this.getShape(pBlockState, pLevel, pPos, CollisionContext.empty());
        Vec3 vec3 = voxelshape.bounds().getCenter();
        double d0 = (double)pPos.getX() + vec3.x;
        double d1 = (double)pPos.getZ() + vec3.z;
        
        for(int i = 0; i < 3; ++i) {
            if (pRandomSource.nextBoolean()) {
                pLevel.addParticle(ParticleTypes.ENCHANT, d0 + pRandomSource.nextDouble() / 5.0D, (double)pPos.getY() + (0.5D - pRandomSource.nextDouble()) + 0.25D, d1 + pRandomSource.nextDouble() / 5.0D, 0.0D, 0.0D, 0.0D);
            }
        }
  
    }
    /**
     * Copied from {@link com.awesomeninja.assorted_additions.block.custom.CyanRoseBlock}
     */
    public void entityInside(BlockState pBlockState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!pLevel.isClientSide()) {
            if (pEntity instanceof ItemEntity pItemEntity) {
                ItemStack pItem = pItemEntity.getItem();
                SimpleContainer container = new SimpleContainer(1);
                container.setItem(0, pItem);
                Optional<CyanRoseConversionRecipe> recipe = pLevel.getRecipeManager().getRecipeFor(CyanRoseConversionRecipe.Type.INSTANCE, container, pLevel);
                if (recipe.isPresent()) {
                    // Code based on Create's bulk processing, credits to the Create team
                    // The specific method is decrementProcessingTime, in the class InWorldProcessing
                    CompoundTag nbt = pItemEntity.getPersistentData();
                    if (!nbt.contains("AssortedAdditionsData")) {
                        nbt.put("AssortedAdditionsData", new CompoundTag());
                    }
                    CompoundTag assortedAdditionsData = nbt.getCompound("AssortedAdditionsData");
                    if (!assortedAdditionsData.contains("CyanRoseConvertingTime")) {
                        int timeModifierForStackSize = ((pItem.getCount() - 1) / 16) + 1;
                        int convertingTime = (int) (10 * timeModifierForStackSize) + 1;
                        assortedAdditionsData.putInt("CyanRoseConvertingTime", convertingTime);
                    }
                    int value = assortedAdditionsData.getInt("CyanRoseConvertingTime");
                    if (value == 0) {
                        pItemEntity.setItem(new ItemStack(recipe.get().getResultItem().getItem(), pItem.getCount()));
                    }
                    assortedAdditionsData.putInt("CyanRoseConvertingTime", value - 1);
                }
            }
        }
    }
}
