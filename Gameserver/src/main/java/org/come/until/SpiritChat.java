package org.come.until;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.safety.Whitelist;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.apache.commons.lang3.StringUtils;
import org.come.model.Skill;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import com.github.pagehelper.util.StringUtil;
import org.come.model.Npctable;
import java.util.Map;
import java.util.ArrayList;
import org.come.server.GameServer;

public class SpiritChat
{
    public static String getAnswering(String question) {
        String answer = "";
        if (question.startsWith("N=")) {
            question = question.split("=")[1];
            ConcurrentHashMap<String, Npctable> map = GameServer.getNpcMap();
            StringBuffer str = new StringBuffer();
            List<Npctable> npcList = new ArrayList<>();
            if (map.size() > 0) {
                for (Map.Entry<String, Npctable> entry : map.entrySet()) {
                    npcList.add(entry.getValue());
                }
                boolean flag = false;
                if (npcList.size() > 0) {
                    str.append("#Y根据您查询的NPC名称【" + question + "】，您可能要查询:#r#G");
                    for (Npctable npctable : npcList) {
                        if (npctable.getNpcname().contains(question)) {
                            flag = true;
                            str.append("#r#G" + npctable.getNpcname() + "#Y地图：" + npctable.getMname() + ",坐标（" + Long.parseLong(npctable.getTx()) / 20L + "," + Long.parseLong(npctable.getTy()) / 20L + "）");
                            if (!StringUtil.isEmpty(str.toString())) {
                                str.append("、");
                            }
                            else {
                                continue;
                            }
                        }
                    }
                    if (flag) {
                        answer = str.toString();
                    }
                    else {
                        answer = "请检查您输入的NPC名称#G" + question + "#Y是否正确，或者无此NPC！";
                    }
                }
            }
            else {
                answer = "请检查您输入的NPC名称#G" + question + "#Y是否正确，或者无此NPC！";
            }
        }
        else if (question.startsWith("D=")) {
            question = question.split("=")[1];
            ConcurrentHashMap<String, List<String>> map2 = GameServer.getGoodsByRobot();
            StringBuffer str = new StringBuffer();
            if (map2.containsKey(question) && ((List<String>)map2.get(question)).size() > 0) {
                List<String> list = (List<String>)map2.get(question);
                for (String robot : list) {
                    if (!StringUtil.isEmpty(str.toString())) {
                        str.append("、");
                    }
                    str.append(robot);
                }
                answer = "物品#G" + question + "#Y击杀以下怪物有几率掉落：#r" + str.toString();
            }
            else {
                answer = "请检查您输入的物品名称#G" + question + "#Y是否正确，或者此物品不存在掉落列表";
            }
        }
        else if (question.startsWith("J=")) {
            question = question.split("=")[1];
            Skill map3 = GameServer.getSkill(question);
            StringBuffer str = new StringBuffer();
            if (map3 != null && map3.getSkillname().contains(question)) {
                str.append(map3.getRemark());
                String msg = convert(str.toString());
                answer = "技能#G" + question + "：#Y描述：" + msg;
            }
            else {
                answer = "请检查您输入的技能名称#G" + question + "#Y是否正确，或者暂无此技能！";
            }
        }
        else if (question.contains("开服")) {
            answer = "#Y11！！！";
        }
        else if (question.contains("挣钱") || question.contains("DHB")) {
            answer = "#Y可以通过：跑300环！做每日任务！月卡每日礼包、仙玉购买等多种途径！";
        }
        else if (question.contains("变强")) {
            answer = "#Y打造装备，养成召唤兽，每日活跃，也可以 找群主充点小钱，分分钟#G霸服当爷！#28";
        }
        else if (question.contains("全民竞猜") || question.contains("竞猜")) {
            answer = "#Y全民竞猜：#r中奖规则 系统每10分钟开一次奖，玩家提前选择要买的生肖，并且输入金额（最低20W最高99999999大话币），如果开奖结果与购买结果相同（不考虑顺序）则获得奖励，#r猜中1个生肖获得购买金额的50%，#r猜中2个生肖获得购买金额的100%，#r猜中3个生肖全中将获得#G（中奖金额*50）#Y的银两 ，#r如果开奖结果中有#G对数（两个相同的生肖）#Y中奖后将获得#G（购买金额*100）#Y的银两，#r如果开奖生肖为#G豹子（3个相同的生肖）#Y中奖后将获得#G（中奖金额*1000）#Y的银两！该玩法只供玩家娱乐消遣，请勿用于其他用途！！！";
        }
        else if (question.contains("新手攻略") || question.contains("新手") || question.contains("攻略")) {
            answer = "#Y新手攻略：#r看左上角感叹号提示，内有升级、打造装备等攻略，也可参考群文件文字版攻略。#Y创建角色后点击仙女姐姐做新手任务，然后渔村找印度阿三做任务到60级，之后地府抓小鬼到70级，70级以后天庭，90级以后鬼王，110级以后修罗，140级以后域外。#r #c4ADEDD大部分任务和活动都会掉落仙玉卡，掉落物品待完善，如有缺失请说明。#r #G如果使用变身卡，请多使用几张，变身卡时间到，人物会停止自动任务，有时候怪物在传送点上，可能会使人物到另一张地图，自动任务失效需注意。";
        }
        else {
            answer = "#Y您要查询什么呢？如果有我解决不了的问题可以找老G哦！#66#r#G查询#RNPC#G请发送N=加NPC名字，#Y例如：N=玉皇大帝。#r#G查询#R掉落#G请发送D=物品名字，#Y例如：D=补天神石。#r#G查询#R技能#G请发送J=技能名称，#Y例如：J=夺命勾魂。";
        }
        return answer;
    }
    
    public static String convert(String html) {
        if (StringUtils.isEmpty(html)) {
            return "";
        }
        Document document = Jsoup.parse(html);
        Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
        document.outputSettings(outputSettings);
        document.select("br").append("\\n");
        document.select("p").prepend("\\n");
        document.select("p").append("\\n");
        String newHtml = document.html().replaceAll("\\\\n", "\n");
        String plainText = Jsoup.clean(newHtml, "", Whitelist.none(), outputSettings);
        String result = StringEscapeUtils.unescapeHtml(plainText.trim());
        return result;
    }
}
