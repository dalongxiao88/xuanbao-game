package org.come.action.monitor;

import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.entity.Record;
import org.come.until.AllServiceUtil;
import org.come.entity.UserTable;

public class MonitorLimit
{
    public static void seal(String userName, String msg) {
        if (userName == null) {
            return;
        }
        UserTable table = new UserTable();
        table.setUsername(userName);
        table.setActivity(Short.valueOf((short)1));
        AllServiceUtil.getUserTableService().updateUser(table);
        AllServiceUtil.getRecordService().insert(new Record(5, "封号:" + userName + "_理由:" + msg));
        SendMessage.sendMessageByUserName(userName, Agreement.getAgreement().serverstopAgreement());
    }
    
    public static boolean unSeal(String userName, String msg) {
        if (userName == null) {
            return false;
        }
        if (AllServiceUtil.getUserTableService().updateUnSeal(userName) != 0) {
            AllServiceUtil.getRecordService().insert(new Record(5, "解封:" + userName + "_理由:" + msg));
            return true;
        }
        return false;
    }
    
    public static void silence(BigDecimal roleId, String msg) {
    }
    
    public static boolean unSilence(BigDecimal roleId, String msg) {
        return AllServiceUtil.getHatersService().deleteByPrimaryKey(roleId) != 0;
    }
}
