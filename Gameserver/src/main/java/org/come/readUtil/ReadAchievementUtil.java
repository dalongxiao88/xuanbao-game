package org.come.readUtil;

import org.come.handler.MainServerHandler;
import org.come.model.Achievement;
import org.come.readBean.AllAchievement;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.tool.ReadExelTool;
import org.come.tool.SettModelMemberTool;
import org.come.until.GsonUtil;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 功绩千秋
 * @author admin
 *
 */
public class ReadAchievementUtil {

	/**读取功绩千秋表*/
	public static ConcurrentHashMap<Integer, Achievement> allAchievementMap(String path, StringBuffer buffer) {
		ConcurrentHashMap<Integer, Achievement> allPetExchange = new ConcurrentHashMap<Integer, Achievement>();
		String[][] result = ReadExelTool.getResult("config/"+path+".xls");
		for (int i = 1; i < result.length; i++) {
			if (result[i][0].equals("")) {continue;}
			Achievement achievement=new Achievement();
			for (int j = 0; j < result[i].length; j++) {
				try {
					SettModelMemberTool.setReflect(achievement,result[i][j], j);
				} catch (Exception e) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                    return null;
                }
			}
			if (achievement.getId()!=0) {
				allPetExchange.put(achievement.getId(), achievement);
			}
		}
		return allPetExchange;
	}
	/**获取功绩千秋表txt数据*/
	public static String createTxtAchievement(ConcurrentHashMap<Integer, Achievement> map){
		AllAchievement allAchievement = new AllAchievement();
		allAchievement.setAllPetExchange(map);
		String msgString = GsonUtil.getGsonUtil().getgson().toJson(allAchievement);
		return msgString;
	}
}
