package org.come.readUtil;

import org.come.entity.Car;
import org.come.entity.Mount;
import org.come.handler.MainServerHandler;
import org.come.model.InitMount;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.tool.ReadExelTool;
import org.come.tool.SettModelMemberTool;

import java.util.concurrent.ConcurrentHashMap;

public class ReadCarUtil
{
    public static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Car>> getAllCar(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Car>> getAllCar = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                InitMount mount = new InitMount();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(mount, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (!mount.getRaceid().equals("")) {
                        ConcurrentHashMap<Integer, Car> hashMap = (ConcurrentHashMap<Integer, Car>)getAllCar.get(Integer.valueOf(Integer.parseInt(mount.getRaceid())));
                        if (hashMap == null) {
                            hashMap = new ConcurrentHashMap<>();
                            getAllCar.put(Integer.valueOf(Integer.parseInt(mount.getRaceid())), hashMap);
                        }
                        Car mountBean = new Car();
                        mountBean.setMountname(mount.getMountname());
                        mountBean.setMountid(Integer.valueOf(Integer.parseInt(mount.getMountid())));
                        mountBean.setMountlvl(Integer.valueOf(Integer.parseInt(mount.getMountlvl())));
                        mountBean.setLive(Integer.valueOf(Integer.parseInt(mount.getLive())));
                        mountBean.setSpri(Integer.valueOf(Integer.parseInt(mount.getSpri())));
                        mountBean.setPower(Integer.valueOf(Integer.parseInt(mount.getPower())));
                        mountBean.setBone(Integer.valueOf(Integer.parseInt(mount.getBone())));
                        mountBean.setGradeexp(Integer.valueOf(Integer.parseInt(mount.getExp())));
                        hashMap.put(Integer.valueOf(Integer.parseInt(mount.getMountid())), mountBean);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return getAllCar;
    }
}
