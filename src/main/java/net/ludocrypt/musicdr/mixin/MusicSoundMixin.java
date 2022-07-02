package net.ludocrypt.musicdr.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.shedaniel.autoconfig.AutoConfig;
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
		if (AutoConfig.getConfigHolder(MusicDrConfig.class) != null) {
			MusicDrConfig config = MusicDrConfig.getInstance();

			if (config.divide && config.division > 0) {
				ci.setReturnValue(Math.round(ci.getReturnValue() / Math.abs(config.division)));
			} else {
				ci.setReturnValue(Math.round(MathHelper.clamp(config.minTime, config.minTime, config.maxTime) * 20.0F));
			}
		}
	}

	@Inject(method = "getMaxDelay", at = @At("RETURN"), cancellable = true)
	public void musicdr$getMaxDelay(CallbackInfoReturnable<Integer> ci) {
		if (AutoConfig.getConfigHolder(MusicDrConfig.class) != null) {
			MusicDrConfig config = MusicDrConfig.getInstance();

			if (config.divide && config.division > 0) {
				ci.setReturnValue(Math.round(ci.getReturnValue() / Math.abs(config.division)));
			} else {
				ci.setReturnValue(Math.round(MathHelper.clamp(config.maxTime, config.minTime, config.maxTime) * 20.0F));
			}
		}
	}
}
