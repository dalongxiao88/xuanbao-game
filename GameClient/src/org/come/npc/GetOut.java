package org.come.npc;

import org.come.bean.LoginResult;
import java.math.BigDecimal;
import com.tool.role.GetExp;
import org.come.until.AnalysisString;
import org.come.until.Util;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;
import com.tool.PlayerKill.PKSys;
import org.come.Frame.ZhuFrame;
import org.come.until.UserData;
import org.come.bean.QuoteOutBean;
import org.come.action.NpcMenuAction;

public class GetOut implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("哦?大爷是来办理出狱手续")) {
            this.NOxinghui();
        }
        else if (type.equals("这是8888W,小小意识")) {
            this.xinghui();
        }
        else if (type.equals("我是来办理出狱手续")) {
            this.Out((QuoteOutBean)null);
        }
        else if (type.equals("我是来自首的")) {
            this.initiative();
        }
    }
    
    public void NOxinghui() {
        Creeps.getfight(16);
    }
    
    public void xinghui() {
        if (UserData.uptael(88888888L)) {
            this.Out((QuoteOutBean)null);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("金钱不足88888888");
        }
    }
    
    public void Out(QuoteOutBean bean) {
        if (bean == null) {
            bean = new QuoteOutBean();
        }
        PKSys pkSys = PKSys.getPkSys();
        pkSys.setPk2(0);
        RoleData.getRoleData().getLoginResult().setTaskDaily(pkSys.splicing());
        bean.setData(RoleData.getRoleData().getLoginResult().getTaskDaily());
        String sendmes = Agreement.getAgreement().QuoteOutAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessageUntil.toServer(sendmes);
        ZhuFrame.getZhuJpanel().addPrompt2("好好做人");
    }
    
    public void initiative() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        PKSys pkSys = PKSys.getPkSys();
        pkSys.setPk2((Util.random.nextInt(10) < 9) ? 2 : 1);
        pkSys.setPk1(pkSys.getPk1() - 1);
        if (pkSys.getPk1() < 1) {
            pkSys.setPk1(1);
        }
        if (pkSys.getPk1() >= 8 && pkSys.getPk3() % 4 == 3) {
            long exp = loginResult.getExperience().longValue();
            long expmax = GetExp.getRoleExp(loginResult.getTurnAround(), AnalysisString.lvlint((int)loginResult.getGrade()));
            expmax = (long)((double)expmax * 0.6);
            exp -= expmax;
            ZhuFrame.getZhuJpanel().addPrompt2("你损失了" + expmax + "经验");
            loginResult.setExperience(new BigDecimal(exp));
            pkSys.setPk3(pkSys.getPk3() + 1);
        }
        pkSys.setPk4(pkSys.getPk4() + 1);
        loginResult.setTaskDaily(pkSys.splicing());
        QuoteOutBean bean = new QuoteOutBean();
        bean.setData(loginResult.getTaskDaily());
        String sendmes = Agreement.getAgreement().QuoteOutAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessageUntil.toServer(sendmes);
        ZhuFrame.getZhuJpanel().addPrompt2("好好改造,重新做人");
    }
}
