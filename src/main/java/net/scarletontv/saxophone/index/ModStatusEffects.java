package net.scarletontv.saxophone.index;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.effect.InsistenceEffect;
import net.scarletontv.saxophone.effect.SaxitoxinEffect;

public interface ModStatusEffects {
    RegistryEntry<StatusEffect> INSISTENCE = create("insistence", new InsistenceEffect(StatusEffectCategory.NEUTRAL, 0x0000));
    RegistryEntry<StatusEffect> SAXITOXIN = create("saxitoxin", new SaxitoxinEffect(StatusEffectCategory.HARMFUL, 0x0000));

    private static RegistryEntry<StatusEffect> create(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Saxophone.id(name), statusEffect);
    }

    static void registerStatusEffects() {
    }
}
