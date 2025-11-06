package net.scarletontv.saxophone.effect;

import com.nitron.nitrogen.util.interfaces.StatusEffectBackground;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;

public class InsistenceEffect extends StatusEffect implements StatusEffectBackground {
    public InsistenceEffect(StatusEffectCategory category, int color) {
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
}
