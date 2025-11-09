package net.scarletontv.saxophone.mixin;

import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnderPearlItem.class)
public abstract class EnderPearlItemMixin extends Item {
    public EnderPearlItemMixin(Settings settings) {
        super(settings);
    }


}
