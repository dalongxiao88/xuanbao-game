package org.come.entity;

public class SoundEffect
{
    private String skinId;
    private String wlSoundEffect;
    private String skillSoundEffect;
    private String injuredEffect;
    private String defenseSoundEffect;
    
    public String getSkinId() {
        return this.skinId;
    }
    
    public void setSkinId(String skinId) {
        this.skinId = skinId;
    }
    
    public String getWlSoundEffect() {
        return this.wlSoundEffect;
    }
    
    public void setWlSoundEffect(String wlSoundEffect) {
        this.wlSoundEffect = wlSoundEffect;
    }
    
    public String getSkillSoundEffect() {
        return this.skillSoundEffect;
    }
    
    public void setSkillSoundEffect(String skillSoundEffect) {
        this.skillSoundEffect = skillSoundEffect;
    }
    
    public String getInjuredEffect() {
        return this.injuredEffect;
    }
    
    public void setInjuredEffect(String injuredEffect) {
        this.injuredEffect = injuredEffect;
    }
    
    public String getDefenseSoundEffect() {
        return this.defenseSoundEffect;
    }
    
    public void setDefenseSoundEffect(String defenseSoundEffect) {
        this.defenseSoundEffect = defenseSoundEffect;
    }
}
