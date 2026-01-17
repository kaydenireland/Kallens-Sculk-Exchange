package net.kallen.kse.item.custom;

import net.kallen.klib.item.BellItem;
import net.kallen.kse.sound.kseSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class GlowBellItem extends BellItem {

    private static final String COOLDOWN_TAG = "GlowBellCooldown";
    private static final int COOLDOWN_TIME = 3000;


    public GlowBellItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {

            ServerPlayer serverPlayer = (ServerPlayer) pPlayer;

            pLevel.playSound(null, pPlayer.blockPosition(), kseSounds.GLOW_BELL.get(), SoundSource.PLAYERS,
                    1f, 1f);

            CompoundTag persistentData = pPlayer.getPersistentData();

            long lastUsedTime = persistentData.getLongOr(COOLDOWN_TAG, 0);
            long currentTime = pPlayer.level().getGameTime();

            if ((currentTime < lastUsedTime + COOLDOWN_TIME) && lastUsedTime > 0) {
                return InteractionResult.FAIL;
            }

            serverPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400, 0, true, true));

            persistentData.putLong(COOLDOWN_TAG, currentTime);
            applyCooldown(pPlayer, itemInHand, COOLDOWN_TIME);

            return InteractionResult.SUCCESS;

        }

        return InteractionResult.CONSUME;
    }


}
