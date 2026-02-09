package net.scarletontv.saxophone.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModItems;
import net.scarletontv.saxophone.index.ModParticles;

import java.util.List;

public class ForsakenCharterEntity extends Entity {
    public ForsakenCharterEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    protected void initDataTracker(DataTracker.Builder builder) {}
    protected void readCustomDataFromNbt(NbtCompound nbt) {}
    protected void writeCustomDataToNbt(NbtCompound nbt) {}

    public void tick() {
        if (getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ModParticles.FOLLY,
                    this.getX(),
                    this.getY() + 0.5,
                    this.getZ(),
                    1,
                    0,
                    0,
                    0,
                    0.05
            );

            Box box = new Box(this.getBlockPos()).expand(150, 150, 150);
            List<LivingEntity> entities = getWorld().getEntitiesByClass(
                    LivingEntity.class, box,
                    entity -> true
            );

            for (LivingEntity entity : entities) {
                if (!Saxophone.avarice.contains(entity.getUuid())) {
                    if (entity instanceof PlayerEntity player) {
                        if (!player.getInventory().contains(ModItems.DEIFIC_WARRANT.getDefaultStack())) {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 60));
                        }
                    }
                }
            }
        }
        super.tick();
    }

    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (Saxophone.avarice.contains(player.getUuid()) || player.getInventory().contains(ModItems.DEIFIC_WARRANT.getDefaultStack())) {
            if (player.isSneaking()) {
                this.discard();
                dropStack(ModItems.FORSAKEN_CHARTER.getDefaultStack());
            }
        } else {
            player.sendMessage(Text.translatable("attempt.pickup.charter.fail").formatted(Formatting.ITALIC).withColor(0x8e1a41), true);
        }
        return super.interact(player, hand);
    }
}