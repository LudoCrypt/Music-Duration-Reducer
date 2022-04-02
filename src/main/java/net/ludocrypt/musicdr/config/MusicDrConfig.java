package net.ludocrypt.musicdr.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "musicdr")
public class MusicDrConfig implements ConfigData {

	@ConfigEntry.Gui.Tooltip()
	public int minTime = 0;

	@ConfigEntry.Gui.Tooltip()
	public int maxTime = 30;

	@ConfigEntry.Gui.Tooltip()
	public boolean divide = false;

	@ConfigEntry.Gui.Tooltip()
	public int division = 2;

	@ConfigEntry.Gui.CollapsibleObject
	public Experimental experimental = new Experimental();

	public static class Experimental {

		@ConfigEntry.Gui.Tooltip()
		public boolean distortPitch = false;

		@ConfigEntry.Gui.Tooltip()
		public double chanceToPitchChange = 0.3;

		@ConfigEntry.Gui.Tooltip()
		public float minNoteChange = -12;

		@ConfigEntry.Gui.Tooltip()
		public float maxNoteChange = 12;
	}

	public static MusicDrConfig getInstance() {
		return AutoConfig.getConfigHolder(MusicDrConfig.class).getConfig();
	}

}
