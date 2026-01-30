package org.come.model;

public class Npctable
{
    private String npcid;
    private String tx;
    private String ty;
    private String npcname;
    private String npctype;
    private String talkid;
    private String skin;
    private String dir;
    private String npcway;
    private String mname;
    private String binding;
    private String tick;
    private String ticktxt;
    private String title;
    private String typexdt;
    private Integer fz;
    private String roleId;
    private Long exTime;
    private transient int map;
    private transient int robotId;
    
    public Npctable() {
        this.robotId = -1;
    }
    
    public String getNpcid() {
        return this.npcid;
    }
    
    public void setNpcid(String npcid) {
        this.npcid = npcid;
    }
    
    public String getTx() {
        return this.tx;
    }
    private String num;
    private String roleIdName;
    
    public void setTx(String tx) {
        this.tx = tx;
    }
    
    public String getTy() {
        return this.ty;
    }
    
    public void setTy(String ty) {
        this.ty = ty;
    }
    
    public String getNpcname() {
        return this.npcname;
    }
    
    public void setNpcname(String npcname) {
        this.npcname = ((npcname == null) ? null : npcname.trim());
    }
    
    public String getNpctype() {
        return this.npctype;
    }
    
    public void setNpctype(String npctype) {
        this.npctype = npctype;
    }
    
    public String getTalkid() {
        return this.talkid;
    }
    
    public void setTalkid(String talkid) {
        this.talkid = ((talkid == null) ? null : talkid.trim());
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public String getDir() {
        return this.dir;
    }
    
    public void setDir(String dir) {
        this.dir = dir;
    }
    
    public String getNpcway() {
        return this.npcway;
    }
    
    public void setNpcway(String npcway) {
        this.npcway = ((npcway == null) ? null : npcway.trim());
    }
    
    public String getMname() {
        return this.mname;
    }
    
    public void setMname(String mname) {
        this.mname = ((mname == null) ? null : mname.trim());
    }
    
    public String getBinding() {
        return this.binding;
    }
    
    public void setBinding(String binding) {
        this.binding = binding;
    }
    
    public String getTick() {
        return this.tick;
    }
    
    public void setTick(String tick) {
        this.tick = tick;
    }
    
    public String getTicktxt() {
        return this.ticktxt;
    }
    
    public void setTicktxt(String ticktxt) {
        this.ticktxt = ((ticktxt == null) ? null : ticktxt.trim());
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getMap() {
        return this.map;
    }
    
    public void setMap(int map) {
        this.map = map;
    }
    
    public int getRobotID() {
        if (this.robotId == -1 && this.binding != null && !this.binding.equals("")) {
            try {
                this.robotId = Integer.parseInt(this.binding);
            }
            catch (Exception ex) {}
        }
        return this.robotId;
    }
    
    public String getTypexdt() {
        return this.typexdt;
    }
    
    public void setTypexdt(String typexdt) {
        this.typexdt = typexdt;
    }
    
    public Integer getFz() {
        return this.fz;
    }
    
    public void setFz(Integer fz) {
        this.fz = fz;
    }
    
    public Long getExTime() {
        return this.exTime;
    }
    
    public void setExTime(Long exTime) {
        this.exTime = exTime;
    }
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    public String getRoleIdName() {
        return roleIdName;
    }

    public void setRoleIdName(String roleIdName) {
        this.roleIdName = roleIdName;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getRoleId() {
        return roleId;
    }
}
