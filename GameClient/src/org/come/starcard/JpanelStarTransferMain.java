package org.come.starcard;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.FormsManagement;
import org.come.entity.Goodstable;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpanelStarTransferMain extends JPanel
{
    private JLabel starCardImgLeft;
    private JLabel starArrayImgLeft;
    private JLabel starArrayNameLeft;
    private JLabel starCardImgRight;
    private JLabel starArrayImgRight;
    private JLabel starArrayNameRight;
    private JLabel[] starCardLab;
    private JLabel[] radioLabLeft;
    private JLabel[] starArrayAttributeLabLeft;
    private JLabel[] radioLabRight;
    private JLabel[] starArrayAttributeLabRight;
    private BtnStarCard yesBtn;
    private BigDecimal chooseOneId;
    private BigDecimal chooseTwoId;
    private ImageIcon iconBack;
    
    public JpanelStarTransferMain() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(562, 435));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 97);
            offBtn.setBounds(537, 0, 25, 25);
            this.add(offBtn);
            this.getStarCardLab();
            this.getStarArrayAttributeLabLeft();
            this.getStarArrayAttributeLabRight();
            this.getStarArrayImgLeft();
            this.getStarArrayImgRight();
            this.getStarArrayNameLeft();
            this.getStarArrayNameRight();
            this.getStarCardImgLeft();
            this.getStarCardImgRight();
            this.getRadioLabLeft();
            this.getRadioLabRight();
            this.getYesBtn();
        }
        else {
            this.setPreferredSize(new Dimension(540, 497));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 97);
            offBtn.setBounds(515, 0, 23, 23);
            this.add(offBtn);
            this.getStarCardLab();
            this.getStarArrayAttributeLabLeft();
            this.getStarArrayAttributeLabRight();
            this.getStarArrayImgLeft();
            this.getStarArrayImgRight();
            this.getStarArrayNameLeft();
            this.getStarArrayNameRight();
            this.getStarCardImgLeft();
            this.getStarCardImgRight();
            this.getRadioLabLeft();
            this.getRadioLabRight();
            this.getYesBtn();
        }
    }
    
    public void showStarCardAttribute(int type, String[] values, Goodstable goodstable) {
        if (goodstable == null) {
            FormsManagement.HideForm(97);
        }
        String[] starArray = { "青帝宫", "黄帝宫", "白帝宫", "黑帝宫" };
        if (type == 0) {
            this.chooseOneId = goodstable.getRgid();
            this.starCardImgLeft.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 55, 53));
            this.starArrayImgLeft.setIcon(CutButtonImage.getImage("img/skill/" + values[1] + ".png", 55, 53));
            this.starArrayNameLeft.setText(values[1]);
            for (int i = 4; i < values.length; ++i) {
                this.radioLabLeft[i - 4].setIcon(null);
                this.starArrayAttributeLabLeft[i - 4].setText(starArray[i - 4] + " " + values[i]);
            }
        }
        else if (goodstable == null && values == null) {
            this.chooseTwoId = null;
            this.starCardImgRight.setIcon(null);
            this.starArrayImgRight.setIcon(null);
            this.starArrayNameRight.setText("");
            for (int i = 0; i < this.radioLabRight.length; ++i) {
                this.radioLabRight[i].setIcon(null);
                this.starArrayAttributeLabRight[i].setText("");
            }
        }
        else {
            this.chooseTwoId = goodstable.getRgid();
            this.starCardImgRight.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 55, 53));
            this.starArrayImgRight.setIcon(CutButtonImage.getImage("img/skill/" + values[1] + ".png", 55, 53));
            this.starArrayNameRight.setText(values[1]);
            for (int i = 4; i < values.length; ++i) {
                this.radioLabRight[i - 4].setIcon(null);
                this.starArrayAttributeLabRight[i - 4].setText(starArray[i - 4] + " " + values[i]);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background/S97.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 562, 435, this);
            GoodsListFromServerUntil.drawStarArray(g, 55, 345);
        }
        else {
            super.paintComponent(g);
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/xy2uiimg/斗转星移_540,h497.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 540, 497, this);
            GoodsListFromServerUntil.drawStarArray(g, 40, 392);
        }
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JLabel[] getStarCardLab() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.starCardLab == null) {
                this.starCardLab = new JLabel[9];
                for (int i = 0; i < this.starCardLab.length; ++i) {
                    (this.starCardLab[i] = new JLabel()).setBounds(55 + i * 51, 345, 50, 50);
                    this.starCardLab[i].setOpaque(false);
                    this.starCardLab[i].addMouseListener(new MouseListenerStarTransferMain(i, this));
                    this.add(this.starCardLab[i]);
                }
            }
            return this.starCardLab;
        }
        else {
            if (this.starCardLab == null) {
                this.starCardLab = new JLabel[9];
                for (int i = 0; i < this.starCardLab.length; ++i) {
                    (this.starCardLab[i] = new JLabel()).setBounds(40 + i * 51, 392, 50, 50);
                    this.starCardLab[i].setOpaque(false);
                    this.starCardLab[i].addMouseListener(new MouseListenerStarTransferMain(i, this));
                    this.add(this.starCardLab[i]);
                }
            }
            return this.starCardLab;
        }
    }
    
    public void setStarCardLab(JLabel[] starCardLab) {
        this.starCardLab = starCardLab;
    }
    
    public JLabel getStarCardImgLeft() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.starCardImgLeft == null) {
                (this.starCardImgLeft = new JLabel()).setBounds(133, 74, 55, 53);
                this.starCardImgLeft.setOpaque(false);
                this.add(this.starCardImgLeft);
            }
            return this.starCardImgLeft;
        }
        else {
            if (this.starCardImgLeft == null) {
                (this.starCardImgLeft = new JLabel()).setBounds(118, 112, 55, 53);
                this.starCardImgLeft.setOpaque(false);
                this.add(this.starCardImgLeft);
            }
            return this.starCardImgLeft;
        }
    }
    
    public void setStarCardImgLeft(JLabel starCardImgLeft) {
        this.starCardImgLeft = starCardImgLeft;
    }
    
    public JLabel getStarArrayImgLeft() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.starArrayImgLeft == null) {
                (this.starArrayImgLeft = new JLabel("", 0)).setBounds(205, 74, 30, 25);
                this.starArrayImgLeft.setOpaque(false);
                this.add(this.starArrayImgLeft);
            }
            return this.starArrayImgLeft;
        }
        else {
            if (this.starArrayImgLeft == null) {
                (this.starArrayImgLeft = new JLabel("", 0)).setBounds(195, 118, 30, 25);
                this.starArrayImgLeft.setOpaque(false);
                this.add(this.starArrayImgLeft);
            }
            return this.starArrayImgLeft;
        }
    }
    
    public void setStarArrayImgLeft(JLabel starArrayImgLeft) {
        this.starArrayImgLeft = starArrayImgLeft;
    }
    
    public JLabel getStarArrayNameLeft() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.starArrayNameLeft == null) {
                (this.starArrayNameLeft = new JLabel("", 0)).setBounds(195, 100, 55, 20);
                this.starArrayNameLeft.setOpaque(false);
                this.starArrayNameLeft.setFont(UIUtils.TEXT_FONT1);
                this.add(this.starArrayNameLeft);
            }
            return this.starArrayNameLeft;
        }
        else {
            if (this.starArrayNameLeft == null) {
                (this.starArrayNameLeft = new JLabel("", 0)).setBounds(183, 148, 55, 20);
                this.starArrayNameLeft.setOpaque(false);
                this.starArrayNameLeft.setFont(UIUtils.TEXT_FONT1);
                this.add(this.starArrayNameLeft);
            }
            return this.starArrayNameLeft;
        }
    }
    
    public void setStarArrayNameLeft(JLabel starArrayNameLeft) {
        this.starArrayNameLeft = starArrayNameLeft;
    }
    
    public JLabel getStarCardImgRight() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.starCardImgRight == null) {
                (this.starCardImgRight = new JLabel()).setBounds(381, 74, 55, 53);
                this.starCardImgRight.setOpaque(false);
                this.add(this.starCardImgRight);
            }
            return this.starCardImgRight;
        }
        else {
            if (this.starCardImgRight == null) {
                (this.starCardImgRight = new JLabel()).setBounds(366, 112, 55, 53);
                this.starCardImgRight.setOpaque(false);
                this.add(this.starCardImgRight);
            }
            return this.starCardImgRight;
        }
    }
    
    public void setStarCardImgRight(JLabel starCardImgRight) {
        this.starCardImgRight = starCardImgRight;
    }
    
    public JLabel getStarArrayImgRight() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.starArrayImgRight == null) {
                (this.starArrayImgRight = new JLabel("", 0)).setBounds(335, 74, 30, 25);
                this.starArrayImgRight.setOpaque(false);
                this.add(this.starArrayImgRight);
            }
            return this.starArrayImgRight;
        }
        else {
            if (this.starArrayImgRight == null) {
                (this.starArrayImgRight = new JLabel("", 0)).setBounds(315, 118, 30, 25);
                this.starArrayImgRight.setOpaque(false);
                this.add(this.starArrayImgRight);
            }
            return this.starArrayImgRight;
        }
    }
    
    public void setStarArrayImgRight(JLabel starArrayImgRight) {
        this.starArrayImgRight = starArrayImgRight;
    }
    
    public JLabel getStarArrayNameRight() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.starArrayNameRight == null) {
                (this.starArrayNameRight = new JLabel("", 0)).setBounds(322, 100, 55, 20);
                this.starArrayNameRight.setOpaque(false);
                this.starArrayNameRight.setFont(UIUtils.TEXT_FONT1);
                this.add(this.starArrayNameRight);
            }
            return this.starArrayNameRight;
        }
        else {
            if (this.starArrayNameRight == null) {
                (this.starArrayNameRight = new JLabel("", 0)).setBounds(300, 148, 55, 20);
                this.starArrayNameRight.setOpaque(false);
                this.starArrayNameRight.setFont(UIUtils.TEXT_FONT1);
                this.add(this.starArrayNameRight);
            }
            return this.starArrayNameRight;
        }
    }
    
    public void setStarArrayNameRight(JLabel starArrayNameRight) {
        this.starArrayNameRight = starArrayNameRight;
    }
    
    public JLabel[] getRadioLabLeft() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.radioLabLeft == null) {
                this.radioLabLeft = new JLabel[4];
                for (int i = 0; i < this.radioLabLeft.length; ++i) {
                    (this.radioLabLeft[i] = new JLabel()).setHorizontalAlignment(0);
                    this.radioLabLeft[i].setVerticalAlignment(0);
                    this.radioLabLeft[i].setBounds(58, 146 + i * 39, 20, 20);
                    this.radioLabLeft[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/选中w7,h7px.png", 7, 7));
                    class Mlistener extends MouseAdapter
                    {
                        int i;
                        public Mlistener(int i) {
                        	this.i = i;
                        }
                        
                        @Override
                        public void mousePressed(MouseEvent e) {
                            for (int i = 0; i < JpanelStarTransferMain.this.radioLabLeft.length; ++i) {
                                if (JpanelStarTransferMain.this.radioLabLeft[i].getIcon() != null && i != this.i) {
                                    JpanelStarTransferMain.this.radioLabLeft[i].setIcon(null);
                                }
                            }
                            if (JpanelStarTransferMain.this.radioLabLeft[this.i].getIcon() == null) {
                                JpanelStarTransferMain.this.radioLabLeft[this.i].setIcon(CutButtonImage.getImage("img/xy2uiimg/选中w7,h7px.png", 7, 7));
                            }
                        }
                    }
                    this.radioLabLeft[i].addMouseListener(new Mlistener(i));
                    this.add(this.radioLabLeft[i]);
                }
            }
            return this.radioLabLeft;
        }
        else {
            if (this.radioLabLeft == null) {
                this.radioLabLeft = new JLabel[4];
                for (int i = 0; i < this.radioLabLeft.length; ++i) {
                    (this.radioLabLeft[i] = new JLabel()).setHorizontalAlignment(0);
                    this.radioLabLeft[i].setVerticalAlignment(0);
                    this.radioLabLeft[i].setBounds(54, 186 + i * 39, 20, 20);
                    this.radioLabLeft[i].setIcon(CutButtonImage.getImage("img/xy2uiimg/选中w7,h7px.png", 7, 7));
                    class Mlistener extends MouseAdapter
                    {
                        private int i;
                        
                        public Mlistener(int i) {
                        	this.i = i;
                        }
                        
                        @Override
                        public void mousePressed(MouseEvent e) {
                            for (int i = 0; i < JpanelStarTransferMain.this.radioLabLeft.length; ++i) {
                                if (JpanelStarTransferMain.this.radioLabLeft[i].getIcon() != null && i != this.i) {
                                    JpanelStarTransferMain.this.radioLabLeft[i].setIcon(null);
                                }
                            }
                            if (JpanelStarTransferMain.this.radioLabLeft[this.i].getIcon() == null) {
                                JpanelStarTransferMain.this.radioLabLeft[this.i].setIcon(CutButtonImage.getImage("img/xy2uiimg/选中w7,h7px.png", 7, 7));
                            }
                        }
                    }
                    this.radioLabLeft[i].addMouseListener(new Mlistener(i));
                    this.add(this.radioLabLeft[i]);
                }
            }
            return this.radioLabLeft;
        }
    }
    
    public void setRadioLabLeft(JLabel[] radioLabLeft) {
        this.radioLabLeft = radioLabLeft;
    }
    
    public JLabel[] getStarArrayAttributeLabLeft() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.starArrayAttributeLabLeft == null) {
                this.starArrayAttributeLabLeft = new JLabel[4];
                for (int i = 0; i < this.starArrayAttributeLabLeft.length; ++i) {
                    (this.starArrayAttributeLabLeft[i] = new JLabel()).setBounds(84, 141 + i * 39, 184, 30);
                    this.starArrayAttributeLabLeft[i].setOpaque(false);
                    this.starArrayAttributeLabLeft[i].setForeground(Color.white);
                    this.starArrayAttributeLabLeft[i].setFont(UIUtils.TEXT_FONT1);
                    this.add(this.starArrayAttributeLabLeft[i]);
                }
            }
            return this.starArrayAttributeLabLeft;
        }
        else {
            if (this.starArrayAttributeLabLeft == null) {
                this.starArrayAttributeLabLeft = new JLabel[4];
                for (int i = 0; i < this.starArrayAttributeLabLeft.length; ++i) {
                    (this.starArrayAttributeLabLeft[i] = new JLabel()).setBounds(74, 181 + i * 39, 184, 30);
                    this.starArrayAttributeLabLeft[i].setOpaque(false);
                    this.starArrayAttributeLabLeft[i].setForeground(Color.white);
                    this.starArrayAttributeLabLeft[i].setFont(UIUtils.TEXT_FONT1);
                    this.add(this.starArrayAttributeLabLeft[i]);
                }
            }
            return this.starArrayAttributeLabLeft;
        }
    }
    
    public void setStarArrayAttributeLabLeft(JLabel[] starArrayAttributeLabLeft) {
        this.starArrayAttributeLabLeft = starArrayAttributeLabLeft;
    }
    
    public JLabel[] getRadioLabRight() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.radioLabRight == null) {
                this.radioLabRight = new JLabel[4];
                for (int i = 0; i < this.radioLabRight.length; ++i) {
                    (this.radioLabRight[i] = new JLabel("", 0)).setBounds(307, 148 + i * 39, 17, 17);
                    class Mlistener extends MouseAdapter
                    {
                        private int i;
                        
                        public Mlistener(int i) {
                        	this.i = i;
                        }
                        
                        @Override
                        public void mousePressed(MouseEvent e) {
                            for (int i = 0; i < JpanelStarTransferMain.this.radioLabRight.length; ++i) {
                                if (JpanelStarTransferMain.this.radioLabRight[i].getIcon() != null && i != this.i) {
                                    JpanelStarTransferMain.this.radioLabRight[i].setIcon(null);
                                }
                            }
                            if (JpanelStarTransferMain.this.radioLabRight[this.i].getIcon() == null) {
                                JpanelStarTransferMain.this.radioLabRight[this.i].setIcon(CutButtonImage.getImage("img/xy2uiimg/选中w7,h7px.png", 7, 7));
                            }
                        }
                    }
                    this.radioLabRight[i].addMouseListener(new Mlistener(i));
                    this.add(this.radioLabRight[i]);
                }
            }
            return this.radioLabRight;
        }
        else {
            if (this.radioLabRight == null) {
                this.radioLabRight = new JLabel[4];
                for (int i = 0; i < this.radioLabRight.length; ++i) {
                    (this.radioLabRight[i] = new JLabel("", 0)).setBounds(286, 186 + i * 39, 17, 17);
                    class Mlistener extends MouseAdapter
                    {
                        private int i;
                        
                        public Mlistener(int i) {
                        	this.i = i;
                        }
                        
                        @Override
                        public void mousePressed(MouseEvent e) {
                            for (int i = 0; i < JpanelStarTransferMain.this.radioLabRight.length; ++i) {
                                if (JpanelStarTransferMain.this.radioLabRight[i].getIcon() != null && i != this.i) {
                                    JpanelStarTransferMain.this.radioLabRight[i].setIcon(null);
                                }
                            }
                            if (JpanelStarTransferMain.this.radioLabRight[this.i].getIcon() == null) {
                                JpanelStarTransferMain.this.radioLabRight[this.i].setIcon(CutButtonImage.getImage("img/xy2uiimg/选中w7,h7px.png", 7, 7));
                            }
                        }
                    }
                    this.radioLabRight[i].addMouseListener(new Mlistener(i));
                    this.add(this.radioLabRight[i]);
                }
            }
            return this.radioLabRight;
        }
    }
    
    public void setRadioLabRight(JLabel[] radioLabRight) {
        this.radioLabRight = radioLabRight;
    }
    
    public JLabel[] getStarArrayAttributeLabRight() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.starArrayAttributeLabRight == null) {
                this.starArrayAttributeLabRight = new JLabel[4];
                for (int i = 0; i < this.starArrayAttributeLabRight.length; ++i) {
                    (this.starArrayAttributeLabRight[i] = new JLabel()).setBounds(330, 143 + i * 39, 184, 30);
                    this.starArrayAttributeLabRight[i].setOpaque(false);
                    this.starArrayAttributeLabRight[i].setForeground(Color.white);
                    this.starArrayAttributeLabRight[i].setFont(UIUtils.TEXT_FONT1);
                    this.add(this.starArrayAttributeLabRight[i]);
                }
            }
            return this.starArrayAttributeLabRight;
        }
        else {
            if (this.starArrayAttributeLabRight == null) {
                this.starArrayAttributeLabRight = new JLabel[4];
                for (int i = 0; i < this.starArrayAttributeLabRight.length; ++i) {
                    (this.starArrayAttributeLabRight[i] = new JLabel()).setBounds(306, 181 + i * 39, 184, 30);
                    this.starArrayAttributeLabRight[i].setOpaque(false);
                    this.starArrayAttributeLabRight[i].setForeground(Color.white);
                    this.starArrayAttributeLabRight[i].setFont(UIUtils.TEXT_FONT1);
                    this.add(this.starArrayAttributeLabRight[i]);
                }
            }
            return this.starArrayAttributeLabRight;
        }
    }
    
    public void setStarArrayAttributeLabRight(JLabel[] starArrayAttributeLabRight) {
        this.starArrayAttributeLabRight = starArrayAttributeLabRight;
    }
    
    public BtnStarCard getYesBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.yesBtn == null) {
                (this.yesBtn = new BtnStarCard("inkImg/button/75.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY88, "确认转移", 40, this)).setBounds(245, 307, 82, 26);
                this.add(this.yesBtn);
            }
            return this.yesBtn;
        }
        else {
            if (this.yesBtn == null) {
                (this.yesBtn = new BtnStarCard("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "确认转移", 40, this)).setBounds(215, 347, 80, 26);
                this.add(this.yesBtn);
            }
            return this.yesBtn;
        }
    }
    
    public void setYesBtn(BtnStarCard yesBtn) {
        this.yesBtn = yesBtn;
    }
    
    public BigDecimal getChooseOneId() {
        return this.chooseOneId;
    }
    
    public void setChooseOneId(BigDecimal chooseOneId) {
        this.chooseOneId = chooseOneId;
    }
    
    public BigDecimal getChooseTwoId() {
        return this.chooseTwoId;
    }
    
    public void setChooseTwoId(BigDecimal chooseTwoId) {
        this.chooseTwoId = chooseTwoId;
    }
}
