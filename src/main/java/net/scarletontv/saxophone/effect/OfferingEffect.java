package net.scarletontv.saxophone.effect;

import com.nitron.nitrogen.util.interfaces.StatusEffectBackground;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModDamageTypes;

public class OfferingEffect extends StatusEffect implements StatusEffectBackground {
    public OfferingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public Identifier smallInventorySprite() {
        return Identifier.of(Saxophone.MOD_ID, "container/inventory/insis_bg_small");
    }

    @Override
    public Identifier largeInventorySprite() {
        return Identifier.of(Saxophone.MOD_ID, "container/inventory/insis_bg_large");
    }

    @Override
    public Identifier hudSprite() {
        return Identifier.of(Saxophone.MOD_ID, "hud/insis_bg");
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(ModDamageTypes.offering(entity), 2.5f);
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 25;
        return duration % i == 0;
    }

}
