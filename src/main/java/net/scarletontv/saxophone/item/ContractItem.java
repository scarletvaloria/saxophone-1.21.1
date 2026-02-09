package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModItems;
import net.scarletontv.saxophone.index.ModStatusEffects;

import java.util.List;

public class ContractItem extends Item implements ColorableItem {
    public int startColor(ItemStack itemStack) {return 0xFFd70048;}
    public int endColor(ItemStack itemStack) {return 0xFF8e1a41;}
    public int backgroundColor(ItemStack itemStack) {return 0xF01c0810;}

    public ContractItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.isSneaking()) {
            if (stack.isOf(ModItems.CONTRACT)) {
                stack.decrement(1);
                user.giveItemStack(ModItems.SIGNED_CONTRACT.getDefaultStack());
                user.damage(user.getDamageSources().generic(), 3f);
                Saxophone.contractedPlayers.add(user.getUuid());
            }
        }
        return super.use(world, user, hand);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.isOf(ModItems.SIGNED_CONTRACT)) {
            if (!target.hasStatusEffect(ModStatusEffects.INSISTENCE)) {
                target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.INSISTENCE, 999999999, 0, true, false, false)); // instead of a bunch of 9s, use Integer.MAX_VALUE instead.
            }
            if (target.hasStatusEffect(ModStatusEffects.INSISTENCE)) {
                target.removeStatusEffect(ModStatusEffects.INSISTENCE);
            }
        }
        return super.postHit(stack, target, attacker);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.isOf(ModItems.CONTRACT)) {
            tooltip.add(Text.translatable("item.saxophone.contract_unsigned").withColor(0x8e1a41));
        }
        if (stack.isOf(ModItems.SIGNED_CONTRACT)) {
            tooltip.add(Text.translatable("item.saxophone.contract_signed_0").withColor(0x8e1a41));
            tooltip.add(Text.translatable("item.saxophone.contract_signed_1").withColor(0x8e1a41));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}