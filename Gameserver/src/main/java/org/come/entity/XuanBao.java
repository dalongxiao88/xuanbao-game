

package org.come.entity;

import java.math.BigDecimal;

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
    public String rgb;
    public String rolelvl;
    public String rolerace;
    private String menpai;
    private String mark1;
    private String mark2;
    private String mark3;
    private String mark4;
    public BigDecimal bid;
    public String fushi;
    private int equipment;
    private BigDecimal roleid;
    public int num;
    public int lvl;
    private int xuanyun;
    // 操作字段  为空不操作 删除="删除" 新增="增加"
    private String operation;

    public String getLs() {
        return ls;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public XuanBao() {
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public void setMenpai(String menpai) {
        this.menpai = menpai;
    }

    public String getMark1() {
        return mark1;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }

    public String getMark3() {
        return mark3;
    }

    public void setMark3(String mark3) {
        this.mark3 = mark3;
    }

    public String getMark4() {
        return mark4;
    }

    public void setMark4(String mark4) {
        this.mark4 = mark4;
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

    public int getXuanyun() {
        return this.xuanyun;
    }

    public void setXuanyun(int xuanyun) {
        this.xuanyun = xuanyun;
    }

    public String getRgb() {
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

    // ... existing code ...

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
