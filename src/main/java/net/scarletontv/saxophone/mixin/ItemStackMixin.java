package net.scarletontv.saxophone.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.scarletontv.saxophone.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract boolean isOf(Item item);

    @ModifyReturnValue(method = "getDamage", at = @At("RETURN"))
    private int noDamage(int original) {
        if (this.isOf(ModItems.LIBERATION)) {
            return 0;
        }
        return original;
    }
}