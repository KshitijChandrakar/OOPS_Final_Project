package Handlers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private Clip shootClip;
    private Clip gameSoundClip;
    private Clip collisionClip;

    public SoundManager() {
        shootClip = loadSound("src/Audio/SPACE INVADERS SHOOT - Gaming Sound Effects HD FREE NO Copyright.wav");


    }

    private Clip loadSound(String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void playShoot() {
        playClip(shootClip);
    }





    private void playClip(Clip clip) {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
}


