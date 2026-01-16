package net.kallen.kse.trim;

import net.kallen.kse.KallensSculkExpanse;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class kseTrimMaterials {

    public static final ResourceKey<TrimMaterial> ECHO_SHARD =
            ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(KallensSculkExpanse.MODID, "echo_shard"));

    public static final ResourceKey<TrimMaterial> GLOWSTONE =
            ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(KallensSculkExpanse.MODID, "glowstone"));

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, ECHO_SHARD, Items.ECHO_SHARD, Style.EMPTY.withColor(TextColor.parseColor("#034050").getOrThrow()), "echo_shard");
        register(context, GLOWSTONE, Items.GLOWSTONE_DUST, Style.EMPTY.withColor(TextColor.parseColor("#034050").getOrThrow()), "glowstone");
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item, Style style, String assetName) {
        TrimMaterial trimmaterial = new TrimMaterial(MaterialAssetGroup.create(assetName),
                Component.translatable(Util.makeDescriptionId("trim_material", trimKey.identifier())).withStyle(style));
        context.register(trimKey, trimmaterial);
    }

}
