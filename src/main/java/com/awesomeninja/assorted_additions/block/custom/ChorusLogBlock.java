package com.awesomeninja.assorted_additions.block.custom;

import org.jetbrains.annotations.Nullable;

import com.awesomeninja.assorted_additions.block.ModBlocks;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class ChorusLogBlock extends RotatedPillarBlock {
    public ChorusLogBlock(Properties p_55926_) {
        super(p_55926_);
    }
    
    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction,
            boolean simulate) {
        if (toolAction == ToolActions.AXE_STRIP) {
            return ModBlocks.STRIPPED_CHORUS_BLOCK.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
