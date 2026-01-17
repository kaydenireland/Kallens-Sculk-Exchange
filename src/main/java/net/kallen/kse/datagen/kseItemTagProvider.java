package net.kallen.kse.datagen;

import net.kallen.kse.KallensSculkExpanse;
import net.kallen.kse.item.kseItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class kseItemTagProvider extends ItemTagsProvider {
    public kseItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, KallensSculkExpanse.MODID);
    }

    public static final TagKey<Item> GOAT_HORNS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("minecraft", "goat_horns"));

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ItemTags.SPEARS)
                .add(kseItems.AMETHYST_SPEAR.get())
                .add(kseItems.ECHO_SPEAR.get());


        tag(ItemTags.TRIM_MATERIALS)
                .add(Items.ECHO_SHARD)
                .add(Items.GLOWSTONE_DUST);

        tag(GOAT_HORNS)
                .add(kseItems.SCULK_HORN.get());

    }
}