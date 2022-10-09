package net.ludocrypt.musicdr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.ludocrypt.musicdr.config.MusicDrConfig;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.random.RandomGenerator;

@Mixin(PositionedSoundInstance.class)
public class PositionedSoundInstanceMixin {

	@ModifyArg(method = "music", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/PositionedSoundInstance;<init>(Lnet/minecraft/util/Identifier;Lnet/minecraft/sound/SoundCategory;FFLnet/minecraft/util/random/RandomGenerator;ZILnet/minecraft/client/sound/SoundInstance$AttenuationType;DDDZ)V"), index = 3)
	private static float musicdr$music(float in) {

		if (MusicDrConfig.distortPitch) {
			RandomGenerator random = RandomGenerator.createLegacy();
			if (random.nextDouble() < MusicDrConfig.chanceToPitchChange) {
				float note;

				if (MusicDrConfig.bellDistribution) {
					note = (float) MathHelper.clamp(normal(1.0D / MusicDrConfig.bellStandardDeviationReciprocal) * 0.25D, MusicDrConfig.minNoteChange, MusicDrConfig.maxNoteChange);
				} else {
					note = MathHelper.nextBetween(random, (float) MathHelper.clamp(MusicDrConfig.minNoteChange, MusicDrConfig.minNoteChange, MusicDrConfig.maxNoteChange), (float) MathHelper.clamp(MusicDrConfig.maxNoteChange, MusicDrConfig.minNoteChange, MusicDrConfig.maxNoteChange));
				}

				float pitch = (float) Math.pow(2.0F, note / 12.0F);

				return MathHelper.clamp(pitch, 0.5F, 2.0F);
			}
		}

		return in;
	}

	private static double normal(double sig) {
		RandomGenerator random = RandomGenerator.createLegacy();
		double u, v, x, y, q;
		do {
			u = random.nextDouble();
			v = 1.7156 * (random.nextDouble() - 0.5);
			x = u - 0.449871;
			y = Math.abs(v) + 0.386595;
			q = Math.sqrt(x) + y * (0.19600 * y - 0.25472 * x);
		} while (q > 0.27597 && (q > 0.27846 || Math.sqrt(v) > -4.0 * Math.log(u) * Math.sqrt(u)));
		return sig * v / u;
	}

}
