package net.scarletontv.saxophone.effect;

import com.nitron.nitrogen.util.interfaces.StatusEffectBackground;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModDamageTypes;

public class SaxitoxinEffect extends StatusEffect implements StatusEffectBackground {
    public SaxitoxinEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(ModDamageTypes.saxitoxin(entity), 2.5f);
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 15;
        return duration % i == 0;
    }

    @Override
    public Identifier smallInventorySprite() {
        return Identifier.of(Saxophone.MOD_ID, "container/inventory/saxi_bg_small");
    }

    @Override
    public Identifier largeInventorySprite() {
        return Identifier.of(Saxophone.MOD_ID, "container/inventory/saxi_bg_large");
    }

    @Override
    public Identifier hudSprite() {
        return Identifier.of(Saxophone.MOD_ID, "hud/saxi_bg");
    }
}
