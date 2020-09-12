package net.ludocrypt.musicdr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ludocrypt.musicdr.ModConfig;
import net.minecraft.sound.MusicSound;

@Environment(EnvType.CLIENT)
@Mixin(MusicSound.class)
public class MusicMixin {

	@Shadow
	private int minDelay;
	@Shadow
	private int maxDelay;

	@Inject(method = "getMinDelay", at = @At("HEAD"), cancellable = true)
	private void getMinDelay(CallbackInfoReturnable<Integer> ci) {
		boolean configLoaded = checkConfig();
		boolean divide = configLoaded ? ModConfig.getInstance().divide : false;
		int divisionAmount = configLoaded ? ModConfig.getInstance().division : 0;
		int new_minTime = configLoaded ? ModConfig.getInstance().minTime : minDelay;
		int new_maxTime = configLoaded ? ModConfig.getInstance().maxTime : maxDelay;
		boolean minOverMax = configLoaded ? new_minTime > new_maxTime : false;
		if (configLoaded && !minOverMax && divisionAmount > 0) {
			if (divide)
				ci.setReturnValue(minDelay / divisionAmount);
			else
				ci.setReturnValue(new_minTime * 20);
		}
	}

	@Inject(method = "getMaxDelay", at = @At("HEAD"), cancellable = true)
	private void getMaxDelay(CallbackInfoReturnable<Integer> ci) {
		boolean configLoaded = checkConfig();
		boolean divide = configLoaded ? ModConfig.getInstance().divide : false;
		int divisionAmount = configLoaded ? ModConfig.getInstance().division : 0;
		int new_minTime = configLoaded ? ModConfig.getInstance().minTime : minDelay;
		int new_maxTime = configLoaded ? ModConfig.getInstance().maxTime : maxDelay;
		boolean minOverMax = configLoaded ? new_minTime > new_maxTime : false;
		if (configLoaded && !minOverMax && divisionAmount > 0) {
			if (divide)
				ci.setReturnValue(maxDelay / divisionAmount);
			else
				ci.setReturnValue(new_maxTime * 20);
		}
	}

	private boolean checkConfig() {
		if (AutoConfig.getConfigHolder(ModConfig.class) != null) {
			return true;
		} else {
			return false;
		}
	}

}
