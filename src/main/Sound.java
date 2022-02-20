package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30]; //amount of sounds
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/ds.wav");
		soundURL[1] = getClass().getResource("/sound/ac.wav");
		soundURL[2] = getClass().getResource("/sound/main_theme.wav");
		soundURL[3] = getClass().getResource("/sound/fx/attack/saber_slash.wav");
		soundURL[4] = getClass().getResource("/sound/fx/attack/saber_miss.wav");
	}
	
	public void setfile(int index) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch(Exception e) {
			
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
	
}
