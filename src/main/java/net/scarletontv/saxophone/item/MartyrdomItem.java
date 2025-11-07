package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class MartyrdomItem extends AxeItem implements ColorableItem {
    public MartyrdomItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
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
}
