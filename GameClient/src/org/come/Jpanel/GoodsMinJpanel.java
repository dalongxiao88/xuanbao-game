package org.come.Jpanel;

import com.tool.tcp.SpriteFactory;
import java.util.Date;
import java.text.ParseException;
import com.tool.time.TimerUtil;
import java.awt.Color;
import org.come.Frame.TaobaoCourtMainJframe;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import org.come.model.Eshop;
import javax.swing.ImageIcon;
import org.come.until.CutButtonImage;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import java.awt.Dimension;
import org.come.model.Shop;
import java.text.SimpleDateFormat;
import com.tool.tcp.Sprite;
import org.come.mouslisten.ShopingImgBorderMosulisten;
import com.tool.btn.ShopBuyBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GoodsMinJpanel extends JPanel
{
    private int type;
    private JLabel buyCount;
    private JLabel goodsImg;
    private JLabel labBorder;
    private ShopBuyBtn buyBtn;
    private String name;
    private String limitTime;
    private String value;
    private ShopingImgBorderMosulisten borderMosulisten;
    static Sprite tcp;
    private static final SimpleDateFormat sdf;
    
    public GoodsMinJpanel(Shop shop) {
        this.type = 0;
        this.type = 1;
        this.borderMosulisten = new ShopingImgBorderMosulisten(shop);
        this.name = shop.getShopname();
        if (shop.getShoptype() == 0) {
            this.value = shop.getShopprice() + "金钱";
        }
        else if (shop.getShoptype() == 1) {
            this.value = shop.getShopprice() + "仙玉";
        }
        else if (shop.getShoptype() == 2) {
            this.value = shop.getShopprice() + "积分";
        }
        else {
            this.value = shop.getShopprice() + "转区币";
        }
        this.setPreferredSize(new Dimension(114, 155));
        this.setLayout(null);
        this.setOpaque(false);
        (this.buyBtn = new ShopBuyBtn("inkImg/button1/B30.png", 1, "购买", 2, shop)).setBounds(7, 85, 34, 17);
        (this.labBorder = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GoodsMinJpanel.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                GoodsMinJpanel.tcp.draw(g, 0, 0);
            }
        }).setBounds(0, 0, 52, 52);
        this.labBorder.setOpaque(false);
        this.labBorder.setIcon(CutButtonImage.getImage("inkImg/tupiankuang/S145-2.png", -1, -1));
        (this.goodsImg = new JLabel()).setBounds(2, 2, 48, 48);
        ImageIcon image = CutButtonImage.getImage("img/item/" + shop.getShopskin() + ".png", 48, 48);
        this.goodsImg.setIcon(image);
        this.goodsImg.addMouseListener(this.borderMosulisten);
        this.limitTime = shop.getLimitTime();
        this.add(this.buyBtn);
        this.add(this.goodsImg);
        this.add(this.labBorder);
    }
    
    public GoodsMinJpanel(Eshop eshop) {
        this.type = 0;
        this.borderMosulisten = new ShopingImgBorderMosulisten(eshop);
        this.name = eshop.getEshopname();
        this.value = eshop.getEshopprice() + "仙玉";
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(114, 155));
            this.setLayout(null);
            this.setOpaque(false);
            this.buyBtn = new ShopBuyBtn("inkImg/button/2.png", 1, "购买", 1, eshop);
            ImageIcon image = CutButtonImage.getImage("img/item/" + eshop.getEshopskin() + ".png", 85, 85);
            this.buyBtn.setBounds(76, 138, 34, 17);
            (this.labBorder = new JLabel()).setBounds(0, 0, 120, 120);
            this.labBorder.setOpaque(false);
            this.labBorder.setIcon(CutButtonImage.getImage("inkImg/background/3.png", -1, -1));
            (this.goodsImg = new JLabel()).setBounds(15, 15, 85, 85);
            this.goodsImg.setIcon(image);
            this.goodsImg.addMouseListener(this.borderMosulisten);
            this.add(this.buyBtn);
            this.add(this.goodsImg);
            this.add(this.labBorder);
        }
        else {
            this.setPreferredSize(new Dimension(732, 480));
            this.setLayout(null);
            this.setOpaque(false);
            this.setBackground(UIUtils.Color_BACK);
            this.buyBtn = new ShopBuyBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "购买", 1, eshop);
            ImageIcon image = CutButtonImage.getImage("img/item/" + eshop.getEshopskin() + ".png", 85, 85);
            this.buyBtn.setBounds(76, 138, 34, 17);
            (this.labBorder = new JLabel()).setBounds(0, 0, 120, 120);
            this.labBorder.setOpaque(false);
            this.labBorder.setIcon(CutButtonImage.getImage("img/xy2uiimg/物品框_120×120.png", -1, -1));
            (this.goodsImg = new JLabel()).setBounds(15, 15, 85, 85);
            this.goodsImg.setIcon(image);
            this.goodsImg.addMouseListener(this.borderMosulisten);
            this.add(this.buyBtn);
            this.add(this.goodsImg);
            this.add(this.labBorder);
        }
    }
    
    public void clearView() {
        this.buyBtn.setBtn(0);
        this.buyBtn.setIcon(null);
        this.buyBtn.setText("");
        this.buyBtn.setEshop(null);
        this.goodsImg.setIcon(null);
        this.borderMosulisten.setEshop(null);
        this.labBorder.setIcon(null);
        this.name = "";
        this.value = "";
    }
    
    public void addEshopMessage(Eshop eshop) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (eshop == null) {
                return;
            }
            this.buyBtn.setEshop(eshop);
            this.buyBtn.setBtn(1);
            this.buyBtn.setText("购买");
            try {
                this.buyBtn.setIcons(CutButtonImage.cuts("inkImg/button/2.png"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.borderMosulisten.setEshop(eshop);
            if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 11) {
                this.value = eshop.getEshopprice() + "积分";
            }
            else if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 15) {
                this.value = eshop.getEshopprice() + "转区币";
            }
            else {
                this.value = eshop.getEshopprice() + "仙玉";
            }
            this.name = eshop.getEshopname();
            this.labBorder.setIcon(CutButtonImage.getImage("inkImg/background/3.png", -1, -1));
            this.goodsImg.setIcon(CutButtonImage.getImage("img/item/" + eshop.getEshopskin() + ".png", 85, 85));
        }
        else {
            if (eshop == null) {
                return;
            }
            this.buyBtn.setEshop(eshop);
            this.buyBtn.setBtn(1);
            this.buyBtn.setText("购买");
            try {
                this.buyBtn.setIcons(CutButtonImage.cuts("inkImg/hongmu/21_png.button.tab_nex.png"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.borderMosulisten.setEshop(eshop);
            if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 11) {
                this.value = eshop.getEshopprice() + "积分";
            }
            else if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 15) {
                this.value = eshop.getEshopprice() + "转区币";
            }
            else {
                this.value = eshop.getEshopprice() + "仙玉";
            }
            this.name = eshop.getEshopname();
            this.labBorder.setIcon(CutButtonImage.getImage("img/xy2uiimg/物品框_120×120.png", -1, -1));
            this.goodsImg.setIcon(CutButtonImage.getImage("img/item/" + eshop.getEshopskin() + ".png", 85, 85));
        }
    }
    
    public void addEshopMessage(Shop shop) {
        if (shop == null) {
            return;
        }
        this.buyBtn.setShop(shop);
        this.buyBtn.setBtn(1);
        this.buyBtn.setText("购买");
        try {
            this.buyBtn.setIcons(CutButtonImage.cuts("inkImg/button/2.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.borderMosulisten.setShop(shop);
        if (shop.getShoptype() == 0) {
            this.value = shop.getShopprice() + "金钱";
        }
        else if (shop.getShoptype() == 1) {
            this.value = shop.getShopprice() + "仙玉";
        }
        else if (shop.getShoptype() == 2) {
            this.value = shop.getShopprice() + "积分";
        }
        else {
            this.value = shop.getShopprice() + "转区币";
        }
        this.name = shop.getShopname();
        this.limitTime = shop.getLimitTime();
        this.labBorder.setIcon(CutButtonImage.getImage("inkImg/tupiankuang/S145-2.png", 50, 50));
        this.goodsImg.setIcon(CutButtonImage.getImage("img/item/" + shop.getShopskin() + ".png", 48, 48));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.type == 0) {
            if (MyIsif.getStyle().equals("水墨")) {
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(Color.black);
            }
            else {
                g.setFont(UIUtils.TEXT_FONT);
                g.setColor(Color.white);
            }
            g.drawString(this.name, 0, 134);
            g.setColor(Color.red);
            g.drawString(this.value, 0, 149);
        }
        else {
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(Color.black);
            g.drawString(this.value, 0, 64);
            g.setColor(Color.red);
            String msg = "剩余";
            try {
                Date parse = GoodsMinJpanel.sdf.parse(this.limitTime);
                int tian = TimerUtil.residueDay(parse.getTime());
                if (tian > 0) {
                    msg = msg + tian + "天";
                }
                else {
                    int xiaos = TimerUtil.xiaoshi(parse.getTime());
                    if (xiaos > 0) {
                        msg = msg + tian + "小时";
                    }
                    else {
                        int timei = TimerUtil.fenzhong(parse.getTime());
                        msg = msg + timei + "分钟";
                    }
                }
                g.drawString(msg, 0, 79);
            }
            catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public ShopBuyBtn getBuyBtn() {
        return this.buyBtn;
    }
    
    public void setBuyBtn(ShopBuyBtn buyBtn) {
        this.buyBtn = buyBtn;
    }
    
    public JLabel getBuyCount() {
        return this.buyCount;
    }
    
    public void setBuyCount(JLabel buyCount) {
        this.buyCount = buyCount;
    }
    
    public JLabel getGoodsImg() {
        return this.goodsImg;
    }
    
    public void setGoodsImg(JLabel goodsImg) {
        this.goodsImg = goodsImg;
    }
    
    public JLabel getLabBorder() {
        return this.labBorder;
    }
    
    public void setLabBorder(JLabel labBorder) {
        this.labBorder = labBorder;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public ShopingImgBorderMosulisten getBorderMosulisten() {
        return this.borderMosulisten;
    }
    
    public void setBorderMosulisten(ShopingImgBorderMosulisten borderMosulisten) {
        this.borderMosulisten = borderMosulisten;
    }
    
    static {
        GoodsMinJpanel.tcp = SpriteFactory.VloadSprite("resource/mouse/flicker.tcp", null);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }
}
