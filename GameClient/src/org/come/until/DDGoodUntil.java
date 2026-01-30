package org.come.until;

import java.util.ArrayList;
import java.util.Collections;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import org.come.entity.Good;
import java.util.List;

public class DDGoodUntil
{
    public static List<Good> ddgood;
    public static int ys;
    
    public static void draw(Graphics g, int x, int y) {
        g.setFont(UIUtils.TEXT_FONT);
        g.setColor(Color.red);
        int p = (DDGoodUntil.ys - 1) * 42;
        for (int i = 0; i < 42; ++i) {
            if (p >= DDGoodUntil.ddgood.size()) {
                return;
            }
            Good good = (Good)DDGoodUntil.ddgood.get(p);
            int row = i % 6;
            int col = i / 6;
            ImageIcon icon = GoodsListFromServerUntil.imgpath(good.getGoodstable().getSkin());
            g.drawImage(icon.getImage(), x + row * 51 + 2, y + col * 51 + 2, 45, 45, null);
            if (!EquipTool.isEquip(good.getGoodstable().getType())) {
                g.setFont(new Font("宋体", 1, 14));
                g.setColor(Color.WHITE);
                g.drawString("" + good.getGoodstable().getUsetime(), x + 3 + row * 51, y + 13 + col * 51);
            }
            ++p;
        }
    }
    
    public static void addddgood(Goodstable goodstable) {
        for (int i = 0; i < DDGoodUntil.ddgood.size(); ++i) {
            if (((Good)DDGoodUntil.ddgood.get(i)).getGoodstable().getRgid().compareTo(goodstable.getRgid()) == 0) {
                ((Good)DDGoodUntil.ddgood.get(i)).setGoodstable(goodstable);
                return;
            }
        }
        Good good = new Good();
        good.setGoodstable(goodstable);
        DDGoodUntil.ddgood.add(good);
    }
    
    public static void Retrieve(int i) {
        Goodstable goodstable = getgood(i);
        if (goodstable != null) {
            int sum = GoodsListFromServerUntil.Surplussum("" + goodstable.getType(), "" + goodstable.getGoodsid(), (int)goodstable.getUsetime());
            if (sum <= 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你背包已满");
            }
            else {
                DDGoodUntil.ddgood.remove((DDGoodUntil.ys - 1) * 42 + i);
                String mes = Agreement.getAgreement().RecivePawnAgreement(goodstable.getRgid().toString());
                SendMessageUntil.toServer(mes);
            }
        }
    }
    
    public static Goodstable getgood(int i) {
        i += (DDGoodUntil.ys - 1) * 42;
        return (i >= DDGoodUntil.ddgood.size()) ? null : ((Good)DDGoodUntil.ddgood.get(i)).getGoodstable();
    }
    
    public static void zhengli() {
        Collections.sort(DDGoodUntil.ddgood);
    }
    
    static {
        DDGoodUntil.ddgood = new ArrayList<>();
        DDGoodUntil.ys = 1;
    }
}
