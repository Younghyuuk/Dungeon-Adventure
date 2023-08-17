package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
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
     * An array of URLs containing the locations of the audio in the game. <br>
     * More audio files (like sound effects) can be added in the future.
     */
    private URL[] mySoundURL;
    /**
     * Used to control the volume level.
     */
    private FloatControl myVolumeControl;

    /**
     * Initializes the audio file URLs.
     */
    public Sound() {
        mySoundURL = new URL[30];
        mySoundURL[0] = getClass().getResource("/sound/dungeon.wav");
    }

    /**
     * Sets the clip to a specific audio file depending on the index.
     *
     * @param theIndex The index to get a specific audio URL.
     */
    public void setFile(final int theIndex) {
        try {
            // Play the audio
            AudioInputStream inputAudio = AudioSystem.getAudioInputStream(mySoundURL[theIndex]);
            myClip = AudioSystem.getClip();
            myClip.open(inputAudio);

            // We want to get the FloatControl of the clip
            if (myClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                myVolumeControl = (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);
            }
        } catch (Exception e) {
            System.out.println("There was an error loading the audio: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Plays the audio.
     */
    public void play() {
        if (myClip != null) {
            myClip.start();
        }
    }

    /**
     * Loops the audio.
     */
    public void loop() {
        if (myClip != null) {
            myClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Stops the audio.
     */
    public void stop() {
        if (myClip != null && myClip.isRunning()) {
            myClip.stop();
            myClip.close();
        }
    }

    /**
     * Sets the volume of the audio.
     *
     * @param theVolume The volume to set the audio level to.
     */
    public void setVolume(final float theVolume) {
        if (myVolumeControl != null) {
            myVolumeControl.setValue(theVolume);
        }
    }
}
