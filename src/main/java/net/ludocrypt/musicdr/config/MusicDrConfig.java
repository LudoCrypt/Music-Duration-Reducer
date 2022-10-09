package net.ludocrypt.musicdr.config;

import eu.midnightdust.lib.config.MidnightConfig.Comment;
import eu.midnightdust.lib.config.MidnightConfig.Entry;

public class MusicDrConfig {

	@Entry
	public static float minTime = 0;

	@Entry
	public static float maxTime = 30;

	@Entry
	public static boolean divide = false;

	@Entry
	public static float division = 2;

	@Comment
	public static Comment experimental;

	@Entry
	public static boolean distortPitch = false;

	@Entry
	public static boolean bellDistribution = true;

	@Entry
	public static float bellStandardDeviationReciprocal = 2;

	@Entry
	public static float chanceToPitchChange = 0.3F;

	@Entry
	public static float minNoteChange = -12;

	@Entry
	public static float maxNoteChange = 12;
}
