package com.tool.btn;

import com.tool.image.ImageMixDeal;
import org.come.Frame.NPCJfram;
import org.come.bean.PalacePkBean;
import org.come.Frame.PalacePKJframe;
import org.come.Jpanel.TransferJpanel;
import org.come.Jpanel.JadeUpJpanel;
import javax.swing.ImageIcon;
import org.come.Jpanel.UpgradeJpanel;
import org.come.Jpanel.SuitBaptizeJpanel;
import org.come.Frame.SuitBaptizeJframe;
import org.come.Jpanel.WashJpanel;
import org.come.until.UserMessUntil;
import org.come.bean.RoleSuitBean;
import org.come.until.Goodtype;
import org.come.Jpanel.SynthesisJpanel;
import org.come.bean.LoginResult;
import org.come.until.AccessSuitMsgUntil;
import org.come.Jpanel.StorageJadeJpanel2;
import java.util.List;
import org.come.entity.Goodstable;
import org.come.bean.JadeorGoodstableBean;
import java.util.ArrayList;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.PartJade;
import org.come.until.CutButtonImage;
import org.come.until.UserData;
import org.come.bean.SuitOperBean;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.until.Music;
import org.come.Frame.ZhuFrame;
import org.come.until.MessagrFlagUntil;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.Frame.ExchangeValueJframe;
import org.come.until.FormsManagement;
import org.come.Frame.CollectionJadeJframe;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.AlreadyRecordedJpanel;
import org.come.Jpanel.PalacePKJpanel;

public class WorkshopBtn extends MoBanBtn
{
    private int caozuo;
    private PalacePKJpanel palacePKJpanel;
    private AlreadyRecordedJpanel alreadyRecordedJpanel;
    
    public WorkshopBtn(String iconpath, int type, String text, int caozuo) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.orange);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public WorkshopBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public WorkshopBtn(String iconpath, int type, String text, int caozuo, AlreadyRecordedJpanel alreadyRecordedJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.orange);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.alreadyRecordedJpanel = alreadyRecordedJpanel;
    }
    
    public WorkshopBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, AlreadyRecordedJpanel alreadyRecordedJpanel) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.alreadyRecordedJpanel = alreadyRecordedJpanel;
    }
    
    public WorkshopBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, PalacePKJpanel palacePKJpanel) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.palacePKJpanel = palacePKJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo != 1) {
            if (this.caozuo == 2) {
                this.heCheng();
            }
            else if (this.caozuo == 3) {
                this.xiLian();
            }
            else if (this.caozuo == 4) {
                this.tzShengJi();
            }
            else if (this.caozuo == 5) {
                this.yfShengJi();
            }
            else if (this.caozuo == 6) {
                this.zhuanYi();
            }
            else if (this.caozuo == 7) {
                this.duiHuan();
            }
            else if (this.caozuo == 8) {
                this.shouLu();
            }
            else if (this.caozuo == 9) {
                this.shanChu();
            }
            else if (this.caozuo == 10) {
                CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().clearInterface();
                FormsManagement.showForm(64);
            }
            else if (this.caozuo == 11) {
                ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().clearInterface();
                FormsManagement.showForm(63);
            }
            else if (this.caozuo == 12) {
                this.applyChallenge();
            }
            else if (this.caozuo == 13) {
                this.getJade();
            }
            else if (this.caozuo == 20) {
                System.exit(0);
            }
            else if (this.caozuo == 14) {
                if (this.palacePKJpanel.getWinnerType() == 2) {
                    this.palacePKJpanel.getPalacePkBean().setType(2);
                    String senmes = Agreement.getAgreement().bookofchalgAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.palacePKJpanel.getPalacePkBean()));
                    SendMessageUntil.toServer(senmes);
                }
                FormsManagement.HideForm(66);
            }
            else if (this.caozuo == 203) {
                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE5);
                ZhuFrame.getZhuJpanel().tradingMenu(true, 1);
                Music.addyinxiao("开关窗口.mp3");
            }
            else if (this.caozuo == 204) {
                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE3);
                ZhuFrame.getZhuJpanel().tradingMenu(true, 1);
                Music.addyinxiao("开关窗口.mp3");
            }
            else if (this.caozuo == 15) {
                if (this.alreadyRecordedJpanel.getLabAct().getText().equals("激活")) {
                    PartJade jade = this.alreadyRecordedJpanel.getGoodstableBean().getPartJade();
                    if (jade == null || (jade != null && jade.getJade1() == 1)) {
                        ZhuFrame.getZhuJpanel().addPrompt("#R请选择你要激活的玉符");
                        return;
                    }
                    if (new BigDecimal(50000000).compareTo(RoleData.getRoleData().getLoginResult().getGold()) > 0) {
                        ZhuFrame.getZhuJpanel().addPrompt("#R金币不足");
                        return;
                    }
                    if (200L > RoleData.getRoleData().getLoginResult().getScoretype("灵修值").longValue()) {
                        ZhuFrame.getZhuJpanel().addPrompt("#R灵修值不足，快去获取吧");
                        return;
                    }
                    if (RoleData.getRoleData().getPackRecord().isCollect(jade.getSuitid(), jade.getPartId()) != null) {
                        ZhuFrame.getZhuJpanel().addPrompt("#R没有可激活的部件");
                        return;
                    }
                    SuitOperBean operBean = new SuitOperBean();
                    jade.setJade1(0);
                    operBean.setJade(jade);
                    operBean.setType(8);
                    String senmes2 = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
                    SendMessageUntil.toServer(senmes2);
                    RoleData.getRoleData().getLoginResult().setGold(RoleData.getRoleData().getLoginResult().getGold().subtract(new BigDecimal(500000)));
                    RoleData.getRoleData().getLoginResult().setScore(UserData.Splice(RoleData.getRoleData().getLoginResult().getScore(), "灵修值=200", 3));
                    ZhuFrame.getZhuJpanel().addPrompt("消耗了200点灵修值    扣除了5000万两");
                    this.alreadyRecordedJpanel.getLabAct().setBtn(-1);
                    this.alreadyRecordedJpanel.getLabAct().setForeground(Color.GRAY);
                    this.alreadyRecordedJpanel.getLabAct().setIcon(CutButtonImage.getImage("inkImg/button/36.png", -1, -1));
                }
            }
            else if (this.caozuo == 30) {
                main();
            }
        }
    }
    
    public void duiHuan() {
        JadeorGoodstableBean bean = ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getJadeorGoodstableBean();
        String v = ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getTextNum().getText();
        int num = (v != null && !v.equals("")) ? Integer.parseInt(v) : 0;
        if (bean == null || (bean != null && bean.getType() == 0)) {
            ZhuFrame.getZhuJpanel().addPrompt2("#R请选择你要兑换的玉符或玄玉");
            return;
        }
        if (num <= 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("#R请输入你要兑换的玉符或玄玉的数量");
            return;
        }
        int val = 0;
        SuitOperBean operBean = new SuitOperBean();
        operBean.setType(7);
        if (bean.getType() == 1) {
            if (bean.getPartJade() != null && bean.getPartJade().getJade1() >= num) {
                PartJade jade = new PartJade(bean.getPartJade().getSuitid(), bean.getPartJade().getPartId());
                jade.setJade1(num);
                operBean.setJade(jade);
                val = num;
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("#R你的玉符数量不足");
                return;
            }
        }
        else if (bean.getType() == 2) {
            if (bean.getPartJade() != null && bean.getPartJade().getJade2() >= num) {
                PartJade jade = new PartJade(bean.getPartJade().getSuitid(), bean.getPartJade().getPartId());
                jade.setJade2(num);
                operBean.setJade(jade);
                val = num;
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("#R你的玉符数量不足");
                return;
            }
        }
        else if (bean.getType() == 3) {
            if (bean.getPartJade() != null && bean.getPartJade().getJade3() >= num) {
                PartJade jade = new PartJade(bean.getPartJade().getSuitid(), bean.getPartJade().getPartId());
                jade.setJade3(num);
                operBean.setJade(jade);
                val = num * 2;
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("#R你的玉符数量不足");
                return;
            }
        }
        else if (bean.getType() == 4) {
            if (bean.getPartJade() != null && bean.getPartJade().getJade4() >= num) {
                PartJade jade = new PartJade(bean.getPartJade().getSuitid(), bean.getPartJade().getPartId());
                jade.setJade4(num);
                operBean.setJade(jade);
                val = num * 2;
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("#R你的玉符数量不足");
                return;
            }
        }
        else if (bean.getType() == 5) {
            if (bean.getPartJade() != null && bean.getPartJade().getJade5() >= num) {
                PartJade jade = new PartJade(bean.getPartJade().getSuitid(), bean.getPartJade().getPartId());
                jade.setJade5(num);
                operBean.setJade(jade);
                val = num * 3;
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("#R你的玉符数量不足");
                return;
            }
        }
        else if (bean.getType() == 6) {
            if (bean.getGoodstable() != null && (int)bean.getGoodstable().getUsetime() >= num) {
                Goodstable goodstable = bean.getGoodstable();
                if (goodstable.getGoodlock() == 1) {
                    ZhuFrame.getZhuJpanel().addPrompt("#R该物品已被加锁");
                    return;
                }
                if (GoodsListFromServerUntil.isExist(goodstable)) {
                    return;
                }
                PartJade jade2 = new PartJade(-1, -1);
                jade2.setJade1(num);
                List<BigDecimal> goods = new ArrayList<>();
                goods.add(bean.getGoodstable().getRgid());
                operBean.setGoods(goods);
                operBean.setJade(jade2);
                val = num * 3;
                bean.getGoodstable().setUsetime(Integer.valueOf((int)bean.getGoodstable().getUsetime() - num));
                if ((int)bean.getGoodstable().getUsetime() <= 0) {
                    GoodsListFromServerUntil.Deletebiaoid(bean.getGoodstable().getRgid());
                }
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("#R你的九天玄玉数量不足");
                return;
            }
        }
        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(senmes);
        ZhuFrame.getZhuJpanel().addPrompt("获得了 " + val + " 点灵修值");
        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().clearInterface();
    }
    
    public void shouLu() {
        JadeorGoodstableBean bean = CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().getGoodstableBean();
        if (bean == null || (bean != null && bean.getPartJade() == null)) {
            ZhuFrame.getZhuJpanel().addPrompt("#R请选择你要收录的玉符");
            return;
        }
        int num = AccessSuitMsgUntil.getCollNum(StorageJadeJpanel2.partJade.getSuitid());
        BigDecimal sxlxz = new BigDecimal(50);
        BigDecimal money = new BigDecimal((num + 1) * 10000000);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (sxlxz.compareTo(loginResult.getScoretype("灵修值")) > 0) {
            ZhuFrame.getZhuJpanel().addPrompt("#R灵修值点数不足，快去获取灵修值吧");
            return;
        }
        if (money.compareTo(loginResult.getGold()) > 0) {
            ZhuFrame.getZhuJpanel().addPrompt("#R金币不足");
            return;
        }
        if (RoleData.getRoleData().getPackRecord().isCollect(bean.getPartJade().getSuitid(), bean.getPartJade().getPartId()) != null) {
            ZhuFrame.getZhuJpanel().addPrompt("#R已到达收录上限");
            return;
        }
        SuitOperBean operBean = new SuitOperBean();
        PartJade jade = new PartJade(bean.getPartJade().getSuitid(), bean.getPartJade().getPartId());
        jade.setJade(bean.getType(), 1);
        operBean.setJade(jade);
        operBean.setType(8);
        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(senmes);
        loginResult.setScore(UserData.Splice(loginResult.getScore(), "灵修值=" + sxlxz, 3));
        loginResult.setGold(loginResult.getGold().subtract(money));
        ZhuFrame.getZhuJpanel().addPrompt("消耗了" + sxlxz + "点灵修值       扣除了" + money + "金币..");
        CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().clearInterface();
    }
    
    public void shanChu() {
        if (this.alreadyRecordedJpanel.getListSuit() != null && this.alreadyRecordedJpanel.getListSuit().getSelectedIndex() != -1) {
            String name = (String)this.alreadyRecordedJpanel.getListSuit().getSelectedValue();
            SuitOperBean operBean = new SuitOperBean();
            PartJade jade = new PartJade((int)AccessSuitMsgUntil.returnSuitID(name), 0);
            operBean.setJade(jade);
            operBean.setType(8);
            String senmes = null;
            try {
                senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            SendMessageUntil.toServer(senmes);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt("#R请选择你要删除的套装");
        }
    }
    
    public void getJade() {
        PartJade jade = this.alreadyRecordedJpanel.getGoodstableBean().getPartJade();
        if (jade == null) {
            ZhuFrame.getZhuJpanel().addPrompt("#R请选择你要生成的玉符");
            return;
        }
        if (jade.getJade1() != 1) {
            ZhuFrame.getZhuJpanel().addPrompt("#R你还没收录过此玉符");
            return;
        }
        if (this.alreadyRecordedJpanel.getTextField().getText() == null || this.alreadyRecordedJpanel.getTextField().getText().equals("")) {
            ZhuFrame.getZhuJpanel().addPrompt("#R请输入你要生成的玉符数量");
            return;
        }
        long val = Long.parseLong(this.alreadyRecordedJpanel.getTextField().getText());
        if (val <= 0L) {
            return;
        }
        BigDecimal money = new BigDecimal(10000000L * val);
        BigDecimal sxlxz = new BigDecimal(10L * val);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (money.compareTo(loginResult.getGold()) > 0) {
            ZhuFrame.getZhuJpanel().addPrompt("#R金币不足");
            return;
        }
        if (sxlxz.compareTo(loginResult.getScoretype("灵修值")) > 0) {
            ZhuFrame.getZhuJpanel().addPrompt("#R灵修值不足，快去获取吧");
            return;
        }
        PartJade jade2 = new PartJade(jade.getSuitid(), jade.getPartId());
        jade2.setJade(1, (int)val);
        SuitOperBean operBean = new SuitOperBean();
        operBean.setJade(jade2);
        operBean.setType(9);
        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(senmes);
        loginResult.setGold(loginResult.getGold().subtract(money));
        loginResult.setScore(UserData.Splice(loginResult.getScore(), "灵修值=" + sxlxz, 3));
        ZhuFrame.getZhuJpanel().addPrompt("消耗了" + sxlxz + "点灵修值    扣除了" + money + "金币");
        this.alreadyRecordedJpanel.clearInterface();
    }
    
    public void heCheng() {
        BigDecimal big = AccessSuitMsgUntil.returnMoney(SynthesisJpanel.getGoodstableBean(), 1);
        if (big == null) {
            ZhuFrame.getZhuJpanel().addPrompt("#R请准备要合成的装备和玉符");
            return;
        }
        PartJade jade = SynthesisJpanel.getGoodstableBean().getPartJade();
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (big.compareTo(loginResult.getGold()) > 0) {
            ZhuFrame.getZhuJpanel().addPrompt("#R#R金币不足");
            return;
        }
        Goodstable good = SynthesisJpanel.getGoodstableBean().getGoodstable();
        if (good.getGoodlock() == 1) {
            ZhuFrame.getZhuJpanel().addPrompt("#R该物品已被加锁");
            return;
        }
        if (GoodsListFromServerUntil.isExist(good)) {
            return;
        }
        int type = Goodtype.EquipmentType((long)SynthesisJpanel.getGoodstableBean().getGoodstable().getType());
        if (jade.getPartId() == 11) {
            if (type != 10) {
                ZhuFrame.getZhuJpanel().addPrompt("#R装备类型和玉符不一致");
                return;
            }
        }
        else if (type != jade.getPartId()) {
            ZhuFrame.getZhuJpanel().addPrompt("#R装备类型和玉符不一致");
            return;
        }
        RoleSuitBean suit = (RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(Integer.valueOf(jade.getSuitid()));
        if (suit.getSysex() != 2) {
            String[] values = good.getValue().split("\\|");
            if (values.length > 0) {
                for (String v : values) {
                    if (v.startsWith("性别要求") && !v.split("=")[1].equals(suit.getSysex() + "")) {
                        ZhuFrame.getZhuJpanel().addPrompt("#R装备与套装的性别要求不符");
                        return;
                    }
                }
            }
        }
        SuitOperBean operBean = new SuitOperBean();
        List<BigDecimal> goods = new ArrayList<>();
        goods.add(SynthesisJpanel.getGoodstableBean().getGoodstable().getRgid());
        PartJade jade2 = new PartJade(jade.getSuitid(), jade.getPartId());
        jade2.setJade(SynthesisJpanel.getGoodstableBean().getType(), 1);
        operBean.setType(0);
        operBean.setGoods(goods);
        operBean.setJade(jade2);
        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(senmes);
        jade.deleteJade(SynthesisJpanel.getGoodstableBean().getType(), 1);
        loginResult.setGold(loginResult.getGold().subtract(big));
        SynthesisJpanel.clearInterface();
        ZhuFrame.getZhuJpanel().addPrompt("消耗了一个" + AccessSuitMsgUntil.returnJadeName(SynthesisJpanel.getGoodstableBean().getType()) + "玉符");
        ZhuFrame.getZhuJpanel().addPrompt("消耗了100万两");
    }
    
    public void xiLian() {
        if (WashJpanel.getGoodstableBean().getGoodstable() == null) {
            ZhuFrame.getZhuJpanel().addPrompt("#R请选择你要洗炼的套装");
            return;
        }
        Goodstable good = WashJpanel.getGoodstableBean().getGoodstable();
        if (good.getGoodlock() == 1) {
            ZhuFrame.getZhuJpanel().addPrompt("#R该物品已被加锁");
            return;
        }
        if (GoodsListFromServerUntil.isExist(good)) {
            return;
        }
        SuitBaptizeJpanel suitBaptizeJpanel = SuitBaptizeJframe.getSuitBaptizeJframe().getBaptizeJpanel();
        suitBaptizeJpanel.getLabtz().setIcon(GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49));
        for (int i = 0; i < 4; ++i) {
            suitBaptizeJpanel.getOldAttr()[i].setText("");
            suitBaptizeJpanel.getNewAttr()[i].setText("");
        }
        List<String> attr = AccessSuitMsgUntil.getSuitAttr(AccessSuitMsgUntil.getExtra(good.getValue(), "套装属性"));
        if (attr != null) {
            for (int index = (attr.size() >= 4) ? 4 : attr.size(), j = 0; j < index; ++j) {
                suitBaptizeJpanel.getOldAttr()[j].setText((String)attr.get(j));
            }
        }
        suitBaptizeJpanel.getBaptizeBtn2().setBtn(-1);
        suitBaptizeJpanel.getBaptizeBtn3().setBtn(-1);
        suitBaptizeJpanel.getBaptizeBtn1().setText("开始洗炼");
        FormsManagement.showForm(74);
    }
    
    public void tzShengJi() {
        BigDecimal big = AccessSuitMsgUntil.returnMoney(UpgradeJpanel.getGoodstableBean(), 2);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (big == null) {
            ZhuFrame.getZhuJpanel().addPrompt("#R请准备要升级的装备和玉符");
            return;
        }
        if (big.compareTo(loginResult.getGold()) > 0) {
            ZhuFrame.getZhuJpanel().addPrompt("#R金币不足");
            return;
        }
        PartJade jade = UpgradeJpanel.getGoodstableBean().getPartJade();
        Goodstable goodstable = UpgradeJpanel.getGoodstableBean().getGoodstable();
        if (goodstable.getGoodlock() == 1) {
            ZhuFrame.getZhuJpanel().addPrompt("#R该物品已被加锁");
            return;
        }
        if (GoodsListFromServerUntil.isExist(goodstable)) {
            return;
        }
        SuitOperBean operBean = new SuitOperBean();
        List<BigDecimal> goods = new ArrayList<>();
        goods.add(goodstable.getRgid());
        operBean.setType(3);
        operBean.setGoods(goods);
        PartJade jade2 = new PartJade(jade.getSuitid(), jade.getPartId());
        jade2.setJade(UpgradeJpanel.getGoodstableBean().getType(), 1);
        operBean.setJade(jade2);
        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(senmes);
        String Extras = AccessSuitMsgUntil.getExtra(goodstable.getValue(), "套装属性");
        String[] ss = goodstable.getValue().split("\\|");
        String newEx = AccessSuitMsgUntil.returnnewEx(1, Extras);
        ss[0] = "套装品质=" + AccessSuitMsgUntil.returnnewEx(3, Extras);
        String value = BaptizeBtn.newExtra(ss, 3, newEx);
        goodstable.setValue(value);
        UpgradeJpanel.setGoodstable(goodstable);
        UpgradeJpanel.getLabtz2().setIcon(new ImageIcon(new ImageIcon("img/item/" + goodstable.getSkin() + ".png").getImage().getScaledInstance(50, 50, 10)));
        jade.deleteJade(UpgradeJpanel.getGoodstableBean().getType(), 1);
        loginResult.setGold(loginResult.getGold().subtract(big));
        UpgradeJpanel.clearInterface();
        ZhuFrame.getZhuJpanel().addPrompt("消耗了一个" + AccessSuitMsgUntil.returnJadeName(UpgradeJpanel.getGoodstableBean().getType()) + "玉符");
        ZhuFrame.getZhuJpanel().addPrompt("消耗了1000W金币");
    }
    
    public void yfShengJi() {
        if (JadeUpJpanel.getGoodstableBean().getPartJade() == null) {
            ZhuFrame.getZhuJpanel().addPrompt("#R请选择你要升级的玉符");
            return;
        }
        BigDecimal big = AccessSuitMsgUntil.returnJadeMoney(JadeUpJpanel.getGoodstableBean().getType());
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getGold().compareTo(big) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt("#R金币不足");
            return;
        }
        PartJade jade = JadeUpJpanel.getGoodstableBean().getPartJade();
        int num = AccessSuitMsgUntil.returnJadeNum(JadeUpJpanel.getGoodstableBean().getType());
        if (num > jade.getJade(JadeUpJpanel.getGoodstableBean().getType())) {
            ZhuFrame.getZhuJpanel().addPrompt("#R你所需的玉符数量不足");
            return;
        }
        SuitOperBean operBean = new SuitOperBean();
        operBean.setType(4);
        PartJade jade2 = new PartJade(jade.getSuitid(), jade.getPartId());
        jade2.setJade(JadeUpJpanel.getGoodstableBean().getType(), num);
        operBean.setJade(jade2);
        String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
        SendMessageUntil.toServer(senmes);
        jade.deleteJade(JadeUpJpanel.getGoodstableBean().getType(), num);
        loginResult.setGold(loginResult.getGold().subtract(big));
        JadeUpJpanel.clearInterface();
        ZhuFrame.getZhuJpanel().addPrompt("消耗了" + num + "个" + AccessSuitMsgUntil.returnJadeName(JadeUpJpanel.getGoodstableBean().getType()) + "玉符..");
        ZhuFrame.getZhuJpanel().addPrompt("消耗了500W金币");
    }
    
    public void zhuanYi() {
        if (TransferJpanel.getGoodstableBean().getGoodstable() == null) {
            ZhuFrame.getZhuJpanel().addPrompt("#R请选择你要拆解的套装");
            return;
        }
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (TransferJpanel.getWorkshopBtn().getText().equals("拆解")) {
            if (loginResult.getGold().compareTo(new BigDecimal(100000)) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt("#R金币不足");
                return;
            }
            Goodstable goodstable = TransferJpanel.getGoodstableBean().getGoodstable();
            if (goodstable.getGoodlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt("#R该物品已被加锁");
                return;
            }
            if (GoodsListFromServerUntil.isExist(goodstable)) {
                return;
            }
            loginResult.setGold(loginResult.getGold().subtract(new BigDecimal(100000)));
            List<BigDecimal> goods = new ArrayList<>();
            goods.add(goodstable.getRgid());
            SuitOperBean operBean = new SuitOperBean();
            operBean.setGoods(goods);
            operBean.setType(5);
            String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
            SendMessageUntil.toServer(senmes);
            TransferJpanel.clearInterface();
            ZhuFrame.getZhuJpanel().addPrompt("消耗了10万两金币");
        }
        else if (TransferJpanel.getWorkshopBtn().getText().equals("转移")) {
            if (TransferJpanel.getGoodstable() == null) {
                ZhuFrame.getZhuJpanel().addPrompt("#R请选择你要转移属性的装备");
                return;
            }
            if (loginResult.getGold().compareTo(new BigDecimal(10000000)) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt("#R金币不足");
                return;
            }
            long value = (long)AccessSuitMsgUntil.getSxlxz(TransferJpanel.getGoodstableBean().getGoodstable().getValue());
            if (loginResult.getScoretype("灵修值").longValue() < value) {
                ZhuFrame.getZhuJpanel().addPrompt("#R灵修值不足");
                return;
            }
            Goodstable good1 = TransferJpanel.getGoodstableBean().getGoodstable();
            Goodstable good2 = TransferJpanel.getGoodstable();
            if (good1.getGoodlock() == 1 || good2.getGoodlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt("#R该物品已被加锁");
                return;
            }
            if (GoodsListFromServerUntil.isExist(good1) || GoodsListFromServerUntil.isExist(good2)) {
                return;
            }
            if (Goodtype.EquipmentType((long)good1.getType()) != Goodtype.EquipmentType((long)good2.getType())) {
                ZhuFrame.getZhuJpanel().addPrompt("#R装备类型不一致");
                return;
            }
            String[] values = good1.getValue().split("\\|");
            if (values.length > 0) {
                for (String v : values) {
                    if (v.startsWith("性别要求")) {
                        String[] values2 = good2.getValue().split("\\|");
                        if (values2.length > 0) {
                            for (String v2 : values2) {
                                if (v2.startsWith("性别要求") && !v.split("=")[1].equals(v2.split("=")[1])) {
                                    ZhuFrame.getZhuJpanel().addPrompt("#R套装与被转移装备的性别要求不符");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            loginResult.setGold(loginResult.getGold().subtract(new BigDecimal(10000000)));
            loginResult.setScore(UserData.Splice(loginResult.getScore(), "灵修值=" + value, 3));
            List<BigDecimal> goods2 = new ArrayList<>();
            goods2.add(good1.getRgid());
            goods2.add(good2.getRgid());
            SuitOperBean operBean2 = new SuitOperBean();
            operBean2.setGoods(goods2);
            operBean2.setType(6);
            String senmes2 = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean2));
            SendMessageUntil.toServer(senmes2);
            TransferJpanel.clearInterface();
            ZhuFrame.getZhuJpanel().addPrompt("消耗了1000万两");
            ZhuFrame.getZhuJpanel().addPrompt("消耗了" + value + "点灵修值");
        }
    }
    
    public void applyChallenge() {
        String name = PalacePKJframe.getPalacePKJframe().getPkJpanel().getTextName().getText();
        BigDecimal dahuabi = new BigDecimal(this.palacePKJpanel.getFundString()[0]);
        BigDecimal xianyu = new BigDecimal(this.palacePKJpanel.getFundString()[1]);
        BigDecimal exp = new BigDecimal(this.palacePKJpanel.getFundString()[2]);
        String sendStr = this.palacePKJpanel.getSendBelTextArea().getText();
        BigDecimal gold = new BigDecimal(0);
        BigDecimal xianyuMax = new BigDecimal(0);
        gold = gold.add(dahuabi);
        xianyuMax = xianyuMax.add(xianyu);
        if (gold.compareTo(new BigDecimal(10000000)) < 0 && xianyuMax.compareTo(new BigDecimal(1000)) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("大话币最低下注金额： 1千万大话币。或者仙玉最低下注金额： 1000仙玉");
            return;
        }
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getGold().compareTo(gold) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("#R大话币不足以支付投入金额");
            return;
        }
        if (loginResult.getCodecard().compareTo(xianyuMax) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("#R仙玉不足以支付投入仙玉");
            return;
        }
        if (loginResult.getExperience().compareTo(exp) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("#R经验不足以支付投入经验");
            return;
        }
        if (this.palacePKJpanel.isChallengeBool()) {
            gold = gold.add(new BigDecimal(20000000));
            if (loginResult.getGold().compareTo(gold) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("#R大话币不足以支付全服公告金额");
                return;
            }
        }
        gold = gold.add(new BigDecimal(2000000));
        if (loginResult.getGold().compareTo(gold) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("#R大话币不足以战书费用");
            return;
        }
        if (this.palacePKJpanel.isSendBellBool()) {
            xianyuMax = xianyuMax.add(new BigDecimal(100));
            if (loginResult.getCodecard().compareTo(xianyuMax) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("#R仙玉不足以支付铃铛金额");
                return;
            }
        }
        if (this.palacePKJpanel.getWinnerType() == 0) {
            if (name.equals("")) {
                ZhuFrame.getZhuJpanel().addPrompt2("#R请输入您要挑战者的名称");
                return;
            }
            if (name != null && name.equals(loginResult.getRolename())) {
                ZhuFrame.getZhuJpanel().addPrompt2("#R您不能挑战自己！！");
                return;
            }
        }
        else if (this.palacePKJpanel.getWinnerType() == 1) {
            if (gold.compareTo(new BigDecimal(1000000000)) > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("#R最高下注金额： 10亿大话币");
                return;
            }
            if (xianyu.compareTo(new BigDecimal(100000)) > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("#R仙玉最高下注金额： 10万仙玉");
                return;
            }
        }
        else if (this.palacePKJpanel.getWinnerType() != 2) {
            if (this.palacePKJpanel.getWinnerType() == 3) {}
        }
        PalacePkBean palacePkBean = new PalacePkBean();
        if (this.palacePKJpanel.getWinnerType() == 0) {
            palacePkBean.setUsername(name);
            palacePkBean.setType(0);
        }
        else if (this.palacePKJpanel.getWinnerType() == 1) {
            palacePkBean.setType(11);
            palacePkBean.setNtype(Integer.parseInt(NPCJfram.getNpcJfram().getNpcjpanel().getNpctype()));
        }
        else if (this.palacePKJpanel.getWinnerType() == 2) {
            palacePkBean.setType(1);
            palacePkBean.setPId(this.palacePKJpanel.getPalacePkBean().getPId());
        }
        else if (this.palacePKJpanel.getWinnerType() == 3) {
            palacePkBean.setNtype(Integer.parseInt(NPCJfram.getNpcJfram().getNpcjpanel().getNpctype()));
            palacePkBean.setType(1);
            palacePkBean.setPId(this.palacePKJpanel.getPalacePkBean().getPId());
        }
        palacePkBean.setGold(dahuabi);
        palacePkBean.setXianyu(xianyu);
        palacePkBean.setExp(exp);
        palacePkBean.setSendStr(this.palacePKJpanel.isSendBellBool() ? sendStr : null);
        palacePkBean.setChoices(palacePkBean.getChoices() + ((this.palacePKJpanel.isChallengeBool() ? 1 : 0) << 0));
        palacePkBean.setChoices(palacePkBean.getChoices() + ((this.palacePKJpanel.isSendBellBool() ? 1 : 0) << 0));
        String senmes = null;
        try {
            senmes = Agreement.getAgreement().bookofchalgAgreement(GsonUtil.getGsonUtil().getgson().toJson(palacePkBean));
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        SendMessageUntil.toServer(senmes);
    }
    
    public static void main() {
        int sss = ImageMixDeal.userimg.getRoleShow().getTurnAround();
        if (sss < 1) {
            ZhuFrame.getZhuJpanel().addPrompt2("暂时无法清理");
            return;
        }
        try {
            Runtime.getRuntime().exec("cmd /c start RD %TEMP% /S/Q MKDIR %TEMP% ");
        }
        catch (Exception ex) {}
    }
}
