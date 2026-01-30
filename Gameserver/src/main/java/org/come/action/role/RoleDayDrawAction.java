package org.come.action.role;

import come.tool.FightingData.Battlefield;
import org.come.action.IAction;
import org.come.action.reward.DrawnitemsAction;
import org.come.bean.LoginResult;
import org.come.handler.SendMessage;
import org.come.model.Dorp;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.until.GsonUtil;

import come.tool.Good.DropUtil;
import come.tool.Stall.AssetUpdate;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 日常任务抽奖
 *  mist
 */
public class RoleDayDrawAction implements IAction {
	//对应的掉落ID
	private static String[] XG = {"2508","2509"};
	private static String[] TG = {"2508","2509"};
	private static String[] GW = {"2508","2509"};
	private static String[] XL = {"2508","2509"};
	private static String[] YW = {"2508","2509"};
	private static String[] BX = {"2508","2509"};
	@Override
	public void action(ChannelHandlerContext ctx, String message) {
		// 获得角色信息
		LoginResult loginResult = GameServer.getAllLoginRole().get(ctx);
		// 天宫=1
		String[] cjs =  message.split("=");
		int num = loginResult.getDayDrawtype(cjs[1]);
		if(cjs[0].equals("日常抽奖")){
			if(num>=2) {
				SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#W今日#G"+cjs[1]+"#W抽奖次数已用尽，请明日再来！"));
				return;
			}
			String msgtxt = "DayDraw日常任务"+cjs[1]+"抽奖";
			String[] jl = cjs[1].equals("小鬼")?XG:cjs[1].equals("天庭")?TG:cjs[1].equals("鬼王")?GW:cjs[1].equals("修罗")?XL:cjs[1].equals("域外")?YW:cjs[1].equals("宝象")?BX:new String[0];
			if(jl!=null && jl.length>0) {
//				Dorp dorp=GameServer.getDorp(jl[num]);
				Dorp dorp = new Dorp();
				String[] ids = cjs[2].split("\\|");
				String id = ids[Battlefield.random.nextInt(ids.length)];
				dorp.setDorpValue("物品=0&"+id+"$1$100");
				if (dorp!=null) {
					DropUtil.getDrop(loginResult,dorp.getDorpValue(),msgtxt, 22,1D,null);
				}
			}
			AssetUpdate assetUpdate = new AssetUpdate();
			assetUpdate.setType(AssetUpdate.USEGOOD);
			loginResult.setDayDraw(DrawnitemsAction.Splice(loginResult.getDayDraw(), cjs[1]+"=1", 2));
			assetUpdate.setData("日常任务抽奖=" + cjs[1]+"=1=2");
			SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
			return;
		}else if(cjs[0].equals("获取奖池")){
			String[] jl = cjs[1].equals("小鬼")?XG:cjs[1].equals("天庭")?TG:cjs[1].equals("鬼王")?GW:cjs[1].equals("修罗")?XL:cjs[1].equals("域外")?YW:cjs[1].equals("宝象")?BX:new String[0];
			String goods = "";
			if(jl!=null && jl.length>0) {
				Dorp dorp=GameServer.getDorp(jl[num]);
				if (dorp!=null) {
					goods = dorp.getDorpValue();
				}
			}
			String[] vss = goods.split("&");
			List<String> goodsIds = new ArrayList<>();
			for(int i=1;i<vss.length;i++){
				String[] v = vss[i].split("\\$");
				for(String vd : v[0].split("-")){
					goodsIds.add(vd);
				}
			}
			Collections.shuffle(goodsIds);
			String doodsid = "";
			for(int i=0;i<8;i++){
				doodsid += goodsIds.get(i)+"|";
			}
			if(!goods.equals("")){
				SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().roledaydrawAgreement("获得奖池="+doodsid));
			}else{
				SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("未查询到奖池信息！"));
				return;
			}


			return;
		}

	}
	
}
