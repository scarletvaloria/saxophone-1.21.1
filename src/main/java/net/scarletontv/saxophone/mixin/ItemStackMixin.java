package net.scarletontv.saxophone.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract boolean isOf(Item item);

    @ModifyReturnValue(method = "getDamage", at = @At("RETURN"))
    private int noDamage(int original) {
        if (this.isOf(ModItems.LIBERATION) || this.isOf(ModItems.MARTYRDOM) || this.isOf(ModItems.WRATH_OF_TWILIGHT)) {
            return 0;
        }
        return original;
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void saxo$overrideUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        RegistryKey<World> heavenWorldKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Saxophone.MOD_ID, "asphodel"));
        if (user != null) {
            if (user.getWorld().getRegistryKey() == heavenWorldKey) {
                if (!user.isInCreativeMode()) {
                    if (!user.getInventory().contains(ModItems.DEIFIC_WARRANT.getDefaultStack())) {
                        Random rand = new Random();
                        int randomNumber = rand.nextInt(3) + 1;

                        if (randomNumber == 1) {
                            user.sendMessage(Text.literal("Your hands fumble with the item".formatted(Formatting.ITALIC).formatted(Formatting.DARK_GRAY)), true);
                        }
                        if (randomNumber == 2) {
                            user.sendMessage(Text.literal("You lose track of what you were doing".formatted(Formatting.ITALIC).formatted(Formatting.DARK_GRAY)), true);
                        }
                        if (randomNumber == 3) {
                            user.sendMessage(Text.literal("You can't focus on the task at hand".formatted(Formatting.ITALIC).formatted(Formatting.DARK_GRAY)), true);
                        }
                        cir.setReturnValue(TypedActionResult.fail(user.getStackInHand(hand)));
                    }
                }
            }
        }
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void saxo$overrideUseOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        RegistryKey<World> heavenWorldKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Saxophone.MOD_ID, "asphodel"));
        PlayerEntity user = context.getPlayer();
        if (user != null) {
            if (user.getWorld().getRegistryKey() == heavenWorldKey) {
                if (!user.isInCreativeMode()) {
                    if (!user.getInventory().contains(ModItems.DEIFIC_WARRANT.getDefaultStack())) {
                        Random rand = new Random();
                        int randomNumber = rand.nextInt(3) + 1;

                        if (randomNumber == 1) {
                            user.sendMessage(Text.literal("Your hands fumble with the item".formatted(Formatting.ITALIC).formatted(Formatting.GRAY)), true);
                        }
                        if (randomNumber == 2) {
                            user.sendMessage(Text.literal("You lose track of what you were doing".formatted(Formatting.ITALIC).formatted(Formatting.GRAY)), true);
                        }
                        if (randomNumber == 3) {
                            user.sendMessage(Text.literal("You can't focus on the task at hand".formatted(Formatting.ITALIC).formatted(Formatting.GRAY)), true);
                        }
                        cir.setReturnValue(ActionResult.FAIL);
                    }
                }
            }
        }
    }

    @Inject(method = "useOnEntity", at = @At("HEAD"), cancellable = true)
    private void saxo$overrideUseOnEntity(PlayerEntity user, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        RegistryKey<World> heavenWorldKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Saxophone.MOD_ID, "asphodel"));
        if (user != null) {
            if (user.getWorld().getRegistryKey() == heavenWorldKey) {
                if (!user.isInCreativeMode()) {
                    if (!user.getInventory().contains(ModItems.DEIFIC_WARRANT.getDefaultStack())) {
                        Random rand = new Random();
                        int randomNumber = rand.nextInt(3) + 1;

                        if (randomNumber == 1) {
                            user.sendMessage(Text.literal("Your hands fumble with the item".formatted(Formatting.ITALIC).formatted(Formatting.GRAY)), true);
                        }
                        if (randomNumber == 2) {
                            user.sendMessage(Text.literal("You lose track of what you were doing".formatted(Formatting.ITALIC).formatted(Formatting.GRAY)), true);
                        }
                        if (randomNumber == 3) {
                            user.sendMessage(Text.literal("You can't focus on the task at hand".formatted(Formatting.ITALIC).formatted(Formatting.GRAY)), true);
                        }
                        cir.setReturnValue(ActionResult.FAIL);
                    }
                }
            }
        }
    }
}