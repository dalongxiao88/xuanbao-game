package org.come.readUtil;

import org.apache.commons.lang.StringUtils;
import org.come.entity.BaiTan;
import org.come.entity.JiaRenBT;
import org.come.handler.MainServerHandler;
import org.come.model.InitBaiTan;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.tool.ReadExelTool;
import org.come.tool.SettModelMemberTool;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static org.come.servlet.UpXlsAndTxtFile.addStringBufferMessage;

/**
 * 自动机器人
 * */
public class ReadBtUtil {
    public static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, JiaRenBT>> getAllBt(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, JiaRenBT>> getAllBt = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < Objects.requireNonNull(result).length; i++) {
            if (result[i][0].equals("")) {
                continue;
            }

            InitBaiTan initBaiTan = new InitBaiTan();
            for (int j = 0; j < result[i].length; j++) {
                try {
                    SettModelMemberTool.setReflectRelative(initBaiTan, result[i][j], j);
                } catch (Exception e) {
                    addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                    return null;
                }
            }
            try {
                if (initBaiTan.getId().equals("")) {
                    continue;
                }
                ConcurrentHashMap<Integer, JiaRenBT> hashMap = getAllBt.get(Integer.parseInt(initBaiTan.getId()));
                if (hashMap == null) {
                    hashMap = new ConcurrentHashMap<>();
                    getAllBt.put(Integer.parseInt(initBaiTan.getId()), hashMap);
                }
                JiaRenBT jiaRenBT = new JiaRenBT();
                jiaRenBT.setId(Integer.parseInt(initBaiTan.getId()));
                jiaRenBT.setStallId(Integer.parseInt(initBaiTan.getStallId()));
                jiaRenBT.setStallName(StringUtils.isBlank(jiaRenBT.getStallName()) ?  initBaiTan.getStallName(): jiaRenBT.getStallName());
//                if (initBaiTan.getGoodsId() != null&&initBaiTan.getGoodsName()!= null
//                        &&initBaiTan.getMoney()!=null&&initBaiTan.getUseTime()!=null) {//假人出售物品
//                }
                try {
                    jiaRenBT.setGoodsId(new BigDecimal(initBaiTan.getGoodsId()));
                    jiaRenBT.setGoodsName(initBaiTan.getGoodsName());
                    jiaRenBT.setMoney(Long.parseLong(initBaiTan.getMoney()));
                    jiaRenBT.setUseTime(Integer.parseInt(initBaiTan.getUseTime()));
                } catch (NumberFormatException e) {
                    jiaRenBT.setGoodsId(null);
                    jiaRenBT.setGoodsName(null);
                    jiaRenBT.setMoney(0L);
                    jiaRenBT.setUseTime(0);
                }
//                if (initBaiTan.getAcquiredId() != null&&initBaiTan.getAcquiredName()!=null
//                        &&initBaiTan.getAcquiredmoney()!=null&&initBaiTan.getAcquirednum()!=null) {//假人收购物品
//                }
                try {
                    jiaRenBT.setAcquiredId(new BigDecimal(initBaiTan.getAcquiredId()));
                    jiaRenBT.setAcquiredName(initBaiTan.getAcquiredName());
                    jiaRenBT.setAcquiredmoney(Long.parseLong(initBaiTan.getAcquiredmoney()));
                    jiaRenBT.setAcquirednum(Integer.parseInt(initBaiTan.getAcquirednum()));
                }catch (NumberFormatException e){
                    jiaRenBT.setAcquiredId(null);
                    jiaRenBT.setAcquiredName(null);
                    jiaRenBT.setAcquiredmoney(0L);
                    jiaRenBT.setAcquirednum(0);
                }
                jiaRenBT.setMapId(Integer.parseInt(initBaiTan.getMapId()));
                jiaRenBT.setMap_x(Integer.parseInt(initBaiTan.getMap_x()));
                jiaRenBT.setMap_y(Integer.parseInt(initBaiTan.getMap_y()));
                jiaRenBT.setCurrency(initBaiTan.getCurrency());
                jiaRenBT.setAuto_sj(initBaiTan.getAuto_sj());
                jiaRenBT.setShuaxin(initBaiTan.getShuaxin());
                hashMap.put(Integer.parseInt(initBaiTan.getId()), jiaRenBT);

            } catch (Exception e) {
                // TODO: handle exception
                addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e));
                return null;
            }
        }

        return getAllBt;
    }
}