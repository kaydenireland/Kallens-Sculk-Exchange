package net.kallen.kse.event;

import net.kallen.kse.KallensSculkExpanse;
import net.kallen.kse.item.kseItems;
import net.kallen.kse.potion.ksePotions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Map;
import java.util.function.Supplier;


@EventBusSubscriber(modid = KallensSculkExpanse.MODID)
public class kseEvents {

    private static class BellCooldownData {
        final String nbtTag;
        final int cooldownTime;
        final Supplier<Item> itemSupplier;

        BellCooldownData(String nbtTag, int cooldownTime, Supplier<Item> itemSupplier) {
            this.nbtTag = nbtTag;
            this.cooldownTime = cooldownTime;
            this.itemSupplier = itemSupplier;
        }
    }

    private static final Map<String, BellCooldownData> BELL_COOLDOWNS = Map.of(
            "amethyst", new BellCooldownData("AmethystBellCooldown", 6000, kseItems.AMETHYST_BELL),
            "echo", new BellCooldownData("EchoBellCooldown", 6000, kseItems.ECHO_BELL),
            "glow", new BellCooldownData("GlowBellCooldown", 3000, kseItems.GLOW_BELL)
    );

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        if (!player.level().isClientSide()) {
            CompoundTag persistentData = player.getPersistentData();
            long currentTime = player.level().getGameTime();

            for (BellCooldownData bellData : BELL_COOLDOWNS.values()) {
                long lastUsedTime = persistentData.getLongOr(bellData.nbtTag, 0);

                if (lastUsedTime > 0) {
                    long timeRemaining = (lastUsedTime + bellData.cooldownTime) - currentTime;

                    if (timeRemaining > 0) {
                        ItemStack bellStack = new ItemStack(bellData.itemSupplier.get());
                        player.getCooldowns().addCooldown(bellStack, (int) timeRemaining);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, Items.ECHO_SHARD, ksePotions.BLINDNESS_POTION);
    }



}