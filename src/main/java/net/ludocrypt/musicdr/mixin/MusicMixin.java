package net.ludocrypt.musicdr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ludocrypt.musicdr.config.MusicDrConfig;
import net.minecraft.sound.MusicSound;

@Environment(EnvType.CLIENT)
@Mixin(MusicSound.class)
public class MusicMixin {

	@Inject(method = "getMinDelay", at = @At("RETURN"), cancellable = true)
	private void getMinDelay(CallbackInfoReturnable<Integer> ci) {

		if (AutoConfig.getConfigHolder(MusicDrConfig.class) != null) {
			MusicDrConfig config = MusicDrConfig.getInstance();
			if (config.divide) {
				ci.setReturnValue(ci.getReturnValue() / config.division);
			} else {
				ci.setReturnValue(Math.min(ci.getReturnValue(), Math.min(config.minTime * 20, config.maxTime * 20)));
			}
		}

	}

	@Inject(method = "getMaxDelay", at = @At("RETURN"), cancellable = true)
	private void getMaxDelay(CallbackInfoReturnable<Integer> ci) {

		if (AutoConfig.getConfigHolder(MusicDrConfig.class) != null) {
			MusicDrConfig config = MusicDrConfig.getInstance();
			if (config.divide) {
				ci.setReturnValue(ci.getReturnValue() / config.division);
			} else {
				ci.setReturnValue(Math.min(ci.getReturnValue(), Math.max(config.minTime * 20, config.maxTime * 20)));
			}
		}

	}

}
