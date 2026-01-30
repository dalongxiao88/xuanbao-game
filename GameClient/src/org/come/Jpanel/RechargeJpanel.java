package org.come.Jpanel;

import com.tool.role.RoleData;
import org.come.until.CutButtonImage;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Dimension;
import com.tool.btn.TaoBaoBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RechargeJpanel extends JPanel
{
    private JLabel bannerImg;
    private JLabel jadeLab;
    private JLabel jadeNum;
    private TaoBaoBtn btnhp;
    private TaoBaoBtn btnep;
    private TaoBaoBtn btnsyy;
    private TaoBaoBtn btnxyy;
    private int maxPage;
    private int nowpage;
    private JLabel labpage;
    private RechargeLeftGoodsJpanel[] imageJpanel;
    
    public RechargeJpanel() {
        this.maxPage = 1;
        this.nowpage = 1;
        this.imageJpanel = new RechargeLeftGoodsJpanel[6];
        this.setPreferredSize(new Dimension(656, 445));
        this.setLayout(null);
        this.setOpaque(false);
        (this.labpage = new JLabel("1/1", 0)).setForeground(Color.white);
        this.labpage.setBounds(335, 378, 58, 17);
        this.labpage.setFont(UIUtils.TEXT_FONT1);
        this.add(this.labpage);
        (this.btnhp = new TaoBaoBtn("inkImg/button/2.png", 1, "首页", 40)).setBounds(279, 378, 34, 17);
        this.add(this.btnhp);
        (this.btnsyy = new TaoBaoBtn("inkImg/button/10.png", 1, "", 41)).setBounds(315, 378, 19, 20);
        this.add(this.btnsyy);
        (this.btnxyy = new TaoBaoBtn("inkImg/button/9.png", 1, "", 42)).setBounds(397, 378, 19, 20);
        this.add(this.btnxyy);
        (this.btnep = new TaoBaoBtn("inkImg/button/2.png", 1, "末页", 43)).setBounds(416, 378, 34, 17);
        this.add(this.btnep);
        this.add(this.getBannerImg());
        this.add(this.getJadeNum());
        this.add(this.getJadeLab());
        for (int i = 0; i < 6; ++i) {
            int row = i % 3;
            int col = i / 3;
            (this.imageJpanel[i] = new RechargeLeftGoodsJpanel(null)).setBounds(54 + row * 205, 135 + col * 100, 200, 90);
            this.add(this.imageJpanel[i]);
        }
    }
    
    public JLabel getLabpage() {
        return this.labpage;
    }
    
    public void setLabpage(JLabel labpage) {
        this.labpage = labpage;
    }
    
    public JLabel getBannerImg() {
        if (this.bannerImg == null) {
            (this.bannerImg = new JLabel()).setBounds(54, 0, 600, 125);
            this.bannerImg.setOpaque(true);
            this.bannerImg.setBackground(Color.red);
        }
        return this.bannerImg;
    }
    
    public void setBannerImg(JLabel bannerImg) {
        this.bannerImg = bannerImg;
    }
    
    public TaoBaoBtn getBtnhp() {
        return this.btnhp;
    }
    
    public void setBtnhp(TaoBaoBtn btnhp) {
        this.btnhp = btnhp;
    }
    
    public TaoBaoBtn getBtnep() {
        return this.btnep;
    }
    
    public void setBtnep(TaoBaoBtn btnep) {
        this.btnep = btnep;
    }
    
    public TaoBaoBtn getBtnsyy() {
        return this.btnsyy;
    }
    
    public void setBtnsyy(TaoBaoBtn btnsyy) {
        this.btnsyy = btnsyy;
    }
    
    public TaoBaoBtn getBtnxyy() {
        return this.btnxyy;
    }
    
    public void setBtnxyy(TaoBaoBtn btnxyy) {
        this.btnxyy = btnxyy;
    }
    
    public RechargeLeftGoodsJpanel[] getImageJpanel() {
        return this.imageJpanel;
    }
    
    public void setImageJpanel(RechargeLeftGoodsJpanel[] imageJpanel) {
        this.imageJpanel = imageJpanel;
    }
    
    public int getMaxPage() {
        return this.maxPage;
    }
    
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
    
    public int getNowpage() {
        return this.nowpage;
    }
    
    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }
    
    public JLabel getJadeLab() {
        if (this.jadeLab == null) {
            (this.jadeLab = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/background/51.png", -1, -1));
            this.jadeLab.setOpaque(false);
            this.jadeLab.setBounds(60, 378, 124, 19);
        }
        return this.jadeLab;
    }
    
    public void setJadeLab(JLabel jadeLab) {
        this.jadeLab = jadeLab;
    }
    
    public JLabel getJadeNum() {
        if (this.jadeNum == null) {
            (this.jadeNum = new JLabel(RoleData.getRoleData().getLoginResult().getCodecard() + "")).setBounds(103, 380, 80, 15);
            this.jadeNum.setOpaque(false);
            this.jadeNum.setForeground(Color.white);
            this.jadeNum.setFont(UIUtils.TEXT_COM_FONT);
        }
        return this.jadeNum;
    }
    
    public void setJadeNum(JLabel jadeNum) {
        this.jadeNum = jadeNum;
    }
}
