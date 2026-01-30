package org.come.Jpanel;

import com.tool.tcp.NewPart;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.until.FormsManagement;
import org.come.bean.Skill;
import org.come.Frame.MsgJframe;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.image.ImageMixDeal;
import org.come.until.Util;
import org.come.until.SendRoleAndRolesummingUntil;
import org.come.mouslisten.PetAddPointMouslisten;
import com.tool.pet.PetProperty;
import org.come.summonequip.JpanelSummonEquipMain;
import org.come.until.CutButtonImage;
import java.util.ArrayList;
import org.come.summonequip.JframeSummonEquipMain;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import org.come.mouslisten.PetEquipMouslisten;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.PetEquipmentBtn;
import org.come.mouslisten.GoodsMouslisten;
import com.tool.btn.goodbtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PetEquipmentJpanel extends JPanel
{
    public JLabel[] GoodsListLabel;
    private goodbtn[] btnrights;
    private GoodsMouslisten[] goodsMouslistens;
    private JLabel[] labpetequ;
    private JLabel equipSkillLab;
    private PetEquipmentBtn forgeBtn;
    private String equipSkillMessage;
    private ImageIcon icon;
    
    public PetEquipmentJpanel() {
        this.GoodsListLabel = new JLabel[24];
        this.goodsMouslistens = new GoodsMouslisten[24];
        this.labpetequ = new JLabel[4];
        this.icon = new ImageIcon("img/xy2uiimg/petequipment.png");
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(368, 506));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 67);
            offBtn.setBounds(328, 5, 25, 25);
            this.add(offBtn);
            for (int i = 0; i < 4; ++i) {
                this.labpetequ[i] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        if (this.getIcon() != null) {
                            g.setColor(UIUtils.COLOR_Pack);
                            g.fillRect(0, 0, 39, 39);
                        }
                        super.paintComponent(g);
                    }
                };
                PetEquipMouslisten equipMouslisten = new PetEquipMouslisten(this, i);
                this.labpetequ[i].addMouseListener(equipMouslisten);
                if (i == 3) {
                    this.labpetequ[i].setBounds(275, 173, 39, 39);
                }
                else {
                    this.labpetequ[i].setBounds(41, 56 + i * 49, 39, 39);
                }
                this.add(this.labpetequ[i]);
            }
            for (int i = 0; i < 24; ++i) {
                int row = i % 6;
                int col = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).setBounds(26 + row * 51, 268 + col * 51, 49, 49);
                this.goodsMouslistens[i] = new GoodsMouslisten(i);
                this.GoodsListLabel[i].addMouseListener(this.goodsMouslistens[i]);
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/button1/C0" + (i + 1) + ".png", 0, this, i)).setBounds(332, 265 + i * 35, 35, 31);
                this.add(this.btnrights[i]);
            }
            this.getEquipSkillLab();
            this.getForgeBtn();
        }
        else {
            this.setPreferredSize(new Dimension(360, 510));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 67);
            offBtn.setBounds(334, 0, 23, 23);
            this.add(offBtn);
            for (int i = 0; i < 4; ++i) {
                this.labpetequ[i] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        if (this.getIcon() != null) {
                            g.setColor(UIUtils.Color_BACK);
                            g.fillRect(0, 0, 39, 39);
                        }
                        super.paintComponent(g);
                    }
                };
                PetEquipMouslisten equipMouslisten = new PetEquipMouslisten(this, i);
                this.labpetequ[i].addMouseListener(equipMouslisten);
                if (i == 3) {
                    this.labpetequ[i].setBounds(275, 173, 39, 39);
                }
                else {
                    this.labpetequ[i].setBounds(42, 75 + i * 49, 39, 39);
                }
                this.add(this.labpetequ[i]);
            }
            for (int i = 0; i < 24; ++i) {
                int row = i % 6;
                int col = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).setBounds(26 + row * 51, 268 + col * 51, 49, 49);
                this.goodsMouslistens[i] = new GoodsMouslisten(i);
                this.GoodsListLabel[i].addMouseListener(this.goodsMouslistens[i]);
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/hongmu/SBG.png", 0, this, i)).setBounds(332, 265 + i * 33, 24, 33);
                this.add(this.btnrights[i]);
            }
            this.getEquipSkillLab();
            this.getForgeBtn();
        }
    }
    
    public void btntype(int path) {
        for (int i = 0; i < path; ++i) {
            this.btnrights[i].btntypechange(2);
        }
    }
    
    public void showPet(RoleSummoning pet) {
        for (int i = 0; i < this.labpetequ.length; ++i) {
            this.labpetequ[i].setIcon(null);
        }
        String stype = pet.getStye();
        if (stype == null || stype.length() <= 1) {
            return;
        }
        String[] v = stype.split("\\|");
        for (int j = 1; j < v.length; ++j) {
            String[] vs = v[j].split("-");
            if (vs.length >= 2) {
                Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(vs[1]));
                if (goodstable != null) {
                    ImageIcon icon = GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49);
                    this.labpetequ[Integer.parseInt(vs[0])].setIcon(icon);
                }
            }
        }
        this.trueSkillIdView(v);
        this.equipSkillMessage = this.trueSkillId(v);
    }
    
    public String trueSkillId(String[] v) {
        StringBuffer skillMessage = null;
        ArrayList<Goodstable> summonEquips = JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getSummonEquips(v);
        if (summonEquips.size() >= 3) {
            String[] skillIds = new String[3];
            int j = 0;
            while (j < summonEquips.size()) {
                String[] values = ((Goodstable)summonEquips.get(j)).getValue().split("\\|");
                String valuesMessage = JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getValuesMessage(values, "觉醒技");
                if (valuesMessage != null) {
                    skillIds[j] = valuesMessage;
                    if (j == 2) {
                        String[] skillOne = skillIds[0].split("&");
                        String[] skillTwo = skillIds[1].split("&");
                        String[] skillThree = skillIds[2].split("&");
                        if (skillOne[1].equals(skillTwo[1]) && skillOne[1].equals(skillThree[1])) {
                            skillMessage = new StringBuffer();
                            BigDecimal quality = averageMath(skillOne[2], skillTwo[2], skillThree[2]);
                            BigDecimal exp = averageMath(skillOne[3], skillTwo[3], skillThree[3]);
                            skillMessage.append(skillOne[1]);
                            skillMessage.append("&");
                            skillMessage.append(quality);
                            skillMessage.append("&");
                            skillMessage.append(exp.intValue());
                        }
                    }
                    ++j;
                }
                else {
                    break;
                }
            }
        }
        return (skillMessage == null) ? null : skillMessage.toString();
    }
    
    public void trueSkillIdView(String[] v) {
        ArrayList<Goodstable> summonEquips = JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getSummonEquips(v);
        if (MyIsif.getStyle().equals("水墨")) {
            if (summonEquips.size() < 3) {
                this.equipSkillLab.setIcon(CutButtonImage.getImage("img/lingbao/msg/圆封.png", 28, 28));
            }
            else {
                String[] skillIds = new String[3];
                int j = 0;
                while (j < summonEquips.size()) {
                    String[] values = ((Goodstable)summonEquips.get(j)).getValue().split("\\|");
                    String valuesMessage = JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getValuesMessage(values, "觉醒技");
                    if (valuesMessage != null) {
                        skillIds[j] = valuesMessage.split("&")[1];
                        if (j == 2) {
                            if (skillIds[0].equals(skillIds[1]) && skillIds[0].equals(skillIds[2])) {
                                this.equipSkillLab.setIcon(CutButtonImage.getImage("img/skill/jx.png", -1, -1));
                            }
                            else {
                                this.equipSkillLab.setIcon(CutButtonImage.getImage("img/lingbao/msg/圆封.png", -1, -1));
                            }
                        }
                        ++j;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        else if (summonEquips.size() < 3) {
            this.equipSkillLab.setIcon(CutButtonImage.getImage("img/lingbao/msg/圆封.png", 28, 28));
        }
        else {
            String[] skillIds = new String[3];
            int j = 0;
            while (j < summonEquips.size()) {
                String[] values = ((Goodstable)summonEquips.get(j)).getValue().split("\\|");
                String valuesMessage = JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getValuesMessage(values, "觉醒技");
                if (valuesMessage != null) {
                    skillIds[j] = valuesMessage.split("&")[1];
                    if (j == 2) {
                        if (skillIds[0].equals(skillIds[1]) && skillIds[0].equals(skillIds[2])) {
                            this.equipSkillLab.setIcon(CutButtonImage.getImage("img/skill/jx.png", -1, -1));
                        }
                        else {
                            this.equipSkillLab.setIcon(CutButtonImage.getImage("img/lingbao/msg/圆封.png", -1, -1));
                        }
                    }
                    ++j;
                }
                else {
                    break;
                }
            }
        }
        JpanelSummonEquipMain jpanelSummonEquipMain = JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain();
        if (jpanelSummonEquipMain.getJpanelNowEquip().isVisible()) {
            jpanelSummonEquipMain.getpaneMessage();
        }
    }
    
    public static BigDecimal averageMath(String one, String two, String three) {
        return new BigDecimal(one).add(new BigDecimal(two)).add(new BigDecimal(three)).divide(new BigDecimal(3), 2, 4);
    }
    
    public void Equip(RoleSummoning pet, Goodstable good, int type) {
        String yb = pet.getStye();
        BigDecimal id = null;
        if (type != -1) {
            id = pet.ChangePart(null, type);
            this.labpetequ[type].setIcon(null);
        }
        else {
            type = getPetGoodType(good.getType());
            this.labpetequ[type].setIcon(GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49));
            good.setStatus(Integer.valueOf(1));
            GoodsMouslisten.gooduse(good, 0);
            GoodsListFromServerUntil.Deletebiaoid(good.getRgid());
            id = pet.ChangePart(good, type);
            GoodsListFromServerUntil.fushis.put(good.getRgid(), good);
        }
        Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.remove(id);
        if (goodstable != null) {
            goodstable.setStatus(Integer.valueOf(0));
            GoodsMouslisten.gooduse(goodstable, 0);
            GoodsListFromServerUntil.addGood(goodstable);
        }
        int[] pets = PetProperty.getPetHMASp(pet);
        pet.setBasishp((long)pets[0]);
        pet.setBasismp((long)pets[1]);
        PetAddPointMouslisten.showPetValue();
        TestPetJpanel.part = null;
        if (!yb.equals(pet.getStye())) {
            SendRoleAndRolesummingUntil.sendRoleSumming(pet);
        }
        this.trueSkillIdView(pet.getStye().split("\\|"));
    }
    
    public static int getPetGoodType(Long type) {
        if ((long)type == 510L) {
            return 0;
        }
        if ((long)type == 511L) {
            return 1;
        }
        if ((long)type == 512L) {
            return 2;
        }
        if ((long)type == 729L) {
            return 3;
        }
        return 4;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (Util.zzs == 8) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.icon = new ImageIcon("inkImg/background1/B255.png");
                g.drawImage(this.icon.getImage(), 0, 0, 356, 506, this);
                if (TestPetJpanel.part != null) {
                    TestPetJpanel.part.draw(g, 185, 185, 0, ImageMixDeal.userimg.getTime());
                }
                GoodsListFromServerUntil.draw(g, 26, 268);
            }
            else {
                this.icon = new ImageIcon("img/xy2uiimg/petequipment3Z.png");
                g.drawImage(this.icon.getImage(), 0, 0, 360, 510, this);
                if (TestPetJpanel.part != null) {
                    TestPetJpanel.part.draw(g, 185, 185, 0, ImageMixDeal.userimg.getTime());
                }
                GoodsListFromServerUntil.draw(g, 26, 268);
            }
        }
        else if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B255.png");
            g.drawImage(this.icon.getImage(), 0, 0, 356, 506, this);
            if (TestPetJpanel.part != null) {
                TestPetJpanel.part.draw(g, 185, 185, 0, ImageMixDeal.userimg.getTime());
            }
            GoodsListFromServerUntil.draw(g, 26, 268);
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/petequipment.png");
            g.drawImage(this.icon.getImage(), 0, 0, 360, 510, this);
            if (TestPetJpanel.part != null) {
                TestPetJpanel.part.draw(g, 185, 185, 0, ImageMixDeal.userimg.getTime());
            }
            GoodsListFromServerUntil.draw(g, 26, 268);
        }
    }
    
    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }
    
    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
    }
    
    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }
    
    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }
    
    public GoodsMouslisten[] getGoodsMouslistens() {
        return this.goodsMouslistens;
    }
    
    public void setGoodsMouslistens(GoodsMouslisten[] goodsMouslistens) {
        this.goodsMouslistens = goodsMouslistens;
    }
    
    public JLabel[] getLabpetequ() {
        return this.labpetequ;
    }
    
    public void setLabpetequ(JLabel[] labpetequ) {
        this.labpetequ = labpetequ;
    }
    
    public JLabel getEquipSkillLab() {
        if (this.equipSkillLab == null) {
            (this.equipSkillLab = new JLabel()).setIcon(CutButtonImage.getImage("img/lingbao/msg/圆封.png", -1, -1));
            this.equipSkillLab.setBounds(165, 199, 28, 28);
            this.equipSkillLab.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (PetEquipmentJpanel.this.equipSkillMessage != null) {
                        String[] split = PetEquipmentJpanel.this.equipSkillMessage.split("&");
                        Skill skill = UserMessUntil.getSkillId(split[0]);
                        MsgJframe.getJframe().getJapnel().TYC((skill != null) ? skill.getSkillname() : "", " #cFFFF00【技能等级】 " + JpanelSummonEquipMain.expChangeLevel(Long.parseLong(split[2])) + "#r 【类型】 通用#r#G" + GoodsMsgJpanel.SummonSkillRemark(skill.getRemark(), skill, split[1], JpanelSummonEquipMain.expChangeLevel(Long.parseLong(split[2])) + ""));
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    FormsManagement.HideForm(46);
                }
            });
            this.add(this.equipSkillLab);
        }
        return this.equipSkillLab;
    }
    
    public void setEquipSkillLab(JLabel equipSkillLab) {
        this.equipSkillLab = equipSkillLab;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public PetEquipmentBtn getForgeBtn() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (this.forgeBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.forgeBtn = new PetEquipmentBtn("inkImg/button1/B20.png", 1, "洗练", 1)).setColors(UIUtils.COLOR_BLACK);
                this.forgeBtn.setBounds(150, 230, 59, 24);
            }
            else {
                (this.forgeBtn = new PetEquipmentBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "洗 练", 1)).setBounds(145, 230, 80, 26);
            }
            if (!configure.getLzjskg().equals("3")) {
                this.add(this.forgeBtn);
            }
        }
        return this.forgeBtn;
    }
    
    public void setForgeBtn(PetEquipmentBtn forgeBtn) {
        this.forgeBtn = forgeBtn;
    }
    
    public String getEquipSkillMessage() {
        return this.equipSkillMessage;
    }
    
    public void setEquipSkillMessage(String equipSkillMessage) {
        this.equipSkillMessage = equipSkillMessage;
    }
}
