package org.updateNew;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class updateTcp
{
    static String ur;
    static String path;
    
    public static void main(String[] args) {
        File fileText = new File(updateTcp.path);
        if (fileText.canExecute()) {
            setText(fileText, "SP", "SP");
        }
    }
    
    private static void setText(File fileText, String target, String src) {
        BufferedReader br = null;
        PrintWriter pw = null;
        StringBuffer buff = new StringBuffer();
        String line = System.getProperty("line.separator");
        try {
            br = new BufferedReader(new FileReader(fileText));
            for (String str = br.readLine(); str != null; str = br.readLine()) {
                if (str.contains(target)) {
                    str = str.replaceAll(target, src);
                }
                buff.append(str + line);
            }
            pw = new PrintWriter(new FileWriter(fileText), true);
            pw.println(buff);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
    }
    
    static {
        updateTcp.ur = System.getProperty("user.dir");
        updateTcp.path = updateTcp.ur + "/skin/500173/dslmy/10995116297761/stand1.tcp";
    }
}
