package org.come.nettyClient;

import org.come.bean.IpActionBean;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.Account;
import org.come.bean.AccountActionBean;

public class UrlUntil
{
    public static String account_ip;
    public static String tomcat_port;
    public static String poject;
    public static int account_port;
    public static long time;
    
    public static void accountAction(String type, String account_flag, String param) {
        AccountActionBean accaction = new AccountActionBean();
        Account account = new Account();
        account.setAc_flag(account_flag);
        if ("updatePhone".equals(type)) {
            account.setAc_phone(param);
        }
        else if ("updatePasw".equals(type)) {
            account.setAc_pasw(param);
        }
        else if ("updateSafely".equals(type)) {
            account.setAc_safely(param);
        }
        accaction.setAccount(account);
        accaction.setType(type);
        String Content = GsonUtil.getGsonUtil().getgson().toJson(accaction);
        String res = Agreement.getAgreement().Account_UpdateAgreement(Content);
        ClientSendMessage.toServer(UrlUntil.account_ip + "|" + UrlUntil.account_port, res);
    }
    
    public static void ipAction(String type, String ip) {
        IpActionBean ipaction = new IpActionBean();
        ipaction.setType(type);
        ipaction.setIpaddress(ip);
        String content = GsonUtil.getGsonUtil().getgson().toJson(ipaction);
        String res = Agreement.getAgreement().Ip_ActionAgreement(content);
        ClientSendMessage.toServer(UrlUntil.account_ip + "|" + UrlUntil.account_port, res);
    }
    
    static {
        UrlUntil.account_ip = "127.0.0.1";
        UrlUntil.tomcat_port = "8083";
        UrlUntil.poject = "loginServer";
        UrlUntil.account_port = 7100;
    }
}
