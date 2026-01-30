package come.tool.Role;

public class RoleSystem
{
    private Integer show;
    private Integer isSound;
    private Integer isSound2;
    private Integer isPk;
    private Integer isNews;
    private Integer isMail;
    private Integer isFriend;
    private Integer isGood;
    private Integer isTeam;
    
    public RoleSystem() {
        this.show = Integer.valueOf(1);
        this.isSound = Integer.valueOf(1);
        this.isSound2 = Integer.valueOf(1);
        this.isPk = Integer.valueOf(1);
        this.isNews = Integer.valueOf(1);
        this.isMail = Integer.valueOf(1);
        this.isFriend = Integer.valueOf(1);
        this.isGood = Integer.valueOf(1);
        this.isTeam = Integer.valueOf(1);
    }
    
    public void set(RoleSystem roleSystem) {
        if (roleSystem.show != null) {
            this.show = roleSystem.show;
        }
        if (roleSystem.isSound != null) {
            this.isSound = roleSystem.isSound;
        }
        if (roleSystem.isSound2 != null) {
            this.isSound2 = roleSystem.isSound2;
        }
        if (roleSystem.isPk != null) {
            this.isPk = roleSystem.isPk;
        }
        if (roleSystem.isNews != null) {
            this.isNews = roleSystem.isNews;
        }
        if (roleSystem.isMail != null) {
            this.isMail = roleSystem.isMail;
        }
        if (roleSystem.isFriend != null) {
            this.isFriend = roleSystem.isFriend;
        }
        if (roleSystem.isGood != null) {
            this.isGood = roleSystem.isGood;
        }
        if (roleSystem.isTeam != null) {
            this.isTeam = roleSystem.isTeam;
        }
    }
    
    public Integer getShow() {
        return this.show;
    }
    
    public void setShow(Integer show) {
        this.show = show;
    }
    
    public Integer getIsSound() {
        return this.isSound;
    }
    
    public void setIsSound(Integer isSound) {
        this.isSound = isSound;
    }
    
    public Integer getIsSound2() {
        return this.isSound2;
    }
    
    public void setIsSound2(Integer isSound2) {
        this.isSound2 = isSound2;
    }
    
    public Integer getIsPk() {
        return this.isPk;
    }
    
    public void setIsPk(Integer isPk) {
        this.isPk = isPk;
    }
    
    public Integer getIsNews() {
        return this.isNews;
    }
    
    public void setIsNews(Integer isNews) {
        this.isNews = isNews;
    }
    
    public Integer getIsMail() {
        return this.isMail;
    }
    
    public void setIsMail(Integer isMail) {
        this.isMail = isMail;
    }
    
    public Integer getIsFriend() {
        return this.isFriend;
    }
    
    public void setIsFriend(Integer isFriend) {
        this.isFriend = isFriend;
    }
    
    public Integer getIsGood() {
        return this.isGood;
    }
    
    public void setIsGood(Integer isGood) {
        this.isGood = isGood;
    }
    
    public Integer getIsTeam() {
        return this.isTeam;
    }
    
    public void setIsTeam(Integer isTeam) {
        this.isTeam = isTeam;
    }
}
