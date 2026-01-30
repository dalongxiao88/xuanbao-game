package org.skill.panel;

import java.awt.FontMetrics;
import org.come.until.Util;
import com.tool.role.RoleData;
import java.awt.Graphics;
import java.awt.Font;
import io.netty.util.internal.StringUtil;
import org.come.entity.RoleSummoning;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import com.updateNew.MyIsif;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import com.tool.btn.PetLxBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 提升灵犀点主要面板
 *
 * @author
 *
 */
public class LXiulianMainPanel extends JPanel
{
    private static final long serialVersionUID = 8760326700583559912L;
    private JLabel currentPoin;// 当前灵犀等级
    private JLabel zhaohuanshou;// 召唤兽名称
    private JLabel trainingProgress;// 当前修炼进度
    private JLabel needExperience; // 所需经验
    private JLabel currentExperience; // 当前经验
    private JLabel needMoney;// 所需金钱
    private JLabel needQinmi;// 所需亲密
    private JLabel currentQinmi;// 当前亲密
    private PetLxBtn promoteBtn;// 提升修炼点按钮
    private PetLxBtn exchangeBtn;// 一键兑换按钮
    private int numExp;
    private BigDecimal needMoneyNum;//扣出大话币
    private BigDecimal needExperienceNum;//扣除经验
    private BigDecimal needQinmiNum;//扣除亲密
    private ImageIcon icon;
    private ImageIcon iconExp; //修炼进度
    
    public LXiulianMainPanel() {//灵犀修炼  184943010
        this.needMoneyNum = new BigDecimal("3500000");//金钱
        this.needExperienceNum = new BigDecimal("32000000");//经验
        this.needQinmiNum = new BigDecimal("5000");//亲密
        this.setPreferredSize(new Dimension(401, 384));
        this.setOpaque(false);
        this.setLayout(null);
        this.currentPoin = new JLabel("30");
        this.zhaohuanshou = new JLabel("召唤兽");
        this.trainingProgress = new JLabel("瓶颈");
        this.needExperience = new JLabel(this.needExperienceNum + "");
        this.currentExperience = new JLabel("1321321321");
        this.needMoney = new JLabel(this.needMoneyNum + "");
        this.needQinmi = new JLabel(this.needQinmiNum + "");
        this.currentQinmi = new JLabel("8888");
        // 组件设置大小和方位
        if (MyIsif.getStyle().equals("水墨")) {
            this.promoteBtn = new PetLxBtn("inkImg/button1/B22.png", 1, "提升修炼", 8, "");
            this.exchangeBtn = new PetLxBtn("inkImg/button1/B22.png", 1, "一键修炼", 9, "");
            this.zhaohuanshou.setBounds(117, 40, 120, 18);
            this.exchangeBtn.setBounds(75, 337, 99, 24);
            this.promoteBtn.setBounds(235, 337, 99, 24);
        }
        else {
            this.promoteBtn = new PetLxBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "提升修炼", 8);
            this.exchangeBtn = new PetLxBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "一键修炼", 9);
            this.zhaohuanshou.setBounds(70, 40, 220, 18);
            this.exchangeBtn.setBounds(75, 337, 80, 26);
            this.promoteBtn.setBounds(235, 337, 80, 26);
        }
        this.currentPoin.setBounds(310, 40, 45, 16);
        this.trainingProgress.setBounds(200, 73, 150, 17);
        this.needExperience.setBounds(200, 152, 150, 16);
        this.currentExperience.setBounds(200, 183, 150, 16);
        this.needMoney.setBounds(200, 214, 150, 16);
        this.needQinmi.setBounds(200, 276, 150, 16);
        this.currentQinmi.setBounds(200, 307, 150, 16);
        // 组件内容居中
        this.currentPoin.setHorizontalAlignment(0);
        this.trainingProgress.setHorizontalAlignment(0);
        this.needExperience.setHorizontalAlignment(0);
        this.currentExperience.setHorizontalAlignment(0);
        this.needMoney.setHorizontalAlignment(0);
        this.zhaohuanshou.setHorizontalAlignment(2);
        this.needQinmi.setHorizontalAlignment(0);
        this.currentQinmi.setHorizontalAlignment(0);
        // 组件设置字体颜色
        this.currentPoin.setForeground(Color.white);
        this.trainingProgress.setForeground(Color.white);
        this.needExperience.setForeground(Color.white);
        this.currentExperience.setForeground(Color.white);
        this.needMoney.setForeground(Color.white);
        this.zhaohuanshou.setForeground(Color.red);
        this.needQinmi.setForeground(Color.white);
        this.currentQinmi.setForeground(Color.white);
        this.currentPoin.setFont(UIUtils.TEXT_FONT1B);
        this.trainingProgress.setFont(UIUtils.TEXT_FONT1);
        this.needExperience.setFont(UIUtils.TEXT_FONT1);
        this.currentExperience.setFont(UIUtils.TEXT_FONT1);
        this.needMoney.setFont(UIUtils.TEXT_FONT1);
        this.zhaohuanshou.setFont(UIUtils.TEXT_HYK16);
        this.needQinmi.setFont(UIUtils.TEXT_FONT1);
        this.currentQinmi.setFont(UIUtils.TEXT_FONT1);
        // 添加组件
        this.currentPoin.setOpaque(false);
        this.trainingProgress.setOpaque(false);
        this.needExperience.setOpaque(false);
        this.currentExperience.setOpaque(false);
        this.needMoney.setOpaque(false);
        this.exchangeBtn.setOpaque(false);
        this.promoteBtn.setOpaque(false);
        this.zhaohuanshou.setOpaque(false);
        this.needQinmi.setOpaque(false);
        this.currentQinmi.setOpaque(false);
        this.add(this.currentPoin);
        this.add(this.trainingProgress);
        this.add(this.needExperience);
        this.add(this.currentExperience);
        this.add(this.needMoney);
        this.add(this.zhaohuanshou);
        this.add(this.needQinmi);
        this.add(this.currentQinmi);
        this.add(this.exchangeBtn);
        this.add(this.promoteBtn);
    }
    /**
     *  面板获取数据
     * @param
     * @param
     * @param
     * @param
     * @param
     * @param
     * @param
     */
    public void panelGetData(RoleSummoning pet) {
        String lingxi = pet.getLingxi();
        if (StringUtil.isNullOrEmpty(lingxi)) {
            lingxi = "Lx=0&Lv=0&Point=0&Open=11001_0|11002_0|11003_0|11004_0|11005_0|11006_0|11007_0|11026_0|11045_0|11046_0";
        }
        String[] param = lingxi.split("&");
        // 修炼等级
        int xl = Integer.parseInt(param[1].split("=")[1]);
        // 灵犀点数
        int ds = Integer.parseInt(param[2].split("=")[1]);
        if (pet.getSsn().equals("2") || pet.getSsn().equals("3") || pet.getSsn().equals("4")) {
            if (ds == 110) {
                xl = 110;
                this.getTrainingProgress().setFont(new Font("黑体", 0, 14));
                this.getTrainingProgress().setForeground(Color.RED);
            }
        }
        else if (pet.getSsn().equals("6")) {
            if (ds == 60) {
                xl = 60;
                this.getTrainingProgress().setFont(new Font("黑体", 0, 14));
                this.getTrainingProgress().setForeground(Color.RED);
            }
        }
        else if (pet.getSsn().equals("5")) {
            if (ds == 50) {
                xl = 50;
                this.getTrainingProgress().setFont(new Font("黑体", 0, 14));
                this.getTrainingProgress().setForeground(Color.RED);
            }
        }
        else if (ds == 40) {
            xl = 40;
            this.getTrainingProgress().setFont(new Font("黑体", 0, 14));
            this.getTrainingProgress().setForeground(Color.RED);
        }
        // 当前经验值
        this.getCurrentExperience().setText(pet.getExp().toString());
        // 当前修炼进度
        if (pet.getSsn().equals("2") || pet.getSsn().equals("3") || pet.getSsn().equals("4")) {
            this.getTrainingProgress().setText((ds == 110) ? "登峰造极" : (xl + "/" + (ds + 1)));
            this.numExp = (int)((double)xl / ((double)ds + 1.0) * 181.0);
        }
        else if (pet.getSsn().equals("6")) {
            this.getTrainingProgress().setText((ds == 60) ? "登峰造极" : (xl + "/" + (ds + 1)));
            this.numExp = (int)((double)xl / ((double)ds + 1.0) * 181.0);
        }
        else if (pet.getSsn().equals("5")) {
            this.getTrainingProgress().setText((ds == 50) ? "登峰造极" : (xl + "/" + (ds + 1)));
            this.numExp = (int)((double)xl / ((double)ds + 1.0) * 181.0);
        }
        else {
            this.getTrainingProgress().setText((ds == 40) ? "登峰造极" : (xl + "/" + (ds + 1)));
            this.numExp = (int)((double)xl / ((double)ds + 1.0) * 181.0);
        }
        // 当前灵犀点数
        this.getCurrentPoin().setText(ds + "");
        // 召唤兽名称
        this.getZhaohuanshou().setText(pet.getSummoningname());
        // 召唤兽亲密
        this.getCurrentQinmi().setText(pet.getFriendliness() + "");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S265.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 401, 384, this);
            if (this.iconExp == null) {
                this.iconExp = new ImageIcon("img/soaringSkill/exp.png");
            }
            g.drawImage(this.iconExp.getImage(), 193, 71, this.numExp, 19, null);
            Util.drawMoney(g, this.drawCenteredString(g, RoleData.getRoleData().getLoginResult().getGold().toString(), 170, 180, UIUtils.TEXT_FONT1), 258);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S265.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 401, 384, this);
            if (this.iconExp == null) {
                this.iconExp = new ImageIcon("img/soaringSkill/exp.png");
            }
            g.drawImage(this.iconExp.getImage(), 193, 71, this.numExp, 19, null);
            Util.drawMoney(g, this.drawCenteredString(g, RoleData.getRoleData().getLoginResult().getGold().toString(), 170, 180, UIUtils.TEXT_FONT1), 258);
        }
    }
    /**
     * 居中算法
     *
     */
    public int drawCenteredString(Graphics g, String text, int getX, int lonX, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = lonX + (getX - metrics.stringWidth(text)) / 2;
        return x;
    }
    /** 当前天枢点 */
    public JLabel getCurrentPoin() {
        return this.currentPoin;
    }
    
    public void setCurrentPoin(JLabel currentPoin) {
        this.currentPoin = currentPoin;
    }
    
    public JLabel getTrainingProgress() {
        return this.trainingProgress;
    }
    
    public void setTrainingProgress(JLabel trainingProgress) {
        this.trainingProgress = trainingProgress;
    }
    
    public JLabel getNeedExperience() {
        return this.needExperience;
    }
    
    public void setNeedExperience(JLabel needExperience) {
        this.needExperience = needExperience;
    }
    
    public JLabel getCurrentExperience() {
        return this.currentExperience;
    }
    
    public void setCurrentExperience(JLabel currentExperience) {
        this.currentExperience = currentExperience;
    }
    
    public JLabel getNeedMoney() {
        return this.needMoney;
    }
    
    public void setNeedMoney(JLabel needMoney) {
        this.needMoney = needMoney;
    }
    
    public PetLxBtn getPromoteBtn() {
        return this.promoteBtn;
    }
    
    public void setPromoteBtn(PetLxBtn promoteBtn) {
        this.promoteBtn = promoteBtn;
    }
    
    public PetLxBtn getExchangeBtn() {
        return this.exchangeBtn;
    }
    
    public void setExchangeBtn(PetLxBtn exchangeBtn) {
        this.exchangeBtn = exchangeBtn;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public BigDecimal getNeedMoneyNum() {
        return this.needMoneyNum;
    }
    
    public void setNeedMoneyNum(BigDecimal needMoneyNum) {
        this.needMoneyNum = needMoneyNum;
    }
    
    public BigDecimal getNeedExperienceNum() {
        return this.needExperienceNum;
    }
    
    public void setNeedExperienceNum(BigDecimal needExperienceNum) {
        this.needExperienceNum = needExperienceNum;
    }
    
    public JLabel getZhaohuanshou() {
        return this.zhaohuanshou;
    }
    
    public void setZhaohuanshou(JLabel zhaohuanshou) {
        this.zhaohuanshou = zhaohuanshou;
    }
    
    public JLabel getNeedQinmi() {
        return this.needQinmi;
    }
    
    public void setNeedQinmi(JLabel needQinmi) {
        this.needQinmi = needQinmi;
    }
    
    public JLabel getCurrentQinmi() {
        return this.currentQinmi;
    }
    
    public void setCurrentQinmi(JLabel currentQinmi) {
        this.currentQinmi = currentQinmi;
    }
}
