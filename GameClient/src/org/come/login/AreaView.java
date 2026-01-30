package org.come.login;

import com.tool.tcp.SpriteFactory;
import org.come.test.Main;
import java.awt.Graphics;
import org.come.until.AnalysisString;
import org.come.until.CutButtonImage;
import org.come.bean.LoginRoleInfo;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.awt.Color;
import org.come.bean.LoginInfo;
import com.tool.tcp.Sprite;
import com.tool.tcp.NewPart;
import org.come.entity.RegionResult;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.come.view.View;

public class AreaView extends View
{
    private final AreaTypeMousisten[] btnTypes;
    private static SpriteBtn returnBt;
    private SpriteBtn btnNewxq;
    private final JLabel labNetwork;
    private final JLabel labSelection;
    private SpriteBtn[] btnSectors;
    private JLabel[] labAreacodes;
    private SpriteBtn[] btnSectorss;
    private SpriteBtn[] btnAreaBottom;
    private JLabel[] labAreacodess;
    private String[] aramBackgroundImagesUrl;
    private String[] aramBtnAreaBottomCoImagesUrl;
    private ImageIcon backGroundImageIcon;
    private Integer aramIndex;
    private final ImageIcon xqbj;
    private final ImageIcon xqbj1;
    private final ImageIcon xqbj2;
    private final ImageIcon xqbj3;
    private final ImageIcon xqht;
    private final ImageIcon xqht2;
    private final ImageIcon xqhtk;
    private final ImageIcon cyjs;
    private final ImageIcon state;
    private final ImageIcon yjhServer;
    private final ImageIcon listServer;
    private final ImageIcon opServer;
    private final ImageIcon newServer;
    private final ImageIcon newServer1;
    private final ImageIcon newServer2;
    private final ImageIcon newServer3;
    private final ImageIcon jrbj;
    private final ImageIcon SearchBj;
    private JLabel labMsgTip;
    private JLabel shurumingcheng;
    private BindAccountView accountView;
    private BindAccountTipView accountTipView;
    long time;
    private List<RegionResult> regionResultList;
    private SpriteBtn fanghui;
    private NewPart part;
    private int dir;
    private RegionResult area;
    private SpriteBtn[] btnDel;
    private JLabel[] commonlyUseds;
    private JLabel[] commonlyUsedsIcon;
    private JLabel[] commonlyUsedsName;
    private JLabel[] commonlyUsedsLvl;
    private JLabel[] commonlyUsedsRaceName;
    private JLabel[] commonlyAreaName;
    private String msg;
    public static Sprite bgbk;
    
    public AreaView(List<RegionResult> regionResultList, LoginInfo loginInfo, LoginJpanel loginJpanel) {
        this.aramBackgroundImagesUrl = new String[] { "B5FE7FCD.png", "BFBE1987.png", "53683A80.png", "C2C6E7CC.png", "F591D605.png" };
        this.aramBtnAreaBottomCoImagesUrl = new String[] { "E8192240.was", "F0DD31DD.was", "32F3693D.was", "39C1C229.was" };
        this.aramIndex = Integer.valueOf(1);
        this.msg = null;
        this.setBounds(0, 0, 1027, 720);
        this.xqbj = new ImageIcon("resource/xinUI/xin/font421_@.png");
        this.xqbj1 = new ImageIcon("resource/xinUI/xin/987-638.png");
        this.xqbj2 = new ImageIcon("resource/xinUI/xin/1004-720.png");
        this.xqbj3 = new ImageIcon("resource/xinUI/xin/1024-269.png");
        this.xqht = new ImageIcon("resource/xinUI/xin/xqht.png");
        this.xqht2 = new ImageIcon("resource/xinUI/xin/xqht2.png");
        this.xqhtk = new ImageIcon("resource/xinUI/xin/xqhtk.png");
        this.yjhServer = new ImageIcon("resource/xinUI/xin/yjhServer.png");
        this.listServer = new ImageIcon("resource/xinUI/xin/listServer.png");
        this.opServer = new ImageIcon("resource/xinUI/xin/B406.png");
        this.newServer = new ImageIcon("resource/xinUI/xin/xc1.png");
        this.newServer1 = new ImageIcon("resource/xinUI/xin/xc2.png");
        this.newServer2 = new ImageIcon("resource/xinUI/xin/xc3.png");
        this.newServer3 = new ImageIcon("resource/xinUI/xin/newServer3.png");
        this.cyjs = new ImageIcon("resource/xinUI/xin/cyjs.png");
        this.jrbj = new ImageIcon("resource/xinUI/xin/jrbjht.png");
        this.state = new ImageIcon("resource/xinUI/xin/state.png");
        this.SearchBj = new ImageIcon("resource/xinUI/xin/175.png");
        this.btnTypes = new AreaTypeMousisten[5];
        int index = 0;
        for (int i = 0; i < 5; ++i) {
            String name = "推荐";
            switch (i) {
                case 0: {
                    name = "推荐";
                    break;
                }
                case 1: {
                    name = "天界";
                    break;
                }
                case 2: {
                    name = "地界";
                    break;
                }
                case 3: {
                    name = "人界";
                    break;
                }
                case 4: {
                    name = "魔界";
                    break;
                }
            }
            (this.btnTypes[i] = new AreaTypeMousisten("resource/xinUI/xin/按钮_服务器选择." + name + ".png", 2, name, loginJpanel, this)).setBounds(37 + i * 127, 78, 124, 48);
            if (index == i) {
                this.btnTypes[i].dianji();
            }
            this.add(this.btnTypes[i]);
        }
        this.drawSectors(regionResultList, loginJpanel);
        (this.labSelection = new JLabel()).setBounds(137, 440, 76, 26);
        this.labSelection.setForeground(Color.black);
        this.labSelection.setFont(new Font("宋体", 0, 16));
        this.labSelection.setHorizontalAlignment(0);
        this.labSelection.setText("推荐");
        (this.labNetwork = new JLabel()).setBounds(380, 440, 63, 26);
        this.labNetwork.setForeground(Color.black);
        this.labNetwork.setFont(new Font("宋体", 0, 16));
        this.labNetwork.setHorizontalAlignment(0);
        this.labNetwork.setText("默认");
        (this.labMsgTip = new JLabel()).setBounds(45, 406, 300, 30);
        this.labMsgTip.setFont(new Font("楷体", 1, 16));
        this.labMsgTip.setForeground(Color.red);
        this.add(this.labMsgTip);
        (this.shurumingcheng = new JLabel()).setBounds(833, 678, 300, 30);
        this.shurumingcheng.setFont(UIUtils.TEXT_FONT15);
        this.shurumingcheng.setForeground(new Color(173, 169, 140));
        this.shurumingcheng.setText("请输入服务器名称搜索");
        this.add(this.shurumingcheng);
        (AreaView.returnBt = new SpriteBtn("resource/xinUI/xin/返回按钮", 10, 673, false)).setBounds(10, 673, 71, 31);
        this.add(AreaView.returnBt);
        this.addCommonlyUsedRoles(loginJpanel, loginInfo);
    }
    
    public void addCommonlyUsedRoles(LoginJpanel loginJpanel, LoginInfo loginInfo) {
        if (this.commonlyUseds != null) {
            for (int i = 0; i < this.commonlyUseds.length; ++i) {
                this.remove(this.btnDel[i]);
                this.remove(this.commonlyUseds[i]);
                this.remove(this.commonlyUsedsIcon[i]);
                this.remove(this.commonlyUsedsName[i]);
                this.remove(this.commonlyUsedsLvl[i]);
                this.remove(this.commonlyUsedsRaceName[i]);
                this.remove(this.commonlyAreaName[i]);
            }
            this.commonlyUseds = null;
            this.commonlyUsedsIcon = null;
            this.commonlyUsedsName = null;
            this.commonlyUsedsLvl = null;
            this.commonlyUsedsRaceName = null;
            this.commonlyAreaName = null;
            this.btnDel = null;
        }
        List<LoginRoleInfo> roleInfos = loginInfo.getLoginRoleInfos();
        if (roleInfos != null && roleInfos.size() > 0) {
            this.commonlyUseds = new JLabel[roleInfos.size()];
            this.commonlyUsedsIcon = new JLabel[roleInfos.size()];
            this.commonlyUsedsName = new JLabel[roleInfos.size()];
            this.commonlyUsedsLvl = new JLabel[roleInfos.size()];
            this.commonlyUsedsRaceName = new JLabel[roleInfos.size()];
            this.commonlyAreaName = new JLabel[roleInfos.size()];
            this.btnDel = new SpriteBtn[roleInfos.size()];
            for (int j = 0; j < roleInfos.size() && j < 6; ++j) {
                LoginRoleInfo loginRoleInfo = (LoginRoleInfo)roleInfos.get(j);
                if (loginRoleInfo.getRaceId() != null && loginRoleInfo.getRoleName() != null) {
                    int y = j * 90;
                    (this.commonlyUsedsIcon[j] = new JLabel()).setBounds(808, 140 + y, 60, 60);
                    this.commonlyUsedsIcon[j].setIcon(CutButtonImage.getImage("img/head/s" + loginRoleInfo.getRaceId() + ".png", 60, 60));
                    this.add(this.commonlyUsedsIcon[j]);
                    (this.commonlyUsedsName[j] = new JLabel(loginRoleInfo.getRoleName())).setBounds(875, 140 + y, 180, 24);
                    this.commonlyUsedsName[j].setForeground(Color.BLACK);
                    this.commonlyUsedsName[j].setFont(UIUtils.TEXT_FONT89);
                    this.add(this.commonlyUsedsName[j]);
                    (this.commonlyUsedsLvl[j] = new JLabel(AnalysisString.lvl((int)loginRoleInfo.getRoleLvl()) + "级")).setBounds(875, 160 + y, 90, 24);
                    this.commonlyUsedsLvl[j].setForeground(Color.BLACK);
                    this.commonlyUsedsLvl[j].setFont(UIUtils.TEXT_FONT87);
                    this.add(this.commonlyUsedsLvl[j]);
                    (this.commonlyUsedsRaceName[j] = new JLabel(loginRoleInfo.getRaceName())).setBounds(955, 160 + y, 90, 24);
                    this.commonlyUsedsRaceName[j].setForeground(Color.BLACK);
                    this.commonlyUsedsRaceName[j].setFont(UIUtils.TEXT_FONT87);
                    this.add(this.commonlyUsedsRaceName[j]);
                    (this.commonlyAreaName[j] = new JLabel(loginRoleInfo.getAreaName())).setBounds(925, 180 + y, 90, 24);
                    this.commonlyAreaName[j].setForeground(Color.BLACK);
                    this.commonlyAreaName[j].setFont(UIUtils.TEXT_FONT90);
                    this.add(this.commonlyAreaName[j]);
                    (this.btnDel[j] = new SpriteBtn("resource/NewUi/X", 180, 0, false)).setBounds(180, 0, 16, 23);
                    this.btnDel[j].addMouseListener(new AreaMouslisten(this.btnDel[j], loginJpanel, loginRoleInfo, 1));
                    (this.commonlyUseds[j] = new JLabel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            if (AreaView.this.btnDel != null) {
                                for (int i = 0; i < AreaView.this.btnDel.length; ++i) {
                                    if (AreaView.this.btnDel[i] != null) {
                                        AreaView.this.btnDel[i].draw(g);
                                    }
                                }
                            }
                        }
                    }).add(this.btnDel[j]);
                    this.commonlyUseds[j].setIcon(new ImageIcon("resource/NewUi/常用角色.png"));
                    this.commonlyUseds[j].setBounds(800, 134 + y, 201, 84);
                    this.commonlyUseds[j].addMouseListener(new AreaMouslisten(null, loginJpanel, loginRoleInfo, 0));
                    this.add(this.commonlyUseds[j]);
                }
            }
        }
    }
    
    public void delCommonlyUsedRoles(LoginJpanel loginJpanel, LoginRoleInfo loginRoleInfo) {
        LoginInfo loginInfo = Main.frame.getLoginInfo();
        loginInfo.delCommonlyUsedRoles(loginRoleInfo);
        this.addCommonlyUsedRoles(loginJpanel, loginInfo);
    }
    
    public void drawSectors(List<RegionResult> regionResultList, LoginJpanel loginJpanel) {
        if (this.btnSectors != null) {
            for (int i = 0; i < this.btnSectors.length; ++i) {
                this.remove(this.btnSectors[i]);
                this.remove(this.labAreacodes[i]);
                this.remove(this.btnAreaBottom[i]);
                this.remove(this.btnSectors[i]);
            }
        }
        this.btnSectors = new SpriteBtn[regionResultList.size()];
        this.labAreacodes = new JLabel[this.btnSectors.length];
        this.btnSectorss = new SpriteBtn[regionResultList.size()];
        this.labAreacodess = new JLabel[this.btnSectorss.length];
        this.btnAreaBottom = new SpriteBtn[regionResultList.size()];
        this.area = (RegionResult)regionResultList.get(0);
        for (int i = 0; i < this.btnSectors.length; ++i) {
            int x = i % 6 * 125;
            int y = i / 6 * 50;
            if (((RegionResult)regionResultList.get(i)).getIfNew().equals("3")) {
                this.btnSectors[i] = new SpriteBtn("resource/xinUI/按钮.was", 32 + x, 335 + y, false);
                (this.btnNewxq = new SpriteBtn("resource/xinUI/regions-region-icon.was", 145 + x, 340 + y, false)).setBounds(38 + x, 340 + y, 100, 100);
                this.add(this.btnNewxq);
            }
            else if (((RegionResult)regionResultList.get(i)).getIfNew().equals("2")) {
                this.btnSectors[i] = new SpriteBtn("resource/xinUI/按钮1", 32 + x, 335 + y, false);
            }
            else {
                this.btnSectors[i] = new SpriteBtn("resource/xinUI/按钮1", 32 + x, 335 + y, false);
            }
            this.btnSectors[i].setBounds(32 + x, 335 + y, 120, 40);
            this.btnSectors[i].addMouseListener(new AreaMouslisten(((RegionResult)regionResultList.get(i)).getRA_ID(), this.btnSectors[i], loginJpanel, (RegionResult)regionResultList.get(i)));
            this.add(this.btnSectors[i]);
            (this.labAreacodes[i] = new JLabel()).setForeground(Color.black);
            this.labAreacodes[i].setFont(new Font("楷体", 0, 20));
            this.labAreacodes[i].setText(((RegionResult)regionResultList.get(i)).getRA_NAME());
            this.labAreacodes[i].setHorizontalAlignment(0);
            this.labAreacodes[i].setBounds(52 + x, 337 + y, 80, 35);
            this.add(this.labAreacodes[i]);
        }
    }
    
    public void changeBackGroundImageIcon(Integer index) {
        this.backGroundImageIcon = new ImageIcon("resource/loginUI/" + this.aramBackgroundImagesUrl[(int)index]);
        this.aramIndex = index;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.xqbj.getImage(), 0, 0, 1024, 720, null);
        g.drawImage(this.xqbj1.getImage(), 19, 0, 987, 638, null);
        this.time += 12L;
        g.drawImage(this.xqbj3.getImage(), 0, 449, 1024, 269, null);
        for (int i = 0; i < this.btnSectors.length; ++i) {
            this.btnSectors[i].draw(g);
        }
        this.btnNewxq.draw(g);
        g.drawImage(this.jrbj.getImage(), 190, 583, 560, 37, null);
        if (AreaView.bgbk != null) {
            AreaView.bgbk.updateToTime(this.time, 0);
            AreaView.bgbk.draw(g, 0, 170);
        }
        AreaView.returnBt.draw(g);
        g.drawImage(this.xqht.getImage(), 29, 127, 758, 7, null);
        g.drawImage(this.xqht.getImage(), 29, 127, 758, 7, null);
        g.drawImage(this.xqht2.getImage(), 35, 210, 751, 20, null);
        g.drawImage(this.xqht2.getImage(), 35, 210, 751, 20, null);
        g.drawImage(this.xqhtk.getImage(), 34, 135, 746, 72, null);
        g.drawImage(this.yjhServer.getImage(), 35, 135, 102, 25, null);
        g.drawImage(this.listServer.getImage(), 35, 213, 88, 25, null);
        g.drawImage(this.opServer.getImage(), 704, 136, 69, 24, null);
        g.drawImage(this.newServer3.getImage(), 35, 239, 244, 95, null);
        g.drawImage(this.newServer3.getImage(), 285, 239, 244, 95, null);
        g.drawImage(this.newServer3.getImage(), 535, 239, 244, 95, null);
        g.drawImage(this.newServer.getImage(), 39, 243, 235, 86, null);
        g.drawImage(this.newServer1.getImage(), 289, 243, 235, 86, null);
        g.drawImage(this.newServer2.getImage(), 539, 243, 235, 86, null);
        g.drawImage(this.cyjs.getImage(), 862, 102, 81, 22, null);
        g.drawImage(this.state.getImage(), 416, 685, 370, 14, null);
        g.drawImage(this.SearchBj.getImage(), 821, 677, 188, 30, null);
    }
    
    public BindAccountView getAccountView() {
        return this.accountView;
    }
    
    public void setAccountView(BindAccountView accountView) {
        this.accountView = accountView;
    }
    
    public BindAccountTipView getAccountTipView() {
        return this.accountTipView;
    }
    
    public void setAccountTipView(BindAccountTipView accountTipView) {
        this.accountTipView = accountTipView;
    }
    
    public JLabel getLabMsgTip() {
        return this.labMsgTip;
    }
    
    public void setLabMsgTip(JLabel labMsgTip) {
        this.labMsgTip = labMsgTip;
    }
    
    public List<RegionResult> getRegionResultList() {
        return this.regionResultList;
    }
    
    public void setRegionResultList(List<RegionResult> regionResultList) {
        this.regionResultList = regionResultList;
    }
    
    public void setArea(RegionResult area) {
        this.area = area;
    }
    
    public RegionResult getArea() {
        return this.area;
    }
    
    public JLabel getLabNetwork() {
        return this.labNetwork;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public AreaTypeMousisten[] getBtnTypes() {
        return this.btnTypes;
    }
    
    static {
        AreaView.bgbk = SpriteFactory.VloadSprite("resource/xinUI/xin/bgbk", null);
    }
}
