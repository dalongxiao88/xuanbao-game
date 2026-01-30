package org.come.control;

import org.skill.panel.SkillMainPanel;
import com.tool.role.BaseMeridians;
import org.skill.frame.SkillMainFrame;
import com.tool.role.RoleProperty;
import org.come.until.FormsManagement;
import org.come.Jpanel.XYJpanel;
import org.come.Frame.XYJframe;
import org.come.Jpanel.XYXYDJpanel;
import org.come.Frame.XYXYDJframe;
import org.come.action.FromServerAction;

public class RolechangeControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (mes.split("-")[0].equals("XY")) {
            if (!mes.split("-")[1].equals("null")) {
                int dianshu = Integer.parseInt(mes.split("-")[1].split("#")[mes.split("-")[1].split("#").length - 1]);
                XYXYDJframe.getXYXYDJfeame().getXyxydJpanel();
                XYXYDJpanel.CS(dianshu, mes.split("-")[1]);
                XYJframe.getXYJframe().getXyJpanel();
                XYJpanel.CS(dianshu, mes.split("-")[1]);
            }
            else {
                XYXYDJframe.getXYXYDJfeame().getXyxydJpanel();
                XYXYDJpanel.CS(0, null);
                XYJframe.getXYJframe().getXyJpanel();
                XYJpanel.CS(0, null);
            }
            FormsManagement.showForm(125);
            return;
        }
        else if (mes.split("-")[0].equals("XY1")) {
            if (XYJframe.getXYJframe().getXyJpanel().isVisible()) {
                FormsManagement.HideForm(124);
            }
            if (!mes.split("-")[1].equals("null")) {
                int dianshu = Integer.parseInt(mes.split("-")[1].split("#")[mes.split("-")[1].split("#").length - 1]);
                XYJframe.getXYJframe().getXyJpanel();
                XYJpanel.CS(dianshu, mes.split("-")[1]);
            }
            else {
                XYXYDJframe.getXYXYDJfeame().getXyxydJpanel();
                XYXYDJpanel.CS(0, null);
                XYJframe.getXYJframe().getXyJpanel();
                XYJpanel.CS(0, null);
            }
            FormsManagement.showForm(124);
            return;
        }
        else {
            BaseMeridians upMeridians = RoleProperty.getRoleProperty().upMeridians(mes);
            RoleProperty.ResetEw();
            if (upMeridians != null && FormsManagement.getInternalForm2(82) != null && FormsManagement.getframe(82).isVisible()) {
                SkillMainPanel skillMainPanel = SkillMainFrame.getSkillMainFrame().getSkillMainPanel();
                if (skillMainPanel.getTypeBtn() == 2) {
                    skillMainPanel.getMeridiansMainJpanel().serverRefreshView(upMeridians);
                }
            }
            return;
        }
    }
}
