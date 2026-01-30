package org.come.until;

import java.util.ArrayList;
import java.io.File;
import java.util.List;

public class Music
{
    public static MusicPlayer_3 player;
    public static boolean kz1;
    public static List<MusicPlayer_3> players;
    public static boolean kz2;
    public static String currpath;
    public static String MusicNew;
    
    public static void addbeijing(String path) {
        try {
            if (Music.currpath.equals(path)) {
                return;
            }
            Music.currpath = path;
            if (Music.player != null) {
                Music.player.stop();
                Music.player.finalize();
            }
            Music.player = null;
            if (Music.kz1) {
                File file = new File("resource/music/" + path);
                if (file.exists()) {
                    (Music.player = new MusicPlayer_3("resource/music/" + path)).start(true);
                    beijing(Music.kz1);
                }
            }
        }
        catch (Exception e) {
            beijing(false);
            yinxiao(false);
        }
        catch (Throwable t) {}
    }
    
    public static void addyinxiao(String path) {
        try {
            for (int i = Music.players.size() - 1; i >= 0; --i) {
                if (((MusicPlayer_3)Music.players.get(i)).getMusicPath().equals("resource/music/" + path)) {
                    return;
                }
            }
            if (Music.kz2) {
                File file = new File("resource/music/" + path);
                if (file.exists()) {
                    MusicPlayer_3 yinxiao = new MusicPlayer_3("resource/music/" + path);
                    yinxiao.start(false);
                    Music.players.add(yinxiao);
                }
            }
        }
        catch (Exception e) {
            beijing(false);
            yinxiao(false);
            e.printStackTrace();
        }
    }
    
    public static void beijing(boolean b) {
        try {
            Music.kz1 = b;
            if (Music.player != null) {
                if (Music.kz1) {
                    Music.player.continues();
                }
                else {
                    Music.player.stop();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void yinxiao(boolean b) {
        try {
            Music.kz2 = b;
            for (int i = 0; i < Music.players.size(); ++i) {
                try {
                    ((MusicPlayer_3)Music.players.get(i)).stop();
                    ((MusicPlayer_3)Music.players.get(i)).finalize();
                }
                catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            Music.players.clear();
        }
        catch (Exception e2) {
            e2.printStackTrace();
            Music.players.clear();
        }
    }
    
    static {
        Music.kz1 = true;
        Music.players = new ArrayList<>();
        Music.kz2 = true;
        Music.currpath = "";
        Music.MusicNew = "1";
    }
}
