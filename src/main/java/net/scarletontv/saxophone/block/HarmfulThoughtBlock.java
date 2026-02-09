package net.scarletontv.saxophone.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.index.ModItems;

public class HarmfulThoughtBlock extends Block {
    public HarmfulThoughtBlock(Settings settings) {
        super(settings);
    }

    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof PlayerEntity player) {
            if (!player.getInventory().contains(ModItems.DEIFIC_WARRANT.getDefaultStack()) || !Saxophone.avarice.contains(player.getUuid())) {
                player.damage(player.getDamageSources().outOfWorld(), 3);
            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }
}