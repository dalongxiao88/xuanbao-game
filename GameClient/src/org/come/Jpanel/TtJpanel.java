package org.come.Jpanel;

import java.awt.Graphics;
import org.come.bean.LoginResult;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.AnalysisString;
import org.come.until.CutButtonImage;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import com.tool.btn.TtOperationBtn;
import com.tool.tcpimg.RichLabel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TtJpanel extends JPanel
{
    private JLabel headImgLab;
    private JLabel nameLab;
    private JLabel lvLab;
    private JLabel jfLab;
    private JLabel tjLab;
    private JLabel currRankLab;
    private JLabel lastRankLab;
    private RichLabel sjInfo;
    private TtOperationBtn sjjlBtn;
    private TtOperationBtn fiveTimeBtn;
    private TtOperationBtn threeWinBtn;
    private TtOperationBtn fiveWinBtn;
    private TtOperationBtn jcBtn;
    private TtOperationBtn jfBtn;
    private TtOperationBtn intoGameBtn;
    private ImageIcon icon;
    
    public TtJpanel() {
        this.setBounds(176, 0, 674, 449);
        this.setOpaque(false);
        this.setLayout((LayoutManager)null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 606);
        offBtn.setBounds(637, 10, 25, 25);
        this.add(offBtn);
        this.headImgLab = new JLabel();
        this.nameLab = new JLabel();
        this.lvLab = new JLabel();
        this.jfLab = new JLabel();
        this.tjLab = new JLabel();
        this.currRankLab = new JLabel();
        this.lastRankLab = new JLabel();
        this.sjInfo = new RichLabel();
        this.sjjlBtn = new TtOperationBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNPUTONG, "赛季奖励", UIUtils.TEXT_FONT1, 3, this);
        this.fiveTimeBtn = new TtOperationBtn("inkImg/button/B386.png", 1, 1, this);
        this.threeWinBtn = new TtOperationBtn("inkImg/button/B387.png", 1, 1, this);
        this.fiveWinBtn = new TtOperationBtn("inkImg/button/B388.png", 1, 1, this);
        this.jcBtn = new TtOperationBtn("inkImg/button/B389.png", 1, 5, this);
        this.jfBtn = new TtOperationBtn("inkImg/button/B390.png", 1, 6, this);
        this.intoGameBtn = new TtOperationBtn("inkImg/button/B391.png", 1, 1, this);
        this.headImgLab.setBounds(38, 63, 50, 50);
        this.nameLab.setBounds(152, 55, 120, 30);
        this.lvLab.setBounds(152, 86, 120, 30);
        this.jfLab.setBounds(152, 117, 120, 30);
        this.tjLab.setBounds(352, 55, 120, 30);
        this.currRankLab.setBounds(352, 86, 120, 30);
        this.lastRankLab.setBounds(352, 117, 120, 30);
        this.sjInfo.setBounds(31, 152, 500, 30);
        this.sjjlBtn.setBounds(31, 122, 68, 17);
        this.fiveTimeBtn.setBounds(450, 60, 80, 21);
        this.threeWinBtn.setBounds(450, 91, 80, 21);
        this.fiveWinBtn.setBounds(450, 122, 80, 21);
        this.jcBtn.setBounds(548, 60, 100, 19);
        this.jfBtn.setBounds(548, 91, 100, 19);
        this.intoGameBtn.setBounds(548, 122, 100, 19);
        this.add(this.headImgLab);
        this.add(this.nameLab);
        this.add(this.lvLab);
        this.add(this.jfLab);
        this.add(this.tjLab);
        this.add(this.currRankLab);
        this.add(this.lastRankLab);
        this.add(this.sjInfo);
        this.add(this.sjjlBtn);
        this.add(this.fiveTimeBtn);
        this.add(this.threeWinBtn);
        this.add(this.fiveWinBtn);
        this.add(this.jcBtn);
        this.add(this.jfBtn);
        this.add(this.intoGameBtn);
        this.initData();
    }
    
    public void initData() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (nao.equals("新")) {
            this.sjInfo.setText("#R第一赛季");
            LoginResult data = RoleData.getRoleData().getLoginResult();
            this.headImgLab.setIcon(CutButtonImage.getImage("img/head/s" + getSpeciesId(data.getLocalname()) + "-1.png", 50, 50));
            this.nameLab.setText(data.getRolename());
            this.lvLab.setText(AnalysisString.lvl((int)data.getGrade()));
            this.jfLab.setText("入门0点");
            this.tjLab.setText("0/0");
            this.currRankLab.setText("0");
            this.lastRankLab.setText("未上榜");
        }
        else {
            this.sjInfo.setText("#R第一赛季");
            LoginResult data = RoleData.getRoleData().getLoginResult();
            this.headImgLab.setIcon(CutButtonImage.getImage("img/head/s" + getSpeciesId(data.getLocalname()) + ".png", 50, 50));
            this.nameLab.setText(data.getRolename());
            this.lvLab.setText(AnalysisString.lvl((int)data.getGrade()));
            this.jfLab.setText("入门0点");
            this.tjLab.setText("0/0");
            this.currRankLab.setText("0");
            this.lastRankLab.setText("未上榜");
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/S284.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 674, 449, null);
    }
    
    public static BigDecimal getSpeciesId(String name) {
        if (name.equals("逍遥生")) {
            return new BigDecimal(20001);
        }
        if (name.equals("剑侠客")) {
            return new BigDecimal(20002);
        }
        if (name.equals("猛壮士")) {
            return new BigDecimal(20003);
        }
        if (name.equals("飞燕女")) {
            return new BigDecimal(20004);
        }
        if (name.equals("英女侠")) {
            return new BigDecimal(20005);
        }
        if (name.equals("俏千金")) {
            return new BigDecimal(20006);
        }
        if (name.equals("飞剑侠")) {
            return new BigDecimal(20007);
        }
        if (name.equals("燕山雪")) {
            return new BigDecimal(20008);
        }
        if (name.equals("纯阳子")) {
            return new BigDecimal(20009);
        }
        if (name.equals("红拂女")) {
            return new BigDecimal(20010);
        }
        if (name.equals("神秀生")) {
            return new BigDecimal(20011);
        }
        if (name.equals("玲珑女")) {
            return new BigDecimal(20012);
        }
        if (name.equals("虎头怪")) {
            return new BigDecimal(21001);
        }
        if (name.equals("夺命妖")) {
            return new BigDecimal(21002);
        }
        if (name.equals("巨魔王")) {
            return new BigDecimal(21003);
        }
        if (name.equals("小蛮妖")) {
            return new BigDecimal(21004);
        }
        if (name.equals("骨精灵")) {
            return new BigDecimal(21005);
        }
        if (name.equals("狐美人")) {
            return new BigDecimal(21006);
        }
        if (name.equals("逆天魔")) {
            return new BigDecimal(21007);
        }
        if (name.equals("媚灵狐")) {
            return new BigDecimal(21008);
        }
        if (name.equals("混天魔")) {
            return new BigDecimal(21009);
        }
        if (name.equals("九尾狐")) {
            return new BigDecimal(21010);
        }
        if (name.equals("绝影魔")) {
            return new BigDecimal(21011);
        }
        if (name.equals("霜月灵")) {
            return new BigDecimal(21012);
        }
        if (name.equals("神天兵")) {
            return new BigDecimal(22001);
        }
        if (name.equals("智圣仙")) {
            return new BigDecimal(22002);
        }
        if (name.equals("龙战将")) {
            return new BigDecimal(22003);
        }
        if (name.equals("精灵仙")) {
            return new BigDecimal(22004);
        }
        if (name.equals("舞天姬")) {
            return new BigDecimal(22005);
        }
        if (name.equals("玄剑娥")) {
            return new BigDecimal(22006);
        }
        if (name.equals("武尊神")) {
            return new BigDecimal(22007);
        }
        if (name.equals("玄天姬")) {
            return new BigDecimal(22008);
        }
        if (name.equals("紫薇神")) {
            return new BigDecimal(22009);
        }
        if (name.equals("霓裳仙")) {
            return new BigDecimal(22010);
        }
        if (name.equals("祭剑魂")) {
            return new BigDecimal(23001);
        }
        if (name.equals("猎魂引")) {
            return new BigDecimal(23002);
        }
        if (name.equals("无崖子")) {
            return new BigDecimal(23003);
        }
        if (name.equals("墨衣行")) {
            return new BigDecimal(23004);
        }
        if (name.equals("夜溪灵")) {
            return new BigDecimal(23005);
        }
        if (name.equals("幽梦影")) {
            return new BigDecimal(23006);
        }
        if (name.equals("南冠客")) {
            return new BigDecimal(23007);
        }
        if (name.equals("镜花影")) {
            return new BigDecimal(23008);
        }
        if (name.equals("沧浪君")) {
            return new BigDecimal(24001);
        }
        if (name.equals("龙渊客")) {
            return new BigDecimal(24002);
        }
        if (name.equals("忘忧子")) {
            return new BigDecimal(24003);
        }
        if (name.equals("骊珠儿")) {
            return new BigDecimal(24004);
        }
        if (name.equals("木兰行")) {
            return new BigDecimal(24005);
        }
        if (name.equals("莫解语")) {
            return new BigDecimal(24006);
        }
        if (name.equals("游无极")) {
            return new BigDecimal(24007);
        }
        if (name.equals("临九渊")) {
            return new BigDecimal(24008);
        }
        return new BigDecimal(20001);
    }
}
