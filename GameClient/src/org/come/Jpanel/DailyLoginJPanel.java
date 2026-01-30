package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import org.cbg.until.TraslationDemoScrollBarUI;
import org.come.until.CutButtonImage;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import com.tool.btn.TaoBaoBtn;
import javax.swing.JPanel;

public class DailyLoginJPanel extends JPanel
{
    private String[] menuName;
    private TaoBaoBtn[] menuBtn;
    private int menuType;
    private JLabel menuStr;
    private JScrollPane scrollPane;
    private TaoBaoBtn customBtn;
    private DailyLoginGoodsJpanel[] dailyLoginGoodsJpanel;
    private ImageIcon iconBack;
    
    public DailyLoginJPanel() {
        this.menuName = new String[] { "每日签到", "周/月卡领奖" };
        this.menuBtn = new TaoBaoBtn[2];
        this.menuType = 1;
        this.dailyLoginGoodsJpanel = new DailyLoginGoodsJpanel[31];
        this.setPreferredSize(new Dimension(589, 479));
        this.setLayout(null);
        this.setOpaque(false);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 86);
        offBtn.setBounds(564, 3, 25, 25);
        this.add(offBtn);
        for (int i = 0; i < this.menuBtn.length; ++i) {
            (this.menuBtn[i] = new TaoBaoBtn("inkImg/button/6.png", 1, this.menuName[i], i + 60, this)).setBounds(60 + i * 72, 22, 70, 35);
            this.menuBtn[i].setOpaque(false);
            this.add(this.menuBtn[i]);
        }
        try {
            this.menuBtn[0].setIcons(CutButtonImage.cuts("inkImg/button/5.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
        this.scrollPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.scrollPane));
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        this.scrollPane.getViewport().setOpaque(false);
        this.scrollPane.setOpaque(false);
        this.scrollPane.setBounds(48, 97, 522, 359);
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.scrollPane.setHorizontalScrollBarPolicy(31);
        JPanel jPanel = new JPanel();
        jPanel.setOpaque(false);
        jPanel.setLayout(null);
        for (int j = 0; j < this.dailyLoginGoodsJpanel.length; ++j) {
            int row = j / 2;
            int rank = j % 2;
            (this.dailyLoginGoodsJpanel[j] = new DailyLoginGoodsJpanel()).setBounds(rank * 259 + 0, row * 65 + 0, 250, 65);
            jPanel.add(this.dailyLoginGoodsJpanel[j]);
        }
        jPanel.setPreferredSize(new Dimension(656, 65 * (this.dailyLoginGoodsJpanel.length / 2 + 1)));
        this.scrollPane.setViewportView(jPanel);
        this.add(this.scrollPane);
        this.scrollPane.updateUI();
        this.scrollPane.invalidate();
        this.scrollPane.validate();
        this.scrollPane.repaint();
        this.add(this.getMenuStr());
        this.add(this.getCustomBtn());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = new ImageIcon("inkImg/background/56.png");
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 589, 479, this);
    }
    
    public String[] getMenuName() {
        return this.menuName;
    }
    
    public void setMenuName(String[] menuName) {
        this.menuName = menuName;
    }
    
    public TaoBaoBtn[] getMenuBtn() {
        return this.menuBtn;
    }
    
    public void setMenuBtn(TaoBaoBtn[] menuBtn) {
        this.menuBtn = menuBtn;
    }
    
    public int getMenuType() {
        return this.menuType;
    }
    
    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }
    
    public JLabel getMenuStr() {
        if (this.menuStr == null) {
            (this.menuStr = new JLabel()).setOpaque(false);
            this.menuStr.setBounds(45, 78, 522, 20);
            this.menuStr.setForeground(Color.white);
            this.menuStr.setFont(UIUtils.TEXT_HY17);
            this.menuStr.setText(this.menuName[0]);
            this.menuStr.setHorizontalAlignment(0);
        }
        return this.menuStr;
    }
    
    public void setMenuStr(JLabel menuStr) {
        this.menuStr = menuStr;
    }
    
    public JScrollPane getScrollPane() {
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public DailyLoginGoodsJpanel[] getDailyLoginGoodsJpanel() {
        return this.dailyLoginGoodsJpanel;
    }
    
    public void setDailyLoginGoodsJpanel(DailyLoginGoodsJpanel[] dailyLoginGoodsJpanel) {
        this.dailyLoginGoodsJpanel = dailyLoginGoodsJpanel;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public TaoBaoBtn getCustomBtn() {
        if (this.customBtn == null) {
            (this.customBtn = new TaoBaoBtn("inkImg/button/34.png", 1, "自定义", 70, this)).setBounds(516, 60, 51, 17);
            this.customBtn.setOpaque(false);
        }
        return this.customBtn;
    }
    
    public void setCustomBtn(TaoBaoBtn customBtn) {
        this.customBtn = customBtn;
    }
}
