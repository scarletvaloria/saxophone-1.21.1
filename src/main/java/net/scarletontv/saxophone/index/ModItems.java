package net.scarletontv.saxophone.index;

import net.acoyt.acornlib.api.item.AcornItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.block.item.CovetousMonolithBlockItem;
import net.scarletontv.saxophone.block.item.DenouementBlockItem;
import net.scarletontv.saxophone.item.*;

import java.util.function.Function;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;

@SuppressWarnings("deprecation")
public class ModItems {
    public static final Item LIBERATION = registerItem("liberation",
            new LiberationItem(
                    ToolMaterials.NETHERITE,
                    new Item.Settings()
                            .attributeModifiers(LiberationItem.createAttributeModifiers())
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

    public static final Item SIGNED_CONTRACT = registerItem("contract_signed",
            new ContractItem(
                    new AcornItemSettings()
                            .maxCount(1)
            ));

    public static final Item AUTHORITYS_OBITUARY = registerItem("authoritys_obituary",
            new AuthoritysObituaryItem(
                    new AcornItemSettings()
                            .maxCount(1)
            ));

    public static final Item AVARITIAS_MASK = registerItem("avaritias_mask",
            new MaskItem(
                    new AcornItemSettings()
                            .maxCount(1)
                            .equipmentSlot((livingEntity, itemStack) -> EquipmentSlot.HEAD)
                            .attributeModifiers(MaskItem.createAttributeModifiers())
            ));

    public static final Item DEIFIC_WARRANT = registerItem("deific_warrant",
            new DeificWarrantItem(
                    new AcornItemSettings()
                            .maxCount(1)
            ));

    public static final Item FORSAKEN_CHARTER = registerItem("forsaken_charter",
            new ForsakenCharterItem(
                    new AcornItemSettings()
                            .maxCount(1)
            ));

    public static final Item MARTYRDOM = registerItem("martyrdom",
            new MartyrdomItem(
                    ToolMaterials.NETHERITE,
                    new AcornItemSettings()
                            .maxCount(1)
                            .attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.NETHERITE, 5, -3.0f))
            ));

    public static final Item WRATH_OF_TWILIGHT = registerItem("wrath_of_twilight",
            new WrathOfTwilightItem(
                    ToolMaterials.NETHERITE,
                    new AcornItemSettings()
                            .maxCount(1)
                            .attributeModifiers(WrathOfTwilightItem.createAttributeModifiers())
            ));

    public static final Item TWELVE_GAUGE = registerItem("bulwark",
            new BulwarkItem(
                    new AcornItemSettings()
                            .followsCam()
                            .maxCount(1)
                            .attributeModifiers(BulwarkItem.createAttributeModifiers())
            ));

    public static final Item SALVATION = registerItem("salvation",
            new SalvationItem(
                    ToolMaterials.NETHERITE,
                    new AcornItemSettings()
                            .maxCount(1)
                            .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 4, -2.4f))
            ));

    public static final Item REVITALIZE = registerItem("revitalize",
            new RevitalizeItem(
                    new AcornItemSettings()
                            .maxCount(1)
            ));


    // blockitems
    public static final Item MONOLITH_ITEM = registerItem("covetous_monolith",
            new CovetousMonolithBlockItem(
                    ModBlocks.MONOLITH_BLOCK,
                    new AcornItemSettings()
                            .maxCount(1)
            ));

    public static final Item DENOUEMENT_ITEM = registerItem("denouement",
            new DenouementBlockItem(
                    ModBlocks.DENOUEMENT,
                    new AcornItemSettings()
                            .maxCount(1)
            ));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Saxophone.MOD_ID, name), item);
    }

    public static void registerModItems() {
        modifyItemNameColor(LIBERATION, 0xd70048);
        modifyItemNameColor(AUTHORITYS_OBITUARY, 0xd70048);
        modifyItemNameColor(DEITYS_HANDBELL, 0xd70048);
        modifyItemNameColor(DEIFIC_WARRANT, 0xd70048);
        modifyItemNameColor(FORSAKEN_CHARTER, 0xd70048);
        modifyItemNameColor(MARTYRDOM, 0xd70048);
        modifyItemNameColor(WRATH_OF_TWILIGHT, 0xd70048);
        modifyItemNameColor(CONTRACT, 0x8a724c);
        modifyItemNameColor(SIGNED_CONTRACT, 0x611133);
        modifyItemNameColor(Item.fromBlock(ModBlocks.CLOUDED_THOUGHT), 0x38352f);
        modifyItemNameColor(Item.fromBlock(ModBlocks.HARMFUL_THOUGHT), 0x38352f);
        modifyItemNameColor(AVARITIAS_MASK, 0xab5c7e);
        modifyItemNameColor(TWELVE_GAUGE, 0xb0dd90);
        modifyItemNameColor(Item.fromBlock(ModBlocks.MONOLITH_BLOCK), 0xd70048);
        modifyItemNameColor(Item.fromBlock(ModBlocks.DENOUEMENT), 0xd70048);
        modifyItemNameColor(SALVATION, 0x2cfcf3);

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
