package org.come.redis;

import come.tool.FightingData.Battlefield;
import org.come.bean.NChatBean;
import org.come.entity.JiaRenBT;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.server.GolemBean;
import org.come.server.GolemConfig;
import org.come.server.GolemServer;
import org.come.until.GsonUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShiJieSendMessage   implements Runnable {
    // 静态变量来保存 StallSendMessage 实例
    public static ShiJieSendMessage instance;
    public static List<NChatBean> botROLES = new ArrayList<>();
    public static double pinLv = 1.0;//分钟
    // 添加线程安全的开关控制变量
    private AtomicBoolean running = new AtomicBoolean(true);
    @Override
    public void run() {
        while (running.get()) { // 使用开关控制循环
            GolemConfig config = GameServer.getGolemConfig();
            if (Objects.equals(config.get("世界喊话").split("\\|")[0], "开")) {
                String[] mes=config.get("世界喊话").split("\\|")[2].split("&");
                if (mes != null&&mes.length>2) {
                    creatMessage(mes);
                }
            }
            try {
                Thread.sleep((long) (60000*pinLv)); // 假设每20秒执行一次
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }


    // 提供方法来设置开关状态
    public void setRunning(boolean running) {
        this.running.set(running);
    }

    // 提供方法来获取开关状态
    public boolean isRunning() {
        return running.get();
    }

    public static List<NChatBean> getBotROLES() {
        if (botROLES == null) {
            botROLES = new ArrayList<>();
        }
        return botROLES;
    }

    public static void setBotROLES(List<NChatBean> botROLES) {
        StallSendMessage.botROLES = botROLES;
    }

    public static void start() {
        if (instance == null) {
            instance = new ShiJieSendMessage();
            instance.setRunning(true);
            GolemConfig config = GameServer.getGolemConfig();
            if (config.get("世界喊话")!=null&&config.get("世界喊话").split("\\|").length>1) {
                pinLv = Double.parseDouble(config.get("世界喊话").split("\\|")[1]);
            }

            new Thread(instance).start();
        } else {
            System.out.println("机器人摆摊喊话已开启");
        }
    }

    public static void stop() {
        if (instance != null) {
            instance.setRunning(false);
            instance = null; // 可选：将实例置为null以便垃圾回收
        } else {
            System.out.println("机器人摆摊喊话已关闭");
        }
    }

    public static BigDecimal oldID = new BigDecimal(0);
    public static String oldMesssage = "";
    public static void creatMessage(String[] mes) {
        NChatBean nchat = new NChatBean();
        nchat.setId(3);//世界

        while (true){
            String m = mes[Battlefield.random.nextInt(mes.length)];
            if (!Objects.equals(m, oldMesssage)) {
                nchat.setMessage(m);
                oldMesssage = m;
                break;
            }
        }

        while (true){
            boolean have =false;
            for (Map.Entry<String, GolemBean> entry : GolemServer.loginGolems.entrySet()) {
                if (Battlefield.random.nextInt(100) < 30) {
                    String key = entry.getKey(); // 获取键
                    GolemBean golemBean = entry.getValue();
                    if (golemBean.getRoleId().compareTo(oldID)!=0) {
                        nchat.setRoleId(golemBean.getRoleId());
                        nchat.setRole(golemBean.getRoleName());
                        oldID = golemBean.getRoleId();
                        have=true;
                        break;
                    }
                }
            }
            if (have) {
                break;
            }
        }

        if (nchat.getMessage() != null && !nchat.getMessage().equals("")) {
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(nchat));
            SendMessage.sendMessageToAllRoles(msg);
        }
    }
}
