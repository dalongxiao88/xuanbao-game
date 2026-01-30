package org.come.Jpanel;

import org.come.Frame.TradeJframe;
import org.come.until.UserMessUntil;
import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import com.tool.Stall.StallBean;
import org.come.until.EquipTool;
import org.come.until.GoodsListFromServerUntil;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.tool.Document.NumberDocument;
import javax.swing.BorderFactory;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.come.Frame.BoothBoxJframe;
import javax.swing.ImageIcon;
import com.tool.btn.BoothBoxBtn;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.tool.Stall.Commodity;
import com.tool.Stall.Stall;
import javax.swing.JPanel;

public class BoothBoxJpanel extends JPanel
{
    private Stall Stall;
    private String tw;
    private Commodity commodity;
    private JLabel labpettrade;
    private JTextField textUnitprice;
    private JTextField textNumber;
    private JTextField TWname;
    public JLabel[] GoodsListLabel;
    public String[] Usetime;
    public JLabel[] PetsListLabel;
    private BoothBoxBtn btnTheshelves;
    private BoothBoxBtn btnshelves;
    private BoothBoxBtn btnStall;
    private ImageIcon icon;
    
    public Stall getStall2() {
        return this.Stall;
    }
    
    public Stall getStall() {
        if (this.Stall == null) {
            this.Stall = new Stall();
        }
        return this.Stall;
    }
    
    public BoothBoxJpanel(BoothBoxJframe boothBoxJframe) throws Exception {
        this.GoodsListLabel = new JLabel[24];
        this.Usetime = new String[24];
        this.PetsListLabel = new JLabel[5];
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(619, 430));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 16);
            offBtn.setBounds(582, 10, 25, 25);
            this.add(offBtn);
            (this.labpettrade = new JLabel("", 0)).setBounds(478, 83, 66, 66);
            this.add(this.labpettrade);
            (this.textUnitprice = new JTextField()).setBounds(478, 188, 110, 16);
            this.textUnitprice.setText("0");
            this.textUnitprice.setOpaque(false);
            this.textUnitprice.setForeground(Color.WHITE);
            this.textUnitprice.setCaretColor(Color.WHITE);
            this.textUnitprice.setBorder(BorderFactory.createEmptyBorder());
            this.textUnitprice.setDocument(new NumberDocument(this.textUnitprice));
            this.add(this.textUnitprice);
            (this.textNumber = new JTextField()).setBounds(478, 150, 65, 16);
            this.textNumber.setText("0");
            this.textNumber.setOpaque(false);
            this.textNumber.setCaretColor(Color.WHITE);
            this.textNumber.setForeground(Color.WHITE);
            this.textNumber.setBorder(BorderFactory.createEmptyBorder());
            this.textNumber.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if (keyChar < 48 || keyChar > 57 || BoothBoxJpanel.this.commodity == null) {
                        e.consume();
                        return;
                    }
                    if (BoothBoxJpanel.this.commodity.getGood() != null) {
                        String key = BoothBoxJpanel.this.textNumber.getText();
                        String value = BoothBoxJpanel.this.getLimit(key, (int)BoothBoxJpanel.this.commodity.getGood().getUsetime());
                        if (value.indexOf((int)e.getKeyChar()) < 0) {
                            e.consume();
                        }
                    }
                    else {
                        e.consume();
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    if (BoothBoxJpanel.this.commodity != null) {
                        Goodstable good = BoothBoxJpanel.this.commodity.getGood();
                        RoleSummoning pet = BoothBoxJpanel.this.commodity.getPet();
                        if (good != null) {
                            String str = BoothBoxJpanel.this.textNumber.getText();
                            if (str.length() > 0) {
                                if (str.charAt(0) == '0') {
                                    BoothBoxJpanel.this.textNumber.setText(Integer.valueOf(str).toString());
                                }
                                if (Integer.parseInt(str) >= (int)good.getUsetime()) {
                                    BoothBoxJpanel.this.textNumber.setText(good.getUsetime().toString());
                                }
                            }
                        }
                        else if (pet != null) {
                            BoothBoxJpanel.this.textNumber.setText("1");
                        }
                        else {
                            BoothBoxJpanel.this.textNumber.setText("0");
                        }
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(this.textNumber);
            int Flag = 0;
            int count = 1;
            for (int i = 0; i < 24; ++i) {
                Flag = i % 6;
                count = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).setBounds(73 + Flag * 51, 92 + 51 * count, 48, 48);
                this.add(this.GoodsListLabel[i]);
            }
            for (int j = 0; j < 5; ++j) {
                (this.PetsListLabel[j] = new JLabel()).setBounds(73 + j * 63, 352, 51, 51);
                this.add(this.PetsListLabel[j]);
            }
            (this.TWname = new JTextField()).setBorder(BorderFactory.createEmptyBorder());
            this.TWname.setForeground(Color.white);
            this.TWname.setCaretColor(Color.white);
            this.TWname.setFont(new Font("宋体", 0, 15));
            this.TWname.setBounds(455, 27, 105, 16);
            this.TWname.setText("");
            this.TWname.setOpaque(false);
            this.TWname.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    if (BoothBoxJpanel.this.TWname.getText().length() > 6) {
                        BoothBoxJpanel.this.TWname.setText(BoothBoxJpanel.this.TWname.getText().substring(0, 6));
                    }
                    BoothBoxJpanel.this.tw = BoothBoxJpanel.this.TWname.getText();
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(this.TWname);
            (this.btnTheshelves = new BoothBoxBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "上架", this)).setBounds(445, 220, 59, 24);
            this.add(this.btnTheshelves);
            (this.btnshelves = new BoothBoxBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "下架", this)).setBounds(525, 220, 59, 24);
            this.add(this.btnshelves);
            (this.btnStall = new BoothBoxBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "摆摊", this)).setBounds(485, 360, 59, 24);
            this.add(this.btnStall);
        }
        else {
            this.setPreferredSize(new Dimension(378, 419));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 16);
            offBtn.setBounds(353, 0, 25, 25);
            this.add(offBtn);
            (this.labpettrade = new JLabel("", 0)).setBounds(32, 320, 66, 66);
            this.add(this.labpettrade);
            (this.TWname = new JTextField()).setBorder(BorderFactory.createEmptyBorder());
            this.TWname.setForeground(Color.white);
            this.TWname.setCaretColor(Color.white);
            this.TWname.setFont(new Font("宋体", 0, 15));
            this.TWname.setBounds(168, 314, 114, 16);
            this.TWname.setText("");
            this.TWname.setOpaque(false);
            this.TWname.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    if (BoothBoxJpanel.this.TWname.getText().length() > 6) {
                        BoothBoxJpanel.this.TWname.setText(BoothBoxJpanel.this.TWname.getText().substring(0, 6));
                    }
                    BoothBoxJpanel.this.tw = BoothBoxJpanel.this.TWname.getText();
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(this.TWname);
            (this.textUnitprice = new JTextField()).setBounds(170, 339, 114, 16);
            this.textUnitprice.setText("0");
            this.textUnitprice.setOpaque(false);
            this.textUnitprice.setForeground(Color.WHITE);
            this.textUnitprice.setCaretColor(Color.WHITE);
            this.textUnitprice.setBorder(BorderFactory.createEmptyBorder());
            this.textUnitprice.setDocument(new NumberDocument(this.textUnitprice));
            this.add(this.textUnitprice);
            (this.textNumber = new JTextField()).setBounds(170, 369, 114, 16);
            this.textNumber.setText("0");
            this.textNumber.setOpaque(false);
            this.textNumber.setCaretColor(Color.WHITE);
            this.textNumber.setForeground(Color.WHITE);
            this.textNumber.setBorder(BorderFactory.createEmptyBorder());
            this.textNumber.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if (keyChar < 48 || keyChar > 57 || BoothBoxJpanel.this.commodity == null) {
                        e.consume();
                        return;
                    }
                    if (BoothBoxJpanel.this.commodity.getGood() != null) {
                        String key = BoothBoxJpanel.this.textNumber.getText();
                        String value = BoothBoxJpanel.this.getLimit(key, (int)BoothBoxJpanel.this.commodity.getGood().getUsetime());
                        if (value.indexOf((int)e.getKeyChar()) < 0) {
                            e.consume();
                        }
                    }
                    else {
                        e.consume();
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    if (BoothBoxJpanel.this.commodity != null) {
                        Goodstable good = BoothBoxJpanel.this.commodity.getGood();
                        RoleSummoning pet = BoothBoxJpanel.this.commodity.getPet();
                        if (good != null) {
                            String str = BoothBoxJpanel.this.textNumber.getText();
                            if (str.length() > 0) {
                                if (str.charAt(0) == '0') {
                                    BoothBoxJpanel.this.textNumber.setText(Integer.valueOf(str).toString());
                                }
                                if (Integer.parseInt(str) >= (int)good.getUsetime()) {
                                    BoothBoxJpanel.this.textNumber.setText(good.getUsetime().toString());
                                }
                            }
                        }
                        else if (pet != null) {
                            BoothBoxJpanel.this.textNumber.setText("1");
                        }
                        else {
                            BoothBoxJpanel.this.textNumber.setText("0");
                        }
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(this.textNumber);
            int Flag = 0;
            int count = 1;
            for (int i = 0; i < 24; ++i) {
                Flag = i % 6;
                count = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).setBounds(25 + Flag * 55, 34 + 51 * count, 48, 48);
                this.add(this.GoodsListLabel[i]);
            }
            for (int j = 0; j < 5; ++j) {
                (this.PetsListLabel[j] = new JLabel()).setBounds(25 + j * 69, 248, 51, 51);
                this.add(this.PetsListLabel[j]);
            }
            (this.btnTheshelves = new BoothBoxBtn("inkImg/hongmu/aaa8.png", 1, UIUtils.COLOR_BTNPUTONG, "上架", this)).setBounds(290, 308, 60, 26);
            this.add(this.btnTheshelves);
            (this.btnshelves = new BoothBoxBtn("inkImg/hongmu/aaa8.png", 1, UIUtils.COLOR_BTNPUTONG, "下架", this)).setBounds(290, 338, 60, 26);
            this.add(this.btnshelves);
            (this.btnStall = new BoothBoxBtn("inkImg/hongmu/aaa8.png", 1, UIUtils.COLOR_BTNPUTONG, "摆摊", this)).setBounds(290, 368, 60, 26);
            this.add(this.btnStall);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B250.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 619, 430, this);
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_MSG);
            for (int i = 0; i < this.Usetime.length; ++i) {
                int row = i % 6;
                int col = i / 6;
                if (this.Usetime[i] != null) {
                    g.drawString(this.Usetime[i], 73 + row * 51, 105 + col * 51);
                }
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/54_png.xy2uiimg.boothbox.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 378, 419, this);
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_MSG);
            for (int i = 0; i < this.Usetime.length; ++i) {
                int row = i % 6;
                int col = i / 6;
                if (this.Usetime[i] != null) {
                    g.drawString(this.Usetime[i], 25 + row * 55, 51 + col * 50);
                }
            }
        }
    }
    
    public void XZBuy(Goodstable good, RoleSummoning pet) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (good != null) {
                (this.commodity = new Commodity()).setGood(good);
                this.commodity.setMoney(1L);
                this.commodity.setCurrency("金钱");
                this.textUnitprice.setText("1");
                this.textNumber.setText("1");
                ImageIcon img = GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49);
                this.labpettrade.setIcon(img);
            }
            else if (pet != null) {
                (this.commodity = new Commodity()).setPet(pet);
                this.commodity.setMoney(1L);
                this.commodity.setCurrency("金钱");
                this.textUnitprice.setText("1");
                this.textNumber.setText("1");
                ImageIcon img = new ImageIcon("img/xy2uiimg/101_png.xy2uiimg.pet_def.png");
                img.setImage(img.getImage().getScaledInstance(55, 55, 10));
                this.labpettrade.setIcon(img);
            }
        }
        else if (good != null) {
            (this.commodity = new Commodity()).setGood(good);
            this.commodity.setMoney(1L);
            this.commodity.setCurrency("金钱");
            this.textUnitprice.setText("1");
            this.textNumber.setText("1");
            ImageIcon img = GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49);
            this.labpettrade.setIcon(img);
        }
        else if (pet != null) {
            (this.commodity = new Commodity()).setPet(pet);
            this.commodity.setMoney(1L);
            this.commodity.setCurrency("金钱");
            this.textUnitprice.setText("1");
            this.textNumber.setText("1");
            ImageIcon img = new ImageIcon("img/xy2uiimg/101_png.xy2uiimg.pet_def.png");
            img.setImage(img.getImage().getScaledInstance(55, 55, 10));
            this.labpettrade.setIcon(img);
        }
    }
    
    public void init(Stall stall2) {
        this.Stall = stall2;
        Commodity[] goodstables = this.Stall.getGoodstables();
        for (int i = 0; i < goodstables.length; ++i) {
            Commodity com = goodstables[i];
            if (com == null) {
                this.GoodsListLabel[i].setIcon(null);
                this.Usetime[i] = null;
            }
            else {
                ImageIcon img = GoodsListFromServerUntil.imgpathAdaptive(com.getGood().getSkin(), 49, 49);
                this.GoodsListLabel[i].setIcon(img);
                if (!EquipTool.isEquip(com.getGood().getType())) {
                    this.Usetime[i] = com.getGood().getUsetime() + "";
                }
            }
        }
        Commodity[] pets = this.Stall.getPets();
        for (int j = 0; j < pets.length; ++j) {
            Commodity com2 = pets[j];
            if (com2 == null) {
                this.PetsListLabel[j].setIcon(null);
            }
            else {
                ImageIcon img2 = new ImageIcon("img/xy2uiimg/101_png.xy2uiimg.pet_def.png");
                img2.setImage(img2.getImage().getScaledInstance(49, 49, 10));
                this.PetsListLabel[j].setIcon(img2);
            }
        }
    }
    
    public void CId(StallBean bean) {
        if (this.Stall == null) {
            this.Stall = new Stall();
        }
        this.Stall.setId(bean.getId());
        this.Stall.setState(bean.getState());
        if (bean.getState() == StallBean.NO) {
            this.Stall.setId(0);
            for (int i = 0; i < 24; ++i) {
                this.Stall.getGoodstables()[i] = null;
                this.GoodsListLabel[i].setIcon(null);
                this.Usetime[i] = null;
            }
            for (int j = 0; j < 5; ++j) {
                this.Stall.getPets()[j] = null;
                this.PetsListLabel[j].setIcon(null);
            }
            this.commodity = null;
            this.labpettrade.setIcon(null);
            this.textUnitprice.setText("0");
            this.textNumber.setText("0");
            FormsManagement.HideForm(15);
            FormsManagement.HideForm(16);
        }
    }
    
    public void shuaxing(Stall stall) {
        Commodity[] goodstables = stall.getGoodstables();
        for (int i = 0; i < goodstables.length; ++i) {
            Commodity com = goodstables[i];
            if (com == null) {
                this.getStall().getGoodstables()[i] = null;
                this.GoodsListLabel[i].setIcon(null);
                this.Usetime[i] = null;
            }
            else {
                this.getStall().getGoodstables()[i].setGood(com.getGood());
                if (!EquipTool.isEquip(com.getGood().getType())) {
                    this.Usetime[i] = com.getGood().getUsetime() + "";
                }
            }
        }
        Commodity[] pets = stall.getPets();
        for (int j = 0; j < pets.length; ++j) {
            Commodity com2 = pets[j];
            if (com2 == null) {
                this.getStall().getPets()[j] = null;
                this.PetsListLabel[j].setIcon(null);
            }
            else {
                this.getStall().getPets()[j].setPet(com2.getPet());
            }
        }
    }
    
    public void SJ() {
        if (this.commodity == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选中的商品");
            return;
        }
        Goodstable good = this.commodity.getGood();
        RoleSummoning pet = this.commodity.getPet();
        int sum = Integer.parseInt(this.textNumber.getText());
        long money = Long.parseLong(this.textUnitprice.getText().replaceAll(",", ""));
        if (sum <= 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("最少也的卖一个吧");
            return;
        }
        if (money <= 0L) {
            ZhuFrame.getZhuJpanel().addPrompt2("金额需要大于0");
            return;
        }
        this.commodity.setMoney(money);
        if (good != null) {
            if (GoodsListFromServerUntil.isExist(good)) {
                return;
            }
            good = (Goodstable)good.clone();
            this.commodity.setGood(good);
            if (sum > (int)good.getUsetime()) {
                ZhuFrame.getZhuJpanel().addPrompt2("数量不足");
                return;
            }
            good.setUsetime(Integer.valueOf(sum));
            Commodity[] goods = this.getStall().getGoodstables();
            if (EquipTool.isEquip(good.getType())) {
                for (int i = 0; i < goods.length; ++i) {
                    Commodity com = goods[i];
                    if (com == null) {
                        this.commodity.setGood(good);
                        goods[i] = this.commodity;
                        this.GoodsListLabel[i].setIcon(this.labpettrade.getIcon());
                        GoodsListFromServerUntil.stall1(good);
                        this.Usetime[i] = null;
                        this.XZBuy(null);
                        return;
                    }
                }
                ZhuFrame.getZhuJpanel().addPrompt2("最多上架24个物品");
            }
            else {
                int p = -1;
                for (int j = 0; j < goods.length; ++j) {
                    Commodity com2 = goods[j];
                    if (com2 != null) {
                        Goodstable good2 = com2.getGood();
                        if (good2.getGoodsid().compareTo(good.getGoodsid()) == 0 && com2.getMoney() == this.commodity.getMoney()) {
                            good2.setUsetime(Integer.valueOf((int)good2.getUsetime() + (int)good.getUsetime()));
                            GoodsListFromServerUntil.stall1(good);
                            this.Usetime[j] = good2.getUsetime() + "";
                            this.XZBuy(null);
                            return;
                        }
                    }
                    else if (p == -1) {
                        p = j;
                    }
                }
                if (p == -1) {
                    ZhuFrame.getZhuJpanel().addPrompt2("最多上架24个物品");
                }
                else {
                    this.commodity.setGood(good);
                    goods[p] = this.commodity;
                    this.GoodsListLabel[p].setIcon(this.labpettrade.getIcon());
                    GoodsListFromServerUntil.stall1(good);
                    this.Usetime[p] = good.getUsetime() + "";
                    this.XZBuy(null);
                    return;
                }
            }
        }
        else if (pet != null) {
            Commodity[] pets = this.getStall().getPets();
            int i = 0;
            while (i < pets.length) {
                Commodity com = pets[i];
                if (com == null) {
                    int p2 = -1;
                    int k = UserMessUntil.getPetListTable().size() - 1;
                    while (k >= 0) {
                        if (((RoleSummoning)UserMessUntil.getPetListTable().get(k)).getSid().compareTo(pet.getSid()) == 0) {
                            UserMessUntil.getPetListTable().remove(k);
                            p2 = k;
                            break;
                        }
                        else {
                            --k;
                        }
                    }
                    if (p2 == -1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("宝宝去哪了");
                        return;
                    }
                    TradeJframe.getTradejframe().getTradejpanel().getModelname().remove(p2);
                    this.commodity.setPet(pet);
                    pets[i] = this.commodity;
                    this.PetsListLabel[i].setIcon(this.labpettrade.getIcon());
                    this.XZBuy(null);
                    return;
                }
                else {
                    ++i;
                }
            }
            ZhuFrame.getZhuJpanel().addPrompt2("最多上架5只召唤兽");
        }
    }
    
    public void XZBuy(Commodity commodity) {
        if (commodity == null) {
            this.commodity = null;
            this.labpettrade.setIcon(null);
            this.textUnitprice.setText("0");
            this.textNumber.setText("0");
        }
        else {
            this.commodity = commodity;
            ((NumberDocument)this.textUnitprice.getDocument()).replace(commodity.getMoney() + "");
            Goodstable good = commodity.getGood();
            RoleSummoning pet = commodity.getPet();
            if (good != null) {
                ImageIcon img = GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49);
                this.labpettrade.setIcon(img);
                this.textNumber.setText(good.getUsetime() + "");
            }
            else if (pet != null) {
                ImageIcon img = new ImageIcon("img/xy2uiimg/101_png.xy2uiimg.pet_def.png");
                img.setImage(img.getImage().getScaledInstance(49, 49, 10));
                this.labpettrade.setIcon(img);
                this.textNumber.setText("1");
            }
        }
    }
    
    public void QXJ() {
        if (this.getStall().getId() > 0) {
            return;
        }
        for (int i = 0; i < this.getStall().getGoodstables().length; ++i) {
            if (this.getStall().getGoodstables()[i] != null) {
                this.XZBuy(this.getStall().getGoodstables()[i]);
                this.btnshelves.nochoose(null);
            }
        }
        for (int i = 0; i < this.getStall().getPets().length; ++i) {
            if (this.getStall().getPets()[i] != null) {
                this.XZBuy(this.getStall().getPets()[i]);
                this.btnshelves.nochoose(null);
            }
        }
    }
    
    public JLabel getLabpettrade() {
        return this.labpettrade;
    }
    
    public void setLabpettrade(JLabel labpettrade) {
        this.labpettrade = labpettrade;
    }
    
    public JTextField getTextNumber() {
        return this.textNumber;
    }
    
    public void setTextNumber(JTextField textNumber) {
        this.textNumber = textNumber;
    }
    
    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }
    
    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
    }
    
    public JLabel[] getPetsListLabel() {
        return this.PetsListLabel;
    }
    
    public void setPetsListLabel(JLabel[] petsListLabel) {
        this.PetsListLabel = petsListLabel;
    }
    
    private String getLimit(String key, int num) {
        String value = String.valueOf(num);
        int[] a = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int b = num;
        int len = key.length();
        int length = value.length();
        int i = 0;
        while (b > 0) {
            a[length - 1 - i] = b % 10;
            ++i;
            b /= 10;
        }
        int in;
        if (key.equals("")) {
            in = 0;
        }
        else {
            in = (int)Integer.valueOf(key) * 10;
        }
        if (len < length - 1) {
            if (len == 0) {
                value = string(1, 9);
            }
            else {
                value = string(0, 9);
            }
        }
        else if (len == length - 1) {
            if (in <= num) {
                if (in == 0) {
                    value = string(1, a[len]);
                }
                else {
                    value = string(0, a[len]);
                }
            }
            else {
                value = "";
            }
        }
        else {
            value = "";
        }
        return value;
    }
    
    private static String string(int a, int b) {
        String value = "";
        for (int i = a; i <= b; ++i) {
            value += String.valueOf(i);
        }
        return value;
    }
    
    public Commodity getCommodity() {
        return this.commodity;
    }
    
    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
    
    public BoothBoxBtn getBtnTheshelves() {
        return this.btnTheshelves;
    }
    
    public void setBtnTheshelves(BoothBoxBtn btnTheshelves) {
        this.btnTheshelves = btnTheshelves;
    }
    
    public BoothBoxBtn getBtnshelves() {
        return this.btnshelves;
    }
    
    public void setBtnshelves(BoothBoxBtn btnshelves) {
        this.btnshelves = btnshelves;
    }
    
    public BoothBoxBtn getBtnStall() {
        return this.btnStall;
    }
    
    public void setBtnStall(BoothBoxBtn btnStall) {
        this.btnStall = btnStall;
    }
    
    public String getTw() {
        return this.tw;
    }
    
    public void setTw(String tw) {
        this.tw = tw;
    }
}
