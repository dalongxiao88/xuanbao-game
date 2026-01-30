package org.come.readUtil;

import java.util.ArrayList;
import java.util.List;
import org.come.until.GsonUtil;
import org.come.readBean.allPetExchange;
import org.come.server.GameServer;
import org.come.model.PetExchange;
import come.tool.Calculation.PalEquipQl;
import org.come.model.PalEquip;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.entity.RoleSummoning;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class ReadPetUtil
{
    public static ConcurrentHashMap<BigDecimal, RoleSummoning> allPetId(String path, StringBuffer buffer) {
        ConcurrentHashMap<BigDecimal, RoleSummoning> allpet = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                RoleSummoning pet = new RoleSummoning();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(pet, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (!pet.getSummoningid().equals("")) {
                    pet.SB();
                    allpet.put(new BigDecimal(pet.getSummoningid()), pet);
                }
            }
        }
        return allpet;
    }
    
    public static ConcurrentHashMap<Long, PalEquip> allPalEquip(String path, StringBuffer buffer) {
        ConcurrentHashMap<Long, PalEquip> map = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                PalEquip palEquip = new PalEquip();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(palEquip, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (palEquip.getId() != 0) {
                    try {
                        String[] vs = palEquip.getValues().split(" ");
                        PalEquipQl[] qls = new PalEquipQl[vs.length];
                        for (int k = 0; k < qls.length; ++k) {
                            qls[k] = new PalEquipQl(vs[k]);
                        }
                        palEquip.setQls(qls);
                        vs = palEquip.getQh().split("\\|");
                        int[] qhs = new int[vs.length];
                        for (int l = 0; l < qhs.length; ++l) {
                            qhs[l] = Integer.parseInt(vs[l]);
                        }
                        palEquip.setQhs(qhs);
                        palEquip.setLimits(palEquip.getLimit().split(" "));
                        palEquip.setNames(palEquip.getName().split("\\|"));
                        palEquip.setSkins(palEquip.getSkin().split("\\|"));
                        palEquip.setIns(palEquip.getIn().split("\\|"));
                        palEquip.setUpLvl(qhs.length);
                        map.put(Long.valueOf(palEquip.getType()), palEquip);
                    }
                    catch (Exception e2) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                        return null;
                    }
                }
            }
        }
        return map;
    }
    
    public static ConcurrentHashMap<Integer, PetExchange> allPetExchangeMap(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, PetExchange> allPetExchange = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                PetExchange petExchange = new PetExchange();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(petExchange, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (petExchange.geteId() != 0) {
                    String[] ids = petExchange.getPid().split("\\|");
                    if (petExchange.getType() != null && petExchange.getType().equals("召唤兽分解")) {
                        allPetExchange.put(Integer.valueOf(petExchange.geteId()), petExchange);
                    }
                    else if (petExchange.getType() != null && !petExchange.getType().equals("召唤兽分解")) {
                        if (ids.length > 1) {
                            RoleSummoning pet = GameServer.getPet(new BigDecimal(ids[0]));
                            if (pet == null) {
                                buffer.append("未找到对应的PETID:" + petExchange.getPid());
                            }
                            else {
                                petExchange.initPetRadom(pet, petExchange);
                                allPetExchange.put(Integer.valueOf(petExchange.geteId()), petExchange);
                            }
                        }
                        else if (ids.length == 1) {
                            RoleSummoning pet = GameServer.getPet(new BigDecimal(petExchange.getPid()));
                            if (pet == null) {
                                buffer.append("未找到对应的PETID:" + petExchange.getPid());
                                return null;
                            }
                            petExchange.initPet(pet);
                            allPetExchange.put(Integer.valueOf(petExchange.geteId()), petExchange);
                        }
                    }
                }
            }
        }
        return allPetExchange;
    }
    
    public static String createTxtPetExchange(ConcurrentHashMap<Integer, PetExchange> map) {
        allPetExchange allPetExchange = new allPetExchange();
        allPetExchange.setAllPetExchange(map);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allPetExchange);
        return msgString;
    }
    
    public static List<RoleSummoning> selectRoleSummonings(String path, StringBuffer buffer) {
        List<RoleSummoning> RoleSummonings = new ArrayList<>();
        String[][] result = ReadExelTool.getResultRelative(path);
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                RoleSummoning pet = new RoleSummoning();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(pet, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                RoleSummonings.add(pet);
            }
        }
        return RoleSummonings;
    }
}
