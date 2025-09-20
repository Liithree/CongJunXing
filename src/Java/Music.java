package Java;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    //判断音乐是否循环
    boolean isLoop;
    public Music(boolean isLoop) {
        this.isLoop=isLoop;
    }

    public void PlayMusic(String name){
        AudioInputStream audioInputStream ;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir")+"/src/music/"+name));
        } catch (UnsupportedAudioFileException | IOException ex) {
            throw new RuntimeException(ex);
        }
        Clip clip;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException ex) {
            throw new RuntimeException(ex);
        }
        FloatControl floatControl= (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        floatControl.setValue(-10.0f);
        clip.start();
        if(isLoop){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
