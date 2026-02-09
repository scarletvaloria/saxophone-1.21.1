package net.scarletontv.saxophone.index;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.block.entity.CovetousMonolithBlockEntity;
import net.scarletontv.saxophone.block.entity.DenouementBlockEntity;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public interface ModBlockEntities {
    Map<BlockEntityType<?>, Identifier> BLOCK_ENTITIES = new LinkedHashMap<>();

    BlockEntityType<DenouementBlockEntity> DENOUEMENT = create("denouement", FabricBlockEntityTypeBuilder
            .create(DenouementBlockEntity::new)
            .addBlocks(ModBlocks.DENOUEMENT)
            .build());

    BlockEntityType<CovetousMonolithBlockEntity> MONOLITH = create("monolith", FabricBlockEntityTypeBuilder
            .create(CovetousMonolithBlockEntity::new)
            .addBlocks(ModBlocks.MONOLITH_BLOCK)
            .build());

    private static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType<T> blockEntity) {
        BLOCK_ENTITIES.put(blockEntity, Saxophone.id(name));
        return blockEntity;
    }

    static void registerBlockEntities() {
        BLOCK_ENTITIES.keySet().forEach(blockEntity -> {
            Registry.register(Registries.BLOCK_ENTITY_TYPE, BLOCK_ENTITIES.get(blockEntity), blockEntity);
        });
    }
}
