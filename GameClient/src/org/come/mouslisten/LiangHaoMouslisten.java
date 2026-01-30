package org.come.mouslisten;

import java.util.ArrayList;
import org.come.Jpanel.AircraftJPanel;
import org.come.bean.LoginResult;
import org.come.bean.RoleShow;
import org.come.until.LotteryFromServerUntil;
import org.skill.panel.SkillSMGatePanel2;
import com.tool.time.TimeLimit;
import org.come.entity.Fly;
import org.come.bean.LaborRank;
import com.tool.image.ImageMixDeal;
import com.tool.time.Limit;
import org.come.Frame.QuackGameJframe;
import com.tool.pet.PetProperty;
import org.come.Frame.MountSkillsJframe;
import com.tool.btn.MountPanelBtn;
import org.come.entity.MountSkill;
import org.come.entity.Mount;
import org.come.Frame.MountJframe;
import java.util.Map;
import org.come.bean.ConfigureBean;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.entity.RoleSummoning;
import org.come.until.AnalysisString;
import org.come.until.SendRoleAndRolesummingUntil;
import org.come.until.UserMessUntil;
import org.come.until.GsonUtil;
import org.come.until.DevicGoodsEquiptmentUntil;
import org.come.entity.Pal;
import org.come.Jpanel.PartnerMainJpanel;
import org.come.model.Lingbao;
import org.come.Frame.PartnerJframe;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import com.tool.role.RoleLingFa;
import org.come.Frame.NPCJfram;
import org.lottery.frame.LotteryMainFrame;
import org.come.good.PetEquip;
import org.come.good.TaskGood;
import org.come.good.BabyGood;
import org.come.Frame.ExchangeAwardJframe;
import org.come.good.Lingbaogood;
import org.come.good.Flight;
import org.come.good.Consumptions;
import org.come.good.Box;
import org.come.until.Music;
import org.come.socket.SendMessageUntil;
import com.tool.role.RoleData;
import org.come.socket.Agreement;
import org.come.good.Medicine;
import org.come.until.Goodtype;
import javax.swing.JTextField;
import org.come.entity.Goodstable;
import org.come.until.EquipTool;
import org.come.Jpanel.ZhuJpanel;
import org.come.until.Util;
import org.come.until.FormsManagement;
import org.come.Frame.TestpackJframe;
import com.tool.imagemonitor.FightingMonitor;
import come.tool.Fighting.FightingMixDeal;
import org.come.until.MessagrFlagUntil;
import javax.swing.text.BadLocationException;
import com.tool.Document.RichDocument;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import org.come.bean.GoodsResultArrBean;
import java.util.List;
import org.come.Frame.AthChartJframe;
import java.awt.event.MouseListener;

public class LiangHaoMouslisten implements MouseListener
{
    private int goodPlace;
    public static int replace;
    public static int replacepath;
    AthChartJframe athChartJframe;
    public static List<String> list;
    public static GoodsResultArrBean goodarr;
    
    public LiangHaoMouslisten(int goodPlace) {
        this.goodPlace = goodPlace;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        LiangHaoMouslisten.list.add(this.goodPlace + "");
        Goodstable goodstable = GoodsListFromServerUntil.Getgood(this.goodPlace);
        if (e.isShiftDown() && e.getButton() == 1) {
            if (goodstable != null) {
                try {
                    JTextField SendMes = ZhuFrame.getZhuJpanel().getSendMes();
                    ((RichDocument)SendMes.getDocument()).insertRich(SendMes.getCaretPosition(), 2, goodstable.getRgid(), "[" + goodstable.getGoodsname() + "]", "G", null);
                }
                catch (BadLocationException e2) {
                    e2.printStackTrace();
                }
            }
            return;
        }
        else {
            if (e.getButton() == 3) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE10) || MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE11)) {
                    return;
                }
                if (LiangHaoMouslisten.replace != -1) {
                    goodreplace(-1, -1);
                }
                else if (goodstable != null) {
                    long type = (long)goodstable.getType();
                    if (FightingMixDeal.State != 0) {
                        if (!GoodsMouslisten.isFightingUse(type)) {
                            return;
                        }
                        int man = -1;
                        if (FightingMixDeal.State == 1) {
                            man = FightingMixDeal.myman();
                        }
                        else if (FightingMixDeal.State == 2) {
                            man = FightingMixDeal.myman() + 5;
                        }
                        int path = FightingMixDeal.CurrentData(FightingMixDeal.camp, man);
                        if (path != -1) {
                            FightingMonitor.mousesname = goodstable.getRgid() + "";
                            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE9);
                            FightingMonitor.mousestate = 2;
                            ZhuFrame.getZhuJpanel().HideBeastBtn();
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt("当前状态无法使用药品");
                        }
                        TestpackJframe.getTestpackJframe().getJpac().ClearText();
                        ZhuFrame.getZhuJpanel().cleargoodtext();
                        FormsManagement.HideForm(2);
                    }
                    else {
                        if (Util.isM()) {
                            return;
                        }
                        if (ZhuJpanel.getUseGoodsType() == 0) {
                            try {
                                this.ManNoCombat(goodstable, type);
                            }
                            catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        else if (ZhuJpanel.getUseGoodsType() == 1) {
                            this.petUseGoods(goodstable, type);
                        }
                        else if (ZhuJpanel.getUseGoodsType() == 2) {
                            this.mountUseGoods(goodstable, type);
                        }
                    }
                }
            }
            else if (e.getButton() == 1) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE10)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    if (goodstable != null) {
                        if (EquipTool.isEquip(goodstable.getType())) {
                            if (goodstable.getGoodlock() == 1) {
                                ZhuFrame.getZhuJpanel().addPrompt("该物品已加锁");
                            }
                            else {
                                goodstable.setGoodlock(1);
                                gooduse(goodstable, 0);
                                ZhuFrame.getZhuJpanel().addPrompt("加锁成功");
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt("该类型物品不可加锁");
                        }
                    }
                }
                else if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE11)) {
                    if (goodstable != null && goodstable.getGoodlock() == 1) {
                        goodstable.setGoodlock(0);
                        gooduse(goodstable, 0);
                        ZhuFrame.getZhuJpanel().addPrompt("解锁成功");
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    }
                }
                else if (LiangHaoMouslisten.replace != -1) {
                    goodreplace(LiangHaoMouslisten.replace, this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24);
                }
                else if (goodstable != null) {
                    LiangHaoMouslisten.replacepath = this.goodPlace;
                    LiangHaoMouslisten.replace = this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24;
                }
            }
            return;
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        LiangHaoMouslisten.list.remove(this.goodPlace + "");
    }
    
    public void ManNoCombat(Goodstable goodstable, long type) throws Exception {
        int EquipmentType = Goodtype.EquipmentType(type);
        if (EquipmentType != -1) {
            this.UseEquipment(EquipmentType, goodstable);
        }
        else if (type == 0L) {
            int usetime = (int)goodstable.getUsetime();
            Medicine.RedAndBlue(goodstable, goodstable.getValue());
            if (usetime != (int)goodstable.getUsetime()) {
                gooduse(goodstable, 1);
                String mes = Agreement.getAgreement().rolechangeAgreement("H" + RoleData.getRoleData().getLoginResult().getHp() + "=" + RoleData.getRoleData().getLoginResult().getMp());
                SendMessageUntil.toServer(mes);
                PetAddPointMouslisten.getplayerValue();
                Music.addyinxiao("战斗、站立、行走使用药品.mp3");
            }
        }
        else if (type == 60001L || type == 60002L) {
            Box.Novice(goodstable);
        }
        else if (type == 2114L) {
            Box.xms(goodstable);
        }
        else if (type == 2042L) {
            Consumptions.Consumption(goodstable, goodstable.getValue());
        }
        else if (Goodtype.Flightchess(type)) {
            Flight.Flightchess(goodstable, type);
        }
        else if (type == 188L) {
            Lingbaogood.fushi(goodstable, this.goodPlace);
        }
        else if (type == 1688L || type == 300L || type == 121L || type == 122L || type == 2121L) {
            ExchangeAwardJframe.getExchangeAwardJframe().getAwardJpanel().use((type == 1688L) ? 0 : ((type == 300L) ? 1 : ((type == 121L) ? 3 : ((type == 2121L) ? 888 : 4))), goodstable.getRgid());
        }
        else if (type == 213L) {
            Box.tesheling(goodstable);
        }
        else if (type >= 50L && type <= 61L) {
            BabyGood.BabyGoods(goodstable, type);
        }
        else if (type == 1003L) {
            TaskGood.gainTask(goodstable);
        }
        else if (type == 729L) {
            PetEquip.RoleCJS(goodstable);
        }
        else if (type == 889L) {
            this.usePsyVou();
        }
        else {
            if (type == 60003L) {
                LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getlotteryTypeGoods(Integer.parseInt(goodstable.getValue()));
                return;
            }
            if (type == 506L) {
                NPCJfram.getNpcJfram().getNpcjpanel().openJxryd();
            }
            else if (type == 190L || type == 891L || type == 1002L || type == 28955L) {
                Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
                if (lingbao == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你没有选中的灵宝");
                    return;
                }
                this.userling(goodstable, lingbao);
            }
            else if (type == 520L) {
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.starCardDeposit, goodstable, "真的要放入地煞星录吗?");
            }
            else if (Goodtype.isPalGoods(type)) {
                PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                if (mainJpanel.getPalDataChooseId() < 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择一个伙伴");
                    return;
                }
                Pal pidGetPal = mainJpanel.pidGetPal(mainJpanel.getPalDataChooseId());
                if (pidGetPal == null) {
                    return;
                }
                if (goodstable != null) {
                    if ((long)goodstable.getType() == 7500L) {
                        return;
                    }
                    String sendmes = Agreement.getAgreement().userpalAgreement(pidGetPal.getId() + "|" + goodstable.getRgid());
                    SendMessageUntil.toServer(sendmes);
                }
            }
            else {
                this.userRole(goodstable);
            }
        }
        if ((int)goodstable.getUsetime() <= 0) {
            GoodsListFromServerUntil.Delete(this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24);
        }
    }
    
    public void UseEquipment(int EquipmentType, Goodstable goodstable) throws Exception {
        int part = DevicGoodsEquiptmentUntil.getEquiptmentOrNo(goodstable);
        if (part == -2) {
            ZhuFrame.getZhuJpanel().addPrompt2("你达不到装备要求");
            return;
        }
        if (part != -1) {
            EquipmentType = part;
        }
        else if (EquipmentType == 10 && GoodsListFromServerUntil.getChoseGoodsList()[EquipmentType] != null) {
            EquipmentType = 11;
        }
        GoodsListFromServerUntil.mutualChange(EquipmentType, this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = GoodsListFromServerUntil.Getgood(this.goodPlace);
        if (goodstable != null) {
            TestpackJframe.getTestpackJframe().getJpac().PaintingText(this.goodPlace);
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        TestpackJframe.getTestpackJframe().getJpac().ClearText();
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public int getGoodPlace() {
        return this.goodPlace;
    }
    
    public void setGoodPlace(int goodPlace) {
        this.goodPlace = goodPlace;
    }
    
    public static void goodreplace(int one, int two) {
        if (one != -1) {
            GoodsListFromServerUntil.tihuan(one, two);
        }
        LiangHaoMouslisten.replace = -1;
        LiangHaoMouslisten.replacepath = -1;
    }
    
    public static void gooduse(Goodstable goodstable, int type) {
        LiangHaoMouslisten.goodarr.setI(type);
        LiangHaoMouslisten.goodarr.getList().clear();
        LiangHaoMouslisten.goodarr.getList().add(goodstable);
        String sendmes = Agreement.getAgreement().packchangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(LiangHaoMouslisten.goodarr));
        SendMessageUntil.toServer(sendmes);
    }
    
    public static void gooduse(Goodstable good1, Goodstable good2, int type) {
        LiangHaoMouslisten.goodarr.setI(type);
        LiangHaoMouslisten.goodarr.getList().clear();
        if (good1 != null) {
            LiangHaoMouslisten.goodarr.getList().add(good1);
        }
        if (good2 != null) {
            LiangHaoMouslisten.goodarr.getList().add(good2);
        }
        String sendmes = Agreement.getAgreement().packchangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(LiangHaoMouslisten.goodarr));
        SendMessageUntil.toServer(sendmes);
    }
    
    public void petUseGoods(Goodstable goodstable, long type) {
        if (Goodtype.FightingMedicine(type)) {
            int usetime = (int)goodstable.getUsetime();
            Medicine.PetRedAndBlue(goodstable, goodstable.getValue());
            if (usetime != (int)goodstable.getUsetime()) {
                gooduse(goodstable, 1);
                if ((int)goodstable.getUsetime() <= 0) {
                    GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
                }
                SendRoleAndRolesummingUntil.sendRoleSumming(UserMessUntil.getChosePetMes());
                PetAddPointMouslisten.showPetValue();
                Music.addyinxiao("战斗、站立、行走使用药品.mp3");
            }
        }
        else if (type == 750L) {
            this.useNedan();
        }
        else if (type == 2115L) {
            hyd(goodstable);
            if ((int)goodstable.getUsetime() <= 0) {
                GoodsListFromServerUntil.Deleted(this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24);
            }
        }
        else if (type == 729L || type == 510L || type == 511L || type == 512L) {
            PetEquip.PetCJS(goodstable);
        }
        else {
            this.userPet(goodstable, UserMessUntil.getChosePetMes());
        }
    }
    
    public static void hyd(Goodstable goodstable) {
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        int lvl = AnalysisString.petLvlint((int)pet.getGrade());
        pet.setBone(Integer.valueOf(lvl));
        pet.setSpir(Integer.valueOf(lvl));
        pet.setPower(Integer.valueOf(lvl));
        pet.setSpeed(Integer.valueOf(lvl));
        if (pet.getTurnRount() >= 4) {
            pet.setCalm(Integer.valueOf(lvl));
        }
        ZhuFrame.getZhuJpanel().addPrompt2("你使用了" + goodstable.getGoodsname());
        goodstable.setUsetime(Integer.valueOf((int)goodstable.getUsetime() - 1));
        gooduse(goodstable, 1);
        PetAddPointMouslisten.showPetValue();
        SendRoleAndRolesummingUntil.sendRoleSumming(pet);
    }
    
    public void useNedan() {
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        if (pet == null) {
            ZhuFrame.getZhuJpanel().addPrompt("请选择召唤兽！");
            return;
        }
        Goodstable good = GoodsListFromServerUntil.Getgood(this.goodPlace);
        if (good == null) {
            return;
        }
        String[] strings = good.getValue().split("\\|");
        String[] stringLevel = strings[2].split("=");
        String[] stringLevel2 = stringLevel[1].split("转");
        int Nedanturn = Integer.parseInt(stringLevel2[0]);
        int Nedanlvl = Integer.parseInt(stringLevel2[1]);
        int Petturn = pet.getTurnRount();
        int Petlvl = AnalysisString.petLvlint((int)pet.getGrade());
        if (Petturn < Nedanturn || (Petturn == Nedanturn && Petlvl < Nedanlvl)) {
            ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽等级低于内丹等级！！！");
            return;
        }
        String[] goodIds = null;
        if (pet.getInnerGoods() != null && !pet.getInnerGoods().equals("")) {
            goodIds = pet.getInnerGoods().split("\\|");
        }
        StringBuffer buffer = new StringBuffer();
        if (goodIds != null) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            if (goodIds.length >= Integer.parseInt(configure.getNdslsx())) {
                ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽的内丹已超过上限!");
                return;
            }
            for (int i = 0; i < goodIds.length; ++i) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(goodIds[i]);
                Goodstable good2 = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(goodIds[i]));
                if (good2.getGoodsname().equals(good.getGoodsname())) {
                    ZhuFrame.getZhuJpanel().addPrompt("你已有这种类型的内丹了！");
                    return;
                }
            }
        }
        if (buffer.length() != 0) {
            buffer.append("|");
        }
        buffer.append(good.getRgid());
        pet.setInnerGoods(buffer.toString());
        GoodsListFromServerUntil.fushis.put(good.getRgid(), good);
        ZhuFrame.getZhuJpanel().addPrompt("你获得了内丹技能 " + good.getGoodsname());
        SendRoleAndRolesummingUntil.sendRoleSumming(pet);
        good.setStatus(Integer.valueOf(1));
        gooduse(good, 0);
        GoodsListFromServerUntil.Deleted(this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24);
        ChangeMouseSymbolMouslisten.refreshNedan(pet);
    }
    
    public void mountUseGoods(Goodstable goodstable, long type) {
        if (MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex() == -1) {
            return;
        }
        int index = MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex();
        Mount mount = (Mount)ZhuJpanel.getListMount().get(index);
        if (type == 718L) {
            this.useMountSkillCard(mount);
        }
        else if (type == 719L) {
            this.mountMissSkills(mount);
        }
        else if (type != 720L) {
            this.userMount(goodstable, mount);
        }
    }
    
    public void useMountSkillCard(Mount mount) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (mount.getMountskill().size() >= Integer.parseInt(configure.getZqjnsx()) && (int)mount.getMountid() < 8) {
            ZhuFrame.getZhuJpanel().addPrompt("普通坐骑最多可拥有" + Integer.parseInt(configure.getZqjnsx()) + "个技能！！！");
            return;
        }
        if (mount.getMountskill().size() >= Integer.parseInt(configure.getZqjnsx()) + 1 && (int)mount.getMountid() > 7) {
            ZhuFrame.getZhuJpanel().addPrompt("飞行坐骑最多可拥有" + (Integer.parseInt(configure.getZqjnsx()) + 1) + "个技能");
            return;
        }
        int index = MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex();
        MountSkill mountSkill = new MountSkill();
        mountSkill.setMid(mount.getMid());
        mountSkill.setSkillname(GoodsListFromServerUntil.Getgood(this.goodPlace).getGoodsname());
        Boolean bool = Boolean.valueOf(false);
        if (mount.getMountskill().size() != 0) {
            for (int i = 0; i < mount.getMountskill().size(); ++i) {
                if (((MountSkill)mount.getMountskill().get(i)).getSkillname().equals(mountSkill.getSkillname())) {
                    bool = Boolean.valueOf(true);
                }
            }
            if ((boolean)bool) {
                ZhuFrame.getZhuJpanel().addPrompt("该坐骑已有这个技能!");
            }
            else {
                mount.getMountskill().add(mountSkill);
                String sendmes = Agreement.getAgreement().addMountSkill(GsonUtil.getGsonUtil().getgson().toJson(mountSkill));
                SendMessageUntil.toServer(sendmes);
                GoodsListFromServerUntil.Getgood(this.goodPlace).goodxh(1);
                gooduse(GoodsListFromServerUntil.Getgood(this.goodPlace), 1);
                if ((int)GoodsListFromServerUntil.Getgood(this.goodPlace).getUsetime() <= 0) {
                    GoodsListFromServerUntil.Deleted(this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24);
                }
                MountPanelBtn.refreshMountSkills(index);
            }
        }
        else {
            ((Mount)ZhuJpanel.getListMount().get(index)).getMountskill().add(mountSkill);
            String sendmes = Agreement.getAgreement().addMountSkill(GsonUtil.getGsonUtil().getgson().toJson(mountSkill));
            SendMessageUntil.toServer(sendmes);
            GoodsListFromServerUntil.Getgood(this.goodPlace).goodxh(1);
            gooduse(GoodsListFromServerUntil.Getgood(this.goodPlace), 1);
            if ((int)GoodsListFromServerUntil.Getgood(this.goodPlace).getUsetime() <= 0) {
                GoodsListFromServerUntil.Deleted(this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24);
            }
            MountPanelBtn.refreshMountSkills(index);
        }
        if (UserMessUntil.getPetListTable() != null && UserMessUntil.getPetListTable().size() > 0) {
            for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
                RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i);
                if (mount.getSid() != null && pet.getSid().compareTo(mount.getSid()) == 0) {
                    ChangeMouseSymbolMouslisten.addProperties(pet, mount);
                }
                else if (mount.getOthrersid() != null && pet.getSid().compareTo(mount.getOthrersid()) == 0) {
                    ChangeMouseSymbolMouslisten.addProperties(pet, mount);
                }
                else if (mount.getSid3() != null && pet.getSid().compareTo(mount.getSid3()) == 0) {
                    ChangeMouseSymbolMouslisten.addProperties(pet, mount);
                }
            }
        }
    }
    
    public void mountMissSkills(Mount mount) {
        int index = MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex();
        if (mount.getMountskill().size() != 0) {
            if (UserMessUntil.getPetListTable() != null && UserMessUntil.getPetListTable().size() > 0) {
                for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
                    RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i);
                    if (mount.getSid() != null && pet.getSid().compareTo(mount.getSid()) == 0) {
                        ChangeMouseSymbolMouslisten.clearPropertie(pet);
                    }
                    else if (mount.getOthrersid() != null && pet.getSid().compareTo(mount.getOthrersid()) == 0) {
                        ChangeMouseSymbolMouslisten.clearPropertie(pet);
                    }
                    else if (mount.getSid3() != null && pet.getSid().compareTo(mount.getSid3()) == 0) {
                        ChangeMouseSymbolMouslisten.clearPropertie(pet);
                    }
                }
            }
            mount.getMountskill().clear();
            String sendmes = Agreement.getAgreement().missMountSkill(GsonUtil.getGsonUtil().getgson().toJson(mount.getMid()));
            SendMessageUntil.toServer(sendmes);
            MountPanelBtn.refreshMountSkills(index);
            MountSkillsJframe.getMountSkillsJframe().getMountSkillsJpanel().showSkillMsg(null);
            if (FormsManagement.getframe(58).isVisible()) {
                PetProperty.ShowQl(UserMessUntil.getChosePetMes());
            }
        }
        GoodsListFromServerUntil.Getgood(this.goodPlace).goodxh(1);
        gooduse(GoodsListFromServerUntil.Getgood(this.goodPlace), 1);
        if ((int)GoodsListFromServerUntil.Getgood(this.goodPlace).getUsetime() <= 0) {
            GoodsListFromServerUntil.Deleted(this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24);
        }
    }
    
    public void usePsyVou() {
        QuackGameJframe.getQuackGameJframe().getGameJpanel().kyNum = (int)GoodsListFromServerUntil.Getgood(this.goodPlace).getUsetime();
        String sendMes = Agreement.getFiveMsgAgreement("G");
        SendMessageUntil.toServer(sendMes);
    }
    
    public void userRole(Goodstable good) {
        Limit limit = new Limit();
        long type = (long)good.getType();
        if (type == 100L || type == 888L || type == 2041L || type == 935L || type == 936L || type == 951L || type == 2126L) {
            long[] xiaohao = AnalysisString.xiaohao(good.getValue());
            if (xiaohao[0] != 0L && RoleData.getRoleData().getLoginResult().getGold().longValue() + xiaohao[0] > Util.GoldUP.longValue()) {
                ZhuFrame.getZhuJpanel().addPrompt2("金钱上限9999.99亿");
                return;
            }
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        }
        else if (type == 7005L || type == 118L || type == 2051L || type == 2052L || type == 2057L || type == 1007L) {
            if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的背包已满");
                return;
            }
            if (type == 2051L || type == 2052L || type == 2057L || type == 1007L) {
                String[] vs = good.getValue().split("\\=");
                if (vs[0].equals("宝图")) {
                    vs = vs[1].split(",");
                    int mapid = Integer.parseInt(vs[0]);
                    int x = Integer.parseInt(vs[2]) / 20;
                    int y = Integer.parseInt(vs[3]) / 20;
                    if (mapid != Util.ditubianma) {
                        ZhuFrame.getZhuJpanel().addPrompt2("好像不是在这个场景吧");
                        return;
                    }
                    RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
                    long x2 = (long)(roleShow.getX() / 20);
                    long y2 = (long)(roleShow.getY() / 20);
                    x = (int)((long)x - x2);
                    y = (int)((long)y - y2);
                    x = Math.abs(x);
                    y = Math.abs(y);
                    if (x > 4 || y > 4) {
                        ZhuFrame.getZhuJpanel().addPrompt2("坐标不对");
                        return;
                    }
                }
            }
            String sendmes2 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
            good.goodxh(1);
        }
        else if (type == 502L) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            if (UserMessUntil.getPetListTable() != null && UserMessUntil.getPetListTable().size() >= Integer.parseInt(configure.getXdzhssx())) {
                ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽可携带的数量已满！！！");
                return;
            }
            String sendmes3 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes3);
            good.goodxh(1);
        }
        else if (type == 2122L) {
            if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的背包已满");
                return;
            }
            String[] vs = good.getValue().split("\\=");
            if (vs[0].equals("宝图")) {
                vs = vs[1].split(",");
                int mapid = Integer.parseInt(vs[0]);
                int x = Integer.parseInt(vs[2]) / 20;
                int y = Integer.parseInt(vs[3]) / 20;
                if (mapid != Util.ditubianma) {
                    ZhuFrame.getZhuJpanel().addPrompt2("好像不是在这个场景吧");
                    return;
                }
                AthChartJframe.getAthChartJPanel();
                FormsManagement.showForm(3001);
                LaborRank laborRank = new LaborRank();
                laborRank.setRank(11);
                laborRank.setValue1("");
                AthChartJframe.getAthChartJPanel().showViewData(laborRank);
                String sendmes4 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
                SendMessageUntil.toServer(sendmes4);
                good.goodxh(1);
            }
        }
        else if (type == 2123L) {
            if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的背包已满");
                return;
            }
            AthChartJframe.getAthChartJPanel();
            if (Util.nums != 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("抽奖还没有结束，请别着急!");
                return;
            }
            FormsManagement.showForm(3001);
            LaborRank laborRank2 = new LaborRank();
            laborRank2.setRank(11);
            laborRank2.setValue1("");
            AthChartJframe.getAthChartJPanel().showViewData(laborRank2);
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        }
        else if (type == 2124L) {
            if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的背包已满");
                return;
            }
            boolean falg = GoodsListFromServerUntil.isgood(80659, 1);
            if (falg) {
                GoodsListFromServerUntil.consume(80659, 1);
                String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
                SendMessageUntil.toServer(sendmes);
                good.goodxh(1);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("金钥匙不足！");
                return;
            }
        }
        else if (type == 717L) {
            int lvl = Integer.parseInt(good.getValue()) / 100;
            if (lvl <= 12) {
                if (ZhuJpanel.getListMount() != null && ZhuJpanel.getListMount().size() != 0) {
                    for (int i = ZhuJpanel.getListMount().size() - 1; i >= 0; --i) {
                        Mount mount = (Mount)ZhuJpanel.getListMount().get(i);
                        if ((int)mount.getMountid() == lvl) {
                            ZhuFrame.getZhuJpanel().addPrompt("你已有这种坐骑！");
                            return;
                        }
                    }
                }
                String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
                SendMessageUntil.toServer(sendmes);
                good.goodxh(1);
            }
        }
        else if (type == 2235L) {
            String[] vs = good.getValue().split("\\=");
            String flyId = vs[2];
            if (ZhuJpanel.getListFly() != null && ZhuJpanel.getListFly().size() != 0) {
                for (int j = ZhuJpanel.getListFly().size() - 1; j >= 0; --j) {
                    Fly fly = (Fly)ZhuJpanel.getListFly().get(j);
                    if (fly.getFlytid().toString().equals(flyId)) {
                        ZhuFrame.getZhuJpanel().addPrompt("你已有这种飞行器");
                        return;
                    }
                }
            }
            String sendmes5 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes5);
            good.goodxh(1);
        }
        else if (type == 2525L) {
            String sendmes2 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
            good.goodxh(1);
        }
        else if (type == 66668L || type == 66669L) {
            String sendmes2 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
            good.goodxh(1);
        }
        else if (type == 66778L || type == 66779L) {
            String sendmes3 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes3);
            good.goodxh(1);
        }
        else if (type == 924L) {
            String sendmes2 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
            good.goodxh(1);
        }
        else if (type == 926L) {
            String sendmes2 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
            good.goodxh(1);
        }
        else if (Goodtype.TimingGood(type) || Goodtype.Medicine(type) || Goodtype.BlueBack(type) || type == 88L || type == 99L || type == 111L || type == 112L || type == 113L) {
            limit = TimeLimit.getLimits().getLimit("强法型");
            if (good.getGoodsname().equals("复活符文") && limit != null && limit.getName().equals(good.getGoodsname())) {
                ZhuFrame.getZhuJpanel().addPrompt("你已拥有此效果不可继续使用");
                return;
            }
            good.goodxh(1);
            String sendmes2 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
        }
        else if (type == 66666L) {
            String sendmes2 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
            good.goodxh(1);
        }
        else if (type == 1006L) {
            String sendmes2 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
            good.goodxh(1);
        }
        else if (type == 6699L) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            int attachPack = loginResult.getAttachPack();
            if (attachPack >= 2) {
                ZhuFrame.getZhuJpanel().addPrompt2(good.getGoodsname() + "最多只能使用两次");
                return;
            }
            String sendmes5 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes5);
            good.goodxh(1);
            loginResult.setAttachPack(++attachPack);
            GoodsListFromServerUntil.GoodExpansion(loginResult.getTurnAround(), loginResult.getAttachPack());
        }
        else if (type == 9002L) {
            if (!AnalysisString.lvlfull((int)ImageMixDeal.userimg.getRoleShow().getGrade(), "1转120")) {
                ZhuFrame.getZhuJpanel().addPrompt2("飞升后才能学习法门");
                return;
            }
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            if ((int)loginResult.getFmsld() > 10000) {
                ZhuFrame.getZhuJpanel().addPrompt2("法门熟练度已满");
                return;
            }
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
            loginResult.setFmsld(Integer.valueOf((int)loginResult.getFmsld() + 20));
            SkillSMGatePanel2.getSkillValue()[0].setText(loginResult.getFmsld() + "/10000");
            SkillSMGatePanel2.getSkillValue()[1].setText(loginResult.getFmsld() + "/10000");
            SkillSMGatePanel2.getSkillValue()[2].setText(loginResult.getFmsld() + "/10000");
            SkillSMGatePanel2.getSkillValue()[3].setText(loginResult.getFmsld() + "/10000");
            SkillSMGatePanel2.getSkillValue()[4].setText(loginResult.getFmsld() + "/10000");
            SkillSMGatePanel2.getSkillValue()[5].setText(loginResult.getFmsld() + "/10000");
            ZhuFrame.getZhuJpanel().addPrompt2("法门熟练度+20");
        }
        else if (type == 2120L) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            ConfigureBean allConfigure2 = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item2 = allConfigure2.getAllConfigure();
            Configure configure2 = (Configure)item2.get(new BigDecimal(1));
            if (loginResult.getGradeincrease() != null && (int)loginResult.getGradeincrease() >= Integer.parseInt(configure2.getRwdjsx())) {
                ZhuFrame.getZhuJpanel().addPrompt2("等级突破丹最大可服用#G" + configure2.getRwdjsx() + "#Y次！#R您已达到上限！");
                return;
            }
            if (loginResult.getGradeincrease() == null) {
                loginResult.setGradeincrease(Integer.valueOf(0));
            }
            loginResult.setGradeincrease(Integer.valueOf((int)loginResult.getGradeincrease() + 1));
            good.goodxh(1);
            String sendmes6 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes6);
        }
        else if (type == 2233L) {
            String sendmes2 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
            good.goodxh(1);
        }
        else if (type == 2250L) {
            String sendmes2 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
        }
        else if (type == 2251L) {
            FormsManagement.HideForm(639);
            LotteryFromServerUntil.drop();
            FormsManagement.showForm(639);
            String sendmes2 = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes2);
            good.goodxh(1);
            LotteryFromServerUntil.setHasStart(false);
        }
    }
    
    public void userPet(Goodstable good, RoleSummoning pet) {
        if (pet == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择召唤兽！");
            return;
        }
        long type = (long)good.getType();
        if (type == 715L) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            if ((long)pet.getFriendliness() >= (long)Integer.parseInt(configure.getZhsqmsx())) {
                ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽的亲密值已满！");
                return;
            }
        }
        else if (type == 503L || type == 2326L) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            String petskill = pet.getPetSkills();
            int sum = (int)pet.getOpenSeal();
            if (sum > 0 && petskill != null && !petskill.equals("")) {
                String[] vs = petskill.split("\\|");
                if (vs.length >= Integer.parseInt(configure.getZhsjngs()) - 1 || sum <= vs.length) {
                    ZhuFrame.getZhuJpanel().addPrompt2("召唤兽技能格子已经满了");
                    return;
                }
            }
        }
        else if (type == 504L) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            int skllNum = Integer.parseInt(configure.getZhsjngs());
            if (skllNum == 5) {
                int flag = skllNum - 1;
                if (pet.getSsn().equals("2") || pet.getSsn().equals("3") || pet.getSsn().equals("4")) {
                    flag = skllNum;
                }
                if ((int)pet.getOpenSeal() >= flag) {
                    ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽的技能封印都已解开！");
                    return;
                }
            }
            else if (skllNum == 7) {
                int flag = skllNum - 1;
                if (pet.getSsn().equals("2") || pet.getSsn().equals("3") || pet.getSsn().equals("4")) {
                    flag = skllNum;
                }
                if ((int)pet.getOpenSeal() >= flag) {
                    ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽的技能封印都已解开！");
                    return;
                }
            }
            else if ((int)pet.getOpenSeal() >= 6) {
                ZhuFrame.getZhuJpanel().addPrompt2("普通聚魄丹无法开启更高的技能格");
                return;
            }
        }
        else if (type == 10086L) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            int skllNum = Integer.parseInt(configure.getZhsjngs());
            if (skllNum == 7) {
                ZhuFrame.getZhuJpanel().addPrompt2("暂未开启！");
                return;
            }
            int flag = 8;
            if (pet.getSsn().equals("2") || pet.getSsn().equals("3") || pet.getSsn().equals("4")) {
                flag = 9;
            }
            if ((int)pet.getOpenSeal() < 6) {
                ZhuFrame.getZhuJpanel().addPrompt2("高级聚魄丹适合更高的技能格！");
                return;
            }
            if ((int)pet.getOpenSeal() >= flag) {
                ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽的技能封印都已解开！");
                return;
            }
        }
        else if (type == 938L) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            int skllNum = Integer.parseInt(configure.getZhsjngs());
            if (skllNum == 7) {
                ZhuFrame.getZhuJpanel().addPrompt2("暂未开启！");
                return;
            }
            int flag = 6;
            if ((int)pet.getOpenql() >= flag) {
                ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽的启灵技能格子都已解开！");
                return;
            }
        }
        else if (type == 939L) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            int skllNum = Integer.parseInt(configure.getZhsjngs());
            if (skllNum == 7) {
                ZhuFrame.getZhuJpanel().addPrompt2("暂未开启！");
                return;
            }
            String value = pet.getPetQlSkills();
            int sum2 = (int)pet.getOpenql();
            if (sum2 > 0 && value != null && !value.equals("")) {
                String[] vs2 = value.split("\\|");
                if (vs2.length >= 6 || sum2 <= vs2.length) {
                    ZhuFrame.getZhuJpanel().addPrompt2("召唤兽启灵技能格子已经满了");
                    return;
                }
            }
        }
        else if (type != 2040L) {
            if (type == 2043L) {
                if ((int)pet.getGrade() > 100) {
                    ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽等级过高！");
                    return;
                }
            }
            else if (type == 2113L) {
                ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                Configure configure = (Configure)item.get(new BigDecimal(1));
                if (pet.getDragon() >= Integer.parseInt(configure.getLzgsx())) {
                    ZhuFrame.getZhuJpanel().addPrompt2("龙骨数量已达到上限！");
                    return;
                }
            }
            else if (type == 918L) {
                ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                Configure configure = (Configure)item.get(new BigDecimal(1));
                if (pet.getSpdragon() >= Integer.parseInt(configure.getCjlzgsx())) {
                    ZhuFrame.getZhuJpanel().addPrompt2("超级龙之骨数量已达到上限！");
                    return;
                }
            }
            else if (type == 716L) {
                String value2 = good.getValue();
                if (value2 == null || value2.equals("")) {
                    value2 = "100|0";
                }
                String[] v = value2.split("\\|");
                if (v[1].equals("0") || v[1].equals(pet.getSummoningid())) {
                    if (good.getGoodsname().indexOf("元气") == -1 && pet.getSsn().equals("6")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("不能吃变色丹");
                        return;
                    }
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("召唤兽无法使用该类型的元气丹");
                    return;
                }
            }
            else if (type == 192L) {
                ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                Configure configure = (Configure)item.get(new BigDecimal(1));
                if (pet.getSsn().equals("5") || pet.getSummoningid().equals("200125")) {
                    int drac = pet.getDraC();
                    if (drac >= Integer.parseInt(configure.getLywsx())) {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经达到最大使用次数");
                        return;
                    }
                    int maxsum = 0;
                    if ((int)pet.getGrade() >= 433) {
                        maxsum = 9;
                    }
                    else if ((int)pet.getGrade() >= 362) {
                        maxsum = 8;
                    }
                    else if ((int)pet.getGrade() >= 322) {
                        maxsum = 7;
                    }
                    else if ((int)pet.getGrade() >= 272) {
                        maxsum = 6;
                    }
                    else if ((int)pet.getGrade() >= 221) {
                        maxsum = 5;
                    }
                    else if ((int)pet.getGrade() >= 191) {
                        maxsum = 4;
                    }
                    else if ((int)pet.getGrade() >= 151) {
                        maxsum = 3;
                    }
                    else if ((int)pet.getGrade() >= 90) {
                        maxsum = 2;
                    }
                    else if ((int)pet.getGrade() >= 50) {
                        maxsum = 1;
                    }
                    if ((int)pet.getGrade() >= 433) {
                        maxsum = Integer.parseInt(configure.getLywsx());
                    }
                    if (drac >= maxsum) {
                        String tsm = "召唤兽当前等级最多使用" + maxsum + "个";
                        ZhuFrame.getZhuJpanel().addPrompt2(tsm);
                        return;
                    }
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("不是龙涎丸宝宝");
                    return;
                }
            }
            else if (type == 667L) {
                if (pet.getDragon() <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt("召唤兽没有服用过龙骨！！");
                    return;
                }
            }
            else if (type == 919L) {
                if (pet.getSpdragon() <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt("召唤兽没有服用过超级龙之骨头！！");
                    return;
                }
            }
            else if (type == 2323L) {
                if (pet.getPetSkills().indexOf("3034") == -1 || pet.getPetSkills().indexOf("3040") == -1) {
                    ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽没有???");
                    return;
                }
            }
            else if (type == 2423L) {
                if (pet.getPetSkills().indexOf("3034") == -1 || pet.getPetSkills().indexOf("3040") == -1) {
                    ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽没有???");
                    return;
                }
            }
            else if (type == 925L) {
                if (pet.getPetSkills() == null || pet.getPetSkills().equals("")) {
                    ZhuFrame.getZhuJpanel().addPrompt2("召唤兽没有技能");
                    return;
                }
            }
            else if (type == 2325L) {
                if (pet.getTurnRount() < 3) {
                    ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽未3转！");
                    return;
                }
                if (pet.getPetSkills() == null || pet.getPetSkills().equals("")) {
                    ZhuFrame.getZhuJpanel().addPrompt2("召唤兽没有技能");
                    return;
                }
                if (pet.getPetSkills().equals("1828") || pet.getPetSkills().equals("1840") || pet.getPetSkills().equals("1841") || pet.getPetSkills().equals("1842") || pet.getPetSkills().equals("1606") || pet.getPetSkills().equals("1607") || pet.getPetSkills().equals("1608") || pet.getPetSkills().equals("1609") || pet.getPetSkills().equals("1829") || pet.getPetSkills().equals("1830") || pet.getPetSkills().equals("1867") || pet.getPetSkills().equals("1868") || pet.getPetSkills().equals("1869") || pet.getPetSkills().equals("3034") || pet.getPetSkills().equals("3040")) {
                    ZhuFrame.getZhuJpanel().addPrompt2("该技能不能被提取");
                    return;
                }
                if (pet.getGoods() != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽携带着装备");
                    return;
                }
                if (ZhuJpanel.getPetMount(pet.getSid()) != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽被管制中！！！");
                    return;
                }
                if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(pet.getSid()) == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽已在参战中！！！");
                    return;
                }
            }
            else if (type == 727L) {
                int petid = Integer.parseInt(pet.getSummoningid());
                if (petid != 200123 && petid != 200904 && petid != 200116 && petid != 200117 && petid != 200097 && petid != 200098 && petid != 200099 && petid != 200100 && petid != 200101 && petid != 200188 && petid != 200185 && petid != 200184 && petid != 200195 && petid != 200106 && petid != 200190 && petid != 200189 && petid != 200202 && petid != 200203 && petid != 200205 && petid != 200206 && petid != 200207 && petid != 200208 && petid != 200209 && petid != 200210 && petid != 200211 && petid != 200212 && petid != 200213 && petid != 200214 && petid != 200215 && petid != 200216 && petid != 200217 && petid != 200201 && petid != 200238 && petid != 200903) {
                    String msg = "召唤兽不能使用此化形丹·神";
                    ZhuFrame.getZhuJpanel().addPrompt2(msg);
                    return;
                }
            }
            else if (type == 8828L) {
                int petid = Integer.parseInt(pet.getSummoningid());
                if (petid != 200090 && petid != 200905 && petid != 200077 && petid != 200085 && petid != 200093 && petid != 200147 && petid != 200135 && petid != 200193 && petid != 200158 && petid != 200187 && petid != 200143 && petid != 200140) {
                    String msg = "召唤兽不能使用此化形丹·幻";
                    ZhuFrame.getZhuJpanel().addPrompt2(msg);
                    return;
                }
            }
            else if (type == 2116L) {
                ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                Configure configure = (Configure)item.get(new BigDecimal(1));
                if (pet.getFlyupNum() >= Integer.parseInt(configure.getSsfsdsx())) {
                    ZhuFrame.getZhuJpanel().addPrompt2("神兽 " + pet.getSummoningname() + "的飞升次数已达到上限！");
                    return;
                }
            }
            else if (type == 2118L) {
                if (pet.getCzjjd() >= 200) {
                    ZhuFrame.getZhuJpanel().addPrompt2("召唤兽 " + pet.getSummoningname() + "的成长进阶丹已达到上限！");
                    return;
                }
            }
            else if (type != 2119L && type != 1005L) {
                if (type == 8002L) {
                    String value2 = good.getValue();
                    String[] value3 = value2.split("=");
                    String value4 = "0";
                    if (value3[1].equals("高级分裂攻击")) {
                        value4 = "1833";
                    }
                    else if (value3[1].equals("春风佛面")) {
                        value4 = "1871";
                    }
                    else if (value3[1].equals("春意盎然")) {
                        value4 = "1612";
                    }
                    else if (value3[1].equals("分花拂柳")) {
                        value4 = "1831";
                    }
                    else if (value3[1].equals("悬刃")) {
                        value4 = "1834";
                    }
                    else if (value3[1].equals("遗患")) {
                        value4 = "1836";
                    }
                    else if (value3[1].equals("报复")) {
                        value4 = "1835";
                    }
                    else if (value3[1].equals("吉人天相")) {
                        value4 = "1838";
                    }
                    else if (value3[1].equals("妙手回春")) {
                        value4 = "1611";
                    }
                    else if (value3[1].equals("视死如归")) {
                        value4 = "1872";
                    }
                    else if (value3[1].equals("天地同寿")) {
                        value4 = "1880";
                    }
                    else if (value3[1].equals("扶伤")) {
                        value4 = "1858";
                    }
                    else if (value3[1].equals("福禄双全")) {
                        value4 = "1873";
                    }
                    else if (value3[1].equals("炊金馔玉")) {
                        value4 = "1600";
                    }
                    else if (value3[1].equals("枯木逢春")) {
                        value4 = "1601";
                    }
                    else if (value3[1].equals("西天净土")) {
                        value4 = "1602";
                    }
                    else if (value3[1].equals("如人饮水")) {
                        value4 = "1603";
                    }
                    else if (value3[1].equals("风火燎原")) {
                        value4 = "1604";
                    }
                    else if (value3[1].equals("高级清明术")) {
                        value4 = "1850";
                    }
                    else if (value3[1].equals("高级脱困术")) {
                        value4 = "1852";
                    }
                    else if (value3[1].equals("高级强心术")) {
                        value4 = "1854";
                    }
                    else if (value3[1].equals("舍身取义")) {
                        value4 = "1839";
                    }
                    else if (value3[1].equals("义之金叶神")) {
                        value4 = "1820";
                    }
                    else if (value3[1].equals("信之土叶神")) {
                        value4 = "1822";
                    }
                    else if (value3[1].equals("孤勇成军")) {
                        value4 = "1878";
                    }
                    else if (value3[1].equals("高级禅机顿悟")) {
                        value4 = "1887";
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt("获取技能错误");
                        return;
                    }
                    if (pet.getPetSkills() == null || pet.getPetSkills().equals("")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("召唤兽没有技能");
                        return;
                    }
                    if (pet.getPetSkills().indexOf(value4) == -1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽没有" + value3[1]);
                        return;
                    }
                }
                else if (type == 2234L) {
                    GoodsListFromServerUntil.Getgood(this.goodPlace).goodxh(1);
                    if ((int)GoodsListFromServerUntil.Getgood(this.goodPlace).getUsetime() <= 0) {
                        GoodsListFromServerUntil.Deleted(this.goodPlace + GoodsListFromServerUntil.Pagenumber * 24);
                    }
                }
                else {
                    return;
                }
            }
        }
        String sendmes = Agreement.getAgreement().userpetAgreement(good.getRgid().toString() + "|" + pet.getSid());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void userMount(Goodstable good, Mount mount) {
        long type = (long)good.getType();
        if (type == 721L) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            if ((int)mount.getUseNumber() >= Integer.parseInt(configure.getJgtqwsx())) {
                ZhuFrame.getZhuJpanel().addPrompt2("只能使用" + Integer.parseInt(configure.getJgtqwsx()) + "次筋骨提气丹");
                return;
            }
        }
        else if (type == 801L) {
            int lvl = (int)mount.getMountlvl();
            if (lvl == 100 || lvl >= 200) {
                ZhuFrame.getZhuJpanel().addPrompt2("坐骑" + mount.getMountname() + "已达最高等级100级！！");
                return;
            }
        }
        else if (type == 802L) {
            int up = 100000;
            if ((int)mount.getMountlvl() > 100) {
                ConfigureBean allConfigure2 = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item2 = allConfigure2.getAllConfigure();
                Configure configure2 = (Configure)item2.get(new BigDecimal(1));
                up = Integer.parseInt(configure2.getZqsld());
            }
            if ((int)mount.getProficiency() >= up) {
                ZhuFrame.getZhuJpanel().addPrompt2("坐骑" + mount.getMountname() + "的技能熟练度已达到峰值！！");
                return;
            }
        }
        else if (type != 2127L && type != 2128L && type != 2129L) {
            return;
        }
        String sendmes = Agreement.getAgreement().usermountAgreement(good.getRgid().toString() + "|" + mount.getMid());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void userling(Goodstable good, Lingbao lingbao) {
        long type = (long)good.getType();
        if (type == 190L) {
            if (lingbao.getBaotype().equals("法宝")) {
                ZhuFrame.getZhuJpanel().addPrompt2("法宝不能打技能");
                return;
            }
        }
        else if (type == 891L) {
            int pz = RoleLingFa.getQv(lingbao.getBaoquality());
            if (pz == 50) {
                ZhuFrame.getZhuJpanel().addPrompt2("已经达到最高品质了");
                return;
            }
            pz /= 10;
            ++pz;
            int sum = 1;
            switch (pz) {
                case 2: {
                    sum = 1;
                    break;
                }
                case 3: {
                    sum = 2;
                    break;
                }
                case 4: {
                    sum = 4;
                    break;
                }
                case 5: {
                    sum = 10;
                    break;
                }
            }
            if ((int)good.getUsetime() < sum) {
                ZhuFrame.getZhuJpanel().addPrompt2("该品质提升需要消耗" + sum + "个灵宝诸天印");
                return;
            }
        }
        else if (type != 1002L && type != 28955L) {
            return;
        }
        String sendmes = Agreement.getAgreement().userlingAgreement(good.getRgid().toString() + "|" + lingbao.getBaoid());
        SendMessageUntil.toServer(sendmes);
    }
    
    public static void useFly(Goodstable good, Fly fly) {
        long type = (long)good.getType();
        if (type == 1101L) {
            int lvl = (int)fly.getFlylvl();
            int stat = (int)fly.getFlystate();
            if (lvl >= 100) {
                ZhuFrame.getZhuJpanel().addPrompt2("飞行器" + fly.getFlyname() + "已达到最高级！");
                return;
            }
            if (stat >= 5) {
                ZhuFrame.getZhuJpanel().addPrompt2("飞行器#G【" + fly.getFlyname() + "】#Y已达到最高阶#G" + AircraftJPanel.getJieshu((int)fly.getFlystate()) + "#Y阶！");
                return;
            }
            if (type == 1002L) {
                if (lvl != 100) {
                    ZhuFrame.getZhuJpanel().addPrompt2("飞行器" + fly.getFlyname() + "等级不足");
                }
                return;
            }
        }
        Agreement.getAgreement();
        String sendmes = Agreement.useflyAgreement(good.getRgid().toString() + "|" + fly.getMid());
        SendMessageUntil.toServer(sendmes);
    }
    
    static {
        LiangHaoMouslisten.replace = -1;
        LiangHaoMouslisten.replacepath = -1;
        LiangHaoMouslisten.list = new ArrayList<>();
        LiangHaoMouslisten.goodarr = new GoodsResultArrBean();
    }
}
