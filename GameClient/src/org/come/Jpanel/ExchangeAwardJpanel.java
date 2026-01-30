package org.come.Jpanel;

import java.awt.Graphics;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.FormsManagement;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.tool.btn.ExchangeCodeBtn;
import javax.swing.JPanel;

public class ExchangeAwardJpanel extends JPanel
{
    private ExchangeCodeBtn codeBtn1;
    private ExchangeCodeBtn codeBtn2;
    private JTextField textCode;
    private JLabel titleJLabel;
    private int type;
    private BigDecimal rgid;
    String xz2;
    String xz3;
    private ImageIcon icon;
    private ImageIcon icon1;

    public ExchangeAwardJpanel() {
        this.xz2 = "";
        this.xz3 = "";
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(534, 145));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (this.textCode = new JTextField()).setBounds(28, 89, 475, 15);
            this.textCode.setFont(UIUtils.TEXT_FONT1);
            this.textCode.setOpaque(false);
            this.textCode.setBorder(BorderFactory.createEmptyBorder());
            this.textCode.setCaretColor(Color.white);
            this.textCode.setForeground(Color.white);
            this.add(this.textCode);
            (this.codeBtn1 = new ExchangeCodeBtn("inkImg/button1/B20.png", 1, "兑 换", this, null)).setBounds(150, 112, 59, 24);
            this.add(this.codeBtn1);
            (this.codeBtn2 = new ExchangeCodeBtn("inkImg/button1/B20.png", 1, "取 消", this, null)).setBounds(300, 112, 59, 24);
            this.add(this.codeBtn2);
            (this.titleJLabel = new JLabel()).setForeground(Color.YELLOW);
            this.titleJLabel.setFont(UIUtils.TEXT_FONT);
            this.titleJLabel.setVerticalTextPosition(0);
            this.titleJLabel.setBounds(200, 10, 134, 20);
            this.add(this.titleJLabel);
        }
        else {
            this.setPreferredSize(new Dimension(534, 145));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (this.textCode = new JTextField()).setBounds(40, 70, 475, 15);
            this.textCode.setFont(UIUtils.TEXT_FONT1);
            this.textCode.setOpaque(false);
            this.textCode.setBorder(BorderFactory.createEmptyBorder());
            this.textCode.setCaretColor(Color.white);
            this.textCode.setForeground(Color.white);
            this.add(this.textCode);
            (this.codeBtn1 = new ExchangeCodeBtn("inkImg/hongmu/6026.png", 1, "兑 换", this)).setBounds(120, 90, 60, 26);
            this.add(this.codeBtn1);
            (this.codeBtn2 = new ExchangeCodeBtn("inkImg/hongmu/6026.png", 1, "取 消", this)).setBounds(270, 90, 60, 26);
            this.add(this.codeBtn2);
            (this.titleJLabel = new JLabel()).setForeground(Color.YELLOW);
            this.titleJLabel.setFont(UIUtils.TEXT_FONT);
            this.titleJLabel.setVerticalTextPosition(0);
            this.titleJLabel.setBounds(150, 10, 134, 20);
            this.add(this.titleJLabel);
        }
    }
    Goodstable good;

    public Goodstable getGood() {
        return good;
    }
    Goodstable good2;

    public Goodstable getGood2() {
        return good2;
    }
    public void use22222(Goodstable good, int type) {
        this.good = good;
        this.type = type;
        // this.aaa222 = type;
        titleJLabel.setText("直接输入数量");
        codeBtn1.setText("确 认");
        xz2 = "不要超过自己承受的上限！";
        xz3="";
        repaint();
        FormsManagement.showForm(73);
    }


    RoleSummoning pet;

    public RoleSummoning getPet() {
        return pet;
    }
    public void use22222(Goodstable good2, RoleSummoning pet, int type) {
        GoodsMouslisten.setaasdf(0);
        this.good2 = good2;
        this.pet = pet;
        this.type = type;
        // this.aaa222 = type;
        titleJLabel.setText("直接输入数量");
        codeBtn1.setText("确 实");
        xz2 = "不要超过自己承受的上限！";
        xz3="";
        repaint();
        FormsManagement.showForm(73);


    }

    /** 更改使用类型 */
    public void use(int type, BigDecimal rgid) {
        this.type = type;
        this.rgid = rgid;
        this.xz2 = "";
        this.xz3 = "";
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));
        int szz = 5;//3种族
        if (configure.getLzjskg() != null) {
            szz = Integer.parseInt(configure.getLzjskg());
        }
        if (MyIsif.getStyle().equals("水墨") ) {
            this.codeBtn1.setBounds(120, 112, 60, 26);
            this.codeBtn2 .setBounds(270, 112, 60, 26);
        }else {
            this.codeBtn1.setBounds(120, 90, 60, 26);
            this.codeBtn2.setBounds(270, 90, 60, 26);
        }

        if (szz == 3) {
            if (this.type == 0) {
                this.titleJLabel.setText("修    改    名    称");
                this.codeBtn1.setText("修 改");
            }
            else if (this.type == 1) {
                this.titleJLabel.setText("修    正    卡");
                this.xz2 = "编号  男人-1,女人-2,男魔-3,女魔-4,男仙-5,女仙-6";
                this.xz3 = "输入格式如:1-2-3即一世男人 二世女人 三世男魔";
                this.codeBtn1.setText("修 正");
            }
            else if (this.type == 3) {
                this.titleJLabel.setText("解    禁    卡");
                this.codeBtn1.setText("解 禁");
                this.xz2 = "输入玩家的角色ID解除对应的被禁言玩家";
                this.xz3 = "不知道ID在客户端左上角查看";
            }
            else if (this.type == 4) {
                this.titleJLabel.setText("解    封    卡");
                this.codeBtn1.setText("解 封");
                this.xz2 = "输入玩家的账号解除对应的被封号玩家";
            }
            else if (this.type == 2) {
                this.titleJLabel.setText("兑    换");
                this.codeBtn1.setText("兑 换");
                this.xz2 = "CTRL是键盘左下角那个键  CTRL+C复制  CTRL+V 粘贴";
                this.xz3 = "   请复制CDK粘贴到此处，点击兑换即可获得对应充值礼包。";
            }
            else if (this.type == 5) {
                this.codeBtn1.setText("招 募");
                this.titleJLabel.setText("你愿意花费多少金钱招募帮手");
                this.xz2 = "注意:开启招募后队伍内每个队员都会获得对应的招募赏";
                this.xz3 = "金(需挑战进度>=挑战的关卡进度)";
            }
            else if (this.type == 888) {
                this.titleJLabel.setText("点卡充值");
                this.codeBtn1.setText("充 值");
                this.xz2 = "输入要充值的玩家ID,注意：写错无法反悔请认真核对！";
            }
            repaint();
            FormsManagement.showForm(73);
        }
        else if (szz == 4) {
            if (this.type == 0) {
                this.titleJLabel.setText("修    改    名    称");
                this.codeBtn1.setText("修 改");
            }
            else if (this.type == 1) {
                this.titleJLabel.setText("修    正    卡");
                this.xz2 = "编号  男人-1,女人-2,男魔-3,女魔-4,男仙-5,女仙-6,男鬼-7,女鬼-8";
                this.xz3 = "输入格式如:1-2-3即一世男人 二世女人 三世男魔";
                this.codeBtn1.setText("修 正");
            }
            else if (this.type == 3) {
                this.titleJLabel.setText("解    禁    卡");
                this.codeBtn1.setText("解 禁");
                this.xz2 = "输入玩家的角色ID解除对应的被禁言玩家";
                this.xz3 = "不知道ID在客户端左上角查看";
            }
            else if (this.type == 4) {
                this.titleJLabel.setText("解    封    卡");
                this.codeBtn1.setText("解 封");
                this.xz2 = "输入玩家的账号解除对应的被封号玩家";
            }
            else if (this.type == 2) {
                this.titleJLabel.setText("兑    换");
                this.codeBtn1.setText("兑 换");
                this.xz2 = "  CTRL是键盘左下角那个键  CTRL+C复制  CTRL+V 粘贴";
                this.xz3 = "  请复制CDK粘贴到此处，点击兑换即可获得对应充值礼包。";
            }
            else if (this.type == 5) {
                this.codeBtn1.setText("招 募");
                this.titleJLabel.setText("你愿意花费多少金钱招募帮手");
                this.xz2 = "注意:开启招募后队伍内每个队员都会获得对应的招募赏";
                this.xz3 = "金(需挑战进度>=挑战的关卡进度)";
            }
            else if (this.type == 888) {
                this.titleJLabel.setText("点卡充值");
                this.codeBtn1.setText("充 值");
                this.xz2 = "输入要充值的玩家ID,注意：写错无法反悔请认真核对！";
            }
            repaint();
            FormsManagement.showForm(73);
        }
        else {
            if (this.type == 0) {
                this.titleJLabel.setText("修    改    名    称");
                this.codeBtn1.setText("修 改");
            }
            else if (this.type == 1) {
                this.titleJLabel.setText("修    正    卡");
                this.xz2 = "编号  男人-1,女人-2,男魔-3,女魔-4,男仙-5,女仙-6,男鬼-7,女鬼-8,男龙-9,女龙-10";
                this.xz3 = "输入格式如:1-2-3即一世男人 二世女人 三世男魔";
                this.codeBtn1.setText("修 正");
            }
            else if (this.type == 3) {
                this.titleJLabel.setText("解    禁    卡");
                this.codeBtn1.setText("解 禁");
                this.xz2 = "输入玩家的角色ID解除对应的被禁言玩家";
                this.xz3 = "不知道ID在客户端左上角查看";
            }
            else if (this.type == 4) {
                this.titleJLabel.setText("解    封    卡");
                this.codeBtn1.setText("解 封");
                this.xz2 = "输入玩家的账号解除对应的被封号玩家";
            }
            else if (this.type == 2) {
                this.titleJLabel.setText("兑    换");
                this.codeBtn1.setText("兑 换");
                this.xz2 = "CTRL是键盘左下角那个键  CTRL+C复制  CTRL+V 粘贴";
                this.xz3 = "   请复制CDK粘贴到此处，点击兑换即可获得对应充值礼包。";
            }
            else if (this.type == 5) {
                this.codeBtn1.setText("招 募");
                this.titleJLabel.setText("你愿意花费多少金钱招募帮手");
                this.xz2 = "注意:开启招募后队伍内每个队员都会获得对应的招募赏";
                this.xz3 = "金(需挑战进度>=挑战的关卡进度)";
            }
            else if (this.type == 888) {
                this.titleJLabel.setText("点卡充值");
                this.codeBtn1.setText("充 值");
                this.xz2 = "输入要充值的玩家ID,注意：写错无法反悔请认真核对！";
            }
            repaint();
            FormsManagement.showForm(73);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(MyIsif.getStyle().equals("水墨")) {


            if(type==999) {//一键使用物品
                if (icon == null)

                    icon = new ImageIcon("inkImg/background1/B235a.png");



                this.setPreferredSize(new Dimension(434, 270));
                this.setLayout(null);
                this.setBackground(UIUtils.Color_BACK);


                textCode.setBounds(68, 70, 475, 15);//输入框

                codeBtn1.setBounds(50, 118, 59, 24);//确认

                codeBtn2.setBounds(150, 118, 59, 24);//取消

                titleJLabel.setBounds(96, 35, 134, 20);//直接输入数量



                g.drawImage(icon.getImage(), 0, 0, 277, 161, this);
                if (xz2 != null) {
                    g.setFont(UIUtils.TEXT_FONT);
                    g.setColor(Color.YELLOW);
                    g.drawString(xz2, 66, 105);//白色字提
                    if (xz3 != null)
                        g.drawString(xz3, 24, 65);
                }



            }
            else {
                if (icon1 == null)
                    icon1 = new ImageIcon("inkImg/background1/B235.png");
                g.drawImage(icon1.getImage(), 0, 0, 534, 145, this);
                if (xz2 != null) {
                    g.setFont(UIUtils.TEXT_FONT);
                    g.setColor(Color.WHITE);
                    g.drawString(xz2, 40, 45);

                }
                if (xz3 != null)
                    g.drawString(xz3, 24, 65);
            }
            // 背景


        }else {
            if(type==999) {	if (icon == null)



                this.setPreferredSize(new Dimension(434, 270));
                this.setLayout(null);
                this.setBackground(UIUtils.Color_BACK);


                textCode.setBounds(33, 58, 475, 15);//输入框

                codeBtn1.setBounds(20, 108, 59, 24);//确认

                codeBtn2.setBounds(95, 108, 59, 24);//取消

                titleJLabel.setBounds(50, 29, 134, 20);//直接输入数量
                icon = new ImageIcon("img/xy2uiimg/单行输入框a.png");
                // 背景
                g.drawImage(icon.getImage(), 0, 0, 167, 162, this);
                if (xz2 != null) {
                    g.setFont(UIUtils.TEXT_FONT);
                    g.setColor(Color.YELLOW);
                    g.drawString(xz2, 20, 95);
                    if (xz3 != null)
                        g.drawString(xz3, 24, 65);
                }
            }else {	if (icon1 == null)
                icon1 = new ImageIcon("img/xy2uiimg/单行输入框.png");
                // 背景
                g.drawImage(icon1.getImage(), 0, 0, 530, 130, this);
                if (xz2 != null) {
                    g.setFont(UIUtils.TEXT_FONT);
                    g.setColor(Color.YELLOW);
                    g.drawString(xz2, 40, 45);
                    if (xz3 != null)
                        g.drawString(xz3, 44, 65);
                }
            }





        }
    }
    public JTextField getTextCode() {
        return textCode;
    }
    public void setTextCode(JTextField textCode) {
        this.textCode = textCode;
    }

    public BigDecimal getRgid() {
        return rgid;
    }

    public void setRgid(BigDecimal rgid) {
        this.rgid = rgid;
    }

}
