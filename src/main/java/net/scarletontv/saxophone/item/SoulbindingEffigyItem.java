package net.scarletontv.saxophone.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;

public class SoulbindingEffigyItem extends Item {
    public SoulbindingEffigyItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            if (!Saxophone.erasedUUIDS.contains(user.getUuid())) {
                Saxophone.erasedUUIDS.add(user.getUuid());
                user.getItemCooldownManager().set(this, 260);
            } else {
                user.sendMessage(Text.translatable("text.soulbound.fail").formatted(Formatting.ITALIC).withColor(0x8e1a41), true);
            }
        }
        return super.use(world, user, hand);
    }
}
