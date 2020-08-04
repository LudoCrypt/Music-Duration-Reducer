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
	private int field_24058;
	@Shadow
	private int field_24059;

	@Inject(method = "method_27280", at = @At("HEAD"), cancellable = true)
	private void bottomInt(CallbackInfoReturnable<Integer> ci) {
		if (AutoConfig.getConfigHolder(ModConfig.class) != null) {
			if (ModConfig.getInstance().divide && ModConfig.getInstance().division > 0) {
				ci.setReturnValue(field_24058 / ModConfig.getInstance().division);
			} else {
				ci.setReturnValue(ModConfig.getInstance().minTime * 20);
			}
		}
	}

	@Inject(method = "method_27281", at = @At("HEAD"), cancellable = true)
	private void topInt(CallbackInfoReturnable<Integer> ci) {
		if (AutoConfig.getConfigHolder(ModConfig.class) != null) {
			if (ModConfig.getInstance().divide && ModConfig.getInstance().division > 0) {
				ci.setReturnValue(field_24059 / ModConfig.getInstance().division);
			} else {
				ci.setReturnValue(ModConfig.getInstance().maxTime * 20);
			}
		}
	}

}
