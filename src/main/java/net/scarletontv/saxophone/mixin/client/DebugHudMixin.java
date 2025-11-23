package net.scarletontv.saxophone.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.scarletontv.saxophone.index.ModStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DebugHud.class)
public abstract class DebugHudMixin {
    @Inject(method = "drawLeftText", at = @At("HEAD"), cancellable = true)
    private void drawNewTextLeft(DrawContext context, CallbackInfo ci) {
        PlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            if (player.hasStatusEffect(ModStatusEffects.UNRAVELING)) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "drawRightText", at = @At("HEAD"), cancellable = true)
    private void drawNewLeftText(DrawContext context, CallbackInfo ci) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            if (player.hasStatusEffect(ModStatusEffects.UNRAVELING)) {
                ci.cancel();
            }
        }
    }
}
