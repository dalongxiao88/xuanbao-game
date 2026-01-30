package come.tool.Mixdeal;

public class PetGrade
{
    public static void main(String[] args) {
        int lvl = 120;
        int base = 500;
        double G = 2.0;
        System.out.println("血" + getRoleValue(lvl, lvl * 8, G, base, 1));
        System.out.println("攻" + getRoleValue(lvl, lvl * 8, G, base, 2));
        System.out.println("敏" + getRoleValue(lvl, lvl * 8, G, base, 3));
    }
    
    public static int getRoleValue(int lvl, int P, double G, int base, int type) {
        if (type == 0 || type == 1) {
            return (int)((double)(lvl * P) * G) + (int)((0.7 * (double)lvl * G + 1.0) * (double)base);
        }
        if (type == 2) {
            return (int)((double)(lvl * P) * G / 5.0) + (int)((0.14 * (double)lvl * G + 1.0) * (double)base);
        }
        return (int)((double)(base + P / 2) * G);
    }
}
