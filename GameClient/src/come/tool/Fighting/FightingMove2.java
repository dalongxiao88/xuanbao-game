package come.tool.Fighting;

import org.come.bean.RoleShow;
import com.tool.image.ManimgAttribute;
import org.come.bean.PathPoint;
import java.util.List;

public class FightingMove2
{
    public static int getmove2(int s, int e, long time, long endtime) {
        return s + (int)((double)(e - s) * getdou(time, endtime));
    }
    
    public static double getdou(long time, long endtime) {
        double b = (double)time / (double)endtime;
        return (b > 1.0) ? 1.0 : b;
    }
    
    public static String calculateRelativePosition(int x1, int y1, int x2, int y2) {
        String position = "";
        if (y1 > y2) {
            position += "上";
        }
        else if (y1 < y2) {
            position += "下";
        }
        if (x1 > x2) {
            position += "左";
        }
        else if (x1 < x2) {
            position += "右";
        }
        return position;
    }
    
    public static boolean getmovetime(Object object, List<PathPoint> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        if (list.size() == 1) {
            if (object instanceof Fightingimage) {
                Fightingimage fightingimage = (Fightingimage)object;
                fightingimage.setX(((PathPoint)list.get(0)).getX());
                fightingimage.setY(((PathPoint)list.get(0)).getY());
            }
            else if (object instanceof ShadowMode) {
                ShadowMode shadowMode = (ShadowMode)object;
                shadowMode.setX(((PathPoint)list.get(0)).getX());
                shadowMode.setY(((PathPoint)list.get(0)).getY());
            }
            return true;
        }
        else {
            int x = 0;
            int y = 0;
            if (object instanceof Fightingimage) {
                Fightingimage fightingimage2 = (Fightingimage)object;
                x = fightingimage2.getX();
                y = fightingimage2.getY();
            }
            else if (object instanceof ShadowMode) {
                ShadowMode shadowMode2 = (ShadowMode)object;
                x = shadowMode2.getX();
                y = shadowMode2.getY();
            }
            PathPoint point1 = (PathPoint)list.get(0);
            PathPoint point2 = (PathPoint)list.get(1);
            double aaa = 2.2;//战斗内的移动速度
//            if (Fightingimage.getNum() >= 2 && Fightingimage.getNum() <= 3) {
//                aaa = 2.6;
//            }
//            else if (Fightingimage.getNum() > 3 && Fightingimage.getNum() <= 6) {
//                aaa = 2.6;
//            }
//            else if (Fightingimage.getNum() > 6 && Fightingimage.getNum() <= 188) {
//                aaa = 2.6;
//            }
//            else if (Fightingimage.getNum() > 188 && Fightingimage.getNum() <= 1880) {
//                aaa = 2.6;
//            }
//            else if (Fightingimage.getNum() > 1880 && Fightingimage.getNum() <= 999999999) {
//                aaa = 2.6;
//            }
//            else {
//                aaa = 0.65;
//            }
            long endtime1 = gettime((long)(point2.getX() - point1.getX()), (long)(point2.getY() - point1.getY()), aaa);
            long endtime2 = gettime((long)(point2.getX() - x), (long)(point2.getY() - y), aaa);
            endtime2 = endtime1 - endtime2 + 20L;
            if (endtime2 >= endtime1) {
                x = point2.getX();
                y = point2.getY();
                list.remove(0);
                System.out.println("移动结束");
            }
            else {
                x = getmove2(point1.getX(), point2.getX(), endtime2, endtime1);
                y = getmove2(point1.getY(), point2.getY(), endtime2, endtime1);
            }
            if (object instanceof Fightingimage) {
                Fightingimage fightingimage3 = (Fightingimage)object;
                fightingimage3.setX(x);
                fightingimage3.setY(y);
                fightingimage3.setDir(getdirTwo(point2.getX() - point1.getX(), point2.getY() - point1.getY()));
            }
            else if (object instanceof ShadowMode) {
                ShadowMode shadowMode3 = (ShadowMode)object;
                shadowMode3.setX(x);
                shadowMode3.setY(y);
            }
            return list.size() <= 1;
        }
    }
    
    public static long getmovetime(ManimgAttribute attribute, RoleShow roleShow, double sp, long time, int dir) {
        List<PathPoint> list = roleShow.getPlayer_Paths();
        int x = roleShow.getX();
        int y = roleShow.getY();
        while (time > 0L && list.size() > 1) {
            PathPoint point = (PathPoint)list.get(0);
            PathPoint point2 = (PathPoint)list.get(1);
            int dx = point2.getX() - point.getX();
            int dy = point2.getY() - point.getY();
            dir = getdir(dx, dy, dir);
            long endtime = gettime((long)dx, (long)dy, sp);
            if (time >= endtime) {
                time -= endtime;
                x = point2.getX();
                y = point2.getY();
                list.remove(0);
            }
            else {
                x = getmove2(point.getX(), point2.getX(), time, endtime);
                y = getmove2(point.getY(), point2.getY(), time, endtime);
                break;
            }
        }
        roleShow.setX(x);
        roleShow.setY(y);
        attribute.setDir(dir);
        return time;
    }
    
    public static int getmove(int x, int min, int max, long time, long time2) {
        double sp = (double)Math.abs(max - min);
        sp /= (double)time2;
        time = (long)((double)time + (double)Math.abs(x - min) / sp);
        return getmove2(min, max, time, time2);
    }
    
    public static long gettime(long l, long m, double sp) {
        double move = Math.sqrt((double)(l * l + m * m));
        return (long)(move / sp);
    }
    
    public static int getdirTwo(int dx, int dy) {
        int x = Math.abs(dx);
        int y = Math.abs(dy);
        if (dx > 0 && dx > 0) {
            if (x * 3 < y) {
                return 7;
            }
            if (y * 3 < x) {
                return 3;
            }
            return 1;
        }
        else if (dx < 0 && dx < 0) {
            if (x * 3 < y) {
                return 3;
            }
            if (y * 3 < x) {
                return 7;
            }
            return 5;
        }
        else {
            if (dx > 0) {
                return 3;
            }
            return 7;
        }
    }
    
    public static int getdir(int dx, int dy, int dir) {
        if (dx == 0 && dy == 0) {
            return dir;
        }
        int x = Math.abs(dx);
        int y = Math.abs(dy);
        if (dx == 0) {
            if (dy < 0) {
                dir = 6;
            }
            else {
                dir = 4;
            }
        }
        else if (dy == 0) {
            if (dx > 0) {
                dir = 7;
            }
            else {
                dir = 5;
            }
        }
        else if (x > 3 * y) {
            if (dx > 0) {
                dir = 7;
            }
            else {
                dir = 5;
            }
        }
        else if (y > 3 * x) {
            if (dy > 0) {
                dir = 4;
            }
            else {
                dir = 6;
            }
        }
        else if (dx > 0 && dy > 0) {
            dir = 0;
        }
        else if (dx > 0 && dy < 0) {
            dir = 3;
        }
        else if (dx < 0 && dy < 0) {
            dir = 2;
        }
        else if (dx < 0 && dy > 0) {
            dir = 1;
        }
        return dir;
    }
}
