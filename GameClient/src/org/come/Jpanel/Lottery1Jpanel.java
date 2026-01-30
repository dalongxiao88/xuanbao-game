package org.come.Jpanel;

import com.tool.btn.TrueFeedbackBtn;
import com.tool.tcpimg.UIUtils;
import org.come.Frame.ZhuFrame;
import org.come.bean.LaborRank;
import org.come.entity.Goodstable;
import org.come.until.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

public class Lottery1Jpanel  extends JPanel {
    private static Point[] points = {
            new Point(259,65),new Point(327,97),new Point(372,155),new Point(390,227),new Point(371,299),
            new Point(325,356),new Point(258,388), new Point(185,388),new Point(119,356),new Point(73,298),
            new Point(57,227),new Point(74,154),new Point(121,98),new Point(187,66)};
    private JLabel[] labImg,labGoodsImg;
    private TrueFeedbackBtn btnLottey;

    public Lottery1Jpanel() {
        this.setPreferredSize(new Dimension(880, 502));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        getBtnLottey();

        labImg = new JLabel[14];
        labGoodsImg = new JLabel[14];
        for (int i = 0; i < labImg.length; i++) {
            labGoodsImg[i] = new JLabel();
            labGoodsImg[i].setBounds(points[i].x, points[i].y, 53, 53);
//            labGoodsImg[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive("54aa", 53, 53));
            labGoodsImg[i].setOpaque(false);
            this.add(labGoodsImg[i]);
            labImg[i] = new JLabel();
            labImg[i].setBounds(points[i].x-20, points[i].y-20, 90, 90);
            labImg[i].setIcon(CutButtonImage.getImage("inkImg/background1/物品框.png", 90, 90));
            this.add(labImg[i]);
        }
    }

    public void showViewData(LaborRank laborRank){
        isLotteyIn = true;
        Util.nums = 1;
        if(laborRank.getValue1()!=null && !laborRank.getValue1().equals("")) {
            showGoods = laborRank.getValue1().split("\\|");
            for(int i=0;i<labGoodsImg.length;i++) {
                try {
                    Goodstable goods = UserMessUntil.getGoodsBean().getgoods(new BigDecimal(showGoods[i].trim()));
                    if (goods != null) {
                        labGoodsImg[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goods.getSkin(), 53, 53));
                    } else {
                        labGoodsImg[i].setIcon(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        lotteyIndex = Integer.parseInt(laborRank.getValue());
        // 计算
        int count = 14-(int)(angle/25.74)+laborRank.getRank() % 14;
        maxCount = (int)((laborRank.getRank() / 14+6)*360+count*25.73+Util.random.nextInt(25)-angle % 25.75);
    }

    public int angle = 0;
    private boolean isLotteyIn = false;
    private String[] showGoods;
    private int lotteyIndex = -1;
    private void getLottey(){
        for (int i = 0; i < showGoods.length; i++) {
            if (lotteyIndex == i) {
                Goodstable goods = UserMessUntil.getGoodsBean().getgoods(new BigDecimal(showGoods[i].trim()));
                if (goods != null) {
                    String value = "恭喜您获得【"+goods.getGoodsname()+"】,真是运气爆棚啊！";
                    ZhuFrame.getZhuJpanel().addPrompt2(value);
                    btnLottey.sendMes("14|"+goods.getGoodsid());
                } else {
                    ZhuFrame.getZhuJpanel().addPrompt2("什么都没有得到");
                }
                angle = (int)(lotteyIndex * 25.73)+13;
                lotteyIndex = -1;
                isLotteyIn = false;
                java.util.Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    private int count = 2;
                    @Override
                    public void run() {
                        this.count--;
                        if (count <= 0) {
                            FormsManagement.HideForm(3005);
                            Util.nums = 0;
                            timer.cancel();//
                        }
                    }
                };
                timer.schedule(task, 0, 1000);
            }
        }
    }
    private ImageIcon icon = CutButtonImage.getImage("inkImg/background1/转盘.png", 880, 502);
    private ImageIcon icon1 = CutButtonImage.getImage("inkImg/background1/指针.png", 96, 96);
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(icon.getImage(), 0, 0, 880, 502, this);
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        int width = icon1.getIconWidth();
        int height = icon1.getIconHeight();
        double centerX = width / 2.0;
        double centerY = height / 2.0;
        this.centerX = 200+centerX;
        this.centerY = 204+centerY;
        g2d.rotate(Math.toRadians(angle), this.centerX, this.centerY);
        g2d.drawImage(icon1.getImage(), 200, 204,null);
        g2d.rotate(Math.toRadians(-angle), this.centerX, this.centerY);
        this.centerX = 0;
        this.centerY = 0;
    }
    public double centerX = 0;
    public double centerY = 0;
    private int maxCount = 0;
    private int count = 0;
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        if (maxCount > 0) {
            switch ((maxCount -count)/360) {
                case 0:
                    angle+=(maxCount -count)/360+1;
                    count+=(maxCount -count)/360+1;
                    break;
                case 1:
                    angle+=(maxCount -count)/270+1;
                    count+=(maxCount -count)/270+1;
                    break;
                case 2:
                    angle+=(maxCount -count)/180+1;
                    count+=(maxCount -count)/180+1;
                    break;
                default:
                    angle+=12;
                    count+=12;
                    break;
            }
            angle%=360;
            if (count >= maxCount) {
                maxCount = 0;
                count = 0;
                getLottey();
            }
        }
    }

    public TrueFeedbackBtn getBtnLottey() {
        if(btnLottey == null){
            btnLottey = new TrueFeedbackBtn("inkImg/button1/B21.png" ,1, 102, "抽奖", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, this);
            btnLottey.setBounds(530, 360, 79, 24);
            this.add(btnLottey);
        }
        return btnLottey;
    }

    public boolean isLotteyIn() {
        return isLotteyIn;
    }
}