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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
    
public class ChiseledPillarBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");

    protected static final VoxelShape CENTER_SHAPE_Y = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape TOP_SHAPE_Y = Block.box(0.0D, 10.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    protected static final VoxelShape BOTTOM_SHAPE_Y = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    protected static final VoxelShape BOTH_SHAPE_Y = Shapes.or(TOP_SHAPE_Y, Shapes.or(CENTER_SHAPE_Y, BOTTOM_SHAPE_Y));
    protected static final VoxelShape TOP_ONLY_SHAPE_Y = Shapes.or(CENTER_SHAPE_Y, TOP_SHAPE_Y);
    protected static final VoxelShape BOTTOM_ONLY_SHAPE_Y = Shapes.or(CENTER_SHAPE_Y, BOTTOM_SHAPE_Y);

    protected static final VoxelShape CENTER_SHAPE_X = Block.box(0.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D);
    protected static final VoxelShape TOP_SHAPE_X = Block.box(10.0D, 0.0D, 0.0D, 6.0D, 16.0D, 16.0D);
    protected static final VoxelShape BOTTOM_SHAPE_X = Block.box(0.0D, 0.0D, 0.0D, 6.0D, 16.0D, 16.0D);
    protected static final VoxelShape BOTH_SHAPE_X = Shapes.or(TOP_SHAPE_X, Shapes.or(CENTER_SHAPE_X, BOTTOM_SHAPE_X));
    protected static final VoxelShape TOP_ONLY_SHAPE_X = Shapes.or(CENTER_SHAPE_X, TOP_SHAPE_X);
    protected static final VoxelShape BOTTOM_ONLY_SHAPE_X = Shapes.or(CENTER_SHAPE_X, BOTTOM_SHAPE_X);

    protected static final VoxelShape CENTER_SHAPE_Z = Block.box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 16.0D);
    protected static final VoxelShape TOP_SHAPE_Z = Block.box(0.0D, 0.0D, 10.0D, 16.0D, 16.0D, 6.0D);
    protected static final VoxelShape BOTTOM_SHAPE_Z = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 6.0D);
    protected static final VoxelShape BOTH_SHAPE_Z = Shapes.or(TOP_SHAPE_Z, Shapes.or(CENTER_SHAPE_Z, BOTTOM_SHAPE_Z));
    protected static final VoxelShape TOP_ONLY_SHAPE_Z = Shapes.or(CENTER_SHAPE_Z, TOP_SHAPE_Z);
    protected static final VoxelShape BOTTOM_ONLY_SHAPE_Z = Shapes.or(CENTER_SHAPE_Z, BOTTOM_SHAPE_Z);

    public ChiseledPillarBlock(Properties p_55926_) {
        super(p_55926_);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y).setValue(TOP, true).setValue(BOTTOM, true));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(AXIS) == Direction.Axis.X) {
            if (pState.getValue(TOP) && pState.getValue(BOTTOM)) {
                return BOTH_SHAPE_X;
            } else if (pState.getValue(TOP) && !pState.getValue(BOTTOM)) {
                return TOP_ONLY_SHAPE_X;
            } else if (!pState.getValue(TOP) && pState.getValue(BOTTOM)) {
                return BOTTOM_ONLY_SHAPE_X;
            } else {
                return CENTER_SHAPE_X;
            }
        } else if (pState.getValue(AXIS) == Direction.Axis.Y) {
            if (pState.getValue(TOP) && pState.getValue(BOTTOM)) {
                return BOTH_SHAPE_Y;
            } else if (pState.getValue(TOP) && !pState.getValue(BOTTOM)) {
                return TOP_ONLY_SHAPE_Y;
            } else if (!pState.getValue(TOP) && pState.getValue(BOTTOM)) {
                return BOTTOM_ONLY_SHAPE_Y;
            } else {
                return CENTER_SHAPE_Y;
            }
        } else if (pState.getValue(AXIS) == Direction.Axis.Z) {
            if (pState.getValue(TOP) && pState.getValue(BOTTOM)) {
                return BOTH_SHAPE_Z;
            } else if (pState.getValue(TOP) && !pState.getValue(BOTTOM)) {
                return TOP_ONLY_SHAPE_Z;
            } else if (!pState.getValue(TOP) && pState.getValue(BOTTOM)) {
                return BOTTOM_ONLY_SHAPE_Z;
            } else {
                return CENTER_SHAPE_Z;
            }
        }
        return BOTH_SHAPE_Y;
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
        Direction.Axis pAxis = pContext.getClickedFace().getAxis();

        Level pLevel = pContext.getLevel();
        BlockPos pPos = pContext.getClickedPos();
        BlockPos above = getAbove(pPos, pAxis);
        BlockPos below = getBelow(pPos, pAxis);
        BlockState blockAbove = pLevel.getBlockState(above);
        BlockState blockBelow = pLevel.getBlockState(below);
        boolean hasBottom = true;
        boolean hasTop = true;
        if (blockAbove.getBlock() instanceof ChiseledPillarBlock && blockAbove.getValue(AXIS) == pAxis) {
            hasTop = false;
        }
        if (blockBelow.getBlock() instanceof ChiseledPillarBlock && blockBelow.getValue(AXIS) == pAxis) {
            hasBottom = false;
        }
        return this.defaultBlockState().setValue(AXIS, pAxis).setValue(BOTTOM, hasBottom).setValue(TOP, hasTop);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        BlockPos above = getAbove(pCurrentPos, pState.getValue(AXIS));
        BlockPos below = getBelow(pCurrentPos, pState.getValue(AXIS));
        BlockState blockAbove = pLevel.getBlockState(above);
        BlockState blockBelow = pLevel.getBlockState(below);
        boolean hasBottom = true;
        boolean hasTop = true;
        if (blockAbove.getBlock() instanceof ChiseledPillarBlock && blockAbove.getValue(AXIS) == pState.getValue(AXIS)) {
            hasTop = false;
        }
        if (blockBelow.getBlock() instanceof ChiseledPillarBlock && blockBelow.getValue(AXIS) == pState.getValue(AXIS)) {
            hasBottom = false;
        }
        return pState.setValue(BOTTOM, hasBottom).setValue(TOP, hasTop);
    }

    private static BlockPos getAbove(BlockPos pInitialPos, Direction.Axis pAxis) {
        if (pAxis == Direction.Axis.Y) {
            return pInitialPos.above();
        } else if (pAxis == Direction.Axis.X) {
            return pInitialPos.east();
        } else if (pAxis == Direction.Axis.Z) {
            return pInitialPos.north();
        } else {
            return pInitialPos.above();
        }
    }

    private static BlockPos getBelow(BlockPos pInitialPos, Direction.Axis pAxis) {
        if (pAxis == Direction.Axis.Y) {
            return pInitialPos.below();
        } else if (pAxis == Direction.Axis.X) {
            return pInitialPos.west();
        } else if (pAxis == Direction.Axis.Z) {
            return pInitialPos.south();
        } else {
            return pInitialPos.below();
        }
    }
}
