package com.tool.tcp;

import java.util.regex.Pattern;
import java.awt.Image;
import java.util.Objects;
import com.gl.util.Xy2Util;
import java.util.Random;

public class GetTcpPath
{
    public static String WEI;
    public static String ROLE;
    public static String BUFF;
    public static String SKILL;
    public static String SKILLXJ;
    public static String MOUSE;
    public static String LIN;
    public static String PK;
    public static String STRTMP;
    public static String STRTMPXJ;
    public static String CLOUD1;
    public static String CLOUD2;
    public static String CLOUD3;
    public static Random random;
    
    public static String getRoleTcp(String skin, int type, String ColorScheme) {
        if (isInteger(skin)) {
            Long ls = Long.valueOf(Long.parseLong(skin));
            if ((long)ls >= 8796093042209L && (long)ls < 9895604669985L) {
                String s = ls.toString().replace("879609304", "");
                if (Xy2Util.roleneworold.equals("旧")) {
                    if (s.equals("4211")) {
                        s = "4211-1";
                    }
                    if (s.equals("2211")) {
                        s = "2211-1";
                    }
                }
                skin = "400566/BASE/" + s;
                StringBuffer buffer = new StringBuffer();
                buffer.append(GetTcpPath.ROLE);
                buffer.append(skin);
                buffer.append("/");
                buffer.append("BASE");
                buffer.append(".HH");
                String dongzuo = SpriteFactory.getActionType(type);
                if (Objects.equals(skin, "9999") && dongzuo.equals("attack")) {
                    dongzuo = dongzuo + "-" + GetTcpPath.random.nextInt(3);
                }
                buffer.append(dongzuo);
                buffer.append(GetTcpPath.WEI);
                if (ColorScheme != null && !ColorScheme.equals("")) {
                    buffer.append("_");
                    buffer.append(ColorScheme);
                }
                return buffer.toString();
            }
            else if ((long)ls >= 9895604669985L && (long)ls < 10995116297761L) {
                String s = ls.toString().replace("98956046", "");
                if (Xy2Util.roleneworold.equals("旧")) {
                    if (s.equals("71987")) {
                        s = "71987-1";
                    }
                    if (s.equals("69987")) {
                        s = "69987-1";
                    }
                }
                skin = "/500173/LMCY/" + s;
                StringBuffer buffer = new StringBuffer();
                buffer.append(GetTcpPath.ROLE);
                buffer.append(skin);
                buffer.append("/");
                buffer.append("BASE");
                buffer.append(".HH");
                if (ColorScheme != null && !ColorScheme.equals("")) {
                    buffer.append("_");
                    buffer.append(ColorScheme);
                }
                return buffer.toString();
            }
            else if ((long)ls >= 10995116297761L && (long)ls < 12094627925537L) {
                String s = ls.toString().replace("1099511629", "");
                StringBuffer buffer = new StringBuffer();
                buffer.append(GetTcpPath.ROLE);
                buffer.append("/");
                buffer.append("BASE");
                buffer.append(".HH");
                if (ColorScheme != null && !ColorScheme.equals("")) {
                    buffer.append("_");
                    buffer.append(ColorScheme);
                }
                return buffer.toString();
            }
            else if ((long)ls >= 12094627925537L) {
                String s = ls.toString().replace("1209462792", "");
                StringBuffer buffer = new StringBuffer();
                buffer.append(GetTcpPath.ROLE);
                buffer.append("/");
                buffer.append("BASE");
                buffer.append(".HH");
                if (ColorScheme != null && !ColorScheme.equals("")) {
                    buffer.append("_");
                    buffer.append(ColorScheme);
                }
                return buffer.toString();
            }
            else {
                //skin/310003/200029991/stand
                //zheyang fang 好的
                //后边的编号改一下就行了好
                //人物座驾添加
                if (
                        (long)ls == 200019991 ||(long)ls == 200019992 ||
                        (long)ls == 200029991 ||(long)ls == 200029992 ||
                        (long)ls == 200039991 ||(long)ls == 200039992 ||
                        (long)ls == 200049991 ||(long)ls == 200049992 ||
                        (long)ls == 200059991 ||(long)ls == 200059992 ||
                        (long)ls == 200069991 ||(long)ls == 200069992 ||
                        (long)ls == 200079991 ||(long)ls == 200079992 ||
                                (long)ls == 200089991 ||(long)ls == 200089992 ||
                                (long)ls == 200099991 ||(long)ls == 200099992 ||
                                (long)ls == 200109991 ||(long)ls == 200109992 ||
                                (long)ls == 200119991 ||(long)ls == 200119992 ||
                                (long)ls == 200129991 ||(long)ls == 200129992 ||
                                (long)ls == 210019991 ||(long)ls == 210019992 ||
                                (long)ls == 210029991 ||(long)ls == 210029992 ||
                                (long)ls == 210039991 ||(long)ls == 210039992 ||
                                (long)ls == 210049991 ||(long)ls == 210049992 ||
                                (long)ls == 210059991 ||(long)ls == 210059992 ||
                                (long)ls == 210069991 ||(long)ls == 210069992 ||
                                (long)ls == 210079991 ||(long)ls == 210079992 ||
                                (long)ls == 210089991 ||(long)ls == 210089992 ||
                                (long)ls == 210099991 ||(long)ls == 210099992 ||
                                (long)ls == 210119991 ||(long)ls == 210119992 ||
                                (long)ls == 210129991 ||(long)ls == 210129992 ||
                                (long)ls == 220019991 ||(long)ls == 220019992 ||
                                (long)ls == 220029991 ||(long)ls == 220029992 ||
                                (long)ls == 220039991 ||(long)ls == 220039992 ||
                                (long)ls == 220049991 ||(long)ls == 220049992 ||
                                (long)ls == 220059991 ||(long)ls == 220059992 ||
                                (long)ls == 220069991 ||(long)ls == 220069992 ||
                                (long)ls == 220079991 ||(long)ls == 220079992 ||
                                (long)ls == 220089991 ||(long)ls == 220089992 ||
                                (long)ls == 220099991 ||(long)ls == 220099992 ||
                                (long)ls == 220109991 ||(long)ls == 220109992 ||
                                (long)ls == 220119991 ||(long)ls == 220119992 ||
                                (long)ls == 220129991 ||(long)ls == 220129992 ||
                                (long)ls == 230019991 ||(long)ls == 230019992 ||
                                (long)ls == 230029991 ||(long)ls == 230029992 ||
                                (long)ls == 230039991 ||(long)ls == 230039992 ||
                                (long)ls == 230049991 ||(long)ls == 230049992 ||
                                (long)ls == 230059991 ||(long)ls == 230059992 ||
                                (long)ls == 230069991 ||(long)ls == 230069992 ||
                                (long)ls == 230079991 ||(long)ls == 230079992 ||
                                (long)ls == 230089991 ||(long)ls == 230089992 ||
                                (long)ls == 240019991 ||(long)ls == 240019992 ||
                                (long)ls == 240029991 ||(long)ls == 240029992 ||
                                (long)ls == 240039991 ||(long)ls == 240039992 ||
                                (long)ls == 240049991 ||(long)ls == 240049992 ||
                                (long)ls == 240059991 ||(long)ls == 240059992 ||
                                (long)ls == 240069991 ||(long)ls == 240069992 ||
                                (long)ls == 240079991 ||(long)ls == 240079992 ||
                                (long)ls == 240089991 ||(long)ls == 240089992

                ) {
                    StringBuffer buffer2 = new StringBuffer();
                    buffer2.append(GetTcpPath.ROLE);
                    buffer2.append("310003/"+skin);
                    buffer2.append("/");
                    buffer2.append(SpriteFactory.getActionType(type));
                    if (ColorScheme != null && !ColorScheme.equals("")) {
                        buffer2.append("_");
                        buffer2.append(ColorScheme);
                    }
                    return buffer2.toString();
                }
                StringBuffer buffer2 = new StringBuffer();
                buffer2.append(GetTcpPath.ROLE);
                buffer2.append(skin);
                buffer2.append("/");
                buffer2.append(SpriteFactory.getActionType(type));
                buffer2.append(GetTcpPath.WEI);
                if (ColorScheme != null && !ColorScheme.equals("")) {
                    buffer2.append("_");
                    buffer2.append(ColorScheme);
                }
                return buffer2.toString();
            }
        }
        else {
            StringBuffer buffer3 = new StringBuffer();
            buffer3.append(GetTcpPath.ROLE);
            buffer3.append(skin);
            buffer3.append("/");
            String dongzuo2 = SpriteFactory.getActionType(type);
            if (Objects.equals(skin, "9999") && dongzuo2.equals("attack")) {
                dongzuo2 = dongzuo2 + "-" + GetTcpPath.random.nextInt(3);
            }
            buffer3.append(dongzuo2);
            buffer3.append(SpriteFactory.getActionType(type));
            buffer3.append(GetTcpPath.WEI);
            if (ColorScheme != null && !ColorScheme.equals("")) {
                buffer3.append("_");
                buffer3.append(ColorScheme);
            }
            return buffer3.toString();
        }
    }
    
    private static Image getImage(String tcps, String string) {
        return null;
    }
    
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    
    public static String getBuffTcp(String tcp) {
        return GetTcpPath.BUFF + tcp + GetTcpPath.WEI;
    }
    
    public static String getSkillTcp(String tcp) {
        if (GetTcpPath.STRTMPXJ.equals("1")) {
            return GetTcpPath.SKILL + tcp + GetTcpPath.WEI;
        }
        return GetTcpPath.SKILLXJ + tcp + GetTcpPath.WEI;
    }
    
    public static String getMouseTcp(String tcp) {
        return GetTcpPath.MOUSE + tcp + GetTcpPath.WEI;
    }
    
    public static String getflytcp(String tcp) {
        return GetTcpPath.ROLE + tcp + GetTcpPath.WEI;
    }
    
    public static String getflyShadowtcp(String tcp) {
        return GetTcpPath.ROLE + tcp + GetTcpPath.WEI;
    }
    
    static {
        GetTcpPath.WEI = ".tcp";
        GetTcpPath.ROLE = "skin/";
        GetTcpPath.BUFF = "resource/FightingSkill/持续状态/";
        GetTcpPath.SKILL = "resource/FightingSkill/";
        GetTcpPath.SKILLXJ = "resource/FightingSkillOld/";
        GetTcpPath.MOUSE = "resource/mouse/";
        GetTcpPath.STRTMP = "1";
        GetTcpPath.STRTMPXJ = "1";
        GetTcpPath.random = new Random();
        GetTcpPath.LIN = GetTcpPath.MOUSE + "令牌" + GetTcpPath.WEI;
        GetTcpPath.PK = GetTcpPath.MOUSE + "PK" + GetTcpPath.WEI;
        GetTcpPath.CLOUD1 = "cloud1";
        GetTcpPath.CLOUD2 = "cloud2";
        GetTcpPath.CLOUD3 = "cloud3";
    }
}
