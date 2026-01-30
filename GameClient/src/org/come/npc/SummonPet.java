package org.come.npc;

import java.util.Map;
import org.come.bean.ConfigureBean;
import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.SuitOperBean;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.PartJade;
import org.come.Frame.NPCJfram;
import org.come.action.NpcMenuAction;

public class SummonPet implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        // TODO Auto-generated method stub

        try {
            String name = type;
            if (type.equals("我是来召唤你的")) {
                String[] names = NPCJfram.getNpcJfram().getNpcjpanel().getNpcInfoBean().getNpctable().getNpcname().split("\\:");
                name = names[names.length - 1];
            }
            PartJade partJade = null;
            List<Integer> integers = null;
            int[] goodids = null;
            switch (name) {

                case "颜如玉":
                    partJade = new PartJade(1, 200101);
                    integers = tianshu();
                    break;
                case "五叶":
                    partJade = new PartJade(1, 200100);
                    integers = tianshu();
                    break;
                case "浪淘沙":
                    partJade = new PartJade(1, 200099);
                    integers = tianshu();
                    break;
                case "范式之魂":
                    partJade = new PartJade(1, 200098);
                    integers = tianshu();
                    break;
                case "垂云叟":
                    partJade = new PartJade(1, 200097);
                    integers = tianshu();
                    break;
                case "剧毒封印之书":
                    partJade = new PartJade(1, 200096);
                    goodids = new int[] { 174, 175, 177, 178 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "寒冰封印之书":
                    partJade = new PartJade(1, 200095);
                    goodids = new int[] { 174, 175, 177, 179 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "天雷封印之书":
                    partJade = new PartJade(1, 200094);
                    goodids = new int[] { 175, 176, 177, 178 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "御剑封印之书":
                    partJade = new PartJade(1, 200093);
                    goodids = new int[] { 176, 177, 178, 179 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "狂力封印之书":
                    partJade = new PartJade(1, 200092);
                    goodids = new int[] { 175, 176, 177, 179 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "冥灵古卷":
                    partJade = new PartJade(1, 200135);
                    goodids = new int[] { 176, 178, 179, 179, 181, 182 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "玲珑古卷":
                    partJade = new PartJade(1, 200147);
                    goodids = new int[] { 177, 178, 179, 179, 180, 181 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "巨翼古卷":
                    partJade = new PartJade(1, 200137);
                    goodids = new int[] { 176, 177, 179, 179, 180, 182 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "苍凛古卷":
                    partJade = new PartJade(1, 200145);
                    goodids = new int[] { 174, 174, 179, 180, 181, 183 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "赭炎古卷":
                    partJade = new PartJade(1, 200132);
                    goodids = new int[] { 174, 175, 180, 181, 182, 183 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "当康古卷":
                    partJade = new PartJade(1, 200133);
                    goodids = new int[] { 174, 176, 177, 180, 180, 183 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "松鼠古卷":
                    partJade = new PartJade(1, 200134);
                    goodids = new int[] { 180, 180, 180, 181, 181, 181 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "佳音小使古卷":
                    partJade = new PartJade(1, 200146);
                    goodids = new int[] { 175, 176, 179, 180, 182, 182 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "葫芦古卷":
                    partJade = new PartJade(1, 200136);
                    goodids = new int[] { 174, 176, 177, 179, 182, 183 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "如意古卷":
                    partJade = new PartJade(1, 200144);
                    goodids = new int[] { 175, 178, 180, 181, 181, 182 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "我要兑换武圣之魂":
                    partJade = new PartJade(1, 200157);
                    goodids = new int[] { 80047 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "年(588碎片)":
                    partJade = new PartJade(1, 200116);
                    goodids = new int[588];
                    for (int i = 0; i < 588; i++) {
                        goodids[i] = 80167;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "画中仙(488碎片)":
                    partJade = new PartJade(1, 200117);
                    goodids = new int[488];
                    for (int i = 0; i < 488; i++) {
                        goodids[i] = 80167;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "白泽(468碎片)":
                    partJade = new PartJade(1, 200123);
                    goodids = new int[468];
                    for (int i = 0; i < 468; i++) {
                        goodids[i] = 80167;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "龙马(188碎片)":
                    partJade = new PartJade(1, 200124);
                    goodids = new int[188];
                    for (int i = 0; i < 188; i++) {
                        goodids[i] = 80168;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "画皮娘子(188碎片)":
                    partJade = new PartJade(1, 200138);
                    goodids = new int[188];
                    for (int i = 0; i < 188; i++) {
                        goodids[i] = 80168;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "孔雀明王(188碎片)":
                    partJade = new PartJade(1, 200141);
                    goodids = new int[188];
                    for (int i = 0; i < 188; i++) {
                        goodids[i] = 80168;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "北冥龙君(288碎片)":
                    partJade = new PartJade(1, 200142);
                    goodids = new int[288];
                    for (int i = 0; i < 288; i++) {
                        goodids[i] = 80168;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "孟极(288碎片)":
                    partJade = new PartJade(1, 200140);
                    goodids = new int[288];
                    for (int i = 0; i < 288; i++) {
                        goodids[i] = 80168;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "镜花水月(388碎片)":
                    partJade = new PartJade(1, 200143);
                    goodids = new int[388];
                    for (int i = 0; i < 388; i++) {
                        goodids[i] = 80168;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "妙音栾女(388碎片)":
                    partJade = new PartJade(1, 200158);
                    goodids = new int[388];
                    for (int i = 0; i < 388; i++) {
                        goodids[i] = 80168;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "换个高级藏宝图":
                    partJade = new PartJade(0, 170);
                    goodids = new int[] { 171, 171, 171 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "换个超级藏宝图":
                    partJade = new PartJade(0, 80060);
                    goodids = new int[] { 80059, 80059, 80059, 80059, 80059 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "我要兑换推广礼包":
                    partJade = new PartJade(0, 80046);
                    goodids = new int[] { 80047 };
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "兑换彩晶石":
                    partJade = new PartJade(0, 80165);
                    goodids = new int[10];
                    for (int i = 0; i < 10; i++) {
                        goodids[i] = 80164;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "兑换超级龙之骨":
                    partJade=new PartJade(0,92381);
                    goodids = new int[3];
                    for (int i = 0; i < 3; i++) {
                        goodids[i] = 188;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                case "兑换高级聚魄丹":
                    partJade=new PartJade(0,92383);
                    goodids = new int[3];
                    for (int i = 0; i < 3; i++) {
                        goodids[i] = 772;
                    }
                    integers = GoodsListFromServerUntil.chaxuns(goodids);
                    break;
                default:
                    break;
            }
            if (partJade == null || integers == null) {
                if (goodids != null) {
                    FrameMessageChangeJpanel.addtext(6, name +"的物品不足"+ goodids.length +"个", null, null);
                }
                return;
            }
            if (partJade.getSuitid() == 1) {
                ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                Configure configure = item.get(new BigDecimal(1));

                if (UserMessUntil.getPetListTable() != null && UserMessUntil.getPetListTable().size() >= Integer.parseInt(configure.getXdzhssx())) {
                    ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽可携带的数量已满！！！");
                    return;
                }
            } else if (partJade.getSuitid() == 0) {
                // 先判断背包是否还有空位
                int packNumber = GoodsListFromServerUntil.Surplussum("-1", "-1", 999);
                if (packNumber <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("背包已满");
                    return;
                }
            }
            SuitOperBean operBean = new SuitOperBean();
            operBean.setType(21);
            operBean.setJade(partJade);
            operBean.setGoods(GoodsListFromServerUntil.Delete2(integers));
            String senmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(operBean));
            SendMessageUntil.toServer(senmes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 判断是否有10个天书
     */
    public List<Integer> tianshu() {
        int[] goodids = { 901, 902, 903, 904, 905, 906, 907, 908, 909, 910 };
        return GoodsListFromServerUntil.chaxuns(goodids);
    }
}
