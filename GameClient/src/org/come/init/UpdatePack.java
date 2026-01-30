package org.come.init;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.io.File;

public class UpdatePack
{
    private String version;
    private long length;
    private UpdateView updateView;
    
    UpdatePack(String version, UpdateView updateView, long length) {
        this.version = version;
        this.length = length;
        this.updateView = updateView;
        this.startUpdate();
    }
    
    public void startUpdate() {
        long start = System.currentTimeMillis();
        Tool.downloadHttpUrl("http://110.40.71.85:9398/request/UpdatePack/" + this.version + ".zip", System.getProperty("user.dir") + "/update/UpdatePacks/", this.version + ".zip", this.updateView, this.length);
        long end = System.currentTimeMillis();
        this.updateView.getTitle().setText("下载完成，耗时：" + (end - start) + " ms 开始解压" + this.version + ".zip....");
        start = System.currentTimeMillis();
        Tool.unZip(new File(System.getProperty("user.dir") + "/update/UpdatePacks/" + this.version + ".zip"), System.getProperty("user.dir") + "/update/UpdatePacks/" + this.version);
        end = System.currentTimeMillis();
        this.updateView.getTitle().setText("解压完成，耗时：" + (end - start) + " ms");
        this.updateView.getTitle().setText("开始更新" + this.version);
        String p = System.getProperty("user.dir") + "/update/UpdatePacks/" + this.version + "/";
        if (new File(p + "Update.jar").exists()) {
            Tool.loadJar(p + "Update.jar");
            Class aClass = null;
            try {
                aClass = Class.forName("cn.dahua.update.Dahua");
                aClass.getDeclaredMethod("main", new Class[] { String[].class }).invoke(null,  this.version  );
            }
            catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        else if (!new File(p + "Update.bat").exists() && new File(p + "Update.json").exists()) {
            Tool.parsing(Tool.StrintToJson(Tool.readToString(p + "Update.json")), this.version);
        }
        File file = new File(System.getProperty("user.dir") + "/update/version.json");
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(file));
            ps.println("{\"version\":\"" + this.version + "\"}");
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
    }
}
