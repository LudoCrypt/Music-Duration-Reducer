package net.ludocrypt.musicdr;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class MusicDr implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
	}
}
