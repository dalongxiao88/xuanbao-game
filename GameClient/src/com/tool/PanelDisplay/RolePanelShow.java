package com.tool.PanelDisplay;

import java.awt.event.MouseEvent;
import com.tool.role.GetExp;
import java.math.BigDecimal;
import org.come.Jpanel.TeststateJpanel;
import java.awt.Color;
import org.come.Frame.Teststatejframe;
import org.come.until.Music;
import com.tool.image.ImageMixDeal;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;

public class RolePanelShow implements MouseListener
{
    public static void Show() {
        if (!FormsManagement.getframe(0).isVisible()) {
            PetAddPointMouslisten.getplayerValue();
            changeGrade((int)ImageMixDeal.userimg.getRoleShow().getGrade());
            FormsManagement.showForm(0);
            Music.addyinxiao("开关窗口.mp3");
        }
        else {
            FormsManagement.HideForm(0);
            Music.addyinxiao("关闭窗口.mp3");
        }
    }
    
    public static void changeGrade(int grade) {
        TeststateJpanel teststateJpanel = Teststatejframe.getTeststatejframe().getTeststateJpanel();
        if (grade <= 102) {
            teststateJpanel.getLabrolelevel().setForeground(new Color(14, 159, 38));
            teststateJpanel.getLabrolelevel().setText("0转" + grade + "级");
        }
        else if (grade > 102 && grade <= 210) {
            teststateJpanel.getLabrolelevel().setForeground(new Color(255, 140, 0));
            teststateJpanel.getLabrolelevel().setText("1转" + (grade - 102 + 14) + "级");
        }
        else if (grade > 210 && grade <= 338) {
            teststateJpanel.getLabrolelevel().setForeground(new Color(0, 245, 255));
            teststateJpanel.getLabrolelevel().setText("2转" + (grade - 210 + 14) + "级");
        }
        else if (grade > 338 && grade <= 459) {
            teststateJpanel.getLabrolelevel().setForeground(new Color(117, 9, 9));
            teststateJpanel.getLabrolelevel().setText("3转" + (grade - 338 + 59) + "级");
        }
        else if (grade > 459) {
            teststateJpanel.getLabrolelevel().setForeground(new Color(86, 25, 145));
            teststateJpanel.getLabrolelevel().setText("飞升" + (grade - 459 + 139) + "级");
        }
        teststateJpanel.getLabrolelevel().setForeground(Color.WHITE);
    }
    
    public static BigDecimal accessMaxValue(int grade) {
        BigDecimal MaxExp = null;
        if (grade <= 102) {
            MaxExp = new BigDecimal(GetExp.getRoleExp(0, grade));
        }
        else if (grade > 102 && grade <= 210) {
            MaxExp = new BigDecimal(GetExp.getRoleExp(1, grade - 102 + 15));
        }
        else if (grade > 210 && grade <= 338) {
            MaxExp = new BigDecimal(GetExp.getRoleExp(2, grade - 210 + 15));
        }
        else if (grade > 338 && grade <= 459) {
            MaxExp = new BigDecimal(GetExp.getRoleExp(3, grade - 338 + 59));
        }
        else if (grade > 459) {
            MaxExp = new BigDecimal(GetExp.getRoleExp(4, grade - 459 + 139));
        }
        return MaxExp;
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
}
