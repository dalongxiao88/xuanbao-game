package org.come.Jpanel;

import com.tool.tcp.Sprite;
import java.util.Map;
import org.come.bean.ConfigureBean;
import com.tool.tcp.SpriteFactory;
import com.tool.image.ImageMixDeal;
import java.math.BigDecimal;
import org.come.model.Configure;
import com.tool.role.RoleTX;
import java.awt.Graphics;
import org.come.bean.RoleTxBean;
import org.come.until.UserMessUntil;
import org.come.Frame.TryOntxJframe;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;
import org.come.until.DeviceEshopUntil;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.model.Eshop;
import javax.swing.JLabel;
import com.tool.btn.SpecificBtn;
import javax.swing.JPanel;

public class TaobaoCourtSplendidJpanel extends JPanel
{
    private SpecificBtn btnTx;
    private SpecificBtn btnZsp;
    private SpecificBtn btnZj;
    private SpecificBtn btnTxk;
    private SpecificBtn btnleft;
    private SpecificBtn btnright;
    private SpecificBtn btnxc;
    private SpecificBtn btnsc;
    private SpecificBtn btnhp;
    private SpecificBtn btnep;
    private SpecificBtn btnsyy;
    private SpecificBtn btnxyy;
    private int type;
    private int nowpage;
    private JLabel labpage;
    private int zbs;
    private static String labroletxk;
    private ShowImageJpanel[] imageJpanel;
    private Eshop[] eshops;
    private ImageIcon iconBeiJing;
    private ImageIcon icon;
    
    public TaobaoCourtSplendidJpanel() {
        this.type = 11;
        this.nowpage = 1;
        this.zbs = 0;
        this.imageJpanel = new ShowImageJpanel[6];
        this.eshops = new Eshop[4];
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(656, 445));
            this.setLayout(null);
            this.setOpaque(false);
            this.setBackground(UIUtils.Color_BACK);
            (this.labpage = new JLabel("", 0)).setForeground(Color.white);
            this.labpage.setBounds(335, 378, 58, 17);
            this.labpage.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labpage);
            (this.btnleft = new SpecificBtn("inkImg/button/12.png", 1, "", 15, this)).setBounds(128, 296, 11, 15);
            this.add(this.btnleft);
            (this.btnright = new SpecificBtn("inkImg/button/11.png", 1, "", 16, this)).setBounds(163, 296, 11, 15);
            this.add(this.btnright);
            (this.btnxc = new SpecificBtn("inkImg/button1/B30.png", 1, "卸除", 17, this)).setBounds(185, 335, 34, 17);
            this.add(this.btnxc);
            (this.btnsc = new SpecificBtn("inkImg/button1/B30.png", 1, "试穿", 18, this)).setBounds(225, 335, 34, 17);
            this.add(this.btnsc);
            (this.btnhp = new SpecificBtn("inkImg/button1/B30.png", 1, "首页", 11, this)).setBounds(279, 378, 34, 17);
            this.add(this.btnhp);
            (this.btnsyy = new SpecificBtn("inkImg/button/10.png", 1, "", 12, this)).setBounds(315, 378, 19, 20);
            this.add(this.btnsyy);
            (this.btnxyy = new SpecificBtn("inkImg/button/9.png", 1, "", 13, this)).setBounds(397, 378, 19, 20);
            this.add(this.btnxyy);
            (this.btnep = new SpecificBtn("inkImg/button1/B30.png", 1, "末页", 14, this)).setBounds(416, 378, 34, 17);
            this.add(this.btnep);
            for (int i = 0; i < 6; ++i) {
                int row = i % 3;
                int col = i / 3;
                (this.imageJpanel[i] = new ShowImageJpanel()).setBounds(274 + row * 125, 37 + col * 165, 120, 155);
                this.add(this.imageJpanel[i]);
            }
            (this.btnTx = new SpecificBtn("inkImg/button/B120.png", 1, "", 19, this)).setBounds(378, 14, 63, 26);
            this.add(this.btnTx);
            (this.btnZsp = new SpecificBtn("inkImg/button/B121.png", 1, "", 20, this)).setBounds(443, 14, 63, 26);
            this.add(this.btnZsp);
            (this.btnZj = new SpecificBtn("inkImg/button/B123.png", 1, "", 21, this)).setBounds(508, 14, 63, 26);
            this.add(this.btnZj);
            (this.btnTxk = new SpecificBtn("inkImg/button/txk.png", 1, "", 22, this)).setBounds(573, 14, 63, 26);
            this.add(this.btnTxk);
        }
        else {
            this.setPreferredSize(new Dimension(732, 480));
            this.setLayout(null);
            this.setOpaque(false);
            this.setBackground(UIUtils.Color_BACK);
            (this.labpage = new JLabel("", 0)).setForeground(Color.white);
            this.labpage.setBounds(570, 378, 58, 17);
            this.labpage.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labpage);
            (this.btnleft = new SpecificBtn("img/xy2uiimg/27_png.button.btn_left.png", 1, "", 15, this)).setBounds(128, 296, 11, 15);
            this.add(this.btnleft);
            (this.btnright = new SpecificBtn("img/xy2uiimg/54_png.button.btn_right.png", 1, "", 16, this)).setBounds(163, 296, 11, 15);
            this.add(this.btnright);
            (this.btnxc = new SpecificBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "卸除", 17, this)).setBounds(185, 335, 34, 17);
            this.add(this.btnxc);
            (this.btnhp = new SpecificBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "首页", 11, this)).setBounds(516, 378, 34, 17);
            this.add(this.btnhp);
            (this.btnsyy = new SpecificBtn("img/xy2uiimg/29_png.button.btn_8.png", 1, "", 12, this)).setBounds(550, 377, 19, 20);
            this.add(this.btnsyy);
            (this.btnxyy = new SpecificBtn("img/xy2uiimg//36_png.button.btn_7.png", 1, "", 13, this)).setBounds(630, 377, 19, 20);
            this.add(this.btnxyy);
            (this.btnep = new SpecificBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "末页", 14, this)).setBounds(650, 378, 34, 17);
            this.add(this.btnep);
            for (int i = 0; i < 6; ++i) {
                int row = i % 3;
                int col = i / 3;
                (this.imageJpanel[i] = new ShowImageJpanel()).setBounds(274 + row * 125, 37 + col * 165, 120, 155);
                this.add(this.imageJpanel[i]);
            }
            (this.btnTx = new SpecificBtn("img/xy2uiimg/imagedress_0.png", 1, "", 19, this)).setBounds(378, 14, 63, 26);
            this.add(this.btnTx);
            (this.btnZsp = new SpecificBtn("img/xy2uiimg/imagedress_2.png", 1, "", 20, this)).setBounds(443, 14, 63, 26);
            this.add(this.btnZsp);
            (this.btnZj = new SpecificBtn("img/xy2uiimg/imagedress_4.png", 1, "", 21, this)).setBounds(508, 14, 63, 26);
            this.add(this.btnZj);
            (this.btnTxk = new SpecificBtn("img/xy2uiimg/imagedress_9.png", 1, "", 22, this)).setBounds(573, 14, 63, 26);
            this.add(this.btnTxk);
        }
    }
    
    public List<Eshop> returnlEshops(int type) {
        List<Eshop> lEshops = new ArrayList<>();
        if (type == 11) {
            lEshops = DeviceEshopUntil.getShopingTX();
        }
        else if (type == 12) {
            lEshops = DeviceEshopUntil.getShopingZS();
        }
        else if (type == 13) {
            lEshops = DeviceEshopUntil.getShopingZJ();
        }
        else if (type == 14) {
            lEshops = DeviceEshopUntil.getShopingTXK();
        }
        return lEshops;
    }
    
    public void showTaobao() {
        List<Eshop> lEshops = this.returnlEshops(this.type);
        if (lEshops == null) {
            return;
        }
        this.refreshDbgSplendid(lEshops, this.nowpage);
        FormsManagement.showForm(39);
    }
    
    public void refresGoodsSplendid() {
        List<Eshop> lEshops = this.returnlEshops(this.type);
        if (lEshops == null || (lEshops != null && lEshops.size() <= 0)) {
            return;
        }
        this.refreshDbgSplendid(lEshops, this.nowpage = 1);
    }
    
    public void refreshDbgSplendid(List<Eshop> lEshops, int nowpage) {
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < 6; ++i) {
                this.imageJpanel[i].clearWindow();
            }
            int totalpage = lEshops.size() / 6 + ((lEshops.size() % 6 == 0) ? 0 : 1);
            this.labpage.setText(nowpage + "/" + totalpage);
            for (int j = 0; j < ((lEshops.size() - (nowpage - 1) * 6 >= 6) ? 6 : (lEshops.size() - (nowpage - 1) * 6)); ++j) {
                this.imageJpanel[j].getXiaoMouslisten().setEshop((Eshop)lEshops.get(j + (nowpage - 1) * 6));
                this.imageJpanel[j].getBtnBuy().setEshop((Eshop)lEshops.get(j + (nowpage - 1) * 6));
                this.imageJpanel[j].setName(((Eshop)lEshops.get(j + (nowpage - 1) * 6)).getEshopname());
                this.imageJpanel[j].setValue(((Eshop)lEshops.get(j + (nowpage - 1) * 6)).getEshopprice() + "仙玉");
                this.imageJpanel[j].getLabImg().setIcon(CutButtonImage.getImage("img/item/" + ((Eshop)lEshops.get(j + (nowpage - 1) * 6)).getEshopskin() + ".png", 100, 100));
                this.imageJpanel[j].getBtnBuy().setIcon(new ImageIcon("inkImg/button1/B30.png"));
                this.imageJpanel[j].getBtnBuy().setText("购买");
                this.imageJpanel[j].getBtnBuy().setBtn(1);
                this.imageJpanel[j].getLabBorder().setIcon(CutButtonImage.getImage("inkImg/tupiankuang/Z1007.png", 108, 108));
            }
        }
        else {
            for (int i = 0; i < 6; ++i) {
                this.imageJpanel[i].clearWindow();
            }
            int totalpage = lEshops.size() / 6 + ((lEshops.size() % 6 == 0) ? 0 : 1);
            this.labpage.setText(nowpage + "/" + totalpage);
            for (int j = 0; j < ((lEshops.size() - (nowpage - 1) * 6 >= 6) ? 6 : (lEshops.size() - (nowpage - 1) * 6)); ++j) {
                this.imageJpanel[j].getXiaoMouslisten().setEshop((Eshop)lEshops.get(j + (nowpage - 1) * 6));
                this.imageJpanel[j].getBtnBuy().setEshop((Eshop)lEshops.get(j + (nowpage - 1) * 6));
                this.imageJpanel[j].setName(((Eshop)lEshops.get(j + (nowpage - 1) * 6)).getEshopname());
                this.imageJpanel[j].setValue(((Eshop)lEshops.get(j + (nowpage - 1) * 6)).getEshopprice() + "仙玉");
                this.imageJpanel[j].getLabImg().setIcon(CutButtonImage.getImage("img/item/" + ((Eshop)lEshops.get(j + (nowpage - 1) * 6)).getEshopskin() + ".png", 100, 100));
                this.imageJpanel[j].getBtnBuy().setIcon(new ImageIcon("inkImg/hongmu/21_png.button.tab_nex.png"));
                this.imageJpanel[j].getBtnBuy().setText("购买");
                this.imageJpanel[j].getBtnBuy().setBtn(1);
                this.imageJpanel[j].getLabBorder().setIcon(CutButtonImage.getImage("img/xy2uiimg/物品框_120×120.png", -1, -1));
            }
        }
    }
    
    public void showTxMsg() {
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < 4; ++i) {
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[i].clearWindow();
            }
            if (this.eshops == null) {
                return;
            }
            List<Eshop> eshops = new ArrayList<>();
            for (int j = 0; j < this.eshops.length; ++j) {
                if (this.eshops[j] != null) {
                    eshops.add(this.eshops[j]);
                }
            }
            if (eshops == null || (eshops != null && eshops.size() <= 0)) {
                return;
            }
            for (int j = 0; j < eshops.size(); ++j) {
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getXiaoMouslisten().setEshop((Eshop)eshops.get(j));
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getBtnBuy().setEshop((Eshop)eshops.get(j));
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].setName(((Eshop)eshops.get(j)).getEshopname());
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].setValue(((Eshop)eshops.get(j)).getEshopprice() + "仙玉");
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getLabImg().setIcon(CutButtonImage.getImage("img/item/" + ((Eshop)eshops.get(j)).getEshopskin() + ".png", 100, 100));
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getBtnBuy().setIcon(CutButtonImage.getImage("inkImg/button1/B30.png", -1, -1));
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getBtnBuy().setBtn(1);
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getBtnBuy().setText("购买");
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getLabBorder().setIcon(CutButtonImage.getImage("inkImg/tupiankuang/Z1007.png", 108, 108));
            }
        }
        else {
            for (int i = 0; i < 4; ++i) {
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[i].clearWindow();
            }
            if (this.eshops == null) {
                return;
            }
            List<Eshop> eshops = new ArrayList<>();
            for (int j = 0; j < this.eshops.length; ++j) {
                if (this.eshops[j] != null) {
                    eshops.add(this.eshops[j]);
                }
            }
            if (eshops == null || (eshops != null && eshops.size() <= 0)) {
                return;
            }
            for (int j = 0; j < eshops.size(); ++j) {
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getXiaoMouslisten().setEshop((Eshop)eshops.get(j));
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getBtnBuy().setEshop((Eshop)eshops.get(j));
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].setName(((Eshop)eshops.get(j)).getEshopname());
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].setValue(((Eshop)eshops.get(j)).getEshopprice() + "仙玉");
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getLabImg().setIcon(CutButtonImage.getImage("img/item/" + ((Eshop)eshops.get(j)).getEshopskin() + ".png", 100, 100));
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getBtnBuy().setIcon(CutButtonImage.getImage("inkImg/hongmu/21_png.button.tab_nex.png", -1, -1));
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getBtnBuy().setBtn(1);
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getBtnBuy().setText("购买");
                TryOntxJframe.getTryOntxJframe().getOntxJpanel().getImageJpanels()[j].getLabBorder().setIcon(CutButtonImage.getImage("img/xy2uiimg/物品框_120×120.png", -1, -1));
            }
        }
    }
    
    public static void addTXK(int type, int id) {
        RoleTxBean txBean = UserMessUntil.getTxBean(id);
        if (txBean == null) {
            return;
        }
        TaobaoCourtSplendidJpanel.labroletxk = "inkImg/txk/tx" + txBean.getRdId() + ".tcp";
    }
    
    public static void removeTXK(int type, int id) {
        RoleTxBean txBean = UserMessUntil.getTxBean(id);
        if (txBean == null) {
            return;
        }
        TaobaoCourtSplendidJpanel.labroletxk = "";
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            g.setFont(UIUtils.TEXT_HURT2);
            g.drawString("装扮数:" + this.zbs, 53, 348);
            if (this.iconBeiJing == null) {
                this.iconBeiJing = new ImageIcon("inkImg/background/2.png");
            }
            g.drawImage(this.iconBeiJing.getImage(), 53, 40, 218, 294, this);
            RoleTX.getRoleTX().draw(g, 1);
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("inkImg/background/S7.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 332, 378, 65, 19, this);
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            String nao = "新";
            if (configure.getNeworold() != null) {
                nao = configure.getNeworold();
            }
            ImageIcon headIcon1 = CutButtonImage.getImage("inkImg/background/S155.png", 58, 58);
            g.drawImage(headIcon1.getImage(), 195, 45, 67, 66, null);
            ImageIcon headIcon2;
            if (nao.equals("新")) {
                headIcon2 = CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + "-1.png", 58, 58);
            }
            else {
                headIcon2 = CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58);
            }
            g.drawImage(headIcon2.getImage(), 200, 50, 58, 58, null);
            Sprite role = SpriteFactory.Prepare(TaobaoCourtSplendidJpanel.labroletxk);
            if (role != null) {
                role.updateToTime(ImageMixDeal.userimg.getTime(), 4);
                role.draw(g, 227, 78);
            }
        }
        else {
            g.setFont(UIUtils.TEXT_HURT2);
            g.setColor(new Color(187, 165, 75));
            g.drawString("装扮数:" + this.zbs, 53, 348);
            if (this.iconBeiJing == null) {
                this.iconBeiJing = new ImageIcon("inkImg/hongmu/shizhuang.png");
            }
            g.drawImage(this.iconBeiJing.getImage(), 43, 40, 228, 274, this);
            RoleTX.getRoleTX().draw(g, 1);
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("img/xy2uiimg/border_quac1k.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 332, 378, 65, 19, this);
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            String nao = "新";
            if (configure.getNeworold() != null) {
                nao = configure.getNeworold();
            }
            ImageIcon headIcon1 = CutButtonImage.getImage("inkImg/danxin/goodse/9_5.png", 58, 58);
            g.drawImage(headIcon1.getImage(), 196, 47, 65, 63, null);
            ImageIcon headIcon2;
            if (nao.equals("新")) {
                headIcon2 = CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + "-1.png", 58, 58);
            }
            else {
                headIcon2 = CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58);
            }
            g.drawImage(headIcon2.getImage(), 199, 50, 58, 58, null);
            Sprite role = SpriteFactory.Prepare(TaobaoCourtSplendidJpanel.labroletxk);
            if (role != null) {
                role.updateToTime(ImageMixDeal.userimg.getTime(), 4);
                role.draw(g, 228, 78);
            }
        }
    }
    
    public SpecificBtn getBtnTx() {
        return this.btnTx;
    }
    
    public void setBtnTx(SpecificBtn btnTx) {
        this.btnTx = btnTx;
    }
    
    public SpecificBtn getBtnZsp() {
        return this.btnZsp;
    }
    
    public void setBtnZsp(SpecificBtn btnZsp) {
        this.btnZsp = btnZsp;
    }
    
    public SpecificBtn getBtnZj() {
        return this.btnZj;
    }
    
    public void setBtnZj(SpecificBtn btnZj) {
        this.btnZj = btnZj;
    }
    
    public SpecificBtn getBtnTxk() {
        return this.btnTxk;
    }
    
    public void setBtnTxk(SpecificBtn btnTxk) {
        this.btnTxk = btnTxk;
    }
    
    public SpecificBtn getBtnleft() {
        return this.btnleft;
    }
    
    public void setBtnleft(SpecificBtn btnleft) {
        this.btnleft = btnleft;
    }
    
    public SpecificBtn getBtnright() {
        return this.btnright;
    }
    
    public void setBtnright(SpecificBtn btnright) {
        this.btnright = btnright;
    }
    
    public SpecificBtn getBtnxc() {
        return this.btnxc;
    }
    
    public void setBtnxc(SpecificBtn btnxc) {
        this.btnxc = btnxc;
    }
    
    public SpecificBtn getBtnsc() {
        return this.btnsc;
    }
    
    public void setBtnsc(SpecificBtn btnsc) {
        this.btnsc = btnsc;
    }
    
    public SpecificBtn getBtnhp() {
        return this.btnhp;
    }
    
    public void setBtnhp(SpecificBtn btnhp) {
        this.btnhp = btnhp;
    }
    
    public SpecificBtn getBtnep() {
        return this.btnep;
    }
    
    public void setBtnep(SpecificBtn btnep) {
        this.btnep = btnep;
    }
    
    public SpecificBtn getBtnsyy() {
        return this.btnsyy;
    }
    
    public void setBtnsyy(SpecificBtn btnsyy) {
        this.btnsyy = btnsyy;
    }
    
    public SpecificBtn getBtnxyy() {
        return this.btnxyy;
    }
    
    public void setBtnxyy(SpecificBtn btnxyy) {
        this.btnxyy = btnxyy;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getNowpage() {
        return this.nowpage;
    }
    
    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }
    
    public JLabel getLabpage() {
        return this.labpage;
    }
    
    public void setLabpage(JLabel labpage) {
        this.labpage = labpage;
    }
    
    public int getZbs() {
        return this.zbs;
    }
    
    public void setZbs(int zbs) {
        this.zbs = zbs;
    }
    
    public ShowImageJpanel[] getImageJpanel() {
        return this.imageJpanel;
    }
    
    public void setImageJpanel(ShowImageJpanel[] imageJpanel) {
        this.imageJpanel = imageJpanel;
    }
    
    public Eshop[] getEshops() {
        return this.eshops;
    }
    
    public void setEshops(Eshop[] eshops) {
        this.eshops = eshops;
    }
    
    public ImageIcon getIconBeiJing() {
        return this.iconBeiJing;
    }
    
    public void setIconBeiJing(ImageIcon iconBeiJing) {
        this.iconBeiJing = iconBeiJing;
    }
}
