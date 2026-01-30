package com.tool.btn;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.GetLiangHaoThreeJpanel;
import org.come.Jpanel.GetLiangHaoTwoJpanel;
import org.come.Jpanel.GetLiangHaoOneJpanel;
import org.come.Jpanel.GetLiangHaoJpanel;
import org.come.Jpanel.GetLiangHaoTabJpanel;

public class GetLiangHaoBtn extends MoBanBtn
{
    private int caozuo;
    private GetLiangHaoTabJpanel cardJpanel;
    private GetLiangHaoJpanel getLiangHaoJpanel;
    private GetLiangHaoOneJpanel getLiangHaoOneJpanel;
    private GetLiangHaoTwoJpanel getLiangHaoTwoJpanel;
    private GetLiangHaoThreeJpanel getLiangHaoThreeJpanel;
    
    public GetLiangHaoBtn(String iconpath, int type, int caozuo, GetLiangHaoTabJpanel cardJpanel, GetLiangHaoJpanel getLiangHaoJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.cardJpanel = cardJpanel;
        this.getLiangHaoJpanel = getLiangHaoJpanel;
    }
    
    public GetLiangHaoBtn(String iconpath, int type, String text, int caozuo, GetLiangHaoOneJpanel getLiangHaoOneJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.getLiangHaoOneJpanel = getLiangHaoOneJpanel;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public GetLiangHaoBtn(String iconpath, int type, String text, int caozuo, GetLiangHaoTwoJpanel getLiangHaoTwoJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.getLiangHaoTwoJpanel = getLiangHaoTwoJpanel;
        this.setText(text);
        if (caozuo == 11) {
            this.setFont(UIUtils.TEXT_HY16);
        }
        else {
            this.setFont(UIUtils.TEXT_FONT11);
            this.setColors(UIUtils.COLOR_BTNTEXT);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public GetLiangHaoBtn(String iconpath, int type, String text, int caozuo, GetLiangHaoThreeJpanel getLiangHaoThreeJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.getLiangHaoThreeJpanel = getLiangHaoThreeJpanel;
        this.setText(text);
        if (caozuo == 11) {
            this.setFont(UIUtils.TEXT_HY16);
        }
        else {
            this.setFont(UIUtils.TEXT_FONT11);
            this.setColors(UIUtils.COLOR_BTNTEXT);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        try {
            switch (this.caozuo) {
                case 2: {
                    if (MyIsif.getStyle().equals("水墨")) {
                        this.getLiangHaoJpanel.getBtnChoose().setIcons(CutButtonImage.cuts("inkImg/button1/xuan.png"));
                        this.getLiangHaoJpanel.getBtnRob().setIcons(CutButtonImage.cuts("inkImg/button1/qiangxiao.png"));
                        this.getLiangHaoJpanel.getBtnLetter().setIcons(CutButtonImage.cuts("inkImg/button1/xinjianxiao.png"));
                    }
                    else {
                        this.getLiangHaoJpanel.getBtnChoose().setIcons(CutButtonImage.cuts("inkImg/button1/xuan.png"));
                        this.getLiangHaoJpanel.getBtnRob().setIcons(CutButtonImage.cuts("inkImg/button1/qiangxiao.png"));
                        this.getLiangHaoJpanel.getBtnLetter().setIcons(CutButtonImage.cuts("inkImg/button1/xinjianxiao.png"));
                    }
                    this.cardJpanel.getGetLiangHaoOneJpanel().changeFrom(this.caozuo);
                    this.cardJpanel.getCar().show(this.cardJpanel, "2");
                    String mes2 = Agreement.getAgreement().selllianghaoAgreement("SELLLIST");
                    SendMessageUntil.toServer(mes2);
                    break;
                }
                case 3: {
                    if (MyIsif.getStyle().equals("水墨")) {
                        this.getLiangHaoJpanel.getBtnChoose().setIcons(CutButtonImage.cuts("inkImg/button1/xuanxiao.png"));
                        this.getLiangHaoJpanel.getBtnRob().setIcons(CutButtonImage.cuts("inkImg/button1/qiang.png"));
                        this.getLiangHaoJpanel.getBtnLetter().setIcons(CutButtonImage.cuts("inkImg/button1/xinjianxiao.png"));
                    }
                    else {
                        this.getLiangHaoJpanel.getBtnChoose().setIcons(CutButtonImage.cuts("inkImg/button1/xuanxiao.png"));
                        this.getLiangHaoJpanel.getBtnRob().setIcons(CutButtonImage.cuts("inkImg/button1/qiang.png"));
                        this.getLiangHaoJpanel.getBtnLetter().setIcons(CutButtonImage.cuts("inkImg/button1/xinjianxiao.png"));
                    }
                    this.cardJpanel.getGetLiangHaoTwoJpanel().changeFrom(this.caozuo);
                    this.cardJpanel.getCar().show(this.cardJpanel, "3");
                    String mes3 = Agreement.getAgreement().selllianghaoAgreement("AUCLIST");
                    SendMessageUntil.toServer(mes3);
                    break;
                }
                case 4: {
                    if (MyIsif.getStyle().equals("水墨")) {
                        this.getLiangHaoJpanel.getBtnChoose().setIcons(CutButtonImage.cuts("inkImg/button1/xuanxiao.png"));
                        this.getLiangHaoJpanel.getBtnRob().setIcons(CutButtonImage.cuts("inkImg/button1/qiangxiao.png"));
                        this.getLiangHaoJpanel.getBtnLetter().setIcons(CutButtonImage.cuts("inkImg/button1/xinjian.png"));
                    }
                    else {
                        this.getLiangHaoJpanel.getBtnChoose().setIcons(CutButtonImage.cuts("inkImg/button1/xuanxiao.png"));
                        this.getLiangHaoJpanel.getBtnRob().setIcons(CutButtonImage.cuts("inkImg/button1/qiangxiao.png"));
                        this.getLiangHaoJpanel.getBtnLetter().setIcons(CutButtonImage.cuts("inkImg/button1/xinjian.png"));
                    }
                    this.cardJpanel.getGetLiangHaoThreeJpanel().changeFrom(this.caozuo);
                    this.cardJpanel.getCar().show(this.cardJpanel, "4");
                    break;
                }
                case 10: {
                    String searchText = this.getLiangHaoOneJpanel.getSearchText().getText();
                    String mes4 = Agreement.getAgreement().selllianghaoAgreement("SELLLIST|" + searchText);
                    SendMessageUntil.toServer(mes4);
                    break;
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
