package com.tool.btn;

import java.awt.*;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import java.util.Arrays;

import org.come.Jpanel.WuLingPanel;
import org.come.bean.LoginResult;
import java.util.Map;
import org.come.bean.ConfigureBean;
import java.util.List;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.socket.SendMessageUntil;
import org.come.mouslisten.GoodsMouslisten;
import org.come.socket.Agreement;
import org.come.bean.Skill;
import java.util.ArrayList;
import org.come.Frame.OpenSkillGridJframe;
import org.come.until.Util;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.Frame.PetSkillsJframe;
import org.come.until.UserMessUntil;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.FormsManagement;
import org.come.Frame.SupportListJframe;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.entity.RoleSummoning;

public class DeleteSkillBtn extends MoBanBtn
{
    public static RoleSummoning spet;
    
    public DeleteSkillBtn(String iconpath, int type, String text) {
        super(iconpath, type, UIUtils.COLOR_BTNTEXT);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public DeleteSkillBtn(String iconpath, int type, String text, int num) {
        super(iconpath, type, UIUtils.COLOR_BTNTEXT);
        this.setFont(UIUtils.TEXT_FONT);
        this.setNum(num);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    public DeleteSkillBtn(String iconpath, int type, String text, Color[] colors, Font font) {
        super(iconpath, type, colors);
        setText(text);
        setFont(font);
        setVerticalTextPosition(CENTER);
        setHorizontalTextPosition(CENTER);
    }
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.getText().equals("支援列表")) {
            RoleData roleData = RoleData.getRoleData();
            roleData.addHelpBb(roleData.getHelpBbId());
            SupportListJframe.getSupportListJframe().getSupportListJpanel().init(roleData.getHelpBbName(roleData.getHelpBb()));
            FormsManagement.showForm(62);
        } else if (this.getText().equals("开启")) {
            if (WuLingPanel.skill == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请先选中要开启的技能！！！");
                return ;
            }
            final String sendmes = Agreement.getAgreement().userpetAgreement("OPENQL|" + UserMessUntil.getChosePetMes().getSid() + "|" + WuLingPanel.skill.getSkillid());
            SendMessageUntil.toServer(sendmes);
        } else if (this.getText().equals("关闭")) {
            if (WuLingPanel.skill == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请先选中要关闭的技能！！！");
                return ;
            }
            String skill = UserMessUntil.getChosePetMes().getPetSkillswl();
            if (!skill.contains(WuLingPanel.skill.getSkillid())) {
                ZhuFrame.getZhuJpanel().addPrompt2("当前技能没未开启悟灵！！！");
                return ;
            }
            String[] split = skill.split("\\|");
            Integer js = 0;
            for (String s : split) {
                if (s.contains(WuLingPanel.skill.getSkillid())) {
                    String s1 = s.split("=")[1];
                    js = Integer.parseInt(s1);
                    break;
                }
            }
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.closeWl, 1, "#W你是否要消耗#Y5亿#W大话币#W关闭 #G" + UserMessUntil.getChosePetMes().getSummoningname() + "#W的#R" + WuLingPanel.skill.getSkillname()+"("+ js+")#W启灵修炼(关闭后精度清空)");


//            final String sendmes = Agreement.getAgreement().userpetAgreement("CLOSEQL|" + UserMessUntil.getChosePetMes().getSid()+"|" + WuLingPanel.skill.getSkillid());
//            SendMessageUntil.toServer(sendmes);
    }else if (this.getText().equals("重修技能")) {
            Goodstable goodstable = null;
            int i = 0;
            while (i < GoodsListFromServerUntil.getGoodslist().length) {
                if (GoodsListFromServerUntil.getGoodslist()[i] != null && (long)GoodsListFromServerUntil.getGoodslist()[i].getType() == 925L) {
                    goodstable = GoodsListFromServerUntil.getGoodslist()[i];
                    break;
                }
                else {
                    ++i;
                }
            }
            if (goodstable == null) {
                ZhuFrame.getZhuJpanel().addPrompt("您没有 #G终极技能修炼丹#Y无法重新修炼！");
                return;
            }
            RoleSummoning pet = UserMessUntil.getChosePetMes();
            String skill = PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID();
            int xhSum = getXHSum(pet, skill);
            if ((int)goodstable.getUsetime() < xhSum) {
                ZhuFrame.getZhuJpanel().addPrompt("重新修炼该技能需要" + xhSum + "个终极重修丹！！！");
                return;
            }
            DeleteSkillBtn.spet = pet;
            Skill skill2 = UserMessUntil.getSkillId("3034");
            if (UserMessUntil.getChosePetMes().getPetSkills() != null && !UserMessUntil.getChosePetMes().getPetSkills().equals("")) {
                if (UserMessUntil.getChosePetMes().getPetSkills().contains("3034")) {
                    ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽#R" + pet.getSummoningname() + "#Y终极技能#G" + skill2.getSkillname() + "#Y尚未修炼");
                    return;
                }
                if (skill == null) {
                    return;
                }
                if (skill.equals("1606") || skill.equals("1607") || skill.equals("1608") || skill.equals("1828") || skill.equals("1829") || skill.equals("1830") || skill.equals("1840") || skill.equals("1841") || skill.equals("1842") || skill.equals("1867") || skill.equals("1868") || skill.equals("1869")) {
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.Practice, Integer.valueOf(2), "#Y你确定要消耗" + xhSum + "个 #R" + goodstable.getGoodsname() + "*1#Y 重新修炼终极技能 #G" + UserMessUntil.getSkillId(PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID()).getSkillname());
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("你确定这是终极技能！！！");
                }
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽没有技能！！！");
            }
        }
        else if (this.getText().equals("删除技能")) {
            if (Util.isCanBuyOrno()) {
                return;
            }
            if (PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID().equals("")) {
                return;
            }
            if (UserMessUntil.getChosePetMes().getPetSkilllock() != null && UserMessUntil.getChosePetMes().getPetSkilllock().contains(PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID())) {
                ZhuFrame.getZhuJpanel().addPrompt2("该技能已被锁定无法删除！");
                return;
            }
            if (PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillNO() == 8) {
                if (UserMessUntil.getChosePetMes().getBeastSkills() != null && !UserMessUntil.getChosePetMes().getBeastSkills().equals("")) {
                    if (!PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID().equals("")) {
                        if (isZJOK(UserMessUntil.getChosePetMes(), PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID())) {
                            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.DeleteSkill, Integer.valueOf(1), "#Y你确定要删除 #G" + UserMessUntil.getSkillId(PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID()).getSkillname() + "#Y这个技能吗?");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择你要删除的召唤兽技能！！！");
                    }
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽没有技能！！！");
                }
            }
            else if (UserMessUntil.getChosePetMes().getPetSkills() != null && !UserMessUntil.getChosePetMes().getPetSkills().equals("")) {
                if (!PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID().equals("") && UserMessUntil.getChosePetMes().getPetSkills().indexOf(PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID()) != -1) {
                    if (isZJF(PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID())) {
                        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.DeleteSkill, Integer.valueOf(1), "#Y你确定要删除 #G" + UserMessUntil.getSkillId(PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID()).getSkillname() + "#Y这个技能吗?");
                    }
                    else if (isZJOK(UserMessUntil.getChosePetMes(), PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID())) {
                        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.DeleteSkill, Integer.valueOf(2), "#Y你确定要删除 #G" + UserMessUntil.getSkillId(PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID()).getSkillname() + "#Y这个技能吗？需要消耗#R" + isJF(UserMessUntil.getChosePetMes(), PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID()) + "#Y积分！");
                    }
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择你要删除的召唤兽技能！！！");
                }
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽没有技能！！！");
            }
        }
        else if (this.getText().equals("获取技能")) {
            if (Util.isCanBuyOrno()) {
                return;
            }
            RoleSummoning pet2 = UserMessUntil.getChosePetMes();
            if (pet2 == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请先选中你的召唤兽！！！");
                return;
            }
            Goodstable good = OpenSkillGridJframe.getOpenSkillGridJframe().getOpenSkillGridJpanel().getGoodstable();
            if (good == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("少侠,请先准备好要使用的聚魄丹#53！！！");
                return;
            }
            String petskill = pet2.getPetSkills();
            int sum = (int)pet2.getOpenSeal();
            List<String> skills = new ArrayList<>();
            if (sum > 0 && petskill != null && !petskill.equals("")) {
                String[] vs = petskill.split("\\|");
                if (vs.length >= 8 || sum <= vs.length) {
                    ZhuFrame.getZhuJpanel().addPrompt2("召唤兽技能格子已经满了#51");
                    return;
                }
                for (int j = 0; j < vs.length; ++j) {
                    if (!vs[j].equals("")) {
                        skills.add(vs[j]);
                    }
                }
            }
            Skill skill3 = null;
            if ((long)good.getType() == 2326L) {
                skill3 = (Skill)UserMessUntil.NameforSkill.get(good.getValue().split("\\|")[0].split("=")[1]);
            }
            else {
                skill3 = skillid(good.getValue());
            }
            if (skill3 == null) {
                return;
            }
            if (GoodsMouslisten.chongfu(skill3, skills, Agreement.getAgreement().userpetAgreement(good.getRgid().toString() + "|" + pet2.getSid() + "|" + skill3.getSkillid())) != null) {
                return;
            }
            String sendmes = Agreement.getAgreement().userpetAgreement(good.getRgid().toString() + "|" + pet2.getSid() + "|" + skill3.getSkillid());
            SendMessageUntil.toServer(sendmes);
        }
        else if (this.getText().equals("开技能格")) {
            if (Util.isCanBuyOrno()) {
                return;
            }
            RoleSummoning pet2 = UserMessUntil.getChosePetMes();
            if (pet2 == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请先选中你的召唤兽！！！");
                return;
            }
            if (pet2.getTurnRount() < 4) {
                ZhuFrame.getZhuJpanel().addPrompt2("未点化的召唤兽不能使用聚魄丹解封技能格！");
                return;
            }
            Goodstable good = OpenSkillGridJframe.getOpenSkillGridJframe().getOpenSkillGridJpanel().getGoodstable();
            if (good == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("少侠,请先准备好要使用的聚魄丹#47");
                return;
            }
            String sendmes2 = Agreement.getAgreement().userpetAgreement("KQ|" + pet2.getSid() + "|" + good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
        }
        else if (this.getText().equals("删除启灵")) {
            if (Util.isCanBuyOrno()) {
                return;
            }
            if (UserMessUntil.getChosePetMes().getPetQlSkills() != null && !UserMessUntil.getChosePetMes().getPetQlSkills().equals("")) {
                if (!PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID().equals("") && UserMessUntil.getChosePetMes().getPetQlSkills().indexOf(PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID()) != -1) {
                    if (isZJOK(UserMessUntil.getChosePetMes(), PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID())) {
                        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.DeleteQlSkill, Integer.valueOf(2), "#Y你确定要删除 #G" + UserMessUntil.getSkillId(PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getPetskillID()).getSkillname() + "#Y这个启灵技能吗?");
                    }
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择你要删除的召唤兽启灵技能！！！");
                }
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽没有启灵技能！！！");
            }
        }
        else if (this.getNum() == 1) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            String petskill2 = UserMessUntil.getChosePetMes().getPetSkills();
            int sum2 = (int)UserMessUntil.getChosePetMes().getOpenSeal();
            if (sum2 > 0 && petskill2 != null && !petskill2.equals("")) {
                String[] vs = petskill2.split("\\|");
                if (vs.length >= Integer.parseInt(configure.getZhsjngs()) - 1 || sum2 <= vs.length) {
                    ZhuFrame.getZhuJpanel().addPrompt2("召唤兽技能格子已经满了");
                    return;
                }
            }
            String sendmes3 = Agreement.getAgreement().userpetAgreement("shaizi|" + UserMessUntil.getChosePetMes().getSid());
            SendMessageUntil.toServer(sendmes3);
        }
    }
    
    public static int getXHSum(RoleSummoning pet, String skillId) {
        int sum = 1;
        int zj = isZJ(pet, skillId);
        switch (zj) {
            case 2: {
                sum = 1000;
                break;
            }
            case 3: {
                sum = 2000;
                break;
            }
            case 4: {
                sum = 3000;
                break;
            }
            case 5: {
                sum = 4000;
                break;
            }
            case 6: {
                sum = 5000;
                break;
            }
            case 7: {
                sum = 6000;
                break;
            }
            case 8: {
                sum = 7000;
                break;
            }
        }
        return sum;
    }
    
    private static int isJF(RoleSummoning pet, String skillId) {
        int zj = isZJ(pet, skillId);
        int num = 0;
        if (zj >= 2) {
            num = 1;
            switch (zj) {
                case 3: {
                    num = 5;
                    break;
                }
                case 4: {
                    num = 10;
                    break;
                }
                case 5: {
                    num = 20;
                    break;
                }
                case 6: {
                    num = 20;
                    break;
                }
                case 7: {
                    num = 20;
                    break;
                }
                case 8: {
                    num = 20;
                    break;
                }
            }
        }
        return num;
    }
    
    private static boolean isZJOK(RoleSummoning pet, String skillId) {
        int zj = isZJ(pet, skillId);
        if (zj >= 2) {
            int num = 1;
            switch (zj) {
                case 3: {
                    num = 5;
                    break;
                }
                case 4: {
                    num = 10;
                    break;
                }
                case 5: {
                    num = 20;
                    break;
                }
                case 6: {
                    num = 20;
                    break;
                }
                case 7: {
                    num = 20;
                    break;
                }
                case 8: {
                    num = 20;
                    break;
                }
            }
            LoginResult role = RoleData.getRoleData().getLoginResult();
            if (role.getMoney().compareTo(Integer.valueOf(num)) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("删除该技能需要" + num + "积分！！！");
                return false;
            }
        }
        return true;
    }
    
    private static int isZJ(RoleSummoning pet, String skillId) {
        int num = 0;
        List<String> ids = Arrays.asList(new String[] { "1606", "1607", "1608", "1828", "1829", "1830", "1840", "1841", "1842", "1867", "1868", "1869" });
        if (ids.contains(skillId) && StringUtils.isNotBlank(pet.getPetSkills())) {
            String[] skills = pet.getPetSkills().split("\\|");
            for (int j = 0; j < skills.length; ++j) {
                if (ids.contains(skills[j])) {
                    ++num;
                }
            }
        }
        return num;
    }
    
    private static boolean isZJF(String skillId) {
        List<String> ids = Arrays.asList(new String[] { "1606", "1607", "1608", "1828", "1829", "1830", "1840", "1841", "1842", "1867", "1868", "1869" });
        return !ids.contains(skillId);
    }
    
    public static Skill skillid(String value) {
        String[] v = value.split("\\|");
        int up = 0;
        for (int i = 0; i < v.length; i += 2) {
            String jl = v[i].split("=")[1];
            double d = Double.parseDouble(jl);
            if (isV(d + (double)up)) {
                v = v[i + 1].split("=")[1].split("&");
                return UserMessUntil.getSkillId(v[new Random().nextInt(v.length)]);
            }
            up = (int)((double)up + d);
        }
        v = v[1].split("=")[1].split("&");
        return UserMessUntil.getSkillId(v[new Random().nextInt(v.length)]);
    }
    
    public static boolean isV(double value) {
        value *= 100.0;
        return value > (double)new Random().nextInt(10000);
    }
}
