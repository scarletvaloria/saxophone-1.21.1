package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class AuthoritysObituaryItem extends Item implements ColorableItem {
    public AuthoritysObituaryItem(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int startColor(ItemStack itemStack) {
        return 0xFFd70048;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int endColor(ItemStack itemStack) {
        return 0xFF8e1a41;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int backgroundColor(ItemStack itemStack) {
        return 0xF01c0810;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.saxophone.obituary.authority_1") .withColor(0x8e1a41));
        tooltip.add(Text.translatable("tooltip.saxophone.obituary.authority_2") .withColor(0x8e1a41));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
