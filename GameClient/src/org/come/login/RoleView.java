package org.come.login;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.AnalysisString;
import com.tool.tcpimg.UIUtils;
import org.come.until.CutButtonImage;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.entity.RoleTableList;
import com.tool.tcp.SpriteFactory;
import java.util.ArrayList;
import com.tool.tcp.Sprite;
import com.tool.tcp.NewPart;
import org.come.entity.RoleTableNew;
import java.util.List;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import org.come.view.View;

public class RoleView extends View
{
    SpriteBtn fanghui;
    private ImageIcon rolebeijing;
    private final ImageIcon dise;
    private final ImageIcon dise2;
    private final ImageIcon btnCreateRoleBg;
    private SpriteBtn btnEntergame;
    private SpriteBtn btnCreateRole;
    private SpriteBtn[] rolebtn;
    public String ShowRoleNameAndGrade;
    public String ShowRoleGrade;
    public String Grade;
    public String sex;
    public String race;
    public ImageIcon roleImageIcon;
    private JLabel[] showRoleIcon;
    private JLabel[] showRoleNameAndGrade;
    private JLabel[] showRoleGrade;
    private JLabel[] showracename;
    public Color nameAndGradeColor;
    private List<JLabel> roles;
    private RoleTableNew roleTableNew;
    private NewPart part;
    private long time;
    private final Sprite tcp1;
    
    public RoleTableNew getRoleTableNew() {
        return this.roleTableNew;
    }
    
    public RoleView(LoginJpanel loginJpanel) {
        this.roles = new ArrayList<>();
        this.roleTableNew = null;
        this.tcp1 = SpriteFactory.VloadSprite("resource/xinUI/xin/xq", null);
        this.setBounds(0, 0, 1027, 720);
        this.rolebeijing = new ImageIcon("resource/xinUI/xin/rolebeijing.png");
        this.dise = new ImageIcon("resource/xinUI/xin/dise.png");
        this.dise2 = new ImageIcon("resource/xinUI/xin/dise2.png");
        this.btnCreateRoleBg = new ImageIcon("resource/xinUI/xin/187.png");
        (this.fanghui = new SpriteBtn("resource/xinUI/xin/返回按钮", 5, 685, false)).setBounds(5, 685, 60, 30);
        this.fanghui.addMouseListener(new RoleMouslisten(20, this.fanghui, loginJpanel));
        this.add(this.fanghui);
        (this.btnEntergame = new SpriteBtn("resource/xinUI/进入游戏_按钮", 416, 523, false)).setBounds(431, 533, 188, 35);
        this.btnEntergame.addMouseListener(new RoleMouslisten(12, this.btnEntergame, loginJpanel));
        this.add(this.btnEntergame);
        (this.btnCreateRole = new SpriteBtn("resource/xinUI/xin/创建新角色1", 837, 64, false)).setBounds(900, 34, 150, 73);
        this.btnCreateRole.addMouseListener(new RoleMouslisten(13, this.btnCreateRole, loginJpanel));
        this.add(this.btnCreateRole);
        this.sx(loginJpanel.getRoleArr(), loginJpanel);
    }
    
    public void sx(RoleTableList roleArr, LoginJpanel loginJpanel) {
        if (this.rolebtn != null) {
            for (int i = 0; i < this.rolebtn.length; ++i) {
                this.remove(this.rolebtn[i]);
                this.remove(this.showRoleIcon[i]);
                this.remove(this.showRoleNameAndGrade[i]);
                this.remove(this.showRoleGrade[i]);
                this.remove(this.showracename[i]);
            }
            this.rolebtn = null;
        }
        if (roleArr != null && roleArr.getRoleList() != null) {
            int size = roleArr.getRoleList().size();
            if (size > 0) {
                this.rolebtn = new SpriteBtn[size];
                this.showRoleIcon = new JLabel[size];
                this.showRoleNameAndGrade = new JLabel[size];
                this.showRoleGrade = new JLabel[size];
                this.showracename = new JLabel[size];
                for (int j = 0; j < size; ++j) {
                    int y = j * 73;
                    RoleTableNew roleTableNew = (RoleTableNew)roleArr.getRoleList().get(j);
                    (this.rolebtn[j] = new SpriteBtn("resource/xinUI/xin/4a371d3d", 783, 123 + y, true, 1, 120, 120)).setBounds(783, 123 + y, 214, 70);
                    this.rolebtn[j].addMouseListener(new RoleMouslisten(14 + j, this.rolebtn[j], loginJpanel));
                    this.add(this.rolebtn[j]);
                    this.showRoleIcon[j] = new JLabel();
                    ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                    Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                    Configure configure = (Configure)item.get(new BigDecimal(1));
                    String nao = "新";
                    if (configure.getNeworold() != null) {
                        nao = configure.getNeworold();
                    }
                    if (nao.equals("新")) {
                        this.showRoleIcon[j].setIcon(CutButtonImage.getImage("img/head/s" + roleTableNew.getSpecies_id() + "-1.png", 60, 60));
                    }
                    else {
                        this.showRoleIcon[j].setIcon(CutButtonImage.getImage("img/head/s" + roleTableNew.getSpecies_id() + ".png", 60, 60));
                    }
                    this.showRoleIcon[j].setBounds(842, 127 + y, 60, 60);
                    this.add(this.showRoleIcon[j]);
                    (this.showRoleNameAndGrade[j] = new JLabel(roleTableNew.getRolename())).setBounds(910, 127 + y, 120, 30);
                    this.showRoleNameAndGrade[j].setFont(UIUtils.nameFont);
                    this.showRoleNameAndGrade[j].setForeground(Color.WHITE);
                    this.add(this.showRoleNameAndGrade[j]);
                    (this.showRoleGrade[j] = new JLabel(AnalysisString.lvl((int)roleTableNew.getGrade()) + "级")).setBounds(910, 155 + y, 90, 24);
                    this.showRoleGrade[j].setFont(UIUtils.TEXT_FONT);
                    this.showRoleGrade[j].setForeground(Color.BLACK);
                    this.add(this.showRoleGrade[j]);
                    (this.showracename[j] = new JLabel(roleTableNew.getRacename())).setBounds(970, 155 + y, 90, 24);
                    this.showracename[j].setFont(UIUtils.TEXT_FONT);
                    this.showracename[j].setForeground(Color.BLACK);
                    this.add(this.showracename[j]);
                }
                this.rolebtn[0].btn(2);
                this.ShowRoleNameAndGrade = ((RoleTableNew)roleArr.getRoleList().get(0)).getRolename();
                this.nameAndGradeColor = UIUtils.getcolor((int)((RoleTableNew)roleArr.getRoleList().get(0)).getTurnaround());
                this.part = SpriteFactory.createPart(((RoleTableNew)roleArr.getRoleList().get(0)).getSpecies_id().toString(), 2, 1, null);
                this.btnCreateRole.setSy(roleArr.getRoleList().size() * 75 + 135);
                this.btnCreateRole.setBounds(840, roleArr.getRoleList().size() * 75 + 135, 184, 41);
                this.xz((RoleTableNew)roleArr.getRoleList().get(0), 0);
            }
            else {
                this.btnCreateRole.setSy(134);
                this.btnCreateRole.setBounds(840, 134, 184, 41);
            }
        }
        else {
            this.btnCreateRole.setSy(34);
            this.btnCreateRole.setBounds(840, 34, 184, 41);
        }
    }
    
    public void xz(RoleTableNew login, int i) {
        LoginJpanel.quxiao(this.rolebtn, i);
        this.ShowRoleNameAndGrade = login.getRolename();
        this.nameAndGradeColor = UIUtils.getcolor((int)login.getTurnaround());
        this.part = SpriteFactory.createPart(login.getSpecies_id().toString(), 2, 1, null);
        for (int j = 0; j < this.rolebtn.length; ++j) {
            int y = j * 73;
            if (i == j) {
                this.showRoleIcon[j].setBounds(812, 126 + y, 60, 60);
                this.showRoleNameAndGrade[j].setBounds(880, 127 + y, 120, 30);
                this.showRoleGrade[j].setBounds(880, 155 + y, 90, 24);
                this.showracename[j].setBounds(940, 155 + y, 90, 24);
            }
            else {
                this.showRoleIcon[j].setBounds(842, 126 + y, 60, 60);
                this.showRoleNameAndGrade[j].setBounds(910, 127 + y, 120, 30);
                this.showRoleGrade[j].setBounds(910, 155 + y, 90, 24);
                this.showracename[j].setBounds(970, 155 + y, 90, 24);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long l = System.currentTimeMillis();
        g.drawImage(this.dise.getImage(), 0, 0, 1027, 720, null);
        g.drawImage(this.dise2.getImage(), 0, 634, 1027, 86, null);
        g.drawImage(this.rolebeijing.getImage(), 0, 0, 1027, 720, null);
        g.drawImage(this.btnCreateRoleBg.getImage(), 790, 103, 234, 113, null);
        g.drawImage(this.btnCreateRoleBg.getImage(), 790, 170, 234, 113, null);
        g.drawImage(this.btnCreateRoleBg.getImage(), 790, 237, 234, 113, null);
        g.drawImage(this.btnCreateRoleBg.getImage(), 790, 304, 234, 113, null);
        g.drawImage(this.btnCreateRoleBg.getImage(), 790, 371, 234, 113, null);
        g.drawImage(this.btnCreateRoleBg.getImage(), 815, 438, 234, 113, null);
        this.btnEntergame.draw(g);
        this.btnCreateRole.draw(g);
        if (this.rolebtn != null) {
            for (int i = 0; i < this.rolebtn.length; ++i) {
                if (this.rolebtn[i] != null) {
                    this.rolebtn[i].draw(g);
                }
            }
        }
        this.fanghui.draw(g);
        if (this.part != null) {
            this.time += 20L;
            this.part.draw(g, 500, 480, 8, this.time);
            Font f5 = UIUtils.nameFont;
            g.setFont(f5);
            int middle = 502;
            int size = 4;
            int nameX = middle - String_length(this.ShowRoleNameAndGrade) * size;
            g.setColor(UIUtils.COLOR_NAME8);
            g.drawString(this.ShowRoleNameAndGrade, nameX - 19, 510);
            g.setColor(this.nameAndGradeColor);
            g.drawString(this.ShowRoleNameAndGrade, nameX - 20, 510);
        }
        this.tcp1.updateToTime(l, 0);
        this.tcp1.draw(g, 0, 0);
    }
    
    public void setRoleInfo(String grade, String sex, String race, Color color) {
    }
    
    public static int String_length(String value) {
        int valueLength = 0;
        String chinese = "[-]";
        for (int i = 0; i < value.length(); ++i) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            }
            else {
                ++valueLength;
            }
        }
        return valueLength;
    }
    
    public void setRoleTableNew(RoleTableNew roleTableNew) {
    }
}
