package net.scarletontv.saxophone.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSoundInstance.class)
public abstract class AbstractSoundInstanceMixin {

    @Shadow
    public abstract SoundCategory getCategory();

    @Inject(method = "getVolume", at = @At("HEAD"), cancellable = true)
    private void lowerVolumeInAsphodel(CallbackInfoReturnable<Float> cir) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        RegistryKey<World> heavenWorldKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Saxophone.MOD_ID, "asphodel"));
        if (player != null) {
            if (player.getWorld().getRegistryKey() == heavenWorldKey && this.getCategory() != SoundCategory.AMBIENT) {
                cir.setReturnValue(0f);
            }
        }
    }
}