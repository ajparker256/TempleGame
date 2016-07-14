package librarys;

import sound.Sound;

public class SoundLibrary {
	public static int gunSound1;
	public static int gunSound2;
	public static int gunSound3;
	public static int gunSound4;
	public static int gunSound5;
	public static int gunSound6;
	public static int gunSound7;
	public static int gunSound8;
	public static int gunSound9;
	public static int gunSound10;
	public static int zombieSound;
	public static int music;
	public static int reloadSound;
	
	public static void init() {
		music = Sound.loadSound("song");
	}

}
