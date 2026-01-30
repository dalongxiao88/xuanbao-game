package org.come.readUtil;

import java.util.List;
import org.come.action.buy.BuyShopAction;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.QuackGameBean;
import org.come.action.lottery.DrawBase;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import java.util.ArrayList;
import org.come.action.lottery.Draw;
import java.util.concurrent.ConcurrentHashMap;

public class ReadDrawUtil
{
    public static ConcurrentHashMap<Integer, Draw> selectDraw(String path, StringBuffer buffer) {
        List<Draw> list = new ArrayList<>();
        ConcurrentHashMap<Integer, Draw> draws = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Draw draw = new Draw();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(draw, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (draw.getDid() != 0) {
                    try {
                        String[] goods = result[i][5].split("&");
                        DrawBase[] drawBases = new DrawBase[goods.length];
                        for (int k = 0; k < goods.length; ++k) {
                            String[] canGetGoods = goods[k].split("\\$");
                            (drawBases[k] = new DrawBase()).setV(Double.parseDouble(canGetGoods[2]));
                            drawBases[k].setSum(Integer.parseInt(canGetGoods[1]));
                            String[] getGoods = canGetGoods[0].split("-");
                            int[] ids = new int[getGoods.length];
                            for (int l = 0; l < ids.length; ++l) {
                                ids[l] = Integer.parseInt(getGoods[l]);
                            }
                            drawBases[k].setIds(ids);
                        }
                        draw.setDrawBases(drawBases);
                        list.add(draw);
                    }
                    catch (Exception e2) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                        return null;
                    }
                }
            }
        }
        try {
            String[] CJS = new String[list.size()];
            for (int m = 0; m < list.size(); ++m) {
                Draw draw2 = (Draw)list.get(m);
                CJS[m] = draw2.getName() + "积分";
                StringBuffer bufferTwo = new StringBuffer();
                for (int k = 0; k < list.size(); ++k) {
                    Draw draw3 = (Draw)list.get(k);
                    if (bufferTwo.length() != 0) {
                        bufferTwo.append("|");
                    }
                    bufferTwo.append((m == k) ? "Y" : "N");
                    bufferTwo.append(draw3.getDid());
                    bufferTwo.append("-");
                    bufferTwo.append(draw3.getName());
                }
                bufferTwo.append("|");
                bufferTwo.append(draw2.getGoods());
                QuackGameBean bean = new QuackGameBean();
                bean.setType(3);
                if ("天梯奖池".equals(draw2.getName())) {
                    bean.setType(6);
                }
                if ("幸运大奖".equals(draw2.getName()))
                    bean.setType(10);
                bean.setMoney(draw2.getMoney());
                bean.setPetmsg(bufferTwo.toString());
                bean.setPetmsg2(draw2.getMoneyType() + "|" + draw2.getGoodid());
                draw2.setText(Agreement.getAgreement().getfivemsgAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
                draws.put(Integer.valueOf(draw2.getDid()), draw2);
            }
            BuyShopAction.CJS = CJS;
        }
        catch (Exception e3) {
            UpXlsAndTxtFile.addStringBufferMessage(buffer, 0, 0, "解析错误", MainServerHandler.getErrorMessage(e3));
            return null;
        }
        return draws;
    }
}
