package org.come.mouslisten;

import org.come.MountShouHu.ShouhuPackJpanel;
import org.come.MountShouHu.ShouhuPackJframe;
import org.come.entity.Mount;
import org.come.Jpanel.ZhuJpanel;
import org.come.Jpanel.MountShouhuJpanel;
import java.util.List;
import javax.swing.DefaultListModel;
import com.tool.time.Limit;
import com.tool.time.TimeLimit;
import com.tool.btn.NeidanBtn;
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
import org.come.bean.LoginResult;
import org.come.until.Article;
import come.tool.Fighting.FightingMixDeal;
import com.tool.role.RoleProperty;
import com.tool.PanelDisplay.RolePanelShow;
import org.come.until.CutButtonImage;
import org.come.Jpanel.TeststateJpanel;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.apache.commons.lang.StringUtils;
import com.tool.image.Creepsskin;
import com.tool.image.ImageMixDeal;
import org.come.until.AnalysisString;
import com.tool.role.RoleData;
import org.come.Frame.Teststatejframe;

import static com.tool.role.RoleProperty.getSpir;

public class PetAddPointMouslisten
{
    public static void getplayerValue() {
        Teststatejframe.getTeststatejframe();
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        TeststateJpanel teststateJpanel = Teststatejframe.getTeststatejframe().getTeststateJpanel();
        int lvltrue = AnalysisString.lvltrue((int)loginResult.getGrade());
        teststateJpanel.changeSoaringPanel(lvltrue);
        teststateJpanel.getLabrace().setText(loginResult.getRace_name() + "·" + Creepsskin.getLocalName(ImageMixDeal.userimg.getRoleShow().getSpecies_id().intValue()));
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
            if (TeststateJpanel.getQhnum().equals("1")) {
                teststateJpanel.getLabrolehead().setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/" + loginResult.getSpecies_id() + ".png", -1, -1));
            }
            else {
                teststateJpanel.getLabrolehead().setIcon(CutButtonImage.getImage("img/head/" + loginResult.getSpecies_id() + "-1.png", -1, -1));
            }
        }
        else if (TeststateJpanel.getQhnum().equals("1")) {
            teststateJpanel.getLabrolehead().setIcon(CutButtonImage.getImage("Client/神通天演册/40×40/仙/qh/" + loginResult.getSpecies_id() + ".png", -1, -1));
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
        teststateJpanel.getLabintelligence().setText(getSpir(loginResult) + "");
        teststateJpanel.getLabpower().setText(RoleProperty.getPower(loginResult) + "");
        teststateJpanel.getLabspeed().setText(RoleProperty.getSpeed(loginResult) + "");
        teststateJpanel.getLabability().setText(RoleProperty.getCalm(loginResult) + "");
        if (FightingMixDeal.State != 0 && loginResult.getBskvalue() != null) {
            if (BSKvalue(1) != 0.0) {
                teststateJpanel.getLabHP().setText((long)BSKvalue(1) + "/" + (long)BSKvalue(1));
            }
            else {
                teststateJpanel.getLabHP().setText(loginResult.getHp().intValue() + "/" + RoleProperty.getHp(loginResult));
            }
            if (BSKvalue(2) != 0.0) {
                teststateJpanel.getLabMP().setText((long)BSKvalue(2) + "/" + (long)BSKvalue(2));
            }
            else {
                teststateJpanel.getLabMP().setText(loginResult.getMp().intValue() + "/" + RoleProperty.getMp(loginResult));
            }
            if (BSKvalue(3) != 0.0) {
                teststateJpanel.getLabAP().setText((long)BSKvalue(3) + "");
            }
            else {
                teststateJpanel.getLabAP().setText(RoleProperty.getAp(loginResult) + "");
            }
            if (BSKvalue(4) != 0.0) {
                teststateJpanel.getLabSP().setText((long)BSKvalue(4) + "");
            }
            else {
                teststateJpanel.getLabSP().setText(RoleProperty.getSp(loginResult) + "");
            }
        }
        else {
            teststateJpanel.getLabHP().setText(loginResult.getHp().intValue() + "/" + RoleProperty.getHp(loginResult));
            teststateJpanel.getLabMP().setText(loginResult.getMp().intValue() + "/" + RoleProperty.getMp(loginResult));
            teststateJpanel.getLabAP().setText(RoleProperty.getAp(loginResult) + "");
            teststateJpanel.getLabSP().setText(RoleProperty.getSp(loginResult) + "");
        }
        teststateJpanel.getLabCP().setText(RoleProperty.getCdz(loginResult) + "");
        Article.manxie(loginResult);
        int spir = getSpir(loginResult);
        property.SETadditional("法术命中率",(double)spir * 0.02);
        property.SETadditional("法术暴击", (double)spir * 0.03);
        property.SETadditional("法术暴击增伤", (double)spir * 0.04);
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
                        NeidanBtn[] s = TestPetJpanel.getLabNedan();
                        TestPetJframe.getTestPetJframe().getTestPetJpanel().add(s[i]);
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
        TestPetJpanel.getLabclose().setText("");
        TestPetJpanel.getTfLab().setVisible(false);
        TestPetJpanel.getTianfucount().setVisible(false);
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        for (int i = 0; i < Integer.parseInt(configure.getNdslsx()); ++i) {
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
    
    public static double BSKvalue(int tpye) {
        boolean foundMatch = false;
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        for (int i = 0; i < TimeLimit.getLimits().limits.size(); ++i) {
            Limit limit = (Limit)TimeLimit.getLimits().limits.get(i);
            if (limit.getType().equals("变身卡")) {
                String value = limit.getValue();
                if (value != null && !value.equals("")) {
                    String[] v = value.split("\\|");
                    for (int z = 0; z < v.length && !foundMatch; ++z) {
                        try {
                            String[] mes = v[z].split("=");
                            if (mes.length > 1) {
                                switch (tpye) {
                                    case 1: {
                                        if (mes[0].equals("加强气血")) {
                                            foundMatch = true;
                                            double valueZ = (double)loginResult.getHp().intValue() / (Double.parseDouble(mes[1]) / 100.0 + 1.0);
                                            return valueZ * (Double.parseDouble(mes[1]) * (double)loginResult.getBskvalue() / 100.0 + 1.0);
                                        }
                                        else {
                                            break;
                                        }
                                    }
                                    case 2: {
                                        if (mes[0].equals("加强魔法")) {
                                            foundMatch = true;
                                            double valueZ = (double)loginResult.getMp().intValue() / (Double.parseDouble(mes[1]) / 100.0 + 1.0);
                                            return valueZ * (Double.parseDouble(mes[1]) * (double)loginResult.getBskvalue() / 100.0 + 1.0);
                                        }
                                        else {
                                            break;
                                        }
                                    }
                                    case 3: {
                                        if (mes[0].equals("加强攻击")) {
                                            foundMatch = true;
                                            double valueZ = (double)RoleProperty.getAp(loginResult) / (Double.parseDouble(mes[1]) / 100.0 + 1.0);
                                            return valueZ * (Double.parseDouble(mes[1]) * (double)loginResult.getBskvalue() / 100.0 + 1.0);
                                        }
                                        else {
                                            break;
                                        }
                                    }
                                    case 4: {
                                        if (mes[0].equals("加强速度")) {
                                            foundMatch = true;
                                            double valueZ = (double)RoleProperty.getSp(loginResult) / (Double.parseDouble(mes[1]) / 100.0 + 1.0);
                                            return valueZ * (Double.parseDouble(mes[1]) * (double)loginResult.getBskvalue() / 100.0 + 1.0);
                                        }
                                        else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
            }
        }
        return 0.0;
    }
    
    public static Object[] value(String value, int mountid) {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        Object[][] attackValues = { { Integer.valueOf(0), Double.valueOf(0.0) } };
        int[] l = { 0 };
        double[] p = { 0.0 };
        double qiong = 1.0;
        double baihu = 1.0;
        double xuanwu = 1.0;
        double zhuque = 1.0;
        DefaultListModel<String> shuxingmodel = new DefaultListModel<>();
        if (loginResult.getJiesuo() != null && loginResult.getJiesuo().contains(mountid + "")) {
            shuxingmodel.addElement("1级 青龙守护属性提升10%");
            shuxingmodel.addElement("2级 白虎守护属性提升10%");
            shuxingmodel.addElement("3级 朱雀守护属性提升10%");
            shuxingmodel.addElement("4级 玄武守护属性提升10%");
            shuxingmodel.addElement("5级 青龙守护属性提升15%");
            shuxingmodel.addElement("6级 白虎守护属性提升15%");
            shuxingmodel.addElement("7级 朱雀守护属性提升15%");
            shuxingmodel.addElement("8级 玄武守护属性提升15%");
            shuxingmodel.addElement("9级 青龙守护属性提升15%");
            shuxingmodel.addElement("10级 白虎守护属性提升15%");
            shuxingmodel.addElement("11级 朱雀守护属性提升15%");
            shuxingmodel.addElement("12级 玄武守护属性提升15%");
            shuxingmodel.addElement("13级 青龙守护属性提升20%");
            shuxingmodel.addElement("14级 白虎守护属性提升20%");
            shuxingmodel.addElement("15级 朱雀守护属性提升20%");
            shuxingmodel.addElement("16级 玄武守护属性提升20%");
            shuxingmodel.addElement("17级 青龙守护属性提升20%");
            shuxingmodel.addElement("18级 白虎守护属性提升20%");
            shuxingmodel.addElement("19级 朱雀守护属性提升20%");
            shuxingmodel.addElement("20级 玄武守护属性提升20%");
            shuxingmodel.addElement("21级 青龙守护属性提升20%");
            shuxingmodel.addElement("22级 白虎守护属性提升20%");
            shuxingmodel.addElement("23级 朱雀守护属性提升20%");
            shuxingmodel.addElement("24级 玄武守护属性提升20%");
            int k = Math.min(Integer.parseInt(loginResult.getZhongtian().split("\\|")[0]), 24);
            int q = 0;
            int b = 0;
            int x = 0;
            int z = 0;
            for (int i = 0; i <= k - 1; ++i) {
                if (((String)shuxingmodel.elementAt(i)).contains("青龙")) {
                    char secondLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 3);
                    char thirdLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 2);
                    q = q + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                }
                else if (((String)shuxingmodel.elementAt(i)).contains("白虎")) {
                    char secondLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 3);
                    char thirdLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 2);
                    b = b + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                }
                if (((String)shuxingmodel.elementAt(i)).contains("朱雀")) {
                    char secondLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 3);
                    char thirdLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 2);
                    z = z + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                }
                if (((String)shuxingmodel.elementAt(i)).contains("玄武")) {
                    char secondLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 3);
                    char thirdLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 2);
                    x = x + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                }
            }
            qiong += (double)q / 100.0;
            baihu += (double)b / 100.0;
            xuanwu += (double)x / 100.0;
            zhuque += (double)z / 100.0;
        }
        shuxingmodel.clear();
        double finalQiong = qiong;
        double finalBaihu = baihu;
        double finalXuanwu = xuanwu;
        double finalZhuque = zhuque;
        UserMessUntil.getMountlsit().forEach(e/* org.come.entity.Mount, */ -> {
            if ((int)e.getMountid() == mountid) {
                switch (e.getSh()) {
                    case 1: {
                        for (int j = 0; j <= ((List<String>)MountShouhuJpanel.shuxingmap.get("青龙")).size() - 1; ++j) {
                            shuxingmodel.addElement(((List<String>)MountShouhuJpanel.shuxingmap.get("青龙")).get(j));
                        }
                        if (loginResult.getQinglong() != null) {
                            attackValues[0] = MountShouhuJpanel.calculateTotalAttackValue(shuxingmodel, Integer.parseInt(loginResult.getQinglong().split("\\|")[0]), value);
                            l[0] = (int)((double)l[0] + (double)(int)attackValues[0][0] * finalQiong);
                            p[0] += (double)attackValues[0][1] * finalQiong;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 2: {
                        for (int m = 0; m <= ((List<String>)MountShouhuJpanel.shuxingmap.get("白虎")).size() - 1; ++m) {
                            shuxingmodel.addElement(((List<String>)MountShouhuJpanel.shuxingmap.get("白虎")).get(m));
                        }
                        if (loginResult.getBaihu() != null) {
                            attackValues[0] = MountShouhuJpanel.calculateTotalAttackValue(shuxingmodel, Integer.parseInt(loginResult.getBaihu().split("\\|")[0]), value);
                            l[0] = (int)((double)l[0] + (double)(int)attackValues[0][0] * finalBaihu);
                            p[0] += (double)attackValues[0][1] * finalBaihu;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 3: {
                        for (int i2 = 0; i2 <= ((List<String>)MountShouhuJpanel.shuxingmap.get("玄武")).size() - 1; ++i2) {
                            shuxingmodel.addElement(((List<String>)MountShouhuJpanel.shuxingmap.get("玄武")).get(i2));
                        }
                        if (loginResult.getXuanwu() != null) {
                            attackValues[0] = MountShouhuJpanel.calculateTotalAttackValue(shuxingmodel, Integer.parseInt(loginResult.getXuanwu().split("\\|")[0]), value);
                            l[0] = (int)((double)l[0] + (double)(int)attackValues[0][0] * finalXuanwu);
                            p[0] += (double)attackValues[0][1] * finalXuanwu;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 4: {
                        for (int i3 = 0; i3 <= ((List<String>)MountShouhuJpanel.shuxingmap.get("朱雀")).size() - 1; ++i3) {
                            shuxingmodel.addElement(((List<String>)MountShouhuJpanel.shuxingmap.get("朱雀")).get(i3));
                        }
                        if (loginResult.getZhuque() != null) {
                            attackValues[0] = MountShouhuJpanel.calculateTotalAttackValue(shuxingmodel, Integer.parseInt(loginResult.getZhuque().split("\\|")[0]), value);
                            l[0] = (int)((double)l[0] + (double)(int)attackValues[0][0] * finalZhuque);
                            p[0] += (double)attackValues[0][1] * finalZhuque;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
            }
            return;
        });
        attackValues[0][0] = Integer.valueOf(l[0]);
        attackValues[0][1] = Double.valueOf(p[0]);
        return attackValues[0];
    }
    
    public static int[] shouhuvalue(int[] pets, RoleSummoning pet) {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        for (int m = 0; m < ZhuJpanel.getListMount().size(); ++m) {
            Mount mount = (Mount)ZhuJpanel.getListMount().get(m);
            if (mount.isID(pet.getSid()) && mount.getSh() != 0) {
                Object[] attackValues = value("气血", (int)mount.getMountid());
                Object[] attackValues2 = value("法力", (int)mount.getMountid());
                Object[] attackValues3 = value("攻击", (int)mount.getMountid());
                Object[] attackValues4 = value("速度", (int)mount.getMountid());
                int n = 0;
                pets[n] += (int)attackValues[0];
                int n2 = 0;
                pets[n2] = (int)((double)pets[n2] + (double)attackValues[1] / 100.0 * (double)pets[0]);
                int n3 = 1;
                pets[n3] += (int)attackValues2[0];
                int n4 = 1;
                pets[n4] = (int)((double)pets[n4] + (double)attackValues2[1] / 100.0 * (double)pets[1]);
                int n5 = 2;
                pets[n5] += (int)attackValues3[0];
                int n6 = 2;
                pets[n6] = (int)((double)pets[n6] + (double)attackValues3[1] / 100.0 * (double)pets[2]);
                int n7 = 3;
                pets[n7] += (int)attackValues4[0];
                int n8 = 3;
                pets[n8] = (int)((double)pets[n8] + (double)attackValues4[1] / 100.0 * (double)pets[3]);
                if (mount.getShouhu() != 0) {
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                    ShouhuPackJpanel.Eqment.forEach(P/* org.come.entity.Goodstable, */ -> {
                        if (P.getRgid().intValue() == mount.getShouhu() && P.getValue() != null && !P.getValue().equals("")) {
                            String[] mes = P.getValue().split("\\|");
                            for (int i = 1; i <= mes.length - 1; ++i) {
                                if (mes[i].split("=")[0].contains("气血")) {
                                    pets[0] = (int)((double)pets[0] + Double.parseDouble(mes[i].split("=")[1]));
                                }
                                else if (mes[i].split("=")[0].contains("魔法")) {
                                    pets[1] = (int)((double)pets[1] + Double.parseDouble(mes[i].split("=")[1]));
                                }
                                else if (mes[i].split("=")[0].contains("攻击")) {
                                    pets[2] = (int)((double)pets[2] + Double.parseDouble(mes[i].split("=")[1]));
                                }
                                else if (mes[i].split("=")[0].contains("速度")) {
                                    pets[3] = (int)((double)pets[3] + Double.parseDouble(mes[i].split("=")[1]));
                                }
                            }
                        }
                        return;
                    });
                }
            }
        }
        return pets;
    }
}
