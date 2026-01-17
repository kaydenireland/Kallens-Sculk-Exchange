package net.kallen.kse.datagen;


import net.kallen.kse.KallensSculkExpanse;
import net.kallen.kse.block.kseBlocks;
import net.kallen.kse.item.kseItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class kseModelProvider extends ModelProvider {
    public kseModelProvider(PackOutput output) {
        super(output, KallensSculkExpanse.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(kseItems.MURKY_MIRROR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(kseItems.SCULK_HORN.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateSpear(kseItems.AMETHYST_SPEAR.get());
        itemModels.generateSpear(kseItems.ECHO_SPEAR.get());

        // Bells
        itemModels.generateFlatItem(kseItems.IRON_BELL.get(), ModelTemplates.FLAT_ITEM);

        /* BLOCKS */
        blockModels.createTrivialCube(kseBlocks.SCULKED_DEEPSLATE.get());
        blockModels.createTrivialCube(kseBlocks.ECHO_SHARD_BLOCK.get());
        blockModels.createTrivialCube(kseBlocks.ECHO_ORE.get());
        blockModels.createTrivialCube(kseBlocks.DEEPSLATE_ECHO_ORE.get());

    }


}