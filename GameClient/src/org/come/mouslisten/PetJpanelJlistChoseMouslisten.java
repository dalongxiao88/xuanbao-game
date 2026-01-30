package org.come.mouslisten;

import com.tool.Document.RichDocument;
import com.tool.PanelDisplay.PetPanelShow;
import com.tool.role.RoleData;
import com.tool.role.RoleLingFa;
import org.come.Frame.TestPetJframe;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.*;
import org.come.bean.ConfigureBean;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import org.come.model.Configure;
import org.come.model.Lingbao;
import org.come.until.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.math.BigDecimal;
import java.util.Map;

public class PetJpanelJlistChoseMouslisten implements MouseListener, MouseMotionListener
{
    private JList<String> listpet;
    private TestPetJpanel testPetJpanel;
    private SummonPetJpanel summonPetJpanel;
    private SummonBaoJpanel summonBaoJpanel;
    private int ndsl;

    public PetJpanelJlistChoseMouslisten(JList<String> listpet, TestPetJpanel testPetJpanel) {
        this.ndsl = 4;
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (configure.getNdslsx() != null) {
            this.ndsl = Integer.parseInt(configure.getNdslsx());
        }
        this.listpet = listpet;
        this.testPetJpanel = testPetJpanel;
    }

    public PetJpanelJlistChoseMouslisten(JList<String> listpet, SummonPetJpanel summonPetJpanel) {
        this.ndsl = 4;
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (configure.getNdslsx() != null) {
            this.ndsl = Integer.parseInt(configure.getNdslsx());
        }
        this.listpet = listpet;
        this.summonPetJpanel = summonPetJpanel;
    }

    public PetJpanelJlistChoseMouslisten(JList<String> listpet, SummonBaoJpanel summonBaoJpanel) {
        this.ndsl = 4;
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (configure.getNdslsx() != null) {
            this.ndsl = Integer.parseInt(configure.getNdslsx());
        }
        this.listpet = listpet;
        this.summonBaoJpanel = summonBaoJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(6);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.summonBaoJpanel != null) {
            if (e.getButton() == 1 && this.listpet.getSelectedIndex() != -1) {
                Lingbao lingBao = RoleLingFa.getRoleLingFa().getLingBao(this.listpet.getSelectedIndex());
                this.summonBaoJpanel.showBaoValue(lingBao);
                if (FormsManagement.getframe(711).isVisible()) {
                    RoleLingFa.showProperty(lingBao);
                }
            }
        }
        else {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            if (UserMessUntil.getPetListTable().size() != 0) {
                if (this.summonPetJpanel != null) {
                    if (e.getButton() == 1 && this.listpet.getSelectedIndex() != -1) {
                        RoleSummoning roleSummoning = (RoleSummoning)UserMessUntil.getPetListTable().get(this.listpet.getSelectedIndex());
                        this.summonPetJpanel.showPetValue(roleSummoning);
                    }
                }
                else {
                    if (this.listpet.getSelectedIndex() != -1) {
                        UserMessUntil.setChosePetMes((RoleSummoning)UserMessUntil.getPetListTable().get(this.listpet.getSelectedIndex()));
                    }
                    if (e.getButton() == 1 && UserMessUntil.getChosePetMes() != null) {
                        if (e.isShiftDown()) {
                            try {
                                JTextField SendMes = ZhuFrame.getZhuJpanel().getSendMes();
                                ((RichDocument)SendMes.getDocument()).insertRich(SendMes.getCaretPosition(), 3, UserMessUntil.getChosePetMes().getSid(), "[" + UserMessUntil.getChosePetMes().getSummoningname() + "]", "G", null);
                            }
                            catch (BadLocationException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE10)) {
                            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                            if (UserMessUntil.getChosePetMes().getPetlock() == 1) {
                                ZhuFrame.getZhuJpanel().addPrompt("该召唤兽已加锁");
                            }
                            else {
                                UserMessUntil.getChosePetMes().setPetlock(1);
                                try {
                                    SendRoleAndRolesummingUntil.sendRoleSumming(UserMessUntil.getChosePetMes());
                                }
                                catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                ZhuFrame.getZhuJpanel().addPrompt("加锁成功");
                            }
                        }
                        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE11) && UserMessUntil.getChosePetMes().getPetlock() == 1) {
                            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                            UserMessUntil.getChosePetMes().setPetlock(0);
                            try {
                                SendRoleAndRolesummingUntil.sendRoleSumming(UserMessUntil.getChosePetMes());
                            }
                            catch (Exception e3) {
                                e3.printStackTrace();
                            }
                            ZhuFrame.getZhuJpanel().addPrompt("解锁成功");
                        }
                    }
                    if (RoleData.getRoleData().getLoginResult().getSummoning_id() == null || RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(UserMessUntil.getChosePetMes().getSid()) != 0) {
                        this.testPetJpanel.getBtnwar().setText("参战");
                        this.testPetJpanel.getBtnwar().setTypeBtn(Integer.valueOf(2));
                    }
                    else {
                        this.testPetJpanel.getBtnwar().setText("休息");
                        this.testPetJpanel.getBtnwar().setTypeBtn(Integer.valueOf(3));
                    }
                    PetPanelShow.ShowMesForJpanel(false);
                    if (this.listpet.getSelectedIndex() == -1) {
                        return;
                    }
                    PetPanelShow.changeGrade((int)((RoleSummoning)UserMessUntil.getPetListTable().get(this.listpet.getSelectedIndex())).getGrade(), ((RoleSummoning)UserMessUntil.getPetListTable().get(this.listpet.getSelectedIndex())).getSummoningname());
                    if (((RoleSummoning)UserMessUntil.getPetListTable().get(this.listpet.getSelectedIndex())).getInnerGoods() != null && !UserMessUntil.getChosePetMes().getInnerGoods().equals("")) {
                        String[] strings = ((RoleSummoning)UserMessUntil.getPetListTable().get(this.listpet.getSelectedIndex())).getInnerGoods().split("\\|");
                        for (int i = 0; i < this.ndsl; ++i) {
                            TestPetJpanel.getLabNedan()[i].setGoodstable(null);
                            TestPetJframe.getTestPetJframe().getTestPetJpanel().remove(TestPetJpanel.getLabNedan()[i]);
                        }
                        if (strings.length > 0) {
                            for (int i = 0; i < strings.length; ++i) {
                                TestPetJframe.getTestPetJframe().getTestPetJpanel().add(TestPetJpanel.getLabNedan()[i]);
                                TestPetJpanel.getLabNedan()[i].setGoodstable((Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(strings[i])));
                            }
                        }
                    }
                    else {
                        for (int j = 0; j < this.ndsl; ++j) {
                            TestPetJpanel.getLabNedan()[j].setGoodstable(null);
                            TestPetJframe.getTestPetJframe().getTestPetJpanel().remove(TestPetJpanel.getLabNedan()[j]);
                        }
                    }
                    if (FormsManagement.getframe(18).isVisible()) {
                        ChosePetSkillsMouslisten.refreshPetSkills();
                    }
                    if (FormsManagement.getframe(601).isVisible()) {
                        ChosePetLxMouslisten.refreshPetSkills();
                    }
                    if (FormsManagement.getframe(602).isVisible()) {
                        FormsManagement.HideForm(602);
                    }
                    if (((RoleSummoning)UserMessUntil.getPetListTable().get(this.listpet.getSelectedIndex())).getTurnRount() >= 4) {
                        TestPetJframe.getTestPetJframe().getTestPetJpanel().getLingxi().setVisible(true);
                    }
                    else {
                        TestPetJframe.getTestPetJframe().getTestPetJpanel().getLingxi().setVisible(false);
                    }
                }
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.summonPetJpanel == null && this.summonBaoJpanel == null) {
            ZhuFrame.getZhuJpanel().pettext();
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.summonPetJpanel == null && this.summonBaoJpanel == null && e.getY() / 25 < this.listpet.getModel().getSize()) {
            int idx = this.listpet.locationToIndex(e.getPoint());
            this.listpet.setCellRenderer(new MyRenderer());
            ZhuFrame.getZhuJpanel().creatpettext((RoleSummoning)UserMessUntil.getPetListTable().get(idx));
        }
    }
}
