package net.scarletontv.saxophone.block.entity.render;

import com.nitron.nitrogen.render.RenderUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.block.entity.CovetousMonolithBlockEntity;

public class CovetousMonolithBlockEntityRenderer implements BlockEntityRenderer<CovetousMonolithBlockEntity> {
    @Override
    public void render(CovetousMonolithBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(-entity.getPos().getX(), -entity.getPos().getY(), -entity.getPos().getZ());
        RenderUtils.renderSkyBeam(
                matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntitySolid(
                        Identifier.of(
                                Saxophone.MOD_ID,
                                "textures/render/color.png"))),
                entity.getPos(),
                1,
                50,
                1
        );
        matrices.pop();

    }
}
