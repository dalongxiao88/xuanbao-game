package org.come.action.shaoxiang;

import com.google.common.collect.Lists;
import org.come.model.TeJiLH;
import org.come.tool.Goodtype;
import come.tool.Good.UsePetAction;
import org.come.model.Configure;
import org.come.model.Skill;
import org.come.entity.RoleSummoning;
import java.text.DateFormat;
import org.come.entity.Goodstable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import come.tool.Good.DropUtil;
import org.come.action.suit.SuitMixdeal;
import come.tool.Stall.AssetUpdate;
import come.tool.Good.ExpUtil;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.model.ShaoXiangLimit;
import org.come.model.ShaoXiang;
import java.util.Map;
import java.util.ArrayList;
import org.come.bean.ShaoXiangLimitResultBean;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import java.util.Random;
import org.come.action.IAction;

public class ShaoXiangAction implements IAction
{
    public static Random random;
    private static List<String> TJ;
    private static List<String> YFTJ;
    private static List<String> XLTJ;
    private static List<String> MZTJ;
    private static List<String> XTJ;
    private static List<String> HSTJ;
    //烧香
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        ConcurrentHashMap<String, ShaoXiang> allshaoxiang = GameServer.getAllShaoXiang();
        if (message.startsWith("GETLIMIT")) {
            ConcurrentHashMap<String, ShaoXiangLimit> allmap = AllServiceUtil.getShaoXiangService().getAllList(loginResult.getRole_id().toString());
            ShaoXiangLimitResultBean sxb = new ShaoXiangLimitResultBean();
            List<String> shaoxianglist = new ArrayList<>();
            for (Map.Entry<String, ShaoXiang> entry : allshaoxiang.entrySet()) {
                shaoxianglist.add(entry.getKey());
                if (!allmap.containsKey(entry.getKey())) {
                    ShaoXiang shaoXiang = (ShaoXiang)entry.getValue();
                    ShaoXiangLimit shaoXiangLimit = new ShaoXiangLimit();
                    shaoXiangLimit.setLimit(shaoXiang.getMax());
                    shaoXiangLimit.setName(shaoXiang.getName());
                    shaoXiangLimit.setId(shaoXiang.getId());
                    shaoXiangLimit.setNum(0);
                    allmap.put(entry.getKey(), shaoXiangLimit);
                }
            }
            sxb.setAllshaoxiang(shaoxianglist);
            sxb.setAllshaoxianglimit(allmap);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().getShaoXiangLimitAgreement("GETLIMIT|" + GsonUtil.getGsonUtil().getgson().toJson(sxb)));
        }
        else if (allshaoxiang != null && allshaoxiang.size() > 0) {
            String[] param = message.split("\\|");
            if (param[0].contains("装备二特技")) {
                String goodsId1 = param[3];
                Goodstable tggood1 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(goodsId1));
                String[] v;
                for (String s : v = tggood1.getValue().split("\\|")) {
                    if (s.contains("炼化属性")) {
                        String[] vv = s.split("&");
                        int count = 0;
                        for (String string : vv) {
                            if (string.contains("特技")) {
                                ++count;
                            }
                        }
                        if (count == 0) {
                            String mes = Agreement.getAgreement().PromptAgreement("请先打造第一个特技！");
                            SendMessage.sendMessageToSlef(ctx, mes);
                            return;
                        }
                    }
                }
            }
            if (allshaoxiang.containsKey(param[0]) && allshaoxiang.get(param[0]) != null) {
                ShaoXiang shaoXiang2 = (ShaoXiang)allshaoxiang.get(param[0]);
                String goodsRgid = param[1];
                ShaoXiangLimit shaoXiangLimit2 = AllServiceUtil.getShaoXiangService().selectByID(loginResult.getRole_id().toString(), shaoXiang2.getId());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String nowdayTime = dateFormat.format(new Date());
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(goodsRgid));
                if ((int)good.getUsetime() < shaoXiang2.getNum()) {
                    String mes2 = Agreement.getAgreement().PromptAgreement("香火不足");
                    SendMessage.sendMessageToSlef(ctx, mes2);
                    return;
                }
                int result = 1;
                if (shaoXiangLimit2 != null) {
                    if (shaoXiangLimit2.getDate().equals(nowdayTime)) {
                        if (shaoXiangLimit2.getNum() < shaoXiang2.getMax()) {
                            shaoXiangLimit2.setNum(shaoXiangLimit2.getNum() + 1);
                            AllServiceUtil.getShaoXiangService().updateReidsLimit(shaoXiangLimit2);
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().getShaoXiangLimitAgreement("UPDATELIMIT|" + GsonUtil.getGsonUtil().getgson().toJson(shaoXiangLimit2)));
                        }
                        else {
                            String mes3 = Agreement.getAgreement().PromptAgreement("已经达到今日上限，请明天再来。");
                            SendMessage.sendMessageToSlef(ctx, mes3);
                            return;
                        }
                    }
                    else {
                        shaoXiangLimit2.setNum(1);
                        shaoXiangLimit2.setDate(nowdayTime);
                        AllServiceUtil.getShaoXiangService().addReidsLimit(shaoXiangLimit2);
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().getShaoXiangLimitAgreement("UPDATELIMIT|" + GsonUtil.getGsonUtil().getgson().toJson(shaoXiangLimit2)));
                    }
                }
                else {
                    shaoXiangLimit2 = new ShaoXiangLimit();
                    shaoXiangLimit2.setRoleId(loginResult.getRole_id().toString());
                    shaoXiangLimit2.setId(shaoXiang2.getId());
                    shaoXiangLimit2.setName(shaoXiang2.getName());
                    shaoXiangLimit2.setNum(1);
                    shaoXiangLimit2.setLimit(shaoXiang2.getMax());
                    shaoXiangLimit2.setDate(nowdayTime);
                    AllServiceUtil.getShaoXiangService().addReidsLimit(shaoXiangLimit2);
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().getShaoXiangLimitAgreement("UPDATELIMIT|" + GsonUtil.getGsonUtil().getgson().toJson(shaoXiangLimit2)));
                }
                if (shaoXiang2.getType().equals("2")) {
                    BigDecimal petId = loginResult.getSummoning_id();
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(petId);
                    BigDecimal addexp = new BigDecimal(shaoXiang2.getItem().split("=")[1]);
                    ExpUtil.PetExp(pet, addexp.longValue());
                    AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                    AssetUpdate assetUpdate = new AssetUpdate();
                    assetUpdate.setMsg("你的当前召唤收，获得" + String.valueOf(addexp.longValue()) + "经验");
                    assetUpdate.updata("P" + pet.getSid() + "=" + pet.getGrade() + "=" + pet.getExp() + "=" + pet.getFriendliness() + "=" + pet.getBasishp() + "=" + pet.getBasismp());
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                }
                else if (shaoXiang2.getType().equals("5")) {
                    BigDecimal petId = loginResult.getSummoning_id();
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(petId);
                    result = addPetSkill(pet, shaoXiang2.getItem(), ctx, loginResult);
                }
                else if (shaoXiang2.getType().equals("6")) {
                    String goodsId2 = param[3];
                    Goodstable tggood2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(goodsId2));
                    String etype = SuitMixdeal.lianhua(tggood2.getType());
                    String s2 = RefinersV(tggood2);
                    AllServiceUtil.getGoodsTableService().updateGoodsIndex(tggood2, null, tggood2.getGoodsid(), null);
                    AllServiceUtil.getGoodsrecordService().insert(tggood2, null, Integer.valueOf(1), Integer.valueOf(8));
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().getShaoXiangLimitAgreement("MODGOODS@" + GsonUtil.getGsonUtil().getgson().toJson(tggood2)));
                    String mes4 = Agreement.getAgreement().PromptAgreement("你的装备" + etype + "获得了一项特技。");
                    SendMessage.sendMessageToSlef(ctx, mes4);
                    Skill skill = GameServer.getSkill(s2);
                    if (skill != null) {
                        StringBuffer sb = new StringBuffer();
                        sb.append("#R");
                        sb.append(loginResult.getRolename());
                        sb.append("#W在炼化装备时,眼前一亮,大喊一声.卧槽#89原来得到了原来得到了");
                        sb.append("#R强力特技#35");
                        SuitMixdeal.jpdC(sb.toString());
                    }
                }
                else if (shaoXiang2.getType().equals("7")) {
                    String goodsId2 = param[3];
                    Goodstable tggood2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(goodsId2));
                    String etype = SuitMixdeal.lianhua(tggood2.getType());
                    String s2 = RefinersV1(tggood2);
                    Skill skill2 = GameServer.getSkill(s2);
                    if (skill2 != null) {
                        StringBuffer sb2 = new StringBuffer();
                        sb2.append("#R");
                        sb2.append(loginResult.getRolename());
                        sb2.append("#W在炼化装备时,眼前一亮,大喊一声.卧槽#89原来得到了");
                        sb2.append("#R牛逼的双特技#35");
                        SuitMixdeal.jpdC(sb2.toString());
                    }
                    AllServiceUtil.getGoodsTableService().updateGoodsIndex(tggood2, null, tggood2.getGoodsid(), null);
                    AllServiceUtil.getGoodsrecordService().insert(tggood2, null, Integer.valueOf(1), Integer.valueOf(8));
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().getShaoXiangLimitAgreement("MODGOODS@" + GsonUtil.getGsonUtil().getgson().toJson(tggood2)));
                    String mes5 = Agreement.getAgreement().PromptAgreement("你的装备" + etype + "获得了第二项特技。");
                    SendMessage.sendMessageToSlef(ctx, mes5);
                }
                else {
                    DropUtil.getDrop(loginResult, shaoXiang2.getItem(), "烧香", 22, 1.0, null);
                }
                if (result == 1) {
                    good.goodxh(shaoXiang2.getNum());
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
                    AssetUpdate assetUpdate2 = new AssetUpdate();
                    assetUpdate2.setType(AssetUpdate.USEGOOD);
                    assetUpdate2.updata("G" + good.getRgid() + "=" + good.getUsetime());
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                }
                else if (result == 2) {
                    String mes3 = Agreement.getAgreement().PromptAgreement("未发现这项技能");
                    SendMessage.sendMessageToSlef(ctx, mes3);
                }
            }
            else {
                String mes6 = Agreement.getAgreement().PromptAgreement("请检查您输入的物品名称#G" + message + "#Y是否正确");
                SendMessage.sendMessageToSlef(ctx, mes6);
            }
        }
    }
    
    public static int addPetSkill(RoleSummoning pet, String val, ChannelHandlerContext ctx, LoginResult login) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        int sum = (int)pet.getOpenSeal();
        Skill skill = UsePetAction.skillid(val);
        if (skill == null) {
            return 2;
        }
        List<String> skills = new ArrayList<>();
        if (pet.getPetSkills() != null && !pet.getPetSkills().equals("")) {
            String[] vs = pet.getPetSkills().split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (!vs[i].equals("")) {
                    skills.add(vs[i]);
                }
            }
        }
        if (sum <= skills.size() || skills.size() >= Integer.parseInt(configure.getZhsjngs())) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽技能格子已经满了"));
            return 3;
        }
        String value = UsePetAction.chongfu(skill, pet, skills, true);
        if (value != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(value));
            return 4;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        skills.add(skill.getSkillid() + "");
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < skills.size(); ++j) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append((String)skills.get(j));
        }
        pet.setPetSkills(buffer.toString());
        UsePetAction.getskills(skills, pet.getSkill());
        UsePetAction.getskills(skills, pet.getBeastSkills());
        pet.setSkillData(UsePetAction.skillData(skills));
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("你的召唤兽学会了" + skill.getSkillname());
        int Id = skill.getSkillid();
        if (Id == 3034) {
            assetUpdate.updata("T悟技");
            StringBuffer sb = new StringBuffer();
            sb.append("#R");
            sb.append(login.getRolename());
            sb.append("#c00FFFF供奉香火得到神明庇护,他的#R");
            sb.append(pet.getSummoningname());
            sb.append("#c00FFFF学到了一个修炼中的终极技能！#24");
            SuitMixdeal.jpdC(sb.toString());
        }
        else if ((Id >= 1815 && Id <= 1827) || (Id >= 1600 && Id <= 1605) || (Id >= 1834 && Id <= 1839) || (Id >= 1882 && Id <= 1888) || (Id >= 1611 && Id <= 1612) || Id == 1850 || Id == 1852 || Id == 1854 || Id == 1858 || Id == 1860 || Id == 1862 || Id == 1864 || Id == 1865 || Id == 1811 || Id == 1831 || Id == 1833 || (Id >= 1871 && Id <= 1880)) {
            assetUpdate.updata("T悟技");
            StringBuffer sb = new StringBuffer();
            sb.append("#R");
            sb.append(login.getRolename());
            sb.append("#c00FFFF供奉香火得到神明庇护,他的#R");
            sb.append(pet.getSummoningname());
            sb.append("#c00FFFF学到了一个#R");
            sb.append(skill.getSkillname());
            sb.append("#c00FFFF技能！#24");
            SuitMixdeal.jpdC(sb.toString());
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        return 1;
    }
    
    public static String RefinersV(Goodstable good) {
        String res = "";
        List<String> TJS = null;
        StringBuffer buffer = new StringBuffer();
        long Gtype = good.getType();
        int ZBType = Goodtype.EquipmentType(Gtype);
        String etype = SuitMixdeal.lianhua(Gtype);
        if (etype == null) {
            return res;
        }
        if (TJS == null) {
            if (ZBType == 0) {
                TJS = ShaoXiangAction.TJ;
            }
            else if (ZBType == 4) {
                TJS = ShaoXiangAction.HSTJ;
            }
            else if (ZBType == 3) {
                TJS = ShaoXiangAction.YFTJ;
            }
            else if (ZBType == 2) {
                TJS = ShaoXiangAction.XLTJ;
            }
            else if (ZBType == 1) {
                TJS = ShaoXiangAction.MZTJ;
            }
            else if (ZBType == 5) {
                TJS = ShaoXiangAction.XTJ;
            }
            if (TJS != null) {
                int y = -1;
                buffer.append("特技");
                int sj = ShaoXiangAction.random.nextInt(TJS.size());
                buffer.append("=");
                buffer.append((String)TJS.get(sj));
                res = (String)TJS.get(sj);
            }
        }
        String[] vl = good.getValue().split("\\|");
        StringBuffer attr = new StringBuffer();
        boolean haslh = false;
        boolean hastj = false;
        if (vl != null && vl.length > 0) {
            for (int i = 0; i < vl.length; ++i) {
                if (vl[i].startsWith("炼化属性")) {
                    haslh = true;
                    StringBuffer lhsx = new StringBuffer();
                    String[] vll = vl[i].split("&");
                    if (vll != null && vll.length > 0) {
                        for (int j = 0; j < vll.length; ++j) {
                            if (vll[j] != null && vll[j].startsWith("特技")) {
                                hastj = true;
                                vll[j] = buffer.toString();
                            }
                            if (j == 0) {
                                lhsx.append(vll[j]);
                            }
                            else {
                                lhsx.append("&" + vll[j]);
                            }
                        }
                        if (!hastj) {
                            lhsx.append("&" + buffer.toString());
                        }
                    }
                    vl[i] = lhsx.toString();
                }
                if (i == 0) {
                    attr.append(vl[i]);
                }
                else {
                    attr.append("|" + vl[i]);
                }
            }
            if (!haslh) {
                attr.append("|炼化属性&" + buffer.toString());
            }
        }
        else if (!haslh) {
            attr.append("炼化属性&" + buffer.toString());
        }
        good.setValue(attr.toString());
        return res;
    }
    
    public static String RefinersV1(Goodstable good) {
        String res = "";
        List<String> TJS = null;
        StringBuffer buffer = new StringBuffer();
        long Gtype = good.getType();
        int ZBType = Goodtype.EquipmentType(Gtype);
        String etype = SuitMixdeal.lianhua(Gtype);
        String[] vl = good.getValue().split("\\|");
        if (etype == null) {
            return res;
        }
        if (TJS == null) {
            if (ZBType == 0) {
                TJS = ShaoXiangAction.TJ;
            }
            else if (ZBType == 4) {
                TJS = ShaoXiangAction.HSTJ;
            }
            else if (ZBType == 3) {
                TJS = ShaoXiangAction.YFTJ;
            }
            else if (ZBType == 2) {
                TJS = ShaoXiangAction.XLTJ;
            }
            else if (ZBType == 1) {
                TJS = ShaoXiangAction.MZTJ;
            }
            else if (ZBType == 5) {
                TJS = ShaoXiangAction.XTJ;
            }
            if (TJS != null) {
                for (int i = 0; i < vl.length; ++i) {
                    if (vl[i].startsWith("炼化属性")) {
                        String[] vll = vl[i].split("&");
                        if (vll.length > 0) {
                            for (int j = 0; j < vll.length; ++j) {
                                if (vll[j] != null && vll[j].startsWith("特技")) {
                                    String[] vlll = vll[j].split("=");
                                    if (vlll.length > 0) {
                                        int k = 0;
                                        if (k < vlll.length) {
                                            int tj = Integer.parseInt(vlll[1]);
                                            buffer.append("特技");
                                            buffer.append("=");
                                            buffer.append(tj);
                                            int tj2;
                                            do {
                                                int sj = ShaoXiangAction.random.nextInt(TJS.size());
                                                tj2 = Integer.parseInt((String)TJS.get(sj));
                                            } while (tj == tj2);
                                            buffer.append("=");
                                            buffer.append(tj2);
                                            res = tj2 + "";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        StringBuffer attr = new StringBuffer();
        boolean haslh = false;
        boolean hastj = false;
        if (vl != null && vl.length > 0) {
            for (int l = 0; l < vl.length; ++l) {
                if (vl[l].startsWith("炼化属性")) {
                    haslh = true;
                    StringBuffer lhsx = new StringBuffer();
                    String[] vll2 = vl[l].split("&");
                    if (vll2 != null && vll2.length > 0) {
                        for (int m = 0; m < vll2.length; ++m) {
                            if (vll2[m] != null && vll2[m].startsWith("特技")) {
                                hastj = true;
                                vll2[m] = buffer.toString();
                            }
                            if (m == 0) {
                                lhsx.append(vll2[m]);
                            }
                            else {
                                lhsx.append("&" + vll2[m]);
                            }
                        }
                        if (!hastj) {
                            lhsx.append("&" + buffer.toString());
                        }
                    }
                    vl[l] = lhsx.toString();
                }
                if (l == 0) {
                    attr.append(vl[l]);
                }
                else {
                    attr.append("|" + vl[l]);
                }
            }
            if (!haslh) {
                attr.append("|炼化属性&" + buffer.toString());
            }
        }
        else if (!haslh) {
            attr.append("炼化属性&" + buffer.toString());
        }
        good.setValue(attr.toString());
        return res;
    }
    
    public static String getExtra(String[] v, String extra) {
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith(extra)) {
                return v[i];
            }
        }
        return null;
    }
    
    public static void saveGoods(Goodstable good, boolean l) {
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
        long gType = good.getType();
        if (gType != 212L && (gType < 497L || gType > 500L) && gType != 505L && gType != 7005L && gType != 191L && gType != 915L) {
            AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(13));
        }
    }
    
    static {
        ShaoXiangAction.random = new Random();
        ShaoXiangAction.TJ = Lists.newArrayList();
        ShaoXiangAction.YFTJ = Lists.newArrayList();
        ShaoXiangAction.XLTJ = Lists.newArrayList();
        ShaoXiangAction.MZTJ = Lists.newArrayList();
        ShaoXiangAction.XTJ = Lists.newArrayList();
        ShaoXiangAction.HSTJ = Lists.newArrayList();
        ConcurrentHashMap<Integer, TeJiLH> alllhtj = GameServer.getAlllhtj();
        if (alllhtj != null && !alllhtj.isEmpty()) {
            alllhtj.entrySet().stream().forEach(e/* java.util.Map.Entry, */ -> {
                if (((TeJiLH)e.getValue()).getWQ() != 0) {
                    ShaoXiangAction.TJ.add(String.valueOf(e.getKey()));
                }
                if (((TeJiLH)e.getValue()).getYF() != 0) {
                    ShaoXiangAction.YFTJ.add(String.valueOf(e.getKey()));
                }
                if (((TeJiLH)e.getValue()).getXL() != 0) {
                    ShaoXiangAction.XLTJ.add(String.valueOf(e.getKey()));
                }
                if (((TeJiLH)e.getValue()).getMZ() != 0) {
                    ShaoXiangAction.MZTJ.add(String.valueOf(e.getKey()));
                }
                if (((TeJiLH)e.getValue()).getXZ() != 0) {
                    ShaoXiangAction.XTJ.add(String.valueOf(e.getKey()));
                }
                if (((TeJiLH)e.getValue()).getHSF() != 0) {
                    ShaoXiangAction.HSTJ.add(String.valueOf(e.getKey()));
                }
                return;
            });
        }
    }
}
