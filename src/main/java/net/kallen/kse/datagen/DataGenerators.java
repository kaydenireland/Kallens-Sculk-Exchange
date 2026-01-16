package net.kallen.kse.datagen;

import net.kallen.kse.KallensSculkExpanse;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = KallensSculkExpanse.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new kseModelProvider(packOutput));
        generator.addProvider(true, new kseItemTagProvider(packOutput, lookupProvider));

        generator.addProvider(true, new kseDatapackProvider(packOutput, lookupProvider));
    }

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new kseModelProvider(packOutput));
        generator.addProvider(true, new kseItemTagProvider(packOutput, lookupProvider));

        generator.addProvider(true, new kseDatapackProvider(packOutput, lookupProvider));

    }
}