import javazoom.jl.player.Player;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

public class Sounds {

    static Player BGMPlayer;
    static boolean on = true;
    static int bgm = 10;

    static void BGM() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (on ) {
                        FileInputStream InputStreams = new FileInputStream(new File(PATH.SOUNDS[bgm]));
                        BGMPlayer = new Player(InputStreams);
                        BGMPlayer.play();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        t.start();
    }


    static void SoundSwitch(){
        if(on) {
            on = false;
            BGMPlayer.close();
        }
        else {
            on = true;
            BGM();
        }

    }




    static void playSingleMp3(int i) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Player player;
                    FileInputStream InputStreams = new FileInputStream(new File(PATH.SOUNDS[i]));
                    player = new Player(InputStreams);
                    if(on)
                    player.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        t.start();
    }

    public static void main(String[] args) throws InterruptedException {
        playSingleMp3(5);
        playSingleMp3(2);
    }
}
