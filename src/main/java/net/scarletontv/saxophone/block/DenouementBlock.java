package net.scarletontv.saxophone.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.scarletontv.saxophone.block.entity.DenouementBlockEntity;
import net.scarletontv.saxophone.effect.SaxitoxinEffect;
import net.scarletontv.saxophone.index.ModStatusEffects;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class DenouementBlock extends BlockWithEntity {
    public static final MapCodec<DenouementBlock> CODEC = createCodec(DenouementBlock::new);
    public DenouementBlock(Settings settings) {
        super(settings);
    }
    public int timer = 30;

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

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        MinecraftServer server = world.getServer();

        if (server != null) {
            Collection<ServerPlayerEntity> the_scooby_gang = server.getPlayerManager().getPlayerList();

            for (PlayerEntity player : the_scooby_gang) {
                player.addStatusEffect(new StatusEffectInstance(ModStatusEffects.OFFERING, Integer.MAX_VALUE));
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
