package org.come.until;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.come.bean.LoginResult;
import org.come.bean.RoleInitBean;
import org.come.bean.NpcSureMenuBean;
import org.come.bean.NpcMenuBean;

public class ControlNpcXmlUntil
{
    private static NpcMenuBean npcmenubean;
    private static String type;
    private static NpcSureMenuBean sureBean;
    private static String noteName1;
    private static String speciesId;
    private static boolean yesOrNo;
    private static RoleInitBean roleInitBean;
    private static LoginResult loginResult;
    
    public ControlNpcXmlUntil() throws Exception {
        GetXmlPath(null);
    }
    
    public static void GetXmlPath(String path) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(Util.class.getClassLoader().getResourceAsStream(path));
            Element root = document.getRootElement();
            listNodes(root, path);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void listNodes(Element node, String path) {
        List<Attribute> list = (List)node.attributes();
        for (Attribute attribute : list) {
            if (path.equals("roleState.xml")) {
                GetRoleInitXml(attribute.getName(), attribute.getValue());
            }
            else if (path.equals("npcMenu.xml")) {
                NpcBean(attribute.getName(), attribute.getValue());
            }
            else {
                continue;
            }
        }
        Iterator<Element> iterator = (Iterator)node.elementIterator();
        while (iterator.hasNext()) {
            Element e = (Element)iterator.next();
            listNodes(e, path);
        }
    }
    
    public static void NpcBean(String note, String noteName) {
        if (note.equals("type") && noteName.equals(ControlNpcXmlUntil.type)) {
            ControlNpcXmlUntil.yesOrNo = true;
        }
        else if (note.equals("type") && !noteName.equals(ControlNpcXmlUntil.type)) {
            ControlNpcXmlUntil.yesOrNo = false;
        }
        if (ControlNpcXmlUntil.yesOrNo) {
            int n = -1;
            switch (note.hashCode()) {
                case 3575610: {
                    if (note.equals("type")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case -1854746644: {
                    if (note.equals("RaceId")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1235559123: {
                    if (note.equals("sureinittitle")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 950673334: {
                    if (note.equals("sureinitActionId")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case -238590136: {
                    if (note.equals("inittitle")) {
                        n = 4;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 610700065: {
                    if (note.equals("initActionId")) {
                        n = 5;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    ControlNpcXmlUntil.type = noteName;
                    ControlNpcXmlUntil.npcmenubean.setType(ControlNpcXmlUntil.type);
                    break;
                }
                case 1: {
                    ControlNpcXmlUntil.sureBean.setConditions(noteName);
                    ControlNpcXmlUntil.npcmenubean.setSureBean(ControlNpcXmlUntil.sureBean);
                    break;
                }
                case 2: {
                    ControlNpcXmlUntil.noteName1 = noteName;
                    break;
                }
                case 3: {
                    ControlNpcXmlUntil.sureBean.getMenuAndMethod().put(ControlNpcXmlUntil.noteName1, noteName);
                    break;
                }
                case 4: {
                    ControlNpcXmlUntil.noteName1 = noteName;
                    break;
                }
                case 5: {
                    ControlNpcXmlUntil.npcmenubean.getInittitle().put(ControlNpcXmlUntil.noteName1, noteName);
                    break;
                }
            }
        }
    }
    
    public static void GetRoleInitXml(String note, String noteName) {
        if (note.equals("sepciesId") && ControlNpcXmlUntil.speciesId.equals(noteName)) {
            ControlNpcXmlUntil.yesOrNo = true;
            ControlNpcXmlUntil.roleInitBean = new RoleInitBean();
        }
        else if (note.equals("sepciesId") && !ControlNpcXmlUntil.speciesId.equals(noteName)) {
            ControlNpcXmlUntil.yesOrNo = false;
        }
        if (ControlNpcXmlUntil.yesOrNo) {
            int n = -1;
            switch (note.hashCode()) {
                case 1781893549: {
                    if (note.equals("sepciesId")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case -938613812: {
                    if (note.equals("raceId")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case -64591620: {
                    if (note.equals("raceName")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 113766: {
                    if (note.equals("sex")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case -1205395306: {
                    if (note.equals("localName")) {
                        n = 4;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    ControlNpcXmlUntil.loginResult.setSpecies_id(new BigDecimal(noteName));
                    break;
                }
                case 1: {
                    ControlNpcXmlUntil.loginResult.setRace_id(new BigDecimal(noteName));
                    break;
                }
                case 2: {
                    ControlNpcXmlUntil.loginResult.setRace_name(noteName);
                    break;
                }
                case 3: {
                    ControlNpcXmlUntil.loginResult.setSex(noteName);
                    break;
                }
                case 4: {
                    ControlNpcXmlUntil.loginResult.setLocalname(noteName);
                    break;
                }
            }
        }
    }
    
    public static NpcMenuBean getNpcmenubean() {
        return ControlNpcXmlUntil.npcmenubean;
    }
    
    public static void setNpcmenubean(NpcMenuBean npcmenubean) {
        ControlNpcXmlUntil.npcmenubean = npcmenubean;
    }
    
    public static String getType() {
        return ControlNpcXmlUntil.type;
    }
    
    public static void setType(String type) {
        ControlNpcXmlUntil.type = type;
    }
    
    public static NpcSureMenuBean getSureBean() {
        return ControlNpcXmlUntil.sureBean;
    }
    
    public static void setSureBean(NpcSureMenuBean sureBean) {
        ControlNpcXmlUntil.sureBean = sureBean;
    }
    
    public static String getNoteName1() {
        return ControlNpcXmlUntil.noteName1;
    }
    
    public static void setNoteName1(String noteName1) {
        ControlNpcXmlUntil.noteName1 = noteName1;
    }
    
    public static String getSpeciesId() {
        return ControlNpcXmlUntil.speciesId;
    }
    
    public static void setSpeciesId(String speciesId) {
        ControlNpcXmlUntil.speciesId = speciesId;
    }
    
    public static boolean isYesOrNo() {
        return ControlNpcXmlUntil.yesOrNo;
    }
    
    public static void setYesOrNo(boolean yesOrNo) {
        ControlNpcXmlUntil.yesOrNo = yesOrNo;
    }
    
    public static RoleInitBean getRoleInitBean() {
        return ControlNpcXmlUntil.roleInitBean;
    }
    
    public static void setRoleInitBean(RoleInitBean roleInitBean) {
        ControlNpcXmlUntil.roleInitBean = roleInitBean;
    }
    
    public static LoginResult getLoginResult() {
        return ControlNpcXmlUntil.loginResult;
    }
    
    public static void setLoginResult(LoginResult loginResult) {
        ControlNpcXmlUntil.loginResult = loginResult;
    }
    
    static {
        ControlNpcXmlUntil.sureBean = new NpcSureMenuBean();
        ControlNpcXmlUntil.yesOrNo = false;
        ControlNpcXmlUntil.loginResult = new LoginResult();
    }
}
