package net.ludocrypt.musicdr;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import eu.midnightdust.lib.config.MidnightConfig;
import net.ludocrypt.musicdr.config.MusicDrConfig;

public class MusicDr implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		MidnightConfig.init("musicdr", MusicDrConfig.class);
	}

}
