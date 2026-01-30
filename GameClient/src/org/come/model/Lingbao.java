package org.come.model;

import org.apache.commons.lang.StringUtils;
import org.come.until.Util;
import org.come.Frame.ZhuFrame;
import org.come.bean.Skill;
import java.util.List;
import java.util.ArrayList;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.NewPart;
import org.come.bean.LoginResult;
import com.tool.role.RoleProperty;
import com.tool.role.RoleData;
import org.come.until.AnalysisString;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.Jpanel.spot.Commodity;

public class Lingbao implements Commodity
{
    private String baoname;
    private String baoquality;
    private String gethard;
    private String baotype;
    private Integer baoactive;
    private String baospeed;
    private String baoreply;
    private String baoshot;
    private String baoap;
    private String resistshot;
    private String assistance;
    private String goodskill;
    private String tianfuskill;
    private String skin;
    private BigDecimal roleid;
    private String fushis;
    private BigDecimal lingbaolvl;
    private BigDecimal lingbaoexe;
    private long lingbaoqihe;
    private String skills;
    private String operation;
    private String kangxing;
    private int skillsum;
    private int fusum;
    private BigDecimal baoid;
    private int equipment;
    private int goodlock;
    private BigDecimal commodityId;
    
    public void getBonus() {
    }
    
    public double getTianfuskillBonus(String type, double value1, double value2) {
        double xs = 0.0;
        if (this.tianfuskill != null) {
            String[] v = this.tianfuskill.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                if (v[i].startsWith("低级") && v[i].endsWith(type)) {
                    xs += value1;
                }
                else if (v[i].startsWith("高级") && v[i].endsWith(type)) {
                    xs += value2;
                }
            }
        }
        return xs;
    }
    
    public int getFushisBonus(String type) {
        int value = 0;
        if (this.fushis != null && !this.fushis.equals("")) {
            String[] v = this.fushis.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v[i]));
                if (good != null) {
                    value = (int)AnalysisString.valuejie(good.getValue(), type);
                }
            }
        }
        return value;
    }
    
    public int hyz() {
        int sp = (int)this.baoactive;
        if (this.fushis != null && !this.fushis.equals("")) {
            String[] v = this.fushis.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v[i]));
                if (good != null) {
                    sp = (int)((double)sp + AnalysisString.valuejie(good.getValue(), "活跃"));
                }
            }
        }
        double xs = 1.0;
        if (this.tianfuskill != null) {
            String[] v2 = this.tianfuskill.split("\\|");
            for (int j = 0; j < v2.length; ++j) {
                if (v2[j].equals("低级活跃")) {
                    xs += 0.1;
                }
                else if (v2[j].equals("高级活跃")) {
                    xs += 0.2;
                }
            }
        }
        return (int)((double)sp * xs);
    }
    
    public int spz() {
        int sp = (int)Double.parseDouble(this.baospeed);
        if (this.fushis != null && !this.fushis.equals("")) {
            String[] v = this.fushis.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v[i]));
                if (good != null) {
                    sp = (int)((double)sp + AnalysisString.valuejie(good.getValue(), "速度") - AnalysisString.valuejie(good.getValue(), "负敏"));
                }
            }
        }
        if (this.tianfuskill != null) {
            String[] v = this.tianfuskill.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                if (v[i].equals("低级敏捷")) {
                    sp += 200;
                }
                else if (v[i].equals("高级敏捷")) {
                    sp += 300;
                }
            }
        }
        return sp;
    }
    
    public double replyz() {
        double sp = Double.parseDouble(this.baoreply);
        if (this.fushis != null && !this.fushis.equals("")) {
            String[] v = this.fushis.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v[i]));
                if (good != null) {
                    sp += AnalysisString.valuejie(good.getValue(), "回复");
                }
            }
        }
        double xs = 0.0;
        if (this.tianfuskill != null) {
            String[] v2 = this.tianfuskill.split("\\|");
            for (int j = 0; j < v2.length; ++j) {
                if (v2[j].length() == 6) {
                    String type = v2[j].substring(4, 6);
                    if (type.equals("回生")) {
                        String dj = v2[j].substring(0, 2);
                        double xss;
                        if (dj.equals("高级")) {
                            xss = 0.001;
                        }
                        else {
                            xss = 5.0E-4;
                        }
                        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                        String jc = v2[j].substring(2, 4);
                        if (jc.equals("根骨")) {
                            xss = xss * (double)RoleProperty.getBone(loginResult) / 4.0;
                        }
                        else if (jc.equals("灵性")) {
                            xss = xss * (double)RoleProperty.getSpir(loginResult) / 4.0;
                        }
                        else if (jc.equals("力量")) {
                            xss = xss * (double)RoleProperty.getPower(loginResult) / 4.0;
                        }
                        else if (jc.equals("敏捷")) {
                            xss = xss * (double)RoleProperty.getSpeed(loginResult) / 4.0;
                        }
                        xs += xss;
                    }
                }
            }
        }
        return sp + xs;
    }
    
    public double shotz() {
        double sp = Double.parseDouble(this.baoshot);
        if (this.fushis != null && !this.fushis.equals("")) {
            String[] v = this.fushis.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v[i]));
                if (good != null) {
                    sp += AnalysisString.valuejie(good.getValue(), "落宝");
                }
            }
        }
        return sp;
    }
    
    public double apz() {
        double sp = Double.parseDouble(this.baoap);
        if (this.fushis != null && !this.fushis.equals("")) {
            String[] v = this.fushis.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v[i]));
                if (good != null) {
                    sp += AnalysisString.valuejie(good.getValue(), "伤害");
                }
            }
        }
        double xs = 0.0;
        if (this.tianfuskill != null) {
            String[] v2 = this.tianfuskill.split("\\|");
            for (int j = 0; j < v2.length; ++j) {
                if (v2[j].length() == 6) {
                    String type = v2[j].substring(4, 6);
                    if (type.equals("增强")) {
                        String dj = v2[j].substring(0, 2);
                        double xss;
                        if (dj.equals("高级")) {
                            xss = 0.001;
                        }
                        else {
                            xss = 5.0E-4;
                        }
                        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                        String jc = v2[j].substring(2, 4);
                        if (jc.equals("根骨")) {
                            xss = xss * (double)RoleProperty.getBone(loginResult) / 4.0;
                        }
                        else if (jc.equals("灵性")) {
                            xss = xss * (double)RoleProperty.getSpir(loginResult) / 4.0;
                        }
                        else if (jc.equals("力量")) {
                            xss = xss * (double)RoleProperty.getPower(loginResult) / 4.0;
                        }
                        else if (jc.equals("敏捷")) {
                            xss = xss * (double)RoleProperty.getSpeed(loginResult) / 4.0;
                        }
                        xs += xss;
                    }
                }
            }
        }
        return sp + xs;
    }
    
    public double resistshopz() {
        double sp = Double.parseDouble(this.resistshot);
        if (this.fushis != null && !this.fushis.equals("")) {
            String[] v = this.fushis.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v[i]));
                if (good != null) {
                    sp += AnalysisString.valuejie(good.getValue(), "抗落宝");
                }
            }
        }
        if (this.tianfuskill != null) {
            String[] v = this.tianfuskill.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                if (v[i].equals("低级抵抗")) {
                    sp += 0.1;
                }
                else if (v[i].equals("高级抵抗")) {
                    sp += 0.2;
                }
            }
        }
        return sp;
    }
    
    public double zyz() {
        double sp = Double.parseDouble(this.assistance);
        if (this.fushis != null && !this.fushis.equals("")) {
            String[] v = this.fushis.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v[i]));
                if (good != null) {
                    sp += AnalysisString.valuejie(good.getValue(), "支援");
                }
            }
        }
        return sp;
    }
    
    public long qhz() {
        return this.lingbaoqihe;
    }
    
    public NewPart getPart() {
        NewPart part = SpriteFactory.createPart(this.skin, 7, 1, null);
        return part;
    }
    
    public int SkillOpen(int path) {
        return (this.skillsum <= path) ? (this.skillsum + 1) : -1;
    }
    
    public int FushiOpen(int path) {
        return (this.fusum <= path) ? (this.fusum + 1) : -1;
    }
    
    public void OpenGrid(boolean type) {
        if (type) {
            ++this.fusum;
        }
        else {
            ++this.skillsum;
        }
    }
    
    public void UpdateLing() {
        String sendMes = Agreement.UpdateLing(GsonUtil.getGsonUtil().getgson().toJson(this));
        SendMessageUntil.toServer(sendMes);
    }
    
    public void fashijihe(String id) {
        List<String> jihe = new ArrayList<>();
        boolean s = true;
        if (this.fushis != null && !this.fushis.equals("")) {
            String[] v = this.fushis.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                if (!v[i].equals(id)) {
                    jihe.add(v[i]);
                }
                else {
                    s = false;
                }
            }
        }
        if (s) {
            jihe.add(id);
        }
        StringBuffer genggai = new StringBuffer();
        for (int i = 0; i < jihe.size(); ++i) {
            if (!genggai.toString().equals("")) {
                genggai.append("|" + (String)jihe.get(i));
            }
            else {
                genggai.append((String)jihe.get(i));
            }
        }
        this.fushis = genggai.toString();
    }
    
    public BigDecimal isfushi(int path) {
        if (this.fushis != null && !this.fushis.equals("")) {
            String[] v = this.fushis.split("\\|");
            if (v.length > path) {
                return new BigDecimal(v[path]);
            }
        }
        return null;
    }
    
    public String isskill(int path) {
        if (this.skills != null && !this.skills.equals("")) {
            String[] v = this.skills.split("\\|");
            if (v.length > path) {
                return v[path];
            }
        }
        return null;
    }
    
    public boolean skilljihe(Skill skill) {
        int type = Integer.parseInt(skill.getSkilltype());
        if (type == 0 && !this.baotype.equals("攻击")) {
            ZhuFrame.getZhuJpanel().addPrompt2("学习技能失败,攻击灵宝无法学习该类型的技能,请继续学习。");
            return false;
        }
        if (type == 1 && !this.baotype.equals("辅助")) {
            ZhuFrame.getZhuJpanel().addPrompt2("学习技能失败,辅助灵宝无法学习该类型的技能,请继续学习。");
            return false;
        }
        if (type == 2 && !this.baotype.equals("落宝")) {
            ZhuFrame.getZhuJpanel().addPrompt2("学习技能失败,落宝类灵宝无法学习该类型的技能,请继续学习。");
            return false;
        }
        String skillname = skill.getSkillname();
        String lvl = skill.getSkilllevel();
        int min = Integer.parseInt(lvl.substring(0, 1));
        int max = Integer.parseInt(lvl.substring(1, 2));
        if ((this.skills == null || this.skills.equals("")) && this.skillsum > 0) {
            this.skills = skillname + "=" + (Util.random.nextInt(max - min + 1) + min);
            return true;
        }
        if (this.skills != null && !this.skills.equals("") && this.skillsum > this.skills.split("\\|").length) {
            String[] v = this.skills.split("\\|");
            int sum = Util.random.nextInt(max - min + 1) + min;
            skillname = skillname + "=" + sum;
            for (int i = 0; i < v.length; ++i) {
                if (v[i].equals(skillname)) {
                    ZhuFrame.getZhuJpanel().addPrompt2("学习技能失败,技能重复");
                    return false;
                }
            }
            this.skills = this.skills + "|" + skillname;
            return true;
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("学习技能失败,技能格子已满");
            return false;
        }
    }
    
    public boolean shanchang(String v) {
        if (this.goodskill == null || this.goodskill.equals("")) {
            return false;
        }
        String[] vs = this.goodskill.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].equals(v)) {
                return true;
            }
        }
        return false;
    }
    
    public String[] getPropertys(int type) {
        List<String> propertys = new ArrayList<>();
        switch (type) {
            case 0: {
                if (this.baoactive != null && (int)this.baoactive > 0) {
                    propertys.add("活跃=" + this.baoactive);
                }
                if (StringUtils.isNotBlank(this.baospeed) && Double.parseDouble(this.baospeed) > 0.0) {
                    propertys.add("速度=" + this.baospeed);
                }
                if (StringUtils.isNotBlank(this.assistance) && Double.parseDouble(this.assistance) > 0.0) {
                    propertys.add("援助几率=" + this.assistance);
                }
                if (StringUtils.isNotBlank(this.baoreply) && Double.parseDouble(this.baoreply) > 0.0) {
                    propertys.add("回复=" + this.baoreply);
                }
                if (StringUtils.isNotBlank(this.baoshot) && Double.parseDouble(this.baoshot) > 0.0) {
                    propertys.add("落宝几率=" + this.baoshot);
                }
                if (StringUtils.isNotBlank(this.baoap) && Double.parseDouble(this.baoap) > 0.0) {
                    propertys.add("伤害=" + this.baoap);
                }
                if (StringUtils.isNotBlank(this.resistshot) && Double.parseDouble(this.resistshot) > 0.0) {
                    propertys.add("抗落宝=" + this.resistshot);
                    break;
                }
                else {
                    break;
                }
            }
            case 1: {
                int add = this.getFushisBonus("活跃");
                if (add > 0) {
                    propertys.add("活跃=" + add);
                }
                add = this.getFushisBonus("速度") + this.getFushisBonus("负敏");
                if (add > 0) {
                    propertys.add("速度=" + add);
                }
                add = this.getFushisBonus("支援");
                if (add > 0) {
                    propertys.add("援助几率=" + add);
                }
                add = this.getFushisBonus("回复");
                if (add > 0) {
                    propertys.add("回复=" + add);
                }
                add = this.getFushisBonus("落宝");
                if (add > 0) {
                    propertys.add("落宝几率=" + add);
                }
                add = this.getFushisBonus("伤害");
                if (add > 0) {
                    propertys.add("伤害=" + add);
                }
                add = this.getFushisBonus("抗落宝");
                if (add > 0) {
                    propertys.add("抗落宝=" + add);
                    break;
                }
                else {
                    break;
                }
            }
            case 2: {
                double bonus = this.getTianfuskillBonus("活跃", 0.1, 0.2);
                if (bonus > 0.0) {
                    propertys.add("活跃=" + bonus);
                }
                bonus = this.getTianfuskillBonus("敏捷", 200.0, 300.0);
                if (bonus > 0.0) {
                    propertys.add("速度=" + bonus);
                }
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                bonus = this.getTianfuskillBonus("根骨回生", 5.0E-4, 0.001) * (double)RoleProperty.getBone(loginResult) / 4.0;
                bonus += this.getTianfuskillBonus("灵性回生", 5.0E-4, 0.001) * (double)RoleProperty.getSpir(loginResult) / 4.0;
                bonus += this.getTianfuskillBonus("力量回生", 5.0E-4, 0.001) * (double)RoleProperty.getPower(loginResult) / 4.0;
                bonus += this.getTianfuskillBonus("敏捷回生", 5.0E-4, 0.001) * (double)RoleProperty.getSpeed(loginResult) / 4.0;
                if (bonus > 0.0) {
                    propertys.add("回复=" + bonus);
                }
                bonus = this.getTianfuskillBonus("根骨增强", 5.0E-4, 0.001) * (double)RoleProperty.getBone(loginResult) / 4.0;
                bonus += this.getTianfuskillBonus("灵性增强", 5.0E-4, 0.001) * (double)RoleProperty.getSpir(loginResult) / 4.0;
                bonus += this.getTianfuskillBonus("力量增强", 5.0E-4, 0.001) * (double)RoleProperty.getPower(loginResult) / 4.0;
                bonus += this.getTianfuskillBonus("敏捷增强", 5.0E-4, 0.001) * (double)RoleProperty.getSpeed(loginResult) / 4.0;
                if (bonus > 0.0) {
                    propertys.add("伤害=" + bonus);
                }
                bonus = this.getTianfuskillBonus("抵抗", 0.1, 0.2);
                if (bonus > 0.0) {
                    bonus = bonus * (double)RoleProperty.getSpeed(loginResult) / 4.0;
                    propertys.add("抗落宝=" + bonus);
                    break;
                }
                else {
                    break;
                }
            }
            case 3: {
                if (StringUtils.isNotBlank(this.kangxing)) {
                    propertys.add(this.kangxing);
                    break;
                }
                else {
                    break;
                }
            }
            case 4: {
                if (this.tianfuskill != null) {
                    String[] vs = this.tianfuskill.split("\\|");
                    for (int i = 0; i < vs.length; ++i) {
                        propertys.add(vs[i]);
                    }
                    break;
                }
                else {
                    break;
                }
            }
        }
        return (String[])propertys.toArray(new String[propertys.size()]);
    }
    
    public String skillmsg(int path) {
        if (this.skills != null && !this.skills.equals("")) {
            String[] vs = this.skills.split("\\|");
            if (vs.length > path) {
                return vs[path];
            }
        }
        return null;
    }
    
    public String getBaoname() {
        return this.baoname;
    }
    
    public void setBaoname(String baoname) {
        this.baoname = baoname;
    }
    
    public String getGethard() {
        return this.gethard;
    }
    
    public void setGethard(String gethard) {
        this.gethard = gethard;
    }
    
    public String getBaotype() {
        return this.baotype;
    }
    
    public void setBaotype(String baotype) {
        this.baotype = baotype;
    }
    
    public Integer getBaoactive() {
        return this.baoactive;
    }
    
    public void setBaoactive(Integer baoactive) {
        this.baoactive = baoactive;
    }
    
    public String getBaospeed() {
        return this.baospeed;
    }
    
    public void setBaospeed(String baospeed) {
        this.baospeed = baospeed;
    }
    
    public String getBaoreply() {
        return this.baoreply;
    }
    
    public void setBaoreply(String baoreply) {
        this.baoreply = baoreply;
    }
    
    public double getBaoshot() {
        return Double.parseDouble(this.baoshot);
    }
    
    public void setBaoshot(String baoshot) {
        this.baoshot = baoshot;
    }
    
    public String getBaoap() {
        return this.baoap;
    }
    
    public void setBaoap(String baoap) {
        this.baoap = baoap;
    }
    
    public double getResistshop() {
        return Double.parseDouble(this.resistshot);
    }
    
    public void setResistshop(String resistshop) {
        this.resistshot = resistshop;
    }
    
    public double getAssistance() {
        return Double.parseDouble(this.assistance);
    }
    
    public void setAssistance(String assistance) {
        this.assistance = assistance;
    }
    
    public String getGoodskill() {
        return this.goodskill;
    }
    
    public void setGoodskill(String goodskill) {
        this.goodskill = goodskill;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public BigDecimal getBaoid() {
        return this.baoid;
    }
    
    public void setBaoid(BigDecimal baoid) {
        this.baoid = baoid;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public String getFushis() {
        return this.fushis;
    }
    
    public void setFushis(String fushis) {
        this.fushis = fushis;
    }
    
    public BigDecimal getLingbaolvl() {
        return this.lingbaolvl;
    }
    
    public void setLingbaolvl(BigDecimal lingbaolvl) {
        this.lingbaolvl = lingbaolvl;
    }
    
    public BigDecimal getLingbaoexe() {
        return this.lingbaoexe;
    }
    
    public void setLingbaoexe(BigDecimal lingbaoexe) {
        this.lingbaoexe = lingbaoexe;
    }
    
    public long getLingbaoqihe() {
        return this.lingbaoqihe;
    }
    
    public void setLingbaoqihe(long lingbaoqihe) {
        this.lingbaoqihe = lingbaoqihe;
    }
    
    public String getSkills() {
        return this.skills;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    public String getOperation() {
        return this.operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public String getKangxing() {
        return this.kangxing;
    }
    
    public void setKangxing(String kangxing) {
        this.kangxing = kangxing;
    }
    
    public int getSkillsum() {
        return this.skillsum;
    }
    
    public void setSkillsum(int skillsum) {
        this.skillsum = skillsum;
    }
    
    public int getFusum() {
        return this.fusum;
    }
    
    public void setFusum(int fusum) {
        this.fusum = fusum;
    }
    
    public int getGoodlock() {
        return this.goodlock;
    }
    
    public void setGoodlock(int goodlock) {
        this.goodlock = goodlock;
    }
    
    public int getEquipment() {
        return this.equipment;
    }
    
    public void setEquipment(int equipment) {
        this.equipment = equipment;
    }
    
    public String getBaoquality() {
        return this.baoquality;
    }
    
    public void setBaoquality(String baoquality) {
        this.baoquality = baoquality;
    }
    
    public String getTianfuskill() {
        return this.tianfuskill;
    }
    
    public void setTianfuskill(String tianfuskill) {
        this.tianfuskill = tianfuskill;
    }
    
    public Lingbao clone() {
        try {
            return (Lingbao)super.clone();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void setCommodityId(BigDecimal commodityId) {
        this.commodityId = commodityId;
    }
    
    @Override
    public BigDecimal getCommodityId() {
        return this.commodityId;
    }
}
