package org.come.action.zxpack;

import org.come.bean.NChatBean;
import java.util.List;
import come.tool.Role.PartJade;
import org.come.entity.Lingbao;
import come.tool.Role.RoleData;
import org.come.until.GsonUtil;
import org.come.entity.Goodstable;
import org.come.tool.EquipTool;
import org.come.until.AllServiceUtil;
import org.come.until.SplitLingbaoValue;
import come.tool.Role.RolePool;
import come.tool.Stall.AssetUpdate;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class ZxpackNineAction implements IAction
{
    public static String valuse;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        String[] result = message.split("\\|");
        if (result.length < 5) {
            return;
        }
        Goodstable goodstable1 = GameServer.getGood(new BigDecimal(result[4]));
        if (goodstable1 == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("要兑换的物品已失效"));
            return;
        }
        if (!goodstable1.getValue().contains(result[2])) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前兑换礼包不支持您选择的物品兑换!"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        StringBuffer buffer = new StringBuffer();
        if (message.startsWith("ADD")) {
            int num = (int)Integer.valueOf(result[1]);
            BigDecimal gid = new BigDecimal(result[2]);
            Goodstable goodstable2 = GameServer.getGood(gid);
            if (goodstable2 == null) {
                return;
            }
            buffer.append("你获取了");
            buffer.append(num);
            buffer.append("个");
            buffer.append(goodstable2.getGoodsname());
            assetUpdate.setMsg(buffer.toString());
            assetUpdate.setType(AssetUpdate.USEGOOD);
            RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
            goodstable2.setRole_id(loginResult.getRole_id());
            long yid = gid.longValue();
            for (int i = 0; i < num; ++i) {
                if (i != 0) {
                    goodstable2 = GameServer.getGood(gid);
                }
                goodstable2.setRole_id(loginResult.getRole_id());
                long sid = goodstable2.getGoodsid().longValue();
                if (sid >= 70001L && sid <= 70030L) {
                    Lingbao lingbao = SplitLingbaoValue.addling(goodstable2.getGoodsname(), loginResult.getRole_id());
                    assetUpdate.setLingbao(lingbao);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable2, null, Integer.valueOf(num), Integer.valueOf(20));
                }
                else if (sid >= 69001L && sid <= 69015L) {
                    Lingbao lingbao = SplitLingbaoValue.addfa(sid, loginResult.getRole_id());
                    assetUpdate.setLingbao(lingbao);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable2, null, Integer.valueOf(num), Integer.valueOf(20));
                }
                else if (goodstable2.getType() == 825L) {
                    if (!goodstable2.getValue().equals("")) {
                        String[] v = goodstable2.getValue().split("\\|");
                        int suitid = Integer.parseInt(v[0]);
                        int part = Integer.parseInt(v[1]);
                        int pz = Integer.parseInt(v[2]);
                        PartJade jade = roleData.getPackRecord().setPartJade(suitid, part, pz, 1);
                        assetUpdate.setJade(jade);
                        AllServiceUtil.getGoodsrecordService().insert(goodstable2, null, Integer.valueOf(num), Integer.valueOf(20));
                    }
                }
                else if (goodstable2.getType() == -1L) {
                    roleData.getPackRecord().addTX(-sid + "");
                    assetUpdate.setGood(goodstable2);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable2, null, Integer.valueOf(1), Integer.valueOf(20));
                }
                else if (EquipTool.canSuper(goodstable2.getType())) {
                    int sum = (yid == sid) ? num : 1;
                    List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), goodstable2.getGoodsid());
                    if (sameGoodstable.size() != 0) {
                        int uses = (int)((Goodstable)sameGoodstable.get(0)).getUsetime() + sum;
                        ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf(uses));
                        AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                        assetUpdate.setGood((Goodstable)sameGoodstable.get(0));
                        AllServiceUtil.getGoodsrecordService().insert((Goodstable)sameGoodstable.get(0), null, Integer.valueOf(num), Integer.valueOf(20));
                    }
                    else {
                        goodstable2.setUsetime(Integer.valueOf(sum));
                        AllServiceUtil.getGoodsTableService().insertGoods(goodstable2);
                        assetUpdate.setGood(goodstable2);
                        AllServiceUtil.getGoodsrecordService().insert(goodstable2, null, Integer.valueOf(num), Integer.valueOf(20));
                    }
                    if (yid == sid) {
                        break;
                    }
                }
                else {
                    goodstable2.setUsetime(Integer.valueOf(1));
                    AllServiceUtil.getGoodsTableService().insertGoods(goodstable2);
                    assetUpdate.setGood(goodstable2);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable2, null, Integer.valueOf(num), Integer.valueOf(20));
                }
            }
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
    
    static {
        ZxpackNineAction.valuse = "";
    }
}
