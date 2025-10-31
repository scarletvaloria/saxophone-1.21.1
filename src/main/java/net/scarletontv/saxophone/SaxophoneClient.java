package net.scarletontv.saxophone;

import net.fabricmc.api.ClientModInitializer;
import net.scarletontv.saxophone.index.ModEntities;

public class SaxophoneClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntities.clientInit();
    }
}
