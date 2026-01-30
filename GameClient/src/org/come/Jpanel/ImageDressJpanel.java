package org.come.Jpanel;

import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import com.tool.image.ImageMixDeal;
import org.come.until.CutButtonImage;
import com.tool.role.RoleTX;
import java.awt.Graphics;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.mouslisten.RoleTxMouslisten;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import javax.swing.ImageIcon;
import com.tool.btn.ImageDressBtn;
import javax.swing.JLabel;
import com.tool.btn.SpecificBtn;
import javax.swing.JPanel;

public class ImageDressJpanel extends JPanel
{
    private SpecificBtn btnYjxc;
    private SpecificBtn btnBcxx;
    private SpecificBtn btnJx;
    private SpecificBtn btnDbggm;
    private SpecificBtn btnLeft;
    private SpecificBtn btnRight;
    private SpecificBtn btnTop;
    private SpecificBtn btnBottom;
    private static JLabel[] labYcd;
    private static JLabel[] labImages;
    private ImageDressBtn btnTx;
    private ImageDressBtn btnZsp;
    private ImageDressBtn btnZj;
    private ImageDressBtn btnWing;
    private ImageDressBtn btnFashion;
    private ImageDressBtn btnTxk;
    private ImageIcon icon0;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private ImageIcon icon3;
    private ImageIcon icon4;
    private ImageIcon icon5;
    private ImageIcon icon;
    
    public ImageDressJpanel() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String cbkg = configure.getCbgnkg();
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon0 = new ImageIcon("img/xy2uiimg/imagedress_0.png");
            this.icon1 = new ImageIcon("img/xy2uiimg/imagedress_1.png");
            this.icon2 = new ImageIcon("img/xy2uiimg/imagedress_2.png");
            this.icon3 = new ImageIcon("img/xy2uiimg/imagedress_3.png");
            this.icon4 = new ImageIcon("img/xy2uiimg/imagedress_4.png");
            this.icon5 = new ImageIcon("img/xy2uiimg/imagedress_5.png");
            this.setPreferredSize(new Dimension(622, 409));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 38);
            offBtn.setBounds(585, 10, 25, 25);
            this.add(offBtn);
            for (int i = 0; i < ImageDressJpanel.labYcd.length; ++i) {
                (ImageDressJpanel.labYcd[i] = new JLabel()).addMouseListener(new RoleTxMouslisten(-(i + 1)));
                ImageDressJpanel.labYcd[i].setBounds(59, 108 + i * 45, 39, 39);
                this.add(ImageDressJpanel.labYcd[i]);
            }
            for (int i = 0; i < 25; ++i) {
                int row = i % 5;
                int col = i / 5;
                (ImageDressJpanel.labImages[i] = new JLabel()).addMouseListener(new RoleTxMouslisten(i));
                ImageDressJpanel.labImages[i].setBounds(330 + row * 51, 97 + col * 51, 49, 49);
                this.add(ImageDressJpanel.labImages[i]);
            }
            (this.btnYjxc = new SpecificBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, "一键卸除", 2)).setBounds(108, 365, 68, 17);
            this.add(this.btnYjxc);
            (this.btnBcxx = new SpecificBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, "保存形象", 3)).setBounds(239, 365, 68, 17);
            this.add(this.btnBcxx);
            (this.btnJx = new SpecificBtn("inkImg/button/B59.png", 1, UIUtils.COLOR_BLACK, "捐献", 4)).setBounds(325, 355, 68, 26);
            (this.btnDbggm = new SpecificBtn("inkImg/button/B60.png", 1, UIUtils.COLOR_BLACK, "多宝阁购买", 5)).setBounds(492, 355, 92, 26);
            (this.btnLeft = new SpecificBtn("inkImg/button/12.png", 1, "", 6)).setBounds(183, 366, 11, 15);
            this.add(this.btnLeft);
            (this.btnRight = new SpecificBtn("inkImg/button/11.png", 1, "", 7)).setBounds(224, 366, 11, 15);
            this.add(this.btnRight);
            (this.btnTop = new SpecificBtn("inkImg/button/B42.png", 1, "", 8)).setBounds(580, 300, 19, 20);
            this.add(this.btnTop);
            (this.btnBottom = new SpecificBtn("inkImg/button/B43.png", 1, "", 9)).setBounds(580, 320, 19, 20);
            this.add(this.btnBottom);
            (this.btnTx = new ImageDressBtn("inkImg/button/B120.png", 1, 1, "", this)).setBounds(325, 59, 63, 26);
            this.add(this.btnTx);
            (this.btnZsp = new ImageDressBtn("inkImg/button/B121.png", 1, 2, "", this)).setBounds(390, 59, 63, 26);
            this.add(this.btnZsp);
            (this.btnZj = new ImageDressBtn("inkImg/button/B123.png", 1, 3, "", this)).setBounds(455, 59, 63, 26);
            this.add(this.btnZj);
            if (cbkg.equals("开")) {
                (this.btnWing = new ImageDressBtn("inkImg/button/B125.png", 1, 4, "", this)).setBounds(520, 59, 63, 26);
                this.add(this.btnWing);
            }
            (this.btnTxk = new ImageDressBtn("inkImg/button/txk.png", 1, 5, "", this)).setBounds(520, 59, 63, 26);
            this.add(this.btnTxk);
        }
        else {
            this.icon0 = new ImageIcon("img/xy2uiimg/imagedress_0.png");
            this.icon1 = new ImageIcon("img/xy2uiimg/imagedress_1.png");
            this.icon2 = new ImageIcon("img/xy2uiimg/imagedress_2.png");
            this.icon3 = new ImageIcon("img/xy2uiimg/imagedress_3.png");
            this.icon4 = new ImageIcon("img/xy2uiimg/imagedress_4.png");
            this.icon5 = new ImageIcon("img/xy2uiimg/imagedress_5.png");
            this.setPreferredSize(new Dimension(596, 457));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 38);
            offBtn.setBounds(570, 0, 25, 25);
            this.add(offBtn);
            for (int i = 0; i < ImageDressJpanel.labYcd.length; ++i) {
                (ImageDressJpanel.labYcd[i] = new JLabel()).addMouseListener(new RoleTxMouslisten(-(i + 1)));
                ImageDressJpanel.labYcd[i].setBounds(35, 119 + i * 45, 39, 39);
                this.add(ImageDressJpanel.labYcd[i]);
            }
            for (int i = 0; i < 25; ++i) {
                int row = i % 5;
                int col = i / 5;
                (ImageDressJpanel.labImages[i] = new JLabel()).addMouseListener(new RoleTxMouslisten(i));
                ImageDressJpanel.labImages[i].setBounds(304 + row * 51, 135 + col * 51, 49, 49);
                this.add(ImageDressJpanel.labImages[i]);
            }
            (this.btnYjxc = new SpecificBtn("inkImg/hongmu/a7.png", 1, "一键卸除", 2)).setBounds(211, 400, 68, 26);
            this.add(this.btnYjxc);
            (this.btnBcxx = new SpecificBtn("inkImg/hongmu/a7.png", 1, "保存形象", 3)).setBounds(112, 400, 68, 26);
            this.add(this.btnBcxx);
            (this.btnLeft = new SpecificBtn("img/xy2uiimg/26_png.button.btn_left.png", 1, "", 6)).setBounds(165, 356, 11, 15);
            this.add(this.btnLeft);
            (this.btnRight = new SpecificBtn("img/xy2uiimg/54_png.button.btn_right.png", 1, "", 7)).setBounds(194, 356, 11, 15);
            this.add(this.btnRight);
            (this.btnTop = new SpecificBtn("img/xy2uiimg/42_png.button.btn_1.png", 1, "", 8)).setBounds(560, 344, 19, 20);
            this.add(this.btnTop);
            (this.btnBottom = new SpecificBtn("img/xy2uiimg/34_png.button.xy_vscroll$down.png", 1, "", 9)).setBounds(560, 368, 19, 20);
            this.add(this.btnBottom);
            (this.btnTx = new ImageDressBtn("img/xy2uiimg/imagedress_1.png", 1, 1, "", this)).setBounds(301, 72, 63, 26);
            this.add(this.btnTx);
            (this.btnZsp = new ImageDressBtn("img/xy2uiimg/imagedress_3.png", 1, 2, "", this)).setBounds(366, 72, 63, 26);
            this.add(this.btnZsp);
            (this.btnZj = new ImageDressBtn("img/xy2uiimg/imagedress_5.png", 1, 3, "", this)).setBounds(431, 72, 63, 26);
            this.add(this.btnZj);
            if (cbkg.equals("开")) {
                (this.btnWing = new ImageDressBtn("img/xy2uiimg/imagedress_7.png", 1, 4, "", this)).setBounds(496, 72, 63, 26);
                this.add(this.btnWing);
            }
            (this.btnTxk = new ImageDressBtn("img/xy2uiimg/imagedress_9.png", 1, 5, "", this)).setBounds(496, 72, 63, 26);
            this.add(this.btnTxk);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B253.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 622, 409, this);
            RoleTX.getRoleTX().draw(g, 0);
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            String nao = "新";
            if (configure.getNeworold() != null) {
                nao = configure.getNeworold();
            }
            ImageIcon headIcon1 = CutButtonImage.getImage("inkImg/background/S155.png", 58, 58);
            g.drawImage(headIcon1.getImage(), 110, 101, 67, 66, null);
            ImageIcon headIcon2;
            if (nao.equals("新")) {
                headIcon2 = CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + "-1.png", 58, 58);
            }
            else {
                headIcon2 = CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58);
            }
            g.drawImage(headIcon2.getImage(), 115, 105, 58, 58, null);
            Sprite role = SpriteFactory.Prepare(RoleTX.getLabroletxk());
            if (role != null) {
                role.updateToTime(ImageMixDeal.userimg.getTime(), 4);
                role.draw(g, 143, 133);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/imagedressjpanel.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 596, 457, this);
            RoleTX.getRoleTX().draw(g, 0);
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            String nao = "新";
            if (configure.getNeworold() != null) {
                nao = configure.getNeworold();
            }
            ImageIcon headIcon1 = CutButtonImage.getImage("inkImg/danxin/goodse/9_5.png", 58, 58);
            g.drawImage(headIcon1.getImage(), 92, 142, 65, 63, null);
            ImageIcon headIcon2;
            if (nao.equals("新")) {
                headIcon2 = CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + "-1.png", 58, 58);
            }
            else {
                headIcon2 = CutButtonImage.getImage("img/head/s" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + ".png", 58, 58);
            }
            g.drawImage(headIcon2.getImage(), 95, 145, 58, 58, null);
            Sprite role = SpriteFactory.Prepare(RoleTX.getLabroletxk());
            if (role != null) {
                role.updateToTime(ImageMixDeal.userimg.getTime(), 4);
                role.draw(g, 122, 172);
            }
        }
    }
    
    public static JLabel[] getLabYcd() {
        return ImageDressJpanel.labYcd;
    }
    
    public static void setLabYcd(JLabel[] labYcd) {
        ImageDressJpanel.labYcd = labYcd;
    }
    
    public static JLabel[] getLabImages() {
        return ImageDressJpanel.labImages;
    }
    
    public static void setLabImages(JLabel[] labImages) {
        ImageDressJpanel.labImages = labImages;
    }
    
    public ImageDressBtn getBtnTx() {
        return this.btnTx;
    }
    
    public void setBtnTx(ImageDressBtn btnTx) {
        this.btnTx = btnTx;
    }
    
    public ImageDressBtn getBtnZsp() {
        return this.btnZsp;
    }
    
    public void setBtnZsp(ImageDressBtn btnZsp) {
        this.btnZsp = btnZsp;
    }
    
    public ImageDressBtn getBtnZj() {
        return this.btnZj;
    }
    
    public void setBtnZj(ImageDressBtn btnZj) {
        this.btnZj = btnZj;
    }
    
    public ImageDressBtn getBtnWing() {
        return this.btnWing;
    }
    
    public void setBtnWing(ImageDressBtn btnWing) {
        this.btnWing = btnWing;
    }
    
    public SpecificBtn getBtnYjxc() {
        return this.btnYjxc;
    }
    
    public void setBtnYjxc(SpecificBtn btnYjxc) {
        this.btnYjxc = btnYjxc;
    }
    
    public SpecificBtn getBtnBcxx() {
        return this.btnBcxx;
    }
    
    public void setBtnBcxx(SpecificBtn btnBcxx) {
        this.btnBcxx = btnBcxx;
    }
    
    public SpecificBtn getBtnJx() {
        return this.btnJx;
    }
    
    public void setBtnJx(SpecificBtn btnJx) {
        this.btnJx = btnJx;
    }
    
    public SpecificBtn getBtnDbggm() {
        return this.btnDbggm;
    }
    
    public void setBtnDbggm(SpecificBtn btnDbggm) {
        this.btnDbggm = btnDbggm;
    }
    
    public SpecificBtn getBtnLeft() {
        return this.btnLeft;
    }
    
    public void setBtnLeft(SpecificBtn btnLeft) {
        this.btnLeft = btnLeft;
    }
    
    public SpecificBtn getBtnRight() {
        return this.btnRight;
    }
    
    public void setBtnRight(SpecificBtn btnRight) {
        this.btnRight = btnRight;
    }
    
    public SpecificBtn getBtnTop() {
        return this.btnTop;
    }
    
    public void setBtnTop(SpecificBtn btnTop) {
        this.btnTop = btnTop;
    }
    
    public SpecificBtn getBtnBottom() {
        return this.btnBottom;
    }
    
    public void setBtnBottom(SpecificBtn btnBottom) {
        this.btnBottom = btnBottom;
    }
    
    public ImageIcon getIcon0() {
        return this.icon0;
    }
    
    public void setIcon0(ImageIcon icon0) {
        this.icon0 = icon0;
    }
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
    }
    
    public ImageIcon getIcon2() {
        return this.icon2;
    }
    
    public void setIcon2(ImageIcon icon2) {
        this.icon2 = icon2;
    }
    
    public ImageIcon getIcon3() {
        return this.icon3;
    }
    
    public void setIcon3(ImageIcon icon3) {
        this.icon3 = icon3;
    }
    
    public ImageIcon getIcon4() {
        return this.icon4;
    }
    
    public void setIcon4(ImageIcon icon4) {
        this.icon4 = icon4;
    }
    
    public ImageIcon getIcon5() {
        return this.icon5;
    }
    
    public void setIcon5(ImageIcon icon5) {
        this.icon5 = icon5;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public ImageDressBtn getBtnTxk() {
        return this.btnTxk;
    }
    
    public void setBtnTxk(ImageDressBtn btnTxk) {
        this.btnTxk = btnTxk;
    }
    
    static {
        ImageDressJpanel.labYcd = new JLabel[5];
        ImageDressJpanel.labImages = new JLabel[25];
    }
}
