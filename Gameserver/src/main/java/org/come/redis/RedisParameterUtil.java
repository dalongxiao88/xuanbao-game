package org.come.redis;

import org.come.server.GameServer;

public class RedisParameterUtil {
    public static final String BATTLEROLE;
    public static final String BABY;
    public static final String FRIENDS;
    public static final String GANG_APPLY;
    public static final String GANG;
    public static final String GANG_NAME;
    public static final String GOODS;
    public static final String ROBOT;
    public static final String GOODSID;
    public static final String GOODSST;
    public static final String MOUNT;
    public static final String FLY;
    public static final String PET;
    public static final String LINGBAO;
    public static final String XUANBAO;
    public static final String PAL;
    public static final String GOODS_RECORD;
    public static final String USER_REDIS;
    public static final String ROLE_CONTROL;
    public static final String COPY_LOGIN;
    public static final String COPY_PACK;
    public static final String SALESGOODS_STATUES;
    public static final String SELL;
    public static final String SHAOXIANGLIMMIT;
    public static final String AUTOTASKLIMIT;
    public static final String USER_LOGIN_IP = "USER_LOGIN_IP:";
    public static final String FENG_USER_IP = "FENG_USER_IP:";
    public static final String LIMITED_TIME_LSHOP = "LIMITED_TIME_LSHOP";
    public static final String DICE;
    public static final String CAR;

    static {
        BATTLEROLE = GameServer.area + "BATTLEROLE";
        BABY = GameServer.area + "BABY";
        FRIENDS = GameServer.area + "FRIENDS";
        GANG_APPLY = GameServer.area + "GANG_APPLY";
        GANG = GameServer.area + "GANG";
        GANG_NAME = GameServer.area + "GANG_NAME";
        GOODS = GameServer.area + "GOODS";
        ROBOT = GameServer.area + "ROBOT";
        GOODSID = GameServer.area + "GOODSID";
        GOODSST = GameServer.area + "GOODSST";
        MOUNT = GameServer.area + "MOUNT";
        FLY = GameServer.area + "FLY";
        PET = GameServer.area + "PET";
        LINGBAO = GameServer.area + "LINGBAO";
        XUANBAO = GameServer.area + "XUANBAO";
        PAL = GameServer.area + "PAL";
        GOODS_RECORD = GameServer.area + "GOODS_RECORD";
        USER_REDIS = GameServer.area + "USER_REDIS";
        ROLE_CONTROL = GameServer.area + "control_redis_delete";
        COPY_LOGIN = GameServer.area + "COPY_LOGIN";
        COPY_PACK = GameServer.area + "COPY_PACK";
        SALESGOODS_STATUES = GameServer.area + "SALESGOODS_STATUES";
        SELL = GameServer.area + "SELL";
        SHAOXIANGLIMMIT = GameServer.area + "SHAOXIANGLIMMIT";
        AUTOTASKLIMIT = GameServer.area + "AUTOTASKLIMIT_";
        DICE = GameServer.area + "DICE";
        CAR = GameServer.area + "CAR";
    }
}
