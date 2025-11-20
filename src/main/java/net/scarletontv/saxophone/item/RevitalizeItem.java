package net.scarletontv.saxophone.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.scarletontv.saxophone.Saxophone;

import java.util.Collection;

public class RevitalizeItem extends Item {
    public RevitalizeItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        MinecraftServer server = world.getServer();

        if (server != null) {
            Collection<ServerPlayerEntity> the_scooby_gang = server.getPlayerManager().getPlayerList();

            for (PlayerEntity player : the_scooby_gang) {
                if (Saxophone.contractedPlayers.contains(player.getUuid())) {
                    if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                        serverPlayerEntity.changeGameMode(GameMode.SURVIVAL);
                        serverPlayerEntity.getServer().getPlayerManager().broadcast(Text.literal((player.getDisplayName() != null ? player.getDisplayName().getString() : player.getNameForScoreboard()) + " had their soul renewed"), false);
                        assert context.getPlayer() != null;
                        context.getPlayer().getStackInHand(context.getHand()).decrement(1);
                    }
                }
            }
        }
        return super.useOnBlock(context);
    }
}
