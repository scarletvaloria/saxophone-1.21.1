package net.scarletontv.saxophone.block;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModItems;
import net.scarletontv.saxophone.index.ModParticles;
import net.scarletontv.saxophone.index.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CovetousMonolithBlock extends Block {
    public CovetousMonolithBlock(Settings settings) {
        super(settings);
    }

    public int timer = 2;

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (placer != null) {
            world.scheduleBlockTick(pos, this, 30);
            placer.playSound(SoundEvents.ENTITY_WITHER_SPAWN);
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (timer == 0) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            world.spawnParticles(ModParticles.FOLLY,
                    pos.getX() + 0.5,
                    pos.getY(),
                    pos.getZ() + 0.5,
                    50,
                    0,
                    0,
                    0,
                    0.05
            );

            Box box = new Box(pos).expand(80, 80, 80);
            List<LivingEntity> entities = world.getEntitiesByClass(
                    LivingEntity.class, box,
                    entity -> true
            );

            for (LivingEntity entity : entities) {
                if (entity instanceof PlayerEntity player) {
                    if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                        if (!serverPlayerEntity.getInventory().contains(ModItems.DEIFIC_WARRANT.getDefaultStack())) {
                            teleportToPurgatory(serverPlayerEntity);
                            serverPlayerEntity.setHealth(serverPlayerEntity.getMaxHealth());
                            player.requestRespawn();

                            serverPlayerEntity.getServer().getPlayerManager().broadcast(Text.literal((player.getDisplayName() != null ? player.getDisplayName().getString() : player.getNameForScoreboard()) + " was the next step towards the Renewal"), false);
                        }
                    }
                    world.spawnParticles(ParticleTypes.END_ROD, player.getX(), player.getY(), player.getZ(), 50, 0, 0, 0, 0.05);
                }
            }

            timer = 2;
        } else {
            Box box = new Box(pos).expand(50, 50, 50);
            List<LivingEntity> entities = world.getEntitiesByClass(
                    LivingEntity.class, box,
                    entity -> true
            );

            for (LivingEntity entity : entities) {
                if (entity instanceof PlayerEntity player) {
                    player.playSoundToPlayer(ModSounds.BELL_TOLL, SoundCategory.BLOCKS, 1, 1);
                }
            }
            timer--;
            world.spawnParticles(ModParticles.FOLLY,
                    pos.getX() + 0.5,
                    pos.getY() + 2,
                    pos.getZ() + 0.5,
                    timer,
                    0,
                    0,
                    0,
                    0.05
            );

        }
        world.scheduleBlockTick(pos, this, 60); // 1 tick later
        super.scheduledTick(state, world, pos, random);
    }

    private void teleportToPurgatory(ServerPlayerEntity player) {
        RegistryKey<World> heavenWorldKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Saxophone.MOD_ID, "asphodel"));

        MinecraftServer server = player.getServer();
        if (server == null) {
            Saxophone.LOGGER.error("Server is null!");
            return;
        }

        ServerWorld targetWorld = server.getWorld(heavenWorldKey);
        if (targetWorld != null) {
            BlockPos spawnPos = targetWorld.getSpawnPos();

            player.teleport(
                    targetWorld,
                    0,
                    spawnPos.getY() + 100,
                    0,
                    player.getYaw(),
                    player.getPitch()
            );
        } else {
            Saxophone.LOGGER.error("Could not find asphodel dimension!");
        }
    }


}
