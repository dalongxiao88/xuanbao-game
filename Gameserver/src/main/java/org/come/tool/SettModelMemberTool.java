package org.come.tool;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;

public class SettModelMemberTool
{
    public static void setReflect(Object model, String values, int digit) throws Exception {
        Field[] field = model.getClass().getDeclaredFields();
        if (digit < field.length) {
            String name = field[digit].getName();
            Field f = model.getClass().getDeclaredField(name);
            f.setAccessible(true);
            if (field[digit].getType().toString().equals("class java.lang.Integer")) {
                if (!values.equals("")) {
                    f.set(model, Integer.valueOf(Integer.parseInt(values)));
                }
            }
            else if (field[digit].getType().toString().equals("class java.lang.String")) {
                f.set(model, values);
            }
            else if (field[digit].getType().toString().equals("class java.math.BigDecimal")) {
                if (!values.equals("")) {
                    f.set(model, new BigDecimal(values));
                }
            }
            else if (field[digit].getType().toString().equals("class java.lang.Long")) {
                if (!values.equals("")) {
                    f.set(model, Long.valueOf(Long.parseLong(values)));
                }
            }
            else if (field[digit].getType().toString().equals("class java.lang.Double") || field[digit].getType().toString().equals("double")) {
                if (!values.equals("")) {
                    f.set(model, Double.valueOf(Double.parseDouble(values)));
                }
            }
            else if (field[digit].getType().toString().equals("int")) {
                if (!values.equals("")) {
                    f.set(model, Integer.valueOf(Integer.parseInt(values)));
                }
            }
            else if (field[digit].getType().toString().equals("long")) {
                if (!values.equals("")) {
                    f.set(model, Long.valueOf(Long.parseLong(values)));
                }
            }
            else if (field[digit].getType().toString().equals("class java.time.LocalTime") && !values.equals("")) {
                f.set(model, LocalTime.parse(values, DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        }
    }
    
    public static void setReflectRelative(Object model, String values, int digit) throws Exception {
        Field[] field = model.getClass().getDeclaredFields();
        if (digit < field.length) {
            String name = field[digit].getName();
            Field f = model.getClass().getDeclaredField(name);
            f.setAccessible(true);
            if (field[digit].getType().toString().equals("class java.lang.Integer")) {
                if (!values.equals("")) {
                    f.set(model, Integer.valueOf(Integer.parseInt(values)));
                }
            }
            else if (field[digit].getType().toString().equals("class java.lang.String")) {
                f.set(model, values);
            }
            else if (field[digit].getType().toString().equals("class java.math.BigDecimal")) {
                if (!values.equals("")) {
                    f.set(model, new BigDecimal(values));
                }
            }
            else if (field[digit].getType().toString().equals("class java.lang.Long")) {
                if (!values.equals("")) {
                    f.set(model, Long.valueOf(Long.parseLong(values)));
                }
            }
            else if (field[digit].getType().toString().equals("class java.lang.Double") || field[digit].getType().toString().equals("double")) {
                if (!values.equals("")) {
                    f.set(model, Double.valueOf(Double.parseDouble(values)));
                }
            }
            else if (field[digit].getType().toString().equals("int")) {
                if (!values.equals("")) {
                    f.set(model, Integer.valueOf(Integer.parseInt(values)));
                }
            }
            else if (field[digit].getType().toString().equals("long")) {
                if (!values.equals("")) {
                    f.set(model, Long.valueOf(Long.parseLong(values)));
                }
            }
            else if (field[digit].getType().toString().equals("class java.time.LocalTime") && !values.equals("")) {
                f.set(model, LocalTime.parse(values, DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        }
    }
}
