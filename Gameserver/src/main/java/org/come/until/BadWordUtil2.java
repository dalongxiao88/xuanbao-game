package org.come.until;

import java.util.HashMap;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BadWordUtil2
{
    public static String filePath;
    public static Set<String> words;
    public static Map<String, String> wordMap;
    public static int minMatchTYpe;
    public static int maxMatchType;
    
    public static Set<String> readTxtByLine(String path) {
        Set<String> keyWordSet = new HashSet<>();
        File file = new File(path);
        if (!file.exists()) {
            return keyWordSet;
        }
        BufferedReader reader = null;
        String temp = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            while ((temp = reader.readLine()) != null) {
                keyWordSet.add(temp);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return keyWordSet;
    }
    
    public static int checkBadWord(String txt, int beginIndex, int matchType) {
        boolean flag = false;
        int matchFlag = 0;
        char word = '\0';
        Map nowMap = BadWordUtil2.wordMap;
        int i = beginIndex;
        while (i < txt.length()) {
            word = txt.charAt(i);
            nowMap = (Map)nowMap.get(Character.valueOf(word));
            if (nowMap != null) {
                ++matchFlag;
                if ("1".equals(nowMap.get("isEnd"))) {
                    flag = true;
                    if (BadWordUtil2.minMatchTYpe == matchType) {
                        break;
                    }
                }
                ++i;
            }
            else {
                break;
            }
        }
        if (!flag) {
            matchFlag = 0;
        }
        return matchFlag;
    }
    
    public static boolean isContaintBadWord(String txt, int matchType) {
        boolean flag = false;
        for (int i = 0; i < txt.length(); ++i) {
            int matchFlag = checkBadWord(txt, i, matchType);
            if (matchFlag > 0) {
                flag = true;
            }
        }
        return flag;
    }
    
    public static String replaceBadWord(String txt, int matchType, String replaceChar) {
        String resultTxt = txt;
        Set<String> set = getBadWord(txt, matchType);
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = (String)iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }
        return resultTxt;
    }
    
    public static Set<String> getBadWord(String txt, int matchType) {
        Set<String> sensitiveWordList = new HashSet<>();
        for (int i = 0; i < txt.length(); ++i) {
            int length = checkBadWord(txt, i, matchType);
            if (length > 0) {
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1;
            }
        }
        return sensitiveWordList;
    }
    
    private static String getReplaceChars(String replaceChar, int length) {
        String resultReplace = replaceChar;
        for (int i = 1; i < length; ++i) {
            resultReplace += replaceChar;
        }
        return resultReplace;
    }
    
    private static void addBadWordToHashMap(Set<String> keyWordSet) {
        BadWordUtil2.wordMap = new HashMap(keyWordSet.size());
        String key = null;
        Map nowMap = null;
        Map newWorMap = null;
        Iterator<String> iterator = keyWordSet.iterator();
        while (iterator.hasNext()) {
            key = (String)iterator.next();
            nowMap = BadWordUtil2.wordMap;
            for (int i = 0; i < key.length(); ++i) {
                char keyChar = key.charAt(i);
                Object wordMap = nowMap.get(Character.valueOf(keyChar));
                if (wordMap != null) {
                    nowMap = (Map)wordMap;
                }
                else {
                    newWorMap = new HashMap<>();
                    newWorMap.put("isEnd", "0");
                    nowMap.put(Character.valueOf(keyChar), newWorMap);
                    nowMap = newWorMap;
                }
                if (i == key.length() - 1) {
                    nowMap.put("isEnd", "1");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Set<String> s = BadWordUtil2.words;
        Map<String, String> map = BadWordUtil2.wordMap;
        System.out.println("敏感词的数量：" + BadWordUtil2.wordMap.size());
        String string = "太 多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，难过就躺在某一个人的怀里尽情的阐述粉饰心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
        System.out.println("待检测语句字数：" + string.length());
        long beginTime = System.currentTimeMillis();
        Set<String> set = getBadWord(string, 2);
        Boolean i = Boolean.valueOf(isContaintBadWord(string, 2));
        Boolean i2 = Boolean.valueOf(isContaintBadWord("粉饰太平", 2));
        Boolean i3 = Boolean.valueOf(isContaintBadWord("粉饰太平", 1));
        Boolean i4 = Boolean.valueOf(isContaintBadWord("粉饰", 2));
        Boolean i5 = Boolean.valueOf(isContaintBadWord("粉饰", 1));
        Boolean i6 = Boolean.valueOf(isContaintBadWord("太平", 2));
        Boolean i7 = Boolean.valueOf(isContaintBadWord("太平", 1));
        Boolean i8 = Boolean.valueOf(isContaintBadWord("个人崇拜", 2));
        Boolean i9 = Boolean.valueOf(isContaintBadWord("个人崇拜", 1));
        Boolean i10 = Boolean.valueOf(isContaintBadWord("个人", 2));
        Boolean i11 = Boolean.valueOf(isContaintBadWord("个人", 1));
        Boolean i12 = Boolean.valueOf(isContaintBadWord("崇拜", 2));
        Boolean i13 = Boolean.valueOf(isContaintBadWord("崇拜", 1));
        long endTime = System.currentTimeMillis();
        System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
        System.out.println("总共消耗时间为：" + (endTime - beginTime));
    }
    
    static {
        BadWordUtil2.filePath = "D:\\dictionary.txt";
        BadWordUtil2.minMatchTYpe = 1;
        BadWordUtil2.maxMatchType = 2;
        addBadWordToHashMap(BadWordUtil2.words = readTxtByLine(BadWordUtil2.filePath));
    }
}
