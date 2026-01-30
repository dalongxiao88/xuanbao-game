package com.tool.btn;

import org.come.entity.Goodstable;
import java.util.List;
import org.come.bean.QualityClBean;
import org.come.until.FormsManagement;
import org.come.until.UserData;
import java.math.BigDecimal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.Jpanel.WashJpanel;
import java.util.ArrayList;
import org.come.bean.SuitOperBean;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.SuitBaptizeJpanel;

public class BaptizeBtn extends MoBanBtn
{
    private int caozuo;
    private static String newEx;
    private SuitBaptizeJpanel suitBaptizeJpanel;
    public static String[] Extras;
    
    public BaptizeBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, SuitBaptizeJpanel suitBaptizeJpanel) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.suitBaptizeJpanel = suitBaptizeJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 1) {
            if (this.suitBaptizeJpanel.getBaptizeBtn1().getText().equals("开始洗炼")) {
                this.suitBaptizeJpanel.getBaptizeBtn1().setText("再次洗炼");
            }
            long money = 100000L;
            int index = 2;
            if (SuitBaptizeJpanel.saveOld) {
                money = 1000000L;
                index = 2;
                this.suitBaptizeJpanel.getBaptizeBtn2().setBtn(1);
                this.suitBaptizeJpanel.getBaptizeBtn3().setBtn(1);
            }
            else {
                money = 800000L;
                index = 1;
                this.suitBaptizeJpanel.getBaptizeBtn2().setBtn(-1);
                this.suitBaptizeJpanel.getBaptizeBtn3().setBtn(-1);
            }
            if (RoleData.getRoleData().getLoginResult().getGold().longValue() < money) {
                ZhuFrame.getZhuJpanel().addPrompt("金币不足..");
                return;
            }
            if (RoleData.getRoleData().getLoginResult().getScoretype("灵修值").longValue() < 30L) {
                ZhuFrame.getZhuJpanel().addPrompt("灵修值不足30点..");
                return;
            }
            SuitOperBean operBean = new SuitOperBean();
            List<BigDecimal> goods = new ArrayList<>();
            goods.add(WashJpanel.getGoodstableBean().getGoodstable().getRgid());
            operBean.setType(index);
            operBean.setGoods(goods);
            String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
            SendMessageUntil.toServer(senmes);
            RoleData.getRoleData().getLoginResult().setGold(RoleData.getRoleData().getLoginResult().getGold().subtract(new BigDecimal(money)));
            RoleData.getRoleData().getLoginResult().setScore(UserData.Splice(RoleData.getRoleData().getLoginResult().getScore(), "灵修值=30", 3));
            ZhuFrame.getZhuJpanel().addPrompt("消耗了" + money + "金币..");
            ZhuFrame.getZhuJpanel().addPrompt("消耗了30点灵修值..");
        }
        else if (this.caozuo == 2) {
            FormsManagement.HideForm(74);
        }
        else if (this.caozuo == 3) {
            Goodstable goodstable = WashJpanel.getGoodstableBean().getGoodstable();
            if (goodstable == null) {
                return;
            }
            QualityClBean clBean = new QualityClBean();
            clBean.setRgid(goodstable.getRgid());
            clBean.setType(4);
            String senmes2 = Agreement.extrAttrOperAgreement(GsonUtil.getGsonUtil().getgson().toJson(clBean));
            SendMessageUntil.toServer(senmes2);
            for (int i = 0; i < 4; ++i) {
                this.suitBaptizeJpanel.getOldAttr()[i].setText(this.suitBaptizeJpanel.getNewAttr()[i].getText());
                this.suitBaptizeJpanel.getNewAttr()[i].setText("");
            }
            String[] ss = goodstable.getValue().split("\\|");
            if (BaptizeBtn.newEx != null && BaptizeBtn.newEx != "") {
                String value = newExtra(ss, 3, BaptizeBtn.newEx);
                goodstable.setValue(value);
            }
        }
    }
    
    public static String newExtra(String[] v, int type, String newEx) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith(BaptizeBtn.Extras[type])) {
                if (newEx != null && !newEx.equals("")) {
                    if (i != 0) {
                        buffer.append("|");
                    }
                    buffer.append(newEx);
                    newEx = null;
                }
            }
            else {
                if (i != 0) {
                    buffer.append("|");
                }
                buffer.append(v[i]);
            }
        }
        if (newEx != null) {
            buffer.append("|");
            buffer.append(newEx);
        }
        return buffer.toString();
    }
    
    public static String getNewEx() {
        return BaptizeBtn.newEx;
    }
    
    public static void setNewEx(String newEx) {
        BaptizeBtn.newEx = newEx;
    }
    
    static {
        BaptizeBtn.Extras = new String[] { "炼化属性", "炼器属性", "神兵属性", "套装属性", "宝石属性", "觉醒技", "五行属性", "赞助属性", "巫铸属性", "点粹属性" };
    }
}
