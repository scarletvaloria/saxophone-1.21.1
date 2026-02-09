package net.scarletontv.saxophone.effect;

import com.nitron.nitrogen.util.interfaces.StatusEffectBackground;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;

public class InsistenceEffect extends StatusEffect implements StatusEffectBackground {
    public Identifier smallInventorySprite() {return Saxophone.id("container/inventory/insis_bg_small");}
    public Identifier largeInventorySprite() {return Saxophone.id("container/inventory/insis_bg_large");}
    public Identifier hudSprite() {return Saxophone.id("hud/insis_bg");}

    public InsistenceEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
}