package org.come.until;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

public class MusicPlayer_3
{
    private String musicPath;
    private volatile boolean run;
    private Thread mainThread;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceDataLine;
    MusicPlayer_3 musicPlayer_3;
    
    public MusicPlayer_3(String musicPath) {
        this.run = true;
        this.musicPlayer_3 = this;
        this.musicPath = musicPath;
        this.prefetch();
    }
    
    private void prefetch() {
        try {
            File file = new File(this.musicPath);
            this.audioStream = AudioSystem.getAudioInputStream(file);
            this.audioFormat = this.audioStream.getFormat();
            if (this.audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                this.audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, this.audioFormat.getSampleRate(), 16, this.audioFormat.getChannels(), this.audioFormat.getChannels() * 2, this.audioFormat.getSampleRate(), false);
                this.audioStream = AudioSystem.getAudioInputStream(this.audioFormat, this.audioStream);
            }
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, this.audioFormat, -1);
            (this.sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo)).open(this.audioFormat);
            this.sourceDataLine.start();
        }
        catch (Exception ex) {
            System.err.println(this.musicPath);
            Music.kz1 = false;
            Music.beijing(Music.kz2 = false);
            Music.yinxiao(false);
            System.err.println("音乐报错");
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.sourceDataLine.drain();
        this.sourceDataLine.close();
        this.audioStream.close();
    }
    
    private void playMusic(boolean loop) throws InterruptedException {
        try {
            if (loop) {
                while (true) {
                    this.playMusic();
                }
            }
            else {
                this.playMusic();
                this.sourceDataLine.drain();
                this.sourceDataLine.close();
                this.audioStream.close();
            }
        }
        catch (Exception ex) {}
    }
    
    private void playMusic() throws Exception {
        synchronized (this) {
            this.run = true;
        }
        File file = new File(this.musicPath);
        this.audioStream = AudioSystem.getAudioInputStream(file);
        this.audioFormat = this.audioStream.getFormat();
        if (this.audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
            this.audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, this.audioFormat.getSampleRate(), 16, this.audioFormat.getChannels(), this.audioFormat.getChannels() * 2, this.audioFormat.getSampleRate(), false);
            this.audioStream = AudioSystem.getAudioInputStream(this.audioFormat, this.audioStream);
        }
        byte[] tempBuff = new byte[1024];
        int count;
        while ((count = this.audioStream.read(tempBuff, 0, tempBuff.length)) != -1) {
            synchronized (this) {
                while (!this.run) {
                    this.wait();
                }
            }
            this.sourceDataLine.write(tempBuff, 0, count);
        }
    }
    
    private void stopMusic() {
        synchronized (this) {
            this.run = false;
            this.notifyAll();
        }
    }
    
    private void continueMusic() {
        synchronized (this) {
            this.run = true;
            this.notifyAll();
        }
    }
    
    public void start(boolean loop) {
        (this.mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MusicPlayer_3.this.playMusic(loop);
                    if (!loop) {
                        Music.players.remove(MusicPlayer_3.this.musicPlayer_3);
                    }
                }
                catch (InterruptedException e) {
                    Music.players.remove(MusicPlayer_3.this.musicPlayer_3);
                }
            }
        })).start();
    }
    
    public void stop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MusicPlayer_3.this.stopMusic();
            }
        }).start();
    }
    
    public void continues() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MusicPlayer_3.this.continueMusic();
            }
        }).start();
    }
    
    public String getMusicPath() {
        return this.musicPath;
    }
    
    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }
    
    public Thread getMainThread() {
        return this.mainThread;
    }
    
    public void setMainThread(Thread mainThread) {
        this.mainThread = mainThread;
    }
}
