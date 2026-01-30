package org.come.good;

import java.util.List;
import org.come.until.SendRoleAndRolesummingUntil;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.GoodsListFromServerUntil;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.Music;
import org.come.Frame.ZhuFrame;
import com.tool.pet.PetProperty;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;
import com.tool.role.RoleProperty;
import com.tool.role.RoleData;
import org.come.until.AnalysisString;
import org.come.entity.Goodstable;

public class Medicine
{
    public static void RedAndBlue(Goodstable goodPlace, String value) {
        // 药的属性
        int[] yao = AnalysisString.yao(value);
        // 总的hp和当前的hp
        double hp_z = (double)RoleProperty.getHp(RoleData.getRoleData().getLoginResult());
        double hp_d = (double)RoleData.getRoleData().getLoginResult().getHp().intValue();
        // 总的mp和当前的mp
        double mp_z = (double)RoleProperty.getMp(RoleData.getRoleData().getLoginResult());
        double mp_d = (double)RoleData.getRoleData().getLoginResult().getMp().intValue();
        if (!isyao(yao, hp_z, hp_d, mp_z, mp_d)) {
            return;
        }
        if (yao[0] != 0 || yao[2] != 0) {
            hp_d = hp_d + (double)yao[0] + (double)yao[2] * hp_z / 100.0;
            if (hp_d > hp_z) {
                hp_d = hp_z;
            }
            RoleData.getRoleData().getLoginResult().setHp(new BigDecimal(hp_d));
        }
        if (yao[1] != 0 || yao[3] != 0) {
            mp_d = mp_d + (double)yao[1] + (double)yao[3] * mp_z / 100.0;
            if (mp_d > mp_z) {
                mp_d = mp_z;
            }
            RoleData.getRoleData().getLoginResult().setMp(new BigDecimal(mp_d));
        }
        goodPlace.setUsetime(Integer.valueOf((int)goodPlace.getUsetime() - 1));
    }
    
    public static void PetRedAndBlue(Goodstable goodPlace, String value) {
        int[] yao = AnalysisString.yao(value);
        int[] pets = PetProperty.getPetHMASp(UserMessUntil.getChosePetMes());
        double hp_z = (double)pets[0];
        double hp_d = (double)UserMessUntil.getChosePetMes().getBasishp();
        double mp_z = (double)pets[1];
        double mp_d = (double)UserMessUntil.getChosePetMes().getBasismp();
        if (!isyao(yao, hp_z, hp_d, mp_z, mp_d)) {
            return;
        }
        if (yao[0] != 0 || yao[2] != 0) {
            hp_d = hp_d + (double)yao[0] + (double)yao[2] * hp_z / 100.0;
            if (hp_d > hp_z) {
                hp_d = hp_z;
            }
            UserMessUntil.getChosePetMes().setBasishp((long)(int)hp_d);
        }
        if (yao[1] != 0 || yao[3] != 0) {
            mp_d = mp_d + (double)yao[1] + (double)yao[3] * mp_z / 100.0;
            if (mp_d > mp_z) {
                mp_d = mp_z;
            }
            UserMessUntil.getChosePetMes().setBasismp((long)(int)mp_d);
        }
        goodPlace.setUsetime(Integer.valueOf((int)goodPlace.getUsetime() - 1));
    }
    
    public static boolean isyao(int[] yao, double hp_z, double hp_d, double mp_z, double mp_d) {
        if (hp_z == hp_d && mp_z == mp_d) {
            ZhuFrame.getZhuJpanel().addPrompt2("你已经很健康了");
            return false;
        }
        boolean is = false;
        if ((yao[0] != 0 || yao[2] != 0) && hp_z > hp_d) {
            is = true;
            Music.addyinxiao("战斗、站立、行走使用药品.mp3");
        }
        else if (hp_z <= hp_d && (yao[0] != 0 || yao[2] != 0)) {
            ZhuFrame.getZhuJpanel().addPrompt2("生命值已满");
        }
        if ((yao[1] != 0 || yao[3] != 0) && mp_z > mp_d) {
            is = true;
            Music.addyinxiao("战斗、站立、行走使用药品.mp3");
        }
        else if (mp_z <= mp_d && (yao[1] != 0 || yao[3] != 0)) {
            ZhuFrame.getZhuJpanel().addPrompt2("魔法值已满");
        }
        return is;
    }
    // 吃红药 第一个是固定第二个是百分比
    public static void Red(int gz, int bz) {
        double hp_z = (double)RoleProperty.getHp(RoleData.getRoleData().getLoginResult());
        double hp_d = (double)RoleData.getRoleData().getLoginResult().getHp().intValue();
        hp_d = hp_d + (double)gz + (double)bz * hp_z / 100.0;
        if (hp_d > hp_z) {
            hp_d = hp_z;
        }
        RoleData.getRoleData().getLoginResult().setHp(new BigDecimal(hp_d));
        String mes = Agreement.getAgreement().rolechangeAgreement("H" + RoleData.getRoleData().getLoginResult().getHp() + "=" + RoleData.getRoleData().getLoginResult().getMp());
        SendMessageUntil.toServer(mes);
        // 进行面板刷新
        PetAddPointMouslisten.getplayerValue();
    }
    // 吃蓝药
    public static void Blue(int gz, int bz) {
        double mp_z = (double)RoleProperty.getMp(RoleData.getRoleData().getLoginResult());
        double mp_d = (double)RoleData.getRoleData().getLoginResult().getMp().intValue();
        mp_d = mp_d + (double)gz + (double)bz * mp_z / 100.0;
        if (mp_d > mp_z) {
            mp_d = mp_z;
        }
        RoleData.getRoleData().getLoginResult().setMp(new BigDecimal(mp_d));
        String mes = Agreement.getAgreement().rolechangeAgreement("H" + RoleData.getRoleData().getLoginResult().getHp() + "=" + RoleData.getRoleData().getLoginResult().getMp());
        SendMessageUntil.toServer(mes);
        PetAddPointMouslisten.getplayerValue();
    }
    
    public static void zdchiyao(int lx) {
        int z = 0;
        long d = 0L;
        if (lx == 0) {
            z = RoleProperty.getHp(RoleData.getRoleData().getLoginResult());
            d = (long)RoleData.getRoleData().getLoginResult().getHp().intValue();
        }
        else if (lx == 1) {
            z = RoleProperty.getMp(RoleData.getRoleData().getLoginResult());
            d = (long)RoleData.getRoleData().getLoginResult().getMp().intValue();
        }
        else if (lx == 2) {
            if (UserMessUntil.getChosePetMes() == null) {
                return;
            }
            int[] pets = PetProperty.getPetHMASp(UserMessUntil.getChosePetMes());
            z = pets[0];
            d = UserMessUntil.getChosePetMes().getBasishp();
        }
        else if (lx == 3) {
            if (UserMessUntil.getChosePetMes() == null) {
                return;
            }
            int[] pets = PetProperty.getPetHMASp(UserMessUntil.getChosePetMes());
            z = pets[1];
            d = UserMessUntil.getChosePetMes().getBasismp();
        }
        List<Goodstable> goodstables = GoodsListFromServerUntil.chaxunss(0L);
        for (int i = 0; i < goodstables.size(); i++) {
            Goodstable good = goodstables.get(i);
            int usetime = good.getUsetime().intValue();
            if ((lx == 0||lx == 2) ? (
                    (!good.getValue().contains("MP")||good.getValue().contains("HP"))) : ((
              
              lx == 1 || lx == 3) &&
                    (!good.getValue().contains("HP")||good.getValue().contains("MP"))))
              if (d < z) {
                while (good.getUsetime().intValue() > 0 && d < z) {
                  try {
                    if (lx == 0 || lx == 1) {
                      RedAndBlue(good, good.getValue());
                      if (lx == 0) {
                        d = RoleData.getRoleData().getLoginResult().getHp().intValue();
                        continue;
                      } 
                      if (lx == 1)
                        d = RoleData.getRoleData().getLoginResult().getMp().intValue(); 
                      continue;
                    } 
                    PetRedAndBlue(good, good.getValue());
                    if (lx == 2) {
                      d = UserMessUntil.getChosePetMes().getBasishp();
                      continue;
                    } 
                    if (lx == 3)
                      d = UserMessUntil.getChosePetMes().getBasismp(); 
                  } catch (Exception e) {
                    e.printStackTrace();
                  } 
                } 
                if (good.getUsetime().intValue() != usetime) {
                  GoodsMouslisten.gooduse(good, 1);
                  if (good.getUsetime().intValue() <= 0)
                    GoodsListFromServerUntil.Deletebiaoid(good.getRgid()); 
                  if (lx == 0 || lx == 1) {
                    String mes = Agreement.getAgreement().rolechangeAgreement("H" + RoleData.getRoleData().getLoginResult().getHp() + "=" + RoleData.getRoleData().getLoginResult().getMp());
                    SendMessageUntil.toServer(mes);
                    PetAddPointMouslisten.getplayerValue();
                  } else {
                    SendRoleAndRolesummingUntil.sendRoleSumming(UserMessUntil.getChosePetMes());
                    PetAddPointMouslisten.showPetValue();
                  } 
                  Music.addyinxiao("战斗、站立、行走使用药品.mp3");
                } 
              }  
        }
        if (d < (long)z) {
            ZhuFrame.getZhuJpanel().addPrompt("你的药品不够了");
            return;
        }
        Music.addyinxiao("战斗、站立、行走使用药品.mp3");
    }
    
    public static void main(String[] args) {
        Music.addyinxiao("战斗、站立、行走使用药品.mp3");
    }
}
