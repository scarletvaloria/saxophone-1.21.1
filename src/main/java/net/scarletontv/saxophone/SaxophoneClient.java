package net.scarletontv.saxophone;

import com.nitron.nitrogen.Nitrogen;
import com.nitron.nitrogen.render.RenderUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.scarletontv.saxophone.block.entity.render.CovetousMonolithBlockEntityRenderer;
import net.scarletontv.saxophone.block.entity.render.DenouementBlockEntityRenderer;
import net.scarletontv.saxophone.entity.ShotgunBulletEntity;
import net.scarletontv.saxophone.index.ModBlockEntities;
import net.scarletontv.saxophone.index.ModBlocks;
import net.scarletontv.saxophone.index.ModEntities;
import net.scarletontv.saxophone.index.ModParticles;
import org.joml.Vector3f;

import java.util.*;

public class SaxophoneClient implements ClientModInitializer {
    private static final Map<UUID, Deque<Vec3d>> TRAILS = new HashMap<>();
    @Override
    public void onInitializeClient() {
        ModParticles.registerParticlesClient();
        ModEntities.registerEntitiesClient();
    //    ModBlocks.registerBlocksClient();
        BlockEntityRendererFactories.register(ModBlockEntities.DENOUEMENT, context -> new DenouementBlockEntityRenderer());
        BlockEntityRendererFactories.register(ModBlockEntities.MONOLITH, context -> new CovetousMonolithBlockEntityRenderer());

        WorldRenderEvents.AFTER_TRANSLUCENT.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            Vec3d pos = client.player.getPos();
            Box box = new Box(pos.x - 1, pos.y - 1, pos.z - 1, pos.x + 1, pos.y + 1, pos.z + 1);
            box = box.expand(40);
            assert client.world != null;
            for (ShotgunBulletEntity entity : client.world.getEntitiesByClass(ShotgunBulletEntity.class, box, allayEntity -> true)) {
                Deque<Vec3d> trail = TRAILS.computeIfAbsent(entity.getUuid(), id -> new ArrayDeque<>());
                Vector3f color = new Vector3f((float) 150 / 255, (float) 200 / 255, (float) 22 / 255);

                if (entity.isRegionUnloaded()) {
                    TRAILS.remove(entity.getUuid());
                }

                RenderUtils.renderEntityTrail(
                        context.matrixStack(),
                        context.consumers().getBuffer(RenderLayer.getEntitySolid(Identifier.of(Nitrogen.MOD_ID, "textures/render/color.png"))),
                        context.camera(),
                        entity,
                        context.tickCounter().getTickDelta(false),
                        trail,
                        200,
                        0.1f,
                        0.001f,
                        255,
                        0,
                        (float) color.x,
                        (float) color.y,
                        (float) color.z,
                        new Vec3d(0.0, 0.25, 0.0)
                );
            }
        });
    }
}
