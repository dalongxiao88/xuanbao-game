package org.come.Jpanel;

import com.tool.role.RoleData;
import org.come.bean.AuctionLog;
import org.come.bean.AuctionSceneInfo;
import org.apache.commons.lang.StringUtils;
import org.come.until.Util;
import java.awt.Graphics;
import javax.swing.JScrollBar;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.come.mouslisten.AuctionGoodsForGoodsChooseGetMouslisten;
import java.math.BigDecimal;
import org.come.entity.Goodstable;
import org.come.bean.AuctionExchange;
import java.util.Map;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.ScrollUI;
import com.tool.btn.FormsOnOffBtn;
import javax.swing.BorderFactory;
import com.tool.Document.NumberDocument;
import java.awt.Color;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.util.ArrayList;
import com.tool.tcpimg.RichLabel;
import javax.swing.ImageIcon;
import org.come.bean.AuctionGoodsForGoodsBean;
import java.util.List;
import com.tool.btn.AuctionGoodsExchangeBtn;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import com.tool.Document.InputNum;
import javax.swing.JPanel;

public class AuctionGoodsExchangeJpanel extends JPanel implements InputNum
{
    private JScrollPane goodScrollPane1;
    private JScrollPane goodScrollPane2;
    private JTextArea srmc;
    private JTextField offer;
    private JLabel needMoney;
    private AuctionGoodsExchangeBtn ssBtn;
    private AuctionGoodsExchangeBtn btnsurebuy;
    private AuctionGoodsExchangeBtn qbBtn;
    private AuctionGoodsExchangeBtn tzBtn;
    private AuctionGoodsExchangeBtn cwBtn;
    private AuctionGoodsExchangeBtn xqBtn;
    private AuctionGoodsExchangeBtn zhBtn;
    private AuctionGoodsExchangeBtn btBtn;
    private String search;
    private int type;
    private List<AuctionGoodsForGoodsBean> goodgodbean;
    private AuctionGoodForGoodJpanel[] auctionGoodForGoodJpanel;
    private AuctionGoodsForDelGoodsJpanel auctionGoodsForDelGoodsJpanel;
    private JLabel selectIcon;
    private JLabel selectText;
    private JPanel jpa;
    private JPanel jpa1;
    private final JLabel[] needIcons;
    private boolean shoptext;
    private int goodPosition;
    private final JLabel[] needCount;
    private final JLabel[] needCountText;
    public static ImageIcon iconx;
    ImageIcon icon;
    ImageIcon icons;
    ImageIcon iconh;
    public ImageIcon iconx2;
    public static AuctionGoodsForGoodsBean forGoodsBean;
    public RichLabel r1;
    private String[] newStr;
    private String basePrice;
    
    public AuctionGoodsExchangeJpanel() {
        this.r1 = new RichLabel();
        this.newStr = new String[2];
        this.search = "";
        this.type = 0;
        this.goodgodbean = new ArrayList<>();
        this.needIcons = new JLabel[9];
        this.shoptext = false;
        this.needCount = new JLabel[9];
        this.needCountText = new JLabel[9];
        this.iconx2 = new ImageIcon("inkimg/danxin/border_quack.png");
        this.setPreferredSize(new Dimension(740, 527));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        this.initGoodsListControl();
        this.initAucitonLogListControl();
        this.initGoodsList();
    }
    
    public void initJlabel() {
        this.srmc = new JTextArea("");
        Font f = new Font("宋体", 0, 14);
        this.srmc.setFont(f);
        this.srmc.setForeground(Color.white);
        this.srmc.setCaretColor(Color.white);
        this.srmc.setOpaque(false);
        this.srmc.setBounds(126, 67, 130, 21);
        this.add(this.srmc);
        (this.offer = new JTextField("0")).setFont(new Font("宋体", 0, 14));
        this.offer.setForeground(Color.yellow);
        this.offer.setCaretColor(Color.white);
        this.offer.setDocument(new NumberDocument(this.offer, 4, this));
        this.offer.setOpaque(false);
        this.offer.setBorder(BorderFactory.createEmptyBorder());
        this.offer.setBounds(564, 300, 130, 18);
        this.add(this.offer);
        this.upMoney(0L);
        Font ff = new Font("宋体", 0, 12);
        int[] xs = { 458, 518, 578, 458, 518, 578, 458, 518, 578 };
        int[] ys = { 220, 220, 220, 275, 275, 275, 330, 330, 330 };
        for (int ii = 0; ii < 9; ++ii) {
            (this.needCount[ii] = new JLabel()).setForeground(Color.yellow);
            this.needCount[ii].setOpaque(false);
            this.needCount[ii].setFont(ff);
            this.needCount[ii].setBounds(xs[ii], ys[ii], 40, 20);
        }
        for (int ii = 0; ii < 9; ++ii) {
            (this.needCountText[ii] = new JLabel("", 2)).setForeground(Color.white);
            this.needCountText[ii].setOpaque(false);
            this.needCountText[ii].setFont(new Font("宋体", 1, 15));
            this.needCountText[ii].setBounds(xs[ii] - 30, ys[ii] + 1, 40, 20);
        }
        for (JLabel jLabel : this.needCountText) {
            this.add(jLabel);
        }
        for (JLabel jLabel : this.needCountText) {
            this.add(jLabel);
        }
        for (JLabel jLabel : this.needCount) {
            this.add(jLabel);
        }
        for (int i = 0; i < 9; ++i) {
            int row = i % 3;
            int col = i / 3;
            (this.needIcons[i] = new JLabel()).setBounds(423 + row * 59, 220 + col * 57, 49, 49);
        }
        for (int i = 0; i < 9; ++i) {
            int row = i % 3;
            int col = i / 3;
            JLabel need1 = new JLabel();
            ImageIcon image = new ImageIcon("inkimg/danxin/goodse/9_2.png");
            need1.setIcon(image);
            need1.setOpaque(false);
            need1.setBounds(423 + row * 59, 220 + col * 57, 55, 55);
        }
        (this.needMoney = new JLabel()).setFont(f);
        this.needMoney.setForeground(Color.magenta);
        this.needMoney.setOpaque(false);
        this.needMoney.setBounds(480, 405, 128, 18);
        this.add(this.needMoney);
        (this.ssBtn = new AuctionGoodsExchangeBtn("inkImg/button1/B20.png", 1, "查询", 1, UIUtils.COLOR_BLACK, this)).setBounds(270, 63, 85, 24);
        this.add(this.ssBtn);
        (this.btnsurebuy = new AuctionGoodsExchangeBtn("inkImg/button1/B20.png", 1, "竞拍", 2, UIUtils.COLOR_BLACK, this)).setBounds(570, 400, 85, 24);
        this.add(this.btnsurebuy);
        this.initBtn();
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 900);
        offBtn.setBounds(720, 0, 25, 25);
        this.add(offBtn);
    }
    
    public void initAucitonLogListControl() {
        (this.jpa1 = new JPanel()).setPreferredSize(new Dimension(160, 338));
        this.jpa1.setOpaque(false);
        this.goodScrollPane2 = new JScrollPane();
        this.goodScrollPane2.getVerticalScrollBar().setUnitIncrement(10);
        this.goodScrollPane2.setVerticalScrollBarPolicy(22);
        this.goodScrollPane2.getVerticalScrollBar().setUI(new ScrollUI());
        this.goodScrollPane2.getViewport().setOpaque(false);
        this.goodScrollPane2.setOpaque(false);
        this.goodScrollPane2.setBounds(48, 353, 430, 135);
        this.goodScrollPane2.setHorizontalScrollBarPolicy(31);
        this.goodScrollPane2.getViewport().add(this.jpa1);
        this.goodScrollPane2.validate();
        this.add(this.goodScrollPane2);
    }
    
    public void initGoodsListControl() {
        (this.selectIcon = new JLabel()).setBounds(485, 126, 59, 59);
        this.selectIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                AuctionGoodsExchangeJpanel.this.selectIcon.setBorder(null);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                AuctionGoodsExchangeJpanel.this.selectIcon.setBounds(485, 126, 59, 59);
                super.mousePressed(e);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                AuctionGoodsExchangeJpanel.this.selectIcon.setBounds(485, 125, 59, 59);
                super.mouseReleased(e);
            }
        });
        this.add(this.selectIcon);
        this.selectText = new JLabel();
        Font f = new Font("宋体", 0, 14);
        this.selectText.setForeground(Color.yellow);
        this.selectText.setFont(f);
        this.selectText.setBounds(600, 125, 140, 40);
        this.add(this.selectText);
        this.initJlabel();
        (this.auctionGoodsForDelGoodsJpanel = new AuctionGoodsForDelGoodsJpanel(this)).setPreferredSize(new Dimension(180, 600));
        (this.jpa = new JPanel()).setPreferredSize(new Dimension(160, 338));
        this.jpa.setOpaque(false);
        this.goodScrollPane1 = new JScrollPane();
        this.goodScrollPane1.getVerticalScrollBar().setUnitIncrement(10);
        this.goodScrollPane1.setVerticalScrollBarPolicy(22);
        this.goodScrollPane1.getVerticalScrollBar().setUI(new ScrollUI());
        this.goodScrollPane1.getViewport().setOpaque(false);
        this.goodScrollPane1.setOpaque(false);
        this.goodScrollPane1.setBounds(48, 124, 430, 206);
        this.goodScrollPane1.setHorizontalScrollBarPolicy(31);
        this.goodScrollPane1.getViewport().add(this.jpa);
        this.goodScrollPane1.validate();
        this.add(this.goodScrollPane1);
    }
    
    public void initGoodsList() {
        if (this.goodgodbean.size() <= 0) {
            ConcurrentHashMap<Integer, AuctionExchange> all = UserMessUntil.getAllAuctionGoodsExchange().getAllAuctionAuctionExchange();
            for (Map.Entry<Integer, AuctionExchange> e : all.entrySet()) {
                AuctionExchange goodsExchange = (AuctionExchange)e.getValue();
                if (this.search.length() > 0 && !goodsExchange.getGoodssname().contains(this.search)) {
                    continue;
                }
                else if (this.getType() != 0 && this.getType() != goodsExchange.getType()) {
                    continue;
                }
                else {
                    AuctionGoodsForGoodsBean goodsBean = new AuctionGoodsForGoodsBean();
                    goodsBean.setDelte(goodsExchange.getConsume());
                    goodsBean.setGetGoods(goodsExchange.getGid() + "=1");
                    goodsBean.setItemid(goodsExchange.getGid());
                    goodsBean.setType(goodsExchange.getType());
                    goodsBean.setAuctionExchange(goodsExchange);
                    goodsBean.setNameString(goodsExchange.getGoodssname());
                    goodsBean.setGetApplyGoods(new ArrayList() {
                        {
                            Goodstable goodstable = UserMessUntil.getgoodstable(goodsExchange.getGid());
                            goodstable.setInstruction(goodsExchange.getInstruction());
                            if (AuctionGoodsExchangeJpanel.this.type == 0) {
                                goodstable.setValue(goodsExchange.getValues());
                            }
                            this.add(goodstable);
                        }
                    });
                    goodsBean.setDelGoodstables(new ArrayList() {
                        {
                            String[] arr = goodsExchange.getConsume().split("\\|");
                            int i = 0;
                            for (int j = 0; j < arr.length; ++j) {
                                if (!arr[j].startsWith("D")) {
                                    String str = arr[j].substring(2);
                                    BigDecimal id = BigDecimal.valueOf(Long.parseLong(str.split("=")[0]));
                                    Goodstable goodInfo = UserMessUntil.getgoodstable(id);
                                    goodInfo.setInstruction(goodsExchange.getInstructions()[i]);
                                    this.add(goodInfo);
                                    ++i;
                                }
                            }
                        }
                    });
                    this.goodgodbean.add(goodsBean);
                }
            }
        }
        this.jpa.removeAll();
        this.auctionGoodForGoodJpanel = new AuctionGoodForGoodJpanel[this.goodgodbean.size()];
        this.jpa.setPreferredSize(new Dimension(160, Math.max((this.goodgodbean.size() + 1) * 47, 338)));
        for (int i = 0; i < this.goodgodbean.size(); ++i) {
            (this.auctionGoodForGoodJpanel[i] = new AuctionGoodForGoodJpanel((AuctionGoodsForGoodsBean)this.goodgodbean.get(i))).setPreferredSize(new Dimension(420, 42));
            this.auctionGoodForGoodJpanel[i].addMouseListener(new AuctionGoodsForGoodsChooseGetMouslisten(this.auctionGoodForGoodJpanel, i, this.auctionGoodsForDelGoodsJpanel, (AuctionGoodsForGoodsBean)this.goodgodbean.get(i), this));
            this.jpa.add(this.auctionGoodForGoodJpanel[i]);
        }
        JScrollBar jscrollBar = this.goodScrollPane1.getVerticalScrollBar();
        jscrollBar.setValue(jscrollBar.getMinimum());
        this.jpa.updateUI();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("Client/神通天演册/40×40/仙/pmh-sm.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 740, 527, this);
        g.setColor(Color.white);
        g.drawString("我的出价", 510, 312);
        if (AuctionGoodsExchangeJpanel.forGoodsBean != null) {
            if ((int)AuctionGoodsExchangeJpanel.forGoodsBean.getAuctionExchange().getMoneyType() == 1) {
                g.drawString("当前银两", 510, 343);
                Util.drawMoney(g, 565, 344);
            }
            else if ((int)AuctionGoodsExchangeJpanel.forGoodsBean.getAuctionExchange().getMoneyType() == 2) {
                g.drawString("当前仙玉", 510, 343);
                Util.drawMoneyS(g, 565, 344);
            }
            g.setColor(Color.red);
            if (StringUtils.isNotBlank(this.newStr[0]) && StringUtils.isNotBlank(this.newStr[1])) {
                g.setFont(UIUtils.TEXT_FONT);
                g.drawString(this.newStr[0], 545, 170);
                Util.drawPrice(g, new BigDecimal(this.newStr[1]), 610, 170);
            }
        }
    }
    
    public void refreshBidding(AuctionSceneInfo auctionSceneInfo) {
        this.jpa1.removeAll();
        (this.r1 = new RichLabel()).setText("");
        if (auctionSceneInfo == null || auctionSceneInfo.getRole_id() == null) {
            this.newStr[0] = "[底价]:";
            this.newStr[1] = this.needMoney.getText().replace(",", "");
        }
        else {
            this.newStr[0] = "[最新出价]:";
            this.newStr[1] = auctionSceneInfo.getMoney().toString().replace(",", "");
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (auctionSceneInfo != null && auctionSceneInfo.getAuctionLogList() != null) {
            for (int i = auctionSceneInfo.getAuctionLogList().size() - 1; i >= 0; --i) {
                AuctionLog auctionLog = (AuctionLog)auctionSceneInfo.getAuctionLogList().get(i);
                StringBuffer gold;
                if (new BigDecimal(auctionLog.getMoney()).compareTo(new BigDecimal(1000)) >= 0) {
                    String str = auctionLog.getMoney().toString();
                    gold = new StringBuffer(str);
                    for (int index = gold.length() - 3; index > 0; index -= 3) {
                        gold.insert(index, ',');
                    }
                }
                else {
                    gold = new StringBuffer(auctionLog.getMoney());
                }
                Color priceColor = Util.getPriceColor(new BigDecimal(auctionLog.getMoney()));
                String hexColor = "#c" + Integer.toHexString(priceColor.getRed()) + Integer.toHexString(priceColor.getGreen()) + Integer.toHexString(priceColor.getBlue());
                if (stringBuffer.length() != 0) {
                    stringBuffer.append("#r");
                }
                if (stringBuffer.length() == 0) {
                    stringBuffer.append("#R[最新出价]");
                }
                stringBuffer.append("#W玩家#Y" + auctionLog.getRoleName() + "#W于#Y" + auctionLog.getTime() + "#W出价#Y" + hexColor + "  " + gold);
            }
        }
        if (stringBuffer.length() != 0) {
            this.r1.setText(stringBuffer.toString());
            Dimension d = this.r1.computeSize(300);
            this.r1.setSize(d);
            this.r1.setPreferredSize(d);
        }
        int height = this.r1.getHeight();
        this.jpa1.add(this.r1);
        this.jpa1.setPreferredSize(new Dimension(350, Math.max(height, 115)));
        this.jpa1.updateUI();
    }
    
    public void initBtn() {
        if (this.qbBtn != null) {
            this.remove(this.qbBtn);
        }
        (this.qbBtn = new AuctionGoodsExchangeBtn("inkImg/hongmu/Smexchange/S653.png", 1, "", 3, this)).setBounds(64, 27, 95, 28);
        this.add(this.qbBtn);
        if (this.tzBtn != null) {
            this.remove(this.tzBtn);
        }
    }
    
    public JScrollPane getgoodScrollPane1() {
        return this.goodScrollPane1;
    }
    
    public void setgoodScrollPane1(JScrollPane goodScrollPane1) {
        this.goodScrollPane1 = goodScrollPane1;
    }
    
    public AuctionGoodsExchangeBtn getBtnsurebuy() {
        return this.btnsurebuy;
    }
    
    public void setBtnsurebuy(AuctionGoodsExchangeBtn btnsurebuy) {
        this.btnsurebuy = btnsurebuy;
    }
    
    public void setSearch(String search) {
        this.search = search;
    }
    
    public String getSearch() {
        return this.search;
    }
    
    public AuctionGoodsExchangeBtn getBtnssBtn() {
        return this.ssBtn;
    }
    
    public void setBtnss(AuctionGoodsExchangeBtn ssBtn) {
        this.ssBtn = ssBtn;
    }
    
    public AuctionGoodsExchangeBtn getBtnqbBtn() {
        return this.qbBtn;
    }
    
    public void setBtnqb(AuctionGoodsExchangeBtn qbBtn) {
        this.qbBtn = qbBtn;
    }
    
    public AuctionGoodsExchangeBtn getBtntzBtn() {
        return this.tzBtn;
    }
    
    public void setBtntz(AuctionGoodsExchangeBtn tzBtn) {
        this.tzBtn = tzBtn;
    }
    
    public AuctionGoodsExchangeBtn getBtncwBtn() {
        return this.cwBtn;
    }
    
    public void setBtncw(AuctionGoodsExchangeBtn cwBtn) {
        this.cwBtn = cwBtn;
    }
    
    public AuctionGoodsExchangeBtn getBtnxqBtn() {
        return this.xqBtn;
    }
    
    public void setBtnxq(AuctionGoodsExchangeBtn xqBtn) {
        this.xqBtn = xqBtn;
    }
    
    public AuctionGoodsExchangeBtn getBtnzhBtn() {
        return this.zhBtn;
    }
    
    public void setBtnzh(AuctionGoodsExchangeBtn zhBtn) {
        this.zhBtn = zhBtn;
    }
    
    public JScrollPane getGoodScrollPane1() {
        return this.goodScrollPane1;
    }
    
    public void setGoodScrollPane1(JScrollPane goodScrollPane1) {
        this.goodScrollPane1 = goodScrollPane1;
    }
    
    public JTextArea getSrmc() {
        return this.srmc;
    }
    
    public void setSrmc(JTextArea srmc) {
        this.srmc = srmc;
    }
    
    public AuctionGoodsExchangeBtn getSsBtn() {
        return this.ssBtn;
    }
    
    public void setSsBtn(AuctionGoodsExchangeBtn ssBtn) {
        this.ssBtn = ssBtn;
    }
    
    public AuctionGoodsExchangeBtn getQbBtn() {
        return this.qbBtn;
    }
    
    public void setQbBtn(AuctionGoodsExchangeBtn qbBtn) {
        this.qbBtn = qbBtn;
    }
    
    public AuctionGoodsExchangeBtn getTzBtn() {
        return this.tzBtn;
    }
    
    public void setTzBtn(AuctionGoodsExchangeBtn tzBtn) {
        this.tzBtn = tzBtn;
    }
    
    public AuctionGoodsExchangeBtn getCwBtn() {
        return this.cwBtn;
    }
    
    public void setCwBtn(AuctionGoodsExchangeBtn cwBtn) {
        this.cwBtn = cwBtn;
    }
    
    public AuctionGoodsExchangeBtn getXqBtn() {
        return this.xqBtn;
    }
    
    public void setXqBtn(AuctionGoodsExchangeBtn xqBtn) {
        this.xqBtn = xqBtn;
    }
    
    public AuctionGoodsExchangeBtn getZhBtn() {
        return this.zhBtn;
    }
    
    public void setZhBtn(AuctionGoodsExchangeBtn zhBtn) {
        this.zhBtn = zhBtn;
    }
    
    public List getGoodgodbean() {
        return this.goodgodbean;
    }
    
    public void setGoodgodbean(List goodgodbean) {
        this.goodgodbean = goodgodbean;
    }
    
    public AuctionGoodForGoodJpanel[] getAuctionGoodForGoodJpanel() {
        return this.auctionGoodForGoodJpanel;
    }
    
    public void setAuctionGoodForGoodJpanel(AuctionGoodForGoodJpanel[] auctionGoodForGoodJpanel) {
        this.auctionGoodForGoodJpanel = auctionGoodForGoodJpanel;
    }
    
    public AuctionGoodsForDelGoodsJpanel getAuctionGoodsForDelGoodsJpanel() {
        return this.auctionGoodsForDelGoodsJpanel;
    }
    
    public void setAuctionGoodsForDelGoodsJpanel(AuctionGoodsForDelGoodsJpanel auctionGoodsForDelGoodsJpanel) {
        this.auctionGoodsForDelGoodsJpanel = auctionGoodsForDelGoodsJpanel;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.initBtn();
        switch (this.type = type) {
            case 0: {
                this.remove(this.qbBtn);
                (this.qbBtn = new AuctionGoodsExchangeBtn("inkImg/hongmu/Smexchange/S653.png", 1, "", 3, this)).setBounds(56, 30, 95, 28);
                this.add(this.qbBtn);
                break;
            }
            case 1: {
                this.remove(this.tzBtn);
                (this.tzBtn = new AuctionGoodsExchangeBtn("inkimg/danxin/goodse/S656.png", 1, "", 4, this)).setBounds(116, 34, 95, 40);
                this.add(this.tzBtn);
                break;
            }
            case 2: {
                this.remove(this.cwBtn);
                (this.cwBtn = new AuctionGoodsExchangeBtn("inkimg/danxin/goodse/S662.png", 1, "", 5, this)).setBounds(211, 34, 95, 40);
                this.add(this.cwBtn);
                break;
            }
            case 3: {
                this.remove(this.xqBtn);
                (this.xqBtn = new AuctionGoodsExchangeBtn("inkimg/danxin/goodse/S660.png", 1, "", 6, this)).setBounds(306, 34, 95, 40);
                this.add(this.xqBtn);
                break;
            }
            case 4: {
                this.remove(this.zhBtn);
                (this.zhBtn = new AuctionGoodsExchangeBtn("inkimg/danxin/goodse/S658.png", 1, "", 7, this)).setBounds(496, 34, 95, 40);
                this.add(this.zhBtn);
                break;
            }
            case 5: {
                this.remove(this.btBtn);
                (this.btBtn = new AuctionGoodsExchangeBtn("inkimg/danxin/goodse/S664.png", 1, "", 8, this)).setBounds(401, 34, 95, 40);
                this.add(this.btBtn);
                break;
            }
        }
    }
    
    public JLabel getSelectIcon() {
        return this.selectIcon;
    }
    
    public JLabel getSelectText() {
        return this.selectText;
    }
    
    public JLabel getNeedMoney() {
        return this.needMoney;
    }
    
    public JLabel[] getNeedIcons() {
        return this.needIcons;
    }
    
    public JLabel[] getNeedCount() {
        return this.needCount;
    }
    
    public JLabel[] getNeedCountText() {
        return this.needCountText;
    }
    
    public JTextField getOffer() {
        return this.offer;
    }
    
    public void setOffer(JTextField offer) {
        this.offer = offer;
    }
    
    public void upMoney(long sum) {
        System.out.println(sum);
        ((NumberDocument)this.offer.getDocument()).replace(sum + "");
    }
    
    public String getBasePrice() {
        return this.basePrice;
    }
    
    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }
    
    @Override
    public void upNum() {
        BigDecimal j1 = ((NumberDocument)this.offer.getDocument()).getNum();
        if (AuctionGoodsExchangeJpanel.forGoodsBean != null) {
            if ((int)AuctionGoodsExchangeJpanel.forGoodsBean.getAuctionExchange().getMoneyType() == 1) {
                if (j1.longValue() > RoleData.getRoleData().getLoginResult().getGold().longValue()) {
                    j1 = new BigDecimal(RoleData.getRoleData().getLoginResult().getGold().toString());
                }
            }
            else if ((int)AuctionGoodsExchangeJpanel.forGoodsBean.getAuctionExchange().getMoneyType() == 2 && j1.longValue() > RoleData.getRoleData().getLoginResult().getCodecard().longValue()) {
                j1 = new BigDecimal(RoleData.getRoleData().getLoginResult().getCodecard().toString());
            }
        }
        this.upMoney(j1.longValue());
    }
    
    @Override
    public boolean isChange() {
        return false;
    }
    
    static {
        AuctionGoodsExchangeJpanel.iconx = new ImageIcon("inkimg/danxin/ss231.png");
    }
}
