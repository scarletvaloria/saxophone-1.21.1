package net.scarletontv.saxophone.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.scarletontv.saxophone.index.ModBlockEntities;

public class DenouementBlockEntity extends BlockEntity {
    public DenouementBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DENOUEMENT, pos, state);
    }
}