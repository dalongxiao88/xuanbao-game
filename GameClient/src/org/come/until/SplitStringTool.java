package org.come.until;

import java.util.ArrayList;
import java.util.List;

public class SplitStringTool
{
    public static List<String> splitString(String allNpcID) {
        List<String> numbers = new ArrayList<>();
        if (allNpcID != null && !allNpcID.equals("")) {
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
}
