package org.come.xingpan;

import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import org.come.entity.Goodstable;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpanelXingBackMain extends JPanel
{
    public static String[] starSouls;
    private BtnStarCard starCardBtn;
    private JLabel[] shuliang;
    private JLabel[] icon;
    private ImageIcon iconBack;
    
    public JpanelXingBackMain() {
        this.setPreferredSize(new Dimension(462, 445));
        this.setLayout(null);
        this.setOpaque(false);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 121);
        offBtn.setBounds(432, 10, 25, 25);
        this.add(offBtn);
        this.getShuliang();
        this.getIcon();
        this.getStarCardBtn();
        this.init();
    }
    
    public void init() {
        for (int i = 0; i < JpanelXingBackMain.starSouls.length; ++i) {
            Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(51000 + i));
            int goodNum = GoodsListFromServerUntil.getStarSoulNum(goodstable.getGoodsid());
            if (goodNum > 0) {
                this.icon[i].setIcon(CutButtonImage.getImage("resource/mouse/xp/XPSoul1.png", 43, 44));
            }
            else {
                this.icon[i].setIcon(CutButtonImage.getImage("resource/mouse/xp/XPSoul2.png", 43, 44));
            }
            this.shuliang[i].setText("" + goodNum);
        }
    }
    
    public JLabel[] getShuliang() {
        if (this.shuliang == null) {
            this.shuliang = new JLabel[JpanelXingBackMain.starSouls.length];
            for (int i = 0; i < this.shuliang.length; ++i) {
                (this.shuliang[i] = new JLabel()).setFont(new Font("宋体", 1, 15));
                this.shuliang[i].setForeground(Color.WHITE);
                this.shuliang[i].setBounds(118 + i / 6 * 51, 70 + i % 6 * 51, 18, 18);
                this.add(this.shuliang[i]);
            }
        }
        return this.shuliang;
    }
    
    public JLabel[] getIcon() {
        if (this.icon == null) {
            this.icon = new JLabel[JpanelXingBackMain.starSouls.length];
            for (int i = 0; i < this.icon.length; ++i) {
                int finalI = i;
                (this.icon[i] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.setFont(UIUtils.TEXT_HY15);
                        g.setColor(Color.BLACK);
                        int height = g.getFontMetrics().getHeight();
                        char[] charArray = JpanelXingBackMain.starSouls[finalI].toCharArray();
                        g.drawString(charArray[0] + "", 14, height + 5);
                        g.drawString(charArray[1] + "", 14, height + 5 + height);
                    }
                }).setBounds(119 + i / 6 * 51, 70 + i % 6 * 51, 50, 50);
                this.add(this.icon[i]);
            }
        }
        return this.icon;
    }
    
    public BtnStarCard getStarCardBtn() {
        if (this.starCardBtn == null) {
            (this.starCardBtn = new BtnStarCard("inkImg/button/32.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "重炼", 9, this)).setBounds(180, 392, 65, 24);
            this.add(this.starCardBtn);
        }
        return this.starCardBtn;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = new ImageIcon("resource/mouse/xp/收录.png");
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 462, 445, this);
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    static {
        JpanelXingBackMain.starSouls = new String[] { "天巧", "天哭", "天暴", "天慧", "天牢", "天败", "天损", "天罪", "天竟", "天剑", "天寿", "天退", "天究", "天微", "天杀", "天异", "天速", "天空", "天佑", "天暗", "天捷", "天立", "天伤", "天孤", "天满", "天富", "天贵", "天英", "天威", "天猛", "天雄", "天勇", "天闲", "天机", "天罡", "天魁" };
    }
}
