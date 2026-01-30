package come.tool.Calculation;

import javax.swing.DefaultListModel;
import org.come.action.reward.DrawnitemsAction;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.come.entity.*;
import org.come.tool.WriteOut;
import org.come.model.ColorScheme;
import com.gl.util.LingXiUtil;
import org.come.tool.CustomFunction;
import org.come.bean.NChatBean;
import come.tool.newTeam.TeamBean;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.text.DecimalFormat;
import come.tool.Role.RolePool;
import come.tool.newTeam.TeamRole;
import come.tool.newTeam.TeamUtil;
import come.tool.Scene.DNTG.DNTGRole;
import come.tool.Scene.Scene;

import java.util.concurrent.ConcurrentHashMap;

import org.come.bean.UseCardBean;
import org.come.model.Title;
import come.tool.Scene.DNTG.DNTGScene;
import come.tool.Scene.SceneUtil;
import come.tool.FightingData.GetqualityUntil;
import come.tool.FightingData.Ql;
import org.apache.commons.lang.StringUtils;
import come.tool.FightingData.FightingSummon;
import come.tool.FightingData.FightingLingbao;
import org.come.until.GsonUtil;
import org.come.model.Skill;
import org.come.tool.Arith;
import org.come.model.Configure;
import come.tool.Mixdeal.CreepsMixdeal;
import come.tool.FightingData.AddState;
import org.come.tool.Goodtype;
import come.tool.Role.Hang;
import org.come.until.AllServiceUtil;
import come.tool.FightingData.FightingSkill;
import come.tool.FightingData.PK_MixDeal;
import org.come.server.GameServer;
import come.tool.Battle.BattleMixDeal;
import come.tool.Battle.BattleData;
import come.tool.Role.RoleData;
import org.come.bean.LoginResult;
import come.tool.FightingData.ManData;
import java.math.BigDecimal;

public class    CalculationUtil
{
    static Map<Integer, XuanBaoSkill> xuanBaoSkillMap = new ConcurrentHashMap();

    /**
     * 初始化等级抗性
     *
     * @return
     */
    public static void initGradeQl() {
        String[] kx = { "抗混乱", "抗封印", "抗昏睡", "抗中毒", "抗风", "抗火", "抗雷", "抗水", "命中率", "狂暴率", "致命率", "物理吸收率", "抗遗忘", "抗三尸", "抗鬼火", "抗浩然正气", "躲闪率", "雷法反击", "风法反击", "水法反击", "火法反击", "被攻击时释放魔神附身", "被攻击时释放含情脉脉", "被攻击时释放乾坤借速" };
        String[] ren = { "0=4=1", "1=4=1", "2=4=1", "3=4=1" };
        String[] mo = { "0=8=1", "1=8=1", "2=8=1", "3=8=1", "4=12=1", "5=12=1", "6=12=1", "7=12=1", "8=20=1", "11=8=1" };
        String[] xian = { "4=4=1", "5=4=1", "6=4=1", "7=4=1" };
        String[] gui = { "15=1=-100", "16=4=1", "0=6=1", "1=6=1", "2=6=1", "3=6=1", "14=6=1", "12=6=1", "13=6=120", "4=8=-1", "5=8=-1", "6=8=-1", "7=8=-1", "8=12=1" };
        String[] LONG = { "11=6=1", "0=6=1", "1=6=1", "2=6=1", "3=6=1", "12=6=1", "8=20=1" };
        (BaseValue.GradeQls = new HashMap<>()).put(new BigDecimal(10001), getGradeQl(kx, ren));
        BaseValue.GradeQls.put(new BigDecimal(10002), getGradeQl(kx, mo));
        BaseValue.GradeQls.put(new BigDecimal(10003), getGradeQl(kx, xian));
        BaseValue.GradeQls.put(new BigDecimal(10004), getGradeQl(kx, gui));
        BaseValue.GradeQls.put(new BigDecimal(10005), getGradeQl(kx, LONG));
        xuanBaoSkillMap.put(30000, new XuanBaoSkill(30000, "50=3086&100=5374&150=7433&200=9357", "10=2.6&15=3.6&20=4.6&25=5.5&30=6.3", "10=6.6&15=9.1&20=11.5&25=13.7&30=15.9", "10=31.5&15=43.6&20=54.9&25=65.6&30=75.9"));
        xuanBaoSkillMap.put(30001, new XuanBaoSkill(30001, "49=15|5&50=13|4&100=11|3&150=9|2&200=9|2", "10=6.3|6.3&15=8.7|8.7&20=10.9|10.9&25=13.1|13.1&30=15.1|15.1", "10=2.6&15=3.6&20=4.6&25=5.5&30=6.3", "10=6.3&15=8.7&20=10.9&25=13.1&30=15.1"));
        xuanBaoSkillMap.put(30002, new XuanBaoSkill(30002, "50=20.7&100=36.1&150=49.9&200=62.9", "10=2.6&15=3.6&20=4.6&25=5.5&30=100", "10=6.6&15=9.1&20=11.5&25=13.7&30=100", "10=31.5&15=43.6&20=54.9&25=65.6&30=75.9"));
        xuanBaoSkillMap.put(30003, new XuanBaoSkill(30003, "49=1&50=2&100=2&150=3&200=3", "10=11.3&15=15.7&20=19.7&25=23.6&30=27.3", "10=2&15=2.7&20=3.5&25=4.2&30=4.8", "10=3785&15=5236&20=6591&25=7879&30=9116"));
        xuanBaoSkillMap.put(30004, new XuanBaoSkill(30004, "49=1&50=2&100=3&150=4&200=4", "10=15.1&15=20.9&20=26.3&25=31.5&30=36.4", "10=1&15=2&20=2&25=3&30=3", "10=1&15=2&20=2&25=3&30=3"));
        xuanBaoSkillMap.put(30005, new XuanBaoSkill(30005, "49=3|12582|15348&50=4|20059|24487&100=5|25855|31570&150=5|31072|37947&200=5|35945|43903", "10=20.5&15=28.3&20=35.7&25=42.6&30=49.3", "10=18.9&15=26.1&20=32.9&25=39.3&30=45.5", "10=9.4&15=13&20=16.4&25=19.6&30=22.7"));
        xuanBaoSkillMap.put(30006, new XuanBaoSkill(30006, "50=21.9|9.8&100=27|12.1&150=30.5|13.7&200=33.3|14.9", "10=20.8&15=24.8&20=28.1&25=30.9&30=33.4", "10=27.4&15=32.6&20=36.9&25=40.7&30=44", "10=36.6&15=43.5&20=49.3&25=54.2&30=58.7"));
        xuanBaoSkillMap.put(30007, new XuanBaoSkill(30007, "50=21.9|9.8&100=27|12.1&150=30.5|13.7&200=33.3|14.9", "10=20.8&15=24.8&20=28.1&25=30.9&30=33.4", "10=27.4&15=32.6&20=36.9&25=40.7&30=44", "10=36.6&15=43.5&20=49.3&25=54.2&30=58.7"));
        xuanBaoSkillMap.put(30008, new XuanBaoSkill(30008, "50=1.6|1.6&100=3|3&150=4.1|4.1&200=4.8|4.8", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=32.8&15=45.3&20=57.1&25=68.2&30=79"));
        xuanBaoSkillMap.put(30009, new XuanBaoSkill(30009, "50=1.6|1.6&100=3|3&150=4.1|4.1&200=4.8|4.8", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=32.8&15=45.3&20=57.1&25=68.2&30=79"));
        xuanBaoSkillMap.put(30010, new XuanBaoSkill(30010, "50=0.7|13&100=1.0|19.8&150=1.3|25.2&200=1.5|30", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5"));
        xuanBaoSkillMap.put(30011, new XuanBaoSkill(30011, "50=1.3|13&100=2|19.8&150=2.5|25.2&200=2.9|30", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", ""));
        xuanBaoSkillMap.put(30012, new XuanBaoSkill(30012, "50=0.7|13&100=1.0|19.8&150=1.3|25.2&200=1.5|30", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", "10=16.4&15=22.6&20=28.5&25=34.1&30=39.5", ""));
        xuanBaoSkillMap.put(30013, new XuanBaoSkill(30013, "50=19339|28.5|28.9&100=24779|45.5|47.5&150=29675|60.7|64.3&200=34249|75|80", "10=7.0&15=9.2&20=11.2&25=13.2&30=15", "10=21.5&15=29.3&20=36.5&25=43.4&30=50.0", "10=27.7&15=38.3&20=48.3&25=57.5&30=66.8"));
        xuanBaoSkillMap.put(30014, new XuanBaoSkill(30014, "50=2|463&100=2|881&150=3|1213&200=3|1426", "10=1388&15=1919&20=2416&25=2889&30=3342", "10=2776&15=3839&20=4833&25=5778&30=6685", "10=2&15=2&20=3&25=3&30=4"));
        xuanBaoSkillMap.put(30015, new XuanBaoSkill(30015, "50=3.3|7.4&100=6.2|14.1&150=8.5|19.4&200=10|22.8", "10=2.0&15=2.8&20=3.6&25=4.3&30=5.0", "10=2.2&15=3.1&20=3.9&25=4.7&30=5.4", "10=2.6&15=3.6&20=4.6&25=5.5&30=6.3"));
        xuanBaoSkillMap.put(30016, new XuanBaoSkill(30016, "50=22638&100=29300&150=35297&200=40898", "10=14.5|1&15=20|2&20=25.2|2&25=30.2|3&30=34.9|3", "10=2&15=3&20=4&25=5&30=6", "10=6.6&15=9.1&20=11.5&25=13.7&30=15.9"));
        xuanBaoSkillMap.put(30017, new XuanBaoSkill(30017, "50=24203|6.6|11.1|77.4&100=32507|9.9|16.2|97.7&150=39981|12.5|20.8|116&200=46964|14.8|25|133.1", "10=5.9&15=7.8&20=9.6&25=11.4&30=13", "10=2.4|4.9&15=2.9|5.8&20=3.3|6.7&25=3.8|7.6&30=4.2|8.4", "10=6.6&15=8.5&20=10.3&25=12.1&30=13.7"));
        xuanBaoSkillMap.put(30018, new XuanBaoSkill(30018, "50=5.7|11.5|11.8&100=9.1|18.3|20.3&150=12.2|24.4|27.9&200=15|30.1|35", "10=9.6|7.2&15=13.0|9.6&20=16.1|11.8&25=19.1|14.0&30=22.0|70.0", "10=10.4&15=13.5&20=16.4&25=19.2&30=21.9", "10=2.3&15=3.1&20=3.7&25=4.4&30=5.0"));
        xuanBaoSkillMap.put(30019, new XuanBaoSkill(30019, "50=6.8|3&100=11.9|3&150=16.5|4&200=20.7|4", "10=3.1&15=4.0&20=4.8&25=5.5&30=6.1", "10=1&15=1&20=2&25=2&30=2", "10=20.3&15=27.8&20=34.8&25=41.5&30=47.9"));
        xuanBaoSkillMap.put(30020, new XuanBaoSkill(30020, "50=2&100=2&150=3&200=3", "10=3.7&15=5.2&20=6.5&25=7.8&30=9.1", "10=1&15=1&20=2&25=2&30=2", "10=5047&15=6981&20=8788&25=10560&30=12155"));
        xuanBaoSkillMap.put(30021, new XuanBaoSkill(30021, "50=9829|9829|44.2&100=12371|12371|69.7&150=14659|14659|92.5&200=16797|16797|113.9", "10=41.0&15=56.7&20=71.4&25=85.3&30=98.7", "10=9.4&15=13.0&20=16.4&25=19.6&30=227", "10=11.3&15=15.7&20=19.7&25=23.6&30=273"));
        xuanBaoSkillMap.put(30022, new XuanBaoSkill(30022, "50=7.5|5.6|3.7&100=12.6|9.4|6.3&150=17.2|12.9|8.6&200=21.4|16.1|10.7", "10=10.4&15=14.0&20=17.4&25=20.6&30=23.7", "10=13.2&15=18.3&20=23.0&25=27.5&30=31.9", "10=25.2&15=30.9&20=35.7&25=40.0&30=43.8"));
        xuanBaoSkillMap.put(30023, new XuanBaoSkill(30023, "50=5.7|5.7|17.5&100=9.1|9.1|29.3&150=12.2|12.2|40&200=15|15|10", "10=9.8&15=13.4&20=16.8&25=20.0&30=23.1", "10=2.4&15=2.9&20=3.3&25=3.6&30=4.2", "10=17.9&15=24.0&20=29.6&25=35.0&30=40.1"));
        xuanBaoSkillMap.put(30024, new XuanBaoSkill(30024, "50=3|26.2&100=4|33.9&150=5|40.7&200=5|47.1", "10=4.2&15=5.7&20=7.0&25=8.3&30=9.6", "10=12.7&15=17.5&20=22.0&25=26.3&30=30.4", "10=28.9&15=36.7&20=43.9&25=50.8&30=57.4"));
        xuanBaoSkillMap.put(30025, new XuanBaoSkill(30025, "50=65.4|21.8&100=76.1|30.3&150=83.9|37.9&200=90.2|45", "10=3.7&15=5.2&20=6.5&25=7.8&30=9.1", "10=2.9&15=3.8&20=4.7&25=5.6&30=6.4", "10=56|2&15=68|3&20=79|3&25=90|4&30=100|4"));
        xuanBaoSkillMap.put(30026, new XuanBaoSkill(30026, "50=3.2&100=4&150=4.6&200=5.2", "10=2&15=3&20=3&25=3&30=4", "10=21.6&15=28.8&20=35.6&25=42.0&30=48.2", "10=64.1&15=68.3&20=110.9&25=132.4&30=153.0"));
        xuanBaoSkillMap.put(30027, new XuanBaoSkill(30027, "50=19&100=31.7&150=43.1&200=53.8", "10=7.7&15=10.1&20=12.3&25=14.4&30=16.3", "10=3807&15=5258&20=6613&25=7901&30=9138", "10=8.4|66.0&15=11.5|74.5&20=14.4|81.2&25=17.2|86.8&30=19.9|91.7"));
        xuanBaoSkillMap.put(30028, new XuanBaoSkill(30028, "50=14.8|3&100=28.2|3&150=38.8|4&200=45.6|4", "10=25.8&15=35.7&20=45.0&25=53.8&30=62.2", "10=23.0&15=31.8&20=40.0&25=47.8&30=55.4", "10=2&15=2&20=2&25=3&30=3"));
        xuanBaoSkillMap.put(30029, new XuanBaoSkill(30029, "50=11.8|11.8|23&100=20.3|20.3|30.3&150=27.9|27.9|35.7&200=35|35|40", "10=2.5&15=3.4&20=4.3&25=5.2&30=6.0", "10=2.8&15=3.9&20=4.9&25=5.9&30=6.8", "10=3.1&15=4.3&20=5.4&25=6.5&30=7.5"));
        xuanBaoSkillMap.put(30030, new XuanBaoSkill(30030, "50=7.4&100=14.1&150=19.4&200=22.8", "10=7.5&15=10.4&20=13.1&25=15.7&30=18.2", "10=7.5&15=10.4&20=13.1&25=15.7&30=18.2", "10=7.5&15=10.4&20=13.1&25=15.7&30=18.2"));
        xuanBaoSkillMap.put(30031, new XuanBaoSkill(30031, "50=448|8961|540|10815&100=852|17045|1028|20572&150=1173|23461|1415|28315&200=1379|27580|1664|22186", "10=11.3&15=15.7&20=19.7&25=23.6&30=27.3", "10=11.3|3.1|5804&15=15.7|4.3|8028&20=19.7|5.4|10106&25=23.6|6.5|12082&30=27.3|7.5|13979", "10=11.3|3.1|6940&15=15.7|4.3|9599&20=19.7|5.4|12084&25=23.6|6.5|14445&30=27.3|7.5|16714"));
        xuanBaoSkillMap.put(30032, new XuanBaoSkill(30032, "50=105.4|105.4&100=125.7|125.7&150=144|144&200=161.1|161.1", "10=9.4&15=13.0&20=16.4&25=19.6&30=22.7", "10=23.0&15=31.8&20=40.0&25=47.9&30=55.4", "10=7.5&15=10.4&20=13.1&25=15.7&30=18.2"));
        xuanBaoSkillMap.put(30033, new XuanBaoSkill(30033, "50=3|13.5&100=4|20.8&150=5|26.8&200=5|32", "10=10.1&15=12.3&20=14.3&25=16.0&30=17.5", "10=4.3&15=5.5&20=6.6&25=7.5&30=8.4", "10=1&15=2&20=2&25=2&30=3"));
        xuanBaoSkillMap.put(30034, new XuanBaoSkill(30034, "50=1|3.3&100=2|5.2&150=2|6.7&200=2|8", "10=25.2&15=30.9&20=35.7&25=40&30=43.8", "10=0.7&15=1&20=1.2&25=1.3&30=1.5", "10=1&15=2&20=2&25=2&30=3"));
        xuanBaoSkillMap.put(30035, new XuanBaoSkill(30035, "50=3.3|6.6|6.4&100=6.2|12.5|12.3&150=8.5|17.1|16.9&200=10|20.1|19.9", "10=1.1&15=1.5&20=1.9&25=2.2&30=2.6", "10=2.3&15=3.3&20=4.1&25=4.9&30=5.7", "10=3.2&15=4.5&20=5.7&25=6.8&30=7.9"));
        xuanBaoSkillMap.put(30036, new XuanBaoSkill(30036, "50=20.5|3&100=35.8|4&150=49.5|5&200=62.3|5", "10=10.3&15=13.2&20=15.6&25=17.9&30=20.0", "10=12.3&15=15.7&20=18.7&25=21.3&30=23.8", "10=1&15=1&20=2&25=2&30=2"));
        xuanBaoSkillMap.put(30037, new XuanBaoSkill(30037, "50=5|8467&100=6|16105&150=7|22167&200=7|26058", "10=11.1&15=14.2&20=16.8&25=19.3&30=21.5", "10=1&15=1&20=2&25=2&30=2", "10=11.1&15=14.2&20=16.8&25=19.3&30=21.5"));
    }
    //掉落率=1|经验加成=5|加强全系法术=5|召唤兽死亡不掉忠诚,血法|人物死亡惩罚减半|每天领取268仙玉
    static String[] limitTypes;
    static String[] baseTypes;
    public static boolean isBsk;
    static String[] evs;
    static Map<Integer, List<Double>> listMap;


    
    public static GradeQl[] getGradeQl(String[] kx, String[] vs) {
        GradeQl[] vsQl = new GradeQl[vs.length];
        for (int i = 0; i < vs.length; ++i) {
            String[] v = vs[i].split("=");
            vsQl[i] = new GradeQl(kx[Integer.parseInt(v[0])], Integer.parseInt(v[1]), (double)Integer.parseInt(v[2]));
        }
        return vsQl;
    }
    //TODO 战斗抗性、技能等等
    /**人物战斗包加载*/
    public static void loadRoleBattle(ManData manData, LoginResult login, RoleData roleData, Map<String, Double> roleMap, Map<String, Double> map, Map<String, Double> eMap, List<BaseEquip> equips, BattleData battleData) {
        if (roleMap == null) {
            roleMap = new HashMap<>();
        }
        else {
            roleMap.clear();
        }
        if (map == null) {
            map = new HashMap<>();
        }
        else {
            map.clear();
        }
        if (eMap == null) {
            eMap = new HashMap<>();
        }
        else {
            eMap.clear();
        }
        if (equips == null) {
            equips = new ArrayList<>();
        }
        else {
            equips.clear();
        }
        int lvl = BattleMixDeal.lvlint((int)login.getGrade());
        int zs = BattleMixDeal.lvltrue((int)login.getGrade());
        manData.setLvl(lvl);
        manData.setZs(zs);
        manData.setManname(login.getRolename());
        manData.setId(login.getRole_id().intValue());
        BigDecimal f1 = login.getScoretype("法门1");
        BigDecimal f2 = login.getScoretype("法门2");
        manData.setSe(login.getSpecies_id());//种族ID
        manData.setFmsld(f1.intValue());//法门熟练度
        manData.setFmsld2(f2.intValue());//法门熟练度
        manData.setSpeciesid(login.getSpecies_id());

        map.put("根骨", (double)login.getBone());
        map.put("灵性", (double) (int) login.getSpir());
        map.put("力量", (double) (int) login.getPower());
        map.put("敏捷", (double) (int) login.getSpeed());
        if (zs == 4) {
            map.put("定力", (double) (int) login.getCalm());
        }

        try{
            if (login.getCar_value()!=null) {
                String[] vvv = login.getCar_value().split("\\|");
                for (int i = 0; i < vvv.length; i++) {
                    if (!vvv[i].contains("=")) {
                        continue;
                    }
                    map.put(vvv[i].split("=")[0], map.get(vvv[i].split("=")[0])+Double.valueOf(vvv[i].split("=")[1]));
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        //召喚獸是否支援
        BigDecimal zy = login.getScoretype("支援");
        manData.setZy(zy.intValue());
        BigDecimal sf = login.getScoretype("首发");
        manData.setSfPet(sf.toString());
        BigDecimal lzy = login.getScoretype("灵宝支援");
        manData.setLzy(lzy.intValue());
        addBaseQl(roleMap, roleData.getBorns());//修正
        BaseValue.gradeQl(login.getRace_id(), lvl, zs, roleMap);//等级
        Title title = GameServer.getTitle(login.getTitle());//称谓抗性
        addBaseQl(map, (title != null) ? title.getQls() : null);
        manData.setExpXS(1.0);
        for (int i = 0; i < CalculationUtil.limitTypes.length; ++i) {
            UseCardBean limit = roleData.getLimit(CalculationUtil.limitTypes[i]);
            if (limit != null) {
                if (i < 5 || i == 8 || i == 9 || i == 10) {
                    CalculationUtil.isBsk = (i == 0);
                    addBaseQl(map, limit.getQls(), manData, login);
                }
                else if (i == 5) {
                    manData.addAddState("回蓝", (double)Integer.parseInt(limit.getValue()), 0.0, 9999);
                }
                else if (i == 6) {
                    manData.setExpXS((double) manData.getExpXS() + 1.0);
                }
                else if (i == 7 && battleData != null && !PK_MixDeal.isPK(battleData.getBattleType())) {
                    addBaseQl(map, limit.getQls(), manData, login);
                }
            }
        }
        //经脉开始
        String str = login.getMeridians();
        List<BaseMeridians> list = BaseMeridians.createBaseMeridiansList(str);
        BaseQl[] qls = BaseMeridians.createBaseQl(list);
        addBaseQl(map, qls);
        //经脉结束
        addBaseQl(roleMap, roleData.getXls(1));//小成修炼
        addBaseQl(roleMap, roleData.getXls(40));//帮派抗性
        addBaseQl(map, roleData.getXls(2));//大成修炼
        addBaseQl(map, roleData.getXls(41));//经脉加成
        addBaseQl(map, roleData.getXls(42));
        addBaseRaceKangXing(map, login.getRace_id());//人物基础属性加成
        getSkill(manData, map, roleData.getSkills(), roleData.getLoginResult());//技能加成
        //逆鳞技能错误先注释
        if (login.getRace_id().longValue() == 10005L) {//龙鳞技能加载
            Skill sl = GameServer.getSkill("3035");
            if (sl != null) {
                FightingSkill fightingSkill = new FightingSkill(sl, lvl, zs, 1.0, 0L, 0);
                manData.addSkill(fightingSkill);
            }
        }
        for (int j = 0; j < roleData.getFs().size() && j < 2; ++j) {
            Lingbao fabao = AllServiceUtil.getLingbaoService().selectLingbaoByID(((Hang)roleData.getFs().get(j)).getId());
            if (fabao != null) {
                addVS(eMap, fabao.getKangxing());//法宝抗性
                Skill skill = GameServer.getSkill(fabao.getBaoname());
                if (skill != null) {
                    int pz = BaseValue.getFBlvl(manData.getZs(), manData.getlvl(), BaseValue.getQv(fabao.getBaoquality()), fabao.getLingbaolvl().intValue());
                    FightingSkill fightingSkill2 = new FightingSkill(skill, fabao.getLingbaolvl().intValue(), 0, 1.0, 0L, pz);
                    manData.addSkill(fightingSkill2);
                }
            }
        }
        if (login.getBabyId() != null) {//孩子技能加成
            Baby baby = AllServiceUtil.getBabyService().selectBabyById(login.getBabyId());
            if (baby != null) {
                manData.setChild(new ManData(baby, manData.getCamp(), manData.getMan(), map));
            }
        }
        int s = 0;//拥有6阶仙器/神兵数量
        manData.setXk_id(roleData.getGoodEquip()[13]);
        long equipType = 0L;//武器类型
        for (int k = 0; k < roleData.getGoodEquip().length; ++k) {//装备加成
            if (roleData.getGoodEquip()[k] != null) {
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(roleData.getGoodEquip()[k]);
                BaseEquip baseEquip = (good != null) ? good.getEquip() : null;
                if (baseEquip != null) {
                    baseEquip.setQhv(good.getQhv());
                    equips.add(baseEquip);
                }
                if (k == 0 && good != null) {
                    equipType = Long.parseLong(good.getSkin());
                }
                // 身上装备是神兵
                if (good != null) {
                    if (Goodtype.GodEquipment_God(good.getType())) {
                        for (BaseQl ql : good.getEquip().getQls()) {
                            if (ql.getKey().equals("等级") && ql.getValue() == 6.0) {
                                ++s;
                                break;
                            }
                        }
                    }
                    else if (Goodtype.GodEquipment_xian(good.getType())) {
                        for (BaseQl ql : good.getEquip().getQls()) {
                            if (ql.getKey().equals("阶数") && ql.getValue() == 6.0) {
                                ++s;
                                break;
                            }
                        }
                    }
                    else if (Goodtype.GodEquipment_Ding(good.getType())) {
                        for (BaseQl ql : good.getEquip().getQls()) {
                            if (ql.getKey().equals("阶数") && ql.getValue() == 6.0) {
                                ++s;
                                break;
                            }
                        }
                    }
                }
            }
        }
        //特技
        List<BaseSkill> baseSkills = null;
        //套装id
        Map<Integer, BaseSuit> suitMap = null;
        //星阵
        BaseStar baseStar = null;
        boolean is = true;
        int qhv = 0;
        while (is) {
            is = false;
            eMap.clear();
            if (baseSkills != null) {
                baseSkills.clear();
            }
            if (suitMap != null) {
                suitMap.clear();
            }
            int size = 0;
            qhv = 0;
            for (int l = equips.size() - 1; l >= 0; --l) {
                BaseEquip baseEquip2 = (BaseEquip)equips.get(l);
                if (baseEquip2.getQhv() != null) {
                    ++size;
                    if (qhv == 0 || qhv > (int)baseEquip2.getQhv()) {
                        qhv = (int)baseEquip2.getQhv();
                    }
                }
            }
            if (size == 5 && qhv != 0) {
                addValue(eMap, "四抗上限", (double)qhv * 0.4);
                addValue(eMap, "装备抗性上限", (double)qhv * 1.5);
                addValue(eMap, "HP", (double)(qhv * 2000));
                addValue(eMap, "MP", (double)(qhv * 1000));
            }
            else {
                qhv = 0;
            }
            baseStar = null;
            for (int l = equips.size() - 1; l >= 0; --l) {
                BaseEquip baseEquip2 = (BaseEquip)equips.get(l);
                if (baseEquip2.getQls() != null) {
                    double qhXS = 1.0;
                    if (baseEquip2.getQhv() != null) {
                        qhXS += BaseEquip.getQHGemXS((int)baseEquip2.getQhv()) / 100.0;
                    }
                    for (int m = baseEquip2.getQls().size() - 1; m >= 0; --m) {
                        BaseQl baseQl = (BaseQl)baseEquip2.getQls().get(m);
                        addValue(eMap, baseQl.getKey(), baseQl.getValue() * qhXS);
                        if ("被攻击时释放含情脉脉".equals(baseQl.getKey()) || "被攻击时释放魔神附身".equals(baseQl.getKey()) || "被攻击时释放乾坤借速".equals(baseQl.getKey())) {
                            AddState addState = new AddState(baseQl.getKey().substring(baseQl.getKey().length() - 4, baseQl.getKey().length()), baseQl.getValue(), 0.0, 9999);
                            if ("被攻击时释放含情脉脉".equals(baseQl.getKey())) {
                                Skill skill2 = GameServer.getSkill("1035");
                                if (skill2 != null) {
                                    FightingSkill fightingSkill3 = new FightingSkill(skill2, manData.getlvl(), manData.getZs(), 10000.0, 0L, 0);
                                    addState.setSkill(fightingSkill3);
                                    fightingSkill3.setSkillblue(0);
                                    fightingSkill3.setSkillsum(3);
                                    addState.setStateEffect2(1035.0);
                                }
                            }
                            else if ("被攻击时释放魔神附身".equals(baseQl.getKey())) {
                                Skill skill2 = GameServer.getSkill("1030");
                                if (skill2 != null) {
                                    FightingSkill fightingSkill3 = new FightingSkill(skill2, manData.getlvl(), manData.getZs(), 25000.0, 0L, 0);
                                    addState.setSkill(fightingSkill3);
                                    fightingSkill3.setSkillblue(0);
                                    fightingSkill3.setSkillsum(3);
                                    addState.setStateEffect2(1030.0);
                                }
                            }
                            else if ("被攻击时释放乾坤借速".equals(baseQl.getKey())) {
                                Skill skill2 = GameServer.getSkill("1040");
                                if (skill2 != null) {
                                    FightingSkill fightingSkill3 = new FightingSkill(skill2, manData.getlvl(), manData.getZs(), 25000.0, 0L, 0);
                                    fightingSkill3.setSkillblue(0);
                                    fightingSkill3.setSkillsum(3);
                                    addState.setSkill(fightingSkill3);
                                    addState.setStateEffect2(1040.0);
                                }
                            }
                            manData.getAddStates().add(addState);
                        }
                    }
                }
                if (baseEquip2.getQlews() != null) {
                    for (int j2 = baseEquip2.getQlews().size() - 1; j2 >= 0; --j2) {
                        BaseQl baseQl2 = (BaseQl)baseEquip2.getQlews().get(j2);
                        addValue(eMap, baseQl2.getKey(), baseQl2.getValue());
                        if ("被攻击时释放含情脉脉".equals(baseQl2.getKey()) || "被攻击时释放魔神附身".equals(baseQl2.getKey()) || "被攻击时释放乾坤借速".equals(baseQl2.getKey())) {
                            AddState addState2 = new AddState(baseQl2.getKey().substring(baseQl2.getKey().length() - 4, baseQl2.getKey().length()), baseQl2.getValue(), 0.0, 9999);
                            if ("被攻击时释放含情脉脉".equals(baseQl2.getKey())) {
                                Skill skill3 = GameServer.getSkill("1035");
                                if (skill3 != null) {
                                    FightingSkill fightingSkill4 = new FightingSkill(skill3, manData.getlvl(), manData.getZs(), 25000.0, 0L, 0);
                                    addState2.setSkill(fightingSkill4);
                                    fightingSkill4.setSkillblue(0);
                                    fightingSkill4.setSkillsum(3);
                                    addState2.setStateEffect2(1035.0);
                                }
                            }
                            else if ("被攻击时释放魔神附身".equals(baseQl2.getKey())) {
                                Skill skill3 = GameServer.getSkill("1030");
                                if (skill3 != null) {
                                    FightingSkill fightingSkill4 = new FightingSkill(skill3, manData.getlvl(), manData.getZs(), 25000.0, 0L, 0);
                                    addState2.setSkill(fightingSkill4);
                                    fightingSkill4.setSkillblue(0);
                                    fightingSkill4.setSkillsum(3);
                                    addState2.setStateEffect2(1030.0);
                                }
                            }
                            else if ("被攻击时释放乾坤借速".equals(baseQl2.getKey())) {
                                Skill skill3 = GameServer.getSkill("1040");
                                if (skill3 != null) {
                                    FightingSkill fightingSkill4 = new FightingSkill(skill3, manData.getlvl(), manData.getZs(), 25000.0, 0L, 0);
                                    fightingSkill4.setSkillblue(0);
                                    fightingSkill4.setSkillsum(3);
                                    addState2.setSkill(fightingSkill4);
                                    addState2.setStateEffect2(1040.0);
                                }
                            }
                            manData.getAddStates().add(addState2);
                        }
                    }
                }
                if (baseEquip2.getBaseSkills() != null) {
                    if (baseSkills == null) {
                        baseSkills = new ArrayList<>();
                    }
                    for (int j2 = baseEquip2.getBaseSkills().size() - 1; j2 >= 0; --j2) {
                        baseSkills.add(baseEquip2.getBaseSkills().get(j2));
                    }
                }
                if (baseEquip2.getBaseSuit() != null) {
                    if (suitMap == null) {
                        suitMap = new HashMap<>();
                    }
                    BaseSuit baseSuit = (BaseSuit)suitMap.get(baseEquip2.getBaseSuit().getSuitId());
                    if (baseSuit == null) {
                        baseSuit = new BaseSuit(baseEquip2.getBaseSuit().getSuitId(), baseEquip2.getBaseSuit().getLvl(), 1);
                        suitMap.put(baseSuit.getSuitId(), baseSuit);
                    }
                    else {
                        baseSuit.setSum(baseSuit.getSum() + 1);
                        if (baseSuit.getLvl() > baseEquip2.getBaseSuit().getLvl()) {
                            baseSuit.setLvl(baseEquip2.getBaseSuit().getLvl());
                        }
                    }
                }
                if (baseEquip2.getBaseStar() != null) {
                    baseStar = baseEquip2.getBaseStar();
                }
            }
            for (int l = equips.size() - 1; l >= 0; --l) {
                BaseLimit baseLimit = ((BaseEquip)equips.get(l)).getBaseLimit();
                if (baseLimit != null) {
                    if (baseLimit.getXs() != -999.0) {
                        if (baseLimit.getGg() != 0 && !isValue("根骨", (int)((double)baseLimit.getGg() * baseLimit.getXs() / 100.0), map, eMap, roleMap)) {
                            equips.remove(l);
                            is = true;
                            break;
                        }
                        else if (baseLimit.getLx() != 0 && !isValue("灵性", (int)((double)baseLimit.getLx() * baseLimit.getXs() / 100.0), map, eMap, roleMap)) {
                            equips.remove(l);
                            is = true;
                            break;
                        }
                        else if (baseLimit.getLm() != 0 && !isValue("力量", (int)((double)baseLimit.getLm() * baseLimit.getXs() / 100.0), map, eMap, roleMap)) {
                            equips.remove(l);
                            is = true;
                            break;
                        }
                        else if (baseLimit.getMj() != 0 && !isValue("敏捷", (int)((double)baseLimit.getMj() * baseLimit.getXs() / 100.0), map, eMap, roleMap)) {
                            equips.remove(l);
                            is = true;
                            break;
                        }
                    }
                    if (!baseLimit.isL()) {
                        if (zs < baseLimit.getZs()) {
                            equips.remove(l);
                            is = true;
                            break;
                        }
                        else if (zs == baseLimit.getZs() && lvl < baseLimit.getLvl()) {
                            equips.remove(l);
                            is = true;
                            break;
                        }
                        else if (zs > baseLimit.getZsMax()) {
                            equips.remove(l);
                            is = true;
                            break;
                        }
                        else if (zs == baseLimit.getZsMax() && lvl > baseLimit.getLvlMax()) {
                            equips.remove(l);
                            is = true;
                            break;
                        }
                    }
                }
            }
        }
        UseCardBean card = roleData.getLimit("变身卡");
        Double cardpf = null;
        if (card != null) {
            cardpf = card.getQlKey("皮肤");
            if (cardpf == null) {
                // 判断是否存在变身卡皮肤，如果有变身卡不再获取光武皮肤
                equipType = (long)CreepsMixdeal.good((int)equipType);
                if (equipType != 0L) {
                    ConcurrentHashMap<Integer, Configure> confi = GameServer.getAllConfigure();
                    Configure configure = (Configure)confi.get(1);
                    String roletyle = "新";
                    if (configure.getNeworold() != null) {
                        roletyle = configure.getNeworold();
                    }
                    if (roletyle.equals("新")) {
                        long se = login.getSpecies_id().longValue();
                        if ((equipType == 1L && se == 20001L) || (equipType == 2L && se == 20001L) || (equipType == 1L && se == 20002L) || (equipType == 3L && se == 20002L) || (equipType == 4L && se == 20003L) || (equipType == 5L && se == 20003L) || (equipType == 9L && se == 20004L) || (equipType == 8L && se == 20004L) || (equipType == 10L && se == 20005L) || (equipType == 7L && se == 20005L) || (equipType == 10L && se == 20006L) || (equipType == 12L && se == 20006L) || (equipType == 1L && se == 20007L) || (equipType == 5L && se == 20007L) || (equipType == 1L && se == 20008L) || (equipType == 10L && se == 20008L) || (equipType == 2L && se == 20009L) || (equipType == 6L && se == 20009L) || (equipType == 8L && se == 20010L) || (equipType == 1L && se == 20010L) || (equipType == 12L && se == 21001L) || (equipType == 7L && se == 21001L) || (equipType == 10L && se == 21002L) || (equipType == 13L && se == 21002L) || (equipType == 10L && se == 21003L) || (equipType == 12L && se == 21003L) || (equipType == 9L && se == 21004L) || (equipType == 10L && se == 21004L) || (equipType == 7L && se == 21005L) || (equipType == 1L && se == 21005L) || (equipType == 14L && se == 21006L) || (equipType == 8L && se == 21006L) || (equipType == 12L && se == 21007L) || (equipType == 4L && se == 21007L) || (equipType == 10L && se == 21008L) || (equipType == 11L && se == 21008L) || (equipType == 10L && se == 21009L) || (equipType == 4L && se == 21009L) || (equipType == 14L && se == 21010L) || (equipType == 9L && se == 21010L) || (equipType == 12L && se == 22001L) || (equipType == 3L && se == 22001L) || (equipType == 14L && se == 22002L) || (equipType == 1L && se == 22002L) || (equipType == 7L && se == 22003L) || (equipType == 14L && se == 22003L) || (equipType == 10L && se == 22004L) || (equipType == 5L && se == 22004L) || (equipType == 7L && se == 22005L) || (equipType == 16L && se == 22005L) || (equipType == 1L && se == 22006L) || (equipType == 12L && se == 22006L) || (equipType == 12L && se == 22007L) || (equipType == 14L && se == 22007L) || (equipType == 11L && se == 22008L) || (equipType == 16L && se == 22008L) || (equipType == 1L && se == 22009L) || (equipType == 13L && se == 22009L) || (equipType == 16L && se == 22010L) || (equipType == 17L && se == 22010L) || (equipType == 1L && se == 23001L) || (equipType == 10L && se == 23001L) || (equipType == 12L && se == 23002L) || (equipType == 5L && se == 23002L) || (equipType == 13L && se == 23003L) || (equipType == 6L && se == 23003L) || (equipType == 9L && se == 23004L) || (equipType == 8L && se == 23004L) || (equipType == 17L && se == 23005L) || (equipType == 11L && se == 23005L) || (equipType == 11L && se == 23006L) || (equipType == 16L && se == 23006L) || (equipType == 1L && se == 24001L) || (equipType == 6L && se == 24001L) || (equipType == 12L && se == 24002L) || (equipType == 10L && se == 24002L) || (equipType == 18L && se == 24003L) || (equipType == 11L && se == 24003L) || (equipType == 9L && se == 24004L) || (equipType == 3L && se == 24004L) || (equipType == 18L && se == 24005L) || (equipType == 12L && se == 24005L) || (equipType == 1L && se == 24006L) || (equipType == 17L && se == 24006L)) {
                            equipType += 18L;
                        }
                        else {
                            equipType = 0L;
                        }
                        if (cardpf != null || roleData.getLimit("童卡") != null || equipType == 0L || s == 5) {}
                    }
                }
            }
        }
        else {
            equipType = (long)CreepsMixdeal.good((int)equipType);
            if (equipType != 0L) {
                ConcurrentHashMap<Integer, Configure> confi = GameServer.getAllConfigure();
                Configure configure = (Configure)confi.get(1);
                String roletyle = "新";
                if (configure.getNeworold() != null) {
                    roletyle = configure.getNeworold();
                }
                if (roletyle.equals("新")) {
                    long se = login.getSpecies_id().longValue();
                    if ((equipType == 1L && se == 20001L) || (equipType == 2L && se == 20001L) || (equipType == 1L && se == 20002L) || (equipType == 3L && se == 20002L) || (equipType == 4L && se == 20003L) || (equipType == 5L && se == 20003L) || (equipType == 9L && se == 20004L) || (equipType == 8L && se == 20004L) || (equipType == 10L && se == 20005L) || (equipType == 7L && se == 20005L) || (equipType == 10L && se == 20006L) || (equipType == 12L && se == 20006L) || (equipType == 1L && se == 20007L) || (equipType == 5L && se == 20007L) || (equipType == 1L && se == 20008L) || (equipType == 10L && se == 20008L) || (equipType == 2L && se == 20009L) || (equipType == 6L && se == 20009L) || (equipType == 8L && se == 20010L) || (equipType == 1L && se == 20010L) || (equipType == 12L && se == 21001L) || (equipType == 7L && se == 21001L) || (equipType == 10L && se == 21002L) || (equipType == 13L && se == 21002L) || (equipType == 10L && se == 21003L) || (equipType == 12L && se == 21003L) || (equipType == 9L && se == 21004L) || (equipType == 10L && se == 21004L) || (equipType == 7L && se == 21005L) || (equipType == 1L && se == 21005L) || (equipType == 14L && se == 21006L) || (equipType == 8L && se == 21006L) || (equipType == 12L && se == 21007L) || (equipType == 4L && se == 21007L) || (equipType == 10L && se == 21008L) || (equipType == 11L && se == 21008L) || (equipType == 10L && se == 21009L) || (equipType == 4L && se == 21009L) || (equipType == 14L && se == 21010L) || (equipType == 9L && se == 21010L) || (equipType == 12L && se == 22001L) || (equipType == 3L && se == 22001L) || (equipType == 14L && se == 22002L) || (equipType == 1L && se == 22002L) || (equipType == 7L && se == 22003L) || (equipType == 14L && se == 22003L) || (equipType == 10L && se == 22004L) || (equipType == 5L && se == 22004L) || (equipType == 7L && se == 22005L) || (equipType == 16L && se == 22005L) || (equipType == 1L && se == 22006L) || (equipType == 12L && se == 22006L) || (equipType == 12L && se == 22007L) || (equipType == 14L && se == 22007L) || (equipType == 11L && se == 22008L) || (equipType == 16L && se == 22008L) || (equipType == 1L && se == 22009L) || (equipType == 13L && se == 22009L) || (equipType == 16L && se == 22010L) || (equipType == 17L && se == 22010L) || (equipType == 1L && se == 23001L) || (equipType == 10L && se == 23001L) || (equipType == 12L && se == 23002L) || (equipType == 5L && se == 23002L) || (equipType == 13L && se == 23003L) || (equipType == 6L && se == 23003L) || (equipType == 9L && se == 23004L) || (equipType == 8L && se == 23004L) || (equipType == 17L && se == 23005L) || (equipType == 11L && se == 23005L) || (equipType == 11L && se == 23006L) || (equipType == 16L && se == 23006L) || (equipType == 1L && se == 24001L) || (equipType == 6L && se == 24001L) || (equipType == 12L && se == 24002L) || (equipType == 10L && se == 24002L) || (equipType == 18L && se == 24003L) || (equipType == 11L && se == 24003L) || (equipType == 9L && se == 24004L) || (equipType == 3L && se == 24004L) || (equipType == 18L && se == 24005L) || (equipType == 12L && se == 24005L) || (equipType == 1L && se == 24006L) || (equipType == 17L && se == 24006L)) {
                        equipType += 18L;
                    }
                    else {
                        equipType = 0L;
                    }
                    if (cardpf != null || roleData.getLimit("童卡") != null || equipType == 0L || s == 5) {}
                }
            }//TODO 获取光武皮肤
        }
        boolean boguang = false;
        boolean jitu = false;
        boolean jinyu = false;
        boolean muyi = false;
        boolean huozhong = false;
        manData.setModel(login.getBattleSkin(equipType));
        manData.setHuoyue(removeValue("根骨", map, eMap, roleMap));
        manData.setShanghai(removeValue("灵性", map, eMap, roleMap));
        manData.setKangluobao(removeValue("力量", map, eMap, roleMap));
        manData.setYuanzhu(removeValue("敏捷", map, eMap, roleMap));
        addValue(map, "法术命中率", manData.getShanghai() * 0.02);
        addValue(map, "法术暴击", manData.getShanghai() * 0.03);
        addValue(map, "法术暴击增伤", manData.getShanghai() * 0.04);
        try {
            addXuanbao(manData, login, map, battleData.getBattleType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (zs == 4) {
            manData.setQihe((long)BaseValue.getRoleValue(login.getRace_id(), (int)removeValue("定力", map, eMap, roleMap), lvl, 4));
        }
        manData.setBaseStar(baseStar);
        if (baseSkills != null) {
            for (int j3 = baseSkills.size() - 1; j3 >= 0; --j3) {
                BaseSkill baseSkill = (BaseSkill)baseSkills.get(j3);
                if (baseSkill.getSkillId() == 8001) {
                    addValue(eMap, "忽视抗混", (double)(int)(manData.getHuoyue() / 50.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8002) {
                    addValue(eMap, "忽视抗封", (double)(int)(manData.getHuoyue() / 50.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8003) {
                    addValue(eMap, "忽视抗睡", (double)(int)(manData.getHuoyue() / 50.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8004) {
                    addValue(eMap, "忽视抗遗忘", (double)(int)(manData.getHuoyue() / 50.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8005) {
                    addValue(eMap, "加强毒伤害", (double)(int)(manData.getHuoyue() / 10.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8006) {
                    addValue(eMap, "忽视抗混", (double)(int)(manData.getShanghai() / 30.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8007) {
                    addValue(eMap, "忽视抗封", (double)(int)(manData.getShanghai() / 30.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8008) {
                    addValue(eMap, "忽视抗睡", (double)(int)(manData.getShanghai() / 30.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8009) {
                    addValue(eMap, "忽视抗遗忘", (double)(int)(manData.getShanghai() / 30.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8010) {
                    addValue(eMap, "加强毒伤害", (double)(int)(manData.getShanghai() / 10.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8011) {
                    addValue(eMap, "忽视抗雷", (double)(int)(manData.getHuoyue() / 10.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8012) {
                    addValue(eMap, "忽视抗火", (double)(int)(manData.getHuoyue() / 10.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8013) {
                    addValue(eMap, "忽视抗风", (double)(int)(manData.getHuoyue() / 10.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8014) {
                    addValue(eMap, "忽视抗水", (double)(int)(manData.getHuoyue() / 10.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8015) {
                    addValue(eMap, "忽视抗鬼火", (double)(int)(manData.getHuoyue() / 20.0) * 0.1);
                }
                else if (baseSkill.getSkillId() == 8018) {
                    addValue(eMap, "命中率", 15.0);
                }
                else if (baseSkill.getSkillId() == 8019) {
                    addValue(eMap, "命中率", 30.0);
                }
                else if (baseSkill.getSkillId() == 8022) {
                    manData.setExpXS((double) manData.getExpXS() + 0.1);
                }
                else if (baseSkill.getSkillId() == 8072) {
                    jitu = true;
                }
                else if (baseSkill.getSkillId() == 8073) {
                    boguang = true;
                }
                else if (baseSkill.getSkillId() == 8077) {
                    jinyu = true;
                }
                else if (baseSkill.getSkillId() == 8078) {
                    muyi = true;
                }
                else if (baseSkill.getSkillId() == 8079) {
                    huozhong = true;
                }
                else if (!baseSkill.isAffect()) {
                    FightingSkill fightingSkill3 = new FightingSkill(baseSkill.getSkill(), lvl, zs, 0.0, 0L, baseSkill.getLvl());
                    manData.addSkill(fightingSkill3);
                }
            }
        }
        if (suitMap != null) {
            for (BaseSuit baseSuit2 : suitMap.values()) {
                Suit suit = GameServer.getSuit(baseSuit2.getSuitId());
                BaseSkill[] suitSkills = (BaseSkill[])((suit != null) ? suit.getSuits() : null);
                if (suitSkills != null) {
                    for (int i2 = 0; i2 < suitSkills.length; ++i2) {
                        BaseSkill baseSkill2 = suitSkills[i2];
                        if (baseSuit2.getSum() >= baseSkill2.getLvl()) {
                            String key = BaseSuit.getsuitSkill(baseSkill2.getSkillId());
                            if (key != null) {
                                double value = BaseSuit.getSuitValue(baseSkill2.getSkillId(), baseSuit2.getLvl());
                                if (key.equals("加强法术")) {
                                    addValue(eMap, "加强风", value);
                                    addValue(eMap, "加强雷", value);
                                    addValue(eMap, "加强水", value);
                                    addValue(eMap, "加强火", value);
                                    addValue(eMap, "加强鬼火", value);
                                }
                                else if (key.equals("提抗上限")) {
                                    addValue(eMap, "四抗", -value);
                                    addValue(eMap, "四抗上限", value);
                                }
                                else {
                                    addValue(eMap, key, value);
                                }
                            }
                            else {
                                Skill skill4 = GameServer.getSkill(baseSkill2.getSkillId() + "");
                                if (skill4 != null) {
                                    if (skill4.getSkillid() == 6022) {
                                        Integer spir = login.getSpir();
                                        double div = Arith.mul(Arith.div((double)(int)spir, 100.0), 0.005);
                                        skill4 = (Skill)GsonUtil.getGsonUtil().getgson().fromJson(GsonUtil.getGsonUtil().getgson().toJson(skill4), Skill.class);
                                        skill4.setValue(skill4.getValue() + div);
                                    }
                                    FightingSkill fightingSkill5 = new FightingSkill(skill4, lvl, zs, 0.0, 0L, baseSuit2.getLvl());
                                    manData.addSkill(fightingSkill5);
                                }
                            }
                        }
                    }
                }
            }
        }
        manData.setHp_z(getBase(login.getRace_id(), lvl, (int)manData.getHuoyue(), 0, map, eMap, roleMap));
        manData.setMp_z(getBase(login.getRace_id(), lvl, (int)manData.getShanghai(), 1, map, eMap, roleMap));
        manData.setAp(getBase(login.getRace_id(), lvl, (int)manData.getKangluobao(), 2, map, eMap, roleMap));
        manData.setSp(getBase(login.getRace_id(), lvl, (int)manData.getYuanzhu(), 3, map, eMap, roleMap));
        if (login.isGolem()) {
            manData.setHp(manData.getHp_z());
            manData.setMp(manData.getMp_z());
        }
        else {
            manData.setHp(login.getHp().intValue());
            manData.setMp(login.getMp().intValue());
        }
        if (manData.getHp() == 0 || manData.getHp() > manData.getHp_z()) {
            manData.setHp(manData.getHp_z());
        }
        if (manData.getMp() == 0 || manData.getMp() > manData.getMp_z()) {
            manData.setMp(manData.getMp_z());
        }
        //召唤兽战斗对象
        List<FightingLingbao> lings = new ArrayList<>();
        Lingbao lingbao = (roleData.getLs() != null) ? AllServiceUtil.getLingbaoService().selectLingbaoByID(roleData.getLs().getId()) : null;
        if (lingbao != null) {
            addVS(eMap, lingbao.getKangxing());
            FightingLingbao ling = new FightingLingbao(new ManData(lingbao, manData), 1);
            lings.add(ling);
        }
        for (Hang helpF : roleData.getHelpFs()) {
            lingbao = AllServiceUtil.getLingbaoService().selectLingbaoByID(helpF.getId());
            if (lingbao != null) {
                FightingLingbao ling2 = new FightingLingbao(new ManData(lingbao, manData), 0);
                lings.add(ling2);
            }
        }
        manData.setLings(lings);
        List<FightingSummon> pets = new ArrayList<>();
        if (roleData.getPets() != null) {
            for (int j4 = roleData.getPets().size() - 1; j4 >= 0; --j4) {
                Hang hang = (Hang)roleData.getPets().get(j4);
                int play = (login.getSummoning_id() != null && hang.getId().compareTo(login.getSummoning_id()) == 0) ? 1 : 0;
                pets.add(new FightingSummon(play, hang, manData.getCamp(), manData.getMan()));
            }
            String helpBb = roleData.getPackRecord().getHelpBb();
            if (helpBb != null && !helpBb.equals("")) {
                String[] vs = helpBb.split("\\|");
                for (int i3 = vs.length - 1; i3 >= 0; --i3) {
                    int id = Integer.parseInt(vs[i3]);
                    int j5 = 0;
                    while (j5 < pets.size()) {
                        FightingSummon pet = (FightingSummon)pets.get(j5);
                        if (pet.getHang().getId().intValue() == id) {
                            pet = (FightingSummon)pets.remove(j5);
                            pets.add(0, pet);
                            break;
                        }
                        else {
                            ++j5;
                        }
                    }
                }
            }
        }
        manData.setPets(pets);
        double sk = removeValue("四抗", map, eMap, roleMap);
        if (sk != 0.0) {
            addValue(eMap, "抗封印", sk);
            addValue(eMap, "抗混乱", sk);
            addValue(eMap, "抗昏睡", sk);
            addValue(eMap, "抗遗忘", sk);
        }
        sk = removeValue("四抗上限", map, eMap, roleMap);
        if (sk != 0.0) {
            addValue(eMap, "抗封印上限", sk);
            addValue(eMap, "抗混乱上限", sk);
            addValue(eMap, "抗昏睡上限", sk);
            addValue(eMap, "抗遗忘上限", sk);
        }
        sk = removeValue("加强仙法", map, eMap, roleMap);
        if (sk != 0.0) {
            addValue(eMap, "加强风", sk);
            addValue(eMap, "加强雷", sk);
            addValue(eMap, "加强水", sk);
            addValue(eMap, "加强火", sk);
        }
        sk = removeValue("加强鬼法", map, eMap, roleMap);
        if (sk != 0.0) {
            addValue(eMap, "加强鬼火", sk);
        }
        sk = removeValue("抗仙法", map, eMap, roleMap);
        if (sk != 0.0) {
            addValue(map, "抗风", sk);
            addValue(map, "抗火", sk);
            addValue(map, "抗水", sk);
            addValue(map, "抗雷", sk);
        }
        sk = removeValue("抗人法", map, eMap, roleMap);
        if (sk != 0.0) {
            addValue(map, "抗封印", sk);
            addValue(map, "抗混乱", sk);
            addValue(map, "抗昏睡", sk);
            addValue(map, "抗中毒", sk);
        }
        //兼容以前靓号
        if (StringUtils.isNotBlank(login.getLianghaoValue())) {
            String lianghaoValue = login.getLianghaoValue();
            if (lianghaoValue.contains("@")) {
                String lhStr = "";
                String[] split1 = lianghaoValue.split("@");
                if (split1[0].equals("6")) {
                    lhStr = split1[1];
                }
                else if (split1[0].equals("7")) {
                    lhStr = split1[3];
                }
                else if (split1[0].equals("8")) {
                    lhStr = split1[4];
                }
                else {
                    lhStr = split1[2];
                }
                String[] split2;
                for (String string : split2 = lhStr.split("&")) {
                    if (!"炼化属性".equals(string)) {
                        String[] v1 = string.split("=");
                        addValue(eMap, v1[0], Double.parseDouble(v1[1]));
                    }
                }
            }
            else {
                String[] split3;
                for (String string2 : split3 = lianghaoValue.split("&")) {
                    if (!"|炼化属性".equals(string2)) {
                        String[] v2 = string2.split("=");
                        addValue(eMap, v2[0], Double.parseDouble(v2[1]));
                    }
                }
            }
        }
        // 修魂固魄
        FightingSkill skill5 = manData.getSkillType(BaseValue.STTYCraceid(login.getRace_id()));
        if (skill5 != null) {
            double v3 = (double)(int)(manData.getHuoyue() / skill5.getSkillhurt()) * skill5.getSkillgain();
            addValue(map, "抗封印", v3);
            addValue(map, "抗混乱", v3);
            addValue(map, "抗昏睡", v3);
            addValue(map, "抗遗忘", v3);
        }
        double ZBMAX = getValue(login.getRace_id(), "装备抗性上限", map, eMap, roleMap, 75.0) + 75.0;
        Ql ql2 = new Ql();
        List<String> keys = allProperty(map, eMap, roleMap);
        for (int i4 = keys.size() - 1; i4 >= 0; --i4) {
            String key2 = (String)keys.get(i4);
            GetqualityUntil.AddR(ql2, key2, getValue(login.getRace_id(), key2, map, eMap, roleMap, ZBMAX));
        }
        sk = removeValue("水魔附身", map, eMap, roleMap);
        if (sk != 0.0) {
            ql2.setRolewxj(0.0);
            ql2.setRolewxm(0.0);
            ql2.setRolewxh(0.0);
            ql2.setRolewxt(0.0);
            ql2.setRolewxs(100.0);
            ql2.setRolewxqkh(ql2.getRolewxqkh() + 20.0 * sk);//修复水魔战斗加成克火超高的问题
        }
        if (manData.getSkillId(6044) != null) {
            double[] numbers = { ql2.getRolewxqkj(), ql2.getRolewxqks(), ql2.getRolewxqkt(), ql2.getRolewxqkm(), ql2.getRolewxqkh() };
            double max = ql2.getRolewxqkj();// 假设第一个元素为最大值
            int c = 0;
            for (int i5 = 1; i5 < numbers.length; ++i5) {
                double tmpMax = max;
                max = Math.max(max, numbers[i5]);// 与当前最大值比较，更新最大值
                if (max != tmpMax) {
                    c = i5;
                }
            }
            switch (c) {
                case 0: {
                    ql2.setRolewxqkj(ql2.getRolewxqkj() + 100.0);
                    break;
                }
                case 1: {
                    ql2.setRolewxqks(ql2.getRolewxqks() + 100.0);
                    break;
                }
                case 2: {
                    ql2.setRolewxqkt(ql2.getRolewxqkt() + 100.0);
                    break;
                }
                case 3: {
                    ql2.setRolewxqkm(ql2.getRolewxqkm() + 100.0);
                    break;
                }
                case 4: {
                    ql2.setRolewxqkh(ql2.getRolewxqkh() + 100.0);
                    break;
                }
            }
        }
        if (jitu) {
            double p = ql2.getRolewxt();
            if (p + 20.0 >= 100.0) {
                ql2.setRolewxt(100.0);
            }
            else {
                ql2.setRolewxt(ql2.getRolewxt() + 20.0);
            }
            Map<Double, String> shu = new HashMap<>();
            shu.put(ql2.getRolewxj(), "金");
            if (!shu.containsKey(ql2.getRolewxm())) {
                shu.put(ql2.getRolewxm(), "木");
            }
            if (!shu.containsKey(ql2.getRolewxs())) {
                shu.put(ql2.getRolewxs(), "水");
            }
            if (!shu.containsKey(ql2.getRolewxh())) {
                shu.put(ql2.getRolewxh(), "火");
            }
            List<Double> shu2 = new ArrayList(shu.keySet());
            double k2 = (double)shu2.get(0);
            if (shu2.size() >= 2) {
                for (int i6 = 1; i6 <= shu2.size() - 1; ++i6) {
                    if (k2 < (double)shu2.get(i6)) {
                        k2 = (double)shu2.get(i6);
                    }
                }
            }
            String s2 = (String)shu.get(k2);
            int n3 = -1;
            switch (s2.hashCode()) {
                case 37329: {
                    if (s2.equals("金")) {
                        n3 = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 26408: {
                    if (s2.equals("木")) {
                        n3 = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 27700: {
                    if (s2.equals("水")) {
                        n3 = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 28779: {
                    if (s2.equals("火")) {
                        n3 = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n3) {
                case 0: {
                    ql2.setRolewxj((ql2.getRolewxj() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxj() - 20.0));
                    break;
                }
                case 1: {
                    ql2.setRolewxm((ql2.getRolewxm() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxm() - 20.0));
                    break;
                }
                case 2: {
                    ql2.setRolewxs((ql2.getRolewxs() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxs() - 20.0));
                    break;
                }
                case 3: {
                    ql2.setRolewxh((ql2.getRolewxh() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxh() - 20.0));
                    break;
                }
            }
        }
        else if (boguang) {
            double p = ql2.getRolewxs();
            if (p + 20.0 >= 100.0) {
                ql2.setRolewxs(100.0);
            }
            else {
                ql2.setRolewxs(ql2.getRolewxs() + 20.0);
            }
            Map<Double, String> shu = new HashMap<>();
            shu.put(ql2.getRolewxj(), "金");
            if (!shu.containsKey(ql2.getRolewxm())) {
                shu.put(ql2.getRolewxm(), "木");
            }
            if (!shu.containsKey(ql2.getRolewxt())) {
                shu.put(ql2.getRolewxs(), "土");
            }
            if (!shu.containsKey(ql2.getRolewxh())) {
                shu.put(ql2.getRolewxh(), "火");
            }
            List<Double> shu2 = new ArrayList(shu.keySet());
            double k2 = (double)shu2.get(0);
            if (shu2.size() >= 2) {
                for (int i6 = 1; i6 <= shu2.size() - 1; ++i6) {
                    if (k2 < (double)shu2.get(i6)) {
                        k2 = (double)shu2.get(i6);
                    }
                }
            }
            String s3 = (String)shu.get(k2);
            int n4 = -1;
            switch (s3.hashCode()) {
                case 37329: {
                    if (s3.equals("金")) {
                        n4 = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 26408: {
                    if (s3.equals("木")) {
                        n4 = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 22303: {
                    if (s3.equals("土")) {
                        n4 = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 28779: {
                    if (s3.equals("火")) {
                        n4 = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n4) {
                case 0: {
                    ql2.setRolewxj((ql2.getRolewxj() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxj() - 20.0));
                    break;
                }
                case 1: {
                    ql2.setRolewxm((ql2.getRolewxm() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxm() - 20.0));
                    break;
                }
                case 2: {
                    ql2.setRolewxt((ql2.getRolewxt() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxt() - 20.0));
                    break;
                }
                case 3: {
                    ql2.setRolewxh((ql2.getRolewxh() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxh() - 20.0));
                    break;
                }
            }
        }
        else if (jinyu) {
            double p = ql2.getRolewxj();
            if (p + 20.0 >= 100.0) {
                ql2.setRolewxj(100.0);
            }
            else {
                ql2.setRolewxj(ql2.getRolewxj() + 20.0);
            }
            Map<Double, String> shu = new HashMap<>();
            shu.put(ql2.getRolewxs(), "水");
            if (!shu.containsKey(ql2.getRolewxm())) {
                shu.put(ql2.getRolewxm(), "木");
            }
            if (!shu.containsKey(ql2.getRolewxt())) {
                shu.put(ql2.getRolewxs(), "土");
            }
            if (!shu.containsKey(ql2.getRolewxh())) {
                shu.put(ql2.getRolewxh(), "火");
            }
            List<Double> shu2 = new ArrayList(shu.keySet());
            double k2 = (double)shu2.get(0);
            if (shu2.size() >= 2) {
                for (int i6 = 1; i6 <= shu2.size() - 1; ++i6) {
                    if (k2 < (double)shu2.get(i6)) {
                        k2 = (double)shu2.get(i6);
                    }
                }
            }
            String s4 = (String)shu.get(k2);
            int n5 = -1;
            switch (s4.hashCode()) {
                case 27700: {
                    if (s4.equals("水")) {
                        n5 = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 26408: {
                    if (s4.equals("木")) {
                        n5 = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 22303: {
                    if (s4.equals("土")) {
                        n5 = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 28779: {
                    if (s4.equals("火")) {
                        n5 = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n5) {
                case 0: {
                    ql2.setRolewxs((ql2.getRolewxs() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxs() - 20.0));
                    break;
                }
                case 1: {
                    ql2.setRolewxm((ql2.getRolewxm() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxm() - 20.0));
                    break;
                }
                case 2: {
                    ql2.setRolewxt((ql2.getRolewxt() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxt() - 20.0));
                    break;
                }
                case 3: {
                    ql2.setRolewxh((ql2.getRolewxh() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxh() - 20.0));
                    break;
                }
            }
        }
        else if (muyi) {
            double p = ql2.getRolewxm();
            if (p + 20.0 >= 100.0) {
                ql2.setRolewxm(100.0);
            }
            else {
                ql2.setRolewxm(ql2.getRolewxm() + 20.0);
            }
            Map<Double, String> shu = new HashMap<>();
            shu.put(ql2.getRolewxj(), "金");
            if (!shu.containsKey(ql2.getRolewxs())) {
                shu.put(ql2.getRolewxs(), "水");
            }
            if (!shu.containsKey(ql2.getRolewxt())) {
                shu.put(ql2.getRolewxs(), "土");
            }
            if (!shu.containsKey(ql2.getRolewxh())) {
                shu.put(ql2.getRolewxh(), "火");
            }
            List<Double> shu2 = new ArrayList(shu.keySet());
            double k2 = (double)shu2.get(0);
            if (shu2.size() >= 2) {
                for (int i6 = 1; i6 <= shu2.size() - 1; ++i6) {
                    if (k2 < (double)shu2.get(i6)) {
                        k2 = (double)shu2.get(i6);
                    }
                }
            }
            String s5 = (String)shu.get(k2);
            int n6 = -1;
            switch (s5.hashCode()) {
                case 37329: {
                    if (s5.equals("金")) {
                        n6 = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 27700: {
                    if (s5.equals("水")) {
                        n6 = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 22303: {
                    if (s5.equals("土")) {
                        n6 = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 28779: {
                    if (s5.equals("火")) {
                        n6 = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n6) {
                case 0: {
                    ql2.setRolewxj((ql2.getRolewxj() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxj() - 20.0));
                    break;
                }
                case 1: {
                    ql2.setRolewxs((ql2.getRolewxs() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxs() - 20.0));
                    break;
                }
                case 2: {
                    ql2.setRolewxt((ql2.getRolewxt() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxt() - 20.0));
                    break;
                }
                case 3: {
                    ql2.setRolewxh((ql2.getRolewxh() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxh() - 20.0));
                    break;
                }
            }
        }
        else if (huozhong) {
            double p = ql2.getRolewxh();
            if (p + 20.0 >= 100.0) {
                ql2.setRolewxh(100.0);
            }
            else {
                ql2.setRolewxh(ql2.getRolewxh() + 20.0);
            }
            Map<Double, String> shu = new HashMap<>();
            shu.put(ql2.getRolewxj(), "金");
            if (!shu.containsKey(ql2.getRolewxm())) {
                shu.put(ql2.getRolewxm(), "木");
            }
            if (!shu.containsKey(ql2.getRolewxt())) {
                shu.put(ql2.getRolewxs(), "土");
            }
            if (!shu.containsKey(ql2.getRolewxs())) {
                shu.put(ql2.getRolewxs(), "水");
            }
            List<Double> shu2 = new ArrayList(shu.keySet());
            double k2 = (double)shu2.get(0);
            if (shu2.size() >= 2) {
                for (int i6 = 1; i6 <= shu2.size() - 1; ++i6) {
                    if (k2 < (double)shu2.get(i6)) {
                        k2 = (double)shu2.get(i6);
                    }
                }
            }
            String s6 = (String)shu.get(k2);
            int n7 = -1;
            switch (s6.hashCode()) {
                case 37329: {
                    if (s6.equals("金")) {
                        n7 = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 26408: {
                    if (s6.equals("木")) {
                        n7 = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 22303: {
                    if (s6.equals("土")) {
                        n7 = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 27700: {
                    if (s6.equals("水")) {
                        n7 = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n7) {
                case 0: {
                    ql2.setRolewxj((ql2.getRolewxj() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxj() - 20.0));
                    break;
                }
                case 1: {
                    ql2.setRolewxm((ql2.getRolewxm() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxm() - 20.0));
                    break;
                }
                case 2: {
                    ql2.setRolewxt((ql2.getRolewxt() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxt() - 20.0));
                    break;
                }
                case 3: {
                    ql2.setRolewxs((ql2.getRolewxs() - 20.0 < 0.0) ? 0.0 : (ql2.getRolewxs() - 20.0));
                    break;
                }
            }
        }
        manData.setQuality(ql2);
        if (battleData != null && battleData.getSceneId() != null && (int)battleData.getSceneId() == 1011) {
            Scene scene = SceneUtil.getScene(1011);
            if (scene != null) {
                DNTGRole dntgRole = ((DNTGScene)scene).getRole(login.getRole_id());
                if (dntgRole != null) {
                    addDNTG(manData, dntgRole.getSLJC());
                }
            }
        }
    }

    public static void addXuanbao(ManData manData, LoginResult loginResult, Map<String, Double> map, int type) {
        List<org.come.entity.XuanBao> list = AllServiceUtil.getXuanBaoService().selectLingbaoByRoleID(loginResult.getRole_id());
        int xblvl = 0;
        for (XuanBao xuanBao : list) {
            xblvl = xuanBao.getLvl();
            int r_ = 0;
            int g_ = 0;
            int y_ = 0;
            int b_ = 0;
            int lvl = 0;
            int lvl_r_ = 0;
            int lvl_g_ = 0;
            int lvl_y_ = 0;
            int lvl_b_ = 0;
            if (xuanBao.getFushi() != null && !xuanBao.getFushi().isEmpty()) {
                String[] fushi = xuanBao.getFushi().split("\\|");
                for (int i = 0; i < fushi.length; i++) {
                    Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(fushi[i]));
                    if (goodstable != null) {
                        if (goodstable.getType() == 10018L) {
                            r_++;
                            lvl_r_ += Integer.parseInt(goodstable.getValue().split("\\|")[0].split("=")[1]);
                        } else if (goodstable.getType() == 10012L) {
                            b_++;
                            lvl_b_ += Integer.parseInt(goodstable.getValue().split("\\|")[0].split("=")[1]);
                        } else if (goodstable.getType() == 10013L) {
                            g_++;
                            lvl_g_ += Integer.parseInt(goodstable.getValue().split("\\|")[0].split("=")[1]);
                        } else if (goodstable.getType() == 10014L) {
                            y_++;
                            lvl_y_ += Integer.parseInt(goodstable.getValue().split("\\|")[0].split("=")[1]);

                        }

                    }
                }
            }
            Skill skill = GameServer.getSkill(xuanBao.getName()).clone();
            Map<Integer, String> xuanBaoSkillMap_zhuskill = new HashMap<>();
            Map<Integer, String> xuanBaoSkillMap_xuanyin1 = new HashMap<>();
            Map<Integer, String> xuanBaoSkillMap_xuanyin2 = new HashMap<>();
            Map<Integer, String> xuanBaoSkillMap_xuanyin3 = new HashMap<>();
            if (skill != null) {
                XuanBaoSkill xuanBaoSkill = xuanBaoSkillMap.get(skill.getSkillid());
                String[] sk1 = xuanBaoSkill.getSkill1().split("&");
                for (String s : sk1) {
                    xuanBaoSkillMap_zhuskill.put(Integer.parseInt(s.split("=")[0]), s.split("=")[1]);

                }
                if (skill.getSkillid() == 30019) {
                    boolean bool = false;
                }
                xuanBaoSkillMap_zhuskill = new TreeMap<>(xuanBaoSkillMap_zhuskill);
                for (Integer i : xuanBaoSkillMap_zhuskill.keySet()) {
                    if (xblvl <= i) {
                        String[] p = ((String) xuanBaoSkillMap_zhuskill.get(i)).split("\\|");
                        for (int c = 0; c < p.length; c++) {
                            if (c == 0) {
                                skill.setValue(Double.parseDouble(p[c]) / i * xblvl);
                            }
                            if (c == 1) {
                                skill.setValue1(Double.parseDouble(p[c]) / i * xblvl);
                            }
                            if (c == 2) {
                                skill.setValue2(Double.parseDouble(p[c]) / i * xblvl);
                            }
                            if (c == 3) {
                                skill.setValue3(Double.parseDouble(p[c]) / i * xblvl);
                            }
                        }
                        if (skill.getSkillid() == 30000) {
                            skill.setValue3(3.0D);
                        }
                        if (skill.getSkillid() == 30003) {
                            if (i == 49) {
                                skill.setValue(1.0D);
                            } else if (i == 50) {
                                skill.setValue(2.0D);
                            } else if (i == 100) {
                                skill.setValue(2.0D);
                            } else if (i == 150) {
                                skill.setValue(3.0D);
                            } else if (i == 200) {
                                skill.setValue(3.0D);
                            }
                        }
                        if (skill.getSkillid() == 30004) {
                            if (i == 49) {
                                skill.setValue(1.0D);
                            } else if (i == 50) {
                                skill.setValue(2.0D);
                            } else if (i == 100) {
                                skill.setValue(3.0D);
                            } else if (i == 150) {
                                skill.setValue(4.0D);
                            } else if (i == 200) {
                                skill.setValue(4.0D);
                            }
                        }
                        if (skill.getSkillid() == 30005) {
                            if (i == 49) {
                                skill.setValue(3.0D);
                                break;
                            }
                            if (i == 50) {
                                skill.setValue(4.0D);
                                break;
                            }
                            if (i == 100) {
                                skill.setValue(5.0D);
                                break;
                            }
                            if (i == 150) {
                                skill.setValue(5.0D);
                                break;
                            }
                            if (i == 200) {
                                skill.setValue(5.0D);
                            }
                        }
                        break;
                    }
                }
                if (xuanBao.skill2 != null && !xuanBao.skill2.isEmpty()) {
                    if (skill.getSkillid() == 30022) {
                        boolean bool = false;
                    }
                    if (setaplpha(xuanBao.skill2, r_, g_, y_, b_)) {
                        lvl = setaplpha(xuanBao.skill2, r_, g_, y_, b_, lvl_r_, lvl_g_, lvl_y_, lvl_b_);
                        lvl = Math.min(lvl, 30);
                        String[] sk2 = xuanBaoSkill.getSkill2().split("&");
                        for (String s : sk2) {
                            xuanBaoSkillMap_xuanyin1.put(Integer.parseInt(s.split("=")[0]), s.split("=")[1]);
                        }
                        xuanBaoSkillMap_xuanyin1 = new TreeMap<>(xuanBaoSkillMap_xuanyin1);
                        for (Integer i : xuanBaoSkillMap_xuanyin1.keySet()) {
                            if (lvl <= i) {
                                String[] p = xuanBaoSkillMap_xuanyin1.get(i).split("\\|");
                                for (int c = 0; c < p.length; c++) {
                                    if (c == 0) {
                                        skill.setS1(Double.parseDouble(p[c]) / i * lvl);
                                    }
                                    if (c == 1) {
                                        skill.setS2(Double.parseDouble(p[c]) / i * lvl);
                                    }
                                    if (c == 2) {
                                        skill.setS3(Double.parseDouble(p[c]) / i * lvl);
                                    }
                                    if (c == 3) {
                                        skill.setS4(Double.parseDouble(p[c]) / i * lvl);
                                    }
                                    if (c == 4) {
                                        skill.setS5(Double.parseDouble(p[c]) / i * lvl);
                                    }
                                    if (c == 5) {
                                        skill.setS6(Double.parseDouble(p[c]) / i * lvl);
                                    }
                                    if (c == 6) {
                                        skill.setS7(Double.parseDouble(p[c]) / i * lvl);
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                if (setaplpha(xuanBao.skill3, r_, g_, y_, b_)) {
                    lvl = setaplpha(xuanBao.skill3, r_, g_, y_, b_, lvl_r_, lvl_g_, lvl_y_, lvl_b_);
                    lvl = Math.min(lvl, 30);
                    String[] sk2 = xuanBaoSkill.getSkill3().split("&");
                    for (String s : sk2) {
                        xuanBaoSkillMap_xuanyin2.put(Integer.parseInt(s.split("=")[0]), s.split("=")[1]);
                    }
                    xuanBaoSkillMap_xuanyin2 = new TreeMap<>(xuanBaoSkillMap_xuanyin2);
                    for (Integer i : xuanBaoSkillMap_xuanyin2.keySet()) {
                        if (lvl <= i) {
                            String[] p = xuanBaoSkillMap_xuanyin2.get(i).split("\\|");
                            for (int c = 0; c < p.length; c++) {
                                if (c == 0) {
                                    skill.setP1(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 1) {
                                    skill.setP2(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 2) {
                                    skill.setP3(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 3) {
                                    skill.setP4(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 4) {
                                    skill.setP5(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 5) {
                                    skill.setP6(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 6) {
                                    skill.setP7(Double.parseDouble(p[c]) / i * lvl);
                                }
                            }
                            break;
                        }
                    }
                }
                if (xuanBao.skill4 != null && !xuanBao.skill4.isEmpty() && setaplpha(xuanBao.skill4, r_, g_, y_, b_)) {
                    lvl = setaplpha(xuanBao.skill4, r_, g_, y_, b_, lvl_r_, lvl_g_, lvl_y_, lvl_b_);
                    lvl = Math.min(lvl, 30);
                    String[] sk2 = xuanBaoSkill.getSkill4().split("&");
                    for (String s : sk2) {
                        xuanBaoSkillMap_xuanyin3.put(Integer.parseInt(s.split("=")[0]), s.split("=")[1]);
                    }
                    xuanBaoSkillMap_xuanyin3 = new TreeMap<>(xuanBaoSkillMap_xuanyin3);
                    for (Integer i : xuanBaoSkillMap_xuanyin3.keySet()) {
                        if (lvl <= i) {
                            String[] p = xuanBaoSkillMap_xuanyin3.get(i).split("\\|");
                            for (int c = 0; c < p.length; c++) {
                                if (c == 0) {
                                    skill.setE1(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 1) {
                                    skill.setE2(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 2) {
                                    skill.setE3(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 3) {
                                    skill.setE4(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 4) {
                                    skill.setE5(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 5) {
                                    skill.setE6(Double.parseDouble(p[c]) / i * lvl);
                                }
                                if (c == 6) {
                                    skill.setE7(Double.parseDouble(p[c]) / i * lvl);
                                }
                            }
                            break;
                        }
                    }
                }
            }
            if (skill != null && skill.getSkillid() == 30023) {
                boolean bool = false;
            }
            if (xuanBao.getEquipment() == 1) {
                if (skill != null) {
                    if (skill.getSkillid() == 30010) {
                        double k = 0.0D;
                        k = (manData.getHuoyue() * (1.0D + skill.getS1() / 100.0D) + manData.getShanghai() * (1.0D + skill.getP1() / 100.0D) + manData.getYuanzhu() * (0.5D + skill.getE1() / 100.0D)) * skill.getValue();
                        skill.setValue(k);
                    } else if (skill.getSkillid() == 30011) {
                        double k = 0.0D;
                        k = (manData.getHuoyue() * (1.0D + skill.getS1() / 100.0D) + manData.getKangluobao() * (1.0D + skill.getP1() / 100.0D) + manData.getYuanzhu() * (0.5D + skill.getE1() / 100.0D)) * skill.getValue();
                        skill.setValue(k);
                    } else if (skill.getSkillid() == 30012) {
                        double k = 0.0D;
                        k = (manData.getHuoyue() * (1.0D + skill.getS1() / 100.0D) + manData.getShanghai() * (1.0D + skill.getP1() / 100.0D) + manData.getYuanzhu() * (0.5D + skill.getE1() / 100.0D)) * skill.getValue();
                        skill.setValue(k);
                    }
                }
                FightingSkill fightingSkill = new FightingSkill(skill);
                if (skill.getSkillid() == 30001) {
                    manData.ddhe = (int) skill.getValue();
                    manData.ddhe_ = manData.ddhe;
                }
                if (xuanBao.getFushi() != null && !xuanBao.getFushi().isEmpty()) {
                    String[] fushi = xuanBao.getFushi().split("\\|");
                    for (int i = 0; i < fushi.length; i++) {
                        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(fushi[i]));
                        if (goodstable != null && (
                                goodstable.getType() == 10018L || goodstable.getType() == 10012L || goodstable.getType() == 10013L || goodstable.getType() == 10014L)) {
                            String[] value = goodstable.getValue().split("\\|");
                            for (int j = 0; j < value.length; j++) {
                                if (!value[j].startsWith("等级")) {
                                    String[] p = value[j].split("=");
                                    addValue(map, p[0], Double.parseDouble(p[1]));
                                }
                            }
                        }
                    }
                }
                if (skill.getSkillid() == 30011 || skill.getSkillid() == 30012 || skill.getSkillid() == 30010) {
                    if (skill.getSkillid() == 30010)
                        addValue(map, "法术伤害", fightingSkill.getSkillhurt());
                    if (skill.getSkillid() == 30011)
                        addValue(map, "物理防御", fightingSkill.getSkillhurt());
                    if (skill.getSkillid() == 30012) {
                        addValue(map, "法术防御", fightingSkill.getSkillhurt());
                    }
                    continue;
                }
                int jichu = 0;
                switch (skill.getSkillid()) {
                    case 30001:
                        jichu = 0;
                        break;
                    case 30002:
                        jichu = 100;
                        break;
                    case 30003:
                        jichu = 0;
                        break;
                    case 30004:
                        jichu = 0;
                        break;
                }
                fightingSkill.xyz = jichu * xblvl;
                if (skill.getSkillid() == 30003 || skill.getSkillid() == 30000 || skill.getSkillid() == 30002 || skill.getSkillid() == 30026 || skill.getSkillid() == 30027) {
                    if (PK_MixDeal.isPK(type))
                        manData.addSkill(fightingSkill);
                    continue;
                }
                manData.addSkill(fightingSkill);
                continue;
            }
            if (skill != null && (skill.getSkillid() == 30011 || skill.getSkillid() == 30012 || skill.getSkillid() == 30010)) {
                if (skill.getSkillid() == 30010) {
                    double k = (manData.getHuoyue() + manData.getShanghai() + manData.getYuanzhu() * 0.5D) * skill.getValue();
                    skill.setValue(k);
                } else if (skill.getSkillid() == 30011) {
                    double k = (manData.getHuoyue() + manData.getKangluobao() + manData.getYuanzhu() * 0.5D) * skill.getValue();
                    skill.setValue(k);
                } else if (skill.getSkillid() == 30012) {
                    double k = (manData.getHuoyue() + manData.getShanghai() + manData.getYuanzhu() * 0.5D) * skill.getValue();
                    skill.setValue(k);
                }
                if (skill.getSkillid() == 30010)
                    addValue(map, "法术伤害", skill.getValue() * skill.getValue1() / 100.0D);
                if (skill.getSkillid() == 30011)
                    addValue(map, "物理防御", skill.getValue() * skill.getValue1() / 100.0D);
                if (skill.getSkillid() == 30012)
                    addValue(map, "法术防御", skill.getValue() * skill.getValue1() / 100.0D);
            }
        }
    }

    public static boolean setaplpha(String text, int r, int g, int y, int b) {
        String[] mes = extractBracketContents(text).get(0).split(",");
        if (mes[0].equals("蓝") && mes[1].equals("红") && b >= 1 && r >= 1) {
            return true;
        }
        if (mes[0].equals("蓝") && mes[1].equals("蓝") && b >= 2) {
            return true;
        }
        if (mes[0].equals("蓝") && mes[1].equals("绿") && b >= 1 && g >= 1) {
            return true;
        }
        if (mes[0].equals("蓝") && mes[1].equals("黄") && b >= 1 && y >= 1) {
            return true;
        }
        if (mes[0].equals("红") && mes[1].equals("蓝") && r >= 1 && b >= 1) {
            return true;
        }
        if (mes[0].equals("红") && mes[1].equals("红") && r >= 2) {
            return true;
        }
        if (mes[0].equals("红") && mes[1].equals("绿") && r >= 1 && g >= 1) {
            return true;
        }
        if (mes[0].equals("红") && mes[1].equals("黄") && r >= 1 && y >= 1) {
            return true;
        }
        if (mes[0].equals("绿") && mes[1].equals("蓝") && g >= 1 && b >= 1) {
            return true;
        }
        if (mes[0].equals("绿") && mes[1].equals("绿") && g >= 2) {
            return true;
        }
        if (mes[0].equals("绿") && mes[1].equals("红") && g >= 1 && r >= 1) {
            return true;
        }
        if (mes[0].equals("绿") && mes[1].equals("黄") && g >= 1 && y >= 1) {
            return true;
        }
        if (mes[0].equals("黄") && mes[1].equals("蓝") && y >= 1 && b >= 1) {
            return true;
        }
        if (mes[0].equals("黄") && mes[1].equals("黄") && y >= 2) {
            return true;
        }
        if (mes[0].equals("黄") && mes[1].equals("红") && y >= 1 && r >= 1) {
            return true;
        }
        if (mes[0].equals("黄") && mes[1].equals("绿") && y >= 1 && g >= 1) {
            return true;
        }
        return false;
    }

    public static int setaplpha(String text, int r, int g, int y, int b, int lvl_r, int lvl_g, int lvl_y, int lvl_b) {
        String[] mes = extractBracketContents(text).get(0).split(",");
        if (mes[0].equals("蓝") && mes[1].equals("红") && b >= 1 && r >= 1) {
            return lvl_r + lvl_b;
        }
        if (mes[0].equals("蓝") && mes[1].equals("蓝") && b >= 2) {
            return lvl_b;
        }
        if (mes[0].equals("蓝") && mes[1].equals("绿") && b >= 1 && g >= 1) {
            return lvl_b + lvl_g;
        }
        if (mes[0].equals("蓝") && mes[1].equals("黄") && b >= 1 && y >= 1) {
            return lvl_b + lvl_y;
        }
        if (mes[0].equals("红") && mes[1].equals("蓝") && r >= 1 && b >= 1) {
            return lvl_r + lvl_b;
        }
        if (mes[0].equals("红") && mes[1].equals("红") && r >= 2) {
            return lvl_r;
        }
        if (mes[0].equals("红") && mes[1].equals("绿") && r >= 1 && g >= 1) {
            return lvl_r + lvl_g;
        }
        if (mes[0].equals("红") && mes[1].equals("黄") && r >= 1 && y >= 1) {
            return lvl_r + lvl_y;
        }
        if (mes[0].equals("绿") && mes[1].equals("蓝") && g >= 1 && b >= 1) {
            return lvl_g + lvl_b;
        }
        if (mes[0].equals("绿") && mes[1].equals("绿") && g >= 2) {
            return lvl_g;
        }
        if (mes[0].equals("绿") && mes[1].equals("红") && g >= 1 && r >= 1) {
            return lvl_g + lvl_r;
        }
        if (mes[0].equals("绿") && mes[1].equals("黄") && g >= 1 && y >= 1) {
            return lvl_g + lvl_y;
        }
        if (mes[0].equals("黄") && mes[1].equals("蓝") && y >= 1 && b >= 1) {
            return lvl_y + lvl_b;
        }
        if (mes[0].equals("黄") && mes[1].equals("黄") && y >= 2) {
            return lvl_y;
        }
        if (mes[0].equals("黄") && mes[1].equals("红") && y >= 1 && r >= 1) {
            return lvl_y + lvl_r;
        }
        if (mes[0].equals("黄") && mes[1].equals("绿") && y >= 1 && g >= 1) {
            return lvl_y + lvl_g;
        }
        return 0;
    }

    public static List<String> extractBracketContents(String input) {
        List<String> contents = new ArrayList<>();
        String regex = "\\[(.*?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String content = matcher.group(1);
            contents.add(content);
        }
        return contents;
    }
    //修改过的抗性上限
    private static void addBaseRaceKangXing(Map<String, Double> map, BigDecimal race_id) {
        if (race_id.intValue() == 10001) {}
        if (race_id.intValue() == 10002) {
            map.put("命中率", 5.0);
            map.put("狂暴率", 5.0);
            map.put("致命率", 5.0);
        }
        if (race_id.intValue() == 10003) {}
        if (race_id.intValue() == 10004) {}
        if (race_id.intValue() == 10005) {
            map.put("命中率", 5.0);
            map.put("狂暴率", 5.0);
            map.put("致命率", 5.0);
        }
    }
    /**取所有的抗性*/
    public static List<String> allProperty(Map<String, Double> map, Map<String, Double> eMap, Map<String, Double> roleMap) {
        List<String> Propertys = new ArrayList<>();
        for (String key : eMap.keySet()) {
            Propertys.add(key);
        }
        for (String key : map.keySet()) {
            if (!Propertys.contains(key)) {
                Propertys.add(key);
            }
        }
        for (String key : roleMap.keySet()) {
            if (!Propertys.contains(key)) {
                Propertys.add(key);
            }
        }
        return Propertys;
    }
    
    public static int getBase(BigDecimal race_id, int lvl, int value, int type, Map<String, Double> map, Map<String, Double> eMap, Map<String, Double> roleMap) {
        value = BaseValue.getRoleValue(race_id, value, lvl, type);
        if (type == 0) {
            value = (int)((double)value + removeValue("hp", map, eMap, roleMap));
            value = (int)((double)value + removeValue("HP", map, eMap, roleMap));
            value = (int)((double)value + removeValue("加气血", map, eMap, roleMap));
            value = (int)((double)value + removeValue("附加气血", map, eMap, roleMap));
            value = (int)((double)value * (removeValue("HP成长", map, eMap, roleMap) + 1.0));
            value = (int)((double)value * (removeValue("加强气血", map, eMap, roleMap) / 100.0 + 1.0));
            value = (int)((double)value * (removeValue("气血增加率", map, eMap, roleMap) / 100.0 + 1.0));
        }
        else if (type == 1) {
            value = (int)((double)value + removeValue("mp", map, eMap, roleMap));
            value = (int)((double)value + removeValue("MP", map, eMap, roleMap));
            value = (int)((double)value + removeValue("加魔法", map, eMap, roleMap));
            value = (int)((double)value + removeValue("附加法力", map, eMap, roleMap));
            value = (int)((double)value + removeValue("附加魔法", map, eMap, roleMap));
            value = (int)((double)value * (removeValue("MP成长", map, eMap, roleMap) + 1.0));
            value = (int)((double)value * (removeValue("加强法力", map, eMap, roleMap) / 100.0 + 1.0));
        }
        else if (type == 2) {
            value = (int)((double)value + removeValue("ap", map, eMap, roleMap));
            value = (int)((double)value + removeValue("AP", map, eMap, roleMap));
            value = (int)((double)value + removeValue("攻击", map, eMap, roleMap));
            value = (int)((double)value + removeValue("加攻击", map, eMap, roleMap));
            value = (int)((double)value + removeValue("基础攻击", map, eMap, roleMap));
            value = (int)((double)value  * (removeValue("加强攻击", map, eMap, roleMap) / 100.0 + 1.0));
            value = (int)((double)value  * (removeValue("加成攻击", map, eMap, roleMap) / 100.0  + 1.0));
            value = (int)((double)value + removeValue("附加攻击", map, eMap, roleMap));
            value = (int)((double)value * (removeValue("AP成长", map, eMap, roleMap)+1));

        }
        else if (type == 3) {
            value = (int)((double)value + removeValue("sp", map, eMap, roleMap));
            value = (int)((double)value + removeValue("SP", map, eMap, roleMap));
            value = (int)((double)value + removeValue("速度", map, eMap, roleMap));
            value = (int)((double)value + removeValue("加速度", map, eMap, roleMap));
            value = (int)((double)value + removeValue("附加速度", map, eMap, roleMap));
            value = (int)((double)value * (removeValue("SP成长", map, eMap, roleMap) + 1.0));
            value = (int)((double)value * (removeValue("加强速度", map, eMap, roleMap) / 100.0 + 1.0));
        }
        return value;
    }
    /**取值 上限判断*/
    public static double getValue(BigDecimal raceId, String key, Map<String, Double> map, Map<String, Double> eMap, Map<String, Double> roleMap, double ZBMAX) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(1);
        int max = 0;
        if (configure.getRwkxsx() != null && configure.getRwkxsx() != "") {
            max = Integer.parseInt(configure.getRwkxsx());
        }
        Double additional = (Double)map.get(key);
        Double equip = (Double)eMap.get(key);
        Double role = (Double)roleMap.get(key);
        if (additional == null) {
            additional = 0.0;
        }
        if (equip == null) {
            equip = 0.0;
        }
        if (role == null) {
            role = 0.0;
        }
        if (key.endsWith("上限")) {
            return (double)equip + (double)additional + (double)role;
        }
        if (key.equals("抗混乱") || key.equals("抗昏睡") || key.equals("抗封印") || key.equals("抗遗忘")) {
            double sx = BaseValue.Upper(key, raceId);
            sx += (double)max;
            sx *= 1.0 + getValue(raceId, key + "上限", map, eMap, roleMap, ZBMAX) / 100.0;
            double z = (double)equip + (double)role;
            return ((z > sx) ? sx : z) + (double)additional;
        }
        if (!key.equals("抗三尸") && !key.equals("抗浩然正气") && !key.equals("抗隔山打牛") && !key.equals("抗青面獠牙") && !key.equals("抗天魔解体") && !key.equals("抗小楼夜哭") && !key.equals("抗分光化影")) {
            if (key.startsWith("抗") || key.contains("物理吸收率")) {
                if ((key.startsWith("抗雷") || key.startsWith("抗水") || key.startsWith("抗火") || key.startsWith("抗风")) && (double)role < 0.0) {
                    return ((double)equip + (double)role > (double)(75 + max)) ? ((double)(75 + max) + (double)additional) : ((double)equip + (double)role + (double)additional);
                }
                return ((double)equip > (double)(75 + max)) ? ((double)(75 + max) + (double)role + (double)additional) : ((double)equip + (double)role + (double)additional);
            }
            else {
                if (key.equals("连击率")) {
                    return ((double)equip + (double)additional + (double)role > (double)(75 + max)) ? ((double)(75 + max) + (double)role + (double)additional) : ((double)equip + (double)additional + (double)role);
                }
                if (key.equals("躲闪率")) {
                    if ((double)equip > 75.0) {
                        equip = 75.0;
                    }
                    return (double)equip + (double)additional + (double)role;
                }
            }
        }
        double var=(double)equip + (double)additional + (double)role;
        if ((key.equals("命中率") ||key.equals("命中") ||key.equals("致命")||key.equals("致命率")||key.equals("致命几率"))&&var>75+ (double)additional + (double)role) {
            var=75+ (double)additional + (double)role;
        }
        if ((key.equals("狂暴几率")||key.equals("狂暴")||key.equals("狂暴率"))&&var>80+ (double)additional + (double)role) {
            var = 80+ (double)additional + (double)role;//设置的上限
        }
        if (key.equals("连击次数")&&var>14) {
            var = 14;//设置的上限
        }

        return var;
    }
    
    public static double removeValue(String key, Map<String, Double> map, Map<String, Double> eMap, Map<String, Double> roleMap) {
        Double additional = (Double)map.get(key);
        Double equip = (Double)eMap.get(key);
        Double role = (Double)roleMap.get(key);
        if (additional == null) {
            additional = 0.0;
        }
        if (equip == null) {
            equip = 0.0;
        }
        if (role == null) {
            role = 0.0;
        }
        return (double)equip + (double)additional + (double)role;
    }
    /**取值*/
    public static double removeValue(String key, Map<String, Double> map) {
        Double value = (Double)map.get(key);
        return (value != null) ? ((double)value) : 0.0;
    }
    /**满足 返回ture*/
    public static boolean isValue(String key, int value, Map<String, Double> map, Map<String, Double> eMap, Map<String, Double> roleMap) {
        Double additional = (Double)map.get(key);
        Double equip = (Double)eMap.get(key);
        Double role = (Double)roleMap.get(key);
        if (additional == null) {
            additional = 0.0;
        }
        if (equip == null) {
            equip = 0.0;
        }
        if (role == null) {
            role = 0.0;
        }
        return (double)equip + (double)additional + (double)role >= (double)value;
    }
    /**技能*/
    public static void getSkill(ManData manData, Map<String, Double> map, List<BaseSkill> baseSkills, LoginResult login) {
        if (baseSkills != null) {
            for (int i = 0; i < baseSkills.size(); ++i) {
                BaseSkill baseSkill = (BaseSkill)baseSkills.get(i);
                if (baseSkill.getQl() != null) {
                    addValue(map, baseSkill.getQl().getKey(), baseSkill.getQl().getValue());
                }
                else {
                    double sl = login.getMeridiansValue("法术熟练");
                    FightingSkill fightingSkill;
                    if (baseSkill.getSkillId() > 1000 && baseSkill.getSkillId() <= 1100 && sl > 0.0) {
                        fightingSkill = new FightingSkill(baseSkill.getSkill(), manData.getlvl(), manData.getZs(), (double)(baseSkill.getLvl() + (int)sl), 0L, 0);
                    }
                    else {
                        fightingSkill = new FightingSkill(baseSkill.getSkill(), manData.getlvl(), manData.getZs(), (double)baseSkill.getLvl(), 0L, 0);
                    }
                    manData.addSkill(fightingSkill);
                }
            }
        }
    }
    /**符文类添加*/
    public static void addBaseQl(Map<String, Double> map, BaseQl[] vs, ManData manData, LoginResult login) {
        if (vs == null) {
            return;
        }
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i] != null) {
                if (vs[i].getKey().equals("技能")) {
                    Skill skill = GameServer.getSkill((int)vs[i].getValue() + "");
                    if (skill != null) {
                        FightingSkill fightingSkill = new FightingSkill(skill, manData.getlvl(), manData.getZs(), 1.0, 0L, 1);
                        if (fightingSkill.getSkillid() == 1832) {
                            fightingSkill.setSkillhurt(33.0);
                        }
                        else if (fightingSkill.getSkillid() == 1833) {
                            if ((int)login.getPower() < 450) {
                                continue;
                            }
                            else {
                                fightingSkill.setSkillhurt(66.0);
                            }
                        }
                        manData.addSkill(fightingSkill);
                    }
                }
                else if (vs[i].getKey().equals("经验加成")) {
                    manData.setExp2XS((double) manData.getExp2XS() + vs[i].getValue() / 100.0);
                }
                else if (vs[i].getKey().equals("怨气受击回复")) {
                    manData.addAddState("怨气", 50.0, 0.0, 9999);
                }
                else if (vs[i].getKey().equals("复活")) {
                    manData.addAddState("复活", 0.5 * (double)manData.getHp_z(), 0.0, 9999);
                }
                else {
                    TeamBean bean = TeamUtil.getTeam(login.getTroop_id());
                    long role_bsk = 0L;//变卡数
                    long qhl = 0L;//总亲和力
                    int countForValue = 0;
                    int countForValue2 = 0;
                    if (bean != null && (vs[0].getKey().equals("皮肤") || vs[0].getKey().equals("皮肤不变"))) {
                        for (int j = 0; j < bean.getTeams().size(); ++j) {
                            TeamRole role = (TeamRole)bean.getTeams().get(j);
                            RoleData roleData = RolePool.getRoleData(role.getRoleId());
                            if (roleData != null) {
                                UseCardBean limit = roleData.getLimit("变身卡");
                                if (limit != null) {
                                    String[] v = roleData.getLimit("变身卡").getValue().split("\\|");
                                    String[] v2 = v[1].split("=");
                                    String[] v3 = v[2].split("=");
                                    String[] v4 = v[0].split("=");
                                    if (CalculationUtil.isBsk) {
                                        ++role_bsk;
                                        qhl += Long.parseLong(v2[1]);
                                        if (!vs[2].getValue1().equals(v3[1])) {
                                            ++countForValue;
                                        }
                                        if (vs[0].getValue() == Double.parseDouble(v4[1])) {
                                            ++countForValue2;
                                        }
                                    }
                                }
                            }
                        }
                        DecimalFormat df = new DecimalFormat("#.#");
                        double value = 1.04 + (double)role_bsk * 0.28 - (double)((role_bsk + 1L) * role_bsk) * 0.01 - Math.abs((vs[1].getValue() - (double)qhl / (double)role_bsk) * 0.1) - (double)(countForValue2 - 1) * 0.18 - (double)countForValue * 0.05;
                        if (bean.getTeamSize() > 1) {
                            if (vs[i].getKey().equals("加强气血")) {
                                sendBsk(login, "#R你的最大HP增加了" + df.format(vs[i].getValue() * value) + "%");
                            }
                            else if (vs[i].getKey().equals("加强魔法")) {
                                sendBsk(login, "#R你的最大MP增加了" + df.format(vs[i].getValue() * value) + "%");
                            }
                            else if (vs[i].getKey().equals("加强攻击")) {
                                sendBsk(login, "#R你的AP增加了" + df.format(vs[i].getValue() * value) + "%");
                            }
                            else if (vs[i].getKey().equals("加强速度")) {
                                sendBsk(login, "#R你的最大SP增加了" + df.format(vs[i].getValue() * value) + "%");
                            }
                            else if (!vs[i].getKey().equals("皮肤") && !vs[i].getKey().equals("皮肤不变") && !vs[i].getKey().equals("亲和力") && !vs[i].getKey().equals("种族") && !vs[i].getKey().equals("金") && !vs[i].getKey().equals("木") && !vs[i].getKey().equals("水") && !vs[i].getKey().equals("火") && !vs[i].getKey().equals("土")) {
                                sendBsk(login, "#R你的" + vs[i].getKey() + "增加了" + df.format(vs[i].getValue() * value) + "%");
                            }
                        }
                        if (role_bsk > 1L) {
                            if (vs[i].getKey().equals("金") || vs[i].getKey().equals("木") || vs[i].getKey().equals("水") || vs[i].getKey().equals("火") || vs[i].getKey().equals("土")) {
                                addValue(map, vs[i].getKey(), vs[i].getValue());
                            }
                            else if (!vs[i].getKey().equals("皮肤") && !vs[i].getKey().equals("亲和力") && !vs[i].getKey().equals("种族")) {
                                addValue(map, vs[i].getKey(), vs[i].getValue() * value);
                                SendMessage.sendMessageByRoleName(login.getRolename(), Agreement.getAgreement().battleStateAgreement("BSKValve=" + value));
                            }
                        }
                        else {
                            addValue(map, vs[i].getKey(), vs[i].getValue());
                        }
                    }
                    else {
                        addValue(map, vs[i].getKey(), vs[i].getValue());
                    }
                }
            }
        }
    }
    
    public static void sendBsk(LoginResult loginResult, String message) {
        NChatBean nChatBean = new NChatBean();
        nChatBean.setId(6);
        nChatBean.setMessage(message);
        nChatBean.setRoleId(loginResult.getRole_id());
        nChatBean.setRole(loginResult.getRolename());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(nChatBean));
        SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg);
    }
    
    public static void addBaseQl(Map<String, Double> map, BaseQl[] vs) {
        if (vs == null) {
            return;
        }
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i] != null) {
                addValue(map, vs[i].getKey(), vs[i].getValue());
            }
        }
    }
    
    public static void addValue(Map<String, Double> map, String key, double value) {
        Double v = (Double)map.get(key);
        if (v != null) {
            value += (double)v;
        }
        map.put(key, value);
    }
    
    public static void addVS(Map<String, Double> map, String v) {
        if (v == null || v.equals("") || !v.startsWith("抗")) {
            return;
        }
        try {
            String[] vs = v.split("=");
            double value = Double.parseDouble(vs[1]);
            if (value <= 10.1) {
                addValue(map, vs[0], value);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**大闹属性添加*/
    public static void addDNTG(ManData data, String text) {
        if (text == null || text.equals("")) {
            return;
        }
        String[] vs = text.split("&");
        for (int i = 0; i < vs.length; ++i) {
            String[] v = vs[i].split("\\$");
            Skill skill = GameServer.getSkill(v[0]);
            if (skill != null) {
                int lvl = Integer.parseInt(v[1]);
                double value = (double)lvl * skill.getGrow();
                if (skill.getSkillid() == 10001) {
                    data.setHp_z((int)((double)data.getHp_z() + value));
                    data.setHp((int)((double)data.getHp() + value));
                }
                else if (skill.getSkillid() == 10002) {
                    GetqualityUntil.AddR(data.getQuality(), "忽视抗封", value);
                    GetqualityUntil.AddR(data.getQuality(), "忽视抗混", value);
                    GetqualityUntil.AddR(data.getQuality(), "忽视抗昏", value);
                    GetqualityUntil.AddR(data.getQuality(), "忽视遗忘", value);
                }
                else if (skill.getSkillid() == 10003) {
                    data.setMp_z((int)((double)data.getMp_z() + value));
                    data.setMp((int)((double)data.getMp() + value));
                }
                else if (skill.getSkillid() == 10004) {
                    GetqualityUntil.AddR(data.getQuality(), "忽视抗风", value);
                    GetqualityUntil.AddR(data.getQuality(), "忽视抗雷", value);
                    GetqualityUntil.AddR(data.getQuality(), "忽视抗水", value);
                    GetqualityUntil.AddR(data.getQuality(), "忽视抗火", value);
                    GetqualityUntil.AddR(data.getQuality(), "忽视抗鬼火", value);
                    GetqualityUntil.AddR(data.getQuality(), "加强毒伤害", value);
                }
                else if (skill.getSkillid() == 10005) {
                    data.setSp((int)(data.getvalue(7) + value));
                }
                else if (skill.getSkillid() == 10006) {
                    GetqualityUntil.AddR(data.getQuality(), "加强三尸虫", value);
                }
                else if (skill.getSkillid() == 10007) {
                    data.setAp((int)(data.getvalue(5) + value));
                }
                else if (skill.getSkillid() == 10008) {
                    GetqualityUntil.AddR(data.getQuality(), "忽视抗震慑", value);
                }
            }
        }
    }
    
    public static void getPet(Ql ql, RoleSummoning pet, Mount mount, ManData data) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = s.get(1);

        int up = 75;//抗性上限过滤
        if (configure.getZhskxsx() != null && !configure.getZhskxsx().equals("")) {
            up += Integer.parseInt(configure.getZhskxsx());
        }
        int[] pets = new int[5];
        int lvl = data.getLvl();
        double grow = (double)Double.valueOf(pet.getGrowlevel());
        pets[0] = pet.getHp();
        pets[1] = pet.getMp();
        pets[2] = pet.getAp();
        pets[3] = pet.getSp();
        pets[4] = 0;
        int zBone = (int)pet.getBone();
        int zSpir = (int)pet.getSpir();
        int zPower = (int)pet.getPower();
        int zSpeed = (int)pet.getSpeed();
        int zCalm = (int)pet.getCalm();
        int addhp = 0;
        int addmp = 0;
        int addap = 0;
        StringBuffer buffer = new StringBuffer();
        buffer.append(pet.getSummoningskin());
        buffer.append("_1_7");
        if (pet.getColorScheme() != null && !pet.getColorScheme().equals("")) {
            buffer.append("_");
            buffer.append(pet.getColorScheme());
        }
        try {
            BaseSkill skill0 = null;//召唤兽装备特技
            BaseSkill skill2 = null;//召唤兽装备特技
            BaseSkill skill3 = null;//召唤兽装备特技
            Map<String, Double> map = null;
            if (pet.getStye() != null && pet.getStye().length() > 1) {
                String[] v = pet.getStye().split("\\|");
                for (int i = 1; i < v.length; ++i) {
                    String[] vs = v[i].split("-");
                    if (vs.length >= 2) {
                        if (vs[0].equals("3") && vs.length > 2) {
                            String color = null;
                            if (vs.length > 3) {
                                ColorScheme value = GameServer.getColor(vs[3]);
                                color = ((value != null) ? value.getValue() : null);
                            }
                            buffer.append("&");
                            buffer.append(vs[2]);
                            buffer.append("_1_7");
                            if (color != null) {
                                buffer.append("_");
                                buffer.append(color);
                            }
                        }
                        Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[1]));
                        BaseEquip baseEquip = (good != null) ? good.getEquip() : null;
                        if (baseEquip != null) {
                            if (baseEquip.getQls() != null) {
                                for (int j = baseEquip.getQls().size() - 1; j >= 0; --j) {
                                    BaseQl baseQl = (BaseQl)baseEquip.getQls().get(j);
                                    if ("被攻击时释放含情脉脉".equals(baseQl.getKey()) || "被攻击时释放魔神附身".equals(baseQl.getKey()) || "被攻击时释放乾坤借速".equals(baseQl.getKey())) {
                                        AddState addState = new AddState(baseQl.getKey().substring(baseQl.getKey().length() - 4, baseQl.getKey().length()), baseQl.getValue(), 0.0, 9999);
                                        if ("被攻击时释放含情脉脉".equals(baseQl.getKey())) {
                                            Skill skill4 = GameServer.getSkill("1035");
                                            if (skill4 != null) {
                                                FightingSkill fightingSkill = new FightingSkill(skill4, data.getlvl(), data.getZs(), 10000.0, 0L, 0);
                                                addState.setSkill(fightingSkill);
                                                fightingSkill.setSkillblue(0);
                                                fightingSkill.setSkillsum(3);
                                                addState.setStateEffect2(1035.0);
                                            }
                                        }
                                        else if ("被攻击时释放魔神附身".equals(baseQl.getKey())) {
                                            Skill skill4 = GameServer.getSkill("1030");
                                            if (skill4 != null) {
                                                FightingSkill fightingSkill = new FightingSkill(skill4, data.getlvl(), data.getZs(), 25000.0, 0L, 0);
                                                addState.setSkill(fightingSkill);
                                                fightingSkill.setSkillblue(0);
                                                fightingSkill.setSkillsum(3);
                                                addState.setStateEffect2(1030.0);
                                            }
                                        }
                                        else if ("被攻击时释放乾坤借速".equals(baseQl.getKey())) {
                                            Skill skill4 = GameServer.getSkill("1040");
                                            if (skill4 != null) {
                                                FightingSkill fightingSkill = new FightingSkill(skill4, data.getlvl(), data.getZs(), 25000.0, 0L, 0);
                                                fightingSkill.setSkillblue(0);
                                                fightingSkill.setSkillsum(3);
                                                addState.setSkill(fightingSkill);
                                                addState.setStateEffect2(1040.0);
                                            }
                                        }
                                        data.getAddStates().add(addState);
                                    }
                                    if (baseQl.getKey().endsWith("等级")) {
                                        if (map == null) {
                                            map = new HashMap<>();
                                        }
                                        addValue(map, baseQl.getKey(), baseQl.getValue());
                                    }
                                    else if (baseQl.getKey().endsWith(CalculationUtil.evs[0])) {
                                        zBone = (int)((double)zBone + baseQl.getValue());
                                    }
                                    else if (baseQl.getKey().endsWith(CalculationUtil.evs[1])) {
                                        zSpir = (int)((double)zSpir + baseQl.getValue());
                                    }
                                    else if (baseQl.getKey().endsWith(CalculationUtil.evs[2])) {
                                        zPower = (int)((double)zPower + baseQl.getValue());
                                    }
                                    else if (baseQl.getKey().endsWith(CalculationUtil.evs[3])) {
                                        zSpeed = (int)((double)zSpeed + baseQl.getValue());
                                    }
                                    else if (baseQl.getKey().endsWith(CalculationUtil.evs[4])) {
                                        addhp = (int)((double)addhp + baseQl.getValue());
                                    }
                                    else if (baseQl.getKey().endsWith(CalculationUtil.evs[5])) {
                                        addmp = (int)((double)addmp + baseQl.getValue());
                                    }
                                    else if (baseQl.getKey().endsWith(CalculationUtil.evs[6])) {
                                        addap = (int)((double)addap + baseQl.getValue());
                                    }
                                    else {
                                        GetqualityUntil.AddR(ql, baseQl.getKey(), baseQl.getValue());
                                    }
                                }
                            }
                            if (baseEquip.getBaseSkills() != null && baseEquip.getBaseSkills().size() != 0) {
                                if (good.getType() == 510L) {
                                    skill0 = (BaseSkill)baseEquip.getBaseSkills().get(0);
                                }
                                else if (good.getType() == 511L) {
                                    skill2 = (BaseSkill)baseEquip.getBaseSkills().get(0);
                                }
                                else if (good.getType() == 512L) {
                                    skill3 = (BaseSkill)baseEquip.getBaseSkills().get(0);
                                }
                            }
                        }
                    }
                }
            }
            pets[0] = BaseValue.getPetValue(lvl, zBone, grow, pets[0], 0) + addhp;
            pets[1] = BaseValue.getPetValue(lvl, zSpir, grow, pets[1], 1) + addmp;
            pets[2] = BaseValue.getPetValue(lvl, zPower, grow, pets[2], 2) + addap;
            pets[3] = BaseValue.getPetValue(lvl, zSpeed, grow, pets[3], 3);
            pets[4] = BaseValue.getPetValue(lvl, zCalm, grow, pets[4], 4);
            try {
                //重置双敏叠加
                List<Integer> skills = new ArrayList<>();
                if (pet.getPetSkills() != null && !pet.getPetSkills().equals("")) {
                    String[] vs2 = pet.getPetSkills().split("\\|");
                    for (int k = 0; k < vs2.length; ++k) {
                        if (!vs2[k].equals("")) {
                            skills.add(Integer.parseInt(vs2[k]));
                        }
                    }
                }
                String s2 = BattskillData(skills, pet.getBeastSkills());
                getSI(pets, s2);
            }
            catch (Exception e) {
                getSI(pets, pet.getSkillData());
            }
            pet.getLX(pets);
            shouhuvalue(pets, pet, mount, ql, data);
            if (pet.getPetSkills() != null && pet.getPetSkills().contains("1254")) {
                int n = 2;
                pets[n] = (int)((double)pets[n] * 1.3);
            }
            if (mount != null) {
                up += 20;
                List<MountSkill> mountSkills = mount.getMountskill();
                if (mountSkills != null) {
                    double xs1 = 1.0;
                    double xs2 = 1.0;
                    double xs3 = 1.0;
                    double xs4 = 1.0;
                    for (int l = 0; l < mountSkills.size(); ++l) {
                        String ms = CalculationMount.calculateAddition(mount, ((MountSkill)mountSkills.get(l)).getSkillname(), ql);
                        if (ms != null) {
                            String[] v2 = ms.split("=");
                            if (v2[0].equals("HP")) {
                                xs1 += Double.parseDouble(v2[1]);
                            }
                            else if (v2[0].equals("MP")) {
                                xs2 += Double.parseDouble(v2[1]);
                            }
                            else if (v2[0].equals("AP")) {
                                xs3 += Double.parseDouble(v2[1]);
                            }
                            else if (v2[0].equals("SP")) {
                                xs4 += Double.parseDouble(v2[1]);
                            }
                        }
                    }
                    int n2 = 0;
                    pets[n2] = (int)((double)pets[n2] * xs1);
                    int n3 = 1;
                    pets[n3] = (int)((double)pets[n3] * xs2);
                    int n4 = 2;
                    pets[n4] = (int)((double)pets[n4] * xs3);
                    int n5 = 3;
                    pets[n5] = (int)((double)pets[n5] * xs4);
                }
            }
            //天生抗性
            if (pet.getResistance() != null && !pet.getResistance().equals("")) {
                String[] v = pet.getResistance().split("\\|");
                if (v.length >= 3) {
                    cl(data);
                    return;
                }
                for (int i = 0; i < v.length; ++i) {
                    String[] v3 = v[i].split("=");
                    double value2 = Double.parseDouble(v3[1]);
                    if (value2 != 30.0) {
                        cl(data);//防修改
                    }
                    GetqualityUntil.AddR(ql, v3[0], value2);
                }
            }

            if (pet.getInnerGoods() != null && !pet.getInnerGoods().equals("")) {
                String[] vv = pet.getInnerGoods().split("\\|");
                for (int i = 0; i < vv.length; ++i) {
                    Goodstable good2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vv[i]));
                    if (good2 != null) {
                        String goodname = good2.getGoodsname();
                        boolean type = goodname.equals("红颜白发") || goodname.equals("梅花三弄") || goodname.equals("开天辟地") || goodname.equals("万佛朝宗") || goodname.equals("暗渡陈仓") || goodname.equals("借力打力") || goodname.equals("凌波微步");
                        if (data != null || type) {
                            String[] strings = good2.getValue().split("\\|");
                            String[] stringLevel = strings[2].split("\\=");
                            String[] stringLevel2 = stringLevel[1].split("\\转");
                            int nddj = Integer.parseInt(stringLevel2[1]);//内丹等级
                            if (map != null) {
                                Double lll = (Double)map.get(goodname + "等级");
                                if (lll != null) {
                                    nddj = (int)((double)nddj + (double)lll);
                                }
                            }
                            int ndzscs = Integer.parseInt(stringLevel2[0]);//内丹转生次数
                            if (data != null) {
                                if (goodname.equals("红颜白发") || goodname.equals("梅花三弄") || goodname.equals("开天辟地") || goodname.equals("万佛朝宗")) {
                                    data.neidang("tj", ndzscs);
                                }
                                else if (goodname.equals("分光化影") || goodname.equals("天魔解体") || goodname.equals("小楼夜哭") || goodname.equals("青面獠牙")) {
                                    data.neidang("mj", ndzscs);
                                }
                                else if (goodname.equals("乘风破浪") || goodname.equals("霹雳流星") || goodname.equals("大海无量") || goodname.equals("祝融取火")) {
                                    data.neidang("xl", ndzscs);
                                }
                                else {
                                    data.neidang("rj", ndzscs);
                                }
                            }
                            if (type) {
                                CalculationPet.addNedanMsg(pet, ql, nddj, ndzscs, goodname);
                                if (goodname.equals("暗渡陈仓")) {
                                    int zhsdj = BattleMixDeal.petLvlint((int)pet.getGrade());//召唤兽等级
                                    int zhszscs = pet.getTurnRount();//召唤兽转生次数
                                    long zhsqm = (long)pet.getFriendliness();//召唤兽亲密值
                                    double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * CalculationPet.xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
                                    FightingSkill skill5 = new FightingSkill();
                                    skill5.setCamp(-1);
                                    skill5.setSkillbeidong(1);
                                    skill5.setSkillname(goodname);
                                    skill5.setSkilltype(goodname);
                                    skill5.setSkillhurt((double)Math.round(ndjl * 10000.0) / 100.0); // 忽视躲闪、忽视反击
                                    skill5.setSkillid(0);
                                    data.addSkill(skill5);
                                }
                            }
                            else if (data != null) {
                                FightingSkill skill6 = CalculationPet.accessNedanMsg(good2, nddj, ndzscs, data.getLvl(), data.getZs(), (long)pet.getFriendliness(), pets[1]);
                                data.addSkill(skill6);
                            }
                        }
                    }
                }
            }
            if (pet.getLyk() != null && !pet.getLyk().equals("")) {
                String[] v = pet.getLyk().split("\\|");
                for (int i = 0; i < v.length; ++i) {
                    String[] v3 = v[i].split("=");
                    GetqualityUntil.AddR(ql, v3[0], Double.parseDouble(v3[1]));
                }
            }
            //炼妖属性
            if ((long)pet.getFriendliness() > 100000L) {
                double ljv = 5.0 + CustomFunction.XS((long)pet.getFriendliness(), 0.7);
                if (ljv > 12.0) {
                    ljv = 12.0;
                }
                int ljs = (int)(3.0 + CustomFunction.XS((long)pet.getFriendliness(), 0.1));
                if (ljs > 9) {
                    ljs = 9;
                }
                double mzv = 2.0 + CustomFunction.XS((long)pet.getFriendliness(), 0.4);
                if (mzv > 30.0) {
                    mzv = 30.0;
                }
                GetqualityUntil.AddR(ql, "连击率", ljv);
                GetqualityUntil.AddR(ql, "连击次数", (double)ljs);
                GetqualityUntil.AddR(ql, "命中率", mzv);
            }
            //修改专属召唤兽装备觉醒技
            if (skill3 != null && skill3.getSkillId() == 1321) {//修改只有大浪能用乱舞狂刀
                if (!"200207".equals(pet.getSummoningid()) && !"200239".equals(pet.getSummoningid()) && !"200099".equals(pet.getSummoningid())) {
                    skill3 = null;
                }
            }
            else if (skill3 != null && skill3.getSkillId() == 1323) {//慧心巧思颜如玉
                if (!"200101".equals(pet.getSummoningid()) && !"200209".equals(pet.getSummoningid())) {
                    skill3 = null;
                }
            }
            else if (skill3 != null && skill3.getSkillId() == 1320) {//黄泉一笑范式之魂
                if (!"200098".equals(pet.getSummoningid()) && !"200206".equals(pet.getSummoningid())) {
                    skill3 = null;
                }
            }
            else if (skill3 != null && skill3.getSkillId() == 1337) {//不夜天灯火阑珊
                if (!"200903".equals(pet.getSummoningid())) {
                    skill3 = null;
                }
            }
            else if (skill3 != null && skill3.getSkillId() == 1322) {//皮糙肉厚五页
                if (!"200201".equals(pet.getSummoningid()) && !"200208".equals(pet.getSummoningid()) && !"200100".equals(pet.getSummoningid())) {
                    skill3 = null;
                }
            }
            else if (skill3 != null && skill3.getSkillId() == 1322 && !"200205".equals(pet.getSummoningid()) && !"200097".equals(pet.getSummoningid()) && !"200241".equals(pet.getSummoningid())) {
                skill3 = null;//垂云叟
            }
            FightingSkill skill7 = CalculationPet.JX(skill0, skill2, skill3);
            if (skill7 != null) {
                data.addSkill(skill7);
                buffer.append("&jx_6_-1");
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        List<FightingSkill> skills2 = data.getSkills();
        for (int m = 0; m < skills2.size(); ++m) {
            FightingSkill skill8 = (FightingSkill)skills2.get(m);
            String skilltype = skill8.getSkilltype();
            int n6 = -1;
            switch (skilltype.hashCode()) {
                case 1509633: {
                    if (skilltype.equals("1299")) {
                        n6 = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n6) {
                case 0: {
                    pets[0] = (int)((double)pets[0] * skill8.getSkillhurt() / 100.0);
                    pets[1] = (int)((double)pets[1] * skill8.getSkillhurt() / 100.0);
                    pets[2] = (int)((double)pets[2] * skill8.getSkillhurt() / 100.0);
                    int n7 = 3;
                    pets[n7] += (int)skill8.getSkillgain();
                    break;
                }
            }
        }
        data.setModel(buffer.toString());
        data.setHuoyue((double)zBone);
        data.setShanghai((double)zSpir);
        data.setKangluobao((double)zPower);
        data.setYuanzhu((double)zSpeed);
        data.setHp_z(pets[0]);
        data.setMp_z(pets[1]);
        data.setAp(pets[2]);
        data.setSp(pets[3]);
        data.setQihe((long)pets[4]);
        data.setHp(pet.getBasishp());
        data.setMp(pet.getBasismp());
        if (data.getHp() == 0 || data.getHp() > data.getHp_z()) {
            data.setHp(data.getHp_z());
        }
        if (data.getMp() == 0 || data.getMp() > data.getMp_z()) {
            data.setMp(data.getMp_z());
        }
        ql.addKUp((double)up);
        ql.addKKUp((double)(up + ((pet.getTurnRount() >= 4) ? 10 : 0)));
        // 灵犀额外控制抗性
        int kx = LingXiUtil.getNumberByStr(pet.getLingxi(), "11005", 1);
        if (kx > 0) {
            GetqualityUntil.AddR(ql, "抗混乱", (double)kx);
            GetqualityUntil.AddR(ql, "抗封印", (double)kx);
            GetqualityUntil.AddR(ql, "抗昏睡", (double)kx);
            GetqualityUntil.AddR(ql, "抗遗忘", (double)kx);
        }
        //抗性五行调整
        if (ql.getRolewxj() != 0.0 || ql.getRolewxm() != 0.0 || ql.getRolewxt() != 0.0 || ql.getRolewxs() != 0.0 || ql.getRolewxh() != 0.0) {
            GetqualityUntil.AddR(ql, "金", Double.parseDouble(pet.getGold()) / 2.0);
            GetqualityUntil.AddR(ql, "木", Double.parseDouble(pet.getWood()) / 2.0);
            GetqualityUntil.AddR(ql, "土", Double.parseDouble(pet.getSoil()) / 2.0);
            GetqualityUntil.AddR(ql, "水", Double.parseDouble(pet.getWater()) / 2.0);
            GetqualityUntil.AddR(ql, "火", Double.parseDouble(pet.getFire()) / 2.0);
        }
        else {
            GetqualityUntil.AddR(ql, "金", Double.parseDouble(pet.getGold()));
            GetqualityUntil.AddR(ql, "木", Double.parseDouble(pet.getWood()));
            GetqualityUntil.AddR(ql, "土", Double.parseDouble(pet.getSoil()));
            GetqualityUntil.AddR(ql, "水", Double.parseDouble(pet.getWater()));
            GetqualityUntil.AddR(ql, "火", Double.parseDouble(pet.getFire()));
        }
        GetqualityUntil.AddR(ql, "法术命中率", (double)zSpir * 0.02);
        GetqualityUntil.AddR(ql, "法术暴击", (double)zSpir * 0.03);
        GetqualityUntil.AddR(ql, "法术暴击增伤", (double)zSpir * 0.04);
        FightingSkill skill_4013 = data.getSkillType("4013");
        if (skill_4013 != null) {
            GetqualityUntil.AddR(ql, "躲闪率", (double)(data.getSp() / 1000) * skill_4013.getValue1());
        }
        ql.setRolewxj((double)(int)ql.getRolewxj());
        ql.setRolewxm((double)(int)ql.getRolewxm());
        ql.setRolewxt((double)(int)ql.getRolewxt());
        ql.setRolewxs((double)(int)ql.getRolewxs());
        ql.setRolewxh((double)(int)ql.getRolewxh());
        if (UPBase(data)) {
            cl(data);
        }
    }
    /**4大基础属性 数值加成*/
    public static void getSI(int[] pets, String vs) {
        if (vs == null || vs.equals("")) {
            return;
        }
        String[] v = vs.split("\\|");
        for (int i = 0; i < v.length; ++i) {
            String[] v2 = v[i].split("=");
            if (v2[0].equals("HP")) {
                int n = 0;
                pets[n] = (int)((double)pets[n] + Double.parseDouble(v2[1]));
            }
            else if (v2[0].equals("MP")) {
                int n2 = 1;
                pets[n2] = (int)((double)pets[n2] + Double.parseDouble(v2[1]));
            }
            else if (v2[0].equals("AP")) {
                int n3 = 2;
                pets[n3] = (int)((double)pets[n3] + Double.parseDouble(v2[1]));
            }
            else if (v2[0].equals("SP")) {
                int n4 = 3;
                pets[n4] = (int)((double)pets[n4] + Double.parseDouble(v2[1]));
            }
        }
    }
    /**5维上限判断*/
    public static boolean UPBase(ManData data) {
        if (data.getYuanzhu() < 0.0) {
            return true;
        }
        double size = (double)data.getQihe() + data.getHuoyue() + data.getShanghai() + data.getKangluobao() + data.getYuanzhu();
        return size > 4000.0;
    }
    
    public static void cl(ManData data) {
        data.setHp(1);
        data.setHp_z(1);
        data.setMp(1);
        data.setMp_z(1);
        data.setAp(1);
        data.setSp(0);
        data.setQuality(new Ql());
        if (data.getType() == 0) {
            data.getPets().clear();
            data.getLings().clear();
            data.setChild(null);
        }
        WriteOut.addtxt("抗性字段溢出:" + data.getManname() + ":" + data.getId() + ":" + data.getType(), 9999L);
    }
    /**
     * 召唤兽技能刷新数据
     */
    public static String BattskillData(List<Integer> skills, String b3) {
        // 获取所有的技能id
        String skilldata = null;
        Boolean b4 = Boolean.FALSE;
        Boolean b5 = Boolean.FALSE;
        skills = (List)skills.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        if (StringUtils.isNotBlank(b3)) {
            skills.add(0, Integer.parseInt(b3));
        }
        for (int i = 0; i < skills.size(); ++i) {
            String string = skills.get(i) + "";
            int n = -1;
            switch (string.hashCode()) {
                case 1515111: {
                    if (string.equals("1800")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515178: {
                    if (string.equals("1825")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515112: {
                    if (string.equals("1801")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515113: {
                    if (string.equals("1802")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515179: {
                    if (string.equals("1826")) {
                        n = 4;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515144: {
                    if (string.equals("1812")) {
                        n = 5;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515146: {
                    if (string.equals("1814")) {
                        n = 6;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515361: {
                    if (string.equals("1882")) {
                        n = 7;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515362: {
                    if (string.equals("1883")) {
                        n = 8;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515363: {
                    if (string.equals("1884")) {
                        n = 9;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515364: {
                    if (string.equals("1885")) {
                        n = 10;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515114: {
                    if (string.equals("1803")) {
                        n = 11;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515180: {
                    if (string.equals("1827")) {
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
                    skilldata = DrawnitemsAction.Splice(skilldata, "HP=27000", 2);
                    break;
                }
                case 2: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "MP=27000", 2);
                    break;
                }
                case 3:
                case 4: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "AP=11000", 2);
                    break;
                }
                case 5: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "SP=-170", 2);
                    break;
                }
                case 6: {
                    b4 = Boolean.TRUE;
                    skilldata = DrawnitemsAction.Splice(skilldata, "SP=250", 2);
                    break;
                }
                case 7: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "HP=32000", 2);
                    break;
                }
                case 8: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "MP=32000", 2);
                    break;
                }
                case 9: {
                    skilldata = DrawnitemsAction.Splice(skilldata, "AP=15000", 2);
                    break;
                }
                case 10: {
                    if (!(boolean)b4) {
                        skilldata = DrawnitemsAction.Splice(skilldata, "SP=200", 2);
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 11: {
                    if (!(boolean)b4 && !(boolean)b5) {
                        skilldata = DrawnitemsAction.Splice(skilldata, "SP=200", 2);
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 12: {
                    if (!(boolean)b4 && !(boolean)b5) {
                        skilldata = DrawnitemsAction.Splice(skilldata, "SP=170", 2);
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
    
    public static Object[] value(String value, int mountid, LoginResult loginResult) {
        Object[][] attackValues = { {0, 0.0} };
        int[] l = { 0 };
        double[] p = { 0.0 };
        double qiong = 1.0;
        double baihu = 1.0;
        double xuanwu = 1.0;
        double zhuque = 1.0;
        List<Mount> mounts = AllServiceUtil.getMountService().selectMountsByRoleID(loginResult.getRole_id());
        DefaultListModel<String> shuxingmodel = new DefaultListModel<>();
        if (loginResult.getJiesuo() != null && loginResult.getJiesuo().contains(mountid + "")) {
            shuxingmodel.addElement("1级 青龙守护属性提升10%");
            shuxingmodel.addElement("2级 白虎守护属性提升10%");
            shuxingmodel.addElement("3级 朱雀守护属性提升10%");
            shuxingmodel.addElement("4级 玄武守护属性提升10%");
            shuxingmodel.addElement("5级 青龙守护属性提升15%");
            shuxingmodel.addElement("6级 白虎守护属性提升15%");
            shuxingmodel.addElement("7级 朱雀守护属性提升15%");
            shuxingmodel.addElement("8级 玄武守护属性提升15%");
            shuxingmodel.addElement("9级 青龙守护属性提升15%");
            shuxingmodel.addElement("10级 白虎守护属性提升15%");
            shuxingmodel.addElement("11级 朱雀守护属性提升15%");
            shuxingmodel.addElement("12级 玄武守护属性提升15%");
            shuxingmodel.addElement("13级 青龙守护属性提升20%");
            shuxingmodel.addElement("14级 白虎守护属性提升20%");
            shuxingmodel.addElement("15级 朱雀守护属性提升20%");
            shuxingmodel.addElement("16级 玄武守护属性提升20%");
            shuxingmodel.addElement("17级 青龙守护属性提升20%");
            shuxingmodel.addElement("18级 白虎守护属性提升20%");
            shuxingmodel.addElement("19级 朱雀守护属性提升20%");
            shuxingmodel.addElement("20级 玄武守护属性提升20%");
            shuxingmodel.addElement("21级 青龙守护属性提升20%");
            shuxingmodel.addElement("22级 白虎守护属性提升20%");
            shuxingmodel.addElement("23级 朱雀守护属性提升20%");
            shuxingmodel.addElement("24级 玄武守护属性提升20%");
            int k = Math.min(Integer.parseInt(loginResult.getZhongtian().split("\\|")[0]), 24);
            int q = 0;
            int b = 0;
            int x = 0;
            int z = 0;
            for (int i = 0; i <= k - 1; ++i) {
                if (((String)shuxingmodel.elementAt(i)).contains("青龙")) {
                    char secondLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 3);
                    char thirdLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 2);
                    q = q + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                }
                else if (((String)shuxingmodel.elementAt(i)).contains("白虎")) {
                    char secondLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 3);
                    char thirdLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 2);
                    b = b + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                }
                if (((String)shuxingmodel.elementAt(i)).contains("朱雀")) {
                    char secondLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 3);
                    char thirdLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 2);
                    z = z + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                }
                if (((String)shuxingmodel.elementAt(i)).contains("玄武")) {
                    char secondLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 3);
                    char thirdLastChar = ((String)shuxingmodel.elementAt(i)).charAt(((String)shuxingmodel.elementAt(i)).length() - 2);
                    x = x + Integer.parseInt(String.valueOf(secondLastChar)) * 10 + Integer.parseInt(String.valueOf(thirdLastChar));
                }
            }
            qiong += (double)q / 100.0;
            baihu += (double)b / 100.0;
            xuanwu += (double)x / 100.0;
            zhuque += (double)z / 100.0;
        }
        shuxingmodel.clear();
        double finalQiong = qiong;
        double finalBaihu = baihu;
        double finalXuanwu = xuanwu;
        double finalZhuque = zhuque;
        mounts.forEach(e/* org.come.entity.Mount, */ -> {
            if ((int)e.getMountid() == mountid) {
                switch (e.getSh()) {
                    case 1: {
                        for (int j = 0; j <= ((List<String>)ManData.shuxingmap.get("青龙")).size() - 1; ++j) {
                            shuxingmodel.addElement(((List<String>)ManData.shuxingmap.get("青龙")).get(j));
                        }
                        if (loginResult.getQinglong() != null) {
                            attackValues[0] = ManData.calculateTotalAttackValue(shuxingmodel, Integer.parseInt(loginResult.getQinglong().split("\\|")[0]), value);
                            l[0] = (int)((double)l[0] + (double)(int)attackValues[0][0] * finalQiong);
                            p[0] += (double)attackValues[0][1] * finalQiong;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 2: {
                        for (int m = 0; m <= ((List<String>)ManData.shuxingmap.get("白虎")).size() - 1; ++m) {
                            shuxingmodel.addElement(((List<String>)ManData.shuxingmap.get("白虎")).get(m));
                        }
                        if (loginResult.getBaihu() != null) {
                            attackValues[0] = ManData.calculateTotalAttackValue(shuxingmodel, Integer.parseInt(loginResult.getBaihu().split("\\|")[0]), value);
                            l[0] = (int)((double)l[0] + (double)(int)attackValues[0][0] * finalBaihu);
                            p[0] += (double)attackValues[0][1] * finalBaihu;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 3: {
                        for (int i2 = 0; i2 <= ((List<String>)ManData.shuxingmap.get("玄武")).size() - 1; ++i2) {
                            shuxingmodel.addElement(((List<String>)ManData.shuxingmap.get("玄武")).get(i2));
                        }
                        if (loginResult.getXuanwu() != null) {
                            attackValues[0] = ManData.calculateTotalAttackValue(shuxingmodel, Integer.parseInt(loginResult.getXuanwu().split("\\|")[0]), value);
                            l[0] = (int)((double)l[0] + (double)(int)attackValues[0][0] * finalXuanwu);
                            p[0] += (double)attackValues[0][1] * finalXuanwu;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 4: {
                        for (int i3 = 0; i3 <= ((List<String>)ManData.shuxingmap.get("朱雀")).size() - 1; ++i3) {
                            shuxingmodel.addElement(((List<String>)ManData.shuxingmap.get("朱雀")).get(i3));
                        }
                        if (loginResult.getZhuque() != null) {
                            attackValues[0] = ManData.calculateTotalAttackValue(shuxingmodel, Integer.parseInt(loginResult.getZhuque().split("\\|")[0]), value);
                            l[0] = (int)((double)l[0] + (double)(int)attackValues[0][0] * finalZhuque);
                            p[0] += (double)attackValues[0][1] * finalZhuque;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
            }
            return;
        });
        attackValues[0][0] = l[0];
        attackValues[0][1] = p[0];
        return attackValues[0];
    }
    
    public static void shouhuvalue(int[] pets, RoleSummoning pet, Mount mount, Ql ql, ManData data) {
        RoleData roleData = RolePool.getRoleData(pet.getRoleid());
        if (roleData == null) {
            return;
        }
        LoginResult loginResult = roleData.getLoginResult();
        if (mount != null && mount.getSh() != 0) {
            Object[] attackValues = value("气血", (int)mount.getMountid(), loginResult);
            Object[] attackValues2 = value("法力", (int)mount.getMountid(), loginResult);
            Object[] attackValues3 = value("攻击", (int)mount.getMountid(), loginResult);
            Object[] attackValues4 = value("速度", (int)mount.getMountid(), loginResult);
            int n = 0;
            pets[n] += (int)attackValues[0];
            int n2 = 0;
            pets[n2] = (int)((double)pets[n2] + (double)attackValues[1] / 100.0 * (double)pets[0]);
            int n3 = 1;
            pets[n3] += (int)attackValues2[0];
            int n4 = 1;
            pets[n4] = (int)((double)pets[n4] + (double)attackValues2[1] / 100.0 * (double)pets[1]);
            int n5 = 2;
            pets[n5] += (int)attackValues3[0];
            int n6 = 2;
            pets[n6] = (int)((double)pets[n6] + (double)attackValues3[1] / 100.0 * (double)pets[2]);
            int n7 = 3;
            pets[n7] += (int)attackValues4[0];
            int n8 = 3;
            pets[n8] = (int)((double)pets[n8] + (double)attackValues4[1] / 100.0 * (double)pets[3]);
            if (mount.getShouhu() != 0) {
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(mount.getShouhu()));
                if (good != null && good.getValue() != null && !good.getValue().equals("")) {
                    String[] mes = good.getValue().split("\\|");
                    for (int i = 1; i <= mes.length - 1; ++i) {
                        if (!mes[i].equals("0")) {
                            if (mes[i].split("=")[0].contains("气血")) {
                                int n9 = 0;
                                pets[n9] = (int)((double)pets[n9] + Double.parseDouble(mes[i].split("=")[1]));
                            }
                            else if (mes[i].split("=")[0].contains("魔法")) {
                                int n10 = 1;
                                pets[n10] = (int)((double)pets[n10] + Double.parseDouble(mes[i].split("=")[1]));
                            }
                            else if (mes[i].split("=")[0].contains("攻击")) {
                                int n11 = 2;
                                pets[n11] = (int)((double)pets[n11] + Double.parseDouble(mes[i].split("=")[1]));
                            }
                            else if (mes[i].split("=")[0].contains("速度")) {
                                int n12 = 3;
                                pets[n12] = (int)((double)pets[n12] + Double.parseDouble(mes[i].split("=")[1]));
                            }
                            else if (mes[i].split("=")[0].matches("\\d+")) {
                                Skill skill = GameServer.getSkill(mes[i].split("=")[0]);
                                FightingSkill fightingSkill = new FightingSkill(skill, 2, 0, 0.0, 0L, 0);
                                data.addSkill(fightingSkill);
                            }
                        }
                    }
                }
            }
            GetqualityUntil.AddR(ql, "命中率", (double)value("命中率", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "抗致命率", (double)value("抗致命几率", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "抗风法狂暴", (double)value("抗仙法鬼火狂暴几率", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "抗火法狂暴", (double)value("抗仙法鬼火狂暴几率", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "抗水法狂暴", (double)value("抗仙法鬼火狂暴几率", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "抗雷法狂暴", (double)value("抗仙法鬼火狂暴几率", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "抗鬼火狂暴", (double)value("抗仙法鬼火狂暴几率", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "物理吸收率", (double)value("物理吸收率", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "法术躲闪", (double)value("法术躲闪几率", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "忽视抗风", (double)value("忽视抗仙法鬼火", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "忽视抗雷", (double)value("忽视抗仙法鬼火", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "忽视抗水", (double)value("忽视抗仙法鬼火", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "忽视抗火", (double)value("忽视抗仙法鬼火", (int)mount.getMountid(), loginResult)[1]);
            GetqualityUntil.AddR(ql, "忽视抗鬼火", (double)value("忽视抗仙法鬼火", (int)mount.getMountid(), loginResult)[1]);
            if (mount.getShouhu() != 0) {
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(mount.getShouhu()));
                if (good != null && good.getValue() != null && !good.getValue().equals("")) {
                    String[] mes = good.getValue().split("\\|");
                    for (int i = 1; i <= mes.length - 1; ++i) {
                        if (!mes[i].equals("0") && (!mes[i].split("=")[0].contains("气血") && !mes[i].split("=")[0].contains("魔法") && !mes[i].split("=")[0].contains("速度")) && !mes[i].split("=")[0].contains("攻击") && !mes[i].split("=")[0].matches("\\d+")) {
                            GetqualityUntil.AddR(ql, mes[i].split("=")[0], Double.parseDouble(mes[i].split("=")[1]));
                        }
                    }
                }
            }
        }
    }
    
    static {
        CalculationUtil.limitTypes = new String[] { "变身卡", "强法型", "加抗型", "增益型", "VIP", "回蓝符", "经验", "SVIP", "帮派", "单人竞技场", "怨气符", "复活符文", "JVIP" };
        CalculationUtil.baseTypes = new String[] { "根骨", "灵性", "力量", "敏捷", "定力" };
        CalculationUtil.isBsk = false;
        CalculationUtil.evs = new String[] { "根骨", "灵性", "力量", "敏捷", "增加气血", "增加法力", "增加攻击" };
        CalculationUtil.listMap = new HashMap<>();
        List<Double> list = new ArrayList<>();
        list.add(30.0);
        CalculationUtil.listMap.put(4000, new ArrayList(list));
        list = new ArrayList<>();
        list.add(15.0);
        CalculationUtil.listMap.put(4001, new ArrayList(list));
        list = new ArrayList<>();
        list.add(6.0);
        list.add(15000.0);
        CalculationUtil.listMap.put(4002, new ArrayList(list));
        list = new ArrayList<>();
        list.add(4.5);
        list.add(3000.0);
        CalculationUtil.listMap.put(4003, new ArrayList(list));
        list = new ArrayList<>();
        list.add(60.0);
        list.add(60.0);
        CalculationUtil.listMap.put(4004, new ArrayList(list));
        list = new ArrayList<>();
        list.add(30.0);
        list.add(45.0);
        CalculationUtil.listMap.put(4005, new ArrayList(list));
        list = new ArrayList<>();
        list.add(105.0);
        list.add(30.0);
        CalculationUtil.listMap.put(4006, new ArrayList(list));
        list = new ArrayList<>();
        list.add(45.0);
        CalculationUtil.listMap.put(4007, new ArrayList(list));
        list = new ArrayList<>();
        list.add(15.0);
        CalculationUtil.listMap.put(4008, new ArrayList(list));
        list = new ArrayList<>();
        list.add(24.0);
        CalculationUtil.listMap.put(4009, new ArrayList(list));
        list = new ArrayList<>();
        list.add(45.0);
        list.add(15.0);
        CalculationUtil.listMap.put(4010, new ArrayList(list));
        list = new ArrayList<>();
        list.add(45.0);
        list.add(16.0);
        CalculationUtil.listMap.put(4011, new ArrayList(list));
        list = new ArrayList<>();
        list.add(75.0);
        list.add(15.0);
        CalculationUtil.listMap.put(4012, new ArrayList(list));
        list = new ArrayList<>();
        list.add(3.0);
        CalculationUtil.listMap.put(4013, new ArrayList(list));
        list = new ArrayList<>();
        list.add(60.0);
        list.add(15.0);
        CalculationUtil.listMap.put(4014, new ArrayList(list));
        list = new ArrayList<>();
        list.add(60.0);
        list.add(15.0);
        CalculationUtil.listMap.put(4015, new ArrayList(list));
        list = new ArrayList<>();
        list.add(22.5);
        CalculationUtil.listMap.put(4016, new ArrayList(list));
        list = new ArrayList<>();
        list.add(75.0);
        list.add(15.0);
        CalculationUtil.listMap.put(4017, new ArrayList(list));
        list = new ArrayList<>();
        list.add(75.0);
        CalculationUtil.listMap.put(4018, new ArrayList(list));
        list = new ArrayList<>();
        list.add(45.0);
        list.add(30.0);
        CalculationUtil.listMap.put(4019, new ArrayList(list));
        list = new ArrayList<>();
        list.add(45.0);
        list.add(30.0);
        CalculationUtil.listMap.put(4020, new ArrayList(list));
        list = new ArrayList<>();
        list.add(60.0);
        list.add(30.0);
        CalculationUtil.listMap.put(4021, new ArrayList(list));
        list = new ArrayList<>();
        list.add(9.0);
        list.add(9.0);
        CalculationUtil.listMap.put(4022, new ArrayList(list));
    }

    public Skill clone() {
        try {
            return (Skill) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
