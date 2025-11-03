package net.scarletontv.saxophone.index;

import net.acoyt.acornlib.api.item.AcornItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.item.AuthoritysObituaryItem;
import net.scarletontv.saxophone.item.ContractItem;
import net.scarletontv.saxophone.item.DeitysHandbellItem;
import net.scarletontv.saxophone.item.LiberationItem;

import java.util.function.Function;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;


public class ModItems {
    public static final Item LIBERATION = registerItem("liberation",
            new LiberationItem(
                    ToolMaterials.NETHERITE,
                    new Item.Settings()
                            .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 5, -3.0f))
            ));

    public static final Item DARK_SANCTUARY_MUSIC_DISC = registerItem("dark_sanctuary_music_disc",
            new Item(
                    new Item.Settings()
                            .jukeboxPlayable(ModSounds.DARK_SANCTUARY_KEY)
                            .maxCount(1)
                            .rarity(Rarity.RARE)
            ));

    public static final Item DEITYS_HANDBELL = registerItem("deitys_handbell",
            new DeitysHandbellItem(
                    new AcornItemSettings()
                            .maxCount(1)
                            .rarity(Rarity.UNCOMMON)
            ));

    public static final Item CONTRACT = registerItem("contract",
            new ContractItem(
                    new AcornItemSettings()
                            .maxCount(1)
            ));

    public static final Item SIGNED_CONTRACT = registerItem("signed_contract",
            new ContractItem(
                    new AcornItemSettings()
                            .maxCount(1)
            ));

    public static final Item AUTHORITYS_OBITUARY = registerItem("authoritys_obituary",
            new AuthoritysObituaryItem(
                    new AcornItemSettings()
                            .maxCount(1)
            ));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Saxophone.MOD_ID, name), item);
    }

    public static void registerModItems() {
        modifyItemNameColor(LIBERATION, 0xd70048);
        modifyItemNameColor(AUTHORITYS_OBITUARY, 0xd70048);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(fabricItemGroupEntries -> {
        fabricItemGroupEntries.addAfter(Items.MUSIC_DISC_PIGSTEP, DARK_SANCTUARY_MUSIC_DISC);
        });
    }

    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings);
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, Saxophone.id(name), item);
    }
}
