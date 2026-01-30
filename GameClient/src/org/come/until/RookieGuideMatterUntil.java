package org.come.until;

import java.util.Iterator;
import org.come.bean.RookieGuideBean;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

public class RookieGuideMatterUntil
{
    public static String[] guides;
    
    public static void showGuideMethod(DefaultMutableTreeNode top) {
        for (int i = 0; i < RookieGuideMatterUntil.guides.length; ++i) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(RookieGuideMatterUntil.guides[i]);
            if (returnFid(RookieGuideMatterUntil.guides[i]) != null) {
                List<String> listChild = returnChild((int)returnFid(RookieGuideMatterUntil.guides[i]));
                if (listChild != null && listChild.size() > 0) {
                    for (int j = 0; j < listChild.size(); ++j) {
                        node.add(new DefaultMutableTreeNode(listChild.get(j)));
                    }
                }
            }
            top.add(node);
        }
    }
    
    public static List<String> returnChild(int fid) {
        if (UserMessUntil.getAllGuide().getRookieguide() == null) {
            return null;
        }
        List<String> listChild = new ArrayList<>();
        for (Map.Entry<Integer, RookieGuideBean> entry : UserMessUntil.getAllGuide().getRookieguide().entrySet()) {
            if (((RookieGuideBean)entry.getValue()).getFid() == fid) {
                listChild.add(((RookieGuideBean)entry.getValue()).getGuidename());
            }
        }
        if (fid == 5) {
            List<String> listChild2 = new ArrayList<>();
            for (int i = 0; i < listChild.size(); ++i) {
                listChild2.add(listChild.get(i));
            }
            for (int i = 0; i < listChild.size(); ++i) {
                if (((String)listChild.get(i)).equals("周一活动")) {
                    listChild2.set(0, listChild.get(i));
                }
                else if (((String)listChild.get(i)).equals("周二活动")) {
                    listChild2.set(1, listChild.get(i));
                }
                else if (((String)listChild.get(i)).equals("周三活动")) {
                    listChild2.set(2, listChild.get(i));
                }
                else if (((String)listChild.get(i)).equals("周四活动")) {
                    listChild2.set(3, listChild.get(i));
                }
                else if (((String)listChild.get(i)).equals("周五活动")) {
                    listChild2.set(4, listChild.get(i));
                }
                else if (((String)listChild.get(i)).equals("周六活动")) {
                    listChild2.set(5, listChild.get(i));
                }
                else if (((String)listChild.get(i)).equals("周日活动")) {
                    listChild2.set(6, listChild.get(i));
                }
                else if (((String)listChild.get(i)).equals("地煞星分布")) {
                    listChild2.set(7, listChild.get(i));
                }
                else if (((String)listChild.get(i)).equals("进阶挑战")) {
                    listChild2.set(8, listChild.get(i));
                }
            }
            listChild.clear();
            listChild = listChild2;
        }
        return listChild;
    }
    
    public static String returnChildMsg(String name) {
        String msg = "";
        for (Map.Entry<Integer, RookieGuideBean> entry : UserMessUntil.getAllGuide().getRookieguide().entrySet()) {
            if (((RookieGuideBean)entry.getValue()).getGuidename().equals(name)) {
                msg = ((RookieGuideBean)entry.getValue()).getBootcontent();
            }
        }
        return msg.equals("") ? null : msg;
    }
    
    public static String returnContent(String name) {
        String content = "";
        if (name.equals("功能介绍")) {
            content = "";
        }
        else if (name.equals("版本介绍")) {
            content = "";
        }
        else if (name.equals("升级攻略")) {
            content = "";
        }
        else if (name.equals("装备介绍")) {
            content = "";
        }
        else if (name.equals("活动玩法")) {
            content = "";
        }
        else if (name.equals("挑战玩法")) {
            content = "";
        }
        else if (name.equals("竞技玩法")) {
            content = "";
        }
        else if (name.equals("版本特色")) {
            content = "";
        }
        else if (name.equals("孩子系统")) {
            content = "";
        }
        else if (name.equals("套装系统")) {
            content = "";
        }
        return content.equals("") ? null : content;
    }
    
    public static Integer returnFid(String name) {
        int fid = 0;
        if (name.equals("功能介绍")) {
            fid = 1;
        }
        else if (name.equals("版本介绍")) {
            fid = 2;
        }
        else if (name.equals("升级攻略")) {
            fid = 3;
        }
        else if (name.equals("装备介绍")) {
            fid = 4;
        }
        else if (name.equals("活动玩法")) {
            fid = 5;
        }
        else if (name.equals("挑战玩法")) {
            fid = 6;
        }
        else if (name.equals("竞技玩法")) {
            fid = 7;
        }
        else if (name.equals("孩子系统")) {
            fid = 8;
        }
        else if (name.equals("套装系统")) {
            fid = 9;
        }
        else if (name.equals("灵宝系统")) {
            fid = 10;
        }
        else if (name.equals("召唤兽系统")) {
            fid = 11;
        }
        return (fid == 0) ? null : Integer.valueOf(fid);
    }
    
    static {
        RookieGuideMatterUntil.guides = new String[] { "功能介绍", "版本介绍", "升级攻略", "装备介绍", "活动玩法", "挑战玩法", "竞技玩法", "孩子系统", "套装系统", "灵宝系统", "召唤兽系统" };
    }
}
