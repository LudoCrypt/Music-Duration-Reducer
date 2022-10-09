package net.ludocrypt.musicdr.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.ludocrypt.musicdr.config.MusicDrConfig;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.math.MathHelper;

@Mixin(MusicSound.class)
public class MusicSoundMixin {

	@Shadow
	@Final
	private int minDelay;

	@Shadow
	@Final
	private int maxDelay;

	@Inject(method = "getMinDelay", at = @At("RETURN"), cancellable = true)
	public void musicdr$getMinDelay(CallbackInfoReturnable<Integer> ci) {

		if (MusicDrConfig.divide && MusicDrConfig.division > 0) {
			ci.setReturnValue(Math.round(ci.getReturnValue() / Math.abs(MusicDrConfig.division)));
		} else {
			ci.setReturnValue(Math.round(MathHelper.clamp(MusicDrConfig.minTime, MusicDrConfig.minTime, MusicDrConfig.maxTime) * 20.0F));
		}

	}

	@Inject(method = "getMaxDelay", at = @At("RETURN"), cancellable = true)
	public void musicdr$getMaxDelay(CallbackInfoReturnable<Integer> ci) {
		if (MusicDrConfig.divide && MusicDrConfig.division > 0) {
			ci.setReturnValue(Math.round(ci.getReturnValue() / Math.abs(MusicDrConfig.division)));
		} else {
			ci.setReturnValue(Math.round(MathHelper.clamp(MusicDrConfig.maxTime, MusicDrConfig.minTime, MusicDrConfig.maxTime) * 20.0F));
		}
	}
}
