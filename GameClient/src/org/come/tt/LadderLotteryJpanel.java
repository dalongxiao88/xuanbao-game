package org.come.tt;

import com.tool.tcp.SpriteFactory;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import org.come.entity.Goodstable;
import org.lottery.mouseListener.PrizeMouseListener;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;
import org.come.bean.QuackGameBean;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.tool.tcpimg.UIUtils;
import com.tool.role.RoleData;
import com.tool.tcp.Sprite;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LadderLotteryJpanel extends JPanel
{
    private ImageIcon beijing;
    private JLabel[] prizeLab;
    private LadderLotteryBtn oneLotteryBtn;
    private LadderLotteryBtn tenLotteryBtn;
    private String[] drawGoods;
    private JLabel integralLab;
    private JLabel consumePoints;
    private Boolean lotteryMarker;
    private int[] drawLotteryType;
    private static Sprite tcp;
    private int time;
    private Integer a;
    private Integer b;
    
    public JLabel getIntegralLab() {
        if (this.integralLab == null) {
            this.integralLab = new JLabel(RoleData.getRoleData().getLoginResult().getScoretype("天梯积分").toString());
            this.getIntegralLab().setBounds(360, 170, 260, 17);
            this.integralLab.setFont(UIUtils.TEXT_FONT6);
            this.integralLab.setOpaque(false);
            this.integralLab.setForeground(UIUtils.COLOR_NAME3);
            this.add(this.integralLab);
        }
        return this.integralLab;
    }
    
    public int[] getDrawLotteryType() {
        if (this.drawLotteryType == null) {
            this.drawLotteryType = new int[16];
            for (int i = 0; i < this.drawLotteryType.length; ++i) {
                this.drawLotteryType[i] = 0;
                if (i == 5) {
                    this.drawLotteryType[i] = 1;
                }
            }
        }
        return this.drawLotteryType;
    }
    
    public JLabel getConsumePoints() {
        if (this.consumePoints == null) {
            this.consumePoints = new JLabel();
            this.getConsumePoints().setBounds(360, 198, 260, 17);
            this.consumePoints.setFont(UIUtils.TEXT_FONT6);
            this.consumePoints.setOpaque(false);
            this.consumePoints.setForeground(UIUtils.COLOR_NAME3);
            this.add(this.consumePoints);
        }
        return this.consumePoints;
    }
    
    public LadderLotteryJpanel() {
        this.lotteryMarker = Boolean.valueOf(true);
        this.time = 0;
        this.a = null;
        this.b = Integer.valueOf(30);
        this.setPreferredSize(new Dimension(652, 446));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        this.beijing = new ImageIcon("inkImg/background/tt-cj.png");
        this.getIntegralLab();
        this.getOneLotteryBtn();
        this.getPrizeLab();
        this.getConsumePoints();
        this.getDrawLotteryType();
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 606);
        offBtn.setBounds(647, 10, 25, 25);
        this.add(offBtn);
    }
    
    public void getlotteryTypeGoods(int type) {
        String sendMes = Agreement.getFiveMsgAgreement("CC" + type);
        SendMessageUntil.toServer(sendMes);
    }
    
    public String[] getDrawGoods() {
        return this.drawGoods;
    }
    
    public void getMenuImg(QuackGameBean gameBean) {
        if (gameBean.getType() == 7) {
            return;
        }
        String menuType = gameBean.getPetmsg();
        String[] split = menuType.split("\\|");
        JLabel consumePoints = this.getConsumePoints();
        consumePoints.setText(gameBean.getMoney().toString());
        int j = 0;
        for (int i = 0; i < split.length - 1; ++i) {
            if (!split[i].startsWith("Y") && !split[i].startsWith("N")) {
                Goodstable good = UserMessUntil.getgoodstable(new BigDecimal(split[i]));
                if (good != null) {
                    this.getPrizeLab()[j].setIcon(GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49));
                    MouseListener[] mouseListeners = this.getPrizeLab()[j].getMouseListeners();
                    PrizeMouseListener listener = (PrizeMouseListener)mouseListeners[0];
                    listener.setGoodsId(split[i]);
                    ++j;
                }
            }
        }
    }
    
    public LadderLotteryBtn getOneLotteryBtn() {
        if (this.oneLotteryBtn == null) {
            (this.oneLotteryBtn = new LadderLotteryBtn("inkImg/button/B59.png", 1, "抽奖", 10, this)).setBounds(291, 393, 68, 26);
            this.add(this.oneLotteryBtn);
        }
        return this.oneLotteryBtn;
    }
    
    public JLabel[] getPrizeLab() {
        if (this.prizeLab == null) {
            this.prizeLab = new JLabel[16];
            for (int i = 0; i < this.prizeLab.length; ++i) {
                (this.prizeLab[i] = new JLabel()).setBounds(i % 6 * 95 + 65, 55, 50, 50);
                if (i >= 6 && i <= 7) {
                    this.prizeLab[i].setBounds(i % 2 * 470 + 65, 148, 50, 50);
                }
                if (i >= 8 && i <= 9) {
                    this.prizeLab[i].setBounds(i % 2 * 470 + 65, 238, 50, 50);
                }
                if (i >= 10) {
                    this.prizeLab[i].setBounds(i % 6 * 95 + 65, 330, 50, 50);
                }
                this.prizeLab[i].addMouseListener(new PrizeMouseListener(null));
                this.prizeLab[i].setOpaque(false);
                this.add(this.prizeLab[i]);
            }
        }
        return this.prizeLab;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.beijing.getImage(), 0, 0, 652, 446, null);
        this.integralLab.setText(RoleData.getRoleData().getLoginResult().getScoretype("天梯积分").toString());
        LadderLotteryJpanel.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
        for (JLabel jLabel : this.prizeLab) {
            LadderLotteryJpanel.tcp.draw(g, (int)jLabel.getBounds().getX() - 1, (int)jLabel.getBounds().getY() - 1);
        }
    }
    
    static {
        LadderLotteryJpanel.tcp = SpriteFactory.VloadSprite("resource/mouse/flicker.tcp", null);
    }
}
