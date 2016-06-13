package sound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import librarys.SoundLibrary;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL.*;
import org.lwjgl.util.WaveData;

import static org.lwjgl.openal.AL10.*;

public class Sound {
	private static int n=1;
	public static int loadSound(String name) {
		int buffer =alGenBuffers();
		WaveData data = null;
		try {
			data = WaveData.create(new BufferedInputStream(new FileInputStream("res/"+name+".wav")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("really");
		}

        alBufferData(buffer, data.format, data.data, data.samplerate);
		data.dispose();
		int source = alGenSources();

			
	
		alSourcei(source,AL_BUFFER,buffer);
		return source;
	}

	public static void playSound(int source){
		alSourceRewind(source);
		alSourcePlay(source);
	}
	public static void loopSound(int source){
		AL10.alSourcei(source, AL10.AL_LOOPING, AL10.AL_TRUE);
		alSourcePlay(source);
	}
	public static void playGunSound(){
		switch (n){
		
		case 1 :
			playSound(SoundLibrary.gunSound1);
			break;
		case 2 :
			playSound(SoundLibrary.gunSound2);
			break;
		case 3 :
			playSound(SoundLibrary.gunSound3);
			break;
		case 4 :
			playSound(SoundLibrary.gunSound4);
			break;
		case 5 :
			playSound(SoundLibrary.gunSound5);
			break;
		case 6 :
			playSound(SoundLibrary.gunSound6);
			break;
		case 7 :
			playSound(SoundLibrary.gunSound7);
			break;
		case 8 :
			playSound(SoundLibrary.gunSound8);
			break;
		case 9 :
			playSound(SoundLibrary.gunSound9);
			break;
		case 10 :
			playSound(SoundLibrary.gunSound10);
			break;


		}
		n++;
		if(n>10){
			n=1;
		}
	}
	
}
