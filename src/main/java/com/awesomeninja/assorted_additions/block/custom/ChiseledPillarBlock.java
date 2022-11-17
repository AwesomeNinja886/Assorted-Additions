package com.awesomeninja.assorted_additions.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
    
public class ChiseledPillarBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");

    protected static final VoxelShape CENTER_SHAPE_X = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape CENTER_SHAPE_Y = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape CENTER_SHAPE_Z = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public ChiseledPillarBlock(Properties p_55926_) {
        super(p_55926_);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y).setValue(TOP, true).setValue(BOTTOM, true));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(AXIS) == Direction.Axis.X ? CENTER_SHAPE_X : pState.getValue(AXIS) == Direction.Axis.Z ? CENTER_SHAPE_Z : CENTER_SHAPE_Y;
    }

    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }
    
    public BlockState rotate(BlockState pBlockState, Rotation pRotation) {
        return rotatePillar(pBlockState, pRotation);
    }
  
    public static BlockState rotatePillar(BlockState pBlockState, Rotation pRotation) {
        switch (pRotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((Direction.Axis)pBlockState.getValue(AXIS)) {
                    case X:
                    return pBlockState.setValue(AXIS, Direction.Axis.Z);
                    case Z:
                    return pBlockState.setValue(AXIS, Direction.Axis.X);
                    default:
                    return pBlockState;
                }
            default:
                return pBlockState;
        }
    }

    @Override
    public BlockState mirror(BlockState pBlockState, Mirror pMirror) {
        if ((pMirror == Mirror.FRONT_BACK && pBlockState.getValue(AXIS) == Direction.Axis.X) || (pMirror == Mirror.LEFT_RIGHT && pBlockState.getValue(AXIS) == Direction.Axis.Z)) {
            boolean hasTop = pBlockState.getValue(TOP);
            boolean hasBottom = pBlockState.getValue(BOTTOM);
            return pBlockState.setValue(TOP, hasBottom).setValue(BOTTOM, hasTop);
        }
        return pBlockState;
    }
  
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBlockState) {
        pBlockState.add(AXIS, TOP, BOTTOM);
    }
  
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState toReturn = this.defaultBlockState();
        toReturn.setValue(AXIS, pContext.getClickedFace().getAxis());

        Level pLevel = pContext.getLevel();
        BlockPos pPos = pContext.getClickedPos();
        BlockPos above = pPos.above();
        BlockPos below = pPos.below();
        if (pContext.getClickedFace().getAxis() == Direction.Axis.Y) {
            above = pPos.above();
            below = pPos.below();
        } else if (pContext.getClickedFace().getAxis() == Direction.Axis.X) {
            above = pPos.east();
            below = pPos.west();
        } else if (pContext.getClickedFace().getAxis() == Direction.Axis.Z) {
            above = pPos.south();
            below = pPos.north();
        }
        BlockState blockAbove = pLevel.getBlockState(above);
        BlockState blockBelow = pLevel.getBlockState(below);
        if (blockAbove.getBlock() instanceof ChiseledPillarBlock && blockAbove.getValue(AXIS) == toReturn.getValue(AXIS)) {
            toReturn.setValue(BOTTOM, false);
        }
        if (blockBelow.getBlock() instanceof ChiseledPillarBlock && blockBelow.getValue(AXIS) == toReturn.getValue(AXIS)) {
            toReturn.setValue(TOP, false);
        }
        return toReturn;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        BlockState toReturn = pState;
        BlockPos above = pCurrentPos.above();
        BlockPos below = pCurrentPos.below();
        if (pFacing.getAxis() == Direction.Axis.Y) {
            above = pCurrentPos.above();
            below = pCurrentPos.below();
        } else if (pFacing.getAxis() == Direction.Axis.X) {
            above = pCurrentPos.east();
            below = pCurrentPos.west();
        } else if (pFacing.getAxis() == Direction.Axis.Z) {
            above = pCurrentPos.south();
            below = pCurrentPos.north();
        }
        if (pLevel.getBlockState(above).getBlock() instanceof ChiseledPillarBlock && pLevel.getBlockState(above).getValue(AXIS) == toReturn.getValue(AXIS)) {
            toReturn.setValue(BOTTOM, false);
        } else {
            toReturn.setValue(BOTTOM, true);
        }
        if (pLevel.getBlockState(below).getBlock() instanceof ChiseledPillarBlock && pLevel.getBlockState(below).getValue(AXIS) == toReturn.getValue(AXIS)) {
            toReturn.setValue(TOP, false);
        } else {
            toReturn.setValue(TOP, true);
        }
        return toReturn;
    }
}
