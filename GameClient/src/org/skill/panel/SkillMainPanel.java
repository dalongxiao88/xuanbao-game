package org.skill.panel;

import org.come.bean.LoginResult;
import org.skill.frame.SkillMainFrame;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import java.util.List;
import org.come.bean.PrivateData;
import org.come.bean.Skill;
import org.come.until.AnalysisString;
import com.tool.image.ImageMixDeal;
import java.util.ArrayList;
import com.tool.role.RoleData;
import java.util.Map;
import org.come.bean.ConfigureBean;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import javax.swing.ImageIcon;
import com.tool.btn.FormsOnOffBtn;
import org.skill.btn.SkillTYCBtn;
import org.come.Jpanel.CharacterMeridiansMainJpanel;
import org.skill.btn.SkillMainBtn;
import javax.swing.JPanel;

public class SkillMainPanel extends JPanel
{
    private SkillMainBtn[] mainBtns;
    private SkillSMGatePanel divisionGatePanel;
    private CopyOfSkillTYCPanel copyOfSkillTYCPanel;
    private CharacterMeridiansMainJpanel meridiansMainJpanel;
    private SkillSMGatePanel2 divisionGatePanel2;
    private String jmkg;
    private String fmkg;
    private String tyckg;
    private SkillTYCBtn YJBtn;
    public FormsOnOffBtn offBtn;
    private ImageIcon icon;
    private ImageIcon icon1;
    private int typeBtn;
    
    public SkillMainPanel() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        this.jmkg = configure.getJmgnkg();
        this.fmkg = configure.getFmgnkg();
        this.tyckg = configure.getTyckg();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(602, 425));
            this.setOpaque(false);
            this.setLayout(null);
            (this.offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 82)).setBounds(565, 10, 25, 25);
            this.add(this.offBtn);
            (this.YJBtn = new SkillTYCBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TXT_hyzjt16, "一键学习", 5)).setBounds(475, 385, 99, 24);
            this.YJBtn.setVisible(false);
            this.add(this.YJBtn);
            this.YJXYbtn();
            if (configure.getLzjskg().equals("3")) {
                this.mainBtns = new SkillMainBtn[1];
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                this.mainBtns = new SkillMainBtn[4];
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                this.mainBtns = new SkillMainBtn[3];
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                this.mainBtns = new SkillMainBtn[3];
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                this.mainBtns = new SkillMainBtn[3];
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("关")) {
                this.mainBtns = new SkillMainBtn[2];
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                this.mainBtns = new SkillMainBtn[2];
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                this.mainBtns = new SkillMainBtn[2];
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("关")) {
                this.mainBtns = new SkillMainBtn[1];
            }
            for (int i = 0; i < this.mainBtns.length; ++i) {
                (this.mainBtns[i] = new SkillMainBtn(null, 1, i, this)).setBounds(70 + 80 * i, 23, 75, 33);
                this.add(this.mainBtns[i]);
            }
            (this.divisionGatePanel = new SkillSMGatePanel()).setVisible(false);
            this.add(this.divisionGatePanel);
            (this.copyOfSkillTYCPanel = new CopyOfSkillTYCPanel()).setVisible(false);
            this.add(this.copyOfSkillTYCPanel);
            (this.meridiansMainJpanel = new CharacterMeridiansMainJpanel()).setVisible(false);
            this.add(this.meridiansMainJpanel);
            (this.divisionGatePanel2 = new SkillSMGatePanel2()).setVisible(false);
            this.add(this.divisionGatePanel2);
        }
        else {
            this.setPreferredSize(new Dimension(576, 447));
            this.setOpaque(false);
            this.setLayout(null);
            (this.offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 82)).setBounds(550, 0, 23, 23);
            this.add(this.offBtn);
            (this.YJBtn = new SkillTYCBtn("inkImg/hongmu/a2.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TXT_hyzjt16, "一键学习", 5)).setBounds(460, 385, 85, 24);
            this.YJBtn.setVisible(false);
            this.add(this.YJBtn);
            this.YJXYbtn();
            if (configure.getLzjskg().equals("3")) {
                this.mainBtns = new SkillMainBtn[1];
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                this.mainBtns = new SkillMainBtn[4];
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                this.mainBtns = new SkillMainBtn[3];
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                this.mainBtns = new SkillMainBtn[3];
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                this.mainBtns = new SkillMainBtn[3];
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("关")) {
                this.mainBtns = new SkillMainBtn[2];
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                this.mainBtns = new SkillMainBtn[2];
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                this.mainBtns = new SkillMainBtn[2];
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("关")) {
                this.mainBtns = new SkillMainBtn[1];
            }
            for (int i = 0; i < this.mainBtns.length; ++i) {
                (this.mainBtns[i] = new SkillMainBtn("img/soaringSkill/选项卡_师门_未选中_w80,h120.png", 1, i, this)).setBounds(22 + 82 * i, 29, 80, 40);
                this.add(this.mainBtns[i]);
            }
            (this.divisionGatePanel = new SkillSMGatePanel()).setVisible(false);
            this.add(this.divisionGatePanel);
            (this.copyOfSkillTYCPanel = new CopyOfSkillTYCPanel()).setVisible(false);
            this.add(this.copyOfSkillTYCPanel);
            (this.meridiansMainJpanel = new CharacterMeridiansMainJpanel()).setVisible(false);
            this.add(this.meridiansMainJpanel);
            (this.divisionGatePanel2 = new SkillSMGatePanel2()).setVisible(false);
            this.add(this.divisionGatePanel2);
        }
    }
    
    protected void YJXYbtn() {
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String[] vs = data.getSkill("S");
        List<Skill> skills_no = new ArrayList<>();
        if (vs != null && vs.length >= 15) {
            for (int i = 0; i < vs.length; ++i) {
                String[] vs2 = vs[i].split("_");
                int usersld = Integer.parseInt(vs2[1]);
                int sld = AnalysisString.shuliandu((int)ImageMixDeal.userimg.getRoleShow().getGrade(), Integer.valueOf(1));
                if (sld > usersld) {
                    Skill skill = new Skill();
                    skill.setSkillid(vs2[0]);
                    skill.setValue(vs2[1]);
                    skills_no.add(skill);
                }
            }
            if (!skills_no.isEmpty()) {
                this.YJBtn.setVisible(true);
            }
            else {
                this.YJBtn.setVisible(false);
            }
        }
        else {
            this.YJBtn.setVisible(true);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                if (this.typeBtn == 1) {
                    this.icon = new ImageIcon("Client/tyc.png");
                }
                else {
                    this.icon1 = new ImageIcon("inkImg/background1/B220.png");
                }
            }
            g.drawImage((this.typeBtn == 1) ? this.icon.getImage() : this.icon1.getImage(), 0, 0, (this.typeBtn == 1) ? 773 : 602, (this.typeBtn == 1) ? 556 : 425, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/soaringSkill/技能w576,h447.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, (this.typeBtn == 1) ? 773 : 576, (this.typeBtn == 1) ? 556 : 447, this);
        }
    }
    
    public void changeBtnPanel(int typeBtn) {
        this.typeBtn = typeBtn;
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < this.mainBtns.length; ++i) {
                try {
                    this.mainBtns[i].setIcons(CutButtonImage.cuts(this.getBtnPanelPath(i, i == this.typeBtn)));
                    if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.copyOfSkillTYCPanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 2) {
                            this.meridiansMainJpanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 3) {
                            this.divisionGatePanel2.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.copyOfSkillTYCPanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 2) {
                            this.meridiansMainJpanel.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.meridiansMainJpanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 2) {
                            this.divisionGatePanel2.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.copyOfSkillTYCPanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 2) {
                            this.divisionGatePanel2.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("关")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.copyOfSkillTYCPanel.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.meridiansMainJpanel.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.divisionGatePanel2.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("关") && i == 0) {
                        this.divisionGatePanel.setVisible(i == this.typeBtn);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            if (this.typeBtn == 1) {
                SkillMainFrame.getSkillMainFrame().setBounds(150, 85, 773, 556);
                this.offBtn.setBounds(737, 10, 23, 23);
                this.copyOfSkillTYCPanel.ChangeBtnPanel(0, ImageMixDeal.userimg.getRoleShow().getSpecies_id());
            }
            if (typeBtn != 1) {
                SkillMainFrame.getSkillMainFrame().setBounds(276, 176, 602, 425);
                this.offBtn.setBounds(565, 10, 23, 23);
            }
            if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.copyOfSkillTYCPanel.changeBtnPanel(0);
                    this.YJBtn.setVisible(false);
                }
                else if (this.typeBtn == 2) {
                    this.meridiansMainJpanel.refreshAllAttribute();
                    this.YJBtn.setVisible(false);
                }
                else if (this.typeBtn == 3) {
                    this.divisionGatePanel2.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.copyOfSkillTYCPanel.changeBtnPanel(0);
                    this.YJBtn.setVisible(false);
                }
                else if (this.typeBtn == 2) {
                    this.meridiansMainJpanel.refreshAllAttribute();
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.meridiansMainJpanel.refreshAllAttribute();
                    this.YJBtn.setVisible(false);
                }
                else if (this.typeBtn == 2) {
                    this.divisionGatePanel2.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.copyOfSkillTYCPanel.changeBtnPanel(0);
                    this.YJBtn.setVisible(false);
                }
                else if (this.typeBtn == 2) {
                    this.divisionGatePanel2.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("关")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.copyOfSkillTYCPanel.changeBtnPanel(0);
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.meridiansMainJpanel.refreshAllAttribute();
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.divisionGatePanel2.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("关") && this.typeBtn == 0) {
                this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                this.YJXYbtn();
            }
        }
        else {
            for (int i = 0; i < this.mainBtns.length; ++i) {
                try {
                    this.mainBtns[i].setIcons(CutButtonImage.cuts(this.getBtnPanelPath(i, i == this.typeBtn)));
                    if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.copyOfSkillTYCPanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 2) {
                            this.meridiansMainJpanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 3) {
                            this.divisionGatePanel2.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.copyOfSkillTYCPanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 2) {
                            this.meridiansMainJpanel.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.meridiansMainJpanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 2) {
                            this.divisionGatePanel2.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.copyOfSkillTYCPanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 2) {
                            this.divisionGatePanel2.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("关")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.copyOfSkillTYCPanel.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.meridiansMainJpanel.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                        if (i == 0) {
                            this.divisionGatePanel.setVisible(i == this.typeBtn);
                        }
                        else if (i == 1) {
                            this.divisionGatePanel2.setVisible(i == this.typeBtn);
                        }
                    }
                    else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("关") && i == 0) {
                        this.divisionGatePanel.setVisible(i == this.typeBtn);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            if (this.typeBtn == 1) {
                SkillMainFrame.getSkillMainFrame().setBounds(150, 85, 773, 556);
                this.offBtn.setBounds(747, 0, 23, 23);
                this.copyOfSkillTYCPanel.ChangeBtnPanel(0, ImageMixDeal.userimg.getRoleShow().getSpecies_id());
            }
            if (typeBtn != 1) {
                SkillMainFrame.getSkillMainFrame().setBounds(276, 176, 576, 447);
                this.offBtn.setBounds(550, 0, 23, 23);
            }
            if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.copyOfSkillTYCPanel.changeBtnPanel(0);
                    this.YJBtn.setVisible(false);
                }
                else if (this.typeBtn == 2) {
                    this.meridiansMainJpanel.refreshAllAttribute();
                    this.YJBtn.setVisible(false);
                }
                else if (this.typeBtn == 3) {
                    this.divisionGatePanel2.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.copyOfSkillTYCPanel.changeBtnPanel(0);
                    this.YJBtn.setVisible(false);
                }
                else if (this.typeBtn == 2) {
                    this.meridiansMainJpanel.refreshAllAttribute();
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.meridiansMainJpanel.refreshAllAttribute();
                    this.YJBtn.setVisible(false);
                }
                else if (this.typeBtn == 2) {
                    this.divisionGatePanel2.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.copyOfSkillTYCPanel.changeBtnPanel(0);
                }
                else if (this.typeBtn == 2) {
                    this.divisionGatePanel2.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("关")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.copyOfSkillTYCPanel.changeBtnPanel(0);
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.meridiansMainJpanel.refreshAllAttribute();
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                if (this.typeBtn == 0) {
                    this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJXYbtn();
                }
                else if (this.typeBtn == 1) {
                    this.divisionGatePanel2.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                    this.YJBtn.setVisible(false);
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("关") && this.typeBtn == 0) {
                this.divisionGatePanel.getRaceSkillPanel(loginResult.getRace_id(), loginResult.getSex());
                this.YJXYbtn();
            }
        }
    }
    
    public String getBtnPanelPath(int btn, boolean is) {
        StringBuffer buffer = new StringBuffer();
        int num = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            buffer.append("inkImg/button1/K100");
            if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                if (btn == 0) {
                    num = 1;
                }
                else if (btn == 1) {
                    num = 3;
                }
                else if (btn == 2) {
                    num = 5;
                }
                else if (btn == 3) {
                    num = 7;
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                if (btn == 0) {
                    num = 1;
                }
                else if (btn == 1) {
                    num = 3;
                }
                else if (btn == 2) {
                    num = 5;
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                if (btn == 0) {
                    num = 1;
                }
                else if (btn == 1) {
                    num = 5;
                }
                else if (btn == 2) {
                    num = 7;
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                if (btn == 0) {
                    num = 1;
                }
                else if (btn == 1) {
                    num = 3;
                }
                else if (btn == 2) {
                    num = 7;
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("关")) {
                if (btn == 0) {
                    num = 1;
                }
                else if (btn == 1) {
                    num = 3;
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                if (btn == 0) {
                    num = 1;
                }
                else if (btn == 1) {
                    num = 5;
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                if (btn == 0) {
                    num = 1;
                }
                else if (btn == 1) {
                    num = 7;
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("关") && btn == 0) {
                num = 1;
            }
        }
        else {
            buffer.append("img/soaringSkill/B");
            if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                if (btn == 0) {
                    num = 63;
                }
                else if (btn == 1) {
                    num = 65;
                }
                else if (btn == 2) {
                    num = 323;
                }
                else if (btn == 3) {
                    num = 325;
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                if (btn == 0) {
                    num = 63;
                }
                else if (btn == 1) {
                    num = 65;
                }
                else if (btn == 2) {
                    num = 323;
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("开")) {
                if (btn == 0) {
                    num = 63;
                }
                else if (btn == 1) {
                    num = 323;
                }
                else if (btn == 2) {
                    num = 325;
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                if (btn == 0) {
                    num = 63;
                }
                else if (btn == 1) {
                    num = 65;
                }
                else if (btn == 2) {
                    num = 325;
                }
            }
            else if (this.tyckg.equals("开") && this.jmkg.equals("关") && this.fmkg.equals("关")) {
                if (btn == 0) {
                    num = 63;
                }
                else if (btn == 1) {
                    num = 65;
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("开") && this.fmkg.equals("关")) {
                if (btn == 0) {
                    num = 63;
                }
                else if (btn == 1) {
                    num = 323;
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("开")) {
                if (btn == 0) {
                    num = 63;
                }
                else if (btn == 1) {
                    num = 325;
                }
            }
            else if (this.tyckg.equals("关") && this.jmkg.equals("关") && this.fmkg.equals("关") && btn == 0) {
                num = 63;
            }
        }
        if (!is) {
            ++num;
        }
        buffer.append(num + ".png");
        return buffer.toString();
    }
    
    public SkillSMGatePanel getDivisionGatePanel() {
        return this.divisionGatePanel;
    }
    
    public void setDivisionGatePanel(SkillSMGatePanel divisionGatePanel) {
        this.divisionGatePanel = divisionGatePanel;
    }
    
    public CharacterMeridiansMainJpanel getMeridiansMainJpanel() {
        return this.meridiansMainJpanel;
    }
    
    public void setMeridiansMainJpanel(CharacterMeridiansMainJpanel meridiansMainJpanel) {
        this.meridiansMainJpanel = meridiansMainJpanel;
    }
    
    public SkillSMGatePanel2 getDivisionGatePanel2() {
        return this.divisionGatePanel2;
    }
    
    public void setDivisionGatePanel2(SkillSMGatePanel2 divisionGatePanel2) {
        this.divisionGatePanel2 = divisionGatePanel2;
    }
    
    public int getTypeBtn() {
        return this.typeBtn;
    }
    
    public void setTypeBtn(int typeBtn) {
        this.typeBtn = typeBtn;
    }
    
    public SkillMainBtn[] getMainBtns() {
        return this.mainBtns;
    }
    
    public CopyOfSkillTYCPanel getCopyOfSkillTYCPanel() {
        return this.copyOfSkillTYCPanel;
    }
}
