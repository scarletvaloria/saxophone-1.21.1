package net.scarletontv.saxophone.item;

import net.acoyt.acornlib.api.item.AcornItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.sound.ModSounds;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;


public class ModItems {
    public static final Item LIBERATION = registerItem("liberation",
            new LiberationItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 6, -3.0f))));
    public static final Item DARK_SANCTUARY_MUSIC_DISC = registerItem("dark_sanctuary_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.DARK_SANCTUARY_KEY).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Saxophone.MOD_ID, name), item);
    }

    public static void registerModItems() {
        modifyItemNameColor(LIBERATION, 0xd70048);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(DARK_SANCTUARY_MUSIC_DISC);
        });

        Saxophone.LOGGER.info("Registering Mod Items for " + Saxophone.MOD_ID);
    }
}
