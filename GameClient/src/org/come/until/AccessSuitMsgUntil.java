package org.come.until;

import java.math.BigDecimal;
import org.come.bean.JadeorGoodstableBean;
import org.come.Jpanel.GoodsMsgJpanel;
import org.come.Jpanel.StorageJadeJpanel2;
import org.come.Jpanel.StorageJadeJpanel;
import org.come.Jpanel.StorageJadeJpanel4;
import org.come.Jpanel.StorageJadeJpanel3;
import org.come.entity.PartJade;
import org.come.bean.IncludedPart;
import java.util.ArrayList;
import org.come.entity.Goodstable;
import java.util.List;
import java.util.Iterator;
import com.tool.role.RoleData;
import org.come.bean.RoleSuitBean;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;

public class AccessSuitMsgUntil
{
    public static int exvalue;
    
    public static void showSuitMethod(DefaultMutableTreeNode top, boolean bool) {
        top.removeAllChildren();
        if (UserMessUntil.getAllSuit().getRolesuit() != null) {
            for (Map.Entry<Integer, RoleSuitBean> entry : UserMessUntil.getAllSuit().getRolesuit().entrySet()) {
                boolean bool2 = true;
                int suitid = (int)returnSuitID(((RoleSuitBean)entry.getValue()).getSname());
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(((RoleSuitBean)entry.getValue()).getSname());
                List<String> partlist = returnPartsList(((RoleSuitBean)entry.getValue()).getHaveParts());
                if (partlist != null && partlist.size() > 0) {
                    for (int i = 0; i < partlist.size(); ++i) {
                        node.add(new DefaultMutableTreeNode(returnPartsName((String)partlist.get(i))));
                        int partId = Integer.parseInt((String)partlist.get(i));
                        if (bool2) {
                            bool2 = RoleData.getRoleData().getPackRecord().getPartJade(suitid, partId).isJade();
                        }
                    }
                }
                if (!bool) {
                    if (!bool2) {
                        top.add(node);
                    }
                    else {
                        continue;
                    }
                }
                else {
                    top.add(node);
                }
            }
        }
    }
    
    public static List<Goodstable> accessIdlEqu(int type) {
        List<Goodstable> listEqui = new ArrayList<>();
        for (int i = 0; i < GoodsListFromServerUntil.getGoodslist().length; ++i) {
            if (GoodsListFromServerUntil.getGoodslist()[i] != null) {
                long goodid = GoodsListFromServerUntil.getGoodslist()[i].getGoodsid().longValue();
                long goodtype = (long)GoodsListFromServerUntil.getGoodslist()[i].getType();
                if (Goodtype.EquipmentType(goodtype) != -1 && Goodtype.EquipmentType(goodtype) != 0 && Goodtype.EquipmentType(goodtype) != 4 && Goodtype.EquipmentType(goodtype) != 5 && goodid != 6029L && goodid != 6030L && goodid != 6031L && goodid != 6032L && goodid != 6033L && goodid != 6034L) {
                    if (type == 2) {
                        if (GoodsListFromServerUntil.getGoodslist()[i].getValue().indexOf("套装属性") != -1) {
                            listEqui.add(GoodsListFromServerUntil.getGoodslist()[i]);
                        }
                    }
                    else if (type == 1 && GoodsListFromServerUntil.getGoodslist()[i].getValue().indexOf("套装属性") == -1) {
                        listEqui.add(GoodsListFromServerUntil.getGoodslist()[i]);
                    }
                }
            }
        }
        return listEqui;
    }
    
    public static int getCollNum(int suitid) {
        int num = 0;
        List<IncludedPart> parts = getIncludedMsg(suitid);
        if (parts == null) {
            return 0;
        }
        for (int i = 0; i < parts.size(); ++i) {
            if (((IncludedPart)parts.get(i)).getNumber() > 0) {
                ++num;
            }
        }
        return num;
    }
    
    public static List<IncludedPart> getIncludedMsg(int suitid) {
        List<IncludedPart> listMsg = new ArrayList<>();
        List<String> parts = returnPartsList(((RoleSuitBean)UserMessUntil.getAllSuit().getRolesuit().get(Integer.valueOf(suitid))).getHaveParts());
        String[] v = RoleData.getRoleData().getPackRecord().getCollect(suitid);
        for (int i = 0; i < parts.size(); ++i) {
            IncludedPart part = new IncludedPart();
            part.setPartid((String)parts.get(i));
            if (v == null) {
                part.setNumber(0);
            }
            else {
                boolean bool = false;
                for (int j = 0; j < v.length; ++j) {
                    if (v[j].equals(parts.get(i))) {
                        bool = true;
                    }
                }
                if (bool) {
                    part.setNumber(1);
                }
                else {
                    part.setNumber(0);
                }
            }
            listMsg.add(part);
        }
        return listMsg;
    }
    
    public static boolean isActivate(List<IncludedPart> parts, int index) {
        boolean bool = true;
        for (int i = 0; i < parts.size(); ++i) {
            if (((IncludedPart)parts.get(i)).getNumber() == 0 && i != index) {
                bool = false;
            }
        }
        return bool;
    }
    
    public static void refreshJadeShow(PartJade jade) {
        if (StorageJadeJpanel3.partJade != null && StorageJadeJpanel3.partJade.getSuitid() == jade.getSuitid() && StorageJadeJpanel3.partJade.getPartId() == jade.getPartId()) {
            StorageJadeJpanel3.partJade = jade;
        }
        if (StorageJadeJpanel4.partJade != null && StorageJadeJpanel4.partJade.getSuitid() == jade.getSuitid() && StorageJadeJpanel4.partJade.getPartId() == jade.getPartId()) {
            StorageJadeJpanel4.partJade = jade;
        }
        if (StorageJadeJpanel.partJade != null && StorageJadeJpanel.partJade.getSuitid() == jade.getSuitid() && StorageJadeJpanel.partJade.getPartId() == jade.getPartId()) {
            StorageJadeJpanel.partJade = jade;
        }
        if (StorageJadeJpanel2.partJade != null && StorageJadeJpanel2.partJade.getSuitid() == jade.getSuitid() && StorageJadeJpanel2.partJade.getPartId() == jade.getPartId()) {
            StorageJadeJpanel2.partJade = jade;
        }
    }
    
    public static List<String> getSuitAttr(String attr) {
        if (attr == null) {
            return null;
        }
        List<String> attribute = new ArrayList<>();
        String[] attrs = attr.split("\\&");
        for (int i = 4; i < attrs.length; ++i) {
            String[] ss = attrs[i].split("\\=");
            attribute.add(ss[0]);
            attribute.add(ss[1] + GoodsMsgJpanel.tianjia(ss[0]));
        }
        return attribute;
    }
    
    public static String getExtra(String value, String extra) {
        String[] v = value.split("\\|");
        if (extra.equals("点粹属性")) {
            String mes = null;
            int i = 0;
            while (i < v.length) {
                if (v[i].length() >= 4 && v[i].substring(0, 4).equals(extra)) {
                    if (v[i + 1] != null && v[i + 1].startsWith("炼化属性&特技")) {
                        return v[i] + "|" + v[i + 1];
                    }
                    return v[i];
                }
                else {
                    ++i;
                }
            }
        }
        else {
            for (int j = 0; j < v.length; ++j) {
                if (v[j].length() >= 4 && v[j].substring(0, 4).equals(extra)) {
                    return v[j];
                }
            }
        }
        return null;
    }
    
    public static String returnnewEx(int type, String value) {
        String[] attrs = value.split("\\&");
        String newEx = "";
        String pz = attrs[3];
        if (type == 0) {
            newEx = pz;
        }
        else if (type == 1) {
            int index = returnJadeType(pz) + 1;
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < attrs.length; ++i) {
                if (i != 0) {
                    buffer.append("&");
                }
                if (i != 3) {
                    buffer.append(attrs[i]);
                }
                else {
                    buffer.append(returnJadeName(index));
                }
            }
            newEx = buffer.toString();
        }
        else if (type == 3) {
            int index = returnJadeType(pz) + 1;
            newEx = returnJadeName(index);
        }
        return newEx;
    }
    
    public static int getSxlxz(String value) {
        int sxlxz = 0;
        String key = returnnewEx(0, getExtra(value, "套装属性"));
        switch (key.hashCode()) {
            case 647926: {
                if (key.equals("传世")) {
                    sxlxz = 160;
                    break;
                }
                else {
                    break;
                }
            }
            case 811615: {
                if (key.equals("把玩")) {
                    sxlxz = 10;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (key.equals("无价")) {
                    sxlxz = 80;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (key.equals("珍藏")) {
                    sxlxz = 40;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (key.equals("贴身")) {
                    sxlxz = 20;
                    break;
                }
                else {
                    break;
                }
            }
        }
        return sxlxz;
    }
    
    public static BigDecimal returnMoney(JadeorGoodstableBean goodstableBean, int type) {
        if (goodstableBean.getGoodstable() != null && goodstableBean.getPartJade() != null && goodstableBean.getType() != 0) {
            BigDecimal big = new BigDecimal(0);
            if (type == 1) {
                big = new BigDecimal(100000);
            }
            else if (type == 2) {
                big = new BigDecimal(10000000);
            }
            return big;
        }
        else {
            return null;
        }
    }
    
    public static List<String> returnPartsList(String parts) {
        List<String> partlist = new ArrayList<>();
        if (parts != null && !parts.equals("")) {
            String[] v = parts.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                partlist.add(v[i]);
            }
        }
        return partlist;
    }
    
    public static Integer returnSuitID(String sname) {
        int id = -1;
        if (UserMessUntil.getAllSuit().getRolesuit() == null) {
            return null;
        }
        for (Map.Entry<Integer, RoleSuitBean> entry : UserMessUntil.getAllSuit().getRolesuit().entrySet()) {
            if (((RoleSuitBean)entry.getValue()).getSname().equals(sname)) {
                id = (int)entry.getKey();
                break;
            }
        }
        return (id == -1) ? null : Integer.valueOf(id);
    }
    
    public static String returnPartsName(String id) {
        String pname = null;
        if (id.equals("1")) {
            pname = "帽子";
        }
        else if (id.equals("2")) {
            pname = "项链";
        }
        else if (id.equals("3")) {
            pname = "衣服";
        }
        else if (id.equals("6")) {
            pname = "面具";
        }
        else if (id.equals("7")) {
            pname = "腰带";
        }
        else if (id.equals("8")) {
            pname = "披风";
        }
        else if (id.equals("9")) {
            pname = "挂件";
        }
        else if (id.equals("10")) {
            pname = "左戒指";
        }
        else if (id.equals("11")) {
            pname = "右戒指";
        }
        return pname;
    }
    
    public static Integer returnPartsID(String pname) {
        int id = -1;
        if (pname.equals("帽子")) {
            id = 1;
        }
        else if (pname.equals("项链")) {
            id = 2;
        }
        else if (pname.equals("衣服")) {
            id = 3;
        }
        else if (pname.equals("面具")) {
            id = 6;
        }
        else if (pname.equals("腰带")) {
            id = 7;
        }
        else if (pname.equals("披风")) {
            id = 8;
        }
        else if (pname.equals("挂件")) {
            id = 9;
        }
        else if (pname.equals("左戒指")) {
            id = 10;
        }
        else if (pname.equals("右戒指")) {
            id = 11;
        }
        return (id == -1) ? null : Integer.valueOf(id);
    }
    
    public static String returnJadeName(int type) {
        String name = "把玩";
        switch (type) {
            case 1: {
                name = "把玩";
                break;
            }
            case 2: {
                name = "贴身";
                break;
            }
            case 3: {
                name = "珍藏";
                break;
            }
            case 4: {
                name = "无价";
                break;
            }
            case 5: {
                name = "传世";
                break;
            }
        }
        return name;
    }
    
    public static int returnJadeType(String name) {
        int index = 0;
        switch (name.hashCode()) {
            case 647926: {
                if (name.equals("传世")) {
                    index = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 811615: {
                if (name.equals("把玩")) {
                    index = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (name.equals("无价")) {
                    index = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (name.equals("珍藏")) {
                    index = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (name.equals("贴身")) {
                    index = 2;
                    break;
                }
                else {
                    break;
                }
            }
        }
        return index;
    }
    
    public static int returnJadeNum(int type) {
        int num = 0;
        switch (type) {
            case 1: {
                num = 5;
                break;
            }
            case 2: {
                num = 4;
                break;
            }
            case 3: {
                num = 3;
                break;
            }
            case 4: {
                num = 3;
                break;
            }
        }
        return num;
    }
    
    public static BigDecimal returnJadeMoney(int type) {
        BigDecimal num = null;
        switch (type) {
            case 1: {
                num = new BigDecimal(5000000);
                break;
            }
            case 2: {
                num = new BigDecimal(5000000);
                break;
            }
            case 3: {
                num = new BigDecimal(5000000);
                break;
            }
            case 4: {
                num = new BigDecimal(5000000);
                break;
            }
        }
        return num;
    }
    
    public static int returnExcNum(int type) {
        int num = 0;
        switch (type) {
            case 1: {
                num = 1;
                break;
            }
            case 2: {
                num = 1;
                break;
            }
            case 3: {
                num = 2;
                break;
            }
            case 4: {
                num = 2;
                break;
            }
            case 5: {
                num = 3;
                break;
            }
        }
        return num;
    }
    
    public static String getPalEquipAgree(String value, String type) {
        String[] split = value.split("\\|");
        for (int i = 0; i < split.length; ++i) {
            if (split[i].startsWith(type)) {
                return split[i];
            }
        }
        return null;
    }
    
    static {
        AccessSuitMsgUntil.exvalue = 3;
    }
}
