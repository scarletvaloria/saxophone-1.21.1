package net.scarletontv.saxophone.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.scarletontv.saxophone.index.ModItems;
import net.scarletontv.saxophone.index.ModStatusEffects;

public class ContractItem extends Item {
    public ContractItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.isSneaking()) {
            stack.decrement(1);
            user.giveItemStack(ModItems.SIGNED_CONTRACT.getDefaultStack());
            user.damage(user.getDamageSources().generic(), 3f);
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.isOf(ModItems.SIGNED_CONTRACT)) {
            target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.INSISTENCE, 999999999, 0, true, false, false));
        }
        return super.postHit(stack, target, attacker);
    }
}
