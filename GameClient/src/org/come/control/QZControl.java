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
        int n = -1;
        switch (type.hashCode()) {
            case 1188767303: {
                if (type.equals("我的钱太多了，想存起来")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case -720856178: {
                if (type.equals("我没有钱花了，想把存款拿出来")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case -1269916788: {
                if (type.equals("查看我的保险箱")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case -764423533: {
                if (type.equals("我什么也不做")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                this.Savemoney();
                break;
            }
            case 1: {
                this.Withdrawmoney();
                break;
            }
            case 2: {
                this.query();
                break;
            }
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
