package org.come.control;

import javax.swing.JLabel;
import com.tool.role.RoleProperty;
import org.come.Frame.TestChildJframe;
import java.util.ArrayList;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import org.come.until.UserMessUntil;
import org.come.entity.Baby;
import java.util.List;
import org.come.until.FormsManagement;
import org.come.until.GsonUtil;
import org.come.bean.BabyListBean;
import javax.swing.ImageIcon;
import org.come.action.FromServerAction;

public class BabyControl implements FromServerAction
{
    static ImageIcon icon1;
    static ImageIcon icon2;
    
    public static ImageIcon geticon(int sex) {
        if (sex == 0) {
            if (BabyControl.icon1 == null) {
                BabyControl.icon1 = new ImageIcon("img/baby/0.png");
            }
            return BabyControl.icon1;
        }
        else {
            if (BabyControl.icon2 == null) {
                BabyControl.icon2 = new ImageIcon("img/baby/1.png");
            }
            return BabyControl.icon2;
        }
    }
    
    @Override
    public void controlMessFromServer(String mes, String type) {
        BabyListBean babyListBean = (BabyListBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, BabyListBean.class);
        List<Baby> babyList = babyListBean.getBabyList();
        babyinit(babyList);
        FormsManagement.showForm(1);
    }
    
    public static void babyinit(List<Baby> babyList) {
        if (babyList == null && UserMessUntil.getMyListBaby() != null) {
            return;
        }
        BigDecimal big = RoleData.getRoleData().getLoginResult().getBabyId();
        if (big == null) {
            big = new BigDecimal(-1);
        }
        if (babyList == null) {
            babyList = new ArrayList<>();
        }
        UserMessUntil.setMyListBaby(babyList);
        JLabel[] jLabels = TestChildJframe.getTestChildJframe().getTestChildJpanel().getLabListChild();
        for (int i = 0; i < 6; ++i) {
            Baby baby = null;
            if (babyList.size() > i) {
                baby = (Baby)babyList.get(i);
            }
            if (baby != null) {
                jLabels[i].setIcon(geticon((int)baby.getChildSex()));
                if (big.compareTo(baby.getBabyid()) == 0) {
                    RoleProperty.ResetBaby(baby);
                }
            }
            else {
                jLabels[i].setIcon(null);
            }
        }
        if (babyList.size() == 0) {
            TestChildJframe.getTestChildJframe().getTestChildJpanel().ShowBaby(null);
        }
        else {
            TestChildJframe.getTestChildJframe().getTestChildJpanel().ShowBaby((Baby)babyList.get(0));
        }
    }
}
