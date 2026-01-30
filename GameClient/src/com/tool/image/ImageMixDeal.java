package com.tool.image;

import org.come.until.CutButtonImage;
import come.tool.Scene.DNTGScene;
import com.tool.ModerateTask.TaskProgress;
import java.awt.image.BufferedImage;
import com.tool.imagemonitor.PlayerMonitor;
import com.tool.imagemonitor.CreepsMonitor;
import com.tool.imagemonitor.TaskMonitor;
import com.tool.imagemonitor.NpcMonitor;
import org.come.until.UserStallUntil;
import org.come.until.MessagrFlagUntil;
import java.math.BigDecimal;
import org.come.model.Door;
import org.come.bean.NpcInfoBean;
import java.util.ArrayList;
import org.come.until.UserMessUntil;
import org.come.until.SplitStringTool;
import org.come.Jpanel.ZhuJpanel;
import java.util.Iterator;
import come.tool.BangBattle.BangFight;
import com.tool.tcpimg.UIUtils;
import org.come.until.Util;
import java.awt.Graphics2D;
import java.awt.Image;
import come.tool.Scene.Scene;
import com.tool.Stall.StallBean;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

public class ImageMixDeal
{
    public static ManimgAttribute userimg;
    public static List<ManimgAttribute> npcimglist;
    public static ConcurrentHashMap<String, ManimgAttribute> Playerimgmap;
    public static String username;
    public static List<ManimgAttribute> mapMonsterlist;
    public static List<ManimgAttribute> tasklist;
    public static List<StallBean> stalls;
    public static Scene scene;
    public static boolean ISSTALL;
    public static boolean isShadow;
    public static Image image;
    public static ConcurrentHashMap<String, List<ManimgAttribute>> petMap;
    public static boolean ismove;
    public static ManimgAttribute selectNpc;
    static String T;
    static StringBuffer buffer;
    
    public static void Drawing(Graphics2D g2, long DieTime) {
        try {
            move(DieTime);
            Util.mapmodel.drawmap(g2);
            if (ImageMixDeal.scene == null) {
                for (int i = ImageMixDeal.mapMonsterlist.size() - 1; i >= 0; --i) {
                    ((ManimgAttribute)ImageMixDeal.mapMonsterlist.get(i)).move2(DieTime);
                    ((ManimgAttribute)ImageMixDeal.mapMonsterlist.get(i)).Drawing(g2, DieTime);
                }
                if (ImageMixDeal.tasklist.size() != 0) {
                    for (int i = ImageMixDeal.tasklist.size() - 1; i >= 0; --i) {
                        if (((ManimgAttribute)ImageMixDeal.tasklist.get(i)).getTaskdata().getType() == 5) {
                            ((ManimgAttribute)ImageMixDeal.tasklist.get(i)).GS(ImageMixDeal.userimg.getmove());
                            ((ManimgAttribute)ImageMixDeal.tasklist.get(i)).Drawing(g2, DieTime);
                        }
                        else if (Util.ditubianma == ((ManimgAttribute)ImageMixDeal.tasklist.get(i)).getTaskdata().getMap()) {
                            ((ManimgAttribute)ImageMixDeal.tasklist.get(i)).Drawing(g2, DieTime);
                        }
                    }
                }
                for (ManimgAttribute value : ImageMixDeal.Playerimgmap.values()) {
                    value.Drawing(g2, DieTime);
                }
                for (int i = ImageMixDeal.npcimglist.size() - 1; i >= 0; --i) {
                    ((ManimgAttribute)ImageMixDeal.npcimglist.get(i)).Drawing(g2, DieTime);
                }
                for (List<ManimgAttribute> value2 : ImageMixDeal.petMap.values()) {
                    for (int j = 0; j < value2.size(); ++j) {
                        ((ManimgAttribute)value2.get(j)).Drawing(g2, DieTime);
                    }
                }
                if (ImageMixDeal.ISSTALL) {
                    g2.setFont(UIUtils.TEXT_FONT1);
                    for (int i = ImageMixDeal.stalls.size() - 1; i >= 0; --i) {
                        ((StallBean)ImageMixDeal.stalls.get(i)).draw(g2);
                    }
                }
                if (ImageMixDeal.userimg.getRoleShow().getFly_id() > 0) {
                    g2.drawImage(ImageMixDeal.image, 0, 0, null);
                }
                for (ManimgAttribute value : ImageMixDeal.Playerimgmap.values()) {
                    if (value.getRoleShow().getFly_id() > 0) {
                        value.Drawing(g2, DieTime);
                    }
                }
                ImageMixDeal.userimg.Drawing(g2, DieTime);
                ImageMixDeal.userimg.draweffects(g2);
                BangFight.getBangFight().draw(g2);
            }
            else {
                ImageMixDeal.scene.draw(g2, DieTime);
            }
        }
        catch (Exception ex) {}
    }
    
    public static void move(long DieTime) {
        for (ManimgAttribute value : ImageMixDeal.Playerimgmap.values()) {
            if (Util.ZOU) {
                value.movezou(DieTime);
            }
            else {
                value.move(DieTime);
            }
        }
        ImageMixDeal.petMap.forEach((k, v)/* java.lang.String,java.util.List, */ -> {
            ManimgAttribute manimgAttribute = huoquLogin(k);
            if (manimgAttribute != null) {
                manimgAttribute.movePet(v);
            }
            return;
        });
        if (ImageMixDeal.userimg != null) {
            if (Util.ZOU) {
                ImageMixDeal.userimg.movezou(DieTime);
            }
            else {
                ImageMixDeal.userimg.move(DieTime);
            }
            if (ImageMixDeal.userimg.getType() == 1) {
                ImageMixDeal.buffer.setLength(0);
                ImageMixDeal.buffer.append(ImageMixDeal.userimg.getRoleShow().getX() / 20);
                ImageMixDeal.buffer.append(ImageMixDeal.T);
                ImageMixDeal.buffer.append(ImageMixDeal.userimg.getRoleShow().getY() / 20);
                ZhuJpanel.getxAndY().setText(Util.mapName + "   [ " + ImageMixDeal.buffer.toString() + " ]");
                Util.mapmodel.ShotMove(ImageMixDeal.userimg.getRoleShow().getX(), ImageMixDeal.userimg.getRoleShow().getY());
                if (Util.CREEPSMAP && ImageMixDeal.userimg.getTeams() != null) {
                    Util.GotoCreeps();
                }
            }
            else {
                Util.mapmodel.ShotMiddlex(ImageMixDeal.userimg.getRoleShow().getX());
                Util.mapmodel.ShotMiddley(ImageMixDeal.userimg.getRoleShow().getY());
            }
        }
    }
    
    public static void move2(long DieTime) {
        for (int i = ImageMixDeal.mapMonsterlist.size() - 1; i >= 0; --i) {}
    }
    
    public static void NpcLoad(String npc) {
        ImageMixDeal.npcimglist.clear();
        if (npc != null) {
            List<String> strings = SplitStringTool.splitString(npc);
            for (int i = 0; i < strings.size(); ++i) {
                NpcInfoBean infoBean = UserMessUntil.getnpc((String)strings.get(i));
                if (infoBean == null)
                    continue;
                if (infoBean != null) {
                    if (infoBean.getNpctable().getNpctype().equals("2") || infoBean.getNpctable().getNpctype().equals("222") || infoBean.getNpctable().getNpctype().equals("2222")) {
                        List<Door> doors = new ArrayList<>();
                        if (infoBean.getNpctable().getNpcway() != null) {
                            List<String> strings2 = SplitStringTool.splitString(infoBean.getNpctable().getNpcway());
                            for (int j = 0; j < strings2.size(); ++j) {
                                doors.add(UserMessUntil.getDoor((String)strings2.get(j)));
                            }
                        }
                        infoBean.setDoors(doors);
                    }
                    ImageMixDeal.npcimglist.add(new ManimgAttribute(infoBean));

                    String Ntype = infoBean.getNpctable().getNpctype();
                    if (Ntype != null && !Ntype.equals("")) {
                        int type = Integer.parseInt(Ntype);
                        String type111 = Util.getNPCType(type);
                        if (type111 != null) {
                            npcimglist.add(new ManimgAttribute(infoBean, type111));
                        }
                    }
                }
            }
        }
    }
    
    public static ManimgAttribute huoquLogin(String name) {
        if (name.equals(ImageMixDeal.username)) {
            return ImageMixDeal.userimg;
        }
        return (ManimgAttribute)ImageMixDeal.Playerimgmap.get(name);
    }
    
    public static ManimgAttribute huoquLogin(BigDecimal id) {
        if (ImageMixDeal.userimg.getRoleShow().getRole_id().compareTo(id) == 0) {
            return ImageMixDeal.userimg;
        }
        try {
            for (ManimgAttribute value : ImageMixDeal.Playerimgmap.values()) {
                if (value.getRoleShow().getRole_id().compareTo(id) == 0) {
                    return value;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public static void colorName(int x, int y) {
        if (ImageMixDeal.scene != null && ImageMixDeal.scene.Monitor(x, y)) {
            return;
        }
        if (ImageMixDeal.userimg.getTeams() != null) {
            try {
                ManimgAttribute attribute = null;
                for (int i = 0; i < ImageMixDeal.npcimglist.size(); ++i) {
                    attribute = (ManimgAttribute)ImageMixDeal.npcimglist.get(i);
                    if (attribute.isContains(x, y)) {
                        attribute.setNameColor(UIUtils.COLOR_NPC_SELECT);
                        (ImageMixDeal.selectNpc = attribute).setisHigh(true);
                        return;
                    }
                    attribute.setDefaultNameColor();
                    attribute.setisHigh(false);
                    if (ImageMixDeal.selectNpc != null && ImageMixDeal.selectNpc.getNpc().getNpctable().getNpcid().equals(attribute.getNpc().getNpctable().getNpcid())) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                        ImageMixDeal.selectNpc = null;
                    }
                }
                for (int i = 0; i < ImageMixDeal.tasklist.size(); ++i) {
                    attribute = (ManimgAttribute)ImageMixDeal.tasklist.get(i);
                    if (attribute.isContains(x, y)) {
                        attribute.setNameColor(UIUtils.COLOR_NPC_SELECT);
                        attribute.setisHigh(true);
                        return;
                    }
                    attribute.setDefaultNameColor();
                    attribute.setisHigh(false);
                }
                for (int i = 0; i < ImageMixDeal.mapMonsterlist.size(); ++i) {
                    attribute = (ManimgAttribute)ImageMixDeal.mapMonsterlist.get(i);
                    if (attribute.isContains(x, y)) {
                        attribute.setNameColor(UIUtils.COLOR_NPC_SELECT);
                        attribute.setisHigh(true);
                        return;
                    }
                    attribute.setDefaultNameColor();
                    attribute.setisHigh(false);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        ManimgAttribute attribute = null;
        attribute = ImageMixDeal.userimg;
        if (ImageMixDeal.userimg.isContains(x, y)) {
            attribute.getPart().setGl(Boolean.valueOf(true));
            attribute.setNameColor(UIUtils.COLOR_NPC_SELECT);
            return;
        }
        attribute.getPart().setGl(Boolean.valueOf(false));
        attribute.setDefaultNameColor();
        try {
            for (ManimgAttribute value : ImageMixDeal.Playerimgmap.values()) {
                for (ManimgAttribute value2 : ImageMixDeal.Playerimgmap.values()) {
                    if (value2.isContains(x, y)) {
                        value2.getPart().setGl(Boolean.valueOf(true));
                        value2.setNameColor(UIUtils.COLOR_NPC_SELECT);
                        return;
                    }
                    value2.getPart().setGl(Boolean.valueOf(false));
                    value2.setDefaultNameColor();
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public static void MonitorTrigger(int x, int y) {
        if (ImageMixDeal.scene != null && ImageMixDeal.scene.Monitor(x, y)) {
            Util.dj = true;
            return;
        }
        if (ImageMixDeal.userimg.getTeams() != null) {
            try {
                ManimgAttribute attribute = null;
                if (ImageMixDeal.ISSTALL) {
                    int i = ImageMixDeal.stalls.size() - 1;
                    while (i >= 0) {
                        StallBean bean = (StallBean)ImageMixDeal.stalls.get(i);
                        if (bean.isDJ(x, y)) {
                            if (bean.getRoleid().compareTo(ImageMixDeal.userimg.getRoleShow().getRole_id()) == 0) {
                                UserStallUntil.startStall();
                            }
                            else {
                                UserStallUntil.showBuyStall(bean);
                            }
                            return;
                        }
                        else {
                            --i;
                        }
                    }
                }
                int i = 0;
                while (i < ImageMixDeal.npcimglist.size()) {
                    attribute = ImageMixDeal.npcimglist.get(i);
                    if (attribute.isContains(x, y)) {
                        NpcMonitor.npc(attribute);
                        Util.dj = true;
                        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
                            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                        }
                        return;
                    }
                    else {
                        ++i;
                    }
                }
                for (i = 0; i < ImageMixDeal.tasklist.size(); ++i) {
                    attribute = (ManimgAttribute)ImageMixDeal.tasklist.get(i);
                    if (attribute.isContains(x, y)) {
                        TaskMonitor.TaskCreeps(attribute.getTaskdata());
                        Util.dj = true;
                        return;
                    }
                }
                i = 0;
                while (i < ImageMixDeal.mapMonsterlist.size()) {
                    attribute = (ManimgAttribute)ImageMixDeal.mapMonsterlist.get(i);
                    if (attribute.isContains(x, y)) {
                        if (attribute.getMapMonsterBean() != null && attribute.getMapMonsterBean().getRobottitle() != null && !attribute.getMapMonsterBean().getRobottitle().equals("")) {
                            Util.dj = true;
                            CreepsMonitor.Creeps(attribute);
                            return;
                        }
                        Util.dj = true;
                        CreepsMonitor.Creeps(attribute);
                        return;
                    }
                    else {
                        ++i;
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (BangFight.getBangFight().Monitor(x, y)) {
                Util.dj = true;
                return;
            }
        }
        if (ImageMixDeal.userimg.isContains(x, y)) {
            PlayerMonitor.Player(ImageMixDeal.userimg);
            return;
        }
        try {
            for (ManimgAttribute value : ImageMixDeal.Playerimgmap.values()) {
                if (value.isContains(x, y)) {
                    PlayerMonitor.Player(value);
                    Util.dj = true;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Util.dj = false;
    }
    
    public static boolean toBufferedImage(Image image, int x, int y) {
        if (image == null) {
            return false;
        }
        if (x >= 0 && x < image.getWidth(null) && y >= 0 && y < image.getHeight(null)) {
            BufferedImage bimage = null;
            if (image instanceof BufferedImage) {
                bimage = (BufferedImage)image;
                return bimage.getRGB(x, y) != 0;
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    public static void addMonster(TaskProgress progress) {
        if (progress.getSum() >= progress.getMax()) {
            return;
        }
        if (progress.getType() == 1 || progress.getType() == 5) {
            ImageMixDeal.tasklist.add(new ManimgAttribute(progress));
        }
    }
    
    public static void removeMonster(TaskProgress progress) {
        if (progress.getType() == 1 || progress.getType() == 5) {
            for (int i = 0; i < ImageMixDeal.tasklist.size(); ++i) {
                if (((ManimgAttribute)ImageMixDeal.tasklist.get(i)).getName().equals(progress.getDName())) {
                    ImageMixDeal.tasklist.remove(i);
                    return;
                }
            }
        }
    }
    
    public static void upScene(String sceneMsg) {
        if (sceneMsg == null || sceneMsg.equals("")) {
            return;
        }
        String[] vs = sceneMsg.split("\\|");
        int sceneId = Integer.parseInt(vs[0]);
        if (vs.length == 1) {
            if (ImageMixDeal.scene != null && ImageMixDeal.scene.getSceneId() == sceneId) {
                ImageMixDeal.scene.end();
                ImageMixDeal.scene = null;
            }
        }
        else if (ImageMixDeal.scene != null) {
            if (ImageMixDeal.scene.getSceneId() == sceneId) {
                ImageMixDeal.scene.UPData(vs);
            }
            else {
                ImageMixDeal.scene.end();
                ImageMixDeal.scene = null;
                initScene(sceneId, vs);
            }
        }
        else {
            initScene(sceneId, vs);
        }
    }
    
    public static void initScene(int sceneId, String[] vs) {
        if (sceneId == 1011) {
            ImageMixDeal.scene = new DNTGScene(vs);
        }
    }
    
    public static void stall(StallBean bean) {
        if (bean.getState() == StallBean.OFF) {
            UserStallUntil.close(bean);
            ManimgAttribute attribute = huoquLogin(bean.getRole());
            if (attribute != null) {
                attribute.getRoleShow().setBooth_id(null);
            }
            for (int i = ImageMixDeal.stalls.size() - 1; i >= 0; --i) {
                if (((StallBean)ImageMixDeal.stalls.get(i)).getId() == bean.getId()) {
                    ImageMixDeal.stalls.remove(i);
                }
            }
        }
        else {
            ManimgAttribute attribute = huoquLogin(bean.getRole());
            if (attribute != null) {
                attribute.getRoleShow().setBooth_id(new BigDecimal(bean.getId()));
            }
            for (int i = ImageMixDeal.stalls.size() - 1; i >= 0; --i) {
                if (((StallBean)ImageMixDeal.stalls.get(i)).getId() == bean.getId()) {
                    ImageMixDeal.stalls.set(i, bean);
                    return;
                }
            }
            ImageMixDeal.stalls.add(bean);
        }
    }
    
    public static ManimgAttribute getNpc(String npc) {
        for (int i = 0; i < ImageMixDeal.npcimglist.size(); ++i) {
            ManimgAttribute attribute = (ManimgAttribute)ImageMixDeal.npcimglist.get(i);
            if (attribute.getNpc().getNpctable().getNpcid().equals(npc)) {
                return attribute;
            }
        }
        return null;
    }
    
    public static ManimgAttribute getTask(int id) {
        for (int i = 0; i < ImageMixDeal.tasklist.size(); ++i) {
            ManimgAttribute attribute = (ManimgAttribute)ImageMixDeal.tasklist.get(i);
            if (attribute.getTaskdata().getDId() == id) {
                return attribute;
            }
        }
        return null;
    }
    
    public static ManimgAttribute getMonster(int id) {
        for (int i = ImageMixDeal.mapMonsterlist.size() - 1; i >= 0; --i) {
            if (id == (int)((ManimgAttribute)ImageMixDeal.mapMonsterlist.get(i)).getMapMonsterBean().getI()) {
                return (ManimgAttribute)ImageMixDeal.mapMonsterlist.get(i);
            }
        }
        return null;
    }
    
    public static void removeMonster(int id) {
        for (int i = ImageMixDeal.mapMonsterlist.size() - 1; i >= 0; --i) {
            if (id == (int)((ManimgAttribute)ImageMixDeal.mapMonsterlist.get(i)).getMapMonsterBean().getI()) {
                ImageMixDeal.mapMonsterlist.remove(i);
                return;
            }
        }
    }
    
    static {
        ImageMixDeal.userimg = null;
        ImageMixDeal.npcimglist = new ArrayList<>();
        ImageMixDeal.Playerimgmap = new ConcurrentHashMap<>();
        ImageMixDeal.mapMonsterlist = new ArrayList<>();
        ImageMixDeal.tasklist = new ArrayList<>();
        ImageMixDeal.stalls = new ArrayList<>();
        ImageMixDeal.ISSTALL = true;
        ImageMixDeal.isShadow = true;
        ImageMixDeal.image = CutButtonImage.getImage("inkImg/background/flb.png", 1366, 768).getImage();
        ImageMixDeal.petMap = new ConcurrentHashMap<>();
        ImageMixDeal.ismove = true;
        ImageMixDeal.selectNpc = null;
        ImageMixDeal.T = ",";
        ImageMixDeal.buffer = new StringBuffer();
    }
}
