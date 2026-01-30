package com.tool.ModerateTask;

import org.come.Jpanel.DreamlandTrialMainJpanel;
import org.come.Frame.DreamlandTrialMainJframe;
import org.come.Frame.PartnerArenaExchangeJframe;
import org.come.Frame.PartnerArenaWarJframe;
import org.come.Frame.PartnerArenaJframe;
import org.come.Frame.ActivityJframe;
import org.come.until.FormsManagement;
import org.come.bean.PrivateData;
import com.tool.role.RoleData;

public class TaskRoleData
{
    public static int SumReceive(int taskSetID, int type) {
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String taskComplete = data.getTaskComplete();
        if (taskComplete == null || taskComplete.equals("")) {
            if (type == 4) {
                return 5;
            }
            return 0;
        }
        else {
            String qz = taskSetID + "-";
            String[] vs = taskComplete.split("\\|");
            int i = 0;
            while (i < vs.length) {
                if (vs[i].startsWith(qz)) {
                    String[] vss = vs[i].split("-");
                    if (type == 4) {
                        return 5 - Integer.parseInt(vss[2]) + Integer.parseInt(vss[1]);
                    }
                    if (type == 3) {
                        if (vss.length == 4) {
                            return Integer.parseInt(vss[3]);
                        }
                        return 0;
                    }
                    else {
                        return Integer.parseInt(vss[type]);
                    }
                }
                else {
                    ++i;
                }
            }
            if (type == 4) {
                return 10;
            }
            return 0;
        }
    }
    
    public static void upTaskComplete(String[] values) {
        int taskSetId = Integer.parseInt(values[0]);
        int r = 0;
        int l = 0;
        int n = 0;
        for (int i = 1; i < values.length; ++i) {
            if (values[i].equals("L")) {
                l = 1;
            }
            else if (values[i].equals("R")) {
                r = 1;
            }
            else if (values[i].startsWith("N")) {
                n = Integer.parseInt(values[i].substring(1));
            }
        }
        if (r == 0 && l == 0 && n == 0) {
            return;
        }
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String taskComplete = data.getTaskComplete();
        if (taskComplete == null || taskComplete.equals("")) {
            taskComplete = taskSetId + "-" + r + "-" + l;
            if (n != 0) {
                taskComplete = taskComplete + "-" + n;
            }
        }
        else {
            String qz = taskSetId + "-";
            boolean is = true;
            StringBuffer buffer = new StringBuffer();
            String[] vs = taskComplete.split("\\|");
            for (int j = 0; j < vs.length; ++j) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                if (vs[j].startsWith(qz)) {
                    String[] ts = vs[j].split("-");
                    buffer.append(ts[0]);
                    buffer.append("-");
                    buffer.append(Integer.parseInt(ts[1]) + r);
                    buffer.append("-");
                    buffer.append(Integer.parseInt(ts[2]) + l);
                    if (n != 0) {
                        buffer.append("-");
                        buffer.append(n);
                    }
                    else if (ts.length == 4) {
                        buffer.append("-");
                        buffer.append(ts[3]);
                    }
                    is = false;
                }
                else {
                    buffer.append(vs[j]);
                }
            }
            if (is) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(taskSetId);
                buffer.append("-");
                buffer.append(r);
                buffer.append("-");
                buffer.append(l);
                if (n != 0) {
                    buffer.append("-");
                    buffer.append(n);
                }
            }
            taskComplete = buffer.toString();
        }
        data.setTaskComplete(taskComplete);
        if (FormsManagement.getInternalForm2(40) != null && FormsManagement.getframe(40).isVisible()) {
            ActivityJframe.getActivityJframe().getActivityJpanel().partRefreshView(taskSetId);
        }
        if (taskSetId == 3) {
            if (FormsManagement.getInternalForm2(5) != null && FormsManagement.getframe(5).isVisible()) {
                PartnerArenaJframe.getPartnerArenaJframe().getPartnerArenaMainPanel().refreshWarNum();
            }
        }
        else if (taskSetId == 4) {
            if (FormsManagement.getInternalForm2(80) != null && FormsManagement.getframe(80).isVisible()) {
                PartnerArenaWarJframe.getPartnerArenaWarJframe().getPartnerArenaWarPanel().refreshRecord();
            }
            if (FormsManagement.getInternalForm2(107) != null && FormsManagement.getframe(107).isVisible()) {
                PartnerArenaExchangeJframe.getPartnerArenaExchangeJframe().getPartnerArenaExchangePanel().showView();
            }
        }
        else if (taskSetId == 6 && n > 0) {
            DreamlandTrialMainJpanel dreamlandTrialMainJpanel = DreamlandTrialMainJframe.getDreamlandTrialMainJframe().getDreamlandTrialMainJpanel();
            dreamlandTrialMainJpanel.setNowLvl(n);
            dreamlandTrialMainJpanel.showLvlTier(Integer.valueOf(dreamlandTrialMainJpanel.getPageNow()));
        }
    }
    
    public static void addReceive(String taskSetID) {
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String taskComplete = data.getTaskComplete();
        if (taskComplete == null || taskComplete.equals("")) {
            taskComplete = taskSetID + "-" + 1 + "-0";
        }
        else {
            String qz = taskSetID + "-";
            String[] vs = taskComplete.split("\\|");
            boolean is = true;
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < vs.length; ++i) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                if (vs[i].startsWith(qz)) {
                    String[] ts = vs[i].split("-");
                    buffer.append(ts[0]);
                    buffer.append("-");
                    buffer.append(Integer.parseInt(ts[1]) + 1);
                    buffer.append("-");
                    buffer.append(ts[2]);
                    if (ts.length == 4) {
                        buffer.append("-");
                        buffer.append(ts[3]);
                    }
                    is = false;
                }
                else {
                    buffer.append(vs[i]);
                }
            }
            if (is) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(taskSetID);
                buffer.append("-");
                buffer.append(1);
                buffer.append("-0");
            }
            taskComplete = buffer.toString();
        }
        data.setTaskComplete(taskComplete);
    }
    
    public static void addComplete(int taskID, String taskSetID) {
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String taskComplete = data.getTaskComplete();
        if (taskComplete == null || taskComplete.equals("")) {
            if (taskID >= 3157 && taskID <= 3500) {
                taskComplete = taskSetID + "-0-0-" + taskID;
            }
            else {
                taskComplete = taskSetID + "-0-" + 1;
            }
        }
        else {
            String qz = taskSetID + "-";
            String[] vs = taskComplete.split("\\|");
            boolean is = true;
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < vs.length; ++i) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                if (vs[i].startsWith(qz)) {
                    String[] ts = vs[i].split("-");
                    buffer.append(ts[0]);
                    buffer.append("-");
                    buffer.append(ts[1]);
                    buffer.append("-");
                    if (taskID >= 3157 && taskID <= 3500) {
                        buffer.append(ts[2]);
                        buffer.append("-");
                        buffer.append(taskID);
                    }
                    else {
                        buffer.append(Integer.parseInt(ts[2]) + 1);
                        if (ts.length == 4) {
                            buffer.append("-");
                            buffer.append(ts[3]);
                        }
                    }
                    is = false;
                }
                else {
                    buffer.append(vs[i]);
                }
            }
            if (is) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(taskSetID);
                buffer.append("-0-");
                if (taskID >= 3157 && taskID <= 3500) {
                    buffer.append("0-");
                    buffer.append(taskID);
                }
                else {
                    buffer.append("1");
                }
            }
            taskComplete = buffer.toString();
        }
        data.setTaskComplete(taskComplete);
    }
}
