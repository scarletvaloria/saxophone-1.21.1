package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class DeitysHandbellItem extends Item implements ColorableItem {
    public DeitysHandbellItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        return super.use(world, user, hand);
    }

    @Override
    public int startColor(ItemStack itemStack) {
        return 0xFF8a6615;
    }

    @Override
    public int endColor(ItemStack itemStack) {
        return 0xFF594109;
    }

    @Override
    public int backgroundColor(ItemStack itemStack) {
        return 0xF01a1304;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.saxophone.deitys_handbell.desc") .withColor(0x634c17));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
