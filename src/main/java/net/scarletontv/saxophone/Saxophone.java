package net.scarletontv.saxophone;

import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.acornlib.api.ALib;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.compat.SaxophoneConfig;
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
    public static final ArrayList<UUID> contractedPlayers = new ArrayList<>();

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModSounds.registerSounds();
        ModEntities.registerEntities();
        ModStatusEffects.registerStatusEffects();
        ModBlocks.registerBlocks();
        ModParticles.registerParticles();
        ModBlockEntities.registerBlockEntities();

        // ALib
        ALib.registerModMenu(MOD_ID, 0xd70048);

        // impl from Phototaxis
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            SaxophoneItemGroups.initialize();
        }

        MidnightConfig.init(MOD_ID, SaxophoneConfig.class);

        avarice.add(UUID.fromString("c38f83cf-2723-497a-9327-f5937fb2fc08"));

        // contracted players

        /// niki
        contractedPlayers.add(UUID.fromString("2fd27cf-5931-462b-8d7d-7f11adb7998b"));

        ///  aco
        contractedPlayers.add(UUID.fromString("017f5cdc-086b-4d98-a0c2-7dc43d5117bd"));

        /// Myth
        contractedPlayers.add(UUID.fromString("fcbf8158-2bfb-4181-b66c-47c651214da0"));

        /// Invis
        contractedPlayers.add(UUID.fromString("fda04ec4-ba0d-4902-9e1d-d17d773842ea"));

        /// Krimson
        contractedPlayers.add(UUID.fromString("9da4f059-fb15-4b34-a2e5-54cfecf7c22e"));

        /// Heartless
        contractedPlayers.add(UUID.fromString("d0f1f0f4-631e-4290-9f60-78ace9e5e0ef"));

        /// Vixo
        contractedPlayers.add(UUID.fromString("2a437484-799d-439b-9574-63df2fe097fe"));

        /// Aethercide
        contractedPlayers.add(UUID.fromString("dd44a1f0-990b-4903-8549-680e7c1b49bc"));

        /// Joey
        contractedPlayers.add(UUID.fromString("97cd3c8b-ed30-49dd-9ae3-04d03b7ebe6f"));

        /// Chem
        contractedPlayers.add(UUID.fromString("a26e29f1-532e-4116-9112-ca18ea30d27f"));

        /// Xzone
        contractedPlayers.add(UUID.fromString("1df5e0ee-58ba-4240-9319-dee22c9d0196"));
    }
}