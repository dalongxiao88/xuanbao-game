package org.come.until;

import org.come.Frame.*;
import org.come.Jpanel.*;
import org.come.entity.*;
import com.tool.role.RoleLingFa;
import com.tool.role.ExpUtil;
import com.tool.btn.NeidanBtn;
import org.come.mouslisten.GoodsMouslisten;
import com.tool.role.GetExp;
import org.come.model.Lingbao;
import com.tool.pet.PetProperty;
import org.come.mouslisten.PetAddPointMouslisten;
import com.tool.role.RoleData;
import com.tool.image.ImageMixDeal;
import java.math.BigDecimal;
import com.tool.role.RoleProperty;
import org.come.bean.LoginResult;

public class ExpIncreaseUntil
{
    public static void RoleExp(LoginResult loginResult, String v) {
        FormsManagement.HideForm(123);
        String[] vs = v.split("=");
        int hp = loginResult.getHp().intValue();
        int mp = loginResult.getMp().intValue();
        if (vs.length >= 4) {
            hp = Integer.parseInt(vs[2]);
            mp = Integer.parseInt(vs[3]);
        }
        if (hp == 0) {
            hp = RoleProperty.getHp(loginResult);
            mp = RoleProperty.getMp(loginResult);
        }
        loginResult.setHp(new BigDecimal(hp));
        loginResult.setMp(new BigDecimal(mp));
        loginResult.setExperience(new BigDecimal(vs[1]));
        int lvlN = Integer.parseInt(vs[0].substring(1));
        if (vs.length > 4) {
            loginResult.setGrade(Integer.valueOf(lvlN));
            ImageMixDeal.userimg.getRoleShow().setGrade(Integer.valueOf(lvlN));
            for (int i = 4; i < vs.length; ++i) {
                if (i == 4) {
                    loginResult.setBone(Integer.valueOf(Integer.parseInt(vs[i])));
                }
                else if (i == 5) {
                    loginResult.setSpir(Integer.valueOf(Integer.parseInt(vs[i])));
                }
                else if (i == 6) {
                    loginResult.setPower(Integer.valueOf(Integer.parseInt(vs[i])));
                }
                else if (i == 7) {
                    loginResult.setSpeed(Integer.valueOf(Integer.parseInt(vs[i])));
                }
                else if (i == 8) {
                    loginResult.setCalm(Integer.valueOf(Integer.parseInt(vs[i])));
                }
            }
        }
        else {
            int lvlY = (int)loginResult.getGrade();
            if (lvlN != lvlY) {
                loginResult.setGrade(Integer.valueOf(lvlN));
                ImageMixDeal.userimg.getRoleShow().setGrade(Integer.valueOf(lvlN));
                int cha = lvlN - lvlY;
                loginResult.setBone(Integer.valueOf((int)loginResult.getBone() + cha));
                loginResult.setSpir(Integer.valueOf((int)loginResult.getSpir() + cha));
                loginResult.setPower(Integer.valueOf((int)loginResult.getPower() + cha));
                loginResult.setSpeed(Integer.valueOf((int)loginResult.getSpeed() + cha));
                if (loginResult.getTurnAround() >= 4) {
                    loginResult.setCalm(Integer.valueOf((int)loginResult.getCalm() + cha));
                }
                RoleProperty.Resetgrade((int)loginResult.getGrade(), loginResult.getRace_id());
                RoleData.getRoleData().getLoginResult().setHp(new BigDecimal(RoleProperty.getHp(RoleData.getRoleData().getLoginResult())));
                RoleData.getRoleData().getLoginResult().setMp(new BigDecimal(RoleProperty.getMp(RoleData.getRoleData().getLoginResult())));
                PetAddPointMouslisten.getplayerValue();
            }
        }
        if (FormsManagement.getInternalForm2(105) != null && FormsManagement.getframe(105).isVisible()) {
            PartnerMainJpanel partnerMainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
            partnerMainJpanel.refreshLvl();
        }
        Article.manxie(loginResult);
    }
    
    public static void PetExtPoint(String v) {
        String[] vs = v.split("=");
        BigDecimal petId = new BigDecimal(vs[1]);
        RoleSummoning pet = getPet(petId);
        if (pet == null) {
            return;
        }
        pet.setExtPoint((int)Integer.valueOf(vs[2]));
        if (UserMessUntil.getChosePetMes() != null && pet.getSid().compareTo(UserMessUntil.getChosePetMes().getSid()) == 0) {
            PetAddPointMouslisten.showPetValue();
        }
        if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && pet.getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
            Article.souxie(pet);
        }
    }
    
    public static void PetExp(String v) {
        String[] vs = v.split("=");
        RoleSummoning pet = getPet(new BigDecimal(vs[0].substring(1)));
        if (pet == null) {
            return;
        }
        int lvlY = (int)pet.getGrade();
        int lvlN = Integer.parseInt(vs[1]);
        pet.setGrade(Integer.valueOf(lvlN));
        pet.setExp(new BigDecimal(vs[2]));
        pet.setFriendliness(Long.valueOf(Long.parseLong(vs[3])));
        if (lvlN != lvlY) {
            int cha = lvlN - lvlY;
            pet.setBone(Integer.valueOf((int)pet.getBone() + cha));
            pet.setSpir(Integer.valueOf((int)pet.getSpir() + cha));
            pet.setPower(Integer.valueOf((int)pet.getPower() + cha));
            pet.setSpeed(Integer.valueOf((int)pet.getSpeed() + cha));
            pet.setFaithful(Integer.valueOf(100));
            if (pet.getTurnRount() >= 4) {
                pet.setCalm(Integer.valueOf((int)pet.getCalm() + cha));
            }
            int[] pets = PetProperty.getPetHMASp(pet);
            pet.setBasishp((long)pets[0]);
            pet.setBasismp((long)pets[1]);
            SendRoleAndRolesummingUntil.sendRoleSumming(pet);
        }
        else {
            int hp = Integer.parseInt(vs[4]);
            int mp = Integer.parseInt(vs[5]);
            if (hp == 0) {
                int[] pets2 = PetProperty.getPetHMASp(pet);
                pet.setBasishp((long)pets2[0]);
                pet.setBasismp((long)pets2[1]);
            }
            else {
                pet.setBasishp((long)hp);
                pet.setBasismp((long)mp);
            }
            if (vs.length > 6) {
                pet.setFaithful(Integer.valueOf(Integer.parseInt(vs[6])));
            }
        }
        if (UserMessUntil.getChosePetMes() != null && pet.getSid().compareTo(UserMessUntil.getChosePetMes().getSid()) == 0) {
            PetAddPointMouslisten.showPetValue();
        }
        if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && pet.getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
            Article.souxie(pet);
        }
    }
    
    public static void MountExp(String v) {
        String[] vs = v.split("=");
        Mount mount = getMount(new BigDecimal(vs[0].substring(1)));
        if (mount != null) {
            mount.setMountlvl(Integer.valueOf(Integer.parseInt(vs[1])));
            mount.setExp(Integer.valueOf(Integer.parseInt(vs[2])));
            mount.setProficiency(Integer.valueOf(Integer.parseInt(vs[3])));
        }
    }
    
    public static void LingExp(String v) {
        String[] vs = v.split("=");
        Lingbao lingbao = getLingBao(new BigDecimal(vs[0].substring(1)));
        if (lingbao != null) {
            lingbao.setLingbaolvl(new BigDecimal(vs[1]));
            lingbao.setLingbaoexe(new BigDecimal(vs[2]));
            lingbao.setLingbaoqihe(Long.parseLong(vs[3]));
        }
    }
    
    public static void NeiDanExp(String[] vs) {
        Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(vs[0].substring(1)));
        if (good != null && (long)good.getType() == 750L) {
            String[] vvs = good.getValue().split("\\|");
            StringBuffer buffer = new StringBuffer();
            buffer.append(vvs[0]);
            buffer.append("|");
            buffer.append(vvs[1]);
            buffer.append("|内丹等级=");
            buffer.append(vs[1]);
            buffer.append("转");
            buffer.append(vs[2]);
            buffer.append("|经验=");
            buffer.append(vs[3]);
            good.setValue(buffer.toString());
        }
    }
    
    public static void IncreaseNedanExp(RoleSummoning pet, Goodstable goodstable, long addexp) {
        String[] strings = goodstable.getValue().split("\\|");
        String[] stringLevel = strings[2].split("\\=");
        String[] stringLevel2 = stringLevel[1].split("\\转");
        String[] stringExp = strings[3].split("\\=");
        long exp = Long.parseLong(stringExp[1]) + addexp;
        int nddj = Integer.parseInt(stringLevel2[1]);
        int ndzscs = Integer.parseInt(stringLevel2[0]);
        int mostLevel = getNedanMostLevel(ndzscs);
        int petlvl = AnalysisString.petLvlint((int)pet.getGrade());
        if (ndzscs >= pet.getTurnRount() && nddj >= petlvl) {
            ZhuFrame.getZhuJpanel().addPrompt2("内丹等级不可超过召唤兽等级");
            return;
        }
        if (nddj >= 200) {
            ZhuFrame.getZhuJpanel().addPrompt2("当前内丹已达最大等级！！！");
            return;
        }
        long maxexp = GetExp.getBBNeiExp(ndzscs, nddj + 1);
        while (exp >= maxexp && exp > 0L) {
            if (nddj + 1 > mostLevel) {
                if (ndzscs >= 4) {
                    ZhuFrame.getZhuJpanel().addPrompt2("当前内丹已达最大等级！！！");
                    break;
                }
                else if (ndzscs + 1 > pet.getTurnRount()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("内丹转生次数不可超过召唤兽");
                    break;
                }
                else {
                    ++ndzscs;
                    nddj = 0;
                    maxexp = GetExp.getBBNeiExp(ndzscs, nddj + 1);
                    exp = 0L;
                }
            }
            else if (ndzscs >= pet.getTurnRount() && nddj + 1 > petlvl) {
                ZhuFrame.getZhuJpanel().addPrompt2("内丹等级不可超过召唤兽等级");
                break;
            }
            else {
                exp -= maxexp;
                ++nddj;
                maxexp = GetExp.getBBNeiExp(ndzscs, nddj + 1);
            }
        }
        FrameMessageChangeJpanel.addtext(6, "你的召唤兽" + pet.getSummoningname() + "的" + goodstable.getGoodsname() + "内丹获得" + exp + "经验", null, null);
        String newValue = refreshNedan(goodstable.getValue(), ndzscs, nddj, new BigDecimal(exp));
        goodstable.setValue(newValue);
        GoodsMouslisten.gooduse(goodstable, 2);
        if (FormsManagement.getframe(47).isVisible()) {
            NeidanBtn.ShowNedanMsg(goodstable);
        }
    }
    
    public static void IncreaseLFExp(Lingbao lingbao, long addexp) throws Exception {
        int lvl = lingbao.getLingbaolvl().intValue();
        long exp = lingbao.getLingbaoexe().longValue();
        long maxexp = ExpUtil.LFExp(lvl);
        exp += addexp;
        StringBuffer buffer = new StringBuffer();
        buffer.append("你的");
        buffer.append(lingbao.getBaoname());
        buffer.append("获得了");
        buffer.append(ExpUtil.LFExptoString(addexp));
        buffer.append("道行");
        FrameMessageChangeJpanel.addtext(6, buffer.toString(), null, null);
        boolean l = false;
        while (exp >= maxexp) {
            if (lvl >= 200) {
                break;
            }
            else if (lvl != 0 && lvl % 30 == 0) {
                exp = maxexp;
                ZhuFrame.getZhuJpanel().addPrompt2("突破后才可继续升级");
                break;
            }
            else {
                exp -= maxexp;
                maxexp = ExpUtil.LFExp(++lvl);
                l = true;
            }
        }
        if (l) {
            StringBuffer buffer2 = new StringBuffer();
            buffer2.append("你的");
            buffer2.append(lingbao.getBaoname());
            buffer2.append("升级了");
            ZhuFrame.getZhuJpanel().addPrompt2(buffer2.toString());
        }
        lingbao.setLingbaolvl(new BigDecimal(lvl));
        lingbao.setLingbaoexe(new BigDecimal(exp));
    }
    
    public static void increasePointAndValue() {
        RoleToggleJframe.getRoleToggleJframe().getRoleToggleJpanel().getplayerValue();
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        loginResult.setGrade(Integer.valueOf((int)loginResult.getGrade() + 1));
        loginResult.setBone(Integer.valueOf((int)loginResult.getBone() + 1));
        loginResult.setSpir(Integer.valueOf((int)loginResult.getSpir() + 1));
        loginResult.setPower(Integer.valueOf((int)loginResult.getPower() + 1));
        loginResult.setSpeed(Integer.valueOf((int)loginResult.getSpeed() + 1));
        if (loginResult.getTurnAround() >= 4) {
            loginResult.setCalm(Integer.valueOf((int)loginResult.getCalm() + 1));
        }
        PetAddPointMouslisten.sureGetPlayerPoint();
        RoleData.getRoleData().getLoginResult().setHp(new BigDecimal(RoleProperty.getHp(RoleData.getRoleData().getLoginResult())));
        RoleData.getRoleData().getLoginResult().setMp(new BigDecimal(RoleProperty.getMp(RoleData.getRoleData().getLoginResult())));
    }
    
    public static int getMostLevel() {
        int mostLevel = 0;
        switch (ImageMixDeal.userimg.getRoleShow().getTurnAround()) {
            case 0: {
                mostLevel = 102;
                break;
            }
            case 1: {
                mostLevel = 210;
                break;
            }
            case 2: {
                mostLevel = 338;
                break;
            }
            case 3: {
                mostLevel = 459;
                break;
            }
            case 4: {
                mostLevel = (int)ImageMixDeal.userimg.getRoleShow().getGrade();
                break;
            }
            default: {
                mostLevel = 519;
                break;
            }
        }
        return mostLevel;
    }
    
    public static int getPetMostLevel(RoleSummoning pet) {
        int mostLevel = 0;
        int TurnRount = AnalysisString.petTurnRount((int)pet.getGrade());
        switch (TurnRount) {
            case 0: {
                mostLevel = 100;
                break;
            }
            case 1: {
                mostLevel = 221;
                break;
            }
            case 2: {
                mostLevel = 362;
                break;
            }
            case 3: {
                mostLevel = 543;
                break;
            }
            default: {
                mostLevel = 744;
                break;
            }
        }
        return mostLevel;
    }
    
    public static int getNedanMostLevel(int TurnRount) {
        int mostLevel = 0;
        switch (TurnRount) {
            case 0: {
                mostLevel = 100;
                break;
            }
            case 1: {
                mostLevel = 120;
                break;
            }
            case 2: {
                mostLevel = 140;
                break;
            }
            case 3: {
                mostLevel = 170;
                break;
            }
            case 4: {
                mostLevel = 200;
                break;
            }
        }
        return mostLevel;
    }
    
    public static String refreshNedan(String values, int turnRount, int grade, BigDecimal exp) {
        String[] strings = values.split("\\|");
        String newValue = strings[0] + "|" + strings[1] + "|内丹等级=" + turnRount + "转" + grade + "|经验=" + exp;
        return newValue;
    }
    
    public static void showMountValue(Mount mount) {
        mountJPanel mountJPanel = MountJframe.getMountjframe().getMountjpanel();
        int lvl = (int)mount.getMountlvl();
        if (lvl > 100) {
            lvl -= 100;
        }
        int lingxing = (int)Math.floor((double)((float)(int)mount.getSpri() + (float)lvl / 10.0f * (float)(int)mount.getSpri() / 2.0f));
        int liliang = (int)Math.floor((double)((float)(int)mount.getPower() + (float)lvl / 10.0f * (float)(int)mount.getPower() / 2.0f));
        int gengu = (int)Math.floor((double)((float)(int)mount.getBone() + (float)lvl / 10.0f * (float)(int)mount.getBone() / 2.0f));
        mountJPanel.getLabmountlevel().setText(mount.getMountlvlString());
        mountJPanel.getLabmounttili().setText(mount.getLive() + "");
        mountJPanel.getLabmountintelligence().setText(lingxing + "");
        mountJPanel.getLabmountpower().setText(liliang + "");
        mountJPanel.getLabmountRootbone().setText(gengu + "");
        if (lvl < 100) {
            mountJPanel.getLabmountexp().setText(mount.getExp() + "/" + GetExp.getMountExp((int)mount.getMountlvl()));
        }
        else {
            mountJPanel.getLabmountexp().setText(mount.getExp() + "/999999");
        }
        if (mount.getMoveGrade() == null) {
            mount.setMoveGrade(Integer.valueOf(0));
        }
        MountSkillsJframe.getMountSkillsJframe().getMountSkillsJpanel().getLabProficiency().setText(mount.getProficiency() + "");
    }
    public static void showCarValue(Car car) {
        carJPanel mountJPanel = CarJframe.getMountjframe().getCarjpanel();
//        int lvl = (int)car.getMountlvl();
//        if (lvl > 100) {
//            lvl -= 100;
//        }
        int lingxing = (int)Math.floor((double)((float)(int)car.getSpri()));
        int liliang = (int)Math.floor((double)((float)(int)car.getPower()));
        int gengu = (int)Math.floor((double)((float)(int)car.getBone()));
//        mountJPanel.getLabmountlevel().setText(car.getMountlvlString());
//        mountJPanel.getLabmounttili().setText(car.getLive() + "");
        mountJPanel.getLabmountintelligence().setText(lingxing + "");
        mountJPanel.getLabmountpower().setText(liliang + "");
        mountJPanel.getLabmountRootbone().setText(gengu + "");
//        if (lvl < 100) {
            mountJPanel.getLabmountexp().setText(car.getGradeexp()+ "");
//        }
//        else {
//            mountJPanel.getLabmountexp().setText(car.getExp() + "/999999");
//        }
//        if (car.getMoveGrade() == null) {
//            car.setMoveGrade(Integer.valueOf(0));
//        }
//        MountSkillsJframe.getMountSkillsJframe().getMountSkillsJpanel().getLabProficiency().setText(car.getProficiency() + "");
    }
    public static RoleSummoning getPet(BigDecimal sid) {
        for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
            RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i);
            if (pet.getSid().compareTo(sid) == 0) {
                return pet;
            }
        }
        return null;
    }
    
    public static Mount getMount(BigDecimal mid) {
        if (ZhuJpanel.getListMount() == null) {
            return null;
        }
        for (int i = 0; i < ZhuJpanel.getListMount().size(); ++i) {
            Mount mount = (Mount)ZhuJpanel.getListMount().get(i);
            if (mount.getMid().compareTo(mid) == 0) {
                return mount;
            }
        }
        return null;
    }
    
    public static Lingbao getLingBao(BigDecimal baoid) {
        for (int i = 0; i < RoleLingFa.getRoleLingFa().equipBao.length; ++i) {
            Lingbao lingbao = RoleLingFa.getRoleLingFa().equipBao[i];
            if (lingbao != null && lingbao.getBaoid().compareTo(baoid) == 0) {
                return lingbao;
            }
        }
        return null;
    }
    
    public static void ShouFlyValue(Fly fly) {
        AircraftJPanel aircraftJPanel = AircraftJframe.getAircraftJframe().getAircraftJPanel();
        int lvl = (int)fly.getFlylvl();
        if (lvl > 100) {
            lvl -= 100;
        }
        aircraftJPanel.setSkin(fly.getSkin());
        aircraftJPanel.getLabName().setText(fly.getFlyname() + "(" + AircraftJPanel.getJieshu((int)fly.getFlystate()) + "阶)");
        aircraftJPanel.getLabLvl().setText(fly.getFlylvlString().toString());
        aircraftJPanel.getLabflytili().setText(fly.getFuel().toString());
        aircraftJPanel.getLabStage().setText(fly.getFlystate().toString());
        if (lvl < 100) {
            aircraftJPanel.getLabExp().setText(fly.getExp() + "/" + GetExp.getFlyExp((int)fly.getFlylvl()));
        }
        else {
            aircraftJPanel.getLabExp().setText(fly.getExp() + "/999999");
        }
    }
    
    public static void RoleExpPoint(LoginResult loginResult, String v) {
        FormsManagement.HideForm(123);
        String[] vs = v.split("=");
        loginResult.setExtPoint(loginResult.getExtPoint().add(new BigDecimal(vs[1])));
        PetAddPointMouslisten.getplayerValue();
    }
}
