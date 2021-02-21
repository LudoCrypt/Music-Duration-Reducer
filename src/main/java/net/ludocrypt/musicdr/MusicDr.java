package net.ludocrypt.musicdr;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.ludocrypt.musicdr.config.MusicDrConfig;

public class MusicDr implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		AutoConfig.register(MusicDrConfig.class, GsonConfigSerializer::new);
	}

}
