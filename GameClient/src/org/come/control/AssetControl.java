package org.come.control;

import org.come.Frame.*;
import org.come.Jpanel.WestboundLevelJpanel;
import org.come.MountShouHu.ShouhuPackJframe;
import org.come.XuanBao.RoleXuanBao;
import org.come.bean.*;
import org.come.entity.*;
import org.come.entity.Fly;
import org.come.model.aCard;

import javax.swing.*;

import java.util.List;

import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.*;
import org.skill.panel.SkillSMGatePanel2;
import org.wing.panel.LHMainPanel;
import io.netty.util.internal.StringUtil;
import com.tool.ModerateTask.Hero;
import com.tool.time.TimeLiTXT;
import org.come.Jpanel.ZhuJpanel;
import org.come.model.Lingbao;
import org.come.Jpanel.TestPetJpanel;
import org.come.mouslisten.ChosePetSkillsMouslisten;
import org.come.mouslisten.PetAddPointMouslisten;
import com.tool.pet.PetProperty;
import org.come.summonequip.JframeSummonEquipMain;
import org.skill.frame.SkillMainFrame;
import com.tool.time.TimeLimit;
import org.apache.commons.lang.StringUtils;
import com.tool.image.ImageMixDeal;
import org.come.lianhua.AutoMaticRefiningJframe;
import com.tool.role.RoleProperty;
import org.wing.panel.LHMainFrame;
import com.tool.PanelDisplay.PetPanelShow;
import com.tool.role.RoleLingFa;
import org.come.Jpanel.OpenSkillGridJpanel;

import java.math.BigDecimal;

import com.tool.role.RoleData;
import org.come.xingpan.StarSoulRefinedJpane;
import org.come.xingpan.JpanelXingBackMain;
import org.come.xingpan.StarSoulRefinedJframe;
import org.come.xingpan.JframeXingBackMain;
import com.tool.Stall.AssetUpdate;
import org.come.action.FromServerAction;

public class AssetControl implements FromServerAction {
    private long i;

    public AssetControl() {
        this.i = -111L;
    }

    @Override
    public void controlMessFromServer(String mes, String type) {
        AssetUpdate assetUpdate = (AssetUpdate) GsonUtil.getGsonUtil().getgson().fromJson(mes, AssetUpdate.class);
        if (this.i != -111L && assetUpdate.getI() == this.i) {
            return;
        }
        this.i = assetUpdate.getI();
        asset(assetUpdate);
        if (FormsManagement.getInternalForm2(121) != null) {
            JpanelXingBackMain jpanelXingBackMain = JframeXingBackMain.getJframeSummonEquipMain().getJpanelXingBackMain();
            jpanelXingBackMain.init();
        }
        if (FormsManagement.getInternalForm2(120) != null) {
            StarSoulRefinedJpane starSoulRefinedJpane = StarSoulRefinedJframe.getStarSoulRefinedJframe().getStarSoulRefinedJpane();
            starSoulRefinedJpane.init();
        }
    }

    public static void asset(AssetUpdate assetUpdate) {
        if (assetUpdate == null) {
            return;
        }
        if (assetUpdate.getData() != null) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            String[] vs = assetUpdate.getData().split("\\|");
            if (assetUpdate.getType() == 2255) {
                loginResult.setShouhu(loginResult.getShouhu() + Integer.parseInt(assetUpdate.getData()));
                ZhuFrame.getZhuJpanel().addPrompt2(assetUpdate.getMsg());
                return;
            }
            for (int i = 0; i < vs.length; ++i) {
                if (vs[i].startsWith("Dice")) {
                    String[] p = vs[i].split("=");
                    DiceReidsBase.setTime(p[2]);
                } else if (vs[i].startsWith("D")) {
                    long p2 = Long.parseLong(vs[i].substring(2));
                    loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() + p2));
                } else if (vs[i].startsWith("refGZ")) {
                    if (FormsManagement.getframe(188888).isVisible()) {
                        OpenSkillGridJframe.getOpenSkillGridJframe().getOpenSkillGridJpanel().refreshPetSkills();
                        OpenSkillGridJframe.getOpenSkillGridJframe().getOpenSkillGridJpanel();
                        OpenSkillGridJpanel.showGoodsListModel();
                        OpenSkillGridJframe.getOpenSkillGridJframe().getOpenSkillGridJpanel();
                        OpenSkillGridJpanel.showListModel();
                    }
                } else if (vs[i].startsWith("CUN")) {
                    long p2 = Long.parseLong(vs[i].substring(4));
                    loginResult.setMoneyshop(new BigDecimal(loginResult.getMoneyshop().longValue() + p2));
                } else if (vs[i].startsWith("QU")) {
                    long p2 = Long.parseLong(vs[i].substring(3));
                    loginResult.setMoneyshop(new BigDecimal(loginResult.getMoneyshop().longValue() + p2));
                } else if (vs[i].startsWith("STALL")) {
                    String[] vals = vs[i].split("=");
                    BigDecimal id = new BigDecimal(vals[2]);
                    BigDecimal commodityId = new BigDecimal(vals[3]);
                    int sum = Integer.parseInt(vals[4]);
                    String s = vals[1];
                    int n = -1;
                    switch (s.hashCode()) {
                        case 48: {
                            if (s.equals("0")) {
                                n = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                        case 49: {
                            if (s.equals("1")) {
                                n = 1;
                                break;
                            } else {
                                break;
                            }
                        }
                        case 50: {
                            if (s.equals("2")) {
                                n = 2;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                    switch (n) {
                        case 0: {
                            Goodstable goods = GoodsListFromServerUntil.czgood(id);
                            if (commodityId.compareTo(BigDecimal.ZERO) <= 0) {
                                goods.setCommodityId(null);
                            } else {
                                goods.setCommodityId(commodityId);
                            }
                            goods.setUsetime(Integer.valueOf(sum));
                            if ((int) goods.getUsetime() <= 0) {
                                GoodsListFromServerUntil.Deletebiaoid(goods.getRgid());
                                break;
                            } else {
                                break;
                            }
                        }
                        case 1: {
                            RoleSummoning pet = UserMessUntil.getPetRgid(id);
                            if (commodityId.compareTo(BigDecimal.ZERO) <= 0) {
                                pet.setCommodityId(null);
                            } else {
                                pet.setCommodityId(commodityId);
                            }
                            if (sum <= 0) {
                                UserMessUntil.removePetToRgid(id);
                                break;
                            } else {
                                break;
                            }
                        }
                        case 2: {
                            Lingbao lingbao = RoleLingFa.getRoleLingFa().czGBG(id);
                            if (commodityId.compareTo(BigDecimal.ZERO) <= 0) {
                                lingbao.setCommodityId(null);
                            } else {
                                lingbao.setCommodityId(commodityId);
                            }
                            if (sum <= 0) {
                                RoleLingFa.getRoleLingFa().deletelingToId(id);
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                } else if (vs[i].startsWith("#DelSid")) {
                    String sid = vs[i].substring(8);
                    for (RoleSummoning roleSummoning : UserMessUntil.getPetListTable()) {
                        if (roleSummoning.getSid().toString().equals(sid)) {
                            UserMessUntil.getPetListTable().remove(roleSummoning);
                            UserMessUntil.getDepositPetListTable().add(roleSummoning);
                            DepositListJframe.getDepositListJframe().getDepositListJpanel().init(RoleData.getRoleData().getDepositBbName());
                            break;
                        }
                    }
                    if (FormsManagement.getframe(6).isVisible()) {
                        PetPanelShow.Show1();
                        break;
                    } else {
                        break;
                    }
                } else if (vs[i].startsWith("LHLHVALUE")) {
                    String v = vs[i].replace("LHLHVALUE=", "");
                    RoleData.getRoleData().getLoginResult().setLianghaoValue(v);
                    if (FormsManagement.getframe(866).isVisible()) {
                        LHMainPanel lhMainPanel = LHMainFrame.LHMainFrame().getLhMainPanel();
                        String[] split = v.split("@");
                        lhMainPanel.changeChooseWingGoods(null, Integer.parseInt(split[0]));
                    }
                    RoleProperty.getRoleProperty().equipWearOff();
                    QualityClBean qualityClBean = new QualityClBean();
                    qualityClBean.setNewAttr(RoleData.getRoleData().getLoginResult().getLianghaoValue());
                    AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel().detectionProperties(qualityClBean);
                    return;
                } else if (vs[i].startsWith("#retrieve")) {
                    String sid = vs[i].substring(10);
                    for (RoleSummoning roleSummoning : UserMessUntil.getDepositPetListTable()) {
                        if (roleSummoning.getSid().toString().equals(sid)) {
                            UserMessUntil.getPetListTable().add(roleSummoning);
                            UserMessUntil.getDepositPetListTable().remove(roleSummoning);
                            DepositListJframe.getDepositListJframe().getDepositListJpanel().init(RoleData.getRoleData().getDepositBbName());
                            break;
                        }
                    }
                    PetPanelShow.Show1();
                    break;
                } else if (vs[i].startsWith("XYDJ")) {
                    String sendMes = Agreement.getFiveMsgAgreement("CC" + 10);
                    SendMessageUntil.toServer(sendMes);
                } else if (vs[i].startsWith("X")) {
                    long p2 = Long.parseLong(vs[i].substring(2));
                    loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() + p2));
                    GoodDetailedJframe.getGoodDetailedJframe().getGoodDetailedJpanel().getYonghuXianyu().setText(loginResult.getCodecard() + "");
                    TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getJadeNum().setText(loginResult.getCodecard() + "");
                } else if (vs[i].startsWith("SKILL")) {
                    String msgVal = vs[i].substring(5);
                    String[] msgVals = msgVal.split("=");
                    int addsld = Integer.parseInt(msgVals[1]);
                    PrivateData data = RoleData.getRoleData().getPrivateData();
                    int maxsld = AnalysisString.shuliandu((int) ImageMixDeal.userimg.getRoleShow().getGrade());
                    String killid = msgVals[0];
                    String[] vss = data.getSkill("S");
                    StringBuffer sb = new StringBuffer();
                    if (vss != null && vss.length > 0) {
                        for (String kv : vss) {
                            String[] vs2 = kv.split("_");
                            Integer id2 = Integer.valueOf(Integer.parseInt(vs2[0]));
                            Integer val = Integer.valueOf(Integer.parseInt(vs2[1]));
                            if ((int) id2 == Integer.parseInt(killid)) {
                                if ((int) val >= maxsld) {
                                    sb.append(killid);
                                    sb.append("_" + String.valueOf(val) + "#");
                                } else if ((int) val + addsld >= maxsld) {
                                    sb.append(killid);
                                    sb.append("_" + String.valueOf(maxsld) + "#");
                                    int n3 = maxsld - (int) val;
                                } else {
                                    sb.append(killid);
                                    sb.append("_" + String.valueOf((int) val + addsld) + "#");
                                }
                            } else {
                                sb.append(kv + "#");
                            }
                        }
                    }
                    if (sb.toString().endsWith("#")) {
                        data.setSkills("S", sb.toString().substring(0, sb.toString().length() - 1));
                    } else {
                        data.setSkills("S", sb.toString());
                    }
                    SendRoleAndRolesummingUntil.sendRole(data);
                } else if (vs[i].startsWith("S")) {
                    long p2 = Long.parseLong(vs[i].substring(2));
                    loginResult.setSavegold(new BigDecimal(loginResult.getSavegold().longValue() + p2));
                } else if (vs[i].startsWith("C")) {
                    long p2 = Long.parseLong(vs[i].substring(2));
                    loginResult.setMoney(Integer.valueOf((int) ((long) (int) loginResult.getMoney() + p2)));
                    GoodDetailedJframe.getGoodDetailedJframe().getGoodDetailedJpanel().getYonghuXianyu().setText(loginResult.getMoney() + "");
                    TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getjfNum().setText(loginResult.getMoney() + "");
                } else if (vs[i].startsWith("Z")) {
                    long p2 = Long.parseLong(vs[i].substring(2));
                    loginResult.setTransfergold(new BigDecimal(loginResult.getTransfergold().longValue() + p2));
                    TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getzqbNum().setText(loginResult.getTransfergold() + "");
                    GoodDetailedJframe.getGoodDetailedJframe().getGoodDetailedJpanel().getYonghuXianyu().setText(loginResult.getTransfergold() + "");
                } else if (vs[i].startsWith("R")) {
                    ExpIncreaseUntil.RoleExp(loginResult, vs[i]);
                } else if (vs[i].startsWith("PEP")) {
                    ExpIncreaseUntil.PetExtPoint(vs[i]);
                } else if (vs[i].startsWith("P")) {
                    ExpIncreaseUntil.PetExp(vs[i]);
                } else if (vs[i].startsWith("M")) {
                    ExpIncreaseUntil.MountExp(vs[i]);
                } else if (vs[i].startsWith("LH")) {
                    String[] upMsg = vs[i].split("=");
                    if (upMsg.length > 1 && StringUtils.isNotEmpty(upMsg[1])) {
                        LiangHaoInfo liangHaoInfo = (LiangHaoInfo) GsonUtil.getGsonUtil().getgson().fromJson(upMsg[1], LiangHaoInfo.class);
                        loginResult.setLiangHao(liangHaoInfo.getLianghao());
                        if (loginResult.getLianghaotype() == null || (int) loginResult.getLianghaotype() <= 0) {
                            loginResult.setLianghaotype(Integer.valueOf(liangHaoInfo.getType()));
                        }
                        loginResult.setLianghaoexpire(liangHaoInfo.getExpTime());
                        loginResult.setContinueprice(liangHaoInfo.getContinueprice());
                        Teststatejframe.getTeststatejframe().getTeststateJpanel().getLabappeleid().setText("");
                    }
                } else if (vs[i].startsWith("L")) {
                    ExpIncreaseUntil.LingExp(vs[i]);
                } else if (vs[i].startsWith("TTJF")) {
                    String p3 = assetUpdate.getData().substring(5);
                    loginResult.setScore(p3);
                } else if (vs[i].equals("baoshi")) {//修复世界装备显示宝石
                    for (int k = 0; k <= assetUpdate.getGoods().size() - 1; ++k) {
                        GoodsListFromServerUntil.fushis.remove(((Goodstable) assetUpdate.getGoods().get(k)).getRgid());
                        GoodsListFromServerUntil.fushis.put(((Goodstable) assetUpdate.getGoods().get(k)).getRgid(), assetUpdate.getGoods().get(k));
                    }
                    return;
                } else if (vs[i].startsWith("G")) {
                    String[] vss2 = vs[i].split("=");
                    if (vss2.length == 2) {
                        BigDecimal id = new BigDecimal(vss2[0].substring(1));
                        int sum2 = Integer.parseInt(vss2[1]);
                        GoodsListFromServerUntil.addGood(id, sum2);
                    } else if (vss2.length == 4) {
                        ExpIncreaseUntil.NeiDanExp(vss2);
                    }
                } else if (vs[i].startsWith("T")) {
                    MsgJframe.getJframe().getJapnel().texiao(vs[i].substring(1));
                } else if (vs[i].startsWith("B")) {
                    long p2 = Long.parseLong(vs[i].substring(2));
                    loginResult.setContribution(new BigDecimal(loginResult.getContribution().longValue() + p2));
                } else if (vs[i].startsWith("K")) {
                    long p2 = Long.parseLong(vs[i].substring(2));
                    loginResult.setPkrecord(new BigDecimal(loginResult.getPkrecord().longValue() + p2));
                } else if (vs[i].startsWith("EP")) {
                    ExpIncreaseUntil.RoleExpPoint(loginResult, vs[i]);
                } else if (vs[i].startsWith("E")) {
                    String[] vss2 = vs[i].split("=");
                    loginResult.setFGExtraPoint(vss2[0] = vss2[0].substring(1), Integer.parseInt(vss2[1]));
                    if (FormsManagement.getInternalForm3(54) != null) {
                        FactionAngelJframe.getFactionAngelJframe().getFactionAngelJpanel().showMenuMessage();
                    }
                    if (FormsManagement.getInternalForm3(106) != null) {
                        FactionAngelPracticeJframe.getFactionAngelPracticeJframe().getFactionAngelPracticeJpanel().showMessage();
                    }
                } else if (vs[i].startsWith("N")) {
                    String[] vss2 = vs[i].split("=");
                    BigDecimal id = new BigDecimal(vss2[0].substring(5));
                    if (vss2[0].startsWith("NSKIN")) {
                        NPCJfram.getNpcJfram().getNpcjpanel().whetherChange(id);
                    } else if (vss2[0].startsWith("NBASE")) {
                        int sum2 = Integer.parseInt(vss2[1]);
                        NPCJfram.getNpcJfram().getNpcjpanel().selectValue(id, sum2);
                    }
                } else if (vs[i].startsWith("偷钱")) {
                    String[] vss2 = vs[i].split("=");
                    long p4 = Long.parseLong(vss2[1]);
                    NPCJfram.getNpcJfram().getNpcjpanel().taskend("和你开个小小的玩笑而已,何必动怒,这份礼物送你,你得到了#R" + p4 + "#W金钱");
                } else if (vs[i].startsWith("召唤兽碎片")) {//add@magor
                    String[] vss = vs[i].split("=");
                    loginResult.setPetdebris(UserData.Splice(loginResult.getPetdebris(), vss[1] + "=" + vss[2], Integer.parseInt(vss[3])));
                } else if (vs[i].startsWith("功绩千秋")) {
                    String[] vss = vs[i].split("=");
                    loginResult.setAchieveRecord(UserData.Splice(loginResult.getAchieveRecord(), vss[1] + "=" + vss[2], Integer.parseInt(vss[3])));
                } else if (vs[i].startsWith("日常任务抽奖")) {
                    String[] vss = vs[i].split("=");
                    loginResult.setDayDraw(UserData.Splice(loginResult.getDayDraw(), vss[1] + "=" + vss[2], Integer.parseInt(vss[3])));
                } else if (vs[i].startsWith("击杀")) {
                    loginResult.setKill(UserData.Splice(loginResult.getKill(), vs[i], 5));
                } else if (vs[i].startsWith("支援")) {
                    BigDecimal zy = loginResult.getScoretype("支援");
                    if (zy.intValue() == 0) {
                        loginResult.setScore(UserData.Splice(loginResult.getScore(), vs[i], 2));
                    }
                    loginResult.setScore(UserData.Splice(loginResult.getScore(), vs[i], 1));
                } else if (vs[i].startsWith("首发")) {
                    BigDecimal zy = loginResult.getScoretype("首发");
                    if (zy.intValue() == 0) {
                        loginResult.setScore(UserData.Splice(loginResult.getScore(), vs[i], 2));
                    }
                    loginResult.setScore(UserData.Splice(loginResult.getScore(), vs[i], 1));
                } else if (vs[i].startsWith("灵宝支援")) {
                    BigDecimal zy = loginResult.getScoretype("灵宝支援");
                    if (zy.intValue() == 0) {
                        loginResult.setScore(UserData.Splice(loginResult.getScore(), vs[i], 2));
                    }
                    loginResult.setScore(UserData.Splice(loginResult.getScore(), vs[i], 1));
                } else if (vs[i].startsWith("法门选定")) {
                    BigDecimal scoretype = loginResult.getScoretype("法门选定");
                    if (scoretype.intValue() == 0) {
                        loginResult.setScore(UserData.Splice(loginResult.getScore(), "法门选定=1", 2));
                    }
                    loginResult.setScore(UserData.Splice(loginResult.getScore(), vs[i], 1));
                } else if (vs[i].startsWith("光武颜色")) {
                    BigDecimal scoretype = loginResult.getScoretype("光武颜色");
                    if (scoretype.intValue() == 0) {
                        loginResult.setScore(UserData.Splice(loginResult.getScore(), "光武颜色=1", 2));
                    }
                    loginResult.setScore(UserData.Splice(loginResult.getScore(), vs[i], 1));
                    TimeLimit.getLimits().gwChangeSkin();
                } else {
                    loginResult.setScore(UserData.Splice(loginResult.getScore(), vs[i], 2));
                    if (vs[i].startsWith("法门")) {
                        if (SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getDivisionGatePanel2().isVisible()) {
                            BigDecimal scoretype = loginResult.getScoretype("法门选定");
                            SkillSMGatePanel2.FaMenItemView faMenItemView = SkillMainFrame.getSkillMainFrame().getSkillMainPanel().getDivisionGatePanel2().getFaMenItemViews()[scoretype.intValue() - 1];
                            faMenItemView.refreshSkillSld();
                        }
                    } else if (vs[i].startsWith("比斗奖章")) {
                        try {
                            if (JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().isVisible() && (int) JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getType() == 3) {
                                JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().changeMenuView((int) JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getType());
                                JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().setScoretype(new BigDecimal(RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章").toString()));
                            }
                        } catch (Exception ex) {
                        }
                    } else if (vs[i].startsWith("解卦灵力")) {
                        DuiHuanLingLiJframe lingLiJframe = DuiHuanLingLiJframe.getDuiHuanLingLiJframe();
                        if (lingLiJframe.isVisible()) {
                            lingLiJframe.getDuiHuanLingLiJpanel().updateBalance();
                        }
                    }
                }
            }
        }
        if (assetUpdate.getVip() != null) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            loginResult.setVipget(assetUpdate.getVip());
        }
        if (assetUpdate.getSceneMsg() != null) {
            ImageMixDeal.upScene(assetUpdate.getSceneMsg());
        }
        if (assetUpdate.getDifficultLevel() != null) {
            if (assetUpdate.getDifficultLevel().equals("重置")) {
                RoleData.getRoleData().getLoginResult().setDifficultLevel(0);
            } else {
                FormsManagement.showForm(3079);
                RoleData.getRoleData().getLoginResult().setDifficultLevel(Integer.parseInt(assetUpdate.getDifficultLevel()));
                WestboundLevelJpanel.updateWH();
                WestboundLevelJpanel.updateDW();
            }
        }
        if (assetUpdate.getDifficultrecord() != null) {
            if (assetUpdate.getDifficultrecord().equals("重置")) {
                RoleData.getRoleData().getLoginResult().setDifficultrecord("");
            } else {
                RoleData.getRoleData().getLoginResult().setDifficultrecord(assetUpdate.getDifficultrecord());
            }
        }

//充值积分抽奖
        List<Goodstable> goods2 = assetUpdate.getGoods();
        if (goods2 != null) {
            for (int j = 0; j < goods2.size(); ++j) {
                if (assetUpdate.getType() == 888) {
                    DianKaJiaoYijframe.getDianKaJiaoYijframe().getDianKaJiaoYiJpanel().getCardJpanel().getChoujiangJpanel().setJPGoods(goods2.get(j));
                }
                GoodsListFromServerUntil.addGood(goods2.get(j));
                if ((goods2.get(j).getType() == 511L || goods2.get(j).getType() == 510L || goods2.get(j).getType() == 512L) && AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel().getDisplaymodetext().getText().equals("兽装重洗属性") && AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel().getStart().getText().equals("停止")) {
                    AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel().petShidetectionPropertiesAttr(goods2.get(j));
                }
                if (assetUpdate.getData() != null && assetUpdate.getData().contains("TTJF")) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你获得了#R" + goods2.get(j).getGoodsname() + "#89#89");
                }
            }
        }
        boolean iiisss = false;
        if (assetUpdate.getGoods() != null && assetUpdate.getGoods().size() > 0 && assetUpdate.getGoods().get(0) != null && assetUpdate.getGoods().get(0).getType() == 520 && assetUpdate.getMsg() != null && assetUpdate.getMsg().equals("魂归成功")) {
            iiisss = true;
        }
        List<Goodstable> goods = assetUpdate.getGoods();
        if (goods != null && !iiisss) {
            for (int i = 0; i < goods.size(); i++) {
                GoodsListFromServerUntil.addGood(goods.get(i));
                if ((goods.get(i).getType() == 511 || goods.get(i).getType() == 510 || goods.get(i).getType() == 512)
                        && AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel().getDisplaymodetext().getText().equals("兽装重洗属性")
                        && AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel().getStart().getText().equals("停止")) {
                    AutoMaticRefiningJframe.getAssistantJframe().getAssistantJpanel().petShidetectionPropertiesAttr(goods.get(i));
                }
                boolean sb = Goodtype.GodEquipment_God(goods.get(i).getType());
                boolean zhzi = goods.get(i).getType() == 2080;
                if ((zhzi || sb) && (assetUpdate.getType() == 2 || assetUpdate.getType() == 8 || assetUpdate.getType() == 25 || assetUpdate.getType() == 22)) {
                    FormsManagement.showForm(3007);
                    //                  WeaponGodJpanel.goods = goods.get(i);
                    //                 WeaponGodJpanel.labGoodsImg.setIcon(CutButtonImage.getImage("img/item/" + goods.get(i).getSkin() + ".png", 90,90));
                    //                 WeaponGodJpanel.labGoodsImg.setVisible(true);
                    //                  WeaponGodJpanel.goodsname.setText(goods.get(i).getGoodsname());
                    //                  WeaponGodJpanel.goodsname.setVisible(true);
                    WeaponGodJframe.getWeaponGodJpanel().x = 466 / 2;
                    WeaponGodJframe.getWeaponGodJpanel().w = 0;
                    WeaponGodJframe.getWeaponGodJpanel().himg = 110;
                    WeaponGodJframe.getWeaponGodJpanel().wimg = 110;
                    WeaponGodJframe.getWeaponGodJpanel().yimg = 10;
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (WeaponGodJframe.getWeaponGodJpanel().himg > 80) {
                                    if (WeaponGodJframe.getWeaponGodJpanel().yimg < 40) {
                                        WeaponGodJframe.getWeaponGodJpanel().yimg = WeaponGodJframe.getWeaponGodJpanel().yimg + 1;
                                    }
                                    WeaponGodJframe.getWeaponGodJpanel().himg = WeaponGodJframe.getWeaponGodJpanel().himg - 1;
                                    WeaponGodJframe.getWeaponGodJpanel().wimg = WeaponGodJframe.getWeaponGodJpanel().wimg - 1;
                                    Thread.sleep(3);
                                }
                                Thread.sleep(500);
                                while (WeaponGodJframe.getWeaponGodJpanel().x > 0) {
                                    WeaponGodJframe.getWeaponGodJpanel().x = WeaponGodJframe.getWeaponGodJpanel().x - 3;
                                    WeaponGodJframe.getWeaponGodJpanel().w = WeaponGodJframe.getWeaponGodJpanel().w + 6;
                                    Thread.sleep(2);
                                }
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();


                    WeaponGodJframe.getWeaponGodJpanel().goods = goods.get(i);
                    WeaponGodJframe.getWeaponGodJpanel().labGoodsImg.setIcon(null);
                    WeaponGodJframe.getWeaponGodJpanel().labGoodsImg.setVisible(true);
                    WeaponGodJframe.getWeaponGodJpanel().iconGoods = new ImageIcon("img/item/" + goods.get(i).getSkin() + ".png");
                    WeaponGodJframe.getWeaponGodJpanel().goodsname.setText(goods.get(i).getGoodsname());
                    WeaponGodJframe.getWeaponGodJpanel().goodsname.setVisible(true);
                }

                if ((assetUpdate.getType() == 2 || assetUpdate.getType() == 8 || assetUpdate.getType() == 25 || assetUpdate.getType() == 22) && goods.get(i).getQuality() >= 0) {
                    ZhuFrame.getZhuJpanel().setObtainGod(new ImageIcon("img/item/" + goods.get(i).getSkin() + ".png"));
                    ZhuFrame.getZhuJpanel().dhb = true;
                    ZhuFrame.getZhuJpanel().initDisplayTimer();
                }

                if (assetUpdate.getData() != null && assetUpdate.getData().contains("TTJF")) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你获得了#R" + goods.get(i).getGoodsname() + "#89#89");
                }
                if (goods.get(i).getType() == 2255) {
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().add(goods.get(i));
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().updata();
                }
            }
        }
        LoginResult loginResult2 = RoleData.getRoleData().getLoginResult();

        //  LoginResult loginResult2 = RoleData.getRoleData().getLoginResult();

        //添加召唤兽
        List<RoleSummoning> pets = assetUpdate.getPets();
        if (pets != null && pets.size() != 0) {
            boolean is = false;
            for (int i = 0; i < pets.size(); i++) {
                RoleSummoning pet = pets.get(i);
                if (pet == null) {
                    break;
                }
                if (pet.getRoleid() != null && pet.getBasishp() == 0L) {
                    int[] petvalue = PetProperty.getPetHMASp(pet);
                    pet.setBasishp(petvalue[0]);
                    pet.setBasismp(petvalue[1]);
                }
                int j = 0;
                while (true) {
                    if (j < UserMessUntil.getPetListTable().size()) {
                        RoleSummoning pet2 = UserMessUntil.getPetListTable().get(j);
                        if (pet.getSid().compareTo(pet2.getSid()) == 0) {
                            if (pet.getRoleid() != null && pet.getRoleid().compareTo(loginResult2.getRole_id()) == 0) {
                                UserMessUntil.getPetListTable().set(j, pet);
                            } else {
                                is = true;
                                UserMessUntil.getPetListTable().remove(j);
                            }
                            if (UserMessUntil.getChosePetMes() != null && UserMessUntil.getChosePetMes().getSid().compareTo(pet.getSid()) == 0) {
                                if (pet.getRoleid() != null && pet.getRoleid().compareTo(loginResult2.getRole_id()) == 0) {
                                    UserMessUntil.setChosePetMes(pet);
                                    PetAddPointMouslisten.showPetValue();
                                    if (FormsManagement.getframe(18).isVisible())
                                        ChosePetSkillsMouslisten.refreshPetSkills();
                                    break;
                                }
                                UserMessUntil.setChosePetMes(null);
                            }
                            break;
                        }
                        j++;
                        continue;
                    }
                    is = true;
                    UserMessUntil.getPetListTable().add(pets.get(i));
                    break;
                }
            }
            if (is) {
                TestPetJpanel.showListModel(UserMessUntil.getPetListTable(), RoleData.getRoleData().getLoginResult().getSummoning_id());
            }
            if (FormsManagement.getframe(188888).isVisible()) {
                OpenSkillGridJframe.getOpenSkillGridJframe().getOpenSkillGridJpanel().refreshPetSkills();
                OpenSkillGridJframe.getOpenSkillGridJframe().getOpenSkillGridJpanel();
                OpenSkillGridJpanel.showGoodsListModel();
                OpenSkillGridJframe.getOpenSkillGridJframe().getOpenSkillGridJpanel();
                OpenSkillGridJpanel.showListModel();
            }
        }
        List<Lingbao> lingbaos = assetUpdate.getLingbaos();
        if (lingbaos != null)
            for (int i = 0; i < lingbaos.size(); i++)
                RoleLingFa.getRoleLingFa().addlingfa(lingbaos.get(i));
        List<Mount> mounts = assetUpdate.getMounts();
        if (mounts != null)
            for (int i = 0; i < mounts.size(); i++) {
                Mount mount = mounts.get(i);
                int j = 0;
                while (true) {
                    if (j < ZhuJpanel.getListMount().size()) {
                        Mount mount2 = ZhuJpanel.getListMount().get(j);
                        if (mount.getMid().compareTo(mount2.getMid()) == 0) {
                            ZhuJpanel.getListMount().set(j, mount);
                            ExpIncreaseUntil.showMountValue(mount);
                            break;
                        }
                        j++;
                        continue;
                    }
                    ZhuJpanel.getListMount().add(mount);
                    break;
                }
            }
        List<Car> cars = assetUpdate.getCars();
        if (cars != null) {
            for (int i = 0; i < cars.size(); i++) {
                Car car = cars.get(i);
                int j = 0;
                while (true) {
                    if (j < ZhuJpanel.getListCar().size()) {
                        Car mount2 = ZhuJpanel.getListCar().get(j);
                        if (car.getMid().compareTo(mount2.getMid()) == 0) {
                            ZhuJpanel.getListCar().set(j, car);
                            ExpIncreaseUntil.showCarValue(car);
                            break;
                        }
                        j++;
                        continue;
                    }
                    ZhuJpanel.getListCar().add(car);
                    break;
                }
            }
        }

        // 添加的灵宝
        List<XuanBao> xuanbaos = assetUpdate.getXuanBaos();
        if (xuanbaos != null) {
            for (int i = 0; i < xuanbaos.size(); ++i) {
                RoleXuanBao.getRoleXuanBao().addlingfa(xuanbaos.get(i));
            }
        }
        List<Fly> flys = (List) assetUpdate.getFlys();
        if (flys != null)
            for (int i = 0; i < flys.size(); i++) {
                Fly fly = flys.get(i);
                int j = 0;
                while (true) {
                    if (j < ZhuJpanel.getListFly().size()) {
                        Fly fly2 = ZhuJpanel.getListFly().get(j);
                        if (fly.getMid().compareTo(fly2.getMid()) == 0) {
                            ZhuJpanel.getListFly().set(j, fly);
                            ExpIncreaseUntil.ShouFlyValue(fly);
                            break;
                        }
                        j++;
                        continue;
                    }
                    ZhuJpanel.getListFly().add(fly);
                    break;
                }
            }
        List<Baby> babys = assetUpdate.getBabys();
        if (babys != null) {
            boolean is = false;
            BigDecimal big = RoleData.getRoleData().getLoginResult().getBabyId();
            if (big == null)
                big = new BigDecimal(-1);
            for (int i = 0; i < babys.size(); i++) {
                Baby baby = babys.get(i);
                int j = 0;
                while (true) {
                    if (j < UserMessUntil.getMyListBaby().size()) {
                        Baby baby2 = UserMessUntil.getMyListBaby().get(j);
                        if (baby.getBabyid().compareTo(baby2.getBabyid()) == 0) {
                            UserMessUntil.getMyListBaby().set(j, baby);
                            if (big.compareTo(baby.getBabyid()) == 0)
                                RoleProperty.ResetBaby(baby);
                            if (FormsManagement.getframe(1).isVisible())
                                TestChildJframe.getTestChildJframe().getTestChildJpanel().ShowBaby(baby);
                            break;
                        }
                        j++;
                        continue;
                    }
                    is = true;
                    UserMessUntil.getMyListBaby().add(baby);
                    break;
                }
            }
            if (is)
                BabyControl.babyinit(UserMessUntil.getMyListBaby());
        }
        List<PartJade> jades = assetUpdate.getJades();
        if (jades != null) {
            for (int i5 = 0; i5 < jades.size(); ++i5) {
                PartJade jade = (PartJade) jades.get(i5);
                RoleData.getRoleData().getPackRecord().setPartJade(jade);
                AccessSuitMsgUntil.refreshJadeShow(jade);
            }
        }
        List<Pal> pals = assetUpdate.getPals();
        if (pals != null) {
            for (int i4 = 0; i4 < pals.size(); ++i4) {
                RoleData.getRoleData().addPal((Pal) pals.get(i4));
            }
            if (FormsManagement.getInternalForm2(105) != null && FormsManagement.getframe(105).isVisible()) {
                PartnerJframe.getPartnerJframe().getPartnerMainJpanel().refreshPals(pals);
            }
        }
        if (assetUpdate.getUseCard() != null) {
            UseCardBean cardBean = assetUpdate.getUseCard();
            TimeLimit.getLimits().addLimit(cardBean.getName(), cardBean.getType(), cardBean.getSkin(), cardBean.getValue(), cardBean.getTime());
            TimeLiTXT.getTimeLiTXT().addLimit(cardBean.getName(), cardBean.getType(), cardBean.getSkin(), cardBean.getValue(), cardBean.getTime());
            if (cardBean.getlCard() != null) {
                RoleData.getRoleData().getPackRecord().setlCard(cardBean.getlCard());
                String[] split2 = cardBean.getlCard().split("\\|");
                JLabel[] commonLab = SeventyTwoChangesJframe.getSeventyTwoChangesJframe().getSeventyTwoChangesJpanel().getCommonLab();
                for (int i6 = 0; i6 < commonLab.length; ++i6) {
                    if (i6 + 1 <= split2.length) {
                        aCard card = UserMessUntil.getACard((int) Integer.decode(split2[i6]));
                        if (card == null) {
                            commonLab[i6].setIcon(null);
                            commonLab[i6].setName(null);
                        } else {
                            commonLab[i6].setIcon(CutButtonImage.getImage("img/head/" + card.getSkin() + ".png", 50, 50));
                            commonLab[i6].setName(split2[i6]);
                        }
                    } else {
                        commonLab[i6].setIcon(null);
                        commonLab[i6].setName(null);
                    }
                }
            }
        }
        if (assetUpdate.getResistance() != null) {
            RoleData.getRoleData().getLoginResult().setResistance(assetUpdate.getResistance().equals("") ? null : assetUpdate.getResistance());
            RoleProperty.ResetBp();
            FactionAngelJframe.getFactionAngelJframe().getFactionAngelJpanel().showMenuMessage();
        }
        if (assetUpdate.getTask() != null) {
            Hero.getHero().addTask(assetUpdate.getTask());
        }
        if (assetUpdate.getType() >= 21 && assetUpdate.getType() < 30) {
            String msg = assetUpdate.getMsg();
            if (msg != null) {
                String[] vs3 = msg.split("\\|");
                for (int i7 = 0; i7 < vs3.length; ++i7) {
                    if (!StringUtil.isNullOrEmpty(vs3[i7])) {
                        if (vs3[i7].equals("重悟技能失败")) {
                            JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getAckBtn().nochoose(null);
                        }
                        ZhuFrame.getZhuJpanel().addPrompt2(vs3[i7]);
                    }
                }
            }
            if (assetUpdate.getType() == 26) {
                TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getVipShopJpanel().refreshPanel();
            } else if (assetUpdate.getType() == 27) {
                EverydayRechargeJframe.getEverydayRechargeJframe().getEverydayRechargeJpanel().refreshPanel();
            } else if (assetUpdate.getType() == 28) {
                ContinuousRechargeJframe.getContinuousRechargeJframe().getContinuousRechargeJpanel().refreshPanel();
            }
        } else if (assetUpdate.getType() == 30) {
            String msg = assetUpdate.getMsg();
            if (msg != null) {
                String[] vs3 = msg.split("\\|");
                for (int i7 = 0; i7 < vs3.length; ++i7) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你获得了" + vs3[i7]);
                }
            }
            ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().refreshPanel();
        } else {
            String msg = assetUpdate.msg();
            if (msg != null) {
                ZhuFrame.getZhuJpanel().addPrompt2(msg);
            }
        }
        if (assetUpdate.getTtVictory() != null) {
            loginResult2.setTtVictory(assetUpdate.getTtVictory());
        }
        if (assetUpdate.getTtFail() != null) {
            loginResult2.setTtFail(assetUpdate.getTtFail());
        }
    }
}
