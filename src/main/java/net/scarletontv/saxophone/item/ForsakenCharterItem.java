package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.scarletontv.saxophone.entity.ForsakenCharterEntity;
import net.scarletontv.saxophone.index.ModEntities;

public class ForsakenCharterItem extends Item implements ColorableItem {
    public ForsakenCharterItem(Settings settings) {
        super(settings);
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
    public ActionResult useOnBlock(ItemUsageContext context) {
        // var
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();

        if (player != null) {
            Hand hand = player.getActiveHand();
            ItemStack stack = player.getStackInHand(hand);
            if (world instanceof ServerWorld serverWorld) {
                ForsakenCharterEntity forsakenCharter = new ForsakenCharterEntity(ModEntities.FORSAKEN_CHARTER, world);
                forsakenCharter.setPos(pos.getX() + 0.5, pos.getY() + 2, pos.getZ() + 0.5);

                serverWorld.spawnEntity(forsakenCharter);
                stack.decrement(1);
            }
        }
        return super.useOnBlock(context);
    }
}
