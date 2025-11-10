package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class MaskItem extends Item implements ColorableItem {
    public MaskItem(Settings settings) {
        super(settings);
    }

    @Override
    public int startColor(ItemStack itemStack) {
        return 0xFFd70048;
    }

    @Override
    public int endColor(ItemStack itemStack) {
        return 0xFF8e1a41;
    }

    @Override
    public int backgroundColor(ItemStack itemStack) {
        return 0xF01c0810;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.saxophone.avaritias_mask.desc") .withColor(0x8e1a41));
        super.appendTooltip(stack, context, tooltip, type);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ARMOR,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 2f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.HEAD
                )
                .add(
                        EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, 0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.HEAD
                )
                .build();
    }
}
