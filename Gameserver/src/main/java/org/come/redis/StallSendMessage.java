package org.come.redis;

import come.tool.FightingData.Battlefield;

import org.come.bean.NChatBean;
import org.come.entity.Goodstable;
import org.come.entity.JiaRenBT;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.server.GolemConfig;
import org.come.until.GsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class StallSendMessage  implements Runnable {
    // 静态变量来保存 StallSendMessage 实例
    public static StallSendMessage instance;
    public static List<NChatBean> botROLES = new ArrayList<>();
    public static double pinLv = 1.0;//分钟
    // 添加线程安全的开关控制变量
    private AtomicBoolean running = new AtomicBoolean(true);
    @Override
    public void run() {
        while (running.get()) { // 使用开关控制循环

            if (botROLES != null) {
                String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(botROLES.get(Battlefield.random.nextInt(botROLES.size()))));
                SendMessage.sendMessageToAllRoles(msg);
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
            instance = new StallSendMessage();
            instance.setRunning(true);
            GolemConfig config = GameServer.getGolemConfig();
            if (config.get("摆摊喊话")!=null&&config.get("摆摊喊话").split("\\|").length>1) {
                pinLv = Double.parseDouble(config.get("摆摊喊话").split("\\|")[1]);
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

    /**
     * 创建摆摊喊话消息   //  #V{"type":13,"id":166,"name":"[星星混元丹]","color":"G"}#L
     * @param goodstable
     * @param boothname
     * @return
     */
    public static String creatMessage(String goodstable, String boothname, int stallid, List<JiaRenBT> list) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAuto_sj() != null && !list.get(i).getAuto_sj().equals("")) {
                String msg = list.get(i).getAuto_sj();
                if (msg.contains("*"))msg=msg.replace("*", goodstable);
                msg=msg.replace("@", "#V{\"type\":13,\"id\":"+stallid+",\"name\":\"["+boothname+"]\",\"color\":\"G\"}#L");
                return msg;
            }
        }
        return null;
    }
}