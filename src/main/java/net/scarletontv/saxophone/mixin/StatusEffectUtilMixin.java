package net.scarletontv.saxophone.mixin;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.text.Text;
import net.scarletontv.saxophone.index.ModStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectUtil.class)
public abstract class StatusEffectUtilMixin {
    @Inject(method = "getDurationText", at = @At("HEAD"), cancellable = true)
    private static void saxophone$overwriteAGAIN(StatusEffectInstance effect, float multiplier, float tickRate, CallbackInfoReturnable<Text> cir) {
        if (effect.getEffectType().equals(ModStatusEffects.OFFERING)) {
            cir.setReturnValue(Text.translatable("text.effect_name.offering"));
        }
        if (effect.getEffectType().equals(ModStatusEffects.UNRAVELING)) {
            cir.setReturnValue(Text.translatable("text.effect_name.unraveling"));
        }
    }
}
