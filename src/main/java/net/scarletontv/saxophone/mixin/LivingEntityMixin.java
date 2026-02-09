package net.scarletontv.saxophone.mixin;

import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.scarletontv.saxophone.index.ModDamageTypes;
import net.scarletontv.saxophone.index.ModParticles;
import net.scarletontv.saxophone.index.ModStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "applyMovementInput", at = @At("HEAD"), cancellable = true)
    private void cancelMovement(Vec3d movementInput, float slipperiness, CallbackInfoReturnable<Vec3d> cir) {
        if ((Object) this instanceof LivingEntity player) {
            if (player.hasStatusEffect(ModStatusEffects.INSISTENCE)) {
                cir.setReturnValue(Vec3d.ZERO);
            }
        }
    }

    @Inject(method = "applyFluidMovingSpeed", at = @At("HEAD"), cancellable = true)
    private void cancelFluidMovement(double gravity, boolean falling, Vec3d motion, CallbackInfoReturnable<Vec3d> cir) {
        if ((Object) this instanceof LivingEntity player) {
            if (player.hasStatusEffect(ModStatusEffects.INSISTENCE)) {
                cir.setReturnValue(Vec3d.ZERO);
            }
        }
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;onDeath(Lnet/minecraft/entity/damage/DamageSource;)V"))
    private void duh(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity)(Object)this;
        World world = living.getWorld();
        if (source.isOf(ModDamageTypes.OFFERING)) {
            if (living instanceof ServerPlayerEntity serverPlayerEntity) {
                if (world instanceof ServerWorld serverWorld) {
                    serverPlayerEntity.changeGameMode(GameMode.SPECTATOR);
                    serverPlayerEntity.requestRespawn();
                    serverWorld.spawnParticles(ModParticles.FOLLY, living.getX(), living.getY(), living.getZ(), 50, 0, 0, 0, 0.4);
                }
            }
        }
    }
}