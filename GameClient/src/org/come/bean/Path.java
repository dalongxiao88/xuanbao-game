package org.come.bean;

import com.tool.image.ManimgAttribute;
import org.come.until.Util;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import java.util.List;
import com.tool.image.ImageMixDeal;
import java.util.ArrayList;

public class Path
{
    private static final int[][] DIREC;
    
    public static void findPath(byte[][] bs, int x1, int y1, int x2, int y2) {
        List<Position> openList = new ArrayList<>();
        Position target = null;
        int target_num = -1;
        boolean findFlag = false;
        Position termPos = null;
        Position startPos = new Position(x1, y1, calcH(x1, y1, x2, y2));
        bs[x1][y1] = 3;
        openList.add(startPos);
        do {
            Position currentPos = (Position)openList.get(0);
            for (int i = openList.size() - 1; i >= openList.size() - 9 && i >= 0; --i) {
                if (currentPos.F > ((Position)openList.get(i)).F) {
                    currentPos = (Position)openList.get(i);
                }
            }
            bs[currentPos.row][currentPos.col] = 2;
            openList.remove(currentPos);
            for (int i = 0; i < Path.DIREC.length; ++i) {
                int tmpX = currentPos.row + Path.DIREC[i][0];
                int tmpY = currentPos.col + Path.DIREC[i][1];
                if (tmpX >= 0 && tmpX < bs.length && tmpY >= 0 && tmpY < bs[0].length && bs[tmpX][tmpY] != 2) {
                    if (Around_map(i, tmpX, tmpY)) {
                        bs[tmpX][tmpY] = 1;
                    }
                    else {
                        Position tmpPos = new Position(tmpX, tmpY, calcH(tmpX, tmpY, x2, y2), currentPos);
                        if (bs[tmpX][tmpY] != 3) {
                            bs[tmpX][tmpY] = 3;
                            openList.add(tmpPos);
                        }
                    }
                }
            }
            if (openList.size() > 0) {
                Position tmp = (Position)openList.get(openList.size() - 1);
                int num_tmp = Math.abs(tmp.row - x2) + Math.abs(tmp.col - y2);
                if (num_tmp < target_num || target_num == -1) {
                    target_num = num_tmp;
                    target = tmp;
                }
            }
            int j = openList.size() - 1;
            while (j >= 0 && j >= openList.size() - 9) {
                if (((Position)openList.get(j)).row == x2 && ((Position)openList.get(j)).col == y2) {
                    termPos = (Position)openList.get(j);
                    findFlag = true;
                    break;
                }
                else {
                    --j;
                }
            }
        } while (!findFlag && openList.size() != 0);
        if (!findFlag) {
            termPos = target;
        }
        List<PathPoint> path = new ArrayList<>();
        if (termPos != null) {
            PathPoint a = new PathPoint(termPos.row * 20, termPos.col * 20);
            path.add(a);
            while (termPos.fa != null) {
                termPos = termPos.fa;
                if (termPos.fa != null) {
                    PathPoint a2 = new PathPoint(termPos.row * 20, termPos.col * 20);
                    path.add(a2);
                }
            }
        }
        path.add(new PathPoint(ImageMixDeal.userimg.getRoleShow().getX(), ImageMixDeal.userimg.getRoleShow().getY()));
        List<PathPoint> paths = PathManage(path);
        try {
            sendXandYToServer(paths);
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
    public static void sendXandYToServer(List<PathPoint> Player_Paths) {
        ImageMixDeal.userimg.setMovejiange(0L);
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        roleShow.setPlayer_Paths(Player_Paths);
        RoleMoveBean bean = new RoleMoveBean();
        bean.setPaths(Player_Paths);
        String sendMes = Agreement.getAgreement().moveAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessageUntil.toServer(sendMes);
    }
    
    private static int calcH(int x, int y, int tx, int ty) {
        int diff = Math.abs(x - tx) + Math.abs(y - ty);
        return diff * 10;
    }
    
    public static boolean Around_map(int dir, int tmpx, int tmpy) {
        if (Util.mapmodel.mskpanduan(tmpx, tmpy)) {
            return true;
        }
        if (dir == 0 || dir == 2 || dir == 4 || dir == 6) {
            return false;
        }
        if (dir == 1) {
            return Util.mapmodel.mskpanduan(tmpx + Path.DIREC[6][0], tmpy + Path.DIREC[6][1]) && Util.mapmodel.mskpanduan(tmpx + Path.DIREC[4][0], tmpy + Path.DIREC[4][1]);
        }
        if (dir == 3) {
            return Util.mapmodel.mskpanduan(tmpx + Path.DIREC[0][0], tmpy + Path.DIREC[0][1]) && Util.mapmodel.mskpanduan(tmpx + Path.DIREC[6][0], tmpy + Path.DIREC[6][1]);
        }
        if (dir == 5) {
            return Util.mapmodel.mskpanduan(tmpx + Path.DIREC[0][0], tmpy + Path.DIREC[0][1]) && Util.mapmodel.mskpanduan(tmpx + Path.DIREC[2][0], tmpy + Path.DIREC[2][1]);
        }
        return Util.mapmodel.mskpanduan(tmpx + Path.DIREC[2][0], tmpy + Path.DIREC[2][1]) && Util.mapmodel.mskpanduan(tmpx + Path.DIREC[4][0], tmpy + Path.DIREC[4][1]);
    }
    
    public static List<PathPoint> PathManage(List<PathPoint> Player_Paths) {
        List<PathPoint> paths = new ArrayList<>();
        int size = Player_Paths.size() - 1;
        paths.add(Player_Paths.get(size));
        for (int i = size; i >= 0; --i) {
            PathPoint qidian = (PathPoint)Player_Paths.get(i);
            int j = 0;
            while (j < i) {
                if (keda(qidian, (PathPoint)Player_Paths.get(j))) {
                    i = j + 1;
                    paths.add(Player_Paths.get(j));
                    break;
                }
                else {
                    ++j;
                }
            }
        }
        return paths;
    }
    
    public static boolean keda(PathPoint qishi, PathPoint zhongdian) {
        int dir = ChangDir_1(zhongdian.getX() - qishi.getX(), zhongdian.getY() - qishi.getY());
        int dirs = Playerdir_key(dir, null);
        PathPoint zjd = qishi;
        while (zhongdian.getX() != zjd.getX() || zhongdian.getY() != zjd.getY()) {
            PathPoint[] premove = PreMove(dirs, zjd);
            zjd = AreaMin(premove[0], premove[1], qishi, zhongdian);
            if (!obstacle(zjd)) {
                return false;
            }
        }
        return true;
    }
    
    public static PathPoint AreaMin(PathPoint dian1, PathPoint dian2, PathPoint qishi, PathPoint zhongdian) {
        PathPoint min = null;
        if (Area(dian1, qishi, zhongdian) <= Area(dian2, qishi, zhongdian)) {
            min = dian1;
        }
        else {
            min = dian2;
        }
        return min;
    }
    
    public static int Area(PathPoint qishi, PathPoint zhongdian, PathPoint danqian) {
        int b = qishi.getX() * (zhongdian.getY() - danqian.getY());
        int c = zhongdian.getX() * (danqian.getY() - qishi.getY());
        int a = danqian.getX() * (qishi.getY() - zhongdian.getY());
        int area = Math.abs(a + b + c);
        return area;
    }
    
    public static boolean obstacle(PathPoint xiayidian) {
        return !Util.mapmodel.mskpanduan(xiayidian.getX() / 20, xiayidian.getY() / 20);
    }
    
    public static int ChangDir_1(int dx, int dy) {
        int dir = 8;
        if (dx == 0 && dy == 0) {
            dir = 8;
        }
        else if (dx == 0) {
            if (dy > 0) {
                dir = 4;
            }
            else {
                dir = 0;
            }
        }
        else if (dy == 0) {
            if (dx > 0) {
                dir = 2;
            }
            else {
                dir = 6;
            }
        }
        else {
            int x = Math.abs(dx);
            int y = Math.abs(dy);
            int dir2 = 0;
            if (x == y) {
                dir2 = 0;
            }
            else if (x > y * 3) {
                dir2 = 1;
            }
            else if (y > x * 3) {
                dir2 = 4;
            }
            else if (x > y) {
                dir2 = 2;
            }
            else {
                dir2 = 3;
            }
            if (dy > 0 && dx > 0) {
                if (dir2 == 0) {
                    dir = 3;
                }
                else {
                    dir = 30 + dir2;
                }
            }
            else if (dy < 0 && dx < 0) {
                if (dir2 == 0) {
                    dir = 7;
                }
                else {
                    dir = 70 + dir2;
                }
            }
            else if (dy > 0 && dx < 0) {
                if (dir2 == 0) {
                    dir = 5;
                }
                else {
                    dir = 50 + dir2;
                }
            }
            else if (dir2 == 0) {
                dir = 1;
            }
            else {
                dir = 10 + dir2;
            }
        }
        return dir;
    }
    
    public static boolean panduan(int dir_1, PathPoint danqian, PathPoint zhongdian) {
        int dir = ChangDir_1(zhongdian.getX() - danqian.getX(), zhongdian.getY() - danqian.getY());
        switch (dir_1) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7: {
                if (dir == dir_1) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 11: {
                if (dir == 2 || dir == 11 || dir == 12) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 12:
            case 13: {
                if (dir == 11 || dir == 12 || dir == 13 || dir == 1) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 14: {
                if (dir == 0 || dir == 13 || dir == 14) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 31: {
                if (dir == 2 || dir == 31 || dir == 32) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 32:
            case 33: {
                if (dir == 31 || dir == 32 || dir == 33 || dir == 3) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 34: {
                if (dir == 4 || dir == 33 || dir == 34) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 51: {
                if (dir == 6 || dir == 51 || dir == 52) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 52:
            case 53: {
                if (dir == 51 || dir == 52 || dir == 53 || dir == 5) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 54: {
                if (dir == 4 || dir == 53 || dir == 54) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 71: {
                if (dir == 6 || dir == 71 || dir == 72) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 72:
            case 73: {
                if (dir == 71 || dir == 72 || dir == 73 || dir == 7) {
                    return true;
                }
                else {
                    break;
                }
            }
            case 74: {
                if (dir == 0 || dir == 73 || dir == 74) {
                    return true;
                }
                else {
                    break;
                }
            }
        }
        return false;
    }
    
    public static PathPoint[] PreMove(int dir, PathPoint pathPoint) {
        PathPoint[] points = new PathPoint[2];
        int x = pathPoint.getX();
        int y = pathPoint.getY();
        switch (dir) {
            case 0: {
                points[0] = new PathPoint(x, y - 1);
                points[1] = new PathPoint(x, y - 1);
                break;
            }
            case 1: {
                points[0] = new PathPoint(x + 1, y - 1);
                points[1] = new PathPoint(x + 1, y - 1);
                break;
            }
            case 2: {
                points[0] = new PathPoint(x + 1, y);
                points[1] = new PathPoint(x + 1, y);
                break;
            }
            case 3: {
                points[0] = new PathPoint(x + 1, y + 1);
                points[1] = new PathPoint(x + 1, y + 1);
                break;
            }
            case 4: {
                points[0] = new PathPoint(x, y + 1);
                points[1] = new PathPoint(x, y + 1);
                break;
            }
            case 5: {
                points[0] = new PathPoint(x - 1, y + 1);
                points[1] = new PathPoint(x - 1, y + 1);
                break;
            }
            case 6: {
                points[0] = new PathPoint(x - 1, y);
                points[1] = new PathPoint(x - 1, y);
                break;
            }
            case 7: {
                points[0] = new PathPoint(x - 1, y - 1);
                points[1] = new PathPoint(x - 1, y - 1);
                break;
            }
            case 12: {
                points[0] = new PathPoint(x, y - 1);
                points[1] = new PathPoint(x + 1, y - 1);
                break;
            }
            case 11: {
                points[0] = new PathPoint(x + 1, y - 1);
                points[1] = new PathPoint(x + 1, y);
                break;
            }
            case 31: {
                points[0] = new PathPoint(x + 1, y);
                points[1] = new PathPoint(x + 1, y + 1);
                break;
            }
            case 32: {
                points[0] = new PathPoint(x + 1, y + 1);
                points[1] = new PathPoint(x, y + 1);
                break;
            }
            case 52: {
                points[0] = new PathPoint(x, y + 1);
                points[1] = new PathPoint(x - 1, y + 1);
                break;
            }
            case 51: {
                points[0] = new PathPoint(x - 1, y + 1);
                points[1] = new PathPoint(x - 1, y);
                break;
            }
            case 71: {
                points[0] = new PathPoint(x - 1, y);
                points[1] = new PathPoint(x - 1, y - 1);
                break;
            }
            case 72: {
                points[0] = new PathPoint(x - 1, y - 1);
                points[1] = new PathPoint(x, y - 1);
                break;
            }
        }
        return points;
    }
    
    public static int Playerdir_key(int dir, ManimgAttribute manmove) {
        switch (dir) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7: {
                return dir;
            }
            case 11: {
                return 11;
            }
            case 12: {
                return 11;
            }
            case 13: {
                return 12;
            }
            case 14: {
                return 12;
            }
            case 31: {
                return 31;
            }
            case 32: {
                return 31;
            }
            case 33: {
                return 32;
            }
            case 34: {
                return 32;
            }
            case 51: {
                return 51;
            }
            case 52: {
                return 51;
            }
            case 53: {
                return 52;
            }
            case 54: {
                return 52;
            }
            case 71: {
                return 71;
            }
            case 72: {
                return 71;
            }
            case 73: {
                return 72;
            }
            case 74: {
                return 72;
            }
            default: {
                return dir;
            }
        }
    }
    
    static {
        DIREC = new int[][] { { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { -1, -1 } };
    }
    
    static class Position
    {
        public int F;
        public int G;
        public int H;
        public Position fa;
        public int row;
        public int col;
        
        public Position() {
        }
        
        public Position(int row, int col, int H) {
            this(row, col, H, null);
        }
        
        public Position(int row, int col, int H, Position pos) {
            this.H = H;
            this.row = row;
            this.col = col;
            this.fa = pos;
            this.G = this.calcG();
            this.F = this.G + H;
        }
        
        private int calcG() {
            if (this.fa == null) {
                return 0;
            }
            if (this.fa.row != this.row && this.fa.col != this.col) {
                return 14 + this.fa.G;
            }
            return 10 + this.fa.G;
        }
        
        public void setFaPos(Position pos) {
            this.fa = pos;
            this.G = this.calcG();
            this.F = this.G + this.H;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Position)) {
                return false;
            }
            Position pos = (Position)obj;
            return this.row == pos.row && this.col == pos.col;
        }
        
        @Override
        public int hashCode() {
            int prime = 31;
            int result = 1;
            result = 31 * result + this.row;
            result = 31 * result + this.col;
            return result;
        }
    }
}
