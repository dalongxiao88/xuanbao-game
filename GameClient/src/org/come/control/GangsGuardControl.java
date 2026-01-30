package org.come.control;

import org.come.until.FormsManagement;
import io.netty.util.internal.StringUtil;
import com.tool.role.RoleData;
import org.come.Frame.GangsGuardJframe;
import org.come.action.NpcMenuAction;

public class GangsGuardControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("我要更换主守护")) {
            this.mainGangsGuard();
        }
        else if (type.equals("我要更换副守护")) {
            this.viceGangsGuard();
        }
    }
    
    public void mainGangsGuard() {
        String[] v = "物理吸收率|抗混乱|抗封印|抗昏睡|抗中毒|抗风|抗雷|抗水|抗火|抗鬼火|抗遗忘".split("\\|");
        for (int i = 0; i < 11; ++i) {
            GangsGuardJframe.getGangsGuardJframe().getGangsGuardJpanel().getLabResistance()[i].setText("主" + v[i]);
        }
        String sh = RoleData.getRoleData().getLoginResult().getResistance();
        String[] kx = sh.split("\\|");
        String zhu = kx[0].substring(2);
        if (StringUtil.isNullOrEmpty(zhu)) {
            GangsGuardJframe.getGangsGuardJframe().getGangsGuardJpanel().getLabCrap().setText("很高兴为您服务，您目前没有主守护，请选择您的主守护。");
        }
        else {
            GangsGuardJframe.getGangsGuardJframe().getGangsGuardJpanel().getLabCrap().setText("很高兴为您服务，您目前的主守护是【" + zhu + "】，请选择您要更换的主守护。");
        }
        FormsManagement.showForm(53);
    }
    
    public void viceGangsGuard() {
        String[] v = "物理吸收率|抗混乱|抗封印|抗昏睡|抗中毒|抗风|抗雷|抗水|抗火|抗鬼火|抗遗忘".split("\\|");
        for (int i = 0; i < 11; ++i) {
            GangsGuardJframe.getGangsGuardJframe().getGangsGuardJpanel().getLabResistance()[i].setText("副" + v[i]);
        }
        String sh = RoleData.getRoleData().getLoginResult().getResistance();
        String[] kx = sh.split("\\|");
        String fu = kx[1].substring(2);
        if (StringUtil.isNullOrEmpty(fu)) {
            GangsGuardJframe.getGangsGuardJframe().getGangsGuardJpanel().getLabCrap().setText("很高兴为您服务，您目前没有副守护，请选择您的副守护。");
        }
        else {
            GangsGuardJframe.getGangsGuardJframe().getGangsGuardJpanel().getLabCrap().setText("很高兴为您服务，您目前的副守护是【" + fu + "】，请选择您要更换的副守护。");
        }
        FormsManagement.showForm(53);
    }
}
