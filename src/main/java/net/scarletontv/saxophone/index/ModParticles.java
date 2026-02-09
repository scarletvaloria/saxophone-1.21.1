package net.scarletontv.saxophone.index;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.scarletontv.saxophone.Saxophone;

public interface ModParticles {
    SimpleParticleType FOLLY = FabricParticleTypes.simple(true);

    private static void create(String name, ParticleType<?> particle) {
        Registry.register(Registries.PARTICLE_TYPE, Saxophone.id(name), particle);
    }

    static void registerParticles() {
        create("folly", FOLLY);
    }

    static void registerParticlesClient() {
        ParticleFactoryRegistry.getInstance().register(FOLLY, EndRodParticle.Factory::new);
    }
}