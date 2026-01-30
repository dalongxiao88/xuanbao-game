package org.come.until;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;
import com.updateNew.MyIsif;
import com.tool.role.RoleProperty;
import com.tool.role.GetExp;
import org.come.bean.LoginResult;
import com.tool.pet.PetProperty;
import org.come.entity.RoleSummoning;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import come.tool.Fighting.FightingMixDeal;
import come.tool.Fighting.FightingManData;
import javax.swing.ImageIcon;

public class Article
{
    public static ImageIcon[] xuelans;
    
    public static void fightingarticlehp(FightingManData data, int hp_Current) {
        try {
            if (data.getType() == 0) {
                if (FightingMixDeal.isFightType()) {
                    RoleData.getRoleData().getLoginResult().setHp(new BigDecimal(hp_Current));
                }
                ZhuFrame.getZhuJpanel().getRolehp().setIcon(articlesize((long)hp_Current, (long)data.getHp_Total(), 0, 0));
            }
            else if (data.getType() == 1) {
                RoleSummoning bb = bb(data.getId());
                if (bb != null) {
                    if (FightingMixDeal.isFightType()) {
                        xiugaibb(bb, hp_Current, data.getMp_Current());
                    }
                    ZhuFrame.getZhuJpanel().getBbhp().setIcon(articlesize((long)hp_Current, (long)data.getHp_Total(), 0, 1));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void fightingarticlemp(FightingManData data, int mp_Current) {
        try {
            if (data.getType() == 0) {
                if (FightingMixDeal.isFightType()) {
                    RoleData.getRoleData().getLoginResult().setMp(new BigDecimal(mp_Current));
                }
                ZhuFrame.getZhuJpanel().getRolemp().setIcon(articlesize((long)mp_Current, (long)data.getMp_Total(), 1, 0));
            }
            else if (data.getType() == 1) {
                RoleSummoning bb = bb(data.getId());
                if (bb != null) {
                    if (FightingMixDeal.isFightType()) {
                        xiugaibb(bb, data.getHp_Current(), mp_Current);
                    }
                    ZhuFrame.getZhuJpanel().getBbmp().setIcon(articlesize((long)mp_Current, (long)data.getMp_Total(), 1, 1));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static RoleSummoning bb(int id) {
        for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
            if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid().intValue() == id) {
                return (RoleSummoning)UserMessUntil.getPetListTable().get(i);
            }
        }
        return null;
    }
    
    public static void xiugaibb(RoleSummoning bb, int hp_Current, int mp_Current) {
        if (bb != null) {
            int[] pets = PetProperty.getPetHMASp(bb);
            if (hp_Current >= pets[0]) {
                bb.setBasishp((long)pets[0]);
            }
            else if (hp_Current <= 0) {
                bb.setBasishp(0L);
            }
            else {
                bb.setBasishp((long)hp_Current);
            }
            if (mp_Current >= pets[1]) {
                bb.setBasismp((long)pets[1]);
            }
            else if (mp_Current <= 0) {
                bb.setBasismp(0L);
            }
            else {
                bb.setBasismp((long)mp_Current);
            }
        }
    }
    
    public static int xiugai(int hp_Total, int hp_Current, int c) {
        if (hp_Current + c > hp_Total) {
            return hp_Total;
        }
        if (hp_Current + c < 0) {
            return 0;
        }
        return hp_Current + c;
    }
    
    public static void manxie(LoginResult loginResult) {
        try {
            ZhuFrame.getZhuJpanel().getRoleexe().setIcon(articlesize(loginResult.getExperience().longValue(), GetExp.getRoleExp(loginResult.getTurnAround(), AnalysisString.lvlint((int)loginResult.getGrade())), 2, 0));
            ZhuFrame.getZhuJpanel().getRolehp().setIcon(articlesize(loginResult.getHp().longValue(), (long)RoleProperty.getHp(loginResult), 0, 0));
            ZhuFrame.getZhuJpanel().getRolemp().setIcon(articlesize(loginResult.getMp().longValue(), (long)RoleProperty.getMp(loginResult), 1, 0));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void souxie(RoleSummoning pet) {
        try {
            if (pet != null) {
                ZhuFrame.getZhuJpanel().getBbexe().setIcon(articlesize(pet.getExp().longValue(), GetExp.getBBExp(pet.getTurnRount(), AnalysisString.petLvlint((int)pet.getGrade())), 2, 1));
                int[] pets = PetProperty.getPetHMASp(pet);
                ZhuFrame.getZhuJpanel().getBbhp().setIcon(articlesize(pet.getBasishp(), (long)pets[0], 0, 1));
                ZhuFrame.getZhuJpanel().getBbmp().setIcon(articlesize(pet.getBasismp(), (long)pets[1], 1, 1));
            }
            else {
                ZhuFrame.getZhuJpanel().getBbexe().setIcon(articlesize(0L, 100L, 2, 1));
                ZhuFrame.getZhuJpanel().getBbhp().setIcon(articlesize(0L, 100L, 0, 1));
                ZhuFrame.getZhuJpanel().getBbmp().setIcon(articlesize(0L, 0L, 1, 1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ImageIcon articlesize(long min, long max, int type, int leixing) throws Exception {
        ImageIcon di = Article.xuelans[0];
        ImageIcon shang = Article.xuelans[type + 1];
        if (min > max) {
            min = max;
        }
        int size = (int)((double)di.getIconWidth() * ((double)min / (double)max)) - 1;
        Image image = ImageCut(di, shang, size, di.getIconHeight()).getImage();
        if (MyIsif.getStyle().equals("水墨")) {
            if (leixing == 0) {
                Image ima = image.getScaledInstance(99, 12, 1);
                return new ImageIcon(ima);
            }
            Image ima = image.getScaledInstance(71, 9, 1);
            return new ImageIcon(ima);
        }
        else {
            if (leixing == 0) {
                Image ima = image.getScaledInstance(99, 12, 1);
                return new ImageIcon(ima);
            }
            Image ima = image.getScaledInstance(71, 10, 1);
            return new ImageIcon(ima);
        }
    }
    
    public static ImageIcon ImageCut(ImageIcon imageIcon, ImageIcon imageIcon2, int x, int y) throws Exception {
        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), 6);
        Graphics2D g2D = (Graphics2D)bufferedImage.getGraphics();
        g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
        BufferedImage bufferedImage2 = new BufferedImage(imageIcon2.getIconWidth(), imageIcon2.getIconHeight(), 6);
        Graphics2D g2D2 = (Graphics2D)bufferedImage2.getGraphics();
        g2D2.drawImage(imageIcon2.getImage(), 0, 0, imageIcon2.getImageObserver());
        for (int j1 = 0; j1 < x; ++j1) {
            for (int j2 = 0; j2 < y; ++j2) {
                int pixel = bufferedImage2.getRGB(j1, j2);
                if (pixel != 0) {
                    bufferedImage.setRGB(j1, j2, pixel);
                }
            }
        }
        g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
        return new ImageIcon(bufferedImage);
    }
    
    static {
        if (MyIsif.getStyle().equals("水墨")) {
            (Article.xuelans = new ImageIcon[4])[0] = new ImageIcon("inkImg/background/S2999.png");
            Article.xuelans[1] = new ImageIcon("inkImg/background1/S29.png");
            Article.xuelans[2] = new ImageIcon("inkImg/background1/S28.png");
            Article.xuelans[3] = new ImageIcon("inkImg/background1/S27.png");
        }
        else {
            (Article.xuelans = new ImageIcon[4])[0] = new ImageIcon("img/xy2uiimg/27_png.xy2uiimg.empty.png");
            Article.xuelans[1] = new ImageIcon("inkImg/hongmu/S29.png");
            Article.xuelans[2] = new ImageIcon("inkImg/hongmu/S28.png");
            Article.xuelans[3] = new ImageIcon("inkImg/hongmu/S27.png");
        }
    }
}
