package net.scarletontv.saxophone.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        MinecraftServer server = world.getServer();
        if (server != null) {
            Collection<ServerPlayerEntity> the_scooby_gang = server.getPlayerManager().getPlayerList();

            for (PlayerEntity player : the_scooby_gang) {
                if (Saxophone.contractedPlayers.contains(player.getUuid())) {
                    if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                        if (world instanceof ServerWorld serverWorld) {
                            serverPlayerEntity.requestTeleport(
                                    pos.getX() + 0.5,
                                    pos.getY() + 3,
                                    pos.getZ() + 0.5
                            );
                            serverPlayerEntity.changeGameMode(GameMode.SURVIVAL);

                            serverWorld.spawnParticles(
                                    ParticleTypes.END_ROD,
                                    serverPlayerEntity.getX(),
                                    serverPlayerEntity.getY(),
                                    serverPlayerEntity.getZ(),
                                    15,
                                    0,
                                    0,
                                    0,
                                    0.1
                            );

                            serverWorld.playSound(
                                    serverPlayerEntity.getX(),
                                    serverPlayerEntity.getY(),
                                    serverPlayerEntity.getZ(),
                                    SoundEvents.BLOCK_BEACON_POWER_SELECT,
                                    SoundCategory.MASTER,
                                    1,
                                    1,
                                    true);
                        }
                    }
                }
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
}