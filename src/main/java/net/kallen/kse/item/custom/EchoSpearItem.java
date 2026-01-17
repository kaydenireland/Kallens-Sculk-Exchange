package net.kallen.kse.item.custom;

import net.kallen.klib.item.SpearItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

public class EchoSpearItem extends SpearItem {
    public EchoSpearItem(Properties properties, ToolMaterial material, float attackDuration, float damageMultiplier, float delay, float dismountTime, float dismountThreshold, float knockbackTime, float knockbackThreshold, float damageTime, float damageThreshold) {
        super(properties, material, attackDuration, damageMultiplier, delay, dismountTime, dismountThreshold, knockbackTime, knockbackThreshold, damageTime, damageThreshold);
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(
                MobEffects.BLINDNESS,
                100, 0
        ));
    }
}
