package com.gl.service;

import java.util.HashMap;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import org.come.readUtil.ReadPoolUtil;
import org.springframework.web.multipart.MultipartFile;
import com.gl.util.Config;
import java.text.DateFormat;
import org.come.until.GsonUtil;
import org.come.bean.ChatBean;
import org.come.until.AllServiceUtil;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import org.come.entity.Wechatrecord;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.apache.commons.lang.math.NumberUtils;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
import com.github.pagehelper.util.StringUtil;
import com.gl.model.Param;
import org.come.until.ReadTxtUtil;
import org.come.tool.ReadExelTool;
import java.util.Map;

public class GameService
{
    //读取游戏公告
    public static Map<String, Integer> XLSmap;
    
    public String readGG() {
        return ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\gg.txt");
    }
    //写入游戏公告
    
    public boolean writeGG(Param param) {
        String gg = param.getValue1();
        if (StringUtil.isNotEmpty(gg)) {
            try {
                // 先备份之前的公告
                File file = new File(ReadExelTool.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\gg.txt");
                if (file.exists()) {
                    file.renameTo(new File(ReadExelTool.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\gg_" + System.currentTimeMillis() + ".txt"));
                }
                File f = new File(ReadExelTool.class.getResource("/").getPath().replaceAll("WEB-INF/classes/", "") + "GetTXT\\gg.txt");
                if (!f.exists()) {
                    f.createNewFile();
                }
                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
                BufferedWriter writer = new BufferedWriter(write);
                writer.write(gg);
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        else {
            return false;
        }
    }
    //发布系统消息 3 聊天框 系统消息，4 屏幕中心喇叭 5 屏幕下方滚动
    
    public boolean sendMsg(Param param) {
        String type = param.getValue1();
        String message = param.getValue2();
        if (StringUtil.isNotEmpty(type) && NumberUtils.isDigits(type) && StringUtil.isNotEmpty(message)) {
            String msg = Agreement.getAgreement().chatAgreement("{\"id\":" + type + ",\"message\":\"" + message + "\"}");
            SendMessage.sendMessageToAllRoles(msg);
            return true;
        }
        return false;
    }
    //发布私聊消息
    
    public boolean sendMsgToPlayer(String message, String roleName) {
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleName);
        if (ctx != null) {
            LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            Wechatrecord wechatrecord = new Wechatrecord();
            wechatrecord.setChatGetid(roleInfo.getRole_id());
            wechatrecord.setChatMes(message);
            wechatrecord.setChatSendid(new BigDecimal(0));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowdayTime = dateFormat.format(new Date());
            wechatrecord.setTime(nowdayTime);
            AllServiceUtil.getWechatrecordService().insert(wechatrecord);
            ChatBean chatBean = new ChatBean();
            chatBean.setFriendName(roleName);
            chatBean.setMessage(message);
            chatBean.setRolename("游戏管理员");
            chatBean.setTime(new Date().getTime());
            String msg = Agreement.getAgreement().friendchatAgreement(GsonUtil.getGsonUtil().getgson().toJson(chatBean));
            SendMessage.sendMessageByRoleName(roleName, msg);
            return true;
        }
        return false;
    }
    //加载配置
    
    public Map<String, String> readConfig() {
        return Config.getValue();
    }
    //保存配置
    public boolean saveConfig(Map<String, String> map) {
        return Config.update(map);
    }
    // 在线配置更新
    public String updateXls(MultipartFile file) throws IllegalStateException, IOException {
        if (!file.isEmpty()) {
            String NewFileName = file.getOriginalFilename();
            System.err.println("在线配置更新：" + GameService.XLSmap.get(NewFileName));
            System.err.println("在线配置更新Name：" + NewFileName);
            String ext = (NewFileName.indexOf(".") != -1) ? NewFileName.substring(NewFileName.lastIndexOf(".") + 1, NewFileName.length()) : null;
            if (ext != null) {
                if ("xls".equals(ext) && GameService.XLSmap.containsKey(NewFileName)) {
                    long times = System.currentTimeMillis();
                    // 根据文件名复制老配置文件为文件名.时间戳
                    File oldFile = new File(ReadExelTool.class.getResource("/").getPath() + "config/" + NewFileName);
                    File oldBackFile = new File(ReadExelTool.class.getResource("/").getPath() + "config/" + NewFileName + times);
                    if (oldFile.exists()) {
                        oldFile.renameTo(oldBackFile);
                    }
                    // 上传文件
                    File dest = new File(ReadExelTool.class.getResource("/").getPath() + "config/" + NewFileName);
                    file.transferTo(dest);
                    StringBuffer buffer = new StringBuffer();
                    if (ReadPoolUtil.readTypeTwo(buffer, (int)GameService.XLSmap.get(NewFileName))) {
                        return "";// 成功加载配置
                    }
                    System.out.println(buffer.toString());
                    // 加载配置失败，回滚文件
                    if (dest.exists()) {
                        dest.delete();
                    }
                    if (oldBackFile.exists()) {
                        oldBackFile.renameTo(dest);
                    }
                    return "配置文件错误加载失败";
                }
                else {
                    return "文件类型错误或不识别的配置文件";
                }
            }
            else {
                return "文件类型不正确";
            }
        }
        else {
            return "上传文件为空";
        }
    }
    //配置文件列表（不分页）
    public Map<String, Long> readXls() {
        File dir = new File(ReadExelTool.class.getResource("/").getPath() + "config/");
        Map<String, Long> fileList = new LinkedHashMap<>();
        File[] fs;
        for (File f : fs = dir.listFiles()) {
            if (!f.isDirectory()) {
                fileList.put(f.getName(), Long.valueOf(f.lastModified()));
            }
        }
        Map<String, Long> result = new LinkedHashMap<>();
        fileList.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x/* java.util.Map.Entry, */ -> {
            Long n = (Long)result.put(x.getKey(), x.getValue());
            return;
        });
        return result;
    }
    //配置文件下载（单个）
    public String downloadXls(String fileName, OutputStream out) {
        try {
            File file = new File(ReadExelTool.class.getResource("/").getPath() + "config/" + fileName);
            if (!file.exists()) {
                return "下载文件不存在";
            }
            //读取要下载的图片或者文件，将其放在缓冲中，再下载
            FileInputStream in = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    //配置文件删除（仅允许删除时间戳结尾的文件）
    public String deleteXls(String fileName) {
        File file = new File(ReadExelTool.class.getResource("/").getPath() + "config/" + fileName);
        if (!file.exists()) {
            return "要删除的文件不存在";
        }
        if (fileName.length() - fileName.indexOf("xls") != 16) {
            return "非备份文件不可删除";
        }
        file.delete();
        return "";
    }
    
    static {
        (GameService.XLSmap = new HashMap<>()).put("pet.xls", 0);
        GameService.XLSmap.put("petExchange.xls", 2);
        GameService.XLSmap.put("map.xls", 3);
        GameService.XLSmap.put("npc.xls", 4);
        GameService.XLSmap.put("door.xls", 5);
        GameService.XLSmap.put("taskSet.xls", 6);
        GameService.XLSmap.put("taskData.xls", 6);
        GameService.XLSmap.put("palData.xls", 7);
        GameService.XLSmap.put("boos.xls", 8);
        GameService.XLSmap.put("monster.xls", 9);
        GameService.XLSmap.put("robots.xls", 10);
        GameService.XLSmap.put("item.xls", 11);
        GameService.XLSmap.put("newequip.xls", 12);
        GameService.XLSmap.put("alchemy.xls", 13);
        GameService.XLSmap.put("decorate.xls", 14);
        GameService.XLSmap.put("godstone.xls", 15);
        GameService.XLSmap.put("palEquip.xls", 16);
        GameService.XLSmap.put("shop.xls", 17);
        GameService.XLSmap.put("eshop.xls", 18);
        GameService.XLSmap.put("lShop.xls", 19);
        GameService.XLSmap.put("sghostpoint.xls", 20);
        GameService.XLSmap.put("xianqi.xls", 21);
        GameService.XLSmap.put("lingbao.xls", 22);
        GameService.XLSmap.put("lingbaofushi.xls", 23);
        GameService.XLSmap.put("gem.xls", 24);
        GameService.XLSmap.put("skill.xls", 25);
        GameService.XLSmap.put("drop.xls", 26);
        GameService.XLSmap.put("dntg.xls", 27);
        GameService.XLSmap.put("bbuy.xls", 28);
        GameService.XLSmap.put("suit.xls", 29);
        GameService.XLSmap.put("tx.xls", 30);
        GameService.XLSmap.put("present.xls", 31);
        GameService.XLSmap.put("exp.xls", 32);
        GameService.XLSmap.put("mount.xls", 33);
        GameService.XLSmap.put("color.xls", 34);
        GameService.XLSmap.put("child.xls", 35);
        GameService.XLSmap.put("draw.xls", 36);
        GameService.XLSmap.put("acard.xls", 37);
        GameService.XLSmap.put("title.xls", 38);
        GameService.XLSmap.put("event.xls", 39);
        GameService.XLSmap.put("wingTraining.xls", 40);
        GameService.XLSmap.put("starPalace.xls", 41);
        GameService.XLSmap.put("tyc.xls", 42);
        GameService.XLSmap.put("babyresult.xls", 43);
        GameService.XLSmap.put("guide.xls", 44);
        GameService.XLSmap.put("active.xls", 45);
        GameService.XLSmap.put("achieve.xls", 46);
        GameService.XLSmap.put("lh.xls", 47);
        GameService.XLSmap.put("Meridians.xls", 48);
        GameService.XLSmap.put("goodsExchange.xls", 49);
        GameService.XLSmap.put("qiandao.xls", 48);
        GameService.XLSmap.put("itemExchange.xls", 50);
        GameService.XLSmap.put("GMshopItem.xls", 51);
        GameService.XLSmap.put("configure.xls", 52);
        GameService.XLSmap.put("fly.xls", 53);
        GameService.XLSmap.put("lhtj.xls", 54);
        GameService.XLSmap.put("wuzhu.xls", 55);
        GameService.XLSmap.put("shaoxiang.xls", 56);
        GameService.XLSmap.put("vipActive.xls", 57);
        GameService.XLSmap.put("lianghao.xls", 58);
        GameService.XLSmap.put("payvip.xls", 59);
        GameService.XLSmap.put("chongjipack.xls", 60);
        GameService.XLSmap.put("lotteryitem.xls", 61);
        GameService.XLSmap.put("auctionGoods.xls", 62);
        GameService.XLSmap.put("selllianghao.xls", 63);
        GameService.XLSmap.put("purchase.xls", 64);
        GameService.XLSmap.put("golemActive.xls", 67);
        GameService.XLSmap.put("golemDraw.xls", 68);
        GameService.XLSmap.put("golemConfig.xls", 69);
        GameService.XLSmap.put("golemStall.xls", 70);
        GameService.XLSmap.put("golemName.xls", 71);
        GameService.XLSmap.put("taskList.xls", 71);
        GameService.XLSmap.put("achievement.xls", 70);
        GameService.XLSmap.put("bt.xls", 70);
        GameService.XLSmap.put("car.xls", 72);
    }
}
