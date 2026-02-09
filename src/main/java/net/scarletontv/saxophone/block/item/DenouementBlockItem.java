package net.scarletontv.saxophone.block.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class DenouementBlockItem extends BlockItem implements ColorableItem {
    public int startColor(ItemStack itemStack) {return 0xFFd70048;}
    public int endColor(ItemStack itemStack) {return 0xFF8e1a41;}
    public int backgroundColor(ItemStack itemStack) {return 0xF01c0810;}

    public DenouementBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.saxophone.denouement_1") .withColor(0x8e1a41).formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip.saxophone.denouement_2") .withColor(0x8e1a41).formatted(Formatting.ITALIC));
        super.appendTooltip(stack, context, tooltip, type);
    }
}