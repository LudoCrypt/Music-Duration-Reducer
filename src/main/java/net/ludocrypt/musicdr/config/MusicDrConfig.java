package net.ludocrypt.musicdr.config;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "musicdr")
public class MusicDrConfig implements ConfigData {

	public int minTime = 0;
	public int maxTime = 30;
	public boolean divide = false;
	public int division = 2;

	public static MusicDrConfig getInstance() {
		return AutoConfig.getConfigHolder(MusicDrConfig.class).getConfig();
	}

}