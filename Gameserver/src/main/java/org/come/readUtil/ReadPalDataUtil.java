package org.come.readUtil;

import come.tool.Calculation.PalEquipQl;
import org.come.model.PalEquip;
import come.tool.Calculation.PalQl;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.PalData;
import java.util.concurrent.ConcurrentHashMap;

public class ReadPalDataUtil
{
    public static ConcurrentHashMap<Integer, PalData> selectPalData(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, PalData> map = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                PalData palData = new PalData();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(palData, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (palData.getPalId() != 0) {
                    try {
                        if (!palData.getKx().equals("")) {
                            String[] vs = palData.getKx().split("\\|");
                            PalQl[] qls = new PalQl[vs.length];
                            for (int k = 0; k < qls.length; ++k) {
                                String[] v = vs[k].split("=");
                                qls[k] = new PalQl(v[0], Double.parseDouble(v[1]), (v.length == 3) ? Double.parseDouble(v[2]) : 0.0);
                            }
                            palData.setQls(qls);
                        }
                        else {
                            palData.setKx(null);
                        }
                        if (!palData.getSkill().equals("")) {
                            palData.setSkills(palData.getSkill().split("\\|"));
                        }
                        else {
                            palData.setSkill(null);
                        }
                        if (palData.getJd().equals("")) {
                            palData.setJd("H=1|M=1|A=1|S=1");
                        }
                        String[] vs = palData.getJd().split("\\|");
                        int[] jds = new int[4];
                        for (int k = 0; k < vs.length; ++k) {
                            if (vs[k].startsWith("H")) {
                                int n = 0;
                                jds[n] += Integer.parseInt(vs[k].substring(2));
                            }
                            else if (vs[k].startsWith("M")) {
                                int n2 = 1;
                                jds[n2] += Integer.parseInt(vs[k].substring(2));
                            }
                            else if (vs[k].startsWith("A")) {
                                int n3 = 2;
                                jds[n3] += Integer.parseInt(vs[k].substring(2));
                            }
                            else if (vs[k].startsWith("S")) {
                                int n4 = 3;
                                jds[n4] += Integer.parseInt(vs[k].substring(2));
                            }
                        }
                        palData.setJds(jds);
                        map.put(Integer.valueOf(palData.getPalId()), palData);
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
    
    public static ConcurrentHashMap<Long, PalEquip> selectPalEquip(String path, StringBuffer buffer) {
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
                try {
                    if (palEquip.getId() != 0) {
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
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return map;
    }
}
