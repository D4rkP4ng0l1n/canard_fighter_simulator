package ui;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// Le code de cette classe Musique est un code que j'ai repris d'un ancien projet trouvable sur mon github ( SAE---S4 )
public class Musique {

    public static final String MUSIQUE_MENU = "src/main/resources/PortYoneuve.wav";
    public static final String MUSIQUE_COMBAT = "src/main/resources/CombatSauvage.wav";
    public static final String MUSIQUE_COMBAT_DRESSEUR = "src/main/resources/CynthiaBattle.wav";

    private Clip clip;
    private String currentMusic;

    public void playMusic(String filepath) {
        // Vérifie si la musique demandée est déjà en train de jouer
        if (clip != null && clip.isRunning() && filepath.equals(currentMusic)) {
            return;
        }

        stopMusic();

        try {
            File musicFile = new File(filepath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Jouer en boucle
            clip.start();
            currentMusic = filepath;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

}
