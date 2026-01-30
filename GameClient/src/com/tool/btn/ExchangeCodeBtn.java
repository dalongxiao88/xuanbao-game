package com.tool.btn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.come.entity.RoleSummoning;
import org.come.until.GsonUtil;
import org.come.bean.ChangeRoleNameBean;
import org.come.until.Util;
import com.tool.image.ImageMixDeal;
import org.come.bean.PrivateData;
import org.come.entity.Goodstable;
import org.come.bean.LoginResult;
import com.tool.role.RoleProperty;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleReborn;
import com.tool.role.RoleSkill;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.ExchangeAwardJpanel;

public class ExchangeCodeBtn extends MoBanBtn
{
    private ExchangeAwardJpanel awardJpanel;
    
    public ExchangeCodeBtn(String iconpath, int type, String text, ExchangeAwardJpanel awardJpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.awardJpanel = awardJpanel;
    }
    
    public ExchangeCodeBtn(String iconpath, int type, String text, ExchangeAwardJpanel awardJpanel, String SM) {
        super(iconpath, type, UIUtils.COLOR_BLACK);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.awardJpanel = awardJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.getText().equals("取 消")) {
            FormsManagement.HideForm(73);
            return;
        }
        String text = this.awardJpanel.getTextCode().getText();
        if (text == null || text.equals("")) {
            ZhuFrame.getZhuJpanel().addPrompt("输入为空");
            return;
        }
        if (this.getText().equals("兑 换")) {
            String senmes = Agreement.exchangeGoodsAgreement(text);
            SendMessageUntil.toServer(senmes);
        }
        else if (this.getText().equals("修 改")) {
            if (this.awardJpanel.getRgid() != null) {
                ChangeNameupload(text, this.awardJpanel.getRgid());
            }
        }
        else if (this.getText().equals("修 正")) {
            if (this.awardJpanel.getRgid() != null) {
                ChangeBorn(text, this.awardJpanel.getRgid());
            }
        }
        else if (this.getText().equals("解 封")) {
            this.unJF(text, this.awardJpanel.getRgid());
        }
        else if (this.getText().equals("解 禁")) {
            this.unJJ(text, this.awardJpanel.getRgid());
        }
        else if (this.getText().equals("招 募")) {
            this.recruit(text);
        }
        else if (this.getText().equals("充 值")) {
            this.unJF(text, this.awardJpanel.getRgid());
        }else if (getText().equals("确 认")) {
            unJF22222(text,awardJpanel.getGood());
        }

        else if (getText().equals("确 实")) {
            unJF222221(text,awardJpanel.getGood2(),awardJpanel.getPet());
        }
    }
    public void unJF222221(String ChangeBorn, Goodstable good, RoleSummoning pet) {
        int number = Integer.parseInt(ChangeBorn);

        if(number>good.getUsetime()) {number=good.getUsetime();}

        // System.out.println(number);
        String sendmes = Agreement.getAgreement().userpetAgreement(good.getRgid().toString() + "|" + pet.getSid()+"!"+number);
        SendMessageUntil.toServer(sendmes);


        good.goodxh(number);

        FormsManagement.HiddenDisplay(73);

        //GoodsMouslisten.setaasdf(number);
    }
    public void unJF22222(String ChangeBorn,Goodstable good) {
        int number = Integer.parseInt(ChangeBorn);
        //   System.out.println(number);  System.out.println(good.getUsetime());
        if(number>good.getUsetime()) {number=good.getUsetime();}

        String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString()+"!"+number);
        SendMessageUntil.toServer(sendmes);
        good.goodxh(1*number);

        FormsManagement.HiddenDisplay(73);
    }
    public void unJF(String ChangeBorn, BigDecimal rgid) {
        String sendmes = Agreement.getAgreement().userAgreement(rgid + "|" + ChangeBorn);
        SendMessageUntil.toServer(sendmes);
        FormsManagement.HiddenDisplay(73);
    }
    
    public void unJJ(String ChangeBorn, BigDecimal rgid) {
        if (!ChangeBorn.matches("[0-9]+")) {
            ZhuFrame.getZhuJpanel().addPrompt2("请输入数字");
            return;
        }
        String sendmes = Agreement.getAgreement().userAgreement(rgid + "|" + ChangeBorn);
        SendMessageUntil.toServer(sendmes);
        FormsManagement.HiddenDisplay(73);
    }
    
    public static void ChangeBorn(String ChangeBorn, BigDecimal rgid) {
        String[] v = ChangeBorn.trim().split("-");
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (v.length > loginResult.getTurnAround() || v.length > 3) {
            ZhuFrame.getZhuJpanel().addPrompt2("只能输入于自身相同的转生次数");
            return;
        }
        int[] vv = getbz(v);
        if (vv == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("输入格式有误");
            return;
        }
        String yuben = null;
        for (int i = 0; i < vv.length; ++i) {
            yuben = RoleReborn.reborn(RoleSkill.getRoleSkill().getAllSkill(vv[i], i * 5000 + 10000), yuben);
        }
        Goodstable goodstable = GoodsListFromServerUntil.Uerbiaoid(rgid);
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("更改修正成功");
            GoodsMouslisten.gooduse(goodstable, 1);
            RoleProperty.Resetborn(null, yuben);
            PrivateData data = RoleData.getRoleData().getPrivateData();
            data.setBorn(yuben);
            String mes = Agreement.getAgreement().rolechangeAgreement("XZ=" + ChangeBorn.trim());
            SendMessageUntil.toServer(mes);
            FormsManagement.HideForm(73);
        }
    }
    
    public static int[] getbz(String[] v) {
        try {
            int[] a = new int[v.length];
            for (int i = 0; i < v.length; ++i) {
                a[i] = Integer.parseInt(v[i]);
                if (a[i] < 1 || a[i] > 10) {
                    return null;
                }
            }
            return a;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static void ChangeNameupload(String ChangeName, BigDecimal rgid) {
        ChangeName = ChangeName.trim();
        int size = 0;
        char[] nz = ChangeName.toCharArray();
        for (int i = 0; i < nz.length; ++i) {
            String a = nz[i] + "";
            if (a.getBytes().length == 1) {
                ++size;
            }
            else {
                size += 2;
            }
        }
        if (size > 14) {
            ZhuFrame.getZhuJpanel().addPrompt2("名称太长");
            return;
        }
        if (ImageMixDeal.userimg.getRoleShow().getTeamInfo() == null || ImageMixDeal.userimg.getRoleShow().getTeamInfo().equals("")) {
            if (!ChangeName.equals(ImageMixDeal.userimg.getRoleShow().getRolename())) {
                if (special(ChangeName)) {
                    if (!Util.isIllegal(ChangeName)) {
                        ZhuFrame.getZhuJpanel().addPrompt("名称中包含非法字符！！");
                        return;
                    }
                    ChangeRoleNameBean bean = new ChangeRoleNameBean();
                    bean.setOldName(ImageMixDeal.userimg.getRoleShow().getRolename());
                    bean.setNewName(ChangeName);
                    bean.setRgid(rgid);
                    String sendmes = Agreement.getAgreement().ChangeRoleNameAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                    SendMessageUntil.toServer(sendmes);
                    FormsManagement.HideForm(73);
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("修改后的名字不能有特殊符号");
                }
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("修改后的名字不能和自己重复");
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("组队状态下不能使用改名卡");
        }
    }
    
    public static boolean special(String ChangeName) {
        String[] a = { "!", "|", "*", "&", "@", "#", "$", "%", "^", "/" };
        for (int i = 0; i < a.length; ++i) {
            if (ChangeName.indexOf(a[i]) != -1) {
                return false;
            }
        }
        return true;
    }
    
    public void recruit(String text) {
        if (!this.isNumeric(text)) {
            ZhuFrame.getZhuJpanel().addPrompt2("请输入正确的数字");
            return;
        }
        SendMessageUntil.toServer(Agreement.getAgreement().hjslAgreement("Z" + text));
        FormsManagement.HideForm(73);
    }
    
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}
