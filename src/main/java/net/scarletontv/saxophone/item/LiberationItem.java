package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.api.item.KillEffectItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.scarletontv.saxophone.index.ModDamageTypes;
import net.scarletontv.saxophone.index.ModSounds;

import java.util.List;

public class LiberationItem extends SwordItem implements  ColorableItem, CustomKillSourceItem, CustomHitSoundItem, CustomHitParticleItem, KillEffectItem {
    public LiberationItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.saxophone.liberation.tooltip") .withColor(0x8e1a41));
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public void playHitSound(PlayerEntity playerEntity) {
        playerEntity.playSound(ModSounds.SCYTHE_SWING);
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return ModDamageTypes.scythe_kill(livingEntity);
    }

    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0xd70048, 0x0c0105)};

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

    @Override
    public void killEntity(World world, ItemStack itemStack, LivingEntity livingEntity, LivingEntity victim) {
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.RAID_OMEN, victim.getX(), victim.getY(), victim.getZ(), 75, 0.1, 4, 0.1, 0.02);
        }
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

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 9.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -3.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(Identifier.ofVanilla("base_entity_interaction_range"), 0.5f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }


}


