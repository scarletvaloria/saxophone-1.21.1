package net.scarletontv.saxophone.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.scarletontv.saxophone.index.ModDamageTypes;
import net.scarletontv.saxophone.index.ModItems;

import static java.lang.Math.random;

public class ShotgunBulletEntity extends ThrownItemEntity {
    public ShotgunBulletEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public int timesBounced = 0;
    public int lifespan = 120;

    @Override
    protected Item getDefaultItem() {
        return ModItems.TWELVE_GAUGE;
    }

    @Override
    public void tick() {
        if (lifespan != 0) {
            lifespan--;
        }
        if (lifespan == 0) {
            this.discard();
        }
        super.tick();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();

        entity.damage(ModDamageTypes.bullet(entity), 3.5f);
        this.discard();
        super.onEntityHit(entityHitResult);
    }

//
//@Override
//protected void onBlockCollision(BlockState state) {
//    this.setVelocity(this.getVelocity().negate());
//    super.onBlockCollision(state);
//}


    @Override
    protected void onBlockCollision(BlockState state) {
        if (!state.isIn(BlockTags.AIR)) {
            this.setVelocity(this.getVelocity().multiply(-0.5));
            this.addVelocity(0, timesBounced -6, 0);
            timesBounced++;
        }
        super.onBlockCollision(state);
    }

    @Override
    public boolean hasNoGravity() {
        return false;
    }
}
