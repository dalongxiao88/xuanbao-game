package org.come.readBean;

import org.come.entity.SoundEffect;
import java.util.concurrent.ConcurrentHashMap;

public class AllSoundEffect
{
    private ConcurrentHashMap<Integer, SoundEffect> allSoundEffect;
    
    public ConcurrentHashMap<Integer, SoundEffect> getAllSoundEffect() {
        return this.allSoundEffect;
    }
    
    public void setAllSoundEffect(ConcurrentHashMap<Integer, SoundEffect> allSoundEffect) {
        this.allSoundEffect = allSoundEffect;
    }
}
