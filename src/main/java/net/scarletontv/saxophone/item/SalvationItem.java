package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class SalvationItem extends SwordItem implements ColorableItem, CustomHitParticleItem {
    public int startColor(ItemStack itemStack) {return 0xFF2cfcf3;}
    public int endColor(ItemStack itemStack) {return 0xFF1b8d89;}
    public int backgroundColor(ItemStack itemStack) {return 0xF00f1c1c;}
    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0x1b8d89, 0x101010)};
    
    public SalvationItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.saxophone.salvation.desc") .withColor(0x23bab3));
        super.appendTooltip(stack, context, tooltip, type);
    }

    public void spawnHitParticles(PlayerEntity player) {
        double deltaX = -MathHelper.sin((float) (player.getYaw() * (Math.PI / 180.0F)));
        double deltaZ = MathHelper.cos((float) (player.getYaw() * (Math.PI / 180.0F)));
        World var7 = player.getWorld();
        if (var7 instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    EFFECTS[player.getRandom().nextInt(EFFECTS.length)],
                    player.getX() + deltaX,
                    player.getBodyY(0.5F),
                    player.getZ() + deltaZ,
                    0, deltaX, 0.0F, deltaZ, 0.0F
            );
        }
    }

    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getItemCooldownManager().isCoolingDown(this)) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 300));
            user.getItemCooldownManager().set(this, 1200);
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    public int getEnchantability() {
        return 0;
    }

    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return false;
    }
}