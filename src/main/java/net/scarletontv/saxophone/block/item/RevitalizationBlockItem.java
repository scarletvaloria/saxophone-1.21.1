package net.scarletontv.saxophone.block.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class RevitalizationBlockItem extends BlockItem implements ColorableItem {
    public RevitalizationBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public int startColor(ItemStack itemStack) {
        return 0xFF2cfcf3;
    }

    @Override
    public int endColor(ItemStack itemStack) {
        return 0xFF1b8d89;
    }

    @Override
    public int backgroundColor(ItemStack itemStack) {
        return 0xF00f1c1c;
    }
}
