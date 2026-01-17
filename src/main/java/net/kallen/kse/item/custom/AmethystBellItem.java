package net.kallen.kse.item.custom;

import net.kallen.klib.item.BellItem;
import net.kallen.kse.sound.kseSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AmethystBellItem extends BellItem {

    private static final String COOLDOWN_TAG = "AmethystBellCooldown";
    private static final int COOLDOWN_TIME = 6000;


    public AmethystBellItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {

            pLevel.playSound(null, pPlayer.blockPosition(), kseSounds.AMETHYST_BELL.get(), SoundSource.PLAYERS,
                    1f, 1f);

            CompoundTag persistentData = pPlayer.getPersistentData();

            long lastUsedTime = persistentData.getLongOr(COOLDOWN_TAG, 0);
            long currentTime = pPlayer.level().getGameTime();

            if ((currentTime < lastUsedTime + COOLDOWN_TIME) && lastUsedTime > 0) {
                return InteractionResult.FAIL;
            }

            pPlayer.removeAllEffects();
            persistentData.putLong(COOLDOWN_TAG, currentTime);
            applyCooldown(pPlayer, itemInHand, COOLDOWN_TIME);

            return InteractionResult.SUCCESS;

        }

        return InteractionResult.CONSUME;
    }
}
