package org.come.starcard;

import com.tool.tcpimg.UIUtils;
import org.come.until.GoodsListFromServerUntil;
import java.awt.Graphics;
import org.come.entity.Goodstable;
import java.awt.LayoutManager;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpanelSoulBackMain extends JPanel
{
    private JLabel[] cardLab;
    private BtnStarCard yesBtn;
    private BtnStarCard noBtn;
    private ArrayList<BigDecimal> starCardList;
    private ImageIcon iconBack;
    
    public JpanelSoulBackMain() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(377, 311));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 96);
            offBtn.setBounds(340, 10, 25, 25);
            this.add(offBtn);
            this.getCardLab();
            this.getNoBtn();
            this.getYesBtn();
            this.getStarCardList();
        }
        else {
            this.setPreferredSize(new Dimension(349, 351));
            this.setLayout((LayoutManager)null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 96);
            offBtn.setBounds(324, 0, 23, 23);
            this.add(offBtn);
            this.getCardLab();
            this.getNoBtn();
            this.getYesBtn();
            this.getStarCardList();
        }
    }
    
    public void addListNum(Goodstable goodstable) {
        BigDecimal rgid = goodstable.getRgid();
        if (this.starCardList.contains(rgid)) {
            this.starCardList.remove(rgid);
        }
        else {
            this.starCardList.add(rgid);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background1/B292.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 377, 311, this);
            GoodsListFromServerUntil.drawStarCardImg(g, 47, 57, this.starCardList);
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/xy2uiimg/归魂面板-w：349-h：351.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 349, 351, this);
            GoodsListFromServerUntil.drawStarCardImg(g, 22, 71, this.starCardList);
        }
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JLabel[] getCardLab() {
        if (this.cardLab == null) {
            this.cardLab = new JLabel[24];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.cardLab.length; ++i) {
                    (this.cardLab[i] = new JLabel()).setBounds(47 + i % 6 * 51, 57 + i / 6 * 51, 50, 50);
                    this.cardLab[i].setOpaque(false);
                    this.cardLab[i].addMouseListener(new MouseListenerSoulBack(i, this));
                    this.add(this.cardLab[i]);
                }
            }
            else {
                for (int i = 0; i < this.cardLab.length; ++i) {
                    (this.cardLab[i] = new JLabel()).setBounds(22 + i % 6 * 51, 71 + i / 6 * 51, 50, 50);
                    this.cardLab[i].setOpaque(false);
                    this.cardLab[i].addMouseListener(new MouseListenerSoulBack(i, this));
                    this.add(this.cardLab[i]);
                }
            }
        }
        return this.cardLab;
    }
    
    public void setCardLab(JLabel[] cardLab) {
        this.cardLab = cardLab;
    }
    
    public BtnStarCard getYesBtn() {
        if (this.yesBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.yesBtn = new BtnStarCard("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "确认", 30, this)).setBounds(70, 270, 59, 24);
            }
            else {
                (this.yesBtn = new BtnStarCard("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "确认", 30, this)).setBounds(70, 295, 60, 26);
            }
            this.add(this.yesBtn);
        }
        return this.yesBtn;
    }
    
    public void setYesBtn(BtnStarCard yesBtn) {
        this.yesBtn = yesBtn;
    }
    
    public BtnStarCard getNoBtn() {
        if (this.noBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.noBtn = new BtnStarCard("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "取消", 31, this)).setBounds(220, 270, 59, 24);
            }
            else {
                (this.noBtn = new BtnStarCard("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "取消", 31, this)).setBounds(220, 295, 60, 26);
            }
            this.add(this.noBtn);
        }
        return this.noBtn;
    }
    
    public void setNoBtn(BtnStarCard noBtn) {
        this.noBtn = noBtn;
    }
    
    public ArrayList<BigDecimal> getStarCardList() {
        if (this.starCardList == null) {
            this.starCardList = new ArrayList<>();
        }
        return this.starCardList;
    }
    
    public void setStarCardList(ArrayList<BigDecimal> starCardList) {
        this.starCardList = starCardList;
    }
}
