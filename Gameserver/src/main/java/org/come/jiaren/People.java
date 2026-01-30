package org.come.jiaren;

import come.tool.Calculation.BaseEquip;
import come.tool.Calculation.BaseQl;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.Sepcies_MixDeal;
import come.tool.Mixdeal.CreepsMixdeal;
import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import come.tool.Role.RoleShow;
import come.tool.newTeam.TeamBean;
import org.come.bean.LoginResult;
import org.come.entity.Baby;
import org.come.entity.Goodstable;
import org.come.model.Configure;
import org.come.server.GameServer;
import org.come.until.AllServiceUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.come.action.sys.enterGameAction.getskin;

public class People {
//    假人系统文件
    public static List<TeamBean>teamBeanList=new ArrayList<>();//队伍
    public static Map<Integer,String> SELL_BOT = new HashMap<>();//摆摊
    public static  List<LoginResult>jiqiren=new ArrayList<>();
    public static List<BigDecimal> BOT_RID = new ArrayList<>();//摆摊id
    public static HashSet<String> jiaRenSet = new HashSet<String>();//创建后的假人名字集合
    public static Baby jiarenBB;
    public static Goodstable JiarenWuqi=new Goodstable();
    private static String[]mes11={"夏","傲","心","一梦","落","牧","落","你","爱","事","爱","不必","人瘦","花开","爱你","雪落","爱情","开"};
    private static String[]mes12={"末","鬼","花","长东","江水","樱花","之森","共粲"};
    //灵宝{"id":2000,"Camp":1,"man":12,"type":3,"State_1":"","States":[],"manname":"巽风袋","hp_Total":1,"hp_Current":1,"mp_Total":0,"mp_Current":0,"yqz":149,"nqz":30,"model":"20","alpha":1.0,"zs":0}
    //孩子{"id":1000,"Camp":1,"man":17,"type":4,"State_1":"","States":[],"manname":"天下第一","hp_Total":1,"hp_Current":1,"mp_Total":0,"mp_Current":0,"yqz":149,"nqz":30,"model":"100001","alpha":1.0,"zs":0}
//    public static ManData jiarenChild(){
//        ManData manData=new ManData();
//
//    }
//    public Baby jrbb(){
//        if (jiarenBB != null) {
//            return jiarenBB;
//        }
//        return new Baby();
//    }

    /**
     * 创建假人孩子
     */
    public static void creathaizi() {
        Baby dbBaby = new Baby();
        dbBaby.setTalents("-1|-1|-1|-1");
        dbBaby.setQizhi(1000);
        dbBaby.setNeili(1000);
        dbBaby.setZhili(1000);
        dbBaby.setNaili(1000);
        dbBaby.setMingqi(1000);
        dbBaby.setDaode(1000);
        dbBaby.setPanni(1000);
        dbBaby.setWanxing(25);
        dbBaby.setQingmi(1000);
        dbBaby.setXiaoxin(1000);
        dbBaby.setWenbao(1000);
        dbBaby.setBabyage(6980);
        dbBaby.setOutcome("革命领袖");
        dbBaby.setBabyname("顶风尿三丈");
        dbBaby.setYangyujin(1000);
        dbBaby.setPilao(1000);
        dbBaby.setChildSex(0);
        dbBaby.setRoleid(BigDecimal.valueOf(-1));
        dbBaby.setBabyid(BigDecimal.valueOf(-1));
        jiarenBB=dbBaby;
    }

    /**
     * 假人飞行造型
     * @return
     */
    public static String jiarenFlySkin(int zs){
        int lvl = zs;
        if (zs > 4) {
            lvl = 4;
        }
        return String.valueOf((10000000+lvl+Battlefield.random.nextInt(6)*5));
    }

    /**
     * 假人摆摊
     * @param mapId
     * @param x
     * @param y
     * @return
     */
    public static LoginResult addStallBot( Long mapId,int x, int y){

        String buffer1;

        buffer1= mes11[Battlefield.random.nextInt(mes11.length)]+ mes11[Battlefield.random.nextInt(mes11.length)]+mes12[Battlefield.random.nextInt(mes12.length)]+ mes11[Battlefield.random.nextInt(mes11.length)];

        return CreatPeople.creatpeople(buffer1,223,1, mapId,(long)x ,(long)y);
    }



    private static final Map<Integer, Integer> specieIdMap = new HashMap<>();

    static {
        specieIdMap.put(20001, 7006);
        specieIdMap.put(20002, 7021);
        specieIdMap.put(20003, 7022);
        specieIdMap.put(20004, 7008);
        specieIdMap.put(20005, 7009);
        specieIdMap.put(20006, 7011);
        specieIdMap.put(20007, 7006);
        specieIdMap.put(20008, 7006);
        specieIdMap.put(20009, 7012);
        specieIdMap.put(20010, 7006);

        specieIdMap.put(21001, 7009);
        specieIdMap.put(21002, 7007);
        specieIdMap.put(21003, 7011);
        specieIdMap.put(21004, 7007);
        specieIdMap.put(21005, 7009);
        specieIdMap.put(21006, 7008);
        specieIdMap.put(21007, 7011);
        specieIdMap.put(21008, 7007);
        specieIdMap.put(21009, 7007);
        specieIdMap.put(21010, 7010);

        specieIdMap.put(22001, 7011);
        specieIdMap.put(22002, 7006);
        specieIdMap.put(22003, 7009);
        specieIdMap.put(22004, 7007);
        specieIdMap.put(22005, 7009);
        specieIdMap.put(22006, 7006);
        specieIdMap.put(22007, 7011);
        specieIdMap.put(22008, 7015);
        specieIdMap.put(22009, 7006);
        specieIdMap.put(22010, 7015);

        specieIdMap.put(23001, 7006);
        specieIdMap.put(23002, 7011);
        specieIdMap.put(23003, 7020);
        specieIdMap.put(23004, 7013);
        specieIdMap.put(23005, 7018);
        specieIdMap.put(23006, 7015);

        specieIdMap.put(24001, 7006);
        specieIdMap.put(24002, 7011);
        specieIdMap.put(24003, 7023);
        specieIdMap.put(24004, 7021);
        specieIdMap.put(24005, 7011);
        specieIdMap.put(24006, 7006);
//        specieIdMap.put(20001, 1);
//        specieIdMap.put(20002, 3);
//        specieIdMap.put(20003, 4);
//        specieIdMap.put(20004, 8);
//        specieIdMap.put(20005, 7);
//        specieIdMap.put(20006, 12);
//        specieIdMap.put(20007, 1);
//        specieIdMap.put(20008, 1);
//        specieIdMap.put(20009, 2);
//        specieIdMap.put(20010, 1);
//
//        specieIdMap.put(21001, 7);
//        specieIdMap.put(21002, 10);
//        specieIdMap.put(21003, 12);
//        specieIdMap.put(21004, 10);
//        specieIdMap.put(21005, 7);
//        specieIdMap.put(21006, 8);
//        specieIdMap.put(21007, 12);
//        specieIdMap.put(21008, 10);
//        specieIdMap.put(21009, 10);
//        specieIdMap.put(21010, 14);
//
//        specieIdMap.put(22001, 12);
//        specieIdMap.put(22002, 1);
//        specieIdMap.put(22003, 7);
//        specieIdMap.put(22004, 10);
//        specieIdMap.put(22005, 7);
//        specieIdMap.put(22006, 1);
//        specieIdMap.put(22007, 12);
//        specieIdMap.put(22008, 16);
//        specieIdMap.put(22009, 1);
//        specieIdMap.put(22010, 16);
//
//        specieIdMap.put(23001, 1);
//        specieIdMap.put(23002, 12);
//        specieIdMap.put(23003, 13);
//        specieIdMap.put(23004, 9);
//        specieIdMap.put(23005, 17);
//        specieIdMap.put(23006, 16);
//
//        specieIdMap.put(24001, 1);
//        specieIdMap.put(24002, 12);
//        specieIdMap.put(24003, 18);
//        specieIdMap.put(24004, 3);
//        specieIdMap.put(24005, 12);
//        specieIdMap.put(24006, 1);
    }

    public static int GetWeaponSkin(BigDecimal specieid) {
        return specieIdMap.getOrDefault(specieid.intValue(), 0);
    }
    //	剑:1 扇:2 锤:3 斧头:4
//	拳套:5 书:6 棍:7 鞭:8
//	钩:9 刀:10 双环:11 枪:12
//	幡:13 爪:14 浮尘:15 飘带:16
//	灯笼:17 弓:18
//    /**根据物品皮肤获取武器皮肤*/
//    public static int good(int Specieid){
//        if (Specieid==20001) {return 1;}
//        if (Specieid == 20002) {return 3;}
//        if (Specieid == 20003) {return 4;}
//        if (Specieid == 20004) {return 8;}
//        if (Specieid == 20005) {return 7;}
//        if (Specieid == 20006) {return 12;}
//        if (Specieid == 20007) {return 1;}
//        if (Specieid == 20008) {return 1;}
//        if (Specieid == 20009) {return 2;}
//        if (Specieid == 20010) {return 1;}
//
//        if (Specieid == 21001) {return 7;}
//        if (Specieid == 21002) {return 10;}
//        if (Specieid == 21003) {return 12;}
//        if (Specieid == 21004) {return 10;}
//        if (Specieid == 21005) {return 7;}
//        if (Specieid == 21006) {return 8;}
//        if (Specieid == 21007) {return 12;}
//        if (Specieid == 21008) {return 10;}
//        if (Specieid == 21009) {return 10;}
//        if (Specieid == 21010) {return 14;}
//
//        if (Specieid == 22001) {return 12;}
//        if (Specieid == 22002) {return 1;}
//        if (Specieid == 22003) {return 7;}
//        if (Specieid == 22004) {return 10;}
//        if (Specieid == 22005) {return 7;}
//        if (Specieid == 22006) {return 1;}
//        if (Specieid == 22007) {return 12;}
//        if (Specieid == 22008) {return 16;}
//        if (Specieid == 22009) {return 1;}
//        if (Specieid == 22010) {return 16;}
//
//        if (Specieid == 23001) {return 1;}
//        if (Specieid == 23002) {return 12;}
//        if (Specieid == 23003) {return 13;}
//        if (Specieid == 23004) {return 9;}
//        if (Specieid == 23005) {return 17;}
//        if (Specieid == 23006) {return 16;}
//
//        if (Specieid == 24001) {return 1;}
//        if (Specieid == 24002) {return 12;}
//        if (Specieid == 24003) {return 18;}
//        if (Specieid == 24004) {return 3;}
//        if (Specieid == 24005) {return 12;}
//        if (Specieid == 24006) {return 1;}
//        return 0;
//    }


}
