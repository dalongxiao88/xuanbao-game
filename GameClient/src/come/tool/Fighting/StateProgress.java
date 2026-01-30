package come.tool.Fighting;

import org.come.bean.PathPoint;
import java.util.List;

public class StateProgress
{
    private int man;
    private int hp_Change;
    private int mp_Change;
    private int yq_Change;
    private int nq_Change;
    private String data;
    private String data2;
    private int type;
    private String addchixu;
    private String addchixu1;
    private String deletechixu;
    private int Escape;
    private int dir;
    private int dirend;
    private List<PathPoint> path;
    private String Music;
    private String extSound;
    private int zxzt;
    private SkillSpell spell;
    private String skillName;
    private String text;
    private String buff;
    private Double sp;
    private int xyz;
    public int getXyz() {
        return this.xyz;
    }

    public void setXyz(int xyz) {
        this.xyz = xyz;
    }
    public Double getSp() {
        return this.sp;
    }
    
    public void setSp(Double sp) {
        this.sp = sp;
    }
    
    public String getExtSound() {
        return this.extSound;
    }
    
    public void setExtSound(String extSound) {
        this.extSound = extSound;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getDir() {
        return this.dir;
    }
    
    public void setDir(int dir) {
        this.dir = dir;
    }
    
    public int getMan() {
        return this.man;
    }
    
    public void setMan(int man) {
        this.man = man;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public List<PathPoint> getPath() {
        return this.path;
    }
    
    public void setPath(List<PathPoint> path) {
        this.path = path;
    }
    
    public int getHp_Change() {
        return this.hp_Change;
    }
    
    public void setHp_Change(int hp_Change) {
        this.hp_Change = hp_Change;
    }
    
    public int getMp_Change() {
        return this.mp_Change;
    }
    
    public void setMp_Change(int mp_Change) {
        this.mp_Change = mp_Change;
    }
    
    public int getDirend() {
        return this.dirend;
    }
    
    public void setDirend(int dirend) {
        this.dirend = dirend;
    }
    
    public String getAddchixu() {
        return this.addchixu;
    }
    
    public void setAddchixu(String addchixu) {
        this.addchixu = addchixu;
    }
    
    public String getDeletechixu() {
        return this.deletechixu;
    }
    
    public void setDeletechixu(String deletechixu) {
        this.deletechixu = deletechixu;
    }
    
    public int getEscape() {
        return this.Escape;
    }
    
    public void setEscape(int escape) {
        this.Escape = escape;
    }
    
    public String getMusic() {
        return (this.Music == null) ? "" : this.Music;
    }
    
    public void setMusic(String music) {
        this.Music = music;
    }
    
    public String getData() {
        return this.data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public int getZxzt() {
        return this.zxzt;
    }
    
    public void setZxzt(int zxzt) {
        this.zxzt = zxzt;
    }
    
    public SkillSpell getSpell() {
        return this.spell;
    }
    
    public void setSpell(SkillSpell spell) {
        this.spell = spell;
    }
    
    public String getData2() {
        if (this.data2 == null) {
            this.data2 = "";
        }
        return this.data2;
    }
    
    public void setData2(String data2) {
        this.data2 = data2;
    }
    
    public int getYq_Change() {
        return this.yq_Change;
    }
    
    public void setYq_Change(int yq_Change) {
        this.yq_Change = yq_Change;
    }
    
    public int getNq_Change() {
        return this.nq_Change;
    }
    
    public void setNq_Change(int nq_Change) {
        this.nq_Change = nq_Change;
    }
    
    public String getBuff() {
        return this.buff;
    }
    
    public void setBuff(String buff) {
        this.buff = buff;
    }
    
    public String getSkillName() {
        return this.skillName;
    }
    
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    
    public String getAddchixu1() {
        return this.addchixu1;
    }
    
    public void setAddchixu1(String addchixu1) {
        this.addchixu1 = addchixu1;
    }
}
