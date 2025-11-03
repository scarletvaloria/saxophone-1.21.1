package net.scarletontv.saxophone.mixin;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
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
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
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
public abstract class PlayerEntityMixin extends LivingEntity implements ScreenShaker {
    @Shadow
    public abstract HungerManager getHungerManager();

    @Shadow
    public abstract void requestRespawn();

    @Shadow
    public abstract void playSoundToPlayer(SoundEvent sound, SoundCategory category, float volume, float pitch);

    @Shadow
    public abstract SoundCategory getSoundCategory();

    @Shadow
    public abstract boolean isInCreativeMode();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "onKilledOther", at = @At("HEAD"))
    private void lesserDivinity(ServerWorld entityWorld, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        World world = this.getWorld();
        if (this.getOffHandStack().isOf(ModItems.AUTHORITYS_OBITUARY)) {
            if (world instanceof ServerWorld serverWorld) {
                if (other instanceof PlayerEntity player) {
                    serverWorld.spawnParticles(ParticleTypes.SQUID_INK, other.getX(), other.getY(), other.getZ(), 35, 5, 0, 5, 0.05f);
                    serverWorld.spawnParticles(ParticleTypes.END_ROD, other.getX(), other.getY(), other.getZ(), 50, 0.05, 0.05, 0.05, 0.4);
                    serverWorld.spawnParticles(ParticleTypes.END_ROD, other.getX(), other.getY(), other.getZ(), 50, 0.05, 0.05, 0.05, 0.09);
                    serverWorld.spawnParticles(ParticleTypes.END_ROD, other.getX(), other.getY(), other.getZ(), 75, 0.05, 0.05, 0.05, 0.8);
                    serverWorld.spawnParticles(ParticleTypes.RAID_OMEN, other.getX(), other.getY() + 0.5, other.getZ(), 50, 0.05, 0.05, 0.05, 0.5);
                    world.createExplosion(other, other.getX(), other.getY(), other.getZ(), 0, World.ExplosionSourceType.NONE);

                    addScreenShake(5, 1);

                    this.playSoundToPlayer(SoundEvents.ITEM_TRIDENT_HIT_GROUND, SoundCategory.MASTER, 3, -5);
                    this.playSoundToPlayer(SoundEvents.BLOCK_CONDUIT_AMBIENT_SHORT, SoundCategory.MASTER, 3, -5);
                    this.playSoundToPlayer(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.MASTER, 3, -5);
                    this.playSoundToPlayer(SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 5, -5);
                    this.playSoundToPlayer(SoundEvents.BLOCK_END_GATEWAY_SPAWN, SoundCategory.MASTER, 3, -5);

                    other.setVelocity(0, 5, 0);
                    if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                        teleportToPurgatory(serverPlayerEntity);
                        serverPlayerEntity.setHealth(serverPlayerEntity.getMaxHealth());
                        ((ServerPlayerEntity) other).requestRespawn();
                    }
                }
            }
            if (!this.isInCreativeMode()) {
                this.getOffHandStack().decrement(1);
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
