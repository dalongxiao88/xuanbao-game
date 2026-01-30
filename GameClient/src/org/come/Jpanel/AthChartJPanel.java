package org.come.Jpanel;

import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import com.tool.image.ImageMixDeal;
import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.Graphics;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import java.math.BigDecimal;
import org.come.until.UserMessUntil;
import org.come.until.Util;
import org.come.bean.LaborRank;
import java.awt.Dimension;
import com.tool.tcp.SpriteFactory;
import org.come.until.CutButtonImage;
import com.tool.tcp.Sprite;
import javax.swing.JLabel;
import com.tool.btn.TrueFeedbackBtn;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AthChartJPanel extends JPanel
{
    private ImageIcon image3;
    private TrueFeedbackBtn btnLottey;
    private JLabel[] labGoodsImg;
    private JLabel[] djsimg;
    private JLabel labRank;
    private String[] msg;
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
    
    public AthChartJPanel() {
        this.image3 = CutButtonImage.getImage("resource/NewUi/shanzi.gif", 410, 397);
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
        Util.nums = 1;
        this.rank = laborRank.getRank();
        this.labRank.setText(String.valueOf(this.rank));
        String string = "";
        if (laborRank.getValue1() != null && !laborRank.getValue1().equals("")) {
            String[] split1 = laborRank.getValue1().split("\\|");
            this.msg = split1;
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < split1.length; ++i) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                Goodstable goods = UserMessUntil.getGoodsBean().getgoods(new BigDecimal(split1[i]));
                if (goods != null) {
                    buffer.append(goods.getSkin());
                }
            }
            string = buffer.toString();
        }
        else {
            string = "484|484|484|484|484|484|484|484|484|484|484|484|484|484";
            this.time3();
        }
        if (string != null) {
            String[] split2 = string.split("\\|");
            int length = this.labGoodsImg.length;
            for (int i = 0; i < split2.length; ++i) {
                if (length > i) {
                    this.labGoodsImg[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive(split2[i], 53, 53));
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
                    if (this.msg != null && this.msg.length > 0) {
                        int i = 0;
                        while (i < this.msg.length) {
                            if (this.value.equals(i + "")) {
                                Goodstable goods = UserMessUntil.getGoodsBean().getgoods(new BigDecimal(this.msg[i]));
                                this.value = "恭喜您获得【" + goods.getGoodsname() + "】,真是运气爆棚啊！";
                                this.btnLottey.sendMes("14|" + goods.getGoodsid());
                                Timer timer = new Timer();
                                TimerTask task = new TimerTask() {
                                    private int count = 2;
                                    
                                    @Override
                                    public void run() {
                                        --this.count;
                                        if (this.count <= 0) {
                                            FormsManagement.HideForm(3001);
                                            Util.nums = 0;
                                            timer.cancel();
                                        }
                                    }
                                };
                                timer.schedule(task, 0L, 1000L);
                                break;
                            }
                            else {
                                ++i;
                            }
                        }
                    }
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
        if (this.downImg == null) {
            this.downImg = new ImageIcon("inkImg/background/S1951.png");
        }
        g.drawImage(this.downImg.getImage(), 0, 130, null);
        if (this.lotteyImg == null) {
            this.lotteyImg = CutButtonImage.getImage("inkImg/background/S116.png", -1, -1);
        }
        g.drawImage(this.image3.getImage(), 195, 265, 42, 42, this);
    }
    
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        this.grawLotteyImg(g);
    }
    
    public boolean goCJPD() {
        return this.getNum() == 0;
    }
    
    public TrueFeedbackBtn getBtnLottey() {
        if (this.btnLottey == null) {
            (this.btnLottey = new TrueFeedbackBtn("inkImg/button/50.png", 1, 100, "抽奖", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, this)).setBounds(165, 272, 80, 26);
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
            this.labRank.setForeground(Color.white);
            this.labRank.setFont(UIUtils.TEXT_FONT991);
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
    
    private void time3() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            private int count = 6;
            
            @Override
            public void run() {
                --this.count;
                if (this.count == 5) {
                    AthChartJPanel.this.image3 = CutButtonImage.getImage("inkImg/number/5.png", 42, 42);
                }
                if (this.count == 4) {
                    AthChartJPanel.this.image3 = CutButtonImage.getImage("inkImg/number/4.png", 42, 42);
                }
                if (this.count == 3) {
                    AthChartJPanel.this.image3 = CutButtonImage.getImage("inkImg/number/3.png", 42, 42);
                }
                if (this.count == 2) {
                    AthChartJPanel.this.image3 = CutButtonImage.getImage("inkImg/number/2.png", 42, 42);
                }
                if (this.count == 1) {
                    AthChartJPanel.this.image3 = CutButtonImage.getImage("inkImg/number/1.png", 42, 42);
                }
                if (this.count <= 0) {
                    AthChartJPanel.this.image3 = CutButtonImage.getImage("inkImg/number/go.png", 42, 42);
                    AthChartJPanel.this.btnLottey.goCJ();
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, 0L, 1000L);
    }
}
