package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.scarletontv.saxophone.entity.ShotgunBulletEntity;
import net.scarletontv.saxophone.index.ModEntities;
import net.scarletontv.saxophone.index.ModSounds;
import net.scarletontv.saxophone.index.ModStatusEffects;

import java.util.List;

public class BulwarkItem extends Item implements ColorableItem, CustomHitSoundItem {
    public BulwarkItem(Settings settings) {
        super(settings);
    }

    public static int ammo = 6;

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 4.5f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.7f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(Identifier.ofVanilla("base_entity_interaction_range"), 1.5f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (ammo != 0) {
            if (world instanceof ServerWorld serverWorld) {
                if (user.getMainHandStack().isOf(this)) {
                        for (int i = 0; i < 3; i++) {
                            ShotgunBulletEntity bullet = new ShotgunBulletEntity(ModEntities.BULLET, serverWorld);

                            Vec3d pos = user.getPos();
                            bullet.updatePosition(pos.x, pos.y + 1.5f, pos.z);
                            bullet.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 7.5F * 0.5F, 3.0F);

                            serverWorld.spawnEntity(bullet);
                            bullet.setOwner(user);
                        }
                        ammo--;
                }
                if (user.getOffHandStack().isOf(this)) {
                    if (ammo == 6 || ammo == 3 || ammo == 4 || ammo == 5) {
                        for (int i = 0; i < 6; i++) {
                            ShotgunBulletEntity bullet = new ShotgunBulletEntity(ModEntities.BULLET, serverWorld);

                            Vec3d pos = user.getPos();
                            bullet.updatePosition(pos.x, pos.y + 1.5f, pos.z);
                            bullet.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 7.5F * 0.5F, 6.0F);

                            serverWorld.spawnEntity(bullet);
                            bullet.setOwner(user);

                        }
                        for (int i = 0; i < 3; i++) {
                            ammo--;
                        }
                    }
                }
            }
            user.playSound(ModSounds.SHOTGUN_SHOT, 1, 1);
            user.getItemCooldownManager().set(this, 15);
        }
        if (ammo == 0) {
                user.playSoundToPlayer(ModSounds.SHOTGUN_RELOAD, SoundCategory.PLAYERS, 1, 1);
                ammo = 6;
                user.getItemCooldownManager().set(this, 75);
        }
        return super.use(world, user, hand);
    }

    @Override
    public int startColor(ItemStack itemStack) {
        return 0xFF49b968;
    }

    @Override
    public int endColor(ItemStack itemStack) {
        return 0xFF2a3637;
    }

    @Override
    public int backgroundColor(ItemStack itemStack) {
        return 0xF0060b0c;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.saxophone.bulwark.desc_1") .withColor(0x254e36).formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("item.saxophone.bulwark.desc_2") .withColor(0x254e36).formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("item.saxophone.bulwark.desc_3") .withColor(0x254e36).formatted(Formatting.ITALIC));
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public void playHitSound(PlayerEntity playerEntity) {
        playerEntity.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 1, -1);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.isSneaking()) {
            target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.SAXITOXIN, 200));
            attacker.damage(attacker.getDamageSources().generic(), 2);
        }
        return super.postHit(stack, target, attacker);
    }
}
