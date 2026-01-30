package org.come.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import org.come.until.BuffeTest;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import org.come.socket.GameClient;
import com.updateNew.MyIsif;
import org.come.login.ImageMap;
import org.come.bean.PathPoint;
import org.come.login.LoginFrame;

public class Main
{
    public static LoginFrame frame;
    public static int index;
    public static String name;
    public static PathPoint pathPoint;
    public static ImageMap imageMap;
    
    public static void main(String[] args) throws Exception {
        MyIsif.setStyle(getStyle());
        String a = MyIsif.ifs;
        StringBuffer stringBuffer = new StringBuffer();
        for (String arg : args) {
            stringBuffer.append(arg);
        }
        if (a != null) {
            if (!a.equals("D")) {
                System.setProperty("java.awt.im.style", "on-the-spot");
                if (args != null) {
                    for (int i = 0; i < args.length; ++i) {
                        if (args[i].startsWith("-name")) {
                            System.out.println(args[i]);
                            String[] v = args[i].split("=");
                            Main.name = v[1];
                        }
                        else if (args[i].startsWith("-index")) {
                            Main.index = Integer.parseInt(args[i].split("=")[1]);
                            break;
                        }
                    }
                }
                if (Main.index < 0) {
                    System.exit(0);
                }
                GameClient.potAndIpStrings = GameClient.getServerIpAndPort();
            }
        }
        else {
            String isd = getTypes();
            if (!isd.equals("Multiple")) {
                System.err.println("该程序不可以直接启动，请点击【登录器】启动！");
                System.exit(0);
            }
            System.setProperty("java.awt.im.style", "on-the-spot");
            if (args != null) {
                for (int j = 0; j < args.length; ++j) {
                    if (args[j].startsWith("-name")) {
                        String[] v2 = args[j].split("=");
                        Main.name = v2[1];
                    }
                    else if (args[j].startsWith("-index")) {
                        Main.index = Integer.parseInt(args[j].split("=")[1]);
                        break;
                    }
                }
            }
            if (Main.index < 0) {
                System.exit(0);
            }
            GameClient.potAndIpStrings = GameClient.getServerIpAndPort();
        }
        GameClient.potAndIpStrings = GameClient.getServerIpAndPort();
        Main.frame = new LoginFrame();
        if (!a.equals("D")) {
            new Thread(()/*  */ -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String xy = scanner.next();
                    String[] xys = xy.split(":");
                    if (xys.length < 2) {
                        continue;
                    }
                    else {
                        Main.pathPoint.setX(Integer.parseInt(xys[0]));
                        Main.pathPoint.setY(Integer.parseInt(xys[1]) + 64);
                    }
                }
            }).start();
        }
        if ((boolean)MyIsif.dun) {
            String property = System.getProperty("user.dir");
            System.load(property + "\\QianNiao.dll");
            MyNativeLib instance = MyNativeLib.INSTANCE;
        }
    }
    
    public static void loadImage() {
        try {
            (Main.imageMap = new ImageMap()).setImgMap(new HashMap<>());
            String[] arry;
            for (String a : arry = new String[] { "inkImg/cbg/" }) {
                File dir = new File(a);
                if (!dir.exists()) {
                    throw new IllegalArgumentException("目录：" + dir + "不存在.");
                }
                if (!dir.isDirectory()) {
                    throw new IllegalArgumentException(dir + "不是目录。");
                }
                File[] files = dir.listFiles();
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        BuffeTest.decryptFileTOByte(file, Main.imageMap.getImgMap());
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isMoreOpen() {
        return false;
    }
    
    public static String getTypes() {
        String url = "";
        String ur = System.getProperty("user.dir");
        String filePath = ur + "/resource/other/StartUpMode.txt";
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(filePath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        try {
            while ((strTmp = buffReader.readLine()) != null) {
                url = strTmp;
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            buffReader.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        return url;
    }
    
    public static String getStyle() {
        String url = "";
        String ur = System.getProperty("user.dir");
        String filePath = ur + "/resource/other/style.txt";
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(filePath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        try {
            while ((strTmp = buffReader.readLine()) != null) {
                url = strTmp;
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            buffReader.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        return url;
    }
    
    static {
        Main.index = 1;
        Main.name = null;
        Main.pathPoint = new PathPoint(0, 0);
    }
}
