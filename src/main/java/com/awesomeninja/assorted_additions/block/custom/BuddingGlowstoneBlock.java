package com.awesomeninja.assorted_additions.block.custom;

import com.awesomeninja.assorted_additions.block.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

/** 
 * Some code copied from {@link net.minecraft.world.level.block.BuddingAmethystBlock};
 */ 
public class BuddingGlowstoneBlock extends Block {
    public static final int GROWTH_CHANCE = 1;
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingGlowstoneBlock(Properties pProperties) {
        super(pProperties);
    }
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandomSource) {
        if (pRandomSource.nextInt(GROWTH_CHANCE) == 0) {
           Direction direction = DIRECTIONS[pRandomSource.nextInt(DIRECTIONS.length)];
           BlockPos blockpos = pPos.relative(direction);
           BlockState blockstate = pLevel.getBlockState(blockpos);
           Block block = null;
           if (canClusterGrowAtState(blockstate)) {
              block = ModBlocks.SMALL_GLOWSTONE_BUD.get();
           } else if (blockstate.is(ModBlocks.SMALL_GLOWSTONE_BUD.get()) && blockstate.getValue(GlowstoneClusterBlock.FACING) == direction) {
              block = ModBlocks.MEDIUM_GLOWSTONE_BUD.get();
           } else if (blockstate.is(ModBlocks.MEDIUM_GLOWSTONE_BUD.get()) && blockstate.getValue(GlowstoneClusterBlock.FACING) == direction) {
              block = ModBlocks.LARGE_GLOWSTONE_BUD.get();
           } else if (blockstate.is(ModBlocks.LARGE_GLOWSTONE_BUD.get()) && blockstate.getValue(GlowstoneClusterBlock.FACING) == direction) {
              block = ModBlocks.GLOWSTONE_CLUSTER.get();
           }
  
           if (block != null) {
              BlockState blockstate1 = block.defaultBlockState().setValue(GlowstoneClusterBlock.FACING, direction).setValue(GlowstoneClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
              pLevel.setBlockAndUpdate(blockpos, blockstate1);
           }
  
        }
     }

     public static boolean canClusterGrowAtState(BlockState p_152735_) {
      return p_152735_.isAir() || p_152735_.is(Blocks.WATER) && p_152735_.getFluidState().getAmount() == 8;
     }
}
