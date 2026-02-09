package net.scarletontv.saxophone.mixin.client;

import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.scarletontv.saxophone.Saxophone;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// Mixin by Gabe-Real
@Mixin(PlayerListHud.class)
public class PlayerListHudMixin {

    @Inject(method = "collectPlayerEntries", at = @At("HEAD"), cancellable = true)
    private void filterAvarice(CallbackInfoReturnable<List<PlayerListEntry>> cir) {
        List<PlayerListEntry> originalList = cir.getReturnValue();
        if (originalList == null) {
            return;
        }

        List<PlayerListEntry> filteredList = originalList.stream()
                .filter(entry -> {
                    if (entry.getProfile() == null) {
                        return true;
                    }

                    UUID playerUuid = entry.getProfile().getId();
                    boolean isBanned = Saxophone.avarice.contains(playerUuid);

                    if (isBanned) {
                        Saxophone.LOGGER.debug("Hiding banned player from tab list: {} ({})",
                                entry.getProfile().getName(), playerUuid);
                    }

                    return !isBanned;
                })
                .collect(Collectors.toList());

        cir.setReturnValue(filteredList);
    }
}