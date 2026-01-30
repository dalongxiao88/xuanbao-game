package org.come.bean;

import java.util.ArrayList;
import org.come.entity.RoleSummoning;
import java.math.BigDecimal;
import java.util.List;

public class RoleShow
{
    private int x;
    private int y;
    private Long mapid;
    private List<PathPoint> Player_Paths;
    private BigDecimal role_id;
    private BigDecimal gang_id;
    private String gangname;
    private BigDecimal species_id;
    private BigDecimal booth_id;
    private Integer skill_id;
    private int TurnAround;
    private Integer grade;
    private String rolename;
    private String title;
    private int fighting;
    private int mount_id;
    private int fly_id;
    private String flyskin;
    private Integer flyType;
    private int flyX;
    private int flyY;
    private Double flySpeed;
    private BigDecimal troop_id;
    private String teamInfo;
    private String skin;
    private BigDecimal summoning_id;
    private List<RoleSummoning> showRoleSummoningList;
    private transient int captian;
    private Boolean divineRune;
    private int attachPack;
    private int car_id;
    public RoleShow(LoginResult loginResult) {
        this.TurnAround = 0;
        this.flyType = Integer.valueOf(-1);
        this.flyX = 0;
        this.flyY = 0;
        this.flySpeed = Double.valueOf(0.28);
        this.divineRune = Boolean.valueOf(false);
        this.init(loginResult);
    }
    
    public RoleShow(int x, int y) {
        this.TurnAround = 0;
        this.flyType = Integer.valueOf(-1);
        this.flyX = 0;
        this.flyY = 0;
        this.flySpeed = Double.valueOf(0.28);
        this.divineRune = Boolean.valueOf(false);
        this.x = x;
        this.y = y;
        if (this.Player_Paths == null) {
            this.Player_Paths = new ArrayList<>();
        }
        else {
            this.Player_Paths.clear();
        }
    }
    
    public void init(LoginResult loginResult) {
        if (this.Player_Paths == null) {
            this.Player_Paths = new ArrayList<>();
        }
        else {
            this.Player_Paths.clear();
        }
        this.x = loginResult.getX().intValue();
        this.y = loginResult.getY().intValue();
        this.mapid = loginResult.getMapid();
        this.role_id = loginResult.getRole_id();
        this.gang_id = loginResult.getGang_id();
        this.gangname = loginResult.getGangname();
        this.species_id = loginResult.getSpecies_id();
        this.booth_id = loginResult.getBooth_id();
        this.skill_id = loginResult.getSkill_id();
        this.TurnAround = loginResult.getTurnAround();
        this.grade = loginResult.getGrade();
        this.rolename = loginResult.getRolename();
        this.title = loginResult.getTitle();
        this.fighting = (int)loginResult.getFighting();
        this.mount_id = (int)loginResult.getMount_id();
        this.car_id = (int)loginResult.getCar_id();
        this.fly_id = (int)loginResult.getFly_id();
        this.flyskin = loginResult.getFlyskin();
        this.troop_id = loginResult.getTroop_id();
        this.teamInfo = loginResult.getTeamInfo();
        this.skin = loginResult.getSkin();
        this.attachPack = loginResult.getAttachPack();
    }
    
    public List<PathPoint> getPlayer_Paths() {
        return this.Player_Paths;
    }
    
    public void setPlayer_Paths(List<PathPoint> player_Paths) {
        this.Player_Paths = player_Paths;
    }
    
    public BigDecimal getRole_id() {
        return this.role_id;
    }
    
    public void setRole_id(BigDecimal role_id) {
        this.role_id = role_id;
    }
    
    public BigDecimal getGang_id() {
        return this.gang_id;
    }
    
    public void setGang_id(BigDecimal gang_id) {
        this.gang_id = gang_id;
    }
    
    public String getGangname() {
        return this.gangname;
    }
    
    public void setGangname(String gangname) {
        this.gangname = gangname;
    }
    
    public BigDecimal getSpecies_id() {
        return this.species_id;
    }
    
    public void setSpecies_id(BigDecimal species_id) {
        this.species_id = species_id;
    }
    
    public BigDecimal getTroop_id() {
        return this.troop_id;
    }
    
    public void setTroop_id(BigDecimal troop_id) {
        this.troop_id = troop_id;
    }
    
    public BigDecimal getBooth_id() {
        return this.booth_id;
    }
    
    public void setBooth_id(BigDecimal booth_id) {
        this.booth_id = booth_id;
    }
    
    public Integer getSkill_id() {
        return this.skill_id;
    }
    
    public void setSkill_id(Integer skill_id) {
        this.skill_id = skill_id;
    }
    
    public int getTurnAround() {
        return this.TurnAround;
    }
    
    public void setTurnAround(int turnAround) {
        this.TurnAround = turnAround;
    }
    
    public Integer getGrade() {
        return this.grade;
    }
    
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public Long getMapid() {
        return this.mapid;
    }
    
    public void setMapid(Long mapid) {
        this.mapid = mapid;
    }
    
    public int getCaptian() {
        return this.captian;
    }
    
    public void setCaptian(int captian) {
        this.captian = captian;
    }
    
    public String getTeamInfo() {
        return this.teamInfo;
    }
    
    public void setTeamInfo(String teamInfo) {
        this.teamInfo = teamInfo;
    }
    
    public int getFighting() {
        return this.fighting;
    }
    
    public void setFighting(int fighting) {
        this.fighting = fighting;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public int getMount_id() {
        return this.mount_id;
    }
    
    public void setMount_id(int mount_id) {
        this.mount_id = mount_id;
    }
    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getAttachPack() {
        return this.attachPack;
    }
    
    public void setAttachPack(int attachPack) {
        this.attachPack = attachPack;
    }
    
    public String getTeam() {
        return (this.teamInfo != null && !this.teamInfo.equals("") && !this.teamInfo.equals("|")) ? this.teamInfo : this.rolename;
    }
    
    public int getFly_id() {
        return this.fly_id;
    }
    
    public void setFly_id(int fly_id) {
        this.fly_id = fly_id;
    }
    
    public String getFlyskin() {
        return this.flyskin;
    }
    
    public void setFlyskin(String flyskin) {
        this.flyskin = flyskin;
    }
    
    public Integer getFlyType() {
        return this.flyType;
    }
    
    public void setFlyType(Integer flyType) {
        this.flyType = flyType;
    }
    
    public int getFlyX() {
        return this.flyX;
    }
    
    public void setFlyX(int flyX) {
        this.flyX = flyX;
    }
    
    public int getFlyY() {
        return this.flyY;
    }
    
    public void setFlyY(int flyY) {
        this.flyY = flyY;
    }
    
    public Double getFlySpeed() {
        return this.flySpeed;
    }
    
    public void setFlySpeed(Double flySpeed) {
        this.flySpeed = flySpeed;
    }
    
    public BigDecimal getSummoning_id() {
        return this.summoning_id;
    }
    
    public void setSummoning_id(BigDecimal summoning_id) {
        this.summoning_id = summoning_id;
    }
    
    public List<RoleSummoning> getShowRoleSummoningList() {
        return this.showRoleSummoningList;
    }
    
    public void setShowRoleSummoningList(List<RoleSummoning> showRoleSummoningList) {
        this.showRoleSummoningList = showRoleSummoningList;
    }
    
    public Boolean getDivineRune() {
        return this.divineRune;
    }
    
    public void setDivineRune(Boolean divineRune) {
        this.divineRune = divineRune;
    }
}
