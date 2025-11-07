package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.scarletontv.saxophone.entity.ShotgunBulletEntity;
import net.scarletontv.saxophone.index.ModEntities;
import net.scarletontv.saxophone.index.ModSounds;

public class KrimsonsSillyShotgunItem extends SwordItem implements ColorableItem {
    public KrimsonsSillyShotgunItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld serverWorld) {
            if (user.getMainHandStack().isOf(this)) {
                for (int i = 0; i < 3; i++) {
                    ShotgunBulletEntity bullet = new ShotgunBulletEntity(ModEntities.BULLET, serverWorld);

                    Vec3d pos = user.getPos();
                    bullet.updatePosition(pos.x, pos.y + 1.5f, pos.z);
                    bullet.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 7.5F * 0.5F, 9.0F);

                    serverWorld.spawnEntity(bullet);
                    bullet.setOwner(user);
                }
            }
            if (user.getOffHandStack().isOf(this)) {
                ShotgunBulletEntity bullet = new ShotgunBulletEntity(ModEntities.BULLET, serverWorld);

                Vec3d pos = user.getPos();
                bullet.updatePosition(pos.x, pos.y + 1.5f, pos.z);
                bullet.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 7.5F * 0.5F, 0.0F);

                serverWorld.spawnEntity(bullet);
                bullet.setOwner(user);
            }
        }
        user.playSound(ModSounds.SHOTGUN_SHOT, 1, 1);
        return super.use(world, user, hand);
    }

    @Override
    public int startColor(ItemStack itemStack) {
        return 0xFF6BF0FA;
    }

    @Override
    public int endColor(ItemStack itemStack) {
        return 0xFFF47DB5;
    }

    @Override
    public int backgroundColor(ItemStack itemStack) {
        return 0xF01c0810;
    }
}
