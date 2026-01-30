package org.come.control;

import java.util.List;
import java.math.BigDecimal;
import org.come.entity.Mount;
import org.come.Jpanel.ZhuJpanel;
import com.tool.role.RoleData;
import org.come.bean.NpcInfoBean;
import org.come.Frame.ZhuFrame;
import com.tool.image.ManimgAttribute;
import com.tool.image.ImageMixDeal;
import org.come.Frame.NPCJfram;
import org.come.npc.TP;
import org.come.model.Door;
import org.come.until.FormsManagement;
import org.come.Jpanel.HuangJpanel;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import come.tool.BangBattle.Build;
import org.come.action.NpcMenuAction;

public class BangBattleControl implements NpcMenuAction {

    public static Build build;

    @Override
    public void menuControl(String type) {
        // TODO Auto-generated method stub
        // 发送消息给服务器
        String sendmes = null;
        switch (type) {
            case "我来报名参加帮战":
                sendmes = "0";
                break;
            case "我要参加帮战":
                sendmes = "1";
                break;
            case "我要脱离帮战":
                sendmes = "2";
                break;
            case "我要进入高手挑战赛":
                sendmes = "3";
                break;
            case "我要进入战场":
                sendmes = "4";
                break;
            case "回到营地":
                sendmes = "5";
                break;
            case "我要挑战":
                sendmes = "6";
                break;
            case "我要取消挑战":
                sendmes = "7";
                break;
            case "我要应战":
                sendmes = "8";
                break;
            case "我要给塔充能":
                if (build == null)
                    return;
                sendmes = "9|" + build.getBh();
                break;
            case "我要攻击塔":
                if (build == null)
                    return;
                sendmes = "10|" + build.getBh();
                break;
            case "好呀，我来赌一把！"://黄大小
                SendMessageUntil.toServer(Agreement.getAgreement().HDXCPAgreement("TCH"));
                HuangJpanel.getTou();
                HuangJpanel.getTou1();
                FormsManagement.showForm(160);
                break;
            case "我要取消操作":
                if (build == null)
                    return;
                sendmes = "11|" + build.getBh();
                break;
            case "我要掐断炮火":
                sendmes = "12|" + build.getBh();
                break;
            case "我要领取守卫蟠桃园奖励":
                sendmes = "13";
                break;
            case "我要去守卫蟠桃园":
                Door door = new Door();
                door.setDoormap("3324");
                door.setDoorpoint("1800|600");
                TP.tp(door, 2);
                return;
            case "进入宝库二层":
                sendmes = "14";
                break;
            case "进入宝库三层":
                sendmes = "15";
                break;
            case "进入宝库四层":
                sendmes = "16";
                break;
            case "挑战一层守护者":
                sendmes = "17";
                break;
            case "挑战二层守护者":
                sendmes = "18";
                break;
            case "挑战三层守护者":
                sendmes = "19";
                break;
            case "挑战四层守护者":
                sendmes = "20";
                break;
            case "参加种族赛":
                FormsManagement.HideForm(41);
                sendmes = "21";
                break;
            case "一键领取种族赛奖励":
                sendmes = "22";
                break;
            case "种族赛匹配":
                sendmes = "23";
                break;
            case "取消种族赛匹配":
                sendmes = "24";
                break;
            case "我要接收战书":
                sendmes = "26|"+ NPCJfram.getNpcJfram().getNpcjpanel().getNpctype();
                break;
            case "我要取消战书":
                sendmes = "27|"+ NPCJfram.getNpcJfram().getNpcjpanel().getNpctype();
                break;
            case "我要观战":
                sendmes = "28|"+ NPCJfram.getNpcJfram().getNpcjpanel().getNpctype();
                break;
            case "参加擂台赛":
                sendmes = "29";
                break;
            case "一键领取擂台赛奖励":
                sendmes = "30";
                break;
            case "我要进行九生九死挑战":
                sendmes = "31";
                break;
            case "开启桃源仙境":
                sendmes = "32";
                break;
            case "我要参加跨服联赛":
                sendmes = "33";
                break;
            case "我要回到500年前":
                sendmes = "34";
                break;
            case "我来上交地煞星之魂":
            case "我来送商旅回家了":
            case "我来上交上古宝箱":
                NpcInfoBean npcInfoBean = NPCJfram.getNpcJfram().getNpcjpanel().getNpcInfoBean();
                if (npcInfoBean == null) {
                    return;
                }
                for (int i = 0; i < ImageMixDeal.npcimglist.size(); i++) {
                    if (ImageMixDeal.npcimglist.get(i).getNpc() == npcInfoBean) {
                        long x = ImageMixDeal.npcimglist.get(i).getX()- ImageMixDeal.userimg.getRoleShow().getX();
                        long y = ImageMixDeal.npcimglist.get(i).getY()- ImageMixDeal.userimg.getRoleShow().getY();
                        if (Math.abs(x) < 200 && Math.abs(y) < 200) {
                            sendmes = "35|"+ npcInfoBean.getNpctable().getNpctype();
                        }
                    }
                }
                if (sendmes == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你离NPC太远了");
                    return;
                }
                break;
            case "我要参观上古战场的风采":sendmes = "36";break;
            case "送我回后方营地":sendmes = "37";break;
            case "领取大闹天宫奖励":sendmes = "38";break;
            case "我要参加水陆大会":sendmes = "39";break;
            case "领取水陆大会奖励":sendmes = "40";break;
            case "领取经验加成":sendmes = "41";break;
            case "领取强法加成":sendmes = "42";break;
            case "领取抗性加成":sendmes = "43";break;
            case "驯养参战召唤兽亲密":
            case "驯养坐骑经验":
            case "驯养坐骑技能熟练度":
                sendmes=XY(type);
                break;
            case "升级帮派等级":sendmes = "47";break;
            case "升级科技等级":sendmes = "48";break;
            case "升级驯养师等级":sendmes = "49";break;
            case "我要前往武神山":
                sendmes = "50";
                break;
            case "人之烛火换我来守护":sendmes = "51";break;
            case "地之烛火换我来守护":sendmes = "52";break;
            case "天之烛火换我来守护":sendmes = "53";break;
            case "天帝印换我来守护":sendmes = "54";break;
            case "大吉大利晚上吃鸡":
                sendmes = "55";
                break;
        }
        if (sendmes == null) {return;}
        // 向服务器发送信息
        SendMessageUntil.toServer(Agreement.getAgreement().gangbattle(sendmes));
    }

    public String XY(String type) {
        if (type.equals("驯养参战召唤兽亲密")) {
            BigDecimal sid=RoleData.getRoleData().getLoginResult().getSummoning_id();
            if (sid == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("您并没有携带召唤兽!!!");
                return null;
            }
            return "44|"+sid;
        }else {
            int mount_id=ImageMixDeal.userimg.getRoleShow().getMount_id();
            Mount mount=null;
            if (mount_id!=0) {
                List<Mount> mounts=ZhuJpanel.getListMount();
                if (mounts!=null) {
                    for (int i = 0; i < mounts.size(); i++) {
                        if (mounts.get(i).getMountid()==mount_id) {
                            mount=mounts.get(i);
                            break;
                        }
                    }
                }
            }
            if (mount==null) {
                ZhuFrame.getZhuJpanel().addPrompt2("你当前未骑坐骑");
                return null;
            }
            return (type.equals("驯养坐骑经验")?"45|":"46|")+mount.getMid();
        }
    }
}
