package net.ludocrypt.musicdr;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "musicdr")
public class ModConfig implements ConfigData {

	public int minTime = 0;
	public int maxTime = 30;
	public boolean divide = false;
	public int division = 2;

	public static ModConfig getInstance() {
		return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
	}

}