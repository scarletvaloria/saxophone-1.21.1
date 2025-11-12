package net.scarletontv.saxophone.mixin.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.scarletontv.saxophone.index.ModStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.stream.StreamSupport;

@Mixin(AbstractInventoryScreen.class)
public abstract class AbstractInventoryScreenMixin<T extends ScreenHandler> extends HandledScreen<T> implements RecipeBookProvider {
    public AbstractInventoryScreenMixin(T handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(method = "drawStatusEffectDescriptions", at = @At("HEAD"), cancellable = true)
    private void saxophone$sillyLittleThingieAboutKillingManyInnocentHumanBeings(DrawContext context, int x, int y, Iterable<StatusEffectInstance> statusEffects, CallbackInfo ci) {
        int i = this.y;
        boolean hasEffect = StreamSupport.stream(statusEffects.spliterator(), false).anyMatch(statusEffectInstance -> statusEffectInstance.equals(ModStatusEffects.OFFERING));
        if (hasEffect) {
            context.drawTextWithShadow(this.textRenderer, Text.translatable("effect.silly").formatted(Formatting.ITALIC), x + 28, i + 6, 0xEA4A7A);
            i += y;
            ci.cancel();
        }

        // x + 10 + 18, i + 6
    }

}
