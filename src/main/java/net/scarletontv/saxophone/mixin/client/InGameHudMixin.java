package net.scarletontv.saxophone.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModItems;
import net.scarletontv.saxophone.item.BulwarkItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow
    @Final
    private static Identifier CROSSHAIR_TEXTURE;

    @Unique
    private static final Identifier GUN_CROSSHAIR_FULL = Saxophone.id("hud/gun_crosshair_full");

    @Unique
    private static final Identifier GUN_CROSSHAIR_5 = Saxophone.id("hud/gun_crosshair_1");

    @Unique
    private static final Identifier GUN_CROSSHAIR_4 = Saxophone.id("hud/gun_crosshair_2");

    @Unique
    private static final Identifier GUN_CROSSHAIR_3 = Saxophone.id("hud/gun_crosshair_3");

    @Unique
    private static final Identifier GUN_CROSSHAIR_2 = Saxophone.id("hud/gun_crosshair_4");

    @Unique
    private static final Identifier GUN_CROSSHAIR_1 = Saxophone.id("hud/gun_crosshair_5");

    @Unique
    private static final Identifier GUN_CROSSHAIR_EMPTY = Saxophone.id("hud/gun_crosshair_empty");

    @Unique
    private static final Identifier ERASED_OVERLAY = Saxophone.id("hud/effect_overlay/erased_overlay");

    @WrapOperation(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"))
    private void customCrosshair(DrawContext instance, Identifier sprite, int x, int y, int width, int height, Operation<Void> original) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (sprite == CROSSHAIR_TEXTURE && client.player != null && (client.player.getMainHandStack().isOf(ModItems.TWELVE_GAUGE) || client.player.getOffHandStack().isOf(ModItems.TWELVE_GAUGE))) {
            if (BulwarkItem.ammo == 6) {
                original.call(instance, GUN_CROSSHAIR_FULL, x-3, y, 20, 13);
            }
            if (BulwarkItem.ammo == 5) {
                original.call(instance, GUN_CROSSHAIR_5, x-3, y, 20, 13);
            }
            if (BulwarkItem.ammo == 4) {
                original.call(instance, GUN_CROSSHAIR_4, x-3, y, 20, 13);
            }
            if (BulwarkItem.ammo == 3) {
                original.call(instance, GUN_CROSSHAIR_3, x-3, y, 20, 13);
            }
            if (BulwarkItem.ammo == 2) {
                original.call(instance, GUN_CROSSHAIR_2, x-3, y, 20, 13);
            }
            if (BulwarkItem.ammo == 1) {
                original.call(instance, GUN_CROSSHAIR_1, x-3, y, 20, 13);
            }
            if (BulwarkItem.ammo == 0) {
                original.call(instance, GUN_CROSSHAIR_EMPTY, x-3, y, 20, 13);
            }
        } else {
            original.call(instance, sprite, x, y, width, height);
        }
    }
}
