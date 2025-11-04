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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.scarletontv.saxophone.index.ModParticles;
import net.scarletontv.saxophone.index.ModSounds;
import net.scarletontv.saxophone.index.ModStatusEffects;

import java.util.List;

public class DeitysHandbellItem extends Item implements ColorableItem {
    public DeitysHandbellItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.playSound(ModSounds.BELL_TOLL, 1, 1);
        if (world instanceof ServerWorld serverWorld) {
            Box box = new Box(user.getBlockPos()).expand(5, 5, 5);
            List<LivingEntity> entities = world.getEntitiesByClass(
                    LivingEntity.class, box,
                    entity -> true
            );

            for (LivingEntity entity : entities) {
                if (entity != user) {
                    entity.addStatusEffect(new StatusEffectInstance(ModStatusEffects.INSISTENCE, 260));
                }
            }
            serverWorld.spawnParticles(ModParticles.FOLLY, user.getX(), user.getY() + 0.5, user.getZ(), 15, 0, 0, 0, 0.05);

            user.getItemCooldownManager().set(this, 60);
        }
        return super.use(world, user, hand);
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
        tooltip.add(Text.translatable("item.saxophone.deitys_handbell.desc") .withColor(0x8e1a41));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
