package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import com.tool.image.ImageMixDeal;
import com.updateNew.MyIsif;
import java.awt.Graphics;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import java.math.BigDecimal;
import javax.swing.Icon;
import org.come.entity.Pal;
import java.awt.LayoutManager;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.mouslisten.PalEquipListener;
import com.tool.btn.PartnerBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PartnerEquipJpanel extends JPanel
{
    private JLabel[] labEquip;
    private PartnerBtn btnEquip;
    private PartnerBtn petDetil;
    private PartnerBtn getPetbtn;
    private PartnerBtn getLingbao;
    private PartnerBtn longbaoDetil;
    private PalEquipListener[] palEquipListeners;
    private ImageIcon icon;
    
    public PartnerEquipJpanel() {
        this.setPreferredSize(new Dimension(335, 291));
        this.setOpaque(false);
        this.setLayout((LayoutManager)null);
        this.getLabEquip();
        this.getBtnEquip();
        this.getBtnPetDetil();
        this.getBtnGetPet();
        this.getBtnGetLingbo();
        this.getBtnLingbaoDetil();
    }
    
    public long palExp(int lvl) {
        return (long)(3000 * (lvl - 20));
    }
    
    public int isBreakLevel(int level) {
        if (level == 60) {
            return 10;
        }
        if (level == 100) {
            return 20;
        }
        if (level == 140) {
            return 40;
        }
        return (level == 180) ? 80 : -1;
    }
    
    public void ShowEquipArr(Pal pal) {
        this.clearShowEquipArr();
        if (pal != null && pal.getParts() != null && !"".equals(pal.getParts())) {
            String[] split = pal.getParts().split("\\|");
            for (int i = 0; i < split.length; ++i) {
                String[] placeAndEquip = split[i].split("=");
                this.showEquip(placeAndEquip[1], placeAndEquip[0]);
            }
        }
    }
    
    public void clearShowEquipArr() {
        for (int i = 0; i < this.labEquip.length; ++i) {
            this.labEquip[i].setIcon((Icon)null);
            this.palEquipListeners[i].setRgid((BigDecimal)null);
        }
    }
    
    public void showEquip(String rgid, String place) {
        int parseInt = Integer.parseInt(place);
        if (rgid == null) {
            this.labEquip[parseInt].setIcon((Icon)null);
        }
        else {
            BigDecimal goodsRgid = new BigDecimal(rgid);
            Goodstable goodstable = GoodsListFromServerUntil.getRgid(goodsRgid);
            this.labEquip[parseInt].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
            this.palEquipListeners[parseInt].setRgid(goodsRgid);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B306.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 335, 291, this);
            if (PartnerTeamJpanel.part != null) {
                PartnerTeamJpanel.part.draw(g, 80, 137, 0, ImageMixDeal.userimg.getTime());
            }
            if (PartnerTeamJpanel.petpart != null) {
                PartnerTeamJpanel.petpart.draw(g, 250, 280, 0, ImageMixDeal.userimg.getTime());
            }
            if (PartnerTeamJpanel.lingBaopart != null) {
                PartnerTeamJpanel.lingBaopart.draw(g, 30, 140, 0, ImageMixDeal.userimg.getTime());
            }
            if (PartnerTeamJpanel.babypart != null) {
                PartnerTeamJpanel.babypart.draw(g, 80, 280, 0, ImageMixDeal.userimg.getTime());
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S152.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 335, 291, this);
            if (PartnerTeamJpanel.part != null) {
                PartnerTeamJpanel.part.draw(g, 80, 137, 0, ImageMixDeal.userimg.getTime());
            }
            if (PartnerTeamJpanel.petpart != null) {
                PartnerTeamJpanel.petpart.draw(g, 250, 280, 0, ImageMixDeal.userimg.getTime());
            }
            if (PartnerTeamJpanel.lingBaopart != null) {
                PartnerTeamJpanel.lingBaopart.draw(g, 30, 140, 0, ImageMixDeal.userimg.getTime());
            }
            if (PartnerTeamJpanel.babypart != null) {
                PartnerTeamJpanel.babypart.draw(g, 80, 280, 0, ImageMixDeal.userimg.getTime());
            }
        }
    }
    
    public JLabel[] getLabEquip() {
        if (this.labEquip == null) {
            this.labEquip = new JLabel[6];
            this.palEquipListeners = new PalEquipListener[6];
            for (int i = 0; i < this.labEquip.length; ++i) {
                (this.labEquip[i] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        if (this.getIcon() != null) {
                            g.setColor(UIUtils.Color_BACK);
                            g.fillRect(0, 0, 49, 49);
                        }
                        super.paintComponent(g);
                    }
                }).setOpaque(false);
                this.palEquipListeners[i] = new PalEquipListener((BigDecimal)null);
                this.labEquip[i].addMouseListener(this.palEquipListeners[i]);
                this.labEquip[i].setBounds(165 + i % 3 * 58, 4 + i / 3 * 56, 49, 49);
                this.add(this.labEquip[i]);
            }
        }
        return this.labEquip;
    }
    
    public void setLabEquip(JLabel[] labEquip) {
        this.labEquip = labEquip;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public PartnerBtn getBtnEquip() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.btnEquip == null) {
                (this.btnEquip = new PartnerBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "物品栏", 9)).setBounds(28, 157, 99, 24);
                this.add(this.btnEquip);
            }
        }
        else if (this.btnEquip == null) {
            (this.btnEquip = new PartnerBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "物品栏", 9)).setBounds(33, 157, 80, 26);
            this.add(this.btnEquip);
        }
        return this.btnEquip;
    }
    
    public void setBtnEquip(PartnerBtn btnEquip) {
        this.btnEquip = btnEquip;
    }
    
    public PartnerBtn getBtnPetDetil() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.petDetil == null) {
                (this.petDetil = new PartnerBtn("inkImg/button1/B31.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_HY14, "详", 11)).setBounds(310, 126, 18, 18);
                this.add(this.petDetil);
            }
        }
        else if (this.petDetil == null) {
            (this.petDetil = new PartnerBtn("inkImg/hongmu/btn-small-1-bg.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY14, "详", 11)).setBounds(310, 126, 18, 18);
            this.add(this.petDetil);
        }
        return this.petDetil;
    }
    
    public PartnerBtn getBtnGetPet() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.getPetbtn == null) {
                (this.getPetbtn = new PartnerBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY88, "取回召唤兽", 12)).setBounds(164, 128, 99, 24);
                this.add(this.getPetbtn);
            }
        }
        else if (this.getPetbtn == null) {
            (this.getPetbtn = new PartnerBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY14, "取回召唤兽", 12)).setBounds(164, 128, 80, 26);
            this.add(this.getPetbtn);
        }
        return this.getPetbtn;
    }
    
    public PartnerBtn getBtnLingbaoDetil() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.longbaoDetil == null) {
                (this.longbaoDetil = new PartnerBtn("inkImg/button1/B31.png", 1, UIUtils.COLOR_WHITE, UIUtils.TEXT_HY14, "详", 13)).setBounds(7, 4, 18, 18);
                this.add(this.longbaoDetil);
            }
        }
        else if (this.longbaoDetil == null) {
            (this.longbaoDetil = new PartnerBtn("inkImg/hongmu/btn-small-1-bg.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY14, "详", 13)).setBounds(7, 4, 18, 18);
            this.add(this.longbaoDetil);
        }
        return this.longbaoDetil;
    }
    
    public PartnerBtn getBtnGetLingbo() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.getLingbao == null) {
                (this.getLingbao = new PartnerBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY88, "取回灵宝", 14)).setBounds(50, 3, 99, 24);
                this.add(this.getLingbao);
            }
        }
        else if (this.getLingbao == null) {
            (this.getLingbao = new PartnerBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "取回灵宝", 14)).setBounds(60, 3, 80, 26);
            this.add(this.getLingbao);
        }
        return this.getLingbao;
    }
}
