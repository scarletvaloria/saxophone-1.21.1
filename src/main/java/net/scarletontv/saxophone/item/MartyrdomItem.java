package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.api.item.KillEffectItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModDamageTypes;
import net.scarletontv.saxophone.index.ModParticles;

import java.util.List;

public class MartyrdomItem extends AxeItem implements ColorableItem, KillEffectItem, CustomKillSourceItem {
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

    @Override
    public void killEntity(World world, ItemStack itemStack, LivingEntity user, LivingEntity victim) {
        if (victim instanceof ServerPlayerEntity serverPlayerEntity) {
            teleportToPurgatory(serverPlayerEntity);
            serverPlayerEntity.setHealth(serverPlayerEntity.getMaxHealth());
            serverPlayerEntity.requestRespawn();
        }
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ModParticles.FOLLY,
                    victim.getX(),
                    victim.getY(),
                    victim.getZ(),
                    150,
                    0,
                    0,
                    0,
                    0.5
            );
        }
    }

    private void teleportToPurgatory(ServerPlayerEntity player) {
        RegistryKey<World> heavenWorldKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Saxophone.MOD_ID, "asphodel"));

        MinecraftServer server = player.getServer();
        if (server == null) {
            Saxophone.LOGGER.error("Server is null!");
            return;
        }

        ServerWorld targetWorld = server.getWorld(heavenWorldKey);
        if (targetWorld != null) {
            BlockPos spawnPos = targetWorld.getSpawnPos();

            player.teleport(
                    targetWorld,
                    0,
                    spawnPos.getY() + 100,
                    0,
                    player.getYaw(),
                    player.getPitch()
            );
        } else {
            Saxophone.LOGGER.error("Could not find asphodel dimension!");
        }
    }

    @Override
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return ModDamageTypes.martyrdom_kill(livingEntity);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.saxophone.martyr_1").withColor(0x8e1a41));
        tooltip.add(Text.translatable("tooltip.saxophone.martyr_2").withColor(0x8e1a41));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
