package net.scarletontv.saxophone.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModItems;
import net.scarletontv.saxophone.index.ModParticles;
import net.scarletontv.saxophone.index.ModSounds;
import net.scarletontv.saxophone.index.ModStatusEffects;
import net.scarletontv.saxophone.item.EmptinessItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ScreenShaker {
    @Shadow
    public abstract HungerManager getHungerManager();

    @Shadow
    public abstract void playSoundToPlayer(SoundEvent sound, SoundCategory category, float volume, float pitch);

    @Shadow
    public abstract SoundCategory getSoundCategory();

    @Shadow
    public abstract boolean isInCreativeMode();

    @Shadow
    @Final
    private PlayerInventory inventory;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "onKilledOther", at = @At("HEAD"))
    private void lesserDivinity(ServerWorld entityWorld, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        World world = this.getWorld();
        if (this.getOffHandStack().isOf(ModItems.AUTHORITYS_OBITUARY)) {
            if (world instanceof ServerWorld serverWorld) {
                if (other instanceof PlayerEntity player) {
                    addScreenShake(5, 1);
                    serverWorld.spawnParticles(ParticleTypes.SQUID_INK, other.getX(), other.getY(), other.getZ(), 35, 5, 0, 5, 0.05f);
                    serverWorld.spawnParticles(ModParticles.FOLLY, other.getX(), other.getY(), other.getZ(), 150, 0.05, 0.05, 0.05, 0.4);
                    serverWorld.spawnParticles(ModParticles.FOLLY, other.getX(), other.getY(), other.getZ(), 50, 0.05, 0.05, 0.05, 0.09);
                    serverWorld.spawnParticles(ModParticles.FOLLY, other.getX(), other.getY(), other.getZ(), 75, 0.05, 0.05, 0.05, 0.8);
                    serverWorld.spawnParticles(ParticleTypes.RAID_OMEN, other.getX(), other.getY() + 0.5, other.getZ(), 50, 0.05, 0.05, 0.05, 0.5);

                    this.playSoundToPlayer(SoundEvents.ITEM_TRIDENT_HIT_GROUND, SoundCategory.MASTER, 3, -5);
                    this.playSoundToPlayer(SoundEvents.BLOCK_CONDUIT_AMBIENT_SHORT, SoundCategory.MASTER, 3, -5);
                    this.playSoundToPlayer(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.MASTER, 3, -5);
                    this.playSoundToPlayer(SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 5, -5);
                    this.playSoundToPlayer(SoundEvents.BLOCK_END_GATEWAY_SPAWN, SoundCategory.MASTER, 3, -5);
                    this.playSoundToPlayer(ModSounds.EXECUTION, SoundCategory.MASTER, 1, 1);

                    Box area = new Box(other.getBlockPos()).expand(5, 5, 5);
                    List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, area, entity -> true);

                    for (LivingEntity entity : entities) {
                        entity.setVelocity(area.getCenter().multiply(2));
                    }

                    if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                        teleportToPurgatory(serverPlayerEntity);
                        serverPlayerEntity.setHealth(serverPlayerEntity.getMaxHealth());
                        ((ServerPlayerEntity) other).requestRespawn();
                    }

                    if (!this.isInCreativeMode()) {
                        this.getOffHandStack().decrement(1);
                    }
                }
            }

        }

        if (this.getStackInHand(this.getActiveHand()).isOf(ModItems.MARTYRDOM)) {
            if (world instanceof ServerWorld serverWorld) {
                addScreenShake(5, 1);
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void yes(CallbackInfo ci) {
        RegistryKey<World> heavenWorldKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Saxophone.MOD_ID, "asphodel"));
        if (this.getWorld().getRegistryKey() == heavenWorldKey) {
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

    @Inject(method = "shouldRenderName", at = @At("HEAD"), cancellable = true)
    private void avarita$disableNameRendering(CallbackInfoReturnable<Boolean> cir) {
        if (((Object) this instanceof PlayerEntity player)) {
            if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.AVARITIAS_MASK)) {
                cir.setReturnValue(false);
            }
        }
    }

    @ModifyReturnValue(method = "getDisplayName", at = @At("RETURN"))
    private Text herald$maskName(Text original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (Saxophone.avarice.contains(player.getUuid())) {
            return Text.translatable("playername.saxo").withColor(0xff003c).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED);
        }
        return original;
    }
}
