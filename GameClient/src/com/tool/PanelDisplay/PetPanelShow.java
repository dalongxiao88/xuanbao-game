package com.tool.PanelDisplay;

import org.come.until.AnalysisString;
import java.awt.Color;
import org.come.mouslisten.PetAddPointMouslisten;
import java.awt.event.MouseEvent;
import org.come.Frame.SummonJframe;
import org.come.entity.RoleSummoning;
import java.util.List;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.Music;
import org.come.until.FormsManagement;
import org.come.Frame.TestPetJframe;
import org.come.Jpanel.TestPetJpanel;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.event.MouseListener;

public class PetPanelShow implements MouseListener
{
    public static void Show() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int ndsl = 4;
        if (configure.getNdslsx() != null) {
            ndsl = Integer.parseInt(configure.getNdslsx());
        }
        List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        TestPetJpanel.showListModel(pets, RoleData.getRoleData().getLoginResult().getSummoning_id());
        for (int i = 0; i < ndsl; ++i) {
            TestPetJpanel.getLabNedan()[i].setGoodstable(null);
            TestPetJframe.getTestPetJframe().getTestPetJpanel().remove(TestPetJpanel.getLabNedan()[i]);
        }
        if (pets.size() != 0) {
            UserMessUntil.setChosePetMes(UserMessUntil.getPetRgid(RoleData.getRoleData().getLoginResult().getSummoning_id()));
            ShowMesForJpanel();
        }
        if (!FormsManagement.getframe(6).isVisible()) {
            FormsManagement.showForm(6);
            Music.addyinxiao("开关窗口.mp3");
        }
        else {
            FormsManagement.HideForm(6);
            Music.addyinxiao("关闭窗口.mp3");
        }
    }
    
    public static void summonShow() {
        SummonJframe.getSummonJframe().getChatSwitchJpanel().updateInfo();
    }
    
    public static void summonPetShow() {
        SummonJframe.getSummonJframe().getChatSwitchJpanel().updatePetInfo();
    }
    
    public static void summonBaoShow() {
        SummonJframe.getSummonJframe().getChatSwitchJpanel().updateBaoInfo();
    }
    
    public static void Show1() {
        List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        TestPetJpanel.showListModel(pets, RoleData.getRoleData().getLoginResult().getSummoning_id());
        for (int i = 0; i < 3; ++i) {
            TestPetJpanel.getLabNedan()[i].setGoodstable(null);
            TestPetJframe.getTestPetJframe().getTestPetJpanel().remove(TestPetJpanel.getLabNedan()[i]);
        }
        if (pets.size() != 0) {
            UserMessUntil.setChosePetMes(UserMessUntil.getPetRgid(RoleData.getRoleData().getLoginResult().getSummoning_id()));
            ShowMesForJpanel();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Show();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public static void ShowMesForJpanel() {
        PetAddPointMouslisten.showPetValue(true);
    }
    
    public static void ShowMesForJpanel(boolean showHead) {
        PetAddPointMouslisten.showPetValue(showHead);
    }
    //召唤兽等级颜色
    public static void changeGrade(int grade, String name) {
        if (grade <= 100) {
            TestPetJpanel.getLablevel().setForeground(new Color(255, 255, 0));
            TestPetJpanel.getLablevel().setText("0转" + grade + "级");
            TestPetJpanel.getLabname().setText(name);
        }
        else if (grade > 100 && grade <= 221) {
            TestPetJpanel.getLablevel().setForeground(new Color(255, 255, 0));
            TestPetJpanel.getLablevel().setText("1转" + (grade - 101) + "级");
            TestPetJpanel.getLabname().setText(name);
        }
        else if (grade > 221 && grade <= 362) {
            TestPetJpanel.getLablevel().setForeground(new Color(255, 255, 0));
            TestPetJpanel.getLablevel().setText("2转" + (grade - 222) + "级");
            TestPetJpanel.getLabname().setText(name);
        }
        else if (grade > 362 && grade <= 543) {
            TestPetJpanel.getLablevel().setForeground(new Color(255, 255, 0));
            TestPetJpanel.getLablevel().setText("3转" + (grade - 363) + "级");
            TestPetJpanel.getLabname().setText(name);
        }
        else {
            TestPetJpanel.getLablevel().setForeground(new Color(255, 255, 0));
            TestPetJpanel.getLablevel().setText("点化" + (grade - 544) + "级");
            TestPetJpanel.getLabname().setText(name);
        }
        TestPetJpanel.getTestPetJpanel().changeSoaringPanel(AnalysisString.petTurnRount(grade));
    }
}
