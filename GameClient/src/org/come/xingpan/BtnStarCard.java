package org.come.xingpan;

import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.come.Frame.ZhuFrame;
import com.tool.role.BaseXingpans;
import java.util.Vector;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import com.tool.tcp.SpriteFactory;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import com.tool.tcp.Sprite;
import com.tool.btn.MoBanBtn;

public class BtnStarCard extends MoBanBtn
{
    private int caozuo;
    private JpanelXingBackMain jpanelXingBackMain;
    private JpanelXingCardMain jpanelXingCardMain;
    private StarSoulRefinedJpane starSoulRefinedJpane;
    private boolean isOpen;
    private Sprite sprite;
    
    public BtnStarCard(String iconpath, int type, String text, int caozuo, JpanelXingCardMain jpanelXingCardMain) {
        super(iconpath, type);
        this.caozuo = caozuo;
        if (caozuo >= 20) {
            this.setText(text);
            this.setHorizontalTextPosition(0);
            this.setVerticalTextPosition(0);
            this.setForeground(Color.yellow);
            this.setFont(UIUtils.TEXT_HY17);
        }
        this.jpanelXingCardMain = jpanelXingCardMain;
    }
    
    public BtnStarCard(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, JpanelXingCardMain jpanelXingCardMain) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.setFont(font);
        this.jpanelXingCardMain = jpanelXingCardMain;
        if (caozuo > 0 && caozuo <= 8) {
            this.sprite = SpriteFactory.VloadSprite("resource/mouse/xp/XPDL" + caozuo + ".tcp", null);
        }
    }
    
    public BtnStarCard(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, StarSoulRefinedJpane starSoulRefinedJpane) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.setFont(font);
        this.starSoulRefinedJpane = starSoulRefinedJpane;
    }
    
    public BtnStarCard(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, JpanelXingBackMain jpanelXingBackMain) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.setFont(font);
        this.jpanelXingBackMain = jpanelXingBackMain;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 0) {
            FormsManagement.showForm(121);
        }
        else if (this.caozuo <= 8) {
            this.jpanelXingCardMain.update(this.caozuo);
        }
        else if (this.caozuo == 9) {
            StarSoulRefinedJframe starSoulRefinedJframe = StarSoulRefinedJframe.getShowJframeSummonEquipMain();
            if (starSoulRefinedJframe != null) {
                starSoulRefinedJframe.getStarSoulRefinedJpane().init(0);
            }
            FormsManagement.showForm(120);
        }
        else if (this.caozuo == 10) {
            Integer selectedStarSoulId1 = this.starSoulRefinedJpane.getSelectedStarSoulId(0);
            Integer selectedStarSoulId2 = this.starSoulRefinedJpane.getSelectedStarSoulId(1);
            if (selectedStarSoulId1 == null || selectedStarSoulId2 == null) {
                return;
            }
            SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Z2=" + selectedStarSoulId1 + "=" + selectedStarSoulId2));
        }
    }
    
    private boolean examinationXPStatus(Vector<BaseXingpans> xingpansVector) {
        for (int z = 0; z < xingpansVector.size(); ++z) {
            BaseXingpans baseXingpans = (BaseXingpans)xingpansVector.get(z);
            if (baseXingpans.getBh() == 7) {
                String vvvvv = unicodeStr2String(baseXingpans.getExp());
                String[] vvvv = vvvvv.split("=");
                if (vvvv.length < 4) {
                    ZhuFrame.getZhuJpanel().addPrompt2("星魂没有全部激活");
                    return true;
                }
            }
        }
        boolean aa = true;
        return false;
    }
    
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; ++i) {
            int data = Integer.parseInt(hex[i], 16);
            string.append((char)data);
        }
        return string.toString();
    }
    
    public static String unicodeStr2String(String unicodeStr) {
        int length = unicodeStr.length();
        int count = 0;
        String regex = "\\\\u[a-f0-9A-F]{1,4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(unicodeStr);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String oldChar = matcher.group();
            String newChar = unicode2String(oldChar);
            int index = matcher.start();
            sb.append(unicodeStr.substring(count, index));
            sb.append(newChar);
            count = index + oldChar.length();
        }
        sb.append(unicodeStr.substring(count, length));
        return sb.toString();
    }
    
    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    
    public boolean isOpen() {
        return this.isOpen;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.isOpen && this.sprite != null) {
            this.sprite.updateToTime(ImageMixDeal.userimg.getTime(), 0);
            this.sprite.draw(g, 0, 0);
        }
    }
}
