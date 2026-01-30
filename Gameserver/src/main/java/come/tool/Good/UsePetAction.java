package come.tool.Good;

import org.come.model.DiceReidsBase;
import java.util.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import org.come.bean.NChatBean;
import java.util.Arrays;
import org.come.model.ColorScheme;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import org.come.action.reward.DrawnitemsAction;
import org.come.tool.Arith;
import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import come.tool.Battle.BattleMixDeal;
import org.come.until.AchievemUtil;
import org.come.until.ReadTxtUtil;
import org.come.tool.ReadExelTool;
import org.come.action.suit.SuitMixdeal;
import java.util.List;
import org.come.model.Skill;
import java.util.ArrayList;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import org.come.model.Configure;
import org.come.until.GsonUtil;
import come.tool.FightingData.Battlefield;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.tool.TalentTool;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.ConcurrentHashMap;
import org.come.action.IAction;

public class UsePetAction implements IAction
{
    static int[] highSkill;
    static int[] TrainSkill;
    static int[] normalSkill;
    private static ConcurrentHashMap<Long, NPCDialogBean> maps;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        int number222 = 0;
        int aaa = 0;

        if (message != null && !message.isEmpty() && message.contains("!")) {

            int percentIndex = message.indexOf('!');

            String numberPart = message.substring(percentIndex + 1).trim();
            number222 = Integer.parseInt(numberPart);

            aaa = 9;
        } else {
            aaa = 0;

        }


        int percentIndex = message.indexOf('!');


        if (percentIndex != -1) {
            message = message.substring(0, percentIndex);

        }

        String[] vs = message.split("\\|");
        if (vs[0].equals("N")) {
            this.XXPet(ctx, loginResult, vs);
            return;
        }
        RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(vs[1]));
        if (pet == null) {
            return;
        }
        if (pet.getRoleid().compareTo(loginResult.getRole_id()) != 0) {
            return;
        }
        if (vs[0].equals("DH")) {
            this.DHPet(pet, ctx, loginResult);
            return;
        }
        if (vs[0].equals("shaizi")) {
            dicePetSkill(pet, ctx, loginResult);
            return;
        }
        if (vs[0].equals("FS")) {
            this.FSPet(pet, ctx, loginResult);
            return;
        }
        if (vs[0].equals("KQ")) {
            Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[2]));
            if (good == null) {
                return;
            }
            if (good.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
                return;
            }
            if ((int)good.getUsetime() <= 0) {
                return;
            }
            this.openSkillSea1l(pet, good, ctx, loginResult);
            return;
        }
        else {
            if (vs[0].equals("KQ1")) {
                this.openSkillSeal2(pet, ctx, loginResult);
                return;
            }
            if (vs[0].equals("SS")) {
                this.SSPet(pet, ctx, loginResult);
                return;
            }
            if (vs[0].equals("JFSSGZ")) {
                this.openSkillSealSSGZ(pet, null, ctx, loginResult);
                return;
            }
            if (vs[0].equals("CX")) {
                int i = Integer.parseInt(vs[2]);
                int num = Integer.parseInt(vs[3]);
                if (i == 0 || i == 1 || i == 2) {
                    return;
                }
                Practice(pet, ctx, loginResult, i, num);
                return;
            } else if (vs[0].equals("QL")) {
                int flag = 3;//启灵次数
                if (pet.getOpenql() >= flag) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("最多启灵3次#23!"));
                    return;
                }
                Integer openql = pet.getOpenql() + 1;

                if (loginResult.getGold().compareTo(new BigDecimal(50000000l * openql)) < 0) {//开启悟灵金额
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("银两不足" + 50000000l * openql) + "两!#91");
                    return;
                }
                if (pet.getFriendliness() < (200000l * openql)) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的召唤兽亲密值不足" + 200000l * openql + "!!!#91"));
                    return;
                }

                loginResult.setGold(loginResult.getGold().subtract(new BigDecimal(50000000l * openql)));//开启悟灵金额
                MonitorUtil.getMoney().useD(50000000l * openql);//开启悟灵金额
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USEGOOD);
                pet.setOpenql(pet.getOpenql() + 1);
                assetUpdate.setMsg(pet.getSummoningname() + "已成功开启第#G" + pet.getOpenql() + "#Y次启灵#32");

                pet.setFriendliness(pet.getFriendliness() - 200000l * openql);
                assetUpdate.updata("T格子");
                assetUpdate.updata("D=-" + 50000000l * openql);//开启悟灵金额
                AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                assetUpdate.setPet(pet);
//            SuitMixdeal.jpd(loginResult.getRolename(), pet.getSummoningname());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                return;
            } else if (vs[0].equals("OPENQL")) {
                openWl(pet, vs[2], ctx, loginResult);
                return;
            } else if (vs[0].equals("CLOSEWL")) {
                closeWl(pet, vs[2], ctx, loginResult);
                return;
            }
            else if (vs[0].equals("TJJ")) {
                int i = Integer.parseInt(vs[2]);
                if (i == 0 || i == 1 || i == 2) {
                    return;
                }
                Practiced(pet, ctx, loginResult, i);
                return;
            }
            else if (vs[0].equals("PS")) {
                int type = Integer.parseInt(vs[2]);
                if (type == 0 || type == 1 || type == 2) {
                    return;
                }
                PetSkill(pet, ctx, loginResult, type);
                return;
            }
            else if (vs[0].equals("ND")) {
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[2]));
                if (good == null) {
                    return;
                }
                if (good.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
                    return;
                }
                if ((int)good.getUsetime() <= 0) {
                    return;
                }
                this.NDPet(pet, good, ctx, loginResult);
                return;
            }
            else if (vs[0].equals("TF")) {
                String msg = TalentTool.isAllowReadTalent(pet);
                if (msg != null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(msg));
                    return;
                }
                BigDecimal money = null;
                BigDecimal qm = null;
                int talentLvl = pet.getTalentLvl();
                switch (talentLvl) {
                    case 0: {
                        msg = "召唤兽学会了一阶天赋";
                        break;
                    }
                    case 1: {
                        money = BigDecimal.valueOf(20000000L);
                        qm = BigDecimal.valueOf(500000L);
                        msg = "召唤兽学会了二阶天赋";
                        break;
                    }
                    case 2: {
                        money = BigDecimal.valueOf(30000000L);
                        qm = BigDecimal.valueOf(1000000L);
                        msg = "召唤兽学会了三阶天赋";
                        break;
                    }
                    case 3: {
                        money = BigDecimal.valueOf(40000000L);
                        qm = BigDecimal.valueOf(1500000L);
                        msg = "召唤兽学会了四阶天赋";
                        break;
                    }
                    case 4: {
                        money = BigDecimal.valueOf(50000000L);
                        qm = BigDecimal.valueOf(2000000L);
                        msg = "召唤兽学会了五阶天赋";
                        break;
                    }
                }
                if (money != null && loginResult.getGold().compareTo(money) < 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("金币不足!!!"));
                    return;
                }
                if (qm != null && (long)pet.getFriendliness() < qm.longValue()) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的神兽亲密值不足" + qm.longValue() + "!!!"));
                    return;
                }
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USEGOOD);
                if (money != null) {
                    loginResult.setGold(loginResult.getGold().subtract(money));
                    assetUpdate.updata("D=-" + money.longValue());
                    MonitorUtil.getMoney().useD(money.longValue());
                }
                if (qm != null) {
                    pet.setFriendliness(Long.valueOf((long)pet.getFriendliness() - qm.longValue()));
                }
                if (talentLvl == 0 || Battlefield.isV(30.0)) {
                    pet.setTalentLvl(++talentLvl);
                    assetUpdate.setMsg(msg);
                }
                else {
                    assetUpdate.setMsg("天赋升级失败");
                }
                AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                assetUpdate.setPet(pet);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                return;
            }
            else {
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[0]));
                if (good == null) {
                    return;
                }
                if (good.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
                    return;
                }
                if ((int)good.getUsetime() <= 0) {
                    return;
                }
                long type2 = good.getType();
                if (type2 == 715L) {
                    if (good.getUsetime() <= 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("够了，够了，不要再下了#15"));

                        return;
                    }

                    ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
                    Configure configure = s.get(1);

                    long addQM = 0;
                    if (aaa == 9) {
                        System.out.println("aaa=999：" + message);

                        if (number222 > good.getUsetime()) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("够了，够了，不要再下了#15"));

                            return;
                        }


                        useGood(good, 1 * number222);
                        addQM = Long.parseLong(good.getValue().split("\\=")[1]);
                        addQM = addQM * number222;


                    } else {

                        useGood(good, 1);
                        addQM = Long.parseLong(good.getValue().split("\\=")[1]);

                    }
                    long value = (long)pet.getFriendliness() + addQM;
                    if (value >= (long)Integer.parseInt(configure.getZhsqmsx())) {
                        value = (long)Integer.parseInt(configure.getZhsqmsx());
                    }
                    pet.setFriendliness(Long.valueOf(value));
                    AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                    AssetUpdate assetUpdate2 = new AssetUpdate();
                    assetUpdate2.setType(AssetUpdate.USEGOOD);
                    assetUpdate2.updata("G" + good.getRgid() + "=" + good.getUsetime());
                    assetUpdate2.updata("P" + pet.getSid() + "=" + pet.getGrade() + "=" + pet.getExp() + "=" + pet.getFriendliness() + "=" + pet.getBasishp() + "=" + pet.getBasismp());
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                }
                else if (type2 == 503L) {
                    addPetSkill(pet, good, ctx, loginResult);
                }
                else if (type2 == 939L) {
                    addpetqiling(pet, good, ctx, loginResult);
                }
                else if (type2 == 504L) {
                    this.openSkillSeal(pet, good, ctx, loginResult);
                }
                else if (type2 == 5033L) {
                    addPetSkill1(pet, good, ctx, loginResult);
                }
                else if (type2 == 938L) {
                    this.oepnqldan(pet, good, ctx, loginResult);
                } else if (type2 == 66690) {//灵击子
                    linguaG(pet, good, ctx, loginResult, message);
                }
                else if (type2 == 10086L) {
                    this.openSkillSealSS(pet, good, ctx, loginResult);
                }
                else if (type2 == 2040L) {
                    if (good.getUsetime() <= 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("够了，够了，不要再下了#15"));

                        return;
                    }

                    long addexp = 0;
                    if (aaa == 9) {
                        System.out.println("aaa=999：" + message);

                        if (number222 > good.getUsetime()) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("够了，够了，不要再下了#15"));

                            return;
                        }


                        useGood(good, 1 * number222);
                        addexp = Long.parseLong(good.getValue().split("\\=")[1]);
                        addexp = addexp * number222;
                    } else {

                        useGood(good, 1);
                        addexp = Long.parseLong(good.getValue().split("\\=")[1]);

                    }
                    ExpUtil.PetExp(pet, addexp, loginResult);
                    pet.setXy(pet.getXy());
                    AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                    AssetUpdate assetUpdate3 = new AssetUpdate();
                    assetUpdate3.setType(AssetUpdate.USEGOOD);
                    assetUpdate3.setPet(pet);
                    assetUpdate3.updata("G" + good.getRgid() + "=" + good.getUsetime());
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate3)));
                }
                else if (type2 == 20400L) {
                    if (!StringUtils.isNotBlank(pet.getInnerGoods())) {
                        return;
                    }
                    String ndId = "";
                    String[] v = pet.getInnerGoods().split("\\|");
                    int j = new Random().nextInt(v.length);
                    ndId = v[j];
                    Goodstable good2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(ndId));
                    if (good2 == null) {
                        return;
                    }
                    if (good2.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
                        return;
                    }
                    if ((int)good2.getUsetime() <= 0) {
                        return;
                    }
                    if (good.getUsetime() <= 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("够了，够了，不要再下了#15"));

                        return;
                    }

                    long addexp = 0;
                    if (aaa == 9) {
                        System.out.println("aaa=999：" + message);

                        if (number222 > good.getUsetime()) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("够了，够了，不要再下了#15"));

                            return;
                        }


                        useGood(good, 1 * number222);
                        addexp = Long.parseLong(good.getValue().split("\\=")[1]);
                        addexp = addexp * number222;
                    } else {


                        useGood(good, 1);

                        addexp = Long.parseLong(good.getValue().split("\\=")[1]);
                    }
                    this.NDGoodsPet(pet, good2, ctx, loginResult, addexp);
                    useGood(good, 1);
                    AssetUpdate assetUpdate2 = new AssetUpdate(AssetUpdate.USEGOOD);
                    assetUpdate2.updata("G" + good.getRgid() + "=" + good.getUsetime());
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                }
                else if (type2 == 2043L) {
                    this.useNgauWanPills(pet, good, ctx, loginResult);
                }
                else if (type2 == 2113L) {
                    this.useKeel(pet, good, ctx, loginResult);
                }
                else if (type2 == 918L) {
                    this.useKeelsp(pet, good, ctx, loginResult);
                }
                else if (type2 == 716L) {
                    GrowUpDan(pet, good, ctx, loginResult);
                }
                else if (type2 == 192L) {
                    dragonSaliva(pet, good, ctx, loginResult);
                }
                else if (type2 == 667L) {
                    this.useBoneElution(pet, good, ctx, loginResult);
                }
                else if (type2 == 919L) {
                    this.useBoneElutionsp(pet, good, ctx, loginResult);
                }
                else if (type2 == 2323L) {
                    this.train(pet, good, ctx, loginResult);
                }
                else if (type2 == 2423L) {
                    this.traingd(pet, good, ctx, loginResult);
                }
                else if (type2 == 2325L) {
                    this.useDraw(pet, good, ctx, loginResult);
                }
                else if (type2 == 2326L) {
                    addPetSkill(pet, good, ctx, loginResult);
                }
                else if (type2 == 727L || type2 == 8828L) {
                    changeDan(pet, good, ctx, loginResult);
                }
                else if (type2 == 2116L) {
                    this.petFlyUpDan(pet, good, ctx, loginResult);
                }
                else if (type2 == 2118L) {
                    this.petCzUpDan(pet, good, ctx, loginResult);
                }
                else if (type2 == 2119L) {
                    petTransformAppearance(pet, good, ctx, loginResult);
                }
                else if (type2 == 8002L) {
                    this.lingteng(pet, good, ctx, loginResult);
                }
                else if (type2 == 1005L) {
                    useGood(good, 1);
                    UseMixdeal.gld(good, pet, ctx, loginResult);
                }
                else if (type2 == 2234L) {
                    useGood(good, 1);
                    addPetExtPoint(pet, good, ctx, loginResult);
                }
                return;
            }
        }
    }
    
    public static void Practiced(RoleSummoning pet, ChannelHandlerContext ctx, LoginResult login, int type) {
        Skill skill = GameServer.getSkill(type + "");
        if (skill == null) {
            return;
        }
        List<String> skills = new ArrayList<>();
        if (pet.getPetSkills() != null && !pet.getPetSkills().equals("")) {
            String[] vs = pet.getPetSkills().split("\\|");
            for (int j = 0; j < vs.length; ++j) {
                if (!vs[j].equals("")) {
                    skills.add(vs[j]);
                }
            }
        }
        skills.add(skill.getSkillid() + "");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < skills.size(); ++i) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append((String)skills.get(i));
        }
        pet.setPetSkills(buffer.toString());
        getskills(skills, pet.getSkill());
        getskills(skills, pet.getBeastSkills());
        pet.setSkillData(skillData(skills));
        pet.setTrainNum(0);
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("您的" + pet.getSummoningname() + "领悟技能#G" + skill.getSkillname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static boolean Practice(RoleSummoning pet, ChannelHandlerContext ctx, LoginResult login, int type, int num) {
        boolean is = true;
        long money = Numberskill(num);
        if (login.getGold().compareTo(new BigDecimal(money)) < 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("金币不足" + money + "两!!!"));
            return false;
        }
        String grade = null;
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        Skill skill = GameServer.getSkill(type + "");
        List<String> skills = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        if (pet.getPetSkills() != null && !pet.getPetSkills().equals("")) {
            String types = type + "";
            String[] vs = pet.getPetSkills().split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (!vs[i].equals("")) {
                    if (vs[i].equals(types)) {
                        is = false;
                    }
                    else {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append(vs[i]);
                        skills.add(vs[i]);
                    }
                }
            }
        }
        pet.setPetSkills((buffer.length() == 0) ? null : buffer.toString());
        if (is) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽未携带技能"));
            return false;
        }
        getskills(skills, pet.getSkill());
        getskills(skills, pet.getBeastSkills());
        pet.setSkillData(skillData(skills));
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.updata("D=" + -money);
        login.setGold(login.getGold().subtract(new BigDecimal(money)));
        MonitorUtil.getMoney().useD(money);
        assetUpdate.setMsg("扣除" + money + "大话币");
        SuitMixdeal.JN3(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), grade);
        assetUpdate.setPet(pet);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        return true;
    }
    
    public static long Numberskill(int num) {
        switch (num) {
            case 1: {
                return 500000000L;
            }
            case 2: {
                return 2000000000L;
            }
            case 3: {
                return 5000000000L;
            }
            case 4: {
                return 10000000000L;
            }
            case 5: {
                return 20000000000L;
            }
            case 6: {
                return 20000000000L;
            }
            case 7: {
                return 20000000000L;
            }
            case 8: {
                return 20000000000L;
            }
            default: {
                return 0L;
            }
        }
    }
    
    public static void addPetExtPoint(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        if (good.getValue() != null) {
            String[] valus = good.getValue().split("=");
            if (valus.length >= 2) {
                int extPoint = (int)Integer.valueOf(valus[1]);
                if (extPoint > 0) {
                    if (pet.getExtPoint() >= 400) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽属性丹已达上限！"));
                        return;
                    }
                    pet.setExtPoint(pet.getExtPoint() + extPoint);
                    AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                    AssetUpdate assetUpdate = new AssetUpdate();
                    assetUpdate.setType(AssetUpdate.USEGOOD);
                    assetUpdate.setMsg("你使用" + good.getGoodsname() + ",召唤兽获得了" + extPoint + "属性点");
                    assetUpdate.updata("PEP=" + pet.getSid() + "=" + pet.getExtPoint());
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                }
            }
        }
    }
    
    public static void petTransformAppearance(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        String skin = good.getValue().split("\\|")[0].split("=")[1];
        if (skin.equals("随机")) {
            String up = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "Changeable.db");
            String[] skinList = up.split("\\|");
            Random random = new Random();
            String skins = skinList[random.nextInt(skinList.length - 1)];
            pet.setSummoningskin(skins);
        }
        else {
            pet.setSummoningskin(skin);
        }
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("恭喜：您的召唤兽【 " + pet.getSummoningname() + "】成功变化造型！");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void lingteng(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        String value = good.getValue();
        int ltlvl = -1;
        int ltlvl2 = -1;
        int ltlvl3 = -1;
        if (good.getGoodsname().indexOf("低") != -1) {
            ltlvl = 3;
        }
        else if (good.getGoodsname().indexOf("中") != -1) {
            ltlvl2 = 6;
        }
        else if (good.getGoodsname().indexOf("高") != -1) {
            ltlvl3 = 9;
        }
        else {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("获取物品错误"));
            return;
        }
        String[] value2 = value.split("=");
        String value3 = "0";
        String level1 = "0";
        if (value2[1].equals("高级分裂攻击")) {
            value3 = "1833";
        }
        else if (value2[1].equals("春风佛面")) {
            value3 = "1871";
        }
        else if (value2[1].equals("春意盎然")) {
            value3 = "1612";
        }
        else if (value2[1].equals("分花拂柳")) {
            value3 = "1831";
        }
        else if (value2[1].equals("悬刃")) {
            value3 = "1834";
        }
        else if (value2[1].equals("遗患")) {
            value3 = "1836";
        }
        else if (value2[1].equals("报复")) {
            value3 = "1835";
        }
        else if (value2[1].equals("吉人天相")) {
            value3 = "1838";
        }
        else if (value2[1].equals("妙手回春")) {
            value3 = "1611";
        }
        else if (value2[1].equals("视死如归")) {
            value3 = "1872";
        }
        else if (value2[1].equals("天地同寿")) {
            value3 = "1880";
        }
        else if (value2[1].equals("扶伤")) {
            value3 = "1858";
        }
        else if (value2[1].equals("福禄双全")) {
            value3 = "1873";
        }
        else if (value2[1].equals("炊金馔玉")) {
            value3 = "1600";
        }
        else if (value2[1].equals("枯木逢春")) {
            value3 = "1601";
        }
        else if (value2[1].equals("西天净土")) {
            value3 = "1602";
        }
        else if (value2[1].equals("如人饮水")) {
            value3 = "1603";
        }
        else if (value2[1].equals("风火燎原")) {
            value3 = "1604";
        }
        else if (value2[1].equals("高级清明术")) {
            value3 = "1850";
        }
        else if (value2[1].equals("高级脱困术")) {
            value3 = "1852";
        }
        else if (value2[1].equals("高级强心术")) {
            value3 = "1854";
        }
        else if (value2[1].equals("舍身取义")) {
            value3 = "1839";
        }
        else if (value2[1].equals("义之金叶神")) {
            value3 = "1820";
        }
        else if (value2[1].equals("信之土叶神")) {
            value3 = "1822";
        }
        else if (value2[1].equals("孤勇成军")) {
            value3 = "1878";
        }
        else if (value2[1].equals("高级禅机顿悟")) {
            value3 = "1887";
        }
        else {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("获取技能错误"));
            return;
        }
        if (pet.getPetSkills() == null || pet.getPetSkills().equals("")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽没有技能"));
            return;
        }
        if (pet.getPetSkills().indexOf(value3) == -1) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该召唤兽没有" + value3));
            return;
        }
        List<String> skillswl = new ArrayList<>();
        if (pet.getPetSkillswl() != null && !pet.getPetSkillswl().equals("")) {
            String[] vs = pet.getPetSkillswl().split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (!vs[i].equals("") && vs[i].indexOf(value3) == -1) {
                    skillswl.add(vs[i]);
                }
                else if (vs[i].indexOf(value3) != -1) {
                    String[] level2 = vs[i].split("=");
                    level1 = level2[1];
                }
            }
        }
        if (skillswl.size() > 3) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("最多悟灵3个技能"));
            return;
        }
        if (Integer.parseInt(level1) >= 10) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("高级技能已满阶"));
            return;
        }
        if (Integer.parseInt(level1) <= ltlvl) {
            if (GameServer.random.nextInt(100) < 20) {
                int level3 = Integer.parseInt(level1) + 1;
                skillswl.add(value3 + "=" + String.valueOf(level3));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(value2[1] + "的技能等级提升到了" + level3 + "阶"));
            }
            else {
                skillswl.add(value3 + "=" + level1);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("很遗憾，升级失败"));
            }
        }
        else if (Integer.parseInt(level1) <= ltlvl2 && Integer.parseInt(level1) > 3) {
            if (GameServer.random.nextInt(100) < 15) {
                int level3 = Integer.parseInt(level1) + 1;
                skillswl.add(value3 + "=" + String.valueOf(level3));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(value2[1] + "的技能等级提升到了" + level3 + "阶"));
            }
            else {
                skillswl.add(value3 + "=" + level1);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("很遗憾，升级失败"));
            }
        }
        else if (Integer.parseInt(level1) <= ltlvl3 && Integer.parseInt(level1) > 6) {
            if (GameServer.random.nextInt(100) < 10) {
                int level3 = Integer.parseInt(level1) + 1;
                skillswl.add(value3 + "=" + String.valueOf(level3));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(value2[1] + "的技能等级提升到了" + level3 + "阶"));
            }
            else {
                skillswl.add(value3 + "=" + level1);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("很遗憾，升级失败"));
            }
        }
        else {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("请使用等级对应的灵藤"));
            return;
        }
        StringBuffer buffer1 = new StringBuffer();
        for (int i = 0; i < skillswl.size(); ++i) {
            if (buffer1.length() != 0) {
                buffer1.append("|");
            }
            buffer1.append((String)skillswl.get(i));
        }
        pet.setPetSkillswl(buffer1.toString());
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setPet(pet);
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void NDPet(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        if (pet.getExp().longValue() <= 0L) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你召唤兽没有经验可以转换"));
            return;
        }
        String[] vs = good.getValue().split("\\|");
        String[] stringLevel = vs[2].split("=")[1].split("转");
        int zs = Integer.parseInt(stringLevel[0]);
        long addExp = (long)((double)pet.getExp().longValue() * 0.2);
        int lvl = Integer.parseInt(stringLevel[1]);
        long exp = Long.parseLong(vs[3].split("=")[1]) + addExp;
        int petlvl = BattleMixDeal.petLvlint((int)pet.getGrade());
        int maxlvl = ExpUtil.getNedanMostLevel(zs);
        if (zs >= pet.getTurnRount() && lvl >= petlvl) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽等级不够哦，快去修炼吧！！！"));
            return;
        }
        if (lvl >= 300) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前内丹已达最大等级！！！"));
            return;
        }
        long maxexp = ExpUtil.getBBNeiExp(zs, lvl + 1);
        while (exp >= maxexp && exp > 0L) {
            if (lvl + 1 > maxlvl) {
                if (zs >= 4) {
                    break;
                }
                else if (zs + 1 > pet.getTurnRount()) {
                    break;
                }
                else {
                    ++zs;
                    lvl = 0;
                    maxexp = ExpUtil.getBBNeiExp(zs, lvl + 1);
                    exp = 0L;
                }
            }
            else if (zs >= pet.getTurnRount() && lvl + 1 > petlvl) {
                break;
            }
            else {
                exp -= maxexp;
                ++lvl;
                maxexp = ExpUtil.getBBNeiExp(zs, lvl + 1);
            }
        }
        pet.setExp(new BigDecimal(0));
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        StringBuffer buffer = new StringBuffer();
        buffer.append(vs[0]);
        buffer.append("|");
        buffer.append(vs[1]);
        buffer.append("|内丹等级=");
        buffer.append(zs);
        buffer.append("转");
        buffer.append(lvl);
        buffer.append("|经验=");
        buffer.append(exp);
        good.setValue(buffer.toString());
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.setPet(pet);
        assetUpdate.updata("G" + good.getRgid() + "=" + zs + "=" + lvl + "=" + exp);
        assetUpdate.updata("P" + pet.getSid() + "=" + pet.getGrade() + "=" + pet.getExp() + "=" + pet.getFriendliness() + "=" + pet.getBasishp() + "=" + pet.getBasismp());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void NDGoodsPet(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login, long addExp) {
        String[] vs = good.getValue().split("\\|");
        String[] stringLevel = vs[2].split("=")[1].split("转");
        int zs = Integer.parseInt(stringLevel[0]);
        int lvl = Integer.parseInt(stringLevel[1]);
        long exp = Long.parseLong(vs[3].split("=")[1]) + addExp;
        int petlvl = BattleMixDeal.petLvlint((int)pet.getGrade());
        int maxlvl = ExpUtil.getNedanMostLevel(zs);
        if (zs >= pet.getTurnRount() && lvl >= petlvl) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽等级不够哦，快去修炼吧！！！"));
            return;
        }
        if (lvl >= 300) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前内丹已达最大等级！！！"));
            return;
        }
        long maxexp = ExpUtil.getBBNeiExp(zs, lvl + 1);
        while (exp >= maxexp && exp > 0L) {
            if (lvl + 1 > maxlvl) {
                if (zs >= 4) {
                    break;
                }
                else if (zs + 1 > pet.getTurnRount()) {
                    break;
                }
                else {
                    ++zs;
                    lvl = 0;
                    maxexp = ExpUtil.getBBNeiExp(zs, lvl + 1);
                    exp = 0L;
                }
            }
            else if (zs >= pet.getTurnRount() && lvl + 1 > petlvl) {
                break;
            }
            else {
                exp -= maxexp;
                ++lvl;
                maxexp = ExpUtil.getBBNeiExp(zs, lvl + 1);
            }
        }
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        StringBuffer buffer = new StringBuffer();
        buffer.append(vs[0]);
        buffer.append("|");
        buffer.append(vs[1]);
        buffer.append("|内丹等级=");
        buffer.append(zs);
        buffer.append("转");
        buffer.append(lvl);
        buffer.append("|经验=");
        buffer.append(exp);
        good.setValue(buffer.toString());
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.setPet(pet);
        assetUpdate.updata("G" + good.getRgid() + "=" + zs + "=" + lvl + "=" + exp);
        assetUpdate.updata("P" + pet.getSid() + "=" + pet.getGrade() + "=" + pet.getExp() + "=" + pet.getFriendliness() + "=" + pet.getBasishp() + "=" + pet.getBasismp());
        assetUpdate.setMsg("#R" + good.getGoodsname() + "#W获得经验#Y" + addExp);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static boolean PetSkill(RoleSummoning pet, ChannelHandlerContext ctx, LoginResult login, int type) {
        if (type == 0 || type == 1 || type == 2) {
            boolean getNormal;
            boolean getHigh;
            boolean getTrain;
            if (type == 1) {
                getNormal = DropUtil.isV(100.0);
                getHigh = DropUtil.isV(1.0);
                getTrain = DropUtil.isV(0.01);
            }
            else if (type == 2) {
                getNormal = DropUtil.isV(100.0);
                getHigh = DropUtil.isV(5.0);
                getTrain = DropUtil.isV(0.01);
            }
            else {
                getNormal = DropUtil.isV(1.0);
                getHigh = DropUtil.isV(0.2);
                RoleData data = RolePool.getLineRoleData(login.getRole_id());
                getTrain = (data != null && data.getLimit("VIP") != null && DropUtil.isV(0.003));
            }
            if (!getNormal && !getHigh && !getTrain) {
                return false;
            }
            int skillId = 0;
            String grade = null;
            if (getTrain) {
                int skillIndex = GameServer.random.nextInt(UsePetAction.TrainSkill.length);
                skillId = UsePetAction.TrainSkill[skillIndex];
                grade = "终极";
            }
            else if (getHigh) {
                int skillIndex = GameServer.random.nextInt(UsePetAction.highSkill.length);
                skillId = UsePetAction.highSkill[skillIndex];
                grade = "高级";
            }
            else if (getNormal) {
                int skillIndex = GameServer.random.nextInt(UsePetAction.normalSkill.length);
                skillId = UsePetAction.normalSkill[skillIndex];
                grade = "普通";
            }
            if (skillId == 0) {
                return false;
            }
            Skill skill = GameServer.getSkill(skillId + "");
            if (skill == null) {
                return false;
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
            if ((int)pet.getOpenSeal() <= skills.size() || skills.size() >= 4) {
                return false;
            }
            if (chongfu(skill, pet, skills, false) != null) {
                return false;
            }
            skills.add(skill.getSkillid() + "");
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < skills.size(); ++i) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append((String)skills.get(i));
            }
            pet.setPetSkills(buffer.toString());
            getskills(skills, pet.getSkill());
            getskills(skills, pet.getBeastSkills());
            pet.setSkillData(skillData(skills));
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            if (type == 1 || type == 2) {
                SuitMixdeal.JN2(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), grade);
            }
            else {
                SuitMixdeal.JN5(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), grade);
            }
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USEGOOD);
            assetUpdate.setPet(pet);
            assetUpdate.setMsg("你的召唤兽学会了" + skill.getSkillname());
            if (!grade.equals("普通")) {
                assetUpdate.updata("T悟技");
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else {
            boolean is = true;
            boolean iss = true;
            String[] vs2 = null;
            String[] vs3 = null;
            if (!isSkillOK1(ctx, pet, type + "")) {
                return false;
            }
            List<String> skills2 = new ArrayList<>();
            List<String> skills3 = new ArrayList<>();
            if (type == 1509 || type == 1609 || type == 1814 || type == 1866) {
                is = false;
                pet.setBeastSkills("-1");
            }
            else {
                StringBuffer buffer2 = new StringBuffer();
                StringBuffer buffer3 = new StringBuffer();
                if (pet.getPetSkills() != null && !pet.getPetSkills().equals("")) {
                    String types = type + "";
                    vs2 = pet.getPetSkills().split("\\|");
                    for (int j = 0; j < vs2.length; ++j) {
                        if (!vs2[j].equals("")) {
                            if (vs2[j].equals(types)) {
                                is = false;
                                if (pet.getPetSkillswl() != null && !pet.getPetSkillswl().equals("")) {
                                    vs3 = pet.getPetSkillswl().split("\\|");
                                    for (int i2 = 0; i2 < vs3.length; ++i2) {
                                        if (vs3[i2].indexOf(types) == -1) {
                                            if (buffer3.length() != 0) {
                                                buffer3.append("|");
                                            }
                                            buffer3.append(vs3[i2]);
                                            skills3.add(vs3[i2]);
                                        }
                                    }
                                }
                            }
                            else {
                                if (buffer2.length() != 0) {
                                    buffer2.append("|");
                                }
                                buffer2.append(vs2[j]);
                                skills2.add(vs2[j]);
                            }
                        }
                    }
                }
                pet.setPetSkills((buffer2.length() == 0) ? null : buffer2.toString());
                pet.setPetSkillswl((buffer3.length() == 0) ? null : buffer3.toString());
            }
            if (type == 1509 || type == 1609 || type == 1814 || type == 1866) {
                is = false;
                pet.setBeastSkills("-1");
            }
            else {
                StringBuffer buffer4 = new StringBuffer();
                if (pet.getPetQlSkills() != null && !pet.getPetQlSkills().equals("")) {
                    String types2 = type + "";
                    vs2 = pet.getPetQlSkills().split("\\|");
                    for (int i = 0; i < vs2.length; ++i) {
                        if (!vs2[i].equals("")) {
                            if (vs2[i].equals(types2)) {
                                is = false;
                            }
                            else {
                                if (buffer4.length() != 0) {
                                    buffer4.append("|");
                                }
                                buffer4.append(vs2[i]);
                                skills2.add(vs2[i]);
                            }
                        }
                    }
                }
                pet.setPetQlSkills((buffer4.length() == 0) ? null : buffer4.toString());
            }
            if (is) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽未携带技能"));
                return false;
            }
            getskills(skills2, pet.getSkill());
            getskills(skills2, pet.getBeastSkills());
            pet.setSkillData(skillData(skills2));
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            AssetUpdate assetUpdate2 = new AssetUpdate();
            assetUpdate2.setType(AssetUpdate.USEGOOD);
            assetUpdate2.setPet(pet);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
        }
        return true;
    }
    
    public static void addPetSkill(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login, Skill skill) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = s.get(1);
        if (pet != null) {
            int sum = (int)pet.getOpenSeal();
            if (skill == null) {
                return;
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
                return;
            }
            String value = chongfu(skill, pet, skills, true);
            if (value != null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(value));
                return;
            }
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USEGOOD);
            skills.add(skill.getSkillid() + "");
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < skills.size(); ++j) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append((String)skills.get(j));
            }
            pet.setPetSkills(buffer.toString());
            getskills(skills, pet.getSkill());
            getskills(skills, pet.getBeastSkills());
            pet.setSkillData(skillData(skills));
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            assetUpdate.setPet(pet);
            assetUpdate.setMsg("你的召唤兽学会了" + skill.getSkillname());
            int Id = skill.getSkillid();
            if ((Id >= 1606 && Id <= 1608) || (Id >= 1828 && Id <= 1830) || (Id >= 1840 && Id <= 1842) || (Id >= 1867 && Id <= 1869) || Id == 3034) {//学习终极技能
                assetUpdate.updata("T悟技");
                SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "终级");
                AchievemUtil.detectionAchievem(login, "终极技能");//add@magor
            }
            else if ((Id >= 1815 && Id <= 1827) || (Id >= 1600 && Id <= 1605) || (Id >= 1834 && Id <= 1839) || (Id >= 1882 && Id <= 1888) || (Id >= 1611 && Id <= 1612) || Id == 1850 || Id == 1852 || Id == 1854 || Id == 1858 || Id == 1860 || Id == 1862 || Id == 1864 || Id == 1865 || Id == 1811 || Id == 1831 || Id == 1833 || (Id >= 1871 && Id <= 1880)) {
                assetUpdate.updata("T悟技");
                SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "高级");//学习高级技能
            }
            else if ((Id >= 1800 && Id <= 1806) || (Id >= 1843 && Id <= 1849) || (Id >= 1855 && Id <= 1857)|| Id == 1808 || Id == 1810 || Id == 1812 || Id == 1832 || Id == 1851 || Id == 1853 || Id == 1861 || Id == 1863) {
                assetUpdate.updata("T悟技");
                SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "普通");//学习普通技能
                AchievemUtil.detectionAchievem(login, "普通技能");
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    
    public void SSPet(RoleSummoning pet, ChannelHandlerContext ctx, LoginResult login) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = s.get(Integer.valueOf(1));
        int num = 9;
        if (configure.getZhsjngs() != null) {
            num = Integer.parseInt(configure.getZhsjngs());
        }
        int ssn = Integer.parseInt(pet.getSsn());
        if (ssn != 2 && ssn != 3 && ssn != 4) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您携带的召唤兽不是神兽!!!"));
            return;
        }
        if (login.getGold().compareTo(new BigDecimal(50000000)) < 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("金币不足!!!"));
            return;
        }
        if (pet.getFriendliness() < 200000L) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的神兽亲密值不足200000!!!"));
            return;
        }
        if (pet.getBeastSkills() == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的神兽技能格子还未解封!"));
            return;
        }
        String yb = pet.getBeastSkills();
        String skillid = null;
        int Chances = GameServer.random.nextInt(4);
        if (Chances == 0) {
            skillid = "1509";
        }
        else if (Chances == 1) {
            skillid = "1609";
        }
        else if (Chances == 2) {
            skillid = "1814";
        }
        else if (Chances == 3) {
            skillid = "1866";
        }
        Skill skill = GameServer.getSkill(skillid);
        if (skill == null) {
            return;
        }
        pet.setBeastSkills(skillid);
        if (yb == null || !yb.equals(skillid)) {
            List<String> skills = new ArrayList<>();
            getskills(skills, pet.getPetSkills());
            getskills(skills, pet.getSkill());
            getskills(skills, pet.getBeastSkills());
            pet.setSkillData(skillData(skills));
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.updata("D=-50000000");
        login.setGold(login.getGold().subtract(new BigDecimal(50000000)));
        MonitorUtil.getMoney().useD(50000000L);
        pet.setFriendliness(Long.valueOf((long)pet.getFriendliness() - 200000L));
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("召唤兽学会了" + skill.getSkillname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void FSPet(RoleSummoning pet, ChannelHandlerContext ctx, LoginResult login) {
        int ssn = Integer.parseInt(pet.getSsn());
        if (ssn != 3 && ssn != 4) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您携带的召唤兽不是可飞升的神兽!!!"));
            return;
        }
        boolean bool = false;
        if (pet.getRevealNum() == 0) {
            if (pet.getGrade() >= 50) {
                bool = true;
            }
        }
        else if (pet.getRevealNum() == 1) {
            if ((int)pet.getGrade() >= 188) {
                bool = true;
            }
        }
        else if (pet.getRevealNum() == 2 && (int)pet.getGrade() >= 316) {
            bool = true;
        }
        if (!bool) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的神兽" + pet.getSummoningname() + "不符合飞升的条件!"));
            return;
        }
        if (login.getGold().longValue() < 5000000L) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的银两不足500W"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.updata("D=-5000000");
        login.setGold(login.getGold().subtract(new BigDecimal(5000000)));
        MonitorUtil.getMoney().useD(5000000L);
        if (pet.getRevealNum() < 2) {
            if (pet.getRevealNum() == 0) {
                if (ssn == 3) {
                    otherPetId(pet, 0);
                    assetUpdate.updata("NSKIN" + pet.getSid());
                }
                BigDecimal grow = mathDouble(Double.parseDouble(pet.getGrowlevel()), 0.1);
                pet.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));
            }
            else if (pet.getRevealNum() == 1) {
                if (ssn == 3) {
                    otherPetId(pet, 1);
                    assetUpdate.updata("NSKIN" + pet.getSid());
                }
                BigDecimal grow = mathDouble(Double.parseDouble(pet.getGrowlevel()), 0.05);
                pet.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));
            }
            pet.setRevealNum(pet.getRevealNum() + 1);
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            assetUpdate.setPet(pet);
            assetUpdate.setMsg("您的神兽 " + pet.getSummoningname() + "飞升成功!!!");
        }
        else if (pet.getRevealNum() == 2) {
            NPCDialogBean bean = new NPCDialogBean(assetUpdate.getI(), 1, pet.getSid(), 60);
            UsePetAction.maps.put(Long.valueOf(bean.getId()), bean);
            assetUpdate.updata("NBASE" + assetUpdate.getI() + "=60");
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void DHPet(RoleSummoning pet, ChannelHandlerContext ctx, LoginResult login) {
        String mes = pet.getXy();
        int roleTurn = login.getTurnAround();
        int petTurn = pet.getTurnRount();
        if (petTurn >= roleTurn || petTurn >= 4) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("快去升级吧!你快驾驭不你的召唤兽了"));
            return;
        }
        int lvl = (int)pet.getGrade();
        if (petTurn == 0) {
            if (lvl != 100) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的召唤兽" + pet.getSummoningname() + "等级不够,还需多加历练!"));
                return;
            }
        }
        else if (petTurn == 1) {
            if (lvl != 221) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的召唤兽" + pet.getSummoningname() + "等级不够,还需多加历练!"));
                return;
            }
        }
        else if (petTurn == 2) {
            if (lvl != 362) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的召唤兽" + pet.getSummoningname() + "等级不够,还需多加历练!"));
                return;
            }
        }
        else if (petTurn == 3) {
            if (lvl != 543) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的召唤兽" + pet.getSummoningname() + "等级不够,还需多加历练!"));
                return;
            }
            if ((long)pet.getFriendliness() < 2000000L) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的召唤兽与您的亲密值不足200万"));
                return;
            }
        }
        ++lvl;
        if (++petTurn <= 3) {
            if (login.getGold().longValue() < (long)(200000 * petTurn)) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的银两不足" + 20 * petTurn + "万"));
                return;
            }
        }
        else if (login.getGold().longValue() < 20000000L) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的银两不足2000万"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        if (petTurn <= 3) {
            assetUpdate.updata("D=-" + 200000 * petTurn);
            login.setGold(login.getGold().subtract(new BigDecimal(200000 * petTurn)));
            MonitorUtil.getMoney().useD((long)petTurn * 200000L);
        }
        else {
            assetUpdate.updata("D=-20000000");
            login.setGold(login.getGold().subtract(new BigDecimal(20000000)));
            MonitorUtil.getMoney().useD(20000000L);
        }
        pet.setBone(0);
        pet.setSpir(0);
        pet.setPower(0);
        pet.setSpeed(0);
        pet.setCalm(0);
        pet.setExp(new BigDecimal(0));
        pet.setGrade(lvl);
        pet.setTurnRount(petTurn);
        if (petTurn > 3) {
            pet.setFriendliness((long) pet.getFriendliness() - 2000000L);
        }
        pet.setFaithful(100);
        if (petTurn <= 3) {
            BigDecimal grow = mathDouble(Double.parseDouble(pet.getGrowlevel()), 0.1);
            pet.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));
        }
        else {
            BigDecimal grow = mathDouble(Double.parseDouble(pet.getGrowlevel()), 0.0);
            pet.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));
        }
        pet.setBasishp(0);
        pet.setBasismp(0);
        pet.setXy(mes);
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        if (petTurn <= 3) {
            assetUpdate.setMsg("#Y召唤兽转生成功");
            if (petTurn == 1) {
                AchievemUtil.detectionAchievem(login, "召唤兽1转");
            } else if (petTurn == 2) {
                AchievemUtil.detectionAchievem(login, "召唤兽2转");
            } else if (petTurn == 3) {
                AchievemUtil.detectionAchievem(login, "召唤兽3转");
            }
        }
        else {
            assetUpdate.setMsg("#Y召唤兽点化成功");
            if (pet.getSsn().equals("2")) {
                NPCDialogBean bean = new NPCDialogBean(assetUpdate.getI(), 0, pet.getSid(), 60);
                UsePetAction.maps.put(Long.valueOf(bean.getId()), bean);
                assetUpdate.updata("NBASE" + assetUpdate.getI() + "=60");
            }
            else if (pet.getSsn().equals("6")) {
                NPCDialogBean bean = new NPCDialogBean(assetUpdate.getI(), 0, pet.getSid(), 200);
                UsePetAction.maps.put(Long.valueOf(bean.getId()), bean);
                assetUpdate.updata("NBASE" + assetUpdate.getI() + "=200");
            }
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void petFlyUpDan(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        if (!pet.getSsn().equals("2")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("这只召唤兽不能使用神兽飞升丹!!!"));
            return;
        }
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        if (configure != null) {
            if (pet.getFlyupNum() >= Integer.parseInt(configure.getSsfsdsx().toString())) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("神兽 " + pet.getSummoningname() + "的飞升次数已达到上限！"));
                return;
            }
        }
        else if (pet.getFlyupNum() >= 3) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("神兽 " + pet.getSummoningname() + "的飞升次数已达到上限！"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        pet.setFlyupNum(pet.getFlyupNum() + 1);
        BigDecimal grow = mathDouble(Double.parseDouble(pet.getGrowlevel()), 0.1);
        pet.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("神兽 " + pet.getSummoningname() + "飞升成功!!！");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void petCzUpDan(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        if (pet.getTurnRount() < 1) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("神兽 " + pet.getSummoningname() + "未转生不可服用成长进阶丹！"));
            return;
        }
        if (pet.getTurnRount() < 2 && pet.getCzjjd() >= 2) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("神兽 " + pet.getSummoningname() + "的进阶次数已达到上限！一转上限20个"));
            return;
        }
        if (pet.getTurnRount() < 3 && pet.getCzjjd() >= 2) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("神兽 " + pet.getSummoningname() + "的进阶次数已达到上限！二转上限30个"));
            return;
        }
        if (pet.getTurnRount() < 4 && pet.getCzjjd() >= 2) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("神兽 " + pet.getSummoningname() + "的进阶次数已达到上限！三转上限50个"));
            return;
        }
        if (pet.getTurnRount() < 5 && pet.getCzjjd() >= 2) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("神兽 " + pet.getSummoningname() + "的进阶次数已达到上限！飞升后可增加上限到100颗！"));
            return;
        }
        if (pet.getCzjjd() >= 2) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("神兽 " + pet.getSummoningname() + "的进阶次数已达到上限！召唤兽最多可吃100颗"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        pet.setCzjjd(pet.getCzjjd() + 1);
        BigDecimal grow = mathDouble(Double.parseDouble(pet.getGrowlevel()), 0.001);
        pet.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("神兽 " + pet.getSummoningname() + "进阶成功!!！");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    public static void closeWl(RoleSummoning pet, String value2, ChannelHandlerContext ctx, LoginResult login) {
        Skill skill = GameServer.getSkill(value2);

        if (pet.getPetSkills() == null || pet.getPetSkills().equals("")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽没有技能"));
            return;
        }
        if (pet.getPetSkillswl().indexOf(value2) == -1) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(skill.getSkillname() + "#R没有开启悟灵模式"));
            return;
        }

        if (login.getGold().compareTo(new BigDecimal(500000000)) < 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("银两不足500000000两!#91"));
            return;
        }

        List<String> skillswl = new ArrayList<>();

        String[] vs = pet.getPetSkillswl().split("\\|");


        //拼接悟灵技能
        StringBuffer buffer1 = new StringBuffer();
        for (int i = 0; i < vs.length; i++) {
            if (vs[i].contains(value2)) continue;
            if (buffer1.length() != 0) {
                buffer1.append("|");
            }

            buffer1.append(vs[i]);
        }
        //保存悟灵技能
        pet.setPetSkillswl(buffer1.toString());
        login.setGold(login.getGold().subtract(new BigDecimal(50000000)));//开启悟灵金额
        MonitorUtil.getMoney().useD(50000000);//开启悟灵金额
        //扣除物品
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.setData("D=-" + 500000000);//删除技能扣金钱//悟灵技能金钱消耗
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("#R" + skill.getSkillname() + "#Y以关闭悟灵");
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }


    public static void openWl(RoleSummoning pet, String value2, ChannelHandlerContext ctx, LoginResult login) {
        //获取value 物品信息
        int ltlvl = -1;
        int ltlvl1 = -1;
        int ltlvl2 = -1;
        String level1 = "0";


        Skill skill = GameServer.getSkill(value2);

        if (pet.getPetSkills() == null || pet.getPetSkills().equals("")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽没有技能"));
            return;
        }
        if (pet.getPetSkills().indexOf(value2) == -1) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该召唤兽没有" + value2));
            return;
        }

        List<String> skillswl = new ArrayList<>();

        if (pet.getPetSkillswl() != null && !pet.getPetSkillswl().equals("")) {
            String[] vs = pet.getPetSkillswl().split("\\|");
            for (int i = 0; i < vs.length; i++) {
                if (!vs[i].equals("") && vs[i].indexOf(value2) == -1) {
                    skillswl.add(vs[i]);
                } else if (vs[i].indexOf(value2) != -1) {
                    String[] level = vs[i].split("=");
                    level1 = level[1];
                }
            }
        }
        if (skillswl.size() > 3) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("最多悟灵3个技能"));
            return;
        }

        if (Integer.parseInt(level1) >= 10) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("高级技能已满阶"));
            return;
        }
        skillswl.add(value2 + "=" + level1);


        //拼接悟灵技能
        StringBuffer buffer1 = new StringBuffer();
        for (int i = 0; i < skillswl.size(); i++) {
            if (buffer1.length() != 0) {
                buffer1.append("|");
            }
            buffer1.append(skillswl.get(i));
        }
        //保存悟灵技能
        pet.setPetSkillswl(buffer1.toString());
        //扣除物品
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("#R" + skill.getSkillname() + "#Y以开启悟灵");
        ;
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    public static void changeDan(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        int petid = Integer.parseInt(pet.getSummoningid());
        long type = good.getType();
        if (type == 727L) {
            if (petid != 200123 && petid != 200116 && petid != 200117 && petid != 200097 && petid != 200098 && petid != 200099 && petid != 200100 && petid != 200101 && petid != 200188 && petid != 200185 && petid != 200184 && petid != 200195 && petid != 200106 && petid != 200190 && petid != 200189 && petid != 200202 && petid != 200203 && petid != 200205 && petid != 200206 && petid != 200207 && petid != 200208 && petid != 200209 && petid != 200210 && petid != 200211 && petid != 200212 && petid != 200213 && petid != 200214 && petid != 200215 && petid != 200216 && petid != 200217 && petid != 200201 && petid != 200238 && petid != 200400 && petid != 200401 && petid != 200402 && petid != 200403 && petid != 200404 && petid != 200405 && petid != 200500 && petid != 200501 && petid != 200502 && petid != 200503 && petid != 200504 && petid != 200900 && petid != 200901 && petid != 200902 && petid != 200704 && petid != 200904 && petid != 200903) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽不能使用此化形丹·神"));
                return;
            }
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USEGOOD);
            useGood(good, 1);
            assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
            String skin = null;
            if (petid == 200123) {
                skin = "400518";
            }
            else if (petid == 200116) {
                skin = "400523";
            }
            else if (petid == 200904) {
                skin = "607";
            }
            else if (petid == 200117) {
                skin = "400521";
            }
            else if (petid == 200097) {
                skin = "400519";
            }
            else if (petid == 200098) {
                skin = "400520";
            }
            else if (petid == 200099) {
                skin = "400522";
            }
            else if (petid == 200100) {
                skin = "400524";
            }
            else if (petid == 200201) {
                skin = "400524";
            }
            else if (petid == 200101) {
                skin = "400525";
            }
            else if (petid == 200188) {
                skin = "900058";
            }
            else if (petid == 200185) {
                skin = "900085";
            }
            else if (petid == 200184) {
                skin = "900086";
            }
            else if (petid == 200195) {
                skin = "900087";
            }
            else if (petid == 200106) {
                skin = "900088";
            }
            else if (petid == 200190) {
                skin = "900098";
            }
            else if (petid == 200189) {
                skin = "900099";
            }
            else if (petid == 200205) {
                skin = "400519";
            }
            else if (petid == 200206) {
                skin = "400520";
            }
            else if (petid == 200207) {
                skin = "400522";
            }
            else if (petid == 200208) {
                skin = "400524";
            }
            else if (petid == 200209) {
                skin = "400525";
            }
            else if (petid == 200210) {
                skin = "400521";
            }
            else if (petid == 200211) {
                skin = "400518";
            }
            else if (petid == 200212) {
                skin = "900086";
            }
            else if (petid == 200203) {
                skin = "900086";
            }
            else if (petid == 200213) {
                skin = "900085";
            }
            else if (petid == 200214) {
                skin = "900058";
            }
            else if (petid == 200215) {
                skin = "900099";
            }
            else if (petid == 200216) {
                skin = "900098";
            }
            else if (petid == 200217) {
                skin = "900087";
            }
            else if (petid == 200202) {
                skin = "400523";
            }
            else if (petid == 200238) {
                skin = "500789";
            }
            else if (petid == 200901) {
                skin = "400518";
            }
            else if (petid == 200902) {
                skin = "400523";
            }
            else if (petid == 200903) {
                skin = "500251";
            }
            else if (petid == 200900) {
                skin = "400521";
            }
            else if (petid == 200500) {
                skin = "400519";
            }
            else if (petid == 200501) {
                skin = "400520";
            }
            else if (petid == 200502) {
                skin = "400522";
            }
            else if (petid == 200503) {
                skin = "400524";
            }
            else if (petid == 200504) {
                skin = "400525";
            }
            else if (petid == 200402) {
                skin = "900058";
            }
            else if (petid == 200401) {
                skin = "900085";
            }
            else if (petid == 200400) {
                skin = "900086";
            }
            else if (petid == 200405) {
                skin = "900087";
            }
            else if (petid == 200404) {
                skin = "900098";
            }
            else if (petid == 200403) {
                skin = "900099";
            }
            else if (petid == 200704) {
                skin = "900088";
            }
            pet.setSummoningskin(skin);
            pet.setColorScheme(null);
            String four = pet.getFourattributes();
            int ran1 = pet.getSI2("hhp");
            int ran2 = pet.getSI2("hmp");
            int ran3 = pet.getSI2("hap");
            int ran4 = pet.getSI2("hsp");
            pet.setHp(pet.getHp() - ran1);
            pet.setMp(pet.getMp() - ran2);
            pet.setAp(pet.getAp() - ran3);
            pet.setSp(pet.getSp() - ran4);
            if (ran1 != 0) {
                four = DrawnitemsAction.Splice(four, "hhp=" + ran1, 4);
            }
            if (ran2 != 0) {
                four = DrawnitemsAction.Splice(four, "hmp=" + ran2, 4);
            }
            if (ran3 != 0) {
                four = DrawnitemsAction.Splice(four, "hap=" + ran3, 4);
            }
            if (ran4 != 0) {
                four = DrawnitemsAction.Splice(four, "hsp=" + ran4, 4);
            }
            ran1 = 0;
            ran2 = 0;
            ran3 = 0;
            ran4 = 0;
            switch (GameServer.random.nextInt(4)) {
                case 0: {
                    ran1 = 10;
                    break;
                }
                case 1: {
                    ran2 = 10;
                    break;
                }
                case 2: {
                    ran3 = 10;
                    break;
                }
                case 3: {
                    ran4 = 10;
                    break;
                }
            }
            pet.setHp(pet.getHp() + ran1);
            pet.setMp(pet.getMp() + ran2);
            pet.setAp(pet.getAp() + ran3);
            pet.setSp(pet.getSp() + ran4);
            if (ran1 != 0) {
                four = DrawnitemsAction.Splice(four, "hhp=" + ran1, 2);
            }
            if (ran2 != 0) {
                four = DrawnitemsAction.Splice(four, "hmp=" + ran2, 2);
            }
            if (ran3 != 0) {
                four = DrawnitemsAction.Splice(four, "hap=" + ran3, 2);
            }
            if (ran4 != 0) {
                four = DrawnitemsAction.Splice(four, "hsp=" + ran4, 2);
            }
            pet.setFourattributes(four);
            pet.setBasishp(0);
            pet.setBasismp(0);
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            assetUpdate.setPet(pet);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (type == 8828L) {
            if (petid != 200142 && petid != 200124 && petid != 200905 && petid != 200076 && petid != 200183 && petid != 200148 && petid != 200090 && petid != 200077 && petid != 200085 && petid != 200093 && petid != 200147 && petid != 200135 && petid != 200158 && petid != 200187 && petid != 200143 && petid != 200140 && petid != 200802 && petid != 200805 && petid != 200806 && petid != 200809) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽不能使用此化形丹·幻"));
                return;
            }
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.USEGOOD);
            useGood(good, 1);
            assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
            String skin = null;
            if (petid == 200090) {
                skin = "900089";
            }
            else if (petid == 200077) {
                skin = "900090";
            }
            else if (petid == 200148) {
                skin = "900100";
            }
            else if (petid == 200183) {
                skin = "900101";
            }
            else if (petid == 200076) {
                skin = "900102";
            }
            else if (petid == 200124) {
                skin = "900103";
            }
            else if (petid == 200142) {
                skin = "900104";
            }
            else if (petid == 200085) {
                skin = "900091";
            }
            else if (petid == 200093) {
                skin = "900088";
            }
            else if (petid == 200147) {
                skin = "900092";
            }
            else if (petid == 200135) {
                skin = "900093";
            }
            else if (petid == 200158) {
                skin = "900095";
            }
            else if (petid == 200187) {
                skin = "9514201";
            }
            else if (petid == 200143) {
                skin = "900097";
            }
            else if (petid == 200140) {
                skin = "300289";
            }
            else if (petid == 200806) {
                skin = "900095";
            }
            else if (petid == 200809) {
                skin = "9514201";
            }
            else if (petid == 200805) {
                skin = "900097";
            }
            else if (petid == 200802) {
                skin = "300289";
            }
            else if (petid == 200905) {
                skin = "300290";
            }
            pet.setSummoningskin(skin);
            pet.setColorScheme(null);
            String four = pet.getFourattributes();
            int ran1 = pet.getSI2("hhp");
            int ran2 = pet.getSI2("hmp");
            int ran3 = pet.getSI2("hap");
            int ran4 = pet.getSI2("hsp");
            pet.setHp(pet.getHp() - ran1);
            pet.setMp(pet.getMp() - ran2);
            pet.setAp(pet.getAp() - ran3);
            pet.setSp(pet.getSp() - ran4);
            if (ran1 != 0) {
                four = DrawnitemsAction.Splice(four, "hhp=" + ran1, 4);
            }
            if (ran2 != 0) {
                four = DrawnitemsAction.Splice(four, "hmp=" + ran2, 4);
            }
            if (ran3 != 0) {
                four = DrawnitemsAction.Splice(four, "hap=" + ran3, 4);
            }
            if (ran4 != 0) {
                four = DrawnitemsAction.Splice(four, "hsp=" + ran4, 4);
            }
            ran1 = 0;
            ran2 = 0;
            ran3 = 0;
            ran4 = 0;
            switch (GameServer.random.nextInt(4)) {
                case 0: {
                    ran1 = 5;
                    break;
                }
                case 1: {
                    ran2 = 5;
                    break;
                }
                case 2: {
                    ran3 = 5;
                    break;
                }
                case 3: {
                    ran4 = 5;
                    break;
                }
            }
            pet.setHp(pet.getHp() + ran1);
            pet.setMp(pet.getMp() + ran2);
            pet.setAp(pet.getAp() + ran3);
            pet.setSp(pet.getSp() + ran4);
            if (ran1 != 0) {
                four = DrawnitemsAction.Splice(four, "hhp=" + ran1, 2);
            }
            if (ran2 != 0) {
                four = DrawnitemsAction.Splice(four, "hmp=" + ran2, 2);
            }
            if (ran3 != 0) {
                four = DrawnitemsAction.Splice(four, "hap=" + ran3, 2);
            }
            if (ran4 != 0) {
                four = DrawnitemsAction.Splice(four, "hsp=" + ran4, 2);
            }
            pet.setFourattributes(four);
            pet.setBasishp(0);
            pet.setBasismp(0);
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            assetUpdate.setPet(pet);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    
    public void useDraw(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        if (pet.getTurnRount() < 3) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该召唤兽未3转！"));
            return;
        }
        if (pet.getPetSkills() == null || pet.getPetSkills().equals("")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽没有技能"));
            return;
        }
        if (pet.getGoods() != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该召唤兽携带着装备"));
            return;
        }
        if (login.getSummoning_id() != null && login.getSummoning_id().compareTo(pet.getSid()) == 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("这只召唤兽已在参战中！！！"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        AllServiceUtil.getRoleSummoningService().deleteRoleSummoningBySid(pet.getSid());
        RoleSummoning pet2 = new RoleSummoning();
        pet2.setSid(pet.getSid());
        assetUpdate.setPet(pet2);
        if (GameServer.random.nextInt(100) < 40) {
            List<String> skills = new ArrayList<>();
            if (pet.getPetSkills() != null && !pet.getPetSkills().equals("")) {
                String[] vs = pet.getPetSkills().split("\\|");
                for (int i = 0; i < vs.length; ++i) {
                    if (!vs[i].equals("")) {
                        skills.add(vs[i]);
                    }
                }
            }
            String id = (String)skills.get(GameServer.random.nextInt(skills.size()));
            Skill skill = GameServer.getSkill(id);
            good.setType(2326L);
            Integer grade = Integer.valueOf(skill.getSkilltype());
            String instru = "技能=" + skill.getSkillname() + "|技能等级=" + (((int)grade == 1) ? "普通" : (((int)grade == 2) ? "高级" : (((int)grade == 3) ? "终极" : "终极")));
            good.setValue(instru);
            AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(9));
            AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
            assetUpdate.setGood(good);
            assetUpdate.setMsg("提取成功!");
        }
        else {
            useGood(good, 1);
            assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
            assetUpdate.setMsg("提取失败!");
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
	public void train(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
		if (pet.getPetSkills().indexOf("3034") == -1 && pet.getPetSkills().indexOf("3040") == -1) {
			SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该召唤兽没有???"));
			return;
		}
		Map<String, Integer> skillweightMap = new HashMap<>();
		skillweightMap.put("1606", Integer.valueOf(15));
		skillweightMap.put("1607", Integer.valueOf(15));
		skillweightMap.put("1608", Integer.valueOf(15));
		skillweightMap.put("1828", Integer.valueOf(15));
		skillweightMap.put("1829", Integer.valueOf(15));
		skillweightMap.put("1830", Integer.valueOf(15));
		skillweightMap.put("1840", Integer.valueOf(15));
		skillweightMap.put("1841", Integer.valueOf(15));
		skillweightMap.put("1842", Integer.valueOf(15));
		skillweightMap.put("1867", Integer.valueOf(15));
		skillweightMap.put("1868", Integer.valueOf(15));
		skillweightMap.put("1869", Integer.valueOf(15));
		List<String> ids = new ArrayList(skillweightMap.keySet());
		int max = 999;
		List<String> skillIds = new ArrayList<>();
		getskills(skillIds, pet.getPetSkills());
		int i = 0;
		while (i < skillIds.size()) {
			if (ids.contains(skillIds.get(i))) {
				max = 5000;
				break;
			} else {
				++i;
			}
		}
		AssetUpdate assetUpdate = new AssetUpdate();
		assetUpdate.setType(AssetUpdate.USEGOOD);
		useGood(good, 1);
		assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
		int train = DropUtil.isV(90.0) ? 5 : 20;
		assetUpdate.setMsg("#您的 " + pet.getSummoningname() + " 召唤兽提升修炼度:" + train + " 点");
		pet.setTrainNum(pet.getTrainNum() + train);
		if (pet.getTrainNum() >= max) {
			List<String> skills = new ArrayList<>();
			if (pet.getPetSkills() != null && !pet.getPetSkills().equals("")) {
				String[] vs = pet.getPetSkills().split("\\|");
				for (int k = 0; k < vs.length; k++) {
					if (!vs[k].equals("") && !vs[k].equals("3034") && !vs[k].equals("3040"))
						skills.add(vs[k]);
				}
			}
			int totalweight = skillweightMap.values().stream().mapToInt(Integer::intValue).sum();
			for (int j = ids.size() - 1; j >= 0; j--) {
				if (skills.contains(ids.get(j)))
					ids.remove(j);
			}
			LOOP: while (ids.size() != 0) {
				int randomIndex = ThreadLocalRandom.current().nextInt(totalweight);
				int weightsum = 0;
				String selectedid = null;
				for (String id : ids) {
					weightsum += ((Integer) skillweightMap.get(id)).intValue();
					if (randomIndex < weightsum) {
						selectedid = id;
						break;
					}
				}
				if (selectedid == null)
					continue;
				Skill skill = GameServer.getSkill(selectedid);
				if (skill == null)
					continue;
				if (skill.getSkillralation() != null && !skill.getSkillralation().equals("")) {
					String[] chongtu = skill.getSkillralation().split("\\|");
					for (int m = 0; m < chongtu.length; m++) {
						if (chongtu[m].equals(skill.getSkillid() + ""))
							continue;
						if (skills.contains(chongtu[m])) {
							Skill skill2 = GameServer.getSkill(chongtu[m]);
							if (skill2 == null)
								continue;
							continue LOOP;
						}
						continue;
					}
				}
				skills.add(selectedid);
				StringBuffer buffer = new StringBuffer();
				for (int k = 0; k < skills.size(); k++) {
					if (buffer.length() != 0)
						buffer.append("|");
					buffer.append(skills.get(k));
				}
				pet.setPetSkills(buffer.toString());
				getskills(skills, pet.getSkill());
				getskills(skills, pet.getBeastSkills());
				pet.setSkillData(skillData(skills));
				assetUpdate.updata("T悟技");
				SuitMixdeal.PYJN(login.getRolename(), pet.getSummoningname(), skill.getSkillname());
                AchievemUtil.detectionAchievem(login, "终极技能");//add@magor
				break;
			}
			pet.setTrainNum(0);
		}
		AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
		assetUpdate.setPet(pet);
		SendMessage.sendMessageToSlef(ctx,
				Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
	}
    
	public void traingd(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
		if (pet.getPetSkills().indexOf("3034") == -1 && pet.getPetSkills().indexOf("3040") == -1) {
			SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该召唤兽没有???"));
			return;
		}
		Map<String, Integer> skillweightMap = new HashMap<>();
		skillweightMap.put("1606", Integer.valueOf(15));
		skillweightMap.put("1607", Integer.valueOf(15));
		skillweightMap.put("1608", Integer.valueOf(15));
		skillweightMap.put("1828", Integer.valueOf(15));
		skillweightMap.put("1829", Integer.valueOf(15));
		skillweightMap.put("1830", Integer.valueOf(15));
		skillweightMap.put("1840", Integer.valueOf(15));
		skillweightMap.put("1841", Integer.valueOf(15));
		skillweightMap.put("1842", Integer.valueOf(15));
		skillweightMap.put("1867", Integer.valueOf(15));
		skillweightMap.put("1868", Integer.valueOf(15));
		skillweightMap.put("1869", Integer.valueOf(15));
		List<String> ids = new ArrayList(skillweightMap.keySet());
		int max = 999;
		List<String> skillIds = new ArrayList<>();
		getskills(skillIds, pet.getPetSkills());
		int i = 0;
		while (i < skillIds.size()) {
			if (ids.contains(skillIds.get(i))) {
				max = 5000;
				break;
			} else {
				++i;
			}
		}
		AssetUpdate assetUpdate = new AssetUpdate();
		assetUpdate.setType(AssetUpdate.USEGOOD);
		useGood(good, 1);
		assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
		if (good.getValue() != null) {
			String[] valus = good.getValue().split("=");
			if (valus.length >= 2) {
				int train = Integer.valueOf(valus[1]).intValue();
				assetUpdate.setMsg("#您的 " + pet.getSummoningname() + " 召唤兽提升修炼度:" + train + " 点");
				pet.setTrainNum(pet.getTrainNum() + train);
				if (pet.getTrainNum() >= max) {
					List<String> skills = new ArrayList<>();
					if (pet.getPetSkills() != null && !pet.getPetSkills().equals("")) {
						String[] vs = pet.getPetSkills().split("\\|");
						for (int k = 0; k < vs.length; k++) {
							if (!vs[k].equals("") && !vs[k].equals("3034") && !vs[k].equals("3040"))
								skills.add(vs[k]);
						}
					}
					int totalweight = skillweightMap.values().stream().mapToInt(Integer::intValue).sum();
					for (int j = ids.size() - 1; j >= 0; j--) {
						if (skills.contains(ids.get(j)))
							ids.remove(j);
					}
					LOOP: while (ids.size() != 0) {
						int randomIndex = ThreadLocalRandom.current().nextInt(totalweight);
						int weightsum = 0;
						String selectedid = null;
						for (String id : ids) {
							weightsum += ((Integer) skillweightMap.get(id)).intValue();
							if (randomIndex < weightsum) {
								selectedid = id;
								break;
							}
						}
						if (selectedid == null)
							continue;
						Skill skill = GameServer.getSkill(selectedid);
						if (skill == null)
							continue;
						if (skill.getSkillralation() != null && !skill.getSkillralation().equals("")) {
							String[] chongtu = skill.getSkillralation().split("\\|");
							for (int m = 0; m < chongtu.length; m++) {
								if (chongtu[m].equals(skill.getSkillid() + ""))
									continue;
								if (skills.contains(chongtu[m])) {
									Skill skill2 = GameServer.getSkill(chongtu[m]);
									if (skill2 == null)
										continue;
									continue LOOP;
								}
								continue;
							}
						}
						skills.add(selectedid);
						StringBuffer buffer = new StringBuffer();
						for (int k = 0; k < skills.size(); k++) {
							if (buffer.length() != 0)
								buffer.append("|");
							buffer.append(skills.get(k));
						}
						pet.setPetSkills(buffer.toString());
						getskills(skills, pet.getSkill());
						getskills(skills, pet.getBeastSkills());
						pet.setSkillData(skillData(skills));
						assetUpdate.updata("T悟技");
						SuitMixdeal.PYJN(login.getRolename(), pet.getSummoningname(), skill.getSkillname());
                        AchievemUtil.detectionAchievem(login, "终极技能");
						break;
					}
					pet.setTrainNum(0);
				}
				AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
				assetUpdate.setPet(pet);
				SendMessage.sendMessageToSlef(ctx,
						Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
			}
		}
	}
    
    public void useBoneElution(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        if (pet.getDragon() <= 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽没有服用过龙之骨"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        pet.setGrowlevel(Arith.sub(Double.parseDouble(pet.getGrowlevel()), 0.01 * (double)pet.getDragon()) + "");
        pet.setDragon(0);
        pet.setHp(pet.getHp() - pet.getSI2("hp"));
        pet.setMp(pet.getMp() - pet.getSI2("mp"));
        pet.setAp(pet.getAp() - pet.getSI2("ap"));
        pet.setSp(pet.getSp() - pet.getSI2("sp"));
        String four = pet.getFourattributes();
        four = DrawnitemsAction.Splice(four, "hp=" + pet.getSI2("hp"), 4);
        four = DrawnitemsAction.Splice(four, "mp=" + pet.getSI2("mp"), 4);
        four = DrawnitemsAction.Splice(four, "ap=" + pet.getSI2("ap"), 4);
        four = DrawnitemsAction.Splice(four, "sp=" + pet.getSI2("sp"), 4);
        pet.setFourattributes(four);
        pet.setBasishp(0);
        pet.setBasismp(0);
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("#G龙之骨已经被清除");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void useBoneElutionsp(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        if (pet.getSpdragon() <= 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽没有服用过超级龙之骨"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        pet.setGrowlevel(Arith.sub(Double.parseDouble(pet.getGrowlevel()), 0.01 * (double)pet.getSpdragon()) + "");
        pet.setSpdragon(0);
        pet.setHp(pet.getHp() - pet.getSI2("hps"));
        pet.setMp(pet.getMp() - pet.getSI2("mps"));
        pet.setAp(pet.getAp() - pet.getSI2("aps"));
        pet.setSp(pet.getSp() - pet.getSI2("sps"));
        String four = pet.getFourattributes();
        four = DrawnitemsAction.Splice(four, "hps=" + pet.getSI2("hps"), 4);
        four = DrawnitemsAction.Splice(four, "mps=" + pet.getSI2("mps"), 4);
        four = DrawnitemsAction.Splice(four, "aps=" + pet.getSI2("aps"), 4);
        four = DrawnitemsAction.Splice(four, "sps=" + pet.getSI2("sps"), 4);
        pet.setFourattributes(four);
        pet.setBasishp(0);
        pet.setBasismp(0);
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setMsg("#G超级龙之骨已经被清除");
        assetUpdate.setPet(pet);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void dragonSaliva(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        if (!pet.getSsn().equals("5") && !pet.getSummoningid().equals("200125")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("不是龙涎丸宝宝"));
            return;
        }
        int drac = pet.getDraC();
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        if (drac >= Integer.parseInt(configure.getLywsx())) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("已经达到最大使用次数"));
            return;
        }
        int maxsum = 0;
        if ((int)pet.getGrade() >= 433) {
            maxsum = 9;
        }
        else if ((int)pet.getGrade() >= 362) {
            maxsum = 8;
        }
        else if ((int)pet.getGrade() >= 322) {
            maxsum = 7;
        }
        else if ((int)pet.getGrade() >= 272) {
            maxsum = 6;
        }
        else if ((int)pet.getGrade() >= 221) {
            maxsum = 5;
        }
        else if ((int)pet.getGrade() >= 191) {
            maxsum = 4;
        }
        else if ((int)pet.getGrade() >= 151) {
            maxsum = 3;
        }
        else if ((int)pet.getGrade() >= 90) {
            maxsum = 2;
        }
        else if ((int)pet.getGrade() >= 50) {
            maxsum = 1;
        }
        if ((int)pet.getGrade() >= 433) {
            maxsum = Integer.parseInt(configure.getLywsx());
        }
        if (drac >= maxsum) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽当前等级最多使用" + maxsum + "个"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        ++drac;
        pet.setDraC(drac);
        if (drac == 9) {
            pet.setColorScheme("1|0|255|256|0|0|512|256|0|512|0|256");
        }
        double grow = Double.parseDouble(pet.getGrowlevel()) + 0.02;
        pet.setGrowlevel(Arith.xiaoshu3(grow));
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("#G使用成功");
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void GrowUpDan(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        String value = good.getValue();
        if (value == null || value.equals("")) {
            value = "100|0";
        }
        String[] v = value.split("\\|");
        if (!v[1].equals("0") && !v[1].equals(pet.getSummoningid())) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽无法使用该类型的元气丹"));
            return;
        }
        int type = 0;
        if (good.getGoodsname().indexOf("元气") != -1) {
            type = 1;
        }
        if (type == 0 && (pet.getSsn().equals("6") || pet.getSsn().equals("2") || pet.getSsn().equals("3") || pet.getSsn().equals("4"))) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该召唤兽类型不能变色"));
            return;
        }
        ColorScheme colorScheme = GameServer.getColors(Integer.parseInt(v[1]));
        if (colorScheme == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("没有该类型的变色方案"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        int gl = Integer.parseInt(v[0]);
        if (gl < GameServer.random.nextInt(100)) {
            assetUpdate.setMsg("召唤兽吃了一点反应都没有");
        }
        else {
            if (type == 1) {
                if (pet.getSsn().equals("2") || pet.getSsn().equals("3") || pet.getSsn().equals("4") || pet.getSsn().equals("5") || pet.getSsn().equals("6")) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该召唤兽无法使用元气丹"));
                    return;
                }
                double grow = Double.parseDouble(pet.getGrowlevel());
                grow = Arith.sub(grow, Arith.div((double)pet.getGrowUpDanNum(), 1000.0));
                int zhi = colorScheme.getMin() + GameServer.random.nextInt(colorScheme.getMax() - colorScheme.getMin() + 1);
                grow = Arith.add(grow, Arith.div((double)zhi, 1000.0));
                pet.setGrowUpDanNum(zhi);
                pet.setGrowlevel(Arith.xiaoshu3(grow));
                assetUpdate.setMsg("#G使用成功,召唤兽成长发生了变化");
            }
            else {
                assetUpdate.setMsg("召唤兽变色成功");
            }
            if (!pet.getSsn().equals("6")) {
                pet.setColorScheme(colorScheme.getValue());
            }
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            assetUpdate.setPet(pet);
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void useKeel(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        if (pet.getDragon() >= Integer.parseInt(configure.getLzgsx())) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("龙之骨数量已达到上限！"));
            return;
        }
        useGood(good, 1);
        pet.setDragon(pet.getDragon() + 1);
        pet.setGrowlevel(mathDouble(Double.parseDouble(pet.getGrowlevel()), 0.01).toString());
        String four = pet.getFourattributes();
        if (good.getValue() != null && !good.getValue().isEmpty()) {
            String[] value = good.getValue().split("=");
            StringBuilder message = new StringBuilder();
            String separator = "#Y";
            if (value[0].equals("HP")) {
                pet.setHp(pet.getHp() + Integer.parseInt(value[1]));
                four = DrawnitemsAction.Splice(four, "hp=" + Integer.parseInt(value[1]), 2);
                message.append(separator).append("HP+").append(Integer.parseInt(value[1]));
                separator = " ";
            }
            if (value[0].equals("MP")) {
                pet.setMp(pet.getMp() + Integer.parseInt(value[1]));
                four = DrawnitemsAction.Splice(four, "mp=" + Integer.parseInt(value[1]), 2);
                message.append(separator).append("MP+").append(Integer.parseInt(value[1]));
                separator = " ";
            }
            if (value[0].equals("AP")) {
                pet.setAp(pet.getAp() + Integer.parseInt(value[1]));
                four = DrawnitemsAction.Splice(four, "ap=" + Integer.parseInt(value[1]), 2);
                message.append(separator).append("AP+").append(Integer.parseInt(value[1]));
                separator = " ";
            }
            if (value[0].equals("SP")) {
                pet.setSp(pet.getSp() + Integer.parseInt(value[1]));
                four = DrawnitemsAction.Splice(four, "sp=" + Integer.parseInt(value[1]), 2);
                message.append(separator).append("SP+").append(Integer.parseInt(value[1]));
            }
            String finalMessage = message.toString().trim();
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(finalMessage));
            pet.setFourattributes(four);
        }
        else {
            int[] rans = new int[4];
            int sum = 0;
            Random random = new Random();
            int decision = random.nextInt(2);
            if (decision == 0) {
                int chosenIndex = random.nextInt(4);
                rans[chosenIndex] = 6;
                sum = 6;
            }
            else {
                Arrays.fill(rans, 2);
                sum = 8;
            }
            pet.setHp(pet.getHp() + rans[0]);
            pet.setMp(pet.getMp() + rans[1]);
            pet.setAp(pet.getAp() + rans[2]);
            pet.setSp(pet.getSp() + rans[3]);
            if (rans[0] != 0) {
                four = DrawnitemsAction.Splice(four, "hp=" + rans[0], 2);
            }
            if (rans[1] != 0) {
                four = DrawnitemsAction.Splice(four, "mp=" + rans[1], 2);
            }
            if (rans[2] != 0) {
                four = DrawnitemsAction.Splice(four, "ap=" + rans[2], 2);
            }
            if (rans[3] != 0) {
                four = DrawnitemsAction.Splice(four, "sp=" + rans[3], 2);
            }
            pet.setFourattributes(four);
            StringBuilder message2 = new StringBuilder();
            String separator2 = "#Y";
            if (rans[0] != 0) {
                message2.append(separator2).append("HP+").append(rans[0]);
                separator2 = " ";
            }
            if (rans[1] != 0) {
                message2.append(separator2).append("MP+").append(rans[1]);
                separator2 = " ";
            }
            if (rans[2] != 0) {
                message2.append(separator2).append("AP+").append(rans[2]);
                separator2 = " ";
            }
            if (rans[3] != 0) {
                message2.append(separator2).append("SP+").append(rans[3]);
            }
            String finalMessage2 = message2.toString().trim();
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(finalMessage2));
        }
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setPet(pet);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void useKeelsp(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        if (configure != null) {
            if (pet.getSpdragon() >= Integer.parseInt(configure.getCjlzgsx().toString())) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("超级龙之骨数量已达到上限！"));
                return;
            }
        }
        else if (pet.getSpdragon() >= 2) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("超级龙之骨数量已达到上限！"));
            return;
        }
        if ((int)pet.getPower() <= 4) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽点化后才能使用！"));
            return;
        }
        useGood(good, 1);
        pet.setSpdragon(pet.getSpdragon() + 1);
        boolean isCJ = good.getGoodsname().contains("超级龙之骨");
        pet.setGrowlevel(mathDouble(Double.parseDouble(pet.getGrowlevel()), 0.01).toString());
        String four = pet.getFourattributes();
        if (good.getValue() != null && !good.getValue().isEmpty()) {
            String[] value = good.getValue().split("=");
            StringBuilder message = new StringBuilder();
            String separator = "#Y";
            if (value[0].equals("HP")) {
                pet.setHp(pet.getHp() + Integer.parseInt(value[1]));
                four = DrawnitemsAction.Splice(four, "hps=" + Integer.parseInt(value[1]), 2);
                message.append(separator).append("HP+").append(Integer.parseInt(value[1]));
                separator = " ";
            }
            if (value[0].equals("MP")) {
                pet.setMp(pet.getMp() + Integer.parseInt(value[1]));
                four = DrawnitemsAction.Splice(four, "mps=" + Integer.parseInt(value[1]), 2);
                message.append(separator).append("MP+").append(Integer.parseInt(value[1]));
                separator = " ";
            }
            if (value[0].equals("AP")) {
                pet.setAp(pet.getAp() + Integer.parseInt(value[1]));
                four = DrawnitemsAction.Splice(four, "aps=" + Integer.parseInt(value[1]), 2);
                message.append(separator).append("AP+").append(Integer.parseInt(value[1]));
                separator = " ";
            }
            if (value[0].equals("SP")) {
                pet.setSp(pet.getSp() + Integer.parseInt(value[1]));
                four = DrawnitemsAction.Splice(four, "sps=" + Integer.parseInt(value[1]), 2);
                message.append(separator).append("SP+").append(Integer.parseInt(value[1]));
            }
            pet.setFourattributes(four);
            String finalMessage = message.toString().trim();
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(finalMessage));
        }
        else {
            int[] rans = new int[4];
            int sum = 0;
            Random random = new Random();
            int decision = random.nextInt(2);
            if (decision == 0) {
                int chosenIndex = random.nextInt(4);
                rans[chosenIndex] = 6;
                sum = 6;
            }
            else {
                Arrays.fill(rans, 2);
                sum = 8;
            }
            pet.setHp(pet.getHp() + rans[0]);
            pet.setMp(pet.getMp() + rans[1]);
            pet.setAp(pet.getAp() + rans[2]);
            pet.setSp(pet.getSp() + rans[3]);
            if (rans[0] != 0) {
                four = DrawnitemsAction.Splice(four, "hps=" + rans[0], 2);
            }
            if (rans[1] != 0) {
                four = DrawnitemsAction.Splice(four, "mps=" + rans[1], 2);
            }
            if (rans[2] != 0) {
                four = DrawnitemsAction.Splice(four, "aps=" + rans[2], 2);
            }
            if (rans[3] != 0) {
                four = DrawnitemsAction.Splice(four, "sps=" + rans[3], 2);
            }
            pet.setFourattributes(four);
            StringBuilder message2 = new StringBuilder();
            String separator2 = "#Y";
            if (rans[0] != 0) {
                message2.append(separator2).append("HP+").append(rans[0]);
                separator2 = " ";
            }
            if (rans[1] != 0) {
                message2.append(separator2).append("MP+").append(rans[1]);
                separator2 = " ";
            }
            if (rans[2] != 0) {
                message2.append(separator2).append("AP+").append(rans[2]);
                separator2 = " ";
            }
            if (rans[3] != 0) {
                message2.append(separator2).append("SP+").append(rans[3]);
            }
            String finalMessage2 = message2.toString().trim();
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(finalMessage2));
        }
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setPet(pet);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void useNgauWanPills(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        if (pet.getGrade() > 100) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽已转生"));
            return;
        }
        useGood(good, 1);
        pet.setGrade(101);
        pet.setBone(Integer.valueOf(0));
        pet.setSpir(Integer.valueOf(0));
        pet.setPower(Integer.valueOf(0));
        pet.setSpeed(Integer.valueOf(0));
        pet.setCalm(Integer.valueOf(0));
        pet.setExp(new BigDecimal(0));
        pet.setTurnRount(BattleMixDeal.petTurnRount(101));
        pet.setFriendliness(Long.valueOf(0L));
        pet.setFaithful(Integer.valueOf(100));
        BigDecimal grow = mathDouble(Double.parseDouble(pet.getGrowlevel()), 0.1);
        pet.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));
        pet.setBasishp(0);
        pet.setBasismp(0);
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        assetUpdate.setPet(pet);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static BigDecimal mathDouble(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }
    
    public void oepnqldan(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        int flag = 6;
        if ((int)pet.getOpenql() >= flag) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽的技能格子都已解开!"));
            return;
        }
        useGood(good, 1);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        if (GameServer.random.nextInt(100) < 10) {
            pet.setOpenql(Integer.valueOf((int)pet.getOpenql() + 1));
            assetUpdate.updata("T格子");
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            assetUpdate.setPet(pet);
            SuitMixdeal.jpd(login.getRolename(), pet.getSummoningname());
        }
        else {
            assetUpdate.setMsg("开启失败");
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void openSkillSeal(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        Boolean b = Boolean.valueOf(true);
        if ((int)pet.getFoPenSeal() > (int)pet.getOpenSeal()) {
            b = Boolean.valueOf(false);
        }
        if ((int)pet.getFoPenSeal() - 1 != (int)pet.getOpenSeal() && (boolean)b) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("当前没有可解封技能封印格#46!"));
            return;
        }
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        int skllNum = Integer.parseInt(configure.getZhsjngs());
        int flag = 6;
        if (skllNum == 9) {
            flag = skllNum - 1;
            if (pet.getTurnRount() == 4 && Integer.parseInt(pet.getSsn()) > 0) {
                flag = skllNum;
            }
        }
        if ((int)pet.getOpenSeal() >= flag) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽的技能格子都已解开!"));
            return;
        }
        int sum = 1;
        useGood(good, sum);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        configure = (Configure)s.get(Integer.valueOf(8));
        String xdzhssx = configure.getXdzhssx();
        if (Battlefield.isV(Double.parseDouble(xdzhssx))) {
            pet.setOpenSeal(Integer.valueOf((int)pet.getOpenSeal() + 1));
            assetUpdate.updata("T格子");
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            assetUpdate.setPet(pet);
            SuitMixdeal.jpd(login.getRolename(), pet.getSummoningname());
        }
        else {
            assetUpdate.setMsg("开启失败");
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void openSkillSealSSGZ(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        if (login.getGold().longValue() < 20000000L) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您的银两不足2000W"));
            return;
        }
        if (!pet.getSsn().equals("2") && !pet.getSsn().equals("3") && !pet.getSsn().equals("4")) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您携带的召唤兽并不是神兽!!!"));
            return;
        }
        if (StringUtils.isNotBlank(pet.getBeastSkills())) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("神兽技能格已经解封!!!"));
            return;
        }
        if (StringUtils.isNotBlank(pet.getFourattributes())) {
            String[] v = pet.getFourattributes().split("\\|");
            String ssjf = null;
            int length = v.length;
            int i = 0;
            while (i < length) {
                String s = v[i];
                if (s.startsWith("ssjn")) {
                    ssjf = s;
                    break;
                }
                else {
                    ++i;
                }
            }
            if (StringUtils.isNotBlank(ssjf)) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽的神兽技能格子已解开!"));
                return;
            }
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.updata("D=-20000000");
        login.setGold(login.getGold().subtract(new BigDecimal(20000000)));
        if (GameServer.random.nextInt(100) < 95) {
            pet.setBeastSkills("-1");
            String four = DrawnitemsAction.Splice(pet.getFourattributes(), "ssjn=1", 2);
            pet.setFourattributes(four);
            assetUpdate.updata("T格子");
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            assetUpdate.setPet(pet);
            StringBuffer buffer = new StringBuffer();
            buffer.append("#c00E3E3");
            buffer.append("鸿运当头，");
            buffer.append("#R");
            buffer.append(login.getRolename());
            buffer.append("#c00E3E3");
            buffer.append("成功为");
            buffer.append("#R");
            buffer.append(pet.getSummoningname());
            buffer.append("#c00E3E3");
            buffer.append("开启了#G神兽技能栏！");
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage(buffer.toString());
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msg);
        }
        else {
            assetUpdate.setMsg("开启失败");
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void openSkillSealSS(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        int skllNum = Integer.parseInt(configure.getZhsjngs());
        int flag = skllNum - 1;
        if (pet.getSsn().equals("2") || pet.getSsn().equals("3") || pet.getSsn().equals("4")) {
            flag = skllNum;
        }
        if ((int)pet.getOpenSeal() >= flag) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽的技能格子都已解开!"));
            return;
        }
        useGood(good, 1);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        if (GameServer.random.nextInt(100) < 5) {
            pet.setOpenSeal(Integer.valueOf((int)pet.getOpenSeal() + 1));
            assetUpdate.updata("T格子");
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            assetUpdate.setPet(pet);
            SuitMixdeal.jpd(login.getRolename(), pet.getSummoningname());
        }
        else {
            assetUpdate.setMsg("开启失败");
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void dicePetSkill(RoleSummoning pet, ChannelHandlerContext ctx, LoginResult login) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        DiceReidsBase diceReidsBase = AllServiceUtil.getDiceService().selectByID(String.valueOf(login.getRole_id()));
        int sum = (int)pet.getOpenSeal();
        String skillvalue = "几率=40|五行技能=1815&1816&1817&1818&1819&1820&1821&1822&1823&1824&1834&1835&1836&1837&1838&1839&1850&1852&1854&1859&1860&1862&1864&1865&1871&1872&1873&1874&1875&1876&1877&1878&1880&|几率=24|五行技能=1600&1601&1602&1603&1604&1605&1611&1612&|几率=16|五行技能=1825&1826&1827&1831&1833&1882&1883&1884&1885&1887&1888&|几率=16.7|五行技能=1843&1844&1846&1847&1848&1849&1851&1853&1855&1856&1857&1861&1863&1804&1803&1802&1801&1800&|几率=3.3五行技能=3034";
        String skillvalue2 = "几率=90|五行技能=1825&1826&1827&1831&1833&1882&1883&1884&1885&1887&1888&1600&1601&1602&1603&1604&1605&1611&1612&|几率=15|五行技能=3034";
        Skill skill;
        if (pet.getDicenum() >= 15) {
            skill = skillid(skillvalue2);
        }
        else {
            skill = skillid(skillvalue);
        }
        if (skill == null) {
            return;
        }
        if (diceReidsBase.getTime() != null) {
            String oldpet = diceReidsBase.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = sdf.parse(oldpet);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(10, 2);
                long startTime = c.getTimeInMillis();
                long endTime = System.currentTimeMillis();
                long time = (startTime - endTime) / 1000L / 60L;
                long Time = startTime - endTime;
                long hours = Time / 3600000L;
                long minutes = Time % 3600000L / 60000L;
                long seconds = Time % 60000L / 1000L;
                String formattedTime = String.format("%01d小时%02d分钟%02d秒", new Object[] { Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds) });
                if (time >= 0L) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("请等待" + formattedTime + "以后再摇骰子"));
                    return;
                }
            }
            catch (ParseException e) {
                throw new RuntimeException(e);
            }
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
            return;
        }
        String value = chongfu(skill, pet, skills, true);
        if (value != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(value));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        skills.add(skill.getSkillid() + "");
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < skills.size(); ++j) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append((String)skills.get(j));
        }
        pet.setPetSkills(buffer.toString());
        getskills(skills, pet.getSkill());
        getskills(skills, pet.getBeastSkills());
        pet.setSkillData(skillData(skills));
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(currentDate);
        diceReidsBase.setTime(formattedDate);
        pet.setDicenum(pet.getDicenum() + 1);
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        AllServiceUtil.getDiceService().addReidsLimit(diceReidsBase);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("你的召唤兽学会了" + skill.getSkillname());
        assetUpdate.updata("Dice=" + login.getRole_id() + "=" + diceReidsBase.getTime());
        int Id = skill.getSkillid();
      //  if (Id == 3034) {
        if ((Id >= 1606 && Id <= 1608)
                || (Id >= 1828 && Id <= 1830)
                || (Id >= 1840 && Id <= 1842)
                || (Id >= 1867 && Id <= 1869)
                || Id == 3034) {//学习终极技能
            pet.setDicenum(0);
            assetUpdate.updata("T悟技");
         //   StringBuffer sb = new StringBuffer();
         //   sb.append("#G");
         //   sb.append(login.getRolename());
         //   sb.append("#W轻点骰子，其召唤兽#Y");
         //   sb.append(pet.getSummoningname());
         //   sb.append("#W猛然全身发抖如被电击，若干秒后回神发现领悟了一个");
        //    sb.append("#Y");
         //   sb.append("#W修炼中的终极技能。");
         //   SuitMixdeal.jpdC(sb.toString());
       // }
       // else if ((Id >= 1815 && Id <= 1827) || (Id >= 1600 && Id <= 1605) || (Id >= 1834 && Id <= 1839) || (Id >= 1882 && Id <= 1888) || (Id >= 1611 && Id <= 1612) || Id == 1850 || Id == 1852 || Id == 1854 || Id == 1858 || Id == 1860 || Id == 1862 || Id == 1864 || Id == 1865 || Id == 1811 || Id == 1831 || Id == 1833 || (Id >= 1871 && Id <= 1880)) {
        //    pet.setDicenum(0);
            SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "终级");
            AchievemUtil.detectionAchievem(login, "终级技能");
        } else if ((Id >= 1600 && Id <= 1605)
                || (Id >= 1610 && Id <= 1612)
                || Id == 1811
                || (Id >= 1815 && Id <= 1827)
                || Id == 1831
                || (Id >= 1833 && Id <= 1839) || Id == 1848
                || Id == 1850 || Id == 1852 || Id == 1854
                || (Id >= 1858 && Id <= 1860)
                || Id == 1862 || (Id >= 1864 && Id <= 1866)
                || (Id >= 1871 && Id <= 1880) || (Id >= 1882 && Id <= 1885)
                || (Id >= 1887 && Id <= 1888)) {//学习高级技能
            pet.setDicenum(0);
            assetUpdate.updata("T悟技");
          //  StringBuffer sb = new StringBuffer();
           // sb.append("#G");
          //  sb.append(login.getRolename());
           // sb.append("#W轻点骰子，其召唤兽#Y");
          //  sb.append(pet.getSummoningname());
          //  sb.append("#W猛然全身发抖如被电击，若干秒后回神发现领悟了一个");
          //  sb.append("#Y");
          //  sb.append(skill.getSkillname());
         //   sb.append("#W技能。");
         //   SuitMixdeal.jpdC(sb.toString());
            SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "高级");
            AchievemUtil.detectionAchievem(login, "高级技能");
        } else {
            AchievemUtil.detectionAchievem(login, "普通技能");
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void addPetSkill(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = s.get(Integer.valueOf(1));
        int sum = pet.getOpenSeal();
        Skill skill = null;
        if (good.getType() == 2326L) {
            String skillName = good.getValue().split("\\|")[0].split("=")[1];
            skill = GameServer.getSkill(skillName);
        }
        else {
            skill = skillid(good.getValue());
        }
        if (skill == null) {
            return;
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
            return;
        }
        String value = chongfu(skill, pet, skills, true);
        if (value != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(value));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        skills.add(skill.getSkillid() + "");
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < skills.size(); ++j) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append((String)skills.get(j));
        }
        pet.setPetSkills(buffer.toString());
        getskills(skills, pet.getSkill());
        getskills(skills, pet.getBeastSkills());
        pet.setSkillData(skillData(skills));
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("你的召唤兽学会了" + skill.getSkillname());
        int Id = skill.getSkillid();
        if ((Id >= 1606 && Id <= 1608) || (Id >= 1828 && Id <= 1830) || (Id >= 1840 && Id <= 1842) || (Id >= 1867 && Id <= 1869) || Id == 3034) {
            assetUpdate.updata("T悟技");
            SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "终级");
            AchievemUtil.detectionAchievem(login, "终级技能");
        }
        else if ((Id >= 1815 && Id <= 1827) || (Id >= 1600 && Id <= 1605) || (Id >= 1834 && Id <= 1839) || (Id >= 1882 && Id <= 1888) || (Id >= 1611 && Id <= 1612) || Id == 1850 || Id == 1852 || Id == 1854 || Id == 1858 || Id == 1860 || Id == 1862 || Id == 1864 || Id == 1865 || Id == 1811 || Id == 1831 || Id == 1833 || (Id >= 1871 && Id <= 1880)) {
            assetUpdate.updata("T悟技");
            SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "高级");
            AchievemUtil.detectionAchievem(login, "高级技能");
        }
        else if ((Id >= 1800 && Id <= 1806) || (Id >= 1843 && Id <= 1849) || (Id >= 1855 && Id <= 1857)|| Id == 1808 || Id == 1810 || Id == 1812 || Id == 1832 || Id == 1851 || Id == 1853 || Id == 1861 || Id == 1863) {
            assetUpdate.updata("T悟技");
            SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "普通");//学习普通技能
            AchievemUtil.detectionAchievem(login, "普通技能");
        }

        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void addpetqiling(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        int sum = (int)pet.getOpenql();
        Skill skill = null;
        if (good.getType() == 2326L) {
            String skillName = good.getValue().split("\\|")[0].split("=")[1];
            skill = GameServer.getSkill(skillName);
        }
        else {
            skill = skillid(good.getValue());
        }
        if (skill == null) {
            return;
        }
        List<String> skills = new ArrayList<>();
        if (pet.getPetQlSkills() != null && !pet.getPetQlSkills().equals("")) {
            String[] vs = pet.getPetQlSkills().split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                if (!vs[i].equals("")) {
                    skills.add(vs[i]);
                }
            }
        }
        if (sum <= skills.size() || skills.size() >= 6) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽技能格子已经满了"));
            return;
        }
        String value = chongfu(skill, pet, skills, true);
        if (value != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(value));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        skills.add(skill.getSkillid() + "");
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < skills.size(); ++j) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append((String)skills.get(j));
        }
        pet.setPetQlSkills(buffer.toString());
        getskills(skills, pet.getSkill());
        getskills(skills, pet.getBeastSkills());
        pet.setSkillData(skillData(skills));
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("你的召唤兽学会了" + skill.getSkillname());
        int Id = skill.getSkillid();
        if ((Id >= 1606 && Id <= 1608) || (Id >= 1828 && Id <= 1830) || (Id >= 1840 && Id <= 1842) || (Id >= 1867 && Id <= 1869) || Id == 3034) {
            assetUpdate.updata("T悟技");
            SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "终级");
            AchievemUtil.detectionAchievem(login, "终级技能");
        }
        else if ((Id >= 1815 && Id <= 1827) || (Id >= 1600 && Id <= 1605) || (Id >= 1834 && Id <= 1839) || (Id >= 1882 && Id <= 1888) || (Id >= 1611 && Id <= 1612) || Id == 1850 || Id == 1852 || Id == 1854 || Id == 1858 || Id == 1860 || Id == 1862 || Id == 1864 || Id == 1865 || Id == 1811 || Id == 1831 || Id == 1833 || (Id >= 1871 && Id <= 1880)) {
            assetUpdate.updata("T悟技");
            SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "高级");
            AchievemUtil.detectionAchievem(login, "高级技能");
        }
        else if ((Id >= 1800 && Id <= 1806) || (Id >= 1843 && Id <= 1849) || (Id >= 1855 && Id <= 1857)|| Id == 1808 || Id == 1810 || Id == 1812 || Id == 1832 || Id == 1851 || Id == 1853 || Id == 1861 || Id == 1863) {
            assetUpdate.updata("T悟技");
            SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "普通");//学习普通技能
            AchievemUtil.detectionAchievem(login, "普通技能");
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static Skill skillid(String value) {
        String[] v = value.split("\\|");
        int up = 0;
        for (int i = 0; i < v.length; i += 2) {
            String jl = v[i].split("=")[1];
            double d = Double.parseDouble(jl);
            if (DropUtil.isV(d + (double)up)) {
                v = v[i + 1].split("=")[1].split("&");
                return GameServer.getSkill(v[GameServer.random.nextInt(v.length)]);
            }
            up = (int)((double)up + d);
        }
        v = v[1].split("=")[1].split("&");
        return GameServer.getSkill(v[GameServer.random.nextInt(v.length)]);
    }
    
    public static String chongfu(Skill skill, RoleSummoning pet, List<String> lists, boolean l) {
        String skillID = skill.getSkillid() + "";
        if (lists.contains(skillID)) {
            return "召唤兽已经学过" + skill.getSkillname();
        }
        int Id = skill.getSkillid();
        if ((Id >= 1606 && Id <= 1608) || (Id >= 1828 && Id <= 1830) || (Id >= 1840 && Id <= 1842) || (Id >= 1867 && Id <= 1869) || Id == 3034) {
            String HaveSkills =pet.getPetSkills();
            if (HaveSkills != null&&!HaveSkills.equals("")) {
                int num = 0;
                String[] ss = HaveSkills.split("\\|");
                for (int i = 0; i < ss.length; i++) {
                    int Idd =Integer.parseInt(ss[i]);
                    if ((Idd >= 1606 && Idd <= 1608) || (Idd >= 1828 && Idd <= 1830) || (Idd >= 1840 && Idd <= 1842) || (Idd >= 1867 && Idd <= 1869) || Idd == 3034) {
                        num++;
                    }
                }
                if (num >= 5) {
                    return "领悟失败，召唤兽已经拥有5个终极技能了！";
                }
            }

        }


        if (skill.getSkillralation() != null && !skill.getSkillralation().equals("")) {
            int lvl = skill.getSkilllevel();
            String[] chongtu = skill.getSkillralation().split("\\|");
            for (int i = 0; i < chongtu.length; ++i) {
                if (!chongtu[i].equals(skillID) && lists.contains(chongtu[i])) {
                    Skill skill2 = (Skill)GameServer.getGetSkill().get(chongtu[i]);
                    if (skill2 != null) {
                        int lvl2 = skill2.getSkilllevel();
                        if (l) {
                            if (lvl < lvl2) {
                                return "不能拥有同类型的更高级技能";
                            }
                            if (pet.getPetSkilllock() != null && pet.getPetSkilllock().contains(chongtu[i])) {
                                return "技能已被锁定保护！";
                            }
                            lists.remove(chongtu[i]);
                        }
                        else {
                            return "";
                        }
                    }
                }
            }
        }
        int id = skill.getSkillid();
        if (id == 1820) {
            if (Integer.parseInt(pet.getGold()) < 50 && !lists.contains("1815")) {
                return "你的召唤兽金属性不足50";
            }
        }
        else if (id == 1821) {
            if (Integer.parseInt(pet.getWood()) < 50 && !lists.contains("1816")) {
                return "你的召唤兽木属性不足50";
            }
        }
        else if (id == 1822) {
            if (Integer.parseInt(pet.getSoil()) < 50 && !lists.contains("1819")) {
                return "你的召唤兽土属性不足50";
            }
        }
        else if (id == 1823) {
            if (Integer.parseInt(pet.getWater()) < 50 && !lists.contains("1817")) {
                return "你的召唤兽水属性不足50";
            }
        }
        else if (id == 1824) {
            if (Integer.parseInt(pet.getFire()) < 50 && !lists.contains("1818")) {
                return "你的召唤兽火属性不足50";
            }
        }
        else if (id == 1825) {
            if (Integer.parseInt(pet.getWood()) < 50 && !lists.contains("1816")) {
                return "你的召唤兽木属性不足50";
            }
        }
        else if (id == 1826) {
            if (Integer.parseInt(pet.getFire()) < 50 && !lists.contains("1818")) {
                return "你的召唤兽火属性不足50";
            }
        }
        else if (id == 1827) {
            if (Integer.parseInt(pet.getWater()) < 50 && !lists.contains("1817")) {
                return "你的召唤兽水属性不足50";
            }
        }
        else if (id == 1246) {
            if (pet.getTurnRount() < 4) {
                return "你的召唤兽未飞升";
            }
            if ((int)pet.getSpir() < 500) {
                return "你的召唤兽灵性不足500";
            }
        }
        else if (id == 1247) {
            if (pet.getTurnRount() < 4) {
                return "你的召唤兽未飞升";
            }
            if ((int)pet.getBone() < 500) {
                return "你的召唤兽根骨不足500";
            }
        }
        else if (id == 1248) {
            if (pet.getTurnRount() < 4) {
                return "你的召唤兽未飞升";
            }
            if ((int)pet.getPower() < 500) {
                return "你的召唤兽力量不足500";
            }
        }
        else if (id == 1249) {
            if (pet.getTurnRount() < 4) {
                return "你的召唤兽未飞升";
            }
            if ((int)pet.getSpeed() < 500) {
                return "你的召唤兽敏捷不足500";
            }
        }
        else if (id == 1839) {
            if (Integer.parseInt(pet.getSoil()) < 50 && !lists.contains("1819")) {
                return "你的召唤兽土属性不足50";
            }
            if ((int)pet.getBone() < 450) {
                return "你的召唤兽根骨不足450";
            }
        }
        else if (id == 1831 || id == 1833) {
            if ((int)pet.getPower() < 450) {
                return "你的召唤兽力量不足450";
            }
        }
        else if (id == 1832 && (int)pet.getPower() < 450) {
            return "你的召唤兽力量不足450";
        }
        if (pet.getPetSkillswl() != null && !pet.getPetSkillswl().equals("")) {
            if (id == 1600) {
                if (pet.getPetSkillswl().indexOf("1602") != -1 || pet.getPetSkillswl().indexOf("1603") != -1 || pet.getPetSkillswl().indexOf("1604") != -1 || pet.getPetSkillswl().indexOf("1605") != -1 || pet.getPetSkillswl().indexOf("1601") != -1) {
                    return "已开启已开启悟灵技能替换失败";
                }
            }
            else if (id == 1601) {
                if (pet.getPetSkillswl().indexOf("1600") != -1 || pet.getPetSkillswl().indexOf("1602") != -1 || pet.getPetSkillswl().indexOf("1603") != -1 || pet.getPetSkillswl().indexOf("1604") != -1 || pet.getPetSkillswl().indexOf("1605") != -1) {
                    return "已开启已开启悟灵技能替换失败";
                }
            }
            else if (id == 1602) {
                if (pet.getPetSkillswl().indexOf("1600") != -1 || pet.getPetSkillswl().indexOf("1603") != -1 || pet.getPetSkillswl().indexOf("1604") != -1 || pet.getPetSkillswl().indexOf("1605") != -1 || pet.getPetSkillswl().indexOf("1601") != -1) {
                    return "已开启已开启悟灵技能替换失败";
                }
            }
            else if (id == 1603) {
                if (pet.getPetSkillswl().indexOf("1600") != -1 || pet.getPetSkillswl().indexOf("1602") != -1 || pet.getPetSkillswl().indexOf("1604") != -1 || pet.getPetSkillswl().indexOf("1605") != -1 || pet.getPetSkillswl().indexOf("1601") != -1) {
                    return "已开启已开启悟灵技能替换失败";
                }
            }
            else if (id == 1604) {
                if (pet.getPetSkillswl().indexOf("1600") != -1 || pet.getPetSkillswl().indexOf("1602") != -1 || pet.getPetSkillswl().indexOf("1603") != -1 || pet.getPetSkillswl().indexOf("1605") != -1 || pet.getPetSkillswl().indexOf("1601") != -1) {
                    return "已开启已开启悟灵技能替换失败";
                }
            }
            else if (id == 1605) {
                if (pet.getPetSkillswl().indexOf("1600") != -1 || pet.getPetSkillswl().indexOf("1602") != -1 || pet.getPetSkillswl().indexOf("1603") != -1 || pet.getPetSkillswl().indexOf("1604") != -1 || pet.getPetSkillswl().indexOf("1601") != -1) {
                    return "已开启已开启悟灵技能替换失败";
                }
            }
            else if (id == 1611) {
                if (pet.getPetSkillswl().indexOf(id) != -1) {
                    return "已开启悟灵技能替换失败";
                }
            }
            else if (id == 1612) {
                if (pet.getPetSkillswl().indexOf(id) != -1) {
                    return "已开启悟灵技能替换失败";
                }
            }
            else if (id == 1831) {
                if (pet.getPetSkillswl().indexOf("1831") != -1 || pet.getPetSkillswl().indexOf("1833") != -1) {
                    return "已开启悟灵技能替换失败";
                }
            }
            else if (id == 1834) {
                if (pet.getPetSkillswl().indexOf("1834") != -1 || pet.getPetSkillswl().indexOf("1836") != -1) {
                    return "已开启悟灵技能替换失败";
                }
            }
            else if (id == 1835) {
                if (pet.getPetSkillswl().indexOf(id) != -1) {
                    return "已开启悟灵技能替换失败";
                }
            }
            else if (id == 1836) {
                if (pet.getPetSkillswl().indexOf("1834") != -1 || pet.getPetSkillswl().indexOf("1836") != -1) {
                    return "已开启悟灵技能替换失败";
                }
            }
            else if (id == 1833) {
                if (pet.getPetSkillswl().indexOf("1831") != -1 || pet.getPetSkillswl().indexOf("1833") != -1) {
                    return "已开启悟灵技能替换失败";
                }
            }
            else if (id == 1871) {
                if (pet.getPetSkillswl().indexOf(id) != -1) {
                    return "已开启悟灵技能替换失败";
                }
            }
            else if (id == 1872) {
                if (pet.getPetSkillswl().indexOf(id) != -1) {
                    return "已开启悟灵技能替换失败";
                }
            }
            else if (id == 1880) {
                if (pet.getPetSkillswl().indexOf(id) != -1) {
                    return "已开启悟灵技能替换失败";
                }
            }
            else if (id == 1838 && pet.getPetSkillswl().indexOf(id) != -1) {
                return "已开启悟灵技能替换失败";
            }
        }
        return null;
    }
    
    public static String skillData(List<String> skills) {
        Boolean b = Boolean.valueOf(false);
        Boolean b2 = Boolean.valueOf(false);
        String skilldata = null;
        for (int i = 0; i < skills.size(); ++i) {
            String s = (String)skills.get(i);
            int n = -1;
            switch (s.hashCode()) {
                case 1515111: {
                    if (s.equals("1800")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515178: {
                    if (s.equals("1825")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515112: {
                    if (s.equals("1801")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515113: {
                    if (s.equals("1802")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515179: {
                    if (s.equals("1826")) {
                        n = 4;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515144: {
                    if (s.equals("1812")) {
                        n = 5;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515146: {
                    if (s.equals("1814")) {
                        n = 6;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515361: {
                    if (s.equals("1882")) {
                        n = 7;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515362: {
                    if (s.equals("1883")) {
                        n = 8;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515363: {
                    if (s.equals("1884")) {
                        n = 9;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515364: {
                    if (s.equals("1885")) {
                        n = 10;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515114: {
                    if (s.equals("1803")) {
                        n = 11;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515180: {
                    if (s.equals("1827")) {
                        n = 12;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0:
                case 1: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "HP=27000", 11);
                    break;
                }
                case 2: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "MP=27000", 11);
                    break;
                }
                case 3:
                case 4: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "AP=11000", 11);
                    break;
                }
                case 5: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "SP=-170", 11);
                    break;
                }
                case 6: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "SP=250", 11);
                    b2 = Boolean.valueOf(true);
                    break;
                }
                case 7: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "HP=32000", 11);
                    break;
                }
                case 8: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "MP=32000", 11);
                    break;
                }
                case 9: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "AP=15000", 11);
                    break;
                }
                case 10: {
                    if (!(boolean)b2) {
                        skilldata = DrawnitemsAction.Splice(skilldata, "SP=200", 11);
                        b2 = Boolean.valueOf(true);
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 11:
                case 12: {
                    if (!(boolean)b && !(boolean)b2) {
                        skilldata = DrawnitemsAction.Splice(skilldata, "SP=170", 11);
                        b = Boolean.valueOf(true);
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return skilldata;
    }
    
    public static void getskills(List<String> skills, String petskill) {
        if (petskill == null || petskill.equals("")) {
            return;
        }
        String[] v = petskill.split("\\|");
        for (int i = 0; i < v.length; ++i) {
            skills.add(v[i]);
        }
    }
    
    public void XXPet(ChannelHandlerContext ctx, LoginResult login, String[] vs) {
        long id = Long.parseLong(vs[1]);
        NPCDialogBean bean = (NPCDialogBean)UsePetAction.maps.remove(Long.valueOf(id));
        if (bean == null) {
            return;
        }
        RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(bean.getOId());
        if (pet == null) {
            return;
        }
        if (pet.getRoleid().compareTo(login.getRole_id()) != 0) {
            return;
        }
        int type = Integer.parseInt(vs[2]);
        if (type == 0) {
            pet.setHp(pet.getHp() + bean.getValue());
        }
        else if (type == 1) {
            pet.setMp(pet.getMp() + bean.getValue());
        }
        else if (type == 2) {
            pet.setAp(pet.getAp() + bean.getValue());
        }
        else if (type == 3) {
            pet.setSp(pet.getSp() + bean.getValue());
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        switch (bean.getType()) {
            case 1: {
                pet.setRevealNum(pet.getRevealNum() + 1);
                assetUpdate.setMsg("您的神兽 " + pet.getSummoningname() + "飞升成功!!!");
                break;
            }
        }
        pet.setBasishp(0);
        pet.setBasismp(0);
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.setPet(pet);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void useGood(Goodstable good, int sum) {
        good.goodxh(sum);
        AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(9));
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
    }
    
    public static void otherPetId(RoleSummoning pet, int flag) {
    }
    
    private static boolean isSkillOK1(ChannelHandlerContext ctx, RoleSummoning pet, String skillId) {
        int sum = getZJSum(pet, skillId);
        if (sum > 0) {
            LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            if (loginResult.getMoney().compareTo(Integer.valueOf(sum)) < 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("删除该技能需要" + sum + "积分！！！"));
                return false;
            }
            loginResult.setMoney(Integer.valueOf((int)loginResult.getMoney() - sum));
            MonitorUtil.getMoney().useC((long)sum);
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate.updata("C=" + -sum);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        return true;
    }
    
    private static boolean isSkillOK(ChannelHandlerContext ctx, RoleSummoning pet, String skillId, int type) {
        int sum = (type == 0) ? getZJSum(pet, skillId) : getXHSum(pet, skillId);
        if (sum > 0) {
            List<Goodstable> goodsList = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(pet.getRoleid(), BigDecimal.valueOf(91007L));
            if (goodsList.size() <= 0 || (int)((Goodstable)goodsList.get(0)).getUsetime() < sum) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(((type == 0) ? "删除" : "重修") + "该技能需要" + sum + "个终极重修丹！！！"));
                return false;
            }
            ((Goodstable)goodsList.get(0)).setUsetime(Integer.valueOf((int)((Goodstable)goodsList.get(0)).getUsetime() - sum));
            AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)goodsList.get(0));
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate.updata("G" + ((Goodstable)goodsList.get(0)).getRgid() + "=" + ((Goodstable)goodsList.get(0)).getUsetime());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        return true;
    }
    
    public static int getXHSum(RoleSummoning pet, String skillId) {
        int sum = 1;
        int zj = isZJ(pet, skillId);
        switch (zj) {
            case 2: {
                sum = 1000;
                break;
            }
            case 3: {
                sum = 2000;
                break;
            }
            case 4: {
                sum = 3000;
                break;
            }
            case 5: {
                sum = 4000;
                break;
            }
        }
        return sum;
    }
    
    public static int getZJSum(RoleSummoning pet, String skillId) {
        int sum = 0;
        int zj = isZJ(pet, skillId);
        switch (zj) {
            case 2: {
                sum = 1;
                break;
            }
            case 3: {
                sum = 5;
                break;
            }
            case 4: {
                sum = 10;
                break;
            }
            case 5: {
                sum = 20;
                break;
            }
            case 6: {
                sum = 20;
                break;
            }
            case 7: {
                sum = 20;
                break;
            }
            case 8: {
                sum = 20;
                break;
            }
        }
        return sum;
    }
    
    private static int isZJ(RoleSummoning pet, String skillId) {
        int num = 0;
        List<String> ids = Arrays.asList(new String[] { "1606", "1607", "1608", "1828", "1829", "1830", "1840", "1841", "1842", "1867", "1868", "1869" });
        if (ids.contains(skillId) && StringUtils.isNotBlank(pet.getPetSkills())) {
            String[] skills = pet.getPetSkills().split("\\|");
            for (int j = 0; j < skills.length; ++j) {
                if (ids.contains(skills[j])) {
                    ++num;
                }
            }
        }
        return num;
    }
    
    public static int addPetSkill1(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        int sum = (int)pet.getOpenSeal();
        Skill skill = null;
        if (good.getType() == 2326L) {
            String skillName = good.getValue().split("\\|")[0].split("=")[1];
            skill = GameServer.getSkill(skillName);
        }
        else {
            skill = skillid(good.getValue());
        }
        if (skill == null) {
            return sum;
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
            return sum;
        }
        String value = chongfu(skill, pet, skills, true);
        if (value != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(value));
            return 4;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        useGood(good, 1);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        skills.add(skill.getSkillid() + "");
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < skills.size(); ++j) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append((String)skills.get(j));
        }
        pet.setPetSkills(buffer.toString());
        getskills(skills, pet.getSkill());
        getskills(skills, pet.getBeastSkills());
        pet.setSkillData(skillData(skills));
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        assetUpdate.setMsg("你的召唤兽学会了" + skill.getSkillname());
        int Id = skill.getSkillid();
        if ((Id >= 1606 && Id <= 1608) || (Id >= 1828 && Id <= 1830) || (Id >= 1840 && Id <= 1842) || (Id >= 1867 && Id <= 1869) || Id == 3034) {
            assetUpdate.updata("T悟技");
            SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "终级");
        }
        else if ((Id >= 1815 && Id <= 1827) || (Id >= 1600 && Id <= 1605) || (Id >= 1834 && Id <= 1839) || (Id >= 1882 && Id <= 1888) || (Id >= 1611 && Id <= 1612) || Id == 1850 || Id == 1852 || Id == 1854 || Id == 1858 || Id == 1860 || Id == 1862 || Id == 1864 || Id == 1865 || Id == 1811 || Id == 1831 || Id == 1833 || (Id >= 1871 && Id <= 1880)) {
            assetUpdate.updata("T悟技");
            SuitMixdeal.JN(login.getRolename(), pet.getSummoningname(), skill.getSkillname(), "高级");
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        return sum;
    }
    
    public void openSkillSeal2(RoleSummoning pet, ChannelHandlerContext ctx, LoginResult login) {
        if ((int)pet.getOpenSeal() >= 3) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("只可以解封3个技能格!"));
            return;
        }
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(2));
        BigDecimal xhjf = new BigDecimal(configure.getZqjnsx());
        Integer openSeal = Integer.valueOf((int)pet.getOpenSeal() + 1);
        xhjf = xhjf.multiply(new BigDecimal((int)openSeal));
        BigDecimal dgjf = login.getScoretype("地宫积分");
        if (dgjf.longValue() < xhjf.longValue()) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("没有足够的地宫积分!"));
            return;
        }
        login.setScore(DrawnitemsAction.Splice(login.getScore(), "地宫积分=" + xhjf, 3));
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setData("地宫积分=" + -xhjf.longValue());
        assetUpdate.setType(AssetUpdate.USEGOOD);
        pet.setOpenSeal(Integer.valueOf((int)pet.getOpenSeal() + 1));
        assetUpdate.updata("T格子");
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
        assetUpdate.setPet(pet);
        StringBuffer sb = new StringBuffer();
        sb.append("#G");
        sb.append(login.getRolename());
        sb.append("#c00FFFF使用#R地宫积分#c00FFFF成功对其召唤兽");
        sb.append("#G");
        sb.append(pet.getSummoningname());
        sb.append("#c00FFFF");
        sb.append("开启一个新技能栏！#50");
        SuitMixdeal.jpdC(sb.toString());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void openSkillSea1l(RoleSummoning pet, Goodstable good, ChannelHandlerContext ctx, LoginResult login) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        int skllNum = Integer.parseInt(configure.getZhsjngs());
        int flag = 6;
        if (pet.getTurnRount() == 4) {
            flag = 8;
        }
        else {
            flag = 6;
        }
        if ((int)pet.getFoPenSeal() >= flag) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("召唤兽的技能格子都已解开!"));
            return;
        }
        useGood(good, 1);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        Configure configure2 = (Configure)s.get(Integer.valueOf(8));
        int openLev = Integer.parseInt(configure2.getZqsld());
        if (Battlefield.isV((double)openLev)) {
            pet.setFoPenSeal(Integer.valueOf((int)pet.getFoPenSeal() + 1));
            assetUpdate.updata("T格子");
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            assetUpdate.setPet(pet);
            StringBuffer sb = new StringBuffer();
            sb.append("#W鸿运当头");
            sb.append("#R");
            sb.append(login.getRolename());
            sb.append("#W使用聚魄丹时意外的让自己的");
            sb.append("#R");
            sb.append(pet.getSummoningname());
            sb.append("#W获得一个");
            sb.append("#G新技能栏。");
            SuitMixdeal.jpdC(sb.toString());
        }
        else {
            StringBuffer sb = new StringBuffer();
            sb.append("#W很遗憾，#G");
            sb.append(pet.getSummoningname());
            sb.append("#W召唤兽没有获得新的技能栏。");
            assetUpdate.setMsg(sb.toString());
            assetUpdate.updata("refGZ");
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public static void battleOpenSkillSeal(RoleSummoning pet, ChannelHandlerContext ctx, LoginResult login) {
        Boolean b = Boolean.valueOf(true);
        if ((int)pet.getFoPenSeal() > (int)pet.getOpenSeal()) {
            b = Boolean.valueOf(false);
        }
        if ((int)pet.getFoPenSeal() - 1 != (int)pet.getOpenSeal() && (boolean)b) {
            return;
        }
        int flag = 6;
        if (pet.getTurnRount() == 4) {
            flag = 8;
        }
        if ((int)pet.getOpenSeal() >= flag) {
            return;
        }
        int sum = 1;
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(8));
        String zqjnsx = configure.getZqjnsx();
        if (DropUtil.isV(Double.parseDouble(zqjnsx))) {
            pet.setOpenSeal(Integer.valueOf((int)pet.getOpenSeal() + 1));
            assetUpdate.updata("T格子");
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            assetUpdate.setPet(pet);
            SuitMixdeal.battleJpd(login.getRolename(), pet.getSummoningname());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    
    public static RoleSummoning lvlPpenSkillSea1l(RoleSummoning pet, ChannelHandlerContext ctx, LoginResult login) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(Integer.valueOf(1));
        int flag = 6;
        if (pet.getTurnRount() == 4) {
            flag = 8;
        }
        else {
            flag = 6;
        }
        if ((int)pet.getFoPenSeal() >= flag) {
            return null;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        Configure configure2 = (Configure)s.get(Integer.valueOf(8));
        configure = (Configure)s.get(Integer.valueOf(8));
        String zqjnsx = configure.getJumpurl();
        if (Battlefield.isV(Double.parseDouble(zqjnsx))) {
            pet.setFoPenSeal(Integer.valueOf((int)pet.getFoPenSeal() + 1));
            assetUpdate.updata("T格子");
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            StringBuffer sb = new StringBuffer();
            sb.append("#Y");
            sb.append(login.getRolename());
            sb.append("#W携带的");
            sb.append("#Y");
            sb.append(pet.getSummoningname());
            sb.append("#W升级时开启了第#R");
            sb.append(pet.getFoPenSeal());
            sb.append("#W个技能栏封印。#50");
            SuitMixdeal.jpdC(sb.toString());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        return pet;
    }
    
    static {
        UsePetAction.highSkill = new int[] { 1811, 1600, 1601, 1602, 1603, 1604, 1605, 1611, 1612, 1815, 1816, 1817, 1818, 1819, 1820, 1821, 1822, 1823, 1824, 1825, 1826, 1827, 1831, 1833, 1834, 1835, 1836, 1837, 1838, 1839, 1850, 1852, 1854, 1858, 1860, 1862, 1864, 1865, 1871, 1872, 1873, 1874, 1875, 1876, 1877, 1878, 1880, 1882, 1883, 1884, 1885, 1887, 1888 };
        UsePetAction.TrainSkill = new int[] { 3034, 1840, 1841, 1842, 3034, 3034, 3034, 1606, 3034, 1607, 1608, 3034, 1828, 1829, 3034, 1830, 1867, 3034, 1868, 1869, 3034, 3034, 3034, 1883, 1884, 1885, 1887, 1888 };
        UsePetAction.normalSkill = new int[] { 1800, 1801, 1802, 1803, 1804, 1805, 1806, 1808, 1810, 1812, 1832, 1843, 1844, 1845, 1846, 1847, 1848, 1849, 1851, 1853, 1855, 1856, 1857, 1861, 1863, 1879 };
        UsePetAction.maps = new ConcurrentHashMap<>();
    }
    public void linguaG(RoleSummoning pet, Goodstable good2, ChannelHandlerContext ctx, LoginResult login, String message) {
        String[] vs = message.split("\\|");
        Skill skill = GameServer.getSkill(vs[2]);
        Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[0]));
        String[] dlt = new String[]{"低灵藤", "中灵藤"};
        String ltg = "高灵藤";
        Random random = new Random(System.currentTimeMillis());
        String skin = dlt[random.nextInt(dlt.length)];
        String train = DropUtil.isV(95.0) ? skin : ltg;
        if (train.equals("高灵藤")) {
            good.setType(8002L);
            good.setGoodsid(new BigDecimal(66602));
            good.setSkin("ltg");
            good.setInstruction("召唤兽使用后，概率提升对应技能的悟灵灵阶。当前灵阶7-9阶。");
            good.setUsetime(1);
        } else if (train.equals("中灵藤")) {
            good.setType(8002L);
            good.setGoodsid(new BigDecimal(66601));
            good.setSkin("ltz");
            good.setInstruction("召唤兽使用后，概率提升对应技能的悟灵灵阶。当前灵阶4-6阶。");
            good.setUsetime(1);
        } else if (train.equals("低灵藤")) {
            good.setType(8002L);
            good.setGoodsid(new BigDecimal(66600));
            good.setSkin("ltd");
            good.setInstruction("召唤兽使用后，概率提升对应技能的悟灵灵阶。当前灵阶0-3阶。");
            good.setUsetime(1);
        }
        good.setQuality(0l);
        good.setGoodsname(train);
        String instru = "技能=" + skill.getSkillname();
        good.setValue(instru);
        useGood(good2, 1);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        AllServiceUtil.getGoodsTableService().insertGoods(good);
        assetUpdate.setGood(good);
        assetUpdate.updata("G" + good2.getRgid() + "=" + good2.getUsetime());
        assetUpdate.setMsg("#Y你获得了 " + train + "。");
        AllServiceUtil.getGoodsrecordService().insert(good, (BigDecimal) null, 1, 9);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
}
