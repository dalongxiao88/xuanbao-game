package org.come.action.gl;

import org.come.model.Skill;
import org.come.entity.RoleSummoning;
import come.tool.FightingData.Battlefield;
import org.come.entity.Goodstable;
import java.util.stream.Collectors;
import java.util.List;
import org.come.until.GsonUtil;
import come.tool.Stall.AssetUpdate;
import org.apache.commons.lang.StringUtils;
import org.come.action.monitor.MonitorUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import com.github.pagehelper.util.StringUtil;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;
//TODO 灵犀修炼费用
public class LxAction implements IAction
{
    private final String LINGXI_INIT = "Lx=0&Lv=0&Point=0&Open=11001_0|11002_0|11003_0|11004_0|11005_0|11006_0|11007_0|11008_0|11009_0|11010_0|11011_0|11026_0|11027_0|11028_0|11029_0|11045_0|11046_0|11047_0|11048_0|11049_0&10";
    private long needMoneyNum;
    private long needExperienceNum;
    private long needQinmiNum;
    private long xidian;
    private Integer[] sum;
    private static final int[] W;
    private static final int[] F;
    private static final int[] Z;
    private static final XSkillLx[] WULI;
    private static final XSkillLx[] FASHU;
    private static final XSkillLx[] FUZHU;
    
    public LxAction() {
        this.needMoneyNum = 3500000L;//灵犀需要的金钱
        this.needExperienceNum = 32000000L;//灵犀需要经验
        this.needQinmiNum = 5000L;//灵犀需要的亲密
        this.xidian = 180000000L;
        this.sum = new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(8), Integer.valueOf(10), Integer.valueOf(12), Integer.valueOf(15), Integer.valueOf(15), Integer.valueOf(15) };
    }
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        synchronized (loginResult) {
            message = message.replaceAll("\"", "");
            String[] vs = message.split("&");
            if (vs.length < 2) {
                return;
            }
            RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(vs[1]));
            if (pet == null) {
                return;
            }
            String lx = pet.getLingxi();
            if (StringUtil.isEmpty(lx)) {
                this.getClass();
                lx = "Lx=0&Lv=0&Point=0&Open=11001_0|11002_0|11003_0|11004_0|11005_0|11006_0|11007_0|11008_0|11009_0|11010_0|11011_0|11026_0|11027_0|11028_0|11029_0|11045_0|11046_0|11047_0|11048_0|11049_0&10";
            }
            String[] svs = lx.split("&");
            if (svs.length != 5) {
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R召唤兽信息出错，请联系管理员，并提供加点方案及召唤兽信息"));
                return;
            }
            if (vs[0].equals("D")) {//洗点
                // 扣除玩家货币
                if (loginResult.getGold().longValue() < this.xidian) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R你的大话币不足以支付洗点费用#17"));
                    return;
                }
                loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() - this.xidian));
                MonitorUtil.getMoney().useX(this.xidian);

                String srcLx = svs[3].split("=")[1];
                String[] srcJN = srcLx.split("\\|");

                for (int i = 0; i < srcJN.length; ++i) {
                    srcJN[i] = srcJN[i].split("_")[0] + "_0";
                }
                svs[0] = "Lx=0";
                svs[3] = "Open=" + StringUtils.join(srcJN, "|");
                // -----------保存并通知客户端刷新面板
                pet.setLingxi(StringUtils.join(svs, "&"));
                AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USEGOOD);
                assetUpdate.updata("D=-" + this.xidian);
                assetUpdate.setPet(pet);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#G洗点成功"));
                // 通知修炼面板更新点数及召唤兽信息，灵犀面板更新点数
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().LingXiAgreement(GsonUtil.getGsonUtil().getgson().toJson(pet)));
            }
            if (vs[0].equals("K")) {//开启灵犀格
                if (StringUtil.isEmpty(lx)) {
                    this.getClass();
                    lx = "Lx=0&Lv=0&Point=0&Open=11001_0|11002_0|11003_0|11004_0|11005_0|11006_0|11007_0|11008_0|11009_0|11010_0|11011_0|11026_0|11027_0|11028_0|11029_0|11045_0|11046_0|11047_0|11048_0|11049_0&10";
                }
                String[] var1 = lx.split("&");
                String p = var1[var1.length - 1];
                int newSize = var1.length - 1;// 新的容量
                String[] newArray = new String[newSize];// 创建新的数组

                for (int j = 0; j < newArray.length; ++j) {
                    newArray[j] = var1[j];
                }
                lx = StringUtils.join(newArray, "&");

                // 类型
                int type = Integer.parseInt(newArray[0].split("=")[1]);
                List<Goodstable> allGoods = AllServiceUtil.getGoodsTableService().getGoodsByRoleID(loginResult.getRole_id());
                List<Goodstable> goods = (List)allGoods.stream().filter(item/* org.come.entity.Goodstable, */ -> item.getType() == 119L).collect(Collectors.toList());

                int sysum = 0;// 所需灵犀丹数量
                boolean isMsg = true;
                boolean isOk = false;
                AssetUpdate assetUpdate2 = new AssetUpdate();
                assetUpdate2.setType(AssetUpdate.USEGOOD);
                String[] ids = vs[2].split("_");
                LOOP: for (int j = 0; j < ids.length; ) {
                    if (lx.indexOf(ids[j]) != -1) {
                      j++;
                      continue;
                    } 
                    lx = lx + "|" + ids[j] + "_0";
                    String openString = isOpenString(Integer.valueOf(Integer.parseInt(ids[j])));
                    if (openString != null) {
                      String[] v = openString.split("#");
                      for (String s : v) {
                        if (StringUtils.isNotBlank(s) && !lx.contains(s))
                          lx = lx + "|" + s + "_0"; 
                      } 
                    }
                    // 灵犀丹
                    while (true) {
                      Goodstable good = null;
                      if (type == 0) {
                        sysum = Integer.parseInt(p) - 10;
                      } else {
                        sysum = Integer.parseInt(p) - 10 + 1;
                      } 
                      sysum = this.sum[sysum].intValue();
                      if (sysum < 1) {
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R召唤兽信息出错，请联系管理员，并提供加点方案及召唤兽信息"));
                        return;
                      } 
                      if (good == null && goods != null && goods.size() > 0)
                        for (int k = 0; k < goods.size(); k++) {
                          if (((Goodstable)goods.get(k)).getUsetime().intValue() >= sysum) {
                            good = goods.get(k);
                            break;
                          } 
                        }  
                      if (good != null) {
                        isMsg = false;
                        good.setUsetime(Integer.valueOf(good.getUsetime().intValue() - sysum));
                        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
                        assetUpdate2.updata("G" + good.getRgid() + "=" + good.getUsetime());
                        int v = 20;
                        try {
                          v = Integer.parseInt(good.getValue().split("=")[1]);
                        } catch (Exception exception) {}
                        if (Battlefield.random.nextInt(100) < v) {
                          int point = Integer.parseInt(p);
                          point++;
                          p = point + "";
                          pet.setLingxi(lx + "&" + point);
                          isOk = true;
                          continue LOOP;
                        } 
                        continue;
                      } 
                      break;
                    } 
                  } 
                if (isMsg) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#Y你的灵犀丹不够" + sysum + "个！"));
                    return;
                }
                if (isOk) {
                    AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                    assetUpdate2.setPet(pet);
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#Y灵犀技能格开启成功！"));
                }
                else {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("很遗憾，开启失败！"));
                }
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().LingXiAgreement(GsonUtil.getGsonUtil().getgson().toJson(pet)));
            }
            if (vs[0].equals("S")) {
                if (vs.length != 4) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R召唤兽信息出错，请联系管理员，并提供加点方案及召唤兽信息"));
                    return;
                }
                int mb = Integer.parseInt(vs[2].split("=")[1]);
                if (mb == 0) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R该召唤兽还没有选择灵犀技能类型"));
                    return;
                }
                int smb = Integer.parseInt(svs[0].split("=")[1]);
                int point2 = Integer.parseInt(svs[2].split("=")[1]);
                String srcLx2 = svs[3].split("=")[1];
                String newLx = vs[3];
                if (smb == 0) {
                    svs[0] = "Lx=" + mb;
                }
                else {
                    if (smb != mb) {
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R该召唤兽已选择了其他灵犀类型，如要改变类型请先清空之前的技能点数"));
                        return;
                    }
                    svs[0] = "Lx=" + mb;
                }
                String[] srcJN2 = srcLx2.split("\\|");
                String[] newJN = newLx.split("\\|");
                XSkillLx[] xskills = new XSkillLx[srcJN2.length];
                for (int m = 0; m < srcJN2.length; ++m) {
                    String[] sjn = srcJN2[m].split("_");
                    Skill skill = GameServer.getSkill(sjn[0]);
                    if (skill == null) {
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R召唤兽技能信息出错，请联系管理员，并提供加点方案及召唤兽信息"));
                        return;
                    }
                    int count = Integer.parseInt(sjn[1]);
                    xskills[m] = this.getXSkillLx(skill.getSkillid(), mb);
                    if (xskills[m] == null) {
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R召唤兽技能信息出错，请联系管理员，并提供加点方案及召唤兽信息"));
                        return;
                    }
                    xskills[m].setNowPoint(count);
                    xskills[m].setSKill(skill);
                    if (xskills[m].type != mb) {
                        xskills[m].setNowPoint(0);
                    }
                    else {
                        for (String jn2 : newJN) {
                            String[] njn = jn2.split("_");
                            int count2 = Integer.parseInt(njn[1]);
                            if (sjn[0].equals(njn[0]) && xskills[m].nowPoint < count2) {
                                xskills[m].setNowPoint(count2);
                            }
                        }
                    }
                }
                if (this.calPoint(xskills) > point2) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R当前召唤兽的灵犀点数不足"));
                    return;
                }
                for (XSkillLx xskill : xskills) {
                    if (xskill.type == mb && xskill.nowPoint != 0) {
                        if (xskill.skill.getDielectric() > 0.0 && (double)this.downPoint(xskills, xskill.col) < xskill.skill.getDielectric()) {
                            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#Y技能" + xskill.skill.getSkillname() + "不满足前置要求无法保存"));
                            return;
                        }
                        int lv = (int)pet.getGrade() - 544;
                        if (xskill.skill.getSkilllevel() != 0 && lv < xskill.skill.getSkilllevel()) {
                            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#Y召唤兽等级不满足条件，无法学习"));
                            return;
                        }
                        if (StringUtils.isNotEmpty(xskill.skill.getSkillralation()) && this.getCountTemp(xskills, Integer.parseInt(xskill.skill.getSkillralation())) > 0) {
                            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R存在互斥关系，无法保存"));
                            return;
                        }
                        if (!this.selectMax(xskills, xskill.id, xskill.type)) {
                            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R超出可同时修炼限制，此召唤兽不可修炼此技能"));
                            return;
                        }
                    }
                }
                svs[3] = this.getLingXiStr(xskills);
                pet.setLingxi(StringUtils.join(svs, "&"));
                AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                AssetUpdate assetUpdate3 = new AssetUpdate();
                assetUpdate3.setType(AssetUpdate.USEGOOD);
                assetUpdate3.setPet(pet);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate3)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("加点完成，保存成功"));
                // 通知修炼面板更新点数及召唤兽信息，灵犀面板更新点数
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().LingXiAgreement(GsonUtil.getGsonUtil().getgson().toJson(pet)));
                return;
            }else if (vs[0].equals("Z") || vs[0].equals("X")) {//单次修炼
                // 扣除召唤兽经验
                long exp = pet.getExp().longValue();
                if (exp < this.needExperienceNum) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("当前召唤兽经验不足本次修炼所需"));
                    return;
                }
                // 扣除召唤兽亲密
                long qm = pet.getFriendliness();
                if (qm < this.needQinmiNum) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("当前召唤兽亲密值不足本次修炼所需"));
                    return;
                }
                // 扣除玩家货币
                long gold = loginResult.getGold().longValue();
                if (gold < this.needMoneyNum) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("你的金钱不足召唤兽本次修炼所需"));
                    return;
                }
                String[] var2 = lx.split("&");
                String p2 = var2[var2.length - 1];
                int newSize2 = var2.length - 1;
                String[] newArray2 = new String[newSize2];
                for (int i2 = 0; i2 < newArray2.length; ++i2) {
                    newArray2[i2] = var2[i2];
                }
                lx = StringUtils.join(newArray2, "&");
                long count3;
                if (vs[0].equals("X")) {
                    count3 = this.calCount(lx, exp, qm, gold);
                    lx = this.addXiulian(pet, lx, count3);
                }
                else {
                    count3 = this.calCount(pet, lx, exp, qm, gold);
                    lx = this.addXiulianMax(pet, lx, count3);
                }
                if (count3 == 0) {
                    if (exp < this.needExperienceNum) {
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("当前召唤兽经验不足本次修炼所需"));
                        return;
                    }
                    if (qm < this.needQinmiNum) {
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("当前召唤兽亲密值不足本次修炼所需"));
                        return;
                    }
                    if (gold < this.needMoneyNum) {
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("你的金钱不足召唤兽本次修炼所需"));
                        return;
                    }
                }
                if (exp < this.needExperienceNum * (long)count3) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("当前召唤兽经验不足本次修炼所需"));
                    return;
                }
                if (qm < this.needQinmiNum * (long)count3) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("当前召唤兽亲密值不足本次修炼所需"));
                    return;
                }
                if (gold < this.needMoneyNum * (long)count3) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("你的金钱不足召唤兽本次修炼所需"));
                    return;
                }
                if (lx == null) {
                    return;
                }
                if (!lx.startsWith("Lx")) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement(lx));
                    return;
                }
                loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() - this.needMoneyNum * (long)count3));
                MonitorUtil.getMoney().useD(this.needMoneyNum * (long)count3);
                pet.setExp(new BigDecimal(exp - this.needExperienceNum * (long)count3));
                pet.setLingxi(lx + "&" + p2);
                pet.setFriendliness(Long.valueOf(qm - this.needQinmiNum * (long)count3));
                AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                AssetUpdate assetUpdate4 = new AssetUpdate();
                assetUpdate4.setType(AssetUpdate.USEGOOD);
//                assetUpdate4.updata("refD=" + loginResult.getGold());
                assetUpdate4.updata("D=-" + (this.needMoneyNum * (long)count3));
                assetUpdate4.setPet(pet);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate4)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#W修炼完成，召唤兽的修炼进度提高了" + count3 + "点"));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().LingXiAgreement(GsonUtil.getGsonUtil().getgson().toJson(pet)));
            }
        }
    }
    
    public String getLingXiStr(XSkillLx[] lxs) {
        StringBuilder str = new StringBuilder();
        str.append("Open=");
        for (int i = 0; i < lxs.length; ++i) {
            if (i != 0) {
                str.append("|");
            }
            str.append(lxs[i].id);
            str.append("_");
            str.append(lxs[i].nowPoint);
        }
        return str.toString();
    }
    
    public int getMaxPoint(String skillId) {
        Skill skill = GameServer.getSkill(skillId);
        return (int)skill.getValue();
    }
    
    public int calPoint(XSkillLx[] lxs) {
        int point = 0;
        for (XSkillLx lx : lxs) {
            point += lx.nowPoint;
        }
        return point;
    }
    
    public int downPoint(XSkillLx[] lxs, int col) {
        int point = 0;
        for (XSkillLx lx : lxs) {
            point += lx.nowPoint;
        }
        return point;
    }
    
    public int getCountTemp(XSkillLx[] lxs, int skillId) {
        for (XSkillLx xSkilllx : lxs) {
            if (xSkilllx.id == skillId) {
                return xSkilllx.nowPoint;
            }
        }
        return 0;
    }
    
    public boolean selectMax(XSkillLx[] lxs, int skillId, int type) {
        int[] arr = { 0, 0, 0, 0, 0, 0 };
        int idx = -1;
        int upCount = 0;
        int downCount = 0;
        switch (type) {
            case 1: {
                int i = 0;
                while (i < LxAction.W.length) {
                    if (LxAction.W[i] == skillId) {
                        idx = i;
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                if (idx == -1) {
                    return true;
                }
                for (XSkillLx xSkilllx : lxs) {
                    if (xSkilllx.id == LxAction.W[0] && idx != 0 && xSkilllx.nowPoint > 0) {
                        ++upCount;
                        int n = 0;
                        ++arr[n];
                    }
                    else if (xSkilllx.id == LxAction.W[1] && idx != 1 && xSkilllx.nowPoint > 0) {
                        ++upCount;
                        int n2 = 1;
                        ++arr[n2];
                    }
                    else if (xSkilllx.id == LxAction.W[2] && idx != 2 && xSkilllx.nowPoint > 0) {
                        ++upCount;
                        int n3 = 2;
                        ++arr[n3];
                    }
                    else if (xSkilllx.id == LxAction.W[3] && idx != 3 && xSkilllx.nowPoint > 0) {
                        ++downCount;
                        int n4 = 3;
                        ++arr[n4];
                    }
                    else if (xSkilllx.id == LxAction.W[4] && idx != 4 && xSkilllx.nowPoint > 0) {
                        ++downCount;
                        int n5 = 4;
                        ++arr[n5];
                    }
                    else if (xSkilllx.id == LxAction.W[5] && idx != 5 && xSkilllx.nowPoint > 0) {
                        ++downCount;
                        int n6 = 5;
                        ++arr[n6];
                    }
                }
                break;
            }
            case 2: {
                int i = 0;
                while (i < LxAction.F.length) {
                    if (LxAction.F[i] == skillId) {
                        idx = i;
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                if (idx == -1) {
                    return true;
                }
                for (XSkillLx xSkilllx : lxs) {
                    if (xSkilllx.id == LxAction.F[0] && idx != 0 && xSkilllx.nowPoint > 0) {
                        ++upCount;
                        int n7 = 0;
                        ++arr[n7];
                    }
                    else if (xSkilllx.id == LxAction.F[1] && idx != 1 && xSkilllx.nowPoint > 0) {
                        ++upCount;
                        int n8 = 1;
                        ++arr[n8];
                    }
                    else if (xSkilllx.id == LxAction.F[2] && idx != 2 && xSkilllx.nowPoint > 0) {
                        ++upCount;
                        int n9 = 2;
                        ++arr[n9];
                    }
                    else if (xSkilllx.id == LxAction.F[3] && idx != 3 && xSkilllx.nowPoint > 0) {
                        ++downCount;
                        int n10 = 3;
                        ++arr[n10];
                    }
                    else if (xSkilllx.id == LxAction.F[4] && idx != 4 && xSkilllx.nowPoint > 0) {
                        ++downCount;
                        int n11 = 4;
                        ++arr[n11];
                    }
                    else if (xSkilllx.id == LxAction.F[5] && idx != 5 && xSkilllx.nowPoint > 0) {
                        ++downCount;
                        int n12 = 5;
                        ++arr[n12];
                    }
                }
                break;
            }
            case 3: {
                int i = 0;
                while (i < LxAction.Z.length) {
                    if (LxAction.Z[i] == skillId) {
                        idx = i;
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                if (idx == -1) {
                    return true;
                }
                for (XSkillLx xSkilllx : lxs) {
                    if (xSkilllx.id == LxAction.Z[0] && idx != 0 && xSkilllx.nowPoint > 0) {
                        ++upCount;
                        int n14 = 0;
                        ++arr[n14];
                    }
                    else if (xSkilllx.id == LxAction.Z[1] && idx != 1 && xSkilllx.nowPoint > 0) {
                        ++upCount;
                        int n15 = 1;
                        ++arr[n15];
                    }
                    else if (xSkilllx.id == LxAction.Z[2] && idx != 2 && xSkilllx.nowPoint > 0) {
                        ++upCount;
                        int n16 = 2;
                        ++arr[n16];
                    }
                    else if (xSkilllx.id == LxAction.Z[3] && idx != 3 && xSkilllx.nowPoint > 0) {
                        ++downCount;
                        int n17 = 3;
                        ++arr[n17];
                    }
                    else if (xSkilllx.id == LxAction.Z[4] && idx != 4 && xSkilllx.nowPoint > 0) {
                        ++downCount;
                        int n18 = 4;
                        ++arr[n18];
                    }
                    else if (xSkilllx.id == LxAction.Z[5] && idx != 5 && xSkilllx.nowPoint > 0) {
                        ++downCount;
                        int n19 = 5;
                        ++arr[n19];
                    }
                }
                break;
            }
            default: {
                return false;
            }
        }
        if (idx < 3) {
            int index = 0;
            for (int j = 0; j < 3; ++j) {
                if (arr[j] != 0) {
                    ++index;
                }
            }
            return upCount <= 2 && index <= 1;
        }
        else {
            int index = 0;
            for (int j = 3; j < arr.length; ++j) {
                if (arr[j] != 0) {
                    ++index;
                }
            }
            return downCount <= 2 && index <= 1;
        }
    }
    
    public int calCount(String lingxi, long exp, long qm, long gold) {
        long count = 0;
        String[] param = lingxi.split("&");
        if (param.length != 4) {
            return 0;
        }
        int xl = Integer.parseInt(param[1].split("=")[1]);
        int ds = Integer.parseInt(param[2].split("=")[1]);
        count = ds - xl + 1;
        long count1 =count, count2 =count,count3=count;
        if (gold < this.needMoneyNum * (long)count) {
            count1 = (long) Math.floorDiv(gold, this.needMoneyNum);
            if (count1 == 0) {
                return 0;
            }
        }
        if (exp < this.needExperienceNum * (long)count) {
            count2 = (long) Math.floorDiv(exp, this.needExperienceNum);
            if (count2 == 0) {
                return 0;
            }
        }
        if (qm < this.needQinmiNum * (long)count) {
            count3 = (long) Math.floorDiv(qm, this.needQinmiNum);
            if (count3 == 0) {
                return 0;
            }
        }
        count= Math.min(count, count1);
        count= Math.min(count, count2);
        count= Math.min(count, count3);
        return (int) count;
    }
    
    public int calCount(RoleSummoning pet, String lingxi, long exp, long qm, long gold) {
        long count = 0;
        String[] param = lingxi.split("&");
        if (param.length != 4) {
            return 0;
        }
        int maxLvl;
        if (pet.getSsn().equals("2") || pet.getSsn().equals("3") || pet.getSsn().equals("4")) {
            maxLvl = 110;
        }
        else if (pet.getSsn().equals("6")) {
            maxLvl = 110;
        }
        else if (pet.getSsn().equals("5")) {
            maxLvl = 50;
        }
        else {
            maxLvl = 40;
        }
        int xl = Integer.parseInt(param[1].split("=")[1]);
        int i;
        for (int ds = i = Integer.parseInt(param[2].split("=")[1]); i <= maxLvl; ++i) {
            count += i + 1;
        }
        count -= xl;
        long count1 =count, count2 =count,count3=count;
        if (gold < this.needMoneyNum * (long)count) {
            count1 = (long) Math.floorDiv(gold, this.needMoneyNum);
            for (int j = 0; j < count1; j++) {
                if (count1*this.needMoneyNum <= gold) {
                    break;
                }else {
                    count1--;
                }
            }
            if (count1 == 0) {
                return 0;
            }
        }
        if (exp < this.needExperienceNum * (long)count) {
            count2 = (long) Math.floorDiv(exp, this.needExperienceNum);
            for (int j = 0; j < count2; j++) {
                if (count2*this.needExperienceNum <= exp) {
                    break;
                }else {
                    count2--;
                }
            }
            if (count2 == 0) {
                return 0;
            }
        }
        if (qm < this.needQinmiNum * (long)count) {
            count3 = (long) Math.floorDiv(qm, this.needQinmiNum);
            for (int j = 0; j < count3; j++) {
                if (count3*this.needQinmiNum <= qm) {
                    break;
                }else {
                    count3--;
                }
            }
            if (count3 == 0) {
                return 0;
            }
        }
        count= Math.min(count, count1);
        count= Math.min(count, count2);
        count= Math.min(count, count3);
        return (int) count;
    }
    
    public String addXiulianMax(RoleSummoning pet, String lingxi, long count) {
        String[] param = lingxi.split("&");
        if (param.length > 5) {
            return null;
        }
        int xl = Integer.parseInt(param[1].split("=")[1]);
        int ds = Integer.parseInt(param[2].split("=")[1]);
        int maxLvl;
        if (pet.getSsn().equals("2") || pet.getSsn().equals("3") || pet.getSsn().equals("4")) {
            maxLvl = 110;
        }
        else if (pet.getSsn().equals("6")) {
            maxLvl = 110;
        }
        else if (pet.getSsn().equals("5")) {
            maxLvl = 50;
        }
        else {
            maxLvl = 40;
        }
        if (ds >= maxLvl) {
            return "#R当前召唤兽的修炼等级已达到最高";
        }
        while (count > 0 && ds < maxLvl) {
            if (xl > ds) {
                xl = 0;
                ++ds;
            }
            else {
                ++xl;
                --count;
            }
        }
        param[1] = param[1].split("=")[0] + "=" + xl;
        param[2] = param[2].split("=")[0] + "=" + ds;
        return StringUtils.join(param, "&");
    }
    
    public String addXiulian(RoleSummoning pet, String lingxi, long count) {
        String[] param = lingxi.split("&");
        if (param.length > 5) {
            return null;
        }
        int xl = Integer.parseInt(param[1].split("=")[1]);
        int ds = Integer.parseInt(param[2].split("=")[1]);
        if (pet.getSsn().equals("2") || pet.getSsn().equals("3") || pet.getSsn().equals("4")) {
            if (ds >= 110) {
                return "#R当前召唤兽的修炼等级已达到最高";
            }
        }
        else if (pet.getSsn().equals("6")) {
            if (ds >= 110) {
                return "#R当前召唤兽的修炼等级已达到最高";
            }
        }
        else if (pet.getSsn().equals("5")) {
            if (ds >= 50) {
                return "#R当前召唤兽的修炼等级已达到最高";
            }
        }
        else if (ds >= 40) {
            return "#R当前召唤兽的修炼等级已达到最高";
        }
        if (xl + count > ds) {
            xl = 0;
            ++ds;
        }
        else {
            xl += count;
        }
        param[1] = param[1].split("=")[0] + "=" + xl;
        param[2] = param[2].split("=")[0] + "=" + ds;
        return StringUtils.join(param, "&");
    }
    
    public XSkillLx getXSkillLx(int skillid, int type) {
        switch (type) {
            case 1: {
                for (XSkillLx lx : LxAction.WULI) {
                    if (lx.id == skillid) {
                        return lx;
                    }
                }
                break;
            }
            case 2: {
                for (XSkillLx lx : LxAction.FASHU) {
                    if (lx.id == skillid) {
                        return lx;
                    }
                }
                break;
            }
            case 3: {
                for (XSkillLx lx : LxAction.FUZHU) {
                    if (lx.id == skillid) {
                        return lx;
                    }
                }
                break;
            }
        }
        for (XSkillLx lx : LxAction.WULI) {
            if (lx.id == skillid) {
                return lx;
            }
        }
        for (XSkillLx lx : LxAction.FASHU) {
            if (lx.id == skillid) {
                return lx;
            }
        }
        for (XSkillLx lx : LxAction.FUZHU) {
            if (lx.id == skillid) {
                return lx;
            }
        }
        return null;
    }
    
    private String isOpenString(Integer Id) {
        if ((int)Id == 11012 || (int)Id == 11013 || (int)Id == 11031 || (int)Id == 11032 || (int)Id == 11050 || (int)Id == 11051) {
            return "11012#11013#11031#11032#11050#11051";
        }
        if ((int)Id == 11014 || (int)Id == 11015 || (int)Id == 11034 || (int)Id == 11033 || (int)Id == 11053 || (int)Id == 11052) {
            return "11014#11015#11034#11033#11053#11052";
        }
        if ((int)Id == 11016 || (int)Id == 11017 || (int)Id == 11035 || (int)Id == 11030 || (int)Id == 11054 || (int)Id == 11055) {
            return "11016#11017#11035#11030#11054#11055";
        }
        if ((int)Id == 11018 || (int)Id == 11019 || (int)Id == 11037 || (int)Id == 11036 || (int)Id == 11056 || (int)Id == 11057) {
            return "11018#11019#11037#11036#11056#11057";
        }
        if ((int)Id == 11020 || (int)Id == 11021 || (int)Id == 11022 || (int)Id == 11039 || (int)Id == 11040 || (int)Id == 11041 || (int)Id == 11058 || (int)Id == 11059 || (int)Id == 11060) {
            return "11020#11021#11022#11039#11040#11058#11059#11060#11041";
        }
        if ((int)Id == 11023 || (int)Id == 11024 || (int)Id == 11025 || (int)Id == 11042 || (int)Id == 11043 || (int)Id == 11044 || (int)Id == 11061 || (int)Id == 11062 || (int)Id == 11063) {
            return "11023#11024#11025#11042#11043#11044#11061#11062#11063";
        }
        return null;
    }
    
    static {
        W = new int[] { 11020, 11021, 11022, 11023, 11024, 11025 };
        F = new int[] { 11039, 11040, 11041, 11042, 11043, 11044 };
        Z = new int[] { 11058, 11059, 11060, 11061, 11062, 11063 };
        WULI = new XSkillLx[24];
        FASHU = new XSkillLx[23];
        FUZHU = new XSkillLx[23];
        LxAction.WULI[0] = new XSkillLx(1, 11003, 3, 1);
        LxAction.WULI[1] = new XSkillLx(1, 11001, 3, 1);
        LxAction.WULI[2] = new XSkillLx(1, 11004, 3, 1);
        LxAction.WULI[3] = new XSkillLx(1, 11005, 3, 1);
        LxAction.WULI[4] = new XSkillLx(1, 11006, 3, 1);
        LxAction.WULI[5] = new XSkillLx(1, 11007, 3, 1);
        LxAction.WULI[6] = new XSkillLx(1, 11008, 3, 2);
        LxAction.WULI[7] = new XSkillLx(1, 11009, 3, 2);
        LxAction.WULI[8] = new XSkillLx(1, 11010, 3, 2);
        LxAction.WULI[9] = new XSkillLx(1, 11011, 3, 2);
        LxAction.WULI[10] = new XSkillLx(1, 11012, 3, 3);
        LxAction.WULI[11] = new XSkillLx(1, 11013, 3, 3);
        LxAction.WULI[12] = new XSkillLx(1, 11014, 3, 4);
        LxAction.WULI[13] = new XSkillLx(1, 11015, 3, 4);
        LxAction.WULI[14] = new XSkillLx(1, 11016, 3, 5);
        LxAction.WULI[15] = new XSkillLx(1, 11017, 3, 5);
        LxAction.WULI[16] = new XSkillLx(1, 11018, 4, 6);
        LxAction.WULI[17] = new XSkillLx(1, 11019, 4, 6);
        LxAction.WULI[18] = new XSkillLx(1, 11020, 30, 7);
        LxAction.WULI[19] = new XSkillLx(1, 11021, 30, 7);
        LxAction.WULI[20] = new XSkillLx(1, 11022, 30, 7);
        LxAction.WULI[21] = new XSkillLx(1, 11023, 30, 7);
        LxAction.WULI[22] = new XSkillLx(1, 11024, 30, 7);
        LxAction.WULI[23] = new XSkillLx(1, 11025, 30, 7);
        LxAction.FASHU[0] = new XSkillLx(2, 11001, 3, 1);
        LxAction.FASHU[1] = new XSkillLx(2, 11004, 3, 1);
        LxAction.FASHU[2] = new XSkillLx(2, 11002, 3, 1);
        LxAction.FASHU[3] = new XSkillLx(2, 11005, 3, 1);
        LxAction.FASHU[4] = new XSkillLx(2, 11007, 3, 1);
        LxAction.FASHU[5] = new XSkillLx(2, 11026, 3, 1);
        LxAction.FASHU[6] = new XSkillLx(2, 11027, 3, 2);
        LxAction.FASHU[7] = new XSkillLx(2, 11028, 4, 2);
        LxAction.FASHU[8] = new XSkillLx(2, 11029, 4, 2);
        LxAction.FASHU[9] = new XSkillLx(2, 11031, 3, 3);
        LxAction.FASHU[10] = new XSkillLx(2, 11032, 3, 3);
        LxAction.FASHU[11] = new XSkillLx(2, 11033, 3, 4);
        LxAction.FASHU[12] = new XSkillLx(2, 11034, 3, 4);
        LxAction.FASHU[13] = new XSkillLx(2, 11035, 3, 5);
        LxAction.FASHU[14] = new XSkillLx(2, 11030, 3, 5);
        LxAction.FASHU[15] = new XSkillLx(2, 11036, 4, 6);
        LxAction.FASHU[16] = new XSkillLx(2, 11037, 4, 6);
        LxAction.FASHU[17] = new XSkillLx(2, 11039, 30, 7);
        LxAction.FASHU[18] = new XSkillLx(2, 11040, 30, 7);
        LxAction.FASHU[19] = new XSkillLx(2, 11041, 30, 7);
        LxAction.FASHU[20] = new XSkillLx(2, 11042, 30, 7);
        LxAction.FASHU[21] = new XSkillLx(2, 11043, 30, 7);
        LxAction.FASHU[22] = new XSkillLx(2, 11044, 30, 7);
        LxAction.FUZHU[0] = new XSkillLx(3, 11001, 3, 1);
        LxAction.FUZHU[1] = new XSkillLx(3, 11004, 3, 1);
        LxAction.FUZHU[2] = new XSkillLx(3, 11002, 3, 1);
        LxAction.FUZHU[3] = new XSkillLx(3, 11005, 3, 1);
        LxAction.FUZHU[4] = new XSkillLx(3, 11045, 3, 1);
        LxAction.FUZHU[5] = new XSkillLx(3, 11046, 3, 1);
        LxAction.FUZHU[6] = new XSkillLx(3, 11047, 3, 2);
        LxAction.FUZHU[7] = new XSkillLx(3, 11048, 5, 2);
        LxAction.FUZHU[8] = new XSkillLx(3, 11049, 5, 2);
        LxAction.FUZHU[9] = new XSkillLx(3, 11050, 5, 3);
        LxAction.FUZHU[10] = new XSkillLx(3, 11051, 5, 3);
        LxAction.FUZHU[11] = new XSkillLx(3, 11052, 3, 4);
        LxAction.FUZHU[12] = new XSkillLx(3, 11053, 3, 4);
        LxAction.FUZHU[13] = new XSkillLx(3, 11054, 5, 5);
        LxAction.FUZHU[14] = new XSkillLx(3, 11055, 5, 5);
        LxAction.FUZHU[15] = new XSkillLx(3, 11056, 4, 6);
        LxAction.FUZHU[16] = new XSkillLx(3, 11057, 4, 6);
        LxAction.FUZHU[17] = new XSkillLx(3, 11058, 30, 7);
        LxAction.FUZHU[18] = new XSkillLx(3, 11059, 30, 7);
        LxAction.FUZHU[19] = new XSkillLx(3, 11060, 30, 7);
        LxAction.FUZHU[20] = new XSkillLx(3, 11061, 30, 7);
        LxAction.FUZHU[21] = new XSkillLx(3, 11062, 30, 7);
        LxAction.FUZHU[22] = new XSkillLx(3, 11063, 30, 7);
    }
    
    static class XSkillLx
    {
        public int type;
        public int id;
        public int nowPoint;
        public int maxPoint;
        public int col;
        public Skill skill;
        
        public XSkillLx(int type, int id, int maxPoint, int col) {
            this.type = type;
            this.id = id;
            this.maxPoint = maxPoint;
            this.col = col;
        }
        
        public void setNowPoint(int nowPoint) {
            if (this.maxPoint >= nowPoint) {
                this.nowPoint = nowPoint;
            }
            else {
                this.nowPoint = this.maxPoint;
            }
        }
        
        public void setSKill(Skill skill) {
            this.skill = skill;
        }
    }
}
