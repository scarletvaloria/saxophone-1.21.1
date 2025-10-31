package net.scarletontv.saxophone.index;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.scarletontv.saxophone.Saxophone;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ModBlocks {
    Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();

    // Item DAMNED_BOOK = create("damned_book", new DamnedBookItem(new Item.Settings()
    //            .maxCount(1)
    //            .fireproof()
    //            .rarity(Rarity.UNCOMMON)
    //    ));




    static <T extends Block> T create(String name, T item) {
        BLOCKS.put(item, Saxophone.id(name));
        return item;
    }
}
