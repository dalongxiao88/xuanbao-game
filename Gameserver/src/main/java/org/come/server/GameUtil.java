package org.come.server;

import java.util.concurrent.ConcurrentHashMap;

public class GameUtil
{
    public static ConcurrentHashMap<Long, GameMap> allGameMap;
    private static Object object;
    
    public static GameMap getGameMap(long mapID) {
        GameMap gameMap = (GameMap)GameUtil.allGameMap.get(Long.valueOf(mapID));
        if (gameMap == null) {
            synchronized (GameUtil.object) {
                gameMap = (GameMap)GameUtil.allGameMap.get(Long.valueOf(mapID));
                if (gameMap == null) {
                    gameMap = new GameMap(mapID);
                    GameUtil.allGameMap.put(Long.valueOf(mapID), gameMap);
                }
            }
        }
        return gameMap;
    }
    
    static {
        GameUtil.allGameMap = new ConcurrentHashMap<>();
        GameUtil.object = new Object();
    }
}
