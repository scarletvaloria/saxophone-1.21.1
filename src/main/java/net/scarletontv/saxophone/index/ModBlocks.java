package net.scarletontv.saxophone.index;

import net.acoyt.acornlib.impl.item.TranslationBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.scarletontv.saxophone.Saxophone;
import net.scarletontv.saxophone.block.CovetousMonolithBlock;

import java.util.function.Function;

public interface ModBlocks {
    Block MONOLITH_BLOCK = createWithItem("covetous_monolith", CovetousMonolithBlock::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
            .sounds(BlockSoundGroup.ANCIENT_DEBRIS)
            .dropsNothing()
    );

    Block CLOUDED_THOUGHT = createWithItem("clouded_thought", Block::new, AbstractBlock.Settings.copy(Blocks.BEDROCK)
            .sounds(BlockSoundGroup.GLASS)
            .dropsNothing()
    );

    static Block create(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings);
        return Registry.register(Registries.BLOCK, Saxophone.id(name), block);
    }

    static Block createWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = create(name, factory, settings);
        ModItems.create(name, itemSettings -> new TranslationBlockItem(block, itemSettings), new Item.Settings().maxCount(1));
        return block;
    }

    static void init() {
    }
}
