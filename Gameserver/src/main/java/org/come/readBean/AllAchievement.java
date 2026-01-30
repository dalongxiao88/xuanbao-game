package org.come.readBean;

import org.come.model.Achievement;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 功绩千秋
 * @author admin
 *
 */
public class AllAchievement {
	private ConcurrentHashMap<Integer, Achievement> allAchievement;

	public ConcurrentHashMap<Integer, Achievement> getAllAchievement() {
		return this.allAchievement;
	}

	public void setAllPetExchange(ConcurrentHashMap<Integer, Achievement> allAchievement) {
		this.allAchievement = allAchievement;
	}


}
