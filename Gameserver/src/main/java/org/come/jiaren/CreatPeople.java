package org.come.jiaren;

import come.tool.Calculation.BaseValue;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.Sepcies_MixDeal;
import come.tool.Role.RolePool;
import come.tool.Role.RoleShow;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.EventExecutor;
import org.come.bean.GetClientUserMesageBean;
import org.come.bean.LoginResult;
import org.come.bean.UseCardBean;
import org.come.entity.*;
import org.come.handler.SendMessage;
import org.come.model.Configure;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.tool.EquipTool;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;

import java.math.BigDecimal;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static org.come.action.sys.enterGameAction.getskin;



public class CreatPeople {
    public static LoginResult creatpeople(String name,int lvl,int h,Long mapid,Long x,Long y){

        ChannelHandlerContext cxt = getChannelHandlerContext();

        int l=Battlefield.random.nextInt(5)+1;
        int p=0;

        //新开区
        //1转-2转
//        int lvl;
//        if (DOOR.wuxianzhuan) {
//            lvl=Battlefield.random.nextInt(2000)+700;
//        }else {
//            lvl=Battlefield.random.nextInt(200)+120;
//        }

//        int shi=DOOR.jiarenlvl();
        lvl=Battlefield.random.nextInt(lvl - lvl/9) + lvl/9;//等级表格控制

        int zs=3;

        int speciesid=10;
        if (Objects.equals (zs, 0)) {
            speciesid=6;
        } else if (Objects.equals (zs, 1)) {
            speciesid=8;
        }else if (Objects.equals (zs, 2)) {
            speciesid=8;
        }else {
            speciesid=10;
        }

        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();

        switch (l){
            case 1:
                p=20001+Battlefield.random.nextInt(speciesid);
                break;
            case 2:
                p=21001+Battlefield.random.nextInt(speciesid);
                break;
            case 3:
                p=22001+Battlefield.random.nextInt(speciesid);
                break;
            case 4:
                p=23001+Battlefield.random.nextInt(6);
                break;
            case 5:
                p=24001+Battlefield.random.nextInt(6);
                break;
        }


        LoginResult loginResult=new LoginResult();
        loginResult.setRolename(name);
        loginResult.setUser_id(BigDecimal.valueOf(800000+h));
        loginResult.setSpecies_id(BigDecimal.valueOf(p));
        loginResult.setRace_id(BigDecimal.valueOf(Sepcies_MixDeal.getRace(loginResult.getSpecies_id())));
        loginResult.setRole_id(BigDecimal.valueOf(25000+h));
        loginResult.setTitle(Battlefield.random.nextInt(10)>5?"御武盟小虾米":"");//

        loginResult.setLocalname(Sepcies_MixDeal.getLocalName(loginResult.getSpecies_id().intValue()));
        loginResult.setSex(Sepcies_MixDeal.getSex(loginResult.getSpecies_id())==0?"女":"男");
        loginResult.setResistance("主-|副-");
        loginResult.setServerMeString("100");
        loginResult.setSavegold(BigDecimal.valueOf(0));
        loginResult.setPower(0);
        loginResult.setHavebaby(0);
        loginResult.setFighting(0);
        loginResult.setMakeloveTime(0);
        loginResult.setMoney(888);
        loginResult.setTtFail(0);
        loginResult.setGold(new BigDecimal(99999999999L));
//        loginResult.setNewrole(1);//标记机器人用


//        loginResult.setGang_id(BigDecimal.valueOf(0));//假人初始无帮派
//        int lvl=Battlefield.random.nextInt(740)+140;
//        String []dengji=lvl2(lvl).split("-");
//        while (Integer.parseInt(dengji[1])>200||Integer.parseInt(dengji[1])<70){//机器人等级
//            lvl=Battlefield.random.nextInt(740)+20;
//            dengji=lvl2(lvl).split("-");
//        }
        //loginResult.setRanling(0);
        loginResult.setDayfirstinorno(0);
         loginResult.setAttachPack(0);
        loginResult.setHjmax(0);
        loginResult.setLowOrHihtpack(0);
        loginResult.setMount_id(Battlefield.random.nextInt(4)>2?Battlefield.random.nextInt(4)+3:0);//坐骑
        loginResult.setGrade(lvl);
        loginResult.setTurnAround(zs);
        // loginResult.setBorn(loginResult.get);
        loginResult.setSkills(getskill(loginResult.getSex(), loginResult.getSpecies_id()));
        RoleTable role = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(loginResult.getRolename());
        loginResult.setHp(new BigDecimal(BaseValue.getRoleValue(loginResult.getRace_id(), 0, 0, 0)+90000000));
        loginResult.setMp(new BigDecimal(BaseValue.getRoleValue(loginResult.getRace_id(), 0, 0, 0)+10000000));

        loginResult.setCodecard (BigDecimal.valueOf (8888));
        loginResult.setSkin("10001");//假人造型

        //loginResult.setRole_id(RedisCacheUtil.getRole_pk());
        String belongId = AllServiceUtil.getOpenareatableService().selectBelong("100");
        List<RoleSummoning> pets=new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            RoleSummoning pet= JiaRenPet.jiqirenpet;
//            pets.add(pet);
//        }

        loginResult.setServerMeString(belongId);
        {


        }
        List<Goodstable> goods=new ArrayList<>();


        // 获得角色所有的灵宝
        List<Lingbao> lingbaos=new ArrayList<>();
        // 返回该角色所有宝宝
        List<Baby> babys =new ArrayList<>();
        // 获得角色所有坐骑
        List<Mount> mounts=new ArrayList<>();
        // 获取角色所有的伙伴
        List<Pal> pals=new ArrayList<>();
        List<Fly>flys=new ArrayList<>();
        loginResult.setPaysum(BigDecimal.valueOf(0));
        // loginResult.setGrade(441);
        RolePool.addRoleData(loginResult,goods,pets,lingbaos,babys,mounts,flys);
        //这个就是设置出生地的地方的
        //假人坐标
        loginResult.setMapid(mapid);//假人摆摊
        loginResult.setX(x);
        loginResult.setY(y);




        List<RoleShow> roleShows=new ArrayList<>();
        roleShows.add(loginResult.getRoleShow());
        GetClientUserMesageBean getClientUserMesageBean = new GetClientUserMesageBean();
        getClientUserMesageBean.setRoleShows(roleShows);
        roleShows.clear();

        Map<String, ChannelHandlerContext> mapRoleMap =GameServer.getMapRolesMap().get(loginResult.getMapid());

        mapRoleMap.put(loginResult.getRolename(),cxt);

        // 怪物列表
        // gameBean.setMonster(MonsterUtil.getMapMonster(mapid,loginResult.getGang_id()));
        //一些记录数据

        //系统设置
        long time = 1000L * 60L * 60L * 24L * 30L;
        UseCardBean limit = loginResult.getRoleData().getLimit("VIP");
        limit = new UseCardBean("VIP", "VIP", "1", System.currentTimeMillis() + time, "掉落率=1|经验加成=5|召唤兽死亡不掉忠诚,血法|人物死亡惩罚减半");
        loginResult.getRoleData().addLimit(limit);
        limit=new UseCardBean("超级六脉化神丸_月", "超级六脉化神丸_月", null, (Integer.parseInt("20")* time)+System.currentTimeMillis(), null);
        loginResult.getRoleData().addLimit(limit);//超级玉枢返虚丸_月
        limit=new UseCardBean("超级玉枢返虚丸_月", "超级玉枢返虚丸_月", null, (Integer.parseInt("20")* time)+System.currentTimeMillis(), null);
        loginResult.getRoleData().addLimit(limit);
        String mes = Agreement.getAgreement().intogameAgreement(GsonUtil.getGsonUtil().getgson().toJson(getClientUserMesageBean));

        SendMessage.sendMessageToAllRoles(mes);

        return loginResult;
    }

    public static ChannelHandlerContext getChannelHandlerContext() {
        return new ChannelHandlerContext() {
            @Override
            public Channel channel() {
                return null;
            }

            @Override
            public EventExecutor executor() {
                return null;
            }

            @Override
            public String name() {
                return "1";
            }

            @Override
            public ChannelHandler handler() {

                return null;
            }

            @Override
            public boolean isRemoved() {
                return false;
            }

            @Override
            public ChannelHandlerContext fireChannelRegistered() {
                return null;
            }

            @Override
            public ChannelHandlerContext fireChannelUnregistered() {
                return null;
            }

            @Override
            public ChannelHandlerContext fireChannelActive() {
                return null;
            }

            @Override
            public ChannelHandlerContext fireChannelInactive() {
                return null;
            }

            @Override
            public ChannelHandlerContext fireExceptionCaught(Throwable throwable) {
                return null;
            }

            @Override
            public ChannelHandlerContext fireUserEventTriggered(Object o) {
                return null;
            }

            @Override
            public ChannelHandlerContext fireChannelRead(Object o) {
                return null;
            }

            @Override
            public ChannelHandlerContext fireChannelReadComplete() {
                return null;
            }

            @Override
            public ChannelHandlerContext fireChannelWritabilityChanged() {
                return null;
            }

            @Override
            public ChannelHandlerContext read() {
                return null;
            }

            @Override
            public ChannelHandlerContext flush() {
                return null;
            }

            @Override
            public ChannelPipeline pipeline() {
                return null;
            }

            @Override
            public ByteBufAllocator alloc() {
                return null;
            }

            @Override
            public <T> Attribute<T> attr(AttributeKey<T> attributeKey) {
                return null;
            }

            @Override
            public <T> boolean hasAttr(AttributeKey<T> attributeKey) {
                return false;
            }

            @Override
            public ChannelFuture bind(SocketAddress socketAddress) {
                return null;
            }

            @Override
            public ChannelFuture connect(SocketAddress socketAddress) {
                return null;
            }

            @Override
            public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress1) {
                return null;
            }

            @Override
            public ChannelFuture disconnect() {
                return null;
            }

            @Override
            public ChannelFuture close() {
                return null;
            }

            @Override
            public ChannelFuture deregister() {
                return null;
            }

            @Override
            public ChannelFuture bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress1, ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture disconnect(ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture close(ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture deregister(ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture write(Object o) {
                return null;
            }

            @Override
            public ChannelFuture write(Object o, ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture writeAndFlush(Object o, ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture writeAndFlush(Object o) {
                return null;
            }

            @Override
            public ChannelPromise newPromise() {
                return null;
            }

            @Override
            public ChannelProgressivePromise newProgressivePromise() {
                return null;
            }

            @Override
            public ChannelFuture newSucceededFuture() {
                return null;
            }

            @Override
            public ChannelFuture newFailedFuture(Throwable throwable) {
                return null;
            }

            @Override
            public ChannelPromise voidPromise() {
                return null;
            }
        };
    }
    public static String getskill(String sex, BigDecimal id){
        String skill=null;


            ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
            Configure configure = s.get(1);


        if (getRaceSting(id).equals("人"))
            if (sex.equals("男")){
                skill="S1005_25000";
                // skill="S1001_25000#1002_25000#1004_25000#1005_25000#1006_25000#1007_25000#1008_25000#1009_25000#1010_2500011#1012_25000#1013_25000#1014_25000#1015_25000";
            }else {
                skill="S1020_25000";
                //skill="S1016_25000#1017_25000#1019_25000#1020_25000#1006_25000#1007_25000#1008_25000#1009_25000#1010_2500011#1012_25000#1013_25000#1014_25000#1015_25000";
            }
        else if (getRaceSting(id).equals("魔")){
            if (sex.equals("男")){
                skill="S1025_25000";
                //skill="S1021_25000#1022_25000#1024_25000#1025_25000#1026_25000#1027_25000#1028_25000#1029_25000#1030_2500011#1037_25000#1038_25000#1039_25000#1040_25000";
            }else {
                skill="S1025_25000";
                //skill="S1021_25000#1022_25000#1024_25000#1025_25000#1026_25000#1027_25000#1028_25000#1029_25000#1030_2500011#1032_25000#1033_25000#1034_25000#1035_25000";
            }

        }else if (getRaceSting(id).equals("仙")){
            if (sex.equals("男")){
                skill="S1055_25000";
                //skill="S1041_25000#1042_25000#1044_25000#1045_25000#1046_25000#1047_25000#1048_25000#1049_25000#1050_2500011#1052_25000#1053_25000#1054_25000#1055_25000";
            }else {
                skill="S1055_25000";
                //skill="S1056_25000#1057_25000#1059_25000#1060_25000#1046_25000#1047_25000#1048_25000#1049_25000#1050_2500011#1052_25000#1053_25000#1054_25000#1055_25000";
            }

        }else if (getRaceSting(id).equals("鬼")){
            if (sex.equals("男")){
                skill="S1070_25000";
                //skill="S1061_25000#1062_25000#1064_25000#1065_25000#1066_25000#1067_25000#1068_25000#1069_25000#1070_2500011#1072_25000#1073_25000#1074_25000#1075_25000";
            }else {
                skill="S1065_25000";
                //skill="S1061_25000#1062_25000#1064_25000#1065_25000#1076_25000#1077_25000#1078_25000#1079_25000#1080_2500011#1072_25000#1073_25000#1074_25000#1075_25000";
            }

        }else {
            if (sex.equals("男")){
                skill="S1085_25000";
                //skill="S1081_25000#1082_25000#1084_25000#1085_25000#1096_25000#1097_25000#1098_25000#1099_25000#1100_2500011#1092_25000#1093_25000#1094_25000#1095_25000";
            }else {
                skill="S1085_25000";
                //skill="S1081_25000#1082_25000#1084_25000#1085_25000#1086_25000#1087_25000#1088_25000#1089_25000#1090_2500011#1092_25000#1093_25000#1094_25000#1095_25000";
            }
        }




        return skill;
    }
    public static String getRaceSting(BigDecimal se) {
        if (se == null) {
            return "";
        }
        int id = se.intValue();

        if (id >= 20001 && id <= 20010) {
            return "人";
        } else if (id >= 21001 && id <= 21010) {
            return "魔";
        } else if (id >= 22001 && id <= 22010) {
            return "仙";
        } else if (id >= 23001 && id <= 23010) {
            return "鬼";
        } else if (id >= 24001 && id <= 24010) {
            return "龙";
        }

        return null;
    }
}
