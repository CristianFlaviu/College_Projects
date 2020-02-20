import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class MusicStuff {


    public static void main(String[] args) {
        System.out.println("abc");
        MusicStuff a = new MusicStuff();
        a.playMusic("song1.wav");
    }

    void playMusic(String musicLocation) {

try {
    File musicPath = new File(musicLocation);
    if (musicPath.exists()) {
        AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();

        JOptionPane.showMessageDialog(null, "pres ok to stop");
    } else {
        System.out.println("cant find file");
    }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
