package net.scarletontv.saxophone.index;

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

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Saxophone.id(name));
    }
}

