package org.come.control;

import javax.swing.JLabel;

import org.come.Frame.ImpactGradeJframe;
import org.come.tt.LadderLotteryJframe;
import org.come.daily.JframeDailyMain;
import org.lottery.frame.LotteryMainFrame;
import com.tool.role.RoleData;
import org.come.until.FormsManagement;
import org.come.Frame.QuackGameJframe;
import org.come.until.GsonUtil;
import org.come.bean.QuackGameBean;
import org.come.action.FromServerAction;

public class QuackGameControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        QuackGameBean gameBean = (QuackGameBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, QuackGameBean.class);
        if (gameBean.getType() == 1) {
            String[] picmsg = gameBean.getPetmsg().split("\\|");
            QuackGameJframe.getQuackGameJframe().getGameJpanel().resetGuard(picmsg);
            FormsManagement.showForm(71);
        }
        else if (gameBean.getType() == 2) {
            if (gameBean.getMoney() != null) {
                RoleData.getRoleData().getLoginResult().setGold(RoleData.getRoleData().getLoginResult().getGold().add(gameBean.getMoney()));
            }
            String[] picmsg = gameBean.getPetmsg().split("\\|");
            QuackGameJframe.getQuackGameJframe().getGameJpanel().drawLottery(picmsg, gameBean.getMoney());
        }
        else if (gameBean.getType() == 3) {
            LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getMenuImg(gameBean);
        }
        else if (gameBean.getType() == 4) {
            String[] drawGoods = gameBean.getPetmsg().split("\\|");
            LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().setDrawGoods(drawGoods);
            LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getIntegralLab().setText("当前积分:" + gameBean.getMoney());
        }
        else if (gameBean.getType() == 5) {
            String[] teams = gameBean.getPetmsg().split("\\|");
            JLabel[] firstTeams = JframeDailyMain.getJframeDailyMain().getJpanelDailyMain().getFirstTeams();
            for (int i = 0; i < firstTeams.length; ++i) {
                firstTeams[i].setText((i + 1 <= teams.length) ? teams[i] : "");
            }
        }
        else if (gameBean.getType() == 6) {
            LadderLotteryJframe.getLadderLotteryJframe().getLadderLotteryJpanel().getMenuImg(gameBean);
        }
        else if (gameBean.getType() == 7) {
            LadderLotteryJframe.getLadderLotteryJframe().getLadderLotteryJpanel().getMenuImg(gameBean);
        }else if (gameBean.getType()==10) {
            //抽奖物品
            String[] drawGoods = gameBean.getPetmsg().split("\\|");
            ImpactGradeJframe.getImpactGradeJframe().getImpactGradeJpanel().XYDJInit(drawGoods,gameBean.getEndTime(),gameBean);
//			LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().setDrawGoods(drawGoods);
//			LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel().getIntegralLab().setText("当前积分:" + gameBean.getMoney());
        }
    }
}
