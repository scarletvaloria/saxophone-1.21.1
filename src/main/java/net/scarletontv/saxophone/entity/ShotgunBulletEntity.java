package net.scarletontv.saxophone.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.scarletontv.saxophone.index.ModDamageTypes;
import net.scarletontv.saxophone.index.ModItems;

public class ShotgunBulletEntity extends ThrownItemEntity {
    public int timesBounced = 0;
    public int lifespan = 120;

    public ShotgunBulletEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    protected Item getDefaultItem() {
        return ModItems.TWELVE_GAUGE;
    }

    public void tick() {
        if (lifespan != 0) {
            lifespan--;
        }
        if (lifespan == 0) {
            this.discard();
        }
        super.tick();
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();

        entity.damage(ModDamageTypes.bullet(entity), 5.5f);
        this.discard();
        super.onEntityHit(entityHitResult);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("timesBounced", this.timesBounced);
        nbt.putInt("lifespan", this.lifespan);
        super.writeCustomDataToNbt(nbt);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.timesBounced = nbt.getInt("timesBounced");
        this.lifespan = nbt.getInt("lifespan");
        super.readCustomDataFromNbt(nbt);
    }
}