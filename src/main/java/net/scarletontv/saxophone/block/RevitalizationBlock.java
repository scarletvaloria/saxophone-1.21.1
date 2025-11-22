package net.scarletontv.saxophone.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModStatusEffects;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class RevitalizationBlock extends Block {
    public RevitalizationBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        MinecraftServer server = world.getServer();
        if (server != null) {
            Collection<ServerPlayerEntity> the_scooby_gang = server.getPlayerManager().getPlayerList();

            for (PlayerEntity player : the_scooby_gang) {
                if (Saxophone.contractedPlayers.contains(player.getUuid())) {
                    if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                        serverPlayerEntity.requestTeleport(
                                pos.getX() + 0.5,
                                pos.getY() + 3,
                                pos.getZ() + 0.5
                        );
                        serverPlayerEntity.changeGameMode(GameMode.SURVIVAL);
                    }
                }
            }
        }

        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
