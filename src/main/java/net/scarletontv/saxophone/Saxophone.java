package net.scarletontv.saxophone;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.index.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Saxophone implements ModInitializer {
	public static final String MOD_ID = "saxophone";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id (String path){
        return Identifier.of(MOD_ID, path); }
	@Override
	public void onInitialize() {
        ModItems.registerModItems();
        ModSounds.registerSounds();
        //  ModEntities.initialize();
        ModStatusEffects.init();
        ModBlocks.init();

        // impl from Phototaxis
        SaxophoneItemGroups.initialize();
    }
}