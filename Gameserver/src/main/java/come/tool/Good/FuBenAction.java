package come.tool.Good;

import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import come.tool.Stall.AssetUpdate;
import come.tool.newTask.TaskRecord;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;
import org.come.action.buy.AddGoodUtil;
import org.come.action.monitor.MonitorUtil;
import org.come.action.suit.SuitMixdeal;
import org.come.bean.LoginResult;
import org.come.bean.NChatBean;
import org.come.bean.XXGDBean;
import org.come.entity.Goodstable;
import org.come.handler.SendMessage;
import org.come.model.TaskData;
import org.come.model.TaskList;
import org.come.model.TaskListAll;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.tool.EquipTool;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FuBenAction implements IAction {

    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        // TODO Auto-generated method stub

        LoginResult loginResult = GameServer.getAllLoginRole().get(ctx);
        if (loginResult==null) {return;}

        if (message == null) {
            return;
        }
        int ids = Integer.parseInt(message);
        TaskData taskData=GameServer.getTaskData(ids);
        if (taskData==null) {return;}

        RoleData data= RolePool.getRoleData(loginResult.getRole_id());

        TaskListAll taskListAll=GameServer.getTASK_LIST();
        TaskList taskList=taskListAll.getTaskByID(ids);
        if (taskList==null) {return;}
        TaskRecord record=data.getTaskRecord(taskList.getTaskSetID());
        if (record!=null) {
            int cishu =record.getcSum();
            if (cishu >= taskList.getNum()) {
                if (loginResult.CheakFuben(String.valueOf(taskList.getTaskSetID()))) {

                    String[] goods = taskList.getDrops().split("\\|");
                    List<Goodstable> list = new ArrayList<Goodstable>();
                    long exp = 0;
                    for (int i = 0; i < goods.length; i++) {
                        Goodstable goodstable = GameServer.getGood(new BigDecimal(goods[i]));
                        if (goodstable != null) {
                            if (goodstable.getValue().startsWith("经验=")) {
                                exp = Long.parseLong(goodstable.getValue().split("=")[1]);
                            } else {
                                list.add(goodstable);
                            }
                        }
                    }
                    if (exp != 0) {
                        ExpUtil.RoleExp(loginResult, exp);
                        StringBuffer buffer = new StringBuffer();
                        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                        assetUpdate.updata("R" + loginResult.getGrade() + "=" + loginResult.getExperience() + "=" + loginResult.getHp() + "=" + loginResult.getMp());
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("你获得" + exp + "经验");
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                    }

                    for (int i = 0; i < list.size(); i++) {
                        BigDecimal id = list.get(i).getGoodsid();
                        int sum = 1;
                        Goodstable good = GameServer.getGood(id);
                        if (good != null) {
                            good.setRole_id(loginResult.getRole_id());
                            if (EquipTool.canSuper(good.getType())) {
                                List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), good.getGoodsid());
                                if (sameGoodstable.size() != 0) {
                                    ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf((int)((Goodstable)sameGoodstable.get(0)).getUsetime() + sum));
                                    AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                                    good = (Goodstable)sameGoodstable.get(0);
                                }
                                else {
                                    good.setUsetime(Integer.valueOf(sum));
                                    AllServiceUtil.getGoodsTableService().insertGoods(good);
                                }
                                AddGoodUtil.addGood(ctx, good);
                                AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(sum), Integer.valueOf(3));
                            }
                            else {
                                for (int k = 0; k < sum; ++k) {
                                    AllServiceUtil.getGoodsTableService().insertGoods(good);
                                    AddGoodUtil.addGood(ctx, good);
                                    AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(3));
                                }
                            }
                        }
//                        // 角色ID
//                        list.get(i).setRole_id(loginResult.getRole_id());
//                        // 插入数据库
//                        AllServiceUtil.getGoodsTableService().insertGoods(list.get(i));
//                        AddGoodUtil.addGood(ctx, list.get(i));
//                        String msg = loginResult.getRolename() + "完成" + taskList.getTaskName() + "后，获得了" + list.get(i).getGoodsname();
//                        NChatBean bean2 = new NChatBean();
//                        bean2.setId(5);
//                        bean2.setMessage(msg);
//                        msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean2));
//                        SendMessage.sendMessageToAllRoles(msg);
                    }

                    loginResult.addfuben(taskList.getTaskSetID() + "");
                }else {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经领取过了"));
                    return;
                }
            }else {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("完成次数不够，无法获得奖励"));
                return;
            }
        } else {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你还没有完成过这个任务吧"));
            return;
        }
    }
}
