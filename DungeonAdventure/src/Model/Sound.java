package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * This class creates and manages the sound contained within the game.
 */
public class Sound {
    /**
     * The clip to use to play the audio.
     */
    private Clip myClip;
    /**
     * An array of URLs containing the locations of the audio in the game.
     */
    private URL[] mySoundURL = new URL[30];

    /**
     * Initializes the audio file URLs.
     */
    public Sound() {
        mySoundURL[0] = getClass().getResource("Resources/sound/dungeon.wav");
    }

    /**
     * Sets the clip to a specific audio file depending on the index.
     *
     * @param theIndex The index to get a specific audio URL.
     */
    public void setFile(final int theIndex) {
        System.out.println("Setting file");
        try {
            AudioInputStream inputAudio = AudioSystem.getAudioInputStream(mySoundURL[theIndex]);
            myClip = AudioSystem.getClip();
            myClip.open(inputAudio);
            System.out.println("Is the clip null?" + (myClip == null));
        } catch (Exception e) {
            System.out.println("There was an error loading the audio " + e.getMessage());
        }
    }

    /**
     * Plays the audio.
     */
    public void play() {
        myClip.start();
    }

    /**
     * Loops the audio.
     */
    public void loop() {
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the audio.
     */
    public void stop() {
        myClip.stop();
    }
}
