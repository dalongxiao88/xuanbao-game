package org.come.action.sys;

import org.come.service.TtModelService;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import java.util.concurrent.ConcurrentHashMap;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import org.come.bean.TtModel;
import org.come.until.AllServiceUtil;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.bean.UserRoleArrBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class OrderByRoleAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        if (message == null) {
            return;
        }
        Integer type = Integer.valueOf(Integer.parseInt(message));
        UserRoleArrBean bean = new UserRoleArrBean();
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (StringUtils.isBlank(roleInfo.getTTJIANGLI())) {
            roleInfo.setTTJIANGLI("0|0|0|0|0|0");
        }
        String[] ttAward = roleInfo.getTTJIANGLI().split("\\|");
        if (ttAward.length < 4) {
            roleInfo.setTTJIANGLI(ttAward[0] + "|" + ttAward[1] + "|" + ttAward[2] + "|0");
        }
        if (ttAward.length < 5) {
            roleInfo.setTTJIANGLI(ttAward[0] + "|" + ttAward[1] + "|" + ttAward[2] + "|" + ttAward[3] + "|0|0");
        }
        List<LoginResult> list = (List<LoginResult>)GameServer.allBangList.get(type);
        if ((int)type == 8) {
            TtModelService ttModelService = AllServiceUtil.getTtModelService();
            List<TtModel> ttConfig = ttModelService.getTtConfig();
            if (ttConfig.size() != 0) {
                String seasonInfo = "";
                TtModel ttModel = (TtModel)ttConfig.get(0);
                String str = "第 " + ttModel.getCurrentSeason() + " 赛季";
                bean.setCurrSeason(str);
                Date seasonStartTime = ttModel.getSeasonStartTime();
                Date seasonEndTime = ttModel.getSeasonEndTime();
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(seasonStartTime);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(seasonEndTime);
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(new Date());
                int day1 = calendar1.get(6);
                int day2 = calendar2.get(6);
                int day3 = calendar3.get(6);
                seasonInfo = new SimpleDateFormat("yyyy年MM月dd日").format(seasonStartTime) + " - " + new SimpleDateFormat("yyyy年MM月dd日").format(seasonEndTime);
                if ((int)ttModel.getIsOpen() == 1) {
                    if (day2 - day3 < 1) {
                        seasonInfo += ",当前赛季已结束";
                    }
                    else {
                        seasonInfo = seasonInfo + ",当前赛季距离结束还有" + (day2 - day3) + "天";
                    }
                }
                else if ((int)ttModel.getIsOpen() == 3) {
                    seasonInfo += ",当前赛季已结束";
                }
                bean.setSeasonInfo(seasonInfo);
            }
            if (GameServer.allBangList == null) {
                GameServer.allBangList = new ConcurrentHashMap<>();
            }
            GameServer.allBangList.put(Integer.valueOf(8), AllServiceUtil.getRoleTableService().selectOrderByType(Integer.valueOf(8)));
        }
        bean.setList(list);
        bean.setIndex((int)type);
        String msg = Agreement.getAgreement().pankinglistAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
