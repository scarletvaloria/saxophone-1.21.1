package net.scarletontv.saxophone.index;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.entity.ForsakenCharterEntity;
import net.scarletontv.saxophone.entity.ShotgunBulletEntity;

public interface ModEntities {
    EntityType<ForsakenCharterEntity> FORSAKEN_CHARTER = create(
            "forsaken_charter",
            EntityType.Builder.create(
                    ForsakenCharterEntity::new,
                    SpawnGroup.MISC
            ).dimensions(1, 1)
    );

    EntityType<ShotgunBulletEntity> BULLET = create(
            "shotgun_bullet",
            EntityType.Builder.create(
                    ShotgunBulletEntity::new,
                    SpawnGroup.MISC
            ).dimensions(0.2f, 0.2f)
    );

    static <T extends Entity> EntityType<T> create(String name, EntityType.Builder<T> builder) {
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Saxophone.id(name));
        return Registry.register(Registries.ENTITY_TYPE, key.getValue(), builder.build(String.valueOf(key)));
    }

    static void registerEntities() {
        // Entities are Registered Statically
    }

    static void registerEntitiesClient() {
        EntityRendererRegistry.register(FORSAKEN_CHARTER, EmptyEntityRenderer::new);
        EntityRendererRegistry.register(BULLET, EmptyEntityRenderer::new);
    }
}
