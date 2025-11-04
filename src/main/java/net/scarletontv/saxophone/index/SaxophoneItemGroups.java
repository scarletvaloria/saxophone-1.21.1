package net.scarletontv.saxophone.index;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.scarletontv.saxophone.Saxophone;

public interface SaxophoneItemGroups {
    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Saxophone.id("saxophone"));
    ItemGroup A_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.LIBERATION))
            .displayName(Text.translatable("itemgroup.saxophone"))
            .build();

    static void initialize() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, A_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(SaxophoneItemGroups::addEntries);

    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        itemGroup.add(ModItems.DEITYS_HANDBELL);
        itemGroup.add(ModItems.LIBERATION);
        itemGroup.add(ModItems.CONTRACT);
        itemGroup.add(ModItems.AUTHORITYS_OBITUARY);
        itemGroup.add(ModBlocks.CLOUDED_THOUGHT);
        itemGroup.add(ModItems.AVARITIAS_MASK);
        itemGroup.add(ModItems.DEIFIC_WARRANT);
    }
}
