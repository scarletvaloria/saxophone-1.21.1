package net.scarletontv.saxophone.effect;

import com.nitron.nitrogen.util.interfaces.StatusEffectBackground;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;

public class UnravelingEffect extends StatusEffect implements StatusEffectBackground {
    public UnravelingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public Identifier smallInventorySprite() {
        return Saxophone.id("a");
    }

    @Override
    public Identifier largeInventorySprite() {
        return Saxophone.id("a");
    }

    @Override
    public Identifier hudSprite() {
        return Saxophone.id("a");
    }
}
