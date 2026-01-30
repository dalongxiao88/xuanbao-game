package org.come.mouslisten;

import org.come.entity.RoleSummoning;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.Frame.TestPetJframe;
import com.tool.PanelDisplay.PetPanelShow;
import com.tool.role.GetExp;
import org.come.Jpanel.TestPetJpanel;
import com.tool.pet.PetProperty;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.Jpanel.TeststateJpanel;
import org.come.bean.LoginResult;
import org.come.until.Article;
import com.tool.role.RoleProperty;
import com.tool.PanelDisplay.RolePanelShow;
import org.come.until.CutButtonImage;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.apache.commons.lang.StringUtils;
import org.come.until.AnalysisString;
import com.tool.role.RoleData;
import org.come.Frame.Teststatejframe;

public class PetAddPointOneMouslisten
{
    public static void getplayerValue() {
        Teststatejframe.getTeststatejframe();
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        TeststateJpanel teststateJpanel = Teststatejframe.getTeststatejframe().getTeststateJpanel();
        int lvltrue = AnalysisString.lvltrue((int)loginResult.getGrade());
        teststateJpanel.changeSoaringPanel(lvltrue);
        teststateJpanel.getLabrace().setText(loginResult.getRace_name());
        teststateJpanel.getLabgangs().setText(loginResult.getGangname());
        teststateJpanel.getLabappellation().setText(loginResult.getTitle());
        teststateJpanel.getLabrolename().setText(loginResult.getRolename());
        if (StringUtils.isNotEmpty(loginResult.getLiangHao())) {
            teststateJpanel.getLabappeleid().setText("");
        }
        else {
            teststateJpanel.getLabappeleid().setText(loginResult.getRole_id() + "");
        }
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (nao.equals("新")) {
            teststateJpanel.getLabrolehead().setIcon(CutButtonImage.getImage("img/head/" + loginResult.getSpecies_id() + "-1.png", -1, -1));
        }
        else {
            teststateJpanel.getLabrolehead().setIcon(CutButtonImage.getImage("img/head/" + loginResult.getSpecies_id() + ".png", -1, -1));
        }
        teststateJpanel.getLabprestige().setText(loginResult.getPrestige().toString());
        teststateJpanel.getLabrecord().setText(loginResult.getPkrecord().toString());
        RolePanelShow.changeGrade((int)loginResult.getGrade());
        if (lvltrue <= 3) {
            String curr = loginResult.getExperience().toString();
            String max = RolePanelShow.accessMaxValue((int)loginResult.getGrade()).toString();
            curr = ((curr.length() > 7) ? (curr.substring(0, curr.length() - 3) + "K") : curr);
            max = ((max.length() > 7) ? (max.substring(0, max.length() - 3) + "K") : max);
            teststateJpanel.getLabEXP().setText(curr + "/" + max);
        }
        else {
            teststateJpanel.getLabEXP().setText(loginResult.getExperience().toString());
        }
        teststateJpanel.getLabpointnumber().setText(loginResult.getCanpoint().toString());
        RoleProperty property = RoleProperty.property;
        if (property == null) {
            return;
        }
        teststateJpanel.getLabrootbone().setText(RoleProperty.getBone(loginResult) + "");
        teststateJpanel.getLabintelligence().setText(RoleProperty.getSpir(loginResult) + "");
        teststateJpanel.getLabpower().setText(RoleProperty.getPower(loginResult) + "");
        teststateJpanel.getLabspeed().setText(RoleProperty.getSpeed(loginResult) + "");
        teststateJpanel.getLabability().setText(RoleProperty.getCalm(loginResult) + "");
        teststateJpanel.getLabHP().setText(loginResult.getHp().intValue() + "/" + RoleProperty.getHp(loginResult));
        teststateJpanel.getLabMP().setText(loginResult.getMp().intValue() + "/" + RoleProperty.getMp(loginResult));
        teststateJpanel.getLabAP().setText(RoleProperty.getAp(loginResult) + "");
        teststateJpanel.getLabSP().setText(RoleProperty.getSp(loginResult) + "");
        teststateJpanel.getLabCP().setText(RoleProperty.getCdz(loginResult) + "");
        Article.manxie(loginResult);
    }
    
    public static void getPetValue() {
        showPetValue();
    }
    
    public static void showPetValue() {
        showPetValue(true);
    }
    
    public static void showPetValue(boolean showHead) {
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        if (pet != null) {
            int[] pets = PetProperty.getPetHMASp(pet);
            TestPetJpanel.getLabHP().setText(pet.getBasishp() + "/" + pets[0]);
            TestPetJpanel.getLabMP().setText(pet.getBasismp() + "/" + pets[1]);
            TestPetJpanel.getLabAP().setText(pets[2] + "");
            TestPetJpanel.getLabSP().setText(pets[3] + "");
            TestPetJpanel.getLabCP().setText(pets[4] + "");
            TestPetJpanel.getLabrootbone().setText(pet.getZBone() + "");
            TestPetJpanel.getLabintelligence().setText(pet.getZSpir() + "");
            TestPetJpanel.getLabpower().setText(pet.getZPower() + "");
            TestPetJpanel.getLabspeed().setText(pet.getZSpeed() + "");
            TestPetJpanel.getLabconcentrate().setText(pet.getZCalm() + "");
            TestPetJpanel.getLabpointnumber().setText(pet.getCanpoint() + "");
            String curr = pet.getExp().toString();
            String max = GetExp.getBBExp(pet.getTurnRount(), AnalysisString.petLvlint((int)pet.getGrade())) + "";
            max = ((max.length() > 7) ? (max.substring(0, max.length() - 3) + "K") : max);
            TestPetJpanel.getLabEXP().setText(curr);
            TestPetJpanel.getLabloyalty().setText(pet.getFaithful().toString());
            TestPetJpanel.getLabclose().setText(pet.getFriendliness() + "");
            PetPanelShow.changeGrade((int)pet.getGrade(), pet.getSummoningname());
            if (showHead) {
                Article.souxie(pet);
            }
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null) {
                if (RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(UserMessUntil.getChosePetMes().getSid()) == 0) {
                    TestPetJpanel.getTestPetJpanel().getBtnwar().setText("休息");
                    TestPetJpanel.getTestPetJpanel().getBtnwar().setTypeBtn(Integer.valueOf(3));
                }
                else {
                    TestPetJpanel.getTestPetJpanel().getBtnwar().setText("参战");
                    TestPetJpanel.getTestPetJpanel().getBtnwar().setTypeBtn(Integer.valueOf(2));
                }
                if (UserMessUntil.getChosePetMes() != null && UserMessUntil.getChosePetMes().getTurnRount() > 3) {
                    TestPetJpanel.getTestPetJpanel().getLingxi().setVisible(true);
                }
                else {
                    TestPetJpanel.getTestPetJpanel().getLingxi().setVisible(false);
                }
            }
            if (pet.getInnerGoods() != null && !pet.getInnerGoods().equals("")) {
                ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                Configure configure = (Configure)item.get(new BigDecimal(1));
                String[] strings = pet.getInnerGoods().split("\\|");
                for (int i = 0; i < Integer.parseInt(configure.getNdslsx()); ++i) {
                    TestPetJpanel.getLabNedan()[i].setGoodstable(null);
                    TestPetJframe.getTestPetJframe().getTestPetJpanel().remove(TestPetJpanel.getLabNedan()[i]);
                }
                if (strings.length > 0) {
                    for (int i = 0; i < strings.length; ++i) {
                        TestPetJframe.getTestPetJframe().getTestPetJpanel().add(TestPetJpanel.getLabNedan()[i]);
                        TestPetJpanel.getLabNedan()[i].setGoodstable((Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(strings[i])));
                    }
                }
            }
            else {
                ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                Configure configure = (Configure)item.get(new BigDecimal(1));
                for (int j = 0; j < Integer.parseInt(configure.getNdslsx()); ++j) {
                    TestPetJpanel.getLabNedan()[j].setGoodstable(null);
                    TestPetJframe.getTestPetJframe().getTestPetJpanel().remove(TestPetJpanel.getLabNedan()[j]);
                }
            }
            if (pet.getTalentLvl() > 0) {
                TestPetJpanel.getTfLab().setVisible(true);
                TestPetJpanel.getTianfucount().setVisible(true);
                TestPetJpanel.getTianfucount().setText(pet.getTalentLvl() + "阶");
            }
            else {
                TestPetJpanel.getTfLab().setVisible(false);
                TestPetJpanel.getTianfucount().setVisible(false);
            }
        }
    }
    
    public static void clearWindow() {
        TestPetJpanel.getLabname().setText("");
        TestPetJpanel.getLabHP().setText("");
        TestPetJpanel.getLabMP().setText("");
        TestPetJpanel.getLabAP().setText("");
        TestPetJpanel.getLabSP().setText("");
        TestPetJpanel.getLabrootbone().setText("");
        TestPetJpanel.getLabintelligence().setText("");
        TestPetJpanel.getLabpower().setText("");
        TestPetJpanel.getLabspeed().setText("");
        TestPetJpanel.getLabpointnumber().setText("");
        TestPetJpanel.getLabEXP().setText("");
        TestPetJpanel.getLabloyalty().setText("");
        TestPetJpanel.getLablevel().setText("");
        for (int i = 0; i < 4; ++i) {
            TestPetJpanel.getLabNedan()[i].setGoodstable(null);
            TestPetJframe.getTestPetJframe().getTestPetJpanel().remove(TestPetJpanel.getLabNedan()[i]);
        }
    }
    
    public static int newgrade(int grade) {
        int newgrade = 0;
        if (grade <= 102) {
            newgrade = grade;
        }
        else if (grade > 102 && grade <= 210) {
            newgrade = grade - 102 + 14;
        }
        else if (grade > 210 && grade <= 338) {
            newgrade = grade - 210 + 14;
        }
        else if (grade > 338 && grade <= 504) {
            newgrade = grade - 338 + 14;
        }
        else if (grade > 504) {
            newgrade = grade - 504 + 14;
        }
        return newgrade;
    }
    
    public static void sureGetPlayerPoint() {
        RoleProperty.getRoleProperty().equipWearOff();
    }
}
