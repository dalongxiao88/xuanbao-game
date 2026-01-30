package org.come.npc;

import java.util.ArrayList;

import come.tool.JDialog.TiShiUtil;
import org.come.Frame.NPCJfram;
import org.come.Frame.OptionsJframe;
import org.come.entity.Mount;
import org.come.Jpanel.ZhuJpanel;
import com.tool.image.ImageMixDeal;
import org.come.until.AnalysisString;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.apache.commons.lang.StringUtils;
import org.come.model.Configure;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import org.come.until.UserMessUntil;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import org.come.entity.RoleSummoning;
import java.util.List;
import org.come.action.NpcMenuAction;

public class PetConversion implements NpcMenuAction
{
    private static List<RoleSummoning> petListSkill;
    private static RoleSummoning pet;
    
    @Override
    public void menuControl(String type) {
        if (type.equals("转生当前召唤兽")) {
            this.petTurnaround();
        }
        else if (type.equals("点化当前坐骑")) {
            this.dianhuaMount();
        }
        else if (type.equals("点化当前召唤兽")) {
            this.dianhuaPet();
        }
        else if (type.equals("飞升当前参战神兽")) {
            this.revealPet();
        }
        else if (type.equals("学习神兽技能(扣除50万亲密)")) {
            this.petLearnSkill();
        }
        else if (type.equals("神兽开启技能格子(消耗游戏币2000万)")) {
            this.petOpenSkillG();
        }
        else if (type.equals("我要学习天赋(扣除一定金钱和亲密,有概率失败!)")) {
            this.xueXiTianFu();
        }
        else if (type.equals("我想重新修炼终极技能")) {
            this.delpetskill();
        }else if (type.equals("我要启灵当前参战召唤兽")) {
            if (UserMessUntil.getChosePetMes() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("没有选中的召唤兽");
                return;
            }
            if (UserMessUntil.getChosePetMes().getOpenql() > 3) {
                ZhuFrame.getZhuJpanel().addPrompt2("召唤兽最多启灵三次");
                return;
            }
            Integer openql = UserMessUntil.getChosePetMes().getOpenql() + 1;
            int qlMoney = openql * 5000;
            int qlQm = openql * 20;

            StringBuffer sb = new StringBuffer();
            sb.append("你的#Y");
            sb.append(UserMessUntil.getChosePetMes().getSummoningname());
            sb.append("#W当前启灵次数#Y");
            sb.append(UserMessUntil.getChosePetMes().getOpenql());
            sb.append("#W次。第#Y");
            sb.append(UserMessUntil.getChosePetMes().getOpenql() + 1 + "#W次启灵需要消耗#Y" + qlMoney + "W大话币和#G" + qlQm + "W召唤兽亲密。#R（启灵必定成功,最多启灵3次）");
            OptionsJframe.getOptionsJframe().getOptionsJpanel().
                    showBox(TiShiUtil.QL, UserMessUntil.getChosePetMes(), sb.toString());
        }
    }
    
    public void petOpenSkillG() {
        if (RoleData.getRoleData().getLoginResult().getSummoning_id() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("您并没有携带召唤兽!!!");
            return;
        }
        RoleSummoning pet = null;
        int i = 0;
        while (i < UserMessUntil.getPetListTable().size()) {
            if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
                pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i);
                break;
            }
            else {
                ++i;
            }
        }
        if (pet == null) {
            return;
        }
        if (!pet.getSsn().equals("2") && !pet.getSsn().equals("3") && !pet.getSsn().equals("4")) {
            ZhuFrame.getZhuJpanel().addPrompt2("您携带的召唤兽并不是神兽!!!");
            return;
        }
        if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(20000000)) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("少侠,你没有2000万银子!!!");
            return;
        }
        String sendmes = Agreement.getAgreement().userpetAgreement("JFSSGZ|" + pet.getSid());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void petLearnSkill() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int num = 9;
        if (configure.getZhsjngs() != null) {
            num = Integer.parseInt(configure.getZhsjngs());
        }
        if (RoleData.getRoleData().getLoginResult().getSummoning_id() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("您并没有携带召唤兽!!!");
            return;
        }
        RoleSummoning pet = null;
        int i = 0;
        while (i < UserMessUntil.getPetListTable().size()) {
            if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
                pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i);
                break;
            }
            else {
                ++i;
            }
        }
        if (pet == null) {
            return;
        }
        if (!pet.getSsn().equals("2") && !pet.getSsn().equals("3") && !pet.getSsn().equals("4")) {
            ZhuFrame.getZhuJpanel().addPrompt2("您携带的召唤兽并不是神兽!!!");
            return;
        }
        if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(50000000)) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("金币不足!!!");
            return;
        }
        if ((long)pet.getFriendliness() < 500000L) {
            ZhuFrame.getZhuJpanel().addPrompt2("您的神兽亲密值不足500000!!!");
            return;
        }
        String ssjf = null;
        if (StringUtils.isNotBlank(pet.getFourattributes())) {
            String[] v;
            for (String s1 : v = pet.getFourattributes().split("\\|")) {
                if (s1.startsWith("ssjn")) {
                    ssjf = s1;
                }
            }
        }
        if (StringUtils.isBlank(ssjf)) {
            ZhuFrame.getZhuJpanel().addPrompt2("您的神兽技能格子还未解封!!!");
            return;
        }
        String sendmes = Agreement.getAgreement().userpetAgreement("SS|" + pet.getSid());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void revealPet() {
        if (RoleData.getRoleData().getLoginResult().getSummoning_id() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("您并没有携带召唤兽!!!");
            return;
        }
        RoleSummoning pet = null;
        int i = 0;
        while (i < UserMessUntil.getPetListTable().size()) {
            if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
                pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i);
                break;
            }
            else {
                ++i;
            }
        }
        if (pet == null) {
            return;
        }
        if (!pet.getSsn().equals("3") && !pet.getSsn().equals("4")) {
            ZhuFrame.getZhuJpanel().addPrompt2("您携带的召唤兽不是可飞升的神兽!!!");
            return;
        }
        if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(5000000)) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("金币不足5000000两!");
            return;
        }
        boolean bool = false;
        if (pet.getRevealNum() == 0) {
            if ((int)pet.getGrade() >= 50) {
                bool = true;
            }
        }
        else if (pet.getRevealNum() == 1) {
            if ((int)pet.getGrade() >= 188) {
                bool = true;
            }
        }
        else if (pet.getRevealNum() == 2 && (int)pet.getGrade() >= 316) {
            bool = true;
        }
        if (!bool) {
            ZhuFrame.getZhuJpanel().addPrompt2("您的神兽 " + pet.getSummoningname() + " 不符合飞升的条件!");
            return;
        }
        String sendmes = Agreement.getAgreement().userpetAgreement("FS|" + pet.getSid());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void dianhuaPet() {
        if (AnalysisString.lvltrue((int)RoleData.getRoleData().getLoginResult().getGrade()) <= 3) {
            ZhuFrame.getZhuJpanel().addPrompt2("人物飞升后才能点化召唤兽");
            return;
        }
        BigDecimal sid = RoleData.getRoleData().getLoginResult().getSummoning_id();
        RoleSummoning pet = null;
        if (sid != null) {
            int i = 0;
            while (i < UserMessUntil.getPetListTable().size()) {
                if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid().longValue() == sid.longValue()) {
                    pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i);
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        if (pet == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有参战的召唤兽！");
            return;
        }
        if ((int)pet.getGrade() != 543) {
            ZhuFrame.getZhuJpanel().addPrompt2("召唤兽等级不够");
            return;
        }
        if ((long)pet.getFriendliness() < 2000000L) {
            ZhuFrame.getZhuJpanel().addPrompt2("召唤兽亲密不够200万");
            return;
        }
        String sendmes = Agreement.getAgreement().userpetAgreement("DH|" + pet.getSid());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void petTurnaround() {
        BigDecimal sid = RoleData.getRoleData().getLoginResult().getSummoning_id();
        RoleSummoning pet = null;
        if (sid != null) {
            int i = 0;
            while (i < UserMessUntil.getPetListTable().size()) {
                if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid().longValue() == sid.longValue()) {
                    pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i);
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        if (pet == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有参战的召唤兽！");
            return;
        }
        if (pet.getTurnRount() >= RoleData.getRoleData().getLoginResult().getTurnAround()) {
            ZhuFrame.getZhuJpanel().addPrompt2("快去升级吧!你快驾驭不你的召唤兽了");
            return;
        }
        if (pet.getTurnRount() >= 3) {
            ZhuFrame.getZhuJpanel().addPrompt2("最高到3转");
            return;
        }
        if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(200000)) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("金币不足！");
            return;
        }
        int lvl = (int)pet.getGrade();
        int petTurn = pet.getTurnRount();
        if (petTurn == 0) {
            if (lvl != 100) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的召唤兽" + pet.getSummoningname() + "等级不够,还需多加历练!");
                return;
            }
        }
        else if (petTurn == 1) {
            if (lvl != 221) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的召唤兽" + pet.getSummoningname() + "等级不够,还需多加历练!");
                return;
            }
        }
        else if (petTurn == 2 && lvl != 362) {
            ZhuFrame.getZhuJpanel().addPrompt2("你的召唤兽" + pet.getSummoningname() + "等级不够,还需多加历练!");
            return;
        }
        String sendmes = Agreement.getAgreement().userpetAgreement("DH|" + pet.getSid());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void dianhuaMount() {
        if (ImageMixDeal.userimg.getRoleShow().getMount_id() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("你当前未骑坐骑");
            return;
        }
        int mount_id = ImageMixDeal.userimg.getRoleShow().getMount_id();
        Mount mount = null;
        List<Mount> mounts = ZhuJpanel.getListMount();
        if (mounts != null) {
            int i = 0;
            while (i < mounts.size()) {
                if ((int)((Mount)mounts.get(i)).getMountid() == mount_id) {
                    mount = (Mount)mounts.get(i);
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        if (mount == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你当前未骑坐骑");
            return;
        }
        if ((int)mount.getMountlvl() != 100 || (int)mount.getProficiency() < 100000) {
            ZhuFrame.getZhuJpanel().addPrompt2("坐骑不符合点化要求");
            return;
        }
        if (mount.getSid() != null || mount.getOthrersid() != null || mount.getSid3() != null || mount.getSid4() != null || mount.getSid5() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("坐骑还管制着召唤兽");
            return;
        }
        String sendmes = Agreement.getAgreement().usermountAgreement("DH|" + mount.getMid());
        SendMessageUntil.toServer(sendmes);
    }
    
    private void xueXiTianFu() {
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        if (pet == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有参战的召唤兽！");
            return;
        }
        String sendmes = Agreement.getAgreement().userpetAgreement("TF|" + pet.getSid());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void delpetskill() {
        PetConversion.petListSkill.clear();
        PetConversion.pet = null;
        for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
            RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i);
            if (pet.getPetSkills() != null && getskill(pet.getPetSkills())) {
                PetConversion.petListSkill.add(UserMessUntil.getPetListTable().get(i));
            }
        }
        NPCJfram.getNpcJfram().getNpcjpanel().GetDelpetSkill(PetConversion.petListSkill);
    }
    
    public static boolean getskill(String skill) {
        String[] skillid = { "1606", "1607", "1608", "1828", "1829", "1830", "1840", "1841", "1842", "1867", "1868", "1869" };
        String[] vs;
        for (String v : vs = skill.split("\\|")) {
            for (String id : skillid) {
                if (v.equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static List<RoleSummoning> getPetListSkill() {
        return PetConversion.petListSkill;
    }
    
    public static void setPetListSkill(List<RoleSummoning> petListSkill) {
        PetConversion.petListSkill = petListSkill;
    }
    
    public static RoleSummoning getPet() {
        return PetConversion.pet;
    }
    
    public static void setPet(RoleSummoning pet) {
        PetConversion.pet = pet;
    }
    
    static {
        PetConversion.petListSkill = new ArrayList<>();
    }
}
