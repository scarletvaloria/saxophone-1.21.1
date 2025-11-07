package net.scarletontv.saxophone.index;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;


public class ModSounds {
    public static final SoundEvent SCYTHE_SWING = registerSoundEvent("scythe_swing");

    public static final SoundEvent DARK_SANCTUARY = registerSoundEvent("dark_sanctuary");
    public static final RegistryKey<JukeboxSong> DARK_SANCTUARY_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(Saxophone.MOD_ID, "dark_sanctuary"));

    public static final SoundEvent AMBIENCE_ASPHODEL = registerSoundEvent("ambience_asphodel");


    public static final SoundEvent EXECUTION = registerSoundEvent("execution");
    public static final SoundEvent BELL_TOLL = registerSoundEvent("bell_toll");

    public static final SoundEvent SHOTGUN_SHOT = registerSoundEvent("shotgun_shot");
    public static final SoundEvent SHOTGUN_RELOAD = registerSoundEvent("shotgun_reload");


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Saxophone.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {}
}