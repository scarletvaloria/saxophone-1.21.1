package net.scarletontv.saxophone.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    public abstract HungerManager getHungerManager();

    @Shadow
    public abstract void requestRespawn();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "onKilledOther", at = @At("HEAD"))
    private void lesserDivinity(ServerWorld world, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        if (this.getOffHandStack().isOf(ModItems.AUTHORITYS_OBITUARY)) {
            if (other instanceof PlayerEntity player) {
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    teleportToPurgatory(serverPlayerEntity);
                    serverPlayerEntity.setHealth(20f);
                    ((ServerPlayerEntity) other).requestRespawn();


                }
            }

            if (getWorld() instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.SQUID_INK, other.getX(), other.getY(), other.getZ(), 35, 0, 0, 0, 0.05f);
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void yes(CallbackInfo ci) {
        RegistryKey<World> heavenWorldKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Saxophone.MOD_ID, "asphodel"));
        if (this.getWorld().getRegistryKey() == heavenWorldKey) {
            if (this.isDead()) {
                this.requestRespawn();
            }
            this.getHungerManager().setFoodLevel(20);
            this.getHungerManager().setSaturationLevel(20);
            this.setHealth(20);
        }
    }

    @Unique
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
