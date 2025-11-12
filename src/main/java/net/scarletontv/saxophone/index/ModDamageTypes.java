package net.scarletontv.saxophone.index;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.scarletontv.saxophone.Saxophone;

public interface ModDamageTypes {
    RegistryKey<DamageType> SCYTHE_KILL = of("avarice_damage");

    static DamageSource scythe_kill(LivingEntity entity) {
        return entity.getDamageSources().create(SCYTHE_KILL); }

    RegistryKey<DamageType> BULLET = of("bullet");

    static DamageSource bullet(Entity entity) {
        return entity.getDamageSources().create(BULLET); }

    RegistryKey<DamageType> SAXITOXIN = of("saxitoxin");

    static DamageSource saxitoxin(Entity entity) {
        return entity.getDamageSources().create(SAXITOXIN); }

    RegistryKey<DamageType> RAPIER_KILL = of("rapier_kill");

    static DamageSource rapier_kill(Entity entity) {
        return entity.getDamageSources().create(RAPIER_KILL); }

    RegistryKey<DamageType> MARTYRDOM_KILL = of("martyrdom_kill");

    static DamageSource martyrdom_kill(Entity entity) {
        return entity.getDamageSources().create(MARTYRDOM_KILL); }

    RegistryKey<DamageType> OFFERING = of("offering");

    static DamageSource offering(Entity entity) {
        return entity.getDamageSources().create(OFFERING); }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Saxophone.id(name));
    }
}

