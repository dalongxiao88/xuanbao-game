package org.come.control;

import org.come.Frame.AchievemMinJframe;
import org.come.action.FromServerAction;
import org.come.model.Achievement;
import org.come.until.FormsManagement;
import org.come.until.UserMessUntil;
import org.come.Frame.AchievemJframe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 功绩千秋
 * @author Administrator
 *
 */
public class AchievemControl implements FromServerAction {
    @Override
    public void controlMessFromServer(String mes,String type) {
//        System.out.println("mes:" + mes);
        //打开获取
        if (mes.contains("功绩千秋")) {// 检查是否包含"功绩千秋"成就消息
        	List<Achievement> mapList = new ArrayList<>();
			// 获取所有成就条目
            Set<Entry<Integer, Achievement>> entrySet = UserMessUntil.getAllAchievement().getAllAchievement().entrySet();
			// 遍历成就列表
            for (Entry<Integer, Achievement> entry : entrySet) {
            	Achievement ach = entry.getValue();
				// 匹配特定条件的成就
            	if(ach.getConditions().equals(mes.split("=")[1])) {
					// 显示成就窗体(3073)
            		FormsManagement.showForm(3073);
					// 设置成就面板显示内容
					AchievemMinJframe.getAchievemMinJpanel().getMcbel().setText(ach.getConditions());
					// 设置条件文本
            		AchievemMinJframe.getAchievemMinJpanel().getGjbel().setText(ach.getPrice());
					// 设置奖励文本
            		AchievemMinJframe.getAchievemMinJpanel().getMsgbel().setText(ach.getDescribeText());
					// 设置描述文本
					// 初始化动画参数
            		AchievemMinJframe.getAchievemMinJpanel().x = 345/2;
            		AchievemMinJframe.getAchievemMinJpanel().w = 1;
            		Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                        	try {
								// 执行平移动画效果
                        		while (AchievemMinJframe.getAchievemMinJpanel().x>0) {
									AchievemMinJframe.getAchievemMinJpanel().x = AchievemMinJframe.getAchievemMinJpanel().x - 1;
									AchievemMinJframe.getAchievemMinJpanel().w = AchievemMinJframe.getAchievemMinJpanel().w + 2;
									Thread.sleep(1);
								}
            				} catch (Exception e) {
            					// TODO Auto-generated catch block
            					e.printStackTrace();
            				}
                        }
                    });
                    thread.start();// 启动动画线程
            	}
            }
        }
    }
}
