package net.scarletontv.saxophone.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.scarletontv.saxophone.entity.ForsakenCharterEntity;
import net.scarletontv.saxophone.index.ModEntities;

import java.util.List;

public class ForsakenCharterItem extends Item implements ColorableItem {
    public int startColor(ItemStack itemStack) {return 0xFFd70048;}
    public int endColor(ItemStack itemStack) {return 0xFF8e1a41;}
    public int backgroundColor(ItemStack itemStack) {return 0xF01c0810;}

    // just use one class for the tooltip colors, since they never change. Name it "MiscSaxophoneItem" and implement ColorableItem, then do this

    public ForsakenCharterItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        // var
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();

        if (player != null) {
            if (player.isSneaking()) {
                Hand hand = player.getActiveHand();
                ItemStack stack = player.getStackInHand(hand);
                if (world instanceof ServerWorld serverWorld) {
                    ForsakenCharterEntity forsakenCharter = new ForsakenCharterEntity(ModEntities.FORSAKEN_CHARTER, world);
                    forsakenCharter.setPos(pos.getX() + 0.5, pos.getY() + 2, pos.getZ() + 0.5);

                    serverWorld.spawnEntity(forsakenCharter);
                    stack.decrement(1);
                }
            }
        }
        return super.useOnBlock(context);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.saxophone.charter_1") .withColor(0x8e1a41));
        tooltip.add(Text.translatable("tooltip.saxophone.charter_2") .withColor(0x8e1a41));
        super.appendTooltip(stack, context, tooltip, type);
    }
}