package org.come.control;

import java.util.HashSet;
import org.apache.commons.lang.StringUtils;
import java.util.Set;
import org.come.Frame.ZhuFrame;
import org.come.Frame.QiandaoListJframe;
import org.come.until.FormsManagement;
import org.come.action.FromServerAction;

public class QianDaoControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        System.out.println("mes:" + mes);
        if (mes.contains("open")) {
            FormsManagement.showForm(1100);
            LoginQD.init(mes.substring(5));
            QiandaoListJframe.getQiandaoListJpanel().setDay(LoginQD.getEd().size());
            QiandaoListJframe.getQiandaoListJpanel().setDays(LoginQD.getEd());
            QiandaoListJframe.getQiandaoListJpanel().setLqs(LoginQD.getLq());
        }
        if (mes.contains("qd")) {
            String[] openInfo = mes.split("=");
            if (openInfo[1].contains("succ")) {
                QiandaoListJframe.getQiandaoListJpanel().getDays().add(Integer.valueOf(openInfo[1].split("&")[1]));
            }
            if (openInfo[1].contains("fail")) {
                ZhuFrame.getZhuJpanel().addPrompt2(openInfo[1].split("&")[1]);
            }
        }
        if (mes.contains("select")) {
            String[] openInfo = mes.split("=");
            if (openInfo[1].contains("succ")) {
                QiandaoListJframe.getQiandaoListJpanel().getLqs().add(Integer.valueOf(openInfo[1].split("&")[1]));
            }
            if (openInfo[1].contains("fail")) {
                ZhuFrame.getZhuJpanel().addPrompt2(openInfo[1].split("&")[1]);
            }
        }
    }
    
    public static class LoginQD
    {
        private static Set<Integer> ed;
        private static Set<Integer> lq;
        
        public static void init(String info) {
            if (StringUtils.isEmpty(info)) {
                return;
            }
            String[] split = info.split("&");
            String[] eds = split[0].split("=");
            if (eds.length > 1) {
                LoginQD.ed = new HashSet<>();
                for (String s : eds[1].split(",")) {
                    LoginQD.ed.add(Integer.valueOf(s));
                }
            }
            String[] lqs = split[1].split("=");
            if (lqs.length > 1) {
                LoginQD.lq = new HashSet<>();
                for (String s2 : lqs[1].split(",")) {
                    LoginQD.lq.add(Integer.valueOf(s2));
                }
            }
        }
        
        public static Set<Integer> getEd() {
            return LoginQD.ed;
        }
        
        public static Set<Integer> getLq() {
            return LoginQD.lq;
        }
        
        static {
            LoginQD.ed = new HashSet<>();
            LoginQD.lq = new HashSet<>();
        }
    }
}
