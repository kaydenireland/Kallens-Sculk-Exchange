package net.kallen.kse.datagen;


import net.kallen.kse.KallensSculkExpanse;
import net.kallen.kse.trim.kseTrimMaterials;
import net.kallen.kse.worldgen.kseBiomeModifiers;
import net.kallen.kse.worldgen.kseConfiguredFeatures;
import net.kallen.kse.worldgen.ksePlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class kseDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.TRIM_MATERIAL, kseTrimMaterials::bootstrap)

        .add(Registries.CONFIGURED_FEATURE, kseConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ksePlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, kseBiomeModifiers::bootstrap);


    public kseDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(KallensSculkExpanse.MODID));
    }
}