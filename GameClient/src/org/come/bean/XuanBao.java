
package org.come.bean;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;

public class XuanBao {
    public int id;
    public String name;
    public String mp;
    public String ls;
    public String leixing;
    public String type;
    public String pinzhi;
    public String remark;
    public String skill1;
    public String skill2;
    public String skill3;
    public String skill4;
    public String skin;
    public BigDecimal bid;
    public String fushi;
    private int equipment;
    private BigDecimal roleid;
    private int num;
    public int lvl;
    private int xuanyun;
    private String rgb;
    public String rolelvl;
    public String rolerace;
    private String menpai;
    private String mark1;
    private String mark2;
    private String mark3;
    private String mark4;
    public String skill_1;
    public String skill_2;
    public String skill_3;
    public String skill_4;

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getLs() {
        return ls;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeixing() {
        return this.leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPinzhi() {
        return this.pinzhi;
    }

    public void setPinzhi(String pinzhi) {
        this.pinzhi = pinzhi;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSkill1() {
        return this.skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return this.skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return this.skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return this.skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public String getSkin() {
        return this.skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public BigDecimal getBid() {
        return this.bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public String getFushi() {
        return this.fushi;
    }

    public void setFushi(String fushi) {
        this.fushi = fushi;
    }

    public int getEquipment() {
        return this.equipment;
    }

    public void setEquipment(int equipment) {
        this.equipment = equipment;
    }

    public BigDecimal getRoleid() {
        return this.roleid;
    }

    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getLvl() {
        return this.lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int FushiOpen(int path) {
        return this.num <= path ? this.num + 1 : -1;
    }

    public void OpenGrid() {
        ++this.num;
    }

    public BigDecimal isfushi(int path) {
        String[] v;
        if (this.fushi != null && !this.fushi.equals("") && (v = this.fushi.split("\\|")).length > path) {
            return new BigDecimal(v[path]);
        }
        return null;
    }

    public boolean fashijihe(String id) {
        int i;
        ArrayList<String> jihe = new ArrayList<String>();
        boolean s = true;
        if (this.fushi != null && !this.fushi.equals("")) {
            String[] v = this.fushi.split("\\|");
            for (i = 0; i < v.length; ++i) {
                if (!v[i].equals(id)) {
                    jihe.add(v[i]);
                    continue;
                }
                s = false;
            }
        }
        if (s) {
            jihe.add(id);
        }
        StringBuffer genggai = new StringBuffer();
        for (i = 0; i < jihe.size(); ++i) {
            if (!genggai.toString().equals("")) {
                genggai.append("|" + (String) jihe.get(i));
                continue;
            }
            genggai.append((String) jihe.get(i));
        }
        for (i = 0; i < jihe.size(); i++) {
            String string = jihe.get(i);
            Goodstable goodstable = (Goodstable) GoodsListFromServerUntil.fushis.get(new BigDecimal(string));
            String[] rgb = getRgb().split("\\|");
            if (goodstable != null &&
                    !rgb[i].contains(String.valueOf(goodstable.getGoodsname().charAt(0)))) {
                ZhuFrame.getZhuJpanel().addPrompt2("请勿使用非" + goodstable.getGoodsname() + "的符石");
                if (!GoodsListFromServerUntil.newgood(goodstable)) {
                    ZhuFrame.getZhuJpanel().addPrompt2("背包已满");
                }
                return false;
            }
        }
        this.fushi = genggai.toString();
        return true;
    }

    public int getXuanyun() {
        return this.xuanyun;
    }

    public void setXuanyun(int xuanyun) {
        this.xuanyun = xuanyun;
    }

    public String getRgb() {
        if (this.rgb != null && (this.rgb.contains("R") || this.rgb.contains("G") || this.rgb.contains("B") || this.rgb.contains("Y"))) {
            this.rgb = this.rgb.replace("R", "\u7ea2").replace("G", "\u7eff").replace("B", "\u84dd").replace("Y", "\u9ec4");
        }
        return this.rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public String getRolelvl() {
        return this.rolelvl;
    }

    public void setRolelvl(String rolelvl) {
        this.rolelvl = rolelvl;
    }

    public String getRolerace() {
        return this.rolerace;
    }

    public void setRolerace(String rolerace) {
        this.rolerace = rolerace;
    }

    public String getMenpai() {
        return this.menpai;
    }

    public void setMenpai(String menpai) {
        this.menpai = menpai;
    }

    public String getMark1() {
        return this.mark1;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return this.mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }

    public String getMark3() {
        return this.mark3;
    }

    public void setMark3(String mark3) {
        this.mark3 = mark3;
    }

    public String getMark4() {
        return this.mark4;
    }

    public void setMark4(String mark4) {
        this.mark4 = mark4;
    }

    public String getSkill_1() {
        return this.skill_1;
    }

    public void setSkill_1(String skill_1) {
        this.skill_1 = skill_1;
    }

    public String getSkill_2() {
        return this.skill_2;
    }

    public void setSkill_2(String skill_2) {
        this.skill_2 = skill_2;
    }

    public String getSkill_3() {
        return this.skill_3;
    }

    public void setSkill_3(String skill_3) {
        this.skill_3 = skill_3;
    }

    public String getSkill_4() {
        return this.skill_4;
    }

    public void setSkill_4(String skill_4) {
        this.skill_4 = skill_4;
    }

    private String operation;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
