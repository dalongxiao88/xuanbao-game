package org.come.control;

import org.come.until.Util;
import org.come.bean.LoginResult;
import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import org.come.action.NpcMenuAction;

public class QZControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        // TRACE[C-05][2026-03-13]: 去除钱庄菜单分派中的反编译 hashCode 结构。
        if (type == null) {
            return;
        }
        switch (type) {
            case "我的钱太多了，想存起来":
                this.Savemoney();
                break;
            case "我没有钱花了，想把存款拿出来":
                this.Withdrawmoney();
                break;
            case "查看我的保险箱":
                this.query();
                break;
            default:
                break;
        }
    }
    
    public void Savemoney() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getGold().compareTo(new BigDecimal(0)) == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("你金钱为：#R" + loginResult.getMoneyshop());
            return;
        }
        FormsManagement.showForm(911);
    }
    
    public void Withdrawmoney() {
        if (Util.isCanBuyOrno()) {
            return;
        }
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getMoneyshop().compareTo(new BigDecimal(0)) == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("你在钱庄存款为：#R" + loginResult.getMoneyshop());
            return;
        }
        FormsManagement.showForm(912);
    }
    
    public void query() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getMoneyshop().compareTo(new BigDecimal(0)) == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("别忽悠我，你的保险箱中根本没有银两么。");
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("你在钱庄存款为：#R" + loginResult.getMoneyshop());
        }
    }
}
