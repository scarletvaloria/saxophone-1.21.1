package net.scarletontv.saxophone.item;

import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.scarletontv.saxophone.index.ModItems;

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
}
