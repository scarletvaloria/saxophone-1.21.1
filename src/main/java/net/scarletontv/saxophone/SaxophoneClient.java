package net.scarletontv.saxophone;

import net.fabricmc.api.ClientModInitializer;
import net.scarletontv.saxophone.index.ModParticles;

public class SaxophoneClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModParticles.registerParticlesClient();
    }
}
