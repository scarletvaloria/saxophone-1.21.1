package net.scarletontv.saxophone.effect;

import com.nitron.nitrogen.util.interfaces.StatusEffectBackground;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModDamageTypes;

public class SaxitoxinEffect extends StatusEffect implements StatusEffectBackground {
    public Identifier smallInventorySprite() {return Saxophone.id("container/inventory/saxi_bg_small");}
    public Identifier largeInventorySprite() {return Saxophone.id("container/inventory/saxi_bg_large");}
    public Identifier hudSprite() {return Saxophone.id("hud/saxi_bg");}

    public SaxitoxinEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(ModDamageTypes.saxitoxin(entity), 2.5f);
        return super.applyUpdateEffect(entity, amplifier);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 15;
        return duration % i == 0;
    }
}