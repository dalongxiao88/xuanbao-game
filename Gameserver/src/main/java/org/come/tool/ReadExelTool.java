package org.come.tool;

import jxl.Cell;
import jxl.Sheet;
import java.io.IOException;
import jxl.read.biff.BiffException;
import jxl.Workbook;
import java.io.File;

public class ReadExelTool
{
    private static String[][] result;
    
    public static synchronized String[][] getResult(String path) {
        try {
            String tablePath = ReadExelTool.class.getResource("/").getPath() + path;
            Workbook workbook = Workbook.getWorkbook(new File(tablePath));
            Sheet sheet = workbook.getSheet(0);
            int col = sheet.getColumns();
            int row = sheet.getRows();
            ReadExelTool.result = new String[row][col];
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    try {
                        Cell cell = sheet.getCell(j, i);
                        ReadExelTool.result[i][j] = cell.getContents();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return ReadExelTool.result;
        }
        catch (BiffException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        return (String[][])null;
    }
    
    public static synchronized String[][] getResulttwo(String path) {
        try {
            String tablePath = ReadExelTool.class.getResource("/").getPath() + path;
            Workbook workbook = Workbook.getWorkbook(new File(tablePath));
            Sheet sheet = workbook.getSheet(1);
            int col = sheet.getColumns();
            int row = sheet.getRows();
            ReadExelTool.result = new String[row][col];
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    try {
                        Cell cell = sheet.getCell(j, i);
                        ReadExelTool.result[i][j] = cell.getContents();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return ReadExelTool.result;
        }
        catch (BiffException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        return (String[][])null;
    }
    
    public static synchronized String[][] getResultChooseSheet(String path, int st) {
        try {
            String tablePath = ReadExelTool.class.getResource("/").getPath() + path;
            Workbook workbook = Workbook.getWorkbook(new File(tablePath));
            Sheet sheet = workbook.getSheet(st);
            int col = sheet.getColumns();
            int row = sheet.getRows();
            ReadExelTool.result = new String[row][col];
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    try {
                        Cell cell = sheet.getCell(j, i);
                        ReadExelTool.result[i][j] = cell.getContents();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return ReadExelTool.result;
        }
        catch (BiffException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        return (String[][])null;
    }
    
    public static synchronized String[][] getResultRelative(String path) {
        try {
            Workbook workbook = Workbook.getWorkbook(new File(path));
            Sheet sheet = workbook.getSheet(0);
            int col = sheet.getColumns();
            int row = sheet.getRows();
            ReadExelTool.result = new String[row][col];
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    try {
                        Cell cell = sheet.getCell(j, i);
                        ReadExelTool.result[i][j] = cell.getContents();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return ReadExelTool.result;
        }
        catch (BiffException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        return (String[][])null;
    }
}
