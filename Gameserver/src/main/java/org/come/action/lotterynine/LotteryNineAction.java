package org.come.action.lotterynine;

import org.come.bean.NChatBean;
import come.tool.Role.PartJade;
import org.come.entity.Lingbao;
import come.tool.Role.RoleData;
import java.util.Iterator;
import org.come.tool.EquipTool;
import org.come.until.AllServiceUtil;
import org.come.until.SplitLingbaoValue;
import come.tool.Role.RolePool;
import org.come.entity.Goodstable;
import come.tool.Good.UseRoleAction;
import java.util.List;
import org.come.action.monitor.MonitorUtil;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Stall.AssetUpdate;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class LotteryNineAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        String[] result = message.split("\\|");
        if (result.length < 4) {
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        StringBuffer buffer = new StringBuffer();
        int xynum = (int)Integer.valueOf(result[3]);
        if (xynum > 1) {
            int totalxy = 0;
            switch (xynum) {
                case 2: {
                    totalxy = 10;
                    break;
                }
                case 3: {
                    totalxy = 20;
                    break;
                }
                case 4: {
                    totalxy = 50;
                    break;
                }
                case 5: {
                    totalxy = 100;
                    break;
                }
                case 6: {
                    totalxy = 200;
                    break;
                }
                case 7: {
                    totalxy = 400;
                    break;
                }
                case 8: {
                    totalxy = 800;
                    break;
                }
                case 9: {
                    totalxy = 1600;
                    break;
                }
            }
            if (loginResult.getCodecard().longValue() < (long)totalxy) {
                assetUpdate.setMsg("仙玉不足" + totalxy);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                return;
            }
            loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() - (long)totalxy));
            MonitorUtil.getMoney().useX((long)totalxy);
            assetUpdate.setData("X=" + -totalxy);
            buffer.append("你扣除" + totalxy + "仙玉。");
            assetUpdate.setMsg(buffer.toString());
        }
        if (message.startsWith("ADD")) {
            List<Goodstable> goodstables = UseRoleAction.lottery.get(ctx);
            List<Goodstable> goodstables2 = UseRoleAction.lottery1.get(ctx);
            if (goodstables == null && goodstables2 == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你想干嘛？"));
                return;
            }
            int num = (int)Integer.parseInt(result[1]);
            BigDecimal gid = new BigDecimal(result[2]);
            Goodstable goodstable = GameServer.getGood(gid);
            Boolean b = Boolean.valueOf(true);
            if (goodstables != null) {
                for (Goodstable goodstable2 : goodstables) {
                    if (goodstable2.getGoodsid().longValue() == goodstable.getGoodsid().longValue()) {
                        b = Boolean.valueOf(false);
                        break;
                    }
                }
            }
            if (goodstables2 != null) {
                for (Goodstable goodstable2 : goodstables2) {
                    if (goodstable2.getGoodsid().longValue() == goodstable.getGoodsid().longValue()) {
                        b = Boolean.valueOf(false);
                        break;
                    }
                }
            }
            if ((boolean)b) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你想干嘛？??"));
                return;
            }
            if (num >= 10) {
                buffer.append("你获取了");
                buffer.append(num);
                buffer.append("个");
                buffer.append(goodstable.getGoodsname());
                assetUpdate.setMsg(buffer.toString());
                assetUpdate.setType(AssetUpdate.USEGOOD);
                this.sendChuanYin(loginResult, "#c00FFFF使用#Y鸡驴翻翻乐#c00FFFF意外获得#R" + num + "#G个" + goodstable.getGoodsname() + "#c00FFFF，真是人品爆发#49");
            }
            else {
                buffer.append("你获取了");
                buffer.append(num);
                buffer.append("个");
                buffer.append(goodstable.getGoodsname());
                assetUpdate.setMsg(buffer.toString());
                assetUpdate.setType(AssetUpdate.USEGOOD);
            }
            RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
            goodstable.setRole_id(loginResult.getRole_id());
            long yid = gid.longValue();
            for (int i = 0; i < num; ++i) {
                if (i != 0) {
                    goodstable = GameServer.getGood(gid);
                }
                goodstable.setRole_id(loginResult.getRole_id());
                long sid = goodstable.getGoodsid().longValue();
                if (sid >= 70001L && sid <= 70030L) {
                    Lingbao lingbao = SplitLingbaoValue.addling(goodstable.getGoodsname(), loginResult.getRole_id());
                    assetUpdate.setLingbao(lingbao);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(num), Integer.valueOf(20));
                }
                else if (sid >= 69001L && sid <= 69015L) {
                    Lingbao lingbao = SplitLingbaoValue.addfa(sid, loginResult.getRole_id());
                    assetUpdate.setLingbao(lingbao);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(num), Integer.valueOf(20));
                }
                else if (goodstable.getType() == 825L) {
                    if (!goodstable.getValue().equals("")) {
                        String[] v = goodstable.getValue().split("\\|");
                        int suitid = Integer.parseInt(v[0]);
                        int part = Integer.parseInt(v[1]);
                        int pz = Integer.parseInt(v[2]);
                        PartJade jade = roleData.getPackRecord().setPartJade(suitid, part, pz, 1);
                        assetUpdate.setJade(jade);
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(num), Integer.valueOf(20));
                    }
                }
                else if (goodstable.getType() == -1L) {
                    roleData.getPackRecord().addTX(-sid + "");
                    assetUpdate.setGood(goodstable);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(20));
                }
                else if (EquipTool.canSuper(goodstable.getType())) {
                    int sum = (yid == sid) ? num : 1;
                    List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), goodstable.getGoodsid());
                    if (sameGoodstable.size() != 0) {
                        int uses = (int)((Goodstable)sameGoodstable.get(0)).getUsetime() + sum;
                        ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf(uses));
                        AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                        assetUpdate.setGood((Goodstable)sameGoodstable.get(0));
                        AllServiceUtil.getGoodsrecordService().insert((Goodstable)sameGoodstable.get(0), null, Integer.valueOf(num), Integer.valueOf(20));
                    }
                    else {
                        goodstable.setUsetime(Integer.valueOf(sum));
                        AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                        assetUpdate.setGood(goodstable);
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(num), Integer.valueOf(20));
                    }
                    if (yid == sid) {
                        break;
                    }
                }
                else {
                    goodstable.setUsetime(Integer.valueOf(1));
                    AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                    assetUpdate.setGood(goodstable);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(num), Integer.valueOf(20));
                }
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (message.startsWith("PAYXY")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    
    public void sendChuanYin(LoginResult loginResult, String message) {
        NChatBean nChatBean = new NChatBean();
        nChatBean.setId(4);
        nChatBean.setMessage(message);
        nChatBean.setRoleId(loginResult.getRole_id());
        nChatBean.setRole(loginResult.getRolename());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(nChatBean));
        SendMessage.sendMessageToAllRoles(msg);
    }
}
