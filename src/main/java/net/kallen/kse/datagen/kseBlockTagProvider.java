package net.kallen.kse.datagen;

import net.kallen.kse.KallensSculkExpanse;
import net.kallen.kse.block.kseBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class kseBlockTagProvider extends BlockTagsProvider {
    public kseBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, KallensSculkExpanse.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(kseBlocks.SCULKED_COBBLESTONE.get())
                .add(kseBlocks.SCULKED_DEEPSLATE.get())
                .add(kseBlocks.ECHO_SHARD_BLOCK.get())
                .add(kseBlocks.ECHO_ORE.get())
                .add(kseBlocks.DEEPSLATE_ECHO_ORE.get())
        ;

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(kseBlocks.ECHO_ORE.get())
                .add(kseBlocks.DEEPSLATE_ECHO_ORE.get())
        ;
    }
}