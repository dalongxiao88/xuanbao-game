package come.tool.Good;

import java.util.List;
import come.tool.Role.PartJade;
import org.come.entity.Lingbao;
import org.come.tool.EquipTool;
import org.come.until.AllServiceUtil;
import org.come.until.SplitLingbaoValue;
import java.math.BigDecimal;
import org.come.bean.XXGDBean;
import come.tool.Role.RoleData;
import org.come.entity.Goodstable;
import come.tool.Stall.AssetUpdate;
import org.come.protocol.ParamTool;
import org.come.tool.WriteOut;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class AddGoodAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        String v = "添加物品旧协议头:" + loginResult.getRole_id() + ",角色名:" + loginResult.getRolename() + ",:" + message;
        WriteOut.addtxt(v, 9999L);
        ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action((ChannelHandlerContext)GameServer.getInlineUserNameMap().get(loginResult.getUserName()), loginResult.getUserName());
    }
    
    public static void addGood(AssetUpdate assetUpdate, Goodstable goodstable, LoginResult loginResult, RoleData roleData, XXGDBean xxgdBean, int type) {
        goodstable.setRole_id(loginResult.getRole_id());
        BigDecimal id = new BigDecimal(xxgdBean.getId());
        long yid = id.longValue();
        for (int i = 0; i < xxgdBean.getSum(); ++i) {
            if (i != 0) {
                goodstable = GameServer.getGood(id);
            }
            goodstable.setRole_id(loginResult.getRole_id());
            long sid = goodstable.getGoodsid().longValue();
            if (sid >= 70001L && sid <= 70030L) {
                Lingbao lingbao = SplitLingbaoValue.addling(goodstable.getGoodsname(), loginResult.getRole_id());
                assetUpdate.setLingbao(lingbao);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(type));
            }
            else if (sid >= 69001L && sid <= 69015L) {
                Lingbao lingbao = SplitLingbaoValue.addfa(sid, loginResult.getRole_id());
                assetUpdate.setLingbao(lingbao);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(type));
            }
            else if (goodstable.getType() == 825L) {
                if (!goodstable.getValue().equals("")) {
                    String[] v = goodstable.getValue().split("\\|");
                    int suitid = Integer.parseInt(v[0]);
                    int part = Integer.parseInt(v[1]);
                    int pz = Integer.parseInt(v[2]);
                    PartJade jade = roleData.getPackRecord().setPartJade(suitid, part, pz, 1);
                    assetUpdate.setJade(jade);
                    if (i == 0) {
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(xxgdBean.getSum()), Integer.valueOf(type));
                    }
                }
            }
            else if (goodstable.getType() == -1L) {
                roleData.getPackRecord().addTX(-sid + "");
                assetUpdate.setGood(goodstable);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(type));
            }
            else if (EquipTool.canSuper(goodstable.getType())) {
                int sum = (yid == sid) ? xxgdBean.getSum() : 1;
                Goodstable assetGood = assetUpdate.getGoodSid(goodstable.getGoodsid());
                if (assetGood != null) {
                    int uses = (int)assetGood.getUsetime() + sum;
                    assetGood.setUsetime(Integer.valueOf(uses));
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(assetGood);
                    AllServiceUtil.getGoodsrecordService().insert(assetGood, null, Integer.valueOf(xxgdBean.getSum()), Integer.valueOf(type));
                }
                else {
                    List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), goodstable.getGoodsid());
                    if (sameGoodstable.size() != 0) {
                        int uses2 = (int)((Goodstable)sameGoodstable.get(0)).getUsetime() + sum;
                        ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf(uses2));
                        AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                        assetUpdate.setGood((Goodstable)sameGoodstable.get(0));
                        AllServiceUtil.getGoodsrecordService().insert((Goodstable)sameGoodstable.get(0), null, Integer.valueOf(xxgdBean.getSum()), Integer.valueOf(type));
                    }
                    else {
                        goodstable.setUsetime(Integer.valueOf(sum));
                        AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                        assetUpdate.setGood(goodstable);
                        AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(xxgdBean.getSum()), Integer.valueOf(type));
                    }
                }
                if (yid == sid) {
                    break;
                }
            }
            else {
                goodstable.setUsetime(Integer.valueOf(1));
                AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                assetUpdate.setGood(goodstable);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(type));
            }
        }
    }
}
