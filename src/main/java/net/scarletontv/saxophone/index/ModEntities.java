package net.scarletontv.saxophone.index;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.scarletontv.saxophone.Saxophone;

public interface ModEntities {

//    EntityType<PylonEntity> PYLON = create(
//            "pylon",
//            EntityType.Builder.create(
//                    PylonEntity::new,
//                    SpawnGroup.MISC
//            ).dimensions(0.3f, 0.8f)
//    );
//
//    EntityType<MonolithEntity> MONOLITH = create(
//            "monolith",
//            EntityType.Builder.create(
//                    MonolithEntity::new,
//                    SpawnGroup.MISC
//            ).dimensions(0.6f, 0.6f)
//    );

    static <T extends Entity> EntityType<T> create(String name, EntityType.Builder<T> builder) {
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Saxophone.id(name));
        return Registry.register(Registries.ENTITY_TYPE, key.getValue(), builder.build(String.valueOf(key)));
    }

    static void initialize() {
        // Entities are Registered Statically
    }

    static void clientInit() {
      //  EntityRendererRegistry.register(MONOLITH, EmptyEntityRenderer::new);
    }
}
