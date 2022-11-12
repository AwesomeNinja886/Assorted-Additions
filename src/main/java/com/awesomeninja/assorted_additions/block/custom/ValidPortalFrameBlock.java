package com.awesomeninja.assorted_additions.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ValidPortalFrameBlock extends Block {
    public ValidPortalFrameBlock(Properties p_49795_) {
        super(p_49795_);
    }
    @Override
    public boolean isPortalFrame(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }
}
