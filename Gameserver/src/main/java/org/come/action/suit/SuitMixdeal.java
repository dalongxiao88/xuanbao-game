package org.come.action.suit;

import come.tool.Good.DropV;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.StringUtils;
import org.come.action.rich.InputBean;
import org.come.bean.LoginResult;
import org.come.entity.Goodstable;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import com.gl.util.Config;
import come.tool.Good.UsePalAction;
import org.come.tool.Goodtype;
import java.math.BigDecimal;
import org.come.server.GameServer;
import org.come.model.Gem;
import java.util.Random;

public class SuitMixdeal
{
    static Random random;
    static String[] NOS;
    static String MSG4;
    static String MSG5;
    static String MSG6;
    static String MSG7;
    static String MSG8;
    static String MSG9;
    static String MSG10;
    static String MSG11;
    static String MSG12;
    static String MSG13;
    static String MSG14;
    static String MSG15;
    
    public static Gem getGemType(long type) {
        String goodname = null;
        if (type == 746L) {
            goodname = "赤焰石";
        }
        else if (type == 749L) {
            goodname = "芙蓉石";
        }
        else if (type == 752L) {
            goodname = "寒山石";
        }
        else if (type == 755L) {
            goodname = "孔雀石";
        }
        else if (type == 758L) {
            goodname = "琉璃石";
        }
        else if (type == 761L) {
            goodname = "落星石";
        }
        else if (type == 764L) {
            goodname = "沐阳石";
        }
        else if (type == 767L) {
            goodname = "紫烟石";
        }
        return GameServer.getGem(goodname);
    }
    
    public static String getPartsName(int id) {
        switch (id) {
            case 1: {
                return "帽子";
            }
            case 2: {
                return "项链";
            }
            case 3: {
                return "衣服";
            }
            case 6: {
                return "面具";
            }
            case 7: {
                return "腰带";
            }
            case 8: {
                return "披风";
            }
            case 9: {
                return "挂件";
            }
            case 10: {
                return "左戒指";
            }
            case 11: {
                return "右戒指";
            }
            default: {
                return null;
            }
        }
    }
    
    public static int getPZlvl(String pz) {
        int n = -1;
        switch (pz.hashCode()) {
            case 811615: {
                if (pz.equals("把玩")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (pz.equals("贴身")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (pz.equals("珍藏")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (pz.equals("无价")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 647926: {
                if (pz.equals("传世")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1000027: {
                if (pz.equals("神迹")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return 1;
            }
            case 1: {
                return 2;
            }
            case 2: {
                return 3;
            }
            case 3: {
                return 4;
            }
            case 4: {
                return 5;
            }
            case 5: {
                return 6;
            }
            default: {
                return 1;
            }
        }
    }
    
    public static String getPZName(int pzlvl) {
        switch (pzlvl) {
            case 1: {
                return "把玩";
            }
            case 2: {
                return "贴身";
            }
            case 3: {
                return "珍藏";
            }
            case 4: {
                return "无价";
            }
            case 5: {
                return "传世";
            }
            case 6: {
                return "神迹";
            }
            default: {
                return null;
            }
        }
    }
    
    public static int returnJadeNum(int type) {
        switch (type) {
            case 1: {
                return 5;
            }
            case 2: {
                return 4;
            }
            case 3: {
                return 3;
            }
            case 4: {
                return 3;
            }
            default: {
                return 99999999;
            }
        }
    }
    
    public static BigDecimal returnJadeMoney(int type) {
        switch (type) {
            case 1: {
                return new BigDecimal(5000000);
            }
            case 2: {
                return new BigDecimal(5000000);
            }
            case 3: {
                return new BigDecimal(5000000);
            }
            case 4: {
                return new BigDecimal(5000000);
            }
            default: {
                return new BigDecimal(5000000);
            }
        }
    }
    
    public static int returnExcNum(int type) {
        switch (type) {
            case 1: {
                return 1;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 4: {
                return 2;
            }
            case 5: {
                return 3;
            }
            case 6: {
                return 3;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static int getSxlxz(int pz) {
        switch (pz) {
            case 1: {
                return 10;
            }
            case 2: {
                return 20;
            }
            case 3: {
                return 40;
            }
            case 4: {
                return 80;
            }
            case 5: {
                return 160;
            }
            default: {
                return 9999999;
            }
        }
    }
    
    public static String lianhua(long type) {
        String leixing = null;
        int etype = Goodtype.EquipmentType(type);
        if (etype == -1) {
            etype = UsePalAction.palEquipPath(type);
            if (etype == 0) {
                return "武器";
            }
            if (etype == 2) {
                return "衣服";
            }
            if (etype == 3) {
                return "帽子";
            }
            if (etype == 4) {
                return "项链";
            }
            if (etype == 5) {
                return "鞋子";
            }
        }
        switch (etype) {
            case 0: {
                leixing = "武器";
                break;
            }
            case 1: {
                leixing = "帽子";
                break;
            }
            case 2: {
                leixing = "项链";
                break;
            }
            case 3: {
                leixing = "衣服";
                break;
            }
            case 4: {
                leixing = "护身符";
                break;
            }
            case 5: {
                leixing = "鞋子";
                break;
            }
            case 12: {
                leixing = "翅膀";
                break;
            }
            case 14: {
                leixing = "靓号";
                break;
            }
        }
        if (leixing != null && (Goodtype.GodEquipment_xian(type))) {
            leixing = "仙器" + leixing;
        }
        if (leixing != null && (Goodtype.GodEquipment_God(type))) {
            leixing = "神兵" + leixing;
        }
        return leixing;
    }
    
    public static int getAlchemySum(long type) {
        int size = 1;
        if (type == 6500L || type == 6900L || type == 6601L || type == 6600L || type == 6701L || type == 6700L || type == 6900L || type == 6800L) {}
        if (SuitMixdeal.random.nextInt(100) < 39) {
            ++size;
        }
        if (SuitMixdeal.random.nextInt(100) < 9) {
            ++size;
        }
        return size;
    }
    
    public static int getAlchemySum(long type, int max) {
        int size = 1;
        if (SuitMixdeal.random.nextInt(100) < 80) {
            ++size;
        }
        if (SuitMixdeal.random.nextInt(100) < 70) {
            ++size;
        }
        if (max > 3) {
            if (SuitMixdeal.random.nextInt(100) < 60) {
                ++size;
            }
            if (max > 4 && SuitMixdeal.random.nextInt(100) < 50) {
                ++size;
            }
        }
        return (max > size) ? size : max;
    }
    
    public static int getTJSum(long type) {
        int size = 0;
        if (Goodtype.GodEquipment_xian(type) || Goodtype.GodEquipment_Ding(type)) {
            if (SuitMixdeal.random.nextInt(Config.getInt("xq_teji1")) == 0) {
                ++size;
                if (SuitMixdeal.random.nextInt(Config.getInt("xq_teji2")) == 0) {
                    ++size;
                }
            }
        }
        else if (Goodtype.GodEquipment_God(type)) {
            if (SuitMixdeal.random.nextInt(Config.getInt("sb_teji1")) == 0) {
                ++size;
                if (SuitMixdeal.random.nextInt(Config.getInt("sb_teji2")) == 0) {
                    ++size;
                }
            }
        }
        else if (SuitMixdeal.random.nextInt(Config.getInt("pt_teji1")) == 0) {
            ++size;
            if (SuitMixdeal.random.nextInt(Config.getInt("pt_teji2")) == 0) {
                ++size;
            }
        }
        return size;
    }
    
    public static String GetGodEquipment(String[] vs, int lvl) {
        StringBuffer buffer = new StringBuffer();
        int ylvl = Integer.parseInt(vs[0].split("=")[1]);
        for (int i = 0; i < vs.length; ++i) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            if (vs[i].length() >= 4) {
                String vj = vs[i].substring(0, 4);
                if (vj.equals("装备角色") || vj.equals("性别要求") || vj.equals("炼化属性") || vj.equals("炼器属性") || vj.equals("神兵属性") || vj.equals("套装属性")) {
                    buffer.append(vs[i]);
                    continue;
                }
            }
            String[] vss = vs[i].split("=");
            buffer.append(vss[0]);
            buffer.append("=");
            if (i > 2) {
                buffer.append((float)(Double.parseDouble(vss[1]) / (double)ylvl * (double)lvl));
            }
            else {
                buffer.append((int)(Double.parseDouble(vss[1]) / (double)ylvl * (double)lvl));
            }
        }
        return buffer.toString();
    }
    
    public static void sbsj(int lvl, String rolename, String goodname) {
        String msg = null;
        if (lvl == 4) {
            msg = SuitMixdeal.MSG4;
        }
        else if (lvl == 5) {
            msg = SuitMixdeal.MSG5;
        }
        else if (lvl == 6) {
            msg = SuitMixdeal.MSG6;
        }
        else if (lvl == 7) {
            msg = SuitMixdeal.MSG7;
        }
        else if (lvl == 8) {
            msg = SuitMixdeal.MSG8;
        }
        else if (lvl == 9) {
            msg = SuitMixdeal.MSG9;
        }
        else if (lvl == 10) {
            msg = SuitMixdeal.MSG10;
        }
        else if (lvl == 11) {
            msg = SuitMixdeal.MSG11;
        }
        else if (lvl == 12) {
            msg = SuitMixdeal.MSG12;
        }
        else if (lvl == 13) {
            msg = SuitMixdeal.MSG13;
        }
        else if (lvl == 14) {
            msg = SuitMixdeal.MSG14;
        }
        else if (lvl == 15) {
            msg = SuitMixdeal.MSG15;
        }
        if (msg == null) {
            return;
        }
        msg = msg.replace("{角色名}", rolename);
        msg = msg.replace("{物品名}", goodname);
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(msg);
        msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    /**
     * 刮刮乐类型喊话
     */
    public static void ggl(String goodname, String name, String good) throws Exception {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#c00FFFF玩家#R[");
        buffer.append(name);
        buffer.append("]#c00FFFF使用#Y");
        buffer.append(good);
        buffer.append("#c00FFFF意外获得");
        buffer.append("#G");
        buffer.append(goodname);
        buffer.append("#c00FFFF");
        buffer.append("，真是人品爆发#49");
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    public static void good2222(Goodstable good, LoginResult login, String msg, int type) {
        if (msg == null || msg.equals("")) {
            return;
        }
        if(msg.contains("DayDraw")){
            msg = msg.replace("DayDraw","");
            ChannelHandlerContext ctx = GameServer.getRoleNameMap().get(login.getRolename());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().roledaydrawAgreement("获得奖励="+good.getGoodsid().toString()));
        }
        if (good.getQuality() == null || good.getQuality() < 2) {
            return;
        }
        if (type == 22) {
            StringBuffer buffer = new StringBuffer();





            buffer.append("#c00FFFF玩家#R[");
            buffer.append(login.getRolename());
            buffer.append("]#c00FFFF使用#Y");
            buffer.append(msg);
            buffer.append("#c00FFFF意外获得");
            buffer.append("#G");
            buffer.append(good.getGoodsname());
            buffer.append("#c00FFFF");
            buffer.append("，真是人品爆发#51");
            msg = buffer.toString();


        } else {

            if(StringUtils.isNotEmpty(login.getLiangHao())){
                InputBean ib = new InputBean();
                ib.setName("["+login.getRolename());
                ib.setGoodNum(login.getLiangHao());
                ib.setGoodNumType(String.valueOf(login.getLianghaotype()));
                ib.setId(login.getRole_id());
                ib.setColor("R");
                ib.setType(5);



                msg = msg.replace("{角色名}", "#R#V"+GsonUtil.getGsonUtil().getgson().toJson(ib));
            }else {
                msg = msg.replace("{角色名}", login.getRolename());
            }


            msg = msg.replace("{物品名}", good.getGoodsname());
        }
        // 发起世界喊话
        NChatBean bean = new NChatBean();
        if (good.getQuality() > 3) {
            bean.setId(9);
        } else {
            bean.setId(5);
        }


        bean.setMessage(msg);




        msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    public static void good(Goodstable good, String rolename, String msg, int type) {
        if (msg == null || msg.equals("")) {
            return;
        }
        if (good.getQuality() == null || (long)good.getQuality() < 2L) {
            return;
        }
        if (msg.equals("超级藏宝图")) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("#c00FFFF【超级藏宝图】#R");
            buffer.append(rolename);
            buffer.append("#c00FFFF一铲子下去，发现多了一个#G[");
            buffer.append(good.getGoodsname());
            buffer.append("#G]。");
            msg = buffer.toString();
            NChatBean bean = new NChatBean();
            if ((long)good.getQuality() < 2L) {
                bean.setId(9);
            }
            else {
                bean.setId(5);
            }
            bean.setMessage(msg);
            msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
            return;
        }
        else if (msg.equals("高级藏宝图")) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("#c00FFFF某人在挖宝中获得");
            buffer.append("#G[");
            buffer.append(good.getGoodsname());
            buffer.append("#G]。");
            msg = buffer.toString();
            NChatBean bean = new NChatBean();
            if ((long)good.getQuality() < 2L) {
                bean.setId(9);
            }
            else {
                bean.setId(5);
            }
            bean.setMessage(msg);
            msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
            return;
        }
        else if (msg.equals("混沌兽决")) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("#c00FFFF混沌生万物！");
            buffer.append("#R");
            buffer.append(rolename);
            buffer.append("#c00FFFF在江湖百晓生的帮助下，解除了混沌兽决的封印，获得了一本#G[");
            buffer.append(good.getGoodsname());
            buffer.append("#G]。");
            msg = buffer.toString();
            NChatBean bean = new NChatBean();
            if ((long)good.getQuality() < 2L) {
                bean.setId(9);
            }
            else {
                bean.setId(5);
            }
            bean.setMessage(msg);
            msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
            return;
        }
        else if (msg.equals("烧香")) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("#R");
            buffer.append(rolename);
            buffer.append("#c00FFFF供奉香火得到神明庇护,获得赏赐#G[");
            buffer.append(good.getGoodsname());
            buffer.append("#G]#c00FFFF。#24");
            msg = buffer.toString();
            NChatBean bean = new NChatBean();
            if ((long)good.getQuality() < 2L) {
                bean.setId(9);
            }
            else {
                bean.setId(5);
            }
            bean.setMessage(msg);
            msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
            return;
        }
        else {
            if (type == 22) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("#c00FFFF玩家#R[");
                buffer.append(rolename);
                buffer.append("]#c00FFFF使用#Y");
                buffer.append(msg);
                buffer.append("#c00FFFF意外获得");
                buffer.append("#G");
                buffer.append(good.getGoodsname());
                buffer.append("#c00FFFF");
                buffer.append("，真是人品爆发#49");
                msg = buffer.toString();
            }
            else {
                msg = msg.replace("{角色名}", rolename);
                msg = msg.replace("{物品名}", good.getGoodsname());
            }
            NChatBean bean2 = new NChatBean();
            if ((long)good.getQuality() > 3L) {
                bean2.setId(9);
            }
            else {
                bean2.setId(5);
            }
            bean2.setMessage(msg);
            msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean2));
            SendMessage.sendMessageToAllRoles(msg);
            return;
        }
    }
    
    public static void good(Goodstable good, String rolename, DropV dropV) {
        if (good.getQuality() == null || (long)good.getQuality() < 2L) {
            return;
        }
        String msg = dropV.getMsg();
        if (msg == null || msg.equals("")) {
            return;
        }
        if (dropV.getI() == 1) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("#c00FFFF玩家#R[");
            buffer.append(rolename);
            buffer.append("]#c00FFFF使用#Y");
            buffer.append(dropV.getV2());
            buffer.append("#c00FFFF意外获得");
            buffer.append("#G");
            buffer.append(good.getGoodsname());
            buffer.append("#c00FFFF");
            buffer.append("，真是人品爆发#49");
            msg = buffer.toString();
        }
        else if (msg != null) {
            msg = msg.replace("{角色名}", rolename);
            msg = msg.replace("{物品名}", good.getGoodsname());
        }
        NChatBean bean = new NChatBean();
        if ((long)good.getQuality() > 3L) {
            bean.setId(9);
        }
        else {
            bean.setId(5);
        }
        bean.setMessage(msg);
        msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public static void Gem(String rolename, String goodname, int lvl) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#c00FFFF鸿运当头,#R");
        buffer.append(rolename);
        buffer.append("#c00FFFF成功合成");
        buffer.append(lvl);
        buffer.append("级宝石#R");
        buffer.append(goodname);
        buffer.append("#c00FFFF,天地为之一震！#89");
        NChatBean bean = new NChatBean();
        if (lvl == 10) {
            bean.setId(9);
        }
        else {
            bean.setId(5);
        }
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    //终极技能喊话
    public static void PYJN(String rolename, String petname, String skillname) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#R");
        buffer.append(rolename);
        buffer.append("#W的#G");
        buffer.append(petname);
        buffer.append("#W修炼终成正果,领悟了#Y");
        buffer.append(skillname);
        buffer.append("#W技能");
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public static void JN3(String rolename, String petname, String skillname, String lvl) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#R");
        buffer.append(rolename);
        buffer.append("#c00FFFF消耗巨资，为#R");
        buffer.append(petname);
        buffer.append("#c00FFFF重新修炼了");
        buffer.append("#G[");
        buffer.append(skillname);
        buffer.append("]#c00FFFF。#85");
        buffer.append("获得了一个全新的#G[");
        buffer.append("???");
        buffer.append("]#c00FFFF。#50百尺竿头，更进一步。");
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public static void JN(String rolename, String petname, String skillname, String lvl) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#R");
        buffer.append(rolename);
        buffer.append("#c00E3E3的#R");
        buffer.append(petname);
        buffer.append("#c00E3E3轻点骰子，顿时领悟了一个");
        buffer.append(lvl);
        buffer.append("技能#G[");
        buffer.append(skillname);
        buffer.append("]#c00E3E3。#50");
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public static void JN5(String rolename, String petname, String skillname, String lvl) {
        if (lvl.equals("普通")) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        if (lvl.equals("高级")) {
            buffer.append("#R");
            buffer.append(rolename);
            buffer.append("#c00E3E3的#R");
            buffer.append(petname);
            buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
            buffer.append(lvl);
            buffer.append("技能#G[");
            buffer.append(skillname);
            buffer.append("]#c00E3E3。#50");
        }
        else if (lvl.equals("终极")) {
            if (skillname.equals("化无")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，大喊一声.大表哥来#R化无#c00E3E3顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50");
                buffer.append("#c00E3E3所谓：若有众生如来度者，如来既有我--皆化无所化。");
            }
            else if (skillname.equals("当头棒喝")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50#R");
                buffer.append(petname);
                buffer.append("#c00E3E3大喝一句“这不机会来了.我要进场，快点召唤我。”#78[");
            }
            else if (skillname.equals("绝境逢生")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50");
                buffer.append("#c00E3E3万事皆可绝处逢生#55");
            }
            else if (skillname.equals("将死")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50#R");
                buffer.append(petname);
                buffer.append("#c00E3E3嘲讽的笑道“快来杀我呀！来杀我呀！”#65");
            }
            else if (skillname.equals("双管齐下")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50#R");
                buffer.append(petname);
                buffer.append("#c00E3E3一脸的奸笑，默默的说着“杀敌一千.自损八百。”#89");
            }
            else if (skillname.equals("明察秋毫")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50#R");
                buffer.append(petname);
                buffer.append("#c00E3E3所谓知己知彼，百战百胜。#69");
            }
            else if (skillname.equals("春回大地")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50#R");
            }
            else if (skillname.equals("春暖花开")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50#R");
            }
            else if (skillname.equals("作鸟兽散")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50#R");
            }
            else if (skillname.equals("以牙还牙")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50#R");
            }
            else if (skillname.equals("夺魂索命")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50#R");
            }
            else if (skillname.equals("子虚乌有")) {
                buffer.append("#R");
                buffer.append(rolename);
                buffer.append("#c00E3E3的#R");
                buffer.append(petname);
                buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
                buffer.append(lvl);
                buffer.append("技能#G[");
                buffer.append(skillname);
                buffer.append("]#c00E3E3。#50#R");
            }
        }
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public static void JN6(String rolename, String petname, String skillname, String lvl) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#R");
        buffer.append(rolename);
        buffer.append("#c00E3E3的#R");
        buffer.append(petname);
        buffer.append("#c00E3E3灵光一闪，顿时领悟了一个");
        buffer.append(lvl);
        buffer.append("技能#G[");
        buffer.append(skillname);
        buffer.append("]#c00E3E3。#50");
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public static void JN2(String rolename, String petname, String skillname, String lvl) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#R");
        buffer.append(rolename);
        buffer.append("#c00E3E3在王母娘娘的兽园中偶遇灵兽，毫不犹豫的让自己的#R");
        buffer.append(petname);
        buffer.append("#c00E3E3学习了#G[");
        buffer.append(skillname);
        buffer.append("]#c00E3E3");
        buffer.append(lvl);
        buffer.append("技能。#89");
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public static void Stealing(String rolename, long money) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#c00FFFF不得了啊，");
        buffer.append("#Y");
        buffer.append(rolename);
        buffer.append("#c00FFFF身手敏捷，一把抓住#G灵猴#c00FFFF的尾巴：“小样还想跑？先把钱留下！”硬是从灵猴身上抢回了#Y");
        buffer.append(money);
        buffer.append("#c00FFFF金钱");
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public static void jpd(String rolename, String petname) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#c00FFFF");
        buffer.append("鸿运当头，");
        buffer.append("#R");
        buffer.append(rolename);
        buffer.append("#c00FFFF");
        buffer.append("使用启魂丹时意外的让自己的召唤兽");
        buffer.append("#R");
        buffer.append(petname);
        buffer.append("#c00FFFF解封了一个#G新的技能封印！");
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public static void battleJpd(String rolename, String petname) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#Y");
        buffer.append(rolename);
        buffer.append("#W");
        buffer.append("结束战斗时，其携带的");
        buffer.append("#Y");
        buffer.append(petname);
        buffer.append("#W");
        buffer.append("意外的解开了一个新的技能格封印！#50");
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    /**转盘抽奖喊话*/
    public static void ZP(Goodstable good, String type, String rolename, String drawIds) {
        if (good.getQuality() == null || (long)good.getQuality() < 2L) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("#R");
        buffer.append(rolename);
        buffer.append("#Y在#c00FFFF");
        buffer.append(type);
        buffer.append("#Y中被#G");
        buffer.append(good.getGoodsname());
        buffer.append("#Y砸中#24,幸福得快要晕过去了#89#89");
        NChatBean bean = new NChatBean();
        if (drawIds.contains(good.getGoodsid().toString())) {
            bean.setId(4);
            bean.setRoleId(good.getRole_id());
            bean.setRole(rolename);
            bean.setMessage(buffer.toString());
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
        }
    }
    /**
     * 转盘抽奖喊话
     */
    public static void ZP(Goodstable good, String type, String rolename) {
        if (good.getQuality() == null || good.getQuality() < 2) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("#R");
        buffer.append(rolename);
        buffer.append("#Y在#c00FFFF");
        buffer.append(type);
        buffer.append("#Y中被#G");
        buffer.append(good.getGoodsname());
        buffer.append("#Y砸中#24,紧忙收入囊中#132,迈出了六亲不认得步伐离开了#89#89");
        // 发起世界喊话
        NChatBean bean = new NChatBean();
        if (good.getQuality() > 3) {
            bean.setId(9);
        } else {
            bean.setId(5);
        }
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }

    /**仙玉转盘喊话*/
    public static void XYZP(String rolename, int value) {
        if (value < 10000) {
            return;
        }
        NChatBean bean = new NChatBean();
        if (value >= 100000) {
            bean.setId(9);
        }
        else {
            bean.setId(5);
        }
        if (value == 100000) {
            bean.setMessage("#Y天道之外,轮回之中,恭喜#R" + rolename + "#Y被几率大神附身,在仙玉转盘中获得#R10万仙玉#Y大奖#97转身挥一挥衣袖不带走一片云彩#50");
        }
        else if (value == 50000) {
            bean.setMessage("#Y乌云遮日,电闪雷鸣,#R" + rolename + "#Y在仙玉转盘中竟然抽中了#R5万仙玉#Y,让诸位英雄豪杰羡煞不已#89");
        }
        else {
            bean.setMessage("祥瑞彩照,鸿运当头.#R" + rolename + "#Y在仙玉转盘中意外获得#R" + value / 10000 + "万仙玉#24,真乃好运!");
        }
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    //月卡转盘喊话
    public static void CBTZP(String rolename, String value) {
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage("#Y天道之外,轮回之中,恭喜#G【" + rolename + "】#Y被几率大神附身,在特权转盘中获得#R【" + value + "】#Y大奖#97转身挥一挥衣袖不带走一片云彩#50");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public static void jpdC(String msg1) {
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(msg1);
        String msg2 = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg2);
    }
    
    static {
        SuitMixdeal.random = new Random();
        SuitMixdeal.NOS = new String[] { "炼化属性", "炼器属性", "神兵属性", "套装属性", "宝石属性", "装备角色", "性别要求" };
        SuitMixdeal.MSG4 = "#G{角色名}#Y眼疾手快的夺回了何大雷刚想贪污的#R{物品名}#Y,定眼一看原来四级了,乐的屁颠屁颠的。";
        SuitMixdeal.MSG5 = "#Y刹那间,天空忽然传来#G{角色名}#Y的一声长啸“有此五级#R{物品名}#Y,今世别无所求”";
        SuitMixdeal.MSG6 = "#Y神器问世，谁与争锋！何大雷双手将六级#R{物品名}#Y奉上，叹道：“只有风流如#G{角色名}#Y，才配得上这绝世神兵”。";
        SuitMixdeal.MSG7 = "#Y神器问世，谁与争锋!何大雷双手将七级#R{物品名}#Y奉上，瞪大了眼睛叹道:“阁下莫非远古神魔吗，冷漠的#G{角色名}#Y，强装镇静的接过#R{物品名}”";
        SuitMixdeal.MSG8 = "#Y神器问世，谁与争锋!何大雷双手将八级#R{物品名}#Y奉上，惊道:“真乃天意啊，也只有#G{角色名}#Y， 才配得上这绝世神兵”";
        SuitMixdeal.MSG9 = "#Y神器问世，谁与争锋!何大雷双手将九级#R{物品名}#Y奉上，叹道:“我何大雷此生能打出此等神物死而无憾#G{角色名}#Y， 颤抖的接过#R{物品名}”。";
        SuitMixdeal.MSG10 = "#Y神器问世，谁与争锋!刚出炉的十级#R{物品名}#Y所产生的能量波直接蹦飞了何大雷，#G{角色名}#Y默默的拿起#R{物品名}#Y惋惜道可怜的何大雷不知何时才能痊愈!";
        SuitMixdeal.MSG11 = "#Y神器问世，谁与争锋!刚出炉的十一级#R{物品名}#Y所产生的能量波直接蹦死了何大雷，#G{角色名}#Y默默的拿起#R{物品名}#Y惋惜道此天地间再无何大雷!";
        SuitMixdeal.MSG12 = "#Y上古神器问世，谁与争锋!刚出炉的十二级#R{物品名}#Y所产生的能量波直接蹦死了何大雷，#G{角色名}#Y默默的拿起#R{物品名}#Y惋惜道此天地间再无何大雷!";
        SuitMixdeal.MSG13 = "#Y上古神器问世，谁与争锋!刚出炉的十三级#R{物品名}#Y所产生的能量波直接蹦死了何大雷，#G{角色名}#Y默默的拿起#R{物品名}#Y惋惜道此天地间再无何大雷!";
        SuitMixdeal.MSG14 = "#Y上古神器问世，谁与争锋!刚出炉的十四级#R{物品名}#Y所产生的能量波直接蹦死了何大雷，#G{角色名}#Y默默的拿起#R{物品名}#Y惋惜道此天地间再无何大雷!";
        SuitMixdeal.MSG15 = "#Y上古神器问世，谁与争锋!刚出炉的十五级#R{物品名}#Y所产生的能量波直接蹦死了何大雷，#G{角色名}#Y默默的拿起#R{物品名}#Y惋惜道此天地间再无何大雷!";
    }
}
