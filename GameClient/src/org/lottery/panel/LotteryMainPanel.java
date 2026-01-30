package org.lottery.panel;

import com.tool.tcp.SpriteFactory;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import org.come.mouslisten.WLLMouslisten;
import com.tool.role.RoleData;
import org.come.bean.QuackGameBean;
import java.awt.Component;
import org.come.until.CutButtonImage;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.awt.event.MouseListener;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import org.lottery.mouseListener.PrizeMouseListener;
import org.lottery.frame.LotteryMainFrame;
import org.come.until.UserMessUntil;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import com.tool.tcp.Sprite;
import com.tool.tcpimg.ChatBox;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import java.util.List;
import org.lottery.btn.LotteryMainBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LotteryMainPanel extends JPanel
{
    private JLabel[] prizeLab;
    private JLabel[] luckyDrawImg;
    private JLabel[] luckyDrawNumber;
    private JLabel[] luckyDrawName;
    private JLabel consumeGoodsImg;
    private JLabel consumeGoodsNumber;
    private LotteryMainBtn oneLotteryBtn;
    private LotteryMainBtn tenLotteryBtn;
    private LotteryMainBtn helpBtn;
    private List<LotteryMainBtn> menuBtn;
    private JLabel hintCheckbox;
    private JLabel hintContent;
    private int lotteryType;
    private int moenyType;
    private BigDecimal goodid;
    private BigDecimal xianyuDraw;
    private String drawName;
    private boolean hintType;
    private boolean drawLottery;
    private int[] drawLotteryType;
    private String[] drawGoods;
    private JLabel integralLab;
    private LotteryMainBtn integralBtn;
    private ImageIcon iconBack;
    private ChatBox box;
    static Sprite tcp;
    private int time;
    
    public LotteryMainPanel() {
        this.lotteryType = 1;
        this.moenyType = 0;
        this.hintType = true;
        this.drawLottery = false;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(653, 479));
            this.setOpaque(false);
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 87);
            offBtn.setBounds(630, 0, 25, 25);
            this.add(offBtn);
            this.getPrizeLab();
            this.getLuckyDrawNumber();
            this.getLuckyDrawImg();
            this.getLuckyDrawName();
            this.getHelpBtn();
            this.getConsumeGoodsImg();
            this.getConsumeGoodsNumber();
            this.getOneLotteryBtn();
            this.getHintCheckbox();
            this.getHintContent();
            this.getTenLotteryBtn();
            this.getDrawLotteryType();
            this.getIntegralLab();
            this.getIntegralBtn();
            this.getMenuBtn();
        }
        else {
            this.setPreferredSize(new Dimension(655, 522));
            this.setOpaque(false);
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 87);
            offBtn.setBounds(632, 0, 23, 23);
            this.add(offBtn);
            this.getPrizeLab();
            this.getLuckyDrawNumber();
            this.getLuckyDrawImg();
            this.getLuckyDrawName();
            this.getHelpBtn();
            this.getConsumeGoodsImg();
            this.getConsumeGoodsNumber();
            this.getOneLotteryBtn();
            this.getHintCheckbox();
            this.getHintContent();
            this.getTenLotteryBtn();
            this.getDrawLotteryType();
            this.getIntegralLab();
            this.getIntegralBtn();
            this.getMenuBtn();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background1/B322.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 653, 479, this);
            if (this.drawLottery) {
                LotteryMainPanel.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                for (int i = 0; i < this.luckyDrawImg.length; ++i) {
                    if (this.drawLotteryType[i] == 1) {
                        LotteryMainPanel.tcp.draw(g, this.luckyDrawImg[i].getX() - 5, this.luckyDrawImg[i].getY() - 5);
                    }
                }
            }
            if (this.drawGoods != null) {
                if (this.time % 15 == 0) {
                    int i = this.time / 15;
                    String[] goods = this.drawGoods[i].split("_");
                    Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(goods[0]));
                    if (goodstable != null) {
                        MouseListener[] mouseListeners = LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getLuckyDrawImg()[i].getMouseListeners();
                        PrizeMouseListener listener = (PrizeMouseListener)mouseListeners[0];
                        listener.setGoodsId(goods[0]);
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getLuckyDrawImg()[i].setIcon(GoodsListFromServerUntil.imgpath2(goodstable.getSkin()));
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getLuckyDrawName()[i].setText(goodstable.getGoodsname());
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getLuckyDrawNumber()[i].setText(goods[1]);
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getDrawLotteryType()[i] = Integer.parseInt(goods[2]);
                    }
                    if (i >= this.drawGoods.length - 1) {
                        this.drawGoods = null;
                    }
                }
                ++this.time;
            }
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/lottery/抽奖w655,h522.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 655, 522, this);
            if (this.drawLottery) {
                LotteryMainPanel.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                for (int i = 0; i < this.luckyDrawImg.length; ++i) {
                    if (this.drawLotteryType[i] == 1) {
                        LotteryMainPanel.tcp.draw(g, this.luckyDrawImg[i].getX() - 5, this.luckyDrawImg[i].getY() - 5);
                    }
                }
            }
            if (this.drawGoods != null) {
                if (this.time % 15 == 0) {
                    int i = this.time / 15;
                    String[] goods = this.drawGoods[i].split("_");
                    Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(goods[0]));
                    if (goodstable != null) {
                        MouseListener[] mouseListeners = LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getLuckyDrawImg()[i].getMouseListeners();
                        PrizeMouseListener listener = (PrizeMouseListener)mouseListeners[0];
                        listener.setGoodsId(goods[0]);
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getLuckyDrawImg()[i].setIcon(GoodsListFromServerUntil.imgpath2(goodstable.getSkin()));
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getLuckyDrawName()[i].setText(goodstable.getGoodsname());
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getLuckyDrawNumber()[i].setText(goods[1]);
                        LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getDrawLotteryType()[i] = Integer.parseInt(goods[2]);
                        if (Integer.parseInt(goods[2]) == 1) {
                            ZhuFrame.getZhuJpanel().addPrompt2("你获得了数量为" + goods[1] + "的" + goodstable.getGoodsname());
                        }
                    }
                    if (i >= this.drawGoods.length - 1) {
                        this.drawGoods = null;
                    }
                }
                ++this.time;
            }
        }
    }
    
    public void getlotteryTypeGoods(int type) {
        if (this.drawGoods == null) {
            String sendMes = Agreement.getFiveMsgAgreement("CC" + type);
            SendMessageUntil.toServer(sendMes);
            this.clearView();
            this.drawLottery = false;
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("正在抽奖，请勿切换面板！！");
        }
    }
    
    public void getImg(int i, int num, String imgPath) {
        try {
            if (this.menuBtn.size() > i) {
                ((LotteryMainBtn)this.menuBtn.get(i)).setIcons(CutButtonImage.cuts(imgPath));
                ((LotteryMainBtn)this.menuBtn.get(i)).setCaozuo(num);
            }
            else {
                this.menuBtn.add(new LotteryMainBtn(imgPath, 1, "", num, this));
                this.add((Component)this.menuBtn.get(i));
            }
            ((LotteryMainBtn)this.menuBtn.get(i)).setVisible(true);
            ((LotteryMainBtn)this.menuBtn.get(i)).setBounds(30 + i * 104, 25, 104, 40);
        }
        catch (Exception var5) {
            var5.printStackTrace();
        }
    }
    
    public void getMenuImg(QuackGameBean gameBean) {
        for (int k = 0; k < this.menuBtn.size(); ++k) {
            ((LotteryMainBtn)this.menuBtn.get(k)).setVisible(false);
        }
        String menuType = gameBean.getPetmsg();
        String[] split = menuType.split("\\|");
        int j = 0;
        this.lotteryType = 1;
        this.drawName = null;
        for (int i = 0; i < split.length; ++i) {
            if (!split[i].contains("天梯奖池")) {
                if (split[i].startsWith("Y")) {
                    int num = split[i].indexOf("-");
                    this.drawName = split[i].substring(num + 1) + "积分";
                    this.lotteryType = Integer.parseInt(split[i].substring(1, num));
                }
                else if (split[i].startsWith("N")) {
                    int num = split[i].indexOf("-");
                    num = Integer.parseInt(split[i].substring(1, num));
                }
                else {
                    Goodstable good = UserMessUntil.getgoodstable(new BigDecimal(split[i]));
                    if (good != null) {
                        System.out.println(good.getGoodsname());
                        System.out.println(good.getGoodsid());
                        this.getPrizeLab()[j].setIcon(GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49));
                        MouseListener[] mouseListeners = this.getPrizeLab()[j].getMouseListeners();
                        PrizeMouseListener listener = (PrizeMouseListener)mouseListeners[0];
                        listener.setGoodsId(split[i]);
                        ++j;
                    }
                }
            }
        }
        this.integralLab.setText("当前积分:" + RoleData.getRoleData().getLoginResult().getScoretype(this.drawName) + "");
        this.xianyuDraw = gameBean.getMoney();
        this.moenyType = 0;
        this.goodid = null;
        if (gameBean.getPetmsg2() != null) {
            int i = gameBean.getPetmsg2().indexOf("|");
            this.moenyType = Integer.parseInt(gameBean.getPetmsg2().substring(0, i));
            String msg = gameBean.getPetmsg2().substring(i + 1);
            this.goodid = (msg.equals("null") ? null : new BigDecimal(msg));
            StringBuffer buffer = new StringBuffer();
            buffer.append("当物品不足时消耗");
            buffer.append(this.xianyuDraw);
            buffer.append((this.moenyType == 0) ? "仙玉" : "大话币");
            buffer.append("代替");
            this.hintContent.setText(buffer.toString());
        }
        else {
            this.hintContent.setText("当物品不足时消耗" + gameBean.getMoney() + "仙玉代替");
        }
        Goodstable good = (this.goodid != null) ? UserMessUntil.getgoodstable(this.goodid) : null;
        if (good != null) {
            this.getConsumeGoodsImg().setIcon(GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49));
            int num = 0;
            for (int l = 0; l < GoodsListFromServerUntil.getGoodslist().length; ++l) {
                Goodstable goodstable = GoodsListFromServerUntil.getGoodslist()[l];
                if (goodstable != null && goodstable.getGoodsid().compareTo(this.goodid) == 0) {
                    num += (int)goodstable.getUsetime();
                }
            }
            this.getConsumeGoodsNumber().setText(String.valueOf(num));
        }
        else {
            this.getConsumeGoodsImg().setIcon(null);
            this.getConsumeGoodsNumber().setText("");
        }
    }
    
    public void clearView() {
        for (int j = 0; j < this.luckyDrawNumber.length; ++j) {
            this.luckyDrawNumber[j].setText("");
            this.luckyDrawName[j].setText("");
            this.luckyDrawImg[j].setIcon(CutButtonImage.getImage("img/xy2uiimg/101_png.xy2uiimg.pet_def.png", 43, 43));
            this.drawLotteryType[j] = 0;
        }
        for (int j = 0; j < this.luckyDrawImg.length; ++j) {
            MouseListener[] mouseListeners1 = this.luckyDrawImg[j].getMouseListeners();
            PrizeMouseListener listener1 = (PrizeMouseListener)mouseListeners1[0];
            listener1.setGoodsId(null);
        }
        this.time = 0;
    }
    
    public JLabel[] getPrizeLab() {
        if (this.prizeLab == null) {
            this.prizeLab = new JLabel[5];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.prizeLab.length; ++i) {
                    (this.prizeLab[i] = new JLabel()).setBounds(302 + i % 5 * 65, 91, 50, 50);
                    this.prizeLab[i].addMouseListener(new PrizeMouseListener(null));
                    this.prizeLab[i].setOpaque(false);
                    this.add(this.prizeLab[i]);
                }
            }
            else {
                for (int i = 0; i < this.prizeLab.length; ++i) {
                    (this.prizeLab[i] = new JLabel()).setBounds(287 + i % 5 * 68, 98, 50, 50);
                    this.prizeLab[i].addMouseListener(new PrizeMouseListener(null));
                    this.prizeLab[i].setOpaque(false);
                    this.add(this.prizeLab[i]);
                }
            }
        }
        return this.prizeLab;
    }
    
    public void setPrizeLab(JLabel[] prizeLab) {
        this.prizeLab = prizeLab;
    }
    
    public JLabel getConsumeGoodsImg() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.consumeGoodsImg == null) {
                (this.consumeGoodsImg = new JLabel()).setOpaque(false);
                this.consumeGoodsImg.setBounds(212, 381, 43, 43);
                this.add(this.consumeGoodsImg);
            }
        }
        else if (this.consumeGoodsImg == null) {
            (this.consumeGoodsImg = new JLabel()).setOpaque(false);
            this.consumeGoodsImg.setBounds(195, 413, 43, 43);
            this.consumeGoodsImg.addMouseListener(new WLLMouslisten(31));
            this.add(this.consumeGoodsImg);
        }
        return this.consumeGoodsImg;
    }
    
    public void setConsumeGoodsImg(JLabel consumeGoodsImg) {
        this.consumeGoodsImg = consumeGoodsImg;
    }
    
    public JLabel getConsumeGoodsNumber() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.consumeGoodsNumber == null) {
                (this.consumeGoodsNumber = new JLabel()).setBounds(350, 373, 113, 16);
                this.consumeGoodsNumber.setOpaque(false);
                this.consumeGoodsNumber.setForeground(Color.white);
                this.add(this.consumeGoodsNumber);
            }
            return this.consumeGoodsNumber;
        }
        else {
            if (this.consumeGoodsNumber == null) {
                (this.consumeGoodsNumber = new JLabel()).setBounds(329, 406, 113, 16);
                this.consumeGoodsNumber.setOpaque(false);
                this.consumeGoodsNumber.setForeground(Color.white);
                this.add(this.consumeGoodsNumber);
            }
            return this.consumeGoodsNumber;
        }
    }
    
    public void setConsumeGoodsNumber(JLabel consumeGoodsNumber) {
        this.consumeGoodsNumber = consumeGoodsNumber;
    }
    
    public LotteryMainBtn getOneLotteryBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.oneLotteryBtn == null) {
                (this.oneLotteryBtn = new LotteryMainBtn("inkImg/button/B59.png", 1, "抽奖", 10, this, null)).setBounds(291, 393, 68, 26);
                this.add(this.oneLotteryBtn);
            }
        }
        else if (this.oneLotteryBtn == null) {
            (this.oneLotteryBtn = new LotteryMainBtn("inkImg/hongmu/6026.png", 1, "抽奖", 10, this)).setBounds(261, 431, 68, 26);
            this.add(this.oneLotteryBtn);
        }
        return this.oneLotteryBtn;
    }
    
    public void setOneLotteryBtn(LotteryMainBtn oneLotteryBtn) {
        this.oneLotteryBtn = oneLotteryBtn;
    }
    
    public LotteryMainBtn getTenLotteryBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.tenLotteryBtn == null) {
                (this.tenLotteryBtn = new LotteryMainBtn("inkImg/button/B60.png", 1, "十连抽奖", 11, this, null)).setBounds(365, 393, 92, 26);
                this.add(this.tenLotteryBtn);
            }
        }
        else if (this.tenLotteryBtn == null) {
            (this.tenLotteryBtn = new LotteryMainBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "十连抽奖", 11, this)).setBounds(341, 431, 80, 26);
            this.add(this.tenLotteryBtn);
        }
        return this.tenLotteryBtn;
    }
    
    public void setTenLotteryBtn(LotteryMainBtn tenLotteryBtn) {
        this.tenLotteryBtn = tenLotteryBtn;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public LotteryMainBtn getHelpBtn() {
        if (this.helpBtn == null) {
            (this.helpBtn = new LotteryMainBtn("img/lottery/？w17,h51px.png", 1, "?", 20, this)).setBounds(604, 195, 17, 17);
            this.add(this.helpBtn);
        }
        return this.helpBtn;
    }
    
    public void setHelpBtn(LotteryMainBtn helpBtn) {
        this.helpBtn = helpBtn;
    }
    
    public JLabel getHintCheckbox() {
        if (this.hintCheckbox == null) {
            (this.hintCheckbox = new JLabel()).setIcon(CutButtonImage.getImage("img/xy2uiimg/showjadesuit.png", 15, 15));
            if (MyIsif.getStyle().equals("水墨")) {
                this.hintCheckbox.setBounds(288, 423, 15, 15);
            }
            else {
                this.hintCheckbox.setBounds(264, 460, 15, 15);
            }
            this.hintCheckbox.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (LotteryMainPanel.this.hintType) {
                        LotteryMainPanel.this.hintCheckbox.setIcon(null);
                    }
                    else {
                        LotteryMainPanel.this.hintCheckbox.setIcon(CutButtonImage.getImage("img/xy2uiimg/showjadesuit.png", 15, 15));
                    }
                    LotteryMainPanel.this.hintType = !LotteryMainPanel.this.hintType;
                }
            });
            this.add(this.hintCheckbox);
        }
        return this.hintCheckbox;
    }
    
    public void setHintCheckbox(JLabel hintCheckbox) {
        this.hintCheckbox = hintCheckbox;
    }
    
    public JLabel getHintContent() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.hintContent == null) {
                (this.hintContent = new JLabel("")).setBounds(308, 424, 300, 17);
                this.hintContent.setOpaque(false);
                this.hintContent.setFont(UIUtils.TEXT_FONT);
                this.hintContent.setForeground(Color.BLACK);
                this.add(this.hintContent);
            }
        }
        else if (this.hintContent == null) {
            (this.hintContent = new JLabel("")).setBounds(284, 458, 300, 17);
            this.hintContent.setOpaque(false);
            this.hintContent.setFont(UIUtils.TEXT_FONT);
            this.hintContent.setForeground(Color.WHITE);
            this.add(this.hintContent);
        }
        return this.hintContent;
    }
    
    public void setHintContent(JLabel hintContent) {
        this.hintContent = hintContent;
    }
    
    public JLabel[] getLuckyDrawImg() {
        if (this.luckyDrawImg == null) {
            this.luckyDrawImg = new JLabel[12];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.luckyDrawImg.length; ++i) {
                    (this.luckyDrawImg[i] = new JLabel()).setOpaque(false);
                    this.luckyDrawImg[i].setBounds(91 + i % 6 * 90, 222 + i / 6 * 79, 43, 43);
                    this.luckyDrawImg[i].addMouseListener(new PrizeMouseListener(null));
                    this.add(this.luckyDrawImg[i]);
                }
            }
            else {
                for (int i = 0; i < this.luckyDrawImg.length; ++i) {
                    (this.luckyDrawImg[i] = new JLabel()).setOpaque(false);
                    this.luckyDrawImg[i].setBounds(65 + i % 6 * 96, 213 + i / 6 * 100, 43, 43);
                    this.luckyDrawImg[i].addMouseListener(new PrizeMouseListener(null));
                    this.add(this.luckyDrawImg[i]);
                }
            }
        }
        return this.luckyDrawImg;
    }
    
    public JLabel[] getLuckyDrawNumber() {
        if (this.luckyDrawNumber == null) {
            this.luckyDrawNumber = new JLabel[12];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.luckyDrawNumber.length; ++i) {
                    (this.luckyDrawNumber[i] = new JLabel(i * 7 + "", 4)).setOpaque(false);
                    this.luckyDrawNumber[i].setForeground(Color.white);
                    this.luckyDrawNumber[i].setBounds(80 + i % 6 * 90, 220 + i / 6 * 79, 35, 10);
                    this.luckyDrawNumber[i].setHorizontalAlignment(0);
                    this.add(this.luckyDrawNumber[i]);
                }
            }
            else {
                for (int i = 0; i < this.luckyDrawNumber.length; ++i) {
                    (this.luckyDrawNumber[i] = new JLabel(String.valueOf(i * 7), 4)).setOpaque(false);
                    this.luckyDrawNumber[i].setForeground(Color.white);
                    this.luckyDrawNumber[i].setBounds(67 + i % 6 * 96, 215 + i / 6 * 100, 35, 10);
                    this.luckyDrawNumber[i].setHorizontalAlignment(2);
                    this.add(this.luckyDrawNumber[i]);
                }
            }
        }
        return this.luckyDrawNumber;
    }
    
    public JLabel[] getLuckyDrawName() {
        if (this.luckyDrawName == null) {
            this.luckyDrawName = new JLabel[12];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.luckyDrawName.length; ++i) {
                    (this.luckyDrawName[i] = new JLabel("九彩云龙珠", 0)).setOpaque(false);
                    this.luckyDrawName[i].setForeground(UIUtils.COLOR_Wing1);
                    this.luckyDrawName[i].setBounds(75 + i % 6 * 90, 273 + i / 6 * 79, 75, 20);
                    this.add(this.luckyDrawName[i]);
                }
            }
            else {
                for (int i = 0; i < this.luckyDrawName.length; ++i) {
                    (this.luckyDrawName[i] = new JLabel("九彩云龙珠", 0)).setOpaque(false);
                    this.luckyDrawName[i].setForeground(UIUtils.COLOR_Wing1);
                    this.luckyDrawName[i].setBounds(50 + i % 6 * 96, 273 + i / 6 * 100, 75, 20);
                    this.add(this.luckyDrawName[i]);
                }
            }
        }
        return this.luckyDrawName;
    }
    
    public void setLuckyDrawName(JLabel[] luckyDrawName) {
        this.luckyDrawName = luckyDrawName;
    }
    
    public int getLotteryType() {
        return this.lotteryType;
    }
    
    public void setLotteryType(int lotteryType) {
        this.lotteryType = lotteryType;
    }
    
    public boolean isHintType() {
        return this.hintType;
    }
    
    public void setHintType(boolean hintType) {
        this.hintType = hintType;
    }
    
    public BigDecimal getXianyuDraw() {
        if (this.xianyuDraw == null) {
            this.xianyuDraw = new BigDecimal(0);
        }
        return this.xianyuDraw;
    }
    
    public void setXianyuDraw(BigDecimal xianyuDraw) {
        this.xianyuDraw = xianyuDraw;
    }
    
    public boolean isDrawLottery() {
        return this.drawLottery;
    }
    
    public void setDrawLottery(boolean drawLottery) {
        this.drawLottery = drawLottery;
    }
    
    public int[] getDrawLotteryType() {
        if (this.drawLotteryType == null) {
            this.drawLotteryType = new int[12];
            for (int i = 0; i < this.drawLotteryType.length; ++i) {
                this.drawLotteryType[i] = 0;
            }
        }
        return this.drawLotteryType;
    }
    
    public void setDrawLotteryType(int[] drawLotteryType) {
        this.drawLotteryType = drawLotteryType;
    }
    
    public JLabel getIntegralLab() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.integralLab == null) {
                (this.integralLab = new JLabel("当前积分：0", 4)).setBounds(287, 68, 260, 17);
                this.integralLab.setFont(UIUtils.TEXT_FONT);
                this.integralLab.setOpaque(false);
                this.integralLab.setForeground(UIUtils.COLOR_NAME8);
                this.add(this.integralLab);
            }
        }
        else if (this.integralLab == null) {
            (this.integralLab = new JLabel("当前积分：0", 4)).setBounds(287, 78, 260, 17);
            this.integralLab.setFont(UIUtils.TEXT_FONT);
            this.integralLab.setOpaque(false);
            this.integralLab.setForeground(UIUtils.COLOR_HURTB3);
            this.add(this.integralLab);
        }
        return this.integralLab;
    }
    
    public void setIntegralLab(JLabel integralLab) {
        this.integralLab = integralLab;
    }
    
    public LotteryMainBtn getIntegralBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.integralBtn == null) {
                (this.integralBtn = new LotteryMainBtn("inkImg/button/49.png", 1, "积分兑换", 21, this)).setBounds(550, 68, 68, 17);
                this.integralBtn.setOpaque(false);
                this.add(this.integralBtn);
            }
        }
        else if (this.integralBtn == null) {
            (this.integralBtn = new LotteryMainBtn("inkImg/hongmu/a7.png", 1, "积分兑换", 21, this)).setBounds(550, 78, 68, 17);
            this.integralBtn.setOpaque(false);
            this.add(this.integralBtn);
        }
        return this.integralBtn;
    }
    
    public void setIntegralBtn(LotteryMainBtn integralBtn) {
        this.integralBtn = integralBtn;
    }
    
    public String[] getDrawGoods() {
        return this.drawGoods;
    }
    
    public void setDrawGoods(String[] drawGoods) {
        this.drawGoods = drawGoods;
    }
    
    public List<LotteryMainBtn> getMenuBtn() {
        if (this.menuBtn == null) {
            this.menuBtn = new ArrayList<>();
        }
        return this.menuBtn;
    }
    
    public void setMenuBtn(List<LotteryMainBtn> menuBtn) {
        this.menuBtn = menuBtn;
    }
    
    public int getMoenyType() {
        return this.moenyType;
    }
    
    public void setMoenyType(int moenyType) {
        this.moenyType = moenyType;
    }
    
    public BigDecimal getGoodid() {
        return this.goodid;
    }
    
    public void setGoodid(BigDecimal goodid) {
        this.goodid = goodid;
    }
    
    public String getDrawName() {
        return this.drawName;
    }
    
    public void setDrawName(String drawName) {
        this.drawName = drawName;
    }
    
    static {
        LotteryMainPanel.tcp = SpriteFactory.VloadSprite("resource/mouse/flicker.tcp", null);
    }
}
