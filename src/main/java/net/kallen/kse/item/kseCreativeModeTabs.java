package net.kallen.kse.item;

import net.kallen.kse.KallensSculkExpanse;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class kseCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KallensSculkExpanse.MODID);

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register("bismuth_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.ECHO_SHARD))
                    .title(Component.translatable("creativetab.kse.items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(kseItems.AMETHYST_SPEAR);
                        output.accept(kseItems.IRON_BELL);
                        output.accept(kseItems.SCULK_HORN);
                        output.accept(kseItems.MURKY_MIRROR);



                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}