package come.tool.Fighting;

import java.awt.Graphics;
import org.come.until.ScrenceUntil;
import java.util.ArrayList;
import java.util.List;

public class BuffUtil
{
    public boolean isMcqh;
    public List<Buff> buffs;
    public List<SkillTx> skills;
    
    public BuffUtil() {
        this.isMcqh = false;
        this.buffs = new ArrayList<>();
        this.skills = new ArrayList<>();
    }
    
    public void CS(String text, int camp) {
        if (text == null || text.equals("")) {
            return;
        }
        String[] vs = text.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            String[] vss = vs[i].split("=");
            if (vss[0].equals("0")) {
                if (vss[3].equals("化无") || vss[3].equals("bbxr") || vss[3].equals("bbyh") || vss[3].equals("bbtm") || vss[3].equals("bbsgqx") || vss[3].equals("1881") || vss[3].equals("1234") || vss[3].equals("23009") || vss[3].equals("乾坤遮天")) {
                    Buff buff = new Buff(vss);
                    this.buffs.add(buff);
                }
                else if (vss[3].equals("bbmcqh")) {
                    Buff buff = new Buff(vss);
                    this.buffs.add(buff);
                    if (buff.getCamp() == camp) {
                        this.isMcqh = true;
                    }
                }
            }
            else {
                int id = Integer.parseInt(vss[1]);
                int j = this.buffs.size() - 1;
                while (j >= 0) {
                    Buff buff2 = (Buff)this.buffs.get(j);
                    if (buff2.getId() == id) {
                        if (buff2.getType().equals("bbmcqh") && buff2.getCamp() == camp) {
                            this.isMcqh = false;
                        }
                        this.buffs.remove(j);
                        break;
                    }
                    else {
                        --j;
                    }
                }
            }
        }
        this.ViewPath(camp);
    }
    
    public void ViewPath(int camp) {
        Buff tm = null;
        Buff bb_hw1 = null;
        Buff bb_hw2 = null;
        Buff bb_xr1 = null;
        Buff bb_xr2 = null;
        Buff bb_yh1 = null;
        Buff bb_yh2 = null;
        Buff bb_xc1 = null;
        Buff bb_xc2 = null;
        Buff bb_ff1 = null;
        Buff bb_ff2 = null;
        Buff bb_tgbg1 = null;
        Buff bb_tgbg2 = null;
        for (int i = this.buffs.size() - 1; i >= 0; --i) {
            Buff buff = (Buff)this.buffs.get(i);
            buff.setIsv(false);
            if (buff.getType().equals("bbsgqx") || buff.getType().equals("bbtm")) {
                buff.setX(ScrenceUntil.Screen_x / 2);
                buff.setY(ScrenceUntil.Screen_y / 2);
                buff.setIsv(true);
                if (buff.getType().equals("bbsgqx")) {
                    if (tm != null) {
                        tm.setIsv(false);
                    }
                }
                else {
                    tm = buff;
                }
            }
            else if (buff.getType().equals("bbmcqh")) {
                if (buff.getCamp() == camp) {
                    buff.setX(ScrenceUntil.Screen_x - 230);
                    buff.setY(ScrenceUntil.Screen_y - 80);
                    buff.setIsv(true);
                }
                else {
                    buff.setX(100);
                    buff.setY(360);
                    buff.setIsv(true);
                }
            }
            else if (buff.getType().equals("乾坤遮天")) {
                if (buff.getCamp() == camp || (camp == -1 && buff.getCamp() == 1)) {
                    buff.setX(ScrenceUntil.Screen_x - 290);
                    buff.setY(ScrenceUntil.Screen_y - (int)((double)ScrenceUntil.Screen_y * 0.5));
                    buff.setAlpha(0.6f);
                    buff.setIsv(true);
                }
                else {
                    buff.setX(250);
                    buff.setY(270);
                    if (camp == -1) {
                        buff.setAlpha(0.6f);
                    }
                    buff.setIsv(true);
                }
            }
            else if (buff.getType().equals("化无")) {
                if (buff.getCamp() == camp) {
                    bb_hw2 = buff;
                }
                else {
                    bb_hw1 = buff;
                }
            }
            else if (buff.getType().equals("1881")) {
                if (buff.getCamp() == camp) {
                    bb_xc2 = buff;
                }
                else {
                    bb_xc1 = buff;
                }
            }
            else if (buff.getType().equals("1234") || buff.getType().equals("23009")) {
                if (buff.getCamp() == camp) {
                    bb_ff1 = buff;
                }
                else {
                    bb_ff2 = buff;
                }
            }
            else if (buff.getType().equals("23009")) {
                if (buff.getCamp() == camp) {
                    bb_tgbg1 = buff;
                }
                else {
                    bb_tgbg2 = buff;
                }
            }
            else if (buff.getType().equals("bbxr")) {
                if (buff.getCamp() == camp) {
                    bb_xr1 = buff;
                }
                else {
                    bb_xr2 = buff;
                }
            }
            else if (buff.getType().equals("bbyh")) {
                if (buff.getCamp() == camp) {
                    bb_yh1 = buff;
                }
                else {
                    bb_yh2 = buff;
                }
            }
        }
        if (bb_hw1 == null && bb_xr1 != null) {
            bb_hw1 = bb_xr1;
            bb_xr1 = null;
        }
        if (bb_xr1 == null) {
            bb_xr1 = bb_yh1;
        }
        if (bb_hw1 != null) {
            bb_hw1.setX(ScrenceUntil.Screen_x - 70);
            bb_hw1.setY(ScrenceUntil.Screen_y * 4 / 5);
            bb_hw1.setIsv(true);
        }
        if (bb_xr1 != null) {
            bb_xr1.setX(ScrenceUntil.Screen_x - 30);
            bb_xr1.setY(ScrenceUntil.Screen_y * 3 / 5);
            bb_xr1.setIsv(true);
        }
        if (bb_ff1 != null) {
            bb_ff1.setX(ScrenceUntil.Screen_x - 50);
            bb_ff1.setY(ScrenceUntil.Screen_y * 4 / 5);
            bb_ff1.setIsv(true);
        }
        if (bb_xc1 != null) {
            bb_xc1.setX(ScrenceUntil.Screen_x - 90);
            bb_xc1.setY(ScrenceUntil.Screen_y * 4 / 5);
            bb_xc1.setIsv(true);
        }
        if (bb_tgbg1 != null) {
            bb_tgbg1.setX(ScrenceUntil.Screen_x - 50);
            bb_tgbg1.setY(ScrenceUntil.Screen_y * 4 / 5);
            bb_tgbg1.setIsv(true);
        }
        if (bb_hw2 == null && bb_xr2 != null) {
            bb_hw2 = bb_xr2;
            bb_xr2 = null;
        }
        if (bb_xr2 == null) {
            bb_xr2 = bb_yh2;
        }
        if (bb_hw2 != null) {
            bb_hw2.setX(50);
            bb_hw2.setY(ScrenceUntil.Screen_y * 2 / 5);
            bb_hw2.setIsv(true);
        }
        if (bb_xr2 != null) {
            bb_xr2.setX(160);
            bb_xr2.setY((int)((double)ScrenceUntil.Screen_y * 1.5 / 5.0));
            bb_xr2.setIsv(true);
        }
        if (bb_ff2 != null) {
            bb_ff2.setX(120);
            bb_ff2.setY(ScrenceUntil.Screen_y * 2 / 5);
            bb_ff2.setIsv(true);
        }
        if (bb_xc2 != null) {
            bb_xc2.setX(90);
            bb_xc2.setY(ScrenceUntil.Screen_y * 2 / 5);
            bb_xc2.setIsv(true);
        }
        if (bb_tgbg2 != null) {
            bb_tgbg2.setX(120);
            bb_tgbg2.setY(ScrenceUntil.Screen_y * 2 / 5);
            bb_tgbg2.setIsv(true);
        }
    }
    
    public void SXSkill(List<TypeState> states) {
        int size = 0;
        int max = this.skills.size();
        for (int i = 0; i < states.size(); ++i) {
            if (((TypeState)states.get(i)).getType().equals("逆鳞") || ((TypeState)states.get(i)).getType().equals("天罡八卦") || ((TypeState)states.get(i)).getType().equals("清心静气") || ((TypeState)states.get(i)).getType().equals("凝神一击") || ((TypeState)states.get(i)).getType().equals("气吞山河") || ((TypeState)states.get(i)).getType().equals("行气如虹") || ((TypeState)states.get(i)).getType().equals("神龙摆尾") || ((TypeState)states.get(i)).getType().equals("气聚神凝") || ((TypeState)states.get(i)).getType().equals("流风回雪") || ((TypeState)states.get(i)).getType().equals("黯然销魂")) {
                if (size < max) {
                    ((SkillTx)this.skills.get(size++)).SReset(((TypeState)states.get(i)).getType(), ScrenceUntil.Screen_x - 40 - (size - 1) * 40, ScrenceUntil.Screen_y - 118);
                }
                else {
                    ++size;
                    SkillTx skillTx = new SkillTx();
                    skillTx.SReset(((TypeState)states.get(i)).getType(), ScrenceUntil.Screen_x - 40 - (size - 1) * 40, ScrenceUntil.Screen_y - 118);
                    this.skills.add(skillTx);
                }
            }
        }
        for (int i = this.skills.size() - 1; i >= size; --i) {
            this.skills.remove(i);
        }
    }
    
    public SkillTx MonitorBuff(int x, int y) {
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            if (((SkillTx)this.skills.get(i)).isMonitor(x, y)) {
                return (SkillTx)this.skills.get(i);
            }
        }
        return null;
    }
    
    public void draw(Graphics g) {
        for (int i = this.buffs.size() - 1; i >= 0; --i) {
            ((Buff)this.buffs.get(i)).draw(g);
        }
        for (int i = this.skills.size() - 1; i >= 0; --i) {
            ((SkillTx)this.skills.get(i)).draw(g);
        }
    }
}
