package net.ludocrypt.musicdr.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import me.shedaniel.autoconfig.AutoConfig;
import net.ludocrypt.musicdr.config.MusicDrConfig;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.util.math.MathHelper;

@Mixin(PositionedSoundInstance.class)
public class PositionedSoundInstanceMixin {

	@ModifyArg(method = "music", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/PositionedSoundInstance;<init>(Lnet/minecraft/util/Identifier;Lnet/minecraft/sound/SoundCategory;FFZILnet/minecraft/client/sound/SoundInstance$AttenuationType;DDDZ)V"), index = 3)
	private static float musicdr$music(float in) {
		if (AutoConfig.getConfigHolder(MusicDrConfig.class) != null) {
			MusicDrConfig config = MusicDrConfig.getInstance();

			if (config.experimental.distortPitch) {
				Random random = new Random();
				if (random.nextDouble() < config.experimental.chanceToPitchChange) {
					float note = MathHelper.nextBetween(random, Math.min(config.experimental.minNoteChange, config.experimental.maxNoteChange), Math.max(config.experimental.minNoteChange, config.experimental.maxNoteChange));
					float pitch = (float) Math.pow(2.0F, note / 12.0F);
					return MathHelper.clamp(pitch, 0.5F, 2.0F);
				}
			}
		}
		return in;
	}

}
