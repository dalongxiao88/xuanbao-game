package org.come.until;

import come.tool.Stall.AssetUpdate;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.reward.DrawnitemsAction;
import org.come.bean.LoginResult;
import org.come.handler.SendMessage;
import org.come.model.Achievement;
import org.come.protocol.Agreement;
import org.come.server.GameServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功绩千秋
 *
 * @author admin
 */
public class AchievemUtil {

    /**
     * 检测是否完成功绩
     *
     * @param loginResult
     * @param msg
     */
    public static void detectionAchievem(LoginResult loginResult, String msg) {
        ChannelHandlerContext ctx = GameServer.getRoleNameMap().get(loginResult.getRolename());
        ConcurrentHashMap<Integer, Achievement> all = GameServer.getAchievementAll();
        List<Achievement> achievementList = new ArrayList<>();
        if (all != null) {
            all.forEach((key, value) -> achievementList.add(value));
            Map<String, List<Achievement>> mapl = new HashMap<>();
            if (achievementList != null) {
                for (Achievement achievement : achievementList) {
                    mapl.computeIfAbsent(achievement.getSign(), k -> new ArrayList<>()).add(achievement);
                }
                if (mapl.get(msg) != null) {
                    List<Achievement> list = mapl.get(msg);
                    for (Achievement achievement : list) {
                        if (!loginResult.getAchieveRecordtype(achievement.getConditions()).equals("-1")) {
                            int num = Integer.parseInt(loginResult.getAchieveRecordtype(achievement.getConditions())) + 1;
                            if (!loginResult.getAchieveRecordtype(achievement.getConditions()).equals("") && num == Integer.parseInt(achievement.getNum())) {
                                loginResult.setAchieveRecord(DrawnitemsAction.Splice(loginResult.getAchieveRecord(), achievement.getConditions() + "=1", 2));
                                loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "功绩=" + achievement.getPrice(), 2));
                                AssetUpdate assetUpdate = new AssetUpdate();
                                assetUpdate.setType(AssetUpdate.USEGOOD);
                                assetUpdate.setData("功绩千秋=" + achievement.getConditions() + "=1=2");
                                SendMessage.sendMessageToSlef(ctx,
                                        Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                                assetUpdate = new AssetUpdate();
                                assetUpdate.setType(AssetUpdate.USEGOOD);
                                assetUpdate.setData("功绩=" + achievement.getPrice() + "=2");
                                SendMessage.sendMessageToSlef(ctx,
                                        Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement
                                        .getAgreement().roleAchieveAgreement("功绩千秋=" + achievement.getConditions()));
                            } else if (loginResult.getAchieveRecordtype(achievement.getConditions()).equals("") || num < Integer.parseInt(
                                    achievement.getNum())) {
                                AssetUpdate assetUpdate = new AssetUpdate();
                                assetUpdate.setType(AssetUpdate.USEGOOD);
                                loginResult.setAchieveRecord(
                                        DrawnitemsAction.Splice(loginResult.getAchieveRecord(), achievement.getConditions() + "=1", 2));
                                assetUpdate.setData("功绩千秋=" + achievement.getConditions() + "=1=2");
                                SendMessage.sendMessageToSlef(ctx,
                                        Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                            }
                        }
                    }
                }
            }
        }
    }

}
