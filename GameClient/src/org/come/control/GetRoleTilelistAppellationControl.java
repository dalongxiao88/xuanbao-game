package org.come.control;

import java.util.Date;
import java.util.Iterator;
import org.come.bean.LoginResult;
import java.util.Timer;
import com.tool.time.TitleTimerTask;
import org.come.until.Util;
import java.util.Calendar;
import org.come.Jpanel.Change_titleJpanel;
import org.come.entity.Titletable;
import com.tool.role.RoleData;
import org.come.until.GsonUtil;
import org.come.bean.TitleBean;
import org.come.action.FromServerAction;

public class GetRoleTilelistAppellationControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        int n = -1;
        switch (type.hashCode()) {
            case 146356320: {
                if (type.equals("gettitlelist")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                try {
                    this.gettitlelist(mes);
                    break;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    
    public void gettitlelist(String mes) throws Exception {
        TitleBean titleBean = (TitleBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, TitleBean.class);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getTitle() != null && titleBean.getTitletables() != null && titleBean.getTitletables().size() > 0) {
            for (Titletable titletable : titleBean.getTitletables()) {
                if (titletable.getTitlename() == loginResult.getTitle() && titletable.getLimitTime() != -1) {
                    Change_titleJpanel.titleBean.getTitletables();
                    if (Change_titleJpanel.taskMap.get(loginResult.getTitle()) == null) {
                        int addTime = titletable.getLimitTime();
                        if (addTime >= 0) {
                            Date recordTime = titletable.getRecordTime();
                            Calendar c = Calendar.getInstance();
                            c.setTime(recordTime);
                            c.add(12, addTime);
                            long endTime = c.getTimeInMillis();
                            long startTime = Util.getTime();
                            long midTime = (endTime - startTime) / 1000L;
                            TitleTimerTask titleTimerTask = new TitleTimerTask(midTime, titletable.getTitlename());
                            Timer timer = new Timer();
                            timer.schedule(titleTimerTask, 0L);
                            Change_titleJpanel.taskMap.put(titletable.getTitlename(), titleTimerTask);
                        }
                        else {
                            continue;
                        }
                    }
                    else {
                        continue;
                    }
                }
            }
        }
    }
}
