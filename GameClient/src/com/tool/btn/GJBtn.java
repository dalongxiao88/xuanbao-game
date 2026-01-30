
package com.tool.btn;

import com.tool.ModerateTask.TaskData;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.tcp.GetTcpPath;
import org.come.Frame.ShoppingBuyJframe;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.GZJpanel;
import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.model.Shop;
import org.come.socket.Agreement;
import org.come.socket.DownLoadTxt;
import org.come.socket.GameClient;
import org.come.socket.SendMessageUntil;
import org.come.until.FormsManagement;
import org.come.until.UserMessUntil;
import org.come.until.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GJBtn extends MoBanBtn {
    private static final long serialVersionUID = 7822149256715030997L;
    private final int formsid;
    private int x;
    private int y;
    private String text;
    private boolean is;
    private GZJpanel gzJpanel;
    private TaskData taskData;
    private String gjName;

    public GJBtn(String iconpath, int type, int id) {
        super(iconpath, type);
        this.formsid = id;
    }

    public GJBtn(String iconpath, int type, Color[] colors, Font font, String text, int id, GZJpanel gzJpanel, TaskData taskData) {
        super(iconpath, type, colors);
        this.formsid = id;
        this.setText(text);
        this.setFont(font);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        this.gzJpanel = gzJpanel;
        this.taskData = taskData;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.text != null) {
//            g.setFont(UIUtils.TEXT_FONT);
//            g.setColor();
            if (this.type == 2) {
                g.drawString(this.text, this.x + 1, this.y + 1);
            } else {
                g.drawString(this.text, this.x, this.y);
            }
        }

    }


    public void chooseyes() {
    }

    public void chooseno() {
    }

    public void nochoose(MouseEvent e) {
        if (formsid == 9999) {
            BigDecimal zgj = RoleData.getRoleData().getLoginResult().getScoretype("功绩");
            String gz = Util.setZhiWeiRank(zgj.intValue());
            Integer i1 = Util.gjMap.get(gz);
            Integer i2 = Util.gjMap.get(gjName);
            if(i1<i2){
                ZhuFrame.getZhuJpanel().addPrompt2("#W需要官职#R"+gjName+"#W以上才可以领取当前任务！");
                return;
            }
            if(RoleData.getRoleData().getPrivateData().getTaskData() != null && RoleData.getRoleData().getPrivateData().getTaskData().equals(taskData.getTaskID())){
                ZhuFrame.getZhuJpanel().addPrompt2("无需重复领取任务#23");//这改动了
                return;
            }

            gzJpanel.showGoods( taskData,gjName,this);
            String mes = Agreement.getAgreement().TaskNAgreement("L" +taskData.getTaskID());
            SendMessageUntil.toServer(mes);
        } if (formsid == 9998) {
            List<Shop> npcshop180 = UserMessUntil.getNpcshop().getNpcShopMap().get("7120");
            ShoppingBuyJframe.getShoppingBuyJframe().getShoppingBuyJpanel().showshop(npcshop180, "7120", null);
            FormsManagement.showForm(23);
        } else
            gzJpanel.showGoods(taskData, gjName,this);
    }

    public static final int getProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0]);
    }

    public static void STRTMP() {
        if (GetTcpPath.STRTMP.equals("1")) {
            GetTcpPath.STRTMP = "2";
            run("已切换为全屏法术！");
        } else {
            GetTcpPath.STRTMP = "1";
            run("已切换为非全屏法术!");
        }
    }

    /**
     * 联系客服
     */

    public void complainOpenWeb1() {
        DownLoadTxt.getDownLoadTxt().initMes("configure.txt");
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));
        String url = configure.getJumpurl();
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        String stringParams = sb.toString();
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(stringParams));
        } catch (IOException var7) {
            var7.printStackTrace();
        } catch (URISyntaxException var8) {
            var8.printStackTrace();
        }

    }

    public void complainOpenWeb() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleName", ImageMixDeal.userimg.getRoleShow().getRolename());
        params.put("roleAccount", RoleData.getRoleData().getLoginResult().getUserName());
        params.put("roleQuid", GameClient.potAndIpStrings[5]);
        String url = "http://www.dongmengzhongchou.com:80801/question";
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        sb.append("?");
        if (params != null) {
            Iterator var5 = params.entrySet().iterator();

            while (var5.hasNext()) {
                Entry<String, Object> e = (Entry) var5.next();
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }

            sb = sb.deleteCharAt(sb.length() - 1);
        }

        String stringParams = sb.toString();
        Desktop desktop = Desktop.getDesktop();

        try {
            desktop.browse(new URI(stringParams));
        } catch (IOException var7) {
            var7.printStackTrace();
        } catch (URISyntaxException var8) {
            var8.printStackTrace();
        }

    }

    //    /**
//     * 鼠标进入
//     * @param e
//     */
//    public void mouseEntered(MouseEvent e) {
//        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
//    }
//    /**
//     * 鼠标离开
//     * @param e
//     */
//    public void mouseExited(MouseEvent e) {
//        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
//    }
    public static void Show() {
        if (ImageMixDeal.userimg.getRoleShow().getGang_id() != null && ImageMixDeal.userimg.getRoleShow().getGang_id().intValue() != 0) {
            if (FormsManagement.getframe(48).isVisible()) {
                FormsManagement.HideForm(48);
            } else {
                String senmes = Agreement.getAgreement().IntogangAgreement(ImageMixDeal.userimg.getRoleShow().getGang_id().toString());
                SendMessageUntil.toServer(senmes);
            }
        } else {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有帮派!");
        }
    }

    public static void run(String type) {
        try {
            ZhuFrame.getZhuJpanel().addPrompt2(type);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public String getGjName() {
        return gjName;
    }

    public void setGjName(String gjName) {
        this.gjName = gjName;
    }

    public int getFormsid() {
        return formsid;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }
}
