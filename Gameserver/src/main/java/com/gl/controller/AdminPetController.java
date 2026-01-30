package com.gl.controller;

import java.util.Set;
import java.util.HashSet;
import org.apache.commons.collections.CollectionUtils;
import org.come.entity.Baby;
import org.come.entity.Mount;
import org.come.entity.Lingbao;
import org.springframework.context.ApplicationContext;
import org.come.mapper.RoleTableMapper;
import org.come.until.MybatisUntil;
import org.come.entity.Wechatrecord;
import com.github.pagehelper.PageInfo;
import com.gl.service.PlayerService;
import org.come.redis.RedisControl;
import com.gl.model.Param;
import org.springframework.web.bind.annotation.PostMapping;
import com.gl.token.UserToken;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import org.come.bean.LoginResult;
import org.come.entity.RoleSummoning;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Stall.AssetUpdate;
import io.netty.channel.ChannelHandlerContext;
import cn.hutool.core.util.ArrayUtil;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.come.action.reward.DrawnitemsAction;
import java.util.function.Function;
import org.come.model.Skill;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import cn.hutool.core.text.CharSequenceUtil;
import java.util.Objects;
import org.come.tool.Arith;
import come.tool.Good.UsePetAction;
import org.come.server.GameServer;
import com.gl.service.ResultFactory;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import cn.hutool.core.util.URLUtil;
import com.gl.model.Result;
import javax.servlet.http.HttpServletRequest;
import com.gl.model.UpPetParam;
import org.come.entity.Goodstable;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminPetController
{
    private ConcurrentHashMap<String, Goodstable> nds;
    
    public AdminPetController() {
        this.nds = new ConcurrentHashMap<>();
    }
    
    @UserToken
    @PostMapping({ "/api/cb/updUserPet" })
    public Result cbUpdUserPet(UpPetParam param, HttpServletRequest request) throws UnsupportedEncodingException {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        String ndsParam = URLUtil.decode(param.getNds());
        String jineng = URLUtil.decode(param.getJineng());
        param.setJineng(jineng);
        String qljineng = URLUtil.decode(param.getQljineng());
        param.setQljineng(qljineng);
        param.setNds(ndsParam);
        RoleSummoning roleSummoning = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(param.getSid()));
        if (roleSummoning == null) {
            return ResultFactory.fail("未找到对应的召唤兽！");
        }
        roleSummoning.setGrowlevel(param.getGrowlevel());
        RoleSummoning pet = GameServer.getPet(new BigDecimal(roleSummoning.getSummoningid()));
        roleSummoning.setTurnRount(param.getTurnRount());
        if (pet.getGrowlevel().equals(param.getGrowlevel())) {
            for (int i = 0; i < param.getTurnRount(); ++i) {
                BigDecimal grow = UsePetAction.mathDouble(Double.parseDouble(roleSummoning.getGrowlevel()), 0.1);
                roleSummoning.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));
            }
        }
        else {
            roleSummoning.setGrowlevel(param.getGrowlevel());// 设置成长等级
        }
        roleSummoning.setOpenSeal(Objects.isNull(param.getOpenSeal()) ? 1 : ((param.getOpenSeal() > 15) ? 15 : param.getOpenSeal()));
        roleSummoning.setOpenql(Objects.isNull(param.getOpenql()) ? 1 : ((param.getOpenql() > 6) ? 6 : param.getOpenql()));
        if (CharSequenceUtil.isNotBlank(param.getJineng())) {
            int count = 0;
            if (CharSequenceUtil.isNotBlank(roleSummoning.getPetSkills())) {
                count = ((List)Arrays.stream(roleSummoning.getPetSkills().split("\\|")).collect(Collectors.toList())).size();
            }
            Integer openSeal = roleSummoning.getOpenSeal();
            if ((int)openSeal > count) {
                ConcurrentHashMap<String, Skill> getSkill = GameServer.getGetSkill();
                Map<String, Skill> collect = (Map)getSkill.values().stream().collect(Collectors.toMap(Skill::getSkillname, Function.identity(), (key, key1)/* org.come.model.Skill,org.come.model.Skill, */ -> key1));
                Skill skill = (Skill)collect.get(param.getJineng());
                if (Objects.nonNull(skill)) {
                    if (CharSequenceUtil.isNotBlank(roleSummoning.getPetSkills())) {
                        boolean contains = roleSummoning.getPetSkills().contains(skill.getSkillid() + "");
                        if (!contains) {
                            String concat = roleSummoning.getPetSkills().concat("|").concat(skill.getSkillid() + "");
                            roleSummoning.setPetSkills(concat);
                        }
                    }
                    else {
                        String concat2 = skill.getSkillid() + "";
                        roleSummoning.setPetSkills(concat2);
                    }
                }
            }
        }
        if (CharSequenceUtil.isNotBlank(param.getQljineng())) {
            int count = 0;
            if (CharSequenceUtil.isNotBlank(roleSummoning.getPetQlSkills())) {
                count = ((List)Arrays.stream(roleSummoning.getPetQlSkills().split("\\|")).collect(Collectors.toList())).size();
            }
            Integer openSeal = roleSummoning.getOpenql();
            if ((int)openSeal > count) {
                ConcurrentHashMap<String, Skill> getSkill = GameServer.getGetSkill();
                Map<String, Skill> collect = (Map)getSkill.values().stream().collect(Collectors.toMap(Skill::getSkillname, Function.identity(), (key, key1)/* org.come.model.Skill,org.come.model.Skill, */ -> key1));
                Skill skill = (Skill)collect.get(param.getQljineng());
                if (Objects.nonNull(skill)) {
                    if (CharSequenceUtil.isNotBlank(roleSummoning.getPetQlSkills())) {
                        boolean contains = roleSummoning.getPetSkills().contains(skill.getSkillid() + "");
                        if (!contains) {
                            String concat = roleSummoning.getPetQlSkills().concat("|").concat(skill.getSkillid() + "");
                            roleSummoning.setPetQlSkills(concat);
                        }
                    }
                    else {
                        String concat2 = skill.getSkillid() + "";
                        roleSummoning.setPetQlSkills(concat2);
                    }
                }
            }
        }
        roleSummoning.setSkill(param.getSkill());
        Integer petLvl = this.getPetLvl(param.getTurnRount());
        roleSummoning.setFriendliness(param.getFriendliness());
        roleSummoning.setGrade(param.getGrade() + (int)petLvl + 1);
        roleSummoning.setBone(param.getGrade());
        roleSummoning.setSpir(param.getGrade());
        roleSummoning.setPower(param.getGrade());
        roleSummoning.setSpeed(param.getGrade());
        roleSummoning.setFlyupNum(param.getFlyupNum());
        if (param.getCzjjd() > 0 && param.getCzjjd() != roleSummoning.getCzjjd()) {
            BigDecimal grow = UsePetAction.mathDouble(Double.parseDouble(roleSummoning.getGrowlevel()), (double)param.getCzjjd() * 0.001);
            roleSummoning.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));
            roleSummoning.setCzjjd(param.getCzjjd());
        }
        if (param.getDragon() > 0 && param.getDragon() != roleSummoning.getDragon()) {
            roleSummoning.setDragon(param.getDragon());
            roleSummoning.setGrowlevel(UsePetAction.mathDouble(Double.parseDouble(roleSummoning.getGrowlevel()), (double)param.getDragon() * 0.01).toString());// 成长率加0.01
            for (int lgcha = param.getDragon() - roleSummoning.getDragon(), j = 0; j < lgcha; ++j) {
                String four;// 随机给这只召唤兽的hp、mp、ap、sp随机加6点
                int ran1;
                int ran2;
                int ran3;
                int ran4;
                do {
                    four = roleSummoning.getFourattributes();
                    ran1 = GameServer.random.nextInt(7);
                    ran2 = GameServer.random.nextInt(7);
                    ran3 = GameServer.random.nextInt(7);
                    ran4 = GameServer.random.nextInt(7);
                } while (ran1 + ran2 + ran3 + ran4 != 6);
                roleSummoning.setHp(roleSummoning.getHp() + ran1);
                roleSummoning.setMp(roleSummoning.getMp() + ran2);
                roleSummoning.setAp(roleSummoning.getAp() + ran3);
                roleSummoning.setSp(roleSummoning.getSp() + ran4);
                if (ran1 != 0) {
                    four = DrawnitemsAction.Splice(four, "hp=" + ran1, 2);
                }
                if (ran2 != 0) {
                    four = DrawnitemsAction.Splice(four, "mp=" + ran2, 2);
                }
                if (ran3 != 0) {
                    four = DrawnitemsAction.Splice(four, "ap=" + ran3, 2);
                }
                if (ran4 != 0) {
                    four = DrawnitemsAction.Splice(four, "sp=" + ran4, 2);
                }
                roleSummoning.setFourattributes(four);
            }
        }
        if (param.getSpdragon() > 0 && param.getSpdragon() != roleSummoning.getSpdragon()) {
            roleSummoning.setSpdragon(param.getSpdragon());
            roleSummoning.setGrowlevel(UsePetAction.mathDouble(Double.parseDouble(pet.getGrowlevel()), (double)param.getSpdragon() * 0.01).toString());
            for (int lgcha = param.getSpdragon() - roleSummoning.getSpdragon(), j = 0; j < lgcha; ++j) {
                String four;
                int ran1;
                int ran2;
                int ran3;
                int ran4;
                do {
                    four = roleSummoning.getFourattributes();
                    ran1 = GameServer.random.nextInt(7);
                    ran2 = GameServer.random.nextInt(7);
                    ran3 = GameServer.random.nextInt(7);
                    ran4 = GameServer.random.nextInt(7);
                } while (ran1 + ran2 + ran3 + ran4 != 10);
                roleSummoning.setHp(roleSummoning.getHp() + ran1);
                roleSummoning.setMp(roleSummoning.getMp() + ran2);
                roleSummoning.setAp(roleSummoning.getAp() + ran3);
                roleSummoning.setSp(roleSummoning.getSp() + ran4);
                if (ran1 != 0) {
                    four = DrawnitemsAction.Splice(four, "hp=" + ran1, 2);
                }
                if (ran2 != 0) {
                    four = DrawnitemsAction.Splice(four, "mp=" + ran2, 2);
                }
                if (ran3 != 0) {
                    four = DrawnitemsAction.Splice(four, "ap=" + ran3, 2);
                }
                if (ran4 != 0) {
                    four = DrawnitemsAction.Splice(four, "sp=" + ran4, 2);
                }
                roleSummoning.setFourattributes(four);
            }
        }
        List<Goodstable> eqGoods = null;
        LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(roleSummoning.getRoleid());
        if (StringUtils.isNotBlank(param.getSkill())) {
            if (StringUtils.isNotBlank(roleSummoning.getStye())) {
                eqGoods = new ArrayList<>();
                String[]  v = roleSummoning.getSkill().split("\\|");
                for (int k = 1; k < v.length; ++k) {
                    String[] vs = v[k].split("-");
                    if (vs.length >= 2) {
                        Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[1]));
                        eqGoods.add(good);
                    }
                }
            }
            for (Goodstable eqGood : eqGoods) {
                String[] val = eqGood.getValue().split("\\|");
                int index = -1;
                int l = 0;
                while (l < val.length) {
                    if (val[l].startsWith("觉醒技")) {
                        index = l;
                        break;
                    }
                    else {
                        ++l;
                    }
                }
                String jxSkill = "";
                if (index != -1) {
                    String[] split = val[index].split("&");
                    split[1] = param.getSkill();
                    jxSkill = ArrayUtil.join(split, "&");
                }
                val[index] = jxSkill;
                eqGood.setValue(ArrayUtil.join(val, "|"));
                AllServiceUtil.getGoodsTableService().updateGoodRedis(eqGood);
                if (loginResult != null) {
                    ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
                    if (channelHandlerContext != null) {
                        AssetUpdate update = new AssetUpdate();
                        List<Goodstable> goodstables = new ArrayList<>();
                        goodstables.add(eqGood);
                        update.setGoods(goodstables);
                        update.setType(AssetUpdate.GIVE);
                        update.setMsg(":#R多功能后台修改物品修改成功#23");
                        SendMessage.sendMessageToSlef(channelHandlerContext, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(update)));
                    }
                    else {
                        continue;
                    }
                }
            }
        }
        AssetUpdate update2 = new AssetUpdate();
        if (param.getLx() != null) {
            roleSummoning.setLingxi(this.getLx(Integer.valueOf((int)param.getLx() - 1)));
        }
        List<Goodstable> goodstables2 = new ArrayList<>();
        if (StringUtils.isNotBlank(param.getNds())) {
            String[] split2 = param.getNds().split("\\|");
            for (String nd : split2) {
                GameServer.getAllGoodsMap().forEach((k, v)/* java.math.BigDecimal,org.come.entity.Goodstable, */ -> {
                    if (v.getGoodsname().equals(nd)) {
                        String[] split5 = v.getValue().split("\\|");
                        if (split5.length > 2) {
                            this.nds.put(nd, v);
                        }
                    }
                    return;
                });
            }
            if (StringUtils.isNotBlank(roleSummoning.getInnerGoods())) {
                for (String s : roleSummoning.getInnerGoods().split("\\|")) {
                    Goodstable dbGood = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(s));
                    dbGood.goodxh(1);
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(dbGood);
                    goodstables2.add(dbGood);
                }
            }
            String[] ndIds = new String[split2.length];
            for (int l = 0; l < split2.length; ++l) {
                Goodstable goodstable = (Goodstable)this.nds.get(split2[l]);
                String[] split3 = goodstable.getValue().split("\\|");
                split3[2] = "内丹等级=4转180";
                goodstable.setValue(ArrayUtil.join(split3, "|"));
                goodstable.setUsetime(Integer.valueOf(1));
                if (loginResult != null) {
                    goodstable.setRole_id(loginResult.getRole_id());
                }
                goodstable.setStatus(Integer.valueOf(1));
                AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                ndIds[l] = goodstable.getRgid().toString();
                goodstables2.add(goodstable);
            }
            String join = ArrayUtil.join(ndIds, "|");
            roleSummoning.setInnerGoods(join);
            update2.setGoods(goodstables2);
        }
        AllServiceUtil.getRoleSummoningService().updateRoleSummoning(roleSummoning);
        if (loginResult != null) {
            ChannelHandlerContext channelHandlerContext2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
            if (channelHandlerContext2 != null) {
                List<RoleSummoning> roleSummonings = new ArrayList<>();
                roleSummonings.add(roleSummoning);
                update2.setPets(roleSummonings);
                update2.setType(AssetUpdate.USEGOOD);
                update2.setMsg(":#R多功能后台修改宠物成功#23");
                SendMessage.sendMessageToSlef(channelHandlerContext2, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(update2)));
            }
        }
        return ResultFactory.success(null);
    }
    
    public Integer getPetLvl(int zs) {
        if (zs == 1) {
            return 100;
        }
        if (zs == 2) {
            return 221;
        }
        if (zs == 3) {
            return 362;
        }
        if (zs == 4) {
            return 543;
        }
        if (zs == 0) {
            return 0;
        }
        if (zs == 5) {
            return 744;
        }
        if (zs == 6) {
            return 945;
        }
        if (zs == 7) {
            return 1146;
        }
        if (zs == 8) {
            return 1347;
        }
        if (zs == 9) {
            return 1548;
        }
        if (zs == 10) {
            return 1749;
        }
        if (zs == 11) {
            return 1950;
        }
        if (zs == 12) {
            return 2151;
        }
        if (zs == 13) {
            return 2352;
        }
        if (zs == 14) {
            return 2553;
        }
        if (zs == 15) {
            return 2754;
        }
        if (zs == 16) {
            return 2955;
        }
        if (zs == 17) {
            return 3156;
        }
        if (zs == 18) {
            return 3357;
        }
        if (zs == 19) {
            return 3558;
        }
        if (zs == 20) {
            return 3759;
        }
        if (zs == 21) {
            return 3960;
        }
        if (zs == 22) {
            return 4161;
        }
        if (zs == 23) {
            return 4362;
        }
        if (zs == 24) {
            return 4563;
        }
        if (zs == 25) {
            return 4764;
        }
        if (zs == 26) {
            return 4965;
        }
        if (zs == 27) {
            return 5166;
        }
        if (zs == 28) {
            return 5367;
        }
        if (zs == 29) {
            return 5568;
        }
        if (zs == 30) {
            return 5769;
        }
        if (zs == 31) {
            return 5970;
        }
        if (zs == 32) {
            return 6171;
        }
        if (zs == 33) {
            return 6372;
        }
        return 0;
    }
    
    private String getLx(Integer type) {
        String lx = "";
        if ((int)type == 0) {
            lx = "11001_0|11002_0|11003_0|11004_0|11005_0|11006_0|11007_0|11026_0|11045_0|11046_0|11008_0|11009_0|11010_0|11011_0|11012_0|11013_0|11014_0|11015_0|11016_0|11017_0|11018_0|11019_0|11020_0|11021_0|11022_0|11023_0|11024_0|11025_0|11047_0|11049_0|11051_0|11053_0|11055_0|11057_0|11062_0|11063_0|11061_0|11060_0|11058_0|11059_0|11056_0|11054_0|11052_0|11050_0|11048_0|11027_0|11028_0|11029_0|11031_0|11032_0|11033_0|11034_0|11035_0|11036_0|11030_0|11037_0|11042_0|11039_0|11043_0|11044_0|11040_0|11041_0";
        }
        else if ((int)type == 1) {
            lx = "11003_0|11001_0|11004_0|11005_0|11006_0|11007_0|11008_0|11009_0|11010_0|11011_0|11012_0|11013_0|11016_0|11018_0|11013_0|11015_0|11017_0|11019_0|11020_0|11020_0|11021_0|11022_0|11023_0|11024_0|11025_0";
        }
        else if ((int)type == 2) {
            lx = "11001_0|11004_0|11002_0|11005_0|11007_0|11026_0|11027_0|11028_0|11029_0|11031_0|11033_0|11035_0|11036_0|11032_0|11034_0|11030_0|11037_0|11039_0|11040_0|11041_0|11042_0|11043_0|11044_0";
        }
        else if ((int)type == 3) {
            lx = "11001_0|11004_0|11002_0|11005_0|11046_0|11047_0|11048_0|11049_0|11050_0|11052_0|11054_0|11056_0|11051_0|11053_0|11055_0|11057_0|11058_0|11059_0|11060_0|11061_0|11062_0|11063_0";
        }
        String[] lhHead = { "Lx=0", "Lv=0", "Point=0", "Open=" };
        String[] skillIds = lx.split("\\|");
        String[] lxs = new String[skillIds.length];
        int count = 0;
        for (int i = 0; i < skillIds.length; ++i) {
            Skill skill = GameServer.getSkill(skillIds[i].split("_")[0]);
            lxs[i] = skill.getSkillid() + "_" + (int)skill.getValue();
            count += (int)skill.getValue();
        }
        lhHead[2] = "Point=" + count;
        lhHead[0] = "Lx=" + type;
        String join = ArrayUtil.join(lxs, "|");
        String join2 = ArrayUtil.join(lhHead, "&");
        return join2 + join;
    }
    
    @UserToken
    @PostMapping({ "/api/getUserIp" })
    public Result getUserIpLists(Param param) {
        return ResultFactory.success(RedisControl.getUserIpList(param.getPageNum(),param.getPageSize(), param.getRoleName()));
    }
    
    @UserToken
    @PostMapping({ "/api/fengjin/ip" })
    public Result fengJinIp(Param param) {
        RedisControl.fengJinIp(param.getValue1());
        PlayerService playerService = new PlayerService();
        param.setValue2("2");
        String roleName = URLUtil.decode(param.getRoleName());
        param.setValue1(roleName);
        playerService.operation(param);
        return ResultFactory.success("成功！！！");
    }
    
    @UserToken
    @PostMapping({ "/api/jiefeng/ip" })
    public Result jieFengIp(Param param) {
        RedisControl.jieFengIp(param.getValue1());
        return ResultFactory.success("成功！！！");
    }
    
    @UserToken
    @PostMapping({ "/api/fengjin/getUserIp" })
    public Result getfengjinUserIpLists(Param param) {
        return ResultFactory.success(RedisControl.getFengJinIpList(param.getPageNum(), param.getPageSize(), param.getRoleName()));
    }
    
    @UserToken
    @PostMapping({ "/api/selectWeChatrecord" })
    public Result selectWeChatrecord(Param param) {
        String ndsParam = URLUtil.decode(param.getValue2());
        param.setValue2(ndsParam);
        List<Wechatrecord> wechatrecords = AllServiceUtil.getWechatrecordService().selectAll(param);
        PageInfo<Wechatrecord> pageInfo = new PageInfo<>(wechatrecords);
        return ResultFactory.success(pageInfo);
    }
    
    @UserToken
    @PostMapping({ "/api/upGrade" })
    public Result grade(Param param) {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        // id为类名且首字母小写才能被自动扫描扫到
        RoleTableMapper roleTableMapper = (RoleTableMapper)ctx.getBean("roleTableMapper", RoleTableMapper.class);
        BigDecimal roleId = new BigDecimal(param.getValue1());
        roleTableMapper.updateRoleFullGrade(roleId);
        return ResultFactory.success(null);
    }
    
    @UserToken
    @PostMapping({ "/api/fullGrade" })
    public Result fullGrade(Param param) {

        String type = param.getValue3();
        String rolename = param.getValue2();
        BigDecimal roleId = new BigDecimal(param.getValue1());
        if (type != null) {
            int n = -1;
            switch (type.hashCode()) {
                case 76: {
                    if (type.equals("L")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 83: {
                    if (type.equals("S")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 77: {
                    if (type.equals("MMMM")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 66: {
                    if (type.equals("B")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    this.fullLingbao(roleId);
                }
                case 1: {
                    this.zhs(roleId);
                    break;
                }
                case 2: {
                    this.fullMount(roleId);
                    break;
                }
                case 3: {
                    this.fullBaby(roleId);
                    break;
                }
            }
            if (GameServer.getRoleNameMap().get(rolename) != null) {
                SendMessage.sendMessageByRoleName(rolename, Agreement.getAgreement().serverstopAgreement());
            }
        }
        return ResultFactory.success(null);
    }
    
    private void fullLingbao(BigDecimal roleId) {
        List<Lingbao> lingbaos = AllServiceUtil.getLingbaoService().selectLingbaoByRoleID(roleId);
        if (lingbaos.size() > 0) {
            List<Lingbao> collect =lingbaos.stream().filter(f-> !f.getLingbaolvl().equals(new BigDecimal(200))).collect(Collectors.toList());
            for (Lingbao r : collect) {
                r.setLingbaolvl(new BigDecimal(200));
                r.setSkillsum(5);
                r.setFusum(5);
                r.setLingbaoexe(new BigDecimal(491985));
                AllServiceUtil.getLingbaoService().updateLingbaoRedis(r);
                System.out.println("满级灵宝" + r.getBaoname());
            }
        }
    }
    
    private void fullMount(BigDecimal roleId) { // 根据角色ID查询坐骑列表
        List<Mount> mounts = AllServiceUtil.getMountService().selectMountsByRoleID(roleId);
        // 过滤坐骑列表，排除等级为200的坐骑
        List<Mount> collect = mounts.stream().filter(f/* org.come.entity.Mount, */ -> !f.getMountlvl().equals(new BigDecimal(200))).collect(Collectors.toList());
        // 遍历过滤后的坐骑列表
        for (Mount m : collect) {
            // 这里可以添加对每个坐骑的处理逻辑
            if (m.getMountid().equals(1)) {
                m.setMountlvl(200);
                m.setLive(100);
                m.setSpri(12);
                m.setPower(24);
                m.setBone(12);
            }
            if (m.getMountid().equals(2)) {
                m.setMountlvl(200);
                m.setLive(100);
                m.setSpri(12);
                m.setPower(18);
                m.setBone(18);
            }
            if (m.getMountid().equals(3)) {
                m.setMountlvl(200);
                m.setLive(100);
                m.setSpri(24);
                m.setPower(12);
                m.setBone(12);
            }
            if (m.getMountid().equals(4)) {
                m.setMountlvl(200);
                m.setLive(100);
                m.setSpri(18);
                m.setPower(12);
                m.setBone(18);
            }
            if (m.getMountid().equals(5)) {
                m.setMountlvl(200);
                m.setLive(100);
                m.setSpri(12);
                m.setPower(12);
                m.setBone(24);
            }
            if (m.getMountid().equals(6)) {
                m.setMountlvl(200);
                m.setLive(100);
                m.setSpri(3);
                m.setPower(27);
                m.setBone(9);
            }
            if (m.getMountid().equals(7)) {
                m.setMountlvl(200);
                m.setLive(100);
                m.setSpri(16);
                m.setPower(10);
                m.setBone(31);
            }
            m.setExp(1000000);
            m.setProficiency(150000);
            AllServiceUtil.getMountService().updateMount(m);
            System.out.println("满级坐骑" + m.getMountname());
        }
    }
    
    private void fullBaby(BigDecimal roleId) {
        List<Baby> babies = AllServiceUtil.getBabyService().selectBabyByRolename(roleId);
        List<Baby> collect = babies.stream().filter(f/* org.come.entity.Baby, */ -> !f.getBabyage().equals(new BigDecimal(6710))).collect(Collectors.toList());
        for (Baby b : collect) {
            b.setQingmi(400);
            b.setNeili(1000);
            b.setZhili(1000);
            b.setNaili(1000);
            b.setQizhi(1000);
            b.setMingqi(1000);
            b.setDaode(400);
            b.setXiaoxin(400);
            b.setWenbao(400);
            b.setBabyage(6710);
            AllServiceUtil.getBabyService().updateBabyRedis(b);
            System.out.println("满级孩子" + b.getBabyname());
        }
    }
    
    private void zhs(BigDecimal roleId) {
        List<RoleSummoning> roleSummonings = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(roleId);
        if (CollectionUtils.isNotEmpty(roleSummonings)) {
            List<RoleSummoning> collect = roleSummonings.stream().filter(f/* org.come.entity.RoleSummoning, */ -> (int)f.getGrade() != Integer.parseInt("744")).collect(Collectors.toList());
            for (RoleSummoning r : collect) {
                this.mockZs(r, roleId);
            }
        }
    }
    
    private void mockZs(RoleSummoning pet, BigDecimal roleId) {
        int petTurn = pet.getTurnRount();
        //设置这只召唤兽的根骨、灵性、力量、敏捷、经验为0
        pet.setBone(200);
        pet.setSpir(200);
        pet.setPower(200);
        pet.setSpeed(200);
        pet.setCalm(200);
        pet.setExp(new BigDecimal(999999999));

        //等级
        pet.setGrade(744);
        pet.setTurnRount(petTurn);

        //设置忠诚度为100
        pet.setFaithful(100);
        pet.setFriendliness(99999999L);
        pet.setOpenSeal(6);
        int g = 0;
        if (petTurn < 3) {
            g = 3 - petTurn;
        }
        BigDecimal grow = UsePetAction.mathDouble(Double.parseDouble(pet.getGrowlevel()), (double)g * 0.1);
        pet.setGrowlevel(Arith.xiaoshu3(grow.doubleValue()));
        pet.setBasishp(0);
        pet.setBasismp(0);
        String fz = "Lx=3&Lv=0&Point=110&Open=11001_5|11002_0|11003_0|11004_5|11005_5|11006_0|11007_0|11026_0|11045_5|11046_0|11008_0|11009_0|11010_0|11011_0|11012_0|11013_0|11014_0|11015_0|11016_0|11017_0|11018_0|11019_0|11020_0|11021_0|11022_0|11024_0|11023_0|11025_0|11027_0|11028_0|11029_0|11031_0|11032_0|11033_0|11034_0|11035_0|11030_0|11036_0|11037_0|11039_0|11040_0|11041_0|11042_0|11043_0|11044_0|11047_3|11048_3|11049_3|11050_0|11051_3|11052_0|11053_3|11054_0|11056_0|11057_4|11058_0|11059_0|11055_3|11060_0|11061_30|11062_30|11063_0";
        String fs = "Lx=2&Lv=0&Point=110&Open=11001_4|11002_5|11003_0|11004_5|11005_0|11006_0|11007_5|11026_5|11045_0|11046_0|11008_0|11009_0|11010_0|11011_0|11012_0|11013_0|11014_0|11015_0|11016_0|11017_0|11018_0|11019_0|11020_0|11021_0|11022_0|11024_0|11023_0|11025_0|11027_3|11028_3|11029_3|11031_3|11032_0|11033_3|11034_0|11035_3|11030_0|11036_4|11037_0|11039_0|11040_0|11041_0|11042_30|11043_30|11044_0|11047_0|11048_0|11049_0|11050_0|11051_0|11052_0|11053_0|11054_0|11056_0|11057_0|11058_0|11059_0|11055_0|11060_0|11061_0|11062_0|11063_0";
        String gj = "Lx=1&Lv=0&Point=110&Open=11001_5|11002_0|11003_5|11004_5|11005_5|11006_5|11007_5|11026_0|11045_0|11046_0|11008_0|11009_1|11010_3|11011_3|11012_0|11013_3|11014_3|11015_0|11016_0|11017_3|11018_4|11019_0|11020_0|11021_0|11022_0|11024_0|11023_30|11025_30|11027_0|11028_0|11029_0|11031_0|11032_0|11033_0|11034_0|11035_0|11030_0|11036_0|11037_0|11039_0|11040_0|11041_0|11042_0|11043_0|11044_0|11047_0|11048_0|11049_0|11050_0|11051_0|11052_0|11053_0|11054_0|11056_0|11057_0|11058_0|11059_0|11055_0|11060_0|11061_0|11062_0|11063_0";
        if (pet.getStye().equals("0")) {
            pet.setLingxi(gj);
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            return;
        }
        Set<String> fsset = new HashSet<>();
        fsset.add("颜如玉");
        fsset.add("妙音鸾女");
        fsset.add("去疾");
        fsset.add("乘黄");
        fsset.add("垂云搜");
        fsset.add("乐·大吕");
        if (fsset.contains(pet.getSummoningname())) {
            pet.setLingxi(fs);
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            return;
        }
        pet.setLingxi(fz);
        AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
    }
}
