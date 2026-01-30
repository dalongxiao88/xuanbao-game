package org.come.Jpanel;

import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.until.CutButtonImage;
import com.tool.image.ImageMixDeal;
import org.come.Frame.ZhuFrame;
import java.awt.Graphics;
import org.come.until.GoodsListFromServerUntil;
import org.come.bean.LaborRank;
import java.awt.Dimension;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.Sprite;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.TrueFeedbackBtn;
import javax.swing.JPanel;

public class TrueFeedbackLotteyJPanel extends JPanel
{
    private TrueFeedbackBtn btnLottey;
    private JLabel[] labGoodsImg;
    private JLabel labRank;
    private ImageIcon topImg;
    private ImageIcon downImg;
    private ImageIcon lotteyImg;
    private int num;
    private int time;
    private int count;
    private int imgX;
    private int imgY;
    private int rank;
    private boolean is;
    private String value;
    private Sprite cjwrk;
    
    public TrueFeedbackLotteyJPanel() {
        this.imgX = 0;
        this.imgY = 121;
        this.cjwrk = SpriteFactory.VloadSprite("resource/mouse/cjwrk.tcp", null);
        this.setPreferredSize(new Dimension(430, 450));
        this.setOpaque(false);
        this.setLayout(null);
        this.getBtnLottey();
        this.getLabGoodsImg();
        this.getLabRank();
    }
    
    public void showViewData(LaborRank laborRank) {
        this.rank = laborRank.getRank();
        this.labRank.setText(String.valueOf(this.rank));
        String string = laborRank.getValue();
        if (string != null) {
            String[] split = string.split("\\|");
            int length = this.labGoodsImg.length;
            for (int i = 0; i < split.length; ++i) {
                if (length > i) {
                    this.labGoodsImg[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive(split[i], 53, 53));
                }
            }
        }
    }
    
    public void lotteyGoods(LaborRank laborRank) {
        int count = 14 - this.time;
        this.num = laborRank.getRank() + count;
        this.value = laborRank.getValue();
    }
    
    public void grawLotteyImg(Graphics g) {
        if (this.num == 0) {
            if (this.is) {
                if (this.value != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2(this.value);
                    this.value = null;
                }
                this.is = false;
            }
        }
        else {
            ++this.count;
            if (this.count > ((this.num > 1) ? (100 / this.num) : 70)) {
                --this.num;
                ++this.time;
                if (this.time >= 14) {
                    this.time = 0;
                }
                this.count = 0;
                if (this.time <= 4) {
                    this.imgX = this.time * 84;
                    this.imgY = 121;
                }
                else if (this.time <= 7) {
                    this.imgX = 335;
                    this.imgY = 121 + (this.time - 4) * 77;
                }
                else if (this.time <= 11) {
                    this.imgX = 335 - (this.time - 7) * 84;
                    this.imgY = 352;
                }
                else if (this.time <= 13) {
                    this.imgX = 0;
                    this.imgY = 352 - (this.time - 11) * 77;
                }
                if (this.num == 0) {
                    this.is = true;
                }
            }
        }
        this.cjwrk.updateToTime(ImageMixDeal.userimg.getTime(), 0);
        this.cjwrk.draw(g, this.imgX, this.imgY);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.topImg == null) {
            this.topImg = new ImageIcon("inkImg/background/S198.png");
        }
        g.drawImage(this.topImg.getImage(), 0, 0, null);
        if (this.downImg == null) {
            this.downImg = new ImageIcon("inkImg/background/S195.png");
        }
        g.drawImage(this.downImg.getImage(), 0, 130, null);
        if (this.lotteyImg == null) {
            this.lotteyImg = CutButtonImage.getImage("inkImg/background/S116.png", -1, -1);
        }
    }
    
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        this.grawLotteyImg(g);
    }
    
    public TrueFeedbackBtn getBtnLottey() {
        if (this.btnLottey == null) {
            (this.btnLottey = new TrueFeedbackBtn("inkImg/button/50.png", 1, 9, "抽奖", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, this)).setBounds(165, 272, 80, 26);
            this.add(this.btnLottey);
        }
        return this.btnLottey;
    }
    
    public void setBtnLottey(TrueFeedbackBtn btnLottey) {
        this.btnLottey = btnLottey;
    }
    
    public int getNum() {
        return this.num;
    }
    
    public void setNum(int num) {
        this.num = num;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
    
    public JLabel[] getLabGoodsImg() {
        if (this.labGoodsImg == null) {
            this.labGoodsImg = new JLabel[14];
            for (int i = 0; i < this.labGoodsImg.length; ++i) {
                this.labGoodsImg[i] = new JLabel();
                if (i <= 4) {
                    if (i >= 3) {
                        this.labGoodsImg[i].setBounds(187 + (i - 2) * 84, 141, 53, 53);
                    }
                    else {
                        this.labGoodsImg[i].setBounds(20 + i * 84, 141, 53, 53);
                    }
                }
                else if (i <= 7) {
                    this.labGoodsImg[i].setBounds(355, 141 + (i - 4) * 77, 53, 53);
                }
                else if (i <= 11) {
                    if (i >= 9) {
                        this.labGoodsImg[i].setBounds(355 - (i - 7) * 84 + 1, 372, 53, 53);
                    }
                    else {
                        this.labGoodsImg[i].setBounds(355 - (i - 7) * 84, 372, 53, 53);
                    }
                }
                else if (i <= 13) {
                    this.labGoodsImg[i].setBounds(20, 373 - (i - 11) * 77, 53, 53);
                }
                this.labGoodsImg[i].setOpaque(false);
                this.add(this.labGoodsImg[i]);
            }
        }
        return this.labGoodsImg;
    }
    
    public void setLabGoodsImg(JLabel[] labGoodsImg) {
        this.labGoodsImg = labGoodsImg;
    }
    
    public JLabel getLabRank() {
        if (this.labRank == null) {
            (this.labRank = new JLabel("100", 0)).setBounds(233, 318, 40, 14);
            this.labRank.setForeground(Color.red);
            this.labRank.setFont(UIUtils.TEXT_FONT);
            this.add(this.labRank);
        }
        return this.labRank;
    }
    
    public void setLabRank(JLabel labRank) {
        this.labRank = labRank;
    }
    
    public int getRank() {
        return this.rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
}
