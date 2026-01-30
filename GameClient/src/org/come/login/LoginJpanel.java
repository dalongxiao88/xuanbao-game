package org.come.login;

import javax.swing.SwingUtilities;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.File;

import org.come.bean.LoginResult;
import org.come.until.ControlNpcXmlUntil;

import java.awt.Point;

import org.come.test.Main;

import java.awt.MouseInfo;
import java.util.Map;

import org.come.bean.ConfigureBean;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.entity.RoleTableNew;

import java.io.IOException;

import org.come.until.AESUtil;
import org.come.until.CreateTextUtil;
import org.come.until.GsonUtil;
import com.updateNew.MyIsif;
import com.tool.tcp.Sprite;
import org.come.until.MessagrFlagUntil;

import java.awt.Graphics;

import org.come.until.Util;

import java.awt.Dimension;
import java.util.ArrayList;

import javafx.embed.swing.JFXPanel;
import org.come.bean.PathPoint;
import org.come.entity.RegionResult;
import org.come.bean.LoginRoleInfo;
import org.come.bean.LoginInfo;
import com.tool.tcpimg.LoginTipBox;

import java.util.List;
import javax.swing.JLabel;

import com.tool.Stall.Stall;
import org.come.entity.ServerInfo;

import java.math.BigDecimal;

import org.come.entity.RoleTableList;

import javax.swing.JPanel;

public class LoginJpanel extends JPanel {
    private MainView mainView;
    private LoginView loginView;
    private AreaView areaView;
    private RoleView roleView;
    private RaceView raceView;
    private CreateView createView;
    private int ViewId;
    private RoleTableList roleArr;
    private BigDecimal user_id;
    private String username;
    private String userpwd;
    private int Selected;
    private String login_uid;
    private String ra_id;
    public static ServerInfo serverInfo;
    private String msg;
    private Stall stall;
    private JLabel mouse;
    boolean isBox;
    private List<LoginTipBox> SystemPrompt;
    private LoginInfo loginInfo;
    private LoginRoleInfo roleInfo;
    private Integer mainY;
    private Boolean interfaceScrolling;
    private Boolean loginViewInterfaceScrolling;
    private Boolean aramViewInterfaceScrolling;
    private Boolean ZhuViewInterfaceScrolling;
    public List<RegionResult> regionResultList;
    public static LoginInfo loginInfo1;
    private PathPoint pathPoint;
    private JFXPanel fxPanel;



    public static int port =7100;
    public static String Webport ="8083";


    public LoginJpanel(LoginInfo loginInfo) {
        this.isBox = false;
        this.SystemPrompt = new ArrayList<>();
        this.mainY = 0;
        this.interfaceScrolling = Boolean.FALSE;
        this.loginViewInterfaceScrolling = Boolean.FALSE;
        this.aramViewInterfaceScrolling = Boolean.FALSE;
        this.ZhuViewInterfaceScrolling = Boolean.FALSE;
        this.regionResultList = new ArrayList<>();
        this.pathPoint = new PathPoint(0, 0);
        LoginJpanel.loginInfo1 = loginInfo;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1016, 710));
        List<RegionResult> regionResultList = new ArrayList<>();
        RegionResult reg1 = new RegionResult();
        reg1.setRA_ID(new BigDecimal("100"));
        reg1.setRA_NAME("天界");
        reg1.setRA_NAME("本地测试");
        reg1.setIp("116.211.150.175");
        reg1.setPort(7101);
        reg1.setDowport("8083");
        reg1.setIfNew("3");
        Util.regionResultList.add(reg1);
//        reg1 = new RegionResult();
//        reg1.setRA_ID(new BigDecimal("100"));
//        reg1.setRA_NAME("天界");
//        reg1.setRA_NAME("大唐西游");
//        reg1.setIp("81.70.45.106");
//        reg1.setPort(7100);
//        reg1.setDowport("8083");
//        reg1.setIfNew("3");
//        Util.regionResultList.add(reg1);


//        reg1 = new RegionResult();
//        reg1.setRA_ID(new BigDecimal("100"));
//        reg1.setRA_NAME("天界");
//        reg1.setRA_NAME("无差别");
//        reg1.setIp("106.54.162.58");
//        reg1.setPort(7250);
//        reg1.setDowport("4089");
//        reg1.setIfNew("3");
//        Util.regionResultList.add(reg1);


        try {
            this.mouse = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (LoginJpanel.this.isBox) {
                        int chatY = this.getHeight() / 2 - 50;
                        g.translate(0, chatY);
                        for (int i = LoginJpanel.this.SystemPrompt.size() - 1; i >= 0; --i) {
                            LoginTipBox chatPanel = LoginJpanel.this.SystemPrompt.get(i);
                            if (chatPanel.IsTime()) {
                                g.translate(0, -chatPanel.getHeight());
                                chatY -= chatPanel.getHeight();
                                chatPanel.paint(g);
                            } else {
                                LoginJpanel.this.SystemPrompt.remove(i);
                            }
                        }
                        g.translate(0, -chatY);
                        if (LoginJpanel.this.SystemPrompt.size() == 0) {
                            LoginJpanel.this.isBox = false;
                        }
                    }
                    Sprite mouse = MessagrFlagUntil.getMouse();
                    if (mouse != null) {
                        PathPoint point = LoginJpanel.this.mousepath();
                        mouse.updateToTime(System.currentTimeMillis(), 0);
                        mouse.draw(g, point.getX(), point.getY());
                    }
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (MyIsif.isVideo) {
            this.createVideoView();
            this.framechange(5, Util.regionResultList, loginInfo);
        } else {
            this.framechange(1, Util.regionResultList, loginInfo);
        }
    }

    public static void LoadServerInfo() throws IOException {
        System.out.println("The method received in the request-line is known by the origin server supported by the target resource.");
        setServerInfo(GsonUtil.getGsonUtil().getgson().fromJson(AESUtil.AESJDKDncode(CreateTextUtil.getContent("resource\\other\\server.json"), ">LA~h4FNKPMJW099vd0999"), ServerInfo.class));
    }

    public void addPrompt(String text) {
        if (text != null) {
            this.SystemPrompt.add(new LoginTipBox(text));
            this.isBox = true;
        }
    }

    public void intoGame() {
        if (this.roleArr == null) {
            return;
        }
        RoleTableNew tableNew = null;
        if (this.Selected >= this.roleArr.getRoleList().size()) {
            if (this.roleArr.getRoleList().size() != 0) {
                tableNew = this.roleArr.getRoleList().get(0);
            }
        } else {
            tableNew = this.roleArr.getRoleList().get(this.Selected);
        }
        if (tableNew == null) {
            return;
        }
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));
        if (configure.getSfdkms().equals("1") && tableNew.getGameTimeRemaining() != null && (int) tableNew.getGameTimeRemaining() <= 0) {
            return;
        }
        String sendmes = Agreement.getAgreement().enterGameAgreement(tableNew.getRole_id().toString());
        System.out.println("111" + sendmes);
        SendMessageUntil.toServer(sendmes);
    }

    public PathPoint mousepath() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point p2 = Main.frame.getLocation();
        this.pathPoint.setX((int) (p.getX() - p2.getX() - 4.0));
        this.pathPoint.setY((int) (p.getY() - p2.getY() - 26.0));
        return this.pathPoint;
    }

    public void createRole() {
        if (this.roleArr.getRoleList().size() >= 5) {
            this.createView.setMsg("你角色已经有5个了");
            return;
        }
        String roleName = this.createView.getTextAccount().getText();
        if (!Util.special(roleName)) {
            this.createView.setMsg("角色名字不能包含特殊字符!!");
            return;
        }
        if (!Util.isIllegal(roleName)) {
            this.createView.setMsg("名称中包含非法字符！！");
            return;
        }
        if (roleName.equals("")) {
            this.createView.setMsg("你没有输入名称");
            return;
        }
        for (int i = 0; i < this.roleArr.getRoleList().size(); ++i) {
            if (roleName.equals(this.roleArr.getRoleList().get(i).getRolename())) {
                this.createView.setMsg("你名称已重复");
                return;
            }
        }
        int size = 0;
        char[] nz = roleName.toCharArray();
        for (int j = 0; j < nz.length; ++j) {
            String a = nz[j] + "";
            if (a.getBytes().length == 1) {
                ++size;
            } else {
                size += 2;
            }
        }
        if (size > 14) {
            this.createView.setMsg("角色名字超长了!!！");
            return;
        }
        this.createView.setMsg(null);
        BigDecimal SpeciesId = getSpeciesId(this.createView.SelectedRole());
        ControlNpcXmlUntil.setSpeciesId(SpeciesId.toString());
        ControlNpcXmlUntil.GetXmlPath("roleState.xml");
        LoginResult loginResult = ControlNpcXmlUntil.getLoginResult();
        loginResult.setUser_id(new BigDecimal(this.login_uid));
        loginResult.setSpecies_id(SpeciesId);
        loginResult.setRolename(roleName);
//        loginResult.setTitle("御武盟小虾米");
        loginResult.setTitle(Util.regionResultList.get(0)!=null?Util.regionResultList.get(0).getRA_NAME()+"一员":"御武盟小虾米");
        loginResult.setServerMeString(this.ra_id);
        String serverMes = Agreement.getAgreement().createRoleAgreement(GsonUtil.getGsonUtil().getgson().toJson(loginResult));
        SendMessageUntil.toServer(serverMes);
    }

    public void loginSelected(int i) {
        this.Selected = i;
        this.roleView.xz(this.roleArr.getRoleList().get(i), i);
    }

    public void loginSuccess(RoleTableList roleArr) {
        this.roleArr = roleArr;
        if (this.roleArr.getRoleList().size() != 0) {
            this.user_id = this.roleArr.getRoleList().get(0).getUser_id();
            if (this.roleArr.getRoleList().get(0).getGrade() == null || this.roleArr.getRoleList().get(0).getRolename() == null) {
                this.roleArr.getRoleList().remove(0);
            }
        }
        this.Selected = 0;
        this.framechange(2, null, null);
    }

    public void raceSelected(int i) {
        quxiao(this.raceView.getLeis(), -1);
        if (this.createView == null) {
            this.createView = new CreateView(this, i);
        } else {
            this.createView.xzRace(i, this);
            this.createView.xzRole(0);
        }
        this.framechange(4, null, null);
    }

    public void framechange(int id, List<RegionResult> regionResultList, LoginInfo loginInfo) {
        if (id == -1) {
            if (this.ViewId == 2) {
                this.framechange(1, null, null);
            } else if (this.ViewId == 3) {
                this.framechange(2, null, null);
            } else if (this.ViewId == 4) {
                this.framechange(3, null, null);
            }
            return;
        } else {
            this.ViewId = id;
            if (this.ViewId == 5) {
                if (this.mainView == null) {
                    this.mainView = new MainView(this);
                } else {
                    this.remove(this.mainView);
                }
                if (this.loginView != null) {
                    this.remove(this.loginView);
                    this.loginView = null;
                }
                if (this.areaView != null) {
                    this.remove(this.areaView);
                    this.areaView = null;
                }
                if (this.roleView != null) {
                    this.remove(this.roleView);
                }
                if (this.raceView != null) {
                    this.remove(this.raceView);
                }
                if (this.createView != null) {
                    this.remove(this.createView);
                }
                this.add(this.mainView);
            }
            if (this.ViewId == 0) {
                if (this.loginView == null) {
                    this.loginView = new LoginView(this);
                } else {
                    this.remove(this.loginView);
                }
                if (this.areaView != null) {
                    this.remove(this.areaView);
                }
                if (this.roleView != null) {
                    this.remove(this.roleView);
                }
                if (this.raceView != null) {
                    this.remove(this.raceView);
                }
                if (this.createView != null) {
                    this.remove(this.createView);
                }
                this.add(this.loginView);
            } else if (this.ViewId == 1) {
                if (this.areaView == null) {
                    this.areaView = new AreaView(Util.regionResultList, loginInfo, this);
                } else {
                    this.remove(this.areaView);
                }
                if (this.loginView != null) {
                    this.remove(this.loginView);
                }
                if (this.roleView != null) {
                    this.remove(this.roleView);
                }
                if (this.raceView != null) {
                    this.remove(this.raceView);
                }
                if (this.createView != null) {
                    this.remove(this.createView);
                }
                if (this.mainView != null) {
                    this.fxPanel.setVisible(false);
                    this.fxPanel = null;
                    this.remove(this.mainView);
                    this.roleView = null;
                }
                this.add(this.areaView);
            } else if (this.ViewId == 2) {
                if (this.roleView == null) {
                    this.roleView = new RoleView(this);
                } else {
                    this.remove(this.roleView);
                    this.roleView.sx(this.roleArr, this);
                }
                if (this.loginView != null) {
                    this.remove(this.loginView);
                }
                if (this.areaView != null) {
                    this.remove(this.areaView);
                }
                if (this.raceView != null) {
                    this.remove(this.raceView);
                }
                if (this.createView != null) {
                    this.remove(this.createView);
                }
                this.add(this.roleView);
            } else if (this.ViewId == 3) {
                if (this.raceView == null) {
                    this.raceView = new RaceView(this);
                } else {
                    this.remove(this.raceView);
                }
                if (this.loginView != null) {
                    this.remove(this.loginView);
                }
                if (this.areaView != null) {
                    this.remove(this.areaView);
                }
                if (this.roleView != null) {
                    this.remove(this.roleView);
                }
                if (this.createView != null) {
                    this.remove(this.createView);
                }
                this.add(this.raceView);
            } else if (this.ViewId == 4) {
                if (this.createView == null) {
                    this.createView = new CreateView(this);
                } else {
                    this.remove(this.createView);
                }
                if (this.loginView != null) {
                    this.remove(this.loginView);
                }
                if (this.areaView != null) {
                    this.remove(this.areaView);
                }
                if (this.roleView != null) {
                    this.remove(this.roleView);
                }
                if (this.raceView != null) {
                    this.remove(this.raceView);
                }
                this.add(this.createView);
            }
            return;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.mainView != null && this.fxPanel != null) {
            this.setComponentZOrder(this.fxPanel, 1);
            this.setComponentZOrder(this.mainView, 0);
        }
        try {
            if (this.ViewId == 0 && this.loginView.getRegisterView() != null && this.loginView.getRegisterView().isVisible()) {
                Thread.sleep(100L);
            } else {
                Thread.sleep(20L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.repaint();
    }

    public LoginView getLoginView() {
        return this.loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public AreaView getAreaView() {
        return this.areaView;
    }

    public void setAreaView(AreaView areaView) {
        this.areaView = areaView;
    }

    public RoleView getRoleView() {
        return this.roleView;
    }

    public void setRoleView(RoleView roleView) {
        this.roleView = roleView;
    }

    public RaceView getRaceView() {
        return this.raceView;
    }

    public void setRaceView(RaceView raceView) {
        this.raceView = raceView;
    }

    public CreateView getCreateView() {
        return this.createView;
    }

    public void setCreateView(CreateView createView) {
        this.createView = createView;
    }

    public RoleTableList getRoleArr() {
        return this.roleArr;
    }

    public void setRoleArr(RoleTableList roleArr) {
        this.roleArr = roleArr;
    }

    public String getLogin_uid() {
        return this.login_uid;
    }

    public void setLogin_uid(String login_uid) {
        this.login_uid = login_uid;
    }

    public String getRa_id() {
        return this.ra_id;
    }

    public void setRa_id(String ra_id) {
        this.ra_id = ra_id;
    }

    public String getUserpwd() {
        return this.userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public static ServerInfo getServerInfo() {
        return LoginJpanel.serverInfo;
    }

    public static void setServerInfo(ServerInfo serverInfo) {
        LoginJpanel.serverInfo = serverInfo;
    }

    public static void quxiao(SpriteBtn[] btns, int zhi) {
        if (btns != null) {
            for (int i = 0; i < btns.length; ++i) {
                if (btns[i] != null) {
                    if (i == zhi) {
                        btns[i].btn(2);
                    } else {
                        btns[i].btn(0);
                    }
                }
            }
        }
    }

    public static String leipath(int i) {
        if (i == 0) {
            return "人";
        }
        if (i == 1) {
            return "魔";
        }
        if (i == 2) {
            return "仙";
        }
        if (i == 3) {
            return "鬼";
        }
        return "龙";
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
        return new BigDecimal(20001);
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MainView getMainView() {
        return this.mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public int getViewId() {
        return this.ViewId;
    }

    public void setViewId(int viewId) {
        this.ViewId = viewId;
    }

    public BigDecimal getUser_id() {
        return this.user_id;
    }

    public void setUser_id(BigDecimal user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSelected() {
        return this.Selected;
    }

    public void setSelected(int selected) {
        this.Selected = selected;
    }

    public Stall getStall() {
        return this.stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    public Integer getMainY() {
        return this.mainY;
    }

    public void setMainY(Integer mainY) {
        this.mainY = mainY;
    }

    public Boolean getInterfaceScrolling() {
        return this.interfaceScrolling;
    }

    public void setInterfaceScrolling(Boolean interfaceScrolling) {
        this.interfaceScrolling = interfaceScrolling;
    }

    public Boolean getLoginViewInterfaceScrolling() {
        return this.loginViewInterfaceScrolling;
    }

    public void setLoginViewInterfaceScrolling(Boolean loginViewInterfaceScrolling) {
        this.loginViewInterfaceScrolling = loginViewInterfaceScrolling;
    }

    public Boolean getAramViewInterfaceScrolling() {
        return this.aramViewInterfaceScrolling;
    }

    public void setAramViewInterfaceScrolling(Boolean aramViewInterfaceScrolling) {
        this.aramViewInterfaceScrolling = aramViewInterfaceScrolling;
    }

    public Boolean getZhuViewInterfaceScrolling() {
        return this.ZhuViewInterfaceScrolling;
    }

    public void setZhuViewInterfaceScrolling(Boolean zhuViewInterfaceScrolling) {
        this.ZhuViewInterfaceScrolling = zhuViewInterfaceScrolling;
    }

    public List<RegionResult> getRegionResultList() {
        return this.regionResultList;
    }

    public void setRegionResultList(List<RegionResult> regionResultList) {
        this.regionResultList = regionResultList;
    }

    public LoginInfo getLoginInfo() {
        return this.loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public void setRoleInfo(LoginRoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    private void createVideoView() {
        (this.fxPanel = new JFXPanel()).setBounds(-300, 0, 1600, 720);
        this.add(this.fxPanel);

        // 定义可用的动画素材数组
        String[] videoFiles = {
                "resource/map/99999.map",
                "resource/map/99998.map",
                "resource/map/99997.map",
                "resource/map/99996.map",
                "resource/map/99995.map"
        };

        // 随机选择一个动画素材
        int randomIndex = (int) (Math.random() * videoFiles.length);
        String selectedVideo = videoFiles[randomIndex];

        SwingUtilities.invokeLater(()/*  */ -> {
            Media media = new Media(new File(selectedVideo).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(-1);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaView.setFitWidth(1600.0);
            mediaView.setFitHeight(720.0);
            BorderPane root = new BorderPane();
            root.setCenter(mediaView);
            Scene scene = new Scene(root);
            this.fxPanel.setScene(scene);
            mediaPlayer.play();
            return;
        });
    }
}
