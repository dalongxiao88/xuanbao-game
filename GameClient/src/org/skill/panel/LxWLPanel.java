package org.skill.panel;

import org.come.until.FormsManagement;
import org.come.Frame.MsgJframe;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import com.tool.role.GetExp;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import org.come.bean.TYCTypeBean;
import org.come.bean.TYCPointBean;
import org.come.until.JmSum;
import org.come.until.UserMessUntil;
import org.come.until.AnalysisString;
import org.come.Jpanel.MsgJapnel;
import org.come.bean.Skill;
import org.come.bean.PathPoint;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.bean.LoginResult;
import com.tool.role.RoleProperty;
import org.come.Frame.ZhuFrame;
import com.tool.role.SkillUtil;
import org.come.until.SendRoleAndRolesummingUntil;
import com.tool.role.RoleData;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import org.skill.btn.SkillLxBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LxWLPanel extends JPanel
{
    private JLabel maxPointCase;
    private JLabel maxPointLab;
    private SkillLxBtn switchBtn;
    private SkillLxBtn exchangeBtn;
    private SkillLxBtn washPointBtn;
    private SkillLxBtn simulationBtn;
    private SkillLxBtn confirmBtn;
    private JLabel raceCase;
    private JLabel raceLab;
    private SkillSMSelectOptionJpanel TYCselect;
    public int typeBtn;
    private MSkillTYC[] skillTYCs;
    private TYCRole tycRole;
    private ImageIcon icon;
    
    public LxWLPanel() {
        this.icon = new ImageIcon("inkImg/background/S267.png");
        this.typeBtn = -1;
        this.setBounds(21, 60, 560, 350);
        this.setOpaque(false);
        this.setLayout(null);
        String[] rowData = { "男人", "女人", "男魔", "女魔", "男仙", "女仙", "男鬼", "女鬼" };
        (this.TYCselect = new SkillSMSelectOptionJpanel(64, 120, "inkImg/background/74.png", rowData)).setBounds(243, 198, 64, 120);
        this.add(this.TYCselect);
        this.TYCselect.getJlist().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int count = LxWLPanel.this.TYCselect.getJlist().getSelectedIndex();
                if (count == -1) {
                    return;
                }
                String getname = (String)LxWLPanel.this.TYCselect.getJlist().getSelectedValue();
                LxWLPanel.this.initTYC(getname, null, (int)LxWLPanel.this.tycRole.getLvl());
                LxWLPanel.this.raceLab.setText(getname);
                LxWLPanel.this.TYCselect.setVisible(false);
            }
        });
        (this.maxPointLab = new JLabel()).setForeground(Color.white);
        this.maxPointLab.setBounds(63, 317, 60, 22);
        this.maxPointLab.setFont(UIUtils.TEXT_FONT1);
        this.add(this.maxPointLab);
        (this.maxPointCase = new JLabel()).setBounds(25, 317, 98, 22);
        this.maxPointCase.setIcon(CutButtonImage.getImage("inkImg/background/72.png", 97, 19));
        this.add(this.maxPointCase);
        (this.raceLab = new JLabel()).setForeground(Color.white);
        this.raceLab.setBounds(247, 316, 64, 20);
        this.raceLab.setFont(UIUtils.TEXT_FONT1);
        this.add(this.raceLab);
        (this.raceCase = new JLabel()).setBounds(205, 317, 104, 20);
        this.raceCase.setIcon(CutButtonImage.getImage("inkImg/background/73.png", 103, 20));
        this.add(this.raceCase);
        this.tycRole = new TYCRole();
        this.skillTYCs = new MSkillTYC[3];
        for (int i = 0; i < this.skillTYCs.length; ++i) {
            (this.skillTYCs[i] = new MSkillTYC()).px = 176 * i + 25;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.icon.getImage(), 0, 0, 492, 345, this);
        g.setFont(UIUtils.TEXT_HY24);
        g.setColor(Color.yellow);
        for (int i = 0; i < this.skillTYCs.length; ++i) {
            this.skillTYCs[i].draw(g);
        }
    }
    
    public void addPoint() {
        int i = 0;
        while (i < this.skillTYCs.length) {
            if (this.skillTYCs[i].visible) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(this.skillTYCs[i].value);
                for (int j = 0; j < this.skillTYCs[i].lists.size(); ++j) {
                    XSkillTYC skillTYC = (XSkillTYC)this.skillTYCs[i].lists.get(j);
                    if ((int)skillTYC.tycPointBean.getNowPoint() > 0) {
                        buffer.append("#");
                        buffer.append(skillTYC.tycPointBean.getTableId().toString());
                        buffer.append("_");
                        buffer.append(skillTYC.tycPointBean.getNowPoint());
                    }
                }
                String skill = buffer.toString();
                if (skill.equals(this.skillTYCs[i].value)) {
                    return;
                }
                RoleData.getRoleData().getPrivateData().setSkills("T", skill);
                SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                this.initTYC(SkillUtil.getSepciesN(loginResult.getSpecies_id()), skill.split("#"), (int)loginResult.getGrade());
                ZhuFrame.getZhuJpanel().addPrompt2("已保存");
                RoleProperty.ResetEw();
                break;
            }
            else {
                ++i;
            }
        }
    }
    
    public void washPoint() {
        if (this.typeBtn == 1) {
            this.changeBtnPanel(1);
        }
        else {
            int i = 0;
            while (i < this.skillTYCs.length) {
                if (this.skillTYCs[i].visible) {
                    int dian = this.skillTYCs[i].getPoint().getX() * 3000000;
                    if (dian > 0) {
                        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.washPoint, Integer.valueOf(dian), "#W你确定要花费#R" + dian + "#W银两,重置天演策加点吗?");
                        break;
                    }
                    else {
                        break;
                    }
                }
                else {
                    ++i;
                }
            }
        }
    }
    
    public void changeBtnPanel(int typeBtn) {
        if (this.typeBtn != typeBtn) {
            try {
                this.changeBtn(typeBtn);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.typeBtn = typeBtn;
        String seName = SkillUtil.getSepciesN(RoleData.getRoleData().getLoginResult().getSpecies_id());
        if (this.typeBtn == 0) {
            String[] data = RoleData.getRoleData().getPrivateData().getSkill("T");
            this.initTYC(seName, data, (int)this.tycRole.getLvl());
        }
        else if (this.typeBtn == 1) {
            this.initTYC(seName, null, (int)this.tycRole.getLvl());
        }
    }
    
    public void initTYC(String seName, String[] data, int lvl) {
        this.tycRole.data = data;
        this.raceLab.setText(seName);
        this.maxPointLab.setText(this.tycRole.getDian() + "");
        String[] vs = SkillUtil.getSepciesS(seName);
        for (int i = 0; i < vs.length; ++i) {
            this.skillTYCs[i].reset(vs[i], data, lvl);
            if (data != null && !data[0].equals(this.skillTYCs[i].value)) {
                this.skillTYCs[i].setShow(false);
            }
        }
    }
    
    public void changeBtn(int typeBtn) throws Exception {
        if (typeBtn == 0) {
            this.maxPointCase.setBounds(95, 317, 98, 22);
            this.maxPointLab.setBounds(153, 317, 60, 22);
            this.raceCase.setVisible(false);
            this.raceLab.setVisible(false);
            this.TYCselect.setVisible(false);
            this.switchBtn.setBounds(25, 317, 60, 26);
            this.switchBtn.setText("切换");
            this.switchBtn.setIcons(CutButtonImage.cuts("inkImg/button/32.png"));
            this.exchangeBtn.setBounds(195, 318, 34, 17);
            this.exchangeBtn.setText("兑换");
            this.exchangeBtn.setIcons(CutButtonImage.cuts("inkImg/button/2.png"));
            this.exchangeBtn.setBtn(1);
            this.washPointBtn.setBounds(235, 318, 34, 17);
            this.washPointBtn.setText("洗点");
            this.washPointBtn.setIcons(CutButtonImage.cuts("inkImg/button/2.png"));
            this.simulationBtn.setText("模拟加点");
            this.simulationBtn.setFont(UIUtils.TEXT_HY16);
            this.simulationBtn.setIcons(CutButtonImage.cuts("inkImg/button/18.png"));
            this.confirmBtn.setText("确认加点");
            this.confirmBtn.setIcons(CutButtonImage.cuts("inkImg/button/18.png"));
            this.confirmBtn.setBtn(1);
        }
        else {
            this.maxPointCase.setBounds(25, 317, 98, 22);
            this.maxPointLab.setBounds(83, 317, 60, 22);
            this.raceCase.setVisible(true);
            this.raceLab.setVisible(true);
            this.switchBtn.setBounds(288, 318, 18, 18);
            this.switchBtn.setText("");
            this.switchBtn.setIcons(CutButtonImage.cuts("inkImg/button/8.png"));
            this.exchangeBtn.setBounds(128, 318, 34, 17);
            this.exchangeBtn.setText("兑换");
            this.exchangeBtn.setIcon(CutButtonImage.getImage("inkImg/button/36.png", 34, 17));
            this.exchangeBtn.setBtn(-1);
            this.washPointBtn.setBounds(165, 318, 34, 17);
            this.washPointBtn.setText("洗点");
            this.washPointBtn.setIcons(CutButtonImage.cuts("inkImg/button/2.png"));
            this.simulationBtn.setText("取消模拟");
            this.simulationBtn.setIcons(CutButtonImage.cuts("inkImg/button/18.png"));
            this.confirmBtn.setText("确认加点");
            this.confirmBtn.setIcon(CutButtonImage.getImage("inkImg/button/B67.png", 100, 26));
            this.confirmBtn.setBtn(-1);
        }
    }
    
    public void changePoint(XSkillTYC xSkillTYC, boolean is) {
        if (is) {
            if ((int)xSkillTYC.tycPointBean.getNowPoint() >= (int)xSkillTYC.tycPointBean.getMaxPoint()) {
                return;
            }
        }
        else if ((int)xSkillTYC.tycPointBean.getNowPoint() <= 0) {
            return;
        }
        MSkillTYC skillTYC = this.skillTYCs[xSkillTYC.px];
        if (skillTYC == null) {
            return;
        }
        PathPoint point = skillTYC.getPoint();
        if (is) {
            if ((int)this.tycRole.getDian() <= point.getX()) {
                return;
            }
            if (!skillTYC.isCondition(xSkillTYC, point, (int)this.tycRole.getLvl(), is)) {
                return;
            }
            xSkillTYC.setPoint((int)xSkillTYC.tycPointBean.getNowPoint() + 1);
            skillTYC.refreshPanel((int)this.tycRole.getLvl());
            for (int i = 0; i < this.skillTYCs.length; ++i) {
                if (this.skillTYCs[i].visible && this.skillTYCs[i] != skillTYC) {
                    this.skillTYCs[i].setShow(false);
                }
            }
            this.maxPointLab.setText((int)this.tycRole.getDian() - point.getX() - 1 + "");
        }
        else {
            if (this.typeBtn == 0 && this.tycRole.isDian((int)xSkillTYC.tycPointBean.getTableId(), (int)xSkillTYC.tycPointBean.getNowPoint())) {
                return;
            }
            xSkillTYC.setPoint((int)xSkillTYC.tycPointBean.getNowPoint() - 1);
            PathPoint point2 = skillTYC.getPoint();
            for (int j = skillTYC.lists.size() - 1; j >= 0; --j) {
                XSkillTYC xSkillTYC2 = (XSkillTYC)skillTYC.lists.get(j);
                if (xSkillTYC != xSkillTYC2 && (xSkillTYC2.icon1 == null || (int)xSkillTYC2.tycPointBean.getNowPoint() != 0) && !skillTYC.isCondition(xSkillTYC2, point2, (int)this.tycRole.getLvl(), is)) {
                    xSkillTYC.setPoint((int)xSkillTYC.tycPointBean.getNowPoint() + 1);
                    return;
                }
            }
            if (point2.getX() == 0) {
                for (int j = 0; j < this.skillTYCs.length; ++j) {
                    this.skillTYCs[j].reset(this.skillTYCs[j].value, null, (int)this.tycRole.getLvl());
                }
            }
            else {
                skillTYC.refreshPanel((int)this.tycRole.getLvl());
            }
            this.maxPointLab.setText((int)this.tycRole.getDian() - point.getX() + 1 + "");
        }
    }
    
    public String getSkillMsg(Skill skill, XSkillTYC xSkillTYC) {
        TYCPointBean bean = xSkillTYC.tycPointBean;
        TYCTypeBean typeBean = bean.getTycTypeBean();
        int id = (int)bean.getTableId();
        int row = (int)bean.getRow();
        MSkillTYC skillTYC = this.skillTYCs[xSkillTYC.px];
        if (skillTYC == null || skill == null) {
            return "好像出问题了?联系管理员提交BUG";
        }
        PathPoint point = skillTYC.getPoint();
        StringBuffer buffer = new StringBuffer();
        buffer.append("#cffffff【等级】\t");
        buffer.append(bean.getNowPoint());
        buffer.append("/");
        buffer.append(bean.getMaxPoint());
        buffer.append("#r【境界】\t");
        buffer.append((row <= 2) ? "1" : ((row == 3) ? "2" : ((row <= 5) ? "3" : "4")));
        buffer.append("#r【技能类别】\t");
        if (id == 9110 || id == 9111 || id == 9126 || id == 9130 || id == 9151 || id == 9169 || id == 9170 || id == 9171 || id == 9189 || id == 9190 || id == 9207 || id == 9208 || id == 9209 || id == 9231 || id == 9232 || id == 9250 || id == 9251 || id == 9252 || id == 9262 || id == 9270 || id == 9286 || id == 9287 || id == 9307 || id == 9350 || id == 9352 || id == 9372 || id == 9389 || id == 9412) {
            buffer.append("主动");
        }
        else {
            buffer.append("被动");
        }
        String skilllevel = skill.getSkilllevel();
        skill.setSkilllevel(bean.getNowPoint() + "");
        buffer.append(MsgJapnel.skillMsgchange(skill.getRemark(), skill));
        skill.setSkilllevel(skilllevel);
        if (typeBean == null) {
            return buffer.toString();
        }
        boolean is = true;
        Integer dian = typeBean.getLvl();
        if (dian != null && (int)dian > (int)this.tycRole.getLvl()) {
            if (is) {
                buffer.append("#r#r#cff0000");
                is = false;
            }
            else {
                buffer.append("#r");
            }
            buffer.append("需要");
            buffer.append(AnalysisString.lvl((int)dian));
        }
        dian = typeBean.getOneTotalPoint();
        if (dian != null && (int)dian > point.getY()) {
            if (is) {
                buffer.append("#r#r#cff0000");
                is = false;
            }
            else {
                buffer.append("#r");
            }
            buffer.append("需要第一境界#c00ff00");
            buffer.append(dian);
            buffer.append("#cff0000点");
        }
        dian = typeBean.getTotalPoint((int)bean.getNowPoint());
        if (dian != null && (int)dian > point.getX()) {
            if (is) {
                buffer.append("#r#r#cff0000");
                is = false;
            }
            else {
                buffer.append("#r");
            }
            buffer.append("需要总点数#c00ff00");
            buffer.append(dian);
            buffer.append("#cff0000点");
        }
        dian = typeBean.getQZID();
        if (dian != null) {
            XSkillTYC skillTYC2 = skillTYC.getTyc((int)dian);
            if (skillTYC2 != null && (int)typeBean.getQZPoint() > (int)skillTYC2.tycPointBean.getNowPoint()) {
                Skill skill2 = UserMessUntil.getSkillId(dian.toString());
                if (skill2 != null) {
                    if (is) {
                        buffer.append("#r#r#cff0000");
                        is = false;
                    }
                    else {
                        buffer.append("#r");
                    }
                    buffer.append("需要前置技能#c00ff00");
                    buffer.append(skill2.getSkillname());
                    buffer.append("#cff0000");
                    buffer.append(typeBean.getQZPoint());
                    buffer.append("点数");
                }
            }
        }
        List<Integer> list = typeBean.getExclusiveSkills();
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                dian = Integer.valueOf((int)JmSum.MZ((long)(int)list.get(i)));
                if ((int)dian != (int)bean.getTableId()) {
                    Skill skill3 = UserMessUntil.getSkillId(dian.toString());
                    if (skill3 != null) {
                        if (is) {
                            buffer.append("#r#r#cff0000");
                            is = false;
                        }
                        else {
                            buffer.append("#r");
                        }
                        buffer.append("与#c00ff00");
                        buffer.append(skill3.getSkillname());
                        buffer.append("#cff0000技能互斥");
                    }
                }
            }
        }
        return buffer.toString();
    }
    
    public SkillSMSelectOptionJpanel getTYCselect() {
        return this.TYCselect;
    }
    
    public void setTYCselect(SkillSMSelectOptionJpanel tYCselect) {
        this.TYCselect = tYCselect;
    }
    
    class MSkillTYC
    {
        private List<XSkillTYC> lists;
        private String value;
        private ImageIcon icon;
        private int px;
        private Integer p;
        public boolean visible;
        
        public void reset(String value, String[] data, int lvl) {
            this.setShow(true);
            if (this.value == null || !this.value.equals(value)) {
                this.value = value;
                this.icon = CutButtonImage.TYC(value);
                String[] values = UserMessUntil.getTYC(value).split("\\|");
                if (this.lists == null) {
                    this.lists = new ArrayList<>();
                }
                int p = 0;
                int size = this.lists.size();
                for (int i = 0; i < values.length; ++i) {
                    String[] vs = values[i].split("#");
                    TYCPointBean bean = new TYCPointBean();
                    int zhi = Integer.parseInt(vs[0]);
                    bean.setRow(Integer.valueOf(zhi / 10));
                    bean.setRank(Integer.valueOf(zhi % 10));
                    bean.setTableId(Integer.valueOf(Integer.parseInt(vs[1])));
                    Skill skill = UserMessUntil.getSkillId(vs[1]);
                    if (skill != null) {
                        bean.setMaxPoint(Integer.valueOf(Integer.parseInt(skill.getSkilllevel())));
                    }
                    else {
                        bean.setMaxPoint(Integer.valueOf(5));
                    }
                    if (vs.length > 2) {
                        TYCTypeBean tycTypeBean = new TYCTypeBean();
                        for (int j = 2; j < vs.length; ++j) {
                            String L = vs[j].substring(1);
                            if (vs[j].startsWith("D")) {
                                tycTypeBean.setOneTotalPoint(Integer.valueOf(Integer.parseInt(L)));
                            }
                            else {
                                String[] ls = L.split("-");
                                if (vs[j].startsWith("Q")) {
                                    tycTypeBean.setQZID(Integer.valueOf(Integer.parseInt(ls[0])));
                                    tycTypeBean.setQZPoint(Integer.valueOf(Integer.parseInt(ls[1])));
                                }
                                else if (vs[j].startsWith("Z")) {
                                    for (int k = 0; k < ls.length; ++k) {
                                        tycTypeBean.setTotalPoint(Integer.valueOf(Integer.parseInt(ls[k])));
                                    }
                                }
                                else if (vs[j].startsWith("C")) {
                                    for (int k = 0; k < ls.length; ++k) {
                                        tycTypeBean.setExclusiveSkill(Integer.valueOf(Integer.parseInt(ls[k])));
                                    }
                                }
                                else if (vs[j].startsWith("L")) {
                                    tycTypeBean.setLvl(Integer.valueOf(AnalysisString.lvldirection((ls[0].equals("4") ? "飞升" : (ls[0] + "转")) + ls[1])));
                                }
                            }
                        }
                        bean.setTycTypeBean(tycTypeBean);
                    }
                    if (p < size) {
                        ((XSkillTYC)this.lists.get(p)).reset(bean, this.px);
                        ++p;
                    }
                    else {
                        ++p;
                        XSkillTYC skillTYC = new XSkillTYC(bean, this.px);
                        this.lists.add(skillTYC);
                        LxWLPanel.this.add(skillTYC);
                    }
                }
                for (int i = size - 1; i >= p; --i) {
                    LxWLPanel.this.remove((Component)this.lists.remove(i));
                }
            }
            else {
                if (this.lists == null) {
                    this.lists = new ArrayList<>();
                }
                for (int l = this.lists.size() - 1; l >= 0; --l) {
                    ((XSkillTYC)this.lists.get(l)).setPoint(0);
                }
            }
            if (data != null && value.equals(data[0])) {
                for (int l = 1; l < data.length; ++l) {
                    String[] vs2 = data[l].split("_");
                    XSkillTYC skillTYC2 = this.getTyc(Integer.parseInt(vs2[0]));
                    if (skillTYC2 != null) {
                        skillTYC2.setPoint(Integer.parseInt(vs2[1]));
                    }
                }
                PathPoint point = this.getPoint();
                LxWLPanel.this.maxPointLab.setText((int)LxWLPanel.this.tycRole.getDian() - point.getX() + "");
            }
            this.refreshPanel(lvl);
        }
        
        public void refreshPanel(int lvl) {
            if (this.lists != null) {
                PathPoint point = this.getPoint();
                for (int i = this.lists.size() - 1; i >= 0; --i) {
                    XSkillTYC skillTYC = (XSkillTYC)this.lists.get(i);
                    boolean is = this.isCondition(skillTYC, point, lvl, true);
                    if (is) {
                        skillTYC.setIcon1(null);
                    }
                    else {
                        skillTYC.setIcon1(CutButtonImage.getImage("img/soaringSkill/蒙版w34,h34.png", -1, -1));
                    }
                }
            }
        }
        
        public boolean isCondition(XSkillTYC skillTYC, PathPoint point, int lvl, boolean type) {
            TYCPointBean bean = skillTYC.tycPointBean;
            TYCTypeBean typeBean = bean.getTycTypeBean();
            if (typeBean == null) {
                return true;
            }
            Integer dian = typeBean.getQZID();
            if (dian != null && (type ? type : ((int)skillTYC.tycPointBean.getNowPoint() != 0))) {
                XSkillTYC skillTYC2 = this.getTyc((int)dian);
                if (skillTYC2 == null) {
                    return false;
                }
                if ((int)typeBean.getQZPoint() > (int)skillTYC2.tycPointBean.getNowPoint()) {
                    return false;
                }
            }
            dian = typeBean.getLvl();
            if (dian != null && (int)dian > lvl) {
                return false;
            }
            dian = typeBean.getOneTotalPoint();
            if (dian != null && (int)dian > point.getY()) {
                return false;
            }
            dian = typeBean.getTotalPoint((int)bean.getNowPoint());
            if (dian != null && (int)dian > point.getX()) {
                return false;
            }
            List<Integer> list = typeBean.getExclusiveSkills();
            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    dian = Integer.valueOf((int)JmSum.MZ((long)(int)list.get(i)));
                    if ((int)dian != (int)bean.getTableId()) {
                        XSkillTYC skillTYC3 = this.getTyc((int)dian);
                        if (skillTYC3 != null && (int)skillTYC3.tycPointBean.getNowPoint() > 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        
        public XSkillTYC getTyc(int id) {
            for (int i = this.lists.size() - 1; i >= 0; --i) {
                if ((int)((XSkillTYC)this.lists.get(i)).tycPointBean.getTableId() == id) {
                    return (XSkillTYC)this.lists.get(i);
                }
            }
            return null;
        }
        
        public PathPoint getPoint() {
            PathPoint point = new PathPoint();
            for (int i = this.lists.size() - 1; i >= 0; --i) {
                TYCPointBean bean = ((XSkillTYC)this.lists.get(i)).tycPointBean;
                point.setX(point.getX() + (int)bean.getNowPoint());
                if ((int)bean.getRow() <= 2) {
                    point.setY(point.getY() + (int)bean.getNowPoint());
                }
            }
            return point;
        }
        
        public void setShow(boolean flag) {
            this.visible = flag;
            if (this.lists != null) {
                for (int i = this.lists.size() - 1; i >= 0; --i) {
                    ((XSkillTYC)this.lists.get(i)).setVisible(this.visible);
                }
            }
        }
        
        public void draw(Graphics g) {
            if (this.visible) {
                g.drawImage(this.icon.getImage(), this.px, 0, null);
                if (this.p == null) {
                    this.p = Integer.valueOf(g.getFontMetrics().stringWidth(this.value) / 2);
                    this.p = Integer.valueOf(this.px + 85 - (int)this.p);
                }
                g.drawString(this.value, (int)this.p, 28);
            }
        }
    }
    
    class TYCRole
    {
        private int lvl;
        private int dian;
        private String[] data;
        
        public TYCRole() {
            this.lvl = 999;
            this.dian = 60;
            this.setLvl(Integer.valueOf(999));
            this.setDian(Integer.valueOf(60));
        }
        
        public Integer getLvl() {
            if (LxWLPanel.this.typeBtn == 0) {
                return RoleData.getRoleData().getLoginResult().getGrade();
            }
            return Integer.valueOf((int)JmSum.MZ((long)this.lvl));
        }
        
        public void setLvl(Integer lvl) {
            this.lvl = (int)JmSum.ZM((long)(int)lvl);
        }
        
        public Integer getDian() {
            if (LxWLPanel.this.typeBtn == 0) {
                return Integer.valueOf(GetExp.getTSP(RoleData.getRoleData().getLoginResult().getExtraPointInt("T")));
            }
            return Integer.valueOf((int)JmSum.MZ((long)this.dian));
        }
        
        public void setDian(Integer dian) {
            this.dian = (int)JmSum.ZM((long)(int)dian);
        }
        
        public boolean isDian(int id, int dian) {
            if (this.data == null) {
                return false;
            }
            String ids = id + "";
            for (int i = 1; i < this.data.length; ++i) {
                if (this.data[i].startsWith(ids)) {
                    return Integer.parseInt(this.data[i].split("_")[1]) <= dian;
                }
            }
            return false;
        }
    }
    
    class XSkillTYC extends JComponent implements MouseListener
    {
        private TYCPointBean tycPointBean;
        private ImageIcon icon1;
        private ImageIcon icon2;
        private ImageIcon icon3;
        private String value;
        private int py;
        private int px;
        
        public XSkillTYC(TYCPointBean tycPointBean, int x) {
            this.addMouseListener(this);
            this.reset(tycPointBean, x);
            this.px = x / 176;
        }
        
        public void reset(TYCPointBean tycPointBean, int x) {
            this.tycPointBean = tycPointBean;
            this.icon1 = null;
            this.icon2 = CutButtonImage.TYCSkill((int)this.tycPointBean.getTableId());
            this.setPoint(0);
            this.py = 0;
            this.setBounds(x - 40 + (int)tycPointBean.getRank() * 55, -11 + (int)tycPointBean.getRow() * 46, 38, 38);
        }
        
        public void setPoint(int point) {
            this.value = this.tycPointBean.setPoint(Integer.valueOf(point));
            if (this.value.length() == 1) {
                this.icon3 = CutButtonImage.getImage("inkImg/background/S40.png", -1, -1);
            }
            else {
                this.icon3 = CutButtonImage.getImage("inkImg/background/S39.png", -1, -1);
            }
        }
        
        public void setIcon1(ImageIcon icon1) {
            this.icon1 = icon1;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (this.icon2 != null) {
                g.drawImage(this.icon2.getImage(), this.py, this.py, 34, 34, this);
            }
            if (this.icon3 != null) {
                g.drawImage(this.icon3.getImage(), 0, 22, this);
            }
            if (this.value != null) {
                g.setColor(Color.yellow);
                g.setFont(UIUtils.TEXT_FONT);
                g.drawString(this.value, 3, 33);
            }
            if (this.icon1 != null) {
                g.drawImage(this.icon1.getImage(), 0, 0, this);
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1) {
                if (this.icon1 != null) {
                    return;
                }
                LxWLPanel.this.changePoint(this, true);
            }
            else if (e.getButton() == 3) {
                LxWLPanel.this.changePoint(this, false);
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            if (this.icon1 != null) {
                return;
            }
            ++this.py;
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (this.icon1 != null) {
                return;
            }
            --this.py;
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            Skill skill = UserMessUntil.getSkillId(this.tycPointBean.getTableId() + "");
            MsgJframe.getJframe().getJapnel().TYC((skill != null) ? skill.getSkillname() : "", LxWLPanel.this.getSkillMsg(skill, this));
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            FormsManagement.HideForm(46);
        }
    }
}
