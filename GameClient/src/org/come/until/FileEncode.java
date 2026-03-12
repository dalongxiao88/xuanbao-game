package org.come.until;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.List;

public class FileEncode
{
    public List<String> getFileNames(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        List<String> fileNames = new ArrayList<>();
        return this.getFileNames(file, fileNames);
    }
    
    private List<String> getFileNames(File file, List<String> fileNames) {
        File[] files;
        for (File f : files = file.listFiles()) {
            if (f.isDirectory()) {
                this.getFileNames(f, fileNames);
            }
            else {
                fileNames.add(f.getName());
            }
        }
        return fileNames;
    }
    
    public static void main(String[] args) {
        String path = "G:\\jieya\\b";
        String path2 = "G:\\jieya\\b1";
        FileEncode fileEncode = new FileEncode();
        BuffeTest buffeTest1 = new BuffeTest();
        List<String> fileList = fileEncode.getFileNames(path);
        // TRACE[C2-05][2026-03-13]: 清理文件编码工具遍历中的反编译器类型注释残留。
        fileList.forEach(item -> {
            String name = item.substring(0, item.indexOf(".png"));
            try {
                BuffeTest.decryptFile( new File(path + "\\" + item),    new File(path2 + "\\" + name + ".png"));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        });
    }
}
