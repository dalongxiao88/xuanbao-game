package org.come.tool;

import org.come.until.SplitLingbaoValue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SplitStringTool
{
    public static List<String> splitString(String allNpcID) {
        List<String> numbers = new ArrayList<>();
        if (allNpcID != null) {
            String[] v = allNpcID.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                if (v[i].indexOf("-") != -1) {
                    String[] count = v[i].split("-");
                    Integer start = Integer.valueOf(Integer.parseInt(count[0]));
                    Integer end = Integer.valueOf(Integer.parseInt(count[1]));
                    for (int j = (int)start; j <= (int)end; ++j) {
                        numbers.add(String.valueOf(j));
                    }
                }
                else if (v[i].indexOf("&") != -1) {
                    String[] count = v[i].split("&");
                    for (int sum = Integer.parseInt(count[1]), k = 0; k < sum; ++k) {
                        numbers.add(count[0]);
                    }
                }
                else {
                    numbers.add(v[i]);
                }
            }
            return numbers;
        }
        else {
            return numbers;
        }
    }
    
    public static List<Long> splitLong(String allNpcID) {
        List<Long> longs = new ArrayList<>();
        List<String> list = splitString(allNpcID);
        for (int i = 0, size = list.size(); i < size; ++i) {
            try {
                longs.add(Long.valueOf(Long.parseLong((String)list.get(i))));
            }
            catch (Exception ex) {}
        }
        return longs;
    }
    
    public static BigDecimal GoodRandomId(String all) {
        String[] vs = all.split("&");
        int up = 0;
        for (int i = 0; i < vs.length; ++i) {
            String[] vss = vs[i].split("\\$");
            if (vss.length == 1) {
                return RandomId(vss[0]);
            }
            double d = Double.parseDouble(vss[1]) * 100.0;
            if (d + (double)up > (double)SplitLingbaoValue.random.nextInt(10000)) {
                return RandomId(vss[0]);
            }
            up = (int)((double)up + d);
            if (i == vs.length - 1) {
                return RandomId(vss[0]);
            }
        }
        return null;
    }
    
    public static BigDecimal RandomId(String allNpcID) {
        List<String> numbers = splitString(allNpcID);
        if (numbers == null || numbers.size() == 0) {
            return null;
        }
        return new BigDecimal((String)numbers.get(SplitLingbaoValue.random.nextInt(numbers.size())));
    }
    
    public static List<String> Randoms(String allNpcID) {
        List<String> numbers = new ArrayList<>();
        if (allNpcID == null || allNpcID.equals("")) {
            return numbers;
        }
        String[] v = allNpcID.split("\\|");
        for (int i = 1; i < v.length; ++i) {
            if (v[i].indexOf("-") != -1) {
                String[] count = v[i].split("-");
                Integer start = Integer.valueOf(Integer.parseInt(count[0]));
                Integer end = Integer.valueOf(Integer.parseInt(count[1]));
                for (int j = (int)start; j <= (int)end; ++j) {
                    numbers.add(String.valueOf(j));
                }
            }
            else if (v[i].indexOf("&") != -1) {
                String[] count = v[i].split("&");
                for (int sum = Integer.parseInt(count[1]), k = 0; k < sum; ++k) {
                    numbers.add(count[0]);
                }
            }
            else {
                numbers.add(v[i]);
            }
        }
        int size = Integer.parseInt(v[0]);
        List<String> ids = new ArrayList<>();
        int l = 0;
        while (ids.size() < size) {
            String id = (String)numbers.get(SplitLingbaoValue.random.nextInt(numbers.size()));
            if (!ids.contains(id)) {
                ids.add(id);
            }
            if (l > 1200) {
                break;
            }
            else {
                ++l;
            }
        }
        return ids;
    }
}
