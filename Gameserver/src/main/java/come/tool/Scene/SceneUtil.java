package come.tool.Scene;

import come.tool.Scene.CJ.CJScene;
import org.come.task.MonsterMoveBase;
import java.util.Map;
import org.come.bean.LoginResult;
import come.tool.BangBattle.BangBattlePool;
import come.tool.Scene.LTS.LTSScene;
import come.tool.Scene.BWZ.BWZScene;
import come.tool.Scene.BTY.BTYScene;
import come.tool.Scene.DNTG.DNTGScene;
import come.tool.Scene.ZZS.ZZSScene;
import come.tool.Scene.TGDB.TGDBScene;
import java.time.LocalTime;
import org.come.model.Configure;
import org.come.server.GameServer;
import org.come.action.wqx.WenQuXingScene;
import come.tool.Scene.JP.JPScene;
import come.tool.Scene.SLDH.SLDHScene;
import come.tool.Scene.PKLS.PKLSScene;
import come.tool.Scene.RC.RCScene;
import java.util.concurrent.ConcurrentHashMap;

public class SceneUtil
{
    public static final int BTYID = 1001;
    public static final int TGDBID = 1002;
    public static final int ZZSID_0 = 1003;
    public static final int ZZSID_1 = 1004;
    public static final int ZZSID_2 = 1005;
    public static final int ZZSID_3 = 1006;
    public static final int ZZSID_4 = 10061;
    public static final int BWZID = 1007;
    public static final int LTSID = 1008;
    public static final int RCID = 1009;
    public static final int PKLSID = 1010;
    public static final int DNTGID = 1011;
    public static final int SLDHID = 1012;
    public static final int BZ = 1013;
    public static final int WQX = 1017;
    public static final int CJID=1014;
    public static ConcurrentHashMap<Integer, Scene> sceneMap;
    public static final int JP = 1015;

    
    public static void init() {
        (SceneUtil.sceneMap = new ConcurrentHashMap<>()).put(Integer.valueOf(1009), new RCScene());
        SceneUtil.sceneMap.put(Integer.valueOf(1010), new PKLSScene());
        SceneUtil.sceneMap.put(Integer.valueOf(1012), new SLDHScene());
        SceneUtil.sceneMap.put(Integer.valueOf(1015), new JPScene());
        SceneUtil.sceneMap.put(Integer.valueOf(1017), new WenQuXingScene());
        sceneMap.put(CJID, new CJScene());
    }
    /**
     * 判断是否有活动开启
     */
    public static void activityOpen(String week, int day, int hour, int minute, int second) {

        if (week != null && week.equals("测试水陆")) {
            Scene scene = sceneMap.get(SLDHID);
            if (scene == null) {
                scene = new SLDHScene();
                sceneMap.put(SLDHID, scene);
            }
            if (!scene.isEnd()) {
                SLDHScene sldhScene = (SLDHScene) scene;
                sldhScene.open();
            }
        }else if  (week != null && week.equals("测试大闹")) {
            Scene scene = (Scene) SceneUtil.sceneMap.get(Integer.valueOf(1011));
            if (scene == null || !scene.isEnd()) {
                SceneUtil.sceneMap.put(Integer.valueOf(1011), new DNTGScene());
            }
        }else {
            //logger.info("——————————————判断活动开启————————————————————————");
            //System.out.println("——————————————判断活动开启————————————————————————");
            ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
            Configure configure = (Configure) s.get(Integer.valueOf(1));
            String SLDHSJ = configure.getSldhsjsz();//水路
            String TGDBSJ = configure.gettgdbsjsz();//天宫寻宝
            String ZZSSJ = configure.getzzssjsz();//种族赛
            String DNTGSJ = configure.getdntgsjsz();//大闹
            String BTYSJ = configure.getbtysjsz();
            String BWZSJ = configure.getbwzsjsz();//长安保卫战
            String LTSSJ = configure.getltssjsz();//擂台赛
            String CJSJ = configure.getXq();//吃鸡时间
            LocalTime dateTime = LocalTime.of(hour, minute, second);
            LocalTime SLDHSD = configure.getsldhsdsz();
            LocalTime TGDBSD = configure.gettgdbsdsz();
            LocalTime TGDBSDJS = configure.gettgdbsdjssz();
            LocalTime ZZSSD = configure.getzzssdsz();
            LocalTime ZZSSDJS = configure.getzzssdjssz();
            LocalTime DNTGSD = configure.getdntgsdsz();
            LocalTime BTYSD = configure.getbtysdsz();
            LocalTime BTYSDA = configure.getbtysdasz();
            LocalTime BWZSDA = configure.getbwzsdasz();
            LocalTime BWZSDB = configure.getbwzsdbsz();
            LocalTime BWZSDC = configure.getbwzsdcsz();
            LocalTime BWZSDD = configure.getbwzsddsz();
            LocalTime LTSSD = configure.getltssdsz();
            LocalTime CJSD = configure.getSj();
            if ((week.equals(SLDHSJ) || SLDHSJ.contains(week)) && dateTime.equals(SLDHSD)) {//周2晚上8：00  水陆大会
                Scene scene = (Scene) SceneUtil.sceneMap.get(Integer.valueOf(1012));
                if (scene == null) {
                    scene = new SLDHScene();
                    SceneUtil.sceneMap.put(Integer.valueOf(1012), scene);
                }
                if (!scene.isEnd()) {
                    SLDHScene sldhScene = (SLDHScene) scene;
                    sldhScene.open();
                }
            }
            if (week.equals(TGDBSJ) || TGDBSJ.contains(week)) {//周1，晚20：00，天宫寻宝
                if (dateTime.equals(TGDBSD)) {
                    SceneUtil.sceneMap.put(1002, new TGDBScene());
                } else if (dateTime.equals(TGDBSDJS)) {
                    SceneUtil.sceneMap.remove(1002);
                }
            }
            if (week.equals(ZZSSJ) || ZZSSJ.contains(week)) {//周3晚上8：00  种族比武
                if (dateTime.equals(ZZSSD)) {
                    Scene scene = SceneUtil.sceneMap.get(1003);
                    if (scene == null) {
                        SceneUtil.sceneMap.put(1003, new ZZSScene(0));
                    }
                    Scene scene2 = SceneUtil.sceneMap.get(1004);
                    if (scene2 == null) {
                        SceneUtil.sceneMap.put(1004, new ZZSScene(1));
                    }
                    Scene scene3 = SceneUtil.sceneMap.get(1005);
                    if (scene3 == null) {
                        SceneUtil.sceneMap.put(1005, new ZZSScene(2));
                    }
                    Scene scene4 = SceneUtil.sceneMap.get(1006);
                    if (scene4 == null) {
                        SceneUtil.sceneMap.put(1006, new ZZSScene(3));
                    }
                    Scene scene5 = SceneUtil.sceneMap.get(Integer.valueOf(10061));
                    if (scene5 == null) {
                        SceneUtil.sceneMap.put(10061, new ZZSScene(4));
                    }
                } else if (dateTime.equals(ZZSSDJS)) {
                    SceneUtil.sceneMap.remove(Integer.valueOf(1003));
                    SceneUtil.sceneMap.remove(Integer.valueOf(1004));
                    SceneUtil.sceneMap.remove(Integer.valueOf(1005));
                    SceneUtil.sceneMap.remove(Integer.valueOf(1006));
                    SceneUtil.sceneMap.remove(Integer.valueOf(10061));
                }
            }
            if ((week.equals(DNTGSJ) || DNTGSJ.contains(week)) && dateTime.equals(DNTGSD)) {//周4晚上20：00  大闹天宫
                Scene scene = (Scene) SceneUtil.sceneMap.get(Integer.valueOf(1011));
                if (scene == null || !scene.isEnd()) {
                    SceneUtil.sceneMap.put(Integer.valueOf(1011), new DNTGScene());
                }
            }
            if (week.equals(BTYSJ) || BTYSJ.contains(week)) {
                if (dateTime.equals(BTYSD)) {
                    Scene scene = (Scene) SceneUtil.sceneMap.get(Integer.valueOf(1001));
                    if (scene == null || !scene.isEnd()) {
                        SceneUtil.sceneMap.put(Integer.valueOf(1001), new BTYScene());
                    }
                }
                if (dateTime.equals(BTYSDA)) {
                    Scene scene = (Scene) SceneUtil.sceneMap.get(Integer.valueOf(1001));
                    if (scene == null || !scene.isEnd()) {
                        SceneUtil.sceneMap.put(Integer.valueOf(1001), new BTYScene());
                    }
                }
            }
            if (week.equals(BWZSJ) || BWZSJ.contains(week)) {//周六
                // 长安保卫战
                if (dateTime.equals(BWZSDA)) {
                    Scene scene = (Scene) SceneUtil.sceneMap.get(Integer.valueOf(1007));
                    if (scene == null || !scene.isEnd()) {
                        SceneUtil.sceneMap.put(Integer.valueOf(1007), new BWZScene());
                    }
                }
                if (dateTime.equals(BWZSDB)) {
                    Scene scene = (Scene) SceneUtil.sceneMap.get(Integer.valueOf(1007));
                    if (scene == null || !scene.isEnd()) {
                        SceneUtil.sceneMap.put(Integer.valueOf(1007), new BWZScene());
                    }
                }
                if (dateTime.equals(BWZSDC)) {
                    Scene scene = (Scene) SceneUtil.sceneMap.get(Integer.valueOf(1007));
                    if (scene == null || !scene.isEnd()) {
                        SceneUtil.sceneMap.put(Integer.valueOf(1007), new BWZScene());
                    }
                }
                if (dateTime.equals(BWZSDD)) {
                    Scene scene = (Scene) SceneUtil.sceneMap.get(Integer.valueOf(1007));
                    if (scene == null || !scene.isEnd()) {
                        SceneUtil.sceneMap.put(Integer.valueOf(1007), new BWZScene());
                    }
                }
            }
            if ((week.equals(LTSSJ) || LTSSJ.contains(week)) && dateTime.equals(LTSSD)) {// 擂台赛
                Scene scene = (Scene) SceneUtil.sceneMap.get(Integer.valueOf(1008));
                if (scene == null || !scene.isEnd()) {
                    SceneUtil.sceneMap.put(Integer.valueOf(1008), new LTSScene());
                }
            }
            if (week.equals(CJSJ) || CJSJ.contains(week)) {//吃鸡时间
                if (dateTime.equals(CJSD)) {
                    Scene scene = sceneMap.get(CJID);
                    if (scene == null) {
                        scene = new CJScene();
                        sceneMap.put(CJID, scene);
                        System.out.println(CJID + "大吉大利，晚上吃鸡！活动开始");
                    }
                    if (!scene.isEnd()) {
                        CJScene cjScene = (CJScene) scene;
                        cjScene.open();
                    }
                }
            }
            if (minute == 0) {
                if (hour == 0) {
                    SceneUtil.sceneMap.remove(Integer.valueOf(1001));
                    SceneUtil.sceneMap.remove(Integer.valueOf(1007));
                    if (week.equals("Tuesday")) {
                        Scene scene = (Scene) SceneUtil.sceneMap.remove(Integer.valueOf(1011));
                        if (scene != null) {
                            DNTGScene dntgScene = (DNTGScene) scene;
                            dntgScene.removeMonsterBean();
                        }
                    }
                } else if (hour == 5) {
                    Scene scene = (Scene) SceneUtil.sceneMap.get(Integer.valueOf(1009));
                    if (scene != null) {
                        RCScene rcScene = (RCScene) scene;
                        rcScene.clean();
                    }
                }
            }
            if (hour >= Integer.parseInt(configure.getJieSartHour()) && hour < Integer.parseInt(configure.getJieEndHour())) {
                JieGuaScene.open();
            } else {
                JieGuaScene.close();
            }
        }
    }
    /**
     * 额外开启活动
     */
    public static void additionalScene(int Id) {
        Scene scene = (Scene)SceneUtil.sceneMap.get(Integer.valueOf(Id));
        if (scene == null || !scene.isEnd()) {
            if (Id == 1001) {
                SceneUtil.sceneMap.put(Integer.valueOf(1001), new BTYScene());
            }
            else if (Id == 1002) {
                SceneUtil.sceneMap.put(Integer.valueOf(1002), new TGDBScene());
            }
            else if (Id == 1007) {
                SceneUtil.sceneMap.put(Integer.valueOf(1007), new BWZScene());
            }
            else if (Id == 1008) {
                SceneUtil.sceneMap.put(Integer.valueOf(1008), new LTSScene());
            }
            else if (Id == 1013) {
                BangBattlePool.getBangBattlePool().FightOpenClose();
            }
        }
    }
    
    public static Scene getScene(int id) {
        return SceneUtil.sceneMap.get(id);
    }
    /**
     * 获取玩家所在种族赛
     */
    public static ZZSScene getZZS(LoginResult loginResult) {
        for (int i = 0; i < 5; ++i) {
            Scene scene = null;
            if (i == 0) {
                scene = getScene(1003);
            }
            else if (i == 1) {
                scene = getScene(1004);
            }
            else if (i == 2) {
                scene = getScene(1005);
            }
            else if (i == 3) {
                scene = getScene(1006);
            }
            else if (i == 4) {
                scene = getScene(10061);
            }
            if (scene != null) {
                ZZSScene zzsScene = (ZZSScene)scene;
                if (zzsScene.getRole(loginResult) != null) {
                    return zzsScene;
                }
            }
        }
        Scene scene2 = null;
        if (loginResult.getRace_id().intValue() == 10001) {
            scene2 = getScene(1003);
        }
        else if (loginResult.getRace_id().intValue() == 10002) {
            scene2 = getScene(1004);
        }
        else if (loginResult.getRace_id().intValue() == 10003) {
            scene2 = getScene(1005);
        }
        else if (loginResult.getRace_id().intValue() == 10004) {
            scene2 = getScene(1006);
        }
        else if (loginResult.getRace_id().intValue() == 10005) {
            scene2 = getScene(10061);
        }
        if (scene2 == null) {
            return null;
        }
        return (ZZSScene)scene2;
    }
    
    public static Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        if (mapId == 3324L) {
            Scene scene = (Scene)SceneUtil.sceneMap.get(Integer.valueOf(1001));
            if (scene != null) {
                return scene.getMapMonster(buffer, moveMap, mapId);
            }
        }
        else if (mapId == 1193L) {
            Scene scene = (Scene)SceneUtil.sceneMap.get(Integer.valueOf(1007));
            if (scene != null) {
                return scene.getMapMonster(buffer, moveMap, mapId);
            }
        }
        else if (mapId == 3201L || mapId == 3336L) {
            Scene scene = (Scene)SceneUtil.sceneMap.get(Integer.valueOf(1011));
            if (scene != null) {
                return scene.getMapMonster(buffer, moveMap, mapId);
            }
        }
        return moveMap;
    }
    
    public static void ZZSBattleEnd(String m1, String m2, int type) {
        for (int i = 0; i < 5; ++i) {
            Scene scene = null;
            if (i == 0) {
                scene = getScene(1003);
            }
            else if (i == 1) {
                scene = getScene(1004);
            }
            else if (i == 2) {
                scene = getScene(1005);
            }
            else if (i == 3) {
                scene = getScene(1006);
            }
            else if (i == 4) {
                scene = getScene(10061);
            }
            if (scene != null) {
                ZZSScene zzsScene = (ZZSScene)scene;
                zzsScene.BattleEnd(m1, m2, type);
            }
        }
    }
    
    public static boolean isSceneMsg(long oldMapId, long mapId) {
        return oldMapId != mapId && (mapId == 3201L || mapId == 3336L || oldMapId == 3201L || oldMapId == 3336L);
    }
    
    public static String getSceneMsg(LoginResult loginResult, long oldMapId, long mapId) {
        if (oldMapId == mapId) {
            return null;
        }
        if (mapId == 3201L || mapId == 3336L || oldMapId == 3201L || oldMapId == 3336L) {
            if (mapId != 3201L && mapId != 3336L) {
                return "1011";
            }
            Scene scene = (Scene)SceneUtil.sceneMap.get(Integer.valueOf(1011));
            if (scene != null) {
                return scene.getSceneMsg(loginResult, oldMapId, mapId);
            }
        }
        return null;
    }
}
