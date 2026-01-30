package org.come.control;

import org.come.socket.AgreementUtil;
import org.come.until.MessageProcessUntil;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.until.GsonUtil;
import org.come.bean.ConfirmBean;
import org.come.until.Music;
import org.come.Frame.ExitGameTipJframe;
import io.netty.channel.ChannelHandlerContext;
import org.come.until.AC999;
import org.come.until.JmSum;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.action.MapAction;
import com.tool.tcp.NewPart;
import com.tool.image.ManimgAttribute;
import com.tool.tcp.SpriteFactory;
import org.come.action.FromServerAction;

public class ServerMesControl implements FromServerAction
{
    public void Huang(String[] mes1) {
        ManimgAttribute.part3 = SpriteFactory.createPart("骰子全/骰子转动特效", -2, 5, null);
        ManimgAttribute.dianshu[0] = mes1[1];
        ManimgAttribute.dianshu[1] = mes1[2];
        Dingshi2 dingshi2 = new Dingshi2();
        dingshi2.start();
        String b = null;
        for (int f = 0; f <= 1; ++f) {
            b = "骰子全/" + ManimgAttribute.dianshu[f] + "点";
            NewPart partadd = SpriteFactory.createPart(b, -2, 5, null);
            ManimgAttribute.partList.add(partadd);
        }
    }
    
    @Override
    public void controlMessFromServer(String mes, String type) {
    	ExitGameTipJframe exitGameTipJframe1, exitGameTipJframe2;
    	String[] mes1;
    	ConfirmBean confirmBean;
        String ab = mes.substring(0, 4);
        String ReceiveMes = null;
        FromServerAction action = (FromServerAction)MapAction.serverAction.get(ab);
        if (action != null) {
            if (ab.equals("move") || ab.equals("movd") || ab.equals("fig1") || ab.equals("fig4") || ab.equals("fig7") || ab.equals("fig9")) {
                ReceiveMes = mes.substring(6);
                action.controlMessFromServer(ReceiveMes, ab);
            }
            else {
                mes = Agreement.getAgreement().MonitorAgreement(0, mes);
                SendMessageUntil.toServer(mes);
                JmSum.xiugaiqi();
            }
            return;
        }
        else {
            mes = AC999.AESJDKDncode(mes);
            if (mes == null) {
                System.out.println("空数据");
                return;
            }
            int wz = mes.indexOf("//");
            if (wz == -1) {
                return;
            }
            ReceiveMes = mes.substring(wz + 2);
            ab = mes.substring(0, wz);
            System.out.println("协议头:" + ab + ",内容:" + ReceiveMes);
            String s = ab;
            int n = -1;
            switch (s = ab) {
            case "gettitlelist":
              ((FromServerAction)MapAction.serverAction.get("gettitlelist")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "auctionGoods":
              ((FromServerAction)MapAction.serverAction.get("AuctionGoods")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "titlelist":
              ((FromServerAction)MapAction.serverAction.get(MessageProcessUntil.titlelist)).controlMessFromServer(ReceiveMes, ab);
              return;
            case "shopPrice":
              ((FromServerAction)MapAction.serverAction.get("shopPrice")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "searcahChatRoleId":
              ((FromServerAction)MapAction.serverAction.get("searcahChatRoleId")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "serverstop":
              MapAction.flagAction.put(SendMessageUntil.gameServerKey, Boolean.FALSE);
              ((ChannelHandlerContext)MapAction.nettyAction.get(SendMessageUntil.gameServerKey)).channel().close();
              MapAction.nettyAction.put(SendMessageUntil.gameServerKey, null);
              exitGameTipJframe1 = new ExitGameTipJframe();
              exitGameTipJframe1.getExitGameTipJpanel().getLabtip().setText("服务器重新启动，您已经掉线!!!!");
              exitGameTipJframe1.setLocation(400, 300);
              return;
            case AgreementUtil.ROLEACHIEVE: {
                MapAction.serverAction.get(AgreementUtil.ROLEACHIEVE).controlMessFromServer(ReceiveMes, ab);
                return;
            }
                case AgreementUtil.ZXDOOD: {
                    MapAction.serverAction.get(AgreementUtil.ZXDOOD).controlMessFromServer(ReceiveMes, ab);
                    return;
                }
            case AgreementUtil.ROLEDAYDRAW: {
                MapAction.serverAction.get(AgreementUtil.ROLEDAYDRAW).controlMessFromServer(ReceiveMes, ab);
                break;
            }
            case "CBGCollect":
              ((FromServerAction)MapAction.serverAction.get("CBGCollect")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "friendchat":
              Music.addyinxiao("好友信件提示音.mp3");
              ((FromServerAction)MapAction.serverAction.get("friendchat")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "loginerror":
              ((FromServerAction)MapAction.serverAction.get(ab)).controlMessFromServer(ReceiveMes, ab);
              return;
            case "ganglist":
              ((FromServerAction)MapAction.serverAction.get("ganglist")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "inlinelogin":
              MapAction.flagAction.put(SendMessageUntil.gameServerKey, Boolean.valueOf(false));
              ((ChannelHandlerContext)MapAction.nettyAction.get(SendMessageUntil.gameServerKey)).channel().close();
              MapAction.nettyAction.put(SendMessageUntil.gameServerKey, null);
              exitGameTipJframe2 = new ExitGameTipJframe();
              exitGameTipJframe2.setLocation(400, 300);
              return;
            case "battleconnection":
              ((FromServerAction)MapAction.serverAction.get("battleconnection")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "fightinglingbao":
              ((FromServerAction)MapAction.serverAction.get("fightinglingbao")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "UnPhoneBang":
              ((FromServerAction)MapAction.serverAction.get("UnPhoneBang")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "car":
                ((FromServerAction)MapAction.serverAction.get("car")).controlMessFromServer(ReceiveMes, ab);
                return;
            case "chongjipackget":
              ((FromServerAction)MapAction.serverAction.get("chongjipackget")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "DUELBOARD":
              ((FromServerAction)MapAction.serverAction.get("DUELBOARD")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "getfivemsg":
              ((FromServerAction)MapAction.serverAction.get("getfivemsg")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "hatchvalue":
              ((FromServerAction)MapAction.serverAction.get("hatchvalue")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "obtainarticle":
              ((FromServerAction)MapAction.serverAction.get("obtainarticle")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "rigistersuccess":
              ((FromServerAction)MapAction.serverAction.get(ab)).controlMessFromServer(ReceiveMes, ab);
              return;
            case "bangtz":
              ((FromServerAction)MapAction.serverAction.get("bangtz")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "extrattroper":
              ((FromServerAction)MapAction.serverAction.get("extrattroper")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "enlist":
              ((FromServerAction)MapAction.serverAction.get("team1")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "createsuccess":
              ((FromServerAction)MapAction.serverAction.get(ab)).controlMessFromServer(ReceiveMes, ab);
              return;
            case "friend":
              ((FromServerAction)MapAction.serverAction.get("friend")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "TransGood":
              ((FromServerAction)MapAction.serverAction.get("TransGood")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "addgood":
              ((FromServerAction)MapAction.serverAction.get("addgood")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "lingxi":
              ((FromServerAction)MapAction.serverAction.get("lingxi")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "middle":
              ((FromServerAction)MapAction.serverAction.get("middle")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "prompt":
              mes1 = ReceiveMes.split("-");
              if (mes1[0].equals("H")) {
                Huang(mes1);
              } else {
                ((FromServerAction)MapAction.serverAction.get("prompt")).controlMessFromServer(ReceiveMes, ab);
                return;
              } 
            case "rolelevelup":
              ((FromServerAction)MapAction.serverAction.get("rolelevelup")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "creepsfight":
              ((FromServerAction)MapAction.serverAction.get("creepsfight")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "userretreat":
              ((FromServerAction)MapAction.serverAction.get("userretreat")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "rigistererror":
              ((FromServerAction)MapAction.serverAction.get(ab)).controlMessFromServer(ReceiveMes, ab);
              return;
            case "enterGame":
              ((FromServerAction)MapAction.serverAction.get("enterGame")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "ACCOUNT_LOGIN":
              ((FromServerAction)MapAction.serverAction.get("ACCOUNT_LOGIN")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "CBGSearch1":
              ((FromServerAction)MapAction.serverAction.get("CBGSearch1")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "CBGSearch2":
              ((FromServerAction)MapAction.serverAction.get("CBGSearch2")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "CBGSearch3":
              ((FromServerAction)MapAction.serverAction.get("CBGSearch3")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "CBGSearch4":
              ((FromServerAction)MapAction.serverAction.get("CBGSearch4")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "CBGSearch5":
              ((FromServerAction)MapAction.serverAction.get("CBGSearch5")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "CBGSearch6":
              ((FromServerAction)MapAction.serverAction.get("CBGSearch6")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "CBGSearch7":
              ((FromServerAction)MapAction.serverAction.get("CBGSearch7")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "changerolename":
              ((FromServerAction)MapAction.serverAction.get("changerolename")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "createerror":
              ((FromServerAction)MapAction.serverAction.get(ab)).controlMessFromServer(ReceiveMes, ab);
              return;
            case "shopGood":
              ((FromServerAction)MapAction.serverAction.get("shopGood")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "UidAndQidForRole":
              ((FromServerAction)MapAction.serverAction.get("UidAndQidForRole")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "unmarry":
              ((FromServerAction)MapAction.serverAction.get(MessageProcessUntil.marry)).controlMessFromServer(ReceiveMes, ab);
              return;
            case "ACCOUNT_BINDING":
              MapAction.serverAction.get("ACCOUNT_BINDING").controlMessFromServer(ReceiveMes, ab);
              return;
            case "teamArena":
              MapAction.serverAction.get("teamArena").controlMessFromServer(ReceiveMes, ab);
              return;
            case "laddArena":
              MapAction.serverAction.get("laddArena").controlMessFromServer(ReceiveMes, ab);
            case "1":
              MapAction.serverAction.get("1").controlMessFromServer(ReceiveMes, ab);
              return;
            case "npc":
              MapAction.serverAction.get("npc").controlMessFromServer(ReceiveMes, ab);
              return;
            case "pet":
              MapAction.serverAction.get("pet").controlMessFromServer(ReceiveMes, ab);
              return;
            case "tip":
              MapAction.serverAction.get("tip").controlMessFromServer(ReceiveMes, ab);
              return;
            case "baby":
              MapAction.serverAction.get("baby").controlMessFromServer(ReceiveMes, ab);
              return;
            case "chat":
              MapAction.serverAction.get("chat").controlMessFromServer(ReceiveMes, ab);
              return;
            case "fig4":
              MapAction.serverAction.get("fig4").controlMessFromServer(ReceiveMes, ab);
              return;
            case "fig5":
              MapAction.serverAction.get("fig5").controlMessFromServer(ReceiveMes, ab);
              return;
            case "fig6":
              MapAction.serverAction.get("fig6").controlMessFromServer(ReceiveMes, ab);
              return;
            case "fig7":
              MapAction.serverAction.get("fig7").controlMessFromServer(ReceiveMes, ab);
              return;
            case "fig8":
              MapAction.serverAction.get("fig8").controlMessFromServer(ReceiveMes, ab);
              return;
            case "hjsl":
              MapAction.serverAction.get("hjsl").controlMessFromServer(ReceiveMes, ab);
              return;
            case "move":
              MapAction.serverAction.get("move").controlMessFromServer(ReceiveMes, ab);
              return;
            case "pawn":
              MapAction.serverAction.get("pawn").controlMessFromServer(ReceiveMes, ab);
              return;
            case "shop":
              MapAction.serverAction.get("shop").controlMessFromServer(ReceiveMes, ab);
              return;
            case "makelove":
              MapAction.serverAction.get(MessageProcessUntil.marry).controlMessFromServer(ReceiveMes, ab);
              return;
            case "asset":
              MapAction.serverAction.get("asset").controlMessFromServer(ReceiveMes, ab);
              return;
            case "labor":
              MapAction.serverAction.get("labor").controlMessFromServer(ReceiveMes, ab);
              return;
            case "marry":
              MapAction.serverAction.get(MessageProcessUntil.marry).controlMessFromServer(ReceiveMes, ab);
              return;
            case "mount":
              MapAction.serverAction.get("mount").controlMessFromServer(ReceiveMes, ab);
              return;
            case "fly":
              MapAction.serverAction.get("fly").controlMessFromServer(ReceiveMes, ab);
            case "richm":
              MapAction.serverAction.get("richm").controlMessFromServer(ReceiveMes, ab);
              return;
            case "scene":
              MapAction.serverAction.get("scene").controlMessFromServer(ReceiveMes, ab);
              return;
            case "stall":
              MapAction.serverAction.get("stall").controlMessFromServer(ReceiveMes, ab);
              return;
            case "taskN":
              ((FromServerAction)MapAction.serverAction.get("taskN")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "team1":
            case "team2":
            case "team3":
            case "team4":
            case "team6":
              ((FromServerAction)MapAction.serverAction.get("team1")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "vicon":
              ((FromServerAction)MapAction.serverAction.get("vicon")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "Dayforweekgradeget":
              ((FromServerAction)MapAction.serverAction.get("Dayforweekgradeget")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "drawnitemsfail":
              ((FromServerAction)MapAction.serverAction.get("drawnitemsfail")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "TransState":
              ((FromServerAction)MapAction.serverAction.get("TransState")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "gangchange":
              ((FromServerAction)MapAction.serverAction.get("gangchange")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "version":
              ((FromServerAction)MapAction.serverAction.get("version")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "babycustoday":
              ((FromServerAction)MapAction.serverAction.get("babycustoday")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "loginsuccess":
              ((FromServerAction)MapAction.serverAction.get(ab)).controlMessFromServer(ReceiveMes, ab);
              return;
            case "AREASTATUES":
              ((FromServerAction)MapAction.serverAction.get("AREASTATUES")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "bindingMobile":
              ((FromServerAction)MapAction.serverAction.get("bindingMobile")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "intogame":
              ((FromServerAction)MapAction.serverAction.get("intogame")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "intogang":
              ((FromServerAction)MapAction.serverAction.get("intogang")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "drawnitems":
              ((FromServerAction)MapAction.serverAction.get("drawnitems")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "LOGINVERSION":
              ((FromServerAction)MapAction.serverAction.get("LOGINVERSION")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "racialtransformation":
              ((FromServerAction)MapAction.serverAction.get("racialtransformation")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "confirm":
              confirmBean = (ConfirmBean)GsonUtil.getGsonUtil().getgson().fromJson(ReceiveMes, ConfirmBean.class);
              OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.confirm, confirmBean, confirmBean.getMSG());
              confirmBean.setMSG((String)null);
              return;
            case "titlechange":
              ((FromServerAction)MapAction.serverAction.get(MessageProcessUntil.titlelist)).controlMessFromServer(ReceiveMes, ab);
              return;
            case "PhoneBang":
              ((FromServerAction)MapAction.serverAction.get("PhoneBang")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "ROLEQIANDAO":
              ((FromServerAction)MapAction.serverAction.get("ROLEQIANDAO")).controlMessFromServer(ReceiveMes, ab);
            case "APPQIANDAO":
              ((FromServerAction)MapAction.serverAction.get("APPQIANDAO")).controlMessFromServer(ReceiveMes, ab);
            case "PhoneNumberReturn":
              ((FromServerAction)MapAction.serverAction.get("PhoneNumber")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "Getvipgradepack":
              ((FromServerAction)MapAction.serverAction.get("Getvipgradepack")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "bookofchalg":
              ((FromServerAction)MapAction.serverAction.get("bookofchalg")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "applypay":
              ((FromServerAction)MapAction.serverAction.get("applypay")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "pankinglist":
              ((FromServerAction)MapAction.serverAction.get("pankinglist")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "npcdialog":
              ((FromServerAction)MapAction.serverAction.get("npcdialog")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "searchChatRecorde":
              ((FromServerAction)MapAction.serverAction.get("searcahChatRoleId")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "givegoods":
              ((FromServerAction)MapAction.serverAction.get("givegoods")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "givemoney":
              ((FromServerAction)MapAction.serverAction.get("givemoney")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "stallstate":
              ((FromServerAction)MapAction.serverAction.get("stallstate")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "Dayforoneget":
              ((FromServerAction)MapAction.serverAction.get("Dayforoneget")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "LimitedTimeShop":
              ((FromServerAction)MapAction.serverAction.get("LimitedTimeShop")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "searcahChatRoleName":
              ((FromServerAction)MapAction.serverAction.get("searcahChatRoleId")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "Paydaygradepay":
              ((FromServerAction)MapAction.serverAction.get("Paydaygradepay")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "RETAREA":
              ((FromServerAction)MapAction.serverAction.get("RETAREA")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "PhoneNumberIsNoGet":
              ((FromServerAction)MapAction.serverAction.get("PhoneNumberIsNoGet")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "rolechange":
              ((FromServerAction)MapAction.serverAction.get("rolechange")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "gangstate":
              ((FromServerAction)MapAction.serverAction.get("gangstate")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "oneArena":
              ((FromServerAction)MapAction.serverAction.get("oneArena")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "CBGBuy":
              break;
            case "CBGMes":
              ((FromServerAction)MapAction.serverAction.get("CBGMes")).controlMessFromServer(ReceiveMes, ab);
              break;
            case "upRoleShow":
              ((FromServerAction)MapAction.serverAction.get("upRoleShow")).controlMessFromServer(ReceiveMes, ab);
              return;
            case "monsterrefresh":
              ((FromServerAction)MapAction.serverAction.get(MessageProcessUntil.monster)).controlMessFromServer(ReceiveMes, ab);
              return;
            case "rolechange1":
              ((FromServerAction)MapAction.serverAction.get("rolechange1")).controlMessFromServer(ReceiveMes, ab); return;
            case "qd":
              ((FromServerAction)MapAction.serverAction.get("qd")).controlMessFromServer(ReceiveMes, ab); return;
            case "QMJC":
              ((FromServerAction)MapAction.serverAction.get("QMJC")).controlMessFromServer(ReceiveMes, ab); return;
            case "sellxianyu":
              ((FromServerAction)MapAction.serverAction.get("sellxianyu")).controlMessFromServer(ReceiveMes, ab); return;
            case "shaoxiang":
              ((FromServerAction)MapAction.serverAction.get("shaoxiang")).controlMessFromServer(ReceiveMes, ab); return;
            case "AutoTask":
              ((FromServerAction)MapAction.serverAction.get("AutoTask")).controlMessFromServer(ReceiveMes, ab); return;
            case "lottery":
              ((FromServerAction)MapAction.serverAction.get("lottery")).controlMessFromServer(ReceiveMes, ab); return;
            case "zxpack":
              MapAction.serverAction.get("zxpack").controlMessFromServer(ReceiveMes, ab); return;
            case "selllianghao":
              MapAction.serverAction.get("selllianghao").controlMessFromServer(ReceiveMes, ab); return;
            case "HDX":
              MapAction.serverAction.get("HDX").controlMessFromServer(ReceiveMes, ab); return;
            case "GUA":
              MapAction.serverAction.get("GUA").controlMessFromServer(ReceiveMes, ab); return;
            case "shouhuPMxy":
              MapAction.serverAction.get("shouhuPMxy").controlMessFromServer(ReceiveMes, ab);
              return;
                case "tournamentsData":
                    MapAction.serverAction.get("tournamentsData").controlMessFromServer(ReceiveMes, ab);
            case "xydjls": {
                MapAction.serverAction.get("xydjls").controlMessFromServer(ReceiveMes, ab);
                return;

            }
            default:
              MapAction.serverAction.get("team1").controlMessFromServer(ReceiveMes, ab);
              return;
          } 
            MapAction.serverAction.get("CBGBuy").controlMessFromServer(ReceiveMes, ab);
            return;

        }
    }
    
    public class Dingshi2 extends Thread
    {
        @Override
        public void run() {
            try {
                Thread.sleep(5000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
