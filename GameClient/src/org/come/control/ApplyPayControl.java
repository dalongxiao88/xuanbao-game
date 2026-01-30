package org.come.control;

import org.come.bean.UseCardBean;
import org.come.bean.LoginResult;
import com.tool.image.ImageMixDeal;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.Frame.ZhuFrame;
import com.tool.time.TimeLimit;
import org.come.until.UserData;
import org.come.Frame.TaobaoCourtMainJframe;
import org.come.Frame.GoodDetailedJframe;
import com.tool.role.RoleData;
import org.come.until.GsonUtil;
import org.come.bean.ApplyPayBean;
import org.come.action.FromServerAction;

public class ApplyPayControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (type.equals("applypay")) {
            ApplyPayBean applyPayBean = (ApplyPayBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, ApplyPayBean.class);
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            if (applyPayBean.getAddX() != null) {
                loginResult.setCodecard(loginResult.getCodecard().add(applyPayBean.getAddX()));
                GoodDetailedJframe.getGoodDetailedJframe().getGoodDetailedJpanel().getYonghuXianyu().setText(loginResult.getCodecard() + "");
                TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getJadeNum().setText(loginResult.getCodecard() + "");
            }
            if (applyPayBean.getAddC() != null) {
                loginResult.setMoney(Integer.valueOf((int)loginResult.getMoney() + applyPayBean.getAddC().intValue()));
            }
            if (applyPayBean.getAddCZJF() != null) {
                loginResult.setScore(UserData.Splice(loginResult.getScore(), "充值积分=" + applyPayBean.getAddCZJF().longValue(), 2));
            }
            if (applyPayBean.getAddM() != null) {
                loginResult.setPaysum(loginResult.getPaysum().add(applyPayBean.getAddM()));
                loginResult.setDaypaysum(loginResult.getDaypaysum().add(applyPayBean.getAddM()));
                if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getVipShopJpanel().isVisible()) {
                    TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getVipShopJpanel().getShop();
                }
                TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getjfNum().setText(loginResult.getMoney() + "");
            }
            if (applyPayBean.getLowOrHihtpack() != null) {
                loginResult.setLowOrHihtpack(applyPayBean.getLowOrHihtpack().intValue());
            }
            if (applyPayBean.getDayandpayorno() != null) {
                loginResult.setDayandpayorno(loginResult.getDayandpayorno());
                loginResult.setDayfirstinorno(1);
            }
            if (applyPayBean.getUseCardBean() != null) {
                UseCardBean cardBean = applyPayBean.getUseCardBean();
                TimeLimit.getLimits().addLimit(cardBean.getName(), cardBean.getType(), cardBean.getSkin(), cardBean.getValue(), cardBean.getTime());
            }
            if (applyPayBean.getVIPBean() != null) {
                UseCardBean cardBean = applyPayBean.getVIPBean();
                TimeLimit.getLimits().addLimit(cardBean.getName(), cardBean.getType(), cardBean.getSkin(), cardBean.getValue(), cardBean.getTime());
            }
            if (applyPayBean.getMsg() != null) {
                ZhuFrame.getZhuJpanel().addPrompt2(applyPayBean.getMsg());
            }
        }
        else {
            FrameMessageChangeJpanel.addtext(6, "充值失败!!", null, null);
            ImageMixDeal.userimg.Dialogue("充值失败!!");
        }
    }
}
