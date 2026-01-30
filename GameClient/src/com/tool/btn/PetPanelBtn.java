package com.tool.btn;

import java.lang.reflect.Field;
import org.come.Frame.RolePetResistanceJframe;
import org.come.Jpanel.GoodsMsgJpanel;
import com.tool.role.RoleLingFa;
import org.come.model.Lingbao;
import com.tool.pet.PetProperty;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.Frame.AlchemyJframe;
import org.come.until.*;
import org.come.mouslisten.GoodsMouslisten;
import com.updateNew.MyIsif;
import com.tool.PanelDisplay.PetPanelShow;
import org.come.model.PalData;
import org.come.entity.Pal;
import org.come.Jpanel.PartnerMainJpanel;
import org.come.entity.RoleSummoning;
import java.util.List;
import come.tool.Fighting.Fightingimage;
import org.come.Frame.PartnerJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.Frame.PetPrderJframe;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.Frame.PetEquipmentJframe;
import org.come.Jpanel.ZhuJpanel;
import org.come.Frame.PetLxJframe;
import org.come.mouslisten.ChosePetLxMouslisten;
import org.come.mouslisten.ChosePetSkillsMouslisten;
import org.come.Frame.DepositListJframe;
import com.tool.role.RoleData;
import com.tool.imagemonitor.FightingMonitor;
import come.tool.Fighting.TypeState;
import come.tool.Fighting.FightingMixDeal;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.SummonBaoJpanel;
import org.come.Jpanel.SummonPetJpanel;
import org.come.Jpanel.TestPetJpanel;

import javax.swing.*;

import static org.come.Jpanel.TestPetJpanel.*;
import static org.come.Jpanel.TestPetJpanel.dir;

public class PetPanelBtn extends MoBanBtn
{
    private TestPetJpanel jpanel;
    private SummonPetJpanel summonPetJpanel;
    private SummonBaoJpanel summonBaoJpanel;
    private Integer typeBtn;
    
    public PetPanelBtn(String iconpath, int type, Integer typeBtn, TestPetJpanel jpanel) {
        super(iconpath, type);
        this.typeBtn = typeBtn;
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.yellow);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.jpanel = jpanel;
    }
    
    public PetPanelBtn(String iconpath, int type, Color[] colors, Font font, String text, Integer typeBtn, TestPetJpanel jpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.typeBtn = typeBtn;
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.jpanel = jpanel;
    }
    
    public PetPanelBtn(String iconpath, int type, Color[] colors, Font font, String text, Integer typeBtn, SummonPetJpanel summonPetJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.typeBtn = typeBtn;
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.summonPetJpanel = summonPetJpanel;
    }
    
    public PetPanelBtn(String iconpath, int type, Color[] colors, Font font, String text, Integer typeBtn, SummonBaoJpanel summonBaoJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.typeBtn = typeBtn;
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.summonBaoJpanel = summonBaoJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.summonPetJpanel != null) {
            if (this.summonPetJpanel.getChosePetMes() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("您还没有选中召唤兽呢!!");
                return;
            }
            if ((int)this.typeBtn == 10) {
                this.petZhanshi(this.summonPetJpanel.getChosePetMes());
            }
            else if (FightingMixDeal.State == 1 && (int)this.typeBtn == 11) {
                Fightingimage fightingimage = FightingMixDeal.getdata(0);
                List<TypeState> data = fightingimage.getFightingManData().cxxx("召唤兽");
                int sid = this.summonPetJpanel.getChosePetMes().getSid().intValue();
                for (int i = 0; i < data.size(); ++i) {
                    if (((TypeState)data.get(i)).getState() == 0 && sid == Integer.parseInt(((TypeState)data.get(i)).getType())) {
                        if (FightingMixDeal.State != 1) {
                            return;
                        }
                        FormsManagement.HideForm(710);
                        String summoningname = this.summonPetJpanel.getChosePetMes().getSummoningname();
                        FightingMonitor.FightingOperation(FightingBtn.SpellGenerate("召唤&" + ((TypeState)data.get(i)).getType() + "&" + summoningname));
                        FightingMonitor.operateEnd();
                        return;
                    }
                }
                ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽无法召唤");
            }
        }
        else if (this.summonBaoJpanel != null) {
            if (this.summonBaoJpanel.getChoseLingBao() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("您还没有选中灵宝呢!!");
                return;
            }
            if ((int)this.typeBtn == 10) {
                this.baoZhanshi(this.summonBaoJpanel.getChoseLingBao());
            }
            else if (FightingMixDeal.State == 1 && (int)this.typeBtn == 11) {
                Fightingimage fightingimage = FightingMixDeal.getdata(0);
                List<TypeState> data = fightingimage.getFightingManData().cxxx("灵宝");
                int sid = this.summonBaoJpanel.getChoseLingBao().getBaoid().intValue();
                for (int i = 0; i < data.size(); ++i) {
                    if (((TypeState)data.get(i)).getState() == 0 && sid == Integer.parseInt(((TypeState)data.get(i)).getType())) {
                        if (FightingMixDeal.State != 1) {
                            return;
                        }
                        FormsManagement.HideForm(710);
                        String baoname = this.summonBaoJpanel.getChoseLingBao().getBaoname();
                        FightingMonitor.FightingOperation(FightingBtn.SpellGenerate("召唤灵宝&" + ((TypeState)data.get(i)).getType() + "&" + baoname));
                        FightingMonitor.operateEnd();
                        return;
                    }
                }
                ZhuFrame.getZhuJpanel().addPrompt2("这只灵宝无法召唤");
            }
        }
        else if ((int)this.typeBtn == 23) {
            if (UserStallUntil.isStall()) {
                return;
            }
            RoleData roleData = RoleData.getRoleData();
            DepositListJframe.getDepositListJframe().getDepositListJpanel().init(roleData.getDepositBbName());
            FormsManagement.showForm(63333);
            return;
        }
        else {
            if (UserMessUntil.getChosePetMes() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("您还没有选中召唤兽呢!!");
                return;
            }
            if ((int)this.typeBtn == 1 && FightingMixDeal.State == 0) {
                this.changname();
            }
            else if ((int)this.typeBtn == 2 && FightingMixDeal.State == 0) {
                this.canzhan();
            }
            else if ((int)this.typeBtn == 3 && FightingMixDeal.State == 0) {
                try {
                    this.xiuxi();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            else if ((int)this.typeBtn == 4 && FightingMixDeal.State == 0) {
                try {
                    this.jiadian();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            else if ((int)this.typeBtn == 5 && FightingMixDeal.State == 0) {
                this.xunyang();
            }
            else if ((int)this.typeBtn == 6) {
                ChosePetSkillsMouslisten.refreshPetSkills();
                FormsManagement.showForm(18);
            }
            else if ((int)this.typeBtn == 601) {
                ChosePetLxMouslisten.refreshPetSkills();
                PetLxJframe.getPetLxJframe().getLxPanel().changePanel(0);
                FormsManagement.showForm(601);
            }
            else if ((int)this.typeBtn == 7 && FightingMixDeal.State == 0) {
                ZhuJpanel.setUseGoodsType(1);
                PetEquipmentJframe.getPetEquipmentJframe().getEquipmentJpanel().showPet(UserMessUntil.getChosePetMes());
                FormsManagement.HideForm(2);
                FormsManagement.showForm(67);
            }
            else if ((int)this.typeBtn == 8 && FightingMixDeal.State == 0) {
                this.lianyao();
            }
            else if ((int)this.typeBtn == 9 && FightingMixDeal.State == 0) {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                if (UserMessUntil.getChosePetMes() == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有选中的召唤兽");
                    return;
                }
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.Release, UserMessUntil.getChosePetMes(), "#W确定要将召唤兽:#G" + UserMessUntil.getChosePetMes().getSummoningname() + "#W放生吗?  #R放生后无法找回三思而后行");
            }
            else if ((int)this.typeBtn == 10) {
                this.petZhanshi(UserMessUntil.getChosePetMes());
            }
            else if ((int)this.typeBtn == 20) {
                this.changePet(true);
            }
            else if ((int)this.typeBtn == 21) {
                this.changePet(false);
            }
            else if ((int)this.typeBtn == 25) {
                RoleData roleData = RoleData.getRoleData();
                RoleData.getRoleData().addOrderPet(UserMessUntil.getPetListTable());
                PetPrderJframe.getPetPrderJframe().getPetPrderJpanel().init(roleData.getOrderPetName());
                FormsManagement.showForm(621);
            }
            else if ((int)this.typeBtn == 24) {
                if (UserStallUntil.isStall()) {
                    return;
                }
                RoleSummoning roleSummoning = UserMessUntil.getChosePetMes();
                if (roleSummoning.getCommodityId() != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽在摆摊中！无法寄存！");
                    return;
                }
                if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(roleSummoning.getSid()) == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽已在参战中！无法寄存！");
                    return;
                }
                PetAddPointMouslisten.clearWindow();
                UserMessUntil.unSetChosePetMes();
                String mes = Agreement.getAgreement().petDepositAction("deposit=" + roleSummoning.getSid());
                SendMessageUntil.toServer(mes);
            }
            else if (FightingMixDeal.State == 1 && (int)this.typeBtn == 11) {
                Fightingimage fightingimage = FightingMixDeal.getdata(0);
                List<TypeState> data = fightingimage.getFightingManData().cxxx("召唤兽");
                int sid = UserMessUntil.getChosePetMes().getSid().intValue();
                for (int i = 0; i < data.size(); ++i) {
                    if (((TypeState)data.get(i)).getState() == 0 && sid == Integer.parseInt(((TypeState)data.get(i)).getType())) {
                        if (FightingMixDeal.State != 1) {
                            return;
                        }
                        FormsManagement.HideForm(6);
                        String summoningname = this.summonPetJpanel.getChosePetMes().getSummoningname();
                        FightingMonitor.FightingOperation(FightingBtn.SpellGenerate("召唤&" + ((TypeState)data.get(i)).getType() + "&" + summoningname));
                        FightingMonitor.operateEnd();
                        return;
                    }
                }
                ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽无法召唤");
            }
            else if ((int)this.typeBtn == 110 && FightingMixDeal.State == 0) {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                if (UserMessUntil.getChosePetMes() == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有选中的召唤兽");
                    return;
                }
                RoleSummoning pet = UserMessUntil.getChosePetMes();
                PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
                if (mainJpanel.getPalDataChooseId() < 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选择一个伙伴");
                    return;
                }
                Pal pidGetPal = mainJpanel.pidGetPal(mainJpanel.getPalDataChooseId());
                if (pidGetPal == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请选中一个伙伴");
                    return;
                }
                int TurnRount = AnalysisString.petTurnRount((int)pet.getGrade());
                if (TurnRount < 1) {
                    ZhuFrame.getZhuJpanel().addPrompt2("宝宝一转以后才可以分配给召唤兽哦！");
                    return;
                }
                PalData palData = UserMessUntil.getPalData(pidGetPal.getpId());
                String name = palData.getName();
                if (pidGetPal.getSummoning() != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("伙伴【#G" + name + "#Y】已有召唤兽，请先取回");
                    return;
                }
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.palAddPet, pet, "#W确定要将召唤兽:#G" + pet.getSummoningname() + "#W分配到伙伴:【#G" + name + "#W】吗?");
            }
            else if ((int)this.typeBtn == 778) {
                //切换大小
                if (jpanel != null) {
                    if (jpanel.isBigOsmall()) {
                        jpanel.setBigOsmall(false);
                        jpanel.ChangeJpanel();
                    } else  {
                        jpanel.setBigOsmall(true);
                        jpanel.ChangeJpanel();
                    }
                }
            }
            else if ((int)this.typeBtn == 779) {
                //切换大小
                if (jpanel != null) {
                    if (TouOrName) {
                        TouOrName = false;
                        jpanel.getListpet().setFixedCellHeight(22);
                        if (MyIsif.getStyle().equals("水墨")) {
                            jpanel.getBtnTouOrName().setIcons(CutButtonImage.cuts("inkImg/button/86.png"));
                        } else {
                            jpanel.getBtnTouOrName().setIcons(CutButtonImage.cuts("inkImg/hongmu/HM86.png"));
                        }
                    } else  {
                        TouOrName = true ;
                        jpanel.getListpet().setFixedCellHeight(38);
                        if (MyIsif.getStyle().equals("水墨")) {
                            jpanel.getBtnTouOrName().setIcons(CutButtonImage.cuts("inkImg/button/87.png"));
                        } else {
                            jpanel.getBtnTouOrName().setIcons(CutButtonImage.cuts("inkImg/hongmu/HM87.png"));
                        }
                    }
                    jpanel.getBtnTouOrName().repaint();
                }
            }
            //召唤兽新界面动作选择
            else if ((int)this.typeBtn == 780) {
                change = true;
                if (action == 9) {
                    action = 5 ;
                    ZhuFrame.getZhuJpanel().addPrompt2("#G召唤兽动作切换为：#Y法术");
                } else {
                    if (action == 5) {
                        action = 6 ;
                        ZhuFrame.getZhuJpanel().addPrompt2("#G召唤兽动作切换为：#Y防御");
                    } else {
                        if (action == 6) {
                            action = 7 ;
                            ZhuFrame.getZhuJpanel().addPrompt2("#G召唤兽动作切换为：#Y待机");
                        } else {
                            action = 9 ;
                            ZhuFrame.getZhuJpanel().addPrompt2("#G召唤兽动作切换为：#Y攻击");
                        }
                    }
                }
            }
            else if ((int)this.typeBtn == 781) {
                //"4", "0", "7", "3", "6", "2", "5", "1"
                change = true;
                action = 2 ;
                if (dir == 1) {
                    dir = 0 ;
                } else {
                    if (dir == 0) {
                        dir = 3 ;
                    } else {
                        if (dir == 3) {
                            dir = 2 ;
                        } else {
                            dir = 1;
                        }
                    }
                }
            }
            else if ((int)this.typeBtn == 782) {
                change = true;
                action = 2 ;
                if (dir == 1) {
                    dir = 2 ;
                } else {
                    if (dir == 2) {
                        dir = 3 ;
                    } else {
                        if (dir == 3) {
                            dir = 0;
                        } else {
                            dir = 1;
                        }
                    }
                }
            }
        }
    }
    
    public void changname() {
        try {
            if (UserMessUntil.getChosePetMes() != null) {
                String lastname = UserMessUntil.getChosePetMes().getSummoningname();
                if (TestPetJpanel.getLabname().getText() == null || TestPetJpanel.getLabname().getText().equals("")) {
                    ZhuFrame.getZhuJpanel().addPrompt("请输入召唤兽" + lastname + "的新名字！");
                    return;
                }
                if (TestPetJpanel.getLabname().getText().length() > 9) {
                    ZhuFrame.getZhuJpanel().addPrompt("长?那是一种专属");
                    return;
                }
                if (TestPetJpanel.getLabname().getText().indexOf(43) >= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt("召唤兽名字不能含有+号");
                    return;
                }
                UserMessUntil.getChosePetMes().setSummoningname(TestPetJpanel.getLabname().getText().trim());
                String mes = Agreement.getAgreement().petchangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(UserMessUntil.getChosePetMes()));
                SendMessageUntil.toServer(mes);
                ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽改名为:" + UserMessUntil.getChosePetMes().getSummoningname());
                TestPetJpanel.showStar();
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt("请选择你要改名的召唤兽！");
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
    public void canzhan() {
        String path = "img/head/p" + UserMessUntil.getChosePetMes().getSummoningskin() + ".png";
        ZhuJpanel.setLabpetimg(path);
        String mes = Agreement.getAgreement().rolechangeAgreement("P" + UserMessUntil.getChosePetMes().getSid().toString());
        SendMessageUntil.toServer(mes);
        RoleData.getRoleData().getLoginResult().setSummoning_id(UserMessUntil.getChosePetMes().getSid());
        Article.souxie(UserMessUntil.getChosePetMes());
        PetPanelShow.ShowMesForJpanel();
        this.jpanel.getBtnwar().setText("休息");
        if (MyIsif.getStyle().equals("水墨")) {
            this.jpanel.getBtnwar().setColors(UIUtils.COLOR_BLACK);
        }
        this.jpanel.getBtnwar().setTypeBtn(Integer.valueOf(3));
        TestPetJpanel.showStar();
    }
    
    public static void canzhan1() {
        String path = "img/head/p" + UserMessUntil.getChosePetMes().getSummoningskin() + ".png";
        ZhuJpanel.setLabpetimg(path);
        Article.souxie(UserMessUntil.getChosePetMes());
        PetPanelShow.ShowMesForJpanel();
        TestPetJpanel.showStar();
    }
    
    public void xiuxi() throws Exception {
        String path = "resource/jiuUI/chongwudaiji.png";
        ZhuJpanel.setLabpetimg(null);
        String mes = Agreement.getAgreement().rolechangeAgreement("P");
        SendMessageUntil.toServer(mes);
        RoleData.getRoleData().getLoginResult().setSummoning_id(null);
        this.jpanel.getBtnwar().setText("参战");
        if (MyIsif.getStyle().equals("水墨")) {
            this.jpanel.getBtnwar().setColors(UIUtils.COLOR_BLACK);
        }
        this.jpanel.getBtnwar().setTypeBtn(Integer.valueOf(2));
        TestPetJpanel.showStar();
    }
    
    public void jiadian() throws Exception {
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        int Jbone = Integer.parseInt(TestPetJpanel.getLabrootbone().getText());
        int JSpir = Integer.parseInt(TestPetJpanel.getLabintelligence().getText());
        int JPower = Integer.parseInt(TestPetJpanel.getLabpower().getText());
        int JSpeed = Integer.parseInt(TestPetJpanel.getLabspeed().getText());
        int JCalm = Integer.parseInt(TestPetJpanel.getLabconcentrate().getText());
        int canpoint = pet.getCanpoint();
        canpoint -= Jbone;
        canpoint -= JSpir;
        canpoint -= JPower;
        canpoint -= JSpeed;
        canpoint -= JCalm;
        canpoint += (int)pet.getZBone();
        canpoint += (int)pet.getZSpir();
        canpoint += (int)pet.getZPower();
        canpoint += (int)pet.getZSpeed();
        canpoint += (int)pet.getZCalm();
        if (canpoint >= 0 && (Jbone > (int)pet.getZBone() || JSpir > (int)pet.getZSpir() || JPower > (int)pet.getZPower() || JSpeed > (int)pet.getZSpeed() || JCalm > (int)pet.getZCalm())) {
            pet.setZBone(Integer.valueOf(Jbone));
            pet.setZSpir(Integer.valueOf(JSpir));
            pet.setZPower(Integer.valueOf(JPower));
            pet.setZSpeed(Integer.valueOf(JSpeed));
            pet.setZCalm(Integer.valueOf(JCalm));
            PetAddPointMouslisten.showPetValue();
            String mes = Agreement.getAgreement().petchangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(pet));
            SendMessageUntil.toServer(mes);
        }
    }
    
    public void xunyang() {
        int a = 0;
        for (int i = 0; i < GoodsListFromServerUntil.getGoodslist().length; ++i) {
            if ((int)UserMessUntil.getChosePetMes().getFaithful() >= 100) {
                ZhuFrame.getZhuJpanel().addPrompt2("召唤兽 " + UserMessUntil.getChosePetMes().getSummoningname() + "的忠诚度已满！！！");
                return;
            }
            if (GoodsListFromServerUntil.getGoodslist()[i] != null && (long)GoodsListFromServerUntil.getGoodslist()[i].getType() == 49L) {
                UserMessUntil.getChosePetMes().addFaithful(Integer.valueOf(Integer.parseInt(GoodsListFromServerUntil.getGoodslist()[i].getValue())));
                ZhuFrame.getZhuJpanel().addPrompt2("召唤兽 " + UserMessUntil.getChosePetMes().getSummoningname() + "增加了" + GoodsListFromServerUntil.getGoodslist()[i].getValue() + "点忠诚度啦！！！");
                GoodsListFromServerUntil.getGoodslist()[i].goodxh(1);
                GoodsMouslisten.gooduse(GoodsListFromServerUntil.getGoodslist()[i], 1);
                if ((int)GoodsListFromServerUntil.getGoodslist()[i].getUsetime() <= 0) {
                    GoodsListFromServerUntil.Deleted(i);
                }
                SendRoleAndRolesummingUntil.sendRoleSumming(UserMessUntil.getChosePetMes());
                TestPetJpanel.getLabloyalty().setText(UserMessUntil.getChosePetMes().getFaithful().toString());
                a = 1;
            }
        }
        if (a == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("您没有足够宠物口粮了！！！");
        }
    }
    
    public void lianyao() {
        AlchemyJframe.getAlchemyjframe().getalchemyJpanel().getListModel().removeAllElements();
        if (UserMessUntil.getPetListTable() != null) {
            for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
                AlchemyJframe.getAlchemyjframe().getalchemyJpanel().getListModel().add(i, ((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSummoningname());
            }
        }
        FormsManagement.upgradForm(17);
    }
    
    public void petZhanshi(RoleSummoning chosePetMes) {
        if (chosePetMes == null) {
            FrameMessageChangeJpanel.addtext(5, "请选择你要查看抗性的召唤兽！", null, null);
        }
        else if (!FormsManagement.getframe(58).isVisible()) {
            PetProperty.ShowQl(chosePetMes);
            FormsManagement.showForm(58);
        }
        else {
            FormsManagement.HideForm(58);
        }
    }
    
    public void baoZhanshi(Lingbao lingbao) {
        if (lingbao == null) {
            FrameMessageChangeJpanel.addtext(5, "请选择你要查看抗性的灵宝！", null, null);
        }
        RoleLingFa.showProperty(lingbao);
    }
    
    public static void showRolesumming(Object model) {
        int a = -1;
        int doub = -1;
        int jishu = -1;
        try {
            clearShuXingView();
            for (Field field : model.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (Double.parseDouble(field.get(model).toString()) != 0.0) {
                    String sx = null;
                    sx = JpanelOnJalbelBtn.getQuaralyPersonalName(field.getName());
                    String ping = GoodsMsgJpanel.tianjia(sx);
                    if (sx.startsWith("抗灵宝")) {
                        ping = "";
                    }
                    String numbers = sx.substring(sx.length() - 1, sx.length());
                    Integer number = Integer.valueOf(numbers);
                    String shuxingName = sx.substring(0, sx.length() - 2);
                    if (++a % 2 == 0) {
                        ++doub;
                        RolePetResistanceJframe.getResistancejframe().getResistancejpanel();
                        if (ping.equals("%")) {
                            RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[(int)number - 1].getDlm().addElement(shuxingName + ":" + String.format("%.1f", new Object[] { Double.valueOf(Double.parseDouble(field.get(model).toString())) }) + ping);
                        }
                        else {
                            RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[(int)number - 1].getDlm().addElement(shuxingName + ":" + (int)Double.parseDouble(field.get(model).toString()) + ping);
                        }
                    }
                    else {
                        ++jishu;
                        RolePetResistanceJframe.getResistancejframe().getResistancejpanel();
                        if (ping.equals("%")) {
                            RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[(int)number - 1].getDlm1().addElement(shuxingName + ":" + String.format("%.1f", new Object[] { Double.valueOf(Double.parseDouble(field.get(model).toString())) }) + ping);
                        }
                        else {
                            RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[(int)number - 1].getDlm1().addElement(shuxingName + ":" + (int)Double.parseDouble(field.get(model).toString()) + ping);
                        }
                    }
                }
            }
            changViewSize();
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
        catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }
    
    public static void changViewSize() {
        for (int i = 0; i < RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel().length; i++) {
            Boolean open = RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getOpen();
            int num = 0;
            if (open) {
                int leftNum = RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i]
                        .getDlm().getSize();
                int rightNum = RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i]
                        .getDlm1().getSize();
                RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getListNo1()
                        .setBounds(5, 26, 135, leftNum * 20);
                RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getListNo2()
                        .setBounds(145, 26, 140, rightNum * 20);
                num = leftNum > rightNum ? leftNum : rightNum;

            } else {
                num = 0;
            }
            num = num > 0 ? (num * 20 + 24) : 24;
            int y = 0;
            for (int j = 0; j < i; j++) {
                y += RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[j]
                        .getHeight();
            }
            RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].setBounds(0, y,
                    300, num);
        }
        int y = 0;
        for (int i = 0; i < RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel().length; i++) {
            y += RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getHeight();
        }
        // RoleResistanceJframe.getResistancejframe().getResistancejpanel().setBounds(RoleResistanceJframe.getResistancejframe().getResistancejpanel().getX(),RoleResistanceJframe.getResistancejframe().getResistancejpanel().getY(),290,y);
        RolePetResistanceJframe.getResistancejframe().getResistancejpanel().setSize(300, y+3);
        RolePetResistanceJframe.getResistancejframe().setSize(300, y);
    }
    
    public static void clearShuXingView() {
        for (int i = 0; i < RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel().length; ++i) {
            RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getDlm().removeAllElements();
            RolePetResistanceJframe.getResistancejframe().getResistancejpanel().getShuXingJpanel()[i].getDlm1().removeAllElements();
        }
    }
    
    private void changePet(boolean b) {
        if (b && RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(UserMessUntil.getChosePetMes().getSid()) == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("参战召唤兽不可以观看！");
            return;
        }
        UserMessUntil.getChosePetMes().setShow(b);
        String mes = Agreement.getAgreement().petchangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(UserMessUntil.getChosePetMes()));
        SendMessageUntil.toServer(mes);
        TestPetJpanel.showStar();
    }
    
    public Integer getTypeBtn() {
        return this.typeBtn;
    }
    
    public void setTypeBtn(Integer typeBtn) {
        this.typeBtn = typeBtn;
    }
}
