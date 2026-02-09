package net.scarletontv.saxophone.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.scarletontv.saxophone.index.ModBlockEntities;

public class CovetousMonolithBlockEntity extends BlockEntity {
    public CovetousMonolithBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MONOLITH, pos, state);
    }
}