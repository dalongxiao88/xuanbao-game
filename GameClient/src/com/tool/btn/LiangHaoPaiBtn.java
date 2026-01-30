package com.tool.btn;

import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.entity.SellLiangHaoBase;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.PaintLiangHaoJpanel;
import org.come.Jpanel.GetLiangHaoTwoJpanel;

public class LiangHaoPaiBtn extends MoBanBtn
{
    int typeBtn;
    private GetLiangHaoTwoJpanel jpanel;
    private PaintLiangHaoJpanel paintLiangHaoJpanel;
    private int p;
    
    public LiangHaoPaiBtn(String iconpath, int type, Color[] colors, Font font, String text, Integer typeBtn, GetLiangHaoTwoJpanel jpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.typeBtn = (int)typeBtn;
        this.setFont(UIUtils.TXT_hyzjt18);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.jpanel = jpanel;
    }
    
    public LiangHaoPaiBtn(String iconpath, int type, String text, Integer typeBtn, PaintLiangHaoJpanel paintLiangHaoJpanel) {
        super(iconpath, type);
        this.setText(text);
        this.typeBtn = (int)typeBtn;
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.paintLiangHaoJpanel = paintLiangHaoJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.typeBtn == 1) {
            FormsManagement.showForm(641);
        }
        else if (this.typeBtn == 2) {
            this.jpanel.changeContent();
        }
        else if (this.typeBtn == 3) {
            FormsManagement.showForm(704);
        }
        else if (this.typeBtn == 4) {
            int selType = this.paintLiangHaoJpanel.getSelectType();
            BigDecimal payXy = new BigDecimal(this.paintLiangHaoJpanel.getLabPayXy().getText());
            BigDecimal myXu = RoleData.getRoleData().getLoginResult().getCodecard();
            if (myXu.compareTo(payXy) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的仙玉不足：" + payXy);
                return;
            }
            SellLiangHaoBase newSellLiangHaoBase = new SellLiangHaoBase();
            newSellLiangHaoBase.setAucPrice(payXy.intValue());
            newSellLiangHaoBase.setType(selType);
            String mes = Agreement.getAgreement().selllianghaoAgreement("CHANGETYPE|" + GsonUtil.getGsonUtil().getgson().toJson(newSellLiangHaoBase));
            SendMessageUntil.toServer(mes);
        }
    }
}
