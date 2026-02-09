package net.scarletontv.saxophone.effect;

import com.nitron.nitrogen.util.interfaces.StatusEffectBackground;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModDamageTypes;

public class OfferingEffect extends StatusEffect implements StatusEffectBackground {
    public Identifier smallInventorySprite() {return Saxophone.id("container/inventory/insis_bg_small");}
    public Identifier largeInventorySprite() {return Saxophone.id("container/inventory/insis_bg_large");}
    public Identifier hudSprite() {return Saxophone.id("hud/insis_bg");}

    public OfferingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(ModDamageTypes.offering(entity), 6.5f);
        return super.applyUpdateEffect(entity, amplifier);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 25;
        return duration % i == 0;
    }
}