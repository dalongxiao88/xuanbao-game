package org.come.model;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 功绩千秋
 * @author admin
 *
 */
public class AllAchievement {
    private ConcurrentHashMap<Integer, Achievement> allAchievement;

    public ConcurrentHashMap<Integer, Achievement> getAllAchievement() {
        return allAchievement;
    }

    public void setAllAchievement(ConcurrentHashMap<Integer, Achievement> allAchievement) {
        this.allAchievement = allAchievement;
    }

}
