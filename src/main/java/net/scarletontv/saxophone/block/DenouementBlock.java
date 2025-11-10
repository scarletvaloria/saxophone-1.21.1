package net.scarletontv.saxophone.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.scarletontv.saxophone.block.entity.DenouementBlockEntity;
import org.jetbrains.annotations.Nullable;

public class DenouementBlock extends BlockWithEntity {
    public static final MapCodec<DenouementBlock> CODEC = createCodec(DenouementBlock::new);
    public DenouementBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DenouementBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
