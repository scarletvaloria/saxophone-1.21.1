package net.scarletontv.saxophone.block;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModItems;
import net.scarletontv.saxophone.index.ModParticles;
import net.scarletontv.saxophone.index.ModSounds;
import net.scarletontv.saxophone.index.ModStatusEffects;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CovetousMonolithBlock extends Block  {
    public CovetousMonolithBlock(Settings settings) {
        super(settings);
    }

    public int timer = 5;

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        world.scheduleBlockTick(pos, this, 30);
        placer.playSound(SoundEvents.ENTITY_WITHER_SPAWN);
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
                    if (!player.getInventory().contains(ModItems.DEIFIC_WARRANT.getDefaultStack()) || !Saxophone.avarice.contains(player.getUuid())) {
                        player.setVelocity(0, 5, 0);
                        player.velocityModified = true;
                        world.spawnParticles(ParticleTypes.END_ROD, player.getX(), player.getY(), player.getZ(), 50, 0, 0, 0, 0.05);
                    }
                }
            }

            timer = 5;
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
}
