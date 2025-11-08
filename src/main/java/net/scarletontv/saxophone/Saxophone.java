package net.scarletontv.saxophone;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.impl.client.registry.sync.FabricRegistryClientInit;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.index.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.UUID;

public class Saxophone implements ModInitializer {
    public static final String MOD_ID = "saxophone";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id (String path){
        return Identifier.of(MOD_ID, path); }

    public static final ArrayList<UUID> avarice = new ArrayList<>();

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModSounds.registerSounds();
        ModEntities.registerEntities();
        ModStatusEffects.registerStatusEffects();
        ModBlocks.registerBlocks();
        ModParticles.registerParticles();

        // impl from Phototaxis
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            SaxophoneItemGroups.initialize();
        }

        avarice.add(UUID.fromString("c38f83cf-2723-497a-9327-f5937fb2fc08"));
    }
}