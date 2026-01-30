package org.come.Jpanel;

import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.come.bean.ShaoXiangLimit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.UserMessUntil;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import com.tool.btn.ShaoXiangBtn;
import org.skill.panel.EquipmentJpanel;
import org.cbg.btn.TrslationBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShaoXiangJpanel extends JPanel
{
    private JLabel chooseLeft;
    private TrslationBtn chooseLeftArrows;
    private EquipmentJpanel optionJpanel;
    private JLabel chooseItemLeft;
    private TrslationBtn chooseItemLeftArrows;
    private EquipmentJpanel optionItemJpanel;
    private ShaoXiangBtn btnmount;
    private int leftFlag;
    private int leftItemFlag;
    private ImageIcon icon;
    private ImageIcon licon;
    private JProgressBar jProgressBar;
    private int xianghuoNum;
    private int xianghuoNum1;
    
    public ShaoXiangJpanel() {
        this.licon = new ImageIcon("img/xy2uiimg/78_png.xy2uiimg.btn_plock.png");
        this.xianghuoNum = 0;
        this.xianghuoNum1 = 0;
        this.icon = new ImageIcon("inkImg/background1/shgf.png");
        this.setPreferredSize(new Dimension(320, 393));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 636);
        offBtn.setBounds(280, 10, 25, 25);
        this.add(offBtn);
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        String[] rowData = new String[0];
        if (UserMessUntil.getAllshaoxiang() != null && UserMessUntil.getAllshaoxiang().size() > 0) {
            rowData = (String[])UserMessUntil.getAllshaoxiang().toArray(new String[0]);
            this.chooseLeft = new JLabel((String)UserMessUntil.getAllshaoxiang().get(0));
        }
        else {
            this.chooseLeft = new JLabel("人物经验(1/次)");
        }
        (this.chooseLeftArrows = new TrslationBtn("inkimg/button/8.png", 1)).setBounds(260, 43, 19, 20);
        this.chooseLeftArrows.setOpaque(false);
        this.add(this.chooseLeftArrows);
        (this.optionJpanel = new EquipmentJpanel(140, 120, rowData)).setVisible(false);
        this.optionJpanel.setBounds(140, 62, 140, 120);
        this.add(this.optionJpanel);
        this.chooseLeftArrows.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (ShaoXiangJpanel.this.leftFlag == 1) {
                    ShaoXiangJpanel.this.optionJpanel.setVisible(true);
                    ShaoXiangJpanel.this.leftFlag = 0;
                }
                else {
                    ShaoXiangJpanel.this.optionJpanel.setVisible(false);
                    ShaoXiangJpanel.this.leftFlag = 1;
                }
            }
        });
        this.optionJpanel.getJlist().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String getname = (String)ShaoXiangJpanel.this.optionJpanel.getJlist().getSelectedValue();
                ShaoXiangJpanel.this.chooseLeft.setText(getname);
                ShaoXiangJpanel.this.optionJpanel.setVisible(false);
                ShaoXiangJpanel.this.leftFlag = 1;
                if (getname.startsWith("装备特技") || getname.startsWith("装备二特技")) {
                    ShaoXiangJpanel.this.chooseItemLeft.setEnabled(true);
                    ShaoXiangJpanel.this.optionItemJpanel.setVisible(false);
                    ShaoXiangJpanel.this.chooseItemLeftArrows.setEnabled(true);
                }
                else {
                    ShaoXiangJpanel.this.chooseItemLeft.setEnabled(false);
                    ShaoXiangJpanel.this.optionItemJpanel.setVisible(false);
                    ShaoXiangJpanel.this.chooseItemLeftArrows.setEnabled(false);
                }
            }
        });
        (this.btnmount = new ShaoXiangBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, 1, "烧香", this)).setBounds(160, 350, 59, 24);
        this.add(this.btnmount);
        this.chooseLeft.setForeground(Color.white);
        this.chooseLeft.setFont(UIUtils.TEXT_FONT2);
        this.chooseLeft.setBounds(150, 42, 154, 20);
        this.add(this.chooseLeft);
        (this.jProgressBar = new JProgressBar()).setStringPainted(true);
        this.jProgressBar.setBounds(114, 304, 160, 18);
        this.add(this.jProgressBar);
        (this.chooseItemLeft = new JLabel("武器")).setEnabled(false);
        (this.chooseItemLeftArrows = new TrslationBtn("inkimg/button/8.png", 1)).setBounds(260, 78, 19, 20);
        this.chooseItemLeftArrows.setEnabled(false);
        this.chooseItemLeftArrows.setOpaque(false);
        this.add(this.chooseItemLeftArrows);
        String[] rowItemData = { "武器", "帽子", "项链", "衣服", "护身符", "鞋子" };
        (this.optionItemJpanel = new EquipmentJpanel(140, 120, rowItemData)).setVisible(false);
        this.optionItemJpanel.setVisible(false);
        this.optionItemJpanel.setBounds(140, 97, 140, 120);
        this.add(this.optionItemJpanel);
        this.chooseItemLeftArrows.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (ShaoXiangJpanel.this.chooseItemLeftArrows.isEnabled()) {
                    if (ShaoXiangJpanel.this.leftItemFlag == 1) {
                        ShaoXiangJpanel.this.optionItemJpanel.setVisible(true);
                        ShaoXiangJpanel.this.leftItemFlag = 0;
                    }
                    else {
                        ShaoXiangJpanel.this.optionItemJpanel.setVisible(false);
                        ShaoXiangJpanel.this.leftItemFlag = 1;
                    }
                }
            }
        });
        this.optionItemJpanel.getJlist().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String getname = (String)ShaoXiangJpanel.this.optionItemJpanel.getJlist().getSelectedValue();
                ShaoXiangJpanel.this.chooseItemLeft.setText(getname);
                ShaoXiangJpanel.this.optionItemJpanel.setVisible(false);
                ShaoXiangJpanel.this.leftItemFlag = 1;
            }
        });
        this.chooseItemLeft.setForeground(Color.white);
        this.chooseItemLeft.setFont(UIUtils.TEXT_FONT2);
        this.chooseItemLeft.setBounds(150, 77, 154, 20);
        this.add(this.chooseItemLeft);
        String getname = (String)this.optionJpanel.getJlist().getSelectedValue();
        if (getname != null) {
            if (getname.startsWith("装备二特技")) {
                this.setXianghuoNum1();
            }
            else {
                this.setXianghuoNum();
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.icon.getImage(), 0, 0, 320, 393, this);
        g.setColor(Color.BLACK);
        g.setFont(UIUtils.TEXT_FONT1);
        String slectstr = this.chooseLeft.getText();
        ConcurrentHashMap<String, ShaoXiangLimit> alllimit = UserMessUntil.getAllshaoxianglimit();
        if (slectstr != null && alllimit != null) {
            ShaoXiangLimit shaoXianglimit = (ShaoXiangLimit)alllimit.get(slectstr);
            g.drawString(String.valueOf((shaoXianglimit == null) ? 0 : shaoXianglimit.getNum()) + "/" + String.valueOf((shaoXianglimit == null) ? 0 : shaoXianglimit.getLimit()), 170, 262);
        }
        else {
            g.drawString("0/0", 170, 262);
        }
        String getname = (String)this.optionJpanel.getJlist().getSelectedValue();
        if (getname != null) {
            if (getname.startsWith("装备二特技")) {
                g.drawString(String.valueOf(this.xianghuoNum1), 170, 290);
            }
            else {
                g.drawString(String.valueOf(this.xianghuoNum), 170, 290);
            }
        }
        if ((this.optionJpanel.getListModel() == null || this.optionJpanel.getListModel().size() <= 0) && UserMessUntil.getAllshaoxiang() != null) {
            int i = 0;
            for (String entry : UserMessUntil.getAllshaoxiang()) {
                this.optionJpanel.getListModel().add(i, entry);
                ++i;
            }
        }
        this.setXianghuoNum();
        this.setXianghuoNum1();
    }
    
    public JLabel getChooseLeft() {
        return this.chooseLeft;
    }
    
    public void setChooseLeft(JLabel chooseLeft) {
        this.chooseLeft = chooseLeft;
    }
    
    public TrslationBtn getChooseLeftArrows() {
        return this.chooseLeftArrows;
    }
    
    public void setChooseLeftArrows(TrslationBtn chooseLeftArrows) {
        this.chooseLeftArrows = chooseLeftArrows;
    }
    
    public EquipmentJpanel getOptionJpanel() {
        return this.optionJpanel;
    }
    
    public void setOptionJpanel(EquipmentJpanel optionJpanel) {
        this.optionJpanel = optionJpanel;
    }
    
    public ShaoXiangBtn getBtnmount() {
        return this.btnmount;
    }
    
    public void setBtnmount(ShaoXiangBtn btnmount) {
        this.btnmount = btnmount;
    }
    
    public int getLeftFlag() {
        return this.leftFlag;
    }
    
    public void setLeftFlag(int leftFlag) {
        this.leftFlag = leftFlag;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public ImageIcon getLicon() {
        return this.licon;
    }
    
    public void setLicon(ImageIcon licon) {
        this.licon = licon;
    }
    
    public JProgressBar getjProgressBar() {
        return this.jProgressBar;
    }
    
    public void setjProgressBar(JProgressBar jProgressBar) {
        this.jProgressBar = jProgressBar;
    }
    
    public int getXianghuoNum() {
        return this.xianghuoNum;
    }
    
    public void setXianghuoNum() {
        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(90002));
        this.xianghuoNum = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
    }
    
    public int getXianghuoNum1() {
        return this.xianghuoNum1;
    }
    
    public void setXianghuoNum1() {
        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(90006));
        this.xianghuoNum1 = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
    }
    
    public JLabel getChooseItemLeft() {
        return this.chooseItemLeft;
    }
    
    public void setChooseItemLeft(JLabel chooseItemLeft) {
        this.chooseItemLeft = chooseItemLeft;
    }
    
    public TrslationBtn getChooseItemLeftArrows() {
        return this.chooseItemLeftArrows;
    }
    
    public void setChooseItemLeftArrows(TrslationBtn chooseItemLeftArrows) {
        this.chooseItemLeftArrows = chooseItemLeftArrows;
    }
}
