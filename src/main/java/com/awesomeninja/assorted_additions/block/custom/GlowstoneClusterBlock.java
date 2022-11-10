package com.awesomeninja.assorted_additions.block.custom;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/** 
 * Some code copied from {@link net.minecraft.world.level.block.AmethystClusterBlock};
 */ 
public class GlowstoneClusterBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    protected final VoxelShape northAabb;
    protected final VoxelShape southAabb;
    protected final VoxelShape eastAabb;
    protected final VoxelShape westAabb;
    protected final VoxelShape upAabb;
    protected final VoxelShape downAabb;
    public GlowstoneClusterBlock(int pHeight, int pWidth, Properties p_152017_) {
        super(p_152017_);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(FACING, Direction.UP));
        this.upAabb = Block.box((double)pWidth, 0.0D, (double)pWidth, (double)(16 - pWidth), (double)pHeight, (double)(16 - pWidth));
        this.downAabb = Block.box((double)pWidth, (double)(16 - pHeight), (double)pWidth, (double)(16 - pWidth), 16.0D, (double)(16 - pWidth));
        this.northAabb = Block.box((double)pWidth, (double)pWidth, (double)(16 - pHeight), (double)(16 - pWidth), (double)(16 - pWidth), 16.0D);
        this.southAabb = Block.box((double)pWidth, (double)pWidth, 0.0D, (double)(16 - pWidth), (double)(16 - pWidth), (double)pHeight);
        this.eastAabb = Block.box(0.0D, (double)pWidth, (double)pWidth, (double)pHeight, (double)(16 - pWidth), (double)(16 - pWidth));
        this.westAabb = Block.box((double)(16 - pHeight), (double)pWidth, (double)pWidth, 16.0D, (double)(16 - pWidth), (double)(16 - pWidth));
    }
    public VoxelShape getShape(BlockState pBlockState, BlockGetter pBlockGetter, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pBlockState.getValue(FACING);
        switch (direction) {
           case NORTH:
              return this.northAabb;
           case SOUTH:
              return this.southAabb;
           case EAST:
              return this.eastAabb;
           case WEST:
              return this.westAabb;
           case DOWN:
              return this.downAabb;
           case UP:
           default:
              return this.upAabb;
        }
     }
  
     public boolean canSurvive(BlockState pState, LevelReader pLevelReader, BlockPos pPos) {
        Direction direction = pState.getValue(FACING);
        BlockPos blockpos = pPos.relative(direction.getOpposite());
        return pLevelReader.getBlockState(blockpos).isFaceSturdy(pLevelReader, blockpos, direction);
     }
  
     public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNewState, LevelAccessor pLevelAccessor, BlockPos pPos, BlockPos pNewPos) {
        if (pState.getValue(WATERLOGGED)) {
           pLevelAccessor.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevelAccessor));
        }
  
        return pDirection == pState.getValue(FACING).getOpposite() && !pState.canSurvive(pLevelAccessor, pPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNewState, pLevelAccessor, pPos, pNewPos);
     }
  
     @Nullable
     public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        LevelAccessor levelaccessor = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER)).setValue(FACING, pContext.getClickedFace());
     }
  
     public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
     }
  
     public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
     }
  
     public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
     }
  
     protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pStateBuilder) {
        pStateBuilder.add(WATERLOGGED, FACING);
     }
  
     public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
     }
}
