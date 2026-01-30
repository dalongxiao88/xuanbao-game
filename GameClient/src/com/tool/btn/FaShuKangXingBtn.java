package com.tool.btn;

import com.tool.image.ImageMixDeal;
import com.tool.pet.PetProperty;
import com.tool.role.RoleProperty;
import com.tool.tcpimg.UIUtils;
import come.tool.Fighting.FightingMixDeal;
import org.come.Frame.RolePetResistanceJframe;
import org.come.Frame.RoleResistanceJframe;
import org.come.Jpanel.FaShuKangXingJpanel;
import org.come.entity.Pal;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;
import org.come.until.UserMessUntil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

import static com.tool.btn.JpanelOnJalbelBtn.testReflect;
import static com.tool.btn.JpanelOnJalbelBtn.testReflect1;

public class FaShuKangXingBtn extends MoBanBtn implements MouseMotionListener {
    public int caozuo;//心猿
    public FaShuKangXingJpanel faShuKangXingJpanel;
    public Long time = 0l;
    public Boolean b = true;

    public FaShuKangXingBtn(String iconpath, int type, int caozuo, FaShuKangXingJpanel faShuKangXingJpanel) {
        super(iconpath, type, UIUtils.Black);
        this.caozuo = caozuo;
        setFont(UIUtils.TEXT_HY88);
        setVerticalTextPosition(CENTER);
        setHorizontalTextPosition(CENTER);
        this.faShuKangXingJpanel = faShuKangXingJpanel;
    }

    public FaShuKangXingBtn(String iconpath, int type, int caozuo) {
        super(iconpath, type, UIUtils.Black);
        this.caozuo = caozuo;
        setFont(UIUtils.TEXT_HY88);
        setVerticalTextPosition(CENTER);
        setHorizontalTextPosition(CENTER);
    }

    public FaShuKangXingBtn(String iconpath, int type, int caozuo, String str, Color[] colors) {
        super(iconpath, type, colors);
        this.setText(str);
        this.caozuo = caozuo;
        setFont(UIUtils.TEXT_FONT);
        setVerticalTextPosition(CENTER);
        setHorizontalTextPosition(CENTER);
    }

    public FaShuKangXingBtn(String iconpath, int type, String text, Color[] colors, Font font) {
        super(iconpath, type, colors);
        setText(text);
        setFont(font);
        setVerticalTextPosition(CENTER);
        setHorizontalTextPosition(CENTER);
    }

    public FaShuKangXingBtn(String iconpath, int type, String text, int caozuo) {//心猿
        // TODO Auto-generated constructor stub
        super(iconpath, type, UIUtils.COLOR_BTNTEXT);//心猿
        setText(text);//心猿
        this.caozuo = caozuo;//心猿
        setFont(UIUtils.TEXT_FONT);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        long l = (System.currentTimeMillis() - time);
        if (l > 200) b = false;
        super.mouseReleased(e);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        time = 0l;
        b = true;
        if (caozuo < 6) {
            RoleResistanceJframe.getResistancejframe().mousePressed(e);
        } else if (caozuo >= 10 && caozuo <= 14)
            RolePetResistanceJframe.getResistancejframe().mousePressed(e);
        time = System.currentTimeMillis();
    }

    public void chooseyes() {
    }

    public void chooseno() {
    }

    public void nochoose(MouseEvent e) {

        if (caozuo < 6) {
            if (!b) {
                b = true;
                return ;
            }
            faShuKangXingJpanel.setOpen(!faShuKangXingJpanel.getOpen());
            if (ImageMixDeal.userimg.getRoleShow().getFighting() == 0 || FightingMixDeal.camp == -1) {
                try {
                    if (RoleResistanceJframe.getResistancejframe().getResistancejpanel().getrBtn().getText().equals("全")) {
                        testReflect1(RoleProperty.getRoleProperty().getQuality());
                    } else {
                        testReflect(RoleProperty.getRoleProperty().getQuality());

                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {// 获取战斗内的抗性
                SendMessageUntil.toServer(Agreement.getAgreement().fightQlAgreement(""));
            }
        } else if (caozuo >= 10 && caozuo <= 14) {
            if (!b) {
                b = true;
                return ;
            }
            faShuKangXingJpanel.setOpen(!faShuKangXingJpanel.getOpen());
            try {
                if (FormsManagement.getframe(58).isVisible()) {
                    PetProperty.ShowQl(UserMessUntil.getChosePetMes());
                    PetProperty.ShowQl(UserMessUntil.getChosePetMes());
                }

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (caozuo == 990) {
            if (ImageMixDeal.userimg.getRoleShow().getFighting() == 0 || FightingMixDeal.camp == -1) {
                try {
                    testReflect(RoleProperty.getRoleProperty().getQuality());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {// 获取战斗内的抗性
                SendMessageUntil.toServer(Agreement.getAgreement().fightQlAgreement(""));
            }
        } else if (caozuo == 990) {
            if (ImageMixDeal.userimg.getRoleShow().getFighting() == 0 || FightingMixDeal.camp == -1) {
                try {
                    testReflect(RoleProperty.getRoleProperty().getQuality());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {// 获取战斗内的抗性
                SendMessageUntil.toServer(Agreement.getAgreement().fightQlAgreement(""));
            }
        } else if (caozuo == 991) {
            if (getText().equals("全")) {
                setText("简");
                if (ImageMixDeal.userimg.getRoleShow().getFighting() == 0 || FightingMixDeal.camp == -1) {
                    try {
                        testReflect(RoleProperty.getRoleProperty().getQuality());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {// 获取战斗内的抗性
                    SendMessageUntil.toServer(Agreement.getAgreement().fightQlAgreement(""));
                }
            } else {
                setText("全");
                if (ImageMixDeal.userimg.getRoleShow().getFighting() == 0 || FightingMixDeal.camp == -1) {
                    try {
                        testReflect1(RoleProperty.getRoleProperty().getQuality());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {// 获取战斗内的抗性
                    SendMessageUntil.toServer(Agreement.getAgreement().fightQlAgreement(""));
                }
            }
        }
        return ;
    }

    public int getCaozuo() {
        return caozuo;
    }

    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }

    public FaShuKangXingJpanel getFaShuKangXingJpanel() {
        return faShuKangXingJpanel;
    }

    public void setFaShuKangXingJpanel(FaShuKangXingJpanel faShuKangXingJpanel) {
        this.faShuKangXingJpanel = faShuKangXingJpanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (faShuKangXingJpanel != null) {
            if (!faShuKangXingJpanel.getOpen()) {
                ImageIcon openImage = CutButtonImage.getImage("inkImg/Client/smkxsqd.png", -1, -1);
                g.drawImage(openImage.getImage(), 95, 7, null);
                g.drawImage(openImage.getImage(), 193, 7, null);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MouseMotionListener mouseMotionListener = RolePetResistanceJframe.getResistancejframe().getMouseMotionListeners()[0];
        System.out.printf("111111111");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MouseMotionListener mouseMotionListener = RolePetResistanceJframe.getResistancejframe().getMouseMotionListeners()[0];
        System.out.printf("111111111");
    }
}
