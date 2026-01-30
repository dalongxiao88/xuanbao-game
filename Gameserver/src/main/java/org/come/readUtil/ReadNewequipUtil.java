package org.come.readUtil;

import org.come.model.GodStone;
import org.come.model.Decorate;
import org.come.model.Alchemy;
import java.util.ArrayList;
import java.util.List;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.model.Newequip;
import org.come.tool.ReadExelTool;
import java.util.concurrent.ConcurrentHashMap;
import org.come.model.NewequipMapObject;

public class ReadNewequipUtil
{
    public static NewequipMapObject getAllNewequip(String path, StringBuffer buffer) {
        NewequipMapObject newequipMapObject = new NewequipMapObject();
        ConcurrentHashMap<String, List<Newequip>> sameNewequipMap = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, List<Newequip>> witchNewequipMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Newequip newequip = new Newequip();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(newequip, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (!newequip.getEquipid().equals("")) {
                    List<Newequip> equiptwoList = (List<Newequip>)witchNewequipMap.get(newequip.getEquipkey() + newequip.getEquiprace());
                    if (equiptwoList == null) {
                        equiptwoList = new ArrayList<>();
                        witchNewequipMap.put(newequip.getEquipkey() + newequip.getEquiprace(), equiptwoList);
                    }
                    equiptwoList.add(newequip);
                    List<Newequip> equipList = (List<Newequip>)sameNewequipMap.get(newequip.getEquipkey());
                    if (equipList == null) {
                        equipList = new ArrayList<>();
                        sameNewequipMap.put(newequip.getEquipkey(), equipList);
                    }
                    equipList.add(newequip);
                }
            }
        }
        newequipMapObject.setSameNewequipMap(sameNewequipMap);
        newequipMapObject.setWitchNewequipMap(witchNewequipMap);
        return newequipMapObject;
    }
    
    public static ConcurrentHashMap<String, List<Alchemy>> getAllAlchemy(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, List<Alchemy>> allAlchemy = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Alchemy alchemy = new Alchemy();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(alchemy, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (!alchemy.getAlchemyid().equals("")) {
                    List<Alchemy> equipList = (List<Alchemy>)allAlchemy.get(alchemy.getAlchemytype());
                    if (equipList == null) {
                        equipList = new ArrayList<>();
                        allAlchemy.put(alchemy.getAlchemytype(), equipList);
                    }
                    equipList.add(alchemy);
                }
            }
        }
        return allAlchemy;
    }
    
    public static ConcurrentHashMap<String, List<Decorate>> getAllDecorate(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, List<Decorate>> allDecorate = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Decorate decorate = new Decorate();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(decorate, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (!decorate.getDecotateid().equals("")) {
                    List<Decorate> equipList = (List<Decorate>)allDecorate.get(decorate.getDecotatesn());
                    if (equipList == null) {
                        equipList = new ArrayList<>();
                        allDecorate.put(decorate.getDecotatesn(), equipList);
                    }
                    equipList.add(decorate);
                }
            }
        }
        return allDecorate;
    }
    
    public static ConcurrentHashMap<String, List<GodStone>> selectGodStones(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, List<GodStone>> godMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                GodStone godStone = new GodStone();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(godStone, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    String[] v = godStone.getNames().split("\\|");
                    for (int k = 0; k < v.length; ++k) {
                        List<GodStone> stones = (List<GodStone>)godMap.get(v[k]);
                        if (stones == null) {
                            stones = new ArrayList<>();
                            godMap.put(v[k], stones);
                        }
                        stones.add(godStone);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return godMap;
    }
}
