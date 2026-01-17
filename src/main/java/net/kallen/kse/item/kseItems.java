package net.kallen.kse.item;

import net.kallen.klib.item.BellItem;
import net.kallen.klib.item.HornItem;
import net.kallen.klib.item.MagicMirrorItem;
import net.kallen.klib.item.SpearItem;
import net.kallen.kse.KallensSculkExpanse;
import net.kallen.kse.item.custom.AmethystBellItem;
import net.kallen.kse.item.custom.EchoBellItem;
import net.kallen.kse.item.custom.EchoSpearItem;
import net.kallen.kse.item.custom.GlowBellItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class kseItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KallensSculkExpanse.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final DeferredItem<Item> AMETHYST_SPEAR = ITEMS.registerItem("amethyst_spear", properties -> new SpearItem(
            properties, ToolMaterial.COPPER, 1.25F, 3.0F, 0.3F, 2.0F, 6.0F, 4.5F, 5.1F, 7.5F, 4.6F
    ));

    public static final DeferredItem<Item> ECHO_SPEAR = ITEMS.registerItem("echo_spear", properties -> new EchoSpearItem(
            properties, ToolMaterial.COPPER, 1.25F, 3.0F, 0.3F, 2.0F, 6.0F, 4.5F, 5.1F, 7.5F, 4.6F
    ));

    public static final DeferredItem<Item> MURKY_MIRROR = ITEMS.registerItem("murky_mirror", properties -> new MagicMirrorItem(
            properties.stacksTo(1),
            1800,
            SoundEvents.ENDERMAN_TELEPORT
    ));

    public static final DeferredItem<Item> SCULK_HORN = ITEMS.registerItem("sculk_horn", properties -> new HornItem(properties, SoundEvents.WARDEN_SONIC_BOOM));

    // Bells
    public static final DeferredItem<Item> IRON_BELL = ITEMS.registerItem("iron_bell", BellItem::new);
    public static final DeferredItem<Item> AMETHYST_BELL = ITEMS.registerItem("amethyst_bell", AmethystBellItem::new);
    public static final DeferredItem<Item> ECHO_BELL = ITEMS.registerItem("echo_bell", EchoBellItem::new);
    public static final DeferredItem<Item> GLOW_BELL = ITEMS.registerItem("glow_bell", GlowBellItem::new);

}
