package net.scarletontv.saxophone.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class MonolithEntity extends Entity {
    public MonolithEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public void tick() {
        if (getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.SOUL,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    15,
                    0.3,
                    1.9,
                    0.3,
                    0.05
            );

            serverWorld.spawnParticles(ParticleTypes.SCULK_SOUL,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    15,
                    0.3,
                    1.9,
                    0.3,
                    0.05
            );
        }

        super.tick();
    }
}
