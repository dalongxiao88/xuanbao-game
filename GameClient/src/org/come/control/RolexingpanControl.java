package org.come.control;

import org.come.xingpan.JpanelXingBackMain;
import org.come.xingpan.JpanelXingCardMain;
import com.tool.role.BaseXingpans;
import org.come.xingpan.StarSoulRefinedJpane;
import org.come.xingpan.JframeXingBackMain;
import org.come.xingpan.JframeXingCardMain;
import org.come.until.FormsManagement;
import com.tool.role.RoleProperty;
import org.come.xingpan.StarSoulRefinedJframe;
import org.come.action.FromServerAction;

public class RolexingpanControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (mes.startsWith("H")) {
            StarSoulRefinedJpane starSoulRefinedJpane = StarSoulRefinedJframe.getStarSoulRefinedJframe().getStarSoulRefinedJpane();
            starSoulRefinedJpane.init(Integer.parseInt(mes.substring(1)) + 1);
        }
        else {
            BaseXingpans upXingpans = RoleProperty.getRoleProperty().upXingpans(mes.replaceAll("\"", ""));
            RoleProperty.ResetEw();
            if (upXingpans == null) {
                return;
            }
        }
        if (FormsManagement.getInternalForm2(122) != null) {
            JpanelXingCardMain jpanelXingCardMain = JframeXingCardMain.getJframeSummonEquipMain().getJpanelXingCardMain();
            jpanelXingCardMain.update(false);
        }
        if (FormsManagement.getInternalForm2(121) != null) {
            JpanelXingBackMain jpanelXingBackMain = JframeXingBackMain.getJframeSummonEquipMain().getJpanelXingBackMain();
            jpanelXingBackMain.init();
        }
        if (FormsManagement.getInternalForm2(120) != null) {
            StarSoulRefinedJpane starSoulRefinedJpane = StarSoulRefinedJframe.getStarSoulRefinedJframe().getStarSoulRefinedJpane();
            starSoulRefinedJpane.init();
        }
    }
}
