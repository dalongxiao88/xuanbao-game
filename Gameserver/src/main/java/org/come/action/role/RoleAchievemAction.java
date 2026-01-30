package org.come.action.role;

import come.tool.Good.DropUtil;
import come.tool.Stall.AssetUpdate;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.StringUtils;
import org.come.action.IAction;
import org.come.action.reward.DrawnitemsAction;
import org.come.bean.LoginResult;
import org.come.handler.SendMessage;
import org.come.model.Achievement;
import org.come.model.Dorp;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.until.AchievemUtil;
import org.come.until.GsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功绩千秋
 *  mist
 */
public class RoleAchievemAction implements IAction {
	@Override
	public void action(ChannelHandlerContext ctx, String message) {
		// 获得角色信息
		LoginResult loginResult = GameServer.getAllLoginRole().get(ctx);
		String[] cjs =  message.split("=");
		ConcurrentHashMap<Integer, Achievement> all = GameServer.getAchievementAll();
		List<Achievement> achievementList = new ArrayList<>();
        all.forEach((key, value) -> achievementList.add(value));
        Map<String, Achievement> maps = new HashMap<String, Achievement>();
		for(Achievement achievement : achievementList) {
			maps.put(achievement.getConditions(),  achievement);
		}
		if(cjs[0].equals("领取奖励")) {
			if(maps.get(cjs[1])!=null) {
				Achievement achievement = maps.get(cjs[1]);
				if(!loginResult.getAchieveRecordtype(achievement.getConditions()).equals("-1") && Integer.parseInt(loginResult.getAchieveRecordtype(achievement.getConditions())) >= Integer.parseInt(achievement.getNum())) {
					if(StringUtils.isNotEmpty(achievement.getGoodsId())) {
						Dorp dorp = new Dorp();
						dorp.setDorpValue("物品=0&"+achievement.getGoodsId()+"$1$100");
						DropUtil.getDrop(loginResult,dorp.getDorpValue(),"功绩千秋", 22,1D,null);		
					}
					AssetUpdate assetUpdate = new AssetUpdate();
					assetUpdate.setType(AssetUpdate.USEGOOD);
					loginResult.setAchieveRecord(DrawnitemsAction.Splice(loginResult.getAchieveRecord(), achievement.getConditions()+"=-1", 1));
					assetUpdate.setData("功绩千秋=" + achievement.getConditions() + "=-1=1");
					SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
					return;
				}
			}
		}else if(cjs[0].equals("完成功绩")) {
			AchievemUtil.detectionAchievem(loginResult, cjs[1]);
		}
	}
	
}
