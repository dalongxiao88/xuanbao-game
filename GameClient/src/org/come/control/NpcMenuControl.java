package org.come.control;

import come.tool.Scene.Scene;
import org.come.XuanBao.XuanBaoPack_xuanyunJframe;
import org.come.XuanBao.XuanBaoXiuLianJframe;
import org.come.bean.MapMonsterBean;
import org.come.Jpanel.NPCJpanel;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import org.come.npc.PetConversion;
import org.come.good.PetEquip;
import com.tool.role.RoleLingFa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.come.socket.AgreementUtil;
import org.come.until.*;
import com.tool.imagemonitor.PlayerMonitor;
import come.tool.Scene.DNTGScene;
import com.tool.image.ImageMixDeal;
import org.come.Frame.GemMakeFrame;
import org.come.Frame.ExchangeAwardJframe;
import org.come.Frame.FactionAngelJframe;
import org.come.npc.Synthesis;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.NewRefiningJpanel;
import org.come.npc.GangInformation;
import org.come.npc.GangCreate;
import org.come.npc.RoleConversion;
import org.come.npc.Exchangeitems;
import org.come.npc.SummonPet;
import org.come.npc.SkillLearnMsg;
import org.come.npc.NpcShop;
import org.come.action.MapAction;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.npc.TP;
import com.tool.ModerateTask.TaskMixDeal;
import org.come.Frame.NPCJfram;
import org.come.action.NpcMenuAction;

public class NpcMenuControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        NPCJpanel npcJpanel = NPCJfram.getNpcJfram().getNpcjpanel();
        if (type.equals("上一页")) {
            npcJpanel.FY(-1);
            return;
        }
        if (type.equals("下一页")) {
            npcJpanel.FY(1);
            return;
        }
        if (npcJpanel.getNpcInfoBean() != null && !"2".equals(npcJpanel.getNpctype()) && TaskMixDeal.isoption(0, type, npcJpanel.getNpcInfoBean().getNpctable().getNpcway())) {
            return;
        }
        if (TP.isTP(type) || npcJpanel.FlightMsgSD(type)) {
            return;
        }
        if (npcJpanel.getMapMonsterBean() != null) {
            MapMonsterBean bean = npcJpanel.getMapMonsterBean();
            if (bean.getRobottype() >= 100 && bean.getRobottype() <= 199 && bean.getRobottype() != 132) {
                SendMessageUntil.toServer(Agreement.getAgreement().gangbattle("M" + bean.getI() + "|" + type));
                return;
            }
        }
        if (type != null && type.equals("我要启灵当前参战召唤兽")) {
            MapAction.npcmenuAction.get(NpcMenuUntil.getMenu15()).menuControl(type);
            return;
        }


        switch (type) {
            case"我要领取一小时双倍时间":
            case "我要领取二小时双倍时间":
            case "我要领取四小时双倍时间":
            case "我要冻结双倍时间":
            case "我要查询剩余双倍时间":
                MapAction.npcmenuAction.get(NpcMenuUntil.DBEXP).menuControl(type);
                break;
            case "HP":
            case "MP":
            case "AP":
            case "SP":
            case "是":
            case "否":
                MapAction.npcmenuAction.get(NpcMenuUntil.getMenu51()).menuControl(type);
                break;
            case "我要取回物品":
                FormsManagement.showForm(55);
                break;
            case "我是来自首的":
            case "我是来办理出狱手续":
            case "这是8888W,小小意识":
            case "哦?大爷是来办理出狱手续":
                MapAction.npcmenuAction.get(NpcMenuUntil.GETOUT).menuControl(type);
                break;
            case "我想买点东西":
            case "我来换点竞技物品":
            case "我要购买灵宝符石":
            case "我要购买测试物资":
            case "我来看看有什么珍稀商品":
                new NpcShop().menuControl(type);
                break;
            case "我什么都不做":
            case "我是来探监的":
            case "别愁,我去帮你筹点钱":
                break;
            case "学习法术":
                new SkillLearnMsg().menuControl(type);
                break;
            case "我的召唤兽的伤害，帮我治疗一下它并提高它的忠诚度吧":
                MapAction.npcmenuAction.get(NpcMenuUntil.getCurerolesumming()).menuControl("治疗");
                break;
            case "我想查看当前召唤兽的亲密度":
                MapAction.npcmenuAction.get(NpcMenuUntil.getShowrolesummingfaithful()).menuControl("查看亲密");
                break;
            case "江湖救急啊，请救救我和我的宝宝吧":
            case "我要住店(扣除2000银两)":
                MapAction.npcmenuAction.get(NpcMenuUntil.getMenu9()).menuControl(type);
                break;
            case "剧毒封印之书":
            case "寒冰封印之书":
            case "天雷封印之书":
            case "御剑封印之书":
            case "狂力封印之书":
            case "冥灵古卷":
            case "玲珑古卷":
            case "巨翼古卷":
            case "葫芦古卷":
            case "如意古卷":
            case "苍凛古卷":
            case "赭炎古卷":
            case "当康古卷":
            case "松鼠古卷":
            case "佳音小使古卷":
            case "我是来召唤你的":
            case "换个高级藏宝图":
            case "换个超级藏宝图":
            case "年(588碎片)":
            case "画中仙(488碎片)":
            case "白泽(468碎片)":
            case "龙马(188碎片)":
            case "画皮娘子(188碎片)":
            case "孔雀明王(188碎片)":
            case "北冥龙君(288碎片)":
            case "孟极(288碎片)":
            case "镜花水月(388碎片)":
            case "妙音栾女(388碎片)":
            case "兑换彩晶石":
                new SummonPet().menuControl(type);
                break;
            case "兑换超级龙之骨":
            case "兑换高级聚魄丹":
                new Exchangeitems().menuControl(type);
                break;
            case "我要兑换装备":
                FormsManagement.showForm(700);//未知错对
                break;
            case "我要学习天赋(扣除一定金钱和亲密,有概率失败!)":
            case "转生当前召唤兽":
            case "点化当前坐骑":
            case "点化当前召唤兽":
            case "飞升当前参战神兽":
            case "学习神兽技能(扣除50万亲密)":
            case "神兽开启技能格子(消耗游戏币2000万)":
            case "我想重新修炼终极技能":
//			case "我要启灵当前参战召唤兽":
//			case "确定幻肤吗?":
                MapAction.npcmenuAction.get(NpcMenuUntil.getMenu15()).menuControl(type);
                break;
            case "全部医治和修复":
                // 一件修复所有的宝宝伤害和忠诚度 治疗所有
                MapAction.npcmenuAction.get(NpcMenuUntil.getCurerolesumming()).menuControl("治疗所有");
                break;
            case "我已做好了转生准备":
            case "我想转换种族":
            case "我已做好了飞升准备":
                new RoleConversion().menuControl(type);
                break;
            case "我要创建帮派,(需要一个召集令)":
                new GangCreate().menuControl(type);
                break;
            case "加入帮派":
                new GangInformation().menuControl(type);
                break;
            case "进入帮派":
                MapAction.npcmenuAction.get(NpcMenuUntil.getMenu39()).menuControl(type);
                break;
            case "满地宝物先抢一个在说":
            case "我是来劫道的":
            case "我是来击杀你的":
            case "我是来击杀你的(困难难度)":
            case "我是来击杀你的(卓越难度)":
            case "我是来击杀你的(地狱难度)":
            case "我是来击杀你的(天堂难度)":
                MapAction.npcmenuAction.get(NpcMenuUntil.getMenu48()).menuControl(type);
                break;
            case "人物经验转换":
                (XuanBaoXiuLianJframe.getXuanBaoXiuLianJframe()).xuanBaoXiuLianJpanel.update();
                FormsManagement.showForm(8033);
                break;
            case "使用玄蕴禄/极蕴禄":
                List<Goodstable> goodstableList = new ArrayList<>();
                for (Goodstable goodstable : GoodsListFromServerUntil.getGoodslist()) {
                    if (goodstable != null && (
                            goodstable.getType() == 1115L || goodstable.getType() == 1116L)) {
                        goodstableList.add(goodstable);
                    }
                }
                XuanBaoPack_xuanyunJframe.getXuanBaoPack_xuanyunJframe().getXuanBaoPackJpanel().getGoodstableList().clear();
                XuanBaoPack_xuanyunJframe.getXuanBaoPack_xuanyunJframe().getXuanBaoPackJpanel().getGoodstableList().addAll(goodstableList);
                XuanBaoPack_xuanyunJframe.getXuanBaoPack_xuanyunJframe().getXuanBaoPackJpanel().update();
                FormsManagement.showForm(8037);
                break;
            case "我要挑战八十一难":
                MapAction.npcmenuAction.get(NpcMenuUntil.getMenu48()).menuControl(type);
                break;
            case "快送我去":
            case "新增坐标":
            case "删除路标":
            case "重新做路标":
                MapAction.npcmenuAction.get(NpcMenuUntil.TP).menuControl(type);
                break;
            case "我要结婚":
                MapAction.npcmenuAction.get(NpcMenuUntil.getMarry()).menuControl(type);
                break;
            case "我要离婚":
                MapAction.npcmenuAction.get(NpcMenuUntil.getUnMaary()).menuControl(type);
                break;
            case "我要洞房":
                MapAction.npcmenuAction.get(NpcMenuUntil.getMakelove()).menuControl(type);
                break;
//            case "领元气蛋":
//                MapAction.npcmenuAction.get(NpcMenuUntil.getMenu82()).menuControl(type);
//                break;
            case "我要回长安":
            case "我要回家":
            case "我想去死牢探监":
            case "我想去地牢探监":
            case "我想去天牢探监":
                MapAction.npcmenuAction.get(NpcMenuUntil.GOTO).menuControl(type);
                break;
            case "符石合成重铸":
            case "我要打造普通装备":
            case "我要升级神兵":
            case "我要合成炼妖石":
            case "我要合成玄印":
            case "打造11-16级装备":
            case "我要合成仙器":
            case "我要升级仙器":
            case "改变仙器模型":
            case "洗炼仙器-超级悔梦":
            case "我要分解仙器":
            case "重铸仙器-悔梦石":
            case "我要培养饰品":
            case "我要解封神饰":
            case "我要重铸饰品":
            case "我要上神兵石":
            case "我要培养护身符":
            case "我要重铸护身符":
            case "培养彩晶石":
            case "精炼神兵":
                if (NewRefiningJpanel.isLH) {
                    ZhuFrame.getZhuJpanel().addPrompt("炼化中无法打开其他操作");
                    return;
                }
                new Synthesis().menuControl(type);
                break;
            case "我要典当物品":
                FormsManagement.showForm(29);
                break;
            case "我来报名参加帮战":
            case "我要参加帮战":
            case "我要进入高手挑战赛":
            case "我要脱离帮战":
            case "我要进入战场":
            case "回到营地":
            case "我要挑战":
            case "我要取消挑战":
            case "我要应战":
            case "我要给塔充能":
            case "我要攻击塔":
            case "我要取消操作":
            case "我要掐断炮火":
            case "我要去守卫蟠桃园":
            case "我要领取守卫蟠桃园奖励":
            case "进入宝库二层":
            case "进入宝库三层":
            case "进入宝库四层":
            case "挑战一层守护者":
            case "挑战二层守护者":
            case "挑战三层守护者":
            case "挑战四层守护者":
            case "参加种族赛":
            case "一键领取种族赛奖励":
            case "种族赛匹配":
            case "取消种族赛匹配":
            case "我要接收战书":
            case "我要取消战书":
            case "我要观战":
            case "参加擂台赛":
            case "一键领取擂台赛奖励":
            case "我要进行九生九死挑战":
            case "开启桃源仙境":
            case "我要参加跨服联赛":
            case "我要回到500年前":
            case "我来上交地煞星之魂":
            case "我来送商旅回家了":
            case "我来上交上古宝箱":
            case "我要参观上古战场的风采":
            case "送我回后方营地":
            case "领取大闹天宫奖励":
            case "我要参加水陆大会":
            case "领取水陆大会奖励":
            case "领取经验加成":
            case "领取强法加成":
            case "领取抗性加成":
            case "驯养参战召唤兽亲密":
            case "驯养坐骑经验":
            case "驯养坐骑技能熟练度":
            case "升级帮派等级":
            case "升级科技等级":
            case "升级驯养师等级":
            case "我要前往武神山":
            case "人之烛火换我来守护":
            case "地之烛火换我来守护":
            case "天之烛火换我来守护":
            case "天帝印换我来守护":
            case "好呀，我来赌一把！":
            case "大吉大利晚上吃鸡":
                MapAction.npcmenuAction.get(AgreementUtil.npcdialog).menuControl(type);
                break;
            case "确定":
            case "我再考虑考虑":
                MapAction.npcmenuAction.get(NpcMenuUntil.getMenu52()).menuControl(type);
                break;
//		case "我要抽奖":
//			MapAction.npcmenuAction.get(NpcMenuUntil.LOTTERY).menuControl(type);
//			break;
//		case "我要投放功绩物品":
//			MapAction.npcmenuAction.get(NpcMenuUntil.THROWIN).menuControl(type);
//			break;
            case "守护小成修炼":
                FactionAngelJframe.getFactionAngelJframe().getFactionAngelJpanel().changeMenuShow(1);
                FormsManagement.showForm(54);
                break;
            case "守护大成修炼":
                FactionAngelJframe.getFactionAngelJframe().getFactionAngelJpanel().changeMenuShow(2);
                FormsManagement.showForm(54);
                break;
            case "我要更换主守护":
            case "我要更换副守护":
                //				ZhuFrame.getZhuJpanel().addPrompt("当前功能未开放");
                MapAction.npcmenuAction.get(NpcMenuUntil.getMenu80()).menuControl(type);
                break;
            case "我要下挑战书":
            case "我要下战书":
                MapAction.npcmenuAction.get(NpcMenuUntil.CHALLENGE).menuControl(type);
                break;
            case "我要兑换灵修值":
            case "我要收录玉符（玉符转符录）":
            case "我要查看已有符录（符录转玉符）":
                MapAction.npcmenuAction.get(NpcMenuUntil.EXCHANGEVAL).menuControl(type);
                break;
            case "前途缈缈，我还是再准备下吧":
                MapAction.npcmenuAction.get(NpcMenuUntil.WILLENTER).menuControl(type);
                break;
            case "我要激活充值CDK": // 打开兑奖面板
                ExchangeAwardJframe.getExchangeAwardJframe().getAwardJpanel().use(2, null);
                break;
            case "我来助你一孵之力（需提交一个物品）":
            case "元旦快乐，有事先闪":
            case "直接打开":
            case "祈福（500W金币）":
                MapAction.npcmenuAction.get(NpcMenuUntil.GIVEAHAND).menuControl(type);
                break;
            case "我要打造,摘抄宝石":
                GemMakeFrame.getGemMakeFrame().getJpanel().qh(0);
                GemMakeFrame.getGemMakeFrame().getJpanel().qh(0);
                GemMakeFrame.getGemMakeFrame().getJpanel().lingNumChange(0);
                FormsManagement.showForm(84);
                break;
            case "我要合成宝石等":
                FormsManagement.showForm(85);
                break;
            case "星芒恢复(100点=1000点战力)":
            case "金币恢复(5000W=1000点战力)":
                MapAction.npcmenuAction.get(NpcMenuUntil.STARCARDSUPPLEMENT).menuControl(type);
                break;
            case "神力加持":
                if (ImageMixDeal.scene!=null&&ImageMixDeal.scene.getSceneId()== Scene.DNTGID) {
                    ((DNTGScene)ImageMixDeal.scene).showSL();
                }
                break;
            case "我要合成召唤兽":
                FormsManagement.showForm(36);
                break;
            case "我想从你这换点师门贡献":
                PlayerMonitor.give(ImageMixDeal.getNpc(npcJpanel.getNpcInfoBean().getNpctable().getNpcid()));
                break;
            case "我要参加全名竞技切磋":
                String sendmes = Agreement.getAgreement().teamArenaAgreement("0");
                SendMessageUntil.toServer(sendmes);
                break;
            case "我要挑战试炼幻境":
                //      SendMessageUntil.toServer(Agreement.getAgreement().hjslAgreement("S"));

                //     String substring = mes.substring(1);//最高奖励数据
                //  DreamlandTrialMainJframe.getDreamlandTrialMainJframe().getDreamlandTrialMainJpanel().showView(substring);
                FormsManagement.showForm(111);

                break;
            case "一号当铺":
                FormsManagement.showForm(55);
                break;
            case "二号当铺":
                FormsManagement.showForm(1000);

                break;
            case "我要给帮派捐点钱":
                FormsManagement.showForm(112);
                break;
            case "三号当铺":
                FormsManagement.showForm(1001);
                break;
            case "我要兑换物品":
                FormsManagement.showForm(700);
                break;
            case "我要兑换道具":
                FormsManagement.showForm(90);
                break;
            case "我要兑换孤竹城积分":
                new NpcShop().menuControl(type);
                break;
            case "烧香":
            case "为本庙供奉香火":
                FormsManagement.showForm(636);
                break;
            case "兑换吃鸡奖励":
            case "兑换奖励。":
                new NpcShop().menuControl(type);
                break;

            case "匹配比武": // 天梯比武 匹配
                SendMessageUntil.toServer(Agreement.getAgreement().laddArenaAgreement("O"));
                break;
            case "我要交纳物品获得灵力": // 天梯比武 匹配
            case "我要注入灵力进行解卦": // 天梯比武 匹配
                MapAction.npcmenuAction.get(NpcMenuUntil.JG).menuControl(type.equals("我要交纳物品获得灵力") ? "兑换" : "解卦");
                break;
            case "终极技能：绝境逢生":
            case "终极技能：子虚乌有":
            case "终极技能：春回大地":
            case "终极技能：化无":
            case "终极技能：作鸟兽散":
            case "终极技能：将死":
            case "终极技能：明察秋毫":
            case "终极技能：双管齐下":
            case "终极技能：当头棒喝":
            case "终极技能：以牙还牙":
            case "终极技能：春暖花开":
            case "终极技能：夺魂索命":
                MapAction.npcmenuAction.get(NpcMenuUntil.getDelskill()).menuControl(type);
                break;
            case "我的钱太多了，想存起来":
            case "我没有钱花了，想把存款拿出来":
            case "查看我的保险箱":
            case "我什么也不做":
                MapAction.npcmenuAction.get(NpcMenuUntil.getMONEYCQ()).menuControl(type);
                break;
            case "我要浇水":
                String sendmes1 = Agreement.getAgreement().userAgreement("我要浇水");
                SendMessageUntil.toServer(sendmes1);
                break;
            case "我要除虫":
                sendmes1 = Agreement.getAgreement().userAgreement("我要除虫");
                SendMessageUntil.toServer(sendmes1);
                break;
            case "我要施肥":
                sendmes1 = Agreement.getAgreement().userAgreement("我要施肥");
                SendMessageUntil.toServer(sendmes1);
                break;
            default:
                if (UserMessUntil.getskill1(type) != null) {// 技能学习
                    MapAction.npcmenuAction.get(NpcMenuUntil.SKILL).menuControl(type);

                }
                else if (Arrays.asList(UserData.LBKX).contains(type)) {//灵宝抗性
                    RoleLingFa.getRoleLingFa().updateKX(type);//灵宝抗性
                }else if (type.equals("瀚威猫将的大刀") || type.indexOf("饰品") != -1) {// 饰品转换
                    PetEquip.getChangeCJS(npcJpanel.getGood(), type);
                    // MapAction.npcmenuAction.get(NpcMenuUntil.CJS).menuControl(type);
                }
                for (int i = 0; i < PetConversion.getPetListSkill().size(); i++) {
                    RoleSummoning pet = PetConversion.getPetListSkill().get(i);
                    String petName = pet.getSummoningname() + "  (编号：" + pet.getSid() + ")";
                    if (petName.equals(type)) {
                        PetConversion.setPet(PetConversion.getPetListSkill().get(i));
                        NPCJfram.getNpcJfram().getNpcjpanel().GetDelpetSkill1(PetConversion.getPetListSkill(),pet.getSid());
                        break;
                    }
                }
                break;

        }
    }

}
