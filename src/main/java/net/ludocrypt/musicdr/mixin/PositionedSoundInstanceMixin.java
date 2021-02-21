package net.ludocrypt.musicdr.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ludocrypt.musicdr.config.MusicDrConfig;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

@Environment(EnvType.CLIENT)
@Mixin(value = PositionedSoundInstance.class, priority = 100)
public class PositionedSoundInstanceMixin {

	@Inject(method = "music(Lnet/minecraft/sound/SoundEvent;)Lnet/minecraft/client/sound/PositionedSoundInstance;", at = @At("RETURN"), cancellable = true)
	private static void musicDr_changePitch(SoundEvent music, CallbackInfoReturnable<PositionedSoundInstance> ci) {
		MusicDrConfig config = MusicDrConfig.getInstance();
		Random random = new Random();
		if (config.experimental.distortPitch && random.nextDouble() < config.experimental.chanceToPitchChange) {
			int note = random.nextInt((config.experimental.maxNoteChange - config.experimental.minNoteChange) + 1) + config.experimental.minNoteChange;
			double newPitch = Math.pow(2.0D, (double) (note) / 12.0D);
			ci.setReturnValue(new PositionedSoundInstance(music.getId(), SoundCategory.MUSIC, 1.0F, (float) newPitch, false, 0, SoundInstance.AttenuationType.NONE, 0.0D, 0.0D, 0.0D, true));
		}
	}

}
