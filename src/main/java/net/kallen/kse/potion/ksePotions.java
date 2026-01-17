package net.kallen.kse.potion;

import net.kallen.kse.KallensSculkExpanse;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ksePotions {

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, KallensSculkExpanse.MODID);

    public static final Holder<Potion> BLINDNESS_POTION = POTIONS.register("blindness_potion",
            () -> new Potion("blindness_potion", new MobEffectInstance(MobEffects.BLINDNESS, 600, 0))
    );

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

}
