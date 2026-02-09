package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.api.util.ParticleUtils;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.scarletontv.saxophone.index.ModDamageTypes;
import net.scarletontv.saxophone.index.ModStatusEffects;

import java.util.List;

public class EmptinessItem extends SwordItem implements ColorableItem, CustomHitParticleItem, CustomKillSourceItem, CustomHitSoundItem {
    public int startColor(ItemStack itemStack) {return 0xFFff6eff;}
    public int endColor(ItemStack itemStack) {return 0xFF7e087e;}
    public int backgroundColor(ItemStack itemStack) {return 0xF0170317;}

    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0x000000, 0xff6eff)};

    public EmptinessItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("lore.emptiness_1").formatted(Formatting.DARK_PURPLE));
        tooltip.add(Text.translatable("lore.emptiness_2").formatted(Formatting.DARK_PURPLE));
        tooltip.add(Text.translatable("lore.emptiness_3").formatted(Formatting.DARK_PURPLE).formatted(Formatting.OBFUSCATED).formatted(Formatting.ITALIC));
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

    public DamageSource getKillSource(LivingEntity livingEntity) {
        return ModDamageTypes.emptiness_kill(livingEntity);
    }

    public void playHitSound(PlayerEntity playerEntity) {
        playerEntity.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1, 0.25f);
        playerEntity.playSound(SoundEvents.ENTITY_WARDEN_HEARTBEAT, 1, 1f);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.hasStatusEffect(ModStatusEffects.UNRAVELING)) {
            //if (attacker.getHandSwingProgress(1) >= 0.5) {
                target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.UNRAVELING, 600));
            //}
        }
        return super.postHit(stack, target, attacker);
    }
}